package com.ecjia.hamster.paycenter.b;

import android.content.Intent;
import com.ecjia.component.view.f;
import com.ecjia.hamster.paycenter.base.ECJiaOnPaySucceedListener.PaymentType;
import com.ecjia.hamster.paycenter.base.a;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaUppayHelper */
public class b extends a<a> {
    public void a(int i, int i2, Intent intent) {
        String string = intent.getExtras().getString("pay_result");
        boolean z = true;
        if (string.equalsIgnoreCase("success")) {
            string = this.b;
            z = false;
        } else if (string.equalsIgnoreCase("fail")) {
            string = this.d;
        } else if (string.equalsIgnoreCase("cancel")) {
            string = this.e;
        } else {
            string = this.f;
        }
        f fVar = new f(this.h, string, z);
        fVar.a(17, 0, 0);
        fVar.a();
        if (!z) {
            a(PaymentType.PAYMENT_UPPAY, this.h.getResources().getString(R.string.payment_paysuccess));
        }
    }

    public void a(PaymentType paymentType, String str) {
        if (this.g != null) {
            this.g.a(paymentType, str);
        }
    }
}
