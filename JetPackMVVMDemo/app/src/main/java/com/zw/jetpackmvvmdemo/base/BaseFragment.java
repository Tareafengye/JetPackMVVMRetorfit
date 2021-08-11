package com.zw.jetpackmvvmdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.zw.mylibrary.lifecycle.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;



public abstract class BaseFragment<VM extends BaseViewModel> extends BaseNoModelFragment {

    protected VM viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        viewModel = createViewModel();
        initObserve();
        super.onActivityCreated(savedInstanceState);
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
        viewModel.getShowDialog(this, bean -> {
            if (bean.isShow()) {
                showDialog(bean.getMsg());
            } else {
                dismissDialog();
            }
        });
        viewModel.getError(this, obj -> showError(obj));
    }

    /**
     * ViewModel层发生了错误
     */
    protected abstract void showError(Object obj);
}
