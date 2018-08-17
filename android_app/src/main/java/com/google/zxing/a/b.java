package com.google.zxing.a;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.a.b.a;
import com.google.zxing.common.d;
import com.google.zxing.f;
import com.google.zxing.g;
import com.google.zxing.h;
import com.google.zxing.i;
import java.util.List;
import java.util.Map;

/* compiled from: AztecReader */
public final class b implements f {
    public g a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        a a = new a(bVar.c()).a();
        h[] e = a.e();
        if (map != null) {
            i iVar = (i) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (iVar != null) {
                for (h a2 : e) {
                    iVar.a(a2);
                }
            }
        }
        d a3 = new com.google.zxing.a.a.a().a(a);
        g gVar = new g(a3.b(), a3.a(), e, BarcodeFormat.AZTEC);
        List c = a3.c();
        if (c != null) {
            gVar.a(ResultMetadataType.BYTE_SEGMENTS, c);
        }
        String d = a3.d();
        if (d != null) {
            gVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, d);
        }
        return gVar;
    }

    public void a() {
    }
}
