package com.zw.mylibrary.room.dao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.zw.mylibrary.room.entity.LoginUserEntity

/**
 *
 * @ClassName: loginUserDao
 * @Description: 类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/13 16:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/13 16:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Dao
interface loginUserDao {
    //查询表数据
    @WorkerThread
    @Query("SELECT * FROM  LoginUserEntity ")
    fun getUser(): LoginUserEntity?

    //查询整个表列表数据
    @WorkerThread
    @Query("SELECT * FROM LoginUserEntity")
    fun getUserAll(): LiveData<LoginUserEntity>

    //插入数据
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(LoginUserEntity: LoginUserEntity)

    //修改表内容
    @WorkerThread
    @Update
    fun update(LoginUserEntity: LoginUserEntity)

    //修改表数组内容
    @WorkerThread
    @Update
    fun update(LoginUserEntity: List<LoginUserEntity>)

    //删除表
    @WorkerThread
    @Query("DELETE FROM LoginUserEntity")
    fun deleteAll()



    @get:WorkerThread
    @get:Query("select nickname from LoginUserEntity limit 1")
    val niceName:String
}