package com.ecjia.hamster.adapter;

import com.ecjia.a.k;
import com.ecjia.a.q;
import com.ecjia.hamster.model.av;
import com.ecjia.hamster.model.aw;
import com.ecjia.hamster.model.p;
import java.util.ArrayList;

/* compiled from: ECJiaGoodDetailDraft */
public class n {
    private static n d;
    public p a = new p();
    public ArrayList<aw> b = new ArrayList();
    public int c = 1;

    public static n a() {
        if (d == null) {
            d = new n();
        }
        return d;
    }

    public void b() {
        this.a = null;
        this.b.clear();
        this.c = 1;
    }

    public boolean a(int i) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (((aw) this.b.get(i2)).c() == i) {
                return true;
            }
        }
        return false;
    }

    public boolean a(String str) {
        for (int i = 0; i < this.b.size(); i++) {
            if (((aw) this.b.get(i)).a().compareTo(str) == 0) {
                return true;
            }
        }
        return false;
    }

    public void b(int i) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (((aw) this.b.get(i2)).c() == i) {
                this.b.remove(i2);
                return;
            }
        }
    }

    public void a(aw awVar) {
        if (awVar.b().compareTo(av.b) != 0) {
            for (int i = 0; i < this.b.size(); i++) {
                if (((aw) this.b.get(i)).a().compareTo(awVar.a()) == 0) {
                    this.b.remove(i);
                }
            }
            this.b.add(awVar);
        } else if (!a(awVar.c())) {
            this.b.add(awVar);
        }
    }

    public float c() {
        float f = 0.0f;
        if (this.a == null || this.a.r() == null) {
            return f;
        }
        try {
            f = Float.valueOf(k.d(this.a.r())).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        float f2 = f;
        for (int i = 0; i < this.b.size(); i++) {
            try {
                f2 += Float.valueOf(k.d(((aw) this.b.get(i)).d())).floatValue();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return ((float) this.c) * f2;
    }

    public String d() {
        if (this.b.size() == 0) {
            return "";
        }
        int i;
        int i2;
        String str = "";
        int[] iArr = new int[this.b.size()];
        for (i = 0; i < this.b.size(); i++) {
            iArr[i] = ((aw) this.b.get(i)).c();
            q.a("attr_id a[i]" + iArr[i]);
        }
        for (i2 = 0; i2 < iArr.length; i2++) {
            for (i = i2 + 1; i < iArr.length; i++) {
                if (iArr[i] < iArr[i2]) {
                    int i3 = iArr[i2];
                    iArr[i2] = iArr[i];
                    iArr[i] = i3;
                }
            }
        }
        String str2 = str;
        for (i2 = 0; i2 < this.b.size(); i2++) {
            str2 = str2 + iArr[i2] + ",";
        }
        String str3 = str2.substring(0, str2.length() - 1) + "";
        q.a("attr_id " + str3);
        return str3;
    }
}
