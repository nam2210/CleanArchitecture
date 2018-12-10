package com.hnam.domain.interactor.bookmark

import com.hnam.domain.executor.PostExecutionThread
import com.hnam.domain.interactor.CompletableUseCase
import com.hnam.domain.repository.ProjectRepository
import io.reactivex.Completable
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Created by nampham on 12/10/18.
 */
open class UnbookmarkProject @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) :CompletableUseCase<UnbookmarkProject.Params>(postExecutionThread){
    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return projectRepository.unbookmarkProject(params.projectId)
    }


    data class Params(val projectId : String){
        companion object {
            fun forProject(projectId: String) : Params{
                return Params(projectId)
            }
        }
    }
}