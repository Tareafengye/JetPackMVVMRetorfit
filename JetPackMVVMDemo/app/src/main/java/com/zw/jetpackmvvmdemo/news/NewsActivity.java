package com.zw.jetpackmvvmdemo.news;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zw.jetpackmvvmdemo.R;
import com.zw.jetpackmvvmdemo.base.BaseActivity;


public class NewsActivity extends BaseActivity<NewsViewModel> {

    private NewsAdapter adapter;

    @Override
    protected int onCreate() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        findViewById(R.id.btn_request_data).setOnClickListener(v -> viewModel.getWanAndroid(getRepository()));
        RecyclerView rvNews = findViewById(R.id.rv_news);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new NewsAdapter();
        rvNews.setLayoutManager(manager);
        rvNews.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        //数据请求成功通知
        viewModel.getWanAndroid().observe(this,wanAndroidBeans -> {
            adapter.setNewData(wanAndroidBeans);
        });
    }

    @Override
    protected void showError(Object obj) {

    }


}
