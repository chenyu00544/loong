package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaShoplistCategoryAdapter */
public class bp extends BaseAdapter {
    private ArrayList<ECJia_CATEGORY> a = new ArrayList();
    private Context b;

    /* compiled from: ECJiaShoplistCategoryAdapter */
    private class a {
        final /* synthetic */ bp a;
        private TextView b;
        private View c;

        private a(bp bpVar) {
            this.a = bpVar;
        }
    }

    public bp(Context context, ArrayList<ECJia_CATEGORY> arrayList) {
        this.a = arrayList;
        this.b = context;
    }

    public void a(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (i2 == i) {
                ((ECJia_CATEGORY) this.a.get(i2)).setIschecked(true);
            } else {
                ((ECJia_CATEGORY) this.a.get(i2)).setIschecked(false);
            }
        }
    }

    public ArrayList<ECJia_CATEGORY> a() {
        return this.a;
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
        ECJia_CATEGORY eCJia_CATEGORY = (ECJia_CATEGORY) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.shoplist_headview_item, null);
            aVar = new a();
            aVar.c = view.findViewById(R.id.select_view);
            aVar.b = (TextView) view.findViewById(R.id.headview_category_name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(((ECJia_CATEGORY) this.a.get(i)).getName());
        if (eCJia_CATEGORY.isIschecked()) {
            aVar.b.setTextColor(this.b.getResources().getColor(R.color.public_theme_color_normal));
            aVar.c.setVisibility(0);
        } else {
            aVar.c.setVisibility(8);
            aVar.b.setTextColor(this.b.getResources().getColor(R.color.TextColorGray));
        }
        return view;
    }
}
