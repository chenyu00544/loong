package com.vcvb.chenyu.shop.adapter.item.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.javaBean.home.Adses;

import java.util.ArrayList;
import java.util.List;

public class HomeAds9Item extends BaseItem<Adses> {
    public static final int TYPE = 10;
    private RecyclerView recyclerView;
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
        if(recyclerView == null){
            recyclerView = (RecyclerView) holder.getView(R.id.goods_wrap);
            CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            mAdapter.addAll(getItems(mData));
        }
    }
    public List<Item> getItems(Adses bean) {
        List<Item> cells = new ArrayList<>();
        if (bean.getAds() != null && bean.getAds().size() > 0) {
            for (int i = 0; i < bean.getAds().size(); i++) {
                cells.add(new HomeAds9SubItem(bean.getAds().get(i), context));
            }
        }
        return cells;
    }
}
