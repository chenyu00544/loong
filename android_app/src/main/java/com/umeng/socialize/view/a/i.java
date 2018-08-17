package com.umeng.socialize.view.a;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import com.tencent.open.yyb.TitleBar;
import com.umeng.socialize.view.a.b.c;
import java.util.Timer;

/* compiled from: ACProgressPie */
public class i extends a {
    private a a;
    private c b;
    private Timer c;
    private int d;

    /* compiled from: ACProgressPie */
    public static class a {
        private Context a;
        private float b = 0.25f;
        private int c = ViewCompat.MEASURED_STATE_MASK;
        private float d = TitleBar.BACKBTN_LEFT_MARGIN;
        private float e = 0.5f;
        private int f = -1;
        private float g = 0.9f;
        private float h = 0.2f;
        private int i = 3;
        private int j = -1;
        private float k = 0.9f;
        private float l = 0.08f;
        private float m = 6.67f;
        private int n = 100;
        private int o = 200;

        public a(Context context) {
            this.a = context;
        }

        public a a(float f) {
            this.b = f;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public a b(float f) {
            this.e = f;
            return this;
        }

        public a c(float f) {
            this.d = f;
            return this;
        }

        public a b(int i) {
            this.f = i;
            return this;
        }

        public a d(float f) {
            this.g = f;
            return this;
        }

        public a e(float f) {
            this.h = f;
            return this;
        }

        public a c(int i) {
            this.i = i;
            return this;
        }

        public a d(int i) {
            this.j = i;
            return this;
        }

        public a f(float f) {
            this.k = f;
            return this;
        }

        public a g(float f) {
            this.l = f;
            return this;
        }

        public a h(float f) {
            this.m = f;
            return this;
        }

        public a e(int i) {
            this.n = i;
            return this;
        }

        public a f(int i) {
            this.o = i;
            return this;
        }

        public i a() {
            return new i();
        }
    }

    private i(a aVar) {
        super(aVar.a);
        this.d = 0;
        this.a = aVar;
        setOnDismissListener(new j(this));
    }

    public void show() {
        if (this.b == null) {
            this.b = new c(this.a.a, (int) (((float) a(this.a.a)) * this.a.b), this.a.c, this.a.e, this.a.d, this.a.h, this.a.l, this.a.i, this.a.f, this.a.g, this.a.j, this.a.k);
        }
        super.setContentView(this.b);
        super.show();
        if (this.a.o == 200) {
            long n = (long) (1000.0f / this.a.m);
            this.c = new Timer();
            this.c.scheduleAtFixedRate(new k(this), n, n);
        }
    }

    public void a(float f) {
        if (this.a.o == 201 && this.b != null) {
            this.b.a(360.0f * f);
        }
    }
}
