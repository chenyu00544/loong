package com.vcvb.chenyu.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.viewholder.BaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderListBean;

import java.util.List;

public class OrderRecyclerViewAdapter extends BaseRecyclerAdapter<OrderListBean> {
    public OrderRecyclerViewAdapter(Context context, List<OrderListBean> list) {
        super(context, list);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindViewHolder(mList.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= 0 && position < mList.size()) {
            switch (mList.get(position).getIsType()) {
                case 1:
                    return ITEM_VIEW_TYPE_NORMAL;
                case 2:
                    return ITEM_VIEW_TYPE_TITLE;
                case 3:
                    return ITEM_VIEW_TYPE_BUTTOM;
                case 4:
                    return ITEM_VIEW_TYPE_BUTTOM1;
                case 5:
                    return ITEM_VIEW_TYPE_BUTTOM2;
            }
        }
        return ITEM_VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public BaseViewHolder getHolder(View itemView, int viewType, AdapterView.OnItemClickListener
            listener) {
        return new OrderViewHolder(itemView, viewType, listener);
    }

    @Override
    public int getLayoutId(int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_NORMAL:
                return R.layout.order_content_have_data_item;
            case ITEM_VIEW_TYPE_TITLE:
                return R.layout.order_content_have_data_title_item;
            case ITEM_VIEW_TYPE_BUTTOM:
                return R.layout.order_content_have_data_buttom_item;
            case ITEM_VIEW_TYPE_BUTTOM1:
                return R.layout.order_content_have_data_buttom2_item;
            case ITEM_VIEW_TYPE_BUTTOM2:
                return R.layout.order_content_have_data_buttom3_item;
            default:
                return R.layout.order_content_have_data_item;
        }
    }

    public class OrderViewHolder extends BaseViewHolder<OrderListBean> {

        public OrderViewHolder(View itemView, int viewType, AdapterView.OnItemClickListener
                listener) {
            super(itemView, viewType, listener);
        }

        @Override
        public void bindViewHolder(OrderListBean bean, int position) {
            System.out.println(bean);
            switch (OrderRecyclerViewAdapter.this.getItemViewType(position)){
                case ITEM_VIEW_TYPE_TITLE:
                    TextView storeName = itemView.findViewById(R.id.textView95);
                    storeName.setText(bean.getStoreName());
                    break;
                case ITEM_VIEW_TYPE_BUTTOM:
                    break;
                case ITEM_VIEW_TYPE_BUTTOM1:
                    break;
                case ITEM_VIEW_TYPE_BUTTOM2:
                    break;
                default:
                    TextView goodsName = itemView.findViewById(R.id.textView85);
                    goodsName.setText(bean.getGoodsName());
                    break;
            }
        }
    }
}
