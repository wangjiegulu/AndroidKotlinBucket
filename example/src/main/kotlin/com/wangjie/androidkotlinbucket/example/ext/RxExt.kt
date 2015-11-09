package com.xingren.xrpatient.provider.kotlinext

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

//fun Request.Builder.rxExecute(): Observable<Response> = Observable.defer({ Observable.just(OkHttpClient().newCall(this.build()).execute()) }).subscribeOn(Schedulers.newThread());

fun <T> Observable<T>.observeOnMain(): Observable<T> = this.observeOn(AndroidSchedulers.mainThread())

//fun <T> Observable<T>.subscribeOnDb(): Observable<T> = this.subscribeOn(Schedulers.from(BasePresenter.THREAD_POOL_DB))
//fun <T> Observable<T>.subscribeOnNet(): Observable<T> = this.subscribeOn(Schedulers.from(BasePresenter.THREAD_POOL_NET))

fun <T> Observable<T>.subscribeSafeNext(onNext: (T) -> Unit): Subscription = this.subscribe(onNext, { t -> Log.e("RxExt", "", t) }, {});
fun <T> Observable<T>.subscribeSafeCompleted(onCompleted: () -> Unit): Subscription = this.subscribe({}, { t -> Log.e("RxExt", "", t) }, onCompleted);

fun <T> Observable<T>.doOnNextOrError(donoe: () -> Unit): Observable<T> {
    return this
            .doOnNext { donoe.invoke() }
            .doOnError { donoe.invoke() }
};

fun <T> Observable<T>.doOnCompletedOrError(donoe: () -> Unit): Observable<T> {
    return this
            .doOnCompleted { donoe.invoke() }
            .doOnError { donoe.invoke() }
};

fun <V : KViewer> Subscription.bindPresenter(presenter: BasePresenter<V>): Subscription {
    presenter.goSubscription(this)
    return this
}
