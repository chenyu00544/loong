package com.unionpay.mobile.android.pboctransaction;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.media.TransportMediator;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.c;
import com.baidu.location.b.g;
import com.taobao.agoo.a.a.b;
import com.umeng.analytics.pro.dk;
import com.unionpay.mobile.android.c.a;
import com.unionpay.mobile.android.pboctransaction.d.f;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.k;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class e {
    public static String a = "A0000003330101010000000000010000";
    public static String b = "A0000003330101020001050001000000";
    private static Date l = new Date(System.currentTimeMillis());
    private static String m = new SimpleDateFormat("yyyyMMddhhmmss").format(l);
    private static HashMap<String, String> o = new HashMap();
    private static e s = null;
    d c;
    a d;
    public boolean e = false;
    private String f = "1.4";
    private byte g = (byte) 0;
    private byte h = (byte) 0;
    private byte i = (byte) 1;
    private boolean j = true;
    private boolean k = true;
    private String n = null;
    private final String p = "A0000003334355502D4D4F42494C45";
    private boolean q = false;
    private String r = "";

    public e(d dVar, a aVar, String str) {
        this.f = str;
        this.c = dVar;
        this.d = aVar;
    }

    private static String a(String str, String str2) {
        int i = 1;
        if (str == null) {
            return null;
        }
        Object a = f.a(str);
        int i2 = 0;
        while (i2 < a.length) {
            int i3 = ((byte) (a[i2] & 31)) == (byte) 31 ? 2 : 1;
            byte[] bArr = new byte[i3];
            System.arraycopy(a, i2, bArr, 0, i3);
            if (f.a(bArr, i3).compareToIgnoreCase(str2) == 0) {
                i3 += i2;
                if (((byte) (a[i3] & 128)) != Byte.MIN_VALUE) {
                    i2 = a[i3] & 255;
                } else {
                    i = (a[i3] & TransportMediator.KEYCODE_MEDIA_PAUSE) + 1;
                    i2 = i == 2 ? a[i3 + 1] & 255 : i == 3 ? ((a[i3 + 1] & 255) << 8) | (a[i3 + 2] & 255) : i == 4 ? (((a[i3 + 1] & 255) << 16) | ((a[i3 + 2] & 255) << 8)) | (a[i3 + 3] & 255) : 0;
                }
                byte[] bArr2 = new byte[i2];
                System.arraycopy(a, i3 + i, bArr2, 0, i2);
                return f.a(bArr2, i2);
            } else if ((a[i2] & 32) == 32) {
                i3 += i2;
                i2 = (i3 >= a.length || ((byte) (a[i3] & 128)) != Byte.MIN_VALUE) ? 1 : (a[i3] & TransportMediator.KEYCODE_MEDIA_PAUSE) + 1;
                i2 += i3;
            } else {
                int i4 = i2 + i3;
                if (i4 >= a.length || ((byte) (a[i4] & 128)) != (byte) 0) {
                    i3 = i4 < a.length ? (a[i4] & TransportMediator.KEYCODE_MEDIA_PAUSE) + 1 : 0;
                    i2 = (i3 != 2 || i4 + 1 >= a.length) ? (i3 != 3 || i4 + 2 >= a.length) ? (i3 != 4 || i4 + 2 >= a.length) ? 0 : (((a[i4 + 1] & 255) << 16) | ((a[i4 + 2] & 255) << 8)) | (a[i4 + 3] & 255) : ((a[i4 + 1] & 255) << 8) | (a[i4 + 2] & 255) : a[i4 + 1] & 255;
                } else {
                    i2 = a[i4] & 255;
                    i3 = 1;
                }
                i2 = (i2 + i3) + i4;
            }
        }
        return null;
    }

    private static String a(String str, boolean z) {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.toUpperCase().getBytes().length;
        for (i = 0; i < length; i++) {
            stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(r1[i])}));
        }
        int length2 = (stringBuffer.length() % 2) + (stringBuffer.length() / 2);
        if (z) {
            i = length2 + 1;
            if (i % 8 != 0) {
                i += 8 - (i % 8);
            }
            byte[] bArr = new byte[i];
            System.arraycopy(f.a(stringBuffer.toString()), 0, bArr, 0, length2);
            bArr[length2] = Byte.MIN_VALUE;
            return f.a(bArr, i);
        }
        i = length2 % 8 != 0 ? (8 - (length2 % 8)) + length2 : length2;
        bArr = new byte[i];
        System.arraycopy(f.a(stringBuffer.toString()), 0, bArr, 0, length2);
        return f.a(bArr, i);
    }

    private String a(byte[] bArr) {
        byte[] bArr2;
        int i = 0;
        bArr[0] = (byte) (bArr[0] | this.g);
        byte[] a = this.c.a(bArr, this.g);
        int length = a != null ? a.length : 0;
        if (length >= 2 && a[length - 2] == (byte) 97) {
            byte b = a[length - 1];
            a = this.c.a(new byte[]{this.g, (byte) -64, (byte) 0, (byte) 0, b}, this.g);
            length = a != null ? a.length : 0;
        }
        if (length < 2 || a[length - 2] != (byte) 108) {
            i = length;
            bArr2 = a;
        } else {
            bArr[bArr.length - 1] = a[length - 1];
            bArr2 = this.c.a(bArr, this.g);
            if (bArr2 != null) {
                i = bArr2.length;
            }
        }
        return (i > 2 && bArr2[i - 2] == (byte) -112 && bArr2[i - 1] == (byte) 0) ? f.a(bArr2, i - 2) : (i == 2 && bArr2[i - 2] == (byte) -112 && bArr2[i - 1] == (byte) 0) ? f.a(bArr2, 2) : null;
    }

    private String a(byte[] bArr, String str) {
        bArr[bArr.length - 1] = (byte) (str.length() / 2);
        byte[] bArr2 = new byte[(bArr.length + (str.length() / 2))];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(f.a(str), 0, bArr2, bArr.length, str.length() / 2);
        return a(bArr2);
    }

    private static void a(String str, StringBuffer stringBuffer) {
        byte[] bArr = new byte[]{(byte) (((String) o.get(str)).length() / 2)};
        String a = f.a(bArr, bArr.length);
        stringBuffer.append(str);
        stringBuffer.append(a);
        stringBuffer.append(r0);
    }

    private String b(String str) {
        if (this.c instanceof f) {
            return this.c.a(str);
        }
        this.g = this.h;
        if (str == null) {
            return null;
        }
        return a(f.a("00a40400" + f.a(new byte[]{Integer.valueOf(str.length() / 2).byteValue()}) + str));
    }

    private void b(byte[] bArr) {
        int length = (m.length() / 2) + 1;
        Object obj = new byte[length];
        System.arraycopy(f.a(m), 0, obj, 0, m.length() / 2);
        obj[length - 1] = Byte.MIN_VALUE;
        bArr[bArr.length - 1] = (byte) obj.length;
        byte[] bArr2 = new byte[(bArr.length + obj.length)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(obj, 0, bArr2, bArr.length, obj.length);
        a(bArr2);
    }

    private String c(String str) {
        StringBuffer stringBuffer;
        if (str == null || "9000".equals(str)) {
            stringBuffer = new StringBuffer("80A800000b8309");
            for (String str2 : i("9F7A019F02065F2A02")) {
                for (String str3 : o.keySet()) {
                    if (str2.compareToIgnoreCase(str3) == 0) {
                        stringBuffer.append((String) o.get(str3));
                        break;
                    }
                }
            }
            return a(f.a(stringBuffer.toString()));
        }
        k.c("uppay", "test for gongshang version 2");
        stringBuffer = new StringBuffer("");
        Object a = a(str, "9F38");
        if (TextUtils.isEmpty(a)) {
            ByteBuffer allocate = ByteBuffer.allocate(7);
            allocate.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) 2).put((byte) -125).put((byte) 0);
            return a(allocate.array());
        }
        for (String str22 : i(a)) {
            for (String str32 : o.keySet()) {
                if (str22.compareToIgnoreCase(str32) == 0) {
                    stringBuffer.append((String) o.get(str32));
                    break;
                }
            }
        }
        byte[] a2 = f.a(stringBuffer.toString());
        ByteBuffer allocate2 = ByteBuffer.allocate(a2.length + 7);
        allocate2.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) (a2.length + 2)).put((byte) -125).put((byte) a2.length).put(a2);
        return a(allocate2.array());
    }

    private String d(String str) {
        int i = 0;
        String str2 = "8C";
        String str3 = "8D";
        String a = a(str, "80");
        if (a == null) {
            return null;
        }
        o.put("82", a.substring(0, 4));
        Object a2 = f.a(a.substring(4));
        List<String> arrayList = new ArrayList();
        arrayList.add("5A");
        arrayList.add("5F34");
        arrayList.add("9F1F");
        arrayList.add("57");
        arrayList.add("5F24");
        arrayList.add("9F10");
        arrayList.add(str2);
        arrayList.add(str3);
        while (i < a2.length) {
            try {
                byte[] bArr = new byte[]{(byte) 0, (byte) -78, (byte) 0, (byte) 0, (byte) 0};
                Object obj = new byte[4];
                System.arraycopy(a2, i, obj, 0, 4);
                int i2 = i + 4;
                byte b = obj[1];
                while (b <= obj[2]) {
                    bArr[4] = (byte) 0;
                    bArr[3] = (byte) ((obj[0] & -8) | 4);
                    bArr[2] = b;
                    byte b2 = (byte) (b + 1);
                    String a3 = a(bArr);
                    if (a3 != null) {
                        for (String str4 : arrayList) {
                            String a4 = a(a3, str4);
                            if (a4 != null) {
                                o.put(str4, a4);
                            }
                        }
                    }
                    b = b2;
                }
                i = i2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        StringBuffer stringBuffer = new StringBuffer((String) o.get("5F34"));
        stringBuffer.insert(0, "0");
        o.put("5F34", stringBuffer.toString());
        return (String) o.get(str2);
    }

    private static void d() {
        Object format;
        String substring = m.substring(2, 8);
        String valueOf = String.valueOf(new Date(System.currentTimeMillis()).getTime());
        if (valueOf.length() < 8) {
            format = String.format("%08d", new Object[]{Long.valueOf(r2)});
        } else {
            format = valueOf.substring(valueOf.length() - 8, valueOf.length());
        }
        o.put("9F26", "");
        o.put("9F27", "80");
        o.put("9F10", "");
        o.put("9F37", format);
        o.put("9F36", "");
        o.put("95", "0000000800");
        o.put("9A", substring);
        o.put("9C", "45");
        o.put("9F02", "000000000000");
        o.put("5F2A", "0156");
        o.put("82", "");
        o.put("9F1A", "0156");
        o.put("9F03", "000000000000");
        o.put("9F33", "A04000");
        o.put("9F34", "420300");
        o.put("9F35", "34");
        o.put("9F1E", "3030303030303030");
        o.put("84", "");
        o.put("9F09", "0001");
        o.put("9F74", "");
        o.put("9F63", "");
        o.put("9F7A", "01");
        o.put("9F21", m.substring(8));
        o.put("9F4E", "0000000000000000000000000000000000000000");
        o.put("DF31", "0100000000");
        o.put("9F66", "36800000");
        o.put("DF60", "00");
    }

    private String e(String str) {
        StringBuffer stringBuffer = new StringBuffer("80AE800034");
        for (String str2 : i(str)) {
            for (String str3 : o.keySet()) {
                if (str2.compareToIgnoreCase(str3) == 0) {
                    stringBuffer.append((String) o.get(str3));
                    break;
                }
            }
        }
        String str22 = a(f.a(stringBuffer.toString()));
        if (str22 != null) {
            o.put("9F27", str22.substring(4, 6));
            o.put("9F36", str22.substring(6, 10));
            o.put("9F26", str22.substring(10, 26));
            o.put("9F10", str22.substring(26));
        }
        return str22;
    }

    private boolean e() {
        String str = (String) o.get("5A");
        while (str.substring(str.length() - 1, str.length()).equalsIgnoreCase("f")) {
            str = str.substring(0, str.length() - 1);
        }
        str = f(str);
        if (str == null || str.length() == 0) {
            return false;
        }
        o.put("AN1", str);
        str = f("00000001");
        if (str == null || str.length() == 0) {
            return false;
        }
        o.put("TID", str);
        str = f((String) o.get("9F02"));
        if (str == null || str.length() == 0) {
            return false;
        }
        o.put("AMT", str);
        str = f("156");
        if (str == null || str.length() == 0) {
            return false;
        }
        o.put("CUR", str);
        str = (String) o.get("57");
        while (true) {
            if (!str.substring(str.length() - 1, str.length()).equalsIgnoreCase("f") && !str.substring(str.length() - 1, str.length()).equalsIgnoreCase("F")) {
                break;
            }
            str = str.substring(0, str.length() - 1);
        }
        str = f(str);
        if (str == null || str.length() == 0) {
            return false;
        }
        o.put("TD2", str);
        if (o.get("5F24") != null && ((String) o.get("5F24")).length() == 6) {
            str = f(((String) o.get("5F24")).substring(0, 4));
            if (str == null || str.length() == 0) {
                return false;
            }
            o.put("ED", str);
        }
        return true;
    }

    private String f() {
        this.g = this.i;
        String a = a(new byte[]{(byte) 0, (byte) -80, (byte) -126, (byte) 0, (byte) 10});
        if (!(a == null || a.length() == 0)) {
            o.put("CSN", a);
        }
        this.g = this.h;
        return a;
    }

    private String f(String str) {
        this.g = this.i;
        byte[] bArr = new byte[]{Byte.MIN_VALUE, (byte) 26, (byte) 19, (byte) 1, (byte) 0};
        byte[] bArr2 = new byte[]{Byte.MIN_VALUE, (byte) -6, (byte) 0, (byte) 0, (byte) 0};
        String a = a(str, false);
        b(bArr);
        String a2 = a(bArr2, a);
        this.g = this.h;
        return a2;
    }

    private static Bundle g() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    private String g(String str) {
        int i = 0;
        this.g = this.i;
        byte[] bArr = new byte[]{Byte.MIN_VALUE, (byte) 26, (byte) 20, (byte) 1, (byte) 0};
        byte[] bArr2 = new byte[]{Byte.MIN_VALUE, (byte) -6, (byte) 0, (byte) 0, (byte) 0};
        String str2 = String.format("%02d", new Object[]{Integer.valueOf(str.length())}) + str;
        StringBuffer stringBuffer = new StringBuffer(str2);
        while (i < 16 - str2.length()) {
            stringBuffer.append("F");
            i++;
        }
        b(bArr);
        String a = a(bArr2, stringBuffer.toString());
        if (a != null) {
            o.put("PIN", a);
        }
        this.g = this.h;
        return a;
    }

    private String h(String str) {
        this.g = this.i;
        byte[] bArr = new byte[]{Byte.MIN_VALUE, (byte) 26, (byte) 21, (byte) 1, (byte) 8};
        byte[] bArr2 = new byte[]{Byte.MIN_VALUE, (byte) -6, (byte) 1, (byte) 0, (byte) 0};
        String a = a(str, true);
        b(bArr);
        while (a.length() > 448) {
            bArr2[2] = (byte) 3;
            a(bArr2, a.substring(0, 448).toUpperCase());
            a = a.substring(448);
        }
        bArr2[2] = (byte) 1;
        if (VERSION.SDK_INT <= 10) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        a = a(bArr2, a);
        k.c("uppay", "encryptMac in resp" + a);
        if (a != null) {
            o.put("MAC", a.toUpperCase());
        }
        this.g = this.h;
        return a != null ? a.toUpperCase() : a;
    }

    private static List<String> i(String str) {
        List<String> arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        Object a = f.a(str);
        int i = 0;
        while (i < a.length) {
            int i2 = ((byte) (a[i] & 31)) == (byte) 31 ? 2 : 1;
            byte[] bArr = new byte[i2];
            System.arraycopy(a, i, bArr, 0, i2);
            arrayList.add(f.a(bArr, i2));
            i2 += i;
            i = (i2 >= a.length || ((byte) (a[i2] & 128)) != Byte.MIN_VALUE) ? 1 : (a[i2] & TransportMediator.KEYCODE_MEDIA_PAUSE) + 1;
            i += i2;
        }
        return arrayList;
    }

    public final synchronized Bundle a(int i, String str, HashMap<String, String> hashMap, String str2) {
        Bundle bundle;
        k.c("uppay", "startUPCardPurchase() +++");
        Bundle g = g();
        String str3 = "";
        this.c.b();
        String a = a();
        if (a == null || a.length() == 0) {
            this.c.c();
            g.putString("action_resp_code", "10019");
            bundle = g;
        } else {
            k.c("uppay", " ************T1=" + m);
            o.put("PIN", str);
            a = g(PreferenceUtils.decPrefData((String) o.get("PIN"), str2));
            if (a == null || a.length() == 0) {
                this.c.c();
                g.putString("action_resp_code", "10019");
                bundle = g;
            } else {
                k.c("uppay", " ************T2=" + m);
                a = a(i, m);
                if (a == null || a.length() == 0) {
                    this.c.c();
                    g.putString("action_resp_code", "10019");
                    bundle = g;
                } else {
                    String f = f();
                    if (f == null || f.length() == 0) {
                        this.c.c();
                        g.putString("action_resp_code", "10019");
                        bundle = g;
                    } else {
                        f = f.c(a.substring(40, 60));
                        String substring = a.substring(60, 100);
                        String substring2 = a.substring(100, g.new);
                        a = a.substring(216, 232);
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(c.VERSION, this.f);
                            jSONObject.put(b.JSON_CMD, "pay");
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject.put("params", jSONObject2);
                            jSONObject2.put("pay_type", "2");
                            jSONObject2.put("pay_mode", "1");
                            jSONObject2.put("bind", "no");
                            jSONObject2.put("carrier_tp", "1");
                            jSONObject2.put("track2_data", substring);
                            jSONObject2.put("track3_data", substring2);
                            jSONObject2.put("csn", o.get("CSN"));
                            jSONObject2.put("submit_time", m);
                            jSONObject2.put("sp_id", "8889");
                            jSONObject2.put("pin", o.get("PIN"));
                            jSONObject2.put("pan", f);
                            jSONObject2.put("dynamic_key_data", a);
                            jSONObject2.put("carrier_app_tp", "1");
                            if (!(hashMap == null || hashMap.keySet() == null || hashMap.keySet().size() <= 0)) {
                                hashMap.remove("pan");
                                hashMap.remove("pin");
                                for (String a2 : hashMap.keySet()) {
                                    jSONObject2.put(a2, hashMap.get(a2));
                                }
                            }
                            a2 = jSONObject.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            a2 = str3;
                        }
                        k.c("uppay", " ************T3=" + m);
                        g.putString("action_resp_message", this.d.a(a2));
                        this.c.c();
                        d();
                        bundle = g;
                    }
                }
            }
        }
        return bundle;
    }

    public final synchronized Bundle a(a aVar, String str, String str2, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String str3) {
        Bundle bundle;
        this.c.b();
        k.c("uppay", "startPBOCPurchase() +++");
        o.clear();
        d();
        o.put("PIN", str);
        o.put("9F02", hashMap.get("trans_amt"));
        o.put("9F1A", "0156");
        o.put("5F2A", hashMap.get("trans currcy code"));
        o.put("9C", hashMap.get("trans_type"));
        String a = aVar.a();
        if (a.startsWith("A000000333")) {
            Bundle g = g();
            l = new Date(System.currentTimeMillis());
            m = new SimpleDateFormat("yyyyMMddHHmmss").format(l);
            this.n = new String(m);
            k.c("uppay", "selectUPCard");
            String a2 = a();
            k.c("uppay", "selectUPCard return: " + a2);
            if (a2 == null || a2.length() == 0) {
                g.putString("action_resp_code", "10019");
            } else {
                k.c("uppay", "selectPBOC");
                a = b(a);
                k.c("uppay", "selectPBOC return: " + a);
                if (a == null || a.length() == 0) {
                    g.putString("action_resp_code", "10019");
                } else {
                    k.c("uppay", "GPO");
                    a = c(a);
                    k.c("uppay", "gpo return: " + a);
                    if (a == null || a.length() == 0) {
                        g.putString("action_resp_code", "10020");
                    } else {
                        k.c("uppay", "CDOL1");
                        a = d(a);
                        k.c("uppay", "CDOL1 return: " + a);
                        if (a == null || a.length() == 0) {
                            g.putString("action_resp_code", "10019");
                        } else {
                            k.c("uppay", "GAC1");
                            a = e(a);
                            k.c("uppay", "GAC1 return: " + a);
                            if (a == null || a.length() == 0) {
                                g.putString("action_resp_code", "10019");
                            } else {
                                k.c("uppay", "csn");
                                a = f();
                                k.c("uppay", "csn return: " + a);
                                if (a == null || a.length() == 0) {
                                    g.putString("action_resp_code", "10019");
                                }
                            }
                        }
                    }
                }
            }
            if (g.getString("action_resp_code") != "0000") {
                this.c.c();
                bundle = g;
            } else {
                k.c("uppay", "encryptPIN");
                a = g(PreferenceUtils.decPrefData((String) o.get("PIN"), str3));
                k.c("uppay", "encryptPIN return:" + a);
                if (a == null || a.length() == 0) {
                    this.c.c();
                    g.putString("action_resp_code", "10019");
                    bundle = g;
                } else {
                    k.c("uppay", "encryptData");
                    if (e()) {
                        k.c("uppay", "initDCData");
                        StringBuffer stringBuffer = new StringBuffer();
                        a("9F26", stringBuffer);
                        a("9F27", stringBuffer);
                        a("9F10", stringBuffer);
                        a("9F37", stringBuffer);
                        a("9F36", stringBuffer);
                        a("95", stringBuffer);
                        a("9A", stringBuffer);
                        a("9C", stringBuffer);
                        a("9F02", stringBuffer);
                        a("5F2A", stringBuffer);
                        a("82", stringBuffer);
                        a("9F1A", stringBuffer);
                        a("9F03", stringBuffer);
                        a("9F33", stringBuffer);
                        a("9F34", stringBuffer);
                        a("9F35", stringBuffer);
                        a("9F1E", stringBuffer);
                        o.put("DCD", stringBuffer.toString());
                        if (o.get("TID") == null || o.get("AMT") == null || o.get("CUR") == null || o.get("AN1") == null || o.get("CSN") == null || o.get("5F34") == null) {
                            this.c.c();
                            g.putString("action_resp_code", "10019");
                            bundle = g;
                        } else {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(m);
                            if (o.get("TID") != null) {
                                arrayList.add(o.get("TID"));
                            }
                            if (o.get("AMT") != null) {
                                arrayList.add(o.get("AMT"));
                            }
                            if (o.get("CUR") != null) {
                                arrayList.add(o.get("CUR"));
                            }
                            if (o.get("AN1") != null) {
                                arrayList.add(o.get("AN1"));
                            }
                            o.put("AN1", f.c((String) o.get("5A")));
                            if (o.get("CSN") != null) {
                                arrayList.add(o.get("CSN"));
                            }
                            if (this.q && o.get("ED") != null) {
                                arrayList.add(o.get("ED"));
                            }
                            if (o.get("5F34") != null) {
                                arrayList.add(o.get("5F34"));
                            }
                            if (o.get("DCD") != null) {
                                arrayList.add(o.get("DCD"));
                            }
                            a2 = "";
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                a = (String) it.next();
                                a2 = a != null ? a2 + a + " " : a2;
                            }
                            a = a2.trim();
                            k.c("uppay", "encryptMac");
                            a = h(a);
                            k.c("uppay", "encryptMac result" + a);
                            if (a == null || a.length() == 0) {
                                this.c.c();
                                g.putString("action_resp_code", "10019");
                                bundle = g;
                            } else {
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put(c.VERSION, this.f);
                                    jSONObject.put(b.JSON_CMD, "pay");
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject.put("params", jSONObject2);
                                    jSONObject2.put("pay_type", "2");
                                    jSONObject2.put("pay_mode", "1");
                                    jSONObject2.put("bind", "no");
                                    jSONObject2.put("carrier_tp", str2);
                                    jSONObject2.put("icc_data", o.get("DCD"));
                                    jSONObject2.put("csn", o.get("CSN"));
                                    jSONObject2.put("card_seq_id", o.get("5F34") != null ? (String) o.get("5F34") : "");
                                    jSONObject2.put("submit_time", m);
                                    jSONObject2.put("sp_id", "8889");
                                    jSONObject2.put("pin", o.get("PIN"));
                                    jSONObject2.put("pan", o.get("AN1"));
                                    jSONObject2.put("carrier_app_tp", "2");
                                    if (o.get("ED") != null) {
                                        jSONObject2.put("expire", o.get("ED"));
                                    }
                                    if (o.get("TD2") != null) {
                                        jSONObject2.put("track2_data", o.get("TD2"));
                                    }
                                    if (!(hashMap2 == null || hashMap2.keySet() == null || hashMap2.keySet().size() <= 0)) {
                                        hashMap2.remove("pan");
                                        hashMap2.remove("pin");
                                        for (String a3 : hashMap2.keySet()) {
                                            jSONObject2.put(a3, hashMap2.get(a3));
                                        }
                                    }
                                    a3 = jSONObject.toString();
                                    k.c("uppay", a3);
                                    g.putString("action_resp_message", this.d.a(a3));
                                    this.c.c();
                                    d();
                                    bundle = g;
                                } catch (JSONException e) {
                                    this.c.c();
                                    g.putString("action_resp_code", "10019");
                                    bundle = g;
                                }
                            }
                        }
                    } else {
                        k.c("uppay", "encryptData false");
                        this.c.c();
                        g.putString("action_resp_code", "10019");
                        bundle = g;
                    }
                }
            }
        } else {
            this.c.c();
            bundle = g();
            bundle.putString("action_resp_code", "10019");
        }
        return bundle;
    }

    public final String a() {
        if (this.c instanceof f) {
            return this.c.a("A0000003334355502D4D4F42494C45");
        }
        this.g = this.i;
        return a(new byte[]{(byte) 0, (byte) -92, (byte) 4, (byte) 0, dk.m, (byte) -96, (byte) 0, (byte) 0, (byte) 3, (byte) 51, (byte) 67, (byte) 85, (byte) 80, (byte) 45, (byte) 77, (byte) 79, (byte) 66, (byte) 73, (byte) 76, (byte) 69});
    }

    public final String a(int i, String str) {
        this.g = this.i;
        return a(f.a("80F802" + (Integer.toHexString(i).length() == 1 ? "0" + Integer.toHexString(i) : Integer.toHexString(i)) + "08" + str + "80"));
    }

    public final synchronized String a(a aVar) {
        String str = null;
        synchronized (this) {
            String a = aVar.a();
            d();
            b(a);
            a = c(null);
            if (!(a == null || a.length() == 0)) {
                a = d(a);
                if (!(a == null || a.length() == 0)) {
                    str = (String) o.get("5A");
                }
            }
        }
        return str;
    }

    public final String a(String str) {
        this.c.d();
        String b = b(str);
        this.c.d();
        return a(b, "84");
    }

    public final ArrayList<com.unionpay.mobile.android.g.c> b() {
        this.c.d();
        this.c.b();
        ArrayList<com.unionpay.mobile.android.g.c> a = this.c.a(this);
        this.c.c();
        return a;
    }

    public final String c() {
        this.g = this.i;
        return a(f.a("80F2000102"));
    }
}
