package org.xutils.http.app;

import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;

public class DefaultParamsBuilder implements ParamsBuilder {
    private static SSLSocketFactory a;

    static class DefaultParamsBuilder_1 implements X509TrustManager {
        DefaultParamsBuilder_1() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }
    }

    public String buildUri(RequestParams requestParams, HttpRequest httpRequest) throws Throwable {
        return httpRequest.host() + "/" + httpRequest.path();
    }

    public String buildCacheKey(RequestParams requestParams, String[] strArr) {
        String str = null;
        if (strArr != null && strArr.length > 0) {
            String str2 = requestParams.getUri() + "?";
            str = str2;
            for (String str3 : strArr) {
                String stringParameter = requestParams.getStringParameter(str3);
                if (stringParameter != null) {
                    str = str + str3 + "=" + stringParameter + "&";
                }
            }
        }
        return str;
    }

    public SSLSocketFactory getSSLSocketFactory() throws Throwable {
        return getTrustAllSSLSocketFactory();
    }

    public void buildParams(RequestParams requestParams) throws Throwable {
    }

    public void buildSign(RequestParams requestParams, String[] strArr) throws Throwable {
    }

    public static SSLSocketFactory getTrustAllSSLSocketFactory() {
        if (a == null) {
            synchronized (DefaultParamsBuilder.class) {
                if (a == null) {
                    TrustManager[] trustManagerArr = new TrustManager[]{new DefaultParamsBuilder_1()};
                    try {
                        SSLContext instance = SSLContext.getInstance("TLS");
                        instance.init(null, trustManagerArr, null);
                        a = instance.getSocketFactory();
                    } catch (Throwable th) {
                        LogUtil.e(th.getMessage(), th);
                    }
                }
            }
        }
        return a;
    }
}
