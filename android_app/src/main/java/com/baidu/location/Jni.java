package com.baidu.location;

import com.baidu.location.b.f;
import com.baidu.location.f.b.b;
import com.baidu.platform.comapi.location.CoordinateType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Jni implements f {
    private static int k8 = 14;
    private static int k9 = 13;
    private static int la = 1024;
    private static int lb = 11;
    private static int lc = 15;
    private static int ld = 12;
    private static boolean le;
    private static int lf = 1;
    private static int lg = 2;
    private static int lh = 0;

    static {
        le = false;
        try {
            System.loadLibrary("locSDK6a");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            le = true;
        }
    }

    public static String E(String str) {
        if (le) {
            return null;
        }
        try {
            return URLEncoder.encode(if(encrypt(str.getBytes())), "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String F(String str) {
        int i = 740;
        int i2 = 0;
        if (le) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[la];
        int length = bytes.length;
        if (length <= 740) {
            i = length;
        }
        length = 0;
        while (i2 < i) {
            if (bytes[i2] != (byte) 0) {
                bArr[length] = bytes[i2];
                length++;
            }
            i2++;
        }
        String str2 = "err!";
        try {
            return a(bArr, 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    public static String G(String str) {
        if (le) {
            return "err!";
        }
        String str2;
        String str3 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception e) {
            str2 = str3;
        }
        str3 = "err!";
        try {
            str2 = encodeNotLimit(str2, 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            str2 = "err!";
        }
        return str2 + "|tp=3";
    }

    public static String H(String str) {
        return le ? "err!" : F(str) + "|tp=3";
    }

    public static Long I(String str) {
        Long l = null;
        if (!le) {
            String str2;
            String str3 = "";
            try {
                str2 = new String(str.getBytes(), "UTF-8");
            } catch (Exception e) {
                str2 = str3;
            }
            try {
                l = Long.valueOf(murmur(str2));
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
            }
        }
        return l;
    }

    public static String J(String str) {
        if (le) {
            return "err!";
        }
        if (str == null) {
            return "null";
        }
        String str2 = "err!";
        try {
            return c(str.getBytes(), 132456);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    public static String K(String str) {
        if (le) {
            return "err!";
        }
        String str2;
        String str3 = "";
        try {
            str2 = new String(str.getBytes(), "UTF-8");
        } catch (Exception e) {
            str2 = str3;
        }
        str3 = "err!";
        try {
            str2 = ee(str2, 132456);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            str2 = "err!";
        }
        return str2 + "|tp=4";
    }

    public static String L(String str) {
        if (le) {
            return null;
        }
        try {
            String g = g(str.getBytes());
            return (g == null || g.length() < 2 || "no".equals(g)) ? null : g;
        } catch (UnsatisfiedLinkError e) {
            return null;
        }
    }

    private static native String a(byte[] bArr, int i);

    private static native String b(double d, double d2, int i, int i2);

    private static native String c(byte[] bArr, int i);

    public static String dH() {
        if (le) {
            return "err!";
        }
        String str = "err!";
        try {
            return sky();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "err!";
        }
    }

    private static native String ee(String str, int i);

    private static native String encodeNotLimit(String str, int i);

    private static native byte[] encrypt(byte[] bArr);

    private static native void f(byte[] bArr, byte[] bArr2);

    private static native String g(byte[] bArr);

    private static native String ib(byte[] bArr, byte[] bArr2);

    private static String if(byte[] bArr) {
        try {
            return b.a(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String if(byte[] bArr, byte[] bArr2) {
        return le ? null : ib(bArr, bArr2);
    }

    public static double[] if(double d, double d2, String str) {
        double[] dArr = new double[]{0.0d, 0.0d};
        if (le) {
            return dArr;
        }
        int i = -1;
        if (str.equals(BDLocation.BDLOCATION_GCJ02_TO_BD09)) {
            i = lh;
        } else if (str.equals("bd09ll")) {
            i = lf;
        } else if (str.equals(CoordinateType.GCJ02)) {
            i = lg;
        } else if (str.equals("gps2gcj")) {
            i = lb;
        } else if (str.equals(BDLocation.BDLOCATION_BD09_TO_GCJ02)) {
            i = ld;
        } else if (str.equals(BDLocation.BDLOCATION_BD09LL_TO_GCJ02)) {
            i = k9;
        } else if (str.equals("wgs842mc")) {
            i = lc;
        }
        try {
            String[] split = b(d, d2, i, 132456).split(":");
            dArr[0] = Double.parseDouble(split[0]);
            dArr[1] = Double.parseDouble(split[1]);
        } catch (UnsatisfiedLinkError e) {
        }
        return dArr;
    }

    public static void int(String str, String str2) {
        if (!le) {
            try {
                f(str.getBytes(), str2.getBytes());
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }
        }
    }

    private static native long murmur(String str);

    private static native String sky();
}
