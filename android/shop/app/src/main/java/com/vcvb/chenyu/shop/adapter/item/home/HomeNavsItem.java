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

public class HomeNavsItem extends BaseItem<Adses> {
    public static final int TYPE = 1;

    public HomeNavsItem(Adses bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .home_navs_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        FlowLayout flowLayout = (FlowLayout) holder.getView(R.id.navs_wrap);
        flowLayout.removeAllViews();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width / 5, width / 4);
        for (int i = 0; i < mData.getAds().size(); i++) {
            int id = IdsUtils.generateViewId();
            ImageView imageView = new ImageView(context);
            imageView.setId(id);
            posMap.put(id, i);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(listener);
            Glide.with(context).load(R.drawable.icon_no_pic).into(imageView);
            flowLayout.addView(imageView);
        }
    }
}
