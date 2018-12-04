package com.vcvb.chenyu.shop.adapter.item.user.useraccount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.user.UserInfoBean;

public class UserAccountTitleItem extends BaseItem<UserInfoBean> {
    public static final int TYPE = R.layout.user_account_title_item;
    public Integer type;

    public UserAccountTitleItem(UserInfoBean bean, Context c, Integer type) {
        super(bean, c);
        this.type = type;
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
        TextView tv = holder.getTextView(R.id.textView143);
        switch (type){
            case 1:
                tv.setText(R.string.phone_account);
                break;
            case 2:
                tv.setText(R.string.wechat);
                break;
            case 3:
                tv.setText(R.string.account_subtitle);
                break;
        }
    }
}
