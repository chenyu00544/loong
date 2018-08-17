package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaPaymentAdapter */
public class at extends BaseAdapter {
    com.ecjia.hamster.b.a a;
    private Context b;
    private List<ECJia_PAYMENT> c;
    private int d;

    /* compiled from: ECJiaPaymentAdapter */
    class a {
        final /* synthetic */ at a;
        private TextView b;
        private LinearLayout c;
        private ImageView d;

        a(at atVar) {
            this.a = atVar;
        }
    }

    public at(Context context, List<ECJia_PAYMENT> list, int i) {
        this.b = context;
        this.c = list;
        this.d = i;
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.payment_item, null);
            aVar.b = (TextView) view.findViewById(R.id.payment_item_name);
            aVar.c = (LinearLayout) view.findViewById(R.id.payment_item_layout);
            aVar.d = (ImageView) view.findViewById(R.id.payment_status);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ at b;

            public void onClick(View view) {
                if (this.b.a != null) {
                    this.b.a();
                    ((ECJia_PAYMENT) this.b.c.get(i)).setSelected(true);
                    this.b.notifyDataSetChanged();
                    this.b.a.a(this.b.d, i, this.b.c.get(i));
                }
            }
        });
        aVar.b.setText(((ECJia_PAYMENT) this.c.get(i)).getPay_name());
        if (((ECJia_PAYMENT) this.c.get(i)).isSelected()) {
            aVar.d.setBackgroundResource(R.drawable.payment_selected);
        } else {
            aVar.d.setBackgroundResource(R.drawable.payment_unselected);
        }
        return view;
    }

    void a() {
        for (int i = 0; i < this.c.size(); i++) {
            ((ECJia_PAYMENT) this.c.get(i)).setSelected(false);
        }
    }

    public void a(com.ecjia.hamster.b.a aVar) {
        this.a = aVar;
    }
}
