package com.vcvb.chenyu.shop.adapter.item.categray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cate.Categroy;

public class CategroyTitleItem extends BaseItem<Categroy> {
    public static final int TYPE = R.layout.categroy_title_item;

    public CategroyTitleItem(Categroy bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView100);
        tv.setText(mData.getCat_alias_name());
        if (mData.isIs_current()) {
            holder.getView(R.id.view29).setAlpha(1);
        } else {
            holder.getView(R.id.view29).setAlpha(0);
        }
    }
}
