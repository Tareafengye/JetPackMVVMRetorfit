package com.zw.jetpackmvvmdemo.news;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.google.gson.Gson;
import com.zw.jetpackmvvmdemo.api.Api;
import com.zw.mylibrary.api.BaseJson;
import com.zw.mylibrary.bean.WanAndroidBean;
import com.zw.mylibrary.lifecycle.BaseViewModel;
import com.zw.mylibrary.repository.DataRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class NewsViewModel extends BaseViewModel {
    /**
     * 当数据请求成功回调
     */

    protected MutableLiveData<ArrayList<WanAndroidBean>> android = new MutableLiveData<>();




    /**
     * 加载wanandroid列表数据
     * @param dataRepository
     */
    public void getWanAndroid(DataRepository dataRepository) {
        showDialog.setValue(true, "加载中...");
        dataRepository.wanAndroid(new DisposableSingleObserver<BaseJson<ArrayList<WanAndroidBean>>>() {
            @Override
            public void onSuccess(@NotNull BaseJson<ArrayList<WanAndroidBean>> arrayListBaseJson) {
                showDialog.setValue(false);
                android.setValue(arrayListBaseJson.getData());
            }

            @Override
            public void onError(@NotNull Throwable e) {
                showDialog.setValue(false);
                /*
                 * 发生了错误，通知UI层
                 */
                error.setValue("发生错误了");

            }
        });
    }


    public MutableLiveData<ArrayList<WanAndroidBean>> getWanAndroid() {
        return android;
    }
}
