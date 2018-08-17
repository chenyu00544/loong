package com.unionpay.c;

import android.content.Context;
import android.os.Build.VERSION;
import anet.channel.security.ISecurity;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.security.MessageDigest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class am {
    public static boolean a = true;
    public static String b = "TDLog";
    public static boolean c = false;
    public static boolean d = false;
    static final /* synthetic */ boolean e = (!am.class.desiredAssertionStatus());
    private static String f = "ge";
    private static String g = SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE;
    private static String h = "rop";
    private static final ExecutorService i = Executors.newSingleThreadExecutor();
    private static final byte[] j = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static byte[] k = new byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8};

    public static String a(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new JSONObject(new String(bArr)).getString("td_channel_id");
        } catch (Throwable th) {
            return null;
        }
    }

    public static final String a(String str) {
        return str.length() > 256 ? str.substring(0, 256) : str;
    }

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(i));
        }
        return stringBuilder.toString();
    }

    public static void a(Class cls, ak akVar, String str, String str2) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        Object obj = declaredField.get(null);
        Class cls2 = Class.forName(str2);
        InvocationHandler qVar = new q(akVar, obj);
        declaredField.set(null, Proxy.newProxyInstance(cls.getClass().getClassLoader(), new Class[]{cls2}, qVar));
    }

    public static void a(Runnable runnable) {
        i.execute(runnable);
    }

    public static boolean a(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static boolean b(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable th) {
            return false;
        }
    }

    public static final boolean b(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String c(String str) {
        try {
            return a(MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5).digest(str.getBytes("UTF-8")));
        } catch (Exception e) {
            return null;
        }
    }

    public static String d(String str) {
        String str2 = null;
        if (str != null) {
            try {
                str2 = a(MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8")));
            } catch (Exception e) {
            }
        }
        return str2;
    }
}
