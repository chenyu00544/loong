package com.baidu.location.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.Jni;
import com.baidu.location.b.b;
import com.baidu.location.b.c;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.b.m;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

final class e implements b {
    private static final String au = "ofl.config";
    private final d aA;
    private boolean aB = true;
    private boolean aC;
    private boolean aD;
    private boolean aE;
    private boolean aF;
    private String[] aG;
    private double aH;
    private long aI = 8000;
    private int aJ;
    private long aK = com.baidu.location.h.e.kg;
    private double aL;
    private int aM;
    private int aN;
    private boolean aO;
    private final a aP;
    private double aQ;
    private final SQLiteDatabase aR;
    private long aS = com.baidu.location.h.e.kg;
    private int aT;
    private long av = com.baidu.location.h.e.kg;
    private double aw;
    private boolean ax;
    private double ay;
    private long az = com.baidu.location.h.e.kg;

    private final class a extends m implements f {
        private static final String d0 = "0";
        private static final String d1 = "wn";
        private static final String d2 = "addrup";
        private static final String d3 = "poiup";
        private static final String d4 = "bklist";
        private static final String d5 = "2";
        private static final String d6 = "para";
        private static final String d7 = "fl";
        private static final String d8 = "rgcgp";
        private static final String dH = "3";
        private static final String dI = "t";
        private static final String dK = "oflp";
        private static final String dL = "rgcon";
        private static final String dO = "req";
        private static final String dP = "conf";
        private static final int dQ = -1;
        private static final String dR = "1";
        private static final String dS = "ol";
        private static final String dT = "oc";
        private static final String dV = "4";
        private static final long dW = 86400000;
        private static final String dX = "ver";
        private static final int dY = 2;
        private static final String dZ = "cplist";
        private static final String eb = "on";
        private static final String ec = "onlt";
        private static final String ed = "minapn";
        private static final String ee = "qt";
        private static final String ef = "ofl";
        private final String d9;
        private boolean dJ;
        private long dM;
        private int dN;
        private long dU;
        final /* synthetic */ e ea;

        private a(e eVar) {
            this.ea = eVar;
            this.dN = 0;
            this.dJ = false;
            this.dU = -1;
            this.dM = -1;
            this.c7 = new ArrayList();
            this.d9 = Jni.G(String.format(Locale.US, "&ver=%s&cuid=%s&prod=%s:%s&sdk=%.2f", new Object[]{"1", c.N().bm, c.bj, c.bn, Float.valueOf(f.bi)}));
        }

        private boolean aI() {
            boolean z = true;
            if (this.dN >= 2) {
                if (this.dU + 86400000 < System.currentTimeMillis()) {
                    this.dN = 0;
                    this.dU = -1;
                } else {
                    z = false;
                }
            }
            return !z ? z : z;
        }

        private void aJ() {
            if (!this.dJ) {
                boolean z = false;
                try {
                    File file = new File(this.ea.aA.d(), e.au);
                    if (this.dM == -1 && file.exists()) {
                        JSONObject jSONObject;
                        Scanner scanner = new Scanner(file);
                        String next = scanner.next();
                        scanner.close();
                        JSONObject jSONObject2 = new JSONObject(next);
                        this.ea.aO = jSONObject2.getBoolean(dS);
                        this.ea.ax = jSONObject2.getBoolean(d7);
                        this.ea.aE = jSONObject2.getBoolean(eb);
                        this.ea.aD = jSONObject2.getBoolean(d1);
                        this.ea.aF = jSONObject2.getBoolean(dT);
                        this.dM = jSONObject2.getLong("t");
                        if (jSONObject2.has(dZ)) {
                            this.ea.aG = jSONObject2.getString(dZ).split(";");
                        }
                        if (jSONObject2.has(d8)) {
                            this.ea.aN = jSONObject2.getInt(d8);
                        }
                        if (jSONObject2.has(dL)) {
                            this.ea.aC = jSONObject2.getBoolean(dL);
                        }
                        if (jSONObject2.has(d2)) {
                            this.ea.aM = jSONObject2.getInt(d2);
                        }
                        if (jSONObject2.has(d3)) {
                            this.ea.aJ = jSONObject2.getInt(d3);
                        }
                        if (jSONObject2.has(dK)) {
                            jSONObject = jSONObject2.getJSONObject(dK);
                            if (jSONObject.has("0")) {
                                this.ea.aw = jSONObject.getDouble("0");
                            }
                            if (jSONObject.has("1")) {
                                this.ea.aL = jSONObject.getDouble("1");
                            }
                            if (jSONObject.has("2")) {
                                this.ea.aQ = jSONObject.getDouble("2");
                            }
                            if (jSONObject.has("3")) {
                                this.ea.ay = jSONObject.getDouble("3");
                            }
                            if (jSONObject.has("4")) {
                                this.ea.aH = jSONObject.getDouble("4");
                            }
                        }
                        if (jSONObject2.has(ec)) {
                            jSONObject = jSONObject2.getJSONObject(ec);
                            if (jSONObject.has("0")) {
                                this.ea.av = jSONObject.getLong("0");
                            }
                            if (jSONObject.has("1")) {
                                this.ea.aK = jSONObject.getLong("1");
                            }
                            if (jSONObject.has("2")) {
                                this.ea.aI = jSONObject.getLong("2");
                            }
                            if (jSONObject.has("3")) {
                                this.ea.aS = jSONObject.getLong("3");
                            }
                            if (jSONObject.has("4")) {
                                this.ea.az = jSONObject.getLong("4");
                            }
                        }
                        if (jSONObject2.has(ed)) {
                            this.ea.aT = jSONObject2.getInt(ed);
                        }
                    }
                    if (this.dM == -1 && file.exists()) {
                    }
                    if (this.dM != -1 && this.dM + 86400000 <= System.currentTimeMillis()) {
                        z = true;
                    }
                } catch (Exception e) {
                }
                if ((this.dM == -1 || r0) && aI() && k.for(this.ea.aA.char())) {
                    this.dJ = true;
                    ao();
                }
            }
        }

        public void au() {
            this.c7.clear();
            this.c7.add(new BasicNameValuePair(ee, dP));
            this.c7.add(new BasicNameValuePair(dO, this.d9));
            this.c5 = d.ak;
        }

        public void int(boolean z) {
            if (!z || this.c6 == null) {
                this.dN++;
                this.dU = System.currentTimeMillis();
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(EntityUtils.toString(this.c6, "utf-8"));
                    Object obj = "1";
                    long j = 0;
                    if (jSONObject.has(ef)) {
                        j = jSONObject.getLong(ef);
                    }
                    if (jSONObject.has("ver")) {
                        obj = jSONObject.getString("ver");
                    }
                    if ((j & 1) == 1) {
                        this.ea.aO = true;
                    }
                    if ((j & 2) == 2) {
                        this.ea.ax = true;
                    }
                    if ((j & 4) == 4) {
                        this.ea.aE = true;
                    }
                    if ((j & 8) == 8) {
                        this.ea.aD = true;
                    }
                    if ((16 & j) == 16) {
                        this.ea.aF = true;
                    }
                    if ((j & 32) == 32) {
                        this.ea.aC = true;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    if (jSONObject.has(dZ)) {
                        this.ea.aG = jSONObject.getString(dZ).split(";");
                        jSONObject2.put(dZ, jSONObject.getString(dZ));
                    }
                    if (jSONObject.has(d4)) {
                        this.ea.int(jSONObject.getString(d4).split(";"));
                    }
                    if (jSONObject.has(d6)) {
                        JSONObject jSONObject3;
                        jSONObject = jSONObject.getJSONObject(d6);
                        if (jSONObject.has(d8)) {
                            this.ea.aN = jSONObject.getInt(d8);
                        }
                        if (jSONObject.has(d2)) {
                            this.ea.aM = jSONObject.getInt(d2);
                        }
                        if (jSONObject.has(d3)) {
                            this.ea.aJ = jSONObject.getInt(d3);
                        }
                        if (jSONObject.has(dK)) {
                            jSONObject3 = jSONObject.getJSONObject(dK);
                            if (jSONObject3.has("0")) {
                                this.ea.aw = jSONObject3.getDouble("0");
                            }
                            if (jSONObject3.has("1")) {
                                this.ea.aL = jSONObject3.getDouble("1");
                            }
                            if (jSONObject3.has("2")) {
                                this.ea.aQ = jSONObject3.getDouble("2");
                            }
                            if (jSONObject3.has("3")) {
                                this.ea.ay = jSONObject3.getDouble("3");
                            }
                            if (jSONObject3.has("4")) {
                                this.ea.aH = jSONObject3.getDouble("4");
                            }
                        }
                        if (jSONObject.has(ec)) {
                            jSONObject3 = jSONObject.getJSONObject(ec);
                            if (jSONObject3.has("0")) {
                                this.ea.av = jSONObject3.getLong("0");
                            }
                            if (jSONObject3.has("1")) {
                                this.ea.aK = jSONObject3.getLong("1");
                            }
                            if (jSONObject3.has("2")) {
                                this.ea.aI = jSONObject3.getLong("2");
                            }
                            if (jSONObject3.has("3")) {
                                this.ea.aS = jSONObject3.getLong("3");
                            }
                            if (jSONObject3.has("4")) {
                                this.ea.az = jSONObject3.getLong("4");
                            }
                        }
                        if (jSONObject.has(ed)) {
                            this.ea.aT = jSONObject.getInt(ed);
                        }
                    }
                    jSONObject2.put(dS, this.ea.aO);
                    jSONObject2.put(d7, this.ea.ax);
                    jSONObject2.put(eb, this.ea.aE);
                    jSONObject2.put(d1, this.ea.aD);
                    jSONObject2.put(dT, this.ea.aF);
                    this.dM = System.currentTimeMillis();
                    jSONObject2.put("t", this.dM);
                    jSONObject2.put("ver", obj);
                    jSONObject2.put(dL, this.ea.aC);
                    jSONObject2.put(d8, this.ea.aN);
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("0", this.ea.aw);
                    jSONObject4.put("1", this.ea.aL);
                    jSONObject4.put("2", this.ea.aQ);
                    jSONObject4.put("3", this.ea.ay);
                    jSONObject4.put("4", this.ea.aH);
                    jSONObject2.put(dK, jSONObject4);
                    jSONObject4 = new JSONObject();
                    jSONObject4.put("0", this.ea.av);
                    jSONObject4.put("1", this.ea.aK);
                    jSONObject4.put("2", this.ea.aI);
                    jSONObject4.put("3", this.ea.aS);
                    jSONObject4.put("4", this.ea.az);
                    jSONObject2.put(ec, jSONObject4);
                    jSONObject2.put(d2, this.ea.aM);
                    jSONObject2.put(d3, this.ea.aJ);
                    jSONObject2.put(ed, this.ea.aT);
                    File file = new File(this.ea.aA.d(), e.au);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(jSONObject2.toString());
                    fileWriter.close();
                } catch (Exception e) {
                    this.dN++;
                    this.dU = System.currentTimeMillis();
                }
            }
            this.dJ = false;
        }
    }

    e(d dVar, SQLiteDatabase sQLiteDatabase) {
        this.aA = dVar;
        this.aO = false;
        this.ax = false;
        this.aE = false;
        this.aD = false;
        this.aF = false;
        this.aC = false;
        this.aN = 6;
        this.aJ = 30;
        this.aM = 30;
        this.aw = 0.0d;
        this.aL = 0.0d;
        this.aQ = 0.0d;
        this.ay = 0.0d;
        this.aH = 0.0d;
        this.aT = 8;
        this.aG = new String[0];
        this.aR = sQLiteDatabase;
        this.aP = new a();
        if (this.aR != null && this.aR.isOpen()) {
            this.aR.execSQL("CREATE TABLE IF NOT EXISTS BLACK (name VARCHAR(100) PRIMARY KEY);");
        }
        q();
    }

    double A() {
        return this.aw;
    }

    boolean B() {
        return this.aC;
    }

    boolean C() {
        return this.aE;
    }

    int D() {
        return this.aJ;
    }

    double E() {
        return this.ay;
    }

    void int(String[] strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append("(\"");
            stringBuffer.append(strArr[i]);
            stringBuffer.append("\")");
        }
        if (this.aR != null && this.aR.isOpen() && stringBuffer.length() > 0) {
            try {
                this.aR.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO BLACK VALUES %s;", new Object[]{stringBuffer.toString()}));
            } catch (Exception e) {
            }
        }
    }

    boolean new(String str) {
        Cursor cursor = null;
        boolean z = false;
        try {
            cursor = this.aR.rawQuery(String.format(Locale.US, "SELECT * FROM BLACK WHERE NAME IN (\"%s\");", new Object[]{str}), null);
            if (cursor.getCount() > 0) {
                z = true;
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e4) {
                }
            }
        }
        return z ? z : z;
    }

    int o() {
        return this.aN;
    }

    double p() {
        return this.aQ;
    }

    void q() {
        this.aP.aJ();
    }

    double r() {
        return this.aL;
    }

    int s() {
        return this.aT;
    }

    boolean t() {
        return this.aB;
    }

    long try(String str) {
        return str.equals(com.baidu.location.h.c.h) ? this.aI : str.equals(com.baidu.location.h.c.c) ? this.aS : str.equals(com.baidu.location.h.c.if) ? this.az : str.equals(com.baidu.location.h.c.do) ? this.aK : str.equals("unknown") ? this.av : com.baidu.location.h.e.kg;
    }

    int u() {
        return this.aM;
    }

    String[] v() {
        return this.aG;
    }

    boolean w() {
        return this.aO;
    }

    boolean x() {
        return this.ax;
    }

    boolean y() {
        return this.aD;
    }

    double z() {
        return this.aH;
    }
}
