package com.baidu.location.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.Poi;
import com.baidu.location.b.m;
import com.baidu.mapapi.map.WeightedLatLng;
import com.baidu.mtjstatsdk.BasicStoreTools;
import com.taobao.accs.common.Constants;
import com.tencent.stat.DeviceInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

final class a implements com.baidu.location.b.b {
    private static final int c = 10000;
    private static final int char = 1000;
    private static final int d = 2000;
    private static final int e = 4;
    private static final int else = 30;
    private static final int f = 2;
    private static final double for = 100.0d;
    private static final int g = 1;
    private static final int h = 0;
    static final int i = 30;
    private static final int if = 7776000;
    private static final int j = 100000;
    private static final int l = 604800;
    private static final int n = 300;
    private static final int p = 100;
    static final int s = 8;
    private static final int try = 15552000;
    private static final int u = 1000;
    private static final int v = 604800;
    private static final double w = 300.0d;
    private StringBuffer b = new StringBuffer();
    private final SQLiteDatabase byte;
    private int case;
    private ConcurrentHashMap do = new ConcurrentHashMap();
    private StringBuffer goto = null;
    private double int;
    private Long k;
    private ConcurrentHashMap long = new ConcurrentHashMap();
    private boolean m = false;
    private double new;
    private final d o;
    private final SQLiteDatabase q;
    private StringBuffer r = null;
    private HashSet t = new HashSet();
    private final b void = new b(this, this, true);
    private final b x = new b(this, this, false);

    private class a extends Thread {
        final /* synthetic */ a a;
        private BDLocation byte;
        private BDLocation do;
        private Long for;
        private String if;
        private BDLocation int;
        private String new;
        private LinkedHashMap try;

        private a(a aVar, String str, Long l, BDLocation bDLocation, BDLocation bDLocation2, BDLocation bDLocation3, String str2, LinkedHashMap linkedHashMap) {
            this.a = aVar;
            this.new = str;
            this.for = l;
            this.byte = bDLocation;
            this.int = bDLocation2;
            this.do = bDLocation3;
            this.if = str2;
            this.try = linkedHashMap;
        }

        public void run() {
            this.a.if(this.new, this.for, this.byte);
            this.a.r = null;
            this.a.goto = null;
            this.a.if(this.try);
            this.a.if(this.do, this.byte, this.int, this.new, this.for);
            if (this.if != null) {
                this.a.o.j().byte(this.if);
            }
            this.try = null;
            this.new = null;
            this.if = null;
            this.for = null;
            this.byte = null;
            this.int = null;
            this.do = null;
        }
    }

    private final class b extends m {
        private static final String dA = "1";
        private static final String dE = "2";
        private static final int dm = 50;
        private static final String dq = "0";
        private static final long dt = 43200000;
        private static final int dw = 2;
        private static final String dx = "3";
        private static final long dy = 86400000;
        private static final int dz = 5;
        private a dB;
        private boolean dC = false;
        private long dD = -1;
        private long dF = -1;
        final /* synthetic */ a dG;
        private String dn;
        private long dp = -1;
        private int dr = 0;
        private String ds;
        private final String du;
        private long dv = -1;

        class a_b_1 extends Thread {
            final /* synthetic */ b a;

            a_b_1(b bVar) {
                this.a = bVar;
            }

            public void run() {
                JSONObject jSONObject;
                Exception e;
                JSONObject jSONObject2;
                StringBuffer stringBuffer;
                StringBuffer stringBuffer2;
                StringBuffer stringBuffer3;
                Object obj;
                Object obj2;
                Object obj3;
                int i;
                int i2;
                String str;
                String string;
                Double valueOf;
                int i3;
                int i4;
                int i5;
                Object obj4;
                Object obj5;
                int i6;
                Exception exception;
                super.run();
                if (this.a.dG.q == null || this.a.dG.byte == null || !this.a.dG.q.isOpen() || !this.a.dG.byte.isOpen()) {
                    this.a.dC = false;
                    return;
                }
                JSONObject jSONObject3;
                Iterator keys;
                int i7;
                int i8;
                Object obj6;
                Object obj7;
                Object obj8;
                JSONObject jSONObject4 = null;
                try {
                    if (this.a.c6 != null) {
                        jSONObject3 = new JSONObject(EntityUtils.toString(this.a.c6, "utf-8"));
                        try {
                            jSONObject = jSONObject3.has(Constants.KEY_MODEL) ? jSONObject3.getJSONObject(Constants.KEY_MODEL) : null;
                            try {
                                if (jSONObject3.has("rgc")) {
                                    jSONObject4 = jSONObject3.getJSONObject("rgc");
                                }
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                this.a.dG.q.beginTransaction();
                                this.a.dG.byte.beginTransaction();
                                if (jSONObject4 != null) {
                                    this.a.dG.o.l().if(jSONObject4);
                                }
                                this.a.dF = System.currentTimeMillis();
                                this.a.dB.if(jSONObject3.getString("bdlist").split(";"));
                                this.a.dB.if(jSONObject3.getJSONObject("loadurl").getString("host"), jSONObject3.getJSONObject("loadurl").getString("module"), jSONObject3.getJSONObject("loadurl").getString("req"));
                                jSONObject2 = jSONObject.getJSONObject("cell");
                                keys = jSONObject2.keys();
                                stringBuffer = new StringBuffer();
                                stringBuffer2 = new StringBuffer();
                                stringBuffer3 = new StringBuffer();
                                obj = 1;
                                obj2 = 1;
                                obj3 = 1;
                                i = 0;
                                i7 = 0;
                                i2 = 0;
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    string = jSONObject2.getString(str);
                                    valueOf = Double.valueOf(string.split(",")[3]);
                                    if (obj2 == null) {
                                        try {
                                            stringBuffer2.append(',');
                                        } catch (Exception e3) {
                                            this.a.aC();
                                            return;
                                        } finally {
                                            if (this.a.dG.q != null && this.a.dG.q.isOpen()) {
                                                this.a.dG.q.endTransaction();
                                            }
                                            if (this.a.dG.byte != null && this.a.dG.byte.isOpen()) {
                                                this.a.dG.byte.endTransaction();
                                            }
                                            this.a.c6 = null;
                                            this.a.dC = false;
                                        }
                                    } else {
                                        obj2 = null;
                                    }
                                    stringBuffer2.append(str);
                                    i7++;
                                    if (valueOf.doubleValue() <= 0.0d) {
                                        if (obj == null) {
                                            stringBuffer.append(',');
                                        } else {
                                            obj = null;
                                        }
                                        stringBuffer.append(str);
                                        i8 = i + 1;
                                        obj6 = obj;
                                    } else {
                                        if (obj3 == null) {
                                            stringBuffer3.append(',');
                                        } else {
                                            obj3 = null;
                                        }
                                        stringBuffer3.append('(').append(str).append(',').append(string).append("," + (System.currentTimeMillis() / 1000)).append(')');
                                        i2++;
                                        i8 = i;
                                        obj6 = obj;
                                    }
                                    if (i7 >= 100) {
                                        this.a.dG.byte.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                                        obj2 = 1;
                                        stringBuffer2.setLength(0);
                                        i7 -= 100;
                                    }
                                    if (i2 >= 100) {
                                        this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                                        obj3 = 1;
                                        stringBuffer3.setLength(0);
                                        i2 -= 100;
                                    }
                                    if (i8 < 100) {
                                        this.a.dG.q.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                                        obj6 = 1;
                                        stringBuffer.setLength(0);
                                        i8 -= 100;
                                    }
                                    obj = obj6;
                                    i = i8;
                                }
                                if (i7 > 0) {
                                    this.a.dG.byte.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                                }
                                if (i2 > 0) {
                                    this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                                }
                                if (i > 0) {
                                    this.a.dG.q.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                                }
                                jSONObject2 = jSONObject.getJSONObject("ap");
                                keys = jSONObject2.keys();
                                i3 = 0;
                                i4 = 0;
                                i5 = 0;
                                obj7 = 1;
                                obj8 = 1;
                                obj6 = 1;
                                stringBuffer = new StringBuffer();
                                stringBuffer2 = new StringBuffer();
                                stringBuffer3 = new StringBuffer();
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    string = jSONObject2.getString(str);
                                    valueOf = Double.valueOf(string.split(",")[3]);
                                    if (obj8 == null) {
                                        stringBuffer2.append(',');
                                    } else {
                                        obj8 = null;
                                    }
                                    stringBuffer2.append(str);
                                    i4++;
                                    if (valueOf.doubleValue() <= 0.0d) {
                                        if (obj7 == null) {
                                            stringBuffer.append(',');
                                        } else {
                                            obj7 = null;
                                        }
                                        stringBuffer.append(str);
                                        obj4 = obj6;
                                        i = i5;
                                        i5 = i3 + 1;
                                        obj5 = obj4;
                                    } else {
                                        if (obj6 == null) {
                                            stringBuffer3.append(',');
                                        } else {
                                            obj6 = null;
                                        }
                                        stringBuffer3.append('(').append(str).append(',').append(string).append("," + (System.currentTimeMillis() / 1000)).append(')');
                                        i8 = i5 + 1;
                                        i5 = i3;
                                        i6 = i8;
                                        obj5 = obj6;
                                        i = i6;
                                    }
                                    if (i4 >= 100) {
                                        this.a.dG.byte.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                                        obj8 = 1;
                                        stringBuffer2.setLength(0);
                                        i4 -= 100;
                                    }
                                    if (i >= 100) {
                                        this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                                        obj5 = 1;
                                        stringBuffer3.setLength(0);
                                        i -= 100;
                                    }
                                    if (i5 <= 0) {
                                        this.a.dG.q.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                                    }
                                    i3 = i5;
                                    i5 = i;
                                    obj6 = obj5;
                                }
                                if (i4 > 0) {
                                    this.a.dG.byte.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                                }
                                if (i5 > 0) {
                                    this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                                }
                                if (i3 > 0) {
                                    this.a.dG.q.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                                }
                                this.a.dG.q.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);", new Object[]{"AP", "AP", Integer.valueOf(200000)}));
                                this.a.dG.q.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);", new Object[]{"CL", "CL", Integer.valueOf(200000)}));
                                this.a.dG.byte.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);", new Object[]{"AP", "AP", Integer.valueOf(a.c)}));
                                this.a.dG.byte.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);", new Object[]{"CL", "CL", Integer.valueOf(a.c)}));
                                this.a.aC();
                                this.a.dG.q.setTransactionSuccessful();
                                this.a.dG.byte.setTransactionSuccessful();
                                this.a.dG.q.endTransaction();
                                this.a.dG.byte.endTransaction();
                                this.a.c6 = null;
                                this.a.dC = false;
                            }
                        } catch (Exception e4) {
                            exception = e4;
                            jSONObject = null;
                            e = exception;
                            e.printStackTrace();
                            this.a.dG.q.beginTransaction();
                            this.a.dG.byte.beginTransaction();
                            if (jSONObject4 != null) {
                                this.a.dG.o.l().if(jSONObject4);
                            }
                            this.a.dF = System.currentTimeMillis();
                            this.a.dB.if(jSONObject3.getString("bdlist").split(";"));
                            this.a.dB.if(jSONObject3.getJSONObject("loadurl").getString("host"), jSONObject3.getJSONObject("loadurl").getString("module"), jSONObject3.getJSONObject("loadurl").getString("req"));
                            jSONObject2 = jSONObject.getJSONObject("cell");
                            keys = jSONObject2.keys();
                            stringBuffer = new StringBuffer();
                            stringBuffer2 = new StringBuffer();
                            stringBuffer3 = new StringBuffer();
                            obj = 1;
                            obj2 = 1;
                            obj3 = 1;
                            i = 0;
                            i7 = 0;
                            i2 = 0;
                            while (keys.hasNext()) {
                                str = (String) keys.next();
                                string = jSONObject2.getString(str);
                                valueOf = Double.valueOf(string.split(",")[3]);
                                if (obj2 == null) {
                                    obj2 = null;
                                } else {
                                    stringBuffer2.append(',');
                                }
                                stringBuffer2.append(str);
                                i7++;
                                if (valueOf.doubleValue() <= 0.0d) {
                                    if (obj3 == null) {
                                        obj3 = null;
                                    } else {
                                        stringBuffer3.append(',');
                                    }
                                    stringBuffer3.append('(').append(str).append(',').append(string).append("," + (System.currentTimeMillis() / 1000)).append(')');
                                    i2++;
                                    i8 = i;
                                    obj6 = obj;
                                } else {
                                    if (obj == null) {
                                        obj = null;
                                    } else {
                                        stringBuffer.append(',');
                                    }
                                    stringBuffer.append(str);
                                    i8 = i + 1;
                                    obj6 = obj;
                                }
                                if (i7 >= 100) {
                                    this.a.dG.byte.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                                    obj2 = 1;
                                    stringBuffer2.setLength(0);
                                    i7 -= 100;
                                }
                                if (i2 >= 100) {
                                    this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                                    obj3 = 1;
                                    stringBuffer3.setLength(0);
                                    i2 -= 100;
                                }
                                if (i8 < 100) {
                                    this.a.dG.q.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                                    obj6 = 1;
                                    stringBuffer.setLength(0);
                                    i8 -= 100;
                                }
                                obj = obj6;
                                i = i8;
                            }
                            if (i7 > 0) {
                                this.a.dG.byte.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                            }
                            if (i2 > 0) {
                                this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                            }
                            if (i > 0) {
                                this.a.dG.q.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                            }
                            jSONObject2 = jSONObject.getJSONObject("ap");
                            keys = jSONObject2.keys();
                            i3 = 0;
                            i4 = 0;
                            i5 = 0;
                            obj7 = 1;
                            obj8 = 1;
                            obj6 = 1;
                            stringBuffer = new StringBuffer();
                            stringBuffer2 = new StringBuffer();
                            stringBuffer3 = new StringBuffer();
                            while (keys.hasNext()) {
                                str = (String) keys.next();
                                string = jSONObject2.getString(str);
                                valueOf = Double.valueOf(string.split(",")[3]);
                                if (obj8 == null) {
                                    obj8 = null;
                                } else {
                                    stringBuffer2.append(',');
                                }
                                stringBuffer2.append(str);
                                i4++;
                                if (valueOf.doubleValue() <= 0.0d) {
                                    if (obj6 == null) {
                                        obj6 = null;
                                    } else {
                                        stringBuffer3.append(',');
                                    }
                                    stringBuffer3.append('(').append(str).append(',').append(string).append("," + (System.currentTimeMillis() / 1000)).append(')');
                                    i8 = i5 + 1;
                                    i5 = i3;
                                    i6 = i8;
                                    obj5 = obj6;
                                    i = i6;
                                } else {
                                    if (obj7 == null) {
                                        obj7 = null;
                                    } else {
                                        stringBuffer.append(',');
                                    }
                                    stringBuffer.append(str);
                                    obj4 = obj6;
                                    i = i5;
                                    i5 = i3 + 1;
                                    obj5 = obj4;
                                }
                                if (i4 >= 100) {
                                    this.a.dG.byte.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                                    obj8 = 1;
                                    stringBuffer2.setLength(0);
                                    i4 -= 100;
                                }
                                if (i >= 100) {
                                    this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                                    obj5 = 1;
                                    stringBuffer3.setLength(0);
                                    i -= 100;
                                }
                                if (i5 <= 0) {
                                    this.a.dG.q.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                                }
                                i3 = i5;
                                i5 = i;
                                obj6 = obj5;
                            }
                            if (i4 > 0) {
                                this.a.dG.byte.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                            }
                            if (i5 > 0) {
                                this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                            }
                            if (i3 > 0) {
                                this.a.dG.q.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                            }
                            this.a.dG.q.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);", new Object[]{"AP", "AP", Integer.valueOf(200000)}));
                            this.a.dG.q.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);", new Object[]{"CL", "CL", Integer.valueOf(200000)}));
                            this.a.dG.byte.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);", new Object[]{"AP", "AP", Integer.valueOf(a.c)}));
                            this.a.dG.byte.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);", new Object[]{"CL", "CL", Integer.valueOf(a.c)}));
                            this.a.aC();
                            this.a.dG.q.setTransactionSuccessful();
                            this.a.dG.byte.setTransactionSuccessful();
                            this.a.dG.q.endTransaction();
                            this.a.dG.byte.endTransaction();
                            this.a.c6 = null;
                            this.a.dC = false;
                        }
                    }
                    jSONObject3 = null;
                    jSONObject = null;
                } catch (Exception e5) {
                    exception = e5;
                    jSONObject3 = null;
                    jSONObject = null;
                    e = exception;
                    e.printStackTrace();
                    this.a.dG.q.beginTransaction();
                    this.a.dG.byte.beginTransaction();
                    if (jSONObject4 != null) {
                        this.a.dG.o.l().if(jSONObject4);
                    }
                    this.a.dF = System.currentTimeMillis();
                    this.a.dB.if(jSONObject3.getString("bdlist").split(";"));
                    this.a.dB.if(jSONObject3.getJSONObject("loadurl").getString("host"), jSONObject3.getJSONObject("loadurl").getString("module"), jSONObject3.getJSONObject("loadurl").getString("req"));
                    jSONObject2 = jSONObject.getJSONObject("cell");
                    keys = jSONObject2.keys();
                    stringBuffer = new StringBuffer();
                    stringBuffer2 = new StringBuffer();
                    stringBuffer3 = new StringBuffer();
                    obj = 1;
                    obj2 = 1;
                    obj3 = 1;
                    i = 0;
                    i7 = 0;
                    i2 = 0;
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        string = jSONObject2.getString(str);
                        valueOf = Double.valueOf(string.split(",")[3]);
                        if (obj2 == null) {
                            stringBuffer2.append(',');
                        } else {
                            obj2 = null;
                        }
                        stringBuffer2.append(str);
                        i7++;
                        if (valueOf.doubleValue() <= 0.0d) {
                            if (obj == null) {
                                stringBuffer.append(',');
                            } else {
                                obj = null;
                            }
                            stringBuffer.append(str);
                            i8 = i + 1;
                            obj6 = obj;
                        } else {
                            if (obj3 == null) {
                                stringBuffer3.append(',');
                            } else {
                                obj3 = null;
                            }
                            stringBuffer3.append('(').append(str).append(',').append(string).append("," + (System.currentTimeMillis() / 1000)).append(')');
                            i2++;
                            i8 = i;
                            obj6 = obj;
                        }
                        if (i7 >= 100) {
                            this.a.dG.byte.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                            obj2 = 1;
                            stringBuffer2.setLength(0);
                            i7 -= 100;
                        }
                        if (i2 >= 100) {
                            this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                            obj3 = 1;
                            stringBuffer3.setLength(0);
                            i2 -= 100;
                        }
                        if (i8 < 100) {
                            this.a.dG.q.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                            obj6 = 1;
                            stringBuffer.setLength(0);
                            i8 -= 100;
                        }
                        obj = obj6;
                        i = i8;
                    }
                    if (i7 > 0) {
                        this.a.dG.byte.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                    }
                    if (i2 > 0) {
                        this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                    }
                    if (i > 0) {
                        this.a.dG.q.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                    }
                    jSONObject2 = jSONObject.getJSONObject("ap");
                    keys = jSONObject2.keys();
                    i3 = 0;
                    i4 = 0;
                    i5 = 0;
                    obj7 = 1;
                    obj8 = 1;
                    obj6 = 1;
                    stringBuffer = new StringBuffer();
                    stringBuffer2 = new StringBuffer();
                    stringBuffer3 = new StringBuffer();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        string = jSONObject2.getString(str);
                        valueOf = Double.valueOf(string.split(",")[3]);
                        if (obj8 == null) {
                            stringBuffer2.append(',');
                        } else {
                            obj8 = null;
                        }
                        stringBuffer2.append(str);
                        i4++;
                        if (valueOf.doubleValue() <= 0.0d) {
                            if (obj7 == null) {
                                stringBuffer.append(',');
                            } else {
                                obj7 = null;
                            }
                            stringBuffer.append(str);
                            obj4 = obj6;
                            i = i5;
                            i5 = i3 + 1;
                            obj5 = obj4;
                        } else {
                            if (obj6 == null) {
                                stringBuffer3.append(',');
                            } else {
                                obj6 = null;
                            }
                            stringBuffer3.append('(').append(str).append(',').append(string).append("," + (System.currentTimeMillis() / 1000)).append(')');
                            i8 = i5 + 1;
                            i5 = i3;
                            i6 = i8;
                            obj5 = obj6;
                            i = i6;
                        }
                        if (i4 >= 100) {
                            this.a.dG.byte.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                            obj8 = 1;
                            stringBuffer2.setLength(0);
                            i4 -= 100;
                        }
                        if (i >= 100) {
                            this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                            obj5 = 1;
                            stringBuffer3.setLength(0);
                            i -= 100;
                        }
                        if (i5 <= 0) {
                            this.a.dG.q.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                        }
                        i3 = i5;
                        i5 = i;
                        obj6 = obj5;
                    }
                    if (i4 > 0) {
                        this.a.dG.byte.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                    }
                    if (i5 > 0) {
                        this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                    }
                    if (i3 > 0) {
                        this.a.dG.q.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                    }
                    this.a.dG.q.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);", new Object[]{"AP", "AP", Integer.valueOf(200000)}));
                    this.a.dG.q.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);", new Object[]{"CL", "CL", Integer.valueOf(200000)}));
                    this.a.dG.byte.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);", new Object[]{"AP", "AP", Integer.valueOf(a.c)}));
                    this.a.dG.byte.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);", new Object[]{"CL", "CL", Integer.valueOf(a.c)}));
                    this.a.aC();
                    this.a.dG.q.setTransactionSuccessful();
                    this.a.dG.byte.setTransactionSuccessful();
                    this.a.dG.q.endTransaction();
                    this.a.dG.byte.endTransaction();
                    this.a.c6 = null;
                    this.a.dC = false;
                }
                this.a.dG.q.beginTransaction();
                this.a.dG.byte.beginTransaction();
                if (jSONObject4 != null) {
                    this.a.dG.o.l().if(jSONObject4);
                }
                if (jSONObject3 != null && jSONObject3.has("type") && jSONObject3.getString("type").equals("0")) {
                    this.a.dF = System.currentTimeMillis();
                }
                if (jSONObject3 != null && jSONObject3.has("bdlist")) {
                    this.a.dB.if(jSONObject3.getString("bdlist").split(";"));
                }
                if (jSONObject3 != null && jSONObject3.has("loadurl")) {
                    this.a.dB.if(jSONObject3.getJSONObject("loadurl").getString("host"), jSONObject3.getJSONObject("loadurl").getString("module"), jSONObject3.getJSONObject("loadurl").getString("req"));
                }
                if (jSONObject != null && jSONObject.has("cell")) {
                    jSONObject2 = jSONObject.getJSONObject("cell");
                    keys = jSONObject2.keys();
                    stringBuffer = new StringBuffer();
                    stringBuffer2 = new StringBuffer();
                    stringBuffer3 = new StringBuffer();
                    obj = 1;
                    obj2 = 1;
                    obj3 = 1;
                    i = 0;
                    i7 = 0;
                    i2 = 0;
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        string = jSONObject2.getString(str);
                        valueOf = Double.valueOf(string.split(",")[3]);
                        if (obj2 == null) {
                            obj2 = null;
                        } else {
                            stringBuffer2.append(',');
                        }
                        stringBuffer2.append(str);
                        i7++;
                        if (valueOf.doubleValue() <= 0.0d) {
                            if (obj3 == null) {
                                obj3 = null;
                            } else {
                                stringBuffer3.append(',');
                            }
                            stringBuffer3.append('(').append(str).append(',').append(string).append("," + (System.currentTimeMillis() / 1000)).append(')');
                            i2++;
                            i8 = i;
                            obj6 = obj;
                        } else {
                            if (obj == null) {
                                obj = null;
                            } else {
                                stringBuffer.append(',');
                            }
                            stringBuffer.append(str);
                            i8 = i + 1;
                            obj6 = obj;
                        }
                        if (i7 >= 100) {
                            this.a.dG.byte.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                            obj2 = 1;
                            stringBuffer2.setLength(0);
                            i7 -= 100;
                        }
                        if (i2 >= 100) {
                            this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                            obj3 = 1;
                            stringBuffer3.setLength(0);
                            i2 -= 100;
                        }
                        if (i8 < 100) {
                            this.a.dG.q.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                            obj6 = 1;
                            stringBuffer.setLength(0);
                            i8 -= 100;
                        }
                        obj = obj6;
                        i = i8;
                    }
                    if (i7 > 0) {
                        this.a.dG.byte.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                    }
                    if (i2 > 0) {
                        this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO CL (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                    }
                    if (i > 0) {
                        this.a.dG.q.execSQL(String.format("DELETE FROM CL WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                    }
                }
                if (jSONObject != null && jSONObject.has("ap")) {
                    jSONObject2 = jSONObject.getJSONObject("ap");
                    keys = jSONObject2.keys();
                    i3 = 0;
                    i4 = 0;
                    i5 = 0;
                    obj7 = 1;
                    obj8 = 1;
                    obj6 = 1;
                    stringBuffer = new StringBuffer();
                    stringBuffer2 = new StringBuffer();
                    stringBuffer3 = new StringBuffer();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        string = jSONObject2.getString(str);
                        valueOf = Double.valueOf(string.split(",")[3]);
                        if (obj8 == null) {
                            obj8 = null;
                        } else {
                            stringBuffer2.append(',');
                        }
                        stringBuffer2.append(str);
                        i4++;
                        if (valueOf.doubleValue() <= 0.0d) {
                            if (obj6 == null) {
                                obj6 = null;
                            } else {
                                stringBuffer3.append(',');
                            }
                            stringBuffer3.append('(').append(str).append(',').append(string).append("," + (System.currentTimeMillis() / 1000)).append(')');
                            i8 = i5 + 1;
                            i5 = i3;
                            i6 = i8;
                            obj5 = obj6;
                            i = i6;
                        } else {
                            if (obj7 == null) {
                                obj7 = null;
                            } else {
                                stringBuffer.append(',');
                            }
                            stringBuffer.append(str);
                            obj4 = obj6;
                            i = i5;
                            i5 = i3 + 1;
                            obj5 = obj4;
                        }
                        if (i4 >= 100) {
                            this.a.dG.byte.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                            obj8 = 1;
                            stringBuffer2.setLength(0);
                            i4 -= 100;
                        }
                        if (i >= 100) {
                            this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                            obj5 = 1;
                            stringBuffer3.setLength(0);
                            i -= 100;
                        }
                        if (i5 <= 0) {
                            this.a.dG.q.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                        }
                        i3 = i5;
                        i5 = i;
                        obj6 = obj5;
                    }
                    if (i4 > 0) {
                        this.a.dG.byte.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer2.toString()}));
                    }
                    if (i5 > 0) {
                        this.a.dG.q.execSQL(String.format("INSERT OR REPLACE INTO AP (id,x,y,r,cl,timestamp) VALUES %s;", new Object[]{stringBuffer3.toString()}));
                    }
                    if (i3 > 0) {
                        this.a.dG.q.execSQL(String.format("DELETE FROM AP WHERE id IN (%s);", new Object[]{stringBuffer.toString()}));
                    }
                }
                this.a.dG.q.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);", new Object[]{"AP", "AP", Integer.valueOf(200000)}));
                this.a.dG.q.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY timestamp DESC, frequency DESC LIMIT %d);", new Object[]{"CL", "CL", Integer.valueOf(200000)}));
                this.a.dG.byte.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);", new Object[]{"AP", "AP", Integer.valueOf(a.c)}));
                this.a.dG.byte.execSQL(String.format("DELETE FROM %s WHERE id NOT IN (SELECT id FROM %s ORDER BY frequency DESC LIMIT %d);", new Object[]{"CL", "CL", Integer.valueOf(a.c)}));
                if (!(jSONObject == null || jSONObject.has("ap") || jSONObject.has("cell"))) {
                    this.a.aC();
                }
                this.a.dG.q.setTransactionSuccessful();
                this.a.dG.byte.setTransactionSuccessful();
                if (this.a.dG.q != null && this.a.dG.q.isOpen()) {
                    this.a.dG.q.endTransaction();
                }
                if (this.a.dG.byte != null && this.a.dG.byte.isOpen()) {
                    this.a.dG.byte.endTransaction();
                }
                this.a.c6 = null;
                this.a.dC = false;
            }
        }

        b(a aVar, a aVar2, boolean z) {
            this.dG = aVar;
            this.dB = aVar2;
            if (z) {
                this.du = "load";
            } else {
                this.du = "update";
            }
            this.c7 = new ArrayList();
            this.ds = d.ak;
        }

        private String aA() {
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", "2");
                    jSONObject.put(DeviceInfo.TAG_VERSION, "1");
                    jSONObject.put(BasicStoreTools.DEVICE_CUID, com.baidu.location.b.c.N().bm);
                    jSONObject.put("prod", com.baidu.location.b.c.bj + ":" + com.baidu.location.b.c.bn);
                    this.dp = System.currentTimeMillis();
                } catch (Exception e) {
                }
            } catch (Exception e2) {
                jSONObject = null;
            }
            return jSONObject != null ? Jni.G(jSONObject.toString()) : null;
        }

        private String aB() {
            JSONObject jSONObject;
            try {
                JSONObject H = this.dG.o.l().H();
                if (H != null) {
                    jSONObject = new JSONObject();
                    try {
                        jSONObject.put("type", "3");
                        jSONObject.put(DeviceInfo.TAG_VERSION, "1");
                        jSONObject.put(BasicStoreTools.DEVICE_CUID, com.baidu.location.b.c.N().bm);
                        jSONObject.put("prod", com.baidu.location.b.c.bj + ":" + com.baidu.location.b.c.bn);
                        jSONObject.put("rgc", H);
                        this.dp = System.currentTimeMillis();
                    } catch (Exception e) {
                    }
                } else {
                    jSONObject = null;
                }
            } catch (Exception e2) {
                jSONObject = null;
            }
            return jSONObject != null ? Jni.G(jSONObject.toString()) : null;
        }

        private void aC() {
            this.dr++;
            this.dD = System.currentTimeMillis();
        }

        private boolean aD() {
            if (this.dr < 2) {
                return true;
            }
            if (this.dD + dt >= System.currentTimeMillis()) {
                return false;
            }
            this.dr = 0;
            this.dD = -1;
            return true;
        }

        private String aE() {
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject();
                jSONObject.put("type", "0");
                jSONObject.put(BasicStoreTools.DEVICE_CUID, com.baidu.location.b.c.N().bm);
                jSONObject.put(DeviceInfo.TAG_VERSION, "1");
                jSONObject.put("prod", com.baidu.location.b.c.bj + ":" + com.baidu.location.b.c.bn);
            } catch (Exception e) {
                jSONObject = null;
            }
            return jSONObject != null ? Jni.G(jSONObject.toString()) : null;
        }

        private boolean aF() {
            Cursor rawQuery;
            Throwable th;
            Cursor cursor = null;
            boolean z = true;
            try {
                rawQuery = this.dG.q.rawQuery("SELECT COUNT(*) FROM AP;", null);
                try {
                    cursor = this.dG.q.rawQuery("SELECT COUNT(*) FROM CL", null);
                    if (!(rawQuery == null || !rawQuery.moveToFirst() || cursor == null || !cursor.moveToFirst() || (rawQuery.getInt(0) == 0 && cursor.getInt(0) == 0))) {
                        z = false;
                    }
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
                    return z;
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
                rawQuery = cursor;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                rawQuery = cursor;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
            return z;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String aG() {
            /*
            r11 = this;
            r4 = 0;
            r1 = 0;
            r6 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0175, all -> 0x015a }
            r6.<init>();	 Catch:{ Exception -> 0x0175, all -> 0x015a }
            r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0175, all -> 0x015a }
            r0.<init>();	 Catch:{ Exception -> 0x0175, all -> 0x015a }
            r2 = r11.dG;	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r2 = r2.byte;	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r3 = "SELECT * FROM %s WHERE frequency>%d ORDER BY frequency DESC LIMIT %d;";
            r5 = 3;
            r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r7 = 0;
            r8 = "CL";
            r5[r7] = r8;	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r7 = 1;
            r8 = 5;
            r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r5[r7] = r8;	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r7 = 2;
            r8 = 50;
            r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r5[r7] = r8;	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r3 = java.lang.String.format(r3, r5);	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            r5 = 0;
            r2 = r2.rawQuery(r3, r5);	 Catch:{ Exception -> 0x017b, all -> 0x015a }
            if (r2 == 0) goto L_0x0186;
        L_0x0038:
            r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            if (r3 == 0) goto L_0x0186;
        L_0x003e:
            r3 = r2.getCount();	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r5 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r5.<init>();	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
        L_0x0047:
            r7 = r2.isAfterLast();	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            if (r7 != 0) goto L_0x00a8;
        L_0x004d:
            r7 = 1;
            r7 = r2.getString(r7);	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r5.put(r7);	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r2.moveToNext();	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            goto L_0x0047;
        L_0x0059:
            r3 = move-exception;
            r3 = r1;
        L_0x005b:
            if (r3 == 0) goto L_0x0060;
        L_0x005d:
            r3.close();	 Catch:{ Exception -> 0x0169 }
        L_0x0060:
            if (r2 == 0) goto L_0x0183;
        L_0x0062:
            r2.close();	 Catch:{ Exception -> 0x0156 }
            r2 = r0;
        L_0x0066:
            if (r2 == 0) goto L_0x0180;
        L_0x0068:
            r0 = "model";
            r0 = r2.has(r0);
            if (r0 != 0) goto L_0x0180;
        L_0x0070:
            r4 = r11.dv;
            r6 = -1;
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 == 0) goto L_0x0086;
        L_0x0078:
            r4 = r11.dv;
            r6 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
            r4 = r4 + r6;
            r6 = java.lang.System.currentTimeMillis();
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 >= 0) goto L_0x0180;
        L_0x0086:
            r0 = r2.toString();
            r1 = com.baidu.location.Jni.G(r0);
            r4 = java.lang.System.currentTimeMillis();
            r11.dv = r4;
            r0 = r1;
        L_0x0095:
            if (r2 == 0) goto L_0x00a7;
        L_0x0097:
            r1 = "model";
            r1 = r2.has(r1);
            if (r1 == 0) goto L_0x00a7;
        L_0x009f:
            r0 = r2.toString();
            r0 = com.baidu.location.Jni.G(r0);
        L_0x00a7:
            return r0;
        L_0x00a8:
            r7 = "cell";
            r6.put(r7, r5);	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r5 = r3;
        L_0x00ae:
            r3 = r11.dG;	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r3 = r3.byte;	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r7 = "SELECT * FROM %s WHERE frequency>%d ORDER BY frequency DESC LIMIT %d;";
            r8 = 3;
            r8 = new java.lang.Object[r8];	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r9 = 0;
            r10 = "AP";
            r8[r9] = r10;	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r9 = 1;
            r10 = 5;
            r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r8[r9] = r10;	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r9 = 2;
            r10 = 50;
            r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r8[r9] = r10;	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r7 = java.lang.String.format(r7, r8);	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            r8 = 0;
            r3 = r3.rawQuery(r7, r8);	 Catch:{ Exception -> 0x0059, all -> 0x0170 }
            if (r3 == 0) goto L_0x0103;
        L_0x00da:
            r7 = r3.moveToFirst();	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            if (r7 == 0) goto L_0x0103;
        L_0x00e0:
            r4 = r3.getCount();	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r7 = new org.json.JSONArray;	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r7.<init>();	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
        L_0x00e9:
            r8 = r3.isAfterLast();	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            if (r8 != 0) goto L_0x00fe;
        L_0x00ef:
            r8 = 1;
            r8 = r3.getString(r8);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r7.put(r8);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r3.moveToNext();	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            goto L_0x00e9;
        L_0x00fb:
            r4 = move-exception;
            goto L_0x005b;
        L_0x00fe:
            r8 = "ap";
            r6.put(r8, r7);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
        L_0x0103:
            r7 = "type";
            r8 = "1";
            r0.put(r7, r8);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r7 = "cuid";
            r8 = com.baidu.location.b.c.N();	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r8 = r8.bm;	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r0.put(r7, r8);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r7 = "ver";
            r8 = "1";
            r0.put(r7, r8);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r7 = "prod";
            r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r8.<init>();	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r9 = com.baidu.location.b.c.bj;	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r8 = r8.append(r9);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r9 = ":";
            r8 = r8.append(r9);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r9 = com.baidu.location.b.c.bn;	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r8 = r8.append(r9);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r8 = r8.toString();	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            r0.put(r7, r8);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
            if (r5 != 0) goto L_0x0140;
        L_0x013e:
            if (r4 == 0) goto L_0x0145;
        L_0x0140:
            r4 = "model";
            r0.put(r4, r6);	 Catch:{ Exception -> 0x00fb, all -> 0x0172 }
        L_0x0145:
            if (r3 == 0) goto L_0x014a;
        L_0x0147:
            r3.close();	 Catch:{ Exception -> 0x0167 }
        L_0x014a:
            if (r2 == 0) goto L_0x0183;
        L_0x014c:
            r2.close();	 Catch:{ Exception -> 0x0152 }
            r2 = r0;
            goto L_0x0066;
        L_0x0152:
            r2 = move-exception;
            r2 = r0;
            goto L_0x0066;
        L_0x0156:
            r2 = move-exception;
            r2 = r0;
            goto L_0x0066;
        L_0x015a:
            r0 = move-exception;
            r2 = r1;
        L_0x015c:
            if (r1 == 0) goto L_0x0161;
        L_0x015e:
            r1.close();	 Catch:{ Exception -> 0x016c }
        L_0x0161:
            if (r2 == 0) goto L_0x0166;
        L_0x0163:
            r2.close();	 Catch:{ Exception -> 0x016e }
        L_0x0166:
            throw r0;
        L_0x0167:
            r3 = move-exception;
            goto L_0x014a;
        L_0x0169:
            r3 = move-exception;
            goto L_0x0060;
        L_0x016c:
            r1 = move-exception;
            goto L_0x0161;
        L_0x016e:
            r1 = move-exception;
            goto L_0x0166;
        L_0x0170:
            r0 = move-exception;
            goto L_0x015c;
        L_0x0172:
            r0 = move-exception;
            r1 = r3;
            goto L_0x015c;
        L_0x0175:
            r0 = move-exception;
            r0 = r1;
            r2 = r1;
            r3 = r1;
            goto L_0x005b;
        L_0x017b:
            r2 = move-exception;
            r2 = r1;
            r3 = r1;
            goto L_0x005b;
        L_0x0180:
            r0 = r1;
            goto L_0x0095;
        L_0x0183:
            r2 = r0;
            goto L_0x0066;
        L_0x0186:
            r5 = r4;
            goto L_0x00ae;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.a.b.aG():java.lang.String");
        }

        private void az() {
            this.dn = null;
            if (!aF()) {
                this.dn = aG();
            } else if (this.dF == -1 || this.dF + 86400000 <= System.currentTimeMillis()) {
                this.dn = aE();
            }
            if (this.dn == null && (this.dp == -1 || this.dp + 86400000 <= System.currentTimeMillis())) {
                if (this.dG.o.l().F()) {
                    this.dn = aA();
                } else {
                    this.dn = aB();
                }
            }
            if (this.dn != null) {
                ao();
            }
        }

        private void new(String str, String str2, String str3) {
            this.dn = str3;
            this.ds = String.format("http://%s/%s", new Object[]{str, str2});
            ao();
        }

        void aH() {
            if (aD() && !this.dC) {
                this.dG.x.az();
            }
        }

        public void au() {
            this.dC = true;
            this.c5 = this.ds;
            this.c7.clear();
            this.c7.add(new BasicNameValuePair("qt", this.du));
            this.c7.add(new BasicNameValuePair("req", this.dn));
        }

        public void int(boolean z) {
            if (!z || this.c6 == null) {
                this.dC = false;
                aC();
                return;
            }
            new a_b_1(this).start();
        }
    }

    private static final class c {
        double a;
        double do;
        double if;

        private c(double d, double d2, double d3) {
            this.a = d;
            this.do = d2;
            this.if = d3;
        }
    }

    a(d dVar) {
        SQLiteDatabase openOrCreateDatabase;
        SQLiteDatabase sQLiteDatabase = null;
        this.o = dVar;
        try {
            File file;
            file = new File(this.o.d(), "ofl_location.db");
            if (!file.exists()) {
                file.createNewFile();
            }
            openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
        } catch (Exception e) {
            openOrCreateDatabase = null;
        }
        this.q = openOrCreateDatabase;
        if (this.q != null) {
            try {
                this.q.execSQL("CREATE TABLE IF NOT EXISTS AP (id LONG PRIMARY KEY,x DOUBLE,y DOUBLE,r INTEGER,cl DOUBLE,timestamp INTEGER, frequency INTEGER DEFAULT 0);");
                this.q.execSQL("CREATE TABLE IF NOT EXISTS CL (id LONG PRIMARY KEY,x DOUBLE,y DOUBLE,r INTEGER,cl DOUBLE,timestamp INTEGER, frequency INTEGER DEFAULT 0);");
            } catch (Exception e2) {
            }
        }
        try {
            file = new File(this.o.d(), "ofl_statistics.db");
            if (!file.exists()) {
                file.createNewFile();
            }
            sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
        } catch (Exception e3) {
        }
        this.byte = sQLiteDatabase;
        if (this.byte != null) {
            try {
                this.byte.execSQL("CREATE TABLE IF NOT EXISTS AP (id LONG PRIMARY KEY, originid VARCHAR(15), frequency INTEGER DEFAULT 0);");
                this.byte.execSQL("CREATE TABLE IF NOT EXISTS CL (id LONG PRIMARY KEY, originid VARCHAR(40), frequency INTEGER DEFAULT 0);");
            } catch (Exception e4) {
            }
        }
    }

    private double if(double d, double d2, double d3, double d4) {
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

    private int if(ArrayList arrayList, double d) {
        if (arrayList.size() == 0) {
            return 0;
        }
        int i = 0;
        while (true) {
            int i2;
            int i3;
            if (arrayList.size() >= 3) {
                double d2 = 0.0d;
                double d3 = 0.0d;
                for (i2 = 0; i2 < arrayList.size(); i2++) {
                    d2 += ((c) arrayList.get(i2)).a;
                    d3 += ((c) arrayList.get(i2)).do;
                }
                d2 /= (double) arrayList.size();
                d3 /= (double) arrayList.size();
                int i4 = 0;
                int i5 = -1;
                double d4 = -1.0d;
                while (i4 < arrayList.size()) {
                    double d5 = if(d3, d2, ((c) arrayList.get(i4)).do, ((c) arrayList.get(i4)).a);
                    if (d5 > d4) {
                        i2 = i4;
                    } else {
                        i2 = i5;
                        d5 = d4;
                    }
                    i4++;
                    i5 = i2;
                    d4 = d5;
                }
                if (d4 > d && i5 >= 0 && i5 < arrayList.size()) {
                    i++;
                    arrayList.remove(i5);
                    i2 = 1;
                    i3 = i;
                    if (i2 == 1) {
                        return i3;
                    }
                    i = i3;
                }
            }
            i2 = 0;
            i3 = i;
            if (i2 == 1) {
                return i3;
            }
            i = i3;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.baidu.location.BDLocation if(java.lang.Long r20) {
        /*
        r19 = this;
        r2 = 0;
        r0 = r19;
        r0.m = r2;
        r8 = 0;
        r6 = 0;
        r4 = 0;
        r3 = 0;
        r0 = r19;
        r2 = r0.k;
        if (r2 == 0) goto L_0x0046;
    L_0x0011:
        r0 = r19;
        r2 = r0.k;
        r0 = r20;
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0046;
    L_0x001d:
        r3 = 1;
        r0 = r19;
        r6 = r0.new;
        r0 = r19;
        r4 = r0.int;
        r0 = r19;
        r8 = r0.case;
    L_0x002a:
        if (r3 == 0) goto L_0x010d;
    L_0x002c:
        r2 = new com.baidu.location.BDLocation;
        r2.<init>();
        r3 = (float) r8;
        r2.setRadius(r3);
        r2.setLatitude(r4);
        r2.setLongitude(r6);
        r3 = "cl";
        r2.setNetworkLocationType(r3);
        r3 = 66;
        r2.setLocType(r3);
    L_0x0045:
        return r2;
    L_0x0046:
        r2 = 0;
        r9 = java.util.Locale.US;
        r10 = "SELECT * FROM CL WHERE id = %d AND timestamp + %d > %d;";
        r11 = 3;
        r11 = new java.lang.Object[r11];
        r12 = 0;
        r11[r12] = r20;
        r12 = 1;
        r13 = 15552000; // 0xed4e00 float:2.1792994E-38 double:7.683709E-317;
        r13 = java.lang.Integer.valueOf(r13);
        r11[r12] = r13;
        r12 = 2;
        r14 = java.lang.System.currentTimeMillis();
        r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r14 = r14 / r16;
        r13 = java.lang.Long.valueOf(r14);
        r11[r12] = r13;
        r9 = java.lang.String.format(r9, r10, r11);
        r0 = r19;
        r10 = r0.q;	 Catch:{ Exception -> 0x00f6, all -> 0x0101 }
        r11 = 0;
        r2 = r10.rawQuery(r9, r11);	 Catch:{ Exception -> 0x00f6, all -> 0x0101 }
        if (r2 == 0) goto L_0x00e5;
    L_0x0079:
        r9 = r2.moveToFirst();	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        if (r9 == 0) goto L_0x00e5;
    L_0x007f:
        r9 = "cl";
        r9 = r2.getColumnIndex(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r10 = r2.getDouble(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r12 = 0;
        r9 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r9 <= 0) goto L_0x00e5;
    L_0x008f:
        r3 = 1;
        r9 = "x";
        r9 = r2.getColumnIndex(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r6 = r2.getDouble(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r9 = "y";
        r9 = r2.getColumnIndex(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r4 = r2.getDouble(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r9 = "r";
        r9 = r2.getColumnIndex(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r8 = r2.getInt(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r9 = "timestamp";
        r9 = r2.getColumnIndex(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r9 = r2.getInt(r9);	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r10 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;
        r9 = r9 + r10;
        r10 = (long) r9;	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r14 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r12 = r12 / r14;
        r9 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r9 >= 0) goto L_0x00cd;
    L_0x00c8:
        r9 = 1;
        r0 = r19;
        r0.m = r9;	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
    L_0x00cd:
        r9 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r8 >= r9) goto L_0x00ef;
    L_0x00d1:
        r8 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
    L_0x00d3:
        r0 = r19;
        r0.new = r6;	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r0 = r19;
        r0.int = r4;	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r0 = r19;
        r0.case = r8;	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
        r0 = r20;
        r1 = r19;
        r1.k = r0;	 Catch:{ Exception -> 0x00f6, all -> 0x0112 }
    L_0x00e5:
        if (r2 == 0) goto L_0x002a;
    L_0x00e7:
        r2.close();	 Catch:{ Exception -> 0x00ec }
        goto L_0x002a;
    L_0x00ec:
        r2 = move-exception;
        goto L_0x002a;
    L_0x00ef:
        r9 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        if (r9 >= r8) goto L_0x00d3;
    L_0x00f3:
        r8 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        goto L_0x00d3;
    L_0x00f6:
        r9 = move-exception;
        if (r2 == 0) goto L_0x002a;
    L_0x00f9:
        r2.close();	 Catch:{ Exception -> 0x00fe }
        goto L_0x002a;
    L_0x00fe:
        r2 = move-exception;
        goto L_0x002a;
    L_0x0101:
        r3 = move-exception;
        r18 = r3;
        r3 = r2;
        r2 = r18;
    L_0x0107:
        if (r3 == 0) goto L_0x010c;
    L_0x0109:
        r3.close();	 Catch:{ Exception -> 0x0110 }
    L_0x010c:
        throw r2;
    L_0x010d:
        r2 = 0;
        goto L_0x0045;
    L_0x0110:
        r3 = move-exception;
        goto L_0x010c;
    L_0x0112:
        r3 = move-exception;
        r18 = r3;
        r3 = r2;
        r2 = r18;
        goto L_0x0107;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.a.if(java.lang.Long):com.baidu.location.BDLocation");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.baidu.location.BDLocation if(java.util.LinkedHashMap r34, com.baidu.location.BDLocation r35, int r36) {
        /*
        r33 = this;
        r0 = r33;
        r2 = r0.b;
        r3 = 0;
        r2.setLength(r3);
        r6 = 0;
        r4 = 0;
        r2 = 0;
        if (r35 == 0) goto L_0x0382;
    L_0x000f:
        r2 = 1;
        r4 = r35.getLatitude();
        r6 = r35.getLongitude();
        r21 = r2;
    L_0x001a:
        r28 = 0;
        r26 = 0;
        r24 = 0;
        r23 = 0;
        r10 = new java.lang.StringBuffer;
        r10.<init>();
        r3 = 1;
        r2 = r34.entrySet();
        r11 = r2.iterator();
        r2 = 0;
        r8 = r2;
        r9 = r3;
    L_0x0033:
        r2 = r34.size();
        r3 = 30;
        r2 = java.lang.Math.min(r2, r3);
        if (r8 >= r2) goto L_0x0088;
    L_0x003f:
        r2 = r11.next();
        r2 = (java.util.Map.Entry) r2;
        r3 = r2.getKey();
        r3 = (java.lang.String) r3;
        r2 = r2.getValue();
        r2 = (java.lang.Integer) r2;
        r12 = r2.intValue();
        if (r12 >= 0) goto L_0x0060;
    L_0x0057:
        r2 = r2.intValue();
        r2 = -r2;
        r2 = java.lang.Integer.valueOf(r2);
    L_0x0060:
        r12 = com.baidu.location.Jni.I(r3);
        if (r12 != 0) goto L_0x006c;
    L_0x0066:
        r3 = r9;
    L_0x0067:
        r2 = r8 + 1;
        r8 = r2;
        r9 = r3;
        goto L_0x0033;
    L_0x006c:
        r0 = r33;
        r13 = r0.do;
        r13.put(r12, r3);
        if (r9 == 0) goto L_0x0082;
    L_0x0075:
        r9 = 0;
    L_0x0076:
        r0 = r33;
        r3 = r0.long;
        r3.put(r12, r2);
        r10.append(r12);
        r3 = r9;
        goto L_0x0067;
    L_0x0082:
        r3 = 44;
        r10.append(r3);
        goto L_0x0076;
    L_0x0088:
        r2 = java.util.Locale.US;
        r3 = "SELECT * FROM AP WHERE id IN (%s) AND timestamp+%d>%d;";
        r8 = 3;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r10;
        r9 = 1;
        r10 = 7776000; // 0x76a700 float:1.0896497E-38 double:3.8418545E-317;
        r10 = java.lang.Integer.valueOf(r10);
        r8[r9] = r10;
        r9 = 2;
        r10 = java.lang.System.currentTimeMillis();
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r10 = r10 / r12;
        r10 = java.lang.Long.valueOf(r10);
        r8[r9] = r10;
        r3 = java.lang.String.format(r2, r3, r8);
        r2 = 0;
        r0 = r33;
        r8 = r0.q;	 Catch:{ Exception -> 0x034b, all -> 0x0345 }
        r9 = 0;
        r22 = r8.rawQuery(r3, r9);	 Catch:{ Exception -> 0x034b, all -> 0x0345 }
        if (r22 == 0) goto L_0x0379;
    L_0x00ba:
        r2 = r22.moveToFirst();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        if (r2 == 0) goto L_0x0379;
    L_0x00c0:
        r29 = new java.util.ArrayList;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r29.<init>();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
    L_0x00c5:
        r2 = r22.isAfterLast();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        if (r2 != 0) goto L_0x022e;
    L_0x00cb:
        r2 = 0;
        r0 = r22;
        r2 = r0.getLong(r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3 = 1;
        r0 = r22;
        r10 = r0.getDouble(r3);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3 = 2;
        r0 = r22;
        r8 = r0.getDouble(r3);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3 = 3;
        r0 = r22;
        r12 = r0.getInt(r3);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3 = 4;
        r0 = r22;
        r14 = r0.getDouble(r3);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3 = 5;
        r0 = r22;
        r3 = r0.getInt(r3);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r0 = r33;
        r13 = r0.t;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r13.add(r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r13 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;
        r3 = r3 + r13;
        r0 = (long) r3;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r16 = r0;
        r18 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r30 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r18 = r18 / r30;
        r3 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1));
        if (r3 >= 0) goto L_0x0160;
    L_0x0113:
        r0 = r33;
        r3 = r0.b;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3 = r3.length();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        if (r3 <= 0) goto L_0x0126;
    L_0x011d:
        r0 = r33;
        r3 = r0.b;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r13 = ",";
        r3.append(r13);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
    L_0x0126:
        r0 = r33;
        r3 = r0.b;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r13 = java.util.Locale.US;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r16 = "(%d,\"%s\",%d)";
        r17 = 3;
        r0 = r17;
        r0 = new java.lang.Object[r0];	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r17 = r0;
        r18 = 0;
        r17[r18] = r2;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r18 = 1;
        r0 = r33;
        r0 = r0.do;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r19 = r0;
        r0 = r19;
        r19 = r0.get(r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r17[r18] = r19;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r18 = 2;
        r19 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;
        r19 = java.lang.Integer.valueOf(r19);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r17[r18] = r19;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r0 = r16;
        r1 = r17;
        r13 = java.lang.String.format(r13, r0, r1);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3.append(r13);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
    L_0x0160:
        r16 = 0;
        r3 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r3 > 0) goto L_0x0197;
    L_0x0166:
        r22.moveToNext();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        goto L_0x00c5;
    L_0x016b:
        r2 = move-exception;
        r2 = r22;
        r3 = r23;
        r12 = r24;
        r10 = r26;
        r4 = r28;
    L_0x0176:
        if (r2 == 0) goto L_0x017b;
    L_0x0178:
        r2.close();	 Catch:{ Exception -> 0x033f }
    L_0x017b:
        if (r3 == 0) goto L_0x033c;
    L_0x017d:
        r2 = new com.baidu.location.BDLocation;
        r2.<init>();
        r3 = (float) r4;
        r2.setRadius(r3);
        r2.setLatitude(r12);
        r2.setLongitude(r10);
        r3 = "wf";
        r2.setNetworkLocationType(r3);
        r3 = 66;
        r2.setLocType(r3);
    L_0x0196:
        return r2;
    L_0x0197:
        r14 = 0;
        r3 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1));
        if (r3 <= 0) goto L_0x01a9;
    L_0x019d:
        r14 = 0;
        r3 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1));
        if (r3 <= 0) goto L_0x01a9;
    L_0x01a3:
        if (r12 <= 0) goto L_0x01a9;
    L_0x01a5:
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r12 < r3) goto L_0x01b5;
    L_0x01a9:
        r22.moveToNext();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        goto L_0x00c5;
    L_0x01ae:
        r2 = move-exception;
    L_0x01af:
        if (r22 == 0) goto L_0x01b4;
    L_0x01b1:
        r22.close();	 Catch:{ Exception -> 0x0342 }
    L_0x01b4:
        throw r2;
    L_0x01b5:
        r3 = 1;
        r0 = r21;
        if (r0 != r3) goto L_0x01ce;
    L_0x01ba:
        r3 = r33;
        r14 = r3.if(r4, r6, r8, r10);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r16 = 4666723172467343360; // 0x40c3880000000000 float:0.0 double:10000.0;
        r3 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r3 <= 0) goto L_0x01ce;
    L_0x01c9:
        r22.moveToNext();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        goto L_0x00c5;
    L_0x01ce:
        r0 = r33;
        r3 = r0.long;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r2 = r3.get(r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r2 = (java.lang.Integer) r2;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r2 = r2.intValue();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3 = 30;
        r2 = java.lang.Math.max(r3, r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r3 = 100;
        r2 = java.lang.Math.min(r3, r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r14 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r3 = 70;
        if (r2 <= r3) goto L_0x0225;
    L_0x01ee:
        r2 = r2 + -70;
        r2 = (double) r2;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r16 = 4629137466983448576; // 0x403e000000000000 float:0.0 double:30.0;
        r2 = r2 / r16;
        r2 = r2 + r14;
    L_0x01f6:
        r14 = 4632233691727265792; // 0x4049000000000000 float:0.0 double:50.0;
        r12 = (double) r12;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r12 = java.lang.Math.max(r14, r12);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r14 = 4603579539098121011; // 0x3fe3333333333333 float:4.172325E-8 double:0.6;
        r12 = java.lang.Math.pow(r12, r14);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r14 = -4634023872579145564; // 0xbfb0a3d70a3d70a4 float:9.121204E-33 double:-0.065;
        r12 = r12 * r14;
        r2 = r2 * r12;
        r18 = java.lang.Math.exp(r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r13 = new com.baidu.location.c.a$c;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r20 = 0;
        r14 = r10;
        r16 = r8;
        r13.<init>(r14, r16, r18);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r0 = r29;
        r0.add(r13);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r22.moveToNext();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        goto L_0x00c5;
    L_0x0225:
        r2 = r2 + -70;
        r2 = (double) r2;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r16 = 4632233691727265792; // 0x4049000000000000 float:0.0 double:50.0;
        r2 = r2 / r16;
        r2 = r2 + r14;
        goto L_0x01f6;
    L_0x022e:
        r2 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r0 = r33;
        r1 = r29;
        r0.if(r1, r2);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r10 = 0;
        r12 = 0;
        r8 = 0;
        r2 = 0;
        r16 = r2;
    L_0x0243:
        r2 = r29.size();	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r0 = r16;
        if (r0 >= r2) goto L_0x027e;
    L_0x024b:
        r0 = r29;
        r1 = r16;
        r2 = r0.get(r1);	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r2 = (com.baidu.location.c.a.c) r2;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r14 = r2.if;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r18 = 0;
        r3 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1));
        if (r3 > 0) goto L_0x0266;
    L_0x025d:
        r2 = r8;
        r8 = r12;
    L_0x025f:
        r12 = r16 + 1;
        r16 = r12;
        r12 = r8;
        r8 = r2;
        goto L_0x0243;
    L_0x0266:
        r14 = r2.a;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r0 = r2.if;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r18 = r0;
        r14 = r14 * r18;
        r14 = r14 + r10;
        r10 = r2.do;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r0 = r2.if;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r18 = r0;
        r10 = r10 * r18;
        r10 = r10 + r12;
        r2 = r2.if;	 Catch:{ Exception -> 0x016b, all -> 0x01ae }
        r2 = r2 + r8;
        r8 = r10;
        r10 = r14;
        goto L_0x025f;
    L_0x027e:
        r2 = 0;
        r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x036f;
    L_0x0284:
        r2 = 0;
        r2 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x036f;
    L_0x028a:
        r2 = 0;
        r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x036f;
    L_0x0290:
        r10 = r10 / r8;
        r12 = r12 / r8;
        r3 = 1;
        r8 = 0;
        r2 = 0;
        r32 = r2;
        r2 = r8;
        r8 = r32;
    L_0x029a:
        r9 = r29.size();	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        if (r8 >= r9) goto L_0x02c7;
    L_0x02a0:
        r0 = (double) r2;	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r18 = r0;
        r0 = r29;
        r2 = r0.get(r8);	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r2 = (com.baidu.location.c.a.c) r2;	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r14 = r2.a;	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r0 = r29;
        r2 = r0.get(r8);	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r2 = (com.baidu.location.c.a.c) r2;	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r0 = r2.do;	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r16 = r0;
        r9 = r33;
        r14 = r9.if(r10, r12, r14, r16);	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r14 = r14 + r18;
        r9 = (float) r14;	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r2 = r8 + 1;
        r8 = r2;
        r2 = r9;
        goto L_0x029a;
    L_0x02c7:
        r8 = r29.size();	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r8 = (float) r8;	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r2 = r2 / r8;
        r28 = java.lang.Math.round(r2);	 Catch:{ Exception -> 0x0356, all -> 0x01ae }
        r2 = 30;
        r0 = r28;
        if (r0 >= r2) goto L_0x032f;
    L_0x02d7:
        r28 = 30;
        r2 = r3;
        r8 = r12;
        r12 = r28;
    L_0x02dd:
        if (r21 != 0) goto L_0x02e7;
    L_0x02df:
        r3 = r29.size();	 Catch:{ Exception -> 0x035d, all -> 0x01ae }
        r13 = 1;
        if (r3 > r13) goto L_0x02e7;
    L_0x02e6:
        r2 = 0;
    L_0x02e7:
        r3 = r29.size();	 Catch:{ Exception -> 0x035d, all -> 0x01ae }
        r0 = r36;
        if (r3 >= r0) goto L_0x030a;
    L_0x02ef:
        r14 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r3 = r29.size();	 Catch:{ Exception -> 0x035d, all -> 0x01ae }
        r0 = (double) r3;	 Catch:{ Exception -> 0x035d, all -> 0x01ae }
        r16 = r0;
        r14 = r14 * r16;
        r3 = r34.size();	 Catch:{ Exception -> 0x035d, all -> 0x01ae }
        r0 = (double) r3;	 Catch:{ Exception -> 0x035d, all -> 0x01ae }
        r16 = r0;
        r14 = r14 / r16;
        r16 = 4602678819172646912; // 0x3fe0000000000000 float:0.0 double:0.5;
        r3 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r3 >= 0) goto L_0x030a;
    L_0x0309:
        r2 = 0;
    L_0x030a:
        r3 = 1;
        r0 = r21;
        if (r0 != r3) goto L_0x0365;
    L_0x030f:
        r3 = 1;
        if (r2 != r3) goto L_0x0365;
    L_0x0312:
        r3 = r33;
        r4 = r3.if(r4, r6, r8, r10);	 Catch:{ Exception -> 0x035d, all -> 0x01ae }
        r6 = 4666723172467343360; // 0x40c3880000000000 float:0.0 double:10000.0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 <= 0) goto L_0x0365;
    L_0x0321:
        r2 = 0;
        r3 = r2;
        r4 = r12;
        r12 = r8;
    L_0x0325:
        if (r22 == 0) goto L_0x017b;
    L_0x0327:
        r22.close();	 Catch:{ Exception -> 0x032c }
        goto L_0x017b;
    L_0x032c:
        r2 = move-exception;
        goto L_0x017b;
    L_0x032f:
        r2 = 100;
        r0 = r28;
        if (r2 >= r0) goto L_0x0369;
    L_0x0335:
        r28 = 100;
        r2 = r3;
        r8 = r12;
        r12 = r28;
        goto L_0x02dd;
    L_0x033c:
        r2 = 0;
        goto L_0x0196;
    L_0x033f:
        r2 = move-exception;
        goto L_0x017b;
    L_0x0342:
        r3 = move-exception;
        goto L_0x01b4;
    L_0x0345:
        r3 = move-exception;
        r22 = r2;
        r2 = r3;
        goto L_0x01af;
    L_0x034b:
        r3 = move-exception;
        r3 = r23;
        r12 = r24;
        r10 = r26;
        r4 = r28;
        goto L_0x0176;
    L_0x0356:
        r2 = move-exception;
        r2 = r22;
        r4 = r28;
        goto L_0x0176;
    L_0x035d:
        r3 = move-exception;
        r3 = r2;
        r4 = r12;
        r12 = r8;
        r2 = r22;
        goto L_0x0176;
    L_0x0365:
        r3 = r2;
        r4 = r12;
        r12 = r8;
        goto L_0x0325;
    L_0x0369:
        r2 = r3;
        r8 = r12;
        r12 = r28;
        goto L_0x02dd;
    L_0x036f:
        r2 = r23;
        r8 = r24;
        r10 = r26;
        r12 = r28;
        goto L_0x02dd;
    L_0x0379:
        r3 = r23;
        r12 = r24;
        r10 = r26;
        r4 = r28;
        goto L_0x0325;
    L_0x0382:
        r21 = r2;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.a.if(java.util.LinkedHashMap, com.baidu.location.BDLocation, int):com.baidu.location.BDLocation");
    }

    private void if(BDLocation bDLocation, BDLocation bDLocation2, BDLocation bDLocation3, String str, Long l) {
        if (bDLocation != null && bDLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
            String format;
            String format2;
            if (bDLocation2 != null && bDLocation.getNetworkLocationType().equals("cl") && if(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude()) > w) {
                format = String.format(Locale.US, "UPDATE CL SET cl = 0 WHERE id = %d;", new Object[]{l});
                format2 = String.format(Locale.US, "INSERT OR REPLACE INTO CL VALUES (%d,\"%s\",%d);", new Object[]{l, str, Integer.valueOf(j)});
                try {
                    this.q.execSQL(format);
                    this.byte.execSQL(format2);
                } catch (Exception e) {
                }
            }
            if (bDLocation3 != null && bDLocation.getNetworkLocationType().equals("wf") && if(bDLocation3.getLatitude(), bDLocation3.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude()) > for) {
                format = String.format("UPDATE AP SET cl = 0 WHERE id In (%s);", new Object[]{this.r.toString()});
                format2 = String.format("INSERT OR REPLACE INTO AP VALUES %s;", new Object[]{this.goto.toString()});
                try {
                    this.q.execSQL(format);
                    this.byte.execSQL(format2);
                } catch (Exception e2) {
                }
            }
        }
    }

    private void if(String str, Long l, BDLocation bDLocation) {
        if (str != null) {
            if (bDLocation != null) {
                try {
                    this.q.execSQL(String.format(Locale.US, "UPDATE CL SET frequency=frequency+1 WHERE id = %d;", new Object[]{l}));
                } catch (Exception e) {
                }
            } else {
                String format = String.format(Locale.US, "INSERT OR IGNORE INTO CL VALUES (%d,\"%s\",0);", new Object[]{l, str});
                String format2 = String.format(Locale.US, "UPDATE CL SET frequency=frequency+1 WHERE id = %d;", new Object[]{l});
                try {
                    this.byte.execSQL(format);
                    this.byte.execSQL(format2);
                } catch (Exception e2) {
                }
            }
            if (this.m) {
                try {
                    this.byte.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO CL VALUES (%d,\"%s\",%d);", new Object[]{l, str, Integer.valueOf(j)}));
                } catch (Exception e3) {
                }
            }
        }
    }

    private void if(String str, String str2, String str3) {
        this.void.new(str, str2, str3);
    }

    private void if(LinkedHashMap linkedHashMap) {
        if (linkedHashMap != null && linkedHashMap.size() > 0) {
            String str;
            this.r = new StringBuffer();
            this.goto = new StringBuffer();
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            int i = 1;
            int i2 = 1;
            for (Long l : this.long.keySet()) {
                try {
                    int i3;
                    int i4;
                    if (this.t.contains(l)) {
                        if (i2 != 0) {
                            i2 = 0;
                        } else {
                            this.r.append(',');
                            this.goto.append(',');
                        }
                        this.r.append(l);
                        this.goto.append('(').append(l).append(',').append('\"').append((String) this.do.get(l)).append('\"').append(',').append(j).append(')');
                        i3 = i;
                        i4 = i2;
                    } else {
                        str = (String) this.do.get(l);
                        if (i != 0) {
                            i = 0;
                        } else {
                            stringBuffer.append(',');
                            stringBuffer2.append(',');
                        }
                        stringBuffer.append(l);
                        stringBuffer2.append('(').append(l).append(',').append('\"').append(str).append('\"').append(",0)");
                        i3 = i;
                        i4 = i2;
                    }
                    i = i3;
                    i2 = i4;
                } catch (Exception e) {
                    i = i;
                    i2 = i2;
                }
            }
            try {
                this.q.execSQL(String.format(Locale.US, "UPDATE AP SET frequency=frequency+1 WHERE id IN(%s)", new Object[]{this.r.toString()}));
            } catch (Exception e2) {
            }
            if (this.b.length() > 0) {
                if (stringBuffer2.length() > 0) {
                    stringBuffer2.append(",");
                }
                stringBuffer2.append(this.b);
            }
            String format = String.format("INSERT OR IGNORE INTO AP VALUES %s;", new Object[]{stringBuffer2.toString()});
            str = String.format("UPDATE AP SET frequency=frequency+1 WHERE id in (%s);", new Object[]{stringBuffer.toString()});
            try {
                if (stringBuffer2.length() > 0) {
                    this.byte.execSQL(format);
                }
                if (stringBuffer.length() > 0) {
                    this.byte.execSQL(str);
                }
            } catch (Exception e3) {
            }
        }
    }

    private void if(String[] strArr) {
        this.o.new().int(strArr);
    }

    void do() {
        if (this.q != null) {
            try {
                this.q.close();
            } catch (Exception e) {
            }
        }
        if (this.byte != null) {
            try {
                this.byte.close();
            } catch (Exception e2) {
            }
        }
    }

    void for() {
        this.x.aH();
    }

    Cursor if(a aVar) {
        BDLocation bDLocation;
        BDLocation bDLocation2 = new BDLocation();
        bDLocation2.setLocType(67);
        int i = 0;
        if (aVar.new) {
            double[] dArr;
            List list;
            String str = aVar.for;
            LinkedHashMap linkedHashMap = aVar.if;
            int i2 = aVar.do;
            BDLocation bDLocation3 = aVar.try;
            BDLocation bDLocation4 = null;
            Long valueOf = Long.valueOf(Long.MIN_VALUE);
            if (!(str == null || this.q == null)) {
                valueOf = Jni.I(str);
                if (valueOf != null) {
                    bDLocation4 = if(valueOf);
                }
            }
            BDLocation bDLocation5 = null;
            if (!(linkedHashMap == null || linkedHashMap.size() <= 0 || this.q == null)) {
                this.long.clear();
                this.t.clear();
                this.do.clear();
                bDLocation5 = if(linkedHashMap, bDLocation4, i2);
            }
            Double d = null;
            Double d2 = null;
            Double d3 = null;
            Double d4 = null;
            if (bDLocation4 != null) {
                d = Double.valueOf(bDLocation4.getLongitude());
                d2 = Double.valueOf(bDLocation4.getLatitude());
                dArr = Jni.if(bDLocation4.getLongitude(), bDLocation4.getLatitude(), BDLocation.BDLOCATION_BD09LL_TO_GCJ02);
                bDLocation4.setCoorType("gcj");
                bDLocation4.setLatitude(dArr[1]);
                bDLocation4.setLongitude(dArr[0]);
                bDLocation4.setNetworkLocationType("cl");
            }
            if (bDLocation5 != null) {
                d3 = Double.valueOf(bDLocation5.getLongitude());
                d4 = Double.valueOf(bDLocation5.getLatitude());
                dArr = Jni.if(bDLocation5.getLongitude(), bDLocation5.getLatitude(), BDLocation.BDLOCATION_BD09LL_TO_GCJ02);
                bDLocation5.setCoorType("gcj");
                bDLocation5.setLatitude(dArr[1]);
                bDLocation5.setLongitude(dArr[0]);
                bDLocation5.setNetworkLocationType("wf");
            }
            if (bDLocation4 != null && bDLocation5 == null) {
                i = 1;
            } else if (bDLocation4 == null && bDLocation5 != null) {
                i = 2;
            } else if (!(bDLocation4 == null || bDLocation5 == null)) {
                i = 4;
            }
            Object obj = aVar.do > 0 ? 1 : null;
            Object obj2 = (linkedHashMap == null || linkedHashMap.size() <= 0) ? 1 : null;
            if (obj != null) {
                if (bDLocation5 != null) {
                    d2 = d3;
                    bDLocation = bDLocation5;
                } else {
                    if (!(obj2 == null || bDLocation4 == null)) {
                        d4 = d2;
                        bDLocation = bDLocation4;
                        d2 = d;
                    }
                    d4 = null;
                    d2 = null;
                    bDLocation = bDLocation2;
                }
            } else if (bDLocation5 != null) {
                d2 = d3;
                bDLocation = bDLocation5;
            } else {
                if (bDLocation4 != null) {
                    d4 = d2;
                    bDLocation = bDLocation4;
                    d2 = d;
                }
                d4 = null;
                d2 = null;
                bDLocation = bDLocation2;
            }
            if (aVar.byte && this.o.new().B() && d4 != null && d2 != null) {
                bDLocation.setAddr(this.o.l().do(d2.doubleValue(), d4.doubleValue()));
            }
            if (obj != null && aVar.byte && bDLocation.getAddrStr() == null) {
                d4 = null;
                d2 = null;
                i = 0;
                bDLocation = bDLocation2;
            }
            if ((!aVar.int && !aVar.char) || d4 == null || d2 == null) {
                list = null;
            } else {
                List list2 = this.o.l().if(d2.doubleValue(), d4.doubleValue());
                if (aVar.int) {
                    bDLocation.setPoiList(list2);
                }
                list = list2;
            }
            if (obj == null || !aVar.int || (list != null && list.size() > 0)) {
                i2 = i;
            } else {
                i2 = 0;
                bDLocation = bDLocation2;
            }
            String str2 = null;
            if (aVar.char && list != null && list.size() > 0) {
                str2 = String.format(Locale.CHINA, "在%s附近", new Object[]{((Poi) list.get(0)).getName()});
                bDLocation.setLocationDescribe(str2);
            }
            if (obj != null && aVar.char && r4 == null) {
                i2 = 0;
                bDLocation = bDLocation2;
            }
            StringBuffer stringBuffer = new StringBuffer();
            String str3 = null;
            if (aVar.else != null) {
                stringBuffer.append(aVar.else);
                stringBuffer.append(c.if(bDLocation5, bDLocation4, aVar));
                stringBuffer.append(c.if(bDLocation, i2));
                str3 = stringBuffer.toString();
            }
            new a(this, str, valueOf, bDLocation4, bDLocation5, bDLocation3, str3, linkedHashMap) {
                final /* synthetic */ a a;
            }.start();
        } else {
            bDLocation = bDLocation2;
        }
        return c.if(bDLocation);
    }

    SQLiteDatabase if() {
        return this.byte;
    }
}
