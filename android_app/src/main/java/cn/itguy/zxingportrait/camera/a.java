package cn.itguy.zxingportrait.camera;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.AsyncTask;
import android.util.Log;
import cn.itguy.zxingportrait.a.a.b;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: AutoFocusManager */
final class a implements AutoFocusCallback {
    private static final String a = a.class.getSimpleName();
    private static final Collection<String> b = new ArrayList(2);
    private boolean c;
    private final boolean d;
    private final Camera e;
    private a f;
    private final cn.itguy.zxingportrait.a.a.a g = ((cn.itguy.zxingportrait.a.a.a) new b().a());

    /* compiled from: AutoFocusManager */
    private final class a extends AsyncTask<Object, Object, Object> {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            synchronized (this.a) {
                if (this.a.c) {
                    this.a.a();
                }
            }
            return null;
        }
    }

    static {
        b.add("auto");
        b.add("macro");
    }

    a(Context context, Camera camera) {
        this.e = camera;
        String focusMode = camera.getParameters().getFocusMode();
        this.d = true;
        Log.i(a, "Current focus mode '" + focusMode + "'; use auto focus? " + this.d);
        a();
    }

    public synchronized void onAutoFocus(boolean z, Camera camera) {
        if (this.c) {
            this.f = new a();
            this.g.a(this.f, new Object[0]);
        }
    }

    synchronized void a() {
        if (this.d) {
            this.c = true;
            try {
                this.e.autoFocus(this);
            } catch (Throwable e) {
                Log.w(a, "Unexpected exception while focusing", e);
            }
        }
    }

    synchronized void b() {
        if (this.d) {
            try {
                this.e.cancelAutoFocus();
            } catch (Throwable e) {
                Log.w(a, "Unexpected exception while cancelling focusing", e);
            }
        }
        if (this.f != null) {
            this.f.cancel(true);
            this.f = null;
        }
        this.c = false;
    }
}
