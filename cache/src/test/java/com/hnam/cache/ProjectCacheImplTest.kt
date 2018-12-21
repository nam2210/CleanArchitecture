package com.hnam.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.hnam.cache.db.ProjectsDatabase
import com.hnam.cache.mapper.CachedProjectMapper
import com.hnam.cache.test.factory.ProjectDataFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by nampham on 12/17/18.
 */
@RunWith(RobolectricTestRunner::class)
class ProjectCacheImplTest {
    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val datatabase = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        ProjectsDatabase::class.java).allowMainThreadQueries().build()
    private val mapper = CachedProjectMapper()
    private val cache = ProjectsCacheImpl(datatabase, mapper)

    @Test
    fun clearTablesCompletes(){
        val completable = cache.clearProjects().test()
        completable.assertComplete()
    }

    @Test
    fun saveProjectsCompletes(){
        val data = listOf(ProjectDataFactory.makeProjectEntity())
        val observable = cache.saveProjects(data).test()
        observable.assertComplete()
    }

    @Test
    fun getProjectsReturnsData(){
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.getProjects().test()
        testObserver.assertValue(projects)
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val bookmarkedProject = ProjectDataFactory.makeBookmarkedProjectEntity()
        val projects = listOf(ProjectDataFactory.makeProjectEntity(),
            bookmarkedProject)
        cache.saveProjects(projects).test()

        val testObserver = cache.getBookmarkedProjects().test()
        testObserver.assertValue(listOf(bookmarkedProject))
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.setProjectAsBookmarked(projects[0].id).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        val projects = listOf(ProjectDataFactory.makeBookmarkedProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.setProjectAsNotBookmarked(projects[0].id).test()
        testObserver.assertComplete()
    }

    @Test
    fun areProjectsCacheReturnsData() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.areProjectsCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(1000L).test()
        testObserver.assertComplete()
    }

    @Test
    fun isProjectsCacheExpiredReturnsExpired() {
        val testObserver = cache.isProjectsCacheExpired().test()
        testObserver.assertValue(true)
    }

    @Test
    fun isProjectsCacheExpiredReturnsNotExpired() {
        cache.setLastCacheTime(System.currentTimeMillis() - 1000).test()
        val testObserver = cache.isProjectsCacheExpired().test()
        testObserver.assertValue(false)
    }
}