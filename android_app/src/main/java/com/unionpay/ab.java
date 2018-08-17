package com.unionpay;

final class ab implements e {
    final /* synthetic */ WebViewJavascriptBridge a;
    private final String b;

    public ab(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.a = webViewJavascriptBridge;
        this.b = str;
    }

    public final void a(String str) {
        this.a.a(this.b, str);
    }
}
