package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Iterator;

final class d implements OnClickListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void onClick(View view) {
        Iterator it = this.a.l.iterator();
        while (it.hasNext()) {
            ((OnClickListener) it.next()).onClick(view);
        }
    }
}
