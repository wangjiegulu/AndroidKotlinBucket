package com.wangjie.androidkotlinbucket.library

import android.app.ActivityManager
import android.content.Context

/**
 * Created by wangjie on 11/17/15.
 */

/**
 * 是否有SDCard
 */
fun Context.haveSDCard() = android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED

/**
 * 判断当前应用程序处于前台还是后台
 * 需要添加权限: <uses-permission android:name="android.permission.GET_TASKS" />
 */
fun Context.isApplicationBackground(): Boolean {
    val tasks = (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).getRunningTasks(1)
    if (!tasks.isEmpty()) {
        val topActivity = tasks[0].topActivity
        if (topActivity.packageName != packageName) {
            return true
        }
    }
    return false
}