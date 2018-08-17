package com.unionpay.c;

import android.content.Context;
import android.preference.PreferenceManager;
import anet.channel.util.HttpConstant;
import com.umeng.message.util.HttpRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class aa {
    public static int a = 60000;
    public static int b = 60000;
    public static volatile HashMap c = new HashMap();
    private static Context d = null;
    private static volatile HashMap e = new HashMap();

    static class a {
        String a = null;
        String b = null;
        String c = null;
        String d = null;
        String e = null;

        a() {
        }
    }

    static class b implements X509TrustManager {
        X509Certificate a;

        b(X509Certificate x509Certificate) {
            this.a = x509Certificate;
        }

        public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            if (x509CertificateArr.length == 0) {
                throw new CertificateException("No server certificate found!");
            } else if (x509CertificateArr[0].getIssuerDN().equals(this.a.getSubjectDN())) {
                try {
                    String name = x509CertificateArr[0].getSubjectDN().getName();
                    int indexOf = name.indexOf("CN=");
                    if (indexOf >= 0) {
                        name = name.substring(indexOf + 3);
                        indexOf = name.indexOf(",");
                        if (indexOf >= 0) {
                            name = name.substring(0, indexOf);
                        }
                    }
                    String[] split = name.split("\\.");
                    String str2 = split.length >= 2 ? split[split.length - 2] + "." + split[split.length - 1] : name;
                    if (!aa.c.containsKey(Long.valueOf(Thread.currentThread().getId()))) {
                        throw new CertificateException("No valid host provided!");
                    } else if (((String) aa.c.get(Long.valueOf(Thread.currentThread().getId()))).endsWith(str2)) {
                        x509CertificateArr[0].verify(this.a.getPublicKey());
                        x509CertificateArr[0].checkValidity();
                    } else {
                        throw new CertificateException("Server certificate has incorrect host name!");
                    }
                } catch (Throwable th) {
                    if (th instanceof CertificateException) {
                        CertificateException certificateException = new CertificateException(th);
                    }
                }
            } else {
                throw new CertificateException("Parent certificate of server was different than expected signing certificate");
            }
        }

        public final X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public static class c {
        public int a;
        public String b;

        c() {
            this(600, "");
        }

        c(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    public static c a(Context context, String str, String str2, String str3, String str4, byte[] bArr) {
        d = context;
        a(str, str2);
        return a(str, str3, str4, bArr);
    }

    private static c a(String str, String str2, String str3, byte[] bArr) {
        c cVar = new c();
        try {
            if (a(str, 2) != null) {
                cVar = a(a(str, 2), str2, str3, bArr, str);
                if (cVar.a == 600) {
                    a(str, null, 2);
                }
            } else {
                if (a(str, 1) != null) {
                    cVar = a(a(str, 1), str2, str3, bArr, str);
                    if (cVar.a != 600) {
                        a(str, a(str, 1), 2);
                        String a = a(str, 1);
                        if (!(a == null || a.equalsIgnoreCase(a(str, 3)) || d == null)) {
                            PreferenceManager.getDefaultSharedPreferences(d).edit().putString(am.d(str), a(str, 1)).apply();
                            a(str, a(str, 1), 3);
                        }
                    }
                }
                if (cVar.a == 600 && a(str, 3) != null) {
                    cVar = a(a(str, 3), str2, str3, bArr, str);
                    if (cVar.a != 600) {
                        a(str, a(str, 3), 2);
                    }
                }
                if (cVar.a == 600) {
                    cVar = a(a(str, 4), str2, str3, bArr, str);
                    if (cVar.a != 600) {
                        a(str, a(str, 4), 2);
                    }
                }
            }
        } catch (Throwable th) {
        }
        return cVar;
    }

    private static c a(String str, String str2, String str3, byte[] bArr, String str4) {
        try {
            URL url;
            if (str2.startsWith("https://")) {
                c.put(Long.valueOf(Thread.currentThread().getId()), str4);
            }
            if (str2.toLowerCase().startsWith(HttpConstant.HTTPS) && str3.trim().isEmpty()) {
                url = new URL(str2);
            } else {
                url = new URL(str2);
                if (!ah.a()) {
                    url = new URL(url.getProtocol(), str, url.getPort(), url.getFile());
                }
            }
            URLConnection a = a(url, str4, true);
            if (a == null) {
                return new c(600, "");
            }
            if (str2.toLowerCase().startsWith(HttpConstant.HTTPS) && !str3.trim().isEmpty()) {
                a = a(a, str3);
            }
            return a(bArr, a);
        } catch (Throwable th) {
            return new c(600, "");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.unionpay.c.aa.c a(byte[] r8, java.net.URLConnection r9) {
        /*
        r2 = 0;
        r1 = 600; // 0x258 float:8.41E-43 double:2.964E-321;
        r7 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        if (r8 == 0) goto L_0x000d;
    L_0x0008:
        r0 = r8.length;
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        if (r9 != 0) goto L_0x0015;
    L_0x000d:
        r0 = new com.unionpay.c.aa$c;
        r2 = "";
        r0.<init>(r1, r2);
    L_0x0014:
        return r0;
    L_0x0015:
        r9 = (java.net.HttpURLConnection) r9;
        r5 = new java.lang.StringBuffer;
        r5.<init>();
        r0 = "POST";
        r9.setRequestMethod(r0);	 Catch:{ Throwable -> 0x00b6, all -> 0x0089 }
        r3 = r9.getOutputStream();	 Catch:{ Throwable -> 0x00b6, all -> 0x0089 }
        r3.write(r8);	 Catch:{ Throwable -> 0x00ba, all -> 0x00b1 }
        r3.close();	 Catch:{ Throwable -> 0x00ba, all -> 0x00b1 }
        r0 = r9.getResponseCode();	 Catch:{ Throwable -> 0x00ba, all -> 0x00b1 }
        r1 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r0 <= r1) goto L_0x0071;
    L_0x0033:
        r1 = r9.getErrorStream();	 Catch:{ Throwable -> 0x00bf, all -> 0x00b1 }
        r4 = r1;
    L_0x0038:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00bf, all -> 0x00b1 }
        r6 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x00bf, all -> 0x00b1 }
        r6.<init>(r4);	 Catch:{ Throwable -> 0x00bf, all -> 0x00b1 }
        r1.<init>(r6);	 Catch:{ Throwable -> 0x00bf, all -> 0x00b1 }
    L_0x0042:
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x0051, all -> 0x00b3 }
        if (r2 == 0) goto L_0x0077;
    L_0x0048:
        r5.append(r2);	 Catch:{ Throwable -> 0x0051, all -> 0x00b3 }
        r2 = 10;
        r5.append(r2);	 Catch:{ Throwable -> 0x0051, all -> 0x00b3 }
        goto L_0x0042;
    L_0x0051:
        r2 = move-exception;
        r2 = r3;
    L_0x0053:
        if (r2 == 0) goto L_0x0058;
    L_0x0055:
        r2.close();	 Catch:{ Throwable -> 0x00a5 }
    L_0x0058:
        if (r1 == 0) goto L_0x005d;
    L_0x005a:
        r1.close();	 Catch:{ Throwable -> 0x00a7 }
    L_0x005d:
        if (r9 == 0) goto L_0x0062;
    L_0x005f:
        r9.disconnect();	 Catch:{ Throwable -> 0x00a9 }
    L_0x0062:
        a = r7;
        b = r7;
    L_0x0066:
        r1 = new com.unionpay.c.aa$c;
        r2 = r5.toString();
        r1.<init>(r0, r2);
        r0 = r1;
        goto L_0x0014;
    L_0x0071:
        r1 = r9.getInputStream();	 Catch:{ Throwable -> 0x00bf, all -> 0x00b1 }
        r4 = r1;
        goto L_0x0038;
    L_0x0077:
        if (r3 == 0) goto L_0x007c;
    L_0x0079:
        r3.close();	 Catch:{ Throwable -> 0x009f }
    L_0x007c:
        r1.close();	 Catch:{ Throwable -> 0x00a1 }
    L_0x007f:
        if (r9 == 0) goto L_0x0084;
    L_0x0081:
        r9.disconnect();	 Catch:{ Throwable -> 0x00a3 }
    L_0x0084:
        a = r7;
        b = r7;
        goto L_0x0066;
    L_0x0089:
        r0 = move-exception;
        r3 = r2;
    L_0x008b:
        if (r3 == 0) goto L_0x0090;
    L_0x008d:
        r3.close();	 Catch:{ Throwable -> 0x00ab }
    L_0x0090:
        if (r2 == 0) goto L_0x0095;
    L_0x0092:
        r2.close();	 Catch:{ Throwable -> 0x00ad }
    L_0x0095:
        if (r9 == 0) goto L_0x009a;
    L_0x0097:
        r9.disconnect();	 Catch:{ Throwable -> 0x00af }
    L_0x009a:
        a = r7;
        b = r7;
        throw r0;
    L_0x009f:
        r2 = move-exception;
        goto L_0x007c;
    L_0x00a1:
        r1 = move-exception;
        goto L_0x007f;
    L_0x00a3:
        r1 = move-exception;
        goto L_0x0084;
    L_0x00a5:
        r2 = move-exception;
        goto L_0x0058;
    L_0x00a7:
        r1 = move-exception;
        goto L_0x005d;
    L_0x00a9:
        r1 = move-exception;
        goto L_0x0062;
    L_0x00ab:
        r1 = move-exception;
        goto L_0x0090;
    L_0x00ad:
        r1 = move-exception;
        goto L_0x0095;
    L_0x00af:
        r1 = move-exception;
        goto L_0x009a;
    L_0x00b1:
        r0 = move-exception;
        goto L_0x008b;
    L_0x00b3:
        r0 = move-exception;
        r2 = r1;
        goto L_0x008b;
    L_0x00b6:
        r0 = move-exception;
        r0 = r1;
        r1 = r2;
        goto L_0x0053;
    L_0x00ba:
        r0 = move-exception;
        r0 = r1;
        r1 = r2;
        r2 = r3;
        goto L_0x0053;
    L_0x00bf:
        r1 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0053;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.c.aa.a(byte[], java.net.URLConnection):com.unionpay.c.aa$c");
    }

    private static synchronized String a(String str, int i) {
        String str2;
        synchronized (aa.class) {
            if (!am.b(str) && e.containsKey(str)) {
                a aVar = (a) e.get(str);
                if (aVar != null) {
                    switch (i) {
                        case 1:
                            str2 = aVar.b;
                            break;
                        case 2:
                            str2 = aVar.d;
                            break;
                        case 3:
                            str2 = aVar.c;
                            break;
                        case 4:
                            str2 = aVar.a;
                            break;
                        default:
                            str2 = null;
                            break;
                    }
                }
                str2 = null;
            } else {
                str2 = null;
            }
        }
        return str2;
    }

    private static URLConnection a(URL url, String str, boolean z) {
        try {
            URLConnection openConnection = url.openConnection();
            openConnection.setRequestProperty("Accept-Encoding", "");
            openConnection.setRequestProperty(HttpRequest.HEADER_USER_AGENT, "");
            if (str != null) {
                openConnection.setRequestProperty(HttpConstant.HOST, str);
                openConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "");
            }
            openConnection.setDoInput(true);
            if (z) {
                openConnection.setDoOutput(true);
            }
            openConnection.setConnectTimeout(a);
            openConnection.setReadTimeout(b);
            return openConnection;
        } catch (Throwable th) {
            return null;
        }
    }

    private static X509Certificate a(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream);
            try {
                byteArrayInputStream.close();
                return x509Certificate;
            } catch (Throwable th) {
                return x509Certificate;
            }
        } catch (Exception e) {
            byteArrayInputStream.close();
            return null;
        } catch (Throwable th2) {
        }
    }

    private static HttpsURLConnection a(URLConnection uRLConnection, String str) {
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
            SSLContext instance = am.a(16) ? SSLContext.getInstance("TLSv1.2") : SSLContext.getInstance("TLSv1");
            instance.init(null, new TrustManager[]{new b(a(str))}, null);
            httpsURLConnection.setHostnameVerifier(new o());
            httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
            return httpsURLConnection;
        } catch (Throwable th) {
            return null;
        }
    }

    private static synchronized void a(String str, String str2) {
        synchronized (aa.class) {
            if (!(am.b(str) || e.containsKey(str))) {
                a aVar = new a();
                aVar.e = str;
                aVar.a = str2;
                aVar.c = PreferenceManager.getDefaultSharedPreferences(d).getString(am.d(str), null);
                try {
                    aVar.b = InetAddress.getByName(str).getHostAddress();
                } catch (Throwable th) {
                }
                e.put(str, aVar);
            }
        }
    }

    private static synchronized void a(String str, String str2, int i) {
        synchronized (aa.class) {
            if (!am.b(str) && e.containsKey(str)) {
                a aVar = (a) e.get(str);
                switch (i) {
                    case 1:
                        aVar.b = str2;
                        break;
                    case 2:
                        aVar.d = str2;
                        break;
                    case 3:
                        aVar.c = str2;
                        break;
                    case 4:
                        aVar.a = str2;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
