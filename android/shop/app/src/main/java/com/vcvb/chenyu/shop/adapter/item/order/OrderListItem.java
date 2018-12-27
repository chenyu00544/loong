package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
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
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.javaBean.order.OrderGoods;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.List;
import java.util.Locale;

import cn.iwgang.countdownview.CountdownView;

public class OrderListItem extends BaseItem<OrderDetail> {
    public static final int TYPE = R.layout.order_content_have_data_list_item;

    public OrderListItem(OrderDetail bean, Context c) {
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
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        View wrap = holder.get(R.id.order_wrap);

        cly.removeAllViews();
        View header = LayoutInflater.from(context).inflate(R.layout
                .order_content_have_data_header_item, null);
        cly.addView(header);
        set.constrainHeight(header.getId(), ToolUtils.dip2px(context, 40));
        set.connect(header.getId(), ConstraintSet.TOP, wrap.getId(), ConstraintSet.TOP, 0);
        set.connect(header.getId(), ConstraintSet.LEFT, wrap.getId(), ConstraintSet.LEFT, 0);
        set.connect(header.getId(), ConstraintSet.RIGHT, wrap.getId(), ConstraintSet.RIGHT, 0);

        TextView orderSn = header.findViewById(R.id.textView193);
        orderSn.setText(mData.getOrder_sn());
        TextView addTime = header.findViewById(R.id.textView194);
        if (mData.getOrder_status() == 1 && mData.getPay_status() == 0 && mData
                .getShipping_status() == 0) {
            addTime.setText(mData.getAdd_time_date());
        } else if (mData.getPay_status() == 2 && mData.getShipping_status() == 0) {
            addTime.setText(mData.getPay_time_date());
        } else if (mData.getShipping_status() == 1) {
            addTime.setText(mData.getShipping_time_date());
        } else if (mData.getShipping_status() == 2) {
            addTime.setText(mData.getConfirm_take_time_date());
        } else {
            addTime.setText(mData.getAdd_time_date());
        }

        int view_id = header.getId();

        List<OrderGoods> orderGoodsList = mData.getOrderGoodses();
        for (int i = 0; i < orderGoodsList.size(); i++) {
            View og = LayoutInflater.from(context).inflate(R.layout.order_content_have_data_item,
                    null);
            og.setId(IdsUtils.generateViewId());
            set.constrainHeight(og.getId(), ToolUtils.dip2px(context, 100));
            set.connect(og.getId(), ConstraintSet.TOP, view_id, ConstraintSet.BOTTOM, 0);
            set.connect(og.getId(), ConstraintSet.LEFT, wrap.getId(), ConstraintSet.LEFT, 0);
            set.connect(og.getId(), ConstraintSet.RIGHT, wrap.getId(), ConstraintSet.RIGHT, 0);
            view_id = og.getId();
            cly.addView(og);

            ImageView iv = og.findViewById(R.id.imageView41);
            RequestOptions requestOptions = RequestOptions.centerCropTransform().placeholder(R
                    .drawable.icon_no_pic).dontAnimate();
            Glide.with(context).load(orderGoodsList.get(i).getOriginal_img()).apply
                    (requestOptions).into(iv);

            TextView goods_name = og.findViewById(R.id.textView85);
            goods_name.setText(orderGoodsList.get(i).getGoods_name());
            TextView goods_attr = og.findViewById(R.id.textView86);
            goods_attr.setText(orderGoodsList.get(i).getGoods_attr());
            TextView faat = og.findViewById(R.id.textView89);
            TextView shop_price = og.findViewById(R.id.textView87);
            if (orderGoodsList.get(i).getIs_promote() == 1 && mData.getCurrent_time() <
                    orderGoodsList.get(i).getPromote_end_date()) {
                faat.setText("促销");
                faat.setAlpha(1);
                shop_price.setText(orderGoodsList.get(i).getPromote_price_format());
            } else {
                faat.setAlpha(0);
                shop_price.setText(orderGoodsList.get(i).getShop_price_format());
            }
            TextView market_price = og.findViewById(R.id.textView88);
            market_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            market_price.setText(orderGoodsList.get(i).getMarket_price_format());
            TextView num = og.findViewById(R.id.textView90);
            String str = "× %d";
            num.setText(String.format(Locale.CHINA, str, orderGoodsList.get(i).getO_goods_number
                    ()));

            posMap.put(og.getId(), position);
            og.setOnClickListener(listener);
        }

        View foot = LayoutInflater.from(context).inflate(R.layout
                .order_content_have_data_buttom_item, null);
        if (mData.getPay_status() == 0 && mData.getShipping_status() == 0 && mData
                .getComment_status() == 0) {
            foot = LayoutInflater.from(context).inflate(R.layout
                    .order_content_have_data_buttom_item, null);
            TextView order = foot.findViewById(R.id.goods_price);
            String str = "￥ %s";
            order.setText(String.format(Locale.CANADA, str, mData.getOrder_amount()));
            TextView cancel_order = foot.findViewById(R.id.cancel_order);
            posMap.put(cancel_order.getId(), position);
            cancel_order.setOnClickListener(listener);
            TextView now_pay = foot.findViewById(R.id.now_pay);
            posMap.put(now_pay.getId(), position);
            now_pay.setOnClickListener(listener);
        } else if (mData.getPay_status() == 2 && mData.getShipping_status() == 0 && mData
                .getOrder_status() == 1 && mData.getComment_status() == 0) {
            foot = LayoutInflater.from(context).inflate(R.layout
                    .order_content_have_data_buttom2_item, null);
            TextView after_sale = foot.findViewById(R.id.after_sale);
            posMap.put(after_sale.getId(), position);
            after_sale.setOnClickListener(listener);
            TextView buy_again = foot.findViewById(R.id.buy_again);
            posMap.put(buy_again.getId(), position);
            buy_again.setOnClickListener(listener);
        } else if (mData.getPay_status() == 2 && mData.getShipping_status() == 1 && mData
                .getOrder_status() == 1 && mData.getComment_status() == 0) {
            foot = LayoutInflater.from(context).inflate(R.layout
                    .order_content_have_data_buttom3_item, null);
            CountdownView cdv = foot.findViewById(R.id.countdownView);
            CountdownView cdv1 = foot.findViewById(R.id.countdownView1);
            Integer countDown = mData.getAuto_delivery_time() * 86400 - (mData.getCurrent_time()
                    - mData.getShipping_time());
            Long current_time = countDown.longValue() * 1000;
            if (countDown / 86400 > 2) {
                cdv.setAlpha(1);
                cdv1.setAlpha(0);
            } else {
                cdv.setAlpha(0);
                cdv1.setAlpha(1);
            }
            //毫秒数
            cdv.start(current_time);
            cdv1.start(current_time);

            TextView after_sale = foot.findViewById(R.id.after_sale);
            posMap.put(after_sale.getId(), position);
            after_sale.setOnClickListener(listener);
            TextView look_express = foot.findViewById(R.id.look_express);
            posMap.put(look_express.getId(), position);
            look_express.setOnClickListener(listener);
            TextView take_goods = foot.findViewById(R.id.take_goods);
            posMap.put(take_goods.getId(), position);
            take_goods.setOnClickListener(listener);
        } else if (mData.getPay_status() == 2 && mData.getShipping_status() == 2 && mData
                .getOrder_status() == 1 && mData.getComment_status() == 0) {
            foot = LayoutInflater.from(context).inflate(R.layout
                    .order_content_have_data_buttom4_item, null);
            TextView after_sale = foot.findViewById(R.id.after_sale);
            posMap.put(after_sale.getId(), position);
            after_sale.setOnClickListener(listener);
            TextView buy_again = foot.findViewById(R.id.buy_again);
            posMap.put(buy_again.getId(), position);
            buy_again.setOnClickListener(listener);
            TextView evaluate = foot.findViewById(R.id.evaluate);
            posMap.put(evaluate.getId(), position);
            evaluate.setOnClickListener(listener);
        } else if (mData.getPay_status() == 2 && mData.getShipping_status() == 2 && mData
                .getOrder_status() == 1 && mData.getComment_status() == 1) {
            foot = LayoutInflater.from(context).inflate(R.layout
                    .order_content_have_data_buttom6_item, null);
            TextView after_sale = foot.findViewById(R.id.after_sale);
            if (mData.getCurrent_time() < mData.getShipping_time() + mData.getAuto_delivery_time
                    () * 86400) {
                posMap.put(after_sale.getId(), position);
                after_sale.setOnClickListener(listener);
            }else{
                after_sale.setAlpha(0);
            }
            TextView buy_again = foot.findViewById(R.id.buy_again);
            posMap.put(buy_again.getId(), position);
            buy_again.setOnClickListener(listener);
            TextView evaluate = foot.findViewById(R.id.review_evaluate);
            posMap.put(evaluate.getId(), position);
            evaluate.setOnClickListener(listener);
        } else if (mData.getOrder_status() == 4 || mData.getOrder_status() == 8) {
            foot = LayoutInflater.from(context).inflate(R.layout
                    .order_content_have_data_buttom5_item, null);
            TextView sale_after = foot.findViewById(R.id.sale_after_return);
            posMap.put(sale_after.getId(), position);
            sale_after.setOnClickListener(listener);
        }

        cly.addView(foot);
        set.constrainHeight(foot.getId(), ToolUtils.dip2px(context, 45));
        set.connect(foot.getId(), ConstraintSet.TOP, view_id, ConstraintSet.BOTTOM, 0);
        set.connect(foot.getId(), ConstraintSet.LEFT, wrap.getId(), ConstraintSet.LEFT, 0);
        set.connect(foot.getId(), ConstraintSet.RIGHT, wrap.getId(), ConstraintSet.RIGHT, 0);
        set.connect(foot.getId(), ConstraintSet.BOTTOM, wrap.getId(), ConstraintSet.BOTTOM, 0);
        set.applyTo(cly);
    }
}
