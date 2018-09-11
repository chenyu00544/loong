package com.vcvb.chenyu.shop.search;

import android.os.Bundle;

import com.vcvb.chenyu.shop.BaseActivity;

public class SearchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        changeStatusBarTextColor(true);
        setNavBack();
        getData(true);
        initView();
        initListener();
    }

    @Override
    public void setNavBack() {
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void getData(boolean b) {
        super.getData(b);
    }

    @Override
    public void initListener() {
        super.initListener();
    }
}
