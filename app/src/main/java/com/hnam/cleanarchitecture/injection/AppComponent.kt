package com.hnam.cleanarchitecture.injection

import android.app.Application
import android.app.Presentation
import com.hnam.cleanarchitecture.GithubTrendingApplication
import com.hnam.cleanarchitecture.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by nampham on 12/19/18.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,
            ApplicationModule::class,
            UiModule::class,
            PresentationModule::class,
            DataModule::class,
            CacheModule::class,
            RemoteModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun myApplication(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: GithubTrendingApplication)

}