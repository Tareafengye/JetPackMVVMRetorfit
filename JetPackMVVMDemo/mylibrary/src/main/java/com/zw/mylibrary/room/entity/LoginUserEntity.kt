package com.zw.mylibrary.room.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @ClassName: LoginUserEntity
 * @Description: 类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/13 16:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/13 16:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
class LoginUserEntity {

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