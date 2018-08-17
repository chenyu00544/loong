package anet.channel.util;

import android.text.TextUtils;
import anet.channel.strategy.StrategyCenter;
import java.net.MalformedURLException;
import java.net.URL;

/* compiled from: Taobao */
public class e {
    private String a;
    private String b;
    private String c;
    private String d;
    private volatile boolean e = false;

    private e() {
    }

    public static e a(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        e eVar = new e();
        eVar.c = trim;
        if (trim.startsWith("//")) {
            eVar.a = null;
        } else if (trim.regionMatches(true, 0, "https:", 0, 6)) {
            eVar.a = HttpConstant.HTTPS;
            i = 6;
        } else if (!trim.regionMatches(true, 0, "http:", 0, 5)) {
            return null;
        } else {
            eVar.a = HttpConstant.HTTP;
            i = 5;
        }
        int length = trim.length();
        i += 2;
        int i2 = i;
        while (i2 < length) {
            char charAt = trim.charAt(i2);
            if (charAt == '/' || charAt == ':' || charAt == '?' || charAt == '#') {
                eVar.b = trim.substring(i, i2);
                break;
            }
            i2++;
        }
        if (i2 == length) {
            eVar.b = trim.substring(i);
        }
        return eVar;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        if (this.d == null) {
            this.d = StringUtils.concatString(this.a, HttpConstant.SCHEME_SPLIT, this.b);
        }
        return this.d;
    }

    public String d() {
        return this.c;
    }

    public URL e() {
        try {
            return new URL(this.c);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public void f() {
        this.e = true;
        if (!HttpConstant.HTTP.equals(this.a)) {
            this.a = HttpConstant.HTTP;
            this.c = StringUtils.concatString(this.a, ":", this.c.substring(this.c.indexOf("//")));
            this.d = null;
        }
    }

    public void g() {
        if (!this.e) {
            this.e = true;
            String formalizeUrl = StrategyCenter.getInstance().getFormalizeUrl(this.c);
            if (formalizeUrl != null && formalizeUrl != this.c) {
                this.c = formalizeUrl;
                this.d = null;
            }
        }
    }

    public void h() {
        this.e = true;
    }

    public void a(String str, int i) {
        if (i != 0 && str != null) {
            int indexOf = this.c.indexOf(this.b) + this.b.length();
            while (indexOf < this.c.length() && this.c.charAt(indexOf) != '/') {
                indexOf++;
            }
            StringBuilder stringBuilder = new StringBuilder(this.c.length() + str.length());
            stringBuilder.append(this.a).append(HttpConstant.SCHEME_SPLIT).append(str).append(':').append(i).append(this.c.substring(indexOf));
            this.c = stringBuilder.toString();
        }
    }

    public String toString() {
        return this.c;
    }
}
