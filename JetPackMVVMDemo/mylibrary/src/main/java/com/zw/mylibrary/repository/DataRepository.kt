package com.zw.mylibrary.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.zw.mylibrary.api.APIService
import com.zw.mylibrary.api.BaseJson
import com.zw.mylibrary.api.ConverAPI
import com.zw.mylibrary.api.RetrofitConfig
import com.zw.mylibrary.bean.WanAndroidBean
import com.zw.mylibrary.room.base.DBDataBase
import com.zw.mylibrary.room.entity.UserInfoEntity
import io.reactivex.observers.DisposableSingleObserver

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
    private lateinit  var dbDataBase: DBDataBase
    private lateinit var application: Application
    private lateinit var serviceAPI:APIService

    constructor(application: Application,database: DBDataBase): this(){
        this.dbDataBase=database
        this.application=application
        this.serviceAPI=RetrofitConfig.getServiceAPI(database)
    }

    /**
     * 添加数据
     */
    fun addBase(entity:UserInfoEntity){
        dbDataBase.UserInfoDao().insert(entity)
    }

    /**
     * 查询所有数据
     */
    fun selectAll():LiveData<UserInfoEntity>{
       return dbDataBase.UserInfoDao().getUserAll()
    }

    /**
     * 删除所有数据
     */
    fun deleteAll(){
       dbDataBase.UserInfoDao().deleteAll()
    }


    fun  wanAndroid(observer: DisposableSingleObserver<BaseJson<ArrayList<WanAndroidBean>>>){
        ConverAPI.wrapList(serviceAPI.wanAndroidList()).subscribe(observer)

    }

}