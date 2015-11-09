package com.wangjie.androidkotlinbucket.library

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */
public inline fun <T, R> T.lets(f: T.(T) -> R): R = f(this)