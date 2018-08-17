package com.unionpay.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.umeng.analytics.pro.x;
import com.umeng.message.proguard.k;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ad extends s {
    private static volatile ad a = null;
    private static SQLiteDatabase b;
    private static int c;
    private final int d = 1;
    private final int e = 2;
    private final int f = 3;

    static final class a implements BaseColumns {
        static final String[] a = new String[]{k.g, "name", x.W, "duration", "session_id", "refer", "realtime"};

        static final void a(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE activity (_id INTEGER PRIMARY KEY autoincrement,name TEXT,start_time LONG,duration INTEGER,session_id TEXT,refer TEXT,realtime LONG)");
        }

        static final void b(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS activity");
        }
    }

    static final class b implements BaseColumns {
        static final String[] a = new String[]{k.g, "event_id", "event_label", "session_id", "occurtime", "paramap"};

        static final void a(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE app_event (_id INTEGER PRIMARY KEY autoincrement,event_id TEXT,event_label TEXT,session_id TEXT,occurtime LONG,paramap BLOB)");
        }

        static final void b(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS app_event");
        }
    }

    static final class c implements BaseColumns {
        static final String[] a = new String[]{k.g, "error_time", "message", "repeat", "shorthashcode"};

        static final void a(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE error_report (_id INTEGER PRIMARY KEY autoincrement,error_time LONG,message BLOB,repeat INTERGER,shorthashcode TEXT)");
        }

        static final void b(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS error_report");
        }
    }

    static final class d implements BaseColumns {
        static final String[] a = new String[]{k.g, "session_id", x.W, "duration", "is_launch", "interval", "is_connected"};

        static final void a(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE session (_id INTEGER PRIMARY KEY autoincrement,session_id TEXT,start_time LONG,duration INTEGER,is_launch INTEGER,interval LONG, is_connected INTEGER)");
        }

        static final void b(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS session");
        }
    }

    private ad() {
    }

    private synchronized long a(String str, ContentValues contentValues, String str2, String[] strArr, int i) {
        long j = 0;
        synchronized (this) {
            if (!am.b(str)) {
                b.beginTransaction();
                switch (i) {
                    case 1:
                        j = b.insert(str, null, contentValues);
                        break;
                    case 2:
                        j = (long) b.update(str, contentValues, str2, strArr);
                        break;
                    case 3:
                        j = (long) b.delete(str, str2, strArr);
                        break;
                }
                try {
                    b.setTransactionSuccessful();
                } catch (Throwable th) {
                } finally {
                    b.endTransaction();
                }
            }
        }
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized long a(java.lang.String r11, com.unionpay.c.ao.c r12, java.lang.StringBuffer r13) {
        /*
        r10 = this;
        r9 = 3;
        r8 = 0;
        monitor-enter(r10);
        r0 = b;	 Catch:{ Throwable -> 0x0108, all -> 0x0105 }
        r1 = "error_report";
        r2 = com.unionpay.c.ad.c.a;	 Catch:{ Throwable -> 0x0108, all -> 0x0105 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = "_id";
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0108, all -> 0x0105 }
        r0 = "\r\n";
        r0 = r11.split(r0);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1 = r0.length;	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        if (r1 >= r9) goto L_0x0025;
    L_0x001c:
        if (r2 == 0) goto L_0x0021;
    L_0x001e:
        r2.close();	 Catch:{ all -> 0x00df }
    L_0x0021:
        r0 = 0;
    L_0x0023:
        monitor-exit(r10);
        return r0;
    L_0x0025:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1.<init>();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = 0;
        r3 = r0[r3];	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = "\r\n";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = 1;
        r3 = r0[r3];	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = "\r\n";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = 2;
        r0 = r0[r3];	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1 = com.unionpay.c.am.c(r0);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r13.append(r1);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        if (r1 == 0) goto L_0x00ff;
    L_0x005c:
        r1 = r2.isAfterLast();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        if (r1 != 0) goto L_0x00ff;
    L_0x0062:
        r1 = 1;
        r1 = r2.getString(r1);	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r1 = com.unionpay.c.v.b(r1);	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r4 = java.lang.Long.parseLong(r1);	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r12.a = r4;	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r1 = 2;
        r1 = r2.getBlob(r1);	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r12.d = r1;	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r1 = 3;
        r1 = r2.getString(r1);	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r1 = com.unionpay.c.v.b(r1);	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r12.b = r1;	 Catch:{ Throwable -> 0x00e2, all -> 0x00f8 }
        r1 = new java.lang.String;	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = r12.d;	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r4 = "UTF-8";
        r1.<init>(r3, r4);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = r1.length();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r4 = r0.length();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        if (r3 < r4) goto L_0x005c;
    L_0x009a:
        r3 = "\r\n";
        r1 = r1.split(r3);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = r1.length;	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        if (r3 < r9) goto L_0x005c;
    L_0x00a3:
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3.<init>();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r4 = 0;
        r4 = r1[r4];	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r4 = "\r\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r4 = 1;
        r4 = r1[r4];	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r4 = "\r\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r4 = 2;
        r1 = r1[r4];	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1 = r3.append(r1);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        r1 = r1.equals(r0);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        if (r1 == 0) goto L_0x00f3;
    L_0x00d3:
        r0 = 0;
        r0 = r2.getLong(r0);	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        if (r2 == 0) goto L_0x0023;
    L_0x00da:
        r2.close();	 Catch:{ all -> 0x00df }
        goto L_0x0023;
    L_0x00df:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x00e2:
        r1 = move-exception;
        r2.moveToNext();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        goto L_0x005c;
    L_0x00e8:
        r0 = move-exception;
        r0 = r2;
    L_0x00ea:
        if (r0 == 0) goto L_0x00ef;
    L_0x00ec:
        r0.close();	 Catch:{ all -> 0x00df }
    L_0x00ef:
        r0 = 0;
        goto L_0x0023;
    L_0x00f3:
        r2.moveToNext();	 Catch:{ Throwable -> 0x00e8, all -> 0x00f8 }
        goto L_0x005c;
    L_0x00f8:
        r0 = move-exception;
    L_0x00f9:
        if (r2 == 0) goto L_0x00fe;
    L_0x00fb:
        r2.close();	 Catch:{ all -> 0x00df }
    L_0x00fe:
        throw r0;	 Catch:{ all -> 0x00df }
    L_0x00ff:
        if (r2 == 0) goto L_0x00ef;
    L_0x0101:
        r2.close();	 Catch:{ all -> 0x00df }
        goto L_0x00ef;
    L_0x0105:
        r0 = move-exception;
        r2 = r8;
        goto L_0x00f9;
    L_0x0108:
        r0 = move-exception;
        r0 = r8;
        goto L_0x00ea;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.c.ad.a(java.lang.String, com.unionpay.c.ao$c, java.lang.StringBuffer):long");
    }

    private static Map a(byte[] bArr) {
        Closeable closeable;
        Throwable th;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Closeable byteArrayInputStream;
        Closeable dataInputStream;
        try {
            Map hashMap = new HashMap();
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                dataInputStream = new DataInputStream(byteArrayInputStream);
                try {
                    int readInt = dataInputStream.readInt();
                    for (int i = 0; i < readInt; i++) {
                        Object valueOf;
                        String readUTF = dataInputStream.readUTF();
                        int readInt2 = dataInputStream.readInt();
                        if (readInt2 == 66) {
                            valueOf = Double.valueOf(dataInputStream.readDouble());
                        } else if (readInt2 == 88) {
                            valueOf = dataInputStream.readUTF();
                        } else {
                            a(byteArrayInputStream);
                            a(dataInputStream);
                            return null;
                        }
                        hashMap.put(readUTF, valueOf);
                    }
                    a(byteArrayInputStream);
                    a(dataInputStream);
                    return hashMap;
                } catch (Throwable th2) {
                    th = th2;
                    a(byteArrayInputStream);
                    a(dataInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                dataInputStream = null;
                th = th3;
                a(byteArrayInputStream);
                a(dataInputStream);
                throw th;
            }
        } catch (Throwable th32) {
            dataInputStream = null;
            byteArrayInputStream = null;
            th = th32;
            a(byteArrayInputStream);
            a(dataInputStream);
            throw th;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    static ad d() {
        if (a == null) {
            synchronized (ad.class) {
                if (a == null) {
                    a = new ad();
                }
            }
        }
        return a;
    }

    private synchronized void e() {
        try {
            if (d.c != null) {
                if (b == null) {
                    File file = new File(d.c.getFilesDir(), "UPtcagent.db");
                    boolean exists = file.exists();
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    b = SQLiteDatabase.openOrCreateDatabase(file, null);
                    if (file.length() > 6144000 && file.length() > 8089600) {
                        f();
                        g();
                    }
                    b.setMaximumSize(8192000);
                    c = 1;
                    if (!exists) {
                        g();
                    } else if (6 > b.getVersion()) {
                        f();
                        g();
                    }
                } else {
                    c++;
                }
            }
        } catch (Throwable th) {
        }
    }

    private static void f() {
        b.setVersion(6);
        d.b(b);
        a.b(b);
        b.b(b);
        c.b(b);
    }

    private static void g() {
        b.setVersion(6);
        d.a(b);
        a.a(b);
        b.a(b);
        c.a(b);
    }

    private synchronized void h() {
        c--;
        int max = Math.max(0, c);
        c = max;
        if (max == 0 && b != null) {
            b.close();
            b = null;
        }
    }

    final synchronized long a(long j, String str) {
        long a;
        ContentValues contentValues = new ContentValues();
        contentValues.put("error_time", v.a(String.valueOf(j)));
        c cVar = new c();
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            if (0 == a(str, cVar, stringBuffer)) {
                contentValues.put("message", str.getBytes("UTF-8"));
                contentValues.put("repeat", v.a(String.valueOf(1)));
                contentValues.put("shorthashcode", v.a(stringBuffer.toString()));
                a = a("error_report", contentValues, null, null, 1);
            } else {
                contentValues.put("repeat", v.a(String.valueOf(cVar.b + 1)));
                a = a("error_report", contentValues, "_id=?", new String[]{String.valueOf(r8)}, 2);
            }
        } catch (Throwable th) {
            a = 0;
        }
        return a;
    }

    final synchronized long a(List list) {
        long j;
        int size = list.size();
        if (size == 0) {
            j = 0;
        } else {
            int i = size - 1;
            long j2 = 0;
            while (i >= 0) {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("SELECT MAX(_id) from activity where duration != 0 and session_id =?");
                    Cursor cursor = null;
                    Cursor rawQuery = b.rawQuery(stringBuilder.toString(), new String[]{v.a(((j) list.get(i)).a)});
                    try {
                        if (rawQuery.moveToFirst()) {
                            j = rawQuery.getLong(0);
                            if (j == 0) {
                                j2 = j;
                            } else if (rawQuery != null) {
                                try {
                                    rawQuery.close();
                                } catch (Throwable th) {
                                }
                            }
                        }
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        i--;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        cursor = rawQuery;
                    }
                } catch (Throwable th4) {
                    j = j2;
                }
            }
            j = j2;
        }
        return j;
        if (cursor != null) {
            cursor.close();
        }
        throw th3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final synchronized java.util.List a(long r10) {
        /*
        r9 = this;
        monitor-enter(r9);
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x009d }
        r2.<init>();	 Catch:{ all -> 0x009d }
        r0 = 0;
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r1.<init>();	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r3 = "SELECT error_time,message,repeat, shorthashcode from error_report where _id<=?";
        r1.append(r3);	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r3 = b;	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r5 = 0;
        r6 = java.lang.String.valueOf(r10);	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r1 = r3.rawQuery(r1, r4);	 Catch:{ Throwable -> 0x00ab, all -> 0x00a6 }
        r0 = r1.moveToFirst();	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        if (r0 == 0) goto L_0x00a0;
    L_0x002b:
        r0 = com.unionpay.c.d.c;	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        if (r0 == 0) goto L_0x008e;
    L_0x002f:
        r0 = com.unionpay.c.z.d();	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
    L_0x0037:
        r3 = r1.isAfterLast();	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        if (r3 != 0) goto L_0x00a0;
    L_0x003d:
        r3 = new com.unionpay.c.ao$i;	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        r3.<init>();	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        r4 = 3;
        r3.a = r4;	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r4 = new com.unionpay.c.ao$c;	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r4.<init>();	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r5 = 0;
        r5 = r1.getString(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r5 = com.unionpay.c.v.b(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r6 = java.lang.Long.parseLong(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r4.a = r6;	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r5 = 1;
        r5 = r1.getBlob(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r4.d = r5;	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r5 = 2;
        r5 = r1.getString(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r5 = com.unionpay.c.v.b(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r5 = java.lang.Integer.parseInt(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r4.b = r5;	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r5 = 3;
        r5 = r1.getString(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r5 = com.unionpay.c.v.b(r5);	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r4.e = r5;	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r4.c = r0;	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r3.d = r4;	 Catch:{ Throwable -> 0x0091, all -> 0x0096 }
        r2.add(r3);	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        r1.moveToNext();	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        goto L_0x0037;
    L_0x0085:
        r0 = move-exception;
        r0 = r1;
    L_0x0087:
        if (r0 == 0) goto L_0x008c;
    L_0x0089:
        r0.close();	 Catch:{ all -> 0x009d }
    L_0x008c:
        monitor-exit(r9);
        return r2;
    L_0x008e:
        r0 = "";
        goto L_0x0037;
    L_0x0091:
        r3 = move-exception;
        r1.moveToNext();	 Catch:{ Throwable -> 0x0085, all -> 0x0096 }
        goto L_0x0037;
    L_0x0096:
        r0 = move-exception;
    L_0x0097:
        if (r1 == 0) goto L_0x009c;
    L_0x0099:
        r1.close();	 Catch:{ all -> 0x009d }
    L_0x009c:
        throw r0;	 Catch:{ all -> 0x009d }
    L_0x009d:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x00a0:
        if (r1 == 0) goto L_0x008c;
    L_0x00a2:
        r1.close();	 Catch:{ all -> 0x009d }
        goto L_0x008c;
    L_0x00a6:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0097;
    L_0x00ab:
        r1 = move-exception;
        goto L_0x0087;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.c.ad.a(long):java.util.List");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final synchronized java.util.List a(java.lang.String r11) {
        /*
        r10 = this;
        r8 = 0;
        monitor-enter(r10);
        r9 = new java.util.ArrayList;	 Catch:{ all -> 0x008c }
        r9.<init>();	 Catch:{ all -> 0x008c }
        r0 = b;	 Catch:{ Throwable -> 0x0097, all -> 0x0095 }
        r1 = "activity";
        r2 = com.unionpay.c.ad.a.a;	 Catch:{ Throwable -> 0x0097, all -> 0x0095 }
        r3 = "session_id=? AND duration !=? ";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x0097, all -> 0x0095 }
        r5 = 0;
        r6 = com.unionpay.c.v.a(r11);	 Catch:{ Throwable -> 0x0097, all -> 0x0095 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0097, all -> 0x0095 }
        r5 = 1;
        r6 = "0";
        r6 = com.unionpay.c.v.a(r6);	 Catch:{ Throwable -> 0x0097, all -> 0x0095 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0097, all -> 0x0095 }
        r5 = 0;
        r6 = 0;
        r7 = "_id";
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0097, all -> 0x0095 }
        r1 = r0.moveToFirst();	 Catch:{ Throwable -> 0x0076, all -> 0x0083 }
        if (r1 == 0) goto L_0x008f;
    L_0x0030:
        r1 = r0.isAfterLast();	 Catch:{ Throwable -> 0x0076, all -> 0x0083 }
        if (r1 != 0) goto L_0x008f;
    L_0x0036:
        r1 = new com.unionpay.c.ao$a;	 Catch:{ Throwable -> 0x0076, all -> 0x0083 }
        r1.<init>();	 Catch:{ Throwable -> 0x0076, all -> 0x0083 }
        r2 = 1;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = com.unionpay.c.v.b(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r1.a = r2;	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = 2;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = com.unionpay.c.v.b(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = java.lang.Long.parseLong(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r1.b = r2;	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = 3;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = com.unionpay.c.v.b(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r1.c = r2;	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = 5;
        r2 = r0.getString(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r2 = com.unionpay.c.v.b(r2);	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r1.d = r2;	 Catch:{ Throwable -> 0x007e, all -> 0x0083 }
        r9.add(r1);	 Catch:{ Throwable -> 0x0076, all -> 0x0083 }
        r0.moveToNext();	 Catch:{ Throwable -> 0x0076, all -> 0x0083 }
        goto L_0x0030;
    L_0x0076:
        r1 = move-exception;
    L_0x0077:
        if (r0 == 0) goto L_0x007c;
    L_0x0079:
        r0.close();	 Catch:{ all -> 0x008c }
    L_0x007c:
        monitor-exit(r10);
        return r9;
    L_0x007e:
        r1 = move-exception;
        r0.moveToNext();	 Catch:{ Throwable -> 0x0076, all -> 0x0083 }
        goto L_0x0030;
    L_0x0083:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
    L_0x0086:
        if (r8 == 0) goto L_0x008b;
    L_0x0088:
        r8.close();	 Catch:{ all -> 0x008c }
    L_0x008b:
        throw r0;	 Catch:{ all -> 0x008c }
    L_0x008c:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x008f:
        if (r0 == 0) goto L_0x007c;
    L_0x0091:
        r0.close();	 Catch:{ all -> 0x008c }
        goto L_0x007c;
    L_0x0095:
        r0 = move-exception;
        goto L_0x0086;
    L_0x0097:
        r0 = move-exception;
        r0 = r8;
        goto L_0x0077;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.c.ad.a(java.lang.String):java.util.List");
    }

    final void a() {
        e();
    }

    final synchronized long b(List list) {
        long j;
        int size = list.size();
        if (size == 0) {
            j = 0;
        } else {
            int i = size - 1;
            while (i >= 0) {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("SELECT MAX(_id) from app_event where session_id =?");
                    Cursor cursor = null;
                    Cursor rawQuery = b.rawQuery(stringBuilder.toString(), new String[]{v.a(((j) list.get(i)).a)});
                    try {
                        if (rawQuery.moveToFirst()) {
                            j = rawQuery.getLong(0);
                            if (j != 0) {
                                if (rawQuery != null) {
                                    rawQuery.close();
                                }
                            }
                        }
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        i--;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        cursor = rawQuery;
                    }
                } catch (Throwable th3) {
                }
            }
            j = 0;
        }
        return j;
        if (cursor != null) {
            cursor.close();
        }
        throw th2;
    }

    final synchronized List b(String str) {
        List arrayList;
        Throwable th;
        Throwable th2;
        Cursor cursor = null;
        synchronized (this) {
            arrayList = new ArrayList();
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT COUNT(_id), MAX(occurtime), event_id, event_label, paramap from app_event where session_id = ? group by event_id, event_label, paramap");
                cursor = b.rawQuery(stringBuilder.toString(), new String[]{v.a(str)});
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        b bVar = new b();
                        try {
                            bVar.c = cursor.getInt(0);
                            bVar.d = Long.parseLong(v.b(cursor.getString(1)));
                            bVar.a = v.b(cursor.getString(2));
                            bVar.b = v.b(cursor.getString(3));
                            bVar.e = null;
                            bVar.e = a(cursor.getBlob(4));
                            arrayList.add(bVar);
                            cursor.moveToNext();
                        } catch (Throwable th3) {
                            th = th3;
                            r1 = cursor;
                            th2 = th;
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th32) {
                th = th32;
                r1 = cursor;
                th2 = th;
                Cursor cursor2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th2;
            }
        }
        return arrayList;
    }

    final void b() {
        h();
    }

    final synchronized long c(String str) {
        Cursor rawQuery;
        long j;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT MAX(_id) from ");
                stringBuilder.append(str);
                rawQuery = b.rawQuery(stringBuilder.toString(), null);
                try {
                    if (!rawQuery.moveToFirst() || rawQuery.isAfterLast()) {
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        j = 0;
                    } else {
                        j = rawQuery.getLong(0);
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                rawQuery = null;
                th = th3;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        }
        return j;
    }

    final synchronized List c() {
        List arrayList;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            arrayList = new ArrayList();
            Cursor query;
            try {
                query = b.query("session", d.a, null, null, null, null, k.g, "10");
                if (query.moveToFirst()) {
                    while (!query.isAfterLast()) {
                        j jVar = new j();
                        try {
                            jVar.a = v.b(query.getString(1));
                            jVar.b = Long.parseLong(v.b(query.getString(2)));
                            jVar.d = Integer.parseInt(v.b(query.getString(3)));
                            String b = v.b(query.getString(4));
                            if (b == null || b.equals("null") || Integer.parseInt(b) != 0) {
                                jVar.c = jVar.d != 0 ? 3 : 2;
                            } else {
                                jVar.c = 1;
                            }
                        } catch (Throwable th2) {
                            cursor = query;
                            th = th2;
                        }
                        try {
                            if (1 == jVar.c) {
                                jVar.g = Integer.parseInt(v.b(query.getString(5)));
                                if (jVar.g < 0) {
                                    jVar.g = 0;
                                }
                                jVar.d = jVar.g / 1000;
                            }
                            jVar.h = Integer.parseInt(v.b(query.getString(6)));
                            arrayList.add(jVar);
                            query.moveToNext();
                        } catch (Throwable th22) {
                            cursor = query;
                            th = th22;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return arrayList;
    }
}
