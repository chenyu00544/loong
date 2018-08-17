package com.umeng.socialize.weixin.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WXAuthUtils {
    public static String request(String str) {
        String str2 = "";
        try {
            URLConnection openConnection = new URL(str).openConnection();
            if (openConnection != null) {
                openConnection.connect();
                InputStream inputStream = openConnection.getInputStream();
                if (inputStream != null) {
                    str2 = convertStreamToString(inputStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    private static String convertStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = "";
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                str = str + readLine;
            } else {
                inputStream.close();
                return str;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String convertStreamToString(java.io.InputStream r4) {
        /*
        r0 = new java.io.BufferedReader;
        r1 = new java.io.InputStreamReader;
        r1.<init>(r4);
        r0.<init>(r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
    L_0x000f:
        r2 = r0.readLine();	 Catch:{ IOException -> 0x002c }
        if (r2 == 0) goto L_0x0038;
    L_0x0015:
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x002c }
        r3.<init>();	 Catch:{ IOException -> 0x002c }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x002c }
        r3 = "/n";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x002c }
        r2 = r2.toString();	 Catch:{ IOException -> 0x002c }
        r1.append(r2);	 Catch:{ IOException -> 0x002c }
        goto L_0x000f;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0046 }
        r4.close();	 Catch:{ IOException -> 0x0041 }
    L_0x0033:
        r0 = r1.toString();
        return r0;
    L_0x0038:
        r4.close();	 Catch:{ IOException -> 0x003c }
        goto L_0x0033;
    L_0x003c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0033;
    L_0x0041:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0033;
    L_0x0046:
        r0 = move-exception;
        r4.close();	 Catch:{ IOException -> 0x004b }
    L_0x004a:
        throw r0;
    L_0x004b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.weixin.net.WXAuthUtils.convertStreamToString(java.io.InputStream):java.lang.String");
    }
}
