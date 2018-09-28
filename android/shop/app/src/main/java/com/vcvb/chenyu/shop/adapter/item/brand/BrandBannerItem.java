package com.vcvb.chenyu.shop.adapter.item.brand;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.faat.Banner;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class BrandBannerItem extends BaseItem<Banner> {
    public static final int TYPE = R.layout.faat_banner_item;

    public BrandBannerItem(Banner bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        View v = holder.get(R.id.banner);
        ImageView iv = holder.get(R.id.imageView122);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width, width * mData
                .getHeight() / mData.getWidth());
        v.setLayoutParams(params);
        RequestOptions requestOptions = RequestOptions.centerCropTransform().diskCacheStrategy(DiskCacheStrategy
                .AUTOMATIC).skipMemoryCache(true).override
                (width, width * mData.getHeight() / mData.getWidth());
        Glide.with(context).load(mData.getBackGroundPic()).apply(requestOptions).into(iv);
    }
}
