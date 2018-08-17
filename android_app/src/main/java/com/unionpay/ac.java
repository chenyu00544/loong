package com.unionpay;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

final class ac extends WebChromeClient {
    final /* synthetic */ WebViewJavascriptBridge a;

    private ac(WebViewJavascriptBridge webViewJavascriptBridge) {
        this.a = webViewJavascriptBridge;
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return true;
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        jsResult.cancel();
        Toast.makeText(this.a.b, str2, 0).show();
        return true;
    }
}
