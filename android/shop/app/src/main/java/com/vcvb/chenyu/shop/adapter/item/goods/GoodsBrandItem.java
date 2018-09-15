package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsBrand;

import java.util.List;

public class GoodsBrandItem extends BaseItem<List<GoodsBrand>> {
    public static final int TYPE = 10;

    public GoodsBrandItem(List<GoodsBrand> beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_brand_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        HorizontalScrollView hsv = (HorizontalScrollView) holder.getView(R.id.brand_list);
        LinearLayout ly = hsv.findViewById(R.id.brand_list_wrap);
        ly.removeAllViews();
        for (int i = 0; i < mData.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.goods_brand_sub_item, null, false);
            ly.addView(view);
        }
    }
}
