package com.zw.mylibrary.room.dao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.zw.mylibrary.room.entity.UserInfoEntity

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.room.dao
 * @ClassName: UserInfoDao
 * @Description: 增删改查
 * @Author: ltt
 * @CreateDate: 2021/8/10 14:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/10 14:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Dao
interface UserInfoDao {
    //查询表数据
    @WorkerThread
    @Query("SELECT * FROM  UserInfoEntity ")
    fun getUser(): UserInfoEntity?

    //查询整个表列表数据
    @WorkerThread
    @Query("SELECT * FROM UserInfoEntity")
    fun getUserAll(): LiveData<UserInfoEntity>

    //插入数据
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(UserInfoEntity: UserInfoEntity)

    //修改表内容
    @WorkerThread
    @Update
    fun update(UserInfoEntity: UserInfoEntity)

    //修改表数组内容
    @WorkerThread
    @Update
    fun update(UserInfoEntity: List<UserInfoEntity>)

    //删除表
    @WorkerThread
    @Query("DELETE FROM UserInfoEntity")
    fun deleteAll()
}