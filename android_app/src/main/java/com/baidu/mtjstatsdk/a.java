package com.baidu.mtjstatsdk;

import android.content.Context;
import anet.channel.strategy.dispatch.c;
import com.baidu.mtjstatsdk.b.b;
import com.baidu.mtjstatsdk.b.d;
import com.baidu.mtjstatsdk.b.e;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class a {
    static HashMap<String, DataCoreObject> a = new HashMap();
    private static a b;

    private a() {
    }

    public static a a(String str) {
        if (b == null) {
            b = new a();
        }
        b(str);
        return b;
    }

    private void a(boolean z, String str) {
        ((DataCoreObject) a.get(str)).g = z;
    }

    static void b(String str) {
        if ((str == null || str.equals("")) && com.baidu.mtjstatsdk.b.a.a(str)) {
            d.c("statsdk", "AppKey can not be null");
        }
        if (!a.containsKey(str)) {
            a.put(str, new DataCoreObject());
        }
    }

    private boolean d(String str) {
        return ((DataCoreObject) a.get(str)).g;
    }

    public void a(int i, String str) {
        if (i >= 0 && i <= 30) {
            ((DataCoreObject) a.get(str)).a(i);
        }
    }

    public void a(Context context, String str) {
        String str2;
        if (com.baidu.mtjstatsdk.b.a.a(str)) {
            d.a("statsdk", "flush cache to " + str + "__local_stat_cache.json");
        }
        JSONObject jSONObject = new JSONObject();
        try {
            synchronized (((DataCoreObject) a.get(str)).c) {
                jSONObject.put("pr", new JSONArray(((DataCoreObject) a.get(str)).c.toString()));
            }
            synchronized (((DataCoreObject) a.get(str)).d) {
                jSONObject.put("ev", new JSONArray(((DataCoreObject) a.get(str)).d.toString()));
            }
            synchronized (((DataCoreObject) a.get(str)).f) {
                jSONObject.put("ex", new JSONArray(((DataCoreObject) a.get(str)).f.toString()));
            }
        } catch (JSONException e) {
            str2 = "flushLogWithoutHeader() construct cache error";
            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", str2);
            }
        }
        str2 = "{}";
        String jSONObject2 = jSONObject.toString();
        if (!d(str)) {
            int length = jSONObject2.getBytes().length;
            if (length >= 204800) {
                a(true, str);
                return;
            }
            ((DataCoreObject) a.get(str)).a = length;
            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "flush:cacheFileSize is:" + ((DataCoreObject) a.get(str)).a);
            }
            b.a(false, context, str + "__local_stat_cache.json", jSONObject2, false);
        } else if (com.baidu.mtjstatsdk.b.a.a(str)) {
            d.a("statsdk", "cache.json exceed 204800B,stop flush.");
        }
    }

    public void a(Context context, String str, boolean z, String str2) {
        if ((str == null || str.equals("")) && com.baidu.mtjstatsdk.b.a.a(str2)) {
            d.c("statsdk", "设置的渠道不能为空或者为null || The channel that you have been set is null or empty, please check it.");
        }
        ((DataCoreObject) a.get(str2)).h.setAppChannel(str);
        if (!(!z || str == null || str.equals(""))) {
            BasicStoreTools.getInstance().setAppChannelWithPreferenceAndAppKey(context, str, str2, true);
        }
        if (!z) {
            BasicStoreTools.getInstance().setAppChannelWithPreferenceAndAppKey(context, "", str2, false);
        }
    }

    public void a(String str, String str2) {
        if ((str == null || str.equals("")) && com.baidu.mtjstatsdk.b.a.a(str2)) {
            d.c("statsdk", "设置的渠道不能为空或者为null || The channel that you have been set is null or empty, please check it.");
        }
        ((DataCoreObject) a.get(str2)).h.setAppVersionName(str);
    }

    public void a(String str, String str2, int i, long j, long j2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("i", str);
            jSONObject.put("l", str2);
            jSONObject.put("c", i);
            jSONObject.put(c.TIMESTAMP, j);
            jSONObject.put("d", j2);
            b(jSONObject, false, str3);
            if (com.baidu.mtjstatsdk.b.a.a(str3)) {
                d.a("statsdk", "put event:" + jSONObject.toString());
            }
        } catch (Throwable e) {
            if (com.baidu.mtjstatsdk.b.a.a(str3)) {
                d.a("statsdk", e);
            }
        }
    }

    public void a(JSONObject jSONObject, boolean z, String str) {
        if (!(jSONObject == null || z)) {
            int length = jSONObject.toString().getBytes().length;
            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "putSession:addSize is:", Integer.valueOf(length));
            }
            if (((DataCoreObject) a.get(str)).a + length > 204800) {
                if (com.baidu.mtjstatsdk.b.a.a(str)) {
                    d.a("statsdk", "putSession: size is full!");
                    return;
                }
                return;
            }
        }
        synchronized (((DataCoreObject) a.get(str)).c) {
            try {
                ((DataCoreObject) a.get(str)).c.put(((DataCoreObject) a.get(str)).c.length(), jSONObject);
            } catch (Throwable e) {
                if (com.baidu.mtjstatsdk.b.a.a(str)) {
                    d.a("statsdk", e);
                }
            }
        }
    }

    public void b(Context context, String str) {
        if (com.baidu.mtjstatsdk.b.a.a(str)) {
            d.a("statsdk", "LoadLastSession()");
        }
        if (context != null && b.c(context, str + "__local_last_session.json")) {
            String a = b.a(false, context, str + "__local_last_session.json");
            if (!a.equals("")) {
                b.a(false, context, str + "__local_last_session.json", new JSONObject().toString(), false);
                b(a, str);
                a(context, str);
            } else if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "loadLastSession(): last_session.json file not found.");
            }
        }
    }

    public void b(String str, String str2) {
        if (!str.equals("{}") && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                a(jSONObject, false, str2);
                if (com.baidu.mtjstatsdk.b.a.a(str2)) {
                    d.a("statsdk", "Load last session:" + jSONObject);
                }
            } catch (JSONException e) {
                if (com.baidu.mtjstatsdk.b.a.a(str2)) {
                    d.a("statsdk", "putSession()" + e);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(org.json.JSONObject r20, boolean r21, java.lang.String r22) {
        /*
        r19 = this;
        if (r20 == 0) goto L_0x004a;
    L_0x0002:
        if (r21 != 0) goto L_0x004a;
    L_0x0004:
        r2 = r20.toString();
        r2 = r2.getBytes();
        r3 = r2.length;
        r2 = com.baidu.mtjstatsdk.b.a.a(r22);
        if (r2 == 0) goto L_0x002a;
    L_0x0013:
        r2 = 3;
        r2 = new java.lang.Object[r2];
        r4 = 0;
        r5 = "statsdk";
        r2[r4] = r5;
        r4 = 1;
        r5 = "putEvent:eventSize is:";
        r2[r4] = r5;
        r4 = 2;
        r5 = java.lang.Integer.valueOf(r3);
        r2[r4] = r5;
        com.baidu.mtjstatsdk.b.d.a(r2);
    L_0x002a:
        r2 = a;
        r0 = r22;
        r2 = r2.get(r0);
        r2 = (com.baidu.mtjstatsdk.DataCoreObject) r2;
        r2 = r2.a;
        r2 = r2 + r3;
        r3 = 204800; // 0x32000 float:2.86986E-40 double:1.011846E-318;
        if (r2 <= r3) goto L_0x004a;
    L_0x003c:
        r2 = com.baidu.mtjstatsdk.b.a.a(r22);
        if (r2 == 0) goto L_0x0049;
    L_0x0042:
        r2 = "statsdk";
        r3 = "putEvent: size is full!";
        com.baidu.mtjstatsdk.b.d.a(r2, r3);
    L_0x0049:
        return;
    L_0x004a:
        r2 = 0;
        r3 = "i";
        r0 = r20;
        r6 = r0.getString(r3);	 Catch:{ JSONException -> 0x00e9 }
        r3 = "l";
        r0 = r20;
        r7 = r0.getString(r3);	 Catch:{ JSONException -> 0x00e9 }
        r3 = "t";
        r0 = r20;
        r4 = r0.getLong(r3);	 Catch:{ JSONException -> 0x00e9 }
        r8 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r8 = r4 / r8;
        r3 = "s";
        r0 = r20;
        r3 = r0.optString(r3);	 Catch:{ JSONException -> 0x00e9 }
        r4 = "d";
        r0 = r20;
        r2 = r0.getInt(r4);	 Catch:{ JSONException -> 0x00f7 }
    L_0x0078:
        if (r2 != 0) goto L_0x01c6;
    L_0x007a:
        r2 = a;
        r0 = r22;
        r2 = r2.get(r0);
        r2 = (com.baidu.mtjstatsdk.DataCoreObject) r2;
        r10 = r2.d;
        monitor-enter(r10);
        r2 = a;	 Catch:{ all -> 0x0116 }
        r0 = r22;
        r2 = r2.get(r0);	 Catch:{ all -> 0x0116 }
        r2 = (com.baidu.mtjstatsdk.DataCoreObject) r2;	 Catch:{ all -> 0x0116 }
        r2 = r2.d;	 Catch:{ all -> 0x0116 }
        r5 = r2.length();	 Catch:{ all -> 0x0116 }
        if (r3 == 0) goto L_0x00a1;
    L_0x0099:
        r2 = "";
        r2 = r3.equals(r2);	 Catch:{ JSONException -> 0x0107 }
        if (r2 == 0) goto L_0x00aa;
    L_0x00a1:
        r2 = "s";
        r3 = "0|";
        r0 = r20;
        r0.put(r2, r3);	 Catch:{ JSONException -> 0x0107 }
    L_0x00aa:
        r4 = 0;
        r3 = r5;
    L_0x00ac:
        if (r4 >= r5) goto L_0x0215;
    L_0x00ae:
        r2 = a;	 Catch:{ JSONException -> 0x0128 }
        r0 = r22;
        r2 = r2.get(r0);	 Catch:{ JSONException -> 0x0128 }
        r2 = (com.baidu.mtjstatsdk.DataCoreObject) r2;	 Catch:{ JSONException -> 0x0128 }
        r2 = r2.d;	 Catch:{ JSONException -> 0x0128 }
        r11 = r2.getJSONObject(r4);	 Catch:{ JSONException -> 0x0128 }
        r2 = "i";
        r12 = r11.getString(r2);	 Catch:{ JSONException -> 0x0128 }
        r2 = "l";
        r13 = r11.getString(r2);	 Catch:{ JSONException -> 0x0128 }
        r2 = "t";
        r14 = r11.getLong(r2);	 Catch:{ JSONException -> 0x0128 }
        r16 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r14 = r14 / r16;
        r2 = 0;
        r16 = "d";
        r0 = r16;
        r2 = r11.getInt(r0);	 Catch:{ JSONException -> 0x0119 }
    L_0x00de:
        r14 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1));
        if (r14 != 0) goto L_0x00e4;
    L_0x00e2:
        if (r2 == 0) goto L_0x013a;
    L_0x00e4:
        r2 = r3;
    L_0x00e5:
        r4 = r4 + 1;
        r3 = r2;
        goto L_0x00ac;
    L_0x00e9:
        r2 = move-exception;
        r3 = com.baidu.mtjstatsdk.b.a.a(r22);
        if (r3 == 0) goto L_0x0049;
    L_0x00f0:
        r3 = "statsdk";
        com.baidu.mtjstatsdk.b.d.a(r3, r2);
        goto L_0x0049;
    L_0x00f7:
        r4 = move-exception;
        r4 = com.baidu.mtjstatsdk.b.a.a(r22);
        if (r4 == 0) goto L_0x0078;
    L_0x00fe:
        r4 = "statsdk";
        r5 = "old version data, No duration Tag";
        com.baidu.mtjstatsdk.b.d.a(r4, r5);
        goto L_0x0078;
    L_0x0107:
        r2 = move-exception;
        r2 = com.baidu.mtjstatsdk.b.a.a(r22);	 Catch:{ all -> 0x0116 }
        if (r2 == 0) goto L_0x00aa;
    L_0x010e:
        r2 = "statsdk";
        r3 = "event put s fail";
        com.baidu.mtjstatsdk.b.d.a(r2, r3);	 Catch:{ all -> 0x0116 }
        goto L_0x00aa;
    L_0x0116:
        r2 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0116 }
        throw r2;
    L_0x0119:
        r16 = move-exception;
        r16 = com.baidu.mtjstatsdk.b.a.a(r22);	 Catch:{ JSONException -> 0x0128 }
        if (r16 == 0) goto L_0x00de;
    L_0x0120:
        r16 = "statsdk";
        r17 = "old version data, No duration Tag";
        com.baidu.mtjstatsdk.b.d.a(r16, r17);	 Catch:{ JSONException -> 0x0128 }
        goto L_0x00de;
    L_0x0128:
        r2 = move-exception;
        r18 = r2;
        r2 = r3;
        r3 = r18;
    L_0x012e:
        r11 = com.baidu.mtjstatsdk.b.a.a(r22);	 Catch:{ all -> 0x0116 }
        if (r11 == 0) goto L_0x00e5;
    L_0x0134:
        r11 = "statsdk";
        com.baidu.mtjstatsdk.b.d.a(r11, r3);	 Catch:{ all -> 0x0116 }
        goto L_0x00e5;
    L_0x013a:
        r2 = r12.equals(r6);	 Catch:{ JSONException -> 0x0128 }
        if (r2 == 0) goto L_0x01a2;
    L_0x0140:
        r2 = r13.equals(r7);	 Catch:{ JSONException -> 0x0128 }
        if (r2 == 0) goto L_0x01a2;
    L_0x0146:
        r2 = "c";
        r0 = r20;
        r2 = r0.getInt(r2);	 Catch:{ JSONException -> 0x0128 }
        r12 = "c";
        r12 = r11.getInt(r12);	 Catch:{ JSONException -> 0x0128 }
        r12 = r12 + r2;
        r2 = "s";
        r2 = r11.optString(r2);	 Catch:{ JSONException -> 0x0128 }
        if (r2 == 0) goto L_0x0165;
    L_0x015d:
        r13 = "";
        r13 = r2.equalsIgnoreCase(r13);	 Catch:{ JSONException -> 0x0128 }
        if (r13 == 0) goto L_0x0167;
    L_0x0165:
        r2 = "0|";
    L_0x0167:
        r13 = "t";
        r0 = r20;
        r14 = r0.getLong(r13);	 Catch:{ JSONException -> 0x0128 }
        r13 = "t";
        r16 = r11.getLong(r13);	 Catch:{ JSONException -> 0x0128 }
        r14 = r14 - r16;
        r13 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0128 }
        r13.<init>();	 Catch:{ JSONException -> 0x0128 }
        r2 = r13.append(r2);	 Catch:{ JSONException -> 0x0128 }
        r2 = r2.append(r14);	 Catch:{ JSONException -> 0x0128 }
        r13 = "|";
        r2 = r2.append(r13);	 Catch:{ JSONException -> 0x0128 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x0128 }
        r3 = "c";
        r11.remove(r3);	 Catch:{ JSONException -> 0x0210 }
        r3 = "c";
        r11.put(r3, r12);	 Catch:{ JSONException -> 0x0210 }
        r3 = "s";
        r11.put(r3, r2);	 Catch:{ JSONException -> 0x0210 }
    L_0x019d:
        if (r4 >= r5) goto L_0x01a5;
    L_0x019f:
        monitor-exit(r10);	 Catch:{ all -> 0x0116 }
        goto L_0x0049;
    L_0x01a2:
        r2 = r3;
        goto L_0x00e5;
    L_0x01a5:
        r2 = a;	 Catch:{ JSONException -> 0x01b9 }
        r0 = r22;
        r2 = r2.get(r0);	 Catch:{ JSONException -> 0x01b9 }
        r2 = (com.baidu.mtjstatsdk.DataCoreObject) r2;	 Catch:{ JSONException -> 0x01b9 }
        r2 = r2.d;	 Catch:{ JSONException -> 0x01b9 }
        r0 = r20;
        r2.put(r5, r0);	 Catch:{ JSONException -> 0x01b9 }
    L_0x01b6:
        monitor-exit(r10);	 Catch:{ all -> 0x0116 }
        goto L_0x0049;
    L_0x01b9:
        r2 = move-exception;
        r3 = com.baidu.mtjstatsdk.b.a.a(r22);	 Catch:{ all -> 0x0116 }
        if (r3 == 0) goto L_0x01b6;
    L_0x01c0:
        r3 = "statsdk";
        com.baidu.mtjstatsdk.b.d.a(r3, r2);	 Catch:{ all -> 0x0116 }
        goto L_0x01b6;
    L_0x01c6:
        r2 = a;
        r0 = r22;
        r2 = r2.get(r0);
        r2 = (com.baidu.mtjstatsdk.DataCoreObject) r2;
        r3 = r2.d;
        monitor-enter(r3);
        r2 = a;	 Catch:{ all -> 0x0200 }
        r0 = r22;
        r2 = r2.get(r0);	 Catch:{ all -> 0x0200 }
        r2 = (com.baidu.mtjstatsdk.DataCoreObject) r2;	 Catch:{ all -> 0x0200 }
        r2 = r2.d;	 Catch:{ all -> 0x0200 }
        r4 = r2.length();	 Catch:{ all -> 0x0200 }
        r2 = "s";
        r5 = "0";
        r0 = r20;
        r0.put(r2, r5);	 Catch:{ JSONException -> 0x0203 }
        r2 = a;	 Catch:{ JSONException -> 0x0203 }
        r0 = r22;
        r2 = r2.get(r0);	 Catch:{ JSONException -> 0x0203 }
        r2 = (com.baidu.mtjstatsdk.DataCoreObject) r2;	 Catch:{ JSONException -> 0x0203 }
        r2 = r2.d;	 Catch:{ JSONException -> 0x0203 }
        r0 = r20;
        r2.put(r4, r0);	 Catch:{ JSONException -> 0x0203 }
    L_0x01fd:
        monitor-exit(r3);	 Catch:{ all -> 0x0200 }
        goto L_0x0049;
    L_0x0200:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0200 }
        throw r2;
    L_0x0203:
        r2 = move-exception;
        r4 = com.baidu.mtjstatsdk.b.a.a(r22);	 Catch:{ all -> 0x0200 }
        if (r4 == 0) goto L_0x01fd;
    L_0x020a:
        r4 = "statsdk";
        com.baidu.mtjstatsdk.b.d.a(r4, r2);	 Catch:{ all -> 0x0200 }
        goto L_0x01fd;
    L_0x0210:
        r2 = move-exception;
        r3 = r2;
        r2 = r4;
        goto L_0x012e;
    L_0x0215:
        r4 = r3;
        goto L_0x019d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mtjstatsdk.a.b(org.json.JSONObject, boolean, java.lang.String):void");
    }

    public void c(Context context, String str) {
        if (context != null && b.c(context, str + "__local_stat_cache.json")) {
            String a = b.a(false, context, str + "__local_stat_cache.json");
            if (!a.equals("")) {
                if (com.baidu.mtjstatsdk.b.a.a(str)) {
                    d.a("statsdk", "loadStatData, ");
                }
                try {
                    int i;
                    JSONObject jSONObject;
                    ((DataCoreObject) a.get(str)).a = a.getBytes().length;
                    if (com.baidu.mtjstatsdk.b.a.a(str)) {
                        d.a("statsdk", "load Stat Data:cacheFileSize is:" + ((DataCoreObject) a.get(str)).a);
                    }
                    JSONObject jSONObject2 = new JSONObject(a);
                    if (com.baidu.mtjstatsdk.b.a.a(str)) {
                        d.a("statsdk", "Load cache:" + a);
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    JSONArray jSONArray = jSONObject2.getJSONArray("pr");
                    for (i = 0; i < jSONArray.length(); i++) {
                        jSONObject = jSONArray.getJSONObject(i);
                        if (currentTimeMillis - jSONObject.getLong("s") <= 604800000) {
                            a(jSONObject, true, str);
                        }
                    }
                    jSONArray = jSONObject2.getJSONArray("ev");
                    for (i = 0; i < jSONArray.length(); i++) {
                        jSONObject = jSONArray.getJSONObject(i);
                        if (currentTimeMillis - jSONObject.getLong(c.TIMESTAMP) <= 604800000) {
                            b(jSONObject, true, str);
                        }
                    }
                    jSONArray = jSONObject2.getJSONArray("ex");
                    for (i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                        if (currentTimeMillis - jSONObject3.getLong(c.TIMESTAMP) <= 604800000) {
                            c(jSONObject3, true, str);
                        }
                    }
                } catch (JSONException e) {
                    if (com.baidu.mtjstatsdk.b.a.a(str)) {
                        d.a("statsdk", "Load stat data error:" + e);
                    }
                }
            } else if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "stat_cache file not found.");
            }
        }
    }

    public void c(JSONObject jSONObject, boolean z, String str) {
        if (!(jSONObject == null || z)) {
            int length = jSONObject.toString().getBytes().length;
            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "putException:addSize is:", Integer.valueOf(length));
            }
            if (((DataCoreObject) a.get(str)).a + length > 204800) {
                if (com.baidu.mtjstatsdk.b.a.a(str)) {
                    d.a("statsdk", "putException: size is full!");
                    return;
                }
                return;
            }
        }
        synchronized (((DataCoreObject) a.get(str)).f) {
            try {
                ((DataCoreObject) a.get(str)).f.put(((DataCoreObject) a.get(str)).f.length(), jSONObject);
            } catch (Throwable e) {
                if (com.baidu.mtjstatsdk.b.a.a(str)) {
                    d.a("statsdk", e);
                }
            }
        }
    }

    public boolean c(String str) {
        return ((DataCoreObject) a.get(str)).c.length() == 0 && ((DataCoreObject) a.get(str)).d.length() == 0 && ((DataCoreObject) a.get(str)).f.length() == 0;
    }

    public synchronized boolean d(Context context, String str) {
        boolean z;
        boolean z2 = false;
        synchronized (this) {
            String str2;
            HeadObject headObject = ((DataCoreObject) a.get(str)).h;
            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "sendLogData() begin.");
                d.a("statsdk", "sendLogData appkey= " + str);
                d.a("statsdk", "start installHeader begin.");
            }
            headObject.installHeader(context, ((DataCoreObject) a.get(str)).b, str);
            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "setAppKey appkey= " + str);
            }
            if (headObject != null && (headObject.getAppKey() == null || "".equalsIgnoreCase(headObject.getAppKey()))) {
                headObject.installHeader(context, ((DataCoreObject) a.get(str)).b, str);
                if (headObject.getAppKey() == null || "".equalsIgnoreCase(headObject.getAppKey())) {
                    str2 = "不能在manifest.xml中找到APP Key||can't find app key in manifest.xml.";
                    if (com.baidu.mtjstatsdk.b.a.a(str)) {
                        d.c(str2);
                    }
                    z = false;
                }
            }
            ((DataCoreObject) a.get(str)).h = headObject;
            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "end setAppKey appkey= " + headObject.getAppKey());
            }
            JSONObject jSONObject = new JSONObject();
            if (((DataCoreObject) a.get(str)) == null) {
                d.a("Log", "");
            }
            synchronized (((DataCoreObject) a.get(str)).b) {
                try {
                    ((DataCoreObject) a.get(str)).b.put(c.TIMESTAMP, System.currentTimeMillis());
                    ((DataCoreObject) a.get(str)).b.put("ss", ((SessionAnalysisObject) n.a(str).a().get(str)).getSessionStartTime());
                    jSONObject.put("he", ((DataCoreObject) a.get(str)).b);
                    synchronized (((DataCoreObject) a.get(str)).c) {
                        try {
                            jSONObject.put("pr", ((DataCoreObject) a.get(str)).c);
                            synchronized (((DataCoreObject) a.get(str)).d) {
                                try {
                                    jSONObject.put("ev", ((DataCoreObject) a.get(str)).d);
                                    synchronized (((DataCoreObject) a.get(str)).e) {
                                        synchronized (((DataCoreObject) a.get(str)).f) {
                                            try {
                                                jSONObject.put("ex", ((DataCoreObject) a.get(str)).f);
                                                str2 = jSONObject.toString();
                                                try {
                                                    e.a(context, "http://hmma.baidu.com/app.gif", str2, 50000, 50000);
                                                    if (com.baidu.mtjstatsdk.b.a.a(str)) {
                                                        d.a("statsdk", "sendLogData() send_sucess.data=" + str2);
                                                    }
                                                    z2 = true;
                                                } catch (Exception e) {
                                                    if (com.baidu.mtjstatsdk.b.a.a(str)) {
                                                        d.b("statsdk", "httpPost:" + e);
                                                    }
                                                }
                                                if (com.baidu.mtjstatsdk.b.a.a(str)) {
                                                    d.b("statsdk", "send log data over. result=" + z2);
                                                }
                                                if (z2) {
                                                    a(false, str);
                                                    ((DataCoreObject) a.get(str)).f = new JSONArray();
                                                    ((DataCoreObject) a.get(str)).d = new JSONArray();
                                                    ((DataCoreObject) a.get(str)).c = new JSONArray();
                                                    a(context, str);
                                                    ((SessionAnalysisObject) n.a(str).a().get(str)).setSessionCounted();
                                                    j.a().a(context);
                                                }
                                            } catch (Throwable e2) {
                                                if (com.baidu.mtjstatsdk.b.a.a(str)) {
                                                    d.a("statsdk", e2);
                                                }
                                                z = false;
                                            }
                                        }
                                    }
                                } catch (Throwable e22) {
                                    if (com.baidu.mtjstatsdk.b.a.a(str)) {
                                        d.a("statsdk", e22);
                                    }
                                    z = false;
                                }
                            }
                        } catch (JSONException e3) {
                            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                                d.a("statsdk", e3.toString());
                            }
                            z = false;
                        }
                    }
                } catch (Throwable e222) {
                    if (com.baidu.mtjstatsdk.b.a.a(str)) {
                        d.a("statsdk", e222);
                    }
                    z = false;
                }
            }
            if (com.baidu.mtjstatsdk.b.a.a(str)) {
                d.a("statsdk", "sendLogData() end.");
            }
            z = z2;
        }
        return z;
    }
}
