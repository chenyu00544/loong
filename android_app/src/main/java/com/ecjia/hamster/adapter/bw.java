package com.ecjia.hamster.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ecjia.a.f;
import com.ecjia.a.q;
import com.ecjia.hamster.model.ba;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: ECJiaSweepSql */
public class bw {
    private static bw c;
    public SQLiteDatabase a = null;
    f b = null;

    private bw(Context context) {
        this.b = new f(context);
    }

    public static bw a(Context context) {
        if (c == null) {
            c = new bw(context);
        }
        return c;
    }

    public void a(ba baVar) {
        a(baVar.b());
        String str = new SimpleDateFormat("yyyy年MM月dd日").format(new Date()).toString();
        this.a = this.b.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sweep_title", baVar.a());
        contentValues.put("sweep_content", baVar.b());
        contentValues.put("save_date", str);
        this.a.insert("sweephistory", "id", contentValues);
        this.a.close();
    }

    public Cursor a() {
        this.a = this.b.getReadableDatabase();
        return this.a.rawQuery("select * from sweephistory order by id desc", null);
    }

    public void b() {
        this.a = this.b.getReadableDatabase();
        this.a.execSQL("delete from sweephistory");
        this.a.close();
    }

    public void a(String str) {
        this.a = this.b.getReadableDatabase();
        this.a.execSQL("delete from sweephistory where sweep_content='" + str + "'");
        q.a("删除一条记录");
        this.a.close();
    }
}
