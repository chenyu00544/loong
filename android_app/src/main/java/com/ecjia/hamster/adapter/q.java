package com.ecjia.hamster.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.ecjia.hamster.model.ECJia_ATTR_LIST;
import com.ecjia.hamster.model.ECJia_FILTER_ATTR;
import com.ecjia.hamster.model.ECJia_SelectedInterface;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ECJiaGoodlistMenuAdapter */
public class q extends BaseExpandableListAdapter implements com.ecjia.hamster.adapter.i.a {
    public String[] a;
    public String[] b;
    private String c;
    private int d = 0;
    private Context e;
    private ArrayList<String> f = new ArrayList();
    private ArrayList<ArrayList<? extends ECJia_SelectedInterface>> g = new ArrayList();
    private ArrayList<ECJia_FILTER_ATTR> h;
    private String i;

    /* compiled from: ECJiaGoodlistMenuAdapter */
    class a {
        TextView a;
        LinearLayout b;
        TextView c;
        View d;
        final /* synthetic */ q e;

        a(q qVar) {
            this.e = qVar;
        }
    }

    public q(Context context, ArrayList<ECJia_FILTER_ATTR> arrayList) {
        this.e = context;
        this.h = arrayList;
        this.c = " " + this.e.getResources().getString(R.string.all);
        this.i = this.e.getResources().getString(R.string.filter_close);
        a();
    }

    public void a() {
        if (this.h != null && this.h.size() > 0) {
            this.d = this.h.size();
            this.a = new String[this.d];
            this.b = new String[this.d];
            for (int i = 0; i < this.d; i++) {
                this.a[i] = "0";
                this.b[i] = this.c;
            }
            this.f.clear();
            this.g.clear();
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                this.f.add(((ECJia_FILTER_ATTR) this.h.get(i2)).getFilter_attr_name());
                this.g.add(((ECJia_FILTER_ATTR) this.h.get(i2)).getAttrs());
                for (int i3 = 0; i3 < ((ECJia_FILTER_ATTR) this.h.get(i2)).getAttrs().size(); i3++) {
                    if (((ECJia_ATTR_LIST) ((ECJia_FILTER_ATTR) this.h.get(i2)).getAttrs().get(i3)).isSelected()) {
                        this.a[i2] = ((ECJia_ATTR_LIST) ((ECJia_FILTER_ATTR) this.h.get(i2)).getAttrs().get(i3)).getAttr_id();
                        this.b[i2] = ((ECJia_ATTR_LIST) ((ECJia_FILTER_ATTR) this.h.get(i2)).getAttrs().get(i3)).getAttr_value();
                        break;
                    }
                }
            }
        }
    }

    public Object getChild(int i, int i2) {
        return ((ArrayList) this.g.get(i)).get(i2);
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.goodlist_child_item, null);
        GridView gridView = (GridView) inflate.findViewById(R.id.child_item_gridview);
        gridView.setNumColumns(2);
        gridView.setGravity(17);
        gridView.setHorizontalSpacing(6);
        gridView.setVerticalSpacing(6);
        ListAdapter iVar = new i(this.e, i, (List) this.g.get(i));
        iVar.a((com.ecjia.hamster.adapter.i.a) this);
        gridView.setAdapter(iVar);
        return inflate;
    }

    public int getChildrenCount(int i) {
        return 1;
    }

    public Object getGroup(int i) {
        return this.g.get(i);
    }

    public int getGroupCount() {
        return this.g.size();
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a(this);
            view = LayoutInflater.from(this.e).inflate(R.layout.goodlist_parent_item, null);
            aVar.b = (LinearLayout) view.findViewById(R.id.goodlist_p_item);
            aVar.a = (TextView) view.findViewById(R.id.goodlist_parent_name);
            aVar.c = (TextView) view.findViewById(R.id.goodlist_parent_selected_name);
            aVar.d = view.findViewById(R.id.goodlist_parent_bottomline);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText((CharSequence) this.f.get(i));
        if (z) {
            if (this.b[i].equals(this.c)) {
                aVar.c.setText(this.i);
                aVar.c.setTextColor(Color.parseColor("#999999"));
            } else {
                aVar.c.setText(this.b[i]);
                aVar.c.setTextColor(this.e.getResources().getColor(R.color.public_theme_color_normal));
            }
            aVar.d.setVisibility(8);
        } else {
            if (this.b[i].equals(this.c)) {
                aVar.c.setText(this.c);
                aVar.c.setTextColor(Color.parseColor("#999999"));
            } else {
                aVar.c.setText(this.b[i]);
                aVar.c.setTextColor(this.e.getResources().getColor(R.color.public_theme_color_normal));
            }
            aVar.d.setVisibility(0);
        }
        return view;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void a(int i, int i2, int i3) {
        for (int i4 = 0; i4 < ((ArrayList) this.g.get(i2)).size(); i4++) {
            ((ECJia_SelectedInterface) ((ArrayList) this.g.get(i2)).get(i4)).setSelected(false);
        }
        ((ECJia_SelectedInterface) ((ArrayList) this.g.get(i2)).get(i3)).setSelected(true);
        this.a[i2] = ((ECJia_ATTR_LIST) ((ArrayList) this.g.get(i2)).get(i3)).getAttr_id();
        this.b[i2] = ((ECJia_ATTR_LIST) ((ArrayList) this.g.get(i2)).get(i3)).getAttr_value();
        notifyDataSetChanged();
    }
}
