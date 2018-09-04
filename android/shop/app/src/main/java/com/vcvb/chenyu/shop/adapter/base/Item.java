package com.vcvb.chenyu.shop.adapter.base;

import android.view.ViewGroup;

public interface Item {
    /**
     * 回收资源
     *
     */
    public void releaseResource();

    /**
     * 获取viewType
     * @return
     */
    public  int getItemType();

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    public  void onBindViewHolder(CYCBaseViewHolder holder, int position);
}
