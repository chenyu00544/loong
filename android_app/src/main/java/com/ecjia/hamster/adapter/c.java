package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaCategoryListAdapter */
public class c extends b {

    /* compiled from: ECJiaCategoryListAdapter */
    public class a extends com.ecjia.hamster.adapter.b.a {
        TextView c;
        ImageView d;
        View e;
        final /* synthetic */ c f;

        public a(c cVar) {
            this.f = cVar;
            super(cVar);
        }
    }

    public c(Context context, ArrayList arrayList) {
        super(context, arrayList);
    }

    protected com.ecjia.hamster.adapter.b.a a(View view) {
        com.ecjia.hamster.adapter.b.a aVar = new a(this);
        aVar.c = (TextView) view.findViewById(R.id.category_name);
        aVar.d = (ImageView) view.findViewById(R.id.right_arrow);
        aVar.e = view.findViewById(R.id.category_top);
        return aVar;
    }

    protected View a(int i, View view, ViewGroup viewGroup, com.ecjia.hamster.adapter.b.a aVar) {
        ECJia_CATEGORY eCJia_CATEGORY = (ECJia_CATEGORY) this.e.get(i);
        a aVar2 = (a) aVar;
        aVar2.c.setText(eCJia_CATEGORY.getName());
        if (i == 0) {
            aVar2.e.setVisibility(0);
        } else {
            aVar2.e.setVisibility(8);
        }
        if (eCJia_CATEGORY.getChildren().size() > 0) {
            aVar2.d.setVisibility(0);
        } else {
            aVar2.d.setVisibility(8);
        }
        return view;
    }

    public View a() {
        return this.c.inflate(R.layout.category_list_item, null);
    }
}
