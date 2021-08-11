package com.zw.mylibrary.api

import com.zw.mylibrary.BuildConfig
import com.zw.mylibrary.api.conver.CusGsonConverterFactory
import com.zw.mylibrary.room.base.DBDataBase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.api
 * @ClassName: RetrofitConfig
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
object RetrofitConfig {

    private val URL = if (BuildConfig.DEBUG)
        "https://wanandroid.com/" //测试地址
    else
        "https://wanandroid.com/" //正式地址

    fun getServiceAPI(dataBase: DBDataBase): APIService {
        val retrofit = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                .connectTimeout(300000, TimeUnit.MILLISECONDS)
                .readTimeout(300000, TimeUnit.MILLISECONDS)
                .writeTimeout(300000, TimeUnit.MILLISECONDS)
//                .addInterceptor(LoggingInterceptor(dataBase)) //自定义拦截器
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
            .baseUrl(URL)
            .addConverterFactory(CusGsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(APIService::class.java)
    }
}