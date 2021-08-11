package com.zw.jetpackmvvmdemo.lazy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zw.jetpackmvvmdemo.R;
import com.zw.jetpackmvvmdemo.base.BaseLazyFragment;
import com.zw.jetpackmvvmdemo.detail.DetailActivity;




public class LazyFragment extends BaseLazyFragment<LazyViewModel> implements AndroidAdapter.OnItemClickListener {

    private static final String TAG = "LazyFragment";

    private AndroidAdapter adapter;
    private String category;

    public static LazyFragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("category", category);
        LazyFragment fragment = new LazyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int onCreate() {
        return R.layout.fragment_lazy;
    }

    @Override
    protected void initView(View view) {
        RecyclerView rvNews = view.findViewById(R.id.rv_list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        adapter = new AndroidAdapter();
        adapter.setOnItemListener(this);
        rvNews.setLayoutManager(manager);
        rvNews.setAdapter(adapter);
    }


    @Override
    protected void initData() {
        category = getArguments().getString("category");
        viewModel.getJueJin().observe(this, new Observer<JueJinBean>() {
            @Override
            public void onChanged(JueJinBean bean) {
                adapter.setNewData(bean.getData());
            }
        });
    }

    @Override
    protected void lazyLoad() {
        viewModel.loadData(category);
    }


    @Override
    public void onItemClick(JueJinBean.DataBean bean, int position) {
        startActivity(new Intent(context, DetailActivity.class)
                .putExtra("url", "https://juejin.im/post/" + bean.getArticle_id())
                .putExtra("title", bean.getArticle_info().getTitle()));
    }

    @Override
    protected void showError(Object obj) {

    }
}
