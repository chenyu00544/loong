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
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgFaat;

import java.util.Locale;

public class MessageFaatItem extends BaseItem<NotifyMsgFaat> {
    public static final int TYPE = R.layout.message_info_faat_item;

    public MessageFaatItem(NotifyMsgFaat bean, Context c) {
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
        TextView title = holder.get(R.id.textView222);
        ImageView iv = holder.get(R.id.imageView92);
        TextView desc = holder.get(R.id.textView223);
        TextView time = holder.get(R.id.textView221);

        title.setText(mData.getTitle());
        Glide.with(context).load(mData.getImg()).into(iv);
        String str = "%s-%s";
        desc.setText(String.format(Locale.CHINA, str, mData.getDescribe(), mData.getContent()));
        time.setText(mData.getAdd_time_format());
    }
}
