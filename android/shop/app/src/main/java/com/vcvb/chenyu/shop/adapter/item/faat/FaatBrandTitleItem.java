package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.faat.BrandNav;

public class FaatBrandTitleItem extends BaseItem<BrandNav> {
    public static final int TYPE = R.layout.faat_brand_title_item;

    public FaatBrandTitleItem(BrandNav bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        View v = holder.get(R.id.banner);
        v.setBackgroundColor(Color.parseColor(mData.getColor()));
        TextView tv = holder.get(R.id.textView269);
        tv.setText(mData.getCat_alias_name());
        tv.setTextColor(context.getResources().getColor(R.color.white));
    }
}
