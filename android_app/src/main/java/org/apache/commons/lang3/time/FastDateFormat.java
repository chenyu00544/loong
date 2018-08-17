package org.apache.commons.lang3.time;

import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FastDateFormat extends Format {
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final a<FastDateFormat> a = new FastDateFormat_1();
    private static ConcurrentMap<g, String> b = new ConcurrentHashMap(7);
    private final String c;
    private final TimeZone d;
    private final Locale e;
    private transient d[] f;
    private transient int g;

    static class FastDateFormat_1 extends a<FastDateFormat> {
        FastDateFormat_1() {
        }

        protected /* synthetic */ Format b(String str, TimeZone timeZone, Locale locale) {
            return a(str, timeZone, locale);
        }

        protected FastDateFormat a(String str, TimeZone timeZone, Locale locale) {
            return new FastDateFormat(str, timeZone, locale);
        }
    }

    private interface d {
        int a();

        void a(StringBuffer stringBuffer, Calendar calendar);
    }

    private static class a implements d {
        private final char a;

        a(char c) {
            this.a = c;
        }

        public int a() {
            return 1;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.a);
        }
    }

    private interface b extends d {
        void a(StringBuffer stringBuffer, int i);
    }

    private static class c implements b {
        private final int a;
        private final int b;

        c(int i, int i2) {
            if (i2 < 3) {
                throw new IllegalArgumentException();
            }
            this.a = i;
            this.b = i2;
        }

        public int a() {
            return 4;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(this.a));
        }

        public final void a(StringBuffer stringBuffer, int i) {
            int i2;
            if (i < 100) {
                i2 = this.b;
                while (true) {
                    i2--;
                    if (i2 >= 2) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append((char) ((i / 10) + 48));
                        stringBuffer.append((char) ((i % 10) + 48));
                        return;
                    }
                }
            }
            if (i < 1000) {
                i2 = 3;
            } else {
                org.apache.commons.lang3.e.a(i > -1, "Negative values should not be possible", (long) i);
                i2 = Integer.toString(i).length();
            }
            int i3 = this.b;
            while (true) {
                i3--;
                if (i3 >= i2) {
                    stringBuffer.append('0');
                } else {
                    stringBuffer.append(Integer.toString(i));
                    return;
                }
            }
        }
    }

    private static class e implements d {
        private final String a;

        e(String str) {
            this.a = str;
        }

        public int a() {
            return this.a.length();
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.a);
        }
    }

    private static class f implements d {
        private final int a;
        private final String[] b;

        f(int i, String[] strArr) {
            this.a = i;
            this.b = strArr;
        }

        public int a() {
            int i = 0;
            int length = this.b.length;
            while (true) {
                int i2 = length - 1;
                if (i2 < 0) {
                    return i;
                }
                length = this.b[i2].length();
                if (length <= i) {
                    length = i;
                }
                i = length;
                length = i2;
            }
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.b[calendar.get(this.a)]);
        }
    }

    private static class g {
        private final TimeZone a;
        private final int b;
        private final Locale c;

        g(TimeZone timeZone, boolean z, int i, Locale locale) {
            this.a = timeZone;
            if (z) {
                i |= Integer.MIN_VALUE;
            }
            this.b = i;
            this.c = locale;
        }

        public int hashCode() {
            return (((this.b * 31) + this.c.hashCode()) * 31) + this.a.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof g)) {
                return false;
            }
            g gVar = (g) obj;
            if (this.a.equals(gVar.a) && this.b == gVar.b && this.c.equals(gVar.c)) {
                return true;
            }
            return false;
        }
    }

    private static class h implements d {
        private final TimeZone a;
        private final String b;
        private final String c;

        h(TimeZone timeZone, Locale locale, int i) {
            this.a = timeZone;
            this.b = FastDateFormat.a(timeZone, false, i, locale);
            this.c = FastDateFormat.a(timeZone, true, i, locale);
        }

        public int a() {
            return Math.max(this.b.length(), this.c.length());
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            if (!this.a.useDaylightTime() || calendar.get(16) == 0) {
                stringBuffer.append(this.b);
            } else {
                stringBuffer.append(this.c);
            }
        }
    }

    private static class i implements d {
        static final i a = new i(true);
        static final i b = new i(false);
        final boolean c;

        i(boolean z) {
            this.c = z;
        }

        public int a() {
            return 5;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(15) + calendar.get(16);
            if (i < 0) {
                stringBuffer.append('-');
                i = -i;
            } else {
                stringBuffer.append('+');
            }
            int i2 = i / 3600000;
            stringBuffer.append((char) ((i2 / 10) + 48));
            stringBuffer.append((char) ((i2 % 10) + 48));
            if (this.c) {
                stringBuffer.append(':');
            }
            i = (i / 60000) - (i2 * 60);
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class j implements b {
        private final b a;

        j(b bVar) {
            this.a = bVar;
        }

        public int a() {
            return this.a.a();
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(10);
            if (i == 0) {
                i = calendar.getLeastMaximum(10) + 1;
            }
            this.a.a(stringBuffer, i);
        }

        public void a(StringBuffer stringBuffer, int i) {
            this.a.a(stringBuffer, i);
        }
    }

    private static class k implements b {
        private final b a;

        k(b bVar) {
            this.a = bVar;
        }

        public int a() {
            return this.a.a();
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(11);
            if (i == 0) {
                i = calendar.getMaximum(11) + 1;
            }
            this.a.a(stringBuffer, i);
        }

        public void a(StringBuffer stringBuffer, int i) {
            this.a.a(stringBuffer, i);
        }
    }

    private static class l implements b {
        static final l a = new l();

        l() {
        }

        public int a() {
            return 2;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(2) + 1);
        }

        public final void a(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class m implements b {
        private final int a;

        m(int i) {
            this.a = i;
        }

        public int a() {
            return 2;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(this.a));
        }

        public final void a(StringBuffer stringBuffer, int i) {
            if (i < 100) {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
                return;
            }
            stringBuffer.append(Integer.toString(i));
        }
    }

    private static class n implements b {
        static final n a = new n();

        n() {
        }

        public int a() {
            return 2;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(1) % 100);
        }

        public final void a(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class o implements b {
        static final o a = new o();

        o() {
        }

        public int a() {
            return 2;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(2) + 1);
        }

        public final void a(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
                return;
            }
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class p implements b {
        private final int a;

        p(int i) {
            this.a = i;
        }

        public int a() {
            return 4;
        }

        public void a(StringBuffer stringBuffer, Calendar calendar) {
            a(stringBuffer, calendar.get(this.a));
        }

        public final void a(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
            } else if (i < 100) {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            } else {
                stringBuffer.append(Integer.toString(i));
            }
        }
    }

    public static FastDateFormat getInstance() {
        return (FastDateFormat) a.a(Integer.valueOf(3), Integer.valueOf(3), null, null);
    }

    public static FastDateFormat getInstance(String str) {
        return (FastDateFormat) a.c(str, null, null);
    }

    public static FastDateFormat getInstance(String str, TimeZone timeZone) {
        return (FastDateFormat) a.c(str, timeZone, null);
    }

    public static FastDateFormat getInstance(String str, Locale locale) {
        return (FastDateFormat) a.c(str, null, locale);
    }

    public static FastDateFormat getInstance(String str, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) a.c(str, timeZone, locale);
    }

    public static FastDateFormat getDateInstance(int i) {
        return (FastDateFormat) a.a(Integer.valueOf(i), null, null, null);
    }

    public static FastDateFormat getDateInstance(int i, Locale locale) {
        return (FastDateFormat) a.a(Integer.valueOf(i), null, null, locale);
    }

    public static FastDateFormat getDateInstance(int i, TimeZone timeZone) {
        return (FastDateFormat) a.a(Integer.valueOf(i), null, timeZone, null);
    }

    public static FastDateFormat getDateInstance(int i, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) a.a(Integer.valueOf(i), null, timeZone, locale);
    }

    public static FastDateFormat getTimeInstance(int i) {
        return (FastDateFormat) a.a(null, Integer.valueOf(i), null, null);
    }

    public static FastDateFormat getTimeInstance(int i, Locale locale) {
        return (FastDateFormat) a.a(null, Integer.valueOf(i), null, locale);
    }

    public static FastDateFormat getTimeInstance(int i, TimeZone timeZone) {
        return (FastDateFormat) a.a(null, Integer.valueOf(i), timeZone, null);
    }

    public static FastDateFormat getTimeInstance(int i, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) a.a(null, Integer.valueOf(i), timeZone, locale);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2) {
        return (FastDateFormat) a.a(Integer.valueOf(i), Integer.valueOf(i2), null, null);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, Locale locale) {
        return (FastDateFormat) a.a(Integer.valueOf(i), Integer.valueOf(i2), null, locale);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, TimeZone timeZone) {
        return getDateTimeInstance(i, i2, timeZone, null);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) a.a(Integer.valueOf(i), Integer.valueOf(i2), timeZone, locale);
    }

    static String a(TimeZone timeZone, boolean z, int i, Locale locale) {
        g gVar = new g(timeZone, z, i, locale);
        String str = (String) b.get(gVar);
        if (str != null) {
            return str;
        }
        String displayName = timeZone.getDisplayName(z, i, locale);
        str = (String) b.putIfAbsent(gVar, displayName);
        return str != null ? str : displayName;
    }

    protected FastDateFormat(String str, TimeZone timeZone, Locale locale) {
        this.c = str;
        this.d = timeZone;
        this.e = locale;
        b();
    }

    private void b() {
        List a = a();
        this.f = (d[]) a.toArray(new d[a.size()]);
        int i = 0;
        int length = this.f.length;
        while (true) {
            length--;
            if (length >= 0) {
                i += this.f[length].a();
            } else {
                this.g = i;
                return;
            }
        }
    }

    protected List<d> a() {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(this.e);
        List<d> arrayList = new ArrayList();
        String[] eras = dateFormatSymbols.getEras();
        String[] months = dateFormatSymbols.getMonths();
        String[] shortMonths = dateFormatSymbols.getShortMonths();
        String[] weekdays = dateFormatSymbols.getWeekdays();
        String[] shortWeekdays = dateFormatSymbols.getShortWeekdays();
        String[] amPmStrings = dateFormatSymbols.getAmPmStrings();
        int length = this.c.length();
        int[] iArr = new int[1];
        int i = 0;
        while (i < length) {
            iArr[0] = i;
            String a = a(this.c, iArr);
            int i2 = iArr[0];
            i = a.length();
            if (i == 0) {
                return arrayList;
            }
            Object eVar;
            switch (a.charAt(0)) {
                case '\'':
                    a = a.substring(1);
                    if (a.length() != 1) {
                        eVar = new e(a);
                        break;
                    }
                    eVar = new a(a.charAt(0));
                    break;
                case 'D':
                    eVar = a(6, i);
                    break;
                case 'E':
                    String[] strArr;
                    if (i < 4) {
                        strArr = shortWeekdays;
                    } else {
                        strArr = weekdays;
                    }
                    f fVar = new f(7, strArr);
                    break;
                case 'F':
                    eVar = a(8, i);
                    break;
                case 'G':
                    eVar = new f(0, eras);
                    break;
                case 'H':
                    eVar = a(11, i);
                    break;
                case 'K':
                    eVar = a(10, i);
                    break;
                case 'M':
                    if (i < 4) {
                        if (i != 3) {
                            if (i != 2) {
                                eVar = o.a;
                                break;
                            }
                            eVar = l.a;
                            break;
                        }
                        eVar = new f(2, shortMonths);
                        break;
                    }
                    eVar = new f(2, months);
                    break;
                case 'S':
                    eVar = a(14, i);
                    break;
                case 'W':
                    eVar = a(4, i);
                    break;
                case 'Z':
                    if (i != 1) {
                        eVar = i.a;
                        break;
                    }
                    eVar = i.b;
                    break;
                case 'a':
                    eVar = new f(9, amPmStrings);
                    break;
                case 'd':
                    eVar = a(5, i);
                    break;
                case 'h':
                    j jVar = new j(a(10, i));
                    break;
                case 'k':
                    k kVar = new k(a(11, i));
                    break;
                case 'm':
                    eVar = a(12, i);
                    break;
                case 's':
                    eVar = a(13, i);
                    break;
                case 'w':
                    eVar = a(3, i);
                    break;
                case com.baidu.location.b.g.char /*121*/:
                    if (i != 2) {
                        if (i < 4) {
                            i = 4;
                        }
                        eVar = a(1, i);
                        break;
                    }
                    eVar = n.a;
                    break;
                case com.baidu.location.b.g.K /*122*/:
                    if (i < 4) {
                        eVar = new h(this.d, this.e, 0);
                        break;
                    }
                    eVar = new h(this.d, this.e, 1);
                    break;
                default:
                    throw new IllegalArgumentException("Illegal pattern component: " + a);
            }
            arrayList.add(eVar);
            i = i2 + 1;
        }
        return arrayList;
    }

    protected String a(String str, int[] iArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt < 'A' || charAt > 'Z') && (charAt < 'a' || charAt > 'z')) {
            stringBuilder.append('\'');
            int i2 = 0;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 != '\'') {
                    if (i2 == 0 && ((charAt2 >= 'A' && charAt2 <= 'Z') || (charAt2 >= 'a' && charAt2 <= 'z'))) {
                        i--;
                        break;
                    }
                    stringBuilder.append(charAt2);
                } else if (i + 1 >= length || str.charAt(i + 1) != '\'') {
                    i2 = i2 == 0 ? 1 : 0;
                } else {
                    i++;
                    stringBuilder.append(charAt2);
                }
                i++;
            }
        } else {
            stringBuilder.append(charAt);
            while (i + 1 < length && str.charAt(i + 1) == charAt) {
                stringBuilder.append(charAt);
                i++;
            }
        }
        iArr[0] = i;
        return stringBuilder.toString();
    }

    protected b a(int i, int i2) {
        switch (i2) {
            case 1:
                return new p(i);
            case 2:
                return new m(i);
            default:
                return new c(i, i2);
        }
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer);
        }
        throw new IllegalArgumentException("Unknown class: " + (obj == null ? "<null>" : obj.getClass().getName()));
    }

    public String format(long j) {
        return format(new Date(j));
    }

    public String format(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar(this.d, this.e);
        gregorianCalendar.setTime(date);
        return a(gregorianCalendar, new StringBuffer(this.g)).toString();
    }

    public String format(Calendar calendar) {
        return format(calendar, new StringBuffer(this.g)).toString();
    }

    public StringBuffer format(long j, StringBuffer stringBuffer) {
        return format(new Date(j), stringBuffer);
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer) {
        Calendar gregorianCalendar = new GregorianCalendar(this.d, this.e);
        gregorianCalendar.setTime(date);
        return a(gregorianCalendar, stringBuffer);
    }

    public StringBuffer format(Calendar calendar, StringBuffer stringBuffer) {
        return a(calendar, stringBuffer);
    }

    protected StringBuffer a(Calendar calendar, StringBuffer stringBuffer) {
        for (d a : this.f) {
            a.a(stringBuffer, calendar);
        }
        return stringBuffer;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        parsePosition.setIndex(0);
        parsePosition.setErrorIndex(0);
        return null;
    }

    public String getPattern() {
        return this.c;
    }

    public TimeZone getTimeZone() {
        return this.d;
    }

    public Locale getLocale() {
        return this.e;
    }

    public int getMaxLengthEstimate() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateFormat)) {
            return false;
        }
        FastDateFormat fastDateFormat = (FastDateFormat) obj;
        if (this.c.equals(fastDateFormat.c) && this.d.equals(fastDateFormat.d) && this.e.equals(fastDateFormat.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.c.hashCode() + ((this.d.hashCode() + (this.e.hashCode() * 13)) * 13);
    }

    public String toString() {
        return "FastDateFormat[" + this.c + "]";
    }
}
