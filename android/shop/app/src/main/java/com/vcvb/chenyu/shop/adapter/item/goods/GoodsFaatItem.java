package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsFaat;

import java.util.List;

public class GoodsFaatItem extends BaseItem<List<GoodsFaat>> {
    public static final int TYPE = 4;

    public GoodsFaatItem(List<GoodsFaat> beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_faat_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv1 = holder.getTextView(R.id.textView2);
        TextView tv2 = holder.getTextView(R.id.textView1);
        TextView tv3 = holder.getTextView(R.id.textView4);
        TextView tv4 = holder.getTextView(R.id.textView5);

        for (int i = 0; i < mData.size(); i++) {
            switch (i) {
                case 0:
                    tv1.setText("[" + mData.get(i).getType() + "]");
                    tv2.setText("[" + mData.get(i).getInfo() + "]");
                    break;
                case 1:
                    tv3.setText("[" + mData.get(i).getType() + "]");
                    tv4.setText("[" + mData.get(i).getInfo() + "]");
                    break;
            }
        }
        View v = holder.getItemView();
        posMap.put(v.getId(), 0);
        v.setOnClickListener(listener);
    }
}
