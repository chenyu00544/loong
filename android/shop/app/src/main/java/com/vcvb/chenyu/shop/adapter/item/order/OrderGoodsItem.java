package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;

public class OrderGoodsItem extends BaseItem<OrderDetail> {
    public static final int TYPE = 3;

    public OrderGoodsItem(OrderDetail bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_details_goods_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        ImageView iv = holder.getImageView(R.id.imageView87);
        TextView tv1 = holder.getTextView(R.id.textView187);
        TextView tv2 = holder.getTextView(R.id.textView188);
        TextView tv3 = holder.getTextView(R.id.textView189);
        TextView tv4 = holder.getTextView(R.id.textView190);
        TextView tv5 = holder.getTextView(R.id.textView191);

        tv1.setText(mData.getGoodsName());
        tv2.setText(mData.getGoodsAttr());
        tv3.setText(mData.getGoodsPriceFormat());
        tv4.setText(mData.getGoodsMarketFormat());
        tv4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        tv5.setText("× "+mData.getGoodsNum());
    }
}
