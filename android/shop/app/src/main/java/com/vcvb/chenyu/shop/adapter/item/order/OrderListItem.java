package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class OrderListItem extends BaseItem<OrderDetail> {
    public static final int TYPE = R.layout.order_content_have_data_list_item;

    public OrderListItem(OrderDetail bean, Context c) {
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
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        View wrap = holder.get(R.id.order_wrap);

        View header = holder.get(R.id.order_title);

        int view_id = header.getId();
        for (int i = 0; i < mData.getOrderGoodses().size(); i++) {
            View og = LayoutInflater.from(context).inflate(R.layout
                    .order_content_have_data_item, null);
            set.constrainHeight(view_id, ToolUtils.dip2px(context, 100));
            set.connect(og.getId(), ConstraintSet.BOTTOM, view_id, ConstraintSet
                    .TOP);
            set.connect(og.getId(), ConstraintSet.LEFT, header.getId(), ConstraintSet
                    .LEFT, 0);
            set.connect(og.getId(), ConstraintSet.RIGHT, header.getId(), ConstraintSet
                    .RIGHT, 0);
            view_id = IdsUtils.generateViewId();
            og.setId(view_id);
            cly.addView(og);
        }

        View foot = LayoutInflater.from(context).inflate(R.layout
                .order_content_have_data_buttom_item, null);
        cly.addView(foot);
        set.constrainHeight(foot.getId(), ToolUtils.dip2px(context, 45));
        set.connect(foot.getId(), ConstraintSet.TOP, view_id, ConstraintSet
                .BOTTOM, 0);
        set.connect(foot.getId(), ConstraintSet.LEFT, wrap.getId(), ConstraintSet
                .LEFT, 0);
        set.connect(foot.getId(), ConstraintSet.RIGHT, wrap.getId(), ConstraintSet
                .RIGHT, 0);
        set.connect(foot.getId(), ConstraintSet.BOTTOM, wrap.getId(), ConstraintSet
                .BOTTOM, 0);
        set.applyTo(cly);
    }
}
