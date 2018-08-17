package com.google.zxing;

import com.google.zxing.a.b;
import com.google.zxing.c.i;
import com.google.zxing.qrcode.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* compiled from: MultiFormatReader */
public final class d implements f {
    private Map<DecodeHintType, ?> a;
    private f[] b;

    public g a(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        a((Map) map);
        return b(bVar);
    }

    public g a(b bVar) throws NotFoundException {
        if (this.b == null) {
            a(null);
        }
        return b(bVar);
    }

    public void a(Map<DecodeHintType, ?> map) {
        Object obj = null;
        this.a = map;
        Object obj2 = (map == null || !map.containsKey(DecodeHintType.TRY_HARDER)) ? null : 1;
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        Collection arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.UPC_E) || collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.CODABAR) || collection.contains(BarcodeFormat.CODE_39) || collection.contains(BarcodeFormat.CODE_93) || collection.contains(BarcodeFormat.CODE_128) || collection.contains(BarcodeFormat.ITF) || collection.contains(BarcodeFormat.RSS_14) || collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                obj = 1;
            }
            if (obj != null && obj2 == null) {
                arrayList.add(new i(map));
            }
            if (collection.contains(BarcodeFormat.QR_CODE)) {
                arrayList.add(new a());
            }
            if (collection.contains(BarcodeFormat.DATA_MATRIX)) {
                arrayList.add(new com.google.zxing.datamatrix.a());
            }
            if (collection.contains(BarcodeFormat.AZTEC)) {
                arrayList.add(new b());
            }
            if (collection.contains(BarcodeFormat.PDF_417)) {
                arrayList.add(new com.google.zxing.pdf417.b());
            }
            if (collection.contains(BarcodeFormat.MAXICODE)) {
                arrayList.add(new com.google.zxing.b.a());
            }
            if (!(obj == null || obj2 == null)) {
                arrayList.add(new i(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (obj2 == null) {
                arrayList.add(new i(map));
            }
            arrayList.add(new a());
            arrayList.add(new com.google.zxing.datamatrix.a());
            arrayList.add(new b());
            arrayList.add(new com.google.zxing.pdf417.b());
            arrayList.add(new com.google.zxing.b.a());
            if (obj2 != null) {
                arrayList.add(new i(map));
            }
        }
        this.b = (f[]) arrayList.toArray(new f[arrayList.size()]);
    }

    public void a() {
        if (this.b != null) {
            for (f a : this.b) {
                a.a();
            }
        }
    }

    private g b(b bVar) throws NotFoundException {
        if (this.b != null) {
            f[] fVarArr = this.b;
            int length = fVarArr.length;
            int i = 0;
            while (i < length) {
                try {
                    return fVarArr[i].a(bVar, this.a);
                } catch (ReaderException e) {
                    i++;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
