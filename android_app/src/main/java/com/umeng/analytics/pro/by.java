package com.umeng.analytics.pro;

import android.content.Context;

/* compiled from: ReportPolicy */
public class by {
    public static final int a = 0;
    public static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 8;

    /* compiled from: ReportPolicy */
    public static class h {
        public boolean a(boolean z) {
            return true;
        }

        public boolean a() {
            return true;
        }
    }

    /* compiled from: ReportPolicy */
    public static class a extends h {
        private final long a = 15000;
        private bc b;

        public a(bc bcVar) {
            this.b = bcVar;
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b.c >= 15000) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: ReportPolicy */
    public static class b extends h {
        private bf a;
        private bc b;

        public b(bc bcVar, bf bfVar) {
            this.b = bcVar;
            this.a = bfVar;
        }

        public boolean a(boolean z) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.b.c >= this.a.b()) {
                return true;
            }
            return false;
        }

        public boolean a() {
            return this.a.d();
        }
    }

    /* compiled from: ReportPolicy */
    public static class c extends h {
        private long a;
        private long b = 0;

        public c(int i) {
            this.a = (long) i;
            this.b = System.currentTimeMillis();
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b >= this.a) {
                return true;
            }
            return false;
        }

        public boolean a() {
            return System.currentTimeMillis() - this.b < this.a;
        }
    }

    /* compiled from: ReportPolicy */
    public static class d extends h {
        public boolean a(boolean z) {
            return z;
        }
    }

    /* compiled from: ReportPolicy */
    public static class e extends h {
        private static long a = 90000;
        private static long b = 86400000;
        private long c;
        private bc d;

        public e(bc bcVar, long j) {
            this.d = bcVar;
            a(j);
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.d.c >= this.c) {
                return true;
            }
            return false;
        }

        public void a(long j) {
            if (j < a || j > b) {
                this.c = a;
            } else {
                this.c = j;
            }
        }

        public long b() {
            return this.c;
        }

        public static boolean a(int i) {
            if (((long) i) < a) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: ReportPolicy */
    public static class f extends h {
        private long a = 86400000;
        private bc b;

        public f(bc bcVar) {
            this.b = bcVar;
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b.c >= this.a) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: ReportPolicy */
    public static class g extends h {
        public boolean a(boolean z) {
            return true;
        }
    }

    /* compiled from: ReportPolicy */
    public static class i extends h {
        private Context a = null;

        public i(Context context) {
            this.a = context;
        }

        public boolean a(boolean z) {
            return bt.k(this.a);
        }
    }

    /* compiled from: ReportPolicy */
    public static class j extends h {
        private final long a = 10800000;
        private bc b;

        public j(bc bcVar) {
            this.b = bcVar;
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b.c >= 10800000) {
                return true;
            }
            return false;
        }
    }

    public static boolean a(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return true;
            default:
                return false;
        }
    }
}
