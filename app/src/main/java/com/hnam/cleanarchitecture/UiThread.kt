package com.hnam.cleanarchitecture

import com.hnam.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by nampham on 12/19/18.
 */
class UiThread @Inject constructor() : PostExecutionThread {
    override val schduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}