package com.vcvb.chenyu.shop.adapter.item.goods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsDetail;
import com.vcvb.chenyu.shop.tools.UserInfoUtils;

public class GoodsShipItem extends BaseItem<GoodsDetail> {
    public static final int TYPE = 6;

    public GoodsShipItem(GoodsDetail beans, Context c) {
        super(beans, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CYCBaseViewHolder base = new CYCBaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_ship_item, null));
        return base;
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        View v = holder.getItemView();
        v.setOnClickListener(listener);
        posMap.put(v.getId(), 0);

        TextView address = holder.getTextView(R.id.textView1);
        TextView remarks = holder.getTextView(R.id.textView241);
        String addressStr = (String) UserInfoUtils.getInstance(context).getUserInfo().get
                ("formatted_address");
        if (addressStr == null || addressStr == "") {
            address.setText("请添加收货地址");
        } else {
            address.setText("至 " + addressStr);
        }

        remarks.setText(mData.getGoodsTransport().getRemarks());


        ImageView fromIv = holder.getImageView(R.id.imageView5);
        RequestOptions requestOptions = RequestOptions.circleCropTransform().diskCacheStrategy
                (DiskCacheStrategy.AUTOMATIC).override(120, 120);
        String from_en = mData.getGoodsCountryCAttr().getAttr_value();
        String fromLoc = "海外直接采购";
        Integer cPic = R.drawable.c_au;
        if (from_en.equals("au")) {
            cPic = R.drawable.c_au;
        } else if (from_en.equals("de")) {
            cPic = R.drawable.c_de;
        } else if (from_en.equals("fr")) {
            cPic = R.drawable.c_fr;
        } else if (from_en.equals("jp")) {
            cPic = R.drawable.c_jp;
        } else if (from_en.equals("sk")) {
            cPic = R.drawable.c_sk;
        } else if (from_en.equals("th")) {
            cPic = R.drawable.c_th;
        } else if (from_en.equals("uf")) {
            cPic = R.drawable.c_uf;
        } else if (from_en.equals("us")) {
            cPic = R.drawable.c_us;
        }
        Glide.with(context).load(cPic).apply(requestOptions).into(fromIv);

        TextView from = holder.getTextView(R.id.textView7);
        from.setText(fromLoc);
        TextView to = holder.getTextView(R.id.textView8);
        String toLoc = (String) UserInfoUtils.getInstance(context).getUserInfo().get("region_name");
        to.setText(toLoc);
    }
}
