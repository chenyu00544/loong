package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.List;

public class EvaluateImgItem extends BaseItem<List<String>> {
    public static final int TYPE = R.layout.evaluate_img_item;
    private FlowLayout flowLayout;

    public EvaluateImgItem(List<String> bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width / 4 - ToolUtils.dip2px
                (context, 16), width / 4 - ToolUtils.dip2px(context, 16));
        flowLayout = holder.get(R.id.return_img);
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.removeAllViews();
        RequestOptions requestOptions = RequestOptions.centerInsideTransform().diskCacheStrategy
                (DiskCacheStrategy.NONE).dontAnimate();
        for (int i = 0; i < mData.size(); i++) {
            ImageView addImg = new ImageView(context);
            addImg.setId(IdsUtils.generateViewId());
            addImg.setBackgroundResource(R.drawable.shape_4_grad_b_white);
            addImg.setLayoutParams(lp);
            addImg.setPadding(ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 5),
                    ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 5));
            Glide.with(context).load(mData.get(i)).apply(requestOptions).into(addImg);
            flowLayout.addView(addImg);
            posMap.put(addImg.getId(), i);
            addImg.setOnClickListener(listener);
        }
        if (mData.size() <= 5) {
            ImageView addImg = new ImageView(context);
            addImg.setId(IdsUtils.generateViewId());
            addImg.setBackgroundResource(R.drawable.shape_4_grad_b_white);
            addImg.setLayoutParams(lp);
            addImg.setPadding(ToolUtils.dip2px(context, 15), ToolUtils.dip2px(context, 15),
                    ToolUtils.dip2px(context, 15), ToolUtils.dip2px(context, 15));
            Glide.with(context).load(R.drawable.icon_add_back).into(addImg);
            flowLayout.addView(addImg);
            posMap.put(addImg.getId(), mData.size());
            addImg.setOnClickListener(listener);
        }
    }
}
