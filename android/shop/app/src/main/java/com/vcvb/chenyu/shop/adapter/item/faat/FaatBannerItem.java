package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;

public class FaatBannerItem extends BaseItem<Faat> {
    public static final int TYPE = R.layout.faat_banner_item;

    public FaatBannerItem(Faat bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        ImageView iv = holder.get(R.id.imageView122);
        Glide.with(context).load(mData.getBanner().getBackGroundPic()).into(iv);
    }
}
