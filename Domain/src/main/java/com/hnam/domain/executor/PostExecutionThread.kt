package com.hnam.domain.executor

import io.reactivex.Scheduler

/**
 * Created by nampham on 12/9/18.
 */
interface PostExecutionThread {
    val schduler: Scheduler
}