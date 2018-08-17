package com.ecjia.hamster.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.j;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaBannerWebActivity extends a {
    private TextView a;
    private ImageView b;
    private WebView c;
    private LinearLayout d;

    class ECJiaBannerWebActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaBannerWebActivity a;

        ECJiaBannerWebActivity_1(ECJiaBannerWebActivity eCJiaBannerWebActivity) {
            this.a = eCJiaBannerWebActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaBannerWebActivity_2 extends WebViewClient {
        final /* synthetic */ ECJiaBannerWebActivity a;

        ECJiaBannerWebActivity_2(ECJiaBannerWebActivity eCJiaBannerWebActivity) {
            this.a = eCJiaBannerWebActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    class ECJiaBannerWebActivity_3 implements DownloadListener {
        final /* synthetic */ ECJiaBannerWebActivity a;

        ECJiaBannerWebActivity_3(ECJiaBannerWebActivity eCJiaBannerWebActivity) {
            this.a = eCJiaBannerWebActivity;
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    class ECJiaBannerWebActivity_4 extends WebChromeClient {
        final /* synthetic */ ECJiaBannerWebActivity a;

        ECJiaBannerWebActivity_4(ECJiaBannerWebActivity eCJiaBannerWebActivity) {
            this.a = eCJiaBannerWebActivity;
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (!TextUtils.isEmpty(str)) {
                this.a.a.setText(str);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.banner_web);
        PushAgent.getInstance(this).onAppStart();
        Object stringExtra = getIntent().getStringExtra("url");
        this.a = (TextView) findViewById(R.id.top_view_text);
        this.d = (LinearLayout) findViewById(R.id.payweb_wap);
        this.d.setVisibility(0);
        this.a.setText(this.g.getString(R.string.browser));
        this.b = (ImageView) findViewById(R.id.top_view_back);
        this.b.setOnClickListener(new ECJiaBannerWebActivity_1(this));
        this.c = (WebView) findViewById(R.id.pay_web);
        this.c.setWebViewClient(new ECJiaBannerWebActivity_2(this));
        if (TextUtils.isEmpty(stringExtra)) {
            this.c.loadUrl(j.a().a.e());
        } else {
            this.c.loadUrl(stringExtra);
        }
        this.c.setDownloadListener(new ECJiaBannerWebActivity_3(this));
        this.c.setInitialScale(25);
        WebSettings settings = this.c.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        this.c.getSettings().setUseWideViewPort(true);
        this.c.getSettings().setLoadWithOverviewMode(true);
        this.c.setWebChromeClient(new ECJiaBannerWebActivity_4(this));
    }
}
