package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.faat.SecKillNav;

import cn.iwgang.countdownview.CountdownView;

public class FaatSecKillNavItem extends BaseItem<SecKillNav> {
    public static final int TYPE = R.layout.faat_seckill_nav_item;

    public FaatSecKillNavItem(SecKillNav bean, Context c) {
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
        TextView textView = holder.get(R.id.textView309);
        textView.setText(mData.getTitle());

        CountdownView cdv = holder.get(R.id.count_down);
        Integer countDown = mData.getEnd_time() - mData.getCurrent_time();
        Integer startTime = mData.getCurrent_time() - mData.getBegin_time();
        Long current_time = countDown.longValue() * 1000;
        if (current_time > 0 && startTime > 0) {
            cdv.setAlpha(1);
        } else if (current_time > 0 && startTime < 0) {
            cdv.setAlpha(0);
        }else{
            cdv.setAlpha(0);
        }
        cdv.start(current_time);
    }
}
