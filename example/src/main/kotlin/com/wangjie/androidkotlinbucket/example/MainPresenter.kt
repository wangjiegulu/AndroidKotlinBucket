package com.wangjie.androidkotlinbucket.example

import com.wangjie.androidkotlinbucket.example.base.BasePresenter
import com.wangjie.androidkotlinbucket.example.ext.bindPresenter
import com.wangjie.androidkotlinbucket.example.ext.doOnNextOrError
import com.wangjie.androidkotlinbucket.example.ext.observeOnMain
import com.wangjie.androidkotlinbucket.example.ext.subscribeSafeNext
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */
public class MainPresenter(viewer: MainViewer) : BasePresenter<MainViewer>(viewer) {

    public fun test() {

        viewer.dialog("title", "Load data ?", positiveClickListener = {
            dialog, int ->

            viewer.loadingDialog("I'm loading...")

            Observable.create<String> {
                Thread.sleep(2000)
                it.onNext("load success")
                it.onCompleted()
            }
                    .subscribeOn(Schedulers.newThread())
                    .observeOnMain()
                    .doOnNextOrError { viewer.cancelLoadingDialog() }
                    .subscribeSafeNext {
                        viewer.toast("succeed: $it")
                    }
                    .bindPresenter(this)
        })

    }

}