package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
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
import com.vcvb.chenyu.shop.javaBean.goods.GoodsComment;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GoodsEvaluateSubItem extends BaseItem<GoodsComment> {
    public static final int TYPE = R.layout.goods_evaluate_sub_item;

    public GoodsEvaluateSubItem(GoodsComment beans, Context c) {
        super(beans, c);
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
        RequestOptions requestOptions = RequestOptions.circleCropTransform().diskCacheStrategy
                (DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).error(R.drawable
                .icon_boy_head).override(120, 120);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(6, 0,
                RoundedCornersTransformation.CornerType.RIGHT);
        RequestOptions requestOptions2 = RequestOptions.bitmapTransform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).override
                        (ToolUtils.dip2px(context,120), ToolUtils.dip2px(context,120));
        ImageView iv = holder.getImageView(R.id.imageView);
        Glide.with(context).load(mData.getLogo()).apply(requestOptions)
                .into(iv);
        ImageView iv2 = holder.getImageView(R.id.imageView3);
        if(mData.getComment_imgs().size() > 0){
            Glide.with(context).load(mData.getComment_imgs().get(0).getComment_img()).apply
                    (requestOptions2).into(iv2);
        }else{
            Glide.with(context).load(R.drawable.icon_no_pic).apply
                    (requestOptions2).into(iv2);
        }

        TextView tv1 = holder.get(R.id.textView4);
        tv1.setText(mData.getUser_name());
        TextView tv2 = holder.get(R.id.textView5);
        tv2.setText(mData.getContent());
    }
}
