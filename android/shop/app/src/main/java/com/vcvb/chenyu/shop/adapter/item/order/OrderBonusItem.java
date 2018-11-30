package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.faat.Bonus;

import java.util.List;
import java.util.Locale;

public class OrderBonusItem extends BaseItem<List<Bonus>> {
    public static final int TYPE = R.layout.order_details_bonus_item;

    public OrderBonusItem(List<Bonus> bean, Context c) {
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
        TextView tv = holder.get(R.id.textView196);
        View v = holder.getItemView();
        boolean b = true;
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getDef() == 1) {
                b = false;
                tv.setText(String.format(Locale.CANADA, "-￥ %s", mData.get(i).getType_money()));
            }
        }
        if(b){
            tv.setText(R.string.select);
        }
        posMap.put(v.getId(), 1);
        v.setOnClickListener(listener);
    }
}
