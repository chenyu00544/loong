package com.unionpay.mobile.android.pboctransaction.b;

import android.support.v4.media.TransportMediator;
import android.text.TextUtils;
import android.util.Log;
import com.unionpay.mobile.android.pboctransaction.b.b.b;
import com.unionpay.mobile.android.pboctransaction.f;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class a {
    protected static final byte[] d = new byte[]{(byte) 50, (byte) 80, (byte) 65, (byte) 89, (byte) 46, (byte) 83, (byte) 89, (byte) 83, (byte) 46, (byte) 68, (byte) 68, (byte) 70, (byte) 48, (byte) 49};
    protected String a = "UnionPay Card";
    com.unionpay.mobile.android.c.a b;
    b c;

    public a(com.unionpay.mobile.android.c.a aVar, b bVar) {
        this.b = aVar;
        this.c = bVar;
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

    private static List<String> a(String str) {
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

    public static void b(String str, HashMap<String, String> hashMap) {
        List arrayList = new ArrayList();
        arrayList.add("82");
        arrayList.add("9F36");
        arrayList.add("9F10");
        arrayList.add("9F26");
        arrayList.add("5F34");
        arrayList.add("57");
        arrayList.add("9F63");
        int i = 0;
        while (i < arrayList.size()) {
            try {
                String str2 = (String) arrayList.get(i);
                String a = a(str, str2);
                if (a != null) {
                    hashMap.put(str2, a);
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        StringBuffer stringBuffer = new StringBuffer((String) hashMap.get("5F34"));
        stringBuffer.insert(0, "0");
        hashMap.put("5F34", stringBuffer.toString());
        String str3 = (String) hashMap.get("57");
        while (true) {
            if (!str3.substring(str3.length() - 1, str3.length()).equalsIgnoreCase("f") && !str3.substring(str3.length() - 1, str3.length()).equalsIgnoreCase("F")) {
                break;
            }
            str3 = str3.substring(0, str3.length() - 1);
        }
        hashMap.put("TD2", str3.toString());
        int indexOf = str3.indexOf("D");
        Object substring = str3.substring(0, indexOf);
        if (substring.endsWith("F") || substring.endsWith("f")) {
            substring = substring.substring(0, substring.length() - 1);
        }
        hashMap.put("AN1", substring);
        hashMap.put("ED", str3.substring(indexOf + 1, indexOf + 5));
        hashMap.put("AMT", hashMap.get("9F02"));
        if (hashMap.containsKey("9F10") && ((byte) (f.a((String) hashMap.get("9F10"))[4] & 48)) == (byte) 32) {
            hashMap.put("9F27", "80");
        }
    }

    public final String a() {
        com.unionpay.mobile.android.pboctransaction.b.b.a a = this.c.a(d);
        if (!a.b()) {
            return null;
        }
        String a2 = a(a.toString(), "4F");
        if (!a2.startsWith("A000000333")) {
            return "noSupUnionpay";
        }
        a = this.c.a(f.a(a2));
        return a.b() ? a.toString() : null;
    }

    public final String a(String str, HashMap<String, String> hashMap) {
        String a = a(str, "9F38");
        StringBuffer stringBuffer = new StringBuffer();
        for (String a2 : a(a2)) {
            for (String str2 : hashMap.keySet()) {
                if (a2.compareToIgnoreCase(str2) == 0) {
                    stringBuffer.append((String) hashMap.get(str2));
                    break;
                }
            }
        }
        b bVar = this.c;
        byte[] a3 = f.a(stringBuffer.toString());
        ByteBuffer allocate = ByteBuffer.allocate(a3.length + 7);
        allocate.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) (a3.length + 2)).put((byte) -125).put((byte) a3.length).put(a3);
        Log.e("PBOC Transceive", b.c(allocate.array()));
        com.unionpay.mobile.android.pboctransaction.b.b.a aVar = new com.unionpay.mobile.android.pboctransaction.b.b.a(bVar.b(allocate.array()));
        Log.e("PBOC receive", b.c(aVar.a()));
        if (!aVar.b()) {
            return null;
        }
        a2 = a(aVar.toString(), "9F10");
        return (TextUtils.isEmpty(a2) || ((byte) (f.a(a2)[4] & 48)) == (byte) 32) ? aVar.toString() : null;
    }
}
