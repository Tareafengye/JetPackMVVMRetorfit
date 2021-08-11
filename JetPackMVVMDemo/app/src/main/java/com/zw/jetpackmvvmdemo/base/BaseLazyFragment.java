package com.zw.jetpackmvvmdemo.base;

import com.zw.mylibrary.lifecycle.BaseViewModel;



public abstract class BaseLazyFragment<VM extends BaseViewModel> extends BaseFragment<VM> {

    private boolean visibleToUser;

    @Override
    public void onResume() {
        super.onResume();
        if (!visibleToUser) {
            visibleToUser = true;
            lazyLoad();
        }
    }

    /**
     * 懒加载，只有在Fragment第一次创建且第一次对用户可见
     */
    protected abstract void lazyLoad();

}
