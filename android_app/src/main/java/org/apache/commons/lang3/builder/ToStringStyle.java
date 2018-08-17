package org.apache.commons.lang3.builder;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang3.ObjectUtils;

public abstract class ToStringStyle implements Serializable {
    public static final ToStringStyle DEFAULT_STYLE = new a();
    public static final ToStringStyle MULTI_LINE_STYLE = new b();
    public static final ToStringStyle NO_FIELD_NAMES_STYLE = new c();
    public static final ToStringStyle SHORT_PREFIX_STYLE = new d();
    public static final ToStringStyle SIMPLE_STYLE = new e();
    private static final ThreadLocal<WeakHashMap<Object, Object>> a = new ThreadLocal();
    private boolean b = true;
    private boolean c = true;
    private boolean d = false;
    private boolean e = true;
    private String f = "[";
    private String g = "]";
    private String h = "=";
    private boolean i = false;
    private boolean j = false;
    private String k = ",";
    private String l = "{";
    private String m = ",";
    private boolean n = true;
    private String o = "}";
    private boolean p = true;
    private String q = "<null>";
    private String r = "<size=";
    private String s = ">";
    private String t = "<";
    private String u = ">";

    private static final class a extends ToStringStyle {
        a() {
        }
    }

    private static final class b extends ToStringStyle {
        b() {
            setContentStart("[");
            setFieldSeparator(org.apache.commons.lang3.d.F + "  ");
            setFieldSeparatorAtStart(true);
            setContentEnd(org.apache.commons.lang3.d.F + "]");
        }
    }

    private static final class c extends ToStringStyle {
        c() {
            setUseFieldNames(false);
        }
    }

    private static final class d extends ToStringStyle {
        d() {
            setUseShortClassName(true);
            setUseIdentityHashCode(false);
        }
    }

    private static final class e extends ToStringStyle {
        e() {
            setUseClassName(false);
            setUseIdentityHashCode(false);
            setUseFieldNames(false);
            setContentStart("");
            setContentEnd("");
        }
    }

    static Map<Object, Object> a() {
        return (Map) a.get();
    }

    static boolean a(Object obj) {
        Map a = a();
        return a != null && a.containsKey(obj);
    }

    static void b(Object obj) {
        if (obj != null) {
            if (a() == null) {
                a.set(new WeakHashMap());
            }
            a().put(obj, null);
        }
    }

    static void c(Object obj) {
        if (obj != null) {
            Map a = a();
            if (a != null) {
                a.remove(obj);
                if (a.isEmpty()) {
                    a.remove();
                }
            }
        }
    }

    protected ToStringStyle() {
    }

    public void appendSuper(StringBuffer stringBuffer, String str) {
        appendToString(stringBuffer, str);
    }

    public void appendToString(StringBuffer stringBuffer, String str) {
        if (str != null) {
            int indexOf = str.indexOf(this.f) + this.f.length();
            int lastIndexOf = str.lastIndexOf(this.g);
            if (indexOf != lastIndexOf && indexOf >= 0 && lastIndexOf >= 0) {
                String substring = str.substring(indexOf, lastIndexOf);
                if (this.i) {
                    a(stringBuffer);
                }
                stringBuffer.append(substring);
                d(stringBuffer);
            }
        }
    }

    public void appendStart(StringBuffer stringBuffer, Object obj) {
        if (obj != null) {
            a(stringBuffer, obj);
            b(stringBuffer, obj);
            b(stringBuffer);
            if (this.i) {
                d(stringBuffer);
            }
        }
    }

    public void appendEnd(StringBuffer stringBuffer, Object obj) {
        if (!this.j) {
            a(stringBuffer);
        }
        c(stringBuffer);
        c(obj);
    }

    protected void a(StringBuffer stringBuffer) {
        Object obj = null;
        int length = stringBuffer.length();
        int length2 = this.k.length();
        if (length > 0 && length2 > 0 && length >= length2) {
            for (int i = 0; i < length2; i++) {
                if (stringBuffer.charAt((length - 1) - i) != this.k.charAt((length2 - 1) - i)) {
                    break;
                }
            }
            int i2 = 1;
            if (obj != null) {
                stringBuffer.setLength(length - length2);
            }
        }
    }

    public void append(StringBuffer stringBuffer, String str, Object obj, Boolean bool) {
        b(stringBuffer, str);
        if (obj == null) {
            a(stringBuffer, str);
        } else {
            a(stringBuffer, str, obj, a(bool));
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, Object obj, boolean z) {
        if (!a(obj) || (obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof Character)) {
            b(obj);
            try {
                if (obj instanceof Collection) {
                    if (z) {
                        a(stringBuffer, str, (Collection) obj);
                    } else {
                        b(stringBuffer, str, ((Collection) obj).size());
                    }
                } else if (obj instanceof Map) {
                    if (z) {
                        a(stringBuffer, str, (Map) obj);
                    } else {
                        b(stringBuffer, str, ((Map) obj).size());
                    }
                } else if (obj instanceof long[]) {
                    if (z) {
                        a(stringBuffer, str, (long[]) obj);
                    } else {
                        b(stringBuffer, str, (long[]) obj);
                    }
                } else if (obj instanceof int[]) {
                    if (z) {
                        a(stringBuffer, str, (int[]) obj);
                    } else {
                        b(stringBuffer, str, (int[]) obj);
                    }
                } else if (obj instanceof short[]) {
                    if (z) {
                        a(stringBuffer, str, (short[]) obj);
                    } else {
                        b(stringBuffer, str, (short[]) obj);
                    }
                } else if (obj instanceof byte[]) {
                    if (z) {
                        a(stringBuffer, str, (byte[]) obj);
                    } else {
                        b(stringBuffer, str, (byte[]) obj);
                    }
                } else if (obj instanceof char[]) {
                    if (z) {
                        a(stringBuffer, str, (char[]) obj);
                    } else {
                        b(stringBuffer, str, (char[]) obj);
                    }
                } else if (obj instanceof double[]) {
                    if (z) {
                        a(stringBuffer, str, (double[]) obj);
                    } else {
                        b(stringBuffer, str, (double[]) obj);
                    }
                } else if (obj instanceof float[]) {
                    if (z) {
                        a(stringBuffer, str, (float[]) obj);
                    } else {
                        b(stringBuffer, str, (float[]) obj);
                    }
                } else if (obj instanceof boolean[]) {
                    if (z) {
                        a(stringBuffer, str, (boolean[]) obj);
                    } else {
                        b(stringBuffer, str, (boolean[]) obj);
                    }
                } else if (obj.getClass().isArray()) {
                    if (z) {
                        a(stringBuffer, str, (Object[]) obj);
                    } else {
                        b(stringBuffer, str, (Object[]) obj);
                    }
                } else if (z) {
                    b(stringBuffer, str, obj);
                } else {
                    c(stringBuffer, str, obj);
                }
                c(obj);
            } catch (Throwable th) {
                c(obj);
            }
        } else {
            a(stringBuffer, str, obj);
        }
    }

    protected void a(StringBuffer stringBuffer, String str, Object obj) {
        ObjectUtils.a(stringBuffer, obj);
    }

    protected void b(StringBuffer stringBuffer, String str, Object obj) {
        stringBuffer.append(obj);
    }

    protected void a(StringBuffer stringBuffer, String str, Collection<?> collection) {
        stringBuffer.append(collection);
    }

    protected void a(StringBuffer stringBuffer, String str, Map<?, ?> map) {
        stringBuffer.append(map);
    }

    protected void c(StringBuffer stringBuffer, String str, Object obj) {
        stringBuffer.append(this.t);
        stringBuffer.append(a(obj.getClass()));
        stringBuffer.append(this.u);
    }

    public void append(StringBuffer stringBuffer, String str, long j) {
        b(stringBuffer, str);
        a(stringBuffer, str, j);
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, long j) {
        stringBuffer.append(j);
    }

    public void append(StringBuffer stringBuffer, String str, int i) {
        b(stringBuffer, str);
        a(stringBuffer, str, i);
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, int i) {
        stringBuffer.append(i);
    }

    public void append(StringBuffer stringBuffer, String str, short s) {
        b(stringBuffer, str);
        a(stringBuffer, str, s);
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, short s) {
        stringBuffer.append(s);
    }

    public void append(StringBuffer stringBuffer, String str, byte b) {
        b(stringBuffer, str);
        a(stringBuffer, str, b);
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, byte b) {
        stringBuffer.append(b);
    }

    public void append(StringBuffer stringBuffer, String str, char c) {
        b(stringBuffer, str);
        a(stringBuffer, str, c);
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, char c) {
        stringBuffer.append(c);
    }

    public void append(StringBuffer stringBuffer, String str, double d) {
        b(stringBuffer, str);
        a(stringBuffer, str, d);
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, double d) {
        stringBuffer.append(d);
    }

    public void append(StringBuffer stringBuffer, String str, float f) {
        b(stringBuffer, str);
        a(stringBuffer, str, f);
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, float f) {
        stringBuffer.append(f);
    }

    public void append(StringBuffer stringBuffer, String str, boolean z) {
        b(stringBuffer, str);
        a(stringBuffer, str, z);
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, boolean z) {
        stringBuffer.append(z);
    }

    public void append(StringBuffer stringBuffer, String str, Object[] objArr, Boolean bool) {
        b(stringBuffer, str);
        if (objArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, objArr);
        } else {
            b(stringBuffer, str, objArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, Object[] objArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            if (obj == null) {
                a(stringBuffer, str);
            } else {
                a(stringBuffer, str, obj, this.n);
            }
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, Object[] objArr) {
        b(stringBuffer, str, objArr.length);
    }

    public void append(StringBuffer stringBuffer, String str, long[] jArr, Boolean bool) {
        b(stringBuffer, str);
        if (jArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, jArr);
        } else {
            b(stringBuffer, str, jArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, long[] jArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < jArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            a(stringBuffer, str, jArr[i]);
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, long[] jArr) {
        b(stringBuffer, str, jArr.length);
    }

    public void append(StringBuffer stringBuffer, String str, int[] iArr, Boolean bool) {
        b(stringBuffer, str);
        if (iArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, iArr);
        } else {
            b(stringBuffer, str, iArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, int[] iArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < iArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            a(stringBuffer, str, iArr[i]);
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, int[] iArr) {
        b(stringBuffer, str, iArr.length);
    }

    public void append(StringBuffer stringBuffer, String str, short[] sArr, Boolean bool) {
        b(stringBuffer, str);
        if (sArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, sArr);
        } else {
            b(stringBuffer, str, sArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, short[] sArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < sArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            a(stringBuffer, str, sArr[i]);
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, short[] sArr) {
        b(stringBuffer, str, sArr.length);
    }

    public void append(StringBuffer stringBuffer, String str, byte[] bArr, Boolean bool) {
        b(stringBuffer, str);
        if (bArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, bArr);
        } else {
            b(stringBuffer, str, bArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, byte[] bArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < bArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            a(stringBuffer, str, bArr[i]);
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, byte[] bArr) {
        b(stringBuffer, str, bArr.length);
    }

    public void append(StringBuffer stringBuffer, String str, char[] cArr, Boolean bool) {
        b(stringBuffer, str);
        if (cArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, cArr);
        } else {
            b(stringBuffer, str, cArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, char[] cArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < cArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            a(stringBuffer, str, cArr[i]);
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, char[] cArr) {
        b(stringBuffer, str, cArr.length);
    }

    public void append(StringBuffer stringBuffer, String str, double[] dArr, Boolean bool) {
        b(stringBuffer, str);
        if (dArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, dArr);
        } else {
            b(stringBuffer, str, dArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, double[] dArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < dArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            a(stringBuffer, str, dArr[i]);
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, double[] dArr) {
        b(stringBuffer, str, dArr.length);
    }

    public void append(StringBuffer stringBuffer, String str, float[] fArr, Boolean bool) {
        b(stringBuffer, str);
        if (fArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, fArr);
        } else {
            b(stringBuffer, str, fArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, float[] fArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < fArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            a(stringBuffer, str, fArr[i]);
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, float[] fArr) {
        b(stringBuffer, str, fArr.length);
    }

    public void append(StringBuffer stringBuffer, String str, boolean[] zArr, Boolean bool) {
        b(stringBuffer, str);
        if (zArr == null) {
            a(stringBuffer, str);
        } else if (a(bool)) {
            a(stringBuffer, str, zArr);
        } else {
            b(stringBuffer, str, zArr);
        }
        c(stringBuffer, str);
    }

    protected void a(StringBuffer stringBuffer, String str, boolean[] zArr) {
        stringBuffer.append(this.l);
        for (int i = 0; i < zArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(this.m);
            }
            a(stringBuffer, str, zArr[i]);
        }
        stringBuffer.append(this.o);
    }

    protected void b(StringBuffer stringBuffer, String str, boolean[] zArr) {
        b(stringBuffer, str, zArr.length);
    }

    protected void a(StringBuffer stringBuffer, Object obj) {
        if (this.c && obj != null) {
            b(obj);
            if (this.d) {
                stringBuffer.append(a(obj.getClass()));
            } else {
                stringBuffer.append(obj.getClass().getName());
            }
        }
    }

    protected void b(StringBuffer stringBuffer, Object obj) {
        if (isUseIdentityHashCode() && obj != null) {
            b(obj);
            stringBuffer.append('@');
            stringBuffer.append(Integer.toHexString(System.identityHashCode(obj)));
        }
    }

    protected void b(StringBuffer stringBuffer) {
        stringBuffer.append(this.f);
    }

    protected void c(StringBuffer stringBuffer) {
        stringBuffer.append(this.g);
    }

    protected void a(StringBuffer stringBuffer, String str) {
        stringBuffer.append(this.q);
    }

    protected void d(StringBuffer stringBuffer) {
        stringBuffer.append(this.k);
    }

    protected void b(StringBuffer stringBuffer, String str) {
        if (this.b && str != null) {
            stringBuffer.append(str);
            stringBuffer.append(this.h);
        }
    }

    protected void c(StringBuffer stringBuffer, String str) {
        d(stringBuffer);
    }

    protected void b(StringBuffer stringBuffer, String str, int i) {
        stringBuffer.append(this.r);
        stringBuffer.append(i);
        stringBuffer.append(this.s);
    }

    protected boolean a(Boolean bool) {
        if (bool == null) {
            return this.p;
        }
        return bool.booleanValue();
    }

    protected String a(Class<?> cls) {
        return org.apache.commons.lang3.b.a((Class) cls);
    }

    protected boolean isUseClassName() {
        return this.c;
    }

    protected void setUseClassName(boolean z) {
        this.c = z;
    }

    protected boolean isUseShortClassName() {
        return this.d;
    }

    protected void setUseShortClassName(boolean z) {
        this.d = z;
    }

    protected boolean isUseIdentityHashCode() {
        return this.e;
    }

    protected void setUseIdentityHashCode(boolean z) {
        this.e = z;
    }

    protected boolean isUseFieldNames() {
        return this.b;
    }

    protected void setUseFieldNames(boolean z) {
        this.b = z;
    }

    protected boolean isDefaultFullDetail() {
        return this.p;
    }

    protected void setDefaultFullDetail(boolean z) {
        this.p = z;
    }

    protected boolean isArrayContentDetail() {
        return this.n;
    }

    protected void setArrayContentDetail(boolean z) {
        this.n = z;
    }

    protected String getArrayStart() {
        return this.l;
    }

    protected void setArrayStart(String str) {
        if (str == null) {
            str = "";
        }
        this.l = str;
    }

    protected String getArrayEnd() {
        return this.o;
    }

    protected void setArrayEnd(String str) {
        if (str == null) {
            str = "";
        }
        this.o = str;
    }

    protected String getArraySeparator() {
        return this.m;
    }

    protected void setArraySeparator(String str) {
        if (str == null) {
            str = "";
        }
        this.m = str;
    }

    protected String getContentStart() {
        return this.f;
    }

    protected void setContentStart(String str) {
        if (str == null) {
            str = "";
        }
        this.f = str;
    }

    protected String getContentEnd() {
        return this.g;
    }

    protected void setContentEnd(String str) {
        if (str == null) {
            str = "";
        }
        this.g = str;
    }

    protected String getFieldNameValueSeparator() {
        return this.h;
    }

    protected void setFieldNameValueSeparator(String str) {
        if (str == null) {
            str = "";
        }
        this.h = str;
    }

    protected String getFieldSeparator() {
        return this.k;
    }

    protected void setFieldSeparator(String str) {
        if (str == null) {
            str = "";
        }
        this.k = str;
    }

    protected boolean isFieldSeparatorAtStart() {
        return this.i;
    }

    protected void setFieldSeparatorAtStart(boolean z) {
        this.i = z;
    }

    protected boolean isFieldSeparatorAtEnd() {
        return this.j;
    }

    protected void setFieldSeparatorAtEnd(boolean z) {
        this.j = z;
    }

    protected String getNullText() {
        return this.q;
    }

    protected void setNullText(String str) {
        if (str == null) {
            str = "";
        }
        this.q = str;
    }

    protected String getSizeStartText() {
        return this.r;
    }

    protected void setSizeStartText(String str) {
        if (str == null) {
            str = "";
        }
        this.r = str;
    }

    protected String getSizeEndText() {
        return this.s;
    }

    protected void setSizeEndText(String str) {
        if (str == null) {
            str = "";
        }
        this.s = str;
    }

    protected String getSummaryObjectStartText() {
        return this.t;
    }

    protected void setSummaryObjectStartText(String str) {
        if (str == null) {
            str = "";
        }
        this.t = str;
    }

    protected String getSummaryObjectEndText() {
        return this.u;
    }

    protected void setSummaryObjectEndText(String str) {
        if (str == null) {
            str = "";
        }
        this.u = str;
    }
}
