package com.baidu.location.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.util.Log;
import anet.channel.util.HttpConstant;
import com.baidu.location.f;
import com.taobao.accs.common.Constants;
import com.umeng.message.util.HttpRequest;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public abstract class m implements f {
    public static int c8 = g.w;
    private static String dd = "10.0.0.172";
    protected static int de = 0;
    private static int df = 80;
    public String c5 = null;
    public HttpEntity c6 = null;
    public List c7 = null;
    private boolean c9 = false;
    public String da = null;
    public byte[] db = null;
    public String dc = null;
    public int dg = 3;

    class m_1 extends Thread {
        final /* synthetic */ m a;

        m_1(m mVar) {
            this.a = mVar;
        }

        public void run() {
            int i;
            boolean z;
            this.a.c5 = k.Z();
            this.a.au();
            int i2 = this.a.dg;
            this.a.am();
            if (m.c8 == g.byte) {
                i = i2;
                z = true;
            } else {
                i = i2;
                z = false;
            }
            while (i > 0) {
                try {
                    Object httpGet = new HttpGet(this.a.c5);
                    httpGet.setHeader(HttpRequest.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8");
                    httpGet.setHeader(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8;");
                    HttpClient defaultHttpClient = new DefaultHttpClient();
                    defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.Q));
                    defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.Q));
                    HttpProtocolParams.setUseExpectContinue(defaultHttpClient.getParams(), false);
                    if (z) {
                        defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(m.dd, m.df, HttpConstant.HTTP));
                    }
                    z = !z;
                    HttpResponse execute = defaultHttpClient.execute(httpGet);
                    if (execute.getStatusLine().getStatusCode() == 200) {
                        this.a.c6 = execute.getEntity();
                        this.a.int(true);
                        break;
                    }
                    httpGet.abort();
                    i--;
                } catch (Exception e) {
                    Log.d(g.m, "NetworkCommunicationException!");
                }
            }
            if (i <= 0) {
                m.de++;
                this.a.c6 = null;
                this.a.int(false);
            } else {
                m.de = 0;
            }
            this.a.c9 = false;
        }
    }

    class m_2 extends Thread {
        final /* synthetic */ m a;

        m_2(m mVar) {
            this.a = mVar;
        }

        public void run() {
            int i;
            HttpEntity httpEntity;
            boolean z;
            HttpPost httpPost;
            Object obj;
            Object obj2;
            this.a.c5 = k.Z();
            this.a.au();
            int i2 = this.a.dg;
            this.a.am();
            if (m.c8 == g.byte) {
                i = i2;
                httpEntity = null;
                z = true;
            } else {
                i = i2;
                httpEntity = null;
                z = false;
            }
            while (i > 0) {
                try {
                    httpPost = new HttpPost(this.a.c5);
                    try {
                        httpEntity = new UrlEncodedFormEntity(this.a.c7, "utf-8");
                        httpPost.setHeader(HttpRequest.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8");
                        httpPost.setHeader(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8;");
                        httpPost.setEntity(httpEntity);
                        HttpClient defaultHttpClient = new DefaultHttpClient();
                        defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.Q));
                        defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.Q));
                        HttpProtocolParams.setUseExpectContinue(defaultHttpClient.getParams(), false);
                        if (z) {
                            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(m.dd, m.df, HttpConstant.HTTP));
                        }
                        z = !z;
                        HttpResponse execute = defaultHttpClient.execute(httpPost);
                        if (execute.getStatusLine().getStatusCode() == 200) {
                            this.a.c6 = execute.getEntity();
                            this.a.int(true);
                            break;
                        }
                        httpPost.abort();
                        i--;
                        obj = httpPost;
                    } catch (Exception e) {
                    } catch (Error e2) {
                        httpPost.abort();
                        Log.d(g.m, "NetworkCommunicationException!");
                        i--;
                        obj = httpPost;
                    }
                } catch (Exception e3) {
                    obj2 = httpEntity;
                    httpPost.abort();
                    Log.d(g.m, "NetworkCommunicationException!");
                    i--;
                    obj = httpPost;
                } catch (Error e4) {
                    obj2 = httpEntity;
                    httpPost.abort();
                    Log.d(g.m, "NetworkCommunicationException!");
                    i--;
                    obj = httpPost;
                }
            }
            if (i <= 0) {
                m.de++;
                this.a.c6 = null;
                this.a.int(false);
            } else {
                m.de = 0;
            }
            this.a.c9 = false;
        }
    }

    class m_3 extends Thread {
        final /* synthetic */ m a;

        m_3(m mVar) {
            this.a = mVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r11 = this;
            r6 = 0;
            r3 = 1;
            r4 = 0;
            r0 = r11.a;
            r1 = com.baidu.location.b.k.Z();
            r0.c5 = r1;
            r0 = r11.a;
            r0.am();
            r0 = r11.a;
            r0.au();
            r0 = r11.a;
            r0 = r0.dg;
            r1 = com.baidu.location.b.m.c8;
            r2 = com.baidu.location.b.g.byte;
            if (r1 != r2) goto L_0x014b;
        L_0x001f:
            r5 = r0;
            r2 = r6;
            r0 = r3;
        L_0x0022:
            if (r5 <= 0) goto L_0x0117;
        L_0x0024:
            r1 = new org.apache.http.client.methods.HttpPost;	 Catch:{ Exception -> 0x0144, Error -> 0x0141 }
            r7 = r11.a;	 Catch:{ Exception -> 0x0144, Error -> 0x0141 }
            r7 = r7.c5;	 Catch:{ Exception -> 0x0144, Error -> 0x0141 }
            r1.<init>(r7);	 Catch:{ Exception -> 0x0144, Error -> 0x0141 }
            r2 = new org.apache.http.client.entity.UrlEncodedFormEntity;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = r11.a;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = r7.c7;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = "utf-8";
            r2.<init>(r7, r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = "Content-Type";
            r8 = "application/x-www-form-urlencoded; charset=utf-8";
            r1.setHeader(r7, r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = "Accept-Charset";
            r8 = "UTF-8;";
            r1.setHeader(r7, r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = "Host";
            r8 = "loc.map.baidu.com";
            r1.setHeader(r7, r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = "Accept-Encoding";
            r8 = "gzip";
            r1.addHeader(r7, r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r1.setEntity(r2);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r2 = new org.apache.http.impl.client.DefaultHttpClient;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r2.<init>();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = r2.getParams();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = "http.connection.timeout";
            r9 = com.baidu.location.b.g.Q;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7.setParameter(r8, r9);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = r2.getParams();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = "http.socket.timeout";
            r9 = com.baidu.location.b.g.Q;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7.setParameter(r8, r9);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = r2.getParams();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = 0;
            org.apache.http.params.HttpProtocolParams.setUseExpectContinue(r7, r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            if (r0 == 0) goto L_0x009c;
        L_0x0084:
            r7 = new org.apache.http.HttpHost;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = com.baidu.location.b.m.dd;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r9 = com.baidu.location.b.m.df;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r10 = "http";
            r7.<init>(r8, r9, r10);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = r2.getParams();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r9 = "http.route.default-proxy";
            r8.setParameter(r9, r7);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
        L_0x009c:
            if (r0 != 0) goto L_0x00f6;
        L_0x009e:
            r0 = r3;
        L_0x009f:
            r2 = r2.execute(r1);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = r2.getStatusLine();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = r7.getStatusCode();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r7 != r8) goto L_0x012e;
        L_0x00af:
            r7 = r2.getEntity();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = r7.getContent();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = "Content-Encoding";
            r2 = r2.getFirstHeader(r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            if (r2 == 0) goto L_0x0149;
        L_0x00bf:
            r2 = r2.getValue();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = "gzip";
            r2 = r2.equalsIgnoreCase(r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            if (r2 == 0) goto L_0x0149;
        L_0x00cb:
            r2 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r8.<init>(r7);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r2.<init>(r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
        L_0x00d5:
            r7 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7.<init>();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
        L_0x00da:
            r8 = r2.read();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r9 = -1;
            if (r8 == r9) goto L_0x00f8;
        L_0x00e1:
            r7.write(r8);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            goto L_0x00da;
        L_0x00e5:
            r2 = move-exception;
        L_0x00e6:
            r1.abort();
            r2 = com.baidu.location.b.g.m;
            r7 = "NetworkCommunicationException!";
            android.util.Log.d(r2, r7);
        L_0x00f0:
            r2 = r5 + -1;
            r5 = r2;
            r2 = r1;
            goto L_0x0022;
        L_0x00f6:
            r0 = r4;
            goto L_0x009f;
        L_0x00f8:
            r8 = r7.toString();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            if (r2 == 0) goto L_0x0101;
        L_0x00fe:
            r2.close();	 Catch:{ Exception -> 0x0147, Error -> 0x0132 }
        L_0x0101:
            if (r7 == 0) goto L_0x0106;
        L_0x0103:
            r7.close();	 Catch:{ Exception -> 0x0147, Error -> 0x0132 }
        L_0x0106:
            r2 = r11.a;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = new org.apache.http.entity.StringEntity;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r9 = "UTF-8";
            r7.<init>(r8, r9);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r2.c6 = r7;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r2 = r11.a;	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            r7 = 1;
            r2.int(r7);	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
        L_0x0117:
            if (r5 > 0) goto L_0x013e;
        L_0x0119:
            r0 = com.baidu.location.b.m.de;
            r0 = r0 + 1;
            com.baidu.location.b.m.de = r0;
            r0 = r11.a;
            r0.c6 = r6;
            r0 = r11.a;
            r0.int(r4);
        L_0x0128:
            r0 = r11.a;
            r0.c9 = r4;
            return;
        L_0x012e:
            r1.abort();	 Catch:{ Exception -> 0x00e5, Error -> 0x0132 }
            goto L_0x00f0;
        L_0x0132:
            r2 = move-exception;
        L_0x0133:
            r1.abort();
            r2 = com.baidu.location.b.g.m;
            r7 = "NetworkCommunicationException!";
            android.util.Log.d(r2, r7);
            goto L_0x00f0;
        L_0x013e:
            com.baidu.location.b.m.de = r4;
            goto L_0x0128;
        L_0x0141:
            r1 = move-exception;
            r1 = r2;
            goto L_0x0133;
        L_0x0144:
            r1 = move-exception;
            r1 = r2;
            goto L_0x00e6;
        L_0x0147:
            r2 = move-exception;
            goto L_0x0106;
        L_0x0149:
            r2 = r7;
            goto L_0x00d5;
        L_0x014b:
            r5 = r0;
            r2 = r6;
            r0 = r4;
            goto L_0x0022;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.m.3.run():void");
        }
    }

    class m_4 extends Thread {
        final /* synthetic */ m a;

        m_4(m mVar) {
            this.a = mVar;
        }

        public void run() {
            this.a.c5 = k.Z();
            this.a.au();
            int i = this.a.dg;
            this.a.am();
            int i2 = i;
            HttpEntity httpEntity = null;
            while (i2 > 0) {
                HttpPost httpPost;
                Object obj;
                try {
                    httpPost = new HttpPost(this.a.c5);
                    try {
                        httpEntity = new FileEntity(new File(this.a.dc), "binary/octet-stream");
                        httpPost.setHeader(HttpRequest.HEADER_CONTENT_TYPE, "application/octet-stream");
                        httpPost.setHeader(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8;");
                        httpPost.setEntity(httpEntity);
                        HttpClient defaultHttpClient = new DefaultHttpClient();
                        defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.Q));
                        defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.d));
                        HttpProtocolParams.setUseExpectContinue(defaultHttpClient.getParams(), false);
                        if ((m.c8 == g.byte || m.c8 == g.w) && (this.a.dg - i2) % 2 == 0) {
                            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(m.dd, m.df, HttpConstant.HTTP));
                        }
                        HttpResponse execute = defaultHttpClient.execute(httpPost);
                        if (execute.getStatusLine().getStatusCode() == 200) {
                            this.a.c6 = execute.getEntity();
                            this.a.int(true);
                            break;
                        }
                        httpPost.abort();
                        i2--;
                        obj = httpPost;
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                    Object obj2 = httpEntity;
                    httpPost.abort();
                    Log.d(g.m, "NetworkCommunicationException!");
                    i2--;
                    obj = httpPost;
                }
            }
            if (i2 <= 0) {
                m.de++;
                this.a.c6 = null;
                this.a.int(false);
            } else {
                m.de = 0;
            }
            this.a.c9 = false;
        }
    }

    class m_5 extends Thread {
        final /* synthetic */ m a;

        m_5(m mVar) {
            this.a = mVar;
        }

        public void run() {
            int i;
            HttpEntity httpEntity;
            boolean z;
            this.a.c5 = k.Z();
            this.a.au();
            int i2 = this.a.dg;
            this.a.am();
            if (m.c8 == g.byte) {
                i = i2;
                httpEntity = null;
                z = true;
            } else {
                i = i2;
                httpEntity = null;
                z = false;
            }
            while (i > 0) {
                HttpPost httpPost;
                Object obj;
                try {
                    httpPost = new HttpPost(this.a.c5);
                    try {
                        httpEntity = new ByteArrayEntity(this.a.db);
                        httpPost.setHeader(HttpRequest.HEADER_CONTENT_TYPE, "application/octet-stream");
                        httpPost.setHeader(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8;");
                        httpPost.setEntity(httpEntity);
                        HttpClient defaultHttpClient = new DefaultHttpClient();
                        defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.Q));
                        defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.Q));
                        HttpProtocolParams.setUseExpectContinue(defaultHttpClient.getParams(), false);
                        if (z) {
                            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(m.dd, m.df, HttpConstant.HTTP));
                        }
                        z = !z;
                        HttpResponse execute = defaultHttpClient.execute(httpPost);
                        if (execute.getStatusLine().getStatusCode() == 200) {
                            this.a.c6 = execute.getEntity();
                            this.a.int(true);
                            break;
                        }
                        httpPost.abort();
                        i--;
                        obj = httpPost;
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                    Object obj2 = httpEntity;
                    httpPost.abort();
                    Log.d(g.m, "NetworkCommunicationException!");
                    i--;
                    obj = httpPost;
                }
            }
            if (i <= 0) {
                m.de++;
                this.a.c6 = null;
                this.a.int(false);
            } else {
                m.de = 0;
            }
            this.a.db = null;
            this.a.c9 = false;
        }
    }

    class m_6 extends Thread {
        final /* synthetic */ m a;

        m_6(m mVar) {
            this.a = mVar;
        }

        public void run() {
            int i;
            HttpEntity httpEntity;
            boolean z;
            HttpPost httpPost;
            Object obj;
            this.a.c5 = k.ab();
            this.a.au();
            int i2 = this.a.dg;
            this.a.am();
            if (m.c8 == g.byte) {
                i = i2;
                httpEntity = null;
                z = true;
            } else {
                i = i2;
                httpEntity = null;
                z = false;
            }
            while (i > 0) {
                try {
                    httpPost = new HttpPost(this.a.c5);
                    try {
                        httpEntity = new StringEntity(this.a.da, "UTF-8");
                        httpPost.setHeader(HttpRequest.HEADER_CONTENT_TYPE, "text/xml");
                        httpPost.setEntity(httpEntity);
                        HttpParams basicHttpParams = new BasicHttpParams();
                        HttpConnectionParams.setConnectionTimeout(basicHttpParams, g.Q);
                        HttpConnectionParams.setSoTimeout(basicHttpParams, g.Q);
                        HttpClient httpClient = m.if(basicHttpParams);
                        HttpProtocolParams.setUseExpectContinue(httpClient.getParams(), false);
                        if (z) {
                            httpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(m.dd, m.df, HttpConstant.HTTP));
                        }
                        z = !z;
                        HttpResponse execute = httpClient.execute(httpPost);
                        if (execute.getStatusLine().getStatusCode() == 200) {
                            this.a.c6 = execute.getEntity();
                            this.a.int(true);
                            break;
                        }
                        httpPost.abort();
                        i--;
                        obj = httpPost;
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                    Object obj2 = httpEntity;
                    httpPost.abort();
                    Log.d(g.m, "NetworkCommunicationException!");
                    i--;
                    obj = httpPost;
                }
            }
            if (i <= 0) {
                m.de++;
                this.a.c6 = null;
                this.a.int(false);
            } else {
                m.de = 0;
            }
            this.a.c9 = false;
        }
    }

    public static class a extends SSLSocketFactory {
        final SSLContext a = SSLContext.getInstance("TLS");

        class m_a_1 implements X509TrustManager {
            final /* synthetic */ a a;

            m_a_1(a aVar) {
                this.a = aVar;
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }

        public a(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            m_a_1 com_baidu_location_b_m_a_1 = new m_a_1(this);
            this.a.init(null, new TrustManager[]{com_baidu_location_b_m_a_1}, null);
        }

        public Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(socket, str, i, z);
        }
    }

    private void am() {
        c8 = aq();
    }

    private int aq() {
        Context serviceContext = f.getServiceContext();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) serviceContext.getSystemService("connectivity");
            if (connectivityManager == null) {
                return g.w;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return g.w;
            }
            if (activeNetworkInfo.getType() != 1) {
                return if(serviceContext, activeNetworkInfo);
            }
            String defaultHost = Proxy.getDefaultHost();
            return (defaultHost == null || defaultHost.length() <= 0) ? g.o : g.p;
        } catch (Exception e) {
            return g.w;
        }
    }

    private static int if(Context context, NetworkInfo networkInfo) {
        String toLowerCase;
        if (!(networkInfo == null || networkInfo.getExtraInfo() == null)) {
            toLowerCase = networkInfo.getExtraInfo().toLowerCase();
            if (toLowerCase != null) {
                if (toLowerCase.startsWith("cmwap") || toLowerCase.startsWith("uniwap") || toLowerCase.startsWith("3gwap")) {
                    toLowerCase = Proxy.getDefaultHost();
                    if (toLowerCase == null || toLowerCase.equals("") || toLowerCase.equals("null")) {
                        toLowerCase = "10.0.0.172";
                    }
                    dd = toLowerCase;
                    return g.byte;
                } else if (toLowerCase.startsWith("ctwap")) {
                    toLowerCase = Proxy.getDefaultHost();
                    if (toLowerCase == null || toLowerCase.equals("") || toLowerCase.equals("null")) {
                        toLowerCase = "10.0.0.200";
                    }
                    dd = toLowerCase;
                    return g.byte;
                } else if (toLowerCase.startsWith("cmnet") || toLowerCase.startsWith("uninet") || toLowerCase.startsWith("ctnet") || toLowerCase.startsWith("3gnet")) {
                    return g.for;
                }
            }
        }
        toLowerCase = Proxy.getDefaultHost();
        if (toLowerCase != null && toLowerCase.length() > 0) {
            if ("10.0.0.172".equals(toLowerCase.trim())) {
                dd = "10.0.0.172";
                return g.byte;
            } else if ("10.0.0.200".equals(toLowerCase.trim())) {
                dd = "10.0.0.200";
                return g.byte;
            }
        }
        return g.for;
    }

    public static HttpClient if(HttpParams httpParams) {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory aVar = new a(instance);
            aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme(HttpConstant.HTTPS, aVar, Constants.PORT));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(httpParams, schemeRegistry), httpParams);
        } catch (Exception e) {
            return new DefaultHttpClient(httpParams);
        }
    }

    public static boolean int(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return connectivityManager.getActiveNetworkInfo() != null ? connectivityManager.getActiveNetworkInfo().isAvailable() : false;
        } catch (Exception e) {
            return false;
        }
    }

    public void an() {
        new m_5(this).start();
    }

    public void ao() {
        new m_3(this).start();
    }

    public void ap() {
        new m_2(this).start();
    }

    public void as() {
        new m_1(this).start();
    }

    public void at() {
        new m_4(this).start();
    }

    public abstract void au();

    public void av() {
        new m_6(this).start();
    }

    public abstract void int(boolean z);
}
