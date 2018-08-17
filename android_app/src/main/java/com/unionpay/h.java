package com.unionpay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class h implements OnClickListener {
    final /* synthetic */ Context a;

    h(Context context) {
        this.a = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        a.a(this.a, a.I, a.m());
        dialogInterface.dismiss();
    }
}
