package com.ecjia.component.view.wheel;

import java.util.ArrayList;

/* compiled from: ECJiaItemWheelAdapter */
public class a<T extends ECJiaWheelData> implements e {
    private ArrayList<T> a;
    private int b;

    public /* synthetic */ Object b(int i) {
        return a(i);
    }

    public a(ArrayList<T> arrayList, int i) {
        this.a = arrayList;
        this.b = i;
    }

    public a(ArrayList<T> arrayList) {
        this(arrayList, -1);
    }

    public T a(int i) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        return (ECJiaWheelData) this.a.get(i);
    }

    public int a() {
        return this.a.size();
    }

    public int b() {
        return this.b;
    }
}
