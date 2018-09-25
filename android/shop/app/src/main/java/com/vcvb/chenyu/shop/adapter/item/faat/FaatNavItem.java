package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;

import java.util.ArrayList;
import java.util.List;

public class FaatNavItem extends BaseItem<Faat> {
    public static final int TYPE = R.layout.faat_nav_item;
    public static final int POSTYPE = ConstantManager.Item.HEADER;
    public static int pos_dx = 0;

    private static RecyclerView recyclerView;
    private static LinearLayoutManager mLayoutManager;
    private static CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private static List<Item> cells;

    private ScrollListener scrollListener;

    public FaatNavItem(Faat bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public int getPosType() {
        return POSTYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(recyclerView == null){
            recyclerView = holder.get(R.id.navs_wrap);
            recyclerView.addOnScrollListener(listener);
            mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            mAdapter.addAll(getItems(mData.getHeader()));
        }else{
            recyclerView = holder.get(R.id.navs_wrap);
            mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            System.out.println(pos_dx);
            recyclerView.scrollBy(pos_dx, 0);
        }
    }

    public List<Item> getItems(List<Object> bean) {
        if (cells == null) {
            List<FaatNav> list = new ArrayList<>();
            for (int i = 0; i < mData.getHeader().size(); i++) {
                list.add((FaatNav) bean.get(i));
            }
            cells = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                cells.add(new FaatSubNavItem(list.get(i), context));
            }
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
            pos_dx += dx;
            if (scrollListener != null) {
                scrollListener.Scrolled(recyclerView, dx, dy);
            }
        }
    };
}
