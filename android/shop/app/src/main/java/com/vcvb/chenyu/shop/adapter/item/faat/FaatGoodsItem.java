package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class FaatGoodsItem extends BaseItem<Goods> {
    public static final int TYPE = R.layout.faat_goods_item;

    public FaatGoodsItem(Goods bean, Context c) {
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
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        View item = holder.get(R.id.goods_item);
        ImageView iv = holder.get(R.id.imageView124);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width / 3, width
                / 3);
        iv.setLayoutParams(params);
        Glide.with(context).load(mData.getOriginal_img()).into(iv);
        TextView tv1 = holder.get(R.id.textView239);
        tv1.setText(mData.getGoods_name());
        TextView tv2 = holder.get(R.id.textView240);
        tv2.setText("1");

        View addCart = holder.get(R.id.view76);
    }
}
