package com.unionpay.mobile.android.nocard.views;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class aj implements OnTouchListener {
    final /* synthetic */ bi a;

    aj(bi biVar) {
        this.a = biVar;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
            case 1:
                if (!view.hasFocus()) {
                    view.requestFocus();
                    break;
                }
                break;
        }
        return false;
    }
}
