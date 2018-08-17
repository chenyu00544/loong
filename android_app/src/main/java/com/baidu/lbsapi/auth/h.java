package com.baidu.lbsapi.auth;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.baidu.location.b.g;
import com.baidu.mtjstatsdk.BasicStoreTools;
import com.taobao.accs.utl.BaseMonitor;
import com.umeng.analytics.pro.x;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class h {
    private static Context a;
    private static n d = null;
    private static int e = 0;
    private static Hashtable<String, m> f = new Hashtable();
    private static h g;
    private c b = null;
    private e c = null;
    private final Handler h = new i(this, Looper.getMainLooper());

    private h(Context context) {
        a = context;
        if (!(d == null || d.isAlive())) {
            d = null;
        }
        d();
    }

    private int a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
            int i = jSONObject.getInt("status");
            if (jSONObject.has("current") && i == 0) {
                long j = jSONObject.getLong("current");
                long currentTimeMillis = System.currentTimeMillis();
                if (((double) (currentTimeMillis - j)) / 3600000.0d >= 24.0d) {
                    i = g.x;
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (!simpleDateFormat.format(Long.valueOf(currentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(j)))) {
                        i = g.x;
                    }
                }
            }
            if (jSONObject.has("current") && i == 602) {
                if (((double) ((System.currentTimeMillis() - jSONObject.getLong("current")) / 1000)) > 180.0d) {
                    return g.x;
                }
            }
            return i;
        } catch (JSONException e) {
            JSONException jSONException = e;
            int i2 = -1;
            jSONException.printStackTrace();
            return i2;
        }
    }

    public static h a(Context context) {
        if (g == null) {
            synchronized (h.class) {
                if (g == null) {
                    g = new h(context);
                }
            }
        } else {
            a = context;
        }
        return g;
    }

    private String a(int i) throws IOException {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        Object obj;
        Object obj2;
        BufferedReader bufferedReader;
        Throwable th;
        Throwable th2;
        String str = null;
        try {
            fileInputStream = new FileInputStream(new File("/proc/" + i + "/cmdline"));
            try {
                inputStreamReader = new InputStreamReader(fileInputStream);
            } catch (FileNotFoundException e) {
                obj = str;
                obj2 = str;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (IOException e2) {
                obj = str;
                obj2 = str;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (Throwable th3) {
                obj2 = str;
                String str2 = str;
                th = th3;
                obj = str2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                try {
                    str = bufferedReader.readLine();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (FileNotFoundException e3) {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str;
                } catch (IOException e4) {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str;
                } catch (Throwable th4) {
                    th = th4;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                obj = str;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (IOException e6) {
                obj = str;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (Throwable th32) {
                th2 = th32;
                obj = str;
                th = th2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e7) {
            bufferedReader = str;
            inputStreamReader = str;
            fileInputStream = str;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (IOException e8) {
            bufferedReader = str;
            inputStreamReader = str;
            fileInputStream = str;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable th322) {
            inputStreamReader = str;
            fileInputStream = str;
            th2 = th322;
            bufferedReader = str;
            th = th2;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    private String a(Context context, String str) {
        String str2 = "";
        m mVar;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                mVar = (m) f.get(str);
                if (mVar != null) {
                    mVar.a(-1, ErrorMessage.a("AndroidManifest.xml的application中没有meta-data标签"));
                }
                return str2;
            }
            str2 = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
            if (str2 == null || str2.equals("")) {
                mVar = (m) f.get(str);
                if (mVar != null) {
                    mVar.a(-1, ErrorMessage.a("无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
                }
            }
            return str2;
        } catch (NameNotFoundException e) {
            mVar = (m) f.get(str);
            if (mVar != null) {
                mVar.a(-1, ErrorMessage.a("无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
            }
        }
    }

    private synchronized void a(String str, String str2) {
        if (str == null) {
            str = e();
        }
        Message obtainMessage = this.h.obtainMessage();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
            if (!jSONObject.has("current")) {
                jSONObject.put("current", System.currentTimeMillis());
            }
            c(jSONObject.toString());
            if (jSONObject.has("current")) {
                jSONObject.remove("current");
            }
            obtainMessage.what = jSONObject.getInt("status");
            obtainMessage.obj = jSONObject.toString();
            Bundle bundle = new Bundle();
            bundle.putString("listenerKey", str2);
            obtainMessage.setData(bundle);
            this.h.sendMessage(obtainMessage);
        } catch (JSONException e) {
            e.printStackTrace();
            obtainMessage.what = -1;
            obtainMessage.obj = new JSONObject();
            bundle = new Bundle();
            bundle.putString("listenerKey", str2);
            obtainMessage.setData(bundle);
            this.h.sendMessage(obtainMessage);
        }
        d.c();
        e--;
        if (a.a) {
            a.a("httpRequest called mAuthCounter-- = " + e);
        }
        if (e == 0) {
            d.a();
            if (d != null) {
                d = null;
            }
        }
    }

    private void a(boolean z, String str, Hashtable<String, String> hashtable, String str2) {
        String a = a(a, str2);
        if (a != null && !a.equals("")) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", "https://api.map.baidu.com/sdkcs/verify");
            hashMap.put("output", "json");
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_AK, a);
            hashMap.put("mcode", b.a(a));
            hashMap.put("from", "lbs_yunsdk");
            if (hashtable != null && hashtable.size() > 0) {
                for (Entry entry : hashtable.entrySet()) {
                    String str3 = (String) entry.getKey();
                    a = (String) entry.getValue();
                    if (!(TextUtils.isEmpty(str3) || TextUtils.isEmpty(a))) {
                        hashMap.put(str3, a);
                    }
                }
            }
            CharSequence charSequence = "";
            try {
                charSequence = CommonParam.a(a);
            } catch (Exception e) {
            }
            a.a("cuid:" + charSequence);
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put(BasicStoreTools.DEVICE_CUID, "");
            } else {
                hashMap.put(BasicStoreTools.DEVICE_CUID, charSequence);
            }
            hashMap.put("pcn", a.getPackageName());
            hashMap.put("version", "1.0.6");
            charSequence = "";
            try {
                charSequence = b.c(a);
            } catch (Exception e2) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put("macaddr", "");
            } else {
                hashMap.put("macaddr", charSequence);
            }
            charSequence = "";
            try {
                charSequence = b.a();
            } catch (Exception e3) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put(x.F, "");
            } else {
                hashMap.put(x.F, charSequence);
            }
            if (z) {
                hashMap.put("force", z ? "1" : "0");
            }
            if (str == null) {
                hashMap.put("from_service", "");
            } else {
                hashMap.put("from_service", str);
            }
            this.b = new c(a);
            this.b.a(hashMap, new k(this, str2));
        }
    }

    private void a(boolean z, String str, Hashtable<String, String> hashtable, String[] strArr, String str2) {
        String a = a(a, str2);
        if (a != null && !a.equals("")) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", "https://api.map.baidu.com/sdkcs/verify");
            hashMap.put("output", "json");
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_AK, a);
            hashMap.put("from", "lbs_yunsdk");
            if (hashtable != null && hashtable.size() > 0) {
                for (Entry entry : hashtable.entrySet()) {
                    String str3 = (String) entry.getKey();
                    a = (String) entry.getValue();
                    if (!(TextUtils.isEmpty(str3) || TextUtils.isEmpty(a))) {
                        hashMap.put(str3, a);
                    }
                }
            }
            CharSequence charSequence = "";
            try {
                charSequence = CommonParam.a(a);
            } catch (Exception e) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put(BasicStoreTools.DEVICE_CUID, "");
            } else {
                hashMap.put(BasicStoreTools.DEVICE_CUID, charSequence);
            }
            hashMap.put("pcn", a.getPackageName());
            hashMap.put("version", "1.0.6");
            charSequence = "";
            try {
                charSequence = b.c(a);
            } catch (Exception e2) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put("macaddr", "");
            } else {
                hashMap.put("macaddr", charSequence);
            }
            charSequence = "";
            try {
                charSequence = b.a();
            } catch (Exception e3) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put(x.F, "");
            } else {
                hashMap.put(x.F, charSequence);
            }
            if (z) {
                hashMap.put("force", z ? "1" : "0");
            }
            if (str == null) {
                hashMap.put("from_service", "");
            } else {
                hashMap.put("from_service", str);
            }
            this.c = new e(a);
            this.c.a(hashMap, strArr, new l(this, str2));
        }
    }

    private String b(Context context) {
        int myPid = Process.myPid();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        String str = null;
        try {
            str = a(myPid);
        } catch (IOException e) {
        }
        return str == null ? a.getPackageName() : str;
    }

    private boolean b(String str) {
        String a = a(a, str);
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(e());
            if (!jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_AK)) {
                return true;
            }
            Object string = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_AK);
            return (a == null || string == null || a.equals(string)) ? false : true;
        } catch (JSONException e) {
            e.printStackTrace();
            String str3 = str2;
        }
    }

    private void c(String str) {
        a.getSharedPreferences("authStatus_" + b(a), 0).edit().putString("status", str).commit();
    }

    private void d() {
        synchronized (h.class) {
            if (d == null) {
                d = new n(BaseMonitor.ALARM_POINT_AUTH);
                d.start();
                while (d.a == null) {
                    try {
                        if (a.a) {
                            a.a("wait for create auth thread.");
                        }
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String e() {
        return a.getSharedPreferences("authStatus_" + b(a), 0).getString("status", "{\"status\":601}");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(boolean r10, java.lang.String r11, java.util.Hashtable<java.lang.String, java.lang.String> r12, com.baidu.lbsapi.auth.m r13) {
        /*
        r9 = this;
        r0 = -1;
        r7 = com.baidu.lbsapi.auth.h.class;
        monitor-enter(r7);
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c5 }
        r1.<init>();	 Catch:{ all -> 0x00c5 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00c5 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x00c5 }
        r2 = "";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00c5 }
        r4 = r1.toString();	 Catch:{ all -> 0x00c5 }
        if (r13 == 0) goto L_0x0022;
    L_0x001d:
        r1 = f;	 Catch:{ all -> 0x00c5 }
        r1.put(r4, r13);	 Catch:{ all -> 0x00c5 }
    L_0x0022:
        r1 = a;	 Catch:{ all -> 0x00c5 }
        r1 = r9.a(r1, r4);	 Catch:{ all -> 0x00c5 }
        if (r1 == 0) goto L_0x0032;
    L_0x002a:
        r2 = "";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x00c5 }
        if (r1 == 0) goto L_0x0035;
    L_0x0032:
        monitor-exit(r7);	 Catch:{ all -> 0x00c5 }
        r2 = r0;
    L_0x0034:
        return r2;
    L_0x0035:
        r1 = e;	 Catch:{ all -> 0x00c5 }
        r1 = r1 + 1;
        e = r1;	 Catch:{ all -> 0x00c5 }
        r1 = com.baidu.lbsapi.auth.a.a;	 Catch:{ all -> 0x00c5 }
        if (r1 == 0) goto L_0x0057;
    L_0x003f:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c5 }
        r1.<init>();	 Catch:{ all -> 0x00c5 }
        r2 = " mAuthCounter  ++ = ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x00c5 }
        r2 = e;	 Catch:{ all -> 0x00c5 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x00c5 }
        r1 = r1.toString();	 Catch:{ all -> 0x00c5 }
        com.baidu.lbsapi.auth.a.a(r1);	 Catch:{ all -> 0x00c5 }
    L_0x0057:
        r1 = r9.e();	 Catch:{ all -> 0x00c5 }
        r2 = com.baidu.lbsapi.auth.a.a;	 Catch:{ all -> 0x00c5 }
        if (r2 == 0) goto L_0x0075;
    L_0x005f:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c5 }
        r2.<init>();	 Catch:{ all -> 0x00c5 }
        r3 = "getAuthMessage from cache:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00c5 }
        r2 = r2.append(r1);	 Catch:{ all -> 0x00c5 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c5 }
        com.baidu.lbsapi.auth.a.a(r2);	 Catch:{ all -> 0x00c5 }
    L_0x0075:
        r2 = r9.a(r1);	 Catch:{ all -> 0x00c5 }
        r1 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        if (r2 != r1) goto L_0x0091;
    L_0x007d:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00c0 }
        r1.<init>();	 Catch:{ JSONException -> 0x00c0 }
        r3 = "status";
        r5 = 602; // 0x25a float:8.44E-43 double:2.974E-321;
        r1 = r1.put(r3, r5);	 Catch:{ JSONException -> 0x00c0 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00c0 }
        r9.c(r1);	 Catch:{ JSONException -> 0x00c0 }
    L_0x0091:
        r9.d();	 Catch:{ all -> 0x00c5 }
        r1 = com.baidu.lbsapi.auth.a.a;	 Catch:{ all -> 0x00c5 }
        if (r1 == 0) goto L_0x00b2;
    L_0x0098:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c5 }
        r1.<init>();	 Catch:{ all -> 0x00c5 }
        r3 = "mThreadLooper.mHandler = ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x00c5 }
        r3 = d;	 Catch:{ all -> 0x00c5 }
        r3 = r3.a;	 Catch:{ all -> 0x00c5 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x00c5 }
        r1 = r1.toString();	 Catch:{ all -> 0x00c5 }
        com.baidu.lbsapi.auth.a.a(r1);	 Catch:{ all -> 0x00c5 }
    L_0x00b2:
        r1 = d;	 Catch:{ all -> 0x00c5 }
        if (r1 == 0) goto L_0x00bc;
    L_0x00b6:
        r1 = d;	 Catch:{ all -> 0x00c5 }
        r1 = r1.a;	 Catch:{ all -> 0x00c5 }
        if (r1 != 0) goto L_0x00c8;
    L_0x00bc:
        monitor-exit(r7);	 Catch:{ all -> 0x00c5 }
        r2 = r0;
        goto L_0x0034;
    L_0x00c0:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x00c5 }
        goto L_0x0091;
    L_0x00c5:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x00c5 }
        throw r0;
    L_0x00c8:
        r0 = d;	 Catch:{ all -> 0x00c5 }
        r8 = r0.a;	 Catch:{ all -> 0x00c5 }
        r0 = new com.baidu.lbsapi.auth.j;	 Catch:{ all -> 0x00c5 }
        r1 = r9;
        r3 = r10;
        r5 = r11;
        r6 = r12;
        r0.<init>(r1, r2, r3, r4, r5, r6);	 Catch:{ all -> 0x00c5 }
        r8.post(r0);	 Catch:{ all -> 0x00c5 }
        monitor-exit(r7);	 Catch:{ all -> 0x00c5 }
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.h.a(boolean, java.lang.String, java.util.Hashtable, com.baidu.lbsapi.auth.m):int");
    }
}
