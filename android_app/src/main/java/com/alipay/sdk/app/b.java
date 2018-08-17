package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.h.a;
import com.alipay.sdk.util.i;

public final class b extends WebViewClient {
    boolean a;
    private Activity b;
    private boolean c;
    private Handler d;
    private a e;
    private Runnable f = new f(this);

    public b(Activity activity) {
        this.b = activity;
        this.d = new Handler(this.b.getMainLooper());
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.a = true;
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        com.alipay.sdk.app.a.a.a("net", "SSLError", "证书错误");
        if (this.c) {
            sslErrorHandler.proceed();
            this.c = false;
            return;
        }
        this.b.runOnUiThread(new c(this, sslErrorHandler));
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return i.a(webView, str, this.b);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.e == null) {
            this.e = new a(this.b, "正在加载");
        }
        this.e.a();
        this.d.postDelayed(this.f, StatisticConfig.MIN_UPLOAD_INTERVAL);
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        a();
        this.d.removeCallbacks(this.f);
    }

    private void a() {
        if (this.e != null) {
            this.e.b();
        }
        this.e = null;
    }
}
