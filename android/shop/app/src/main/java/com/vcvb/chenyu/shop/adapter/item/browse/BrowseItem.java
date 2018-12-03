package com.vcvb.chenyu.shop.adapter.item.browse;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.browse.Browse;
import com.vcvb.chenyu.shop.javaBean.browse.GroupBrowse;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class BrowseItem extends BaseItem<GroupBrowse> {
    public static final int TYPE = R.layout.browse_item;

    public BrowseItem(GroupBrowse bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        Browse bean = (Browse) mData.getObjs().get(position);
        TextView goodsName = holder.get(R.id.textView134);
        TextView tip = holder.get(R.id.textView136);
        TextView goodsPrice = holder.get(R.id.textView135);
        CheckBox cb = holder.get(R.id.checkBox6);
        ImageView iv = holder.get(R.id.imageView58);

        tip.setAlpha(0);
        goodsName.setText(bean.getGoods().getGoods_name());
        goodsPrice.setText(bean.getGoods().getShop_price_format());
        RequestOptions requestOptions = RequestOptions.centerCropTransform().placeholder(R
                .drawable.icon_no_pic).dontAnimate();
        Glide.with(context).load(bean.getGoods().getOriginal_img()).apply(requestOptions).into(iv);

        ConstraintSet set = new ConstraintSet();
        ConstraintLayout cly = holder.get(R.id.goods_item);
        set.clone(cly);
        if (!bean.isLong()) {
            set.constrainWidth(cb.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(cb.getId(), ToolUtils.dip2px(context, 0));
        } else {
            set.constrainWidth(cb.getId(), ToolUtils.dip2px(context, 30));
            set.constrainHeight(cb.getId(), ToolUtils.dip2px(context, 30));
        }
        set.applyTo(cly);

        cb.setChecked(bean.isSelect());
        groupMap.put(cb.getId(), groupPosition);
        posMap.put(cb.getId(), position);
        cb.setOnClickListener(listener);

        groupMap.put(iv.getId(), groupPosition);
        posMap.put(iv.getId(), position);
        iv.setOnClickListener(listener);

        TextView tv = holder.get(R.id.textView133);
        groupMap.put(tv.getId(), groupPosition);
        posMap.put(tv.getId(), position);
        tv.setOnClickListener(listener);
    }
}
