package com.hnam.data.repository

import com.hnam.data.model.ProjectEntity
import io.reactivex.Observable

/**
 * Created by nampham on 12/12/18.
 */
interface ProjectRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}