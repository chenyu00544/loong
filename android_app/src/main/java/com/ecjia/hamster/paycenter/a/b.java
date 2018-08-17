package com.ecjia.hamster.paycenter.a;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.ecjia.a.q;
import com.ecjia.component.view.f;
import com.ecjia.component.view.k;
import com.ecjia.hamster.paycenter.a.a.c;
import com.ecjia.hamster.paycenter.base.ECJiaOnPaySucceedListener;
import com.ecjia.hamster.paycenter.base.ECJiaOnPaySucceedListener.PaymentType;
import com.ecjia.hamster.paycenter.base.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* compiled from: ECJiaAlipayHelper */
public class b extends a<a> {
    Handler a = new b_1(this);
    private String i;

    /* compiled from: ECJiaAlipayHelper */
    class b_1 extends Handler {
        final /* synthetic */ b a;

        b_1(b bVar) {
            this.a = bVar;
        }

        public void handleMessage(Message message) {
            boolean z = true;
            this.a.i = new com.ecjia.hamster.paycenter.a.a.b((String) message.obj).a();
            q.b("===showsuccess===" + this.a.i);
            if (TextUtils.equals(this.a.i, "9000")) {
                this.a.i = this.a.b;
                z = false;
            } else if (TextUtils.equals(this.a.i, "8000")) {
                this.a.i = this.a.c;
                z = false;
            } else {
                this.a.i = this.a.d;
            }
            f fVar = new f(this.a.h, this.a.i, z);
            fVar.a(17, 0, 0);
            fVar.a();
            if (!z) {
                this.a.a(PaymentType.PAYMENT_ALIPAY, this.a.b);
            }
        }
    }

    public b(Activity activity) {
        super(activity);
    }

    public void a(a aVar) {
        if (TextUtils.isEmpty(aVar.b()) || TextUtils.isEmpty(aVar.c()) || TextUtils.isEmpty(aVar.h()) || TextUtils.isEmpty(aVar.a())) {
            k kVar = new k(this.h, this.f);
            kVar.a(17, 0, 0);
            kVar.a();
            return;
        }
        try {
            String encode;
            String b = b(aVar);
            String a = c.a(b, aVar.a());
            try {
                encode = URLEncoder.encode(a, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                encode = a;
            }
            encode = b + "&sign=\"" + encode + "\"&" + a();
            new Thread(this) {
                final /* synthetic */ b b;

                public void run() {
                    String a = new com.alipay.sdk.app.a(this.b.h).a(encode, true);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = a;
                    this.b.a.sendMessage(message);
                }
            }.start();
        } catch (Exception e2) {
            e2.printStackTrace();
            kVar = new k(this.h, this.d);
            kVar.a(17, 0, 0);
            kVar.a();
        }
    }

    private String b(a aVar) {
        return ((((((((((("partner=\"" + aVar.d() + "\"") + "&seller_id=\"" + aVar.e() + "\"") + "&out_trade_no=\"" + aVar.g() + "\"") + "&subject=\"" + aVar.c() + "\"") + "&body=\"" + aVar.h() + "\"") + "&total_fee=\"" + aVar.b() + "\"") + "&notify_url=\"" + URLEncoder.encode(aVar.f()) + "\"") + "&service=\"mobile.securitypay.pay\"") + "&payment_type=\"1\"") + "&_input_charset=\"utf-8\"") + "&it_b_pay=\"30m\"") + "&return_url=\"m.alipay.com\"";
    }

    private String a() {
        return "sign_type=\"RSA\"";
    }

    public void a(ECJiaOnPaySucceedListener eCJiaOnPaySucceedListener) {
        this.g = eCJiaOnPaySucceedListener;
    }

    public void a(PaymentType paymentType, String str) {
        if (this.g != null) {
            this.g.a(paymentType, str);
        }
    }
}
