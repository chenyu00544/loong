package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.d;
import com.ecmoban.android.missmall.R;
import java.util.List;

/* compiled from: ECJiaHelpAdapter */
public class u extends BaseAdapter {
    private Context a;
    private List<d> b;
    private LayoutInflater c;

    /* compiled from: ECJiaHelpAdapter */
    class a {
        View a;
        View b;
        View c;
        View d;
        final /* synthetic */ u e;
        private TextView f;

        a(u uVar) {
            this.e = uVar;
        }
    }

    public u(Context context, List<d> list) {
        this.a = context;
        this.b = list;
        this.c = LayoutInflater.from(context);
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
        if (view == null) {
            a aVar2 = new a(this);
            view = this.c.inflate(R.layout.help_item, null);
            aVar2.f = (TextView) view.findViewById(R.id.help_item_title);
            aVar2.a = view.findViewById(R.id.help_first_line);
            aVar2.b = view.findViewById(R.id.help_end_line);
            aVar2.c = view.findViewById(R.id.help_middle_line_top);
            aVar2.d = view.findViewById(R.id.help_middle_line_buttom);
            if (this.b.size() != 0) {
                if (this.b.size() == 1) {
                    aVar2.a.setVisibility(0);
                    aVar2.b.setVisibility(0);
                } else if (i == 0) {
                    aVar2.a.setVisibility(0);
                } else if (i == this.b.size() - 1) {
                    aVar2.b.setVisibility(0);
                    aVar2.c.setVisibility(0);
                } else {
                    aVar2.c.setVisibility(0);
                }
            }
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.f.setText(((d) this.b.get(i)).c());
        return view;
    }
}
