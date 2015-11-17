package com.wangjie.androidkotlinbucket.library.view

import android.support.v4.view.ViewPager

/**
 * Created by liudan on 15/11/17.
 */
/**
 * addOnPageChangeListener
 */
fun ViewPager._pageChanged(pageSelected: ((Int) -> Unit)? = null, pageScrolled: ((Int, Float, Int) -> Unit)? = null, pageScrollStateChanged: ((Int) -> Unit)? = null) {
    this.addOnPageChangeListener(
            object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    pageScrolled?.invoke(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    pageScrollStateChanged?.invoke(state)
                }

                override fun onPageSelected(position: Int) {
                    pageSelected?.invoke(position)
                }
            })
}