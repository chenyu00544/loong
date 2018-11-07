package com.vcvb.chenyu.shop.adapter.item.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;

public class CartHeaderItem extends BaseItem<CartListBean> {
    public static final int TYPE = R.layout.cart_content_have_data_title_item;

    public CartHeaderItem(CartListBean bean, Context c) {
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
        TextView storeName = holder.get(R.id.textView95);
        storeName.setText(mData.getShop().getShop_name());
        CheckBox cb = holder.get(R.id.checkBox2);
        cb.setChecked(mData.isCheckAll());

        posMap.put(cb.getId(), position);
        cb.setOnClickListener(listener);
        posMap.put(storeName.getId(), position);
        storeName.setOnClickListener(listener);
    }
}
