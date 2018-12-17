package com.hnam.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hnam.domain.interactor.bookmark.BookmarkProject
import com.hnam.domain.interactor.bookmark.UnbookmarkProject
import com.hnam.domain.interactor.browse.GetProjects
import com.hnam.domain.model.Project
import com.hnam.presentation.mapper.ProjectViewMapper
import com.hnam.presentation.model.ProjectView
import com.hnam.presentation.state.Resource
import com.hnam.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by nampham on 12/17/18.
 */
open class BrowseProjectsViewModel @Inject internal constructor(
    private val getProjects: GetProjects?,
    private val bookmarkProject: BookmarkProject,
    private val unBookmarkProject: UnbookmarkProject,
    private val mapper: ProjectViewMapper
): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getProjects?.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getProjects?.execute(ProjectsSubscriber())
    }

    fun bookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return bookmarkProject.execute(BookmarkProjectsSubscriber(),
            BookmarkProject.Params.forProject(projectId))
    }

    fun unbookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return unBookmarkProject.execute(BookmarkProjectsSubscriber(),
            UnbookmarkProject.Params.forProject(projectId))
    }

    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                t.map { mapper.mapToView(it) }, null))
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

    inner class BookmarkProjectsSubscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data,
                e.localizedMessage))
        }

    }
}