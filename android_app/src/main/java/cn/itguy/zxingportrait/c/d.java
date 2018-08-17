package cn.itguy.zxingportrait.c;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import cn.itguy.zxingportrait.CaptureActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.i;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* compiled from: DecodeThread */
final class d extends Thread {
    private final CaptureActivity a;
    private final Map<DecodeHintType, Object> b = new EnumMap(DecodeHintType.class);
    private Handler c;
    private final CountDownLatch d = new CountDownLatch(1);

    d(CaptureActivity captureActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, i iVar) {
        this.a = captureActivity;
        if (map != null) {
            this.b.putAll(map);
        }
        if (collection == null || collection.isEmpty()) {
            collection = EnumSet.noneOf(BarcodeFormat.class);
            collection.addAll(b.c);
            collection.addAll(b.d);
            collection.addAll(b.b);
        }
        this.b.put(DecodeHintType.POSSIBLE_FORMATS, collection);
        if (str != null) {
            this.b.put(DecodeHintType.CHARACTER_SET, str);
        }
        this.b.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, iVar);
        Log.i("DecodeThread", "Hints: " + this.b);
    }

    Handler a() {
        try {
            this.d.await();
        } catch (InterruptedException e) {
        }
        return this.c;
    }

    public void run() {
        Looper.prepare();
        this.c = new c(this.a, this.b);
        this.d.countDown();
        Looper.loop();
    }
}
