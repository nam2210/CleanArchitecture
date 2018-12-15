package com.hnam.data.store

import com.hnam.data.model.ProjectEntity
import com.hnam.data.repository.ProjectCache
import com.hnam.data.test.factory.DataFactory
import com.hnam.data.test.factory.ProjectFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by nampham on 12/15/18.
 */
@RunWith(JUnit4::class)
class ProjectsCacheDataStoreTest {
    private val cache  = mock<ProjectCache>()
    private val projecCacheDataStore = ProjectCacheDataStore(cache)


    @Test
    fun getProjectsCompletes() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObservable = projecCacheDataStore.getProjects().test()
        testObservable.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val entity = ProjectFactory.makeProjectEntity()
        stubProjectsCacheGetProjects(Observable.just(listOf(entity)))
        val testObservable = projecCacheDataStore.getProjects().test()
        testObservable.assertValue(listOf(entity))
    }

    @Test
    fun getProjectsCallsCacheSource() {
        //val entity = ProjectFactory.makeProjectEntity()
        //stubProjectsCacheGetProjects(Observable.just(listOf(entity)))
        projecCacheDataStore.getProjects()
        verify(cache).getProjects()
    }

    @Test
    fun saveProjectsCompletes() {
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())
        val completable = projecCacheDataStore.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
        completable.assertComplete()
    }

    @Test
    fun saveProjectsCallsCacheStore() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())
        projecCacheDataStore.saveProjects(data)
        verify(cache).saveProjects(data)
        verify(cache).setLastCacheTime(any())
    }


    @Test
    fun clearProjectsCompletes() {
        stubProjectsClearProjects(Completable.complete())
        val completable = projecCacheDataStore.clearProjects().test()
        completable.assertComplete()
    }

    @Test
    fun clearProjectsCallsCacheStore() {
        projecCacheDataStore.clearProjects()
        verify(cache).clearProjects()
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectsCacheGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val observableTest = projecCacheDataStore.getBookmarkedProjects().test()
        observableTest.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsCallsCacheStore() {
        stubProjectsCacheGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        projecCacheDataStore.getBookmarkedProjects().test()
        verify(cache).getBookmarkedProjects()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetBookmarkedProjects(Observable.just(data))
        val observableTest = projecCacheDataStore.getBookmarkedProjects().test()
        observableTest.assertValue(data)
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())
        val completable = projecCacheDataStore.setProjectAsBookmarked(DataFactory.randomString()).test()
        completable.assertComplete()
    }

    @Test
    fun setProjectAsBookmarkedCallsCacheStore() {
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())
        projecCacheDataStore.setProjectAsBookmarked(DataFactory.randomString()).test()
        verify(cache).setProjectAsBookmarked(any())
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        val completable = projecCacheDataStore.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        completable.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCallsCacheStore(){
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        projecCacheDataStore.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        verify(cache).setProjectAsNotBookmarked(any())
    }


    private fun stubProjectsCacheGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getProjects())
            .thenReturn(observable)
    }

    private fun stubProjectsCacheSaveProjects(completable: Completable) {
        whenever(cache.saveProjects(any()))
            .thenReturn(completable)
    }

    private fun stubProjectsCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
            .thenReturn(completable)
    }

    private fun stubProjectsClearProjects(completable: Completable) {
        whenever(cache.clearProjects())
            .thenReturn(completable)
    }

    private fun stubProjectsCacheGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getBookmarkedProjects())
            .thenReturn(observable)
    }

    private fun stubProjectsCacheSetProjectAsBookmarked(completable: Completable) {
        whenever(cache.setProjectAsBookmarked(any()))
            .thenReturn(completable)
    }

    private fun stubProjectsCacheSetProjectAsNotBookmarked(completable: Completable) {
        whenever(cache.setProjectAsNotBookmarked(any()))
            .thenReturn(completable)
    }

}