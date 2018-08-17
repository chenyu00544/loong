package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

final class j implements ConnectionKeepAliveStrategy {
    final /* synthetic */ h a;

    j(h hVar) {
        this.a = hVar;
    }

    public final long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return 180000;
    }
}
