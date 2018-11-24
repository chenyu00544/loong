package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.Pay;

import java.util.List;

public class OrderPayTypeItem extends BaseItem<List<Pay>> {
    public static final int TYPE = R.layout.order_details_paytype_item;

    public OrderPayTypeItem(List<Pay> bean, Context c) {
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
        TextView tv = holder.get(R.id.textView196);
        ImageView iv = holder.get(R.id.imageView127);
        View v = holder.getItemView();

        RequestOptions requestOptions = RequestOptions.circleCropTransform().diskCacheStrategy
                (DiskCacheStrategy.AUTOMATIC).override(120, 120);
        for (int i = 0; i < mData.size(); i++) {
            if(mData.get(i).getDef() == 1){
                tv.setText(mData.get(i).getPay_name());
                if (mData.get(i).getPay_code().equals("alipay")) {
                    Glide.with(context).load(R.drawable.alipay).apply(requestOptions).into(iv);
                } else if (mData.get(i).getPay_code().equals("wxpay")) {
                    Glide.with(context).load(R.drawable.wechat).apply(requestOptions).into(iv);
                } else {
                    tv.setText(R.string.alipay);
                    Glide.with(context).load(R.drawable.alipay).apply(requestOptions).into(iv);
                }
            }
        }
        posMap.put(v.getId(), 1);
        v.setOnClickListener(listener);
    }
}
