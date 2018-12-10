package com.hnam.domain.interactor.bookmark

import com.hnam.domain.executor.PostExecutionThread
import com.hnam.domain.model.Project
import com.hnam.domain.repository.ProjectRepository
import com.hnam.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by nampham on 12/11/18.
 */
class GetBookmarkedProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectRepository, postExecutionThread)
    }

    @Test
    fun getProjectsCompletes(){
        whenever(projectRepository.getBookmarkedProjects())
            .thenReturn(Observable.just(ProjectDataFactory.makeProjectList(3)))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnValue(){
        val data = ProjectDataFactory.makeProjectList(4)
        stubGetProjects(Observable.just(data))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertValue(data)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>){
        whenever(projectRepository.getBookmarkedProjects())
            .thenReturn(observable)
    }

}