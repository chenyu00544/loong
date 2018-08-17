package com.unionpay;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.utils.h;

final class p implements OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    p(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void onClick(View view) {
        Builder builder = new Builder(this.a);
        this.a.d = builder.create();
        builder.setMessage(h.a().a);
        builder.setTitle(h.a().d);
        builder.setPositiveButton(h.a().b, new q(this));
        builder.setNegativeButton(h.a().c, new r(this));
        builder.create().show();
    }
}
