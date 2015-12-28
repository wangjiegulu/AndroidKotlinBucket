package com.wangjie.androidkotlinbucket.library

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.Toast

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */

/**
 * MVP的View层，UI相关，Activity需要实现该interface
 * 它会包含一个Presenter的引用，当有事件发生（比如按钮点击后），会调用Presenter层的方法
 */
public interface KIViewer {
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

    fun showLoading(message: String) {
        Log.w(KIViewer::class.java.simpleName, "loadingDialog should impl by subclass")
    }

    fun cancelLoading() {
        Log.w(KIViewer::class.java.simpleName, "cancelLoadingDialog should impl by subclass")
    }

    fun <T : View> findView(resId: Int): T;

}

/**
 * MVP的Presenter层，作为沟通View和Model的桥梁，它从Model层检索数据后，返回给View层，它也可以决定与View层的交互操作。
 * 它包含一个View层的引用和一个Model层的引用
 */
public interface KIPresenter {
    public fun closeAll()
}

public open class KPresenter<V : KIViewer>(var viewer: V) : KIPresenter {

    override public fun closeAll() {
        Log.w(KIViewer::class.java.simpleName, "closeAll in KPresenter should impl by subclass")
    }

}

/**
 * Controller，用于派发View中的事件，它在根据不同的事件调用Presenter
 */
public interface KIController {
    /**
     * 注册事件
     */
    fun bindEvents()

    fun <T : View> getView(resId: Int): T;
    fun closeAll()

//    /**
//     * 界面对用户显示(onResume)
//     */
//    fun onUserVisible() {
//    }
//
//    /**
//     * 界面对用户隐藏(onPause)
//     */
//    fun onUserInvisible() {
//    }
//
//    /**
//     * 界面销毁时回调,需要在BaseActivity或者BaseFragment等组件中调用
//     */
//    fun onDestroy() {
//    }

}

public abstract class KController<KV : KIViewer, KP : KPresenter<*>>(val viewer: KV, presenterCreate: () -> KP) : KIController {
    protected val presenter: KP by lazy { presenterCreate() }

    private val viewCache: SparseArray<View> = SparseArray();

    public override fun <T : View> getView(resId: Int): T {
        val view: View? = viewCache.get(resId)
        return view as T? ?: viewer.findView<T>(resId).apply {
            viewCache.put(resId, this)
        }
    }

    public override fun closeAll() = presenter.closeAll()


}
