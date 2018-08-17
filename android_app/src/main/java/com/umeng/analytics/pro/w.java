package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.s.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UMStoreManager */
public class w {
    public static final int a = 2049;
    public static final int b = 2050;
    private static Context c = null;
    private static String d = null;
    private static final String e = "umeng+";
    private static final String f = "ek__id";
    private static final String g = "ek_key";
    private List<String> h;

    /* compiled from: UMStoreManager */
    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION
    }

    /* compiled from: UMStoreManager */
    private static class b {
        private static final w a = new w();

        private b() {
        }
    }

    private w() {
        this.h = new ArrayList();
        if (c != null) {
            b();
            this.h.clear();
        }
    }

    public static final w a(Context context) {
        c = context;
        return b.a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.json.JSONArray r8) {
        /*
        r7 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0089, Throwable -> 0x009e, all -> 0x00ae }
        r1 = com.umeng.analytics.pro.u.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0089, Throwable -> 0x009e, all -> 0x00ae }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0089, Throwable -> 0x009e, all -> 0x00ae }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0089, Throwable -> 0x009e }
        r1 = 0;
    L_0x000f:
        r2 = r8.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0089, Throwable -> 0x009e }
        if (r1 >= r2) goto L_0x0077;
    L_0x0015:
        r3 = r8.getJSONObject(r1);	 Catch:{ Exception -> 0x00ce }
        r4 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00ce }
        r4.<init>();	 Catch:{ Exception -> 0x00ce }
        r2 = "__i";
        r2 = r3.optString(r2);	 Catch:{ Exception -> 0x00ce }
        r5 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00ce }
        if (r5 == 0) goto L_0x0038;
    L_0x002a:
        r2 = c;	 Catch:{ Exception -> 0x00ce }
        r2 = com.umeng.analytics.pro.bb.g(r2);	 Catch:{ Exception -> 0x00ce }
        r5 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00ce }
        if (r5 == 0) goto L_0x0038;
    L_0x0036:
        r2 = "";
    L_0x0038:
        r5 = "__i";
        r4.put(r5, r2);	 Catch:{ Exception -> 0x00ce }
        r2 = "__e";
        r5 = "id";
        r5 = r3.optString(r5);	 Catch:{ Exception -> 0x00ce }
        r4.put(r2, r5);	 Catch:{ Exception -> 0x00ce }
        r2 = "__t";
        r5 = "__t";
        r5 = r3.optInt(r5);	 Catch:{ Exception -> 0x00ce }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x00ce }
        r4.put(r2, r5);	 Catch:{ Exception -> 0x00ce }
        r2 = "__i";
        r3.remove(r2);	 Catch:{ Exception -> 0x00ce }
        r2 = "__t";
        r3.remove(r2);	 Catch:{ Exception -> 0x00ce }
        r2 = "__s";
        r3 = r3.toString();	 Catch:{ Exception -> 0x00ce }
        r3 = r7.a(r3);	 Catch:{ Exception -> 0x00ce }
        r4.put(r2, r3);	 Catch:{ Exception -> 0x00ce }
        r2 = "__et";
        r3 = 0;
        r0.insert(r2, r3, r4);	 Catch:{ Exception -> 0x00ce }
    L_0x0074:
        r1 = r1 + 1;
        goto L_0x000f;
    L_0x0077:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0089, Throwable -> 0x009e }
        if (r0 == 0) goto L_0x007f;
    L_0x007c:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00c1 }
    L_0x007f:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
    L_0x0088:
        return;
    L_0x0089:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x00c9 }
        com.umeng.analytics.pro.v.b(r1);	 Catch:{ all -> 0x00c9 }
        if (r0 == 0) goto L_0x0094;
    L_0x0091:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00c3 }
    L_0x0094:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0088;
    L_0x009e:
        r1 = move-exception;
        if (r0 == 0) goto L_0x00a4;
    L_0x00a1:
        r0.endTransaction();	 Catch:{ Throwable -> 0x00c5 }
    L_0x00a4:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0088;
    L_0x00ae:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x00b2:
        if (r1 == 0) goto L_0x00b7;
    L_0x00b4:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00c7 }
    L_0x00b7:
        r1 = c;
        r1 = com.umeng.analytics.pro.u.a(r1);
        r1.b();
        throw r0;
    L_0x00c1:
        r0 = move-exception;
        goto L_0x007f;
    L_0x00c3:
        r0 = move-exception;
        goto L_0x0094;
    L_0x00c5:
        r0 = move-exception;
        goto L_0x00a4;
    L_0x00c7:
        r1 = move-exception;
        goto L_0x00b7;
    L_0x00c9:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x00b2;
    L_0x00ce:
        r2 = move-exception;
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.w.a(org.json.JSONArray):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r6, java.lang.String r7, int r8) {
        /*
        r5 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e, all -> 0x006e }
        r1 = com.umeng.analytics.pro.u.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e, all -> 0x006e }
        r0 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e, all -> 0x006e }
        r0.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r1 = new android.content.ContentValues;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r1.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r2 = "__i";
        r1.put(r2, r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r2 = r5.a(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        if (r3 != 0) goto L_0x0036;
    L_0x0022:
        r3 = "__a";
        r1.put(r3, r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r2 = "__t";
        r3 = java.lang.Integer.valueOf(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r1.put(r2, r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        r2 = "__er";
        r3 = 0;
        r0.insert(r2, r3, r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
    L_0x0036:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0049, Throwable -> 0x005e }
        if (r0 == 0) goto L_0x003e;
    L_0x003b:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0081 }
    L_0x003e:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
    L_0x0047:
        r0 = 0;
        return r0;
    L_0x0049:
        r1 = move-exception;
        r1 = c;	 Catch:{ all -> 0x0089 }
        com.umeng.analytics.pro.v.b(r1);	 Catch:{ all -> 0x0089 }
        if (r0 == 0) goto L_0x0054;
    L_0x0051:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0083 }
    L_0x0054:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0047;
    L_0x005e:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0064;
    L_0x0061:
        r0.endTransaction();	 Catch:{ Throwable -> 0x0085 }
    L_0x0064:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0047;
    L_0x006e:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0072:
        if (r1 == 0) goto L_0x0077;
    L_0x0074:
        r1.endTransaction();	 Catch:{ Throwable -> 0x0087 }
    L_0x0077:
        r1 = c;
        r1 = com.umeng.analytics.pro.u.a(r1);
        r1.b();
        throw r0;
    L_0x0081:
        r0 = move-exception;
        goto L_0x003e;
    L_0x0083:
        r0 = move-exception;
        goto L_0x0054;
    L_0x0085:
        r0 = move-exception;
        goto L_0x0064;
    L_0x0087:
        r1 = move-exception;
        goto L_0x0077;
    L_0x0089:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.w.a(java.lang.String, java.lang.String, int):boolean");
    }

    public boolean a(String str, JSONObject jSONObject, a aVar) {
        SQLiteDatabase a;
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Throwable th2;
        Cursor cursor2 = null;
        if (jSONObject != null) {
            try {
                a = u.a(c).a();
                try {
                    a.beginTransaction();
                    if (aVar == a.BEGIN) {
                        long longValue = ((Long) jSONObject.get("__e")).longValue();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(com.umeng.analytics.pro.s.c.a.a, str);
                        contentValues.put("__e", String.valueOf(longValue));
                        a.insert(c.a, null, contentValues);
                        cursor = null;
                    } else if (aVar == a.END) {
                        a.execSQL("update __sd set __f=\"" + ((Long) jSONObject.get(com.umeng.analytics.pro.s.c.a.g)).longValue() + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
                        cursor = null;
                    } else if (aVar == a.PAGE) {
                        a(str, jSONObject, a, "__a");
                        cursor = null;
                    } else if (aVar == a.AUTOPAGE) {
                        a(str, jSONObject, a, com.umeng.analytics.pro.s.c.a.c);
                        cursor = null;
                    } else if (aVar == a.NEWSESSION) {
                        Object jSONObject2;
                        Object obj;
                        Object a2;
                        try {
                            jSONObject2 = jSONObject.getJSONObject(com.umeng.analytics.pro.s.c.a.e);
                        } catch (Exception e) {
                            jSONObject2 = null;
                        }
                        if (jSONObject2 != null) {
                            cursor = a.rawQuery("select __d from __sd where __ii=\"" + str + "\"", null);
                            if (cursor != null) {
                                while (cursor.moveToNext()) {
                                    try {
                                        cursor2 = b(cursor.getString(cursor.getColumnIndex(com.umeng.analytics.pro.s.c.a.e)));
                                    } catch (SQLiteDatabaseCorruptException e2) {
                                        sQLiteDatabase = a;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        cursor2 = cursor;
                                        th2 = th;
                                    }
                                }
                                obj = cursor2;
                            } else {
                                obj = null;
                            }
                        } else {
                            obj = null;
                            cursor = null;
                        }
                        if (jSONObject2 != null) {
                            try {
                                JSONArray jSONArray = new JSONArray();
                                if (!TextUtils.isEmpty(obj)) {
                                    jSONArray = new JSONArray(obj);
                                }
                                jSONArray.put(jSONObject2);
                                a2 = a(jSONArray.toString());
                                if (!TextUtils.isEmpty(a2)) {
                                    a.execSQL("update  __sd set __d=\"" + a2 + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
                                }
                            } catch (Exception e3) {
                            }
                        }
                        try {
                            JSONObject jSONObject3 = jSONObject.getJSONObject(com.umeng.analytics.pro.s.c.a.d);
                            if (jSONObject3 != null) {
                                a2 = a(jSONObject3.toString());
                                if (!TextUtils.isEmpty(a2)) {
                                    a.execSQL("update  __sd set __c=\"" + a2 + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
                                }
                            }
                        } catch (Exception e4) {
                        }
                        try {
                            a.execSQL("update  __sd set __f=\"" + String.valueOf(jSONObject.getLong(com.umeng.analytics.pro.s.c.a.g)) + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
                        } catch (Exception e5) {
                        }
                    } else {
                        cursor = null;
                    }
                    a.setTransactionSuccessful();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.endTransaction();
                        } catch (Throwable th4) {
                        }
                    }
                    u.a(c).b();
                } catch (SQLiteDatabaseCorruptException e6) {
                    cursor = null;
                    sQLiteDatabase = a;
                    try {
                        v.b(c);
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                            } catch (Throwable th5) {
                            }
                        }
                        u.a(c).b();
                        return false;
                    } catch (Throwable th6) {
                        th = th6;
                        a = sQLiteDatabase;
                        cursor2 = cursor;
                        th2 = th;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (a != null) {
                            try {
                                a.endTransaction();
                            } catch (Throwable th7) {
                            }
                        }
                        u.a(c).b();
                        throw th2;
                    }
                } catch (Throwable th8) {
                    th2 = th8;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (a != null) {
                        a.endTransaction();
                    }
                    u.a(c).b();
                    throw th2;
                }
            } catch (SQLiteDatabaseCorruptException e7) {
                cursor = null;
                v.b(c);
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                u.a(c).b();
                return false;
            } catch (Throwable th9) {
                th2 = th9;
                a = null;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (a != null) {
                    a.endTransaction();
                }
                u.a(c).b();
                throw th2;
            }
        }
        return false;
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase, String str2) throws JSONException {
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            Object obj;
            rawQuery = sQLiteDatabase.rawQuery("select " + str2 + " from " + c.a + " where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"", null);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    try {
                        cursor = b(rawQuery.getString(rawQuery.getColumnIndex(str2)));
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        cursor = rawQuery;
                        th = th3;
                    }
                }
                obj = cursor;
            } else {
                obj = null;
            }
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(obj)) {
                jSONArray = new JSONArray(obj);
            }
            jSONArray.put(jSONObject);
            Object a = a(jSONArray.toString());
            if (!TextUtils.isEmpty(a)) {
                sQLiteDatabase.execSQL("update __sd set " + str2 + "=\"" + a + "\" where " + com.umeng.analytics.pro.s.c.a.a + "=\"" + str + "\"");
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        c(jSONObject2);
        b(jSONObject2);
        a(jSONObject2);
        try {
            if (jSONObject2.length() > 0) {
                jSONObject.put("body", jSONObject2);
            }
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    private void a(JSONObject jSONObject) {
        SQLiteDatabase a;
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Throwable th2;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            a = u.a(c).a();
            try {
                a.beginTransaction();
                rawQuery = a.rawQuery("select *  from __et", null);
                if (rawQuery != null) {
                    try {
                        String string;
                        JSONObject jSONObject2;
                        JSONArray optJSONArray;
                        JSONObject jSONObject3 = new JSONObject();
                        JSONObject jSONObject4 = new JSONObject();
                        while (rawQuery.moveToNext()) {
                            int i = rawQuery.getInt(rawQuery.getColumnIndex("__t"));
                            string = rawQuery.getString(rawQuery.getColumnIndex("__i"));
                            String string2 = rawQuery.getString(rawQuery.getColumnIndex(com.umeng.analytics.pro.s.b.a.c));
                            if ("".equals(string)) {
                                string = bb.a();
                            }
                            switch (i) {
                                case a /*2049*/:
                                    if (!TextUtils.isEmpty(string2)) {
                                        jSONObject2 = new JSONObject(b(string2));
                                        if (jSONObject3.has(string)) {
                                            optJSONArray = jSONObject3.optJSONArray(string);
                                        } else {
                                            optJSONArray = new JSONArray();
                                        }
                                        optJSONArray.put(jSONObject2);
                                        jSONObject3.put(string, optJSONArray);
                                        break;
                                    }
                                    continue;
                                case b /*2050*/:
                                    if (!TextUtils.isEmpty(string2)) {
                                        jSONObject2 = new JSONObject(b(string2));
                                        if (jSONObject4.has(string)) {
                                            optJSONArray = jSONObject4.optJSONArray(string);
                                        } else {
                                            optJSONArray = new JSONArray();
                                        }
                                        optJSONArray.put(jSONObject2);
                                        jSONObject4.put(string, optJSONArray);
                                        break;
                                    }
                                    continue;
                                default:
                                    break;
                            }
                        }
                        if (jSONObject3.length() > 0) {
                            optJSONArray = new JSONArray();
                            Iterator keys = jSONObject3.keys();
                            while (keys.hasNext()) {
                                jSONObject2 = new JSONObject();
                                string = (String) keys.next();
                                jSONObject2.put(string, new JSONArray(jSONObject3.optString(string)));
                                if (jSONObject2.length() > 0) {
                                    optJSONArray.put(jSONObject2);
                                }
                            }
                            if (optJSONArray.length() > 0) {
                                jSONObject.put(x.aJ, optJSONArray);
                            }
                        }
                        if (jSONObject4.length() > 0) {
                            optJSONArray = new JSONArray();
                            Iterator keys2 = jSONObject4.keys();
                            while (keys2.hasNext()) {
                                JSONObject jSONObject5 = new JSONObject();
                                string = (String) keys2.next();
                                jSONObject5.put(string, new JSONArray(jSONObject4.optString(string)));
                                if (jSONObject5.length() > 0) {
                                    optJSONArray.put(jSONObject5);
                                }
                            }
                            if (optJSONArray.length() > 0) {
                                jSONObject.put(x.aK, optJSONArray);
                            }
                        }
                    } catch (SQLiteDatabaseCorruptException e) {
                        cursor = rawQuery;
                        sQLiteDatabase = a;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                a.setTransactionSuccessful();
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (a != null) {
                    try {
                        a.endTransaction();
                    } catch (Throwable th4) {
                    }
                }
                u.a(c).b();
            } catch (SQLiteDatabaseCorruptException e2) {
                sQLiteDatabase = a;
                try {
                    v.b(c);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th5) {
                        }
                    }
                    u.a(c).b();
                } catch (Throwable th6) {
                    th2 = th6;
                    a = sQLiteDatabase;
                    rawQuery = cursor;
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (a != null) {
                        try {
                            a.endTransaction();
                        } catch (Throwable th7) {
                        }
                    }
                    u.a(c).b();
                    throw th;
                }
            } catch (Throwable th8) {
                th2 = th8;
                rawQuery = null;
                th = th2;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (a != null) {
                    a.endTransaction();
                }
                u.a(c).b();
                throw th;
            }
        } catch (SQLiteDatabaseCorruptException e3) {
            sQLiteDatabase = null;
            v.b(c);
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            u.a(c).b();
        } catch (Throwable th82) {
            a = null;
            th = th82;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (a != null) {
                a.endTransaction();
            }
            u.a(c).b();
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(org.json.JSONObject r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c0, Throwable -> 0x007b, all -> 0x0091 }
        r1 = com.umeng.analytics.pro.u.a(r1);	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c0, Throwable -> 0x007b, all -> 0x0091 }
        r1 = r1.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x00c0, Throwable -> 0x007b, all -> 0x0091 }
        r1.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be, all -> 0x00b2 }
        r2 = "select *  from __er";
        r3 = 0;
        r0 = r1.rawQuery(r2, r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be, all -> 0x00b2 }
        if (r0 == 0) goto L_0x0064;
    L_0x0017:
        r2 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        r2.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
    L_0x001c:
        r3 = r0.moveToNext();	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        if (r3 == 0) goto L_0x0059;
    L_0x0022:
        r3 = "__a";
        r3 = r0.getColumnIndex(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        r3 = r0.getString(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        if (r4 != 0) goto L_0x001c;
    L_0x0032:
        r4 = new org.json.JSONObject;	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        r3 = r6.b(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        r4.<init>(r3);	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        r2.put(r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        goto L_0x001c;
    L_0x003f:
        r2 = move-exception;
    L_0x0040:
        r2 = c;	 Catch:{ all -> 0x00b8 }
        com.umeng.analytics.pro.v.b(r2);	 Catch:{ all -> 0x00b8 }
        if (r0 == 0) goto L_0x004a;
    L_0x0047:
        r0.close();
    L_0x004a:
        if (r1 == 0) goto L_0x004f;
    L_0x004c:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00ac }
    L_0x004f:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
    L_0x0058:
        return;
    L_0x0059:
        r3 = r2.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        if (r3 <= 0) goto L_0x0064;
    L_0x005f:
        r3 = "error";
        r7.put(r3, r2);	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
    L_0x0064:
        r1.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x003f, Throwable -> 0x00be }
        if (r0 == 0) goto L_0x006c;
    L_0x0069:
        r0.close();
    L_0x006c:
        if (r1 == 0) goto L_0x0071;
    L_0x006e:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00aa }
    L_0x0071:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0058;
    L_0x007b:
        r1 = move-exception;
        r1 = r0;
    L_0x007d:
        if (r0 == 0) goto L_0x0082;
    L_0x007f:
        r0.close();
    L_0x0082:
        if (r1 == 0) goto L_0x0087;
    L_0x0084:
        r1.endTransaction();	 Catch:{ Throwable -> 0x00ae }
    L_0x0087:
        r0 = c;
        r0 = com.umeng.analytics.pro.u.a(r0);
        r0.b();
        goto L_0x0058;
    L_0x0091:
        r1 = move-exception;
        r2 = r0;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x0096:
        if (r1 == 0) goto L_0x009b;
    L_0x0098:
        r1.close();
    L_0x009b:
        if (r2 == 0) goto L_0x00a0;
    L_0x009d:
        r2.endTransaction();	 Catch:{ Throwable -> 0x00b0 }
    L_0x00a0:
        r1 = c;
        r1 = com.umeng.analytics.pro.u.a(r1);
        r1.b();
        throw r0;
    L_0x00aa:
        r0 = move-exception;
        goto L_0x0071;
    L_0x00ac:
        r0 = move-exception;
        goto L_0x004f;
    L_0x00ae:
        r0 = move-exception;
        goto L_0x0087;
    L_0x00b0:
        r1 = move-exception;
        goto L_0x00a0;
    L_0x00b2:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0096;
    L_0x00b8:
        r2 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0096;
    L_0x00be:
        r2 = move-exception;
        goto L_0x007d;
    L_0x00c0:
        r1 = move-exception;
        r1 = r0;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.w.b(org.json.JSONObject):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(org.json.JSONObject r18) {
        /*
        r17 = this;
        r3 = 0;
        r2 = 0;
        r4 = c;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156, all -> 0x016b }
        r4 = com.umeng.analytics.pro.u.a(r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156, all -> 0x016b }
        r3 = r4.a();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156, all -> 0x016b }
        r3.beginTransaction();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156, all -> 0x018e }
        r4 = "select *  from __sd";
        r5 = 0;
        r2 = r3.rawQuery(r4, r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156, all -> 0x018e }
        if (r2 == 0) goto L_0x013f;
    L_0x0018:
        r4 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r4.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r0 = r17;
        r5 = r0.h;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r5.clear();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
    L_0x0024:
        r5 = r2.moveToNext();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r5 == 0) goto L_0x0132;
    L_0x002a:
        r5 = new org.json.JSONObject;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r5.<init>();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r6 = "__f";
        r6 = r2.getColumnIndex(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r6 = r2.getString(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r7 = "__e";
        r7 = r2.getColumnIndex(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r7 = r2.getString(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r8 = android.text.TextUtils.isEmpty(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r8 != 0) goto L_0x0024;
    L_0x0049:
        r8 = android.text.TextUtils.isEmpty(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r8 != 0) goto L_0x0024;
    L_0x004f:
        r8 = java.lang.Long.parseLong(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r10 = java.lang.Long.parseLong(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r8 = r8 - r10;
        r10 = 0;
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r8 <= 0) goto L_0x0024;
    L_0x005e:
        r8 = "__a";
        r8 = r2.getColumnIndex(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r8 = r2.getString(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r9 = "__b";
        r9 = r2.getColumnIndex(r9);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r9 = r2.getString(r9);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r10 = "__c";
        r10 = r2.getColumnIndex(r10);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r10 = r2.getString(r10);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r11 = "__d";
        r11 = r2.getColumnIndex(r11);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r11 = r2.getString(r11);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r12 = "__ii";
        r12 = r2.getColumnIndex(r12);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r12 = r2.getString(r12);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r0 = r17;
        r13 = r0.h;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r13.add(r12);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r13 = "id";
        r5.put(r13, r12);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r12 = "start_time";
        r5.put(r12, r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r12 = "end_time";
        r5.put(r12, r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r12 = "duration";
        r14 = java.lang.Long.parseLong(r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r6 = java.lang.Long.parseLong(r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r6 = r14 - r6;
        r5.put(r12, r6);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r6 = android.text.TextUtils.isEmpty(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r6 != 0) goto L_0x00cb;
    L_0x00bb:
        r6 = "pages";
        r7 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r0 = r17;
        r8 = r0.b(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r7.<init>(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r5.put(r6, r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
    L_0x00cb:
        r6 = android.text.TextUtils.isEmpty(r9);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r6 != 0) goto L_0x00e1;
    L_0x00d1:
        r6 = "autopages";
        r7 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r0 = r17;
        r8 = r0.b(r9);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r7.<init>(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r5.put(r6, r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
    L_0x00e1:
        r6 = android.text.TextUtils.isEmpty(r10);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r6 != 0) goto L_0x00f7;
    L_0x00e7:
        r6 = "traffic";
        r7 = new org.json.JSONObject;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r0 = r17;
        r8 = r0.b(r10);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r7.<init>(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r5.put(r6, r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
    L_0x00f7:
        r6 = android.text.TextUtils.isEmpty(r11);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r6 != 0) goto L_0x010d;
    L_0x00fd:
        r6 = "locations";
        r7 = new org.json.JSONArray;	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r0 = r17;
        r8 = r0.b(r11);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r7.<init>(r8);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        r5.put(r6, r7);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
    L_0x010d:
        r6 = r5.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r6 <= 0) goto L_0x0024;
    L_0x0113:
        r4.put(r5);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        goto L_0x0024;
    L_0x0118:
        r4 = move-exception;
        r4 = c;	 Catch:{ all -> 0x0196 }
        com.umeng.analytics.pro.v.b(r4);	 Catch:{ all -> 0x0196 }
        if (r2 == 0) goto L_0x0123;
    L_0x0120:
        r2.close();
    L_0x0123:
        if (r3 == 0) goto L_0x0128;
    L_0x0125:
        r3.endTransaction();	 Catch:{ Throwable -> 0x0188 }
    L_0x0128:
        r2 = c;
        r2 = com.umeng.analytics.pro.u.a(r2);
        r2.b();
    L_0x0131:
        return;
    L_0x0132:
        r5 = r4.length();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r5 <= 0) goto L_0x013f;
    L_0x0138:
        r5 = "sessions";
        r0 = r18;
        r0.put(r5, r4);	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
    L_0x013f:
        r3.setTransactionSuccessful();	 Catch:{ SQLiteDatabaseCorruptException -> 0x0118, Throwable -> 0x0156 }
        if (r2 == 0) goto L_0x0147;
    L_0x0144:
        r2.close();
    L_0x0147:
        if (r3 == 0) goto L_0x014c;
    L_0x0149:
        r3.endTransaction();	 Catch:{ Throwable -> 0x0186 }
    L_0x014c:
        r2 = c;
        r2 = com.umeng.analytics.pro.u.a(r2);
        r2.b();
        goto L_0x0131;
    L_0x0156:
        r4 = move-exception;
        if (r2 == 0) goto L_0x015c;
    L_0x0159:
        r2.close();
    L_0x015c:
        if (r3 == 0) goto L_0x0161;
    L_0x015e:
        r3.endTransaction();	 Catch:{ Throwable -> 0x018a }
    L_0x0161:
        r2 = c;
        r2 = com.umeng.analytics.pro.u.a(r2);
        r2.b();
        goto L_0x0131;
    L_0x016b:
        r4 = move-exception;
        r16 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r16;
    L_0x0172:
        if (r3 == 0) goto L_0x0177;
    L_0x0174:
        r3.close();
    L_0x0177:
        if (r4 == 0) goto L_0x017c;
    L_0x0179:
        r4.endTransaction();	 Catch:{ Throwable -> 0x018c }
    L_0x017c:
        r3 = c;
        r3 = com.umeng.analytics.pro.u.a(r3);
        r3.b();
        throw r2;
    L_0x0186:
        r2 = move-exception;
        goto L_0x014c;
    L_0x0188:
        r2 = move-exception;
        goto L_0x0128;
    L_0x018a:
        r2 = move-exception;
        goto L_0x0161;
    L_0x018c:
        r3 = move-exception;
        goto L_0x017c;
    L_0x018e:
        r4 = move-exception;
        r16 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r16;
        goto L_0x0172;
    L_0x0196:
        r4 = move-exception;
        r16 = r4;
        r4 = r3;
        r3 = r2;
        r2 = r16;
        goto L_0x0172;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.w.c(org.json.JSONObject):void");
    }

    public void a(boolean z, boolean z2) {
        SQLiteDatabase a;
        Throwable th;
        Throwable th2;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            a = u.a(c).a();
            try {
                a.beginTransaction();
                a.execSQL("delete from __er");
                a.execSQL("delete from __et");
                if (!z2) {
                    if (this.h.size() > 0) {
                        for (int i = 0; i < this.h.size(); i++) {
                            a.execSQL("delete from __sd where __ii=\"" + ((String) this.h.get(i)) + "\"");
                        }
                    }
                    this.h.clear();
                } else if (z) {
                    a.execSQL("delete from __sd");
                }
                a.setTransactionSuccessful();
                if (a != null) {
                    try {
                        a.endTransaction();
                    } catch (Throwable th3) {
                    }
                }
                u.a(c).b();
            } catch (SQLiteDatabaseCorruptException e) {
                sQLiteDatabase = a;
                try {
                    v.b(c);
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Throwable th4) {
                        }
                    }
                    u.a(c).b();
                } catch (Throwable th5) {
                    th = th5;
                    a = sQLiteDatabase;
                    th2 = th;
                    if (a != null) {
                        try {
                            a.endTransaction();
                        } catch (Throwable th6) {
                        }
                    }
                    u.a(c).b();
                    throw th2;
                }
            } catch (Throwable th7) {
                th2 = th7;
                if (a != null) {
                    a.endTransaction();
                }
                u.a(c).b();
                throw th2;
            }
        } catch (SQLiteDatabaseCorruptException e2) {
            v.b(c);
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            u.a(c).b();
        } catch (Throwable th52) {
            th = th52;
            a = null;
            th2 = th;
            if (a != null) {
                a.endTransaction();
            }
            u.a(c).b();
            throw th2;
        }
    }

    private void b() {
        try {
            if (TextUtils.isEmpty(d)) {
                SharedPreferences a = az.a(c);
                Object string = a.getString(f, null);
                if (TextUtils.isEmpty(string)) {
                    string = bt.A(c);
                    if (!TextUtils.isEmpty(string)) {
                        a.edit().putString(f, string).commit();
                    }
                }
                if (!TextUtils.isEmpty(string)) {
                    String substring = string.substring(1, 9);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < substring.length(); i++) {
                        char charAt = substring.charAt(i);
                        if (!Character.isDigit(charAt)) {
                            stringBuilder.append(charAt);
                        } else if (Integer.parseInt(Character.toString(charAt)) == 0) {
                            stringBuilder.append(0);
                        } else {
                            stringBuilder.append(10 - Integer.parseInt(Character.toString(charAt)));
                        }
                    }
                    d = stringBuilder.toString();
                }
                if (!TextUtils.isEmpty(d)) {
                    d += new StringBuilder(d).reverse().toString();
                    String string2 = a.getString(g, null);
                    if (TextUtils.isEmpty(string2)) {
                        a.edit().putString(g, a(e)).commit();
                    } else if (!e.equals(b(string2))) {
                        a(true, false);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public String a(String str) {
        try {
            if (TextUtils.isEmpty(d)) {
                return str;
            }
            return Base64.encodeToString(br.a(str.getBytes(), d.getBytes()), 0);
        } catch (Exception e) {
            return null;
        }
    }

    public String b(String str) {
        try {
            if (TextUtils.isEmpty(d)) {
                return str;
            }
            return new String(br.b(Base64.decode(str.getBytes(), 0), d.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }
}
