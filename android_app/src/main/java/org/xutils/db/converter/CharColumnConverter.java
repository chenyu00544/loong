package org.xutils.db.converter;

import android.database.Cursor;
import org.xutils.db.sqlite.ColumnDbType;

public class CharColumnConverter implements ColumnConverter<Character> {
    public Character getFieldValue(Cursor cursor, int i) {
        return cursor.isNull(i) ? null : Character.valueOf((char) cursor.getInt(i));
    }

    public Object fieldValue2DbValue(Character ch) {
        if (ch == null) {
            return null;
        }
        return Integer.valueOf(ch.charValue());
    }

    public ColumnDbType getColumnDbType() {
        return ColumnDbType.INTEGER;
    }
}
