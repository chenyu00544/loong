package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.b.a;
import com.ecjia.a.q;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaPayWebActivity extends a {
    String a;
    Intent b;
    private TextView c;
    private ImageView d;
    private WebView e;
    private Handler k;
    private String l;
    private LinearLayout m;
    private LinearLayout n;
    private TextView o;

    class ECJiaPayWebActivity_1 extends WebViewClient {
        final /* synthetic */ ECJiaPayWebActivity a;

        ECJiaPayWebActivity_1(ECJiaPayWebActivity eCJiaPayWebActivity) {
            this.a = eCJiaPayWebActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            q.a("点击weburl:" + str);
            if (!str.contains("ecjiaopen://app?open_type")) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            a.a().a(this.a, str);
            this.a.finish();
            return true;
        }
    }

    class ECJiaPayWebActivity_2 {
        final /* synthetic */ ECJiaPayWebActivity a;

        class ECJiaPayWebActivity_2_1 implements Runnable {
            final /* synthetic */ ECJiaPayWebActivity_2 a;

            ECJiaPayWebActivity_2_1(ECJiaPayWebActivity_2 eCJiaPayWebActivity_2) {
                this.a = eCJiaPayWebActivity_2;
            }

            public void run() {
                c.a().c(new b("wappay"));
                this.a.a.finish();
            }
        }

        ECJiaPayWebActivity_2(ECJiaPayWebActivity eCJiaPayWebActivity) {
            this.a = eCJiaPayWebActivity;
        }

        @JavascriptInterface
        public void back() {
            this.a.k = new Handler();
            this.a.k.post(new ECJiaPayWebActivity_2_1(this));
        }
    }

    class ECJiaPayWebActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaPayWebActivity a;

        ECJiaPayWebActivity_3(ECJiaPayWebActivity eCJiaPayWebActivity) {
            this.a = eCJiaPayWebActivity;
        }

        public void onClick(View view) {
            this.a.finish();
            this.a.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    @SuppressLint({"JavascriptInterface"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pay_web);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        e();
        this.b = getIntent();
        this.l = this.b.getStringExtra("html");
        this.a = this.b.getStringExtra("code");
        if ("pay_bank".equals(this.a)) {
            this.m.setVisibility(8);
            this.n.setVisibility(0);
            b();
        } else if ("pay_alipay".equals(this.a)) {
            this.m.setVisibility(0);
            this.n.setVisibility(8);
            c();
        }
    }

    private void b() {
        this.o.setText(this.l);
    }

    @SuppressLint({"JavascriptInterface"})
    private void c() {
        this.e.loadUrl(this.l);
        WebSettings settings = this.e.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUserAgentString(settings.getUserAgentString() + " ECJiaBrowse/1.2.0");
        this.e.setWebViewClient(new ECJiaPayWebActivity_1(this));
        settings.setBuiltInZoomControls(true);
        this.e.addJavascriptInterface(new ECJiaPayWebActivity_2(this), "ecmoban");
    }

    private void e() {
        this.c = (TextView) findViewById(R.id.top_view_text);
        this.c.setText(getBaseContext().getResources().getString(R.string.pay));
        this.d = (ImageView) findViewById(R.id.top_view_back);
        this.d.setOnClickListener(new ECJiaPayWebActivity_3(this));
        this.e = (WebView) findViewById(R.id.pay_web);
        this.m = (LinearLayout) findViewById(R.id.payweb_wap);
        this.n = (LinearLayout) findViewById(R.id.payweb_bank);
        this.o = (TextView) findViewById(R.id.pay_banktext);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        return true;
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.a aVar) {
    }
}
