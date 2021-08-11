package com.zw.jetpackmvvmdemo.api;

import com.zw.jetpackmvvmdemo.lazy.JueJinBean;
import com.zw.jetpackmvvmdemo.news.NewsBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    String JUE_JIN_BASE_URL = "https://apinew.juejin.im/recommend_api/v1/";

    /**
     * 测试接口
     */
    @GET("news/latest")
    Observable<NewsBean> news();

    /**
     * 掘金接口
     */
    @POST("article/recommend_cate_feed")
    Observable<JueJinBean> jueJin(@Body RequestBody body);

}
