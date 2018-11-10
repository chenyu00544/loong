package com.vcvb.chenyu.shop.adapter.item.user.real;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;

public class UserRealWhyItem extends BaseItem<UserInfoBean> {
    public static final int TYPE = R.layout.user_real_why_item;

    public UserRealWhyItem(UserInfoBean bean, Context c) {
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
        TextView tv = holder.get(R.id.textView148);
        tv.setText("根据海关规定,根据海关规定,根据海关规定,根据海关规定," +
                "根据海关规定根据海关规定根据海关规定，根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定，根据海关规定");
    }
}
