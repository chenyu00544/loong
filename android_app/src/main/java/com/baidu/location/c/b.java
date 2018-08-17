package com.baidu.location.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.b.o;
import com.baidu.location.e.m;
import com.baidu.location.h.c;
import com.baidu.location.h.h;
import com.baidu.location.h.l;
import com.umeng.analytics.pro.x;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

public final class b implements f {
    private static final int ff = 10000;
    private static b fh = null;
    private static final String fj = (Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata/");
    private static final int fk = 6;
    private static final String fp = "bdcltb09";
    private static final String ft = (Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata/" + "/ls.db");
    private static final String fu = "wof";
    private static final int fw = 2000;
    private Handler fg = new Handler();
    private double fi = 0.0d;
    private double fl = 0.0d;
    private double fm = 0.0d;
    private String fn = null;
    private boolean fo = false;
    private volatile boolean fq = false;
    private double fr = 0.0d;
    private double fs = 0.0d;
    private boolean fv = false;

    class b_1 implements Runnable {
        final /* synthetic */ b a;

        b_1(b bVar) {
            this.a = bVar;
        }

        public void run() {
            if (com.baidu.location.f.isServing) {
                this.a.aY();
            }
        }
    }

    private class a extends AsyncTask {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        protected Boolean a(Boolean... boolArr) {
            SQLiteDatabase sQLiteDatabase = null;
            if (boolArr.length != 4) {
                return Boolean.valueOf(false);
            }
            try {
                sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(b.ft, null);
            } catch (Exception e) {
            }
            if (sQLiteDatabase == null) {
                return Boolean.valueOf(false);
            }
            int currentTimeMillis = (int) (System.currentTimeMillis() >> 28);
            try {
                sQLiteDatabase.beginTransaction();
                if (boolArr[0].booleanValue()) {
                    try {
                        sQLiteDatabase.execSQL("delete from wof where ac < " + (currentTimeMillis - 35));
                    } catch (Exception e2) {
                    }
                }
                if (boolArr[1].booleanValue()) {
                    try {
                        sQLiteDatabase.execSQL("delete from bdcltb09 where ac is NULL or ac < " + (currentTimeMillis - 130));
                    } catch (Exception e3) {
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                sQLiteDatabase.close();
            } catch (Exception e4) {
            }
            return Boolean.valueOf(true);
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Boolean[]) objArr);
        }
    }

    private class b extends AsyncTask {
        final /* synthetic */ b a;

        private b(b bVar) {
            this.a = bVar;
        }

        protected Boolean a(Object... objArr) {
            SQLiteDatabase sQLiteDatabase = null;
            if (objArr.length != 4) {
                this.a.fq = false;
                return Boolean.valueOf(false);
            }
            SQLiteDatabase openOrCreateDatabase;
            try {
                openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(b.ft, null);
            } catch (Exception e) {
                openOrCreateDatabase = sQLiteDatabase;
            }
            if (openOrCreateDatabase == null) {
                this.a.fq = false;
                return Boolean.valueOf(false);
            }
            try {
                openOrCreateDatabase.beginTransaction();
                this.a.if((String) objArr[0], (h) objArr[1], openOrCreateDatabase);
                this.a.if((com.baidu.location.h.f) objArr[2], (BDLocation) objArr[3], openOrCreateDatabase);
                openOrCreateDatabase.setTransactionSuccessful();
                openOrCreateDatabase.endTransaction();
                openOrCreateDatabase.close();
            } catch (Exception e2) {
            }
            this.a.fq = false;
            return Boolean.valueOf(true);
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }
    }

    private b() {
        try {
            File file = new File(fj);
            File file2 = new File(ft);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (file2.exists()) {
                SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file2, null);
                openOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS bdcltb09(id CHAR(40) PRIMARY KEY,time DOUBLE,tag DOUBLE, type DOUBLE , ac INT);");
                openOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS wof(id CHAR(15) PRIMARY KEY,mktime DOUBLE,time DOUBLE, ac INT, bc INT, cc INT);");
                openOrCreateDatabase.setVersion(1);
                openOrCreateDatabase.close();
            }
        } catch (Exception e) {
        }
    }

    private void aY() {
        SQLiteDatabase openOrCreateDatabase;
        SQLiteDatabase sQLiteDatabase = null;
        boolean z = true;
        try {
            openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(ft, null);
        } catch (Exception e) {
            openOrCreateDatabase = sQLiteDatabase;
        }
        if (openOrCreateDatabase != null) {
            try {
                long queryNumEntries = DatabaseUtils.queryNumEntries(openOrCreateDatabase, fu);
                long queryNumEntries2 = DatabaseUtils.queryNumEntries(openOrCreateDatabase, fp);
                boolean z2 = queryNumEntries > 10000;
                if (queryNumEntries2 <= 10000) {
                    z = false;
                }
                if (z2 || z) {
                    new a().execute(new Boolean[]{Boolean.valueOf(z2), Boolean.valueOf(z)});
                }
                openOrCreateDatabase.close();
            } catch (Exception e2) {
            }
        }
    }

    public static b aZ() {
        if (fh == null) {
            fh = new b();
        }
        return fh;
    }

    private void do(String str, List list) {
        SQLiteDatabase sQLiteDatabase;
        if (str == null || str.equals(this.fn)) {
            sQLiteDatabase = null;
        } else {
            sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(ft, null);
            if(str, sQLiteDatabase);
        }
        if (list != null) {
            if (sQLiteDatabase == null) {
                sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(ft, null);
            }
            if(list, sQLiteDatabase);
        }
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            sQLiteDatabase.close();
        }
    }

    private void if(com.baidu.location.h.f fVar, BDLocation bDLocation, SQLiteDatabase sQLiteDatabase) {
        if (bDLocation != null && bDLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
            if (("wf".equals(bDLocation.getNetworkLocationType()) || bDLocation.getRadius() < 300.0f) && fVar.kq != null) {
                int currentTimeMillis = (int) (System.currentTimeMillis() >> 28);
                System.currentTimeMillis();
                int i = 0;
                for (ScanResult scanResult : fVar.kq) {
                    if (scanResult.level != 0) {
                        int i2 = i + 1;
                        if (i2 <= 6) {
                            ContentValues contentValues = new ContentValues();
                            String J = Jni.J(scanResult.BSSID.replace(":", ""));
                            try {
                                int i3;
                                int i4;
                                double d;
                                Object obj;
                                double d2;
                                Cursor rawQuery = sQLiteDatabase.rawQuery("select * from wof where id = \"" + J + "\";", null);
                                if (rawQuery == null || !rawQuery.moveToFirst()) {
                                    i3 = 0;
                                    i4 = 0;
                                    d = 0.0d;
                                    obj = null;
                                    d2 = 0.0d;
                                } else {
                                    double d3 = rawQuery.getDouble(1) - 113.2349d;
                                    double d4 = rawQuery.getDouble(2) - 432.1238d;
                                    int i5 = rawQuery.getInt(4);
                                    i3 = rawQuery.getInt(5);
                                    i4 = i5;
                                    d = d3;
                                    double d5 = d4;
                                    obj = 1;
                                    d2 = d5;
                                }
                                if (rawQuery != null) {
                                    rawQuery.close();
                                }
                                if (obj == null) {
                                    contentValues.put("mktime", Double.valueOf(bDLocation.getLongitude() + 113.2349d));
                                    contentValues.put("time", Double.valueOf(bDLocation.getLatitude() + 432.1238d));
                                    contentValues.put("bc", Integer.valueOf(1));
                                    contentValues.put(x.au, Integer.valueOf(1));
                                    contentValues.put("ac", Integer.valueOf(currentTimeMillis));
                                    contentValues.put("id", J);
                                    sQLiteDatabase.insert(fu, null, contentValues);
                                } else if (i3 == 0) {
                                    i = i2;
                                } else {
                                    float[] fArr = new float[1];
                                    Location.distanceBetween(d2, d, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                                    if (fArr[0] > 1500.0f) {
                                        int i6 = i3 + 1;
                                        if (i6 <= 10 || i6 <= i4 * 3) {
                                            contentValues.put(x.au, Integer.valueOf(i6));
                                        } else {
                                            contentValues.put("mktime", Double.valueOf(bDLocation.getLongitude() + 113.2349d));
                                            contentValues.put("time", Double.valueOf(bDLocation.getLatitude() + 432.1238d));
                                            contentValues.put("bc", Integer.valueOf(1));
                                            contentValues.put(x.au, Integer.valueOf(1));
                                            contentValues.put("ac", Integer.valueOf(currentTimeMillis));
                                        }
                                    } else {
                                        d2 = ((d2 * ((double) i4)) + bDLocation.getLatitude()) / ((double) (i4 + 1));
                                        ContentValues contentValues2 = contentValues;
                                        contentValues2.put("mktime", Double.valueOf((((d * ((double) i4)) + bDLocation.getLongitude()) / ((double) (i4 + 1))) + 113.2349d));
                                        contentValues.put("time", Double.valueOf(d2 + 432.1238d));
                                        contentValues.put("bc", Integer.valueOf(i4 + 1));
                                        contentValues.put("ac", Integer.valueOf(currentTimeMillis));
                                    }
                                    try {
                                        if (sQLiteDatabase.update(fu, contentValues, "id = \"" + J + "\"", null) <= 0) {
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            } catch (Exception e2) {
                            }
                            i = i2;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void if(java.lang.String r8, android.database.sqlite.SQLiteDatabase r9) {
        /*
        r7 = this;
        r0 = 0;
        if (r8 == 0) goto L_0x000b;
    L_0x0003:
        r1 = r7.fn;
        r1 = r8.equals(r1);
        if (r1 == 0) goto L_0x000c;
    L_0x000b:
        return;
    L_0x000c:
        r1 = 0;
        r7.fo = r1;
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0067, all -> 0x0070 }
        r1.<init>();	 Catch:{ Exception -> 0x0067, all -> 0x0070 }
        r2 = "select * from bdcltb09 where id = \"";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0067, all -> 0x0070 }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0067, all -> 0x0070 }
        r2 = "\";";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0067, all -> 0x0070 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0067, all -> 0x0070 }
        r2 = 0;
        r0 = r9.rawQuery(r1, r2);	 Catch:{ Exception -> 0x0067, all -> 0x0070 }
        r7.fn = r8;	 Catch:{ Exception -> 0x0067, all -> 0x007c }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x0067, all -> 0x007c }
        if (r1 == 0) goto L_0x005f;
    L_0x0035:
        r1 = 1;
        r2 = r0.getDouble(r1);	 Catch:{ Exception -> 0x0067, all -> 0x007c }
        r4 = 4653148304163072062; // 0x40934dbaacd9e83e float:-6.193295E-12 double:1235.4323;
        r2 = r2 - r4;
        r7.fs = r2;	 Catch:{ Exception -> 0x0067, all -> 0x007c }
        r1 = 2;
        r2 = r0.getDouble(r1);	 Catch:{ Exception -> 0x0067, all -> 0x007c }
        r4 = 4661478502002851840; // 0x40b0e60000000000 float:0.0 double:4326.0;
        r2 = r2 - r4;
        r7.fi = r2;	 Catch:{ Exception -> 0x0067, all -> 0x007c }
        r1 = 3;
        r2 = r0.getDouble(r1);	 Catch:{ Exception -> 0x0067, all -> 0x007c }
        r4 = 4657424210545395263; // 0x40a27ea4b5dcc63f float:-1.6448975E-6 double:2367.3217;
        r2 = r2 - r4;
        r7.fr = r2;	 Catch:{ Exception -> 0x0067, all -> 0x007c }
        r1 = 1;
        r7.fo = r1;	 Catch:{ Exception -> 0x0067, all -> 0x007c }
    L_0x005f:
        if (r0 == 0) goto L_0x000b;
    L_0x0061:
        r0.close();	 Catch:{ Exception -> 0x0065 }
        goto L_0x000b;
    L_0x0065:
        r0 = move-exception;
        goto L_0x000b;
    L_0x0067:
        r1 = move-exception;
        if (r0 == 0) goto L_0x000b;
    L_0x006a:
        r0.close();	 Catch:{ Exception -> 0x006e }
        goto L_0x000b;
    L_0x006e:
        r0 = move-exception;
        goto L_0x000b;
    L_0x0070:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x0074:
        if (r1 == 0) goto L_0x0079;
    L_0x0076:
        r1.close();	 Catch:{ Exception -> 0x007a }
    L_0x0079:
        throw r0;
    L_0x007a:
        r1 = move-exception;
        goto L_0x0079;
    L_0x007c:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.b.if(java.lang.String, android.database.sqlite.SQLiteDatabase):void");
    }

    private void if(String str, h hVar, SQLiteDatabase sQLiteDatabase) {
        Object obj = null;
        double d = 0.0d;
        if (hVar.dt() && m.ba().bg()) {
            System.currentTimeMillis();
            int currentTimeMillis = (int) (System.currentTimeMillis() >> 28);
            String dB = hVar.dB();
            try {
                double parseDouble;
                float parseFloat;
                JSONObject jSONObject = new JSONObject(str);
                int parseInt = Integer.parseInt(jSONObject.getJSONObject("result").getString("error"));
                int i;
                if (parseInt == BDLocation.TypeNetWorkLocation) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                    if (jSONObject2.has("clf")) {
                        String string = jSONObject2.getString("clf");
                        if (string.equals("0")) {
                            JSONObject jSONObject3 = jSONObject2.getJSONObject("point");
                            d = Double.parseDouble(jSONObject3.getString("x"));
                            parseDouble = Double.parseDouble(jSONObject3.getString("y"));
                            parseFloat = Float.parseFloat(jSONObject2.getString("radius"));
                        } else {
                            String[] split = string.split("\\|");
                            d = Double.parseDouble(split[0]);
                            parseDouble = Double.parseDouble(split[1]);
                            parseFloat = Float.parseFloat(split[2]);
                        }
                    }
                    i = 1;
                    parseFloat = 0.0f;
                    parseDouble = 0.0d;
                } else {
                    if (parseInt == BDLocation.TypeServerError) {
                        sQLiteDatabase.delete(fp, "id = \"" + dB + "\"", null);
                        return;
                    }
                    i = 1;
                    parseFloat = 0.0f;
                    parseDouble = 0.0d;
                }
                if (obj == null) {
                    d += 1235.4323d;
                    parseDouble += 2367.3217d;
                    float f = 4326.0f + parseFloat;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("time", Double.valueOf(d));
                    contentValues.put("tag", Float.valueOf(f));
                    contentValues.put("type", Double.valueOf(parseDouble));
                    contentValues.put("ac", Integer.valueOf(currentTimeMillis));
                    try {
                        if (sQLiteDatabase.update(fp, contentValues, "id = \"" + dB + "\"", null) <= 0) {
                            contentValues.put("id", dB);
                            sQLiteDatabase.insert(fp, null, contentValues);
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
            }
        }
    }

    private void if(List list, SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Throwable th;
        System.currentTimeMillis();
        this.fv = false;
        if (list != null && sQLiteDatabase != null && list != null) {
            double d = 0.0d;
            double d2 = 0.0d;
            int i = 0;
            Object obj = null;
            double[] dArr = new double[8];
            Object obj2 = null;
            int i2 = 0;
            StringBuffer stringBuffer = new StringBuffer();
            int i3 = 0;
            for (ScanResult scanResult : list) {
                if (i3 > 10) {
                    break;
                }
                if (i3 > 0) {
                    stringBuffer.append(",");
                }
                i3++;
                stringBuffer.append("\"").append(Jni.J(scanResult.BSSID.replace(":", ""))).append("\"");
            }
            Cursor cursor = null;
            try {
                rawQuery = sQLiteDatabase.rawQuery("select * from wof where id in (" + stringBuffer.toString() + ");", null);
                try {
                    if (rawQuery.moveToFirst()) {
                        while (!rawQuery.isAfterLast()) {
                            double d3 = rawQuery.getDouble(1) - 113.2349d;
                            double d4 = rawQuery.getDouble(2) - 432.1238d;
                            int i4 = rawQuery.getInt(4);
                            int i5 = rawQuery.getInt(5);
                            if (i5 <= 8 || i5 <= i4) {
                                int i6;
                                Object obj3;
                                float[] fArr;
                                if (!this.fo) {
                                    if (obj == null) {
                                        int i7;
                                        if (obj2 != null) {
                                            int i8 = 0;
                                            while (i8 < i2) {
                                                Object obj4;
                                                double d5;
                                                double d6;
                                                fArr = new float[1];
                                                Location.distanceBetween(d4, d3, dArr[i8 + 1], dArr[i8], fArr);
                                                if (fArr[0] < 1000.0f) {
                                                    obj4 = 1;
                                                    d5 = d + dArr[i8];
                                                    d6 = dArr[i8 + 1] + d2;
                                                    i5 = i + 1;
                                                } else {
                                                    obj4 = obj;
                                                    i5 = i;
                                                    d6 = d2;
                                                    d5 = d;
                                                }
                                                i8 += 2;
                                                d2 = d6;
                                                d = d5;
                                                obj = obj4;
                                                i = i5;
                                            }
                                            if (obj == null) {
                                                if (i2 >= 8) {
                                                    break;
                                                }
                                                i4 = i2 + 1;
                                                dArr[i2] = d3;
                                                i7 = i4 + 1;
                                                dArr[i4] = d4;
                                                i6 = i7;
                                                obj3 = obj2;
                                            } else {
                                                d += d3;
                                                d2 += d4;
                                                i++;
                                                i6 = i2;
                                                obj3 = obj2;
                                            }
                                        } else {
                                            i4 = i2 + 1;
                                            dArr[i2] = d3;
                                            i7 = i4 + 1;
                                            dArr[i4] = d4;
                                            i3 = 1;
                                            i6 = i7;
                                        }
                                    } else {
                                        fArr = new float[1];
                                        Location.distanceBetween(d4, d3, d2 / ((double) i), d / ((double) i), fArr);
                                        if (fArr[0] > 1000.0f) {
                                            rawQuery.moveToNext();
                                        } else {
                                            i6 = i2;
                                            obj3 = obj2;
                                        }
                                    }
                                } else {
                                    fArr = new float[1];
                                    Location.distanceBetween(d4, d3, this.fr, this.fs, fArr);
                                    if (((double) fArr[0]) > this.fi + 2000.0d) {
                                        rawQuery.moveToNext();
                                    } else {
                                        obj = 1;
                                        d += d3;
                                        d2 += d4;
                                        i++;
                                        i6 = i2;
                                        obj3 = obj2;
                                    }
                                }
                                if (i > 4) {
                                    break;
                                }
                                rawQuery.moveToNext();
                                i2 = i6;
                                obj2 = obj3;
                            } else {
                                rawQuery.moveToNext();
                            }
                        }
                        if (i > 0) {
                            this.fv = true;
                            this.fm = d / ((double) i);
                            this.fl = d2 / ((double) i);
                        }
                    }
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                    cursor = rawQuery;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e4) {
                    }
                }
            } catch (Throwable th3) {
                rawQuery = null;
                th = th3;
                if (rawQuery != null) {
                    try {
                        rawQuery.close();
                    } catch (Exception e5) {
                    }
                }
                throw th;
            }
        }
    }

    private String try(boolean z) {
        double d;
        double d2;
        boolean z2;
        boolean z3;
        double d3 = 0.0d;
        if (this.fv) {
            d = this.fm;
            d2 = this.fl;
            d3 = 246.4d;
            z2 = true;
            z3 = true;
        } else if (this.fo) {
            d = this.fs;
            d2 = this.fr;
            d3 = this.fi;
            z2 = m.ba().bg();
            z3 = true;
        } else {
            z2 = false;
            z3 = false;
            d2 = 0.0d;
            d = 0.0d;
        }
        if (!z3) {
            return z ? "{\"result\":{\"time\":\"" + k.ad() + "\",\"error\":\"67\"}}" : "{\"result\":{\"time\":\"" + k.ad() + "\",\"error\":\"63\"}}";
        } else {
            if (z) {
                return String.format(Locale.CHINA, "{\"result\":{\"time\":\"" + k.ad() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}", new Object[]{Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Boolean.valueOf(true)});
            }
            return String.format(Locale.CHINA, "{\"result\":{\"time\":\"" + k.ad() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}", new Object[]{Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Boolean.valueOf(z2)});
        }
    }

    public void a0() {
        this.fg.postDelayed(new b_1(this), 3000);
    }

    public BDLocation byte(boolean z) {
        h cN = c.a().cN();
        String str = null;
        if (cN != null) {
            str = cN.dB();
        }
        com.baidu.location.h.f c7 = l.a().c7();
        BDLocation bDLocation = if(str, c7.kq, true);
        if (bDLocation != null && bDLocation.getLocType() == 66) {
            StringBuffer stringBuffer = new StringBuffer(1024);
            stringBuffer.append(String.format(Locale.CHINA, "&ofl=%f|%f|%f", new Object[]{Double.valueOf(bDLocation.getLatitude()), Double.valueOf(bDLocation.getLongitude()), Float.valueOf(bDLocation.getRadius())}));
            if (c7 != null && c7.dm() > 0) {
                stringBuffer.append("&wf=");
                stringBuffer.append(c7.d(15));
            }
            if (cN != null) {
                stringBuffer.append(cN.dy());
            }
            stringBuffer.append("&uptype=oldoff");
            stringBuffer.append(com.baidu.location.b.c.N().do(false));
            stringBuffer.append(com.baidu.location.e.c.bq().bt());
            o.for(o.fe, Jni.H(stringBuffer.toString()));
        }
        return bDLocation;
    }

    public BDLocation if(final String str, final List list, boolean z) {
        String str2 = "{\"result\":\"null\"}";
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        String str3 = (FutureTask) newSingleThreadExecutor.submit(new Callable(this) {
            final /* synthetic */ b a;

            public String a() {
                this.a.do(str, list);
                return this.a.try(true);
            }

            public /* synthetic */ Object call() throws Exception {
                return a();
            }
        });
        try {
            str3 = (String) str3.get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            str3.cancel(true);
            str3 = str2;
            return new BDLocation(str3);
        } catch (ExecutionException e2) {
            str3.cancel(true);
            str3 = str2;
            return new BDLocation(str3);
        } catch (TimeoutException e3) {
            if (z) {
                o.aX().j("old offlineLocation Timeout Exception!");
            }
            str3.cancel(true);
            str3 = str2;
            return new BDLocation(str3);
        } finally {
            newSingleThreadExecutor.shutdown();
        }
        return new BDLocation(str3);
    }

    public void if(String str, h hVar, com.baidu.location.h.f fVar, BDLocation bDLocation) {
        int i = (hVar.dt() && m.ba().bg()) ? 0 : true;
        int i2 = (bDLocation == null || bDLocation.getLocType() != BDLocation.TypeNetWorkLocation || (!"wf".equals(bDLocation.getNetworkLocationType()) && bDLocation.getRadius() >= 300.0f)) ? true : 0;
        if (fVar.kq == null) {
            i2 = true;
        }
        if ((i == 0 || r0 == 0) && !this.fq) {
            this.fq = true;
            new b().execute(new Object[]{str, hVar, fVar, bDLocation});
        }
    }
}
