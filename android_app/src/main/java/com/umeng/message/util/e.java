package com.umeng.message.util;

import java.util.List;

/* compiled from: StringUtils */
public class e {
    public static String a(String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : strArr) {
            stringBuilder.append(append).append(",");
        }
        if (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public static String a(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : list) {
            stringBuilder.append(append).append(",");
        }
        if (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
