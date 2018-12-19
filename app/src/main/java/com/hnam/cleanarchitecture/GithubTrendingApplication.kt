package com.hnam.cleanarchitecture

import android.app.Activity
import android.app.Application
import com.hnam.cleanarchitecture.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber

/**
 * Created by nampham on 12/19/18.
 */
class GithubTrendingApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        setupTimber()

        DaggerAppComponent.builder()
            .myApplication(this)
            .build()
            .inject(this)

    }

    fun setupTimber(){
        Timber.plant(Timber.DebugTree())
    }
}