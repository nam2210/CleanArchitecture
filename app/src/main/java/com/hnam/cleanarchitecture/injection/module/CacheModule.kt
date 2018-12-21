package com.hnam.cleanarchitecture.module

import android.app.Application
import com.hnam.cache.ProjectsCacheImpl
import com.hnam.cache.db.ProjectsDatabase
import com.hnam.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by nampham on 12/21/18.
 */
@Module
abstract  class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}