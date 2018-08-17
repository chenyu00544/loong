package com.unionpay;

final class aa implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ WebViewJavascriptBridge b;

    aa(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.b = webViewJavascriptBridge;
        this.a = str;
    }

    public final void run() {
        this.b.a.loadUrl(this.a);
    }
}
