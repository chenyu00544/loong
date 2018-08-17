package com.unionpay.mobile.android.h;

import android.content.Context;
import android.text.TextUtils;
import com.unionpay.mobile.android.utils.c;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

public final class b implements X509TrustManager {
    private X509TrustManager a = null;
    private Context b;

    public b(Context context) throws NoSuchAlgorithmException, KeyStoreException {
        this.b = context;
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(null);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers.length == 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        }
        this.a = (X509TrustManager) trustManagers[0];
    }

    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.a.checkClientTrusted(x509CertificateArr, str);
    }

    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.a.checkServerTrusted(x509CertificateArr, str);
        try {
            int i;
            Object obj;
            X500Principal issuerX500Principal = x509CertificateArr[0].getIssuerX500Principal();
            List arrayList = new ArrayList(0);
            arrayList.add(".*O=(GeoTrust Inc\\.|VeriSign\\\\, Inc\\.|Symantec Corporation).*");
            CharSequence a = c.a(this.b);
            if (!TextUtils.isEmpty(a)) {
                arrayList.add(a);
            }
            for (i = 0; i < arrayList.size(); i++) {
                if (Pattern.compile((String) arrayList.get(i)).matcher(issuerX500Principal.getName()).matches()) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                throw new CertificateException();
            }
            issuerX500Principal = x509CertificateArr[0].getSubjectX500Principal();
            arrayList = new ArrayList(0);
            arrayList.add(".*CN=.*(\\.95516\\.com|\\.chinaunionpay\\.com|\\.unionpay\\.com|\\.unionpaysecure\\.com|\\.95516\\.net).*");
            for (i = 0; i < arrayList.size(); i++) {
                if (Pattern.compile((String) arrayList.get(i)).matcher(issuerX500Principal.getName()).matches()) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                throw new CertificateException();
            }
        } catch (Exception e) {
            throw new CertificateException();
        }
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return this.a.getAcceptedIssuers();
    }
}
