package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsTransportExt;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

import java.util.List;

public class GoodsShipFreeItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = 7;

    public GoodsShipFreeItem(GoodsDetail beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_ship_free_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        TextView tv = holder.getTextView(R.id.textView1);
        String region_id = (String) UserInfoUtils.getInstance(context).getUserInfo().get
                ("region_id");
        String free = "￥0.00";
        List<GoodsTransportExt> exts = mData.getGoodsTransport().getExts();
        for (int i = 0; i < exts.size(); i++) {
            String topAreaId = exts.get(i).getTop_area_id();
            String[] tai = topAreaId.split(",");
            String areaId = exts.get(i).getArea_id();
            String[] ai = areaId.split(",");
            for (String str : ai) {
                if(str.equals(region_id)){
                    free = "￥" + exts.get(i).getSprice();
                }
            }
            for (String str : tai) {
                if(str.equals(region_id)){
                    free = "￥" + exts.get(i).getSprice();
                }
            }
        }
        tv.setText(free);

        View v = holder.getItemView();
        posMap.put(v.getId(), 0);
        v.setOnClickListener(listener);
    }
}
