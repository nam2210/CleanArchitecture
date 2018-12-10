package com.hnam.domain.interactor

import com.hnam.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by nampham on 12/10/18.
 */
abstract class CompletableUseCase<in Params> constructor(private val postExecutionThread: PostExecutionThread){

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseCompletable(params: Params? = null) : Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params? = null){
        val completable = buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.computation())
            .observeOn(postExecutionThread.schduler)
        addDisposable(completable.subscribeWith(observer))
    }

    fun dispose(){
        disposables.dispose()
    }

    private fun addDisposable(d: Disposable){
        disposables.add(d)
    }
}