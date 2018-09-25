package com.vcvb.chenyu.shop.adapter.b;

import android.content.Context;

public abstract class BaseItem<T> implements Item {
    public T mData;
    public Context context;

    public BaseItem(T t, Context c) {
        mData = t;
        context = c;
    }

    @Override
    public void releaseResource() {
        // 如果有需要回收的资源，子类自己实现
    }
}
