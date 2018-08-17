package com.unionpay.mobile.android.h;

import android.content.Context;
import anet.channel.util.HttpConstant;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.taobao.accs.common.Constants;
import com.unionpay.mobile.android.utils.k;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;

public final class c {
    private HttpClient a = null;
    private HttpResponse b = null;
    private HttpEntity c = null;
    private byte[] d = null;
    private InputStream e = null;
    private d f = null;

    public c(d dVar, Context context) {
        this.f = dVar;
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 20);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 60000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpProtocolParams.setUserAgent(basicHttpParams, "uppay");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme(HttpConstant.HTTPS, new a(context), Constants.PORT));
        this.a = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        ((AbstractHttpClient) this.a).setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
    }

    public final int a() {
        k.a("uppay", "HttpConn.connect() +++");
        if (this.f == null) {
            k.c("uppay", "params==null!!!");
            return 1;
        }
        int i;
        HttpUriRequest httpPost = this.f.a() == 1 ? new HttpPost(this.f.b()) : new HttpGet(this.f.b());
        if (this.f.e() != null) {
            ((HttpPost) httpPost).setEntity(new ByteArrayEntity(this.f.e()));
        }
        HashMap d = this.f.d();
        if (d != null) {
            for (String str : d.keySet()) {
                httpPost.addHeader(str, (String) d.get(str));
            }
        }
        try {
            this.b = this.a.execute(httpPost);
            if (this.b.getStatusLine().getStatusCode() == 200) {
                this.c = this.b.getEntity();
                if (this.c != null) {
                    ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(2048);
                    byte[] bArr = new byte[2048];
                    this.e = this.c.getContent();
                    while (true) {
                        int read = this.e.read(bArr, 0, bArr.length);
                        if (read == -1) {
                            break;
                        } else if (read > 0) {
                            byteArrayBuffer.append(bArr, 0, read);
                        }
                    }
                    this.d = byteArrayBuffer.toByteArray();
                    i = 0;
                    k.a("uppay", "HttpConn.connect() ---");
                    return i;
                }
            } else if (this.b.getStatusLine().getStatusCode() == 401) {
                i = 8;
                k.a("uppay", "HttpConn.connect() ---");
                return i;
            } else {
                k.c("uppay", "http status code:" + this.b.getStatusLine().getStatusCode());
            }
            i = 1;
        } catch (SSLHandshakeException e) {
            k.a("uppay", "e0:" + e.getMessage());
            i = 4;
        } catch (IOException e2) {
            k.c("uppay", new StringBuilder("e1: ").append(e2).toString() != null ? e2.getMessage() : "e == null");
            i = 1;
        } catch (IllegalStateException e3) {
            k.c("uppay", new StringBuilder("e2: ").append(e3).toString() != null ? e3.getMessage() : "e == null");
            i = 1;
        } catch (Exception e4) {
            k.c("uppay", new StringBuilder("e3: ").append(e4).toString() != null ? e4.getMessage() : "e == null");
            i = 1;
        }
        k.a("uppay", "HttpConn.connect() ---");
        return i;
    }

    public final byte[] b() {
        return this.d;
    }

    public final String c() {
        if (this.d == null) {
            return null;
        }
        String str;
        try {
            k.a("uppay", this.d.toString());
            str = new String(this.d, "utf-8");
            try {
                k.a("uppay", "respon:" + str);
                return str;
            } catch (UnsupportedEncodingException e) {
            }
        } catch (UnsupportedEncodingException e2) {
            str = null;
            k.c("uppay", "convert response to utf-8 error!!!!");
            return str;
        }
    }
}
