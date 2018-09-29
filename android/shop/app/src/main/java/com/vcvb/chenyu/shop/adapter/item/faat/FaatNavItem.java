package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCGridAdapter;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;

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
        adapter.addAll(getNavItems(mData));
        List<FaatNav> navs = (List<FaatNav>) mData.getHeader();
        boolean bool = true;
        for (int i = 0; i < navs.size(); i++) {
            if (navs.get(i).getIsSelect() == true) {
                selectNavs(i);
                bool = false;
            }
        }
        if (bool) {
            navs.get(0).setIsSelect(true);
            navView.scrollToPosition(0);
        }
    }

    public List<Item> getNavItems(Faat bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < ((List<FaatNav>) bean.getHeader()).size(); i++) {
            FaatSubNavItem faatSubNavItem = new FaatSubNavItem(((List<FaatNav>) bean.getHeader()).get(i), context, (
                    (List<FaatNav>) bean.getHeader()).size());
            faatSubNavItem.setOnItemClickListener(listener);
            cells.add(faatSubNavItem);
        }
        return cells;
    }

    public void selectNavs(int pos) {
        List<FaatNav> navs = (List<FaatNav>) mData.getHeader();
        if (pos > 0 && pos <= 2) {
            navView.scrollToPosition(0);
        } else if (pos > navs.size() - 3 && pos < navs.size() - 1) {
            navView.scrollToPosition(navs.size() - 1);
        } else {
            navView.scrollToPosition(pos-2);
        }
    }

    FaatSubNavItem.OnClickListener listener = new FaatSubNavItem.OnClickListener() {
        @Override
        public void onClicked(View view, int pos) {
            if (onItemClickListener != null) {
                if (mData.getHeader() != null) {
                    for (int i = 0; i < ((List<FaatNav>) mData.getHeader()).size(); i++) {
                        ((List<FaatNav>) mData.getHeader()).get(i).setIsSelect(false);
                    }
                    ((List<FaatNav>) mData.getHeader()).get(pos).setIsSelect(true);
                }
                adapter.notifyDataSetChanged();
                onItemClickListener.clicked(mData.getGroup(), pos);
            }
        }
    };
}
