package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class OrderAfterSaleTypeItem extends BaseItem<OrderDetail> {
    public static final int TYPE = R.layout.order_after_sale_type_item;

    public OrderAfterSaleTypeItem(OrderDetail bean, Context c) {
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
        FlowLayout flowLayout = holder.get(R.id.return_type);
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.removeAllViews();
        for (int i = 0; i < mData.getOrderGoodses().size(); i++) {
            TextView textView = new TextView(context);
            textView.setId(IdsUtils.generateViewId());
            textView.setBackgroundResource(R.drawable.shape_6_red);
            textView.setPadding(ToolUtils.dip2px(context, 10), ToolUtils.dip2px(context, 5),
                    ToolUtils.dip2px(context, 10), ToolUtils.dip2px(context, 5));
            textView.setText("23423f");
            flowLayout.addView(textView);
            posMap.put(textView.getId(), i);
            textView.setOnClickListener(listener);
        }
    }
}
