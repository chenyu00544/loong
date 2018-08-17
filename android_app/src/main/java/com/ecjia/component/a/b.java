package com.ecjia.component.a;

import com.ecjia.hamster.activity.a;
import java.util.ArrayList;

/* compiled from: ECJiaActivityManagerModel */
public class b extends e {
    public static ArrayList<a> a = new ArrayList();
    public static ArrayList<a> b = new ArrayList();
    public static ArrayList<a> c = new ArrayList();

    public static void a(a aVar) {
        if (!a.contains(aVar)) {
            a.add(aVar);
        }
    }

    public static void b(a aVar) {
        a.remove(aVar);
        b.remove(aVar);
        c.remove(aVar);
    }

    public static void c(a aVar) {
        if (!b.contains(aVar)) {
            b.add(aVar);
        }
    }

    public static void d(a aVar) {
        b.remove(aVar);
    }
}
