package com.vcvb.chenyu.shop.adapter.item.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.home.Adses;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;
import com.wangjie.shadowviewhelper.ShadowProperty;
import com.wangjie.shadowviewhelper.ShadowViewHelper;

public class HomeNavsItem extends BaseItem<Adses> {
    public static final int TYPE = 1;

    public HomeNavsItem(Adses bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .home_navs_item, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        int width = context.getResources().getDisplayMetrics().widthPixels - ToolUtils.dip2px
                (context, 12);

        ShadowViewHelper.bindShadowHelper(new ShadowProperty().setShadowRadius(ToolUtils.dip2px
                (context, 3)).setShadowColor(0xAA965456), holder.getView(R.id.view67));
        FlowLayout flowLayout = (FlowLayout) holder.getView(R.id.navs_wrap);
        flowLayout.removeAllViews();
        int spac = 5;
        if (mData.getAds().size() < 5) {
            spac = mData.getAds().size();
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width / spac, width / 4);
        Integer[] ids = new Integer[]{R.drawable.icon_navs_cosmetics, R.drawable.icon_navs_milk,
                R.drawable.icon_navs_nutrition, R.drawable.icon_navs_extravagant, R.drawable
                .icon_navs_clothes};
        for (int i = 0; i < 5; i++) {
            int id = IdsUtils.generateViewId();
            ImageView imageView = new ImageView(context);
            imageView.setId(id);
            posMap.put(id, i);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(listener);
            Glide.with(context).load(ids[i % 5]).into(imageView);
            flowLayout.addView(imageView);
        }
    }
}
