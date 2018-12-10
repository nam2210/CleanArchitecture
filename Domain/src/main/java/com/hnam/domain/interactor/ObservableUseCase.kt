package com.hnam.domain.interactor

import com.hnam.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by nampham on 12/10/18.
 */
abstract class ObservableUseCase<T, in Params> constructor(private val postExecutionThread: PostExecutionThread){

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params? = null) : Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null){
        val observable = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.computation())
            .observeOn(postExecutionThread.schduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose(){
        disposables.dispose()
    }

    private fun addDisposable(d: Disposable){
        disposables.add(d)
    }
}