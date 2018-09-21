package com.vcvb.chenyu.shop.adapter.item.goods.attr;

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
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttrs;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class GoodsAttrsItem extends BaseItem<GoodsAttrs> {
    public static final int TYPE = 1;
    FlowLayout fl1;
    OnClickListener onClickListener;
    int pos;

    public GoodsAttrsItem(GoodsAttrs beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .dialog_attr_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        pos = position;
        fl1 = (FlowLayout) holder.getView(R.id.flowLayout);
        TextView tv  = holder.getTextView(R.id.textView185);
        tv.setText(mData.getAttrName());
        fl1.removeAllViews();
        fl1.setChildSpacing(16);
        fl1.setRowSpacing(16);
        fl1.setChildSpacingForLastRow(16);
        for (int i = 0; i < mData.getAttrs().size(); i++) {
            TextView tv1 = new TextView(context);
            tv1.setText(mData.getAttrs().get(i).getAttrName());
            int id = IdsUtils.generateViewId();
            mData.getAttrs().get(i).setButtonId(id);
            tv1.setId(id);
            tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            tv1.setEllipsize(TextUtils.TruncateAt.END);
            tv1.setGravity(Gravity.CENTER_HORIZONTAL);
            tv1.setLines(1);
            tv1.setMaxEms(8);
            tv1.setPadding(ToolUtils.dip2px(context, 8), ToolUtils.dip2px(context, 8), ToolUtils
                    .dip2px(context, 8), ToolUtils.dip2px(context, 8));
            fl1.addView(tv1);
            if (mData.getAttrs().get(i).getIsSelect() == true) {
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
        void onClicked(View view, GoodsAttrs goodsAttrs, int pos);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < mData.getAttrs().size(); i++) {
                TextView tv = fl1.findViewById(mData.getAttrs().get(i).getButtonId());
                mData.getAttrs().get(i).setIsSelect(false);
                tv.setTextColor(context.getResources().getColor(R.color.black));
                tv.setBackgroundResource(R.drawable.shape_6_grad_d);
                if (tv.getId() == view.getId()) {
                    mData.getAttrs().get(i).setIsSelect(true);
                    tv.setTextColor(context.getResources().getColor(R.color.white));
                    tv.setBackgroundResource(R.drawable.shape_6_red);
                }
            }
            if(onClickListener != null){
                onClickListener.onClicked(view, mData, pos);
            }
        }
    };
}
