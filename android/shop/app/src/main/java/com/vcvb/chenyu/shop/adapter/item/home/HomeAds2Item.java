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

public class HomeAds2Item extends BaseItem<Adses> {
    public static final int TYPE = 3;
    public HashMap<Integer, Integer> groupMap = new HashMap<>();
    public OnClickListener onClickListener;

    public HomeAds2Item(Adses bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .home_ads_2_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        Integer[] ids = new Integer[]{R.id.imageView104, R.id.imageView105, R.id.imageView106, R
                .id.imageView107};
        for (int i = 0; i < ids.length; i++) {
            ImageView iv = holder.get(ids[i]);
            posMap.put(ids[i], i);
            groupMap.put(ids[i], position);
            iv.setOnClickListener(listener);
            set.constrainWidth(iv.getId(), width / 2);
            set.constrainHeight(iv.getId(), width * 5 / 16);
            Glide.with(context).load(mData.getAds().get(i).getAd_code()).into(iv);
        }
        set.applyTo(cly);
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
