package com.wangjie.androidkotlinbucket.example.ext

import android.util.Log
import com.wangjie.androidkotlinbucket.example.base.BasePresenter
import com.wangjie.androidkotlinbucket.library.KViewer
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 10/13/15.
 */

// ---- RxJava ----

fun <T> Observable<T>.observeOnMain(): Observable<T> = this.observeOn(AndroidSchedulers.mainThread())

//fun <T> Observable<T>.subscribeOnDb(): Observable<T> = this.subscribeOn(Schedulers.from(BasePresenter.THREAD_POOL_DB))
//fun <T> Observable<T>.subscribeOnNet(): Observable<T> = this.subscribeOn(Schedulers.from(BasePresenter.THREAD_POOL_NET))

fun <T> Observable<T>.subscribeSafeNext(onNext: (T) -> Unit): Subscription = this.subscribe(onNext, { t -> Log.e("RxExt", "", t) }, {});
fun <T> Observable<T>.subscribeSafeCompleted(onCompleted: () -> Unit): Subscription = this.subscribe({}, { t -> Log.e("RxExt", "", t) }, onCompleted);

fun <T> Observable<T>.doOnNextOrError(f: () -> Unit): Observable<T> {
    return this
            .doOnNext { f() }
            .doOnError { f() }
}

fun <T> Observable<T>.doOnCompletedOrError(f: () -> Unit): Observable<T> {
    return this
            .doOnCompleted { f() }
            .doOnError { f() }
}

fun <V : KViewer> Subscription.bindPresenter(presenter: BasePresenter<V>): Subscription {
    presenter.goSubscription(this)
    return this
}
