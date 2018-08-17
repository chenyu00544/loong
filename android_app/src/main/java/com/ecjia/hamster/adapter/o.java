package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ecjia.hamster.model.ag;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaGoodPropertyAdapter */
public class o extends b {

    /* compiled from: ECJiaGoodPropertyAdapter */
    public class a extends com.ecjia.hamster.adapter.b.a {
        TextView c;
        TextView d;
        View e;
        View f;
        View g;
        final /* synthetic */ o h;

        public a(o oVar) {
            this.h = oVar;
            super(oVar);
        }
    }

    public o(Context context, ArrayList arrayList) {
        super(context, arrayList);
    }

    protected com.ecjia.hamster.adapter.b.a a(View view) {
        com.ecjia.hamster.adapter.b.a aVar = new a(this);
        aVar.c = (TextView) view.findViewById(R.id.property_name);
        aVar.d = (TextView) view.findViewById(R.id.property_value);
        aVar.e = view.findViewById(R.id.pro_top);
        aVar.g = view.findViewById(R.id.bottom_line);
        aVar.f = view.findViewById(R.id.normal_line);
        return aVar;
    }

    protected View a(int i, View view, ViewGroup viewGroup, com.ecjia.hamster.adapter.b.a aVar) {
        ag agVar = (ag) this.e.get(i);
        ((a) aVar).c.setText(agVar.a());
        ((a) aVar).d.setText(agVar.b());
        if (i == 0) {
            ((a) aVar).e.setVisibility(0);
        } else {
            ((a) aVar).e.setVisibility(8);
        }
        if (this.e.size() == 1 || i == this.e.size() - 1) {
            ((a) aVar).g.setVisibility(0);
            ((a) aVar).f.setVisibility(8);
        } else {
            ((a) aVar).g.setVisibility(8);
            ((a) aVar).f.setVisibility(0);
        }
        return view;
    }

    public View a() {
        return LayoutInflater.from(this.d).inflate(R.layout.good_property_cell, null);
    }
}
