package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
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
import com.vcvb.chenyu.shop.javaBean.faat.TeamGoods;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class FaatTeamGoodsItem extends BaseItem<TeamGoods> {
    public static final int TYPE = Integer.MAX_VALUE - 3;

    public FaatTeamGoodsItem(TeamGoods bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .faat_team_goods_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context) - ToolUtils.dip2px(context, 12);
        ImageView iv = holder.get(R.id.imageView124);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(8, 0,
                RoundedCornersTransformation.CornerType.TOP);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners)
                .placeholder(R.drawable.icon_goods_no_pic_v).diskCacheStrategy(DiskCacheStrategy
                        .AUTOMATIC);
        Glide.with(context).load(mData.getOriginal_img()).apply(requestOptions).into(iv);
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        set.constrainWidth(iv.getId(), width / 2);
        set.constrainHeight(iv.getId(), width / 2);
        set.applyTo(cly);

        TextView tv1 = holder.get(R.id.textView239);
        tv1.setText(mData.getGoods_name());
        TextView tv2 = holder.get(R.id.textView240);
        tv2.setText(mData.getTeam_price_format());
    }
}
