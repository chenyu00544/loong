package com.baidu.mtjstatsdk.b;

import java.util.HashMap;
import java.util.Map;

public final class a {
    public static int a = 4;
    public static Map<String, Boolean> b = new HashMap();

    public static boolean a(String str) {
        return b.containsKey(str) ? ((Boolean) b.get(str)).booleanValue() : false;
    }
}
