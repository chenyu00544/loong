package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.utils.k;

final class s implements OnClickListener {
    final /* synthetic */ UPWidget a;

    s(UPWidget uPWidget) {
        this.a = uPWidget;
    }

    public final void onClick(View view) {
        int id = view.getId();
        int i = this.a.c;
        if (id == 10) {
            k.c("kb", " [ FIN ]");
            UPWidget.b(this.a);
        } else if (id == 20) {
            k.c("kb", " [ DEL ]");
            if (i > 0) {
                this.a.deleteOnce(this.a.p);
                r0 = this.a;
                r0.c--;
                r0 = this.a.b.b().toString().substring(0, i - 1);
                this.a.b.c(r0);
                this.a.b.b(r0.length());
            }
            id = this.a.c;
        } else if (this.a.c == 6) {
            k.c("kb", " [ FIN ]");
        } else {
            this.a.appendOnce(this.a.p, Integer.toString(id));
            r0 = i == 0 ? "*" : this.a.b.b() + "*";
            this.a.b.c(r0);
            this.a.b.b(r0.length());
            r0 = this.a;
            r0.c++;
        }
    }
}
