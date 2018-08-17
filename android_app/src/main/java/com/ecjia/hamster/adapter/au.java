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

/* compiled from: ECJiaPopupListAdapter */
public class au extends BaseAdapter {
    private Context a;
    private ArrayList<ECJia_CATEGORY> b;

    /* compiled from: ECJiaPopupListAdapter */
    class a {
        final /* synthetic */ au a;
        private TextView b;
        private View c;

        a(au auVar) {
            this.a = auVar;
        }
    }

    public au(Context context, ArrayList<ECJia_CATEGORY> arrayList) {
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
        ECJia_CATEGORY eCJia_CATEGORY = (ECJia_CATEGORY) this.b.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.shoplist_headview_item, null);
            aVar = new a(this);
            aVar.c = view.findViewById(R.id.select_view);
            aVar.b = (TextView) view.findViewById(R.id.headview_category_name);
            aVar.b.setTextSize(14.0f);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(((ECJia_CATEGORY) this.b.get(i)).getName());
        if (eCJia_CATEGORY.isIschecked()) {
            aVar.c.setVisibility(8);
            aVar.b.setTextColor(this.a.getResources().getColor(R.color.public_theme_color_normal));
        } else {
            aVar.c.setVisibility(8);
            aVar.b.setTextColor(this.a.getResources().getColor(R.color.TextColorGray));
        }
        return view;
    }
}
