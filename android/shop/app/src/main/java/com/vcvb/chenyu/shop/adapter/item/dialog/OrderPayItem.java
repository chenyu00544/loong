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
import com.vcvb.chenyu.shop.javaBean.order.Pay;

import java.util.Locale;

public class OrderPayItem extends BaseItem<Pay> {
    public static final int TYPE = R.layout.dialog_order_pay_item;

    public OrderPayItem(Pay bean, Context c) {
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
        TextView tv1 = holder.get(R.id.textView92);
        TextView tv2 = holder.get(R.id.textView93);

        RequestOptions requestOptions = RequestOptions.centerCropTransform().diskCacheStrategy
                (DiskCacheStrategy.AUTOMATIC).override(120, 120);
        tv1.setText(mData.getPay_name());
        if (mData.getPay_code().equals("alipay")) {
            Glide.with(context).load(R.drawable.alipay).apply(requestOptions).into(iv);
        } else if (mData.getPay_code().equals("wxpay")) {
            Glide.with(context).load(R.drawable.wechat).apply(requestOptions).into(iv);
        } else {
            tv1.setText(R.string.alipay);
            Glide.with(context).load(R.drawable.alipay).apply(requestOptions).into(iv);
        }
        tv2.setText(String.format(Locale.CANADA, "费用 %d", mData.getPay_fee()));
        posMap.put(v.getId(), position);
        v.setOnClickListener(listener);
    }
}
