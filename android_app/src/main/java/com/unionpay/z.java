package com.unionpay;

final class z implements Runnable {
    final /* synthetic */ d a;
    final /* synthetic */ String b;
    final /* synthetic */ e c;
    final /* synthetic */ WebViewJavascriptBridge d;

    z(WebViewJavascriptBridge webViewJavascriptBridge, d dVar, String str, e eVar) {
        this.d = webViewJavascriptBridge;
        this.a = dVar;
        this.b = str;
        this.c = eVar;
    }

    public final void run() {
        if (this.a != null) {
            this.a.a(this.b, this.c);
        }
    }
}
