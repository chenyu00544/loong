package com.vcvb.chenyu.shop.adapter.item.user.real;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.user.UserReal;

public class UserRealCardItem extends BaseItem<UserReal> {
    public static final int TYPE = R.layout.user_real_card_item;

    public UserRealCardItem(UserReal bean, Context c) {
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
        ImageView iv1 = holder.getImageView(R.id.imageView68);
        ImageView iv2 = holder.getImageView(R.id.imageView69);
        ImageView iv3 = holder.getImageView(R.id.imageView73);
        ImageView iv4 = holder.getImageView(R.id.imageView72);
        if (mData.getFront_of_id_card() != null) {
            Glide.with(context).load(mData.getFront_of_id_card()).into(iv1);
            iv3.setAlpha(255);
        } else {
            Glide.with(context).load(R.drawable.icon_up_card).into(iv1);
            iv3.setAlpha(0);
        }

        if (mData.getReverse_of_id_card() != null) {
            Glide.with(context).load(mData.getFront_of_id_card()).into(iv2);
            iv4.setAlpha(255);
        } else {
            Glide.with(context).load(R.drawable.icon_up_card).into(iv2);
            iv4.setAlpha(0);
        }
    }
}
