package com.vcvb.chenyu.shop.goods;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.image.GlideImageLoader;
import com.vcvb.chenyu.shop.image.Images;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsDetailActivity extends GoodsActivity {
    private Banner banner;
    private NestedScrollView nestedScrollView;
    private TextView goods_price;
    private TextView market_price;
    private
    int eY;
    int eI;

    public GoodsDetailActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);
        setNavBack();
        initNSV();
        initBanner();
        initData();
    }

    //在这个方法内才能获取正确的距离宽高参数
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        eY = findViewById(R.id.goods_evaluate).getTop();
        eI = findViewById(R.id.goods_image_text_info).getTop();
    }

    @Override
    public void setNavBack() {
        super.setNavBack();
        goodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsView.setTextSize(ts_22);
                goodsView.setTextColor(Color.parseColor("#000000"));
                goodsEvaluate.setTextSize(ts_18);
                goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                goodsInfo.setTextSize(ts_18);
                goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                nestedScrollView.scrollTo(0,0);
            }
        });
        goodsEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsView.setTextSize(ts_18);
                goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                goodsInfo.setTextSize(ts_18);
                goodsInfo.setTextColor(Color.parseColor("#AAAAAA"));
                goodsEvaluate.setTextSize(ts_22);
                goodsEvaluate.setTextColor(Color.parseColor("#000000"));
                nestedScrollView.scrollTo(0,eY);
            }
        });
        goodsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsInfo.setTextSize(ts_22);
                goodsInfo.setTextColor(Color.parseColor("#000000"));
                goodsView.setTextSize(ts_18);
                goodsView.setTextColor(Color.parseColor("#AAAAAA"));
                goodsEvaluate.setTextSize(ts_18);
                goodsEvaluate.setTextColor(Color.parseColor("#AAAAAA"));
                nestedScrollView.scrollTo(0,eI);
            }
        });
    }

    private void initBanner() {

        HashMap ghm = new HashMap();
        ArrayList<String> imgUrls = new ArrayList<>();
        for (int i = 0; i < Images.imgUrls.length; i++) {
            imgUrls.add(Images.imgUrls[i]);
        }
        ghm.put("banner", imgUrls);

        banner = (Banner) findViewById(R.id.goods_banner_slide);
        //设置内置样式，内含六种特效
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置轮播的动画效果，内含多种特效
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setDelayTime(5000);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());

        ArrayList<String> imageUrls = (ArrayList<String>) ghm.get("banner");
        banner.setImages(imageUrls);
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
    }

    public void initNSV() {
        final LinearLayout nav_wrap = nav_bar;
        final LinearLayout title_wrap = nav_bar.findViewById(R.id.title_wrap);
        final LinearLayout.LayoutParams layoutParams_d = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        nestedScrollView = (NestedScrollView) findViewById(R.id.goods_scroll);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int alpha = 0;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < 255) {
                    alpha = scrollY;
                    title_wrap.setAlpha(alpha);
                    if(scrollY < 10){
                        if(title_wrap.getHeight() > 0){
                            title_wrap.setLayoutParams(layoutParams_d);
                        }
                    }else{
                        if(title_wrap.getHeight() <= 0){
                            title_wrap.setLayoutParams(layoutParams);
                        }
                    }
                    nav_wrap.setBackgroundColor(Color.argb(alpha, 238, 238, 238));
                } else {
                    if (alpha < 255) {
                        alpha = 255;
                        title_wrap.setAlpha(1);
                        nav_wrap.setBackgroundColor(Color.argb(alpha, 238, 238, 238));
                    }
                }
            }
        });
    }

    public void initData(){
        goods_price = (TextView) findViewById(R.id.goods_price);
        market_price = (TextView) findViewById(R.id.goods_market_price);
        market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
