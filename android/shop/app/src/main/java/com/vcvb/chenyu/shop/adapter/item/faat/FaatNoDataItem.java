package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.faat.TeamGoods;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class FaatNoDataItem extends BaseItem<TeamGoods> {
    public static final int TYPE = R.layout.faat_no_data_item;

    public FaatNoDataItem(TeamGoods bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
    }
}
