package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.Evaluates;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GoodsEvaluateSubItem extends BaseItem<Evaluates> {
    public static final int TYPE = 1;

    public GoodsEvaluateSubItem(Evaluates beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_evaluate_sub_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform().diskCacheStrategy
                (DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).override(120, 120);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(6, 0,
                RoundedCornersTransformation.CornerType.RIGHT);
        RequestOptions requestOptions2 = RequestOptions.bitmapTransform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).override
                        (ToolUtils.dip2px(context,120), ToolUtils.dip2px(context,120));
        ImageView iv = holder.getImageView(R.id.imageView);
        Glide.with(context).load(mData.getLogo()).apply(requestOptions)
                .into(iv);
        ImageView iv2 = holder.getImageView(R.id.imageView3);
        Glide.with(context).load(mData.getEva_img_url()).apply
                (requestOptions2).into(iv2);
    }
}
