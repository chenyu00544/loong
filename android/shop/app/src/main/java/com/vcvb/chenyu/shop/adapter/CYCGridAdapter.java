package com.vcvb.chenyu.shop.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.adapter.base.CYCBaseAdapter;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.adapter.item.NoDataItem;

public class CYCGridAdapter extends CYCBaseAdapter {
    public static final int ERROR_TYPE = Integer.MAX_VALUE - 1;
    public static final int EMPTY_TYPE = Integer.MAX_VALUE - 2;
    public static final int GOODS_V_ITEM = Integer.MAX_VALUE - 3;

    private NoDataItem noDataItem;

    public CYCGridAdapter() {
        noDataItem = new NoDataItem(null, null);
    }

    @Override
    protected void onViewHolderBound(CYCBaseViewHolder holder, int position) {
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        //处理GridView 布局
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (viewType == ERROR_TYPE || viewType == EMPTY_TYPE) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if(viewType == GOODS_V_ITEM){
                        return gridLayoutManager.getSpanCount()/2;
                    }
                    return 6;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        // 处理StaggeredGridLayoutManager 显示这个Span
        int position = holder.getAdapterPosition();
        int viewType = getItemViewType(position);
        if (isStaggeredGridLayout(holder)) {
            if (viewType == ERROR_TYPE || viewType == EMPTY_TYPE) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager
                        .LayoutParams) holder.itemView.getLayoutParams();
                //设置显示整个span
                params.setFullSpan(true);
            }
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager
                .LayoutParams) {
            return true;
        }
        return false;
    }

    public void showNoData() {
        clear();
        add(noDataItem);
    }

    public void showNoDataKeepCount(int keepCount) {
        if (keepCount < 0 || keepCount > mData.size()) {
            return;
        }
        remove(keepCount, mData.size() - keepCount);
        if (mData.contains(noDataItem)) {
            mData.remove(noDataItem);
        }
        add(noDataItem);
    }

    public void hideNoData() {
        if (mData.contains(noDataItem)) {
            remove(noDataItem);
        }
    }


}
