package com.vcvb.chenyu.shop.adapter.item.browse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.browse.Browse;

public class BrowseItem extends BaseItem<Browse> {
    public static final int TYPE = 1;

    public BrowseItem(Browse bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.browse_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView goodsName = holder.getTextView(R.id.textView134);
        TextView tip = holder.getTextView(R.id.textView136);
        TextView goodsPrice = holder.getTextView(R.id.textView134);
        CheckBox cb = (CheckBox) holder.getView(R.id.checkBox6);
        ImageView iv = holder.getImageView(R.id.imageView58);

//        goodsName.setText(mData.getGoodsName());
//        Glide.with(context).load(mData.getPic()).into(iv);
//
//        ConstraintSet set = new ConstraintSet();
//        ConstraintLayout cly = (ConstraintLayout) holder.getItemView();
//        set.clone(cly);
//        if (mData.getIsLong() == false) {
//            set.constrainWidth(cb.getId(), ToolUtils.dip2px(context, 0));
//            set.constrainHeight(cb.getId(), ToolUtils.dip2px(context, 0));
//        } else {
//            set.constrainWidth(cb.getId(), ToolUtils.dip2px(context, 30));
//            set.constrainHeight(cb.getId(), ToolUtils.dip2px(context, 30));
//        }
//        set.applyTo(cly);
//
//        if(mData.getIsSelect() == true){
//            cb.setChecked(true);
//        }else{
//            cb.setChecked(false);
//        }
    }
}
