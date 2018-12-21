package com.hnam.cleanarchitecture.module

import com.hnam.data.ProjectDataRepository
import com.hnam.domain.repository.ProjectRepository
import dagger.Binds
import dagger.Module

/**
 * Created by nampham on 12/21/18.
 */
@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectDataRepository): ProjectRepository
}