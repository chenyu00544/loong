package com.vcvb.chenyu.shop.adapter.item.browse;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.browse.Browse;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class BrowseTitleItem extends BaseItem<Browse> {
    public static final int TYPE = R.layout.browse_title_item;

    public BrowseTitleItem(Browse bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(TYPE, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView title = holder.getTextView(R.id.textView132);
        title.setText(mData.getAdd_time_format());
        CheckBox cb = (CheckBox) holder.getView(R.id.checkBox7);
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        set.clone(cly);
        if (mData.isLong()) {
            set.constrainWidth(cb.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(cb.getId(), ToolUtils.dip2px(context, 0));
        } else {
            set.constrainWidth(cb.getId(), ToolUtils.dip2px(context, 33));
            set.constrainHeight(cb.getId(), ToolUtils.dip2px(context, 33));
        }
        set.applyTo(cly);

        if(mData.isSelectAll()){
            cb.setChecked(true);
        }else{
            cb.setChecked(false);
        }
    }
}
