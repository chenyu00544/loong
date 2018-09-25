package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;

public class FaatBannerItem extends BaseItem<Faat> {
    public static final int TYPE = R.layout.faat_banner_item;
    public static final int POSTYPE = ConstantManager.Item.BANNER;

    public FaatBannerItem(Faat bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public int getPosType() {
        return POSTYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ImageView iv = holder.get(R.id.imageView122);
        Glide.with(context).load("http://scimg.jb51.net/allimg/161202/102-161202094551Z8.jpg").into(iv);
    }
}
