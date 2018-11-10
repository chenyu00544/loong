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
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;

public class UserRealItem extends BaseItem<UserInfoBean> {
    public static final int TYPE = R.layout.user_real_item;

    public UserRealItem(UserInfoBean bean, Context c) {
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
    public void onBindViewHolder(CYCBaseViewHolder holder, final int position) {
        final EditText tv = (EditText) holder.getView(R.id.editText12);
        tv.setHint(mData.getTitle());
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
                if(position == 1){
                    intent.setAction("cardNameChange");
                    intent.putExtra("data", String.valueOf(tv.getText()));
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }else if(position == 2){
                    intent.setAction("cardNumChange");
                    intent.putExtra("data", String.valueOf(tv.getText()));
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            }
        });
    }
}
