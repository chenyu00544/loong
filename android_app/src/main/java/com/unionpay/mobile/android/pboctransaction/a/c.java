package com.unionpay.mobile.android.pboctransaction.a;

import android.support.v4.media.TransportMediator;
import com.unionpay.mobile.android.pboctransaction.f;

public final class c {
    public static String a(String str, String str2) {
        int i = 1;
        if (str == null) {
            return null;
        }
        Object a = f.a(str);
        int i2 = 0;
        while (i2 < a.length) {
            int i3 = ((byte) (a[i2] & 31)) == (byte) 31 ? 2 : 1;
            byte[] bArr = new byte[i3];
            System.arraycopy(a, i2, bArr, 0, i3);
            if (f.a(bArr, i3).compareToIgnoreCase(str2) == 0) {
                i3 += i2;
                if (((byte) (a[i3] & 128)) != Byte.MIN_VALUE) {
                    i2 = a[i3] & 255;
                } else {
                    i = (a[i3] & TransportMediator.KEYCODE_MEDIA_PAUSE) + 1;
                    i2 = i == 2 ? a[i3 + 1] & 255 : i == 3 ? ((a[i3 + 1] & 255) << 8) | (a[i3 + 2] & 255) : i == 4 ? (((a[i3 + 1] & 255) << 16) | ((a[i3 + 2] & 255) << 8)) | (a[i3 + 3] & 255) : 0;
                }
                byte[] bArr2 = new byte[i2];
                System.arraycopy(a, i3 + i, bArr2, 0, i2);
                return f.a(bArr2, i2);
            } else if ((a[i2] & 32) == 32) {
                i3 += i2;
                i2 = (i3 >= a.length || ((byte) (a[i3] & 128)) != Byte.MIN_VALUE) ? 1 : (a[i3] & TransportMediator.KEYCODE_MEDIA_PAUSE) + 1;
                i2 += i3;
            } else {
                int i4 = i2 + i3;
                if (i4 >= a.length || ((byte) (a[i4] & 128)) != (byte) 0) {
                    i3 = i4 < a.length ? (a[i4] & TransportMediator.KEYCODE_MEDIA_PAUSE) + 1 : 0;
                    i2 = (i3 != 2 || i4 + 1 >= a.length) ? (i3 != 3 || i4 + 2 >= a.length) ? (i3 != 4 || i4 + 2 >= a.length) ? 0 : (((a[i4 + 1] & 255) << 16) | ((a[i4 + 2] & 255) << 8)) | (a[i4 + 3] & 255) : ((a[i4 + 1] & 255) << 8) | (a[i4 + 2] & 255) : a[i4 + 1] & 255;
                } else {
                    i2 = a[i4] & 255;
                    i3 = 1;
                }
                i2 = (i2 + i3) + i4;
            }
        }
        return null;
    }
}
