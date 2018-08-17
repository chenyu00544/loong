package org.apache.commons.lang3.text;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

public class CompositeFormat extends Format {
    private final Format a;
    private final Format b;

    public CompositeFormat(Format format, Format format2) {
        this.a = format;
        this.b = format2;
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return this.b.format(obj, stringBuffer, fieldPosition);
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        return this.a.parseObject(str, parsePosition);
    }

    public Format getParser() {
        return this.a;
    }

    public Format getFormatter() {
        return this.b;
    }

    public String reformat(String str) throws ParseException {
        return format(parseObject(str));
    }
}
