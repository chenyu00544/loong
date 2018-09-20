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

public class HomeAds5Item extends BaseItem<Adses> {
    public static final int TYPE = 6;

    public HomeAds5Item(Adses bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .home_ads_5_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        FlowLayout fl = (FlowLayout) holder.getView(R.id.three_wrap);
        fl.removeAllViews();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width / 3, width / 3);
        for (int i = 0; i < 9; i++) {
            int id = IdsUtils.generateViewId();
            ImageView iv = new ImageView(context);
            iv.setId(id);
            posMap.put(id, i);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(R.drawable.icon_no_pic).into(iv);
            iv.setOnClickListener(listener);
            fl.addView(iv);
        }
    }
}
