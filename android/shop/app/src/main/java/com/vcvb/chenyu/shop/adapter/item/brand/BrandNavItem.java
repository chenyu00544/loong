package com.vcvb.chenyu.shop.adapter.item.brand;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCGridAdapter;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.faat.FaatSubNavItem;
import com.vcvb.chenyu.shop.javaBean.brand.Brand;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;

import java.util.ArrayList;
import java.util.List;

public class BrandNavItem extends BaseItem<Brand> {
    public static final int TYPE = R.layout.faat_nav_item;

    public RecyclerView navView;
    public CYCGridAdapter adapter = new CYCGridAdapter();

    public BrandNavItem(Brand bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        navView = holder.get(R.id.navs_wrap);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        navView.setLayoutManager(layoutManager);
        navView.setAdapter(adapter);
        adapter.clear();
        ((List<FaatNav>) mData.getHeader()).get(0).setIsSelect(true);
        adapter.addAll(getNavItems(mData));
    }

    public List<Item> getNavItems(Brand bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < ((List<FaatNav>) bean.getHeader()).size(); i++) {
            cells.add(new FaatSubNavItem(((List<FaatNav>) bean.getHeader()).get(i), context, (
                    (List<FaatNav>) bean.getHeader()).size()));
        }
        return cells;
    }
}
