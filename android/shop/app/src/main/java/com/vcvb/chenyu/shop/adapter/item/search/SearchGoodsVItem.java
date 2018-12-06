package com.vcvb.chenyu.shop.adapter.item.search;

import android.content.Context;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
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
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class SearchGoodsVItem extends BaseItem<Goods> {
    public static final int TYPE = R.layout.search_info_v_item;

    public SearchGoodsVItem(Goods bean, Context c) {
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
        TextView tv = holder.getTextView(R.id.textView164);
        tv.setText(mData.getGoods_name());
        TextView tv1 = holder.getTextView(R.id.textView166);
        tv1.setText(mData.getShop_price_format());
        TextView tv2 = holder.getTextView(R.id.textView167);
        tv2.setText(mData.getMarket_price_format());
        tv2.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        FlowLayout fl = (FlowLayout) holder.getView(R.id.tip_wrap);
        fl.setChildSpacing(8);
        fl.setRowSpacing(8);
        fl.setChildSpacingForLastRow(8);
        fl.removeAllViews();
        if (mData.getIs_promote().equals("1") && mData.getPromote_start_date() < mData.getCurrent_time()
                && mData.getPromote_end_date() > mData.getCurrent_time()) {
            tv1.setText(mData.getPromote_price_format());
            TextView textView = new TextView(context);
            textView.setText("促销");
            textView.setTextColor(context.getResources().getColor(R.color.red));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLines(1);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 4), ToolUtils.dip2px(context, 1),
                    ToolUtils.dip2px(context, 4), ToolUtils.dip2px(context, 1));
            textView.setBackgroundResource(R.drawable.red_all_border);
            fl.addView(textView);
        }
        if(!mData.getShop_price().equals("")){
            TextView textView = new TextView(context);
            textView.setText("折扣");
            textView.setTextColor(context.getResources().getColor(R.color.red));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLines(1);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 4), ToolUtils.dip2px(context, 1),
                    ToolUtils.dip2px(context, 4), ToolUtils.dip2px(context, 1));
            textView.setBackgroundResource(R.drawable.red_all_border);
            fl.addView(textView);
        }

        ImageView iv = holder.get(R.id.imageView78);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(8, 0,
                RoundedCornersTransformation.CornerType.TOP);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners)
                .placeholder(R.drawable.icon_goods_no_pic_v).diskCacheStrategy(DiskCacheStrategy
                        .AUTOMATIC).skipMemoryCache(true);
        Glide.with(context).load(mData.getOriginal_img()).apply(requestOptions).into(iv);
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        set.constrainWidth(iv.getId(), (width - ToolUtils.dip2px(context, 11)) / 2);
        set.constrainHeight(iv.getId(), (width - ToolUtils.dip2px(context, 10)) / 2);
        set.applyTo(cly);
    }
}
