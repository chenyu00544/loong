package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderGoods;

public class OrderAfterSaleGoodsItem extends BaseItem<OrderGoods> {
    public static final int TYPE = R.layout.order_after_sale_goods_item;

    public OrderAfterSaleGoodsItem(OrderGoods bean, Context c) {
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
        ImageView iv = holder.get(R.id.imageView87);
        TextView tv1 = holder.get(R.id.textView187);
        TextView tv2 = holder.get(R.id.textView188);
        TextView tv3 = holder.get(R.id.textView189);
        TextView tv4 = holder.get(R.id.textView190);
        TextView tv5 = holder.get(R.id.textView191);

        tv1.setText(mData.getGoods_name());
        tv2.setText(mData.getGoods_attr());

        if (mData.getIs_promote() == 1 && mData.getCurrent_time() < mData.getPromote_end_date()
                && mData.getCurrent_time() > mData.getPromote_start_date()) {
            tv3.setText(mData.getPromote_price_format());
        } else {
            tv3.setText(mData.getShop_price_format());
        }

        tv4.setText(mData.getMarket_price_format());
        tv4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        String str = "× %s";
        tv5.setText(String.format(str, mData.getO_goods_number()));
        RequestOptions requestOptions = RequestOptions.centerCropTransform().placeholder(R
                .drawable.icon_no_pic);
        Glide.with(context).load(mData.getOriginal_img()).apply(requestOptions).into(iv);
    }
}
