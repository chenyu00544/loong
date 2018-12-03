package com.vcvb.chenyu.shop.adapter.item.categray;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyBean;
import com.vcvb.chenyu.shop.javaBean.cate.CategroyGroup;

public class CategroySubTitleItem extends BaseItem<CategroyGroup>{
    public static final int TYPE = R.layout.categroy_subtitle_item;

    public CategroySubTitleItem(CategroyGroup bean, Context c) {
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
        TextView tv = holder.get(R.id.textView100);
        CategroyBean bean = (CategroyBean) mData.getHeader();
        tv.setText(bean.getCateName());
    }
}
