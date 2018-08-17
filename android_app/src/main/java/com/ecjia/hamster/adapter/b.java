package com.ecjia.hamster.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* compiled from: ECJiaBaseAdapter */
public abstract class b<T> extends BaseAdapter {
    protected LayoutInflater c = null;
    protected Context d;
    public ArrayList<T> e = new ArrayList();

    /* compiled from: ECJiaBaseAdapter */
    public class a {
        public int a;
        final /* synthetic */ b b;

        public a(b bVar) {
            this.b = bVar;
        }
    }

    public abstract View a();

    protected abstract View a(int i, View view, ViewGroup viewGroup, a aVar);

    protected abstract a a(View view);

    public b(Context context, ArrayList<T> arrayList) {
        this.d = context;
        this.c = LayoutInflater.from(context);
        this.e = arrayList;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public int getCount() {
        return this.e != null ? this.e.size() : 0;
    }

    public Object getItem(int i) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return this.e.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a a;
        if (view == null) {
            view = a();
            a = a(view);
            if (a != null) {
                view.setTag(a);
            }
        } else {
            a = (a) view.getTag();
            if (a == null) {
                Log.v("lottery", "error");
            }
        }
        if (a != null) {
            a.a = i;
        }
        a(i, view, viewGroup, a);
        return view;
    }
}
