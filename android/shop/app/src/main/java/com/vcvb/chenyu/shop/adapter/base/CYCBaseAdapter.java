package com.vcvb.chenyu.shop.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CYCBaseAdapter<C extends BaseItem> extends RecyclerView
        .Adapter<CYCBaseViewHolder> {
    public static final String TAG = "CYCBaseAdapter";
    protected List<C> mData;

    public CYCBaseAdapter() {
        mData = new ArrayList<>();
    }

    public void setData(List<C> data) {
        addAll(data);
        notifyDataSetChanged();
    }

    public List<C> getData() {
        return mData;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (int i = 0; i < getItemCount(); i++) {
            if (viewType == mData.get(i).getItemType()) {
                return mData.get(i).onCreateViewHolder(parent, viewType);
            }
        }

        throw new RuntimeException("wrong viewType");
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        mData.get(position).onBindViewHolder(holder, position);
    }

    //资源回收
    @Override
    public void onViewDetachedFromWindow(CYCBaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.e(TAG, "onViewDetachedFromWindow invoke...");
        //释放资源
        int position = holder.getAdapterPosition();
        //越界检查
        if (position < 0 || position >= mData.size()) {
            return;
        }
        mData.get(position).releaseResource();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }

    /**
     * add one cell
     *
     * @param items
     */
    public void add(C items) {
        mData.add(items);
        int index = mData.indexOf(items);
        notifyItemChanged(index);
    }

    public void add(int index, C items) {
        mData.add(index, items);
        notifyItemChanged(index);
    }

    /**
     * remove a cell
     *
     * @param items
     */
    public void remove(C items) {
        int indexOfCell = mData.indexOf(items);
        remove(indexOfCell);
    }

    public void remove(int index) {
        mData.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * @param start
     * @param count
     */
    public void remove(int start, int count) {
        if ((start + count) > mData.size()) {
            return;
        }
        int size = getItemCount();
        for (int i = start; i < size; i++) {
            mData.remove(i);
        }
        notifyItemRangeRemoved(start, count);
    }

    /**
     * add a cell list
     *
     * @param items
     */
    public void addAll(List<C> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        Log.e("zhouwei", "addAll cell size:" + items.size());
        mData.addAll(items);
        notifyItemRangeChanged(mData.size() - items.size(), mData.size());
    }

    public void addAll(int index, List<C> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        mData.addAll(index, items);
        notifyItemRangeChanged(index, index + items.size());
    }

    public void clear() {
        if(mData.size() > 0){
            mData.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 如果子类需要在onBindViewHolder 回调的时候做的操作可以在这个方法里做
     *
     * @param holder
     * @param position
     */
    protected abstract void onViewHolderBound(CYCBaseViewHolder holder, int position);
}
