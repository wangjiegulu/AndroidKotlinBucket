package com.wangjie.androidkotlinbucket.example.base

import android.util.Log
import com.wangjie.androidkotlinbucket.library.KPresenter
import com.wangjie.androidkotlinbucket.library.KViewer
import rx.Subscription
import java.util.*

/**
 *
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */

open class BasePresenter<V : KViewer>(viewer: V) : KPresenter<V>(viewer) {

    companion object{
        val TAG: String = BasePresenter::class.java.simpleName
    }

    private val subscriptions = Collections.newSetFromMap(WeakHashMap<Any, Boolean>())

    override fun closeAll() {
        synchronized (subscriptions) {
            val iter = this.subscriptions.iterator()
            while (iter.hasNext()) {
                val subscription = iter.next() as Subscription
                Log.i(TAG, "closeAllTask[subscriptions]: " + subscription)
                if (!subscription.isUnsubscribed) {
                    subscription.unsubscribe()
                }
                iter.remove()
            }
        }
    }

    fun goSubscription(subscription: Subscription) {
        synchronized (subscriptions) {
            this.subscriptions.add(subscription)
        }
    }


}
