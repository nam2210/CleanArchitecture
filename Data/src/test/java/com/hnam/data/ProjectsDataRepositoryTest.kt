package com.hnam.data

import com.hnam.data.mapper.ProjectMapper
import com.hnam.data.model.ProjectEntity
import com.hnam.data.repository.ProjectsCache
import com.hnam.data.repository.ProjectDataStore
import com.hnam.data.store.ProjectDataStoreFactory
import com.hnam.data.test.factory.DataFactory
import com.hnam.data.test.factory.ProjectFactory
import com.hnam.domain.model.Project
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by nampham on 12/14/18.
 */
@RunWith(JUnit4::class)
class ProjectsDataRepositoryTest {

    private val mapper = mock<ProjectMapper>()
    private val factory = mock<ProjectDataStoreFactory>()
    private val cache = mock<ProjectsCache>()
    private val repository = ProjectDataRepository(mapper, cache, factory)
    private val store = mock<ProjectDataStore>()


    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreProjectsCached(Single.just(false))
        stubSaveProjects(Completable.complete())
    }

    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), any())

        val testObserver = repository.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()

        stubGetProjects(Observable.just(listOf(projectEntity)))
        stubMapper(project, projectEntity)

        val testObserver = repository.getProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), any())

        val testObservable = repository.getBookmarkedProjects().test()
        testObservable.assertComplete()
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubBookmarkProject(Completable.complete())

        val testCompletable = repository.bookmarkProject(DataFactory.randomString()).test()
        testCompletable.assertComplete()
    }

    @Test
    fun unbookmarkProjectCompletes() {
        stubUnBookmarkProject(Completable.complete())

        val testCompletable = repository.unbookmarkProject(DataFactory.randomString()).test()
        testCompletable.assertComplete()
    }



    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isProjectsCacheExpired())
            .thenReturn(single)
    }

    private fun stubAreProjectsCached(single: Single<Boolean>) {
        whenever(cache.areProjectsCached())
            .thenReturn(single)
    }

    private fun stubMapper(model: Project, entity: ProjectEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(model)
    }

    private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getProjects())
            .thenReturn(observable)
    }

    private fun stubGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getBookmarkedProjects())
            .thenReturn(observable)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
            .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
            .thenReturn(store)
    }

    private fun stubSaveProjects(completable: Completable) {
        whenever(store.saveProjects(any()))
            .thenReturn(completable)
    }

    private fun stubBookmarkProject(completable: Completable) {
        whenever(store.setProjectAsBookmarked(any()))
            .thenReturn(completable)
    }

    private fun stubUnBookmarkProject(completable: Completable) {
        whenever(store.setProjectAsNotBookmarked(any()))
            .thenReturn(completable)
    }
}