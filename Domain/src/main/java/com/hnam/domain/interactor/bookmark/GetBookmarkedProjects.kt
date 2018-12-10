package com.hnam.domain.interactor.bookmark

import com.hnam.domain.executor.PostExecutionThread
import com.hnam.domain.interactor.ObservableUseCase
import com.hnam.domain.model.Project
import com.hnam.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by nampham on 12/10/18.
 */
open class GetBookmarkedProjects @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread){
    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getBookmarkedProjects()
    }
}