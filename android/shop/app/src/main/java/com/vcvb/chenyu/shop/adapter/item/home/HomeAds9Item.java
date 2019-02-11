package com.vcvb.chenyu.shop.adapter.item.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.javaBean.home.Adses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeAds9Item extends BaseItem<Adses> {
    public static final int TYPE = 10;
    private RecyclerView recyclerView;
    public HashMap<Integer, Integer> groupMap = new HashMap<>();
    public OnClickListener onClickListener;

    public HomeAds9Item(Adses bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .home_ads_9_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        final ImageView iv = holder.get(R.id.imageView115);
        final int pos = position;
        if (mData.getAds() != null) {
            Glide.with(context).load(mData.getAds().get(0).getAd_code()).into(iv);
            posMap.put(iv.getId(), 0);
            groupMap.put(iv.getId(), position);
            iv.setOnClickListener(listener);
        }
        if (recyclerView == null) {
            recyclerView = (RecyclerView) holder.getView(R.id.goods_wrap);
            CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            mAdapter.addAll(getItems(mData));
            CYCItemClickSupport.addTo(recyclerView).setOnItemClickListener(new CYCItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                    if (onClickListener != null) {
                        onClickListener.onClicked(itemView, position + 1, pos)
                        ;
                    }
                }
            });
        }
    }

    public List<Item> getItems(Adses bean) {
        List<Item> cells = new ArrayList<>();
        if (bean.getAds() != null && bean.getAds().size() > 0) {
            for (int i = 1; i < bean.getAds().size(); i++) {
                cells.add(new HomeAds9SubItem(bean.getAds().get(i), context));
            }
        }
        return cells;
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
