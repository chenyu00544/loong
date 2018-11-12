package com.vcvb.chenyu.shop.adapter.item.user.real;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.user.UserReal;

public class UserRealWhyItem extends BaseItem<UserReal> {
    public static final int TYPE = R.layout.user_real_why_item;

    public UserRealWhyItem(UserReal bean, Context c) {
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
        if(mData.getExplain() != null){
            SpannableStringBuilder span = new SpannableStringBuilder("缩进"+mData.getExplain());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tv.setText(span);
        }
    }
}
