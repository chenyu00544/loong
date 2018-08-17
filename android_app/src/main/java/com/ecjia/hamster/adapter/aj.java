package com.ecjia.hamster.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ecjia.a.f;
import com.ecjia.a.q;
import com.ecjia.hamster.model.z;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: ECJiaMsgSql */
public class aj {
    public static aj c;
    public SQLiteDatabase a = null;
    f b = null;

    private aj(Context context) {
        this.b = new f(context);
    }

    public void a(z zVar) {
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()).toString();
        this.a = this.b.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("msgtitle", zVar.n());
        contentValues.put("msgcontent", zVar.o());
        contentValues.put("msgcustom", zVar.m());
        contentValues.put("msgtime", str);
        contentValues.put("msgtype", zVar.j());
        contentValues.put("msgurl", zVar.k());
        contentValues.put("msgActivity", zVar.l());
        contentValues.put("msg_id", zVar.i());
        contentValues.put("open_type", zVar.g());
        contentValues.put("category_id", zVar.c());
        contentValues.put("webUrl", zVar.d());
        contentValues.put("goods_id_comment", zVar.e());
        contentValues.put("goods_id", zVar.b());
        contentValues.put("order_id", zVar.f());
        contentValues.put("keyword", zVar.q());
        contentValues.put("un_read", Integer.valueOf(zVar.a()));
        q.a("un_read==" + zVar.a());
        this.a.insert("msginfo", "id", contentValues);
        this.a.close();
    }

    public Cursor a() {
        this.a = this.b.getReadableDatabase();
        return this.a.rawQuery("select * from msginfo order by id desc", null);
    }

    public int b() {
        this.a = this.b.getReadableDatabase();
        Cursor rawQuery = this.a.rawQuery("select * from msginfo where un_read=1", null);
        int count = rawQuery.getCount();
        rawQuery.close();
        this.a.close();
        return count;
    }

    public static aj a(Context context) {
        if (c == null) {
            c = new aj(context);
        }
        return c;
    }

    public void a(String str, int i) {
        this.a = this.b.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("un_read", Integer.valueOf(i));
        new String[1][0] = "\"" + str + "\"";
        this.a.update("msginfo", contentValues, "msg_id=?", new String[]{str});
        q.a("执行更新语句 msg_id=" + str);
    }
}
