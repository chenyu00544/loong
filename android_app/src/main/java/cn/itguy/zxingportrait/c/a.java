package cn.itguy.zxingportrait.c;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.itguy.zxingportrait.CaptureActivity;
import cn.itguy.zxingportrait.a.e;
import cn.itguy.zxingportrait.camera.c;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.g;
import java.util.Collection;
import java.util.Map;

/* compiled from: CaptureActivityHandler */
public final class a extends Handler {
    private static final String a = a.class.getSimpleName();
    private final CaptureActivity b;
    private final d c;
    private a d = a.SUCCESS;
    private final c e;

    /* compiled from: CaptureActivityHandler */
    private enum a {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public a(CaptureActivity captureActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, c cVar) {
        this.b = captureActivity;
        this.c = new d(captureActivity, collection, map, str, new cn.itguy.zxingportrait.view.a(captureActivity.a()));
        this.c.start();
        this.e = cVar;
        cVar.c();
        b();
    }

    public void handleMessage(Message message) {
        String str = null;
        int i = message.what;
        if (e.restart_preview == i) {
            Log.d(a, "Got restart preview message");
            b();
        } else if (e.decode_succeeded == i) {
            float f;
            Bitmap bitmap;
            Log.d(a, "Got decode succeeded message");
            this.d = a.SUCCESS;
            Bundle data = message.getData();
            if (data != null) {
                Bitmap copy;
                byte[] byteArray = data.getByteArray("barcode_bitmap");
                if (byteArray != null) {
                    copy = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, null).copy(Config.ARGB_8888, true);
                } else {
                    copy = null;
                }
                f = data.getFloat("barcode_scaled_factor");
                bitmap = copy;
            } else {
                bitmap = null;
                f = 1.0f;
            }
            this.b.a((g) message.obj, bitmap, f);
        } else if (e.decode_failed == i) {
            this.d = a.PREVIEW;
            this.e.a(this.c.a(), e.decode);
        } else if (e.return_scan_result == i) {
            Log.d(a, "Got return scan result message");
            this.b.setResult(-1, (Intent) message.obj);
            this.b.finish();
        } else if (e.launch_product_query == i) {
            Log.d(a, "Got product query message");
            String str2 = (String) message.obj;
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(524288);
            intent.setData(Uri.parse(str2));
            ResolveInfo resolveActivity = this.b.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity.activityInfo != null) {
                str = resolveActivity.activityInfo.packageName;
                Log.d(a, "Using browser in package " + str);
            }
            if ("com.android.browser".equals(str) || "com.android.chrome".equals(str)) {
                intent.setPackage(str);
                intent.addFlags(268435456);
                intent.putExtra("com.android.browser.application_id", str);
            }
            try {
                this.b.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.w(a, "Can't find anything to handle VIEW of URI " + str2);
            }
        }
    }

    public void a() {
        this.d = a.DONE;
        this.e.d();
        Message.obtain(this.c.a(), e.quit).sendToTarget();
        try {
            this.c.join(500);
        } catch (InterruptedException e) {
        }
        removeMessages(e.decode_succeeded);
        removeMessages(e.decode_failed);
    }

    private void b() {
        if (this.d == a.SUCCESS) {
            this.d = a.PREVIEW;
            this.e.a(this.c.a(), e.decode);
            this.b.e();
        }
    }
}
