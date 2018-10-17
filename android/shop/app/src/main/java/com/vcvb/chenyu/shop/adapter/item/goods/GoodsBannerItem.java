package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.image.GlideImageLoader;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsBanner;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class GoodsBannerItem extends BaseItem<List<GoodsBanner>> {
    public static final int TYPE = 1;
    Banner banner;

    public GoodsBannerItem(List<GoodsBanner> beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_slide_item, null));
        banner = (Banner) base.getView(R.id.goods_slide);
        //设置内置样式，内含六种特效
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置轮播的动画效果，内含多种特效
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setDelayTime(5000);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());
        ArrayList<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            imageUrls.add(mData.get(i).getImg_original());
        }
        banner.setImages(imageUrls);
        banner.start();
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        set.constrainHeight(R.id.goods_slide, width);
        set.applyTo(cly);
    }
}
