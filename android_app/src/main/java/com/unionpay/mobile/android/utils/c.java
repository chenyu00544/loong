package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Base64;
import com.taobao.accs.common.Constants;
import com.taobao.agoo.a.a.b;
import com.umeng.socialize.common.SocializeConstants;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    private static String a = "[{\"type\":\"app\",\"sort\":100,\"package_info\":[{\"schema\":\"com.unionpay.uppay\",\"version\":\".*\",\"sign\":\"23137B5BE6AEF6682B41E6536F08367E0949A1CC\",\"sort\":101}],\"need_install\":true,\"install_msg\":\"�Ƿ����ذ�װ��������֧������\",\"url\":\"https://mobile.unionpay.com/getclient?platform=android&type=securepayplugin\",\"download_app\":\"UPPayPluginEx.apk\",\"download_title\":\"��������֧������\",\"download_desp\":\"��������֧������\",\"md5\":\"D75BB2802E61738A9A03BF014F927D9A\"},{\"type\":\"jar\",\"sort\":200}]";
    private static SimpleDateFormat b = new SimpleDateFormat("yyyyMMddhhmmss");
    private static HashMap<String, String> c = new d();
    private static long[] d = new long[256];

    static {
        for (int i = 0; i < 256; i++) {
            long j = (long) i;
            for (int i2 = 0; i2 < 8; i2++) {
                j = (j >> 1) ^ ((((int) j) & 1) != 0 ? -7661587058870466123L : 0);
            }
            d[i] = j;
        }
    }

    public static String a() {
        return b.format(new Date(System.currentTimeMillis()));
    }

    public static String a(Context context) {
        String str = "";
        Object a = PreferenceUtils.a(context, "configs");
        Object a2 = PreferenceUtils.a(context, Constants.KEY_MODE);
        Object a3 = PreferenceUtils.a(context, "or");
        if (!(TextUtils.isEmpty(a) || TextUtils.isEmpty(a2) || TextUtils.isEmpty(a3))) {
            try {
                int parseInt;
                JSONObject jSONObject = new JSONObject(a);
                String a4 = j.a(jSONObject, "sign");
                try {
                    parseInt = Integer.parseInt(a2);
                } catch (NumberFormatException e) {
                    parseInt = 0;
                }
                String str2 = new String(Base64.decode(jSONObject.getString("configs"), 2));
                if (!PreferenceUtils.forConfig(parseInt, a4).equals(b(f(str2 + a3)))) {
                    str2 = str;
                }
                str = str2;
            } catch (JSONException e2) {
            }
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                a = j.b(jSONArray, i);
                if (a != null) {
                    JSONObject jSONObject2 = (JSONObject) a;
                    if ("app".equals(j.a(jSONObject2, "type"))) {
                        return new String(Base64.decode(j.a(jSONObject2, "ca"), 2));
                    }
                }
            }
            return "";
        } catch (JSONException e3) {
            return "";
        }
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
        } catch (RuntimeException e2) {
            return false;
        }
    }

    public static boolean a(String str) {
        return str.matches("[0-9A-Fa-f]+");
    }

    public static String b(Context context) {
        String str = (String) c.get(f.b(context));
        return !TextUtils.isEmpty(str) ? str : com.tencent.connect.common.Constants.DEFAULT_UIN;
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

    public static String b(String str) {
        char[] toCharArray = "0123456789ABCDEF".toCharArray();
        StringBuilder stringBuilder = new StringBuilder("");
        for (byte b : str.getBytes()) {
            stringBuilder.append(toCharArray[(b & SocializeConstants.MASK_USER_CENTER_HIDE_AREA) >> 4]);
            stringBuilder.append(toCharArray[b & 15]);
        }
        return stringBuilder.toString().trim();
    }

    public static String c(String str) {
        try {
            return new BigDecimal(str).divide(new BigDecimal(MessageService.MSG_DB_COMPLETE)).toString();
        } catch (Exception e) {
            return "1";
        }
    }

    public static String d(String str) {
        int i;
        int i2 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (i = 0; i < str.length() / 3; i++) {
            stringBuilder.append(b.JSON_CMD);
        }
        for (i = 0; i < str.length() % 3; i++) {
            stringBuilder.append(b.JSON_CMD.charAt(i));
        }
        byte[] bytes = str.getBytes();
        byte[] bytes2 = stringBuilder.toString().getBytes();
        byte[] bArr = new byte[str.length()];
        while (i2 < bytes.length) {
            bArr[i2] = (byte) (bytes[i2] ^ bytes2[i2]);
            i2++;
        }
        return b.a(bArr);
    }

    public static String e(String str) {
        long j;
        int i = 0;
        if (str == null || str.length() == 0) {
            j = 0;
        } else {
            byte[] bArr = new byte[(str.length() * 2)];
            int i2 = 0;
            for (char c : str.toCharArray()) {
                int i3 = i2 + 1;
                bArr[i2] = (byte) (c & 255);
                i2 = i3 + 1;
                bArr[i3] = (byte) (c >> 8);
            }
            j = -1;
            while (i < bArr.length) {
                j ^= d[(bArr[i] ^ ((int) j)) & 255];
                i++;
            }
        }
        return Long.toHexString(j);
    }

    private static String f(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.reset();
            instance.update(bytes);
            return b.a(instance.digest());
        } catch (Exception e) {
            return null;
        }
    }
}
