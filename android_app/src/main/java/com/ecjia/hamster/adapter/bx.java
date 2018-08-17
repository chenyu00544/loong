package com.ecjia.hamster.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.bc;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaTopicDetailPopAdapter */
public class bx extends BaseAdapter {
    public ArrayList<bc> a;
    private Context b;
    private int c;
    private b d = null;

    /* compiled from: ECJiaTopicDetailPopAdapter */
    public interface b {
        void a(View view, int i);
    }

    /* compiled from: ECJiaTopicDetailPopAdapter */
    private class a {
        final /* synthetic */ bx a;
        private TextView b;
        private View c;
        private View d;

        private a(bx bxVar) {
            this.a = bxVar;
        }
    }

    public bx(Context context, ArrayList<bc> arrayList) {
        this.b = context;
        this.c = context.getResources().getColor(R.color.public_theme_color_normal);
        this.a = arrayList;
        this.a.add(0, new bc(context.getResources().getString(R.string.all)));
    }

    public int getCount() {
        return this.a.size();
    }

    public void a(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (i2 == i) {
                ((bc) this.a.get(i2)).a(true);
            } else {
                ((bc) this.a.get(i2)).a(false);
            }
        }
        notifyDataSetChanged();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        bc bcVar = (bc) this.a.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.topic_detail_pop_item, null);
            a aVar2 = new a();
            aVar2.b = (TextView) view.findViewById(R.id.detail_text);
            aVar2.c = view.findViewById(R.id.buttom_short_line);
            aVar2.d = view.findViewById(R.id.buttom_long_line);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(bcVar.a());
        if (this.a.size() == 1) {
            aVar.c.setVisibility(8);
            aVar.d.setVisibility(0);
        } else if (i < this.a.size() - 1) {
            aVar.c.setVisibility(0);
            aVar.d.setVisibility(8);
        } else {
            aVar.c.setVisibility(8);
            aVar.d.setVisibility(0);
        }
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bx b;

            public void onClick(View view) {
                if (this.b.d != null) {
                    this.b.d.a(view, i);
                }
            }
        });
        if (bcVar.b()) {
            aVar.b.setTextColor(this.c);
        } else {
            aVar.b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
        return view;
    }

    public void a(b bVar) {
        this.d = bVar;
    }
}
