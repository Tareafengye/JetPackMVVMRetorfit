package com.zw.mylibrary.api

import com.zw.mylibrary.bean.RegisterInfoBean
import com.zw.mylibrary.bean.WanAndroidBean
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 *
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.api
 * @ClassName: APIService
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface APIService {


    @GET("wxarticle/chapters/json")
    fun wanAndroidList(): Single<BaseJson<ArrayList<WanAndroidBean>>>

    /**
     * wandroid 注册
     */
    @POST("user/register")
    fun register(@Query("username") username: String, @Query("password") password: String, @Query("repassword") repassword: String):Single<BaseJson<RegisterInfoBean>>

    /**
     * wandroid 登录
     */
    @POST("user/login")
    fun login(@Query("username") username: String, @Query("password") password: String):Single<BaseJson<RegisterInfoBean>>



}