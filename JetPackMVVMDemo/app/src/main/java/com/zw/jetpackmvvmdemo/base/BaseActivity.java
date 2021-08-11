package com.zw.jetpackmvvmdemo.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.zw.mylibrary.bean.DialogBean;
import com.zw.mylibrary.lifecycle.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<VM extends BaseViewModel> extends BaseNoModelActivity {

    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = createViewModel();
        initObserve();
        super.onCreate(savedInstanceState);
    }


    /**
     * 初始化ViewModel
     */
    protected VM createViewModel() {
        Type type = getClass().getGenericSuperclass();
        if (!(type instanceof ParameterizedType)) {
            return null;
        }
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        return ViewModelProviders.of(this).get((Class<VM>) arguments[0]);
    }

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    private void initObserve() {
        if (viewModel == null) return;
        viewModel.getShowDialog(this, new Observer<DialogBean>() {
            @Override
            public void onChanged(DialogBean bean) {
                if (bean.isShow()) {
                    showDialog(bean.getMsg());
                } else {
                    dismissDialog();
                }
            }
        });
        viewModel.getError(this, new Observer<Object>() {
            @Override
            public void onChanged(Object obj) {
                showError(obj);
            }
        });
    }

    /**
     * ViewModel层发生了错误
     */
    protected abstract void showError(Object obj);


}
