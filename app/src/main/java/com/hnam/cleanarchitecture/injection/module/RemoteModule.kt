package com.hnam.cleanarchitecture.module

import com.hnam.cleanarchitecture.BuildConfig
import com.hnam.data.repository.ProjectsRemote
import com.hnam.remote.ProjectsRemoteImpl
import com.hnam.remote.services.GithubTrendingService
import com.hnam.remote.services.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by nampham on 12/21/18.
 */
@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}