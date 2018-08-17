package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class IntegerColumnConverter implements ColumnConverter<Integer> {
    public Integer getFieldValue(Cursor cursor, int i) {
        return cursor.isNull(i) ? null : Integer.valueOf(cursor.getInt(i));
    }

    public Object fieldValue2DbValue(Integer num) {
        return num;
    }

    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
