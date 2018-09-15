package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsEvaluate;
import com.vcvb.chenyu.shop.tools.ReflexUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GoodsEvaluateItem extends BaseItem<GoodsEvaluate> {
    public static final int TYPE = 9;

    public GoodsEvaluateItem(GoodsEvaluate beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_evaluate_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        ConstraintLayout v = (ConstraintLayout) holder.getItemView();
        HorizontalScrollView hsv = (HorizontalScrollView) holder.getView(R.id.evaluate_list);
        TextView tv6 = holder.getTextView(R.id.textView24);
        ImageView iv1 = holder.getImageView(R.id.imageView10);

        LinearLayout ly = hsv.findViewById(R.id.evaluate_list_wrap);
        ly.removeAllViews();
        RequestOptions requestOptions = RequestOptions.circleCropTransform().diskCacheStrategy
                (DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).override(120, 120);
        RoundedCornersTransformation roundedCorners = new RoundedCornersTransformation(6, 0,
                RoundedCornersTransformation.CornerType.RIGHT);
        RequestOptions requestOptions2 = RequestOptions.bitmapTransform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).override
                        (ToolUtils.dip2px(context,120), ToolUtils.dip2px(context,120));
        for (int i = 0; i < mData.getEvaluates().size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.goods_evaluate_sub_item,
                    null, false);
            ImageView iv = view.findViewById(R.id.imageView);
            Glide.with(context).load(mData.getEvaluates().get(i).getLogo()).apply(requestOptions)
                    .into(iv);
            ImageView iv2 = view.findViewById(R.id.imageView3);
            Glide.with(context).load(mData.getEvaluates().get(i).getEva_img_url()).apply
                    (requestOptions2).into(iv2);
            ly.addView(view);
        }

        for (int i = 0; i < mData.getProbs().size(); i++) {
            ImageView imgIcon = new ImageView(context);
            imgIcon.setId(ReflexUtils.getResByRid("id", "prob_icon_" + (i), context));
            v.addView(imgIcon);
            ConstraintLayout.LayoutParams icl1 = new ConstraintLayout.LayoutParams(0,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
            icl1.width = 40;
            icl1.height = 35;

            imgIcon.setLayoutParams(icl1);
            imgIcon.setBackgroundResource(R.drawable.icon_problem);
            imgIcon.setPadding(8, 8, 8, 8);

            ConstraintSet constraintSetTv1 = new ConstraintSet();
            constraintSetTv1.clone(v);
            if (i == 0) {
                constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.TOP, tv6.getId(),
                        ConstraintSet.BOTTOM, 16);
                constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.LEFT, tv6.getId(),
                        ConstraintSet.LEFT, 12);
            } else {
                int pid = ReflexUtils.getResByRid("id", "prob_icon_" + (i - 1), context);
                constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.TOP, pid, ConstraintSet
                        .BOTTOM, 8);
                constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.LEFT, pid, ConstraintSet
                        .LEFT, 0);
            }
            constraintSetTv1.applyTo(v);

            TextView ptv2 = new TextView(context);
            ptv2.setId(ReflexUtils.getResByRid("id", "answer_num_" + (i), context));
            v.addView(ptv2);
            ptv2.setText(mData.getProbs().get(i).getAnswer_num());
            ConstraintLayout.LayoutParams tcl2 = new ConstraintLayout.LayoutParams
                    (ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout
                            .LayoutParams.WRAP_CONTENT);
            ptv2.setLayoutParams(tcl2);
            ConstraintSet constraintSetTv3 = new ConstraintSet();
            constraintSetTv3.clone(v);
            constraintSetTv3.connect(ptv2.getId(), ConstraintSet.TOP, imgIcon.getId(),
                    ConstraintSet.TOP, 0);
            constraintSetTv3.connect(ptv2.getId(), ConstraintSet.BOTTOM, imgIcon.getId(),
                    ConstraintSet.BOTTOM, 0);
            constraintSetTv3.connect(ptv2.getId(), ConstraintSet.RIGHT, iv1.getId(),
                    ConstraintSet.RIGHT, 0);
            constraintSetTv3.applyTo(v);

            TextView ptv1 = new TextView(context);
            ptv1.setId(ReflexUtils.getResByRid("id", "prob_" + (i), context));
            ptv1.setTextColor(Color.parseColor("#000000"));
            v.addView(ptv1);
            ptv1.setText(mData.getProbs().get(i).getProblem());
            ConstraintLayout.LayoutParams tcl1 = new ConstraintLayout.LayoutParams
                    (ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout
                            .LayoutParams.WRAP_CONTENT);
            ptv1.setLayoutParams(tcl1);
            ConstraintSet constraintSetTv2 = new ConstraintSet();
            constraintSetTv2.clone(v);
            constraintSetTv2.connect(ptv1.getId(), ConstraintSet.TOP, imgIcon.getId(),
                    ConstraintSet.TOP, 0);
            constraintSetTv2.connect(ptv1.getId(), ConstraintSet.LEFT, imgIcon.getId(),
                    ConstraintSet.RIGHT, 12);
            constraintSetTv2.connect(ptv1.getId(), ConstraintSet.BOTTOM, imgIcon.getId(),
                    ConstraintSet.BOTTOM, 0);
            constraintSetTv2.connect(ptv1.getId(), ConstraintSet.RIGHT, ptv2.getId(),
                    ConstraintSet.LEFT, 12);
            constraintSetTv2.applyTo(v);
        }
    }
}
