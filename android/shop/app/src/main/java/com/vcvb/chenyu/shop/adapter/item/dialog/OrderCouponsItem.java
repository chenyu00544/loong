package com.vcvb.chenyu.shop.adapter.item.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.faat.Coupons;

import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class OrderCouponsItem extends BaseItem<Coupons> {
    public static final int TYPE = R.layout.dialog_order_coupons_item;

    public OrderCouponsItem(Coupons bean, Context c) {
        super(bean, c);
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
        View v = holder.getItemView();

        ImageView iv = holder.get(R.id.imageView129);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(12, 0,
                RoundedCornersTransformation.CornerType.ALL);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true);
        Glide.with(context).load(R.drawable.coupons_bg).apply(requestOptions).into(iv);


        TextView tv1 = holder.get(R.id.textView94);
        tv1.setText(String.format(Locale.CANADA, "%d", mData.getCou_money()));

        TextView tv2 = holder.get(R.id.textView247);
        tv2.setText(String.format(Locale.CANADA, "%s", mData.getCou_name()));

        TextView tv3 = holder.get(R.id.textView248);
        tv3.setText(String.format(Locale.CANADA, "%s", mData.getCou_title()));

        TextView tv4 = holder.get(R.id.textView249);
        tv4.setText(String.format(Locale.CANADA, "使用日期:%s-%s", mData.getCou_start_time_format(),
                mData.getCou_end_time_format()));

        posMap.put(v.getId(), position);
        v.setOnClickListener(listener);
    }
}
