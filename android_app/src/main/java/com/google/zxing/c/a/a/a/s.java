package com.google.zxing.c.a.a.a;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;
import com.umeng.socialize.common.SocializeConstants;

/* compiled from: GeneralAppIdDecoder */
final class s {
    private final a a;
    private final m b = new m();
    private final StringBuilder c = new StringBuilder();

    s(a aVar) {
        this.a = aVar;
    }

    String a(StringBuilder stringBuilder, int i) throws NotFoundException {
        String str = null;
        while (true) {
            o a = a(i, str);
            str = r.a(a.a());
            if (str != null) {
                stringBuilder.append(str);
            }
            if (a.b()) {
                str = String.valueOf(a.c());
            } else {
                str = null;
            }
            if (i == a.e()) {
                return stringBuilder.toString();
            }
            i = a.e();
        }
    }

    private boolean a(int i) {
        if (i + 7 <= this.a.a()) {
            for (int i2 = i; i2 < i + 3; i2++) {
                if (this.a.a(i2)) {
                    return true;
                }
            }
            return this.a.a(i + 3);
        } else if (i + 4 <= this.a.a()) {
            return true;
        } else {
            return false;
        }
    }

    private p b(int i) {
        if (i + 7 > this.a.a()) {
            int a = a(i, 4);
            if (a == 0) {
                return new p(this.a.a(), 10, 10);
            }
            return new p(this.a.a(), a - 1, 10);
        }
        int a2 = a(i, 7);
        return new p(i + 7, (a2 - 8) / 11, (a2 - 8) % 11);
    }

    int a(int i, int i2) {
        return a(this.a, i, i2);
    }

    static int a(a aVar, int i, int i2) {
        int i3 = 0;
        if (i2 > 32) {
            throw new IllegalArgumentException("extractNumberValueFromBitArray can't handle more than 32 bits");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (aVar.a(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    o a(int i, String str) {
        this.c.setLength(0);
        if (str != null) {
            this.c.append(str);
        }
        this.b.a(i);
        o a = a();
        if (a == null || !a.b()) {
            return new o(this.b.a(), this.c.toString());
        }
        return new o(this.b.a(), this.c.toString(), a.c());
    }

    private o a() {
        l d;
        boolean b;
        do {
            int a = this.b.a();
            if (this.b.b()) {
                d = d();
                b = d.b();
            } else if (this.b.c()) {
                d = c();
                b = d.b();
            } else {
                d = b();
                b = d.b();
            }
            if ((a != this.b.a() ? 1 : null) == null && !b) {
                break;
            }
        } while (!b);
        return d.a();
    }

    private l b() {
        while (a(this.b.a())) {
            p b = b(this.b.a());
            this.b.a(b.e());
            if (b.c()) {
                o oVar;
                if (b.d()) {
                    oVar = new o(this.b.a(), this.c.toString());
                } else {
                    oVar = new o(this.b.a(), this.c.toString(), b.b());
                }
                return new l(oVar, true);
            }
            this.c.append(b.a());
            if (b.d()) {
                return new l(new o(this.b.a(), this.c.toString()), true);
            }
            this.c.append(b.b());
        }
        if (i(this.b.a())) {
            this.b.e();
            this.b.b(4);
        }
        return new l(false);
    }

    private l c() {
        while (c(this.b.a())) {
            n d = d(this.b.a());
            this.b.a(d.e());
            if (d.b()) {
                return new l(new o(this.b.a(), this.c.toString()), true);
            }
            this.c.append(d.a());
        }
        if (h(this.b.a())) {
            this.b.b(3);
            this.b.d();
        } else if (g(this.b.a())) {
            if (this.b.a() + 5 < this.a.a()) {
                this.b.b(5);
            } else {
                this.b.a(this.a.a());
            }
            this.b.e();
        }
        return new l(false);
    }

    private l d() {
        while (e(this.b.a())) {
            n f = f(this.b.a());
            this.b.a(f.e());
            if (f.b()) {
                return new l(new o(this.b.a(), this.c.toString()), true);
            }
            this.c.append(f.a());
        }
        if (h(this.b.a())) {
            this.b.b(3);
            this.b.d();
        } else if (g(this.b.a())) {
            if (this.b.a() + 5 < this.a.a()) {
                this.b.b(5);
            } else {
                this.b.a(this.a.a());
            }
            this.b.f();
        }
        return new l(false);
    }

    private boolean c(int i) {
        boolean z = true;
        if (i + 5 > this.a.a()) {
            return false;
        }
        int a = a(i, 5);
        if (a >= 5 && a < 16) {
            return true;
        }
        if (i + 7 > this.a.a()) {
            return false;
        }
        a = a(i, 7);
        if (a >= 64 && a < 116) {
            return true;
        }
        if (i + 8 > this.a.a()) {
            return false;
        }
        a = a(i, 8);
        if (a < 232 || a >= 253) {
            z = false;
        }
        return z;
    }

    private n d(int i) {
        int a = a(i, 5);
        if (a == 15) {
            return new n(i + 5, '$');
        }
        if (a >= 5 && a < 15) {
            return new n(i + 5, (char) ((a + 48) - 5));
        }
        a = a(i, 7);
        if (a >= 64 && a < 90) {
            return new n(i + 7, (char) (a + 1));
        }
        if (a >= 90 && a < 116) {
            return new n(i + 7, (char) (a + 7));
        }
        char c;
        int a2 = a(i, 8);
        switch (a2) {
            case 232:
                c = '!';
                break;
            case 233:
                c = '\"';
                break;
            case 234:
                c = '%';
                break;
            case 235:
                c = '&';
                break;
            case 236:
                c = '\'';
                break;
            case 237:
                c = '(';
                break;
            case 238:
                c = ')';
                break;
            case 239:
                c = '*';
                break;
            case SocializeConstants.MASK_USER_CENTER_HIDE_AREA /*240*/:
                c = '+';
                break;
            case 241:
                c = ',';
                break;
            case 242:
                c = '-';
                break;
            case 243:
                c = '.';
                break;
            case 244:
                c = '/';
                break;
            case 245:
                c = ':';
                break;
            case 246:
                c = ';';
                break;
            case 247:
                c = '<';
                break;
            case 248:
                c = '=';
                break;
            case 249:
                c = '>';
                break;
            case Callback.DEFAULT_SWIPE_ANIMATION_DURATION /*250*/:
                c = '?';
                break;
            case 251:
                c = '_';
                break;
            case 252:
                c = ' ';
                break;
            default:
                throw new IllegalArgumentException("Decoding invalid ISO/IEC 646 value: " + a2);
        }
        return new n(i + 8, c);
    }

    private boolean e(int i) {
        boolean z = true;
        if (i + 5 > this.a.a()) {
            return false;
        }
        int a = a(i, 5);
        if (a >= 5 && a < 16) {
            return true;
        }
        if (i + 6 > this.a.a()) {
            return false;
        }
        a = a(i, 6);
        if (a < 16 || a >= 63) {
            z = false;
        }
        return z;
    }

    private n f(int i) {
        int a = a(i, 5);
        if (a == 15) {
            return new n(i + 5, '$');
        }
        if (a >= 5 && a < 15) {
            return new n(i + 5, (char) ((a + 48) - 5));
        }
        a = a(i, 6);
        if (a >= 32 && a < 58) {
            return new n(i + 6, (char) (a + 33));
        }
        char c;
        switch (a) {
            case 58:
                c = '*';
                break;
            case 59:
                c = ',';
                break;
            case 60:
                c = '-';
                break;
            case 61:
                c = '.';
                break;
            case 62:
                c = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: " + a);
        }
        return new n(i + 6, c);
    }

    private boolean g(int i) {
        if (i + 1 > this.a.a()) {
            return false;
        }
        int i2 = 0;
        while (i2 < 5 && i2 + i < this.a.a()) {
            if (i2 == 2) {
                if (!this.a.a(i + 2)) {
                    return false;
                }
            } else if (this.a.a(i + i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private boolean h(int i) {
        if (i + 3 > this.a.a()) {
            return false;
        }
        for (int i2 = i; i2 < i + 3; i2++) {
            if (this.a.a(i2)) {
                return false;
            }
        }
        return true;
    }

    private boolean i(int i) {
        if (i + 1 > this.a.a()) {
            return false;
        }
        int i2 = 0;
        while (i2 < 4 && i2 + i < this.a.a()) {
            if (this.a.a(i + i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }
}
