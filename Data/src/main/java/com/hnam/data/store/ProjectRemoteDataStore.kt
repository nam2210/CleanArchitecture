package com.hnam.data.store

import com.hnam.data.model.ProjectEntity
import com.hnam.data.repository.ProjectDataStore
import com.hnam.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by nampham on 12/12/18.
 */
class ProjectRemoteDataStore @Inject constructor(private val projectRemote: ProjectsRemote) : ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here...")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Getting bookmarked projects isn't supported here...")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }
}