package com.vcvb.chenyu.shop.adapter.item.test;

import android.content.Context;
import android.view.LayoutInflater;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.browse.GroupBrowse;

public class TestTitleItem extends BaseItem<GroupBrowse> {
    public static final int TYPE = R.layout.browse_title_item;

    public TestTitleItem(GroupBrowse bean, Context c) {
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

    }
}
