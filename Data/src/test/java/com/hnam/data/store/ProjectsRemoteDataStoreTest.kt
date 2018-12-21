package com.hnam.data.store

import com.hnam.data.model.ProjectEntity
import com.hnam.data.repository.ProjectsRemote
import com.hnam.data.test.factory.DataFactory
import com.hnam.data.test.factory.ProjectFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by nampham on 12/15/18.
 */
@RunWith(JUnit4::class)
class ProjectsRemoteDataStoreTest {

    private val remote = mock<ProjectsRemote>()

    private val store = ProjectRemoteDataStore(remote)

    @Test
    fun getProjectsCompletes() {
        stubRemoteGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val observableTest = store.getProjects().test()
        observableTest.assertComplete()
    }

    @Test
    fun getProjectsReturnsData(){
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubRemoteGetProjects(Observable.just(data))
        val observableTest = store.getProjects().test()
        observableTest.assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException() {
        store.clearProjects().test()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsBookmarkedThrowsException() {
        store.setProjectAsBookmarked(DataFactory.randomString()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsNotBookmarkedThrowsException() {
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowsException() {
        store.getBookmarkedProjects().test()
    }

    private fun stubRemoteGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(remote.getProjects())
            .thenReturn(observable)
    }
}