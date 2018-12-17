package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.faat.Brand;

public class FaatBrandItem extends BaseItem<Brand> {
    public static final int TYPE = R.layout.faat_brand_item;

    public FaatBrandItem(Brand bean, Context c) {
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
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        ImageView iv = holder.get(R.id.imageView122);
        ImageView iv1 = holder.get(R.id.imageView133);

        RequestOptions requestOptions = RequestOptions.centerCropTransform().diskCacheStrategy(DiskCacheStrategy
                .AUTOMATIC);
        Glide.with(context).load(mData.getBrand_bg_app()).apply(requestOptions).into(iv);
        Glide.with(context).load(mData.getBrand_logo()).apply(requestOptions).into(iv1);

        TextView tv1 = holder.get(R.id.textView267);
        tv1.setText(mData.getBrand_name());
        TextView tv2 = holder.get(R.id.textView268);
        tv2.setText(mData.getBrand_desc());
    }
}
