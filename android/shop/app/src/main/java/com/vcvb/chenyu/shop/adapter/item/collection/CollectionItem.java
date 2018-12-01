package com.vcvb.chenyu.shop.adapter.item.collection;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.collection.CollectionBean;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class CollectionItem extends BaseItem<CollectionBean> {
    public static final int TYPE = R.layout.collection_item;

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
                .inflate(TYPE, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView delete = holder.get(R.id.textView119);
        View view = holder.getView(R.id.view32);

        TextView tv = holder.get(R.id.textView114);
        tv.setText(mData.getGoods().getGoods_name());
        ImageView iv = holder.get(R.id.imageView47);
        Glide.with(context).load(mData.getGoods().getOriginal_img()).into(iv);

        TextView tv1 = holder.get(R.id.textView115);
        if (mData.getGoods().getIs_promote().equals("1") && mData.getGoods().getPromote_end_date
                () > mData.getGoods().getCurrent_time() && mData.getGoods().getPromote_start_date()
                < mData.getGoods().getCurrent_time()) {
            tv1.setText("促销");
        } else {
            tv1.setText("手机用户专享");
        }

        ConstraintSet set = new ConstraintSet();
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        set.clone(cly);
        if (mData.isLong()) {
            set.connect(view.getId(), ConstraintSet.TOP, cly.getId(), ConstraintSet.TOP);
            set.constrainWidth(delete.getId(), ToolUtils.dip2px(context, 60));
            set.constrainHeight(delete.getId(), ToolUtils.dip2px(context, 60));
        } else {
            set.connect(view.getId(), ConstraintSet.TOP, cly.getId(), ConstraintSet.BOTTOM);
            set.constrainWidth(delete.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(delete.getId(), ToolUtils.dip2px(context, 0));
        }
        set.applyTo(cly);

        ImageView iv1 = holder.get(R.id.imageView48);
        posMap.put(iv1.getId(), position);
        iv1.setOnClickListener(listener);

        TextView tv2 = holder.get(R.id.textView113);
        posMap.put(tv2.getId(), position);
        tv2.setOnClickListener(listener);

        posMap.put(iv.getId(), position);
        iv.setOnClickListener(listener);

        posMap.put(view.getId(), position);
        view.setOnClickListener(listener);

        posMap.put(delete.getId(), position);
        delete.setOnClickListener(listener);
    }
}
