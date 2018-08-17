package com.ecjia.hamster.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.hamster.model.bd;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaToplineAdapter */
public class ca extends BaseAdapter {
    protected p a;
    private Context b;
    private LayoutInflater c;
    private ArrayList<bd> d;

    /* compiled from: ECJiaToplineAdapter */
    class a {
        public LinearLayout a;
        public ImageView b;
        public TextView c;
        public TextView d;
        public TextView e;
        public View f;
        public View g;
        final /* synthetic */ ca h;

        a(ca caVar) {
            this.h = caVar;
        }
    }

    public ca(Context context, ArrayList<bd> arrayList) {
        this.b = context;
        this.d = arrayList;
        this.c = LayoutInflater.from(context);
        this.a = p.a(context);
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = this.c.inflate(R.layout.layout_topline_item, null);
            aVar.a = (LinearLayout) view.findViewById(R.id.item_topline);
            aVar.b = (ImageView) view.findViewById(R.id.item_topline_image);
            aVar.c = (TextView) view.findViewById(R.id.item_topline_tag);
            aVar.d = (TextView) view.findViewById(R.id.item_topline_time);
            aVar.e = (TextView) view.findViewById(R.id.item_topline_title);
            aVar.g = view.findViewById(R.id.item_topline_mid_line);
            aVar.f = view.findViewById(R.id.item_topline_bottom_line);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (TextUtils.isEmpty(((bd) this.d.get(i)).b())) {
            aVar.b.setVisibility(8);
        } else {
            aVar.b.setVisibility(0);
            p.a(this.b).a(aVar.b, ((bd) this.d.get(i)).b());
        }
        if (i == this.d.size() - 1 || this.d.size() == 1) {
            aVar.f.setVisibility(0);
            aVar.g.setVisibility(8);
        } else {
            aVar.g.setVisibility(0);
            aVar.f.setVisibility(8);
        }
        aVar.c.setText(((bd) this.d.get(i)).c());
        aVar.d.setText(((bd) this.d.get(i)).a());
        aVar.e.setText(((bd) this.d.get(i)).d());
        aVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ca b;

            public void onClick(View view) {
                com.ecjia.a.b.a.a().a(this.b.b, ((bd) this.b.d.get(i)).e());
            }
        });
        return view;
    }
}
