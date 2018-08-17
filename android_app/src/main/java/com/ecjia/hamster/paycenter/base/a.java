package com.ecjia.hamster.paycenter.base;

import android.app.Activity;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaBasePayHelper */
public abstract class a<T> implements ECJiaOnPaySucceedListener {
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public ECJiaOnPaySucceedListener g;
    protected Activity h;

    public a(Activity activity) {
        this.h = activity;
        this.b = activity.getResources().getString(R.string.payment_paysuccess);
        this.c = activity.getResources().getString(R.string.payment_paywait);
        this.d = activity.getResources().getString(R.string.payment_payfail);
        this.e = activity.getResources().getString(R.string.payment_cancel_pay);
        this.f = activity.getResources().getString(R.string.payment_system_busy);
    }
}
