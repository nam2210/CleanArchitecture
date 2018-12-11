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
class BookmarkedProjectsTest {

    private lateinit var bookmarkedProjects: BookmarkProject
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        bookmarkedProjects = BookmarkProject(projectRepository, postExecutionThread)
    }

    @Test
    fun bookMarkCompletes(){
        stubGetProjects(Completable.complete())
        val testObserver = bookmarkedProjects.buildUseCaseCompletable(
            BookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowException(){
        bookmarkedProjects.buildUseCaseCompletable().test()
    }


    private fun stubGetProjects(completable: Completable){
        whenever(projectRepository.bookmarkProject(any()))
            .thenReturn(completable)
    }

}