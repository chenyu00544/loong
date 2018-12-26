package com.vcvb.chenyu.shop.adapter.item.categray;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.cate.SubCategroy;
import com.vcvb.chenyu.shop.javaBean.home.Ads;

public class CategroyAdsItem extends BaseItem<SubCategroy> {
    public static final int TYPE = R.layout.categroy_ads_item;

    public CategroyAdsItem(SubCategroy bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        BaseViewHolder base = new BaseViewHolder(LayoutInflater.from(context)
                .inflate(TYPE, null));
        return base;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        Ads bean = (Ads) mData.getObjs().get(position);
        ImageView iv = holder.get(R.id.imageView126);
        Glide.with(context).load(bean.getAd_code()).into(iv);
    }
}
