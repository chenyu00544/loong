package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttr;

import java.util.List;

public class GoodsAttrItem extends BaseItem<List<GoodsAttr>> {
    public static final int TYPE = 5;

    public GoodsAttrItem(List<GoodsAttr> beans, Context c) {
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
            attr += mData.get(i).getAttrName() + " ";
        }
        tv.setText(attr);
    }
}
