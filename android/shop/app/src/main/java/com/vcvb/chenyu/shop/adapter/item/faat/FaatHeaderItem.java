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

public class FaatHeaderItem extends BaseItem<Faat> {
    public static final int TYPE = R.layout.faat_header_item;
    public static final int POSTYPE = ConstantManager.Item.ITEMHEADER;

    public FaatHeaderItem(Faat bean, Context c) {
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
        ImageView iv = holder.get(R.id.imageView123);
        Glide.with(context).load("http://58pic.ooopic.com/58pic/19/50/38/56e00c6189f99.jpg").into(iv);
    }
}
