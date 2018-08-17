package com.unionpay.mobile.android.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;

final class aq extends Dialog {
    final /* synthetic */ an a;

    aq(an anVar, Context context) {
        this.a = anVar;
        super(context);
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4 ? true : super.onKeyDown(i, keyEvent);
    }
}
