package com.ecjia.hamster.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ecjia.component.view.ECJiaHorizontalListView;
import com.ecjia.hamster.activity.ECJiaShopGoodsActivity;
import com.ecjia.hamster.model.ECJia_SHIPPING;
import com.ecjia.hamster.model.ECJia_STOREGOODSLIST;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

public class ECJiaShippingDetailAdapter extends BaseAdapter {
    private ArrayList<ECJia_STOREGOODSLIST> a = new ArrayList();
    private Context b;
    private Resources c;

    static class ViewHolder {
        @BindView(2131558831)
        ECJiaHorizontalListView balanceHorilistview;
        @BindView(2131559133)
        View bottomLine;
        @BindView(2131560069)
        View bottomShortLine;
        @BindView(2131560067)
        TextView tvRuName;
        @BindView(2131560282)
        TextView tvRuShippingFee;

        ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public ECJiaShippingDetailAdapter(Context context, ArrayList<ECJia_STOREGOODSLIST> arrayList) {
        this.b = context;
        this.a = arrayList;
        this.c = context.getResources();
    }

    public int getCount() {
        return this.a.size();
    }

    public ECJia_STOREGOODSLIST a(int i) {
        return (ECJia_STOREGOODSLIST) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        int i2 = 0;
        final ECJia_STOREGOODSLIST eCJia_STOREGOODSLIST = (ECJia_STOREGOODSLIST) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.shipping_detail_item, null);
            ViewHolder viewHolder2 = new ViewHolder(view);
            view.setTag(viewHolder2);
            viewHolder = viewHolder2;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i == this.a.size() - 1) {
            viewHolder.bottomShortLine.setVisibility(8);
            viewHolder.bottomLine.setVisibility(0);
        } else {
            viewHolder.bottomShortLine.setVisibility(0);
            viewHolder.bottomLine.setVisibility(8);
        }
        viewHolder.tvRuName.setText(eCJia_STOREGOODSLIST.getRu_name());
        viewHolder.balanceHorilistview.setAdapter(new r(this.b, eCJia_STOREGOODSLIST.getGoods_list()));
        viewHolder.balanceHorilistview.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ECJiaShippingDetailAdapter b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.b, ECJiaShopGoodsActivity.class);
                intent.putExtra("goods_list", eCJia_STOREGOODSLIST.getGoods_list());
                this.b.b.startActivity(intent);
                ((Activity) this.b.b).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        viewHolder.tvRuShippingFee.setText("");
        while (i2 < eCJia_STOREGOODSLIST.getShipping_list().size()) {
            if (((ECJia_SHIPPING) eCJia_STOREGOODSLIST.getShipping_list().get(i2)).isSelected()) {
                viewHolder.tvRuShippingFee.setText(this.c.getString(R.string.shipping_fee) + ":" + ((ECJia_SHIPPING) eCJia_STOREGOODSLIST.getShipping_list().get(i2)).getFormat_shipping_fee());
                break;
            }
            i2++;
        }
        return view;
    }
}
