package se.emilsjolander.stickylistheaders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: DistinctMultiHashMap */
class c<TKey, TItemValue> {
    LinkedHashMap<Object, List<TItemValue>> a;
    LinkedHashMap<Object, TKey> b;
    private a<TKey, TItemValue> c;

    /* compiled from: DistinctMultiHashMap */
    interface a<TKey, TItemValue> {
        Object a(TKey tKey);

        Object b(TItemValue tItemValue);
    }

    /* compiled from: DistinctMultiHashMap */
    class c_1 implements a<TKey, TItemValue> {
        c_1() {
        }

        public Object a(TKey tKey) {
            return tKey;
        }

        public Object b(TItemValue tItemValue) {
            return tItemValue;
        }
    }

    c() {
        this(new c_1());
    }

    c(a<TKey, TItemValue> aVar) {
        this.a = new LinkedHashMap();
        this.b = new LinkedHashMap();
        this.c = aVar;
    }

    public List<TItemValue> a(TKey tKey) {
        return (List) this.a.get(this.c.a(tKey));
    }

    public TKey b(TItemValue tItemValue) {
        return this.b.get(this.c.b(tItemValue));
    }

    public void a(TKey tKey, TItemValue tItemValue) {
        Object a = this.c.a(tKey);
        if (this.a.get(a) == null) {
            this.a.put(a, new ArrayList());
        }
        a = b(tItemValue);
        if (a != null) {
            ((List) this.a.get(this.c.a(a))).remove(tItemValue);
        }
        this.b.put(this.c.b(tItemValue), tKey);
        if (!a((List) this.a.get(this.c.a(tKey)), (Object) tItemValue)) {
            ((List) this.a.get(this.c.a(tKey))).add(tItemValue);
        }
    }

    protected boolean a(List<TItemValue> list, TItemValue tItemValue) {
        for (TItemValue b : list) {
            if (this.c.b(b).equals(this.c.b(tItemValue))) {
                return true;
            }
        }
        return false;
    }
}
