package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsShipFree;

public class GoodsShipFreeItem extends BaseItem<GoodsShipFree> {
    public static final int TYPE = 7;

    public GoodsShipFreeItem(GoodsShipFree beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_ship_free_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView1);
        tv.setText(mData.getShipFree());

        View v = holder.getItemView();
        posMap.put(v.getId(), 0);
        v.setOnClickListener(listener);
    }
}
