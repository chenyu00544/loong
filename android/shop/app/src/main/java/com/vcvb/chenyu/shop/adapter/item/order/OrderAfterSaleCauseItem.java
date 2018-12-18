package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderReturnCause;

import java.util.List;

public class OrderAfterSaleCauseItem extends BaseItem<List<OrderReturnCause>> {
    public static final int TYPE = R.layout.order_after_sale_cause_item;

    public OrderAfterSaleCauseItem(List<OrderReturnCause> bean, Context c) {
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
        TextView tv1 = holder.getTextView(R.id.textView182);
        boolean b = true;
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isIs_select()) {
                b = false;
                tv1.setText(mData.get(i).getCause_name());
            }
        }

        if (b) {
            tv1.setText(R.string.return_cause);
        }
        View v = holder.getItemView();
        posMap.put(v.getId(), position);
        v.setOnClickListener(listener);
    }
}
