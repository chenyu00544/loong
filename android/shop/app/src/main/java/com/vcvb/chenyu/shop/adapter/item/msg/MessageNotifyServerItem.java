package com.vcvb.chenyu.shop.adapter.item.msg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.msg.NotifyMsgSever;

import java.util.List;

public class MessageNotifyServerItem extends BaseItem<List<NotifyMsgSever>> {
    public static final int TYPE = 1;

    public MessageNotifyServerItem(List<NotifyMsgSever> bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView num = holder.get(R.id.textView220);
        ImageView iv = holder.get(R.id.imageView89);
        Glide.with(context).load(R.drawable.icon_service).into(iv);
        if (mData.size() > 0) {
            if(mData.get(0).isIs_look()){
                num.setAlpha(0);
            }else{
                num.setText("1");
                num.setAlpha(1);
            }
            if(mData.get(0).getDescribe() != null){
                TextView title = holder.get(R.id.textView218);
                title.setText(mData.get(0).getDescribe());
            }
            if(mData.get(0).getContent() != null){
                TextView content = holder.get(R.id.textView219);
                content.setText(mData.get(0).getContent());
            }
        }
        View v = holder.getItemView();
        posMap.put(v.getId(),position);
        v.setOnClickListener(listener);
    }
}
