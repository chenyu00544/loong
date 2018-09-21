package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttrs;

import java.util.List;

public class GoodsAttrItem extends BaseItem<List<GoodsAttrs>> {
    public static final int TYPE = 5;

    public GoodsAttrItem(List<GoodsAttrs> beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_attr_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        String attr = "";
        TextView tv = holder.getTextView(R.id.textView1);
        for (int i = 0; i < mData.size(); i++) {
            for (int j = 0; j < mData.get(i).getAttrs().size(); j++) {
                if (mData.get(i).getAttrs().get(j).getIsSelect() == true) {
                    attr += mData.get(i).getAttrs().get(j).getAttrName() + " ";
                }
            }
        }
        tv.setText(attr);

        View v = holder.getItemView();
        posMap.put(v.getId(), 0);
        v.setOnClickListener(listener);
    }
}
