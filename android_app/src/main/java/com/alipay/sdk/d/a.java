package com.alipay.sdk.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.util.HttpRequest;
import java.net.URL;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpParams;

public final class a {
    public String a;
    private Context b;

    public a(Context context, String str) {
        this.b = context;
        this.a = str;
    }

    private URL a() {
        try {
            return new URL(this.a);
        } catch (Exception e) {
            return null;
        }
    }

    public final HttpResponse a(byte[] bArr, List<Header> list) throws Throwable {
        HttpResponse a;
        Object obj = null;
        new StringBuilder("requestUrl : ").append(this.a);
        b a2 = b.a();
        if (a2 != null) {
            try {
                HttpUriRequest httpGet;
                HttpParams params = a2.b.getParams();
                String c;
                if (VERSION.SDK_INT >= 11) {
                    c = c();
                    if (c == null || c.contains("wap")) {
                        URL a3 = a();
                        if (a3 != null) {
                            HttpConstant.HTTPS.equalsIgnoreCase(a3.getProtocol());
                            Object property = System.getProperty("https.proxyHost");
                            String property2 = System.getProperty("https.proxyPort");
                            if (!TextUtils.isEmpty(property)) {
                                obj = new HttpHost(property, Integer.parseInt(property2));
                            }
                        }
                    }
                } else {
                    NetworkInfo b = b();
                    if (b != null && b.isAvailable() && b.getType() == 0) {
                        c = Proxy.getDefaultHost();
                        int defaultPort = Proxy.getDefaultPort();
                        if (c != null) {
                            obj = new HttpHost(c, defaultPort);
                        }
                    }
                }
                if (obj != null) {
                    params.setParameter("http.route.default-proxy", obj);
                }
                if (bArr == null || bArr.length == 0) {
                    httpGet = new HttpGet(this.a);
                } else {
                    httpGet = new HttpPost(this.a);
                    HttpEntity byteArrayEntity = new ByteArrayEntity(bArr);
                    byteArrayEntity.setContentType("application/octet-stream;binary/octet-stream");
                    ((HttpPost) httpGet).setEntity(byteArrayEntity);
                    httpGet.addHeader(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8");
                    httpGet.addHeader(HttpConstant.CONNECTION, "Keep-Alive");
                    httpGet.addHeader("Keep-Alive", "timeout=180, max=100");
                }
                if (list != null) {
                    for (Header addHeader : list) {
                        httpGet.addHeader(addHeader);
                    }
                }
                a = a2.a(httpGet);
                Header[] headers = a.getHeaders("X-Hostname");
                if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                    a.getHeaders("X-Hostname")[0].toString();
                }
                headers = a.getHeaders("X-ExecuteTime");
                if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                    a.getHeaders("X-ExecuteTime")[0].toString();
                }
            } catch (Throwable th) {
            }
        }
        return a;
    }

    private NetworkInfo b() {
        try {
            return ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }

    private String c() {
        try {
            NetworkInfo b = b();
            if (b == null || !b.isAvailable()) {
                return UInAppMessage.NONE;
            }
            if (b.getType() == 1) {
                return UtilityImpl.NET_TYPE_WIFI;
            }
            return b.getExtraInfo().toLowerCase();
        } catch (Exception e) {
            return UInAppMessage.NONE;
        }
    }
}
