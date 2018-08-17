package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.common.a;
import com.google.zxing.f;
import com.google.zxing.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* compiled from: MultiFormatUPCEANReader */
public final class j extends k {
    private final p[] a;

    public j(Map<DecodeHintType, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        Collection arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new e());
            } else if (collection.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new l());
            }
            if (collection.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new f());
            }
            if (collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new q());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new e());
            arrayList.add(new f());
            arrayList.add(new q());
        }
        this.a = (p[]) arrayList.toArray(new p[arrayList.size()]);
    }

    public g a(int i, a aVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        int[] a = p.a(aVar);
        p[] pVarArr = this.a;
        int length = pVarArr.length;
        int i2 = 0;
        while (i2 < length) {
            try {
                g a2 = pVarArr[i2].a(i, aVar, a, (Map) map);
                int i3 = (a2.d() == BarcodeFormat.EAN_13 && a2.a().charAt(0) == '0') ? 1 : 0;
                Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                if (collection == null || collection.contains(BarcodeFormat.UPC_A)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (i3 == 0 || r0 == 0) {
                    return a2;
                }
                g gVar = new g(a2.a().substring(1), a2.b(), a2.c(), BarcodeFormat.UPC_A);
                gVar.a(a2.e());
                return gVar;
            } catch (ReaderException e) {
                i2++;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void a() {
        for (f a : this.a) {
            a.a();
        }
    }
}
