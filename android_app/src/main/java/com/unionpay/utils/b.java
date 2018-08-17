package com.unionpay.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.umeng.socialize.common.SocializeConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;

public final class b {
    private static SimpleDateFormat a = new SimpleDateFormat("yyyyMMddhhmmss");

    public static String a(String str) {
        char[] toCharArray = "0123456789ABCDEF".toCharArray();
        StringBuilder stringBuilder = new StringBuilder("");
        for (byte b : str.getBytes()) {
            stringBuilder.append(toCharArray[(b & SocializeConstants.MASK_USER_CENTER_HIDE_AREA) >> 4]);
            stringBuilder.append(toCharArray[b & 15]);
        }
        return stringBuilder.toString().trim();
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String toHexString = Integer.toHexString(bArr[i]);
            int length = toHexString.length();
            if (length == 1) {
                toHexString = "0" + toHexString;
            }
            if (length > 2) {
                toHexString = toHexString.substring(length - 2, length);
            }
            stringBuilder.append(toHexString.toUpperCase());
            if (i < bArr.length - 1) {
                stringBuilder.append(':');
            }
        }
        return stringBuilder.toString();
    }

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static String b(Context context, String str) {
        PackageInfo packageInfo;
        String str2 = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 64);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            Object obj = str2;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X509");
        } catch (CertificateException e2) {
            e2.printStackTrace();
            obj = str2;
        }
        try {
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e22) {
            e22.printStackTrace();
            obj = str2;
        }
        try {
            str2 = a(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        } catch (CertificateEncodingException e4) {
            e4.printStackTrace();
        }
        return str2.replaceAll(":", "");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.lang.String r8) {
        /*
        r6 = 0;
        r4 = new java.io.File;
        r4.<init>(r8);
        r7 = new java.io.FileInputStream;
        r7.<init>(r4);
        r0 = r7.getChannel();	 Catch:{ Exception -> 0x003d }
        r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY;	 Catch:{ Exception -> 0x003d }
        r2 = 0;
        r4 = r4.length();	 Catch:{ Exception -> 0x003d }
        r0 = r0.map(r1, r2, r4);	 Catch:{ Exception -> 0x003d }
        r1 = "MD5";
        r1 = java.security.MessageDigest.getInstance(r1);	 Catch:{ Exception -> 0x003d }
        r1.update(r0);	 Catch:{ Exception -> 0x003d }
        r0 = new java.math.BigInteger;	 Catch:{ Exception -> 0x003d }
        r2 = 1;
        r1 = r1.digest();	 Catch:{ Exception -> 0x003d }
        r0.<init>(r2, r1);	 Catch:{ Exception -> 0x003d }
        r1 = 16;
        r0 = r0.toString(r1);	 Catch:{ Exception -> 0x003d }
        r7.close();	 Catch:{ IOException -> 0x0038 }
    L_0x0037:
        return r0;
    L_0x0038:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0037;
    L_0x003d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x004c }
        r7.close();	 Catch:{ IOException -> 0x0046 }
        r0 = r6;
        goto L_0x0037;
    L_0x0046:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r6;
        goto L_0x0037;
    L_0x004c:
        r0 = move-exception;
        r7.close();	 Catch:{ IOException -> 0x0051 }
    L_0x0050:
        throw r0;
    L_0x0051:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.b.b(java.lang.String):java.lang.String");
    }

    public static String c(Context context, String str) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (NameNotFoundException e) {
            return str2;
        }
    }

    public static void c(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                for (File path : file.listFiles()) {
                    c(path.getPath());
                }
            }
            file.delete();
        }
    }
}
