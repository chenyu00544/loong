package com.vcvb.chenyu.shop.adapter.item.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.faat.Bonus;

import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class OrderBonusItem extends BaseItem<Bonus> {
    public static final int TYPE = R.layout.dialog_order_bonus_item;

    public OrderBonusItem(Bonus bean, Context c) {
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
        View v = holder.getItemView();
        posMap.put(v.getId(), mData.getBu_id());
        v.setOnClickListener(listener);

        ImageView iv = holder.get(R.id.imageView130);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(12, 0,
                RoundedCornersTransformation.CornerType.ALL);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true);
        Glide.with(context).load(R.drawable.bonus_bg).apply(requestOptions).into(iv);

        TextView tv1 = holder.get(R.id.textView254);
        tv1.setText(String.format(Locale.CHINA, "%.0f", Double.valueOf(mData.getType_money())));

        TextView tv2 = holder.get(R.id.textView255);
        tv2.setText(String.format(Locale.CHINA, "%s", mData.getType_name()));

        TextView tv3 = holder.get(R.id.textView256);
        tv3.setText(String.format(Locale.CHINA, "使用日期:%s-%s", mData.getUse_start_date_format(),
                mData.getUse_end_date_format()));
    }
}
