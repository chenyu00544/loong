package com.unionpay;

import android.view.View;
import android.view.View.OnClickListener;

final class l implements OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    l(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void onClick(View view) {
        this.a.finish();
    }
}
