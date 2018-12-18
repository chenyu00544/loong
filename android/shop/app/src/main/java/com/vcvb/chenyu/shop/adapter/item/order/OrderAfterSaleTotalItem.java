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

public class OrderAfterSaleTotalItem extends BaseItem<OrderDetail> {
    public static final int TYPE = R.layout.order_after_sale_total_item;

    public OrderAfterSaleTotalItem(OrderDetail bean, Context c) {
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
        TextView tv1 = holder.getTextView(R.id.textView201);
        TextView tv2 = holder.getTextView(R.id.textView202);
        TextView tv3 = holder.getTextView(R.id.textView245);

        Double pay_amount = 0.0;
        Double other_fee = 0.0;
        Double refund_total = 0.0;

        pay_amount = Double.valueOf(mData.getMoney_paid());
        other_fee = Double.valueOf(mData.getShipping_fee());
        refund_total = pay_amount - other_fee;

        String pay_total_str = "￥%.2f";
        tv1.setText(String.format(Locale.CHINA, pay_total_str, pay_amount));
        String other_fee_str = "- ￥%.2f";
        tv2.setText(String.format(Locale.CHINA, other_fee_str, other_fee));
        String refund_str = "￥%.2f";
        tv3.setText(String.format(Locale.CHINA, refund_str, refund_total));
    }
}
