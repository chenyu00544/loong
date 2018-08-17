package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class BooleanColumnConverter implements ColumnConverter<Boolean> {
    public Boolean getFieldValue(Cursor cursor, int i) {
        boolean z = true;
        if (cursor.isNull(i)) {
            return null;
        }
        if (cursor.getInt(i) != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public Object fieldValue2DbValue(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return Integer.valueOf(bool.booleanValue() ? 1 : 0);
    }

    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
