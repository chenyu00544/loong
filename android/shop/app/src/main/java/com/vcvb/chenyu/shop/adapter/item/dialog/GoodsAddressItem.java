package com.vcvb.chenyu.shop.adapter.item.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;

public class GoodsAddressItem extends BaseItem<AddressBean> {
    public static final int TYPE = R.layout.dialog_goods_address_item;

    public GoodsAddressItem(AddressBean bean, Context c) {
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
        TextView tv1 = holder.getTextView(R.id.textView20);
        TextView tv2 = holder.getTextView(R.id.textView10);
        String consignee_fromat = "联系人: ：%s 手机号码: %s";
        tv1.setText(String.format(consignee_fromat, mData.getConsignee(), mData.getMobile()));
        String address_fromat = "%s %s %s %s";
        tv2.setText(String.format(address_fromat, mData.getProvince_name(), mData.getCity_name(),
                mData.getDistrict_name(), mData.getAddress()));

        View itemV = holder.getItemView();
        posMap.put(itemV.getId(), position);
        itemV.setOnClickListener(listener);
    }
}
