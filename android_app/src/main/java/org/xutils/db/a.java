package org.xutils.db;

import android.database.Cursor;
import java.util.HashMap;
import org.xutils.db.table.ColumnEntity;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;

/* compiled from: CursorUtils */
final class a {
    public static <T> T a(TableEntity<T> tableEntity, Cursor cursor) throws Throwable {
        T createEntity = tableEntity.createEntity();
        HashMap columnMap = tableEntity.getColumnMap();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            ColumnEntity columnEntity = (ColumnEntity) columnMap.get(cursor.getColumnName(i));
            if (columnEntity != null) {
                columnEntity.setValueFromCursor(createEntity, cursor, i);
            }
        }
        return createEntity;
    }

    public static DbModel a(Cursor cursor) {
        DbModel dbModel = new DbModel();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            dbModel.add(cursor.getColumnName(i), cursor.getString(i));
        }
        return dbModel;
    }
}
