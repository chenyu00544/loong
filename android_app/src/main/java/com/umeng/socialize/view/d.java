package com.umeng.socialize.view;

import android.view.View;

/* compiled from: OauthDialog */
class d implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ c c;

    d(c cVar, View view, View view2) {
        this.c = cVar;
        this.a = view;
        this.b = view2;
    }

    public void run() {
        this.a.setVisibility(8);
        if (this.b.getVisibility() == 0) {
            this.b.setVisibility(8);
        }
        this.c.requestLayout();
    }
}
