package com.hnam.cleanarchitecture

import android.app.Activity
import android.app.Application
import com.hnam.cleanarchitecture.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by nampham on 12/19/18.
 */
class GithubTrendingApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

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