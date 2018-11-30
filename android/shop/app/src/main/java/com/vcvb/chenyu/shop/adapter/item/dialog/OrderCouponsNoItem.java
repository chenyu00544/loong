package com.vcvb.chenyu.shop.adapter.item.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.faat.Coupons;

import java.util.Locale;

public class OrderCouponsNoItem extends BaseItem<Coupons> {
    public static final int TYPE = R.layout.dialog_order_coupons_no_item;

    public OrderCouponsNoItem(Coupons bean, Context c) {
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
        ImageView iv = holder.get(R.id.imageView129);

        TextView tv1 = holder.get(R.id.textView94);
        tv1.setText(String.format(Locale.CANADA, "%d", mData.getCou_money()));

        TextView tv2 = holder.get(R.id.textView247);
        tv2.setText(String.format(Locale.CANADA, "%s", mData.getCou_name()));

        TextView tv3 = holder.get(R.id.textView248);
        if(mData.isEnabled()){
            tv3.setText(String.format(Locale.CANADA, "%s", mData.getCou_title()));
            tv3.setTextColor(context.getResources().getColor(R.color.white));
        }else{
            tv3.setTextColor(context.getResources().getColor(R.color.red));
            tv3.setText("金额不够");
        }

        TextView tv4 = holder.get(R.id.textView249);
        tv4.setText(String.format(Locale.CANADA, "使用日期:%s-%s", mData.getCou_start_time_format(),
                mData.getCou_end_time_format()));
    }
}
