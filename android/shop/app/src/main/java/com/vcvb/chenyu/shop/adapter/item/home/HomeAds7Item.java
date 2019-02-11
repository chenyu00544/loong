package com.vcvb.chenyu.shop.adapter.item.home;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.home.Adses;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.HashMap;

public class HomeAds7Item extends BaseItem<Adses> {
    public static final int TYPE = 8;
    public HashMap<Integer, Integer> groupMap = new HashMap<>();
    public OnClickListener onClickListener;

    public HomeAds7Item(Adses bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .home_ads_7_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        ImageView iv = holder.get(R.id.imageView113);
        set.constrainWidth(iv.getId(), width);
        set.constrainHeight(iv.getId(), width / 4);
        set.applyTo(cly);
        posMap.put(iv.getId(), 0);
        groupMap.put(iv.getId(), position);
        iv.setOnClickListener(listener);
        if(mData.getAds() != null){
            Glide.with(context).load(mData.getAds().get(0).getAd_code()).into(iv);
        }
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public interface OnClickListener {
        void onClicked(View view, int pos, int group);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, posMap.get(view.getId()), groupMap.get(view.getId()));
            }
        }
    };
}
