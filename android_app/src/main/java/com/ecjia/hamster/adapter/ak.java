package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.p;
import com.ecjia.hamster.model.ah;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaMyFindFragmentAdapter */
public class ak extends BaseAdapter {
    private Context a;
    private ArrayList<ah> b;

    /* compiled from: ECJiaMyFindFragmentAdapter */
    private class a {
        final /* synthetic */ ak a;
        private View b;
        private View c;
        private View d;
        private LinearLayout e;
        private ImageView f;
        private TextView g;

        private a(ak akVar) {
            this.a = akVar;
        }
    }

    public ak(Context context, ArrayList<ah> arrayList) {
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
        final ah ahVar = (ah) this.b.get(i);
        if (view == null) {
            a aVar2 = new a();
            view = LayoutInflater.from(this.a).inflate(R.layout.customfind_item, null);
            aVar2.e = (LinearLayout) view.findViewById(R.id.myfind_item);
            aVar2.f = (ImageView) view.findViewById(R.id.myfind_img);
            aVar2.g = (TextView) view.findViewById(R.id.myfind_text);
            aVar2.b = view.findViewById(R.id.find_top_long_line);
            aVar2.c = view.findViewById(R.id.find_top_short_line);
            aVar2.d = view.findViewById(R.id.find_buttom_line);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        p.a(this.a).a(aVar.f, ahVar.a());
        aVar.g.setText(ahVar.c());
        aVar.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ak b;

            public void onClick(View view) {
                com.ecjia.a.b.a.a().a(this.b.a, ahVar.b());
            }
        });
        if (this.b.size() == 1) {
            aVar.b.setVisibility(0);
            aVar.c.setVisibility(8);
            aVar.d.setVisibility(0);
        } else if (this.b.size() == 2) {
            if (i == 0) {
                aVar.b.setVisibility(0);
                aVar.c.setVisibility(8);
                aVar.d.setVisibility(8);
            } else {
                aVar.b.setVisibility(8);
                aVar.c.setVisibility(0);
                aVar.d.setVisibility(0);
            }
        } else if (i == 0) {
            aVar.b.setVisibility(0);
            aVar.c.setVisibility(8);
            aVar.d.setVisibility(8);
        } else if (i > 0 && i < this.b.size() - 1) {
            aVar.b.setVisibility(8);
            aVar.c.setVisibility(0);
            aVar.d.setVisibility(8);
        } else if (i == this.b.size() - 1) {
            aVar.b.setVisibility(8);
            aVar.c.setVisibility(0);
            aVar.d.setVisibility(0);
        }
        return view;
    }
}
