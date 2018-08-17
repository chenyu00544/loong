package com.ecjia.a;

import java.util.Calendar;

/* compiled from: ECJiaCalenderViewHelper */
public class c {
    public static int a() {
        Calendar instance = Calendar.getInstance();
        instance.set(5, 1);
        int i = instance.get(7);
        System.out.println("本月第一天是：" + i);
        return i;
    }

    public static int b() {
        Calendar instance = Calendar.getInstance();
        instance.set(5, 1);
        instance.roll(5, -1);
        return instance.get(5);
    }

    public static int a(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, i);
        instance.set(2, i2 - 1);
        instance.set(5, 1);
        instance.roll(5, -1);
        return instance.get(5);
    }

    public static int c() {
        int i = 4;
        Calendar instance = Calendar.getInstance();
        instance.get(5);
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(1);
        instance.get(7);
        instance.get(5);
        instance.get(6);
        int a = a(i3, i2);
        i2 = a();
        if (a == 28) {
            if (i2 != 1) {
                i = 5;
            }
        } else if (a == 29) {
            i = 5;
        } else if (a == 30) {
            i = i2 == 7 ? 6 : 5;
        } else if (a == 31) {
            i = (i2 == 6 || i2 == 7) ? 6 : 5;
        }
        q.a("length" + i);
        return i;
    }

    public static int a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        instance.get(1);
        int i = instance.get(2) + 1;
        i = instance.get(5);
        instance.get(6);
        instance.get(5);
        instance.get(7);
        return i;
    }

    public static int b(long j) {
        return a(j) + (a() - 1);
    }
}
