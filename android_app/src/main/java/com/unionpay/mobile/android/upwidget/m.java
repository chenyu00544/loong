package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Iterator;

final class m implements OnClickListener {
    final /* synthetic */ j a;

    m(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        Iterator it = this.a.v.iterator();
        while (it.hasNext()) {
            ((OnClickListener) it.next()).onClick(view);
        }
    }
}
