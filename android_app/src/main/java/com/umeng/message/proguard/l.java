package com.umeng.message.proguard;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: MsgLogStore */
public class l {
    private static final String A = " And ";
    private static final String B = " Asc ";
    private static final String C = " Desc ";
    public static final String a = "MsgLogStore.db";
    public static final int b = 5;
    public static final String c = "MsgLogStore";
    public static final String d = "MsgLogIdTypeStore";
    public static final String e = "MsgLogStoreForAgoo";
    public static final String f = "MsgLogIdTypeStoreForAgoo";
    public static final String g = "MsgConfigInfo";
    public static final String h = "InAppLogStore";
    public static final String i = "MsgId";
    public static final String j = "MsgType";
    public static final String k = "ActionType";
    public static final String l = "Time";
    public static final String m = "TaskId";
    public static final String n = "MsgStatus";
    public static final String o = "SerialNo";
    public static final String p = "AppLaunchAt";
    public static final String q = "UpdateResponse";
    public static final String r = "NumDisplay";
    public static final String s = "NumOpenFull";
    public static final String t = "NumOpenTop";
    public static final String u = "NumOpenBottom";
    public static final String v = "NumClose";
    public static final String w = "NumDuration";
    private static final String x = l.class.getName();
    private static l y;
    private Context z;

    /* compiled from: MsgLogStore */
    class l_1 implements FilenameFilter {
        final /* synthetic */ l a;

        l_1(l lVar) {
            this.a = lVar;
        }

        public boolean accept(File file, String str) {
            if (TextUtils.isEmpty(str) || !str.startsWith(MsgConstant.CACHE_LOG_FILE_PREFIX)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: MsgLogStore */
    public class a {
        public String a;
        public long b;
        public int c;
        final /* synthetic */ l d;

        public a(l lVar, String str, int i, long j) {
            this.d = lVar;
            this.a = str;
            this.c = i;
            this.b = j;
        }

        public a(l lVar, Cursor cursor) {
            this.d = lVar;
            this.a = cursor.getString(cursor.getColumnIndex(l.i));
            this.b = cursor.getLong(cursor.getColumnIndex(l.l));
            this.c = cursor.getInt(cursor.getColumnIndex("ActionType"));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(l.i, this.a);
            contentValues.put(l.l, Long.valueOf(this.b));
            contentValues.put("ActionType", Integer.valueOf(this.c));
            return contentValues;
        }
    }

    /* compiled from: MsgLogStore */
    public class b {
        public String a;
        public String b;
        public String c;
        public long d;
        final /* synthetic */ l e;

        public b(l lVar, String str, String str2, String str3, long j) {
            this.e = lVar;
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = j;
        }

        public b(l lVar, Cursor cursor) {
            this.e = lVar;
            this.a = cursor.getString(cursor.getColumnIndex(l.i));
            this.b = cursor.getString(cursor.getColumnIndex(l.m));
            this.c = cursor.getString(cursor.getColumnIndex(l.n));
            this.d = cursor.getLong(cursor.getColumnIndex(l.l));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(l.i, this.a);
            contentValues.put(l.m, this.b);
            contentValues.put(l.n, this.c);
            contentValues.put(l.l, Long.valueOf(this.d));
            return contentValues;
        }
    }

    /* compiled from: MsgLogStore */
    public class c {
        public String a;
        public String b;
        final /* synthetic */ l c;

        public c(l lVar, String str, String str2) {
            this.c = lVar;
            this.a = str;
            this.b = str2;
        }

        public c(l lVar, Cursor cursor) {
            this.c = lVar;
            this.a = cursor.getString(cursor.getColumnIndex(l.i));
            this.b = cursor.getString(cursor.getColumnIndex(l.j));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(l.i, this.a);
            contentValues.put(l.j, this.b);
            return contentValues;
        }
    }

    /* compiled from: MsgLogStore */
    public class d {
        public String a;
        public String b;
        public String c;
        final /* synthetic */ l d;

        public d(l lVar, String str, String str2, String str3) {
            this.d = lVar;
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public d(l lVar, Cursor cursor) {
            this.d = lVar;
            this.a = cursor.getString(cursor.getColumnIndex(l.i));
            this.b = cursor.getString(cursor.getColumnIndex(l.m));
            this.c = cursor.getString(cursor.getColumnIndex(l.n));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(l.i, this.a);
            contentValues.put(l.m, this.b);
            contentValues.put(l.n, this.c);
            return contentValues;
        }
    }

    public static l a(Context context) {
        if (y == null) {
            y = new l(context);
            y.h();
        }
        return y;
    }

    private l(Context context) {
        this.z = context.getApplicationContext();
    }

    private void h() {
        if (!MessageSharedPrefs.getInstance(this.z).hasTransferedCacheFileDataToSQL()) {
            File[] listFiles = this.z.getCacheDir().listFiles(new l_1(this));
            if (listFiles != null) {
                for (File file : listFiles) {
                    a(file);
                    file.delete();
                }
            }
            MessageSharedPrefs.getInstance(this.z).finishTransferedCacheFileDataToSQL();
        }
    }

    private void a(File file) {
        try {
            JSONObject jSONObject = new JSONObject(b(file));
            a(jSONObject.optString("msg_id"), jSONObject.optInt(MsgConstant.KEY_ACTION_TYPE), jSONObject.optLong("ts"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String b(File file) throws IOException {
        Throwable th;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            try {
                String str = "";
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                }
                str = stringBuilder.toString();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    public boolean a(String str, int i, long j) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        a aVar = new a(this, str, i, j);
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        return contentResolver.insert(com.umeng.message.provider.a.f, aVar.a()) != null;
    }

    public boolean a(String str, int i) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = new String[]{str, i + ""};
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        if (contentResolver.delete(com.umeng.message.provider.a.f, "MsgId=? And ActionType=?", strArr) != 1) {
            z = false;
        }
        return z;
    }

    public a a(String str) {
        a aVar = null;
        if (!TextUtils.isEmpty(str)) {
            String[] strArr = new String[0];
            ContentResolver contentResolver = this.z.getContentResolver();
            com.umeng.message.provider.a.a(this.z);
            Cursor query = contentResolver.query(com.umeng.message.provider.a.f, null, "MsgId=?", strArr, null);
            if (query.moveToFirst()) {
                aVar = new a(this, query);
            }
            if (query != null) {
                query.close();
            }
        }
        return aVar;
    }

    public ArrayList<a> a(int i) {
        if (i < 1) {
            return null;
        }
        ArrayList<a> arrayList = new ArrayList();
        com.umeng.message.provider.a.a(this.z);
        Uri build = com.umeng.message.provider.a.f.buildUpon().appendQueryParameter("limit", i + "").build();
        Cursor query = this.z.getContentResolver().query(build, null, null, null, "Time Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new a(this, query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public ArrayList<a> a() {
        ArrayList<a> arrayList = new ArrayList();
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.f, null, null, null, "Time Asc ");
        if (query == null) {
            return arrayList;
        }
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new a(this, query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        c cVar = new c(this, str, str2);
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        if (contentResolver.insert(com.umeng.message.provider.a.g, cVar.a()) != null) {
            return true;
        }
        return false;
    }

    public boolean b(String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = new String[]{str};
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        if (contentResolver.delete(com.umeng.message.provider.a.g, "MsgId=?", strArr) != 1) {
            z = false;
        }
        return z;
    }

    public ArrayList<c> b(int i) {
        if (i < 1) {
            return null;
        }
        ArrayList<c> arrayList = new ArrayList();
        com.umeng.message.provider.a.a(this.z);
        Uri build = com.umeng.message.provider.a.g.buildUpon().appendQueryParameter("limit", i + "").build();
        Cursor query = this.z.getContentResolver().query(build, null, null, null, "MsgId Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new c(this, query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public ArrayList<c> b() {
        ArrayList<c> arrayList = new ArrayList();
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.g, null, null, null, "MsgId Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new c(this, query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public boolean a(String str, String str2, String str3, long j) {
        return false;
    }

    public boolean b(String str, String str2) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = new String[]{str, str2};
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        if (contentResolver.delete(com.umeng.message.provider.a.h, "MsgId=? And MsgStatus=?", strArr) != 1) {
            z = false;
        }
        return z;
    }

    public b c(String str) {
        b bVar = null;
        if (!TextUtils.isEmpty(str)) {
            String[] strArr = new String[]{str};
            ContentResolver contentResolver = this.z.getContentResolver();
            com.umeng.message.provider.a.a(this.z);
            Cursor query = contentResolver.query(com.umeng.message.provider.a.h, null, "MsgId=?", strArr, null);
            if (query.moveToFirst()) {
                bVar = new b(this, query);
            }
            if (query != null) {
                query.close();
            }
        }
        return bVar;
    }

    public ArrayList<b> c(int i) {
        if (i < 1) {
            return null;
        }
        ArrayList<b> arrayList = new ArrayList();
        com.umeng.message.provider.a.a(this.z);
        Uri build = com.umeng.message.provider.a.h.buildUpon().appendQueryParameter("limit", i + "").build();
        Cursor query = this.z.getContentResolver().query(build, null, null, null, "Time Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new b(this, query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public ArrayList<b> c() {
        ArrayList<b> arrayList = new ArrayList();
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.h, null, null, null, "Time Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new b(this, query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public boolean a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        d dVar = new d(this, str, str2, str3);
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        if (contentResolver.insert(com.umeng.message.provider.a.i, dVar.a()) != null) {
            return true;
        }
        return false;
    }

    public boolean d(String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] strArr = new String[]{str};
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        if (contentResolver.delete(com.umeng.message.provider.a.i, "MsgId=?", strArr) != 1) {
            z = false;
        }
        return z;
    }

    public ArrayList<d> d(int i) {
        if (i < 1) {
            return null;
        }
        ArrayList<d> arrayList = new ArrayList();
        com.umeng.message.provider.a.a(this.z);
        Uri build = com.umeng.message.provider.a.i.buildUpon().appendQueryParameter("limit", i + "").build();
        Cursor query = this.z.getContentResolver().query(build, null, null, null, "MsgId Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new d(this, query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public ArrayList<d> d() {
        ArrayList<d> arrayList = new ArrayList();
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.i, null, null, null, "MsgId Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new d(this, query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public int e() {
        int i;
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.j, new String[]{o}, null, null, null);
        if (query.moveToFirst()) {
            i = query.getInt(query.getColumnIndex(o));
        } else {
            i = 0;
        }
        if (query != null) {
            query.close();
        }
        return i;
    }

    public void e(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(o, i + "");
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        contentResolver.update(com.umeng.message.provider.a.j, contentValues, null, null);
    }

    public long f() {
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.j, new String[]{p}, null, null, null);
        if (query == null) {
            return 0;
        }
        long j;
        if (query.moveToFirst()) {
            j = query.getLong(query.getColumnIndex(p));
        } else {
            j = 0;
        }
        if (query != null) {
            query.close();
        }
        Log.d(x, "appLaunchAt=" + j);
        return j;
    }

    public void a(long j) {
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.j, new String[]{p}, null, null, null);
        int count = query.getCount();
        if (query != null) {
            query.close();
        }
        if (count > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(p, j + "");
            ContentResolver contentResolver2 = this.z.getContentResolver();
            com.umeng.message.provider.a.a(this.z);
            contentResolver2.update(com.umeng.message.provider.a.j, contentValues, null, null);
            return;
        }
        contentValues = new ContentValues();
        contentValues.put(p, j + "");
        contentResolver2 = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        contentResolver2.insert(com.umeng.message.provider.a.j, contentValues);
    }

    public Object g() {
        String str = null;
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.j, new String[]{q}, null, null, null);
        if (query.moveToFirst()) {
            str = query.getString(query.getColumnIndex(q));
        }
        if (query != null) {
            query.close();
        }
        Log.d(x, "updateResponse=" + str);
        return h.f(str);
    }

    public void a(Object obj) {
        String a = h.a(obj);
        ContentValues contentValues = new ContentValues();
        contentValues.put(q, a);
        ContentResolver contentResolver = this.z.getContentResolver();
        com.umeng.message.provider.a.a(this.z);
        contentResolver.update(com.umeng.message.provider.a.j, contentValues, null, null);
    }
}
