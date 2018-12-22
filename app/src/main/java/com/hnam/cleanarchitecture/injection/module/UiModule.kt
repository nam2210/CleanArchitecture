package com.hnam.cleanarchitecture.module

import com.hnam.cleanarchitecture.UiThread
import com.hnam.cleanarchitecture.bookmarked.BookmarkedActivity
import com.hnam.cleanarchitecture.browse.BrowseActivity
import com.hnam.domain.executor.PostExecutionThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by nampham on 12/21/18.
 */
@Module
abstract class UiModule {

    @Binds
    abstract fun binPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity


}