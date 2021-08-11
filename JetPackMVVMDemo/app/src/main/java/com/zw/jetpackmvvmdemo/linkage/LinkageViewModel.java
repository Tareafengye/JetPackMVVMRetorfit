package com.zw.jetpackmvvmdemo.linkage;

import androidx.lifecycle.MutableLiveData;

import com.zw.mylibrary.lifecycle.BaseViewModel;




public class LinkageViewModel extends BaseViewModel {

    private MutableLiveData<Integer> progress = new MutableLiveData<>();

    public MutableLiveData<Integer> getProgress() {
        return progress;
    }
}
