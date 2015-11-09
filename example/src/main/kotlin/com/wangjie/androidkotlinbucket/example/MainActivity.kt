package com.wangjie.androidkotlinbucket.example

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.wangjie.androidkotlinbucket.example.base.BaseActivity
import com.wangjie.androidkotlinbucket.library._click
import com.wangjie.androidkotlinbucket.library._pick
import com.wangjie.androidkotlinbucket.library._presenter

class MainActivity : BaseActivity(), MainViewer {

    private val tv: TextView by _pick(R.id.activity_main_tv)

    override val presenter: MainPresenter by _presenter { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.text = "inject succeed"

        _click(onClickListener, R.id.activity_main_test_a_btn)


    }

    val onClickListener: ((View) -> Unit)? = {
        when(it.id){
            R.id.activity_main_tv,
            R.id.activity_main_test_a_btn -> presenter.test()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
