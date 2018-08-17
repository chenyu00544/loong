package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

final class i extends DefaultRedirectHandler {
    int a;
    final /* synthetic */ h b;

    i(h hVar) {
        this.b = hVar;
    }

    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        this.a++;
        boolean isRedirectRequested = super.isRedirectRequested(httpResponse, httpContext);
        if (isRedirectRequested || this.a >= 5) {
            return isRedirectRequested;
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        return (statusCode == 301 || statusCode == 302) ? true : isRedirectRequested;
    }
}
