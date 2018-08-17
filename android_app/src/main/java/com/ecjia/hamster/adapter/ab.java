package com.ecjia.hamster.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.hamster.model.v;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaInvoiceTypeAdapter */
public class ab extends BaseAdapter {
    public int a = -1;
    public String b;
    private Context c;
    private List<v> d;
    private LayoutInflater e;

    /* compiled from: ECJiaInvoiceTypeAdapter */
    class a {
        final /* synthetic */ ab a;
        private TextView b;
        private ImageView c;
        private View d;
        private View e;
        private View f;

        a(ab abVar) {
            this.a = abVar;
        }
    }

    public ab(Context context, List<v> list, String str) {
        this.c = context;
        this.d = list;
        this.e = LayoutInflater.from(context);
        this.a = -1;
        this.b = str;
    }

    public int getCount() {
        return this.d.size();
    }

    public Object getItem(int i) {
        return this.d.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = this.e.inflate(R.layout.invoice_item, null);
            aVar.b = (TextView) view.findViewById(R.id.invoice_item_text);
            aVar.c = (ImageView) view.findViewById(R.id.invoice_item_select);
            aVar.d = view.findViewById(R.id.invoice_top);
            aVar.e = view.findViewById(R.id.invoice_buttom);
            aVar.f = view.findViewById(R.id.invoice_middle_top);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (this.d.size() == 1) {
            aVar.d.setVisibility(0);
            aVar.e.setVisibility(0);
        } else if (i == 0) {
            aVar.d.setVisibility(0);
            aVar.e.setVisibility(8);
        } else if (i == this.d.size() - 1) {
            aVar.e.setVisibility(0);
            aVar.f.setVisibility(0);
        } else {
            aVar.f.setVisibility(0);
        }
        aVar.b.setText(((v) this.d.get(i)).c());
        if (this.a != -1) {
            if (this.a == i) {
                aVar.c.setBackgroundResource(R.drawable.payment_selected);
            } else {
                aVar.c.setBackgroundResource(R.drawable.payment_unselected);
            }
        } else if (TextUtils.isEmpty(this.b)) {
            aVar.c.setBackgroundResource(R.drawable.payment_unselected);
        } else if (((v) this.d.get(i)).a().equals(this.b)) {
            aVar.c.setBackgroundResource(R.drawable.payment_selected);
        } else {
            aVar.c.setBackgroundResource(R.drawable.payment_unselected);
        }
        return view;
    }
}
