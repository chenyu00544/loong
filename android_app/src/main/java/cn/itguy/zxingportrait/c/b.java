package cn.itguy.zxingportrait.c;

import com.google.zxing.BarcodeFormat;
import java.util.Collection;
import java.util.EnumSet;
import java.util.regex.Pattern;

/* compiled from: DecodeFormatManager */
final class b {
    static final Collection<BarcodeFormat> a = EnumSet.of(BarcodeFormat.UPC_A, new BarcodeFormat[]{BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED});
    static final Collection<BarcodeFormat> b = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
    static final Collection<BarcodeFormat> c = EnumSet.of(BarcodeFormat.QR_CODE);
    static final Collection<BarcodeFormat> d = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    private static final Pattern e = Pattern.compile(",");

    static {
        b.addAll(a);
    }
}
