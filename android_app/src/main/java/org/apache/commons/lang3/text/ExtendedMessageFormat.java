package org.apache.commons.lang3.text;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.e;

public class ExtendedMessageFormat extends MessageFormat {
    private String a;
    private final Map<String, ? extends a> b;

    public ExtendedMessageFormat(String str) {
        this(str, Locale.getDefault());
    }

    public ExtendedMessageFormat(String str, Locale locale) {
        this(str, locale, null);
    }

    public ExtendedMessageFormat(String str, Map<String, ? extends a> map) {
        this(str, Locale.getDefault(), map);
    }

    public ExtendedMessageFormat(String str, Locale locale, Map<String, ? extends a> map) {
        super("");
        setLocale(locale);
        this.b = map;
        applyPattern(str);
    }

    public String toPattern() {
        return this.a;
    }

    public final void applyPattern(String str) {
        int i = 0;
        if (this.b == null) {
            super.applyPattern(str);
            this.a = super.toPattern();
            return;
        }
        Collection arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder(str.length());
        ParsePosition parsePosition = new ParsePosition(0);
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            switch (toCharArray[parsePosition.getIndex()]) {
                case '\'':
                    a(str, parsePosition, stringBuilder, true);
                    continue;
                case '{':
                    Object b;
                    Object a;
                    boolean z;
                    int i3 = i2 + 1;
                    c(str, parsePosition);
                    int index = parsePosition.getIndex();
                    stringBuilder.append('{').append(a(str, a(parsePosition)));
                    c(str, parsePosition);
                    if (toCharArray[parsePosition.getIndex()] == ',') {
                        b = b(str, a(parsePosition));
                        a = a((String) b);
                        if (a == null) {
                            stringBuilder.append(',').append(b);
                        }
                    } else {
                        b = null;
                        a = null;
                    }
                    arrayList.add(a);
                    if (a == null) {
                        b = null;
                    }
                    arrayList2.add(b);
                    if (arrayList.size() == i3) {
                        z = true;
                    } else {
                        z = false;
                    }
                    e.a(z);
                    if (arrayList2.size() == i3) {
                        z = true;
                    } else {
                        z = false;
                    }
                    e.a(z);
                    if (toCharArray[parsePosition.getIndex()] == '}') {
                        i2 = i3;
                        break;
                    }
                    throw new IllegalArgumentException("Unreadable format element at position " + index);
            }
            stringBuilder.append(toCharArray[parsePosition.getIndex()]);
            a(parsePosition);
        }
        super.applyPattern(stringBuilder.toString());
        this.a = a(super.toPattern(), arrayList2);
        if (a(arrayList)) {
            Format[] formats = getFormats();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Format format = (Format) it.next();
                if (format != null) {
                    formats[i] = format;
                }
                i++;
            }
            super.setFormats(formats);
        }
    }

    public void setFormat(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    public void setFormatByArgumentIndex(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    public void setFormats(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    public void setFormatsByArgumentIndex(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (ObjectUtils.b(getClass(), obj.getClass())) {
            return false;
        }
        ExtendedMessageFormat extendedMessageFormat = (ExtendedMessageFormat) obj;
        if (ObjectUtils.b(this.a, extendedMessageFormat.a)) {
            return false;
        }
        if (ObjectUtils.b(this.b, extendedMessageFormat.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((super.hashCode() * 31) + ObjectUtils.a(this.b)) * 31) + ObjectUtils.a(this.a);
    }

    private Format a(String str) {
        if (this.b == null) {
            return null;
        }
        String trim;
        int indexOf = str.indexOf(44);
        if (indexOf > 0) {
            str = str.substring(0, indexOf).trim();
            trim = str.substring(indexOf + 1).trim();
        } else {
            trim = null;
        }
        a aVar = (a) this.b.get(str);
        if (aVar != null) {
            return aVar.a(str, trim, getLocale());
        }
        return null;
    }

    private int a(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        c(str, parsePosition);
        StringBuffer stringBuffer = new StringBuffer();
        Object obj = null;
        while (obj == null && parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (Character.isWhitespace(charAt)) {
                c(str, parsePosition);
                charAt = str.charAt(parsePosition.getIndex());
                if (!(charAt == ',' || charAt == '}')) {
                    obj = 1;
                    a(parsePosition);
                }
            }
            char c = charAt;
            if ((c == ',' || c == '}') && stringBuffer.length() > 0) {
                try {
                    return Integer.parseInt(stringBuffer.toString());
                } catch (NumberFormatException e) {
                }
            }
            if (Character.isDigit(c)) {
                obj = null;
            } else {
                obj = 1;
            }
            stringBuffer.append(c);
            a(parsePosition);
        }
        if (obj != null) {
            throw new IllegalArgumentException("Invalid format argument index at position " + index + ": " + str.substring(index, parsePosition.getIndex()));
        }
        throw new IllegalArgumentException("Unterminated format element at position " + index);
    }

    private String b(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        c(str, parsePosition);
        int index2 = parsePosition.getIndex();
        int i = 1;
        while (parsePosition.getIndex() < str.length()) {
            switch (str.charAt(parsePosition.getIndex())) {
                case '\'':
                    a(str, parsePosition, false);
                    break;
                case '{':
                    i++;
                    break;
                case '}':
                    i--;
                    if (i != 0) {
                        break;
                    }
                    return str.substring(index2, parsePosition.getIndex());
                default:
                    break;
            }
            a(parsePosition);
        }
        throw new IllegalArgumentException("Unterminated format element at position " + index);
    }

    private String a(String str, ArrayList<String> arrayList) {
        if (!a((Collection) arrayList)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str.length() * 2);
        ParsePosition parsePosition = new ParsePosition(0);
        int i = -1;
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            switch (charAt) {
                case '\'':
                    a(str, parsePosition, stringBuilder, false);
                    continue;
                case '{':
                    int i3 = i2 + 1;
                    if (i3 != 1) {
                        i2 = i3;
                        break;
                    }
                    i++;
                    stringBuilder.append('{').append(a(str, a(parsePosition)));
                    String str2 = (String) arrayList.get(i);
                    if (str2 != null) {
                        stringBuilder.append(',').append(str2);
                    }
                    i2 = i3;
                    continue;
                case '}':
                    i2--;
                    break;
            }
            stringBuilder.append(charAt);
            a(parsePosition);
        }
        return stringBuilder.toString();
    }

    private void c(String str, ParsePosition parsePosition) {
        char[] toCharArray = str.toCharArray();
        do {
            int a = b.a().a(toCharArray, parsePosition.getIndex());
            parsePosition.setIndex(parsePosition.getIndex() + a);
            if (a <= 0) {
                return;
            }
        } while (parsePosition.getIndex() < str.length());
    }

    private ParsePosition a(ParsePosition parsePosition) {
        parsePosition.setIndex(parsePosition.getIndex() + 1);
        return parsePosition;
    }

    private StringBuilder a(String str, ParsePosition parsePosition, StringBuilder stringBuilder, boolean z) {
        int index = parsePosition.getIndex();
        char[] toCharArray = str.toCharArray();
        if (z && toCharArray[index] == '\'') {
            a(parsePosition);
            if (stringBuilder == null) {
                return null;
            }
            return stringBuilder.append('\'');
        }
        int index2 = parsePosition.getIndex();
        int i = index;
        while (index2 < str.length()) {
            if (!z || !str.substring(index2).startsWith("''")) {
                switch (toCharArray[parsePosition.getIndex()]) {
                    case '\'':
                        a(parsePosition);
                        if (stringBuilder != null) {
                            return stringBuilder.append(toCharArray, i, parsePosition.getIndex() - i);
                        }
                        return null;
                    default:
                        a(parsePosition);
                        break;
                }
            }
            stringBuilder.append(toCharArray, i, parsePosition.getIndex() - i).append('\'');
            parsePosition.setIndex("''".length() + index2);
            i = parsePosition.getIndex();
            index2++;
        }
        throw new IllegalArgumentException("Unterminated quoted string at position " + index);
    }

    private void a(String str, ParsePosition parsePosition, boolean z) {
        a(str, parsePosition, null, z);
    }

    private boolean a(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        for (Object obj : collection) {
            if (obj != null) {
                return true;
            }
        }
        return false;
    }
}
