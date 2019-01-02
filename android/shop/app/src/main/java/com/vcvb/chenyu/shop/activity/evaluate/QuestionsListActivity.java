package com.vcvb.chenyu.shop.activity.evaluate;

import android.os.Bundle;

import com.vcvb.chenyu.shop.base.BaseActivity;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;

public class QuestionsListActivity extends BaseActivity {

    private GoodsDetail goodsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goodsDetails = (GoodsDetail) getIntent().getSerializableExtra("goods");
        context = this;
        changeStatusBarTextColor(true);
        setNavBack();
        initView();
        initListener();
        getData(true);
    }

    @Override
    public void setNavBack() {
    }

    @Override
    public void initView() {
    }

    @Override
    public void getData(boolean b) {
    }

    @Override
    public void initListener() {
    }
}
