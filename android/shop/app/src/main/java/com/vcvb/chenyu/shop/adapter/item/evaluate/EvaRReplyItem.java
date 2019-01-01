package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.evaluate.Comment;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class EvaRReplyItem extends BaseItem<Comment> {
    public static final int TYPE = R.layout.evaluate_right_reply_item;
    public OnClickListener onClickListener;
    private int pos = 0;

    public EvaRReplyItem(Comment beans, Context c) {
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
        pos = position;
        RequestOptions requestOptions2 = RequestOptions.centerInsideTransform().diskCacheStrategy
                (DiskCacheStrategy.NONE).dontAnimate();
        TextView reply = holder.get(R.id.reply);
        reply.setText(context.getResources().getString(R.string.reply));

        TextView tv1 = holder.get(R.id.textView289);
        tv1.setText(mData.getUser_name());
        TextView tv2 = holder.get(R.id.textView290);
        tv2.setText(mData.getContent());
        FlowLayout flowLayout = holder.get(R.id.flowLayout2);
        int width = ToolUtils.getWindowsWidth(context);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width / 4 - ToolUtils.dip2px
                (context, 16), width / 4 - ToolUtils.dip2px(context, 16));
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.removeAllViews();
        for (int i = 0; i < mData.getCommentImgs().size(); i++) {
            ImageView photoView = new ImageView(context);
            photoView.setId(IdsUtils.generateViewId());
            photoView.setBackgroundResource(R.drawable.shape_4_grad_b_white);
            photoView.setLayoutParams(lp);
            photoView.setPadding(ToolUtils.dip2px(context, 2), ToolUtils.dip2px(context, 2),
                    ToolUtils.dip2px(context, 2), ToolUtils.dip2px(context, 2));
            Glide.with(context).load(mData.getCommentImgs().get(i).getComment_img()).apply
                    (requestOptions2).into(photoView);
            flowLayout.addView(photoView);
            posMap.put(photoView.getId(), i);
            photoView.setOnClickListener(listener);
        }
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public interface OnClickListener {
        void onClicked(View view, int pos, int p);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, posMap.get(view.getId()), pos);
            }
        }
    };
}
