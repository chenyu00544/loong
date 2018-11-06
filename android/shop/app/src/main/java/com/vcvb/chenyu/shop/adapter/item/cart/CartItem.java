package com.vcvb.chenyu.shop.adapter.item.cart;

import android.content.Context;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import java.util.Locale;

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
                .inflate(R.layout.cart_content_have_data_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView goodsName = holder.get(R.id.textView85);
        TextView goodsAttr = holder.get(R.id.textView86);
        TextView goodsNum = holder.get(R.id.textView90);
        TextView goodsPrice = holder.get(R.id.textView87);
        TextView goodsMarket = holder.get(R.id.textView88);
        TextView promote = holder.get(R.id.textView89);
        TextView discount = holder.get(R.id.textView84);
        ImageView iv = holder.get(R.id.imageView41);
        CheckBox cb = holder.get(R.id.checkBox3);
        TextView findSimilarity = holder.get(R.id.textView109);
        TextView iWantCollection = holder.get(R.id.textView110);
        TextView delete = holder.get(R.id.textView111);
        View view = holder.get(R.id.view30);

        goodsName.setText(mData.getGoods().getGoods_name());
        goodsAttr.setText(mData.getGoods().getGoods_attr());
        goodsNum.setText(String.valueOf(mData.getGoods().getGoods_number()));
        if (mData.getGoods().getIs_promote().equals("1") && (mData.getGoods().getPromote_end_date
                () - mData.getGoods().getCurrent_time()) > 0) {
            goodsPrice.setText(mData.getGoods().getPromote_price_format());
            promote.setText("促销");
            promote.setAlpha(1);
            String str = "优惠￥%.2f";
            Double disc = Double.valueOf(mData.getGoods().getShop_price()) - Double.valueOf(mData
                    .getGoods().getPromote_price());
            discount.setText(String.format(Locale.CHINA, str, disc));
        } else {
            goodsPrice.setText(mData.getGoods().getShop_price_format());
            promote.setAlpha(0);
        }
        goodsMarket.setText(mData.getGoods().getMarket_price_format());
        goodsMarket.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        Glide.with(context).load(mData.getGoods().getOriginal_img()).into(iv);

        cb.setChecked(mData.isCheckOnce());

        ConstraintSet set = new ConstraintSet();
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        set.clone(cly);
        if (mData.isLong()) {
            set.connect(view.getId(), ConstraintSet.TOP, cly.getId(), ConstraintSet.TOP);
            set.constrainWidth(findSimilarity.getId(), ToolUtils.dip2px(context, 60));
            set.constrainHeight(findSimilarity.getId(), ToolUtils.dip2px(context, 60));
            String token = (String) UserInfoUtils.getInstance(context).getUserInfo().get("token");
            if(token != null && !token.equals("")){
                set.constrainWidth(iWantCollection.getId(), ToolUtils.dip2px(context, 60));
                set.constrainHeight(iWantCollection.getId(), ToolUtils.dip2px(context, 60));
                iWantCollection.setAlpha(1);
            }else{
                set.constrainWidth(iWantCollection.getId(), 1);
                set.constrainHeight(iWantCollection.getId(), 1);
                iWantCollection.setAlpha(0);
            }
            set.constrainWidth(delete.getId(), ToolUtils.dip2px(context, 60));
            set.constrainHeight(delete.getId(), ToolUtils.dip2px(context, 60));
        } else {
            set.connect(view.getId(), ConstraintSet.TOP, cly.getId(), ConstraintSet.BOTTOM);
            set.constrainWidth(findSimilarity.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(findSimilarity.getId(), ToolUtils.dip2px(context, 0));
            set.constrainWidth(iWantCollection.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(iWantCollection.getId(), ToolUtils.dip2px(context, 0));
            set.constrainWidth(delete.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(delete.getId(), ToolUtils.dip2px(context, 0));
        }
        set.applyTo(cly);
    }
}
