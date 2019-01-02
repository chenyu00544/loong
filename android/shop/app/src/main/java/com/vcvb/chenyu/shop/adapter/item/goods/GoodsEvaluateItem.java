package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsEvaluateItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = R.layout.goods_evaluate_item;
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
                .inflate(TYPE, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView commentTitle = holder.getTextView(R.id.textView23);
        posMap.put(commentTitle.getId(), 0);
        commentTitle.setOnClickListener(listener);

        Integer commentCount = 0;
        for (int i = 0; i < mData.getCommentLabels().size(); i++) {
            commentCount += mData.getCommentLabels().get(i).getCount();
        }
        commentTitle.setText("商品评价(" + String.valueOf(commentCount) + ")");

        TextView goodPercentTv = holder.getTextView(R.id.textView22);
        posMap.put(goodPercentTv.getId(), 0);
        goodPercentTv.setOnClickListener(listener);
        if (mData.getComments_number() > 0) {
            Double goodPercent = (double) (commentCount / mData.getComments_number()) * 100;
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
                String str = "%s(%s)";
                textView.setText(String.format(str, mData.getCommentLabels().get(i).getLabel_name
                        (), mData.getCommentLabels().get(i).getCount()));
                flowLayout.addView(textView);
                posMap.put(textView.getId(), mData.getCommentLabels().get(i).getId());
                textView.setOnClickListener(listener);
            }
        }
        ConstraintLayout v = (ConstraintLayout) holder.getItemView();
        if (mData.getIsScroll() == 1) {
            if (recyclerView == null) {
                recyclerView = (RecyclerView) holder.getView(R.id.evaluate_list);
                CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                if (defaultItemDecoration == null) {
                    defaultItemDecoration = new DefaultItemDecoration(context, 3);
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
        }

        //问答
//        ImageView imgIcon1 = holder.get(R.id.imageView153);
//        TextView textv1 = holder.get(R.id.textView297);
//        TextView textv2 = holder.get(R.id.textView298);
//        ImageView imgIcon2 = holder.get(R.id.imageView154);
//        TextView textv3 = holder.get(R.id.textView299);
//        TextView textv4 = holder.get(R.id.textView300);
//
//        ConstraintSet set = new ConstraintSet();
//        set.clone(v);
//        if (mData.getQas().size() == 1) {
//            set.constrainHeight(imgIcon1.getId(), ToolUtils.dip2px(context, 12));
//            set.constrainHeight(imgIcon2.getId(), 1);
//            set.applyTo(v);
//        } else if (mData.getQas().size() > 1) {
//            set.constrainHeight(imgIcon1.getId(), ToolUtils.dip2px(context, 12));
//            set.constrainHeight(imgIcon2.getId(), ToolUtils.dip2px(context, 12));
//            set.applyTo(v);
//        } else if (mData.getQas().size() == 0){
//            set.constrainHeight(imgIcon1.getId(), 1);
//            set.constrainHeight(imgIcon2.getId(), 1);
//            set.applyTo(v);
//        }

//        View tv5 = holder.getView(R.id.view75);
//        posMap.put(tv5.getId(), Integer.MAX_VALUE);
//        tv5.setOnClickListener(listener);
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
                onClickListener.onClicked(itemView, 0);
            }
        }
    };
}
