package com.hnam.data

import com.hnam.data.mapper.ProjectMapper
import com.hnam.data.repository.ProjectsCache
import com.hnam.data.store.ProjectDataStoreFactory
import com.hnam.domain.model.Project
import com.hnam.domain.repository.ProjectRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

import javax.inject.Inject

/**
 * Created by nampham on 12/13/18.
 */
class ProjectDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectsCache,
    private val factory: ProjectDataStoreFactory
) : ProjectRepository {
    override fun getProjects(): Observable<List<Project>> {
//        return Observable.zip(cache.areProjectsCached().toObservable(),
//            cache.isProjectsCacheExpired().toObservable(),
//            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
//                Pair(areCached, isExpired)
//            })
//            .flatMap {
//                factory.getDataStore(it.first, it.second).getProjects()
//            }
//            .flatMap { projects ->
//                factory.getCacheDataStore()
//                    .saveProjects(projects)
//                    .andThen(Observable.just(projects))
//            }
//            .map {
//                it.map {
//                    mapper.mapFromEntity(it)
//                }
//            }
        return factory.getDataStore(false, true).getProjects()
            .flatMap { projects ->
                factory.getCacheDataStore()
                    .saveProjects(projects)
                    .andThen(Observable.just(projects))
            }
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
            .map {
                it.map { mapper.mapFromEntity(it) }
            }
    }
}