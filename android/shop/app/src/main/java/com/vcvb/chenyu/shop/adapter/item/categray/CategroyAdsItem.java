package com.vcvb.chenyu.shop.adapter.item.categray;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyBean;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyGroup;

public class CategroyAdsItem extends BaseItem<CategroyGroup> {
    public static final int TYPE = R.layout.categroy_ads_item;

    public CategroyAdsItem(CategroyGroup bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        BaseViewHolder base = new BaseViewHolder(LayoutInflater.from(context)
                .inflate(TYPE, null));
        return base;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        CategroyBean bean = (CategroyBean) mData.getObjs().get(position);
        ImageView iv = holder.get(R.id.imageView126);
//        Glide.with(context).load(bean.getPic()).into(iv);
    }
}
