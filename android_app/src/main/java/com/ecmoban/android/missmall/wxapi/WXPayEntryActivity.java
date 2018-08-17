package com.ecmoban.android.missmall.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.a.a;
import com.ecjia.consts.b;
import com.ecmoban.android.missmall.R;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;
import java.util.Timer;
import java.util.TimerTask;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    TimerTask a = new WXPayEntryActivity_1(this);
    private IWXAPI b;
    private ImageView c;
    private ImageView d;
    private TextView e;
    private Resources f;

    class WXPayEntryActivity_1 extends TimerTask {
        final /* synthetic */ WXPayEntryActivity a;

        WXPayEntryActivity_1(WXPayEntryActivity wXPayEntryActivity) {
            this.a = wXPayEntryActivity;
        }

        public void run() {
            this.a.finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pay_result);
        this.e = (TextView) findViewById(R.id.paysuccess_text);
        this.c = (ImageView) findViewById(R.id.pay_image_success);
        this.d = (ImageView) findViewById(R.id.pay_image_fail);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        this.b = WXAPIFactory.createWXAPI(this, "wx38b6c8356cdad915");
        this.b.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.b.handleIntent(intent, this);
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        Log.d(".WXPayEntryActivity", "onPayFinish, errCode = " + baseResp.errCode);
        this.f = b.a((Context) this);
        CharSequence string = this.f.getString(R.string.payment_paysuccess);
        CharSequence string2 = this.f.getString(R.string.payment_payfail);
        if (baseResp.getType() == 5) {
            if (baseResp.errCode == 0) {
                this.e.setText(string);
                this.c.setVisibility(0);
                this.d.setVisibility(8);
                c.a().c(new com.ecjia.a.a.b("wxpay"));
            } else {
                this.e.setText(string2);
                this.c.setVisibility(8);
                this.d.setVisibility(0);
            }
        }
        new Timer().schedule(this.a, 2000);
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(a aVar) {
    }
}
