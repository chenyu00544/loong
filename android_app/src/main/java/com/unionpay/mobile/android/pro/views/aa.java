package com.unionpay.mobile.android.pro.views;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.i.a;

final class aa implements OnClickListener {
    final /* synthetic */ k a;

    aa(k kVar) {
        this.a = kVar;
    }

    public final void onClick(View view) {
        ((a) this.a.d).startActivityForResult(new Intent("android.settings.NFC_SETTINGS"), 0);
    }
}
