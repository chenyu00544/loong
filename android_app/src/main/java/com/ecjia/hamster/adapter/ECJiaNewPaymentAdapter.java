package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

public class ECJiaNewPaymentAdapter extends BaseAdapter {
    private ArrayList<ECJia_PAYMENT> a;
    private Context b;
    private Resources c;

    static class ViewHolder {
        @BindView(2131558891)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public ECJiaNewPaymentAdapter(Context context, ArrayList<ECJia_PAYMENT> arrayList) {
        this.b = context;
        this.a = arrayList;
        this.c = context.getResources();
    }

    public int getCount() {
        return this.a.size();
    }

    public ECJia_PAYMENT a(int i) {
        return (ECJia_PAYMENT) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ECJia_PAYMENT eCJia_PAYMENT = (ECJia_PAYMENT) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.common_gridview_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(eCJia_PAYMENT.getPay_name());
        if (eCJia_PAYMENT.isSelected()) {
            viewHolder.tvName.setTextColor(this.c.getColor(R.color.my_red));
            viewHolder.tvName.setBackgroundResource(R.drawable.shape_red_withsto);
        } else {
            viewHolder.tvName.setTextColor(this.c.getColor(R.color.new_textColor));
            viewHolder.tvName.setBackgroundResource(R.drawable.shape_gray_withsto);
        }
        return view;
    }
}
