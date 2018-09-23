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
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class FaatGoodsItem extends BaseItem<Faat> {
    public static final int TYPE = Integer.MAX_VALUE - 4;

    public FaatGoodsItem(Faat bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .faat_goods_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        ImageView iv = holder.getImageView(R.id.imageView124);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width / 3, width
                / 3);
        iv.setLayoutParams(params);
        Glide.with(context).load(mData.getGoods().getPic()).into(iv);
        TextView name = holder.getTextView(R.id.textView239);
        name.setText(mData.getGoods().getGoodsName());
        TextView price = holder.getTextView(R.id.textView240);
        price.setText(mData.getGoods().getGoodsPriceFormat());

    }
}
