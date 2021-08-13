package com.zw.mylibrary.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.zw.mylibrary.api.APIService
import com.zw.mylibrary.api.BaseJson
import com.zw.mylibrary.api.ConverAPI
import com.zw.mylibrary.api.RetrofitConfig
import com.zw.mylibrary.bean.RegisterInfoBean
import com.zw.mylibrary.bean.WanAndroidBean
import com.zw.mylibrary.room.base.DBDataBase
import com.zw.mylibrary.room.entity.LoginUserEntity
import com.zw.mylibrary.room.entity.UserInfoEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 *
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.repository
 * @ClassName: DataRepository
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/10 14:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/10 14:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class DataRepository() {
    private lateinit var dbDataBase: DBDataBase
    private lateinit var application: Application
    private lateinit var serviceAPI: APIService

    constructor(application: Application, database: DBDataBase) : this() {
        this.dbDataBase = database
        this.application = application
        this.serviceAPI = RetrofitConfig.getServiceAPI(database)
    }

    /**
     * 添加数据
     */
    fun addBase(entity: UserInfoEntity) {
        dbDataBase.UserInfoDao().insert(entity)
    }

    /**
     * 查询所有数据
     */
    fun selectAll(): LiveData<UserInfoEntity> {
        return dbDataBase.UserInfoDao().getUserAll()
    }

    /**
     * 删除所有数据
     */
    fun deleteAll() {
        dbDataBase.UserInfoDao().deleteAll()
    }

    fun getNickName(): String {
        return dbDataBase.loginDao().niceName
    }
    fun getRegisterNickName(): String {
        return dbDataBase.UserInfoDao().niceName
    }


    fun wanAndroid(observer: DisposableSingleObserver<BaseJson<ArrayList<WanAndroidBean>>>) {
        ConverAPI.wrapList(serviceAPI.wanAndroidList()).subscribe(observer)
    }

    /**
     * 注册
     */
    fun register(username: String, password: String, repassword: String, observer: DisposableSingleObserver<UserInfoEntity>) {
        ConverAPI.wrapObject(serviceAPI.register(username, password, repassword))
            .map {
                if (it.code == 0) {
                    val entity = UserInfoEntity()
                    entity.nickname = it.data.nickname
                    entity.id = it.data.id
                    entity.userid = it.data.id.toString()

                    return@map entity
                } else {
                    return@map null
                }
            }.flatMap {
                dbDataBase.UserInfoDao().deleteAll()
                run {
                    dbDataBase.UserInfoDao().insert(it)
                    Single.just(it)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }



    /**
     * 登录
     */
    fun login(username: String, password: String, observer: DisposableSingleObserver<LoginUserEntity>) {
        ConverAPI.wrapObject(serviceAPI.login(username, password))
            .map {
                if (it.code == 0) {
                    val entity = LoginUserEntity()
                    entity.nickname = it.data.nickname
                    entity.id = it.data.id
                    entity.userid = it.data.id.toString()
                    entity.type=it.data.type
                    return@map entity
                } else {
                    return@map null
                }
            }.flatMap {
                dbDataBase.loginDao().deleteAll()
                run {
                    dbDataBase.loginDao().insert(it)
                    Single.just(it)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

}