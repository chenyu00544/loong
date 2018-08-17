package com.ecjia.hamster.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.hamster.model.ah;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaFoundFragmentAdapter */
public class l extends BaseAdapter {
    ArrayList<ah> a;
    Context b;
    LayoutParams c;
    int d;

    /* compiled from: ECJiaFoundFragmentAdapter */
    class a {
        public View a;
        public TextView b;
        public ImageView c;
        public ImageView d;
        public View e;
        final /* synthetic */ l f;

        a(l lVar) {
            this.f = lVar;
        }
    }

    public l(Context context, ArrayList<ah> arrayList) {
        this.b = context;
        this.d = ((Activity) this.b).getWindowManager().getDefaultDisplay().getWidth();
        this.c = new LayoutParams(-1, -1);
        this.c.height = this.d / 3;
        this.a = arrayList;
    }

    public int getCount() {
        if (this.a.size() <= 0 || this.a.size() % 3 == 0) {
            return this.a.size();
        }
        return ((this.a.size() / 3) + 1) * 3;
    }

    public Object getItem(int i) {
        if (i >= this.a.size()) {
            return this.a.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (null == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.b).inflate(R.layout.layout_foundadapter_item, null);
            aVar.a = view.findViewById(R.id.found_item);
            aVar.c = (ImageView) view.findViewById(R.id.found_item_image);
            aVar.b = (TextView) view.findViewById(R.id.found_item_name);
            aVar.d = (ImageView) view.findViewById(R.id.found_item_add);
            aVar.e = view.findViewById(R.id.found_item_rightline);
            view.setLayoutParams(this.c);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (i < this.a.size()) {
            if (i != this.a.size()) {
                aVar.d.setVisibility(8);
                aVar.b.setVisibility(0);
                aVar.c.setVisibility(0);
                p.a(this.b).a(aVar.c, ((ah) this.a.get(i)).a());
                aVar.b.setText(((ah) this.a.get(i)).c());
                aVar.a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ l b;

                    public void onClick(View view) {
                        com.ecjia.a.b.a.a().a(this.b.b, ((ah) this.b.a.get(i)).b());
                    }
                });
                if (i % 3 == 2) {
                    aVar.e.setVisibility(8);
                }
            } else {
                aVar.d.setVisibility(0);
                aVar.b.setVisibility(8);
                aVar.c.setVisibility(8);
            }
        }
        return view;
    }
}
