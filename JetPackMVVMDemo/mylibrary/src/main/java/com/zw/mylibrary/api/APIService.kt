package com.zw.mylibrary.api

import com.zw.mylibrary.bean.WanAndroidBean
import io.reactivex.Single
import retrofit2.http.GET

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
}