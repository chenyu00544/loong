package com.unionpay.mobile.android.h;

import android.content.Context;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class a implements LayeredSocketFactory, SocketFactory {
    private SSLContext a = null;
    private Context b;

    public a(Context context) {
        this.b = context;
    }

    private SSLContext a() throws IOException {
        if (this.a == null) {
            this.a = a(this.b);
        }
        return this.a;
    }

    private static SSLContext a(Context context) throws IOException {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{new b(context)}, null);
            return instance;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) throws IOException, UnknownHostException, ConnectTimeoutException {
        int connectionTimeout = HttpConnectionParams.getConnectionTimeout(httpParams);
        int soTimeout = HttpConnectionParams.getSoTimeout(httpParams);
        SocketAddress inetSocketAddress = new InetSocketAddress(str, i);
        SSLSocket sSLSocket = (SSLSocket) (socket != null ? socket : createSocket());
        if (inetAddress != null || i2 > 0) {
            if (i2 < 0) {
                i2 = 0;
            }
            sSLSocket.bind(new InetSocketAddress(inetAddress, i2));
        }
        sSLSocket.connect(inetSocketAddress, connectionTimeout);
        sSLSocket.setSoTimeout(soTimeout);
        return sSLSocket;
    }

    public Socket createSocket() throws IOException {
        return a().getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        return a().getSocketFactory().createSocket(socket, str, i, z);
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(a.class);
    }

    public int hashCode() {
        return a.class.hashCode();
    }

    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        return true;
    }
}
