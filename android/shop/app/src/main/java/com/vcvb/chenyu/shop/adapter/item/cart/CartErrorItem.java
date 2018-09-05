package com.vcvb.chenyu.shop.adapter.item.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;

public class CartErrorItem extends BaseItem<CartListBean> {
    public static final int TYPE = -1;

    public CartErrorItem(CartListBean bean, Context c) {
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
                        .cart_content_no_data, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
//        TextView tv = holder.getTextView(R.id.textView100);
//        tv.setText(mData.getGoodsName());
//        ImageView iv = holder.getImageView(R.id.imageView40);
//        Picasso.with(context).load(mData.getGoodsPic())
//                //.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//                .config(Bitmap.Config.RGB_565)
//                .placeholder(R.drawable.icon_no_pic).into(iv);
    }
}
