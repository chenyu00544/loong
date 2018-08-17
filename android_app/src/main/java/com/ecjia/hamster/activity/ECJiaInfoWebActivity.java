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
import com.ecjia.component.a.s;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaInfoWebActivity extends a implements a {
    private s a;
    private WebView b;
    private String c = "";

    class ECJiaInfoWebActivity_1 extends WebViewClient {
        final /* synthetic */ ECJiaInfoWebActivity a;

        ECJiaInfoWebActivity_1(ECJiaInfoWebActivity eCJiaInfoWebActivity) {
            this.a = eCJiaInfoWebActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    class ECJiaInfoWebActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaInfoWebActivity a;

        ECJiaInfoWebActivity_2(ECJiaInfoWebActivity eCJiaInfoWebActivity) {
            this.a = eCJiaInfoWebActivity;
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
        this.b.setWebViewClient(new ECJiaInfoWebActivity_1(this));
        this.b.setInitialScale(25);
        WebSettings settings = this.b.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        this.b.getSettings().setUseWideViewPort(true);
        this.b.getSettings().setLoadWithOverviewMode(true);
        this.a = new s(this);
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
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaInfoWebActivity_2(this));
    }

    public void a(String str, String str2, ax axVar) {
        if (str == "shop/info/detail" && axVar.b() == 1) {
            this.b.loadDataWithBaseURL(null, this.a.e, "text/html", "utf-8", null);
        }
    }
}
