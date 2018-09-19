package com.vcvb.chenyu.shop.adapter.item.pay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;

public class PaySuccessItem extends BaseItem<OrderDetail> {
    public static final int TYPE = 1;

    public OnClickListener onClickListener;

    public PaySuccessItem(OrderDetail beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pay_success_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView215);
        tv.setOnClickListener(listener);
        tv.setTag("ORDER");
        TextView tv2 = holder.getTextView(R.id.textView216);
        tv2.setOnClickListener(listener);
        tv2.setTag("HOME");
        TextView t1 = holder.getTextView(R.id.textView210);
        t1.setText(mData.getOrderConsignee());
        TextView t2 = holder.getTextView(R.id.textView211);
        t2.setText(mData.getOrderPhone());
        TextView t3 = holder.getTextView(R.id.textView212);
        t3.setText(mData.getOrderAddress());
        TextView t4 = holder.getTextView(R.id.textView214);
        t4.setText(mData.getTotalPayAbleFormat());
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public interface OnClickListener{
        void onClicked(View view, String str);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, (String) view.getTag());
            }
        }
    };
}
