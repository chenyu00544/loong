package com.vcvb.chenyu.shop.adapter.item.cart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class CartItem extends BaseItem<CartListBean> {
    public static final int TYPE = 1;

    public CartItem(CartListBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout
                        .cart_content_have_data_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView goodsName = holder.getTextView(R.id.textView85);
        TextView goodsNum = holder.getTextView(R.id.textView90);
        TextView goodsPrice = holder.getTextView(R.id.textView87);
        TextView goodsMarket = holder.getTextView(R.id.textView88);
        ImageView iv = holder.getImageView(R.id.imageView41);
        CheckBox cb = (CheckBox) holder.getView(R.id.checkBox3);
        TextView findSimilarity = holder.getTextView(R.id.textView109);
        TextView iWantCollection = holder.getTextView(R.id.textView110);
        TextView delete = holder.getTextView(R.id.textView111);
        View view = holder.getView(R.id.view30);

        goodsName.setText(mData.getGoodsName());
        goodsNum.setText(String.valueOf(mData.getGoodsNum()));
        goodsPrice.setText(String.format("￥%.2f", mData.getGoodsPrice()));
        goodsMarket.setText(String.format("￥%.2f", mData.getGoodsMarket()));
        goodsMarket.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        Picasso.with(context).load(mData.getGoodsPic())
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.icon_no_pic).into(iv);

        cb.setChecked(mData.getIsChecOnce());

        ConstraintSet set = new ConstraintSet();
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        set.clone(cly);
        if (mData.getIsLong() == false) {
            set.connect(view.getId(), ConstraintSet.TOP, cly.getId(), ConstraintSet.BOTTOM);
            set.constrainWidth(findSimilarity.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(findSimilarity.getId(), ToolUtils.dip2px(context, 0));
            set.constrainWidth(iWantCollection.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(iWantCollection.getId(), ToolUtils.dip2px(context, 0));
            set.constrainWidth(delete.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(delete.getId(), ToolUtils.dip2px(context, 0));
        } else {
            set.connect(view.getId(), ConstraintSet.TOP, cly.getId(), ConstraintSet.TOP);
            set.constrainWidth(findSimilarity.getId(), ToolUtils.dip2px(context, 60));
            set.constrainHeight(findSimilarity.getId(), ToolUtils.dip2px(context, 60));
            set.constrainWidth(iWantCollection.getId(), ToolUtils.dip2px(context, 60));
            set.constrainHeight(iWantCollection.getId(), ToolUtils.dip2px(context, 60));
            set.constrainWidth(delete.getId(), ToolUtils.dip2px(context, 60));
            set.constrainHeight(delete.getId(), ToolUtils.dip2px(context, 60));
        }
        set.applyTo(cly);
    }
}
