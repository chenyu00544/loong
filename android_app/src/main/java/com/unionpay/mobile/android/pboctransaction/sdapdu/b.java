package com.unionpay.mobile.android.pboctransaction.sdapdu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public final class b {
    public static String[] a;
    public static int b = 0;
    private static ArrayList<String> c = new ArrayList();

    public static void a() {
        HashSet b = b();
        a = new String[b.size()];
        b.toArray(a);
    }

    private static HashSet<String> b() {
        String str;
        Exception e;
        int i;
        int i2;
        HashSet<String> hashSet = new HashSet();
        String str2 = "(?i).*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
        String str3 = "";
        try {
            Process start = new ProcessBuilder(new String[0]).command(new String[]{"mount"}).redirectErrorStream(true).start();
            start.waitFor();
            InputStream inputStream = start.getInputStream();
            byte[] bArr = new byte[1024];
            str = str3;
            while (inputStream.read(bArr) != -1) {
                try {
                    str = str + new String(bArr);
                } catch (Exception e2) {
                    e = e2;
                }
            }
            inputStream.close();
        } catch (Exception e3) {
            Exception exception = e3;
            str = str3;
            e = exception;
            e.printStackTrace();
            for (String str4 : r0.split("\n")) {
                for (String str5 : str4.split(" ")) {
                    hashSet.add(str5);
                }
            }
            return hashSet;
        }
        for (i = 0; i < r6; i++) {
            if (!str4.toLowerCase(Locale.US).contains("asec") && str4.matches(str2)) {
                for (i2 = 0; i2 < r8; i2++) {
                    if (str5.startsWith("/") && !str5.toLowerCase(Locale.US).contains("vold")) {
                        hashSet.add(str5);
                    }
                }
            }
        }
        return hashSet;
    }
}
