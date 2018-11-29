package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.faat.Coupons;

import java.util.List;
import java.util.Locale;

public class OrderCouponsItem extends BaseItem<List<Coupons>> {
    public static final int TYPE = R.layout.order_details_coupons_item;

    public OrderCouponsItem(List<Coupons> bean, Context c) {
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
        TextView tv = holder.get(R.id.textView196);
        View v = holder.getItemView();

        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getDef() == 1) {
                tv.setText(String.format(Locale.CANADA, "-￥ %d", mData.get(i).getCou_money()));
            }
        }
        posMap.put(v.getId(), 1);
        v.setOnClickListener(listener);
    }
}
