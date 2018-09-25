package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class FaatGoodsItem extends BaseItem<Faat> {
    public static final int TYPE = R.layout.faat_goods_item;
    public static final int POSTYPE = ConstantManager.Item.ITEMS;

    public FaatGoodsItem(Faat bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public int getPosType() {
        return POSTYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        ImageView iv = holder.get(R.id.imageView124);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width / 3, width
                / 3);
        iv.setLayoutParams(params);
    }
}
