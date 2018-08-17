package com.ecjia.hamster.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ecjia.component.view.ECJiaHorizontalListView;
import com.ecjia.component.view.ECJiaMyGridView;
import com.ecjia.hamster.activity.ECJiaShopGoodsActivity;
import com.ecjia.hamster.model.ECJia_STOREGOODSLIST;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

public class ECJiaStoreGoodsListAdapter extends BaseAdapter {
    private ArrayList<ECJia_STOREGOODSLIST> a = new ArrayList();
    private Context b;
    private a c = null;

    public interface a {
        void a(View view, int i, int i2);
    }

    static class ViewHolder {
        @BindView(2131558831)
        ECJiaHorizontalListView balanceHorilistview;
        @BindView(2131559133)
        View bottomLine;
        @BindView(2131560069)
        View bottomShortLine;
        @BindView(2131560068)
        ECJiaMyGridView mgvShipping;
        @BindView(2131560067)
        TextView tvRuName;

        ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public ECJiaStoreGoodsListAdapter(Context context, ArrayList<ECJia_STOREGOODSLIST> arrayList) {
        this.b = context;
        this.a = arrayList;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final ECJia_STOREGOODSLIST eCJia_STOREGOODSLIST = (ECJia_STOREGOODSLIST) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.new_shipping_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
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
            final /* synthetic */ ECJiaStoreGoodsListAdapter b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.b, ECJiaShopGoodsActivity.class);
                intent.putExtra("goods_list", eCJia_STOREGOODSLIST.getGoods_list());
                this.b.b.startActivity(intent);
                ((Activity) this.b.b).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        Object eCJiaNewShippingAdapter = new ECJiaNewShippingAdapter(this.b, eCJia_STOREGOODSLIST.getShipping_list());
        viewHolder.mgvShipping.setAdapter(eCJiaNewShippingAdapter);
        eCJiaNewShippingAdapter.a(new com.ecjia.hamster.adapter.ECJiaNewShippingAdapter.a(this) {
            final /* synthetic */ ECJiaStoreGoodsListAdapter b;

            public void a(View view, int i) {
                if (this.b.c != null) {
                    this.b.c.a(view, i, i);
                }
            }
        });
        return view;
    }

    public void a(a aVar) {
        this.c = aVar;
    }
}
