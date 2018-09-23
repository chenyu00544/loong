package com.vcvb.chenyu.shop.adapter.item.goods;

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

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.javaBean.goods.Evaluates;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsEvaluate;
import com.vcvb.chenyu.shop.tools.ReflexUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsEvaluateItem extends BaseItem<GoodsEvaluate> {
    public static final int TYPE = 9;
    private DefaultItemDecoration defaultItemDecoration;
    private RecyclerView recyclerView;

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
                if (mData.getEvaluates().size() > 0) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)
                            recyclerView.getLayoutParams();
                    params.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                    recyclerView.setLayoutParams(params);
                }
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                mAdapter.addAll(getItems(mData.getEvaluates()));
                CYCItemClickSupport.addTo(recyclerView).setOnItemClickListener(itemListener);
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
                imgIcon.setPadding(ToolUtils.dip2px(context, 6), ToolUtils.dip2px(context, 6),
                        ToolUtils.dip2px(context, 6), ToolUtils.dip2px(context, 6));

                ConstraintSet constraintSetTv1 = new ConstraintSet();
                constraintSetTv1.clone(v);
                if (i == 0) {
                    constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.TOP, tv6.getId(),
                            ConstraintSet.BOTTOM, ToolUtils.dip2px(context, 12));
                    constraintSetTv1.connect(imgIcon.getId(), ConstraintSet.LEFT, tv6.getId(),
                            ConstraintSet.LEFT, 12);
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

            TextView tv1 = holder.getTextView(R.id.textView22);
            posMap.put(tv1.getId(), Integer.MAX_VALUE - 1);
            tv1.setOnClickListener(listener);
            TextView tv2 = holder.getTextView(R.id.textView13);
            posMap.put(tv2.getId(), Integer.MAX_VALUE - 2);
            tv2.setOnClickListener(listener);
            TextView tv3 = holder.getTextView(R.id.textView16);
            posMap.put(tv3.getId(), Integer.MAX_VALUE - 3);
            tv3.setOnClickListener(listener);
            TextView tv4 = holder.getTextView(R.id.textView17);
            posMap.put(tv4.getId(), Integer.MAX_VALUE - 4);
            tv4.setOnClickListener(listener);
            View tv5 = holder.getView(R.id.view75);
            posMap.put(tv5.getId(), Integer.MAX_VALUE - 5);
            tv5.setOnClickListener(listener);
        }
    }

    public List<Item> getItems(List<Evaluates> bean) {
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
