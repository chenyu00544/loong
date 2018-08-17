package com.sina.weibo.sdk.statistic;

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
import org.apache.http.conn.ssl.SSLSocketFactory;

class SSLSocketFactoryImp extends SSLSocketFactory {
    final SSLContext sslContext = SSLContext.getInstance("TLS");

    class SSLSocketFactoryImp_1 implements X509TrustManager {
        SSLSocketFactoryImp_1() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }
    }

    public SSLSocketFactoryImp(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        SSLSocketFactoryImp_1 sSLSocketFactoryImp_1 = new SSLSocketFactoryImp_1();
        this.sslContext.init(null, new TrustManager[]{sSLSocketFactoryImp_1}, null);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        return this.sslContext.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket createSocket() throws IOException {
        return this.sslContext.getSocketFactory().createSocket();
    }
}
