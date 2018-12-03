package com.vcvb.chenyu.shop.adapter.b;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;

public interface Item {
    /**
     * 回收资源
     */
    public void releaseResource();

    /**
     * 获取viewType
     *
     * @return
     */
    public int getItemType();

    /**
     * 创建ViewHolder
     *
     * @param viewType
     * @return
     */
    public BaseViewHolder onCreateViewHolder(int viewType);

    /**
     * 数据绑定
     *
     * @param holder
     * @param position
     */
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position);
}
