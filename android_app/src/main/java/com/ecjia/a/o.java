package com.ecjia.a;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;

/* compiled from: ECJiaIdCardUtil */
public class o {
    private static int b = 1;
    private static int c = 2;
    private static int d = 3;
    private static int e = 4;
    private static int f = 5;
    private String a = null;
    private String[] g = new String[]{"身份证完全正确！", "身份证为空！", "身份证长度不正确！", "身份证有非法字符！", "身份证中出生日期不合法！", "身份证校验位错误！"};
    private int h = 0;

    public o(String str) {
        this.a = str.trim();
        if (!TextUtils.isEmpty(this.a)) {
            this.a = this.a.replace("x", "X");
        }
    }

    public boolean a() {
        if (this.a != null && this.a.trim().length() > 0) {
            return false;
        }
        return true;
    }

    public int b() {
        return a() ? 0 : this.a.length();
    }

    public boolean c() {
        return b() == 15;
    }

    public boolean d() {
        return b() == 18;
    }

    public String e() {
        if (!g()) {
            return "";
        }
        String substring = this.a.substring(this.a.length() - 1);
        if ("x".equals(substring)) {
            return "X";
        }
        return substring;
    }

    public int f() {
        if (a()) {
            this.h = b;
            return this.h;
        } else if (!g()) {
            this.h = c;
            return this.h;
        } else if (!j()) {
            this.h = d;
            return this.h;
        } else if (!h()) {
            this.h = e;
            return this.h;
        } else if (!d() || e().equals(i())) {
            return 0;
        } else {
            this.h = f;
            return this.h;
        }
    }

    private boolean g() {
        return c() || d();
    }

    private boolean h() {
        int parseInt;
        int parseInt2;
        int[] iArr = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] iArr2 = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (c()) {
            parseInt = Integer.parseInt(this.a.substring(8, 10));
        } else {
            parseInt = Integer.parseInt(this.a.substring(10, 12));
        }
        if (c()) {
            parseInt2 = Integer.parseInt(this.a.substring(10, 12));
        } else {
            parseInt2 = Integer.parseInt(this.a.substring(12, 14));
        }
        if (parseInt > 12 || parseInt <= 0) {
            return false;
        }
        if (k()) {
            if (parseInt2 > iArr2[parseInt - 1] || parseInt2 <= 0) {
                return false;
            }
        } else if (parseInt2 > iArr[parseInt - 1] || parseInt2 <= 0) {
            return false;
        }
        return true;
    }

    private String i() {
        int i = 0;
        if (!g()) {
            return "";
        }
        String str;
        if (d()) {
            str = this.a;
        } else {
            str = this.a.substring(0, 6) + Constants.VIA_ACT_TYPE_NINETEEN + this.a.substring(6);
        }
        String[] strArr = new String[]{"1", "0", "X", "9", "8", "7", Constants.VIA_SHARE_TYPE_INFO, "5", "4", "3", "2"};
        int[] iArr = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
        int i2 = 0;
        while (i < 17) {
            i2 += Integer.parseInt(str.substring(i, i + 1)) * iArr[i];
            i++;
        }
        return strArr[i2 % 11];
    }

    private boolean j() {
        if (!g()) {
            return false;
        }
        boolean z;
        byte[] bytes = this.a.getBytes();
        if (c()) {
            int i = 0;
            while (i < bytes.length) {
                if (bytes[i] < (byte) 48 || bytes[i] > (byte) 57) {
                    z = false;
                    break;
                }
                i++;
            }
        }
        z = true;
        if (!d()) {
            return z;
        }
        int i2 = 0;
        while (i2 < bytes.length) {
            if ((bytes[i2] < (byte) 48 || bytes[i2] > (byte) 57) && i2 == 17 && bytes[i2] != (byte) 88) {
                return false;
            }
            i2++;
        }
        return z;
    }

    private boolean k() {
        String str;
        if (c()) {
            str = Constants.VIA_ACT_TYPE_NINETEEN + this.a.substring(6, 8);
        } else {
            str = this.a.substring(6, 10);
        }
        int parseInt = Integer.parseInt(str);
        if ((parseInt % 4 != 0 || parseInt % 100 == 0) && parseInt % 400 != 0) {
            return false;
        }
        return true;
    }
}
