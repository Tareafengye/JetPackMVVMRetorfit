package com.zw.jetpackmvvmdemo

import android.content.Intent
import android.widget.Button
import com.zw.jetpackmvvmdemo.detail.DetailActivity
import com.zw.jetpackmvvmdemo.lazy.LazyActivity
import com.zw.jetpackmvvmdemo.linkage.LinkageActivity
import com.zw.jetpackmvvmdemo.news.NewsActivity
import com.zw.jetpackmvvmdemo.base.BaseNoModelActivity


class MainActivity : BaseNoModelActivity() {
    private val mBtnRv: Button by lazy { findViewById<Button>(R.id.btn_rv) }
    private val mBtnFragment: Button by lazy { findViewById<Button>(R.id.btn_fragment) }
    private val mBtnLazyFragment: Button by lazy { findViewById<Button>(R.id.btn_lazy_fragment) }
    private val mBtnLinkage: Button by lazy { findViewById<Button>(R.id.btn_linkage) }

    override fun onCreate(): Int {
        return R.layout.activity_main

    }

    override fun initView() {


    }

    override fun initData() {
        mBtnRv.setOnClickListener {
            startActivity(Intent(context, NewsActivity::class.java))
        }
        mBtnFragment.setOnClickListener {
            startActivity(Intent(context, DetailActivity::class.java))
        }
        mBtnLazyFragment.setOnClickListener {
            startActivity(Intent(context, LazyActivity::class.java))
        }
        mBtnLinkage.setOnClickListener {
            startActivity(Intent(context, LinkageActivity::class.java))
        }

    }


}