package org.xutils.db.sqlite;

public enum ColumnDbType {
    INTEGER("INTEGER"),
    REAL("REAL"),
    TEXT("TEXT"),
    BLOB("BLOB");
    
    private String a;

    private ColumnDbType(String str) {
        this.a = str;
    }

    public String toString() {
        return this.a;
    }
}
