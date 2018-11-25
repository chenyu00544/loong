package com.vcvb.chenyu.shop.adapter.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;
import com.vcvb.chenyu.shop.javaBean.order.OrderListBean;

import java.util.List;

public class OrderItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private List<OrderDetail> mList;

    public OrderItemDecoration(Context context, List<OrderDetail> list) {
        super();
        mContext = context;
        mList = list;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int position = parent.getChildAdapterPosition(view);
            OrderDetail bean = mList.get(position);
            if(bean != null){
                if (bean.getIsType() == 2) {
                    outRect.set(16, 16, 16, 0);
                }else if (bean.getIsType() == 1){
                    outRect.set(16, 0, 16, 0);
                }else{
                    outRect.set(16, 0, 16, 16);
                }
            }else {
                outRect.set(16, 0, 16, 0);
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            outRect.set(0, 0, 0, 0);
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
