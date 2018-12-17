package com.vcvb.chenyu.shop.adapter.item.cart;

import android.content.Context;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class Goods_V_Item extends BaseItem<CartListBean> {
    public static final int TYPE = Integer.MAX_VALUE - 3;

    public Goods_V_Item(CartListBean beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_v_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        View v = holder.getItemView();
        posMap.put(v.getId(), position);
        v.setOnClickListener(listener);

        int width = ToolUtils.getWindowsWidth(context);
        TextView tv = holder.getTextView(R.id.textView164);
        tv.setText(mData.getGoods().getGoods_name());
        TextView shopPriceV = holder.getTextView(R.id.textView166);
        shopPriceV.setText(mData.getGoods().getShop_price_format());
        TextView marketPriceV = holder.getTextView(R.id.textView167);
        marketPriceV.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        marketPriceV.setText(mData.getGoods().getMarket_price_format());

        FlowLayout fl = holder.get(R.id.tip_wrap);
        fl.setChildSpacing(8);
        fl.setRowSpacing(8);
        fl.setChildSpacingForLastRow(8);
        fl.removeAllViews();
        if (Integer.valueOf(mData.getGoods().getIs_promote()) == 1) {
            Double marketPrice = Double.valueOf(mData.getGoods().getMarket_price()).doubleValue();
            Double promotePrice = Double.valueOf(mData.getGoods().getPromote_price()).doubleValue();
            BigDecimal bd = new BigDecimal(promotePrice / marketPrice * 10).setScale(1,
                    RoundingMode.UP);
            TextView textView = new TextView(context);
            textView.setText(bd.doubleValue() + "折");
            textView.setTextColor(context.getResources().getColor(R.color.red));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLines(1);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 4), ToolUtils.dip2px(context, 1),
                    ToolUtils.dip2px(context, 4), ToolUtils.dip2px(context, 1));
            textView.setBackgroundResource(R.drawable.red_all_border);
            fl.addView(textView);
            shopPriceV.setText(mData.getGoods().getPromote_price_format());
        }

        ImageView iv = holder.get(R.id.imageView78);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(8, 0,
                RoundedCornersTransformation.CornerType.TOP);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners)
                .placeholder(R.drawable.icon_goods_no_pic_v).diskCacheStrategy(DiskCacheStrategy
                        .AUTOMATIC);
        Glide.with(context).load(mData.getGoods().getOriginal_img()).apply(requestOptions).into(iv);
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        set.constrainWidth(iv.getId(), (width - ToolUtils.dip2px(context, 10)) / 2);
        set.constrainHeight(iv.getId(), (width - ToolUtils.dip2px(context, 10)) / 2);
        set.applyTo(cly);
    }
}
