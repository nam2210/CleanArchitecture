package com.hnam.data.repository

import com.hnam.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by nampham on 12/12/18.
 */
interface ProjectDataStore {
    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

}