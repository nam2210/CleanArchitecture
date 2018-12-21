package com.hnam.cleanarchitecture.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by nampham on 12/21/18.
 */
@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application) : Context
}