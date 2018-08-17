package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.q;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaHelpWebActivity extends a implements a {
    private q a;
    private WebView b;
    private String c = "";

    class ECJiaHelpWebActivity_1 extends WebViewClient {
        final /* synthetic */ ECJiaHelpWebActivity a;

        ECJiaHelpWebActivity_1(ECJiaHelpWebActivity eCJiaHelpWebActivity) {
            this.a = eCJiaHelpWebActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    class ECJiaHelpWebActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaHelpWebActivity a;

        ECJiaHelpWebActivity_2(ECJiaHelpWebActivity eCJiaHelpWebActivity) {
            this.a = eCJiaHelpWebActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.helpweb);
        PushAgent.getInstance(this).onAppStart();
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra("id", 0);
        this.c = intent.getStringExtra("title");
        a();
        this.b = (WebView) findViewById(R.id.help_web);
        this.b.setWebViewClient(new ECJiaHelpWebActivity_1(this));
        this.b.setInitialScale(25);
        WebSettings settings = this.b.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        this.b.getSettings().setUseWideViewPort(true);
        this.b.getSettings().setLoadWithOverviewMode(true);
        this.a = new q(this);
        this.a.a((a) this);
        this.a.a(intExtra);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    protected void onResume() {
        super.onResume();
        this.b.onResume();
    }

    @SuppressLint({"NewApi"})
    protected void onPause() {
        super.onPause();
        this.b.onPause();
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.goodsdesc_topview);
        this.i.setTitleText(this.c);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaHelpWebActivity_2(this));
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("shop/help/detail") && axVar.b() == 1) {
            this.b.loadDataWithBaseURL(null, this.a.e, "text/html", "utf-8", null);
        }
    }
}
