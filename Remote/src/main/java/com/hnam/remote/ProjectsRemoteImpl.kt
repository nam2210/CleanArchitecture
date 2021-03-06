package com.hnam.remote

import com.hnam.data.model.ProjectEntity
import com.hnam.data.repository.ProjectsRemote
import com.hnam.remote.mapper.ProjectsResponseModelMapper
import com.hnam.remote.services.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by nampham on 12/16/18.
 */
open class ProjectsRemoteImpl @Inject constructor(val service: GithubTrendingService,
                                             val mapper: ProjectsResponseModelMapper) : ProjectsRemote{
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map {
                it.items.map { i -> mapper.mapFromModel(i) }
            }
    }
}