package com.ecjia.hamster.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ecjia.a.f;
import com.ecjia.a.q;
import com.ecjia.hamster.model.p;
import org.json.JSONException;

/* compiled from: ECJiaSqlcl */
public class bu {
    public static SQLiteDatabase a = null;
    f b = null;

    public bu(Context context) {
        this.b = new f(context);
    }

    public void a(p pVar) {
        a = this.b.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("goods_id", Integer.valueOf(pVar.t()));
        try {
            contentValues.put("goods", pVar.w().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a.insert("goods_history", "id", contentValues);
        a.close();
    }

    public Cursor a() {
        a = this.b.getReadableDatabase();
        return a.rawQuery("select * from goods_history order by id desc", null);
    }

    public boolean a(int i) {
        boolean z = false;
        a = this.b.getReadableDatabase();
        if (a.rawQuery("select * from goods_history where goods_id=" + i, null).getCount() != 0) {
            z = true;
        }
        a.close();
        return z;
    }

    public void b() {
        a = this.b.getReadableDatabase();
        a.execSQL("delete from goods_history");
        a.close();
    }

    public void b(int i) {
        a = this.b.getReadableDatabase();
        a.execSQL("delete from goods_history where goods_id=" + i);
        q.a("删除一条记录");
        a.close();
    }
}
