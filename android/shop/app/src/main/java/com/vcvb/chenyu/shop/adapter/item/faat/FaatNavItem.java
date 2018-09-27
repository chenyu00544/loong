package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCGridAdapter;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;

import java.util.ArrayList;
import java.util.List;

public class FaatNavItem extends BaseItem<Faat> {
    public static final int TYPE = R.layout.faat_nav_item;

    public RecyclerView navView;
    public CYCGridAdapter adapter = new CYCGridAdapter();

    public FaatNavItem(Faat bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        if (navView == null) {
            navView = holder.get(R.id.navs_wrap);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            navView.setLayoutManager(layoutManager);
            navView.setAdapter(adapter);
            adapter.clear();
            mData.getFaatNavs().get(0).setIsSelect(true);
            adapter.addAll(getNavItems(mData));
            CYCItemClickSupport.addTo(navView).setOnItemClickListener(new CYCItemClickSupport
                    .OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                    if (onClickListener != null) {
                        onClickListener.onClicked(itemView, position);
                    }
                }
            });
        }
        navView.scrollToPosition(0);
    }

    public List<Item> getNavItems(Faat bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < bean.getFaatNavs().size(); i++) {
            cells.add(new FaatSubNavItem(bean.getFaatNavs().get(i), context, bean.getFaatNavs()
                    .size()));
        }
        return cells;
    }
}
