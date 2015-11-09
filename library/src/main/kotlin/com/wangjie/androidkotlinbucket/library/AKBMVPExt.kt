package com.wangjie.androidkotlinbucket.library

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */

/**
 * 用于懒加载Presenter
 */
public fun <T : KPresenter<*>> _presenter(f: () -> T) = lazy(LazyThreadSafetyMode.NONE) { f() }

/**
 * MVP的View层，UI相关，Activity需要实现该interface
 * 它会包含一个Presenter的引用，当有事件发生（比如按钮点击后），会调用Presenter层的方法
 */
public interface KViewer {
    //    val onClickListener: ((view: View) -> Unit)?
    val context: Context?;

    fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        context?.lets { Toast.makeText(this, message, duration).show() }
    }

    fun dialog(title: String? = null,
               message: String?,
               okText: String? = "OK",
               cancelText: String? = null,
               positiveClickListener: ((DialogInterface, Int) -> Unit)? = null,
               negativeClickListener: ((DialogInterface, Int) -> Unit)? = null
    ) {
        context?.lets {
            AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(okText, positiveClickListener)
                    .setNegativeButton(cancelText, negativeClickListener)
                    .show()
        }
    }

    fun loadingDialog(message: String) {
        Log.w(KViewer::class.java.simpleName, "loadingDialog should impl by subclass")
    }

    fun cancelLoadingDialog() {
        Log.w(KViewer::class.java.simpleName, "cancelLoadingDialog should impl by subclass")
    }

}

/**
 * MVP的Presenter层，作为沟通View和Model的桥梁，它从Model层检索数据后，返回给View层，它也可以决定与View层的交互操作。
 * 它包含一个View层的引用和一个Model层的引用
 */
public open class KPresenter<V : KViewer>(var viewer: V) {

    open public fun closeAll() {
        Log.w(KViewer::class.java.simpleName, "closeAll in KPresenter should impl by subclass")
    }

}


