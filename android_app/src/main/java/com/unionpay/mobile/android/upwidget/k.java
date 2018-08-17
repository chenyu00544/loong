package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.Iterator;

final class k implements OnItemClickListener {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object obj;
        view.setTag(Integer.valueOf(this.a.o));
        if (this.a.n != this.a.d) {
            obj = this.a.g.get(this.a.n);
            if (obj instanceof c) {
                ((c) obj).a(-1);
            }
        }
        obj = this.a.g.get(this.a.o);
        if (obj instanceof c) {
            ((c) obj).a(i);
        }
        this.a.n = this.a.o;
        this.a.p = i;
        Iterator it = this.a.t.iterator();
        while (it.hasNext()) {
            ((OnItemClickListener) it.next()).onItemClick(adapterView, view, i, j);
        }
    }
}
