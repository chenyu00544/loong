package com.ecjia.hamster.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.component.view.ECJiaDashedLineView;
import com.ecjia.hamster.model.m;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaExpressAdapter */
public class j extends BaseAdapter {
    public ArrayList<m> a;
    public Context b;
    public String[] c;

    /* compiled from: ECJiaExpressAdapter */
    class a {
        TextView a;
        TextView b;
        TextView c;
        ImageView d;
        ECJiaDashedLineView e;
        ECJiaDashedLineView f;
        final /* synthetic */ j g;

        a(j jVar) {
            this.g = jVar;
        }
    }

    public j(Context context, ArrayList<m> arrayList) {
        this.b = context;
        this.a = arrayList;
    }

    public int getCount() {
        if (this.a.size() == 0 || this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object getItem(int i) {
        if (this.a.size() == 0 || this.a == null) {
            return null;
        }
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.a.size() == 0 || this.a == null) {
            return null;
        }
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = View.inflate(this.b, R.layout.kuaidi_item, null);
            aVar.b = (TextView) view.findViewById(R.id.log_date);
            aVar.a = (TextView) view.findViewById(R.id.log_time);
            aVar.c = (TextView) view.findViewById(R.id.log_text);
            aVar.e = (ECJiaDashedLineView) view.findViewById(R.id.dashedlin_top);
            aVar.f = (ECJiaDashedLineView) view.findViewById(R.id.dashedlin_bottom);
            aVar.d = (ImageView) view.findViewById(R.id.img_status2);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        this.c = ((m) this.a.get(i)).a().split(" ");
        aVar.c.setText(((m) this.a.get(i)).b());
        aVar.b.setText(this.c[0]);
        aVar.a.setText(this.c[1]);
        if (i == 0) {
            aVar.e.setVisibility(4);
            aVar.f.setVisibility(0);
            aVar.c.setTextColor(Color.parseColor("#000000"));
            aVar.b.setTextColor(Color.parseColor("#aaaaaa"));
            aVar.a.setTextColor(Color.parseColor("#aaaaaa"));
            return view;
        }
        aVar.e.setVisibility(0);
        if (i == this.a.size() - 1) {
            aVar.f.setVisibility(4);
        } else {
            aVar.f.setVisibility(0);
        }
        aVar.c.setTextColor(Color.parseColor("#999999"));
        aVar.b.setTextColor(Color.parseColor("#aaaaaa"));
        aVar.a.setTextColor(Color.parseColor("#aaaaaa"));
        return view;
    }
}
