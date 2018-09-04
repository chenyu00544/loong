package com.vcvb.chenyu.shop.adapter.itemclick;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemClickSupport {
    private static final int KEY = 0x99999999;
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnChildItemClickListener mOnChildItemClickListener;
    private static int clildId = -1;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(mRecyclerView, v, holder.getAdapterPosition());
            }
        }
    };

    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
            return false;
        }
    };

    private View.OnClickListener mOnChildClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnChildItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                mOnChildItemClickListener.onChildItemClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener mAttachListener = new RecyclerView
            .OnChildAttachStateChangeListener() {

        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnChildItemClickListener != null) {
                View v = view.findViewById(clildId);
                if (v != null) {
                    v.setOnClickListener(mOnChildClickListener);
                }
            }

            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
        }
    };

    /**
     * ItemClickSupport的私有构造方法
     */
    private ItemClickSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(KEY, this);
        // 为RecyclerView设置OnChildAttachStateChangeListener事件监听
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    /**
     * 为RecyclerView设置ItemClickSupport
     */
    public static ItemClickSupport addTo(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new ItemClickSupport(view);
        }
        return support;
    }

    public static ItemClickSupport BuildTo(RecyclerView view, int id) {
        clildId = id;
        ItemClickSupport support = (ItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new ItemClickSupport(null);
        }
        return support;
    }

    /**
     * 为RecyclerView移除ItemClickSupport
     */
    public static ItemClickSupport removeFrom(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(KEY);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    /**
     * 为RecyclerView设置点击事件监听
     */
    public ItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    /**
     * 为RecyclerView设置长按事件监听
     */
    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    /**
     * 为子View设置点击事件监听
     */

    public ItemClickSupport setOnChildClickListener(OnChildItemClickListener listener) {
        mOnChildItemClickListener = listener;
        return this;
    }

    /**
     * 为RecyclerView移除OnChildAttachStateChangeListener事件监听
     */
    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(KEY, null);
    }

    /**
     * RecyclerView的点击事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, View itemView, int position);
    }

    /**
     * RecyclerView的长按事件监听接口
     */
    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, View itemView, int position);
    }

    /**
     * RecyclerView的长按事件监听接口
     */
    public interface OnChildItemClickListener {
        void onChildItemClicked(RecyclerView recyclerView, View itemView, int position);
    }
}
