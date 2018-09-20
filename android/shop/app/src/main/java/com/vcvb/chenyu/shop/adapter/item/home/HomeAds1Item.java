package com.vcvb.chenyu.shop.adapter.item.home;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.home.Adses;

public class HomeAds1Item extends BaseItem<Adses> {
    public static final int TYPE = 2;

    public HomeAds1Item(Adses bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .home_ads_1_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        Integer[] ids = new Integer[]{R.id.imageView99, R.id.imageView103, R.id.imageView102};
        for (int i=0;i<ids.length;i++){
            ImageView iv = holder.getImageView(ids[i]);
            posMap.put(ids[i], i);
            iv.setOnClickListener(listener);
            if(i==0){
                set.constrainWidth(iv.getId(), width * 150 / 375);
                set.constrainHeight(iv.getId(), width * 150 / 375 * 10 / 9);
            }else{
                set.constrainWidth(iv.getId(), width * 225 / 375);
                set.constrainHeight(iv.getId(), width * 150 / 375 * 5 / 9);
            }
        }
        set.applyTo(cly);
    }
}
