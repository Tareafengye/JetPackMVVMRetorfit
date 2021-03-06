package com.zw.mylibrary.room.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.room.entity
 * @ClassName: UserInfoEntity
 * @Description: Entity代表数据库的表，更新表的时候需要在DataBase中更新版本号
 * @Author: ltt
 * @CreateDate: 2021/8/10 14:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/10 14:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
class UserInfoEntity {

    @NonNull
    @PrimaryKey
    lateinit var userid: String

    var admin = false
    var coinCount = 0
    var email: String? = null
    var icon: String? = null
    var id = 0
    var nickname: String? = null
    var password: String? = null
    var publicName: String? = null
    var token: String? = null
    var type = 0

}