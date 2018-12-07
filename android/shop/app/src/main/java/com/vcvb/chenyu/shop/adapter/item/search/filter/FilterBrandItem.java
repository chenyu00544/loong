package com.vcvb.chenyu.shop.adapter.item.search.filter;

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
import com.vcvb.chenyu.shop.javaBean.search.FilterBean;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class FilterBrandItem extends BaseItem<FilterBean> {
    public static final int TYPE = R.layout.search_filter_item;
    private FlowLayout fl1;
    private OnClickListener onClickListener;
    int pos;

    public FilterBrandItem(FilterBean bean, Context c) {
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
        pos = position;

        TextView tv1 = holder.get(R.id.textView185);
        tv1.setText(mData.getTitle());

        TextView tv2 = holder.get(R.id.textView262);
        tv2.setText(R.string.more);

        fl1 = (FlowLayout) holder.getView(R.id.flowLayout);
        fl1.setChildSpacing(16);
        fl1.setRowSpacing(16);
        fl1.setChildSpacingForLastRow(16);
        fl1.removeAllViews();
        for (int i = 0; i < mData.getList().size(); i++) {
            TextView ftv = new TextView(context);
            ftv.setText(mData.getList().get(i).getBrand_name());
            int id = IdsUtils.generateViewId();
            mData.getList().get(i).setButtonId(id);
            ftv.setId(id);
            ftv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            ftv.setEllipsize(TextUtils.TruncateAt.END);
            ftv.setGravity(Gravity.CENTER_HORIZONTAL);
            ftv.setLines(1);
            ftv.setMaxEms(8);
            ftv.setPadding(ToolUtils.dip2px(context, 12), ToolUtils.dip2px(context, 8), ToolUtils
                    .dip2px(context, 12), ToolUtils.dip2px(context, 8));
            fl1.addView(ftv);
            if (mData.getList().get(i).isIs_select()) {
                ftv.setBackgroundResource(R.drawable.shape_6_red);
                ftv.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                ftv.setBackgroundResource(R.drawable.shape_6_gray_d);
                ftv.setTextColor(context.getResources().getColor(R.color.black));
            }
            ftv.setOnClickListener(listener);
        }
    }

    public interface OnClickListener {
        void onClicked(View view, FilterBean filter, int pos);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < mData.getList().size(); i++) {
                TextView tv = fl1.findViewById(mData.getList().get(i).getButtonId());
                mData.getList().get(i).setIs_select(false);
                tv.setTextColor(context.getResources().getColor(R.color.black));
                tv.setBackgroundResource(R.drawable.shape_6_gray_d);
                if (tv.getId() == view.getId()) {
                    mData.getList().get(i).setIs_select(true);
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
