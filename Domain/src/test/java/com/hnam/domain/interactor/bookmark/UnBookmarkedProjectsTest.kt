package com.hnam.domain.interactor.bookmark

import com.hnam.domain.executor.PostExecutionThread
import com.hnam.domain.model.Project
import com.hnam.domain.repository.ProjectRepository
import com.hnam.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException

/**
 * Created by nampham on 12/11/18.
 */
class UnBookmarkedProjectsTest {

    private lateinit var unbookmarkProjects: UnbookmarkProject
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        unbookmarkProjects = UnbookmarkProject(projectRepository, postExecutionThread)
    }

    @Test
    fun bookMarkCompletes(){
        stubGetProjects(Completable.complete())
        val testObserver = unbookmarkProjects.buildUseCaseCompletable(
            UnbookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowException(){
        unbookmarkProjects.buildUseCaseCompletable().test()
    }


    private fun stubGetProjects(completable: Completable){
        whenever(projectRepository.unbookmarkProject(any()))
            .thenReturn(completable)
    }

}