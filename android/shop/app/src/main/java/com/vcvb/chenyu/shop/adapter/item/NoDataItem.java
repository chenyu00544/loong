package com.vcvb.chenyu.shop.adapter.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;

public class NoDataItem extends BaseItem<CartListBean> {
    public static final int TYPE = Integer.MAX_VALUE - 2;

    public NoDataItem(CartListBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .cart_content_no_data, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {

    }
}
