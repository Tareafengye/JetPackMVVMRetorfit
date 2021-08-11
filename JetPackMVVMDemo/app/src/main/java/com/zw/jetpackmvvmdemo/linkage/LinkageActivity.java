package com.zw.jetpackmvvmdemo.linkage;

import android.widget.SeekBar;

import androidx.lifecycle.Observer;

import com.zw.jetpackmvvmdemo.R;
import com.zw.jetpackmvvmdemo.base.BaseActivity;


public class LinkageActivity extends BaseActivity<LinkageViewModel> implements SeekBar.OnSeekBarChangeListener {

    private SeekBar ski;

    @Override
    protected int onCreate() {
        return R.layout.activity_linkage;
    }

    @Override
    protected void initView() {
        setTitle("Activity/Fragment数据联动");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, LinkageFragment.newInstance())
                .commit();
        ski = findViewById(R.id.sk_i);
        ski.setOnSeekBarChangeListener(this);
    }

    @Override
    protected void initData() {
        viewModel.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ski.setProgress(integer);
            }
        });
    }

    @Override
    protected void showError(Object obj) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //更新viewModel中的数据
        viewModel.getProgress().setValue(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
