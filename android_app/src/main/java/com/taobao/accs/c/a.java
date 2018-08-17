package com.taobao.accs.c;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.umeng.analytics.pro.x;
import com.umeng.message.proguard.k;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Taobao */
public class a extends SQLiteOpenHelper {
    private static volatile a c = null;
    private static final Lock e = new ReentrantLock();
    public int a = 0;
    LinkedList<a> b = new LinkedList();
    private Context d;

    /* compiled from: Taobao */
    private class a {
        String a;
        Object[] b;
        final /* synthetic */ a c;

        private a(a aVar, String str, Object[] objArr) {
            this.c = aVar;
            this.a = str;
            this.b = objArr;
        }
    }

    public SQLiteDatabase getWritableDatabase() {
        if (com.taobao.accs.utl.a.a(super.getWritableDatabase().getPath(), 102400)) {
            return super.getWritableDatabase();
        }
        return null;
    }

    public static a a(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(context, Constants.DB_NAME, null, 3);
                }
            }
        }
        return c;
    }

    private a(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.d = context;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            if (e.tryLock()) {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS traffic(_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, host TEXT,serviceid TEXT, bid TEXT, isbackground TEXT, size TEXT)");
            }
            e.unlock();
        } catch (Throwable th) {
            e.unlock();
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS service");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS network");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ping");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS msg");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ack");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS election");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS bindApp");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS bindUser");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS traffic");
            onCreate(sQLiteDatabase);
        }
    }

    public void a(String str, String str2, String str3, boolean z, long j, String str4) {
        if (a(str, str3, z, str4)) {
            a("UPDATE traffic SET size=? WHERE date=? AND host=? AND bid=? AND isbackground=?", new Object[]{Long.valueOf(j), str4, str, str3, String.valueOf(z)}, true);
            return;
        }
        a("INSERT INTO traffic VALUES(null,?,?,?,?,?,?)", new Object[]{str4, str, str2, str3, String.valueOf(z), Long.valueOf(j)}, true);
    }

    private synchronized boolean a(String str, String str2, boolean z, String str3) {
        boolean z2;
        Cursor query;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                z2 = false;
                if (cursor != null) {
                    cursor.close();
                }
            } else {
                query = writableDatabase.query(x.ah, new String[]{k.g, "date", "host", "serviceid", "bid", "isbackground", "size"}, "date=? AND host=? AND bid=? AND isbackground=?", new String[]{str3, str, str2, String.valueOf(z)}, null, null, null, String.valueOf(100));
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            z2 = true;
                            if (query != null) {
                                query.close();
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            ALog.w("DBHelper", e.toString(), new Object[0]);
                            if (query != null) {
                                query.close();
                            }
                            z2 = false;
                            return z2;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = query;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
                z2 = false;
            }
        } catch (Exception e3) {
            e = e3;
            query = cursor;
            ALog.w("DBHelper", e.toString(), new Object[0]);
            if (query != null) {
                query.close();
            }
            z2 = false;
            return z2;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return z2;
    }

    public void a() {
        a("DELETE FROM traffic", null, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.taobao.accs.ut.monitor.TrafficsMonitor.a> a(boolean r15) {
        /*
        r14 = this;
        r9 = 0;
        monitor-enter(r14);
        r1 = 0;
        r10 = new java.util.ArrayList;	 Catch:{ all -> 0x0136 }
        r10.<init>();	 Catch:{ all -> 0x0136 }
        r0 = r14.getWritableDatabase();	 Catch:{ Exception -> 0x0123 }
        if (r0 != 0) goto L_0x0016;
    L_0x000e:
        if (r9 == 0) goto L_0x0013;
    L_0x0010:
        r1.close();	 Catch:{ all -> 0x0136 }
    L_0x0013:
        monitor-exit(r14);	 Catch:{ all -> 0x0136 }
        r0 = r9;
    L_0x0015:
        return r0;
    L_0x0016:
        if (r15 == 0) goto L_0x0067;
    L_0x0018:
        r1 = "traffic";
        r2 = 7;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0123 }
        r3 = 0;
        r4 = "_id";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 1;
        r4 = "date";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 2;
        r4 = "host";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 3;
        r4 = "serviceid";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 4;
        r4 = "bid";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 5;
        r4 = "isbackground";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 6;
        r4 = "size";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = "date=?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0123 }
        r5 = 0;
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0123 }
        r6 = com.taobao.accs.utl.UtilityImpl.formatDay(r6);	 Catch:{ Exception -> 0x0123 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0123 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 100;
        r8 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x0123 }
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0123 }
    L_0x005d:
        if (r8 != 0) goto L_0x009f;
    L_0x005f:
        if (r8 == 0) goto L_0x0064;
    L_0x0061:
        r8.close();	 Catch:{ all -> 0x0136 }
    L_0x0064:
        monitor-exit(r14);	 Catch:{ all -> 0x0136 }
        r0 = r9;
        goto L_0x0015;
    L_0x0067:
        r1 = "traffic";
        r2 = 7;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0123 }
        r3 = 0;
        r4 = "_id";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 1;
        r4 = "date";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 2;
        r4 = "host";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 3;
        r4 = "serviceid";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 4;
        r4 = "bid";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 5;
        r4 = "isbackground";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 6;
        r4 = "size";
        r2[r3] = r4;	 Catch:{ Exception -> 0x0123 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 100;
        r8 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x0123 }
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0123 }
        goto L_0x005d;
    L_0x009f:
        r0 = r8.moveToFirst();	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        if (r0 == 0) goto L_0x011a;
    L_0x00a5:
        r0 = 1;
        r1 = r8.getString(r0);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r0 = 2;
        r5 = r8.getString(r0);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r0 = 3;
        r3 = r8.getString(r0);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r0 = 4;
        r2 = r8.getString(r0);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r0 = 5;
        r0 = r8.getString(r0);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r4 = r0.booleanValue();	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r0 = 6;
        r6 = r8.getLong(r0);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        if (r2 == 0) goto L_0x0114;
    L_0x00cd:
        r12 = 0;
        r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r0 <= 0) goto L_0x0114;
    L_0x00d3:
        r0 = new com.taobao.accs.ut.monitor.TrafficsMonitor$a;	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r0.<init>(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r10.add(r0);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r0 = "DBHelper";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r1.<init>();	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r4 = "resotre traffics from db bid:";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r2 = " serviceid:";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r2 = " host:";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r2 = " size:";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r1 = r1.append(r6);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        com.taobao.accs.utl.ALog.d(r0, r1, r2);	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
    L_0x0114:
        r0 = r8.moveToNext();	 Catch:{ Exception -> 0x0143, all -> 0x0140 }
        if (r0 != 0) goto L_0x00a5;
    L_0x011a:
        if (r8 == 0) goto L_0x011f;
    L_0x011c:
        r8.close();	 Catch:{ all -> 0x0136 }
    L_0x011f:
        monitor-exit(r14);	 Catch:{ all -> 0x0136 }
        r0 = r10;
        goto L_0x0015;
    L_0x0123:
        r0 = move-exception;
    L_0x0124:
        r1 = "DBHelper";
        r0 = r0.toString();	 Catch:{ all -> 0x0139 }
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0139 }
        com.taobao.accs.utl.ALog.w(r1, r0, r2);	 Catch:{ all -> 0x0139 }
        if (r9 == 0) goto L_0x011f;
    L_0x0132:
        r9.close();	 Catch:{ all -> 0x0136 }
        goto L_0x011f;
    L_0x0136:
        r0 = move-exception;
        monitor-exit(r14);
        throw r0;
    L_0x0139:
        r0 = move-exception;
    L_0x013a:
        if (r9 == 0) goto L_0x013f;
    L_0x013c:
        r9.close();	 Catch:{ all -> 0x0136 }
    L_0x013f:
        throw r0;	 Catch:{ all -> 0x0136 }
    L_0x0140:
        r0 = move-exception;
        r9 = r8;
        goto L_0x013a;
    L_0x0143:
        r0 = move-exception;
        r9 = r8;
        goto L_0x0124;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.c.a.a(boolean):java.util.List<com.taobao.accs.ut.monitor.TrafficsMonitor$a>");
    }

    private synchronized void a(String str, Object[] objArr, boolean z) {
        SQLiteDatabase writableDatabase;
        try {
            this.b.add(new a(str, objArr));
            if (this.b.size() > 5 || z) {
                writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    while (this.b.size() > 0) {
                        a aVar = (a) this.b.removeFirst();
                        if (aVar.b != null) {
                            writableDatabase.execSQL(aVar.a, aVar.b);
                        } else {
                            writableDatabase.execSQL(aVar.a);
                        }
                        if (aVar.a.contains("INSERT")) {
                            this.a++;
                            if (this.a > m_AppUI.MSG_APP_SAVESCREEN) {
                                ALog.d("DBHelper", "db is full!", new Object[0]);
                                onUpgrade(writableDatabase, 0, 1);
                                this.a = 0;
                                break;
                            }
                        }
                    }
                    writableDatabase.close();
                }
            }
        } catch (Exception e) {
            ALog.d("DBHelper", e.toString(), new Object[0]);
        } catch (Throwable th) {
            writableDatabase.close();
        }
    }
}
