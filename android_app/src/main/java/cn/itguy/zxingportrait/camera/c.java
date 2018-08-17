package cn.itguy.zxingportrait.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import cn.itguy.zxingportrait.camera.a.b;
import com.google.zxing.e;
import java.io.IOException;

/* compiled from: CameraManager */
public final class c {
    private static final String a = c.class.getSimpleName();
    private final Context b;
    private final b c;
    private Camera d;
    private a e;
    private Rect f;
    private Rect g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private final d l = new d(this.c);

    public c(Context context) {
        this.b = context;
        this.c = new b(context);
    }

    public synchronized void a(SurfaceHolder surfaceHolder) throws IOException {
        String str;
        Camera camera = this.d;
        if (camera == null) {
            camera = ((b) new cn.itguy.zxingportrait.camera.a.c().a()).a();
            if (camera == null) {
                throw new IOException();
            }
            this.d = camera;
        }
        Camera camera2 = camera;
        camera2.setPreviewDisplay(surfaceHolder);
        if (!this.h) {
            this.h = true;
            this.c.a(camera2);
            if (this.j > 0 && this.k > 0) {
                a(this.j, this.k);
                this.j = 0;
                this.k = 0;
            }
        }
        Parameters parameters = camera2.getParameters();
        if (parameters == null) {
            str = null;
        } else {
            str = parameters.flatten();
        }
        try {
            this.c.a(camera2, false);
        } catch (RuntimeException e) {
            Log.w(a, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(a, "Resetting to saved camera params: " + str);
            if (str != null) {
                Parameters parameters2 = camera2.getParameters();
                parameters2.unflatten(str);
                try {
                    camera2.setParameters(parameters2);
                    this.c.a(camera2, true);
                } catch (RuntimeException e2) {
                    Log.w(a, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
    }

    public synchronized boolean a() {
        return this.d != null;
    }

    public synchronized void b() {
        if (this.d != null) {
            this.d.release();
            this.d = null;
            this.f = null;
            this.g = null;
        }
    }

    public synchronized void c() {
        Camera camera = this.d;
        if (!(camera == null || this.i)) {
            camera.startPreview();
            this.i = true;
            this.e = new a(this.b, this.d);
        }
    }

    public synchronized void d() {
        if (this.e != null) {
            this.e.b();
            this.e = null;
        }
        if (this.d != null && this.i) {
            this.d.stopPreview();
            this.l.a(null, 0);
            this.i = false;
        }
    }

    public synchronized void a(boolean z) {
        if (!(z == this.c.b(this.d) || this.d == null)) {
            if (this.e != null) {
                this.e.b();
            }
            this.c.b(this.d, z);
            if (this.e != null) {
                this.e.a();
            }
        }
    }

    public synchronized void a(Handler handler, int i) {
        Camera camera = this.d;
        if (camera != null && this.i) {
            this.l.a(handler, i);
            camera.setOneShotPreviewCallback(this.l);
        }
    }

    public synchronized Rect e() {
        Rect rect = null;
        synchronized (this) {
            if (this.f == null) {
                if (this.d != null) {
                    Point b = this.c.b();
                    if (b != null) {
                        this.b.getResources().getDisplayMetrics();
                        int i = (int) (((double) b.x) * 0.85d);
                        int i2 = (int) (((double) i) * 0.9d);
                        int i3 = (b.x - i) / 2;
                        int i4 = (b.y - i2) / 4;
                        this.f = new Rect(i3, i4, i + i3, i2 + i4);
                    }
                }
            }
            Log.d(a, "Calculated framing rect: " + this.f);
            rect = this.f;
        }
        return rect;
    }

    public synchronized Rect f() {
        Rect rect = null;
        synchronized (this) {
            if (this.g == null) {
                Rect e = e();
                if (e != null) {
                    Rect rect2 = new Rect(e);
                    Point a = this.c.a();
                    Point b = this.c.b();
                    if (!(a == null || b == null)) {
                        rect2.left = (rect2.left * a.y) / b.x;
                        rect2.right = (rect2.right * a.y) / b.x;
                        rect2.top = (rect2.top * a.x) / b.y;
                        rect2.bottom = (rect2.bottom * a.x) / b.y;
                        this.g = rect2;
                    }
                }
            }
            rect = this.g;
        }
        return rect;
    }

    public synchronized void a(int i, int i2) {
        if (this.h) {
            Point b = this.c.b();
            if (i > b.x) {
                i = b.x;
            }
            if (i2 > b.y) {
                i2 = b.y;
            }
            int i3 = (b.x - i) / 2;
            int i4 = (b.y - i2) / 2;
            this.f = new Rect(i3, i4, i3 + i, i4 + i2);
            Log.d(a, "Calculated manual framing rect: " + this.f);
            this.g = null;
        } else {
            this.j = i;
            this.k = i2;
        }
    }

    public e a(byte[] bArr, int i, int i2) {
        Rect f = f();
        if (f == null) {
            return null;
        }
        return new e(bArr, i, i2, f.left, f.top, f.width(), f.height(), false);
    }
}
