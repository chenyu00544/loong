package com.vcvb.chenyu.shop.adapter.item.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.home.Adses;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class HomeAds6Item extends BaseItem<Adses> {
    public static final int TYPE = 7;

    public HomeAds6Item(Adses bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .home_ads_6_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        FlowLayout fl = holder.get(R.id.four_wrap);
        fl.removeAllViews();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width / 4, width / 4);
        for (int i = 0; i < mData.getAds().size(); i++) {
            int id = IdsUtils.generateViewId();
            ImageView iv = new ImageView(context);
            iv.setId(id);
            posMap.put(id, i);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(R.drawable.icon_no_pic).into(iv);
            iv.setOnClickListener(listener);
            fl.addView(iv);
            Glide.with(context).load(mData.getAds().get(i).getAd_code()).into(iv);
        }
    }
}
