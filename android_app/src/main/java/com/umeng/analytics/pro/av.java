package com.umeng.analytics.pro;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import com.umeng.message.MsgConstant;
import com.umeng.message.util.HttpRequest;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;

/* compiled from: NetworkHelper */
public class av {
    private String a;
    private String b = "10.0.0.172";
    private int c = 80;
    private Context d;
    private au e;

    public av(Context context) {
        this.d = context;
        this.a = a(context);
    }

    public void a(au auVar) {
        this.e = auVar;
    }

    private void a() {
        String d = af.a(this.d).b().d("");
        String c = af.a(this.d).b().c("");
        if (!TextUtils.isEmpty(d)) {
            a.f = br.b(d);
        }
        if (!TextUtils.isEmpty(c)) {
            a.g = br.b(c);
        }
        a.h = new String[]{a.f, a.g};
        int b = be.a(this.d).b();
        if (b == -1) {
            return;
        }
        if (b == 0) {
            a.h = new String[]{a.f, a.g};
        } else if (b == 1) {
            a.h = new String[]{a.g, a.f};
        }
    }

    public byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        a();
        for (String a : a.h) {
            bArr2 = a(bArr, a);
            if (bArr2 != null) {
                if (this.e != null) {
                    this.e.c();
                }
                return bArr2;
            }
            if (this.e != null) {
                this.e.d();
            }
        }
        return bArr2;
    }

    private boolean b() {
        if (this.d.getPackageManager().checkPermission(MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, this.d.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.d.getSystemService("connectivity");
            if (!bt.a(this.d, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
        }
    }

    private byte[] a(byte[] bArr, String str) {
        InputStream inputStream;
        Throwable th;
        HttpURLConnection httpURLConnection;
        try {
            if (this.e != null) {
                this.e.a();
            }
            if (b()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Type.HTTP, new InetSocketAddress(this.b, this.c)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("X-Umeng-Sdk", this.a);
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "envelope/json");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                if (VERSION.SDK_INT < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                if (this.e != null) {
                    this.e.b();
                }
                int responseCode = httpURLConnection.getResponseCode();
                Object headerField = httpURLConnection.getHeaderField(HttpRequest.HEADER_CONTENT_TYPE);
                if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                    headerField = null;
                } else {
                    headerField = 1;
                }
                if (responseCode != 200 || r0 == null) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                }
                bw.c("Send message to " + str);
                inputStream = httpURLConnection.getInputStream();
                byte[] b = bu.b(inputStream);
                bu.c(inputStream);
                if (httpURLConnection == null) {
                    return b;
                }
                httpURLConnection.disconnect();
                return b;
            } catch (Throwable th2) {
                th = th2;
                try {
                    bw.e("IOException,Failed to send message.", th);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android");
        stringBuffer.append("/");
        stringBuffer.append(bq.a);
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(bt.v(context));
            stringBuffer2.append("/");
            stringBuffer2.append(bt.b(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append("/");
            stringBuffer2.append(VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(bu.a(AnalyticsConfig.getAppkey(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), "UTF-8"));
        } catch (Throwable th) {
        }
        return stringBuffer.toString();
    }
}
