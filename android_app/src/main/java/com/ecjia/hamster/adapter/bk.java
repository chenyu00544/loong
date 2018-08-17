package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_FAVOUR;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaShopDetailFavoursAdapter */
public class bk extends BaseAdapter {
    public ArrayList<ECJia_FAVOUR> a = new ArrayList();
    private Context b;

    /* compiled from: ECJiaShopDetailFavoursAdapter */
    class a {
        TextView a;
        TextView b;
        final /* synthetic */ bk c;

        a(bk bkVar) {
            this.c = bkVar;
        }
    }

    public bk(Context context, ArrayList<ECJia_FAVOUR> arrayList) {
        this.a = arrayList;
        this.b = context;
    }

    public int getCount() {
        return this.a.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.item_shop_detail_favour, null);
            aVar.a = (TextView) view.findViewById(R.id.tv_favour_name);
            aVar.b = (TextView) view.findViewById(R.id.tv_favour_content);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((ECJia_FAVOUR) this.a.get(i)).getType_label());
        aVar.b.setText(((ECJia_FAVOUR) this.a.get(i)).getName());
        return view;
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }
}
