package com.vcvb.chenyu.shop.adapter.item.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;

public class AddressItem extends BaseItem<AddressBean> {
    public static final int TYPE = R.layout.address_item;

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
                .inflate(TYPE, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView name = holder.getTextView(R.id.textView126);
        TextView phone = holder.getTextView(R.id.textView127);
        TextView tv2 = holder.getTextView(R.id.textView128);
        TextView tv3 = holder.getTextView(R.id.textView124);
        CheckBox cb = (CheckBox) holder.getView(R.id.checkBox5);
        String consignee_fromat = "联系人: %s";
        String phone_fromat = "手机号码: %s";
        name.setText(String.format(consignee_fromat, mData.getConsignee()));
        phone.setText(String.format(phone_fromat, mData.getMobile()));
        String address_fromat = "%s %s %s %s";
        tv2.setText(String.format(address_fromat, mData.getProvince_name(), mData.getCity_name(),
                mData.getDistrict_name(), mData.getAddress()));
        if (mData.getDef() == 1) {
            cb.setChecked(true);
        } else {
            cb.setChecked(false);
        }

        View v = holder.get(R.id.view5);
        posMap.put(v.getId(), position);
        v.setOnClickListener(listener);
        posMap.put(cb.getId(), position);
        cb.setOnClickListener(listener);
        posMap.put(tv3.getId(), position);
        tv3.setOnClickListener(listener);
        View v2 = holder.get(R.id.view37);
        posMap.put(v2.getId(), position);
        v2.setOnClickListener(listener);
    }
}
