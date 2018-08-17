package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.b;
import com.google.zxing.common.a;
import com.google.zxing.g;
import java.util.Map;

/* compiled from: UPCAReader */
public final class l extends p {
    private final p a = new e();

    public g a(int i, a aVar, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        return a(this.a.a(i, aVar, iArr, (Map) map));
    }

    public g a(int i, a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        return a(this.a.a(i, aVar, (Map) map));
    }

    public g a(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        return a(this.a.a(bVar, map));
    }

    BarcodeFormat b() {
        return BarcodeFormat.UPC_A;
    }

    protected int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws NotFoundException {
        return this.a.a(aVar, iArr, stringBuilder);
    }

    private static g a(g gVar) throws FormatException {
        String a = gVar.a();
        if (a.charAt(0) == '0') {
            return new g(a.substring(1), null, gVar.c(), BarcodeFormat.UPC_A);
        }
        throw FormatException.getFormatInstance();
    }
}
