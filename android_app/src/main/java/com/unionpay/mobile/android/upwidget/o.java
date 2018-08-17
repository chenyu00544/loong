package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Iterator;

final class o implements OnClickListener {
    final /* synthetic */ j a;

    o(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        if (this.a.n != this.a.d) {
            Object obj = this.a.g.get(this.a.n);
            if (obj instanceof c) {
                ((c) obj).a(-1);
            }
        }
        this.a.n = this.a.o;
        this.a.p = this.a.d;
        Iterator it = this.a.x.iterator();
        while (it.hasNext()) {
            ((OnClickListener) it.next()).onClick(view);
        }
    }
}
