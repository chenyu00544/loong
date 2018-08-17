package org.xutils.db.converter;

import android.database.Cursor;
import java.sql.Date;
import org.xutils.db.sqlite.ColumnDbType;

public class SqlDateColumnConverter implements ColumnConverter<Date> {
    public Date getFieldValue(Cursor cursor, int i) {
        return cursor.isNull(i) ? null : new Date(cursor.getLong(i));
    }

    public Object fieldValue2DbValue(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime());
    }

    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
