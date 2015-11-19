package com.wangjie.androidkotlinbucket.library

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.View

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */

/** Activity **/
// 从Activity中注入View
public fun <T : View> Activity._pick(resId: Int) = lazy(LazyThreadSafetyMode.NONE) { findViewById(resId) as T }
//// 绑定点击事件
//public fun Activity._click(onClick: ((View) -> Unit)?, vararg resId: Int): Activity {
//    __click({ findViewById(it) }, onClick, resId)
//    return this
//}


/** Fragment **/
// 从Fragment中注入View
public fun <T : View> Fragment._pick(resId: Int) = lazy(LazyThreadSafetyMode.NONE) { view.findViewById(resId) as T }
//// 绑定点击事件
//public fun Fragment._click(onClick: ((View) -> Unit)?, vararg resId: Int): Fragment {
//    __click({ view.findViewById(it) }, onClick, resId)
//    return this
//}


/** View **/
// 从View中注入View
public fun <T : View> View._pick(resId: Int) = lazy(LazyThreadSafetyMode.NONE) { findViewById(resId) as T }
//// 绑定点击事件
//public fun View._click(onClick: ((View) -> Unit)?, vararg resId: Int): View {
//    __click({ findViewById(it) }, onClick, resId)
//    return this
//}


/** Common **/
//public fun _abkClick(onClick: ((View) -> Unit)?, vararg view: View) {
//    onClick?.let {
//        view.forEach { it.setOnClickListener(onClick) }
//    }
//}
//
//
//private inline fun __click(findView: (Int) -> View, noinline onClick: ((View) -> Unit)?, resId: IntArray) {
//    onClick?.let {
//        resId.forEach { findView(it).setOnClickListener(onClick) }
//    }
//}

inline fun <reified T : Activity> Context.toActivity(f: (Intent) -> Unit) {
    with(Intent(this, T::class.java)) {
        f(this)
        startActivity(this)
    }
}










