package org.xutils.db.sqlite;

import android.text.TextUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.table.ColumnUtils;

public class WhereBuilder {
    private final List<String> a = new ArrayList();

    private WhereBuilder() {
    }

    public static WhereBuilder b() {
        return new WhereBuilder();
    }

    public static WhereBuilder b(String str, String str2, Object obj) {
        WhereBuilder whereBuilder = new WhereBuilder();
        whereBuilder.a(null, str, str2, obj);
        return whereBuilder;
    }

    public WhereBuilder and(String str, String str2, Object obj) {
        a(this.a.size() == 0 ? null : "AND", str, str2, obj);
        return this;
    }

    public WhereBuilder and(WhereBuilder whereBuilder) {
        return expr((this.a.size() == 0 ? " " : "AND ") + "(" + whereBuilder.toString() + ")");
    }

    public WhereBuilder or(String str, String str2, Object obj) {
        a(this.a.size() == 0 ? null : "OR", str, str2, obj);
        return this;
    }

    public WhereBuilder or(WhereBuilder whereBuilder) {
        return expr((this.a.size() == 0 ? " " : "OR ") + "(" + whereBuilder.toString() + ")");
    }

    public WhereBuilder expr(String str) {
        this.a.add(" " + str);
        return this;
    }

    public int getWhereItemSize() {
        return this.a.size();
    }

    public String toString() {
        if (this.a.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : this.a) {
            stringBuilder.append(append);
        }
        return stringBuilder.toString();
    }

    private void a(String str, String str2, String str3, Object obj) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a.size() > 0) {
            stringBuilder.append(" ");
        }
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str).append(" ");
        }
        stringBuilder.append("\"").append(str2).append("\"");
        if ("!=".equals(str3)) {
            str3 = "<>";
        } else if ("==".equals(str3)) {
            str3 = "=";
        }
        if (obj != null) {
            stringBuilder.append(" ").append(str3).append(" ");
            int length;
            List arrayList;
            List list;
            Object convert2DbValueIfNeeded;
            String obj2;
            if ("IN".equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    obj = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    length = Array.getLength(obj);
                    arrayList = new ArrayList(length);
                    while (i < length) {
                        arrayList.add(Array.get(obj, i));
                        i++;
                    }
                    list = arrayList;
                } else {
                    obj = null;
                }
                if (r12 != null) {
                    StringBuilder stringBuilder2 = new StringBuilder("(");
                    for (Object convert2DbValueIfNeeded2 : r12) {
                        convert2DbValueIfNeeded2 = ColumnUtils.convert2DbValueIfNeeded(convert2DbValueIfNeeded2);
                        if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(convert2DbValueIfNeeded2.getClass()))) {
                            obj2 = convert2DbValueIfNeeded2.toString();
                            if (obj2.indexOf(39) != -1) {
                                obj2 = obj2.replace("'", "''");
                            }
                            stringBuilder2.append("'").append(obj2).append("'");
                        } else {
                            stringBuilder2.append(convert2DbValueIfNeeded2);
                        }
                        stringBuilder2.append(",");
                    }
                    stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
                    stringBuilder2.append(")");
                    stringBuilder.append(stringBuilder2.toString());
                } else {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
            } else if ("BETWEEN".equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    obj = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    length = Array.getLength(obj);
                    arrayList = new ArrayList(length);
                    while (i < length) {
                        arrayList.add(Array.get(obj, i));
                        i++;
                    }
                    list = arrayList;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    Iterator it = obj.iterator();
                    if (it.hasNext()) {
                        Object next = it.next();
                        if (it.hasNext()) {
                            convert2DbValueIfNeeded2 = it.next();
                            next = ColumnUtils.convert2DbValueIfNeeded(next);
                            Object convert2DbValueIfNeeded3 = ColumnUtils.convert2DbValueIfNeeded(convert2DbValueIfNeeded2);
                            if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(next.getClass()))) {
                                obj2 = next.toString();
                                if (obj2.indexOf(39) != -1) {
                                    obj2 = obj2.replace("'", "''");
                                }
                                String obj3 = convert2DbValueIfNeeded3.toString();
                                if (obj3.indexOf(39) != -1) {
                                    obj3 = obj3.replace("'", "''");
                                }
                                stringBuilder.append("'").append(obj2).append("'");
                                stringBuilder.append(" AND ");
                                stringBuilder.append("'").append(obj3).append("'");
                            } else {
                                stringBuilder.append(next);
                                stringBuilder.append(" AND ");
                                stringBuilder.append(convert2DbValueIfNeeded3);
                            }
                        } else {
                            throw new IllegalArgumentException("value must have tow items.");
                        }
                    }
                    throw new IllegalArgumentException("value must have tow items.");
                }
                throw new IllegalArgumentException("value must be an Array or an Iterable.");
            } else {
                convert2DbValueIfNeeded2 = ColumnUtils.convert2DbValueIfNeeded(obj);
                if (ColumnDbType.TEXT.equals(ColumnConverterFactory.getDbColumnType(convert2DbValueIfNeeded2.getClass()))) {
                    obj2 = convert2DbValueIfNeeded2.toString();
                    if (obj2.indexOf(39) != -1) {
                        obj2 = obj2.replace("'", "''");
                    }
                    stringBuilder.append("'").append(obj2).append("'");
                } else {
                    stringBuilder.append(convert2DbValueIfNeeded2);
                }
            }
        } else if ("=".equals(str3)) {
            stringBuilder.append(" IS NULL");
        } else if ("<>".equals(str3)) {
            stringBuilder.append(" IS NOT NULL");
        } else {
            stringBuilder.append(" ").append(str3).append(" NULL");
        }
        this.a.add(stringBuilder.toString());
    }
}
