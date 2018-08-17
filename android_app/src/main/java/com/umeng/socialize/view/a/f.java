package com.umeng.socialize.view.a;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import com.tencent.open.yyb.TitleBar;
import com.umeng.socialize.view.a.b.b;
import java.util.Timer;

/* compiled from: ACProgressFlower */
public class f extends a {
    private a a;
    private b b;
    private int c;
    private Timer d;

    /* compiled from: ACProgressFlower */
    public static class a {
        private Context a;
        private float b = 0.25f;
        private float c = 0.55f;
        private float d = 0.27f;
        private int e = ViewCompat.MEASURED_STATE_MASK;
        private int f = -1;
        private int g = -12303292;
        private int h = 12;
        private int i = 9;
        private float j = 0.5f;
        private float k = TitleBar.BACKBTN_LEFT_MARGIN;
        private float l = 0.5f;
        private int m = 100;
        private float n = 9.0f;
        private String o = null;
        private int p = -1;
        private float q = 0.5f;
        private float r = 40.0f;
        private int s = 40;
        private boolean t = true;

        public a(Context context) {
            this.a = context;
        }

        public a a(float f) {
            this.b = f;
            return this;
        }

        public a b(float f) {
            this.c = f;
            return this;
        }

        public a c(float f) {
            this.d = f;
            return this;
        }

        public a a(int i) {
            this.e = i;
            return this;
        }

        public a b(int i) {
            this.f = i;
            return this;
        }

        public a c(int i) {
            this.g = i;
            return this;
        }

        public a d(int i) {
            this.h = i;
            return this;
        }

        public a e(int i) {
            this.i = i;
            return this;
        }

        public a d(float f) {
            this.j = f;
            return this;
        }

        public a e(float f) {
            this.k = f;
            return this;
        }

        public a f(float f) {
            this.l = f;
            return this;
        }

        public a f(int i) {
            this.m = i;
            return this;
        }

        public a g(float f) {
            this.n = f;
            return this;
        }

        public a a(String str) {
            this.o = str;
            return this;
        }

        public a g(int i) {
            this.r = (float) i;
            return this;
        }

        public a h(int i) {
            this.p = i;
            return this;
        }

        public a h(float f) {
            this.q = f;
            return this;
        }

        public a i(int i) {
            this.s = i;
            return this;
        }

        public a a(boolean z) {
            this.t = z;
            return this;
        }

        public f a() {
            return new f();
        }
    }

    private f(a aVar) {
        super(aVar.a);
        this.c = 0;
        this.a = aVar;
        setOnDismissListener(new g(this));
    }

    public void show() {
        if (this.b == null) {
            this.b = new b(this.a.a, (int) (((float) a(this.a.a)) * this.a.b), this.a.e, this.a.l, this.a.k, this.a.i, this.a.h, this.a.j, this.a.c, this.a.d, this.a.f, this.a.g, this.a.o, this.a.r, this.a.p, this.a.q, this.a.s, this.a.t);
        }
        super.setContentView(this.b);
        super.show();
        long s = (long) (1000.0f / this.a.n);
        this.d = new Timer();
        this.d.scheduleAtFixedRate(new h(this), s, s);
    }
}
