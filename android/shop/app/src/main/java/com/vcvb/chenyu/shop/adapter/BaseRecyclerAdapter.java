package com.vcvb.chenyu.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.vcvb.chenyu.shop.adapter.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mList = new ArrayList<>();
    protected AdapterView.OnItemClickListener mListener;
    protected Context mContext;
    public final static int ITEM_VIEW_TYPE_NORMAL = 0;
    public final static int ITEM_VIEW_TYPE_TITLE = 1;
    public final static int ITEM_VIEW_TYPE_BUTTOM = 2;
    public final static int ITEM_VIEW_TYPE_BUTTOM1 = 3;
    public final static int ITEM_VIEW_TYPE_BUTTOM2 = 4;
    protected LayoutInflater mInflater;


    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mListener = listener;
    }

    public BaseRecyclerAdapter(Context context, List<T> list) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        if (list != null)
            this.mList = list;
    }

    public abstract BaseViewHolder getHolder(View itemView, int itemType, AdapterView
            .OnItemClickListener listener);

    public abstract int getLayoutId(int viewType);

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(getLayoutId(viewType), parent, false);
        return getHolder(itemView, viewType, mListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindViewHolder(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
