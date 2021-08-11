package com.zw.jetpackmvvmdemo.detail;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zw.jetpackmvvmdemo.R;
import com.zw.jetpackmvvmdemo.base.BaseNoModelFragment;


public class DetailFragment extends BaseNoModelFragment {

    private WebView webView;

    public static DetailFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int onCreate() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void initView(View view) {
        webView = view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    @Override
    protected void initData() {
        String url = getArguments().getString("url");
        webView.loadUrl(url);
    }
}
