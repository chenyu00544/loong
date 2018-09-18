package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;

public class OrderTotalItem extends BaseItem<OrderDetail> {
    public static final int TYPE = 5;

    public OrderTotalItem(OrderDetail bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_details_total_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv1 = holder.getTextView(R.id.textView201);
        TextView tv2 = holder.getTextView(R.id.textView202);
        TextView tv3 = holder.getTextView(R.id.textView203);
        TextView tv4 = holder.getTextView(R.id.textView204);
        tv1.setText(mData.getTotalPayFormat());
        tv2.setText(mData.getShipFreeFormat());
        tv3.setText(mData.getDiscountFormat());
        tv4.setText(mData.getTotalPayAbleFormat());
    }
}
