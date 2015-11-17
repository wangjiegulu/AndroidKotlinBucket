package com.wangjie.androidkotlinbucket.library

/**
 * Created by wangjie on 11/17/15.
 */
fun <T> _lazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)