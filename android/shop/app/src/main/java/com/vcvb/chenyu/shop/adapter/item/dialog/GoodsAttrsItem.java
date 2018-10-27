package com.vcvb.chenyu.shop.adapter.item.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttr;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.List;

public class GoodsAttrsItem extends BaseItem<List<GoodsAttr>> {
    public static final int TYPE = R.layout.dialog_attr_item;
    FlowLayout fl1;
    OnClickListener onClickListener;
    int pos;

    public GoodsAttrsItem(List<GoodsAttr> beans, Context c) {
        super(beans, c);
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
        pos = position;
        fl1 = (FlowLayout) holder.getView(R.id.flowLayout);
        fl1.setChildSpacing(16);
        fl1.setRowSpacing(16);
        fl1.setChildSpacingForLastRow(16);
        fl1.removeAllViews();
        for (int i = 0; i < mData.size(); i++) {
            if (i == 0) {
                TextView tv = holder.getTextView(R.id.textView185);
                tv.setText(mData.get(i).getAttr_name());
            }
            TextView tv1 = new TextView(context);
            tv1.setText(mData.get(i).getAttr_value());
            int id = IdsUtils.generateViewId();
            mData.get(i).setButtonId(id);
            tv1.setId(id);
            tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            tv1.setEllipsize(TextUtils.TruncateAt.END);
            tv1.setGravity(Gravity.CENTER_HORIZONTAL);
            tv1.setLines(1);
            tv1.setMaxEms(8);
            tv1.setPadding(ToolUtils.dip2px(context, 8), ToolUtils.dip2px(context, 8), ToolUtils
                    .dip2px(context, 8), ToolUtils.dip2px(context, 8));
            fl1.addView(tv1);
            if (mData.get(i).getIsSelect()) {
                tv1.setBackgroundResource(R.drawable.shape_6_red);
                tv1.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                tv1.setBackgroundResource(R.drawable.shape_6_grad_d);
                tv1.setTextColor(context.getResources().getColor(R.color.black));
            }
            tv1.setOnClickListener(listener);
        }
    }

    public interface OnClickListener {
        void onClicked(View view, List<GoodsAttr> attrs, int pos);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < mData.size(); i++) {
                TextView tv = fl1.findViewById(mData.get(i).getButtonId());
                mData.get(i).setIsSelect(false);
                tv.setTextColor(context.getResources().getColor(R.color.black));
                tv.setBackgroundResource(R.drawable.shape_6_grad_d);
                if (tv.getId() == view.getId()) {
                    mData.get(i).setIsSelect(true);
                    tv.setTextColor(context.getResources().getColor(R.color.white));
                    tv.setBackgroundResource(R.drawable.shape_6_red);
                }
            }
            if (onClickListener != null) {
                onClickListener.onClicked(view, mData, pos);
            }
        }
    };
}
