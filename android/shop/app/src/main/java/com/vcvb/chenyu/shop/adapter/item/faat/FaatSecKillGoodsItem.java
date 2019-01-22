package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.faat.SecKillGoods;

import cn.iwgang.countdownview.CountdownView;

public class FaatSecKillGoodsItem extends BaseItem<SecKillGoods> {
    public static final int TYPE = R.layout.faat_seckill_goods_item;

    public FaatSecKillGoodsItem(SecKillGoods bean, Context c) {
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
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        TextView tv1 = holder.get(R.id.textView310);
        tv1.setText(mData.getGoods_name());
        TextView tv2 = holder.get(R.id.textView311);

        tv2.setText(mData.getSec_price_format());
        TextView tv3 = holder.get(R.id.textView312);
        tv3.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        tv3.setText(mData.getMarket_price_format());
        TextView tv4 = holder.get(R.id.textView313);
        ImageView iv = holder.get(R.id.imageView157);
        Glide.with(context).load(mData.getOriginal_img()).into(iv);
        CountdownView cdv = holder.get(R.id.count_down);

        Integer countDown = mData.getEnd_time() - mData.getCurrent_time();
        Integer startTime = mData.getCurrent_time() - mData.getBegin_time();
        Long current_time = countDown.longValue() * 1000;
        if (current_time > 0 && startTime > 0) {
            cdv.setAlpha(1);
            tv4.setAlpha(0);
        } else if (current_time > 0 && startTime < 0) {
            cdv.setAlpha(0);
            tv4.setAlpha(1);
            tv4.setText(R.string.is_start);
        }else{
            cdv.setAlpha(0);
            tv4.setAlpha(1);
            tv4.setText(R.string.is_over);
        }
        //毫秒数
        cdv.start(current_time);
    }
}
