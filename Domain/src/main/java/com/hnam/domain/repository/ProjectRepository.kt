package com.hnam.domain.repository

import com.hnam.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by nampham on 12/9/18.
 */
interface ProjectRepository {

    fun getProjects() : Observable<List<Project>>

    fun bookmarkProject(projectId : String) : Completable

    fun unbookmarkProject(projectId: String) : Completable

    fun getBookmarkedProjects(): Observable<List<Project>>
}