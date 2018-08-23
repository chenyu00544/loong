package com.vcvb.chenyu.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

public class GoodsDetailActivity extends BaseActivity {
    private RelativeLayout nav_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        nav_back = (RelativeLayout) findViewById(R.id.nav_back);
        nav_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
