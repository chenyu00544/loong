package org.xutils.db;

import android.database.Cursor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.xutils.common.util.IOUtil;
import org.xutils.db.Selector.OrderBy;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

public final class DbModelSelector {
    private String[] a;
    private String b;
    private WhereBuilder c;
    private Selector<?> d;

    protected DbModelSelector(Selector<?> selector, String str) {
        this.d = selector;
        this.b = str;
    }

    protected DbModelSelector(Selector<?> selector, String[] strArr) {
        this.d = selector;
        this.a = strArr;
    }

    public DbModelSelector where(WhereBuilder whereBuilder) {
        this.d.where(whereBuilder);
        return this;
    }

    public DbModelSelector where(String str, String str2, Object obj) {
        this.d.where(str, str2, obj);
        return this;
    }

    public DbModelSelector and(String str, String str2, Object obj) {
        this.d.and(str, str2, obj);
        return this;
    }

    public DbModelSelector and(WhereBuilder whereBuilder) {
        this.d.and(whereBuilder);
        return this;
    }

    public DbModelSelector or(String str, String str2, Object obj) {
        this.d.or(str, str2, obj);
        return this;
    }

    public DbModelSelector or(WhereBuilder whereBuilder) {
        this.d.or(whereBuilder);
        return this;
    }

    public DbModelSelector expr(String str) {
        this.d.expr(str);
        return this;
    }

    public DbModelSelector groupBy(String str) {
        this.b = str;
        return this;
    }

    public DbModelSelector having(WhereBuilder whereBuilder) {
        this.c = whereBuilder;
        return this;
    }

    public DbModelSelector select(String... strArr) {
        this.a = strArr;
        return this;
    }

    public DbModelSelector orderBy(String str) {
        this.d.orderBy(str);
        return this;
    }

    public DbModelSelector orderBy(String str, boolean z) {
        this.d.orderBy(str, z);
        return this;
    }

    public DbModelSelector limit(int i) {
        this.d.limit(i);
        return this;
    }

    public DbModelSelector offset(int i) {
        this.d.offset(i);
        return this;
    }

    public TableEntity<?> getTable() {
        return this.d.getTable();
    }

    public DbModel findFirst() throws DbException {
        DbModel dbModel = null;
        TableEntity table = this.d.getTable();
        if (table.tableIsExist()) {
            limit(1);
            Cursor execQuery = table.getDb().execQuery(toString());
            if (execQuery != null) {
                try {
                    if (execQuery.moveToNext()) {
                        dbModel = a.a(execQuery);
                        IOUtil.closeQuietly(execQuery);
                    } else {
                        IOUtil.closeQuietly(execQuery);
                    }
                } catch (Throwable th) {
                    IOUtil.closeQuietly(execQuery);
                }
            }
        }
        return dbModel;
    }

    public List<DbModel> findAll() throws DbException {
        List<DbModel> list = null;
        TableEntity table = this.d.getTable();
        if (table.tableIsExist()) {
            Cursor execQuery = table.getDb().execQuery(toString());
            if (execQuery != null) {
                try {
                    list = new ArrayList();
                    while (execQuery.moveToNext()) {
                        list.add(a.a(execQuery));
                    }
                    IOUtil.closeQuietly(execQuery);
                } catch (Throwable th) {
                    IOUtil.closeQuietly(execQuery);
                }
            }
        }
        return list;
    }

    public String toString() {
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        if (this.a != null && this.a.length > 0) {
            for (String append : this.a) {
                stringBuilder.append(append);
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } else if (TextUtils.isEmpty(this.b)) {
            stringBuilder.append("*");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(" FROM ").append("\"").append(this.d.getTable().getName()).append("\"");
        WhereBuilder whereBuilder = this.d.getWhereBuilder();
        if (whereBuilder != null && whereBuilder.getWhereItemSize() > 0) {
            stringBuilder.append(" WHERE ").append(whereBuilder.toString());
        }
        if (!TextUtils.isEmpty(this.b)) {
            stringBuilder.append(" GROUP BY ").append("\"").append(this.b).append("\"");
            if (this.c != null && this.c.getWhereItemSize() > 0) {
                stringBuilder.append(" HAVING ").append(this.c.toString());
            }
        }
        List orderByList = this.d.getOrderByList();
        if (orderByList != null && orderByList.size() > 0) {
            for (i = 0; i < orderByList.size(); i++) {
                stringBuilder.append(" ORDER BY ").append(((OrderBy) orderByList.get(i)).toString()).append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (this.d.getLimit() > 0) {
            stringBuilder.append(" LIMIT ").append(this.d.getLimit());
            stringBuilder.append(" OFFSET ").append(this.d.getOffset());
        }
        return stringBuilder.toString();
    }
}
