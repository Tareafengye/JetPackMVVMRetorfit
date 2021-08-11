package com.zw.jetpackmvvmdemo.linkage;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.zw.jetpackmvvmdemo.R;
import com.zw.jetpackmvvmdemo.base.BaseFragment;


public class LinkageFragment extends BaseFragment<LinkageViewModel> implements SeekBar.OnSeekBarChangeListener {

    private SeekBar skii;

    public static LinkageFragment newInstance() {
        Bundle args = new Bundle();
        LinkageFragment fragment = new LinkageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int onCreate() {
        return R.layout.fragment_linkage;
    }

    @Override
    protected void initView(View view) {
        skii = view.findViewById(R.id.sk_i_i);
        skii.setOnSeekBarChangeListener(this);
    }

    @Override
    protected void initData() {
        viewModel.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                skii.setProgress(integer);
            }
        });
    }

    @Override
    protected LinkageViewModel createViewModel() {
        return ViewModelProviders.of(getActivity()).get(LinkageViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        viewModel.getProgress().setValue(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
