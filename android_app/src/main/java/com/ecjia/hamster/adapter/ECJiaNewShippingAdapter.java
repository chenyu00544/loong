package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ecjia.hamster.model.ECJia_SHIPPING;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

public class ECJiaNewShippingAdapter extends BaseAdapter {
    private ArrayList<ECJia_SHIPPING> a;
    private Context b;
    private Resources c;
    private a d = null;

    static class ViewHolder {
        @BindView(2131558891)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }

    public interface a {
        void a(View view, int i);
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public ECJiaNewShippingAdapter(Context context, ArrayList<ECJia_SHIPPING> arrayList) {
        this.b = context;
        this.a = arrayList;
        this.c = context.getResources();
    }

    public int getCount() {
        return this.a.size();
    }

    public ECJia_SHIPPING a(int i) {
        return (ECJia_SHIPPING) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ECJia_SHIPPING eCJia_SHIPPING = (ECJia_SHIPPING) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.common_gridview_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(eCJia_SHIPPING.getShipping_name());
        if (eCJia_SHIPPING.isSelected()) {
            viewHolder.tvName.setTextColor(this.c.getColor(R.color.my_red));
            viewHolder.tvName.setBackgroundResource(R.drawable.shape_red_withsto);
        } else {
            viewHolder.tvName.setTextColor(this.c.getColor(R.color.new_textColor));
            viewHolder.tvName.setBackgroundResource(R.drawable.shape_gray_withsto);
        }
        viewHolder.tvName.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ECJiaNewShippingAdapter b;

            public void onClick(View view) {
                if (this.b.d != null) {
                    this.b.d.a(view, i);
                }
            }
        });
        return view;
    }

    public void a(a aVar) {
        this.d = aVar;
    }
}
