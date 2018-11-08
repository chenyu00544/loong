package com.vcvb.chenyu.shop.adapter.item.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;

public class AddressItem extends BaseItem<AddressBean> {
    public static final int TYPE = 1;

    public AddressItem(AddressBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView126);
        TextView tv2 = holder.getTextView(R.id.textView128);
        CheckBox cb = (CheckBox) holder.getView(R.id.checkBox5);
        String consignee_fromat = "联系人: %s 手机号码: %s";
        tv.setText(String.format(consignee_fromat, mData.getConsignee(), mData.getMobile()));
        String address_fromat = "%s %s %s %s";
        tv2.setText(String.format(address_fromat, mData.getProvince_name(), mData.getCity_name(),
                mData.getDistrict_name(), mData.getAddress()));
        if (mData.isDef()) {
            cb.setChecked(true);
        } else {
            cb.setChecked(false);
        }
    }
}
