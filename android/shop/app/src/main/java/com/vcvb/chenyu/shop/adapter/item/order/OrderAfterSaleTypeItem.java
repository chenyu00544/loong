package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderAfterSaleTypeItem extends BaseItem<OrderDetail> {
    public static final int TYPE = R.layout.order_after_sale_type_item;
    private FlowLayout flowLayout;

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
        flowLayout = holder.get(R.id.return_type);
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.removeAllViews();
        List<Integer> return_type = new ArrayList<>();
        for (int i = 0; i < mData.getOrderGoodses().size(); i++) {
//            if (mData.getOrderGoodses().get(i).isIs_select()) {
//                List<Integer> return_type2 = new ArrayList<>();
//                String[] strings = mData.getOrderGoodses().get(i).getGoods_cause().split(",");
//                for (int j = 0; j < strings.length; j++) {
//                    return_type2.add(Integer.valueOf(strings[j]));
//                }
//                return_type = ToolUtils.getIntersection(return_type2, return_type);
//            }
        }
        for (int i = 0; i < return_type.size(); i++) {
            TextView textView = new TextView(context);
            textView.setId(IdsUtils.generateViewId());
            textView.setBackgroundResource(R.drawable.shape_4_grad_b_white);
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setPadding(ToolUtils.dip2px(context, 10), ToolUtils.dip2px(context, 5),
                    ToolUtils.dip2px(context, 10), ToolUtils.dip2px(context, 5));
            if (return_type.get(i) == 0) {
                textView.setText(R.string.return_repair);
            } else if (return_type.get(i) == 1) {
                textView.setText(R.string.return_goods);
            } else if (return_type.get(i) == 2) {
                textView.setText(R.string.change_goods);
            } else if (return_type.get(i) == 3) {
                textView.setText(R.string.refund_only);
            }

            if(return_type.get(i).equals(mData.getGoods_cause())){
                textView.setBackgroundResource(R.drawable.shape_6_red);
                textView.setTextColor(context.getResources().getColor(R.color.white));
            }

            flowLayout.addView(textView);
            posMap.put(textView.getId(), return_type.get(i));
            textView.setOnClickListener(listener);
        }
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (Integer key : posMap.keySet()) {
                TextView tv = flowLayout.findViewById(key);
                if (view.getId() == key) {
                    tv.setTextColor(context.getResources().getColor(R.color.white));
                    tv.setBackgroundResource(R.drawable.shape_6_red);
                } else {
                    tv.setTextColor(context.getResources().getColor(R.color.black));
                    tv.setBackgroundResource(R.drawable.shape_4_grad_b_white);
                }
            }

            if (onClickListener != null) {
                onClickListener.onClicked(view, posMap.get(view.getId()));
            }
        }
    };
}
