package com.ecjia.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ECJiaCommonMethod */
public class e {
    private static e a;
    private static List<String> b;
    private static String c = "#FENGCHEN#";

    private e() {
    }

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    public void a(Context context, String str) {
        int i = 0;
        if (str != null && !"".equals(str)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("my_shared", 0);
            Editor edit = sharedPreferences.edit();
            StringBuffer stringBuffer = new StringBuffer();
            String string = sharedPreferences.getString("search_list", "");
            List a = a(context);
            while (i < a.size()) {
                if (str.equals(a.get(i))) {
                    string.replace("" + c + str + c, c);
                    string.replace("" + str + c, "");
                    string.replace("" + c, "");
                }
                i++;
            }
            if ("".equals(string)) {
                stringBuffer.append(str);
                stringBuffer.append(c);
            } else {
                stringBuffer.append(str);
                stringBuffer.append(c);
                stringBuffer.append(string);
            }
            edit.putString("search_list", stringBuffer.toString());
            edit.commit();
        }
    }

    public List<String> a(Context context) {
        int i = 0;
        b = new ArrayList();
        Object string = context.getSharedPreferences("my_shared", 0).getString("search_list", "");
        if (!TextUtils.isEmpty(string)) {
            String[] split = string.split(c);
            for (Object add : split) {
                b.add(add);
            }
        }
        while (i < b.size() - 1) {
            for (int size = b.size() - 1; size > i; size--) {
                if (((String) b.get(size)).equals(b.get(i))) {
                    b.remove(size);
                }
            }
            i++;
        }
        return b;
    }

    public void b(Context context, String str) {
        int i = 0;
        if (str != null && !"".equals(str)) {
            String str2;
            SharedPreferences sharedPreferences = context.getSharedPreferences("my_seller_shared", 0);
            Editor edit = sharedPreferences.edit();
            StringBuffer stringBuffer = new StringBuffer();
            String string = sharedPreferences.getString("search_seller_list", "");
            List b = b(context);
            while (i < b.size()) {
                if (str.equals(b.get(i))) {
                    string.replace("" + c + str + c, c);
                    string.replace("" + str + c, "");
                    string.replace("" + c, "");
                }
                i++;
            }
            if (b.size() >= 7) {
                str2 = string;
                for (int i2 = 6; i2 < b.size() - 1; i2++) {
                    str2 = str2.replace("" + c + ((String) b.get(i2)), "");
                }
            } else {
                str2 = string;
            }
            if ("".equals(str2)) {
                stringBuffer.append(str);
                stringBuffer.append(c);
            } else {
                stringBuffer.append(str);
                stringBuffer.append(c);
                stringBuffer.append(str2);
            }
            edit.putString("search_seller_list", stringBuffer.toString());
            edit.commit();
        }
    }

    public List<String> b(Context context) {
        int i = 0;
        b = new ArrayList();
        Object string = context.getSharedPreferences("my_seller_shared", 0).getString("search_seller_list", "");
        if (!TextUtils.isEmpty(string)) {
            String[] split = string.split(c);
            for (Object add : split) {
                b.add(add);
            }
        }
        while (i < b.size() - 1) {
            for (int size = b.size() - 1; size > i; size--) {
                if (((String) b.get(size)).equals(b.get(i))) {
                    b.remove(size);
                }
            }
            i++;
        }
        return b;
    }

    public void c(Context context, String str) {
        int i = 0;
        if (str != null && !"".equals(str)) {
            String str2;
            SharedPreferences sharedPreferences = context.getSharedPreferences("my_good_shared", 0);
            Editor edit = sharedPreferences.edit();
            StringBuffer stringBuffer = new StringBuffer();
            String string = sharedPreferences.getString("search_good_list", "");
            List c = c(context);
            while (i < c.size()) {
                if (str.equals(c.get(i))) {
                    string.replace("" + c + str + c, c);
                    string.replace("" + str + c, "");
                    string.replace("" + c, "");
                }
                i++;
            }
            if (c.size() >= 7) {
                str2 = string;
                for (int i2 = 6; i2 < c.size() - 1; i2++) {
                    str2 = str2.replace("" + c + ((String) c.get(i2)), "");
                }
            } else {
                str2 = string;
            }
            if ("".equals(str2)) {
                stringBuffer.append(str);
                stringBuffer.append(c);
            } else {
                stringBuffer.append(str);
                stringBuffer.append(c);
                stringBuffer.append(str2);
            }
            edit.putString("search_good_list", stringBuffer.toString());
            edit.commit();
        }
    }

    public List<String> c(Context context) {
        int i = 0;
        b = new ArrayList();
        Object string = context.getSharedPreferences("my_good_shared", 0).getString("search_good_list", "");
        if (!TextUtils.isEmpty(string)) {
            String[] split = string.split(c);
            for (Object add : split) {
                b.add(add);
            }
        }
        while (i < b.size() - 1) {
            for (int size = b.size() - 1; size > i; size--) {
                if (((String) b.get(size)).equals(b.get(i))) {
                    b.remove(size);
                }
            }
            i++;
        }
        return b;
    }
}
