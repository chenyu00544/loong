package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Iterator;

final class f implements OnClickListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        Iterator it = this.a.g.iterator();
        while (it.hasNext()) {
            ((OnClickListener) it.next()).onClick(view);
        }
    }
}
