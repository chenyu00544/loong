package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaPayListAdapter */
public class as extends BaseAdapter {
    public ArrayList<ECJia_PAYMENT> a = new ArrayList();
    private Context b;
    private b c = null;

    /* compiled from: ECJiaPayListAdapter */
    public interface b {
        void a(View view, int i);
    }

    /* compiled from: ECJiaPayListAdapter */
    class a {
        TextView a;
        View b;
        View c;
        LinearLayout d;
        final /* synthetic */ as e;

        a(as asVar) {
            this.e = asVar;
        }
    }

    public as(Context context, ArrayList<ECJia_PAYMENT> arrayList) {
        this.b = context;
        this.a = arrayList;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.choose_changepay_item, null);
            aVar.d = (LinearLayout) view.findViewById(R.id.pay_item);
            aVar.a = (TextView) view.findViewById(R.id.pay_text);
            aVar.b = view.findViewById(R.id.shortline);
            aVar.c = view.findViewById(R.id.longline);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((ECJia_PAYMENT) this.a.get(i)).getPay_name());
        aVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ as b;

            public void onClick(View view) {
                if (this.b.c != null) {
                    this.b.c.a(view, i);
                }
                this.b.notifyDataSetChanged();
            }
        });
        if (i < this.a.size() - 1) {
            aVar.c.setVisibility(8);
            aVar.b.setVisibility(0);
        } else {
            aVar.c.setVisibility(0);
            aVar.b.setVisibility(8);
        }
        return view;
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
