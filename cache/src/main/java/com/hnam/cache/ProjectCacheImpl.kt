package com.hnam.cache

import com.hnam.cache.db.ProjectsDatabase
import com.hnam.cache.mapper.CachedProjectMapper
import com.hnam.cache.model.Config
import com.hnam.data.mapper.ProjectMapper
import com.hnam.data.model.ProjectEntity
import com.hnam.data.repository.ProjectCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by nampham on 12/17/18.
 */
class ProjectCacheImpl @Inject constructor(
    private val db : ProjectsDatabase,
    private val mapper: CachedProjectMapper
): ProjectCache {
    override fun clearProjects(): Completable {
        return Completable.defer {
            db.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            db.cachedProjectsDao().insertProjects(projects.map {
                mapper.mapToCached(it)
            })
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return db.cachedProjectsDao()
                .getProjects()
                .map {
                    it.map { i -> mapper.mapFromCached(i) }
                }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return db.cachedProjectsDao()
            .getBookmarkedProjects()
            .map {
                it.map { i -> mapper.mapFromCached(i) }
            }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            db.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            db.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return db.cachedProjectsDao().getProjects().isEmpty
            .map { !it }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            db.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return db.configDao().getConfig()
            .single(Config(lastCacheTime = 0))
            .map {
                currentTime - it.lastCacheTime > expirationTime
            }
    }
}