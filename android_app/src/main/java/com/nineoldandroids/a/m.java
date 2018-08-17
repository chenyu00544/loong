package com.nineoldandroids.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: ValueAnimator */
public class m extends a {
    private static ThreadLocal<a> h = new ThreadLocal();
    private static final ThreadLocal<ArrayList<m>> i = new m_1();
    private static final ThreadLocal<ArrayList<m>> j = new m_2();
    private static final ThreadLocal<ArrayList<m>> k = new m_3();
    private static final ThreadLocal<ArrayList<m>> l = new m_4();
    private static final ThreadLocal<ArrayList<m>> m = new m_5();
    private static final Interpolator n = new AccelerateDecelerateInterpolator();
    private static final l o = new f();
    private static final l p = new d();
    private static long z = 10;
    private int A = 0;
    private int B = 1;
    private Interpolator C = n;
    private ArrayList<b> D = null;
    long b;
    long c = -1;
    int d = 0;
    boolean e = false;
    k[] f;
    HashMap<String, k> g;
    private boolean q = false;
    private int r = 0;
    private float s = 0.0f;
    private boolean t = false;
    private long u;
    private boolean v = false;
    private boolean w = false;
    private long x = 300;
    private long y = 0;

    /* compiled from: ValueAnimator */
    static class m_1 extends ThreadLocal<ArrayList<m>> {
        m_1() {
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    static class m_2 extends ThreadLocal<ArrayList<m>> {
        m_2() {
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    static class m_3 extends ThreadLocal<ArrayList<m>> {
        m_3() {
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    static class m_4 extends ThreadLocal<ArrayList<m>> {
        m_4() {
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    static class m_5 extends ThreadLocal<ArrayList<m>> {
        m_5() {
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    private static class a extends Handler {
        private a() {
        }

        public void handleMessage(Message message) {
            ArrayList arrayList;
            Object obj;
            ArrayList arrayList2;
            int size;
            int i;
            m mVar;
            ArrayList arrayList3 = (ArrayList) m.i.get();
            ArrayList arrayList4 = (ArrayList) m.k.get();
            switch (message.what) {
                case 0:
                    arrayList = (ArrayList) m.j.get();
                    if (arrayList3.size() > 0 || arrayList4.size() > 0) {
                        obj = null;
                    } else {
                        int i2 = 1;
                    }
                    while (arrayList.size() > 0) {
                        arrayList2 = (ArrayList) arrayList.clone();
                        arrayList.clear();
                        size = arrayList2.size();
                        for (i = 0; i < size; i++) {
                            mVar = (m) arrayList2.get(i);
                            if (mVar.y == 0) {
                                mVar.n();
                            } else {
                                arrayList4.add(mVar);
                            }
                        }
                    }
                    break;
                case 1:
                    obj = 1;
                    break;
                default:
                    return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            arrayList = (ArrayList) m.m.get();
            arrayList2 = (ArrayList) m.l.get();
            size = arrayList4.size();
            for (i = 0; i < size; i++) {
                mVar = (m) arrayList4.get(i);
                if (mVar.b(currentAnimationTimeMillis)) {
                    arrayList.add(mVar);
                }
            }
            size = arrayList.size();
            if (size > 0) {
                for (i = 0; i < size; i++) {
                    mVar = (m) arrayList.get(i);
                    mVar.n();
                    mVar.v = true;
                    arrayList4.remove(mVar);
                }
                arrayList.clear();
            }
            i = arrayList3.size();
            int i3 = 0;
            while (i3 < i) {
                int i4;
                m mVar2 = (m) arrayList3.get(i3);
                if (mVar2.e(currentAnimationTimeMillis)) {
                    arrayList2.add(mVar2);
                }
                if (arrayList3.size() == i) {
                    i4 = i3 + 1;
                    i3 = i;
                } else {
                    i--;
                    arrayList2.remove(mVar2);
                    i4 = i3;
                    i3 = i;
                }
                i = i3;
                i3 = i4;
            }
            if (arrayList2.size() > 0) {
                for (i3 = 0; i3 < arrayList2.size(); i3++) {
                    ((m) arrayList2.get(i3)).e();
                }
                arrayList2.clear();
            }
            if (obj == null) {
                return;
            }
            if (!arrayList3.isEmpty() || !arrayList4.isEmpty()) {
                sendEmptyMessageDelayed(1, Math.max(0, m.z - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
            }
        }
    }

    /* compiled from: ValueAnimator */
    public interface b {
        void a(m mVar);
    }

    public /* synthetic */ a a(long j) {
        return c(j);
    }

    public /* synthetic */ a c() {
        return f();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return f();
    }

    public static m b(float... fArr) {
        m mVar = new m();
        mVar.a(fArr);
        return mVar;
    }

    public void a(float... fArr) {
        if (fArr != null && fArr.length != 0) {
            if (this.f == null || this.f.length == 0) {
                a(k.a("", fArr));
            } else {
                this.f[0].a(fArr);
            }
            this.e = false;
        }
    }

    public void a(k... kVarArr) {
        this.f = kVarArr;
        this.g = new HashMap(r2);
        for (k kVar : kVarArr) {
            this.g.put(kVar.c(), kVar);
        }
        this.e = false;
    }

    void d() {
        if (!this.e) {
            for (k b : this.f) {
                b.b();
            }
            this.e = true;
        }
    }

    public m c(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.x = j;
        return this;
    }

    public void d(long j) {
        d();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.d != 1) {
            this.c = j;
            this.d = 2;
        }
        this.b = currentAnimationTimeMillis - j;
        e(currentAnimationTimeMillis);
    }

    public long g() {
        if (!this.e || this.d == 0) {
            return 0;
        }
        return AnimationUtils.currentAnimationTimeMillis() - this.b;
    }

    public void a(Interpolator interpolator) {
        if (interpolator != null) {
            this.C = interpolator;
        } else {
            this.C = new LinearInterpolator();
        }
    }

    private void a(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.q = z;
        this.r = 0;
        this.d = 0;
        this.w = true;
        this.t = false;
        ((ArrayList) j.get()).add(this);
        if (this.y == 0) {
            d(g());
            this.d = 0;
            this.v = true;
            if (this.a != null) {
                ArrayList arrayList = (ArrayList) this.a.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((com.nineoldandroids.a.a.a) arrayList.get(i)).b(this);
                }
            }
        }
        a aVar = (a) h.get();
        if (aVar == null) {
            aVar = new a();
            h.set(aVar);
        }
        aVar.sendEmptyMessage(0);
    }

    public void a() {
        a(false);
    }

    private void e() {
        ((ArrayList) i.get()).remove(this);
        ((ArrayList) j.get()).remove(this);
        ((ArrayList) k.get()).remove(this);
        this.d = 0;
        if (this.v && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((com.nineoldandroids.a.a.a) arrayList.get(i)).a(this);
            }
        }
        this.v = false;
        this.w = false;
    }

    private void n() {
        d();
        ((ArrayList) i.get()).add(this);
        if (this.y > 0 && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((com.nineoldandroids.a.a.a) arrayList.get(i)).b(this);
            }
        }
    }

    private boolean b(long j) {
        if (this.t) {
            long j2 = j - this.u;
            if (j2 > this.y) {
                this.b = j - (j2 - this.y);
                this.d = 1;
                return true;
            }
        }
        this.t = true;
        this.u = j;
        return false;
    }

    boolean e(long j) {
        boolean z = false;
        if (this.d == 0) {
            this.d = 1;
            if (this.c < 0) {
                this.b = j;
            } else {
                this.b = j - this.c;
                this.c = -1;
            }
        }
        switch (this.d) {
            case 1:
            case 2:
                float f;
                float f2 = this.x > 0 ? ((float) (j - this.b)) / ((float) this.x) : 1.0f;
                if (f2 < 1.0f) {
                    f = f2;
                } else if (this.r < this.A || this.A == -1) {
                    if (this.a != null) {
                        int size = this.a.size();
                        for (int i = 0; i < size; i++) {
                            ((com.nineoldandroids.a.a.a) this.a.get(i)).c(this);
                        }
                    }
                    if (this.B == 2) {
                        this.q = !this.q;
                    }
                    this.r += (int) f2;
                    f = f2 % 1.0f;
                    this.b += this.x;
                } else {
                    f = Math.min(f2, 1.0f);
                    z = true;
                }
                if (this.q) {
                    f = 1.0f - f;
                }
                a(f);
                break;
        }
        return z;
    }

    void a(float f) {
        int i;
        float interpolation = this.C.getInterpolation(f);
        this.s = interpolation;
        for (k a : this.f) {
            a.a(interpolation);
        }
        if (this.D != null) {
            int size = this.D.size();
            for (i = 0; i < size; i++) {
                ((b) this.D.get(i)).a(this);
            }
        }
    }

    public m f() {
        int i = 0;
        m mVar = (m) super.c();
        if (this.D != null) {
            ArrayList arrayList = this.D;
            mVar.D = new ArrayList();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                mVar.D.add(arrayList.get(i2));
            }
        }
        mVar.c = -1;
        mVar.q = false;
        mVar.r = 0;
        mVar.e = false;
        mVar.d = 0;
        mVar.t = false;
        k[] kVarArr = this.f;
        if (kVarArr != null) {
            int length = kVarArr.length;
            mVar.f = new k[length];
            mVar.g = new HashMap(length);
            while (i < length) {
                k a = kVarArr[i].a();
                mVar.f[i] = a;
                mVar.g.put(a.c(), a);
                i++;
            }
        }
        return mVar;
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.f != null) {
            for (k kVar : this.f) {
                str = str + "\n    " + kVar.toString();
            }
        }
        return str;
    }
}
