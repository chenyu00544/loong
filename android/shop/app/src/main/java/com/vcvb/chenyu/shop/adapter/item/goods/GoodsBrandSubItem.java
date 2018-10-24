package com.vcvb.chenyu.shop.adapter.item.goods;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.math.BigDecimal;

public class GoodsBrandSubItem extends BaseItem<Goods> {
    public static final int TYPE = 1;

    public GoodsBrandSubItem(Goods beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_brand_sub_item, null));
        return base;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        ImageView imageView3 = holder.get(R.id.imageView3);
        RequestOptions requestOptions = RequestOptions.centerCropTransform().diskCacheStrategy
                (DiskCacheStrategy.AUTOMATIC).error(R.drawable.icon_no_pic);
        Glide.with(context).load(mData.getOriginal_img()).apply(requestOptions).into(imageView3);

        TextView textView5 = holder.get(R.id.textView5);
        textView5.setText(mData.getGoods_name());

        FlowLayout flowLayout = holder.get(R.id.faat_label);
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.removeAllViews();

        TextView textView12 = holder.get(R.id.textView12);
        if (mData.getIs_promote().equals("1") && (mData.getPromote_end_date() - mData
                .getCurrent_time()) > 0) {
            Double discount = (Double.valueOf(mData.getPromote_price()) / Double.valueOf
                                (mData.getShop_price()) * 10);
            BigDecimal bg3 = new BigDecimal(discount);
            discount = bg3.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            TextView tv1 = new TextView(context);
            tv1.setId(IdsUtils.generateViewId());
            tv1.setPadding(ToolUtils.dip2px(context, 2), 0, ToolUtils.dip2px(context, 2), 0);
            tv1.setBackgroundResource(R.drawable.shape_tip);
            tv1.setTextColor(context.getResources().getColor(R.color.black_29));
            tv1.setTextSize(10);
            tv1.setText(discount + "折");
            flowLayout.addView(tv1);
            textView12.setText(mData.getPromote_price_format());
        } else {
            textView12.setText(mData.getShop_price_format());
        }
//        TextView tv2 = new TextView(context);
    }
}
