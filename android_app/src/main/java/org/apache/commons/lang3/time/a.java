package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: FormatCache */
abstract class a<F extends Format> {
    private final ConcurrentMap<a, F> a = new ConcurrentHashMap(7);
    private final ConcurrentMap<a, String> b = new ConcurrentHashMap(7);

    /* compiled from: FormatCache */
    private static class a {
        private final Object[] a;
        private int b;

        public a(Object... objArr) {
            this.a = objArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof a) {
                return Arrays.equals(this.a, ((a) obj).a);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            if (this.b == 0) {
                for (Object obj : this.a) {
                    if (obj != null) {
                        i = (i * 7) + obj.hashCode();
                    }
                }
                this.b = i;
            }
            return this.b;
        }
    }

    protected abstract F b(String str, TimeZone timeZone, Locale locale);

    a() {
    }

    public F c(String str, TimeZone timeZone, Locale locale) {
        if (str == null) {
            throw new NullPointerException("pattern must not be null");
        }
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        a aVar = new a(str, timeZone, locale);
        Format format = (Format) this.a.get(aVar);
        if (format != null) {
            return format;
        }
        F b = b(str, timeZone, locale);
        format = (Format) this.a.putIfAbsent(aVar, b);
        return format != null ? format : b;
    }

    public F a(Integer num, Integer num2, TimeZone timeZone, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        a aVar = new a(num, num2, locale);
        String str = (String) this.b.get(aVar);
        if (str == null) {
            DateFormat timeInstance;
            if (num == null) {
                try {
                    timeInstance = DateFormat.getTimeInstance(num2.intValue(), locale);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("No date time pattern for locale: " + locale);
                }
            } else if (num2 == null) {
                timeInstance = DateFormat.getDateInstance(num.intValue(), locale);
            } else {
                timeInstance = DateFormat.getDateTimeInstance(num.intValue(), num2.intValue(), locale);
            }
            String toPattern = ((SimpleDateFormat) timeInstance).toPattern();
            str = (String) this.b.putIfAbsent(aVar, toPattern);
            if (str == null) {
                str = toPattern;
            }
        }
        return c(str, timeZone, locale);
    }
}
