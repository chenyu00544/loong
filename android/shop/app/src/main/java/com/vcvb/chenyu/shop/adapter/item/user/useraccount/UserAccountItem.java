package com.vcvb.chenyu.shop.adapter.item.user.useraccount;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
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
    public static final int TYPE = R.layout.user_account_item;
    public Integer type;

    public UserAccountItem(UserInfoBean bean, Context c, Integer type) {
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
        TextView tv = holder.get(R.id.textView144);
        TextView tv2 = holder.get(R.id.textView145);
        TextView tv3 = holder.get(R.id.textView146);
        ImageView iv = holder.get(R.id.imageView66);
        ImageView iv1 = holder.get(R.id.imageView67);

        ConstraintSet set = new ConstraintSet();
        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
        set.clone(cly);
        boolean bool = true;

        View v = holder.get(R.id.view86);
        posMap.put(v.getId(), type);

        switch (type) {
            case 1:
                if (mData.getMobile_phone() != null && !mData.getMobile_phone().equals("")) {
                    Glide.with(context).load(R.drawable.icon_phone_a).into(iv);
                    tv3.setText(R.string.bind);
                    tv2.setText(mData.getMobile_phone());
                    bool = true;
                } else {
                    Glide.with(context).load(R.drawable.icon_phone_gray).into(iv);
                    tv3.setText(R.string.no_bind);
                    bool = false;
                }
                tv.setText(R.string.phone_account);
                break;
            case 2:
                if (mData.getUnion_id() != null && !mData.getUnion_id().equals("")) {
                    Glide.with(context).load(R.drawable.icon_wechat).into(iv);
                    tv3.setText(R.string.bind);
                    tv2.setText(mData.getNick_name());
                    bool = true;
                } else {
                    Glide.with(context).load(R.drawable.icon_wechat_gray).into(iv);
                    tv3.setText(R.string.no_bind);
                    bool = false;
                }
                tv.setText(R.string.wechat);
                break;
            case 3:
                if (mData.getEmail() != null && !mData.getEmail().equals("")) {
                    Glide.with(context).load(R.drawable.icon_email_a).into(iv);
                    tv3.setText(R.string.bind);
                    tv2.setText(mData.getEmail());
                    bool = true;
                } else {
                    Glide.with(context).load(R.drawable.icon_email_gray).into(iv);
                    tv3.setText(R.string.no_bind);
                    bool = false;
                }
                tv.setText(R.string.email_account);
                break;
            case 4:
                if (mData.getQq() != null && !mData.getQq().equals("")) {
                    Glide.with(context).load(R.drawable.icon_qq).into(iv);
                    tv3.setText(R.string.bind);
                    tv2.setText(mData.getNick_name());
                    bool = true;
                } else {
                    Glide.with(context).load(R.drawable.icon_qq_gray).into(iv);
                    tv3.setText(R.string.no_bind);
                    bool = false;
                }
                tv.setText(R.string.qq);
                break;
        }

        if(bool){
            set.constrainHeight(tv2.getId(), ToolUtils.dip2px(context, 15));
            set.constrainWidth(iv1.getId(), ToolUtils.dip2px(context, 0));
            set.constrainHeight(iv1.getId(), ToolUtils.dip2px(context, 0));
//            v.setOnClickListener(null);
        }else{
            set.constrainWidth(iv1.getId(), ToolUtils.dip2px(context, 15));
            set.constrainHeight(iv1.getId(), ToolUtils.dip2px(context, 15));
            set.constrainHeight(tv2.getId(), ToolUtils.dip2px(context, 0));
//            v.setOnClickListener(listener);
        }
        set.applyTo(cly);
        v.setOnClickListener(listener);
    }
}
