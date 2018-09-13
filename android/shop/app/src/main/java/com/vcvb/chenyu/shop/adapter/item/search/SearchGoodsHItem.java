package com.vcvb.chenyu.shop.adapter.item.search;

import android.content.Context;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.home.Goods;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class SearchGoodsHItem extends BaseItem<Goods> {
    public static final int TYPE = 2;

    public SearchGoodsHItem(Goods bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_info_h_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView169);
        tv.setText(mData.getGoodsName());
        TextView tv1 = holder.getTextView(R.id.textView173);
        tv1.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        FlowLayout fl = (FlowLayout) holder.getView(R.id.tip_wrap);
        fl.setChildSpacing(8);
        fl.setRowSpacing(8);
        fl.setChildSpacingForLastRow(8);
        fl.removeAllViews();
        for (int i = 0; i < 2; i++) {
            TextView textView = new TextView(context);
            textView.setText("券");
            textView.setTextColor(context.getResources().getColor(R.color.red));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLines(1);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 4), ToolUtils.dip2px(context,
                    1), ToolUtils.dip2px(context, 4), ToolUtils.dip2px(context, 1));
            textView.setBackgroundResource(R.drawable.red_all_border);
            fl.addView(textView);
        }

        ImageView iv = holder.getImageView(R.id.imageView78);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(6, 0,
                RoundedCornersTransformation.CornerType.TOP);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).override
                        (120, 120);
        Glide.with(context).load(R.drawable.icon_no_pic).apply(requestOptions).into(iv);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        set.constrainWidth(iv.getId(), width / 2);
        set.constrainHeight(iv.getId(), width / 2);
        set.applyTo(cly);
    }
}
