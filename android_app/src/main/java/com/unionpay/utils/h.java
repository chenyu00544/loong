package com.unionpay.utils;

import java.util.Locale;

public class h {
    private static h g = null;
    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public String e = "";
    public String f = "";

    public static h a() {
        if (g == null) {
            if (Locale.getDefault().toString().startsWith("zh")) {
                g = new i();
            } else {
                g = new j();
            }
        }
        return g;
    }
}
