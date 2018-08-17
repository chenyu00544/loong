package com.unionpay.utils;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.InputDeviceCompat;

public final class a {
    public static String a(byte[] bArr) {
        String str = "0123456789abcdef";
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            stringBuilder.append(str.charAt(i >> 4));
            stringBuilder.append(str.charAt(i & 15));
        }
        return stringBuilder.toString();
    }

    public static byte[] a(String str) {
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int digit = (Character.digit(toCharArray[i * 2], 16) << 4) | Character.digit(toCharArray[(i * 2) + 1], 16);
            if (digit > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                digit += InputDeviceCompat.SOURCE_ANY;
            }
            bArr[i] = (byte) digit;
        }
        return bArr;
    }
}
