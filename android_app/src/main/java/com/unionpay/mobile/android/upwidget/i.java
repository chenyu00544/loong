package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Iterator;

final class i implements OnClickListener {
    final /* synthetic */ g a;

    i(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        Iterator it = this.a.d.iterator();
        while (it.hasNext()) {
            ((OnClickListener) it.next()).onClick(view);
        }
    }
}
