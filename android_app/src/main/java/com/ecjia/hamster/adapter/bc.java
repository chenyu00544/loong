package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaSearchHistoryNewAdapter */
public class bc extends BaseAdapter {
    public List<String> a;
    private Context b;
    private LayoutInflater c;

    /* compiled from: ECJiaSearchHistoryNewAdapter */
    class a {
        TextView a;
        View b;
        View c;
        final /* synthetic */ bc d;

        a(bc bcVar) {
            this.d = bcVar;
        }
    }

    public bc(List<String> list, Context context) {
        this.a = list;
        this.b = context;
        this.c = LayoutInflater.from(context);
    }

    public void a(List<String> list) {
        this.a = list;
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

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        String str = (String) this.a.get(i);
        if (view == null) {
            a aVar2 = new a(this);
            view = this.c.inflate(R.layout.search_history_new_item, null);
            aVar2.a = (TextView) view.findViewById(R.id.history_name);
            aVar2.b = view.findViewById(R.id.longline);
            aVar2.c = view.findViewById(R.id.shortline);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (i == this.a.size() - 1) {
            aVar.b.setVisibility(0);
            aVar.c.setVisibility(8);
        } else {
            aVar.b.setVisibility(8);
            aVar.c.setVisibility(0);
        }
        aVar.a.setText(str);
        return view;
    }
}
