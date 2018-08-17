package com.nineoldandroids.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: AnimatorSet */
public final class c extends a {
    boolean b = false;
    private ArrayList<a> c = new ArrayList();
    private HashMap<a, e> d = new HashMap();
    private ArrayList<e> e = new ArrayList();
    private ArrayList<e> f = new ArrayList();
    private boolean g = true;
    private a h = null;
    private boolean i = false;
    private long j = 0;
    private m k = null;
    private long l = -1;

    /* compiled from: AnimatorSet */
    private class a implements com.nineoldandroids.a.a.a {
        final /* synthetic */ c a;
        private c b;

        a(c cVar, c cVar2) {
            this.a = cVar;
            this.b = cVar2;
        }

        public void a(a aVar) {
            aVar.b(this);
            this.a.c.remove(aVar);
            ((e) this.b.d.get(aVar)).f = true;
            if (!this.a.b) {
                int i;
                boolean z;
                ArrayList c = this.b.f;
                int size = c.size();
                for (i = 0; i < size; i++) {
                    if (!((e) c.get(i)).f) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    if (this.a.a != null) {
                        ArrayList arrayList = (ArrayList) this.a.a.clone();
                        int size2 = arrayList.size();
                        for (i = 0; i < size2; i++) {
                            ((com.nineoldandroids.a.a.a) arrayList.get(i)).a(this.b);
                        }
                    }
                    this.b.i = false;
                }
            }
        }

        public void c(a aVar) {
        }

        public void b(a aVar) {
        }
    }

    /* compiled from: AnimatorSet */
    public class b {
        final /* synthetic */ c a;
        private e b;

        b(c cVar, a aVar) {
            this.a = cVar;
            this.b = (e) cVar.d.get(aVar);
            if (this.b == null) {
                this.b = new e(aVar);
                cVar.d.put(aVar, this.b);
                cVar.e.add(this.b);
            }
        }

        public b a(a aVar) {
            e eVar = (e) this.a.d.get(aVar);
            if (eVar == null) {
                eVar = new e(aVar);
                this.a.d.put(aVar, eVar);
                this.a.e.add(eVar);
            }
            eVar.a(new c(this.b, 0));
            return this;
        }

        public b b(a aVar) {
            e eVar = (e) this.a.d.get(aVar);
            if (eVar == null) {
                eVar = new e(aVar);
                this.a.d.put(aVar, eVar);
                this.a.e.add(eVar);
            }
            eVar.a(new c(this.b, 1));
            return this;
        }
    }

    /* compiled from: AnimatorSet */
    private static class c {
        public e a;
        public int b;

        public c(e eVar, int i) {
            this.a = eVar;
            this.b = i;
        }
    }

    /* compiled from: AnimatorSet */
    private static class d implements com.nineoldandroids.a.a.a {
        private c a;
        private e b;
        private int c;

        public d(c cVar, e eVar, int i) {
            this.a = cVar;
            this.b = eVar;
            this.c = i;
        }

        public void a(a aVar) {
            if (this.c == 1) {
                d(aVar);
            }
        }

        public void c(a aVar) {
        }

        public void b(a aVar) {
            if (this.c == 0) {
                d(aVar);
            }
        }

        private void d(a aVar) {
            if (!this.a.b) {
                Object obj;
                int size = this.b.c.size();
                for (int i = 0; i < size; i++) {
                    obj = (c) this.b.c.get(i);
                    if (obj.b == this.c && obj.a.a == aVar) {
                        aVar.b(this);
                        break;
                    }
                }
                obj = null;
                this.b.c.remove(obj);
                if (this.b.c.size() == 0) {
                    this.b.a.a();
                    this.a.c.add(this.b.a);
                }
            }
        }
    }

    /* compiled from: AnimatorSet */
    private static class e implements Cloneable {
        public a a;
        public ArrayList<c> b = null;
        public ArrayList<c> c = null;
        public ArrayList<e> d = null;
        public ArrayList<e> e = null;
        public boolean f = false;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public e(a aVar) {
            this.a = aVar;
        }

        public void a(c cVar) {
            if (this.b == null) {
                this.b = new ArrayList();
                this.d = new ArrayList();
            }
            this.b.add(cVar);
            if (!this.d.contains(cVar.a)) {
                this.d.add(cVar.a);
            }
            e eVar = cVar.a;
            if (eVar.e == null) {
                eVar.e = new ArrayList();
            }
            eVar.e.add(this);
        }

        public e a() {
            try {
                e eVar = (e) super.clone();
                eVar.a = this.a.c();
                return eVar;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    public /* synthetic */ a a(long j) {
        return b(j);
    }

    public /* synthetic */ a c() {
        return d();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public void a(a... aVarArr) {
        int i = 1;
        if (aVarArr != null) {
            this.g = true;
            b a = a(aVarArr[0]);
            while (i < aVarArr.length) {
                a.a(aVarArr[i]);
                i++;
            }
        }
    }

    public void b(a... aVarArr) {
        int i = 0;
        if (aVarArr != null) {
            this.g = true;
            if (aVarArr.length == 1) {
                a(aVarArr[0]);
                return;
            }
            while (i < aVarArr.length - 1) {
                a(aVarArr[i]).b(aVarArr[i + 1]);
                i++;
            }
        }
    }

    public void a(Interpolator interpolator) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((e) it.next()).a.a(interpolator);
        }
    }

    public b a(a aVar) {
        if (aVar == null) {
            return null;
        }
        this.g = true;
        return new b(this, aVar);
    }

    public c b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("duration must be a value of zero or greater");
        }
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((e) it.next()).a.a(j);
        }
        this.l = j;
        return this;
    }

    public void a() {
        int i;
        int i2;
        ArrayList arrayList;
        int i3 = 0;
        this.b = false;
        this.i = true;
        e();
        int size = this.f.size();
        for (i = 0; i < size; i++) {
            e eVar = (e) this.f.get(i);
            Collection b = eVar.a.b();
            if (b != null && b.size() > 0) {
                Iterator it = new ArrayList(b).iterator();
                while (it.hasNext()) {
                    com.nineoldandroids.a.a.a aVar = (com.nineoldandroids.a.a.a) it.next();
                    if ((aVar instanceof d) || (aVar instanceof a)) {
                        eVar.a.b(aVar);
                    }
                }
            }
        }
        final ArrayList arrayList2 = new ArrayList();
        for (i2 = 0; i2 < size; i2++) {
            eVar = (e) this.f.get(i2);
            if (this.h == null) {
                this.h = new a(this, this);
            }
            if (eVar.b == null || eVar.b.size() == 0) {
                arrayList2.add(eVar);
            } else {
                int size2 = eVar.b.size();
                for (i = 0; i < size2; i++) {
                    c cVar = (c) eVar.b.get(i);
                    cVar.a.a.a(new d(this, eVar, cVar.b));
                }
                eVar.c = (ArrayList) eVar.b.clone();
            }
            eVar.a.a(this.h);
        }
        if (this.j <= 0) {
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                eVar = (e) it2.next();
                eVar.a.a();
                this.c.add(eVar.a);
            }
        } else {
            this.k = m.b(0.0f, 1.0f);
            this.k.c(this.j);
            this.k.a(new b(this) {
                boolean a = false;
                final /* synthetic */ c c;

                public void a(a aVar) {
                    if (!this.a) {
                        int size = arrayList2.size();
                        for (int i = 0; i < size; i++) {
                            e eVar = (e) arrayList2.get(i);
                            eVar.a.a();
                            this.c.c.add(eVar.a);
                        }
                    }
                }
            });
            this.k.a();
        }
        if (this.a != null) {
            arrayList = (ArrayList) this.a.clone();
            i2 = arrayList.size();
            for (i = 0; i < i2; i++) {
                ((com.nineoldandroids.a.a.a) arrayList.get(i)).b(this);
            }
        }
        if (this.e.size() == 0 && this.j == 0) {
            this.i = false;
            if (this.a != null) {
                arrayList = (ArrayList) this.a.clone();
                i = arrayList.size();
                while (i3 < i) {
                    ((com.nineoldandroids.a.a.a) arrayList.get(i3)).a(this);
                    i3++;
                }
            }
        }
    }

    public c d() {
        c cVar = (c) super.c();
        cVar.g = true;
        cVar.b = false;
        cVar.i = false;
        cVar.c = new ArrayList();
        cVar.d = new HashMap();
        cVar.e = new ArrayList();
        cVar.f = new ArrayList();
        HashMap hashMap = new HashMap();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            e a = eVar.a();
            hashMap.put(eVar, a);
            cVar.e.add(a);
            cVar.d.put(a.a, a);
            a.b = null;
            a.c = null;
            a.e = null;
            a.d = null;
            ArrayList b = a.a.b();
            if (b != null) {
                Iterator it2 = b.iterator();
                ArrayList arrayList = null;
                while (it2.hasNext()) {
                    com.nineoldandroids.a.a.a aVar = (com.nineoldandroids.a.a.a) it2.next();
                    if (aVar instanceof a) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(aVar);
                    }
                }
                if (arrayList != null) {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        b.remove((com.nineoldandroids.a.a.a) it3.next());
                    }
                }
            }
        }
        it = this.e.iterator();
        while (it.hasNext()) {
            eVar = (e) it.next();
            a = (e) hashMap.get(eVar);
            if (eVar.b != null) {
                Iterator it4 = eVar.b.iterator();
                while (it4.hasNext()) {
                    c cVar2 = (c) it4.next();
                    a.a(new c((e) hashMap.get(cVar2.a), cVar2.b));
                }
            }
        }
        return cVar;
    }

    private void e() {
        int size;
        e eVar;
        int i;
        if (this.g) {
            this.f.clear();
            ArrayList arrayList = new ArrayList();
            size = this.e.size();
            for (int i2 = 0; i2 < size; i2++) {
                eVar = (e) this.e.get(i2);
                if (eVar.b == null || eVar.b.size() == 0) {
                    arrayList.add(eVar);
                }
            }
            Object arrayList2 = new ArrayList();
            while (arrayList.size() > 0) {
                int size2 = arrayList.size();
                for (i = 0; i < size2; i++) {
                    eVar = (e) arrayList.get(i);
                    this.f.add(eVar);
                    if (eVar.e != null) {
                        int size3 = eVar.e.size();
                        for (size = 0; size < size3; size++) {
                            e eVar2 = (e) eVar.e.get(size);
                            eVar2.d.remove(eVar);
                            if (eVar2.d.size() == 0) {
                                arrayList2.add(eVar2);
                            }
                        }
                    }
                }
                arrayList.clear();
                arrayList.addAll(arrayList2);
                arrayList2.clear();
            }
            this.g = false;
            if (this.f.size() != this.e.size()) {
                throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
            }
            return;
        }
        int size4 = this.e.size();
        for (i = 0; i < size4; i++) {
            eVar = (e) this.e.get(i);
            if (eVar.b != null && eVar.b.size() > 0) {
                int size5 = eVar.b.size();
                for (size = 0; size < size5; size++) {
                    c cVar = (c) eVar.b.get(size);
                    if (eVar.d == null) {
                        eVar.d = new ArrayList();
                    }
                    if (!eVar.d.contains(cVar.a)) {
                        eVar.d.add(cVar.a);
                    }
                }
            }
            eVar.f = false;
        }
    }
}
