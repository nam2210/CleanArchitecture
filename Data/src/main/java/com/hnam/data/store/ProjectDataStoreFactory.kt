package com.hnam.data.store

import com.hnam.data.repository.ProjectDataStore
import javax.inject.Inject

/**
 * Created by nampham on 12/13/18.
 */
class ProjectDataStoreFactory @Inject constructor(
    private val projectCacheDataStore: ProjectCacheDataStore,
    private val projectRemoteDataStore: ProjectRemoteDataStore
){
    open fun getDataStore(projectCached : Boolean, cachedExpired : Boolean) : ProjectDataStore{
        return if (projectCached && !cachedExpired){
            projectCacheDataStore
        } else {
            projectRemoteDataStore
        }
    }

    fun getCacheDataStore() : ProjectDataStore{
        return projectCacheDataStore
    }
}