package com.unionpay.c;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.tencent.tauth.AuthActivity;
import com.unionpay.c.aa.c;
import java.io.ByteArrayOutputStream;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

final class ai {
    private static volatile ai a = null;
    private static ab e = null;
    private final CRC32 b = new CRC32();
    private Handler c = null;
    private final HandlerThread d = new HandlerThread("NetWorkThread");

    static {
        a().d.start();
        a().c = new an(a().d.getLooper());
    }

    ai() {
    }

    public static ai a() {
        if (a == null) {
            synchronized (ai.class) {
                if (a == null) {
                    a = new ai();
                }
            }
        }
        return a;
    }

    private final synchronized void a(String str, String str2, String str3, String str4, byte[] bArr, Object obj, int i, boolean z) {
        byte[] bArr2;
        if (z) {
            bArr2 = bArr;
        } else {
            try {
                bArr2 = a(bArr);
            } catch (Throwable th) {
            }
        }
        this.b.reset();
        this.b.update(bArr2);
        c a = aa.a(d.c, str3, str2, i == 0 ? str + "?crc=" + Long.toHexString(this.b.getValue()) : str + "?crc=" + Long.toHexString(this.b.getValue()), str4, bArr2);
        d.f = SystemClock.elapsedRealtime();
        Object acVar = new ac();
        acVar.a.putInt("statusCode", a.a);
        acVar.a.putString("responseMsg", a.b);
        acVar.a.putString(AuthActivity.ACTION_KEY, e.d);
        acVar.b = obj;
        acVar.c = i;
        try {
            i.a().b(acVar);
        } catch (Throwable th2) {
        }
    }

    private static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Deflater deflater = new Deflater(9);
        deflater.setInput(bArr);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            try {
                deflater.finish();
                byte[] bArr2 = new byte[1024];
                while (!deflater.finished()) {
                    byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e) {
                }
                byteArrayOutputStream.close();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                y.a("Original: " + bArr.length);
                y.a("Compressed: " + toByteArray.length);
                return toByteArray;
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    static /* synthetic */ void c() {
        Object acVar = new ac();
        acVar.a = null;
        acVar.b = null;
        try {
            i.a().b(acVar);
        } catch (Throwable th) {
        }
    }
}
