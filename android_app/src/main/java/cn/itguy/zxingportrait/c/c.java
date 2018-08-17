package cn.itguy.zxingportrait.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import cn.itguy.zxingportrait.CaptureActivity;
import cn.itguy.zxingportrait.a.e;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ReaderException;
import com.google.zxing.b;
import com.google.zxing.common.i;
import com.google.zxing.d;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

/* compiled from: DecodeHandler */
final class c extends Handler {
    private static final String a = c.class.getSimpleName();
    private final CaptureActivity b;
    private final d c = new d();
    private boolean d = true;

    c(CaptureActivity captureActivity, Map<DecodeHintType, Object> map) {
        this.c.a((Map) map);
        this.b = captureActivity;
    }

    public void handleMessage(Message message) {
        if (this.d) {
            int i = message.what;
            if (e.decode == i) {
                a((byte[]) message.obj, message.arg1, message.arg2);
            } else if (e.quit == i) {
                this.d = false;
                Looper.myLooper().quit();
            }
        }
    }

    private void a(byte[] bArr, int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        Object obj = null;
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
            }
        }
        com.google.zxing.e a = this.b.c().a(bArr2, i2, i);
        if (a != null) {
            try {
                obj = this.c.a(new b(new i(a)));
            } catch (ReaderException e) {
            } finally {
                a = this.c;
                a.a();
            }
        }
        Handler b = this.b.b();
        if (obj != null) {
            Log.d(a, "Found barcode in " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            if (b != null) {
                Message obtain = Message.obtain(b, e.decode_succeeded, obj);
                Bundle bundle = new Bundle();
                a(a, bundle);
                obtain.setData(bundle);
                obtain.sendToTarget();
            }
        } else if (b != null) {
            Message.obtain(b, e.decode_failed).sendToTarget();
        }
    }

    private static void a(com.google.zxing.e eVar, Bundle bundle) {
        int[] f = eVar.f();
        int g = eVar.g();
        Bitmap createBitmap = Bitmap.createBitmap(f, 0, g, g, eVar.h(), Config.ARGB_8888);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
        bundle.putFloat("barcode_scaled_factor", ((float) g) / ((float) eVar.b()));
    }
}
