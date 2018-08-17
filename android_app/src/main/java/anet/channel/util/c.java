package anet.channel.util;

import android.text.TextUtils;
import anet.channel.request.Request;
import com.taobao.accs.ErrorCode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/* compiled from: Taobao */
public class c {
    static final Pattern a = Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        return a.matcher(str).matches();
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        char[] toCharArray = str.toCharArray();
        if (toCharArray.length <= 0 || toCharArray.length > 255) {
            return false;
        }
        int i = 0;
        boolean z = false;
        while (i < toCharArray.length) {
            if ((toCharArray[i] >= 'A' && toCharArray[i] <= 'Z') || (toCharArray[i] >= 'a' && toCharArray[i] <= 'z')) {
                z = true;
            } else if (!((toCharArray[i] >= '0' && toCharArray[i] <= '9') || toCharArray[i] == '.' || toCharArray[i] == '-')) {
                return false;
            }
            i++;
        }
        return z;
    }

    public static Map<String, List<String>> a(Map<String, List<String>> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        HashMap hashMap = new HashMap(map.size());
        for (Entry entry : map.entrySet()) {
            hashMap.put(entry.getKey(), new ArrayList((Collection) entry.getValue()));
        }
        return hashMap;
    }

    public static List<String> a(Map<String, List<String>> map, String str) {
        if (map == null || map.isEmpty() || TextUtils.isEmpty(str)) {
            return null;
        }
        for (Entry entry : map.entrySet()) {
            if (str.equalsIgnoreCase((String) entry.getKey())) {
                return (List) entry.getValue();
            }
        }
        return null;
    }

    public static String b(Map<String, List<String>> map, String str) {
        List a = a((Map) map, str);
        if (a == null || a.isEmpty()) {
            return null;
        }
        return (String) a.get(0);
    }

    public static void c(Map<String, List<String>> map, String str) {
        if (str != null) {
            for (String equalsIgnoreCase : map.keySet()) {
                if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                    break;
                }
            }
            str = null;
            if (str != null) {
                map.remove(str);
            }
        }
    }

    public static boolean a(Request request, int i) {
        return request.isRedirectEnable() && i >= ErrorCode.APP_NOT_BIND && i < 400 && i != 304 && request.getRedirectTimes() < 10;
    }

    public static boolean b(Map<String, List<String>> map) {
        try {
            if ("gzip".equalsIgnoreCase(b(map, "Content-Encoding"))) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static int c(Map<String, List<String>> map) {
        int i = 0;
        try {
            i = Integer.parseInt(b(map, "Content-Length"));
        } catch (Exception e) {
        }
        return i;
    }

    public static String a(URL url) {
        String str = null;
        if (url != null) {
            try {
                String path = url.getPath();
                int length = path.length();
                if (length > 1) {
                    int lastIndexOf = path.lastIndexOf(47);
                    if (!(lastIndexOf == -1 || lastIndexOf == length - 1)) {
                        int lastIndexOf2 = path.lastIndexOf(46);
                        if (lastIndexOf2 != -1 && lastIndexOf2 > lastIndexOf) {
                            str = path.substring(lastIndexOf2 + 1, length);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return str;
    }
}
