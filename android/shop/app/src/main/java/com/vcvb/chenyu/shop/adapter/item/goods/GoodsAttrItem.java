package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;

public class GoodsAttrItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = R.layout.goods_attr_item;

    public GoodsAttrItem(GoodsDetail bean, Context c) {
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
        String attr = "";
        TextView tv = holder.getTextView(R.id.textView1);
        for (int i = 0; i < mData.getMultiAttrs().size(); i++) {
            for (int j = 0; j < mData.getMultiAttrs().get(i).size(); j++) {
                if (mData.getMultiAttrs().get(i).get(j).getIsSelect() == true) {
                    attr += mData.getMultiAttrs().get(i).get(j).getAttr_value() + " ";
                }
            }
        }
        tv.setText(attr);

        View v = holder.getItemView();
        posMap.put(v.getId(), 0);
        v.setOnClickListener(listener);
    }
}
