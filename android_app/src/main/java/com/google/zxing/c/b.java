package com.google.zxing.c;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;

/* compiled from: Code128Reader */
public final class b extends k {
    static final int[][] a = new int[][]{new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};

    private static int[] a(a aVar) throws NotFoundException {
        int a = aVar.a();
        int c = aVar.c(0);
        Object obj = new int[6];
        int length = obj.length;
        int i = c;
        int i2 = 0;
        int i3 = c;
        int i4 = 0;
        while (i < a) {
            int i5;
            int i6;
            if ((aVar.a(i) ^ i2) != 0) {
                obj[i4] = obj[i4] + 1;
                i5 = i2;
                i6 = i4;
            } else {
                if (i4 == length - 1) {
                    int i7 = 64;
                    c = -1;
                    i6 = 103;
                    while (i6 <= 105) {
                        i5 = k.a((int[]) obj, a[i6], 179);
                        if (i5 < i7) {
                            c = i6;
                        } else {
                            i5 = i7;
                        }
                        i6++;
                        i7 = i5;
                    }
                    if (c < 0 || !aVar.a(Math.max(0, i3 - ((i - i3) / 2)), i3, false)) {
                        c = (obj[0] + obj[1]) + i3;
                        System.arraycopy(obj, 2, obj, 0, length - 2);
                        obj[length - 2] = null;
                        obj[length - 1] = null;
                        i6 = i4 - 1;
                    } else {
                        return new int[]{i3, i, c};
                    }
                }
                i6 = i4 + 1;
                c = i3;
                obj[i6] = 1;
                if (i2 == 0) {
                    i5 = 1;
                } else {
                    boolean z = false;
                }
                i3 = c;
            }
            i++;
            i2 = i5;
            i4 = i6;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(a aVar, int[] iArr, int i) throws NotFoundException {
        k.a(aVar, i, iArr);
        int i2 = 64;
        int i3 = -1;
        for (int i4 = 0; i4 < a.length; i4++) {
            int a = k.a(iArr, a[i4], 179);
            if (a < i2) {
                i3 = i4;
                i2 = a;
            }
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.g a(int r24, com.google.zxing.common.a r25, java.util.Map<com.google.zxing.DecodeHintType, ?> r26) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
        r23 = this;
        if (r26 == 0) goto L_0x001c;
    L_0x0002:
        r2 = com.google.zxing.DecodeHintType.ASSUME_GS1;
        r0 = r26;
        r2 = r0.containsKey(r2);
        if (r2 == 0) goto L_0x001c;
    L_0x000c:
        r2 = 1;
    L_0x000d:
        r15 = a(r25);
        r3 = 2;
        r5 = r15[r3];
        switch(r5) {
            case 103: goto L_0x001e;
            case 104: goto L_0x0080;
            case 105: goto L_0x0083;
            default: goto L_0x0017;
        };
    L_0x0017:
        r2 = com.google.zxing.FormatException.getFormatInstance();
        throw r2;
    L_0x001c:
        r2 = 0;
        goto L_0x000d;
    L_0x001e:
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
    L_0x0020:
        r10 = 0;
        r9 = 0;
        r16 = new java.lang.StringBuilder;
        r4 = 20;
        r0 = r16;
        r0.<init>(r4);
        r17 = new java.util.ArrayList;
        r4 = 20;
        r0 = r17;
        r0.<init>(r4);
        r4 = 0;
        r7 = r15[r4];
        r4 = 1;
        r11 = r15[r4];
        r4 = 6;
        r0 = new int[r4];
        r18 = r0;
        r12 = 0;
        r6 = 0;
        r4 = 0;
        r8 = 1;
        r14 = r9;
        r9 = r7;
        r7 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r12;
        r12 = r6;
        r6 = r10;
    L_0x004b:
        if (r6 != 0) goto L_0x01e9;
    L_0x004d:
        r5 = 0;
        r0 = r25;
        r1 = r18;
        r13 = a(r0, r1, r11);
        r9 = (byte) r13;
        r9 = java.lang.Byte.valueOf(r9);
        r0 = r17;
        r0.add(r9);
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r13 == r9) goto L_0x0065;
    L_0x0064:
        r8 = 1;
    L_0x0065:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r13 == r9) goto L_0x006e;
    L_0x0069:
        r3 = r3 + 1;
        r9 = r3 * r13;
        r4 = r4 + r9;
    L_0x006e:
        r0 = r18;
        r0 = r0.length;
        r19 = r0;
        r9 = 0;
        r10 = r11;
    L_0x0075:
        r0 = r19;
        if (r9 >= r0) goto L_0x0086;
    L_0x0079:
        r20 = r18[r9];
        r10 = r10 + r20;
        r9 = r9 + 1;
        goto L_0x0075;
    L_0x0080:
        r3 = 100;
        goto L_0x0020;
    L_0x0083:
        r3 = 99;
        goto L_0x0020;
    L_0x0086:
        switch(r13) {
            case 103: goto L_0x00aa;
            case 104: goto L_0x00aa;
            case 105: goto L_0x00aa;
            default: goto L_0x0089;
        };
    L_0x0089:
        switch(r7) {
            case 99: goto L_0x0164;
            case 100: goto L_0x0114;
            case 101: goto L_0x00af;
            default: goto L_0x008c;
        };
    L_0x008c:
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
    L_0x0096:
        if (r14 == 0) goto L_0x009e;
    L_0x0098:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r5 != r9) goto L_0x01e5;
    L_0x009c:
        r5 = 100;
    L_0x009e:
        r9 = r11;
        r14 = r7;
        r11 = r10;
        r7 = r5;
        r5 = r12;
        r12 = r13;
        r21 = r8;
        r8 = r6;
        r6 = r21;
        goto L_0x004b;
    L_0x00aa:
        r2 = com.google.zxing.FormatException.getFormatInstance();
        throw r2;
    L_0x00af:
        r9 = 64;
        if (r13 >= r9) goto L_0x00c6;
    L_0x00b3:
        r9 = r13 + 32;
        r9 = (char) r9;
        r0 = r16;
        r0.append(r9);
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x00c6:
        r9 = 96;
        if (r13 >= r9) goto L_0x00dd;
    L_0x00ca:
        r9 = r13 + -64;
        r9 = (char) r9;
        r0 = r16;
        r0.append(r9);
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x00dd:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r13 == r9) goto L_0x00e2;
    L_0x00e1:
        r8 = 0;
    L_0x00e2:
        switch(r13) {
            case 96: goto L_0x00e5;
            case 97: goto L_0x00e5;
            case 98: goto L_0x0108;
            case 99: goto L_0x010f;
            case 100: goto L_0x010c;
            case 101: goto L_0x00e5;
            case 102: goto L_0x00f0;
            case 103: goto L_0x00e5;
            case 104: goto L_0x00e5;
            case 105: goto L_0x00e5;
            case 106: goto L_0x0112;
            default: goto L_0x00e5;
        };
    L_0x00e5:
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x00f0:
        if (r2 == 0) goto L_0x00e5;
    L_0x00f2:
        r9 = r16.length();
        if (r9 != 0) goto L_0x0100;
    L_0x00f8:
        r9 = "]C1";
        r0 = r16;
        r0.append(r9);
        goto L_0x00e5;
    L_0x0100:
        r9 = 29;
        r0 = r16;
        r0.append(r9);
        goto L_0x00e5;
    L_0x0108:
        r5 = 1;
        r7 = 100;
        goto L_0x00e5;
    L_0x010c:
        r7 = 100;
        goto L_0x00e5;
    L_0x010f:
        r7 = 99;
        goto L_0x00e5;
    L_0x0112:
        r6 = 1;
        goto L_0x00e5;
    L_0x0114:
        r9 = 96;
        if (r13 >= r9) goto L_0x012c;
    L_0x0118:
        r9 = r13 + 32;
        r9 = (char) r9;
        r0 = r16;
        r0.append(r9);
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x012c:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r13 == r9) goto L_0x0131;
    L_0x0130:
        r8 = 0;
    L_0x0131:
        switch(r13) {
            case 96: goto L_0x0134;
            case 97: goto L_0x0134;
            case 98: goto L_0x0158;
            case 99: goto L_0x015f;
            case 100: goto L_0x0134;
            case 101: goto L_0x015c;
            case 102: goto L_0x0140;
            case 103: goto L_0x0134;
            case 104: goto L_0x0134;
            case 105: goto L_0x0134;
            case 106: goto L_0x0162;
            default: goto L_0x0134;
        };
    L_0x0134:
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x0140:
        if (r2 == 0) goto L_0x0134;
    L_0x0142:
        r9 = r16.length();
        if (r9 != 0) goto L_0x0150;
    L_0x0148:
        r9 = "]C1";
        r0 = r16;
        r0.append(r9);
        goto L_0x0134;
    L_0x0150:
        r9 = 29;
        r0 = r16;
        r0.append(r9);
        goto L_0x0134;
    L_0x0158:
        r5 = 1;
        r7 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0134;
    L_0x015c:
        r7 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0134;
    L_0x015f:
        r7 = 99;
        goto L_0x0134;
    L_0x0162:
        r6 = 1;
        goto L_0x0134;
    L_0x0164:
        r9 = 100;
        if (r13 >= r9) goto L_0x0184;
    L_0x0168:
        r9 = 10;
        if (r13 >= r9) goto L_0x0173;
    L_0x016c:
        r9 = 48;
        r0 = r16;
        r0.append(r9);
    L_0x0173:
        r0 = r16;
        r0.append(r13);
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x0184:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r13 == r9) goto L_0x0189;
    L_0x0188:
        r8 = 0;
    L_0x0189:
        switch(r13) {
            case 100: goto L_0x018e;
            case 101: goto L_0x01ca;
            case 102: goto L_0x019c;
            case 103: goto L_0x018c;
            case 104: goto L_0x018c;
            case 105: goto L_0x018c;
            case 106: goto L_0x01d8;
            default: goto L_0x018c;
        };
    L_0x018c:
        goto L_0x008c;
    L_0x018e:
        r7 = 100;
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x019c:
        if (r2 == 0) goto L_0x008c;
    L_0x019e:
        r9 = r16.length();
        if (r9 != 0) goto L_0x01b7;
    L_0x01a4:
        r9 = "]C1";
        r0 = r16;
        r0.append(r9);
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x01b7:
        r9 = 29;
        r0 = r16;
        r0.append(r9);
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x01ca:
        r7 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x01d8:
        r6 = 1;
        r21 = r8;
        r8 = r6;
        r6 = r21;
        r22 = r5;
        r5 = r7;
        r7 = r22;
        goto L_0x0096;
    L_0x01e5:
        r5 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x009e;
    L_0x01e9:
        r0 = r25;
        r2 = r0.d(r11);
        r6 = r25.a();
        r10 = r2 - r9;
        r10 = r10 / 2;
        r10 = r10 + r2;
        r6 = java.lang.Math.min(r6, r10);
        r10 = 0;
        r0 = r25;
        r6 = r0.a(r2, r6, r10);
        if (r6 != 0) goto L_0x020a;
    L_0x0205:
        r2 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r2;
    L_0x020a:
        r3 = r3 * r5;
        r3 = r4 - r3;
        r3 = r3 % 103;
        if (r3 == r5) goto L_0x0216;
    L_0x0211:
        r2 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r2;
    L_0x0216:
        r3 = r16.length();
        if (r3 != 0) goto L_0x0221;
    L_0x021c:
        r2 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r2;
    L_0x0221:
        if (r3 <= 0) goto L_0x0230;
    L_0x0223:
        if (r8 == 0) goto L_0x0230;
    L_0x0225:
        r4 = 99;
        if (r7 != r4) goto L_0x025e;
    L_0x0229:
        r4 = r3 + -2;
        r0 = r16;
        r0.delete(r4, r3);
    L_0x0230:
        r3 = 1;
        r3 = r15[r3];
        r4 = 0;
        r4 = r15[r4];
        r3 = r3 + r4;
        r3 = (float) r3;
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = r3 / r4;
        r2 = r2 + r9;
        r2 = (float) r2;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = r2 / r3;
        r6 = r17.size();
        r7 = new byte[r6];
        r2 = 0;
        r3 = r2;
    L_0x024a:
        if (r3 >= r6) goto L_0x0266;
    L_0x024c:
        r0 = r17;
        r2 = r0.get(r3);
        r2 = (java.lang.Byte) r2;
        r2 = r2.byteValue();
        r7[r3] = r2;
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x024a;
    L_0x025e:
        r4 = r3 + -1;
        r0 = r16;
        r0.delete(r4, r3);
        goto L_0x0230;
    L_0x0266:
        r2 = new com.google.zxing.g;
        r3 = r16.toString();
        r6 = 2;
        r6 = new com.google.zxing.h[r6];
        r8 = 0;
        r9 = new com.google.zxing.h;
        r0 = r24;
        r10 = (float) r0;
        r9.<init>(r4, r10);
        r6[r8] = r9;
        r4 = 1;
        r8 = new com.google.zxing.h;
        r0 = r24;
        r9 = (float) r0;
        r8.<init>(r5, r9);
        r6[r4] = r8;
        r4 = com.google.zxing.BarcodeFormat.CODE_128;
        r2.<init>(r3, r7, r6, r4);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.c.b.a(int, com.google.zxing.common.a, java.util.Map):com.google.zxing.g");
    }
}
