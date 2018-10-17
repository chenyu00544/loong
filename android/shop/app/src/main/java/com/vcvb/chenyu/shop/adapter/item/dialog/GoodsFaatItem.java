package com.vcvb.chenyu.shop.adapter.item.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsFaat;

public class GoodsFaatItem extends BaseItem<GoodsFaat> {
    public static final int TYPE = 1;

    public GoodsFaatItem(GoodsFaat beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_faat_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView231);
//        SpannableString spannableString = new SpannableString(mData.getType() + mData.getInfo());
//        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(context.getResources()
//                .getColor(R.color.red));
//        spannableString.setSpan(foregroundColorSpan, 0, mData.getType().length(), Spanned
//                .SPAN_INCLUSIVE_EXCLUSIVE);
//        tv.setText(spannableString);
    }
}
