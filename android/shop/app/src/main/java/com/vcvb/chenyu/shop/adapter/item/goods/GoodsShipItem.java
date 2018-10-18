package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;

public class GoodsShipItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = 6;

    public GoodsShipItem(GoodsDetail beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_ship_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        View v = holder.getItemView();
        v.setOnClickListener(listener);
        posMap.put(v.getId(), 0);

        TextView address = holder.getTextView(R.id.textView1);
        String addressStr = "";
//        if (mData.getProvince() != null) {
//            addressStr += mData.getProvince() + " ";
//        }
//        if (mData.getCity() != null) {
//            addressStr += mData.getCity() + " ";
//        }
//        if (mData.getCounty() != null) {
//            addressStr += mData.getCounty() + " ";
//        }
//        address.setText(addressStr + mData.getAddress());
//
//        ImageView fromIv = holder.getImageView(R.id.imageView5);
//        RequestOptions requestOptions = RequestOptions.circleCropTransform().diskCacheStrategy
//                (DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).override(120, 120);
//        Glide.with(context).load(mData.getFromPic()).apply(requestOptions).into(fromIv);
//
//        TextView from = holder.getTextView(R.id.textView7);
//        from.setText(mData.getFrom());
//        TextView to = holder.getTextView(R.id.textView8);
//        to.setText(mData.getEnd());
    }
}
