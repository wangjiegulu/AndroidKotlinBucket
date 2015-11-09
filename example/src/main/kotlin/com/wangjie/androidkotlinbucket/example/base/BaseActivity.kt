package com.wangjie.androidkotlinbucket.example.base

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.wangjie.androidkotlinbucket.library.KPresenter
import com.wangjie.androidkotlinbucket.library.KViewer

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/9/15.
 */
open class BaseActivity : AppCompatActivity(), KViewer {
//    override val onClickListener: ((View) -> Unit)? = null

    protected open val presenter: KPresenter<*>? = null
    override val context: Context? = this

    private val loadingDialog: ProgressDialog by lazy { ProgressDialog(this) }

    override fun loadingDialog(message: String) {
        loadingDialog.setMessage(message)
        loadingDialog.show()
    }

    override fun cancelLoadingDialog() {
        if(loadingDialog.isShowing){
            loadingDialog.cancel()
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        presenter?.closeAll()
    }
}