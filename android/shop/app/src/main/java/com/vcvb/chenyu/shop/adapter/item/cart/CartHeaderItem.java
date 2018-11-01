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
    public static final int TYPE = 2;

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
                .inflate(R.layout
                        .cart_content_have_data_title_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView storeName = holder.getTextView(R.id.textView95);
        storeName.setText(mData.getShop().getShop_name());
        CheckBox cb = (CheckBox) holder.getView(R.id.checkBox2);
        cb.setChecked(mData.isCheckAll());
    }
}
