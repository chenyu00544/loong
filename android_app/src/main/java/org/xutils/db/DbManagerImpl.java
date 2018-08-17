package org.xutils.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build.VERSION;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.DbManager.DbOpenListener;
import org.xutils.DbManager.DbUpgradeListener;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.DbBase;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

public final class DbManagerImpl extends DbBase {
    private static final HashMap<DaoConfig, DbManagerImpl> a = new HashMap();
    private SQLiteDatabase b;
    private DaoConfig c;
    private boolean d;

    private DbManagerImpl(DaoConfig daoConfig) {
        if (daoConfig == null) {
            throw new IllegalArgumentException("daoConfig may not be null");
        }
        this.c = daoConfig;
        this.d = daoConfig.isAllowTransaction();
        this.b = a(daoConfig);
        DbOpenListener dbOpenListener = daoConfig.getDbOpenListener();
        if (dbOpenListener != null) {
            dbOpenListener.onDbOpened(this);
        }
    }

    public static synchronized DbManager getInstance(DaoConfig daoConfig) {
        DbManager dbManager;
        synchronized (DbManagerImpl.class) {
            if (daoConfig == null) {
                daoConfig = new DaoConfig();
            }
            dbManager = (DbManagerImpl) a.get(daoConfig);
            if (dbManager == null) {
                dbManager = new DbManagerImpl(daoConfig);
                a.put(daoConfig, dbManager);
            } else {
                dbManager.c = daoConfig;
            }
            SQLiteDatabase sQLiteDatabase = dbManager.b;
            int version = sQLiteDatabase.getVersion();
            int dbVersion = daoConfig.getDbVersion();
            if (version != dbVersion) {
                if (version != 0) {
                    DbUpgradeListener dbUpgradeListener = daoConfig.getDbUpgradeListener();
                    if (dbUpgradeListener != null) {
                        dbUpgradeListener.onUpgrade(dbManager, version, dbVersion);
                    } else {
                        try {
                            dbManager.dropDb();
                        } catch (Throwable e) {
                            LogUtil.e(e.getMessage(), e);
                        }
                    }
                }
                sQLiteDatabase.setVersion(dbVersion);
            }
        }
        return dbManager;
    }

    public SQLiteDatabase getDatabase() {
        return this.b;
    }

    public DaoConfig getDaoConfig() {
        return this.c;
    }

    public void saveOrUpdate(Object obj) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    createTableIfNotExist(table);
                    for (Object a : list) {
                        a(table, a);
                    }
                } else {
                    return;
                }
            }
            table = getTable(obj.getClass());
            createTableIfNotExist(table);
            a(table, obj);
            b();
            c();
        } finally {
            c();
        }
    }

    public void replace(Object obj) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    createTableIfNotExist(table);
                    for (Object buildReplaceSqlInfo : list) {
                        execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(table, buildReplaceSqlInfo));
                    }
                } else {
                    return;
                }
            }
            table = getTable(obj.getClass());
            createTableIfNotExist(table);
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(table, obj));
            b();
            c();
        } finally {
            c();
        }
    }

    public void save(Object obj) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    createTableIfNotExist(table);
                    for (Object buildInsertSqlInfo : list) {
                        execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(table, buildInsertSqlInfo));
                    }
                } else {
                    return;
                }
            }
            table = getTable(obj.getClass());
            createTableIfNotExist(table);
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(table, obj));
            b();
            c();
        } finally {
            c();
        }
    }

    public boolean saveBindingId(Object obj) throws DbException {
        boolean z = false;
        try {
            a();
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (list.isEmpty()) {
                    return z;
                }
                TableEntity table = getTable(list.get(0).getClass());
                createTableIfNotExist(table);
                for (Object b : list) {
                    if (!b(table, b)) {
                        throw new DbException("saveBindingId error, transaction will not commit!");
                    }
                }
            }
            TableEntity table2 = getTable(obj.getClass());
            createTableIfNotExist(table2);
            z = b(table2, obj);
            b();
            c();
            return z;
        } finally {
            c();
        }
    }

    public void deleteById(Class<?> cls, Object obj) throws DbException {
        TableEntity table = getTable(cls);
        if (table.tableIsExist()) {
            try {
                a();
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfoById(table, obj));
                b();
            } finally {
                c();
            }
        }
    }

    public void delete(Object obj) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    if (table.tableIsExist()) {
                        for (Object buildDeleteSqlInfo : list) {
                            execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(table, buildDeleteSqlInfo));
                        }
                    } else {
                        c();
                        return;
                    }
                }
                return;
            }
            table = getTable(obj.getClass());
            if (table.tableIsExist()) {
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(table, obj));
            } else {
                c();
                return;
            }
            b();
            c();
        } finally {
            c();
        }
    }

    public void delete(Class<?> cls) throws DbException {
        delete(cls, null);
    }

    public int delete(Class<?> cls, WhereBuilder whereBuilder) throws DbException {
        TableEntity table = getTable(cls);
        if (!table.tableIsExist()) {
            return 0;
        }
        try {
            a();
            int executeUpdateDelete = executeUpdateDelete(SqlInfoBuilder.buildDeleteSqlInfo(table, whereBuilder));
            b();
            return executeUpdateDelete;
        } finally {
            c();
        }
    }

    public void update(Object obj, String... strArr) throws DbException {
        try {
            a();
            TableEntity table;
            if (obj instanceof List) {
                List<Object> list = (List) obj;
                if (!list.isEmpty()) {
                    table = getTable(list.get(0).getClass());
                    if (table.tableIsExist()) {
                        for (Object buildUpdateSqlInfo : list) {
                            execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(table, buildUpdateSqlInfo, strArr));
                        }
                    } else {
                        c();
                        return;
                    }
                }
                return;
            }
            table = getTable(obj.getClass());
            if (table.tableIsExist()) {
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(table, obj, strArr));
            } else {
                c();
                return;
            }
            b();
            c();
        } finally {
            c();
        }
    }

    public int update(Class<?> cls, WhereBuilder whereBuilder, KeyValue... keyValueArr) throws DbException {
        TableEntity table = getTable(cls);
        if (!table.tableIsExist()) {
            return 0;
        }
        try {
            a();
            int executeUpdateDelete = executeUpdateDelete(SqlInfoBuilder.buildUpdateSqlInfo(table, whereBuilder, keyValueArr));
            b();
            return executeUpdateDelete;
        } finally {
            c();
        }
    }

    public <T> T findById(Class<T> cls, Object obj) throws DbException {
        T t = null;
        TableEntity table = getTable(cls);
        if (table.tableIsExist()) {
            Cursor execQuery = execQuery(Selector.a(table).where(table.getId().getName(), "=", obj).limit(1).toString());
            if (execQuery != null) {
                try {
                    if (execQuery.moveToNext()) {
                        t = a.a(table, execQuery);
                        IOUtil.closeQuietly(execQuery);
                    } else {
                        IOUtil.closeQuietly(execQuery);
                    }
                } catch (Throwable th) {
                    IOUtil.closeQuietly(execQuery);
                }
            }
        }
        return t;
    }

    public <T> T findFirst(Class<T> cls) throws DbException {
        return selector(cls).findFirst();
    }

    public <T> List<T> findAll(Class<T> cls) throws DbException {
        return selector(cls).findAll();
    }

    public <T> Selector<T> selector(Class<T> cls) throws DbException {
        return Selector.a(getTable(cls));
    }

    public DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException {
        Cursor execQuery = execQuery(sqlInfo);
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    DbModel a = a.a(execQuery);
                    IOUtil.closeQuietly(execQuery);
                    return a;
                }
                IOUtil.closeQuietly(execQuery);
            } catch (Throwable th) {
                IOUtil.closeQuietly(execQuery);
            }
        }
        return null;
    }

    public List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException {
        List<DbModel> arrayList = new ArrayList();
        Cursor execQuery = execQuery(sqlInfo);
        if (execQuery != null) {
            while (execQuery.moveToNext()) {
                try {
                    arrayList.add(a.a(execQuery));
                } catch (Throwable th) {
                    IOUtil.closeQuietly(execQuery);
                }
            }
            IOUtil.closeQuietly(execQuery);
        }
        return arrayList;
    }

    private SQLiteDatabase a(DaoConfig daoConfig) {
        File dbDir = daoConfig.getDbDir();
        if (dbDir == null || (!dbDir.exists() && !dbDir.mkdirs())) {
            return x.app().openOrCreateDatabase(daoConfig.getDbName(), 0, null);
        }
        return SQLiteDatabase.openOrCreateDatabase(new File(dbDir, daoConfig.getDbName()), null);
    }

    private void a(TableEntity<?> tableEntity, Object obj) throws DbException {
        ColumnEntity id = tableEntity.getId();
        if (!id.isAutoId()) {
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(tableEntity, obj));
        } else if (id.getColumnValue(obj) != null) {
            execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo((TableEntity) tableEntity, obj, new String[0]));
        } else {
            b(tableEntity, obj);
        }
    }

    private boolean b(TableEntity<?> tableEntity, Object obj) throws DbException {
        ColumnEntity id = tableEntity.getId();
        if (id.isAutoId()) {
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(tableEntity, obj));
            long a = a(tableEntity.getName());
            if (a == -1) {
                return false;
            }
            id.setAutoIdValue(obj, a);
            return true;
        }
        execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(tableEntity, obj));
        return true;
    }

    private long a(String str) throws DbException {
        long j = -1;
        Cursor execQuery = execQuery("SELECT seq FROM sqlite_sequence WHERE name='" + str + "' LIMIT 1");
        if (execQuery != null) {
            try {
                if (execQuery.moveToNext()) {
                    j = execQuery.getLong(0);
                }
                IOUtil.closeQuietly(execQuery);
            } catch (Throwable th) {
                IOUtil.closeQuietly(execQuery);
            }
        }
        return j;
    }

    public void close() throws IOException {
        if (a.containsKey(this.c)) {
            a.remove(this.c);
            this.b.close();
        }
    }

    private void a() {
        if (!this.d) {
            return;
        }
        if (VERSION.SDK_INT < 16 || !this.b.isWriteAheadLoggingEnabled()) {
            this.b.beginTransaction();
        } else {
            this.b.beginTransactionNonExclusive();
        }
    }

    private void b() {
        if (this.d) {
            this.b.setTransactionSuccessful();
        }
    }

    private void c() {
        if (this.d) {
            this.b.endTransaction();
        }
    }

    public int executeUpdateDelete(SqlInfo sqlInfo) throws DbException {
        SQLiteStatement sQLiteStatement = null;
        try {
            sQLiteStatement = sqlInfo.buildStatement(this.b);
            int executeUpdateDelete = sQLiteStatement.executeUpdateDelete();
            if (sQLiteStatement != null) {
                try {
                    sQLiteStatement.releaseReference();
                } catch (Throwable th) {
                    LogUtil.e(th.getMessage(), th);
                }
            }
            return executeUpdateDelete;
        } catch (Throwable th2) {
            if (sQLiteStatement != null) {
                try {
                    sQLiteStatement.releaseReference();
                } catch (Throwable th3) {
                    LogUtil.e(th3.getMessage(), th3);
                }
            }
        }
    }

    public int executeUpdateDelete(String str) throws DbException {
        SQLiteStatement sQLiteStatement = null;
        try {
            sQLiteStatement = this.b.compileStatement(str);
            int executeUpdateDelete = sQLiteStatement.executeUpdateDelete();
            if (sQLiteStatement != null) {
                try {
                    sQLiteStatement.releaseReference();
                } catch (Throwable th) {
                    LogUtil.e(th.getMessage(), th);
                }
            }
            return executeUpdateDelete;
        } catch (Throwable th2) {
            if (sQLiteStatement != null) {
                try {
                    sQLiteStatement.releaseReference();
                } catch (Throwable th3) {
                    LogUtil.e(th3.getMessage(), th3);
                }
            }
        }
    }

    public void execNonQuery(SqlInfo sqlInfo) throws DbException {
        SQLiteStatement sQLiteStatement = null;
        try {
            sQLiteStatement = sqlInfo.buildStatement(this.b);
            sQLiteStatement.execute();
            if (sQLiteStatement != null) {
                try {
                    sQLiteStatement.releaseReference();
                } catch (Throwable th) {
                    LogUtil.e(th.getMessage(), th);
                }
            }
        } catch (Throwable th2) {
            if (sQLiteStatement != null) {
                try {
                    sQLiteStatement.releaseReference();
                } catch (Throwable th3) {
                    LogUtil.e(th3.getMessage(), th3);
                }
            }
        }
    }

    public void execNonQuery(String str) throws DbException {
        try {
            this.b.execSQL(str);
        } catch (Throwable th) {
            DbException dbException = new DbException(th);
        }
    }

    public Cursor execQuery(SqlInfo sqlInfo) throws DbException {
        try {
            return this.b.rawQuery(sqlInfo.getSql(), sqlInfo.getBindArgsAsStrArray());
        } catch (Throwable th) {
            DbException dbException = new DbException(th);
        }
    }

    public Cursor execQuery(String str) throws DbException {
        try {
            return this.b.rawQuery(str, null);
        } catch (Throwable th) {
            DbException dbException = new DbException(th);
        }
    }
}
