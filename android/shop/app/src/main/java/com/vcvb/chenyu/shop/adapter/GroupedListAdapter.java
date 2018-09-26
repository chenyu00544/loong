package com.vcvb.chenyu.shop.adapter;

import android.content.Context;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.BaseBean;

import java.util.List;

public class GroupedListAdapter<T extends BaseBean> extends GroupedRecyclerViewAdapter {

    private List<T> mGroups;

    public GroupedListAdapter(Context context, List<T> groups) {
        super(context);
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups == null ? 0 : mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition) == null ? 0 : mGroups.get(groupPosition).getObjs().size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return false;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return viewType;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return viewType;
    }

    @Override
    public int getChildLayout(int viewType) {
        return viewType;
    }

    @Override
    public int getHeaderViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getFooterViewType(int groupPosition) {
        return super.getFooterViewType(groupPosition);
    }

    @Override
    public int getChildViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

    }
}
