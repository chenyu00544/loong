package com.umeng.socialize.view.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* compiled from: ACProgressFlower */
class g implements OnDismissListener {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.a.d != null) {
            this.a.d.cancel();
            this.a.d = null;
        }
        this.a.c = 0;
        this.a.b = null;
    }
}
