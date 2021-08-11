package com.zw.jetpackmvvmdemo

import android.app.Application
import com.zw.mylibrary.repository.DataRepository
import com.zw.mylibrary.room.base.DBDataBase

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.jetpackmvvmdemo
 * @ClassName: App
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/10 14:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/10 14:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MyApplication : Application() {
    private lateinit var dbDataBase: DBDataBase
    private lateinit var mDataRepository: DataRepository
    override fun onCreate() {
        super.onCreate()
        dbDataBase = DBDataBase.getDataBase(this)
        mDataRepository = DataRepository(this, dbDataBase)
    }
    fun getRepository() = mDataRepository

}