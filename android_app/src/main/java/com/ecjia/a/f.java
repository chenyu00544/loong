package com.ecjia.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: ECJiaDBhelp */
public class f extends SQLiteOpenHelper {
    public f(Context context) {
        super(context, "LastBrowse.db", null, 6);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS goods_history(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,goods_id INTEGER NOT NULL,goods text not null )");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS msginfo(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,msgtitle VARCHAR(20) NOT NULL,msgcontent VARCHAR(80) NOT NULL,msgcustom VARCHAR(80) NOT NULL,msgtime  VARCHAR(20) NOT NULL,msgtype VARCHAR(20) NOT NULL,msgurl VARCHAR(80),msgActivity VARCHAR(20),msg_id VARCHAR(32) NOT NULL,open_type VARCHAR(20),category_id VARCHAR(20),webUrl VARCHAR(50),goods_id_comment VARCHAR(20),goods_id VARCHAR(20),order_id VARCHAR(20),keyword VARCHAR(20),un_read INTEGER)");
        sQLiteDatabase.execSQL("CREATE TABLE sweephistory(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,sweep_title VARCHAR(20) NOT NULL,sweep_content VARCHAR(50) NOT NULL,save_date VARCHER(20) NOT NULL)");
        sQLiteDatabase.execSQL("CREATE TABLE if not exists shakehistory(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,type VARCHAR(20) NOT NULL,content text,time VARCHAR(20) NOT NULL,user_id INTEGER NOT NULL)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i <= 3) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS goods_history(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,goods_id INTEGER NOT NULL,goods text not null )");
        }
        if (i < 5) {
            sQLiteDatabase.execSQL("ALTER TABLE msginfo ADD keyword VARCHAR(20)");
            sQLiteDatabase.execSQL("ALTER TABLE msginfo ADD un_read INTEGER");
        }
        if (i < 6) {
            sQLiteDatabase.execSQL("CREATE TABLE if not exists shakehistory(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,type VARCHAR(20) NOT NULL,content text,time VARCHAR(20) NOT NULL,user_id INTEGER NOT NULL)");
        }
    }
}
