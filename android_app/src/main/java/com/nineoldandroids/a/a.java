package com.nineoldandroids.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;

/* compiled from: Animator */
public abstract class a implements Cloneable {
    ArrayList<a> a = null;

    /* compiled from: Animator */
    public interface a {
        void a(a aVar);

        void b(a aVar);

        void c(a aVar);
    }

    public abstract a a(long j);

    public abstract void a(Interpolator interpolator);

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public void a() {
    }

    public void a(a aVar) {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(aVar);
    }

    public void b(a aVar) {
        if (this.a != null) {
            this.a.remove(aVar);
            if (this.a.size() == 0) {
                this.a = null;
            }
        }
    }

    public ArrayList<a> b() {
        return this.a;
    }

    public a c() {
        try {
            a aVar = (a) super.clone();
            if (this.a != null) {
                ArrayList arrayList = this.a;
                aVar.a = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    aVar.a.add(arrayList.get(i));
                }
            }
            return aVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
