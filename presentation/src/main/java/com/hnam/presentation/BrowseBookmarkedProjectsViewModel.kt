package com.hnam.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hnam.domain.interactor.bookmark.GetBookmarkedProjects
import com.hnam.domain.model.Project
import com.hnam.presentation.mapper.ProjectViewMapper
import com.hnam.presentation.model.ProjectView
import com.hnam.presentation.state.Resource
import com.hnam.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by nampham on 12/17/18.
 */
class BrowseBookmarkedProjectsViewModel @Inject constructor(
    private val getBookmarkedProjects: GetBookmarkedProjects,
    private val mapper: ProjectViewMapper
): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> =
        MutableLiveData()

    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookmarkedProjects.execute(ProjectsSubscriber())
    }

    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null,
                e.localizedMessage))
        }

        override fun onComplete() { }

    }

}