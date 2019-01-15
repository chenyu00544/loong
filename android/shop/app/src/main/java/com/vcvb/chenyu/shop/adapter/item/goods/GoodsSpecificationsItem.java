package com.vcvb.chenyu.shop.adapter.item.goods;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.tools.ReflexUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class GoodsSpecificationsItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = R.layout.goods_specifications_item;
    int height;
    int count=0;

    public GoodsSpecificationsItem(GoodsDetail beans, Context c) {
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

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        final int width = ToolUtils.getWindowsWidth(context) - ToolUtils.dip2px(context, 30);
        FlowLayout fl = (FlowLayout) holder.getView(R.id.param_wrap);
        System.out.println(fl.getChildCount());
        if(count == 0){
            fl.removeAllViews();
            for (int i = 0; i < mData.getSingleAttrs().size(); i++) {
                if(mData.getSingleAttrs().get(i).getAttr_group() == 0){
                    count += 2;
                    final TextView tv1 = new TextView(context);
                    final TextView tv2 = new TextView(context);
                    tv1.setPadding(10, 10, 10, 10);
                    tv1.setGravity(Gravity.CENTER_VERTICAL);
                    tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    fl.addView(tv1);

                    ConstraintLayout.LayoutParams tvp1 = new ConstraintLayout.LayoutParams(width / 4,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    tv1.setLayoutParams(tvp1);

                    tv1.setText(mData.getSingleAttrs().get(i).getAttr_name());

                    if (i == 0) {
                        tv1.setBackgroundResource(R.drawable.title_all_border);
                        tv2.setBackgroundResource(R.drawable.content_trb_border);
                    } else {
                        tv1.setBackgroundResource(R.drawable.title_lbr_border);
                        tv2.setBackgroundResource(R.drawable.content_br_border);
                    }
                    tv2.setId(ReflexUtils.getResByRid("id", "attr_content_" + (i), context));
                    tv2.setPadding(10, 10, 10, 10);
                    tv2.setGravity(Gravity.CENTER_VERTICAL);
                    fl.addView(tv2);

                    final ViewTreeObserver vto = tv1.getViewTreeObserver();
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            tv1.getViewTreeObserver().removeOnPreDrawListener(this);
                            height = tv1.getMeasuredHeight();
                            ConstraintLayout.LayoutParams tvp2 = new ConstraintLayout.LayoutParams(width * 3 / 4,
                                    height);
                            tv2.setLayoutParams(tvp2);
                            return true;
                        }
                    });
                    tv2.setText(mData.getSingleAttrs().get(i).getAttr_value());
                }
            }
        }
    }
}
