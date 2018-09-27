package com.vcvb.chenyu.shop.adapter.item.faat;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.faat.FaatNav;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class FaatSubNavItem extends BaseItem<FaatNav> {
    public static final int TYPE = 1;
    public int num = 0;

    public FaatSubNavItem(FaatNav bean, Context c, int num) {
        super(bean, c);
        this.num = num;
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .faat_nav_sub_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        ConstraintLayout.LayoutParams params;
        View v = holder.getItemView();
        if(mData.getIsSelect() == true){
            v.setBackgroundResource(R.color.colorFont_morandi);
        }else {
            v.setBackgroundResource(R.color.color_transparent);
        }
        if (num < 5) {
            params = new ConstraintLayout.LayoutParams(width / num,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);

        }else{
            params = new ConstraintLayout.LayoutParams(width / 5,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
        }
        v.setLayoutParams(params);
        TextView tv = holder.getTextView(R.id.textView25);
        tv.setText(mData.getTitle());
    }
}
