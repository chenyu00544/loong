package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.i;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaHotCityAdapter */
public class z extends BaseAdapter {
    private Context a;
    private ArrayList<i> b;

    /* compiled from: ECJiaHotCityAdapter */
    private class a {
        TextView a;
        final /* synthetic */ z b;

        private a(z zVar) {
            this.b = zVar;
        }
    }

    public z(Context context, ArrayList<i> arrayList) {
        this.a = context;
        this.b = arrayList;
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
            a aVar2 = new a();
            view = LayoutInflater.from(this.a).inflate(R.layout.hotcity_item, null);
            aVar2.a = (TextView) view.findViewById(R.id.city_text);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((i) this.b.get(i)).b());
        return view;
    }
}
