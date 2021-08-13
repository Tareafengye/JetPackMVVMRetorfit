package com.zw.jetpackmvvmdemo.activitys.login

import androidx.lifecycle.MutableLiveData
import com.zw.mylibrary.api.BaseJson
import com.zw.mylibrary.bean.RegisterInfoBean
import com.zw.mylibrary.lifecycle.BaseViewModel
import com.zw.mylibrary.repository.DataRepository
import com.zw.mylibrary.room.entity.UserInfoEntity
import io.reactivex.observers.DisposableSingleObserver

/**
 *
 * @ClassName: RegiterViewModel
 * @Description: 类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/11 17:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 17:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
open class RegisterViewModel : BaseViewModel() {

    protected val mutableLive = MutableLiveData<UserInfoEntity>()

     fun register(userName: String, password: String, repassword: String) {
        showDialog.setValue(true, "正在注册中...")
        mRepository.register(userName, password, repassword, object : DisposableSingleObserver<UserInfoEntity>() {
                override fun onSuccess(t: UserInfoEntity) {

                    mutableLive.value = t
                    showDialog.setValue(false)

                }

                override fun onError(e: Throwable) {
                    showDialog.setValue(false)
                    error.value = e.message?:""


                }

            })
    }

    fun registerRequest(): MutableLiveData<UserInfoEntity> {
        return mutableLive
    }


}