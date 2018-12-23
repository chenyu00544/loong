package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.ReturnOrder;

public class OrderAfterSaleBriefItem extends BaseItem<ReturnOrder> {
    public static final int TYPE = R.layout.order_after_sale_brief_item;

    public OrderAfterSaleBriefItem(ReturnOrder bean, Context c) {
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
        final EditText tv = holder.get(R.id.editText21);
        if (mData.getReturn_brief() != null) {
            tv.setText(mData.getReturn_brief());
        }
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Intent intent = new Intent();
                intent.setAction("briefAction");
                intent.putExtra("data", tv.getText().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }
}
