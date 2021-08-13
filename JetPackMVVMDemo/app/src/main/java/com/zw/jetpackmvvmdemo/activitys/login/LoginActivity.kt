package com.zw.jetpackmvvmdemo.activitys.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.coorchice.library.SuperTextView
import com.zw.jetpackmvvmdemo.MainActivity
import com.zw.jetpackmvvmdemo.R
import com.zw.jetpackmvvmdemo.base.BaseActivity
import com.zw.jetpackmvvmdemo.view.AppViewEditText

class LoginActivity : BaseActivity<LoginViewModel>() {
    private val mTvTitle: AppCompatTextView by lazy { findViewById<AppCompatTextView>(R.id.tv_title) }
    private val mEditUserName: AppViewEditText by lazy { findViewById<AppViewEditText>(R.id.edit_user_name) }
    private val mEditUserPassword: AppViewEditText by lazy { findViewById<AppViewEditText>(R.id.edit_user_password) }
    private val mBtnStart: SuperTextView by lazy { findViewById<SuperTextView>(R.id.btnStart) }
    private val mTvForget: TextView by lazy { findViewById<TextView>(R.id.tv_forget) }
    private val mTvRegister: TextView by lazy { findViewById<TextView>(R.id.tv_register) }
    override fun onCreate(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
        if (viewModel.userName()!=null) {
            //注册成功返回用户名，防止用户名过长自己给忘记了
            mEditUserName.setText(viewModel.userName())
        } else {
            //登录过火的用户名
            if (viewModel.getNickName()!=null) {
                mEditUserName.setText(getRepository().getNickName())
            }
        }

        viewModel.loginRequst().observe(this, { info ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })

    }

    override fun initView() {

        mBtnStart.setOnClickListener {
            login()
        }
        mTvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun login() {
        val userName = mEditUserName.text.toString()
        val password = mEditUserPassword.text.toString()
        if (userName.isEmpty()) {
            showToast("请输入用户名")
            return
        }
        if (password.isEmpty()) {
            showToast("请输入密码")
            return
        }

        viewModel.login(userName, password)
    }

    override fun showError(obj: Any?) {
        showToast(obj.toString())
    }
}