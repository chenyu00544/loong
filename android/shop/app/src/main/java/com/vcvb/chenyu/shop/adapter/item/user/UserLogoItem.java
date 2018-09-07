package com.vcvb.chenyu.shop.adapter.item.user;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class UserLogoItem extends BaseItem<UserInfoBean> {
    public static final int TYPE = 2;

    public UserLogoItem(UserInfoBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_logo_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView139);
        final ImageView iv = holder.getImageView(R.id.imageView61);

        tv.setText(mData.getSubTitle());

        RoundedCornersTransformation corners = new RoundedCornersTransformation(30, 0,
                RoundedCornersTransformation.CornerType.TOP);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(corners).override(120,
 120);
//        RequestOptions requestOptions = RequestOptions.circleCropTransform().diskCacheStrategy
//                (DiskCacheStrategy.RESOURCE).skipMemoryCache(false).override(120, 120);
        Glide.with(context).load("http://pic26.nipic.com/20121219/7447430_180414008000_2.jpg").apply
                (requestOptions).into(iv);

//        Picasso.with(context).load("http://a3.topitme.com/1/21/79/1128833621e7779211o.jpg")
//                .config(Bitmap.Config.RGB_565).transform(corners).placeholder(R.drawable
// .icon_no_pic).into(iv);
    }

    private Bitmap id2Bitmap(int id) {
        return BitmapFactory.decodeResource(context.getResources(), id);
    }
}
