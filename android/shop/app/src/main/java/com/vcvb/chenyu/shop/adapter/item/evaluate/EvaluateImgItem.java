package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.evaluate.EvaImage;
import com.vcvb.chenyu.shop.javaBean.evaluate.EvaluateGroup;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class EvaluateImgItem extends BaseItem<EvaluateGroup> {
    public static final int TYPE = R.layout.evaluate_img_item;
    private FlowLayout flowLayout;

    public EvaluateImgItem(EvaluateGroup bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        int width = ToolUtils.getWindowsWidth(context);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width / 4 - ToolUtils.dip2px
                (context, 16), width / 4 - ToolUtils.dip2px(context, 16));
        flowLayout = holder.get(R.id.return_img);
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.removeAllViews();
        EvaImage evaImage = (EvaImage) mData.getObjs().get(position);
        RequestOptions requestOptions = RequestOptions.centerInsideTransform().diskCacheStrategy
                (DiskCacheStrategy.NONE).dontAnimate();
        for (int i = 0; i < evaImage.getImgs().size(); i++) {
            ImageView addImg = new ImageView(context);
            addImg.setId(IdsUtils.generateViewId());
            addImg.setBackgroundResource(R.drawable.shape_4_grad_b_white);
            addImg.setLayoutParams(lp);
            addImg.setPadding(ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 5),
                    ToolUtils.dip2px(context, 5), ToolUtils.dip2px(context, 5));
            Glide.with(context).load(evaImage.getImgs().get(i)).apply(requestOptions).into(addImg);
            flowLayout.addView(addImg);
            posMap.put(addImg.getId(), i);
            groupMap.put(addImg.getId(), groupPosition);
            addImg.setOnClickListener(listener);
        }
        if (evaImage.getImgs().size() <= 5) {
            ImageView addImg = new ImageView(context);
            addImg.setId(IdsUtils.generateViewId());
            addImg.setBackgroundResource(R.drawable.shape_4_grad_b_white);
            addImg.setLayoutParams(lp);
            addImg.setPadding(ToolUtils.dip2px(context, 15), ToolUtils.dip2px(context, 15),
                    ToolUtils.dip2px(context, 15), ToolUtils.dip2px(context, 15));
            Glide.with(context).load(R.drawable.icon_add_back).into(addImg);
            flowLayout.addView(addImg);
            posMap.put(addImg.getId(), evaImage.getImgs().size());
            groupMap.put(addImg.getId(), groupPosition);
            addImg.setOnClickListener(listener);
        }
    }
}
