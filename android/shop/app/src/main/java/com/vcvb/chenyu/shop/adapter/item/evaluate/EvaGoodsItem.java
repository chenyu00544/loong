package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;

public class EvaGoodsItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = R.layout.evaluate_goods_item;

    public EvaGoodsItem(GoodsDetail beans, Context c) {
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

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.get(R.id.textView278);
        ImageView iv = holder.get(R.id.imageView136);
        if(mData != null){
            RequestOptions requestOptions = RequestOptions.centerCropTransform().placeholder(R
                    .drawable.common_google_signin_btn_icon_light_normal_background).dontAnimate();
            Glide.with(context).load(mData.getOriginal_img()).apply(requestOptions).into(iv);
            tv.setText(mData.getGoods_name());
        }
    }
}
