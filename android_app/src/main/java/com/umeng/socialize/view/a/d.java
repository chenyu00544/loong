package com.umeng.socialize.view.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* compiled from: ACProgressCustom */
class d implements OnDismissListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.a.f != null) {
            this.a.f.cancel();
            this.a.f = null;
        }
        if (this.a.i != null) {
            this.a.i.clear();
            this.a.i = null;
        }
        this.a.g = 0;
        this.a.h = 0;
        this.a.e = null;
    }
}
