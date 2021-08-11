package com.zw.jetpackmvvmdemo.detail;

import android.text.TextUtils;

import com.zw.jetpackmvvmdemo.R;
import com.zw.jetpackmvvmdemo.base.BaseNoModelActivity;




public class DetailActivity extends BaseNoModelActivity {

    @Override
    protected int onCreate() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        if (TextUtils.isEmpty(url)) {
            url = "http://www.baidu.com";
            setTitle("Fragment使用示例");
        } else {
            setTitle(title);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, DetailFragment.newInstance(url))
                .commit();
    }

    @Override
    protected void initData() {

    }
}
