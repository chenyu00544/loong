package com.ecjia.a.b;

import java.util.ArrayList;

/* compiled from: ECJiaOpenTypeAnalyzer */
public class b {
    public static ArrayList<String[]> a(String str) throws Exception {
        return a(str, "ecjiaopen://app?", "&", "=");
    }

    public static ArrayList<String[]> a(String str, String str2, String str3, String str4) throws Exception {
        String str5 = null;
        if (str.indexOf(str2) == 0) {
            str5 = str.replace(str2, "");
        }
        return a(str5, str3, str4);
    }

    private static ArrayList<String[]> a(String str, String str2, String str3) throws Exception {
        if (str == null || str2 == null || str3 == null) {
            return null;
        }
        String[] split = str.split(str2);
        if (split.length <= 0) {
            return null;
        }
        ArrayList<String[]> arrayList = new ArrayList();
        for (String split2 : split) {
            Object split3 = split2.split(str3);
            if (split3 != null && split3.length == 2) {
                arrayList.add(split3);
            }
        }
        return arrayList;
    }
}
