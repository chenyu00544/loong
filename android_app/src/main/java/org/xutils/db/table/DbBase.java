package org.xutils.db.table;

import android.database.Cursor;
import android.text.TextUtils;
import java.util.HashMap;
import org.xutils.DbManager;
import org.xutils.DbManager.TableCreateListener;
import org.xutils.common.util.IOUtil;
import org.xutils.db.sqlite.SqlInfoBuilder;
import org.xutils.ex.DbException;

public abstract class DbBase implements DbManager {
    private final HashMap<Class<?>, TableEntity<?>> a = new HashMap();

    public <T> TableEntity<T> getTable(Class<T> cls) throws DbException {
        TableEntity<T> tableEntity;
        synchronized (this.a) {
            tableEntity = (TableEntity) this.a.get(cls);
            if (tableEntity == null) {
                try {
                    tableEntity = new TableEntity(this, cls);
                    this.a.put(cls, tableEntity);
                } catch (Throwable th) {
                    DbException dbException = new DbException(th);
                }
            }
        }
        return tableEntity;
    }

    public void dropTable(Class<?> cls) throws DbException {
        TableEntity table = getTable(cls);
        if (table.tableIsExist()) {
            execNonQuery("DROP TABLE \"" + table.getName() + "\"");
            table.a(false);
            removeTable(cls);
        }
    }

    public void dropDb() throws DbException {
        Cursor execQuery = execQuery("SELECT name FROM sqlite_master WHERE type='table' AND name<>'sqlite_sequence'");
        if (execQuery != null) {
            while (execQuery.moveToNext()) {
                try {
                    execNonQuery("DROP TABLE " + execQuery.getString(0));
                } catch (Throwable th) {
                    try {
                        DbException dbException = new DbException(th);
                    } catch (Throwable th2) {
                        IOUtil.closeQuietly(execQuery);
                    }
                }
            }
            synchronized (this.a) {
                for (TableEntity a : this.a.values()) {
                    a.a(false);
                }
                this.a.clear();
            }
            IOUtil.closeQuietly(execQuery);
        }
    }

    public void addColumn(Class<?> cls, String str) throws DbException {
        TableEntity table = getTable(cls);
        ColumnEntity columnEntity = (ColumnEntity) table.getColumnMap().get(str);
        if (columnEntity != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ").append("\"").append(table.getName()).append("\"").append(" ADD COLUMN ").append("\"").append(columnEntity.getName()).append("\"").append(" ").append(columnEntity.getColumnDbType()).append(" ").append(columnEntity.getProperty());
            execNonQuery(stringBuilder.toString());
        }
    }

    protected void createTableIfNotExist(TableEntity<?> tableEntity) throws DbException {
        if (!tableEntity.tableIsExist()) {
            synchronized (tableEntity.getClass()) {
                if (!tableEntity.tableIsExist()) {
                    execNonQuery(SqlInfoBuilder.buildCreateTableSqlInfo(tableEntity));
                    Object onCreated = tableEntity.getOnCreated();
                    if (!TextUtils.isEmpty(onCreated)) {
                        execNonQuery((String) onCreated);
                    }
                    tableEntity.a(true);
                    TableCreateListener tableCreateListener = getDaoConfig().getTableCreateListener();
                    if (tableCreateListener != null) {
                        tableCreateListener.onTableCreated(this, tableEntity);
                    }
                }
            }
        }
    }

    protected void removeTable(Class<?> cls) {
        synchronized (this.a) {
            this.a.remove(cls);
        }
    }
}
