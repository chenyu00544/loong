package com.baidu.platform.comapi.search;

import android.os.Message;
import com.baidu.location.b.g;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.UIMsg.m_AppUI;

class c {
    private static final String a = c.class.getSimpleName();
    private b b = null;
    private d c = null;

    c() {
    }

    public void a() {
        this.c = null;
    }

    public void a(Message message) {
        if (message.what != m_AppUI.MSG_APP_DATA_OK || this.b == null) {
            return;
        }
        if (message.arg2 == 0) {
            String b;
            switch (message.arg1) {
                case 7:
                    if (message.arg2 == 0) {
                        b = this.c.b(7);
                        if (b == null || b.equals("")) {
                            this.b.b(null);
                            return;
                        } else {
                            this.b.b(b);
                            return;
                        }
                    }
                    return;
                case 10:
                    if (message.arg2 == 0) {
                        this.b.o(this.c.b(message.arg1));
                        return;
                    }
                    return;
                case 11:
                case 21:
                    if (message.arg2 == 0) {
                        b = this.c.b(11);
                        if (b == null || b.equals("")) {
                            this.b.a(null);
                            return;
                        } else {
                            this.b.a(b);
                            return;
                        }
                    }
                    return;
                case 14:
                    if (message.arg2 == 0) {
                        b = this.c.b(message.arg1);
                        if (b == null || b.trim().length() <= 0) {
                            this.b.i(null);
                            return;
                        } else {
                            this.b.i(b);
                            return;
                        }
                    }
                    return;
                case 15:
                    if (message.arg2 == 0) {
                        b = this.c.b(message.arg1);
                        if (b != null && b.trim().length() > 0) {
                            this.b.j(b);
                            break;
                        } else {
                            this.b.j(null);
                            break;
                        }
                    }
                    break;
                case 18:
                    if (message.arg2 == 0) {
                        b = "";
                        b = this.c.b(18);
                        if (b == null || b.equals("")) {
                            this.b.m(null);
                            return;
                        } else {
                            this.b.m(b);
                            return;
                        }
                    }
                    return;
                case 23:
                    if (message.arg2 == 0) {
                        b = this.c.b(23);
                        if (b == null || b.equals("")) {
                            this.b.c(null);
                            return;
                        } else {
                            this.b.c(b);
                            return;
                        }
                    }
                    return;
                case 30:
                    break;
                case 31:
                    if (message.arg2 == 0) {
                        b = this.c.b(message.arg1);
                        if (b == null || b.equals("")) {
                            this.b.h(null);
                            return;
                        } else {
                            this.b.h(b);
                            return;
                        }
                    }
                    return;
                case 34:
                    b = this.c.b(message.arg1);
                    if (b == null || b.trim().length() <= 0) {
                        this.b.k(null);
                        return;
                    } else {
                        this.b.k(b);
                        return;
                    }
                case 35:
                    if (message.arg2 == 0) {
                        b = this.c.b(35);
                        if (b.equals("")) {
                            this.b.l(null);
                            return;
                        } else {
                            this.b.l(b);
                            return;
                        }
                    }
                    return;
                case 44:
                    if (message.arg2 == 0) {
                        b = this.c.b(44);
                        if (b == null || b.equals("")) {
                            this.b.l(null);
                            return;
                        } else {
                            this.b.l(b);
                            return;
                        }
                    }
                    return;
                case 45:
                    if (message.arg2 == 0) {
                        this.b.a(this.c.b(45));
                        return;
                    }
                    return;
                case 46:
                    if (message.arg2 == 0) {
                        this.b.d(this.c.b(message.arg1));
                        return;
                    }
                    return;
                case 51:
                    if (message.arg2 == 0) {
                        this.b.g(this.c.b(message.arg1));
                        return;
                    }
                    return;
                case d_ResultType.SHORT_URL /*500*/:
                case 514:
                    if (message.arg2 == 0) {
                        b = this.c.b(message.arg1);
                        if (b == null || b.trim().length() <= 0) {
                            this.b.e(null);
                            return;
                        } else {
                            this.b.e(b);
                            return;
                        }
                    }
                    return;
                case d_ResultType.SUGGESTION_SEARCH /*506*/:
                    if (message.arg2 == 0) {
                        b = this.c.b((int) d_ResultType.SUGGESTION_SEARCH);
                        if (b == null || b.equals("")) {
                            this.b.n(null);
                            return;
                        } else {
                            this.b.n(b);
                            return;
                        }
                    }
                    return;
                case g.Z /*801*/:
                    if (message.arg2 == 0) {
                        b = "";
                        b = this.c.b(message.arg1);
                        if (b == null || b.equals("")) {
                            this.b.f(null);
                            return;
                        } else {
                            this.b.f(b);
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
            if (message.arg2 == 0) {
                b = this.c.b(message.arg1);
                if (b == null || b.trim().length() <= 0) {
                    this.b.p(null);
                } else {
                    this.b.p(b);
                }
            }
        } else if (3 == message.arg2 || 108 == message.arg2 || 100 == message.arg2) {
            this.b.a(11);
        } else if (105 == message.arg2 || 106 == message.arg2 || 200 == message.arg2 || 230 == message.arg2) {
            this.b.a((int) d_ResultType.SHORT_URL);
        } else if (107 == message.arg2) {
            this.b.a(107);
        } else {
            this.b.a(message.arg2);
        }
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public void a(d dVar) {
        this.c = dVar;
    }
}
