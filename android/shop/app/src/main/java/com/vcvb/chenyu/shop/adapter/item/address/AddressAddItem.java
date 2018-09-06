package com.vcvb.chenyu.shop.adapter.item.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;

public class AddressAddItem extends BaseItem<AddressBean> {
    public static final int TYPE = 2;

    public AddressAddItem(AddressBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout
                        .address_add, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {

    }
}
