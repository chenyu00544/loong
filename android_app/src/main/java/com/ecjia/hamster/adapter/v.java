package com.ecjia.hamster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.ecjia.hamster.activity.ECJiaHelpWebActivity;
import com.ecjia.hamster.model.as;
import com.ecjia.hamster.model.d;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaHelpNewAdapter */
public class v extends BaseAdapter implements SectionIndexer, am {
    private final Context a;
    private ArrayList<d> b = new ArrayList();
    private int[] c;
    private String[] d;
    private LayoutInflater e;

    /* compiled from: ECJiaHelpNewAdapter */
    class a {
        TextView a;
        final /* synthetic */ v b;

        a(v vVar) {
            this.b = vVar;
        }
    }

    /* compiled from: ECJiaHelpNewAdapter */
    class b {
        TextView a;
        LinearLayout b;
        View c;
        final /* synthetic */ v d;

        b(v vVar) {
            this.d = vVar;
        }
    }

    public v(Context context, ArrayList<as> arrayList) {
        this.a = context;
        this.e = LayoutInflater.from(context);
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                this.b.addAll(((as) arrayList.get(i)).a);
            }
        }
        this.c = a();
        this.d = b();
    }

    private int[] a() {
        int i;
        ArrayList arrayList = new ArrayList();
        String a = ((d) this.b.get(0)).a();
        arrayList.add(Integer.valueOf(0));
        Object obj = a;
        for (i = 1; i < this.b.size(); i++) {
            if (((d) this.b.get(i)).a().equals(obj)) {
                obj = ((d) this.b.get(i)).a();
                arrayList.add(Integer.valueOf(i));
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }

    private String[] b() {
        String[] strArr = new String[this.c.length];
        for (int i = 0; i < this.c.length; i++) {
            strArr[i] = ((d) this.b.get(this.c[i])).a();
        }
        return strArr;
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            bVar = new b(this);
            view = this.e.inflate(R.layout.my_helpcell, viewGroup, false);
            bVar.b = (LinearLayout) view.findViewById(R.id.shophelp_item);
            bVar.a = (TextView) view.findViewById(R.id.shophelp_content);
            bVar.c = view.findViewById(R.id.help_middle_line_top);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        bVar.a.setText(((d) this.b.get(i)).b());
        bVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ v b;

            public void onClick(View view) {
                Intent intent = new Intent(this.b.a, ECJiaHelpWebActivity.class);
                intent.putExtra("id", ((d) this.b.b.get(i)).d());
                intent.putExtra("title", ((d) this.b.b.get(i)).c());
                this.b.a.startActivity(intent);
            }
        });
        return view;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = this.e.inflate(R.layout.header_new_help, viewGroup, false);
            aVar.a = (TextView) view.findViewById(R.id.text1);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText(((d) this.b.get(i)).a());
        return view;
    }

    public long a(int i) {
        return (long) ((d) this.b.get(i)).a().subSequence(0, 1).charAt(0);
    }

    public int getPositionForSection(int i) {
        if (this.c.length == 0) {
            return 0;
        }
        if (i >= this.c.length) {
            i = this.c.length - 1;
        } else if (i < 0) {
            i = 0;
        }
        return this.c[i];
    }

    public int getSectionForPosition(int i) {
        for (int i2 = 0; i2 < this.c.length; i2++) {
            if (i < this.c[i2]) {
                return i2 - 1;
            }
        }
        return this.c.length - 1;
    }

    public Object[] getSections() {
        return this.d;
    }
}
