package com.vcvb.chenyu.shop.adapter.item.user.userlogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.material.Material;

public class UserPhotoItem extends BaseItem<Material> {
    public static final int TYPE = 1;

    public UserPhotoItem(Material bean, Context c) {
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
                        .user_photo_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {

        ImageView iv = holder.getImageView(R.id.imageView62);
        if(mData.getIsType() == 1){
            RequestOptions requestOptions = RequestOptions.centerCropTransform().override(256, 256);
            Glide.with(context).load(mData.getFilePath()).apply(requestOptions).into(iv);
        }else{
            Glide.with(context).load(R.drawable.icon_camera).into(iv);
        }
    }
}
