package com.ecjia.hamster.paycenter.c.a;

import android.util.Log;
import anet.channel.util.HttpConstant;
import com.taobao.accs.common.Constants;
import com.umeng.message.util.HttpRequest;
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
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

/* compiled from: WXPayUtil */
public class a {

    /* compiled from: WXPayUtil */
    private static class a extends SSLSocketFactory {
        SSLContext a = SSLContext.getInstance("TLS");

        /* compiled from: WXPayUtil */
        class a_a_1 implements X509TrustManager {
            final /* synthetic */ a a;

            a_a_1(a aVar) {
                this.a = aVar;
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }
        }

        public a(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            a_a_1 com_ecjia_hamster_paycenter_c_a_a_a_1 = new a_a_1(this);
            this.a.init(null, new TrustManager[]{com_ecjia_hamster_paycenter_c_a_a_a_1}, null);
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }
    }

    public static byte[] a(String str, String str2) {
        byte[] bArr = null;
        if (str == null || str.length() == 0) {
            Log.e("SDK_Sample.Util", "httpPost, url is null");
        } else {
            HttpClient a = a();
            HttpUriRequest httpPost = new HttpPost(str);
            try {
                httpPost.setEntity(new StringEntity(str2));
                httpPost.setHeader("Accept", HttpRequest.CONTENT_TYPE_JSON);
                httpPost.setHeader("Content-type", HttpRequest.CONTENT_TYPE_JSON);
                HttpResponse execute = a.execute(httpPost);
                if (execute.getStatusLine().getStatusCode() != 200) {
                    Log.e("SDK_Sample.Util", "httpGet fail, status code = " + execute.getStatusLine().getStatusCode());
                } else {
                    bArr = EntityUtils.toByteArray(execute.getEntity());
                }
            } catch (Exception e) {
                Log.e("SDK_Sample.Util", "httpPost exception, e = " + e.getMessage());
                e.printStackTrace();
            }
        }
        return bArr;
    }

    private static HttpClient a() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory aVar = new a(instance);
            aVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme(HttpConstant.HTTPS, aVar, Constants.PORT));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
}
