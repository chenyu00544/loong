package com.vcvb.chenyu.shop.adapter.item.goods;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class GoodsPriceItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = R.layout.goods_price_info_item;

    public GoodsPriceItem(GoodsDetail beans, Context c) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView goodsPrice = holder.getTextView(R.id.textView1);
        TextView goodsMarket = holder.getTextView(R.id.textView2);
        goodsMarket.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        if (mData.getIs_promote() == 1) {
            goodsPrice.setText(mData.getPromote_price_format());
        } else {
            goodsPrice.setText(mData.getShop_price_format());
        }
        goodsMarket.setText(mData.getMarket_price_format());
        FlowLayout flowLayout = (FlowLayout) holder.getView(R.id.tip_wrap);
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.setGravity(Gravity.CENTER_VERTICAL);
        flowLayout.removeAllViews();
        if (mData.getIs_promote() == 1) {
            TextView textView = new TextView(context);
            textView.setText("促销");
            textView.setTextColor(context.getResources().getColor(R.color.red));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLines(1);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 2),
                    ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 2));
            textView.setBackgroundResource(R.drawable.shape_tip);
            flowLayout.addView(textView);
        }

        if (mData.getIs_fullcut() == 1) {
            if (mData.getGoodsFCS() != null) {
                for (int i = 0; i < mData.getGoodsFCS().size(); i++) {
                    TextView textView = new TextView(context);
                    textView.setText("满" + mData.getGoodsFCS().get(i).getCfull_format() + "减" + mData
                            .getGoodsFCS().get(i).getCreduce_format());
                    textView.setTextColor(context.getResources().getColor(R.color.red));
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLines(1);
                    textView.setPadding(ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 2),
                            ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 2));
                    textView.setBackgroundResource(R.drawable.shape_tip);
                    flowLayout.addView(textView);
                }
            }
        }

        if (mData.getIs_volume() == 1) {
            if(mData.getGoodsGVPS() != null){
                for (int i = 0; i < mData.getGoodsGVPS().size(); i++) {
                    TextView textView = new TextView(context);
                    textView.setText(mData.getGoodsGVPS().get(i).getVolume_number() + "件" + mData
                            .getGoodsGVPS().get(i).getVolume_price_format());
                    textView.setTextColor(context.getResources().getColor(R.color.red));
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLines(1);
                    textView.setPadding(ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 2),
                            ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 2));
                    textView.setBackgroundResource(R.drawable.shape_tip);
                    flowLayout.addView(textView);
                }
            }
        }
    }
}
