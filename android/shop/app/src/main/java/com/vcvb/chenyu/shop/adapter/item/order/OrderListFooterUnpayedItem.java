package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;

import java.util.Locale;

public class OrderListFooterUnpayedItem extends BaseItem<OrderDetail> {
    public static final int TYPE = R.layout.order_content_have_data_buttom_item;

    public OrderListFooterUnpayedItem(OrderDetail bean, Context c) {
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
        TextView tv = holder.get(R.id.textView93);
        String str = "￥ %s";
        tv.setText(String.format(Locale.CHINA, str, mData.getOrder_amount()));

        TextView cancelOrder = holder.get(R.id.textView91);
        TextView payOrder = holder.get(R.id.textView95);
        posMap.put(cancelOrder.getId(), position);
        posMap.put(payOrder.getId(), position);
        cancelOrder.setOnClickListener(listener);
        payOrder.setOnClickListener(listener);
    }
}
