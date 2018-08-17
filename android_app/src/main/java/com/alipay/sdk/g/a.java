package com.alipay.sdk.g;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.sdk.c.b;
import java.lang.ref.WeakReference;

public final class a extends SQLiteOpenHelper {
    private WeakReference<Context> a;

    public final void a(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.base/java.util.HashMap$HashIterator.nextNode(Unknown Source)
	at java.base/java.util.HashMap$KeyIterator.next(Unknown Source)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:80)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r7 = this;
        r1 = 0;
        r2 = 0;
        r1 = r7.getWritableDatabase();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = a(r1, r8, r9);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 == 0) goto L_0x0020;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x000c:
        r0 = r7;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r2 = r8;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = r9;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = r10;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = r11;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0014:
        if (r1 == 0) goto L_0x001f;
    L_0x0016:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r1.close();
    L_0x001f:
        return;
    L_0x0020:
        r3 = "insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))";	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r7.a;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r0.get();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = com.alipay.sdk.util.a.c(r0);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = com.alipay.sdk.c.b.a(r4, r10, r0);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 3;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = c(r8, r9);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r5] = r0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = 2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4[r0] = r11;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r1.execSQL(r3, r4);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = "select name from tb_tid where tid!='' order by dt asc";	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r3 = r1.rawQuery(r0, r3);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r3.getCount();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = 14;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 > r4) goto L_0x0066;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0055:
        r3.close();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        goto L_0x0014;
    L_0x0059:
        r0 = move-exception;
        if (r1 == 0) goto L_0x001f;
    L_0x005c:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x001f;
    L_0x0062:
        r1.close();
        goto L_0x001f;
    L_0x0066:
        r0 = r3.getCount();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r4 = r0 + -14;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r3.moveToFirst();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r0 == 0) goto L_0x0086;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0074:
        r0 = r2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0075:
        r6 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = r3.getString(r6);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r5[r0] = r6;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r0 + 1;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r6 = r3.moveToNext();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r6 == 0) goto L_0x0086;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0084:
        if (r4 > r0) goto L_0x0075;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0086:
        r3.close();	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r0 = r2;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x008a:
        if (r0 >= r4) goto L_0x0014;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x008c:
        r2 = r5[r0];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        if (r2 != 0) goto L_0x0099;	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0094:
        r2 = r5[r0];	 Catch:{ Exception -> 0x0059, all -> 0x009c }
        a(r1, r2);	 Catch:{ Exception -> 0x0059, all -> 0x009c }
    L_0x0099:
        r0 = r0 + 1;
        goto L_0x008a;
    L_0x009c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00a8;
    L_0x009f:
        r2 = r1.isOpen();
        if (r2 == 0) goto L_0x00a8;
    L_0x00a5:
        r1.close();
    L_0x00a8:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.g.a.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public a(Context context) {
        super(context, "msp.db", null, 1);
        this.a = new WeakReference(context);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
        onCreate(sQLiteDatabase);
    }

    public final String a(String str, String str2) {
        Cursor rawQuery;
        Throwable th;
        String str3 = null;
        String str4 = "select tid from tb_tid where name=?";
        SQLiteDatabase readableDatabase;
        try {
            readableDatabase = getReadableDatabase();
            try {
                rawQuery = readableDatabase.rawQuery(str4, new String[]{c(str, str2)});
                try {
                    if (rawQuery.moveToFirst()) {
                        str3 = rawQuery.getString(0);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase == null || !readableDatabase.isOpen()) {
                        str4 = str3;
                    } else {
                        readableDatabase.close();
                        str4 = str3;
                    }
                } catch (Exception e) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase == null) {
                    }
                    str4 = null;
                    if (TextUtils.isEmpty(str4)) {
                        return str4;
                    }
                    return b.a(2, str4, com.alipay.sdk.util.a.c((Context) this.a.get()));
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    throw th;
                }
            } catch (Exception e2) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (readableDatabase == null) {
                }
                str4 = null;
                if (TextUtils.isEmpty(str4)) {
                    return b.a(2, str4, com.alipay.sdk.util.a.c((Context) this.a.get()));
                }
                return str4;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            rawQuery = null;
            readableDatabase = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase == null && readableDatabase.isOpen()) {
                readableDatabase.close();
                str4 = null;
            } else {
                str4 = null;
            }
            if (TextUtils.isEmpty(str4)) {
                return str4;
            }
            return b.a(2, str4, com.alipay.sdk.util.a.c((Context) this.a.get()));
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th;
        }
        if (TextUtils.isEmpty(str4)) {
            return b.a(2, str4, com.alipay.sdk.util.a.c((Context) this.a.get()));
        }
        return str4;
    }

    public final String b(String str, String str2) {
        SQLiteDatabase readableDatabase;
        Throwable th;
        String str3 = null;
        String str4 = "select key_tid from tb_tid where name=?";
        Cursor rawQuery;
        try {
            readableDatabase = getReadableDatabase();
            try {
                rawQuery = readableDatabase.rawQuery(str4, new String[]{c(str, str2)});
                try {
                    if (rawQuery.moveToFirst()) {
                        str3 = rawQuery.getString(0);
                    }
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (readableDatabase != null && readableDatabase.isOpen()) {
                        readableDatabase.close();
                    }
                } catch (Exception e) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    return str3;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    readableDatabase.close();
                    throw th;
                }
            } catch (Exception e2) {
                rawQuery = null;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                return str3;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                rawQuery = null;
                th = th4;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                readableDatabase.close();
                throw th;
            }
        } catch (Exception e3) {
            rawQuery = null;
            readableDatabase = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            return str3;
        } catch (Throwable th32) {
            readableDatabase = null;
            th = th32;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (readableDatabase != null && readableDatabase.isOpen()) {
                readableDatabase.close();
            }
            throw th;
        }
        return str3;
    }

    private static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        int i;
        Cursor cursor = null;
        try {
            int i2;
            cursor = sQLiteDatabase.rawQuery("select count(*) from tb_tid where name=?", new String[]{c(str, str2)});
            if (cursor.moveToFirst()) {
                i2 = cursor.getInt(0);
            } else {
                i2 = 0;
            }
            if (cursor != null) {
                cursor.close();
                i = i2;
            } else {
                i = i2;
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
                i = 0;
            } else {
                i = 0;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    static String c(String str, String str2) {
        return str + str2;
    }

    final void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        sQLiteDatabase.execSQL("update tb_tid set tid=?, key_tid=?, dt=datetime('now', 'localtime') where name=?", new Object[]{b.a(1, str3, com.alipay.sdk.util.a.c((Context) this.a.get())), str4, c(str, str2)});
    }

    static void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.delete("tb_tid", "name=?", new String[]{str});
        } catch (Exception e) {
        }
    }
}
