package com.ecjia.a.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ecjia.a.f;
import com.ecjia.a.q;
import com.umeng.socialize.common.SocializeConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaShakeHistorySql */
public class a {
    private static a c;
    public SQLiteDatabase a = null;
    f b = null;

    private a(Context context) {
        this.b = new f(context);
    }

    public static a a(Context context) {
        if (c == null) {
            c = new a(context);
        }
        return c;
    }

    public void a(com.ecjia.component.a.w.a aVar) {
        String str = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(new Date()).toString();
        this.a = this.b.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        aVar.a(str);
        contentValues.put("type", aVar.c());
        contentValues.put("time", aVar.b());
        contentValues.put(SocializeConstants.TENCENT_UID, Integer.valueOf(aVar.a()));
        String c = aVar.c();
        Object obj = -1;
        switch (c.hashCode()) {
            case 93921311:
                if (c.equals("bonus")) {
                    obj = null;
                    break;
                }
                break;
            case 570086828:
                if (c.equals("integral")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                try {
                    contentValues.put("content", aVar.d().f().toString());
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                    break;
                }
            case 1:
                contentValues.put("content", aVar.f());
                break;
        }
        this.a.insert("shakehistory", "id", contentValues);
        this.a.close();
        q.a("插入摇一摇数据成功");
    }

    public Cursor a() {
        this.a = this.b.getReadableDatabase();
        return this.a.rawQuery("select * from shakehistory order by id desc", null);
    }

    public List<com.ecjia.component.a.w.a> a(int i) {
        List<com.ecjia.component.a.w.a> arrayList = new ArrayList();
        Cursor a = a();
        while (a.moveToNext()) {
            com.ecjia.component.a.w.a aVar = new com.ecjia.component.a.w.a();
            if (a.getInt(4) == i) {
                aVar.a(i);
                aVar.c(a.getString(1));
                if (a.getString(1).equals("integral")) {
                    aVar.d(a.getString(2));
                } else {
                    try {
                        aVar.a(com.ecjia.component.a.w.a.a.a(new JSONObject(a.getString(2))));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                arrayList.add(aVar);
            }
        }
        a.close();
        this.a.close();
        q.a("shakes.size()====" + arrayList.size());
        return arrayList;
    }
}
