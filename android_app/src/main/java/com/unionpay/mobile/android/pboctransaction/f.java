package com.unionpay.mobile.android.pboctransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class f {
    static HashMap<String, String> a = new g();

    private static String a(String str, String str2) {
        if (!(str == null || str2 == null || str.length() <= str2.length())) {
            while (str.substring(str.length() - str2.length(), str.length()).equalsIgnoreCase(str2)) {
                str = str.substring(0, str.length() - str2.length());
            }
        }
        return str;
    }

    public static final String a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static final String a(byte[] bArr, int i) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bArr == null || i <= 0) {
            return null;
        }
        for (int i2 = 0; i2 < i; i2++) {
            String toHexString = Integer.toHexString(bArr[i2] & 255);
            if (toHexString.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString().toUpperCase();
    }

    private static boolean a(ArrayList<a> arrayList, String str) {
        if (arrayList == null || str == null) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (((a) it.next()).a().equalsIgnoreCase(str)) {
                return false;
            }
        }
        return true;
    }

    public static final byte[] a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String toUpperCase = str.toUpperCase();
        int length = toUpperCase.length() / 2;
        char[] toCharArray = toUpperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (((byte) "0123456789ABCDEF".indexOf(toCharArray[i2 + 1])) | (((byte) "0123456789ABCDEF".indexOf(toCharArray[i2])) << 4));
        }
        return bArr;
    }

    public static final ArrayList<a> b(String str) {
        int i = 0;
        if (str == null || str.length() <= 4) {
            return null;
        }
        ArrayList<a> arrayList = new ArrayList();
        String substring = str.substring(str.length() - 4);
        if (substring != null && substring.equalsIgnoreCase("9000")) {
            String substring2 = str.substring(0, str.length() - 4);
            while (substring2 != null && substring2.length() > 0 && i != -1) {
                substring = "";
                int indexOf = substring2.indexOf("61", i);
                if (indexOf == -1) {
                    break;
                }
                String substring3 = substring2.substring(indexOf + 2, indexOf + 4);
                String substring4 = substring2.substring(indexOf + 4, indexOf + 6);
                if ((indexOf + 6) + (Integer.parseInt(substring4, 16) * 2) < substring2.length()) {
                    substring = substring2.substring(indexOf + 6, (Integer.parseInt(substring4, 16) * 2) + (indexOf + 6));
                }
                i = (Integer.parseInt(substring3, 16) * 2) + (indexOf + 2);
                if (i > substring2.length()) {
                    i = indexOf + 2;
                }
                substring = substring.trim();
                if (substring.length() > 8 && !substring.equalsIgnoreCase("A0000003334355502D4D4F42494C45") && a((ArrayList) arrayList, substring)) {
                    arrayList.add(new a(substring, null));
                }
            }
        }
        return arrayList;
    }

    public static String c(String str) {
        return a(str, "F");
    }

    public static String d(String str) {
        return a(str, "00");
    }

    public static String e(String str) {
        return a.containsKey(str) ? (String) a.get(str) : "";
    }
}
