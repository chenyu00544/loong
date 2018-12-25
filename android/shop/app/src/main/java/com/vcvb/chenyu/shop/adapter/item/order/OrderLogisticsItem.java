package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.Logistics;

public class OrderLogisticsItem extends BaseItem<Logistics> {
    public static final int TYPE = R.layout.order_logistics_item;

    public OrderLogisticsItem(Logistics bean, Context c) {
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

        TextView time = holder.get(R.id.textView275);
        TextView title = holder.get(R.id.textView277);
        TextView content = holder.get(R.id.textView276);

        View upV = holder.get(R.id.view91);
        ImageView ivC = holder.get(R.id.imageView135);

        if (mData.isIs_last()) {
            //阴影部分
            upV.setAlpha(0);
            ivC.setImageResource(R.drawable.icon_spot);
            time.setTextColor(context.getResources().getColor(R.color.colorFont_morandi));
            title.setTextColor(context.getResources().getColor(R.color.colorFont_morandi));
            content.setTextColor(context.getResources().getColor(R.color.colorFont_morandi));
        }else{
            upV.setAlpha(1);
            ivC.setImageResource(R.drawable.black_background);
            time.setTextColor(context.getResources().getColor(R.color.gray));
            title.setTextColor(context.getResources().getColor(R.color.gray));
            content.setTextColor(context.getResources().getColor(R.color.gray));
        }
        time.setText(mData.getTime());
        title.setText(mData.getLocation());
        content.setText(mData.getContext());

    }
}
