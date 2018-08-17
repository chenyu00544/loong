package com.alipay.sdk.d;

import anet.channel.util.HttpConstant;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.taobao.accs.common.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public final class b {
    static b a;
    final DefaultHttpClient b;

    private b(HttpParams httpParams) {
        this.b = new DefaultHttpClient(httpParams);
    }

    private b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.b = new DefaultHttpClient(clientConnectionManager, httpParams);
    }

    public static b a() {
        if (a == null) {
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
            basicHttpParams.setBooleanParameter("http.protocol.expect-continue", false);
            ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
            ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(30));
            ConnManagerParams.setTimeout(basicHttpParams, 1000);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT);
            HttpConnectionParams.setSoTimeout(basicHttpParams, m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT);
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, 16384);
            HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
            HttpClientParams.setRedirecting(basicHttpParams, true);
            HttpClientParams.setAuthenticating(basicHttpParams, false);
            HttpProtocolParams.setUserAgent(basicHttpParams, "msp");
            try {
                SocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
                socketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                Scheme scheme = new Scheme(HttpConstant.HTTPS, socketFactory, Constants.PORT);
                Scheme scheme2 = new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80);
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(scheme);
                schemeRegistry.register(scheme2);
                a = new b(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
            } catch (Exception e) {
                a = new b(basicHttpParams);
            }
        }
        return a;
    }

    public final HttpResponse a(HttpUriRequest httpUriRequest) throws Exception {
        try {
            return this.b.execute(httpUriRequest);
        } catch (Exception e) {
            throw e;
        }
    }
}
