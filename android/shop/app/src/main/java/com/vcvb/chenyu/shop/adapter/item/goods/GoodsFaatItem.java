package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;

public class GoodsFaatItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = 4;

    public GoodsFaatItem(GoodsDetail beans, Context c) {
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

        if (mData.getIs_volume() == 1) {
            for (int i = 0; i < mData.getGoodsGVPS().size(); i++) {
                switch (i) {
                    case 0:
                    tv1.setText("[促销]");
                    tv2.setText("" + mData.getGoodsGVPS().get(i).getVolume_number() + "件"+mData.getGoodsGVPS().get(i).getVolume_price_format());
                        break;
                    case 1:
                    tv3.setText("[促销]");
                    tv4.setText("" + mData.getGoodsGVPS().get(i).getVolume_number() + "件"+mData.getGoodsGVPS().get(i).getVolume_price_format());
                        break;
                }
            }
        }else if (mData.getIs_fullcut() == 1) {
            for (int i = 0; i < mData.getGoodsFCS().size(); i++) {
                switch (i) {
                    case 0:
                    tv1.setText("[满减]");
                    tv2.setText("满" + mData.getGoodsFCS().get(i).getCfull_format() + "减"+mData.getGoodsFCS().get(i).getCreduce_format());
                        break;
                    case 1:
                    tv3.setText("[满减]");
                    tv4.setText("满" + mData.getGoodsFCS().get(i).getCfull_format() + "减"+mData.getGoodsFCS().get(i).getCreduce_format());
                        break;
                }
            }
        }

        View v = holder.getItemView();
        posMap.put(v.getId(), 0);
        v.setOnClickListener(listener);
    }
}
