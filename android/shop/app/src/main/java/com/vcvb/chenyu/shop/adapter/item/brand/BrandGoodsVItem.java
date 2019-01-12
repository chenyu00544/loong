package com.vcvb.chenyu.shop.adapter.item.brand;

import android.content.Context;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class BrandGoodsVItem extends BaseItem<Goods> {
    public static final int TYPE = R.layout.goods_v_item;

    public BrandGoodsVItem(Goods bean, Context c) {
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
        int width = ToolUtils.getWindowsWidth(context);
        ImageView iv = holder.get(R.id.imageView78);
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        set.constrainWidth(iv.getId(), (width - ToolUtils.dip2px(context, 12)) / 2);
        set.constrainHeight(iv.getId(), (width - ToolUtils.dip2px(context, 12)) / 2);
        set.applyTo(cly);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(8, 0,
                RoundedCornersTransformation.CornerType.TOP);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners)
                .placeholder(R.drawable.icon_goods_no_pic_v).diskCacheStrategy(DiskCacheStrategy
                        .AUTOMATIC).skipMemoryCache(true);
        Glide.with(context).load(mData.getOriginal_img()).apply(requestOptions).into(iv);
        TextView tv1 = holder.get(R.id.textView164);
        tv1.setText(mData.getGoods_name());
        TextView tv2 = holder.get(R.id.textView166);
        tv2.setText(mData.getShop_price_format());
        TextView tv3 = holder.get(R.id.textView167);
        tv3.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        tv3.setText(mData.getMarket_price_format());
    }
}
