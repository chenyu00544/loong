package com.vcvb.chenyu.shop.adapter.item.faat;

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
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;

import java.util.ArrayList;
import java.util.List;

public class FaatNavItem extends BaseItem<Faat> {
    public static final int TYPE = 4;
    private RecyclerView recyclerView;
    private ScrollListener scrollListener;
    private LinearLayoutManager mLayoutManager;

    public FaatNavItem(Faat bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .faat_nav_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        if (recyclerView == null) {
            recyclerView = (RecyclerView) holder.getView(R.id.navs_wrap);
            CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
            mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            recyclerView.addOnScrollListener(listener);
            mAdapter.addAll(getItems(mData.getFaatNavs()));
        } else {
            recyclerView.scrollBy(mData.getDx(), mData.getDy());
        }
    }

    public List<Item> getItems(List<FaatNav> bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {
            cells.add(new FaatSubNavItem(bean.get(i), context));
        }
        return cells;
    }

    public interface ScrollListener {
        void Scrolled(RecyclerView recyclerView, int dx, int dy);
    }

    public void setItemScrollListener(ScrollListener listener) {
        scrollListener = listener;
    }

    RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (scrollListener != null) {
                scrollListener.Scrolled(recyclerView, dx, dy);
            }
        }
    };
}
