package com.vcvb.chenyu.shop.adapter.item.goods;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsComment;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ReflexUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsEvaluateItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = 9;
    private DefaultItemDecoration defaultItemDecoration;
    private RecyclerView recyclerView;

    public GoodsEvaluateItem(GoodsDetail beans, Context c) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView commentTitle = holder.getTextView(R.id.textView23);
        Integer commentCount = 0;
        for (int i = 0; i < mData.getCommentLabels().size(); i++) {
            commentCount += mData.getCommentLabels().get(i).getCount();
        }

        commentTitle.setText("商品评价(" + String.valueOf(commentCount) + ")");
        if (mData.getComments_number() > 0) {
            TextView goodPercentTv = holder.getTextView(R.id.textView22);
            Double goodPercent = (double) (commentCount / mData.getComments_number())*100;
            goodPercentTv.setText("好评率 " + String.valueOf(goodPercent) + "%");
        }

        FlowLayout flowLayout = holder.get(R.id.comment_label);
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.removeAllViews();
        for (int i = 0; i < mData.getCommentLabels().size(); i++) {
            if (i < 3) {
                TextView textView = new TextView(context);
                textView.setId(IdsUtils.generateViewId());
                textView.setPadding(ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 2),
                        ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 2));
                textView.setBackgroundResource(R.drawable.shape_tip);
                textView.setTextColor(context.getResources().getColor(R.color.black_29));
                textView.setTextSize(12);
                textView.setText(mData.getCommentLabels().get(i).getLabel_name() + "(" + mData
                        .getCommentLabels().get(i).getCount() + ")");
                flowLayout.addView(textView);
                posMap.put(textView.getId(), Integer.MAX_VALUE - i);
                textView.setOnClickListener(listener);
            }
        }
        if (mData.getIsScroll() == 1) {
            ConstraintLayout v = (ConstraintLayout) holder.getItemView();
            TextView tv6 = holder.getTextView(R.id.textView24);
            ImageView iv1 = holder.getImageView(R.id.imageView10);
            if (recyclerView == null) {
                recyclerView = (RecyclerView) holder.getView(R.id.evaluate_list);
                CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                if (defaultItemDecoration == null) {
                    defaultItemDecoration = new DefaultItemDecoration(context, 5);
                    recyclerView.addItemDecoration(defaultItemDecoration);
                }
                if (mData.getComments().size() > 0) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)
                            recyclerView.getLayoutParams();
                    params.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                    recyclerView.setLayoutParams(params);
                }
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                mAdapter.addAll(getItems(mData.getComments()));
                CYCItemClickSupport.addTo(recyclerView).setOnItemClickListener(itemListener);
            }

            if (mData.getQas().size() > 0) {
                for (int i = 0; i < mData.getQas().size(); i++) {
                    ImageView imgIcon = new ImageView(context);
                    imgIcon.setId(ReflexUtils.getResByRid("id", "prob_icon_" + (i), context));
                    v.addView(imgIcon);
                    ConstraintLayout.LayoutParams icl1 = new ConstraintLayout.LayoutParams(0,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    icl1.width = 40;
                    icl1.height = 35;

                    imgIcon.setLayoutParams(icl1);
                    imgIcon.setBackgroundResource(R.drawable.icon_problem);
                    imgIcon.setPadding(ToolUtils.dip2px(context, 6), ToolUtils.dip2px(context, 6)
                            , ToolUtils.dip2px(context, 6), ToolUtils.dip2px(context, 6));

                    ConstraintSet constraintSetTv1 = new ConstraintSet();
                    constraintSetTv1.clone(v);
                    if (i == 0) {
                        constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.TOP, tv6.getId(),
                                ConstraintSet.BOTTOM, ToolUtils.dip2px(context, 12));
                        constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.LEFT, tv6.getId()
                                , ConstraintSet.LEFT, 12);
                    } else {
                        int pid = ReflexUtils.getResByRid("id", "prob_icon_" + (i - 1), context);
                        constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.TOP, pid,
                                ConstraintSet.BOTTOM, ToolUtils.dip2px(context, 8));
                        constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.LEFT, pid,
                                ConstraintSet.LEFT, 0);
                    }
                    constraintSetTv1.applyTo(v);

                    TextView ptv2 = new TextView(context);
                    ptv2.setId(ReflexUtils.getResByRid("id", "answer_num_" + (i), context));
                    v.addView(ptv2);
                    ptv2.setText(mData.getQas().get(i).getUseful());
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
                    ptv1.setText(mData.getQas().get(i).getContent());
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

            TextView tv1 = holder.getTextView(R.id.textView22);
            posMap.put(tv1.getId(), Integer.MAX_VALUE - 4);
            tv1.setOnClickListener(listener);
            View tv5 = holder.getView(R.id.view75);
            posMap.put(tv5.getId(), Integer.MAX_VALUE - 5);
            tv5.setOnClickListener(listener);
        }
    }

    public List<Item> getItems(List<GoodsComment> bean) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {
            cells.add(new GoodsEvaluateSubItem(bean.get(i), context));
        }
        return cells;
    }

    CYCItemClickSupport.OnItemClickListener itemListener = new CYCItemClickSupport
            .OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
            if (onClickListener != null) {
                onClickListener.onClicked(itemView, position);
            }
        }
    };
}
