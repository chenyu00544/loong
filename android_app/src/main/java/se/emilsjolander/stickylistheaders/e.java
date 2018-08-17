package se.emilsjolander.stickylistheaders;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ExpandableStickyListHeadersAdapter */
class e extends BaseAdapter implements h {
    d<View, Long> a = new d();
    c<Integer, View> b = new c();
    List<Long> c = new ArrayList();
    private final h d;

    e(h hVar) {
        this.d = hVar;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        return this.d.a(i, view, viewGroup);
    }

    public long a(int i) {
        return this.d.a(i);
    }

    public boolean areAllItemsEnabled() {
        return this.d.areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return this.d.isEnabled(i);
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.d.registerDataSetObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.d.unregisterDataSetObserver(dataSetObserver);
    }

    public int getCount() {
        return this.d.getCount();
    }

    public Object getItem(int i) {
        return this.d.getItem(i);
    }

    public long getItemId(int i) {
        return this.d.getItemId(i);
    }

    public boolean hasStableIds() {
        return this.d.hasStableIds();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Object view2 = this.d.getView(i, view, viewGroup);
        this.a.a(view2, Long.valueOf(getItemId(i)));
        this.b.a(Integer.valueOf((int) a(i)), view2);
        if (this.c.contains(Long.valueOf(a(i)))) {
            view2.setVisibility(8);
        } else {
            view2.setVisibility(0);
        }
        return view2;
    }

    public int getItemViewType(int i) {
        return this.d.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.d.getViewTypeCount();
    }

    public boolean isEmpty() {
        return this.d.isEmpty();
    }

    public List<View> a(long j) {
        return this.b.a(Integer.valueOf((int) j));
    }

    public boolean b(long j) {
        return this.c.contains(Long.valueOf(j));
    }

    public void c(long j) {
        if (b(j)) {
            this.c.remove(Long.valueOf(j));
        }
    }

    public void d(long j) {
        if (!b(j)) {
            this.c.add(Long.valueOf(j));
        }
    }

    public View e(long j) {
        return (View) this.a.a(Long.valueOf(j));
    }

    public long a(View view) {
        return ((Long) this.a.b(view)).longValue();
    }
}
