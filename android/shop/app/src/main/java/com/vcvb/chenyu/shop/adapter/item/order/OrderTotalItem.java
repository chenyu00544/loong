package com.vcvb.chenyu.shop.adapter.item.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;

import java.util.List;
import java.util.Locale;

public class OrderTotalItem extends BaseItem<List<OrderDetail>> {
    public static final int TYPE = R.layout.order_details_total_item;

    public OrderTotalItem(List<OrderDetail> bean, Context c) {
        super(bean, c);
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
        TextView tv1 = holder.getTextView(R.id.textView201);
        TextView tv2 = holder.getTextView(R.id.textView202);
        TextView tv3 = holder.getTextView(R.id.textView203);
        TextView tv4 = holder.getTextView(R.id.textView245);
        TextView tv5 = holder.getTextView(R.id.textView204);

        Double goods_amount = 0.0;
        Double shipping_fee = 0.0;
        Double tax = 0.0;
        Double discount = 0.0;
        Double pay_total = 0.0;

        for (int i = 0; i < mData.size(); i++) {
            goods_amount += Double.valueOf(mData.get(i).getGoods_amount());
            shipping_fee += Double.valueOf(mData.get(i).getShipping_fee());
            tax += Double.valueOf(mData.get(i).getTax());
            discount += Double.valueOf(mData.get(i).getDiscount());
            pay_total += goods_amount+shipping_fee+tax-discount;
        }

        String goods_amount_str = "￥%f";
        tv1.setText(String.format(Locale.CHINA, goods_amount_str, goods_amount));
        String shipping_fee_str = "+ ￥%f";
        tv2.setText(String.format(Locale.CHINA, shipping_fee_str, shipping_fee));
        String tax_str = "+ ￥%f";
        tv3.setText(String.format(Locale.CHINA, tax_str, tax));
        String discount_str = "- ￥%f";
        tv4.setText(String.format(Locale.CHINA, discount_str, discount));
        String pay_total_str = "￥%f";
        tv5.setText(String.format(Locale.CHINA, pay_total_str, pay_total));
    }
}
