package com.vcvb.chenyu.shop.goods;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;


public class GoodsActivity extends BaseActivity {
    public TextView goodsView;
    public TextView goodsEvaluate;
    public TextView goodsInfo;
    public ImageView collectionView;
    public LinearLayout title_wrap;
    int ts_18 = 16;
    int ts_22 = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setNavBack() {
        super.setNavBack();
        int color = Color.parseColor("#AAAAAA");
        int gravity = Gravity.CENTER;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        goodsView = new TextView(this);
        goodsView.setLayoutParams(layoutParams);
        goodsView.setGravity(gravity);
        goodsView.setText("商品");
        goodsView.setTextColor(Color.parseColor("#000000"));
        goodsView.setTextSize(ts_22);
        goodsView.setSingleLine();

        LinearLayout.LayoutParams layoutParams_t = new LinearLayout.LayoutParams(80, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        layoutParams_t.setMargins(10, 0, 0, 0);
        goodsEvaluate = new TextView(this);
        goodsEvaluate.setLayoutParams(layoutParams_t);
        goodsEvaluate.setGravity(gravity);
        goodsEvaluate.setText("评价");
        goodsEvaluate.setTextColor(color);
        goodsEvaluate.setTextSize(ts_18);
        goodsEvaluate.setSingleLine();

        goodsInfo = new TextView(this);
        goodsInfo.setLayoutParams(layoutParams_t);
        goodsInfo.setGravity(gravity);
        goodsInfo.setText("详情");
        goodsInfo.setTextColor(color);
        goodsInfo.setTextSize(ts_18);
        goodsInfo.setSingleLine();

        title_wrap = (LinearLayout) findViewById(R.id.title_wrap);
        if (title_wrap != null) {
            title_wrap.addView(goodsView);
            title_wrap.addView(goodsEvaluate);
            title_wrap.addView(goodsInfo);
        }
    }
}
