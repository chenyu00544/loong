package com.ecjia.hamster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.i;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.common.SocializeConstants;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: ECJiaChooseCityAdapter */
public class d extends BaseAdapter {
    final int a = 3;
    public String[] b;
    public HashMap<String, Integer> c;
    private LayoutInflater d;
    private List<i> e;

    /* compiled from: ECJiaChooseCityAdapter */
    private class a {
        TextView a;
        TextView b;
        View c;
        final /* synthetic */ d d;

        private a(d dVar) {
            this.d = dVar;
        }
    }

    public d(Context context, List<i> list) {
        this.d = LayoutInflater.from(context);
        this.e = list;
        this.c = new HashMap();
        this.b = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (!(i + -1 >= 0 ? a(((i) list.get(i - 1)).c()) : " ").equals(a(((i) list.get(i)).c()))) {
                String a = a(((i) list.get(i)).c());
                this.c.put(a, Integer.valueOf(i));
                this.b[i] = a;
            }
        }
    }

    public int getCount() {
        return this.e.size();
    }

    public Object getItem(int i) {
        return this.e.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getViewTypeCount() {
        return 3;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.d.inflate(R.layout.choosecity_list_item, null);
            aVar = new a();
            aVar.a = (TextView) view.findViewById(R.id.alpha);
            aVar.b = (TextView) view.findViewById(R.id.name);
            aVar.c = view.findViewById(R.id.cityitem_top);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(((i) this.e.get(i)).b());
        CharSequence a = a(((i) this.e.get(i)).c());
        if ((i + -1 >= 0 ? a(((i) this.e.get(i - 1)).c()) : " ").equals(a)) {
            aVar.a.setVisibility(8);
            aVar.c.setVisibility(8);
        } else {
            aVar.a.setVisibility(0);
            aVar.a.setText(a);
            aVar.c.setVisibility(0);
        }
        return view;
    }

    private String a(String str) {
        if (str.equals(SocializeConstants.OP_DIVIDER_MINUS)) {
            return "&";
        }
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char charAt = str.trim().substring(0, 1).charAt(0);
        if (Pattern.compile("^[A-Za-z]+$").matcher(charAt + "").matches()) {
            return (charAt + "").toUpperCase();
        }
        return "#";
    }
}
