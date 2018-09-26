package com.vcvb.chenyu.shop.adapter.base;

import android.content.Context;
import android.view.View;

import java.util.HashMap;

public abstract class BaseItem<T> implements Item {
    public T mData;
    public Context context;
    public OnClickListener onClickListener;
    public HashMap<Integer, Integer> posMap = new HashMap<>();

    public BaseItem(T t, Context c) {
        mData = t;
        context = c;
    }

    @Override
    public void releaseResource() {
        // 如果有需要回收的资源，子类自己实现
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public interface OnClickListener {
        void onClicked(View view, int pos);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, posMap.get(view.getId()));
            }
        }
    };
}
