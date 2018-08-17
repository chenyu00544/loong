package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.q;
import com.ecjia.component.view.d;
import com.ecjia.hamster.model.ap;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import im.delight.android.webview.AdvancedWebView;

public class ECJiaWebViewActivity extends a implements im.delight.android.webview.AdvancedWebView.a {
    AdvancedWebView a;
    private TextView b;
    private TextView c;
    private LinearLayout d;
    private LinearLayout e;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private d o;
    private String p;
    private String q;

    class ECJiaWebViewActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_1(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onClick(View view) {
            if (this.a.o != null) {
                this.a.o.dismiss();
            }
            if (this.a.a.canGoBack()) {
                this.a.a.goBack();
            } else {
                this.a.finish();
            }
        }
    }

    class ECJiaWebViewActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_2(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a, ECJiaShareActivity.class);
            intent.putExtra("share_content", this.a.q);
            intent.putExtra("share_goods_url", this.a.q);
            intent.putExtra("share_goods_name", this.a.p);
            intent.putExtra("is_refresh", true);
            this.a.startActivityForResult(intent, 101);
            this.a.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        }
    }

    class ECJiaWebViewActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_3(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onClick(View view) {
            if (this.a.o != null) {
                this.a.o.dismiss();
            }
            this.a.finish();
        }
    }

    class ECJiaWebViewActivity_4 extends WebViewClient {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_4(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.o.show();
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.o.dismiss();
            if (this.a.a.canGoBack()) {
                this.a.c.setVisibility(0);
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            q.b("===webUrl===" + str);
            this.a.q = str;
            if (str.contains("ecjiaopen://app?open_type")) {
                com.ecjia.a.b.a.a().a(this.a, str);
                this.a.finish();
                return true;
            }
            if (!(!str.contains("login=syncapp") || ap.c() == null || TextUtils.isEmpty(ap.c().a))) {
                str = str.replace("login=syncapp", "origin=app&openid=" + this.a.h.e().r() + "&token=" + ap.c().b);
                q.b("===weburl===" + str);
            }
            if (!(!str.contains("http://www.missmall.com/mobile/index.php?m=chat") || ap.c() == null || TextUtils.isEmpty(ap.c().a))) {
                str = str + "&login=syncapporigin=app&openid=" + this.a.h.e().r() + "&token=" + ap.c().b;
                q.b("===weburl===" + str);
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    class ECJiaWebViewActivity_5 extends WebChromeClient {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_5(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (!TextUtils.isEmpty(str)) {
                this.a.p = str;
                this.a.b.setText(str);
            }
        }
    }

    class ECJiaWebViewActivity_6 implements DownloadListener {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_6(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    class ECJiaWebViewActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_7(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onClick(View view) {
            if (this.a.a.canGoBack()) {
                this.a.a.goBack();
            }
        }
    }

    class ECJiaWebViewActivity_8 implements OnClickListener {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_8(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onClick(View view) {
            this.a.a.goForward();
        }
    }

    class ECJiaWebViewActivity_9 implements OnClickListener {
        final /* synthetic */ ECJiaWebViewActivity a;

        ECJiaWebViewActivity_9(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        public void onClick(View view) {
            this.a.a.reload();
        }
    }

    class a {
        final /* synthetic */ ECJiaWebViewActivity a;

        a(ECJiaWebViewActivity eCJiaWebViewActivity) {
            this.a = eCJiaWebViewActivity;
        }

        @JavascriptInterface
        public String toString() {
            return "ajaxSubmit";
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.webview);
        PushAgent.getInstance(this).onAppStart();
        Intent intent = getIntent();
        this.q = intent.getStringExtra("url");
        q.b("===weburl2===" + this.q);
        this.p = intent.getStringExtra("title");
        this.o = d.a((Context) this);
        this.o.a(getResources().getString(R.string.loading));
        this.b = (TextView) findViewById(R.id.top_view_text);
        this.b.setText(getResources().getString(R.string.browser));
        this.c = (TextView) findViewById(R.id.top_close);
        this.d = (LinearLayout) findViewById(R.id.top_back);
        this.d.setOnClickListener(new ECJiaWebViewActivity_1(this));
        this.l = (ImageView) findViewById(R.id.toshare);
        this.l.setVisibility(8);
        this.e = (LinearLayout) findViewById(R.id.top_right_ll);
        this.e.setOnClickListener(new ECJiaWebViewActivity_2(this));
        this.c.setOnClickListener(new ECJiaWebViewActivity_3(this));
        this.a = (AdvancedWebView) findViewById(R.id.webview_webView);
        this.a.setWebViewClient(new ECJiaWebViewActivity_4(this));
        this.a.setWebChromeClient(new ECJiaWebViewActivity_5(this));
        this.a.setDownloadListener(new ECJiaWebViewActivity_6(this));
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setUserAgentString(settings.getUserAgentString() + " ECJiaBrowse/1.2.0");
        if (VERSION.SDK_INT > 17) {
            this.a.addJavascriptInterface(new a(this), "ajaxSubmit");
        } else {
            this.a.loadUrl("javascript:ajaxSubmit()");
        }
        if (this.q != null) {
            this.a.loadUrl(this.q);
        }
        this.k = (ImageView) findViewById(R.id.web_back);
        this.k.setOnClickListener(new ECJiaWebViewActivity_7(this));
        this.m = (ImageView) findViewById(R.id.goForward);
        this.m.setOnClickListener(new ECJiaWebViewActivity_8(this));
        this.n = (ImageView) findViewById(R.id.reload);
        this.n.setOnClickListener(new ECJiaWebViewActivity_9(this));
    }

    protected void onPause() {
        this.a.onPause();
        super.onPause();
    }

    protected void onResume() {
        this.a.onResume();
        super.onResume();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.o != null) {
                this.o.dismiss();
            }
            if (this.a.canGoBack()) {
                this.a.goBack();
            } else {
                finish();
            }
        }
        return true;
    }

    protected void onDestroy() {
        this.a.onDestroy();
        super.onDestroy();
        ((ViewGroup) getWindow().getDecorView()).removeAllViews();
    }

    public void a(String str, Bitmap bitmap) {
    }

    public void a(String str) {
    }

    public void a(int i, String str, String str2) {
    }

    public void a(String str, String str2, String str3, String str4, long j) {
    }

    public void b(String str) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.a.onActivityResult(i, i2, intent);
        switch (i) {
            case 101:
                if (i2 == -1) {
                    this.a.reload();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
