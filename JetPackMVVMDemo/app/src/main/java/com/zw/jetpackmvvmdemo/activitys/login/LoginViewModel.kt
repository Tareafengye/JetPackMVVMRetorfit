package com.zw.jetpackmvvmdemo.activitys.login

import androidx.lifecycle.MutableLiveData
import com.zw.jetpackmvvmdemo.MyApplication
import com.zw.mylibrary.lifecycle.BaseViewModel
import com.zw.mylibrary.repository.DataRepository
import com.zw.mylibrary.room.entity.LoginUserEntity
import com.zw.mylibrary.room.entity.UserInfoEntity
import io.reactivex.observers.DisposableSingleObserver

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.jetpackmvvmdemo.activitys.login
 * @ClassName: LoginViewModel
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/11 17:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 17:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class LoginViewModel : BaseViewModel() {

    protected val mutableLive = MutableLiveData<LoginUserEntity>()

    fun login(userName: String, password: String) {
        showDialog.setValue(true, "正在登录中...")
        mRepository.login(
            userName,
            password,
            object : DisposableSingleObserver<LoginUserEntity>() {
                override fun onSuccess(t: LoginUserEntity) {
                    mutableLive.value = t
                    showDialog.setValue(false)

                }

                override fun onError(e: Throwable) {
                    showDialog.setValue(false)
                    error.value = e.message ?: ""

                }

            })
    }

    fun getNickName(): String {
        return mRepository.getNickName()
    }

    fun userName(): String {
        return mRepository.getRegisterNickName()
    }
    fun loginRequst(): MutableLiveData<LoginUserEntity> {
        return mutableLive
    }
}