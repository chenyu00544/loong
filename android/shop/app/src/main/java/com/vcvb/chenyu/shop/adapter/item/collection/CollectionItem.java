package com.vcvb.chenyu.shop.adapter.item.collection;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.collection.CollectionBean;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class CollectionItem extends BaseItem<CollectionBean> {
    public static final int TYPE = 1;

    public CollectionItem(CollectionBean bean, Context c) {
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
                        .collection_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView114);
        TextView delete = holder.getTextView(R.id.textView119);
        View view = holder.getView(R.id.view32);

        tv.setText(mData.getGoodsName());
//        ImageView iv = holder.getImageView(R.id.imageView47);
//        Picasso.with(context).load(mData.getPic())
                //.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//                .config(Bitmap.Config.RGB_565)
//                .placeholder(R.drawable.icon_no_pic).into(iv);

        ConstraintSet set = new ConstraintSet();
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        set.clone(cly);
        if (mData.getIsLong() == false) {
            set.connect(view.getId(), ConstraintSet.TOP, cly.getId(), ConstraintSet.BOTTOM);
            set.constrainWidth(delete.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(delete.getId(), ToolUtils.dip2px(context, 0));
        } else {
            set.connect(view.getId(), ConstraintSet.TOP, cly.getId(), ConstraintSet.TOP);
            set.constrainWidth(delete.getId(), ToolUtils.dip2px(context, 60));
            set.constrainHeight(delete.getId(), ToolUtils.dip2px(context, 60));
        }
        set.applyTo(cly);
    }
}
