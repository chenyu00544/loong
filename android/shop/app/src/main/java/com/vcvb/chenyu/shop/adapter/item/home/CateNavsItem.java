package com.vcvb.chenyu.shop.adapter.item.home;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cate.Categroy;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.HashMap;
import java.util.List;

public class CateNavsItem extends BaseItem<List<Categroy>> {
    public static final int TYPE = R.layout.cate_navs_item;
    public HashMap<Integer, Integer> groupMap = new HashMap<>();
    public OnClickListener onClickListener;

    public CateNavsItem(List<Categroy> bean, Context c) {
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
        FlowLayout flowLayout = (FlowLayout) holder.getView(R.id.navs_wrap);
        flowLayout.removeAllViews();
        int spac = 5;
        if (mData.size() < 5) {
            spac = mData.size();
        }
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width / spac, width / 4);
        for (int i = 0; i < mData.size(); i++) {
            int id = IdsUtils.generateViewId();
            View v = LayoutInflater.from(context).inflate(R.layout.cate_navs_sub_item, null);
            v.setId(id);
            v.setLayoutParams(params);
            v.setOnClickListener(listener);
            posMap.put(id, i);
            groupMap.put(id, position);
            ImageView imageView = v.findViewById(R.id.imageView159);
            TextView textView = v.findViewById(R.id.textView316);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(mData.get(i).getTouch_icon()).into(imageView);
            textView.setText(mData.get(i).getCat_alias_name());
            flowLayout.addView(v);
        }
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public interface OnClickListener {
        void onClicked(View view, int pos, int group);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, posMap.get(view.getId()), groupMap.get(view.getId()));
            }
        }
    };
}
