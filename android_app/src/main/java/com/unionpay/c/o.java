package com.unionpay.c;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

final class o implements HostnameVerifier {
    o() {
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
