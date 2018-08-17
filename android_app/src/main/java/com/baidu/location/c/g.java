package com.baidu.location.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.Jni;
import com.baidu.location.b.b;
import com.baidu.location.b.m;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class g implements b {
    private static final String ba = String.format(Locale.US, "SELECT * FROM LOG ORDER BY timestamp DESC LIMIT %d;", new Object[]{Integer.valueOf(3)});
    private static final String bb = String.format(Locale.US, "DELETE FROM LOG WHERE timestamp NOT IN (SELECT timestamp FROM LOG ORDER BY timestamp DESC LIMIT %d);", new Object[]{Integer.valueOf(3000)});
    private static final int bc = 3;
    private static final int bd = 3000;
    private final SQLiteDatabase a8;
    private String a9 = null;
    private final a be;

    private class a extends m {
        private static final long ei = 86400000;
        private static final String ej = "ofbh";
        private static final String el = "qt";
        private static final int eo = -1;
        private static final int eq = 2;
        private static final String es = "req";
        private static final int et = 161;
        private static final String eu = "error";
        private int eg;
        private String eh;
        private g ek;
        final /* synthetic */ g em;
        private boolean en;
        private long ep;
        private boolean er;

        class g_a_1 extends Thread {
            final /* synthetic */ a a;

            g_a_1(a aVar) {
                this.a = aVar;
            }

            public void run() {
                super.run();
                this.a.ek.if(this.a.er);
            }
        }

        a(g gVar, g gVar2) {
            this.em = gVar;
            this.ek = gVar2;
            this.eh = null;
            this.en = false;
            this.er = false;
            this.c7 = new ArrayList();
            this.eg = 0;
            this.ep = -1;
        }

        private void aK() {
            if (!this.en) {
                this.eh = this.ek.J();
                if (this.ep != -1 && this.ep + 86400000 <= System.currentTimeMillis()) {
                    this.eg = 0;
                    this.ep = -1;
                }
                if (this.eh != null && this.eg < 2) {
                    this.en = true;
                    ao();
                }
            }
        }

        public void au() {
            this.c7.clear();
            this.c7.add(new BasicNameValuePair(el, ej));
            this.c7.add(new BasicNameValuePair(es, this.eh));
            this.c5 = d.ak;
        }

        public void int(boolean z) {
            this.er = false;
            if (z && this.c6 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(EntityUtils.toString(this.c6, "utf-8"));
                    if (jSONObject != null && jSONObject.has("error") && jSONObject.getInt("error") == 161) {
                        this.er = true;
                    }
                } catch (ParseException e) {
                } catch (JSONException e2) {
                } catch (IOException e3) {
                }
            }
            this.en = false;
            if (!this.er) {
                this.eg++;
                this.ep = System.currentTimeMillis();
            }
            new g_a_1(this).start();
        }
    }

    g(SQLiteDatabase sQLiteDatabase) {
        this.a8 = sQLiteDatabase;
        this.be = new a(this, this);
        if (this.a8 != null && this.a8.isOpen()) {
            this.a8.execSQL("CREATE TABLE IF NOT EXISTS LOG(timestamp LONG PRIMARY KEY, log VARCHAR(4000))");
        }
    }

    private String J() {
        Throwable th;
        String str = null;
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        Cursor rawQuery;
        try {
            rawQuery = this.a8.rawQuery(ba, null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        StringBuffer stringBuffer = new StringBuffer();
                        rawQuery.moveToFirst();
                        while (!rawQuery.isAfterLast()) {
                            jSONArray.put(rawQuery.getString(1));
                            if (stringBuffer.length() != 0) {
                                stringBuffer.append(",");
                            }
                            stringBuffer.append(rawQuery.getLong(0));
                            rawQuery.moveToNext();
                        }
                        try {
                            jSONObject.put("ofloc", jSONArray);
                            str = jSONObject.toString();
                        } catch (JSONException e) {
                        }
                        this.a9 = stringBuffer.toString();
                    }
                } catch (Exception e2) {
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Exception e3) {
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            }
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Exception e5) {
                }
            }
        } catch (Exception e6) {
            Object obj = str;
            if (rawQuery != null) {
                rawQuery.close();
            }
            return str;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            rawQuery = str;
            th = th4;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return str;
    }

    private void if(boolean z) {
        if (z && this.a9 != null) {
            String format = String.format("DELETE FROM LOG WHERE timestamp in (%s);", new Object[]{this.a9});
            if (this.a9.length() > 0) {
                try {
                    this.a8.execSQL(format);
                } catch (Exception e) {
                }
            }
        }
        this.a9 = null;
    }

    void I() {
        this.be.aK();
    }

    void byte(String str) {
        String G = Jni.G(str);
        try {
            this.a8.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO LOG VALUES (%d,\"%s\");", new Object[]{Long.valueOf(System.currentTimeMillis()), G}));
            this.a8.execSQL(bb);
        } catch (Exception e) {
        }
    }
}
