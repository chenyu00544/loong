package com.vcvb.chenyu.shop.adapter.b;

import android.content.Context;
import android.view.View;

import java.util.HashMap;

public abstract class BaseItem<T> implements Item {
    public T mData;
    public Context context;
    public OnItemClickListener onItemClickListener;
    public OnSubItemClickListener onSubItemClickListener;
    public HashMap<Integer, Integer> posMap = new HashMap<>();
    public HashMap<Integer, Integer> groupMap = new HashMap<>();

    public BaseItem(T t, Context c) {
        mData = t;
        context = c;
    }

    @Override
    public void releaseResource() {
        // 如果有需要回收的资源，子类自己实现
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    //item点击事件
    public interface OnItemClickListener {
        void clicked(int group, int pos);
    }

    public void setSubOnItemClickListener(OnSubItemClickListener listener) {
        onSubItemClickListener = listener;
    }

    //item内控件点击事件
    public interface OnSubItemClickListener {
        void clicked(int group, int pos, View v);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onSubItemClickListener != null) {
                onSubItemClickListener.clicked(groupMap.get(view.getId()), posMap.get(view.getId()), view);
            }
        }
    };
}
