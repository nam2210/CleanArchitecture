package com.hnam.domain.interactor.browse

import com.hnam.domain.executor.PostExecutionThread
import com.hnam.domain.interactor.browse.GetProjects
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
class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getProjectsCompletes(){
        whenever(projectsRepository.getProjects())
            .thenReturn(Observable.just(ProjectDataFactory.makeProjectList(3)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnValue(){
        val data = ProjectDataFactory.makeProjectList(4)
        stubGetProjects(Observable.just(data))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(data)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>){
        whenever(projectsRepository.getProjects())
            .thenReturn(observable)
    }

}