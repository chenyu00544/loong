package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.evaluate.EvaluateGroup;
import com.vcvb.chenyu.shop.javaBean.order.OrderGoods;

public class EvaluateGoodsItem extends BaseItem<EvaluateGroup> {
    public static final int TYPE = R.layout.evaluate_goods_item;

    public EvaluateGoodsItem(EvaluateGroup bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        TextView tv = holder.get(R.id.textView278);
        ImageView iv = holder.get(R.id.imageView136);
        OrderGoods bean = (OrderGoods) mData.getObjs().get(position);
        if(bean != null){
            RequestOptions requestOptions = RequestOptions.centerCropTransform().placeholder(R
                    .drawable.common_google_signin_btn_icon_light_normal_background).dontAnimate();
            Glide.with(context).load(bean.getOriginal_img()).apply(requestOptions).into(iv);
            tv.setText(bean.getGoods_name());
        }
    }
}
