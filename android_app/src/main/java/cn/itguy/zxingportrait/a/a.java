package cn.itguy.zxingportrait.a;

import android.os.Build.VERSION;
import android.util.Log;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

/* compiled from: PlatformSupportManager */
public abstract class a<T> {
    private static final String a = a.class.getSimpleName();
    private final Class<T> b;
    private final T c;
    private final SortedMap<Integer, String> d;

    protected a(Class<T> cls, T t) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException();
        } else if (cls.isInstance(t)) {
            this.b = cls;
            this.c = t;
            this.d = new TreeMap(Collections.reverseOrder());
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected final void a(int i, String str) {
        this.d.put(Integer.valueOf(i), str);
    }

    public final T a() {
        for (Integer num : this.d.keySet()) {
            if (VERSION.SDK_INT >= num.intValue()) {
                try {
                    Class asSubclass = Class.forName((String) this.d.get(num)).asSubclass(this.b);
                    Log.i(a, "Using implementation " + asSubclass + " of " + this.b + " for SDK " + num);
                    return asSubclass.getConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable e) {
                    Log.w(a, e);
                } catch (Throwable e2) {
                    Log.w(a, e2);
                } catch (Throwable e22) {
                    Log.w(a, e22);
                } catch (Throwable e222) {
                    Log.w(a, e222);
                } catch (Throwable e2222) {
                    Log.w(a, e2222);
                }
            }
        }
        Log.i(a, "Using default implementation " + this.c.getClass() + " of " + this.b);
        return this.c;
    }
}
