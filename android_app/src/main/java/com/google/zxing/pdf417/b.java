package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.d;
import com.google.zxing.f;
import com.google.zxing.g;
import com.google.zxing.h;
import com.google.zxing.pdf417.a.j;
import com.google.zxing.pdf417.b.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: PDF417Reader */
public final class b implements f {
    public g a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        g[] a = a(bVar, map, false);
        if (a != null && a.length != 0 && a[0] != null) {
            return a[0];
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static g[] a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException, FormatException, ChecksumException {
        List arrayList = new ArrayList();
        com.google.zxing.pdf417.b.b a = a.a(bVar, (Map) map, z);
        for (h[] hVarArr : a.b()) {
            d a2 = j.a(a.a(), hVarArr[4], hVarArr[5], hVarArr[6], hVarArr[7], b(hVarArr), a(hVarArr));
            if (a2 == null) {
                throw NotFoundException.getNotFoundInstance();
            }
            g gVar = new g(a2.b(), a2.a(), hVarArr, BarcodeFormat.PDF_417);
            gVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, a2.d());
            c cVar = (c) a2.e();
            if (cVar != null) {
                gVar.a(ResultMetadataType.PDF417_EXTRA_METADATA, cVar);
            }
            arrayList.add(gVar);
        }
        return (g[]) arrayList.toArray(new g[arrayList.size()]);
    }

    private static int a(h hVar, h hVar2) {
        if (hVar == null || hVar2 == null) {
            return 0;
        }
        return (int) Math.abs(hVar.a() - hVar2.a());
    }

    private static int b(h hVar, h hVar2) {
        if (hVar == null || hVar2 == null) {
            return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return (int) Math.abs(hVar.a() - hVar2.a());
    }

    private static int a(h[] hVarArr) {
        return Math.max(Math.max(a(hVarArr[0], hVarArr[4]), (a(hVarArr[6], hVarArr[2]) * 17) / 18), Math.max(a(hVarArr[1], hVarArr[5]), (a(hVarArr[7], hVarArr[3]) * 17) / 18));
    }

    private static int b(h[] hVarArr) {
        return Math.min(Math.min(b(hVarArr[0], hVarArr[4]), (b(hVarArr[6], hVarArr[2]) * 17) / 18), Math.min(b(hVarArr[1], hVarArr[5]), (b(hVarArr[7], hVarArr[3]) * 17) / 18));
    }

    public void a() {
    }
}
