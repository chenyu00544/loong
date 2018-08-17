package org.android.agoo.message;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UTMini;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.MsgDO;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Taobao */
public class MessageService {
    public static final String MSG_ACCS_NOTIFY_CLICK = "8";
    public static final String MSG_ACCS_NOTIFY_DISMISS = "9";
    public static final String MSG_ACCS_READY_REPORT = "4";
    public static final String MSG_DB_COMPLETE = "100";
    public static final String MSG_DB_NOTIFY_CLICK = "2";
    public static final String MSG_DB_NOTIFY_DISMISS = "3";
    public static final String MSG_DB_NOTIFY_REACHED = "1";
    public static final String MSG_DB_READY_REPORT = "0";
    private static Context a = null;
    private static Map<String, Integer> c = null;
    private volatile SQLiteOpenHelper b = null;

    /* compiled from: Taobao */
    private static class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, "message_accs_db", null, 3);
        }

        public SQLiteDatabase getWritableDatabase() {
            if (com.taobao.accs.utl.a.a(super.getWritableDatabase().getPath(), 102400)) {
                return super.getWritableDatabase();
            }
            return null;
        }

        private String a() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("create table accs_message");
            stringBuffer.append("(");
            stringBuffer.append("id text UNIQUE not null,");
            stringBuffer.append("state text,");
            stringBuffer.append("message text,");
            stringBuffer.append("create_time date");
            stringBuffer.append(");");
            return stringBuffer.toString();
        }

        private String b() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("create table message");
            stringBuffer.append("(");
            stringBuffer.append("id text UNIQUE not null,");
            stringBuffer.append("state integer,");
            stringBuffer.append("body_code integer,");
            stringBuffer.append("report long,");
            stringBuffer.append("target_time long,");
            stringBuffer.append("interval integer,");
            stringBuffer.append("type text,");
            stringBuffer.append("message text,");
            stringBuffer.append("notify integer,");
            stringBuffer.append("create_time date");
            stringBuffer.append(");");
            return stringBuffer.toString();
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.execSQL(b());
                    sQLiteDatabase.execSQL("CREATE INDEX id_index ON message(id)");
                    sQLiteDatabase.execSQL("CREATE INDEX body_code_index ON message(body_code)");
                    sQLiteDatabase.execSQL(a());
                } catch (Throwable th) {
                    ALog.e("MessageService", "messagedbhelper create", th, new Object[0]);
                }
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.execSQL("delete from message where create_time< date('now','-7 day') and state=1");
                } catch (Throwable th) {
                    ALog.e("MessageService", "MessageService onUpgrade is error", th, new Object[0]);
                    return;
                }
            }
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accs_message");
                sQLiteDatabase.execSQL(a());
            } catch (Throwable th2) {
                ALog.e("MessageService", "MessageService onUpgrade is error", th2, new Object[0]);
            }
        }
    }

    public void a(Context context) {
        c = new HashMap();
        a = context;
        this.b = new a(context);
    }

    public void a(String str, String str2) {
        Throwable th;
        Throwable th2;
        SQLiteDatabase sQLiteDatabase;
        if (ALog.isPrintLog(Level.I)) {
            ALog.i("MessageService", "updateAccsMessage sqlite3--->[" + str + ",state=" + str2 + "]", new Object[0]);
        }
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                sQLiteDatabase2 = this.b.getWritableDatabase();
                if (sQLiteDatabase2 != null) {
                    try {
                        if (TextUtils.equals(str2, "1")) {
                            sQLiteDatabase2.execSQL("UPDATE accs_message set state = ? where id = ? and state = ?", new Object[]{str2, str, "0"});
                        } else {
                            sQLiteDatabase2.execSQL("UPDATE accs_message set state = ? where id = ?", new Object[]{str2, str});
                        }
                        if (sQLiteDatabase2 != null) {
                            sQLiteDatabase2.close();
                        }
                    } catch (Throwable th3) {
                        th2 = th3;
                        sQLiteDatabase = sQLiteDatabase2;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        throw th2;
                    }
                } else if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
            } else if (sQLiteDatabase2 != null) {
                sQLiteDatabase2.close();
            }
        } catch (Throwable th4) {
            th2 = th4;
            sQLiteDatabase = sQLiteDatabase2;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th2;
        }
    }

    public void a(String str, String str2, String str3) {
        Throwable th;
        Throwable th2;
        Cursor cursor;
        if (ALog.isPrintLog(Level.I)) {
            ALog.i("MessageService", "addAccsMessage sqlite3--->[" + str + ",message=" + str2 + ",state=" + str3 + "]", new Object[0]);
        }
        Cursor cursor2 = null;
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
                if (writableDatabase != null) {
                    cursor2 = writableDatabase.rawQuery("select count(1) from accs_message where id = ?", new String[]{str});
                    if (cursor2 != null) {
                        try {
                            if (cursor2.moveToFirst() && cursor2.getInt(0) > 0) {
                                cursor2.close();
                                if (cursor2 != null) {
                                    cursor2.close();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable th3) {
                            th2 = th3;
                            cursor = cursor2;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th2;
                        }
                    }
                    writableDatabase.execSQL("INSERT INTO accs_message VALUES(?,?,?,date('now'))", new Object[]{str, str3, str2});
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                } else if (cursor2 != null) {
                    cursor2.close();
                }
            } else if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Throwable th4) {
            th2 = th4;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }

    private String a(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                stringBuffer.append(stackTraceElement.toString() + "\n");
            }
        }
        return stringBuffer.toString();
    }

    public void a(String str, String str2, String str3, int i) {
        a(str, str2, str3, 1, -1, -1, i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r8, java.lang.String r9, java.lang.String r10, int r11, long r12, int r14, int r15) {
        /*
        r7 = this;
        r0 = "MessageService";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "add sqlite3--->[";
        r1 = r1.append(r2);
        r1 = r1.append(r8);
        r2 = "]";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = 0;
        r2 = new java.lang.Object[r2];
        com.taobao.accs.utl.ALog.d(r0, r1, r2);
        r2 = 0;
        r1 = 0;
        r0 = android.text.TextUtils.isEmpty(r9);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        if (r0 == 0) goto L_0x0086;
    L_0x0029:
        r9 = "";
        r0 = -1;
    L_0x002c:
        r3 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        if (r3 == 0) goto L_0x0034;
    L_0x0032:
        r10 = "";
    L_0x0034:
        r3 = c;	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r3 = r3.containsKey(r8);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        if (r3 != 0) goto L_0x0078;
    L_0x003c:
        r3 = c;	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r4 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r3.put(r8, r4);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r3 = com.taobao.accs.utl.ALog.Level.I;	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r3 = com.taobao.accs.utl.ALog.isPrintLog(r3);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        if (r3 == 0) goto L_0x0078;
    L_0x004d:
        r3 = "MessageService";
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r4.<init>();	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r5 = "addMessage,messageId=";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r4 = r4.append(r8);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r5 = ",messageStores＝";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r5 = c;	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        com.taobao.accs.utl.ALog.i(r3, r4, r5);	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
    L_0x0078:
        r3 = r7.b;	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        r1 = r3.getWritableDatabase();	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        if (r1 != 0) goto L_0x00ce;
    L_0x0080:
        if (r1 == 0) goto L_0x0085;
    L_0x0082:
        r1.close();	 Catch:{ Throwable -> 0x008b }
    L_0x0085:
        return;
    L_0x0086:
        r0 = r9.hashCode();	 Catch:{ Throwable -> 0x0156, all -> 0x01e5 }
        goto L_0x002c;
    L_0x008b:
        r5 = move-exception;
        r0 = com.taobao.accs.utl.ALog.Level.E;
        r0 = com.taobao.accs.utl.ALog.isPrintLog(r0);
        if (r0 == 0) goto L_0x00b5;
    L_0x0094:
        r0 = "MessageService";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "addMessage,db.close(),error,e--->[";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r2 = "]";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = 0;
        r2 = new java.lang.Object[r2];
        com.taobao.accs.utl.ALog.e(r0, r1, r2);
    L_0x00b5:
        r0 = com.taobao.accs.utl.UTMini.getInstance();
        r1 = 66002; // 0x101d2 float:9.2489E-41 double:3.26093E-319;
        r2 = "accs.add_agoo_message";
        r3 = a;
        r3 = com.taobao.accs.utl.a.b(r3);
        r4 = "addMessageDBcloseFailed";
        r5 = r5.toString();
        r0.commitEvent(r1, r2, r3, r4, r5);
        goto L_0x0085;
    L_0x00ce:
        r3 = "INSERT INTO message VALUES(?,?,?,?,?,?,?,?,?,date('now'))";
        r4 = 9;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r5 = 0;
        r4[r5] = r8;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r5 = 1;
        r6 = java.lang.Integer.valueOf(r11);	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r5 = 2;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r4[r5] = r0;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r0 = 3;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r4[r0] = r2;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r0 = 4;
        r2 = java.lang.Long.valueOf(r12);	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r4[r0] = r2;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r0 = 5;
        r2 = java.lang.Integer.valueOf(r14);	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r4[r0] = r2;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r0 = 6;
        r4[r0] = r10;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r0 = 7;
        r4[r0] = r9;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r0 = 8;
        r2 = java.lang.Integer.valueOf(r15);	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r4[r0] = r2;	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        r1.execSQL(r3, r4);	 Catch:{ Throwable -> 0x0234, all -> 0x01e5 }
        if (r1 == 0) goto L_0x0085;
    L_0x010d:
        r1.close();	 Catch:{ Throwable -> 0x0112 }
        goto L_0x0085;
    L_0x0112:
        r5 = move-exception;
        r0 = com.taobao.accs.utl.ALog.Level.E;
        r0 = com.taobao.accs.utl.ALog.isPrintLog(r0);
        if (r0 == 0) goto L_0x013c;
    L_0x011b:
        r0 = "MessageService";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "addMessage,db.close(),error,e--->[";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r2 = "]";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = 0;
        r2 = new java.lang.Object[r2];
        com.taobao.accs.utl.ALog.e(r0, r1, r2);
    L_0x013c:
        r0 = com.taobao.accs.utl.UTMini.getInstance();
        r1 = 66002; // 0x101d2 float:9.2489E-41 double:3.26093E-319;
        r2 = "accs.add_agoo_message";
        r3 = a;
        r3 = com.taobao.accs.utl.a.b(r3);
        r4 = "addMessageDBcloseFailed";
        r5 = r5.toString();
        r0.commitEvent(r1, r2, r3, r4, r5);
        goto L_0x0085;
    L_0x0156:
        r0 = move-exception;
        r5 = r0;
        r6 = r1;
    L_0x0159:
        r0 = com.taobao.accs.utl.ALog.Level.E;	 Catch:{ all -> 0x0230 }
        r0 = com.taobao.accs.utl.ALog.isPrintLog(r0);	 Catch:{ all -> 0x0230 }
        if (r0 == 0) goto L_0x0182;
    L_0x0161:
        r0 = "MessageService";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0230 }
        r1.<init>();	 Catch:{ all -> 0x0230 }
        r2 = "addMessage error,e--->[";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0230 }
        r1 = r1.append(r5);	 Catch:{ all -> 0x0230 }
        r2 = "]";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0230 }
        r1 = r1.toString();	 Catch:{ all -> 0x0230 }
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0230 }
        com.taobao.accs.utl.ALog.e(r0, r1, r2);	 Catch:{ all -> 0x0230 }
    L_0x0182:
        r0 = com.taobao.accs.utl.UTMini.getInstance();	 Catch:{ all -> 0x0230 }
        r1 = 66002; // 0x101d2 float:9.2489E-41 double:3.26093E-319;
        r2 = "accs.add_agoo_message";
        r3 = a;	 Catch:{ all -> 0x0230 }
        r3 = com.taobao.accs.utl.a.b(r3);	 Catch:{ all -> 0x0230 }
        r4 = "addMessageFailed";
        r5 = r5.toString();	 Catch:{ all -> 0x0230 }
        r0.commitEvent(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0230 }
        if (r6 == 0) goto L_0x0085;
    L_0x019c:
        r6.close();	 Catch:{ Throwable -> 0x01a1 }
        goto L_0x0085;
    L_0x01a1:
        r5 = move-exception;
        r0 = com.taobao.accs.utl.ALog.Level.E;
        r0 = com.taobao.accs.utl.ALog.isPrintLog(r0);
        if (r0 == 0) goto L_0x01cb;
    L_0x01aa:
        r0 = "MessageService";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "addMessage,db.close(),error,e--->[";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r2 = "]";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = 0;
        r2 = new java.lang.Object[r2];
        com.taobao.accs.utl.ALog.e(r0, r1, r2);
    L_0x01cb:
        r0 = com.taobao.accs.utl.UTMini.getInstance();
        r1 = 66002; // 0x101d2 float:9.2489E-41 double:3.26093E-319;
        r2 = "accs.add_agoo_message";
        r3 = a;
        r3 = com.taobao.accs.utl.a.b(r3);
        r4 = "addMessageDBcloseFailed";
        r5 = r5.toString();
        r0.commitEvent(r1, r2, r3, r4, r5);
        goto L_0x0085;
    L_0x01e5:
        r0 = move-exception;
        r6 = r0;
    L_0x01e7:
        if (r1 == 0) goto L_0x01ec;
    L_0x01e9:
        r1.close();	 Catch:{ Throwable -> 0x01ed }
    L_0x01ec:
        throw r6;
    L_0x01ed:
        r5 = move-exception;
        r0 = com.taobao.accs.utl.ALog.Level.E;
        r0 = com.taobao.accs.utl.ALog.isPrintLog(r0);
        if (r0 == 0) goto L_0x0217;
    L_0x01f6:
        r0 = "MessageService";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "addMessage,db.close(),error,e--->[";
        r1 = r1.append(r2);
        r1 = r1.append(r5);
        r2 = "]";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = 0;
        r2 = new java.lang.Object[r2];
        com.taobao.accs.utl.ALog.e(r0, r1, r2);
    L_0x0217:
        r0 = com.taobao.accs.utl.UTMini.getInstance();
        r1 = 66002; // 0x101d2 float:9.2489E-41 double:3.26093E-319;
        r2 = "accs.add_agoo_message";
        r3 = a;
        r3 = com.taobao.accs.utl.a.b(r3);
        r4 = "addMessageDBcloseFailed";
        r5 = r5.toString();
        r0.commitEvent(r1, r2, r3, r4, r5);
        goto L_0x01ec;
    L_0x0230:
        r0 = move-exception;
        r1 = r6;
        r6 = r0;
        goto L_0x01e7;
    L_0x0234:
        r0 = move-exception;
        r5 = r0;
        r6 = r1;
        goto L_0x0159;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.agoo.message.MessageService.a(java.lang.String, java.lang.String, java.lang.String, int, long, int, int):void");
    }

    public void a() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = this.b.getWritableDatabase();
            if (sQLiteDatabase != null) {
                sQLiteDatabase.execSQL("delete from message where create_time< date('now','-7 day') and state=1");
                sQLiteDatabase.execSQL("delete from accs_message where create_time< date('now','-1 day') ");
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.close();
                    } catch (Throwable th) {
                    }
                }
            } else if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.close();
                } catch (Throwable th2) {
                }
            }
        } catch (Throwable th3) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<org.android.agoo.common.MsgDO> b() {
        /*
        r15 = this;
        r1 = 0;
        r13 = 0;
        r0 = 0;
        r2 = r15.b;	 Catch:{ Throwable -> 0x01d7, all -> 0x019b }
        r3 = r2.getReadableDatabase();	 Catch:{ Throwable -> 0x01d7, all -> 0x019b }
        if (r3 != 0) goto L_0x003b;
    L_0x000b:
        if (r1 == 0) goto L_0x0010;
    L_0x000d:
        r0.close();	 Catch:{ Throwable -> 0x0017 }
    L_0x0010:
        if (r3 == 0) goto L_0x0015;
    L_0x0012:
        r3.close();	 Catch:{ Throwable -> 0x0017 }
    L_0x0015:
        r0 = r1;
    L_0x0016:
        return r0;
    L_0x0017:
        r0 = move-exception;
        r2 = com.taobao.accs.utl.ALog.Level.E;
        r2 = com.taobao.accs.utl.ALog.isPrintLog(r2);
        if (r2 == 0) goto L_0x0015;
    L_0x0020:
        r2 = "MessageService";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "getUnReportMsg close cursor or db, e: ";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        r3 = new java.lang.Object[r13];
        com.taobao.accs.utl.ALog.e(r2, r0, r3);
        goto L_0x0015;
    L_0x003b:
        r0 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x01df, all -> 0x01cd }
        r0.<init>();	 Catch:{ Throwable -> 0x01df, all -> 0x01cd }
        r2 = "select * from accs_message where state = ? or state = ? or state = ?";
        r4 = 3;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x01e7, all -> 0x01cd }
        r5 = 0;
        r6 = "0";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x01e7, all -> 0x01cd }
        r5 = 1;
        r6 = "2";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x01e7, all -> 0x01cd }
        r5 = 2;
        r6 = "3";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x01e7, all -> 0x01cd }
        r2 = r3.rawQuery(r2, r4);	 Catch:{ Throwable -> 0x01e7, all -> 0x01cd }
        if (r2 == 0) goto L_0x016a;
    L_0x005a:
        r4 = "id";
        r5 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r4 = "state";
        r6 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r4 = "message";
        r7 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r4 = "create_time";
        r8 = r2.getColumnIndex(r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
    L_0x0072:
        r4 = r2.moveToNext();	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        if (r4 == 0) goto L_0x016a;
    L_0x0078:
        r4 = r2.getString(r7);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        if (r4 != 0) goto L_0x016a;
    L_0x0082:
        r4 = r2.getString(r6);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r9 = r2.getString(r7);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r10 = com.taobao.accs.utl.ALog.Level.I;	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r10 = com.taobao.accs.utl.ALog.isPrintLog(r10);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        if (r10 == 0) goto L_0x00d3;
    L_0x0092:
        r10 = "MessageService";
        r11 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r11.<init>();	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r12 = "state: ";
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r11 = r11.append(r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r12 = " ,cursor.message:";
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r11 = r11.append(r9);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r12 = " ,cursor.id:";
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r12 = r2.getString(r5);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r12 = " ,cursor.time:";
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r12 = r2.getString(r8);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r11 = r11.append(r12);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r11 = r11.toString();	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r12 = 0;
        r12 = new java.lang.Object[r12];	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        com.taobao.accs.utl.ALog.i(r10, r11, r12);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
    L_0x00d3:
        r10 = "0";
        r10 = android.text.TextUtils.equals(r10, r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        if (r10 == 0) goto L_0x0153;
    L_0x00db:
        r4 = "4";
    L_0x00dd:
        r10 = new org.android.agoo.common.MsgDO;	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r10.<init>();	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r10 = android.text.TextUtils.isEmpty(r9);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        if (r10 != 0) goto L_0x0072;
    L_0x00e8:
        r10 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        if (r10 != 0) goto L_0x0072;
    L_0x00ee:
        r4 = r15.b(r9, r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r9 = "cache";
        r4.messageSource = r9;	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        r0.add(r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        goto L_0x0072;
    L_0x00fb:
        r1 = move-exception;
        r14 = r3;
        r3 = r2;
        r2 = r14;
    L_0x00ff:
        r4 = com.taobao.accs.utl.ALog.Level.E;	 Catch:{ all -> 0x01d2 }
        r4 = com.taobao.accs.utl.ALog.isPrintLog(r4);	 Catch:{ all -> 0x01d2 }
        if (r4 == 0) goto L_0x0122;
    L_0x0107:
        r4 = "MessageService";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01d2 }
        r5.<init>();	 Catch:{ all -> 0x01d2 }
        r6 = "getUnReportMsg, e: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x01d2 }
        r1 = r5.append(r1);	 Catch:{ all -> 0x01d2 }
        r1 = r1.toString();	 Catch:{ all -> 0x01d2 }
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x01d2 }
        com.taobao.accs.utl.ALog.e(r4, r1, r5);	 Catch:{ all -> 0x01d2 }
    L_0x0122:
        if (r3 == 0) goto L_0x0127;
    L_0x0124:
        r3.close();	 Catch:{ Throwable -> 0x012e }
    L_0x0127:
        if (r2 == 0) goto L_0x0016;
    L_0x0129:
        r2.close();	 Catch:{ Throwable -> 0x012e }
        goto L_0x0016;
    L_0x012e:
        r1 = move-exception;
        r2 = com.taobao.accs.utl.ALog.Level.E;
        r2 = com.taobao.accs.utl.ALog.isPrintLog(r2);
        if (r2 == 0) goto L_0x0016;
    L_0x0137:
        r2 = "MessageService";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "getUnReportMsg close cursor or db, e: ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        r3 = new java.lang.Object[r13];
        com.taobao.accs.utl.ALog.e(r2, r1, r3);
        goto L_0x0016;
    L_0x0153:
        r10 = "2";
        r10 = android.text.TextUtils.equals(r10, r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        if (r10 == 0) goto L_0x015e;
    L_0x015b:
        r4 = "8";
        goto L_0x00dd;
    L_0x015e:
        r10 = "3";
        r4 = android.text.TextUtils.equals(r10, r4);	 Catch:{ Throwable -> 0x00fb, all -> 0x01d0 }
        if (r4 == 0) goto L_0x01ee;
    L_0x0166:
        r4 = "9";
        goto L_0x00dd;
    L_0x016a:
        if (r2 == 0) goto L_0x016f;
    L_0x016c:
        r2.close();	 Catch:{ Throwable -> 0x0176 }
    L_0x016f:
        if (r3 == 0) goto L_0x0016;
    L_0x0171:
        r3.close();	 Catch:{ Throwable -> 0x0176 }
        goto L_0x0016;
    L_0x0176:
        r1 = move-exception;
        r2 = com.taobao.accs.utl.ALog.Level.E;
        r2 = com.taobao.accs.utl.ALog.isPrintLog(r2);
        if (r2 == 0) goto L_0x0016;
    L_0x017f:
        r2 = "MessageService";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "getUnReportMsg close cursor or db, e: ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        r3 = new java.lang.Object[r13];
        com.taobao.accs.utl.ALog.e(r2, r1, r3);
        goto L_0x0016;
    L_0x019b:
        r0 = move-exception;
        r3 = r1;
        r2 = r1;
    L_0x019e:
        if (r2 == 0) goto L_0x01a3;
    L_0x01a0:
        r2.close();	 Catch:{ Throwable -> 0x01a9 }
    L_0x01a3:
        if (r3 == 0) goto L_0x01a8;
    L_0x01a5:
        r3.close();	 Catch:{ Throwable -> 0x01a9 }
    L_0x01a8:
        throw r0;
    L_0x01a9:
        r1 = move-exception;
        r2 = com.taobao.accs.utl.ALog.Level.E;
        r2 = com.taobao.accs.utl.ALog.isPrintLog(r2);
        if (r2 == 0) goto L_0x01a8;
    L_0x01b2:
        r2 = "MessageService";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "getUnReportMsg close cursor or db, e: ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        r3 = new java.lang.Object[r13];
        com.taobao.accs.utl.ALog.e(r2, r1, r3);
        goto L_0x01a8;
    L_0x01cd:
        r0 = move-exception;
        r2 = r1;
        goto L_0x019e;
    L_0x01d0:
        r0 = move-exception;
        goto L_0x019e;
    L_0x01d2:
        r0 = move-exception;
        r14 = r2;
        r2 = r3;
        r3 = r14;
        goto L_0x019e;
    L_0x01d7:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r14 = r0;
        r0 = r1;
        r1 = r14;
        goto L_0x00ff;
    L_0x01df:
        r0 = move-exception;
        r2 = r3;
        r3 = r1;
        r14 = r0;
        r0 = r1;
        r1 = r14;
        goto L_0x00ff;
    L_0x01e7:
        r2 = move-exception;
        r14 = r2;
        r2 = r3;
        r3 = r1;
        r1 = r14;
        goto L_0x00ff;
    L_0x01ee:
        r4 = r1;
        goto L_0x00dd;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.android.agoo.message.MessageService.b():java.util.ArrayList<org.android.agoo.common.MsgDO>");
    }

    public boolean a(String str) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        Object obj;
        SQLiteDatabase sQLiteDatabase2 = null;
        boolean z = false;
        Cursor cursor2 = null;
        try {
            if (c.containsKey(str)) {
                if (ALog.isPrintLog(Level.E)) {
                    ALog.e("MessageService", "hasMessageDuplicate,msgid=" + str, new Object[0]);
                }
                z = true;
            }
            SQLiteDatabase readableDatabase = this.b.getReadableDatabase();
            if (readableDatabase == null) {
                if (sQLiteDatabase2 != null) {
                    try {
                        cursor2.close();
                    } catch (Throwable th2) {
                    }
                }
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
            } else {
                try {
                    Cursor rawQuery = readableDatabase.rawQuery("select count(1) from message where id = ?", new String[]{str});
                    if (rawQuery != null) {
                        try {
                            if (rawQuery.moveToFirst() && rawQuery.getInt(0) > 0) {
                                z = true;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            sQLiteDatabase = readableDatabase;
                            cursor = rawQuery;
                            sQLiteDatabase2 = sQLiteDatabase;
                            if (cursor != null) {
                                try {
                                    cursor.close();
                                } catch (Throwable th4) {
                                    throw th;
                                }
                            }
                            if (sQLiteDatabase2 != null) {
                                sQLiteDatabase2.close();
                            }
                            throw th;
                        }
                    }
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (readableDatabase != null) {
                        readableDatabase.close();
                    }
                } catch (Throwable th6) {
                    th = th6;
                    sQLiteDatabase = readableDatabase;
                    obj = sQLiteDatabase2;
                    sQLiteDatabase2 = sQLiteDatabase;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase2 != null) {
                        sQLiteDatabase2.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th7) {
            th = th7;
            cursor = sQLiteDatabase2;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase2 != null) {
                sQLiteDatabase2.close();
            }
            throw th;
        }
        return z;
    }

    public boolean a(String str, int i) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        Object obj;
        SQLiteDatabase sQLiteDatabase2 = null;
        boolean z = false;
        Cursor cursor2 = null;
        try {
            if (c.containsKey(str) && c.containsValue(Integer.valueOf(i))) {
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i("MessageService", "addMessage,messageStores hasMessageDuplicate,msgid=" + str, new Object[0]);
                }
                z = true;
            }
            SQLiteDatabase readableDatabase = this.b.getReadableDatabase();
            if (readableDatabase == null) {
                if (sQLiteDatabase2 != null) {
                    try {
                        cursor2.close();
                    } catch (Throwable th2) {
                    }
                }
                if (readableDatabase != null) {
                    readableDatabase.close();
                }
            } else {
                try {
                    Cursor rawQuery = readableDatabase.rawQuery("select count(1) from message where id = ? and body_code=? create_time< date('now','-1 day')", new String[]{str, "" + i});
                    if (rawQuery != null) {
                        try {
                            if (rawQuery.moveToFirst() && rawQuery.getInt(0) > 0) {
                                z = true;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            sQLiteDatabase = readableDatabase;
                            cursor = rawQuery;
                            sQLiteDatabase2 = sQLiteDatabase;
                            if (cursor != null) {
                                try {
                                    cursor.close();
                                } catch (Throwable th4) {
                                    throw th;
                                }
                            }
                            if (sQLiteDatabase2 != null) {
                                sQLiteDatabase2.close();
                            }
                            throw th;
                        }
                    }
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (readableDatabase != null) {
                        readableDatabase.close();
                    }
                } catch (Throwable th6) {
                    th = th6;
                    sQLiteDatabase = readableDatabase;
                    obj = sQLiteDatabase2;
                    sQLiteDatabase2 = sQLiteDatabase;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase2 != null) {
                        sQLiteDatabase2.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th7) {
            th = th7;
            cursor = sQLiteDatabase2;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase2 != null) {
                sQLiteDatabase2.close();
            }
            throw th;
        }
        return z;
    }

    public MsgDO b(String str, String str2) {
        if (ALog.isPrintLog(Level.I)) {
            ALog.i("MessageService", "msgRecevie,message--->[" + str + "]" + ",utdid=" + com.taobao.accs.utl.a.b(a), new Object[0]);
        }
        if (TextUtils.isEmpty(str)) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.dealMessage", com.taobao.accs.utl.a.b(a), "message==null");
            if (ALog.isPrintLog(Level.I)) {
                ALog.i("MessageService", "handleMessage message==null,utdid=" + com.taobao.accs.utl.a.b(a), new Object[0]);
            }
            return null;
        }
        MsgDO msgDO = new MsgDO();
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            Bundle bundle = new Bundle();
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            StringBuilder stringBuilder3 = new StringBuilder();
            String str3 = null;
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    String str4;
                    String string = jSONObject.getString("p");
                    String string2 = jSONObject.getString("i");
                    CharSequence string3 = jSONObject.getString("b");
                    long j = jSONObject.getLong("f");
                    stringBuilder.append(string2);
                    if (jSONObject.isNull(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND)) {
                        str4 = str3;
                    } else {
                        str4 = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                    }
                    if (i < length - 1) {
                        stringBuilder.append(",");
                    }
                    msgDO.msgIds = string2;
                    msgDO.extData = str4;
                    msgDO.messageSource = "accs";
                    msgDO.type = "cache";
                    if (TextUtils.isEmpty(string3)) {
                        msgDO.errorCode = "11";
                        str3 = str4;
                    } else if (TextUtils.isEmpty(string)) {
                        msgDO.errorCode = "12";
                        str3 = str4;
                    } else if (j == -1) {
                        msgDO.errorCode = "13";
                        str3 = str4;
                    } else {
                        if (a(a, string)) {
                            Object string4 = a(j, msgDO).getString(AgooConstants.MESSAGE_ENCRYPTED);
                            boolean z = false;
                            if (!a.getPackageName().equals(string)) {
                                z = true;
                            } else if (!(TextUtils.equals(Integer.toString(0), string4) || TextUtils.equals(Integer.toString(4), string4))) {
                                msgDO.errorCode = "15";
                                ALog.e("MessageService", "error encrypted: " + string4, new Object[0]);
                                str3 = str4;
                            }
                            msgDO.agooFlag = z;
                            if (!TextUtils.isEmpty(str2)) {
                                msgDO.msgStatus = str2;
                            }
                        } else {
                            ALog.d("MessageService", "ondata checkpackage is del,pack=" + string, new Object[0]);
                            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "accs.dealMessage", com.taobao.accs.utl.a.b(a), "deletePack", string);
                            stringBuilder3.append(string);
                            stringBuilder2.append(string2);
                            msgDO.removePacks = string;
                            if (i < length - 1) {
                                stringBuilder3.append(",");
                                stringBuilder2.append(",");
                                str3 = str4;
                            }
                        }
                        str3 = str4;
                    }
                }
            }
        } catch (Throwable th) {
            if (ALog.isPrintLog(Level.E)) {
                ALog.e("MessageService", "createMsg is error,e: " + th, new Object[0]);
            }
        }
        return msgDO;
    }

    public static final boolean a(Context context, String str) {
        try {
            if (context.getPackageManager().getApplicationInfo(str, 0) != null) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    private static Bundle a(long j, MsgDO msgDO) {
        Bundle bundle = new Bundle();
        try {
            char[] toCharArray = Long.toBinaryString(j).toCharArray();
            if (toCharArray != null && 8 <= toCharArray.length) {
                if (8 <= toCharArray.length) {
                    bundle.putString(AgooConstants.MESSAGE_ENCRYPTED, "" + Integer.parseInt("" + toCharArray[1] + toCharArray[2] + toCharArray[3] + toCharArray[4], 2));
                    if (toCharArray[6] == '1') {
                        bundle.putString(AgooConstants.MESSAGE_REPORT, "1");
                        msgDO.reportStr = "1";
                    }
                    if (toCharArray[7] == '1') {
                        bundle.putString(AgooConstants.MESSAGE_NOTIFICATION, "1");
                    }
                }
                if (9 <= toCharArray.length && toCharArray[8] == '1') {
                    bundle.putString(AgooConstants.MESSAGE_HAS_TEST, "1");
                }
                if (10 <= toCharArray.length && toCharArray[9] == '1') {
                    bundle.putString(AgooConstants.MESSAGE_DUPLICATE, "1");
                }
                if (11 <= toCharArray.length && toCharArray[10] == '1') {
                    bundle.putInt(AgooConstants.MESSAGE_POPUP, 1);
                }
            }
        } catch (Throwable th) {
        }
        return bundle;
    }
}
