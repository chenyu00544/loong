package com.vcvb.chenyu.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class GoodsDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
    }

    public void onClickBackActivity(View view){
        finish();
    }
}
