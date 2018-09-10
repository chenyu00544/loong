package com.vcvb.chenyu.shop.adapter.item.user.useraccount;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class UserAccountItem extends BaseItem<UserInfoBean> {
    public static final int TYPE = 1;

    public UserAccountItem(UserInfoBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_account_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView144);
        TextView tv2 = holder.getTextView(R.id.textView145);
        TextView tv3 = holder.getTextView(R.id.textView146);
        ImageView iv = holder.getImageView(R.id.imageView66);
        tv.setText(mData.getTitle());

        Glide.with(context).load(mData.getIcon()).into(iv);

        if(mData.getIsBind() == true){
            tv2.setText(mData.getName());
            ConstraintSet set = new ConstraintSet();
            ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
            set.clone(cly);
            set.constrainHeight(tv2.getId(), ToolUtils.dip2px(context, 15));
            set.applyTo(cly);

        }else{
            ConstraintSet set = new ConstraintSet();
            ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
            set.clone(cly);
            set.constrainHeight(tv2.getId(), ToolUtils.dip2px(context, 0));
            set.applyTo(cly);
        }
        tv3.setText(mData.getSubTitle());
    }
}
