package com.hnam.data.store

import com.hnam.data.model.ProjectEntity
import com.hnam.data.repository.ProjectCache
import com.hnam.data.repository.ProjectDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by nampham on 12/12/18.
 */
class ProjectCacheDataStore @Inject constructor(private val projectCache: ProjectCache): ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectCache.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectCache.saveProjects(projects)
            .andThen(projectCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectCache.clearProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectCache.setProjectAsNotBookmarked(projectId)
    }
}