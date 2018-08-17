package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.ak;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaSpinnerAdapter */
public class bt extends BaseAdapter {
    private Context a;
    private List<ak> b;
    private LayoutInflater c;

    /* compiled from: ECJiaSpinnerAdapter */
    class a {
        final /* synthetic */ bt a;
        private TextView b;

        a(bt btVar) {
            this.a = btVar;
        }
    }

    public bt(Context context, List<ak> list) {
        this.a = context;
        this.b = list;
        this.c = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = this.c.inflate(R.layout.city_item, null);
            aVar.b = (TextView) view.findViewById(R.id.city_item_name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(((ak) this.b.get(i)).b());
        return view;
    }
}
