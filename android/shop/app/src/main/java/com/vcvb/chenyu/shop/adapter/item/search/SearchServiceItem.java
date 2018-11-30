package com.vcvb.chenyu.shop.adapter.item.search;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.search.FilterBean;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class SearchServiceItem extends BaseItem<FilterBean> {
    public static final int TYPE = 1;

    public SearchServiceItem(FilterBean bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_serveice_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView159);
        tv.setText(mData.getTitle());
        FlowLayout fly = (FlowLayout) holder.getView(R.id.wrap_hot);
        fly.setChildSpacing(8);
        fly.setRowSpacing(8);
        fly.setChildSpacingForLastRow(8);
        for (int i = 0; i < mData.getList().size(); i++) {
            TextView textView = new TextView(context);
            textView.setText(mData.getList().get(i).getTitle());
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            textView.setLines(1);
            textView.setMaxEms(8);
            textView.setPadding(ToolUtils.dip2px(context, 18), ToolUtils.dip2px(context, 8),
                    ToolUtils.dip2px(context, 18), ToolUtils.dip2px(context, 8));
            textView.setBackgroundResource(R.drawable.shape_6_gray_d);
            fly.addView(textView);
        }
    }
}
