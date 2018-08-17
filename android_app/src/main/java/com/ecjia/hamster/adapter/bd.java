package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaSearchLeftAdapter */
public class bd extends BaseAdapter {
    public ArrayList<ECJia_CATEGORY> a;
    private Context b;
    private LayoutInflater c;
    private b d = null;

    /* compiled from: ECJiaSearchLeftAdapter */
    class a {
        LinearLayout a;
        TextView b;
        View c;
        final /* synthetic */ bd d;

        a(bd bdVar) {
            this.d = bdVar;
        }
    }

    /* compiled from: ECJiaSearchLeftAdapter */
    public interface b {
        void a(View view, int i);
    }

    public bd(ArrayList<ECJia_CATEGORY> arrayList, Context context) {
        this.a = arrayList;
        this.b = context;
        this.c = LayoutInflater.from(context);
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        ECJia_CATEGORY eCJia_CATEGORY = (ECJia_CATEGORY) this.a.get(i);
        if (view == null) {
            a aVar2 = new a(this);
            view = this.c.inflate(R.layout.search_left_item, null);
            aVar2.a = (LinearLayout) view.findViewById(R.id.ll_item);
            aVar2.b = (TextView) view.findViewById(R.id.tv_name);
            aVar2.c = view.findViewById(R.id.left_line);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(eCJia_CATEGORY.getName());
        if (eCJia_CATEGORY.isChoose()) {
            aVar.a.setBackgroundColor(this.b.getResources().getColor(R.color.white));
            aVar.b.setTextColor(this.b.getResources().getColor(R.color.public_theme_color_normal));
            aVar.c.setVisibility(0);
        } else {
            aVar.a.setBackgroundColor(this.b.getResources().getColor(R.color.color_gray));
            aVar.b.setTextColor(this.b.getResources().getColor(R.color.filter_text_color));
            aVar.c.setVisibility(4);
        }
        aVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ bd b;

            public void onClick(View view) {
                if (this.b.d != null) {
                    this.b.d.a(view, i);
                }
            }
        });
        return view;
    }

    public void a(b bVar) {
        this.d = bVar;
    }
}
