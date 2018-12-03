package com.vcvb.chenyu.shop.adapter.item.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;

public class UserRealItem extends BaseItem<UserInfoBean> {
    public static final int TYPE = R.layout.user_item;

    public UserRealItem(UserInfoBean bean, Context c) {
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
        TextView tv = holder.getTextView(R.id.textView137);
        TextView tv2 = holder.getTextView(R.id.textView138);
        tv.setText(R.string.real_title);
        if (mData.getIs_real() != null) {
            if (mData.getIs_real() == 1) {
                tv2.setText(R.string.real_subtitle_o);
            }else{
                tv2.setText(R.string.real_subtitle);
            }
        }else{
            tv2.setText(R.string.real_subtitle);
        }
    }
}
