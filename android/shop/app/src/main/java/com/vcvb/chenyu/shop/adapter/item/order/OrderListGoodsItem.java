package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderGoods;

import java.util.Locale;

public class OrderListGoodsItem extends BaseItem<OrderGoods> {
    public static final int TYPE = R.layout.order_content_have_data_item;

    public OrderListGoodsItem(OrderGoods bean, Context c) {
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
        ImageView iv = holder.get(R.id.imageView41);
        RequestOptions requestOptions = RequestOptions.centerCropTransform().placeholder(R
                .drawable.icon_no_pic).dontAnimate();
        Glide.with(context).load(mData.getOriginal_img()).apply(requestOptions).into(iv);

        TextView goods_name = holder.get(R.id.textView85);
        goods_name.setText(mData.getGoods_name());
        TextView goods_attr = holder.get(R.id.textView86);
        goods_attr.setText(mData.getGoods_attr());
        TextView faat = holder.get(R.id.textView89);
        TextView shop_price = holder.get(R.id.textView87);
        if (mData.getIs_promote() == 1 && mData.getCurrent_time() < mData.getPromote_end_date()) {
            faat.setText("促销");
            faat.setAlpha(1);
            shop_price.setText(mData.getPromote_price_format());
        } else {
            faat.setAlpha(0);
            shop_price.setText(mData.getShop_price_format());
        }
        TextView market_price = holder.get(R.id.textView88);
        market_price.setText(mData.getMarket_price_format());
        TextView num = holder.get(R.id.textView90);
        String str = "× %d";
        num.setText(String.format(Locale.CHINA, str, mData.getO_goods_number()));

        View itemView = holder.getItemView();
        posMap.put(itemView.getId(), mData.getGoods_id());
        itemView.setOnClickListener(listener);
    }
}
