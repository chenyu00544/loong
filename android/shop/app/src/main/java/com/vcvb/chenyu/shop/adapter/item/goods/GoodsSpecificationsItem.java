package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsSpecifications;
import com.vcvb.chenyu.shop.tools.ReflexUtils;

public class GoodsSpecificationsItem extends BaseItem<GoodsSpecifications> {
    public static final int TYPE = 11;

    public GoodsSpecificationsItem(GoodsSpecifications beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_specifications_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        ConstraintLayout v = (ConstraintLayout) holder.getItemView();
        TextView tv = v.findViewById(R.id.textView29);
        for (int i = 0; i < mData.getGoodsSpecifications().size(); i++) {
            TextView tv1 = new TextView(context);
            TextView tv2 = new TextView(context);
            tv1.setId(ReflexUtils.getResByRid("id", "attr_title_" + (i), context));
            tv1.setPadding(10, 10, 10, 10);
            tv1.setGravity(Gravity.CENTER_VERTICAL);
            v.addView(tv1);
            ConstraintLayout.LayoutParams tvp1 = new ConstraintLayout.LayoutParams(180,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
            tv1.setLayoutParams(tvp1);
            tv1.setText(mData.getGoodsSpecifications().get(i).getTitle());
            ConstraintSet constraintSetTv1 = new ConstraintSet();
            constraintSetTv1.clone(v);
            if (i == 0) {
                constraintSetTv1.connect(tv1.getId(), ConstraintSet.TOP, tv.getId(),
                        ConstraintSet.BOTTOM, 24);
                constraintSetTv1.connect(tv1.getId(), ConstraintSet.LEFT, tv.getId(),
                        ConstraintSet.LEFT, 0);
                tv1.setBackgroundResource(R.drawable.title_all_border);
                tv2.setBackgroundResource(R.drawable.content_trb_border);
            } else {
                int pid = ReflexUtils.getResByRid("id", "attr_title_" + (i - 1), context);
                constraintSetTv1.connect(tv1.getId(), ConstraintSet.TOP, pid, ConstraintSet
                        .BOTTOM, 0);
                constraintSetTv1.connect(tv1.getId(), ConstraintSet.LEFT, pid, ConstraintSet
                        .LEFT, 0);
                tv1.setBackgroundResource(R.drawable.title_lbr_border);
                tv2.setBackgroundResource(R.drawable.content_br_border);
            }
            constraintSetTv1.applyTo(v);

            tv2.setId(ReflexUtils.getResByRid("id", "attr_content_" + (i), context));
            tv2.setPadding(10, 10, 10, 10);
            tv2.setGravity(Gravity.CENTER_VERTICAL);
            v.addView(tv2);
            ConstraintLayout.LayoutParams tvp2 = new ConstraintLayout.LayoutParams(0,
                    ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
            tv2.setLayoutParams(tvp2);
            tv2.setText(mData.getGoodsSpecifications().get(i).getContent());
            ConstraintSet constraintSetTv2 = new ConstraintSet();
            constraintSetTv2.clone(v);
            constraintSetTv2.connect(tv2.getId(), ConstraintSet.TOP, tv1.getId(), ConstraintSet
                    .TOP, 0);
            constraintSetTv2.connect(tv2.getId(), ConstraintSet.LEFT, tv1.getId(), ConstraintSet
                    .RIGHT, 0);
            constraintSetTv2.connect(tv2.getId(), ConstraintSet.BOTTOM, tv1.getId(),
                    ConstraintSet.BOTTOM, 0);
            constraintSetTv2.connect(tv2.getId(), ConstraintSet.RIGHT, tv.getId(), ConstraintSet
                    .RIGHT, 24);
            constraintSetTv2.applyTo(v);
        }
    }
}
