package com.baidu.location.h;

import com.baidu.location.b.f;
import com.baidu.location.b.g;
import java.util.Locale;

public class h implements f {
    public int kA;
    public int kB;
    public int kC;
    public int kt;
    private boolean ku;
    public long kv;
    public int kw;
    public int kx;
    public int ky;
    public char kz;

    public h() {
        this.kt = -1;
        this.kx = -1;
        this.kA = -1;
        this.kB = -1;
        this.kC = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.kw = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.kv = 0;
        this.ky = -1;
        this.kz = '\u0000';
        this.ku = false;
        this.kv = System.currentTimeMillis();
    }

    public h(int i, int i2, int i3, int i4, int i5, char c) {
        this.kt = -1;
        this.kx = -1;
        this.kA = -1;
        this.kB = -1;
        this.kC = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.kw = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.kv = 0;
        this.ky = -1;
        this.kz = '\u0000';
        this.ku = false;
        this.kt = i;
        this.kx = i2;
        this.kA = i3;
        this.kB = i4;
        this.ky = i5;
        this.kz = c;
        this.kv = System.currentTimeMillis();
    }

    public h(h hVar) {
        this(hVar.kt, hVar.kx, hVar.kA, hVar.kB, hVar.ky, hVar.kz);
    }

    public boolean case(h hVar) {
        return this.kt == hVar.kt && this.kx == hVar.kx && this.kB == hVar.kB && this.kA == hVar.kA;
    }

    public String dA() {
        StringBuffer stringBuffer = new StringBuffer(64);
        stringBuffer.append(String.format(Locale.CHINA, "cell=%d|%d|%d|%d:%d", new Object[]{Integer.valueOf(this.kA), Integer.valueOf(this.kB), Integer.valueOf(this.kt), Integer.valueOf(this.kx), Integer.valueOf(this.ky)}));
        return stringBuffer.toString();
    }

    public String dB() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(this.kx + 23);
        stringBuffer.append("H");
        stringBuffer.append(this.kt + 45);
        stringBuffer.append("K");
        stringBuffer.append(this.kB + 54);
        stringBuffer.append("Q");
        stringBuffer.append(this.kA + g.a);
        return stringBuffer.toString();
    }

    public boolean dC() {
        return this.kt > -1 && this.kx > -1 && this.kB == -1 && this.kA == -1;
    }

    public void ds() {
        this.ku = true;
    }

    public boolean dt() {
        return this.kt > -1 && this.kx > 0;
    }

    public int du() {
        return (this.kA <= 0 || !dt()) ? 2 : (this.kA == 460 || this.kA == 454 || this.kA == 455 || this.kA == 466) ? 1 : 0;
    }

    public boolean dv() {
        return System.currentTimeMillis() - this.kv < 3000;
    }

    public boolean dw() {
        return this.kt == -1 && this.kx == -1 && this.kB == -1 && this.kA == -1;
    }

    public String dx() {
        if (!dt()) {
            return null;
        }
        return String.format(Locale.CHINA, "<cell-tower>\n<mcc>%d</mcc><mnc>%d</mnc><lac>%d</lac><ci>%d</ci><rssi>%d</rssi></cell-tower>", new Object[]{Integer.valueOf(this.kA), Integer.valueOf(this.kB), Integer.valueOf(this.kt), Integer.valueOf(this.kx), Integer.valueOf(this.ky)});
    }

    public String dy() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(this.kz);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", new Object[]{Integer.valueOf(this.kA), Integer.valueOf(this.kB), Integer.valueOf(this.kt), Integer.valueOf(this.kx), Integer.valueOf(this.ky)}));
        if (this.ku) {
            stringBuffer.append("&newcl=1");
        }
        return stringBuffer.toString();
    }

    public boolean dz() {
        return this.kt > -1 && this.kx > -1 && this.kB > -1 && this.kA > -1;
    }
}
