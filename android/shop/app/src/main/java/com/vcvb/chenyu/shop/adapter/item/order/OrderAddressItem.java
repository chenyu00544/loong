package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.address.AddressBean;

import java.util.List;

public class OrderAddressItem extends BaseItem<List<AddressBean>> {
    public static final int TYPE = R.layout.order_details_address_item;

    public OrderAddressItem(List<AddressBean> bean, Context c) {
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
        TextView tv1 = holder.getTextView(R.id.textView183);
        TextView tv2 = holder.getTextView(R.id.textView184);
        TextView tv3 = holder.getTextView(R.id.textView186);
        TextView tv4 = holder.getTextView(R.id.textView91);

        if (mData.size() > 0) {
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).getDef() == 1) {
                    tv4.setAlpha(0);
                    tv1.setText(mData.get(i).getConsignee());
                    tv2.setText(mData.get(i).getMobile());
                    String str = "%s %s %s %s";
                    tv3.setText(String.format(str, mData.get(i).getProvince_name(), mData.get(i)
                            .getCity_name(), mData.get(i).getDistrict_name(), mData.get(i)
                            .getAddress()));

                    View v = holder.getItemView();
                    posMap.put(v.getId(), 1);
                    v.setOnClickListener(listener);
                }
            }
        } else {
            tv4.setAlpha(1);
            posMap.put(tv4.getId(), 1);
            tv4.setOnClickListener(listener);
        }
    }
}
