package com.vcvb.chenyu.shop.adapter.item.home;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.image.GlideImageLoader;
import com.vcvb.chenyu.shop.javaBean.home.Adses;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class HomeSlideItem extends BaseItem<Adses> {
    public static final int TYPE = R.layout.goods_slide_item;
    public OnClickListener onClickListener;
    public OnPageChangeListener onPageChangeListener;

    public HomeSlideItem(Adses beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(TYPE, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        Banner banner = holder.get(R.id.goods_slide);
        //设置内置样式，内含六种特效
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果，内含多种特效
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setDelayTime(5000);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());
        ArrayList<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < mData.getAds().size(); i++) {
            imageUrls.add(mData.getAds().get(i).getAd_code());
        }
        banner.setImages(imageUrls);
        banner.start();
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        set.constrainHeight(R.id.goods_slide, width / 2);
        set.applyTo(cly);
        banner.setOnBannerListener(listener);
        banner.setOnPageChangeListener(pageListener);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public interface OnClickListener {
        void onClicked(int pos);
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        onPageChangeListener = listener;
    }

    public interface OnPageChangeListener {
        void onPageChanged(int pos, Adses adses);
    }

    OnBannerListener listener = new OnBannerListener() {
        @Override
        public void OnBannerClick(int position) {
            if (onClickListener != null) {
                onClickListener.onClicked(position);
            }
        }
    };

    private ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageChanged(position, mData);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
