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

public class OrderAfterSaleInvoiceNoItem extends BaseItem<ReturnOrder> {
    public static final int TYPE = R.layout.order_after_sale_invoice_no_item;

    public OrderAfterSaleInvoiceNoItem(ReturnOrder bean, Context c) {
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
        final EditText tv1 = holder.get(R.id.editText22);
        final EditText tv2 = holder.get(R.id.editText23);
        if(mData.getBack_shipping_name() != null ){
            tv1.setText(mData.getBack_shipping_name());
        }
        if(mData.getBack_invoice_no() != null ){
            tv2.setText(mData.getBack_invoice_no());
        }
        tv1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Intent intent = new Intent();
                intent.setAction("ShipNameAction");
                intent.putExtra("data", tv1.getText().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

        tv2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Intent intent = new Intent();
                intent.setAction("ShipNoAction");
                intent.putExtra("data", tv2.getText().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }
}
