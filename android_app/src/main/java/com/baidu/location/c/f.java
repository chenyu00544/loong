package com.baidu.location.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.Poi;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.map.WeightedLatLng;
import com.tencent.stat.DeviceInfo;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class f implements com.baidu.location.b.b {
    static final int a0 = 30;
    static final int a1 = 6;
    private static final double a2 = 90.0d;
    private static final double a3 = 180.0d;
    private static final double a5 = -90.0d;
    private static final double a6 = -180.0d;
    static final String aV = "rgc";
    private static final double[] aY = new double[]{45.0d, 135.0d, 225.0d, 315.0d};
    private static final String aZ = "0123456789bcdefghjkmnpqrstuvwxyz";
    private final int a4;
    private final SQLiteDatabase a7;
    private int aU = -1;
    private final d aW;
    private int aX = -1;

    class f_1 implements Comparator {
        final /* synthetic */ f a;

        f_1(f fVar) {
            this.a = fVar;
        }

        public int a(Entry entry, Entry entry2) {
            return ((Integer) entry2.getValue()).compareTo((Integer) entry.getValue());
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((Entry) obj, (Entry) obj2);
        }
    }

    private static final class a {
        private double a;
        private double if;

        private a(double d, double d2) {
            this.if = d;
            this.a = d2;
        }
    }

    private enum b {
        AREA("RGCAREA", "area", "addrv", 0, 1000) {
            List a(JSONObject jSONObject, String str, int i) {
                Iterator keys = jSONObject.keys();
                StringBuffer stringBuffer = new StringBuffer();
                StringBuffer stringBuffer2 = new StringBuffer();
                List arrayList = new ArrayList();
                int i2 = 0;
                while (keys.hasNext()) {
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    String str5 = null;
                    String str6 = null;
                    String str7 = null;
                    String str8 = (String) keys.next();
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(str8);
                        if (jSONObject2.has("cy")) {
                            str2 = jSONObject2.getString("cy");
                        }
                        if (jSONObject2.has("cyc")) {
                            str3 = jSONObject2.getString("cyc");
                        }
                        if (jSONObject2.has("prov")) {
                            str4 = jSONObject2.getString("prov");
                        }
                        if (jSONObject2.has("ctc")) {
                            str5 = jSONObject2.getString("ctc");
                        }
                        if (jSONObject2.has(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_TEXT)) {
                            str6 = jSONObject2.getString(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_TEXT);
                        }
                        if (jSONObject2.has("dist")) {
                            str7 = jSONObject2.getString("dist");
                        }
                        if (stringBuffer.length() > 0) {
                            stringBuffer.append(",");
                        }
                        stringBuffer.append("(\"").append(str8).append("\",\"").append(str2).append("\",\"").append(str3).append("\",\"").append(str4).append("\",\"").append(str6).append("\",\"").append(str5).append("\",\"").append(str7).append("\",").append(System.currentTimeMillis() / 1000).append(",\"\")");
                        b.if(stringBuffer2, str8, str, 0);
                    } catch (JSONException e) {
                    }
                    if (i2 % 50 == 49 && stringBuffer.length() > 0) {
                        arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCAREA", stringBuffer}));
                        arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCUPDATE", stringBuffer2}));
                        stringBuffer.setLength(0);
                    }
                    i2++;
                }
                if (stringBuffer.length() > 0) {
                    arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCAREA", stringBuffer}));
                    arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCUPDATE", stringBuffer2}));
                    stringBuffer.setLength(0);
                }
                arrayList.add(String.format(Locale.US, "DELETE FROM RGCAREA WHERE gridkey NOT IN (SELECT gridkey FROM RGCAREA LIMIT %d);", new Object[]{Integer.valueOf(i)}));
                return arrayList;
            }
        },
        ROAD("RGCROAD", "road", "addrv", 1000, 10000) {
            List a(JSONObject jSONObject, String str, int i) {
                Iterator keys = jSONObject.keys();
                List arrayList = new ArrayList();
                StringBuffer stringBuffer = new StringBuffer();
                while (keys.hasNext()) {
                    JSONArray jSONArray;
                    JSONArray jSONArray2 = null;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    String str2 = (String) keys.next();
                    b.if(stringBuffer, str2, str, 0);
                    try {
                        jSONArray = jSONObject.getJSONArray(str2);
                    } catch (JSONException e) {
                        jSONArray = jSONArray2;
                    }
                    if (jSONArray != null) {
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            Object obj = null;
                            Object obj2 = null;
                            Object obj3 = null;
                            Object obj4 = null;
                            String str3 = null;
                            try {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                if (jSONObject2.has(SocializeProtocolConstants.PROTOCOL_KEY_ST)) {
                                    str3 = jSONObject2.getString(SocializeProtocolConstants.PROTOCOL_KEY_ST);
                                }
                                if (jSONObject2.has("x1")) {
                                    obj = Double.valueOf(jSONObject2.getDouble("x1"));
                                }
                                if (jSONObject2.has("y1")) {
                                    obj2 = Double.valueOf(jSONObject2.getDouble("y1"));
                                }
                                if (jSONObject2.has("x2")) {
                                    obj3 = Double.valueOf(jSONObject2.getDouble("x2"));
                                }
                                if (jSONObject2.has("y2")) {
                                    obj4 = Double.valueOf(jSONObject2.getDouble("y2"));
                                }
                                if (!(str3 == null || obj == null || obj2 == null || obj3 == null || obj4 == null)) {
                                    if (stringBuffer2.length() > 0) {
                                        stringBuffer2.append(",");
                                    }
                                    stringBuffer2.append("(NULL,\"").append(str2).append("\",\"").append(str3).append("\",").append(obj).append(",").append(obj2).append(",").append(obj3).append(",").append(obj4).append(")");
                                }
                            } catch (JSONException e2) {
                            }
                            if (i2 % 50 == 49 && stringBuffer2.length() > 0) {
                                arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCROAD", stringBuffer2.toString()}));
                                stringBuffer2.setLength(0);
                            }
                        }
                        if (stringBuffer2.length() > 0) {
                            arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCROAD", stringBuffer2.toString()}));
                        }
                    }
                }
                if (stringBuffer.length() > 0) {
                    arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCUPDATE", stringBuffer}));
                }
                arrayList.add(String.format(Locale.US, "DELETE FROM RGCROAD WHERE _id NOT IN (SELECT _id FROM RGCROAD LIMIT %d);", new Object[]{Integer.valueOf(i)}));
                return arrayList;
            }
        },
        SITE("RGCSITE", "site", "addrv", 100, 50000) {
            List a(JSONObject jSONObject, String str, int i) {
                Iterator keys = jSONObject.keys();
                List arrayList = new ArrayList();
                StringBuffer stringBuffer = new StringBuffer();
                while (keys.hasNext()) {
                    JSONArray jSONArray;
                    JSONArray jSONArray2 = null;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    String str2 = (String) keys.next();
                    b.if(stringBuffer, str2, str, 0);
                    try {
                        jSONArray = jSONObject.getJSONArray(str2);
                    } catch (JSONException e) {
                        jSONArray = jSONArray2;
                    }
                    if (jSONArray != null) {
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            Object obj = null;
                            Object obj2 = null;
                            String str3 = null;
                            String str4 = null;
                            try {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                if (jSONObject2.has(SocializeProtocolConstants.PROTOCOL_KEY_ST)) {
                                    str3 = jSONObject2.getString(SocializeProtocolConstants.PROTOCOL_KEY_ST);
                                }
                                if (jSONObject2.has("stn")) {
                                    str4 = jSONObject2.getString("stn");
                                }
                                if (jSONObject2.has("x")) {
                                    obj = Double.valueOf(jSONObject2.getDouble("x"));
                                }
                                if (jSONObject2.has("y")) {
                                    obj2 = Double.valueOf(jSONObject2.getDouble("y"));
                                }
                                if (stringBuffer2.length() > 0) {
                                    stringBuffer2.append(",");
                                }
                                stringBuffer2.append("(NULL,\"").append(str2).append("\",\"").append(str3).append("\",\"").append(str4).append("\",").append(obj).append(",").append(obj2).append(")");
                            } catch (JSONException e2) {
                            }
                            if (i2 % 50 == 49 && stringBuffer2.length() > 0) {
                                arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCSITE", stringBuffer2.toString()}));
                                stringBuffer2.setLength(0);
                            }
                        }
                        if (stringBuffer2.length() > 0) {
                            arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCSITE", stringBuffer2.toString()}));
                        }
                    }
                }
                if (stringBuffer.length() > 0) {
                    arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCUPDATE", stringBuffer}));
                }
                arrayList.add(String.format(Locale.US, "DELETE FROM RGCSITE WHERE _id NOT IN (SELECT _id FROM RGCSITE LIMIT %d);", new Object[]{Integer.valueOf(i)}));
                return arrayList;
            }
        },
        POI("RGCPOI", "poi", "poiv", 1000, 5000) {
            List a(JSONObject jSONObject, String str, int i) {
                Iterator keys = jSONObject.keys();
                List arrayList = new ArrayList();
                StringBuffer stringBuffer = new StringBuffer();
                while (keys.hasNext()) {
                    JSONArray jSONArray;
                    JSONArray jSONArray2 = null;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    String str2 = (String) keys.next();
                    b.if(stringBuffer, str2, str, 1);
                    try {
                        jSONArray = jSONObject.getJSONArray(str2);
                    } catch (JSONException e) {
                        jSONArray = jSONArray2;
                    }
                    if (jSONArray != null) {
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            Object obj = null;
                            Object obj2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            Object obj3 = null;
                            try {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                if (jSONObject2.has("pid")) {
                                    str3 = jSONObject2.getString("pid");
                                }
                                if (jSONObject2.has("ne")) {
                                    str4 = jSONObject2.getString("ne");
                                }
                                if (jSONObject2.has(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE)) {
                                    str5 = jSONObject2.getString(SocializeProtocolConstants.PROTOCOL_KEY_REQUEST_TYPE);
                                }
                                if (jSONObject2.has("rk")) {
                                    obj3 = Integer.valueOf(jSONObject2.getInt("rk"));
                                }
                                if (jSONObject2.has("x")) {
                                    obj = Double.valueOf(jSONObject2.getDouble("x"));
                                }
                                if (jSONObject2.has("y")) {
                                    obj2 = Double.valueOf(jSONObject2.getDouble("y"));
                                }
                                if (stringBuffer2.length() > 0) {
                                    stringBuffer2.append(",");
                                }
                                stringBuffer2.append("(\"").append(str3).append("\",\"").append(str2).append("\",\"").append(str4).append("\",\"").append(str5).append("\",").append(obj).append(",").append(obj2).append(",").append(obj3).append(")");
                            } catch (JSONException e2) {
                            }
                            if (i2 % 50 == 49) {
                                arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCPOI", stringBuffer2.toString()}));
                                stringBuffer2.setLength(0);
                            }
                        }
                        if (stringBuffer2.length() > 0) {
                            arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCPOI", stringBuffer2.toString()}));
                        }
                    }
                }
                if (stringBuffer.length() > 0) {
                    arrayList.add(String.format(Locale.US, b.new, new Object[]{"RGCUPDATE", stringBuffer}));
                }
                arrayList.add(String.format(Locale.US, "DELETE FROM RGCPOI WHERE pid NOT IN (SELECT pid FROM RGCPOI LIMIT %d);", new Object[]{Integer.valueOf(i)}));
                return arrayList;
            }
        };
        
        private static final int byte = 50;
        private static final String new = "INSERT OR REPLACE INTO %s VALUES %s";
        private final int case;
        private final String else;
        private final String if;
        private final int int;
        private final String try;

        private b(String str, String str2, String str3, int i, int i2) {
            this.if = str;
            this.try = str2;
            this.else = str3;
            this.case = i;
            this.int = i2;
        }

        private String a(int i, double d, double d2) {
            HashSet hashSet = new HashSet();
            hashSet.add(f.if(i, d, d2));
            double d3 = ((double) this.case) * 1.414d;
            if (this.case > 0) {
                for (double d4 : f.aY) {
                    double[] dArr = f.for(d2, d, d3, d4);
                    hashSet.add(f.if(i, dArr[1], dArr[0]));
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = hashSet.iterator();
            Object obj = 1;
            while (it.hasNext()) {
                String str = (String) it.next();
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append("\"").append(str).append("\"");
            }
            return String.format("SELECT * FROM %s WHERE gridkey IN (%s);", new Object[]{this.if, stringBuffer.toString()});
        }

        private String a(JSONObject jSONObject) {
            Iterator keys = jSONObject.keys();
            StringBuffer stringBuffer = new StringBuffer();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (stringBuffer.length() != 0) {
                    stringBuffer.append(",");
                }
                stringBuffer.append("\"").append(str).append("\"");
            }
            return String.format(Locale.US, "DELETE FROM %s WHERE gridkey IN (%s)", new Object[]{this.if, stringBuffer});
        }

        private static void if(StringBuffer stringBuffer, String str, String str2, int i) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append("(\"").append(str).append("\",\"").append(str2).append("\",").append(i).append(",").append(System.currentTimeMillis() / 86400000).append(")");
        }

        abstract List a(JSONObject jSONObject, String str, int i);
    }

    f(d dVar, SQLiteDatabase sQLiteDatabase, int i) {
        this.aW = dVar;
        this.a7 = sQLiteDatabase;
        this.a4 = i;
        if (this.a7 != null && this.a7.isOpen()) {
            this.a7.execSQL("CREATE TABLE IF NOT EXISTS RGCAREA(gridkey VARCHAR(10) PRIMARY KEY, country VARCHAR(100),countrycode VARCHAR(100), province VARCHAR(100), city VARCHAR(100), citycode VARCHAR(100), district VARCHAR(100), timestamp INTEGER, version VARCHAR(50))");
            this.a7.execSQL("CREATE TABLE IF NOT EXISTS RGCROAD(_id INTEGER PRIMARY KEY AUTOINCREMENT, gridkey VARCHAR(10), street VARCHAR(100), x1 DOUBLE, y1 DOUBLE, x2 DOUBLE, y2 DOUBLE)");
            this.a7.execSQL("CREATE TABLE IF NOT EXISTS RGCSITE(_id INTEGER PRIMARY KEY AUTOINCREMENT, gridkey VARCHAR(10), street VARCHAR(100), streetnumber VARCHAR(100), x DOUBLE, y DOUBLE)");
            this.a7.execSQL("CREATE TABLE IF NOT EXISTS RGCPOI(pid VARCHAR(50) PRIMARY KEY , gridkey VARCHAR(10), name VARCHAR(100), type VARCHAR(50), x DOUBLE, y DOUBLE, rank INTEGER)");
            this.a7.execSQL("CREATE TABLE IF NOT EXISTS RGCUPDATE(gridkey VARCHAR(10), version VARCHAR(50), type INTEGER, timestamp INTEGER, PRIMARY KEY(gridkey, type))");
        }
    }

    private static double[] for(double d, double d2, double d3, double d4) {
        double[] dArr = new double[2];
        double toRadians = Math.toRadians(d);
        double toRadians2 = Math.toRadians(d2);
        double toRadians3 = Math.toRadians(d4);
        double asin = Math.asin((Math.sin(toRadians) * Math.cos(d3 / 6378137.0d)) + ((Math.cos(toRadians) * Math.sin(d3 / 6378137.0d)) * Math.cos(toRadians3)));
        toRadians = Math.atan2((Math.sin(toRadians3) * Math.sin(d3 / 6378137.0d)) * Math.cos(toRadians), Math.cos(d3 / 6378137.0d) - (Math.sin(toRadians) * Math.sin(asin))) + toRadians2;
        dArr[0] = Math.toDegrees(asin);
        dArr[1] = Math.toDegrees(toRadians);
        return dArr;
    }

    private double if(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d5 - d3) * (d - d3)) + ((d6 - d4) * (d2 - d4));
        if (d7 <= 0.0d) {
            return Math.sqrt(((d - d3) * (d - d3)) + ((d2 - d4) * (d2 - d4)));
        }
        double d8 = ((d5 - d3) * (d5 - d3)) + ((d6 - d4) * (d6 - d4));
        if (d7 >= d8) {
            return Math.sqrt(((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6)));
        }
        d7 /= d8;
        d8 = ((d5 - d3) * d7) + d3;
        d7 = (d7 * (d6 - d4)) + d4;
        return Math.sqrt(((d7 - d2) * (d7 - d2)) + ((d - d8) * (d - d8)));
    }

    private static int if(int i, int i2) {
        double d;
        int i3;
        if (100 > i2) {
            d = -0.1d;
            i3 = 60000;
        } else if (d_ResultType.SHORT_URL > i2) {
            d = -0.75d;
            i3 = 55500;
        } else {
            d = -0.5d;
            i3 = 0;
        }
        return ((int) (((double) i3) + (d * ((double) i2)))) + i;
    }

    private static String if(int i, double d, double d2) {
        int i2 = i * 5;
        char[] cArr = new char[(i + 1)];
        a aVar = new a(a2, a5);
        a aVar2 = new a(a3, a6);
        int i3 = 1;
        Object obj = 1;
        int i4 = 0;
        while (i3 <= i2) {
            a aVar3;
            double d3;
            int i5;
            int i6;
            if (obj != null) {
                aVar3 = aVar2;
                d3 = d;
            } else {
                aVar3 = aVar;
                d3 = d2;
            }
            double d4 = (aVar3.a + aVar3.if) / 2.0d;
            i4 <<= 1;
            if (((int) (d3 * 1000000.0d)) > ((int) (1000000.0d * d4))) {
                aVar3.a = d4;
                i5 = i4 | 1;
            } else {
                aVar3.if = d4;
                i5 = i4;
            }
            if (i3 % 5 == 0) {
                cArr[(i3 / 5) - 1] = aZ.charAt(i5);
                i6 = 0;
            } else {
                i6 = i5;
            }
            i3++;
            obj = obj == null ? 1 : null;
            i4 = i6;
        }
        cArr[i] = '\u0000';
        StringBuffer stringBuffer = new StringBuffer();
        for (i5 = 0; i5 < i; i5++) {
            stringBuffer.append(cArr[i5]);
        }
        return stringBuffer.toString();
    }

    private double int(double d, double d2, double d3, double d4) {
        double d5 = d4 - d2;
        double d6 = d3 - d;
        double toRadians = Math.toRadians(d);
        Math.toRadians(d2);
        double toRadians2 = Math.toRadians(d3);
        Math.toRadians(d4);
        d5 = Math.toRadians(d5);
        d6 = Math.toRadians(d6);
        d5 = (Math.sin(d5 / 2.0d) * ((Math.cos(toRadians) * Math.cos(toRadians2)) * Math.sin(d5 / 2.0d))) + (Math.sin(d6 / 2.0d) * Math.sin(d6 / 2.0d));
        return (Math.atan2(Math.sqrt(d5), Math.sqrt(WeightedLatLng.DEFAULT_INTENSITY - d5)) * 2.0d) * 6378137.0d;
    }

    boolean F() {
        Cursor rawQuery;
        Throwable th;
        Cursor cursor = null;
        if (this.aW.new().B() && this.aU == -1 && this.aX == -1 && this.a7 != null && this.a7.isOpen()) {
            try {
                rawQuery = this.a7.rawQuery("SELECT COUNT(*) FROM RGCSITE;", null);
                try {
                    rawQuery.moveToFirst();
                    this.aU = rawQuery.getInt(0);
                    cursor = this.a7.rawQuery("SELECT COUNT(*) FROM RGCAREA;", null);
                    cursor.moveToFirst();
                    this.aX = cursor.getInt(0);
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Exception e) {
                        }
                    }
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Exception e2) {
                        }
                    }
                } catch (Exception e3) {
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Exception e4) {
                        }
                    }
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Exception e5) {
                        }
                    }
                    return this.aU == 0 ? false : false;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Exception e7) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e8) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (this.aU == 0) {
                }
            } catch (Throwable th3) {
                th = th3;
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        if (this.aU == 0 && this.aX == 0) {
            return true;
        }
    }

    JSONObject H() {
        Cursor cursor = null;
        Cursor cursor2 = null;
        JSONObject jSONObject = new JSONObject();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        int currentTimeMillis = (int) (System.currentTimeMillis() / 86400000);
        String str = "SELECT * FROM RGCUPDATE WHERE type=%d AND %d > timestamp+%d ORDER BY gridkey";
        String str2 = "UPDATE RGCUPDATE SET timestamp=timestamp+1 WHERE type = %d AND gridkey IN (%s)";
        try {
            if (this.a7 != null && this.a7.isOpen()) {
                HashSet hashSet;
                String string;
                String[] strArr;
                JSONObject jSONObject2;
                JSONArray jSONArray = new JSONArray();
                JSONArray jSONArray2 = new JSONArray();
                JSONArray jSONArray3 = new JSONArray();
                JSONArray jSONArray4 = new JSONArray();
                cursor2 = this.a7.rawQuery(String.format(str, new Object[]{Integer.valueOf(0), Integer.valueOf(currentTimeMillis), Integer.valueOf(this.aW.new().u())}), null);
                cursor = this.a7.rawQuery(String.format(str, new Object[]{Integer.valueOf(1), Integer.valueOf(currentTimeMillis), Integer.valueOf(this.aW.new().D())}), null);
                if (cursor2.moveToFirst()) {
                    hashSet = new HashSet();
                    while (!cursor2.isAfterLast()) {
                        str = cursor2.getString(0);
                        string = cursor2.getString(1);
                        jSONArray3.put(str);
                        hashSet.add(string);
                        if (stringBuffer2.length() > 0) {
                            stringBuffer2.append(",");
                        }
                        stringBuffer2.append("\"").append(str).append("\"");
                        cursor2.moveToNext();
                    }
                    strArr = new String[hashSet.size()];
                    hashSet.toArray(strArr);
                    for (Object put : strArr) {
                        jSONArray4.put(put);
                    }
                }
                if (cursor.moveToFirst()) {
                    hashSet = new HashSet();
                    while (!cursor.isAfterLast()) {
                        str = cursor.getString(0);
                        string = cursor.getString(1);
                        jSONArray.put(str);
                        hashSet.add(string);
                        if (stringBuffer.length() > 0) {
                            stringBuffer.append(",");
                        }
                        stringBuffer.append("\"").append(str).append("\"");
                        cursor.moveToNext();
                    }
                    strArr = new String[hashSet.size()];
                    hashSet.toArray(strArr);
                    for (Object put2 : strArr) {
                        jSONArray2.put(put2);
                    }
                }
                if (jSONArray3.length() != 0) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("gk", jSONArray3);
                    jSONObject2.put(DeviceInfo.TAG_VERSION, jSONArray4);
                    jSONObject.put("addr", jSONObject2);
                }
                if (jSONArray.length() != 0) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("gk", jSONArray);
                    jSONObject2.put(DeviceInfo.TAG_VERSION, jSONArray2);
                    jSONObject.put("poi", jSONObject2);
                }
            }
            if (stringBuffer2.length() > 0) {
                this.a7.execSQL(String.format(Locale.US, str2, new Object[]{Integer.valueOf(0), stringBuffer2.toString()}));
            }
            if (stringBuffer.length() > 0) {
                this.a7.execSQL(String.format(Locale.US, str2, new Object[]{Integer.valueOf(1), stringBuffer.toString()}));
            }
            if (cursor2 != null) {
                try {
                    cursor2.close();
                } catch (Exception e) {
                }
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e2) {
                }
            }
        } catch (Exception e3) {
            if (cursor2 != null) {
                try {
                    cursor2.close();
                } catch (Exception e4) {
                }
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e5) {
                }
            }
        } catch (Throwable th) {
            if (cursor2 != null) {
                try {
                    cursor2.close();
                } catch (Exception e6) {
                }
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e7) {
                }
            }
        }
        return (jSONObject.has("poi") || jSONObject.has("addr")) ? jSONObject : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    com.baidu.location.Address do(double r32, double r34) {
        /*
        r31 = this;
        r24 = 0;
        r23 = 0;
        r22 = 0;
        r21 = 0;
        r20 = 0;
        r25 = 0;
        r12 = 0;
        r11 = 0;
        r10 = 0;
        r4 = com.baidu.location.c.f.b.SITE;	 Catch:{ Exception -> 0x011a, all -> 0x012e }
        r0 = r31;
        r5 = r0.a4;	 Catch:{ Exception -> 0x011a, all -> 0x012e }
        r6 = r32;
        r8 = r34;
        r4 = r4.a(r5, r6, r8);	 Catch:{ Exception -> 0x011a, all -> 0x012e }
        r0 = r31;
        r5 = r0.a7;	 Catch:{ Exception -> 0x011a, all -> 0x012e }
        r6 = 0;
        r4 = r5.rawQuery(r4, r6);	 Catch:{ Exception -> 0x011a, all -> 0x012e }
        r5 = r4.moveToFirst();	 Catch:{ Exception -> 0x02db, all -> 0x02d6 }
        if (r5 == 0) goto L_0x0316;
    L_0x002c:
        r6 = 9218868437227405311; // 0x7fefffffffffffff float:NaN double:1.7976931348623157E308;
        r16 = r6;
        r14 = r11;
        r15 = r12;
    L_0x0035:
        r5 = r4.isAfterLast();	 Catch:{ Exception -> 0x02e3, all -> 0x02d6 }
        if (r5 != 0) goto L_0x0074;
    L_0x003b:
        r5 = 2;
        r19 = r4.getString(r5);	 Catch:{ Exception -> 0x02e3, all -> 0x02d6 }
        r5 = 3;
        r18 = r4.getString(r5);	 Catch:{ Exception -> 0x02e3, all -> 0x02d6 }
        r5 = 5;
        r10 = r4.getDouble(r5);	 Catch:{ Exception -> 0x02e3, all -> 0x02d6 }
        r5 = 4;
        r12 = r4.getDouble(r5);	 Catch:{ Exception -> 0x02e3, all -> 0x02d6 }
        r5 = r31;
        r6 = r34;
        r8 = r32;
        r8 = r5.int(r6, r8, r10, r12);	 Catch:{ Exception -> 0x02e3, all -> 0x02d6 }
        r5 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1));
        if (r5 >= 0) goto L_0x0310;
    L_0x005d:
        r5 = com.baidu.location.c.f.b.SITE;	 Catch:{ Exception -> 0x02e3, all -> 0x02d6 }
        r5 = r5.case;	 Catch:{ Exception -> 0x02e3, all -> 0x02d6 }
        r6 = (double) r5;
        r5 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r5 > 0) goto L_0x0310;
    L_0x0068:
        r5 = r18;
        r6 = r19;
    L_0x006c:
        r4.moveToNext();	 Catch:{ Exception -> 0x02e0, all -> 0x02d6 }
        r16 = r8;
        r14 = r5;
        r15 = r6;
        goto L_0x0035;
    L_0x0074:
        r5 = r14;
        r6 = r15;
    L_0x0076:
        if (r4 == 0) goto L_0x030b;
    L_0x0078:
        r4.close();	 Catch:{ Exception -> 0x0114 }
        r18 = r5;
        r10 = r6;
    L_0x007e:
        if (r18 != 0) goto L_0x013c;
    L_0x0080:
        r11 = 0;
        r4 = com.baidu.location.c.f.b.ROAD;	 Catch:{ Exception -> 0x0255, all -> 0x0264 }
        r0 = r31;
        r5 = r0.a4;	 Catch:{ Exception -> 0x0255, all -> 0x0264 }
        r6 = r32;
        r8 = r34;
        r4 = r4.a(r5, r6, r8);	 Catch:{ Exception -> 0x0255, all -> 0x0264 }
        r0 = r31;
        r5 = r0.a7;	 Catch:{ Exception -> 0x0255, all -> 0x0264 }
        r6 = 0;
        r19 = r5.rawQuery(r4, r6);	 Catch:{ Exception -> 0x0255, all -> 0x0264 }
        r4 = r19.moveToFirst();	 Catch:{ Exception -> 0x02cd, all -> 0x02cb }
        if (r4 == 0) goto L_0x0135;
    L_0x009e:
        r26 = 9218868437227405311; // 0x7fefffffffffffff float:NaN double:1.7976931348623157E308;
        r4 = "wgs842mc";
        r0 = r32;
        r2 = r34;
        r29 = com.baidu.location.Jni.if(r0, r2, r4);	 Catch:{ Exception -> 0x02cd, all -> 0x02cb }
        r4 = r10;
    L_0x00ae:
        r5 = r19.isAfterLast();	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        if (r5 != 0) goto L_0x0136;
    L_0x00b4:
        r5 = 2;
        r0 = r19;
        r28 = r0.getString(r5);	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r5 = 3;
        r0 = r19;
        r6 = r0.getDouble(r5);	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r5 = 4;
        r0 = r19;
        r8 = r0.getDouble(r5);	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r5 = "wgs842mc";
        r5 = com.baidu.location.Jni.if(r6, r8, r5);	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r6 = 5;
        r0 = r19;
        r6 = r0.getDouble(r6);	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r8 = 6;
        r0 = r19;
        r8 = r0.getDouble(r8);	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r10 = "wgs842mc";
        r16 = com.baidu.location.Jni.if(r6, r8, r10);	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r6 = 0;
        r6 = r29[r6];	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r8 = 1;
        r8 = r29[r8];	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r10 = 0;
        r10 = r5[r10];	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r12 = 1;
        r12 = r5[r12];	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r5 = 0;
        r14 = r16[r5];	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r5 = 1;
        r16 = r16[r5];	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r5 = r31;
        r6 = r5.if(r6, r8, r10, r12, r14, r16);	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r5 = (r6 > r26 ? 1 : (r6 == r26 ? 0 : -1));
        if (r5 >= 0) goto L_0x0306;
    L_0x00ff:
        r5 = com.baidu.location.c.f.b.ROAD;	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r5 = r5.case;	 Catch:{ Exception -> 0x02d2, all -> 0x02cb }
        r8 = (double) r5;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 > 0) goto L_0x0306;
    L_0x010a:
        r4 = r6;
        r10 = r28;
    L_0x010d:
        r19.moveToNext();	 Catch:{ Exception -> 0x02cd, all -> 0x02cb }
        r26 = r4;
        r4 = r10;
        goto L_0x00ae;
    L_0x0114:
        r4 = move-exception;
        r18 = r5;
        r10 = r6;
        goto L_0x007e;
    L_0x011a:
        r4 = move-exception;
        r4 = r10;
        r5 = r11;
        r6 = r12;
    L_0x011e:
        if (r4 == 0) goto L_0x030b;
    L_0x0120:
        r4.close();	 Catch:{ Exception -> 0x0128 }
        r18 = r5;
        r10 = r6;
        goto L_0x007e;
    L_0x0128:
        r4 = move-exception;
        r18 = r5;
        r10 = r6;
        goto L_0x007e;
    L_0x012e:
        r4 = move-exception;
    L_0x012f:
        if (r10 == 0) goto L_0x0134;
    L_0x0131:
        r10.close();	 Catch:{ Exception -> 0x029c }
    L_0x0134:
        throw r4;
    L_0x0135:
        r4 = r10;
    L_0x0136:
        if (r19 == 0) goto L_0x0303;
    L_0x0138:
        r19.close();	 Catch:{ Exception -> 0x0251 }
        r10 = r4;
    L_0x013c:
        r4 = com.baidu.location.c.f.b.AREA;
        r0 = r31;
        r5 = r0.a4;
        r6 = r32;
        r8 = r34;
        r5 = r4.a(r5, r6, r8);
        r4 = 0;
        r0 = r31;
        r6 = r0.a7;	 Catch:{ Exception -> 0x026d, all -> 0x0290 }
        r7 = 0;
        r4 = r6.rawQuery(r5, r7);	 Catch:{ Exception -> 0x026d, all -> 0x0290 }
        r5 = r4.moveToFirst();	 Catch:{ Exception -> 0x026d, all -> 0x02a6 }
        if (r5 == 0) goto L_0x02f5;
    L_0x015a:
        r5 = r4.isAfterLast();	 Catch:{ Exception -> 0x026d, all -> 0x02a6 }
        if (r5 != 0) goto L_0x02f5;
    L_0x0160:
        r5 = "country";
        r5 = r4.getColumnIndex(r5);	 Catch:{ Exception -> 0x026d, all -> 0x02a6 }
        r9 = r4.getString(r5);	 Catch:{ Exception -> 0x026d, all -> 0x02a6 }
        r5 = "countrycode";
        r5 = r4.getColumnIndex(r5);	 Catch:{ Exception -> 0x02ad, all -> 0x02a6 }
        r8 = r4.getString(r5);	 Catch:{ Exception -> 0x02ad, all -> 0x02a6 }
        r5 = "province";
        r5 = r4.getColumnIndex(r5);	 Catch:{ Exception -> 0x02b7, all -> 0x02a6 }
        r7 = r4.getString(r5);	 Catch:{ Exception -> 0x02b7, all -> 0x02a6 }
        r5 = "city";
        r5 = r4.getColumnIndex(r5);	 Catch:{ Exception -> 0x02bf, all -> 0x02a6 }
        r6 = r4.getString(r5);	 Catch:{ Exception -> 0x02bf, all -> 0x02a6 }
        r5 = "citycode";
        r5 = r4.getColumnIndex(r5);	 Catch:{ Exception -> 0x02c5, all -> 0x02a6 }
        r5 = r4.getString(r5);	 Catch:{ Exception -> 0x02c5, all -> 0x02a6 }
        r11 = "district";
        r11 = r4.getColumnIndex(r11);	 Catch:{ Exception -> 0x02c9, all -> 0x02a6 }
        r25 = r4.getString(r11);	 Catch:{ Exception -> 0x02c9, all -> 0x02a6 }
        r11 = r9;
        r9 = r8;
        r8 = r7;
        r7 = r6;
        r6 = r5;
        r5 = r25;
    L_0x01a3:
        if (r4 == 0) goto L_0x01a8;
    L_0x01a5:
        r4.close();	 Catch:{ Exception -> 0x02a1 }
    L_0x01a8:
        if (r11 == 0) goto L_0x01b8;
    L_0x01aa:
        r4 = new java.lang.String;
        r11 = r11.getBytes();
        r11 = com.baidu.location.f.b.b.a(r11);
        r4.<init>(r11);
        r11 = r4;
    L_0x01b8:
        if (r9 == 0) goto L_0x01c8;
    L_0x01ba:
        r4 = new java.lang.String;
        r9 = r9.getBytes();
        r9 = com.baidu.location.f.b.b.a(r9);
        r4.<init>(r9);
        r9 = r4;
    L_0x01c8:
        if (r8 == 0) goto L_0x01d8;
    L_0x01ca:
        r4 = new java.lang.String;
        r8 = r8.getBytes();
        r8 = com.baidu.location.f.b.b.a(r8);
        r4.<init>(r8);
        r8 = r4;
    L_0x01d8:
        if (r7 == 0) goto L_0x01e8;
    L_0x01da:
        r4 = new java.lang.String;
        r7 = r7.getBytes();
        r7 = com.baidu.location.f.b.b.a(r7);
        r4.<init>(r7);
        r7 = r4;
    L_0x01e8:
        if (r6 == 0) goto L_0x01f8;
    L_0x01ea:
        r4 = new java.lang.String;
        r6 = r6.getBytes();
        r6 = com.baidu.location.f.b.b.a(r6);
        r4.<init>(r6);
        r6 = r4;
    L_0x01f8:
        if (r5 == 0) goto L_0x0208;
    L_0x01fa:
        r4 = new java.lang.String;
        r5 = r5.getBytes();
        r5 = com.baidu.location.f.b.b.a(r5);
        r4.<init>(r5);
        r5 = r4;
    L_0x0208:
        if (r10 == 0) goto L_0x0218;
    L_0x020a:
        r4 = new java.lang.String;
        r10 = r10.getBytes();
        r10 = com.baidu.location.f.b.b.a(r10);
        r4.<init>(r10);
        r10 = r4;
    L_0x0218:
        if (r18 == 0) goto L_0x02e8;
    L_0x021a:
        r4 = new java.lang.String;
        r12 = r18.getBytes();
        r12 = com.baidu.location.f.b.b.a(r12);
        r4.<init>(r12);
    L_0x0227:
        r12 = new com.baidu.location.Address$Builder;
        r12.<init>();
        r11 = r12.country(r11);
        r9 = r11.countryCode(r9);
        r8 = r9.province(r8);
        r7 = r8.city(r7);
        r6 = r7.cityCode(r6);
        r5 = r6.district(r5);
        r5 = r5.street(r10);
        r4 = r5.streetNumber(r4);
        r4 = r4.build();
        return r4;
    L_0x0251:
        r5 = move-exception;
        r10 = r4;
        goto L_0x013c;
    L_0x0255:
        r4 = move-exception;
        r5 = r11;
        r4 = r10;
    L_0x0258:
        if (r5 == 0) goto L_0x0303;
    L_0x025a:
        r5.close();	 Catch:{ Exception -> 0x0260 }
        r10 = r4;
        goto L_0x013c;
    L_0x0260:
        r5 = move-exception;
        r10 = r4;
        goto L_0x013c;
    L_0x0264:
        r4 = move-exception;
        r19 = r11;
    L_0x0267:
        if (r19 == 0) goto L_0x026c;
    L_0x0269:
        r19.close();	 Catch:{ Exception -> 0x029f }
    L_0x026c:
        throw r4;
    L_0x026d:
        r5 = move-exception;
        r5 = r20;
        r6 = r21;
        r7 = r22;
        r8 = r23;
        r9 = r24;
    L_0x0278:
        if (r4 == 0) goto L_0x02ec;
    L_0x027a:
        r4.close();	 Catch:{ Exception -> 0x0286 }
        r11 = r9;
        r9 = r8;
        r8 = r7;
        r7 = r6;
        r6 = r5;
        r5 = r25;
        goto L_0x01a8;
    L_0x0286:
        r4 = move-exception;
        r11 = r9;
        r9 = r8;
        r8 = r7;
        r7 = r6;
        r6 = r5;
        r5 = r25;
        goto L_0x01a8;
    L_0x0290:
        r5 = move-exception;
        r30 = r5;
        r5 = r4;
        r4 = r30;
    L_0x0296:
        if (r5 == 0) goto L_0x029b;
    L_0x0298:
        r5.close();	 Catch:{ Exception -> 0x02a4 }
    L_0x029b:
        throw r4;
    L_0x029c:
        r5 = move-exception;
        goto L_0x0134;
    L_0x029f:
        r5 = move-exception;
        goto L_0x026c;
    L_0x02a1:
        r4 = move-exception;
        goto L_0x01a8;
    L_0x02a4:
        r5 = move-exception;
        goto L_0x029b;
    L_0x02a6:
        r5 = move-exception;
        r30 = r5;
        r5 = r4;
        r4 = r30;
        goto L_0x0296;
    L_0x02ad:
        r5 = move-exception;
        r5 = r20;
        r6 = r21;
        r7 = r22;
        r8 = r23;
        goto L_0x0278;
    L_0x02b7:
        r5 = move-exception;
        r5 = r20;
        r6 = r21;
        r7 = r22;
        goto L_0x0278;
    L_0x02bf:
        r5 = move-exception;
        r5 = r20;
        r6 = r21;
        goto L_0x0278;
    L_0x02c5:
        r5 = move-exception;
        r5 = r20;
        goto L_0x0278;
    L_0x02c9:
        r11 = move-exception;
        goto L_0x0278;
    L_0x02cb:
        r4 = move-exception;
        goto L_0x0267;
    L_0x02cd:
        r4 = move-exception;
        r5 = r19;
        r4 = r10;
        goto L_0x0258;
    L_0x02d2:
        r5 = move-exception;
        r5 = r19;
        goto L_0x0258;
    L_0x02d6:
        r5 = move-exception;
        r10 = r4;
        r4 = r5;
        goto L_0x012f;
    L_0x02db:
        r5 = move-exception;
        r5 = r11;
        r6 = r12;
        goto L_0x011e;
    L_0x02e0:
        r7 = move-exception;
        goto L_0x011e;
    L_0x02e3:
        r5 = move-exception;
        r5 = r14;
        r6 = r15;
        goto L_0x011e;
    L_0x02e8:
        r4 = r18;
        goto L_0x0227;
    L_0x02ec:
        r11 = r9;
        r9 = r8;
        r8 = r7;
        r7 = r6;
        r6 = r5;
        r5 = r25;
        goto L_0x01a8;
    L_0x02f5:
        r5 = r25;
        r6 = r20;
        r7 = r21;
        r8 = r22;
        r9 = r23;
        r11 = r24;
        goto L_0x01a3;
    L_0x0303:
        r10 = r4;
        goto L_0x013c;
    L_0x0306:
        r10 = r4;
        r4 = r26;
        goto L_0x010d;
    L_0x030b:
        r18 = r5;
        r10 = r6;
        goto L_0x007e;
    L_0x0310:
        r8 = r16;
        r5 = r14;
        r6 = r15;
        goto L_0x006c;
    L_0x0316:
        r5 = r11;
        r6 = r12;
        goto L_0x0076;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.f.do(double, double):com.baidu.location.Address");
    }

    List if(double d, double d2) {
        Cursor rawQuery;
        Poi poi;
        Throwable th;
        Cursor cursor = null;
        List arrayList = new ArrayList();
        String a = b.POI.a(this.a4, d, d2);
        Map hashMap = new HashMap();
        List arrayList2 = new ArrayList();
        try {
            rawQuery = this.a7.rawQuery(a, null);
            try {
                if (rawQuery.moveToFirst()) {
                    while (!rawQuery.isAfterLast()) {
                        String string = rawQuery.getString(0);
                        String string2 = rawQuery.getString(2);
                        double d3 = rawQuery.getDouble(4);
                        double d4 = rawQuery.getDouble(5);
                        int i = rawQuery.getInt(6);
                        double d5 = int(d2, d, d4, d3);
                        if (d5 < ((double) b.POI.case)) {
                            hashMap.put(new Poi(new String(com.baidu.location.f.b.b.a(string.getBytes())), new String(com.baidu.location.f.b.b.a(string2.getBytes())), 0.0d), Integer.valueOf(if(i, Math.round((float) d5))));
                        }
                        rawQuery.moveToNext();
                    }
                }
                if (rawQuery != null) {
                    try {
                        rawQuery.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                if (rawQuery != null) {
                    try {
                        rawQuery.close();
                    } catch (Exception e3) {
                    }
                }
                arrayList2.addAll(hashMap.entrySet());
                Collections.sort(arrayList2, new f_1(this));
                if (arrayList2.size() > 0) {
                    poi = (Poi) ((Entry) arrayList2.get(0)).getKey();
                    arrayList.add(new Poi(poi.getId(), poi.getName(), WeightedLatLng.DEFAULT_INTENSITY));
                }
                return arrayList;
            } catch (Throwable th2) {
                cursor = rawQuery;
                th = th2;
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            arrayList2.addAll(hashMap.entrySet());
            Collections.sort(arrayList2, new f_1(this));
            if (arrayList2.size() > 0) {
                poi = (Poi) ((Entry) arrayList2.get(0)).getKey();
                arrayList.add(new Poi(poi.getId(), poi.getName(), WeightedLatLng.DEFAULT_INTENSITY));
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        arrayList2.addAll(hashMap.entrySet());
        Collections.sort(arrayList2, new f_1(this));
        if (arrayList2.size() > 0) {
            poi = (Poi) ((Entry) arrayList2.get(0)).getKey();
            arrayList.add(new Poi(poi.getId(), poi.getName(), WeightedLatLng.DEFAULT_INTENSITY));
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void if(org.json.JSONObject r9) {
        /*
        r8 = this;
        r0 = r8.a7;
        if (r0 == 0) goto L_0x0075;
    L_0x0004:
        r0 = r8.a7;
        r0 = r0.isOpen();
        if (r0 == 0) goto L_0x0075;
    L_0x000c:
        r0 = r8.a7;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0.beginTransaction();	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r2 = com.baidu.location.c.f.b.values();	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r3 = r2.length;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = 0;
        r1 = r0;
    L_0x0018:
        if (r1 >= r3) goto L_0x007a;
    L_0x001a:
        r4 = r2[r1];	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = r4.try;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = r9.has(r0);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        if (r0 == 0) goto L_0x0076;
    L_0x0026:
        r0 = "";
        r5 = r4.else;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r5 = r9.has(r5);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        if (r5 == 0) goto L_0x003a;
    L_0x0032:
        r0 = r4.else;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = r9.getString(r0);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
    L_0x003a:
        r5 = new java.util.ArrayList;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r5.<init>();	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r6 = r4.try;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r6 = r9.getJSONObject(r6);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r7 = r4.a(r6);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r5.add(r7);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r7 = r4.int;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = r4.a(r6, r0, r7);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r5.addAll(r0);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r4 = r5.iterator();	 Catch:{ Exception -> 0x006f, all -> 0x008d }
    L_0x005d:
        r0 = r4.hasNext();	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        if (r0 == 0) goto L_0x0076;
    L_0x0063:
        r0 = r4.next();	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r5 = r8.a7;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r5.execSQL(r0);	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        goto L_0x005d;
    L_0x006f:
        r0 = move-exception;
        r0 = r8.a7;	 Catch:{ Exception -> 0x0096 }
        r0.endTransaction();	 Catch:{ Exception -> 0x0096 }
    L_0x0075:
        return;
    L_0x0076:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0018;
    L_0x007a:
        r0 = r8.a7;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = -1;
        r8.aX = r0;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = -1;
        r8.aU = r0;	 Catch:{ Exception -> 0x006f, all -> 0x008d }
        r0 = r8.a7;	 Catch:{ Exception -> 0x008b }
        r0.endTransaction();	 Catch:{ Exception -> 0x008b }
        goto L_0x0075;
    L_0x008b:
        r0 = move-exception;
        goto L_0x0075;
    L_0x008d:
        r0 = move-exception;
        r1 = r8.a7;	 Catch:{ Exception -> 0x0094 }
        r1.endTransaction();	 Catch:{ Exception -> 0x0094 }
    L_0x0093:
        throw r0;
    L_0x0094:
        r1 = move-exception;
        goto L_0x0093;
    L_0x0096:
        r0 = move-exception;
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.f.if(org.json.JSONObject):void");
    }
}
