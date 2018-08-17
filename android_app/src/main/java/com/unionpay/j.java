package com.unionpay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class j implements OnClickListener {
    final /* synthetic */ Context a;

    j(Context context) {
        this.a = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        a.a(this.a, a.I, a.m());
        dialogInterface.dismiss();
    }
}
