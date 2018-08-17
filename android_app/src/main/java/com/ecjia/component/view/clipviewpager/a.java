package com.ecjia.component.view.clipviewpager;

import android.os.Build.VERSION;
import android.util.SparseArray;
import android.view.View;

/* compiled from: ECJiaRecycleBin */
public class a {
    private View[] a = new View[0];
    private int[] b = new int[0];
    private SparseArray<View>[] c;
    private int d;
    private SparseArray<View> e;

    public void a(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
        }
        SparseArray[] sparseArrayArr = new SparseArray[i];
        for (int i2 = 0; i2 < i; i2++) {
            sparseArrayArr[i2] = new SparseArray();
        }
        this.d = i;
        this.e = sparseArrayArr[0];
        this.c = sparseArrayArr;
    }

    protected boolean b(int i) {
        return i >= 0;
    }

    View a(int i, int i2) {
        if (this.d == 1) {
            return a(this.e, i);
        }
        if (i2 < 0 || i2 >= this.c.length) {
            return null;
        }
        return a(this.c[i2], i);
    }

    void a(View view, int i, int i2) {
        if (this.d == 1) {
            this.e.put(i, view);
        } else {
            this.c[i2].put(i, view);
        }
        if (VERSION.SDK_INT >= 14) {
            view.setAccessibilityDelegate(null);
        }
    }

    void a() {
        Object obj = 1;
        View[] viewArr = this.a;
        int[] iArr = this.b;
        if (this.d <= 1) {
            obj = null;
        }
        SparseArray sparseArray = this.e;
        for (int length = viewArr.length - 1; length >= 0; length--) {
            View view = viewArr[length];
            if (view != null) {
                int i = iArr[length];
                viewArr[length] = null;
                iArr[length] = -1;
                if (b(i)) {
                    if (obj != null) {
                        sparseArray = this.c[i];
                    }
                    sparseArray.put(length, view);
                    if (VERSION.SDK_INT >= 14) {
                        view.setAccessibilityDelegate(null);
                    }
                }
            }
        }
        b();
    }

    private void b() {
        int length = this.a.length;
        int i = this.d;
        SparseArray[] sparseArrayArr = this.c;
        for (int i2 = 0; i2 < i; i2++) {
            SparseArray sparseArray = sparseArrayArr[i2];
            int size = sparseArray.size();
            int i3 = size - length;
            int i4 = size - 1;
            size = 0;
            while (size < i3) {
                int i5 = i4 - 1;
                sparseArray.remove(sparseArray.keyAt(i4));
                size++;
                i4 = i5;
            }
        }
    }

    static View a(SparseArray<View> sparseArray, int i) {
        int size = sparseArray.size();
        if (size <= 0) {
            return null;
        }
        int i2;
        View view;
        for (i2 = 0; i2 < size; i2++) {
            int keyAt = sparseArray.keyAt(i2);
            view = (View) sparseArray.get(keyAt);
            if (keyAt == i) {
                sparseArray.remove(keyAt);
                return view;
            }
        }
        i2 = size - 1;
        view = (View) sparseArray.valueAt(i2);
        sparseArray.remove(sparseArray.keyAt(i2));
        return view;
    }
}
