package com.unionpay;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.unionpay.utils.g;

final class c extends WebViewClient {
    final /* synthetic */ WebViewJavascriptBridge a;

    private c(WebViewJavascriptBridge webViewJavascriptBridge) {
        this.a = webViewJavascriptBridge;
    }

    public final void onPageFinished(WebView webView, String str) {
        g.a("test", "onPageFinished");
    }
}
