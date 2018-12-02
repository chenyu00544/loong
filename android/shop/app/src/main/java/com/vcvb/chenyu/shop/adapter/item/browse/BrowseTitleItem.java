package com.vcvb.chenyu.shop.adapter.item.browse;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.TextView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.browse.GroupBrowse;

public class BrowseTitleItem extends BaseItem<GroupBrowse> {
    public static final int TYPE = R.layout.browse_title_item;

    public BrowseTitleItem(GroupBrowse bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        TextView title = holder.get(R.id.textView132);
        title.setText((String) mData.getHeader());
        CheckBox cb = holder.get(R.id.checkBox7);
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout cly = holder.get(R.id.group_header);
        set.clone(cly);
//        if (!mData.isLong()) {
//            set.constrainWidth(cb.getId(), ToolUtils.dip2px(context, 0));
//            set.constrainHeight(cb.getId(), ToolUtils.dip2px(context, 0));
//        } else {
//            set.constrainWidth(cb.getId(), ToolUtils.dip2px(context, 33));
//            set.constrainHeight(cb.getId(), ToolUtils.dip2px(context, 33));
//        }
//        set.applyTo(cly);
//
//        cb.setChecked(mData.isSelectAll());
//        posMap.put(cb.getId(), position);
//        cb.setOnClickListener(listener);
    }
}
