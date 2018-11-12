package com.vcvb.chenyu.shop.adapter.item.user.real;

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
import com.vcvb.chenyu.shop.javaBean.user.UserReal;

public class UserRealItem extends BaseItem<UserReal> {
    public static final int TYPE = R.layout.user_real_item;

    public UserRealItem(UserReal bean, Context c) {
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
        final EditText tv = holder.get(R.id.editText12);
        final EditText tv2 = holder.get(R.id.editText17);
        if (mData.getReal_name() != null) {
            tv.setText(mData.getReal_name());
        }
        if (mData.getSelf_num() != null) {
            tv2.setText(mData.getSelf_num());
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
                intent.setAction("cardNameChange");
                intent.putExtra("data", String.valueOf(tv.getText()));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

        tv2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Intent intent = new Intent();
                intent.setAction("cardNumChange");
                intent.putExtra("data", String.valueOf(tv2.getText()));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }
}
