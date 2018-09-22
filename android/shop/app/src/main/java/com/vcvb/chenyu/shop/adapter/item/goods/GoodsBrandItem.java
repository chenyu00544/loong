package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.spacesitem.DefaultItemDecoration;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsBrand;

import java.util.ArrayList;
import java.util.List;

public class GoodsBrandItem extends BaseItem<GoodsBrand> {
    public static final int TYPE = 10;
    private DefaultItemDecoration defaultItemDecoration;
    private RecyclerView recyclerView;

    public GoodsBrandItem(GoodsBrand beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_brand_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView brand = holder.getTextView(R.id.textView28);
        View v = holder.getItemView();
        posMap.put(brand.getId(), -1);
        brand.setOnClickListener(listener);
        if (mData.getIsScroll() == 1) {
            if (recyclerView == null) {
                recyclerView = (RecyclerView) holder.getView(R.id.brand_list);
                CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                if (defaultItemDecoration == null) {
                    defaultItemDecoration = new DefaultItemDecoration(context, 5);
                    recyclerView.addItemDecoration(defaultItemDecoration);
                }
                if (mData.getGoodses().size() > 0) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)
                            recyclerView.getLayoutParams();
                    params.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                    recyclerView.setLayoutParams(params);
                }
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                mAdapter.addAll(getItems(mData));
                CYCItemClickSupport.addTo(recyclerView).setOnItemClickListener(itemListener);
            }
        }
    }

    CYCItemClickSupport.OnItemClickListener itemListener = new CYCItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
            if (onClickListener != null) {
                onClickListener.onClicked(itemView, position);
            }
        }
    };

    public List<Item> getItems(GoodsBrand bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < bean.getGoodses().size(); i++) {
            cells.add(new GoodsBrandSubItem(bean.getGoodses().get(i), context));
        }
        return cells;
    }
}
