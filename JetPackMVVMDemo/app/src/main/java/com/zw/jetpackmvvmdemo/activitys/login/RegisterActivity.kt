package com.zw.jetpackmvvmdemo.activitys.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.coorchice.library.SuperTextView
import com.zw.jetpackmvvmdemo.MainActivity
import com.zw.jetpackmvvmdemo.R
import com.zw.jetpackmvvmdemo.base.BaseActivity
import com.zw.jetpackmvvmdemo.view.AppViewEditText
import com.zw.mylibrary.bean.RegisterInfoBean

class RegisterActivity : BaseActivity<RegisterViewModel>() {
    private val mTvTitle: AppCompatTextView by lazy { findViewById<AppCompatTextView>(R.id.tv_title) }
    private val mEditUserName: AppViewEditText by lazy { findViewById<AppViewEditText>(R.id.edit_user_name) }
    private val mEditUserPassword: AppViewEditText by lazy { findViewById<AppViewEditText>(R.id.edit_user_password) }
    private val mEditUserRepassword: AppViewEditText by lazy { findViewById<AppViewEditText>(R.id.edit_user_repassword) }
    private val mBtnStart: SuperTextView by lazy { findViewById<SuperTextView>(R.id.btnStart) }
    override fun onCreate(): Int {
        return R.layout.activity_regiter
    }

    override fun initData() {
        viewModel.registerRequest().observe(this, { info ->
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })
    }

    override fun initView() {
        mBtnStart.setOnClickListener {
            onRegisterSubmit()
        }
    }

    override fun showError(obj: Any?) {
        showToast(obj.toString())
    }

    private fun onRegisterSubmit() {
        val userName = mEditUserName.text.toString()
        val password = mEditUserPassword.text.toString()
        val repassword = mEditUserRepassword.text.toString()
        if (userName.isEmpty()) {
            showToast("请输入用户名")
            return
        }
        if (password.isEmpty()) {
            showToast("请输入密码")
            return
        }
        if (repassword != password) {
            showToast("两次密码不正确")
            return
        }

        viewModel.register(userName, password, repassword)

    }
}