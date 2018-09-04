package com.vcvb.chenyu.shop.adapter.item.categray;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyBean;

public class CategroyItem extends BaseItem<CategroyBean> {
    public static final int TYPE = 1;

    public CategroyItem(CategroyBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout
                        .categroy_default_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView100);
        tv.setText(mData.getCateName());
        ImageView iv = holder.getImageView(R.id.imageView40);
        Picasso.with(context).load(mData.getPic())
                //.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.icon_no_pic).into(iv);
    }
}
