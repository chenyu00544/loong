package com.vcvb.chenyu.shop.adapter.item.msg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgSever;

import java.util.Locale;

public class MessageServerItem extends BaseItem<NotifyMsgSever> {
    public static final int TYPE = R.layout.message_info_server_item;

    public MessageServerItem(NotifyMsgSever bean, Context c) {
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
        ImageView iv = holder.get(R.id.imageView98);
        Glide.with(context).load(mData.getImg()).into(iv);

        TextView tv = holder.get(R.id.textView229);
        tv.setText(mData.getContent());

        TextView title = holder.get(R.id.textView265);
        String str = "%s:%s";
        title.setText(String.format(Locale.CANADA, str, mData.getTitle(), mData.getDescribe()));
    }
}
