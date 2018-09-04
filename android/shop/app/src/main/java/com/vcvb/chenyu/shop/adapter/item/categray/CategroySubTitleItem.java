package com.vcvb.chenyu.shop.adapter.item.categray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyBean;

public class CategroySubTitleItem extends BaseItem<CategroyBean> implements View.OnClickListener {
    public static final int TYPE = 3;

    public CategroySubTitleItem(CategroyBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .categroy_subtitle_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        holder.getItemView().setOnClickListener(this);
        TextView tv = holder.getTextView(R.id.textView100);
        tv.setText(mData.getCateName());
    }

    @Override
    public void onClick(View view) {
        System.out.println(11);
    }
}
