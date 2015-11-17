package com.wangjie.androidkotlinbucket.library

import android.content.Context

/**
 * Created by wangjie on 11/17/15.
 */

/**
 * 是否有SDCard
 */
fun Context.haveSDCard() = android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED