package com.unionpay.mobile.android.pboctransaction.sdapdu;

import android.content.Context;
import android.util.Log;
import com.unionpay.mobile.android.g.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.pboctransaction.f;
import com.unionpay.mobile.android.utils.k;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

public final class a implements d {
    NativeSDWriter a = null;
    private Context b = null;
    private boolean c = false;

    private static ArrayList<c> b(e eVar) {
        if (eVar.a() == null) {
            Log.e("uppay", " select UPCard failed!!!!");
            return null;
        }
        String c = eVar.c();
        if (c == null) {
            Log.e("uppay", " getBankCardFileEntry failed!!!!");
            return null;
        }
        byte[] a = f.a(c);
        int i = ((a[0] & 255) << 24) | ((a[1] & 255) << 16);
        int i2 = Integer.MIN_VALUE;
        int i3 = 0;
        int i4 = 0;
        while (i4 < 10) {
            int i5 = (i2 & i) == 0 ? i3 + 1 : i3;
            i2 >>>= 1;
            i4++;
            i3 = i5;
        }
        if (i3 <= 0) {
            return null;
        }
        ArrayList<c> arrayList = new ArrayList(i3);
        int i6 = Integer.MIN_VALUE;
        int i7 = 1;
        while (i7 <= i3 && i7 < 11) {
            if ((i & i6) == 0) {
                String a2 = eVar.a(i7, com.unionpay.mobile.android.utils.c.a());
                if (a2 != null && a2.length() > 0) {
                    String str;
                    c = f.d(a2.substring(0, 40));
                    try {
                        str = new String(f.a(c), "gbk");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        str = c;
                    }
                    String substring = a2.substring(40, 60);
                    arrayList.add(new com.unionpay.mobile.android.g.a(8, Integer.toString(i7), str, f.c(substring), 2));
                    k.c("uppay", i7 + "----" + substring);
                }
            }
            i6 >>>= 1;
            i7++;
        }
        return arrayList;
    }

    public final String a(String str) {
        return "";
    }

    public final ArrayList<c> a(e eVar) {
        Collection collection = null;
        k.c("uppay", "SDEngine.readList() +++");
        if (!this.c) {
            return null;
        }
        ArrayList<c> arrayList = new ArrayList();
        String str = "A0000003330101";
        String a = eVar.a(new com.unionpay.mobile.android.pboctransaction.a(str, "1.0"));
        if (a != null && a.length() > 0) {
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(new com.unionpay.mobile.android.g.a(8, str, "", f.c(a), 1));
            k.c("uppay", str + "----" + a);
            collection = arrayList2;
        }
        if (collection != null && collection.size() > 0) {
            arrayList.addAll(collection);
        }
        collection = b(eVar);
        if (collection != null && collection.size() > 0) {
            arrayList.addAll(collection);
        }
        k.c("uppay", "SDEngine.readList() ---");
        return arrayList;
    }

    public final void a() {
    }

    public final void a(com.unionpay.mobile.android.pboctransaction.c cVar, Context context) {
        this.b = context;
        if (cVar != null) {
            cVar.a();
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        String str = "";
        if (this.a != null) {
            str = this.a.a(f.a(bArr));
        }
        return f.a(str);
    }

    public final void b() {
        Context context = this.b;
        this.a = new NativeSDWriter();
        Context context2 = this.b;
        b.a();
        ArrayList arrayList = new ArrayList();
        for (Object add : b.a) {
            arrayList.add(add);
        }
        this.c = this.a.a(arrayList);
    }

    public final void c() {
        if (this.a != null) {
            this.a.a();
        }
    }

    public final void d() {
    }
}
