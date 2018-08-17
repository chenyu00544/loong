package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.taobao.accs.ErrorCode;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class i extends GLSurfaceView implements OnDoubleTapListener, OnGestureListener, com.baidu.platform.comapi.map.MapRenderer.a {
    private static final String a = i.class.getSimpleName();
    private Handler b;
    private MapRenderer c;
    private int d;
    private int e;
    private GestureDetector f;
    private e g;

    static class a {
        float a;
        float b;
        float c;
        float d;
        boolean e;
        float f;
        float g;
        double h;

        a() {
        }
    }

    public i(Context context, B b, String str) {
        super(context);
        if (context == null) {
            throw new RuntimeException("when you create an mapview, the context can not be null");
        }
        this.f = new GestureDetector(context, this);
        EnvironmentUtilities.initAppDirectory(context);
        if (this.g == null) {
            this.g = new e(context, str);
        }
        this.g.a();
        f();
        this.g.b();
        this.g.a(b);
        g();
        this.g.a(this.b);
        this.g.e();
        setBackgroundColor(0);
    }

    public static void a(boolean z) {
        e.j(z);
    }

    private static boolean a(int i, int i2, int i3, int i4, int i5, int i6) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        return egl10.eglChooseConfig(eglGetDisplay, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, new EGLConfig[100], 100, iArr) && iArr[0] > 0;
    }

    private void f() {
        try {
            if (a(5, 6, 5, 0, 24, 0)) {
                setEGLConfigChooser(5, 6, 5, 0, 24, 0);
            } else {
                setEGLConfigChooser(true);
            }
        } catch (IllegalArgumentException e) {
            setEGLConfigChooser(true);
        }
        this.c = new MapRenderer(this, this);
        this.c.a(this.g.h);
        setRenderer(this.c);
        setRenderMode(1);
    }

    private void g() {
        this.b = new j(this);
    }

    public e a() {
        return this.g;
    }

    public void a(int i) {
        if (this.g != null) {
            Message message = new Message();
            message.what = 50;
            message.obj = Long.valueOf(this.g.h);
            boolean p = this.g.p();
            if (i == 3) {
                message.arg1 = 0;
            } else if (p) {
                message.arg1 = 1;
            }
            this.b.sendMessage(message);
        }
    }

    public void a(String str, Rect rect) {
        if (this.g != null && this.g.g != null) {
            if (rect != null) {
                int i = rect.left;
                int i2 = this.e < rect.bottom ? 0 : this.e - rect.bottom;
                int width = rect.width();
                int height = rect.height();
                if (i >= 0 && i2 >= 0 && width > 0 && height > 0) {
                    if (width > this.d) {
                        width = Math.abs(rect.width()) - (rect.right - this.d);
                    }
                    if (height > this.e) {
                        height = Math.abs(rect.height()) - (rect.bottom - this.e);
                    }
                    if (i > SysOSUtil.getScreenSizeX() || i2 > SysOSUtil.getScreenSizeY()) {
                        this.g.g.a(str, null);
                        requestRender();
                        return;
                    }
                    this.d = width;
                    this.e = height;
                    Bundle bundle = new Bundle();
                    bundle.putInt("x", i);
                    bundle.putInt("y", i2);
                    bundle.putInt("width", width);
                    bundle.putInt("height", height);
                    this.g.g.a(str, bundle);
                    requestRender();
                    return;
                }
                return;
            }
            this.g.g.a(str, null);
            requestRender();
        }
    }

    public void b() {
        if (this.g != null) {
            for (k f : this.g.f) {
                f.f();
            }
            this.g.b(this.b);
            this.g.M();
            this.g = null;
        }
    }

    public void c() {
        if (this.g != null) {
            this.g.t();
        }
    }

    public void d() {
        if (this.g != null) {
            this.g.u();
        }
    }

    public void e() {
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.g == null || this.g.g == null || !this.g.i) {
            return true;
        }
        GeoPoint b = this.g.b((int) motionEvent.getX(), (int) motionEvent.getY());
        if (b == null) {
            return false;
        }
        for (k b2 : this.g.f) {
            b2.b(b);
        }
        if (!this.g.e) {
            return false;
        }
        D D = this.g.D();
        D.a += 1.0f;
        D.d = b.getLongitudeE6();
        D.e = b.getLatitudeE6();
        this.g.a(D, (int) ErrorCode.APP_NOT_BIND);
        e eVar = this.g;
        e.k = System.currentTimeMillis();
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (this.g == null || this.g.g == null || !this.g.i) {
            return true;
        }
        if (!this.g.d) {
            return false;
        }
        float sqrt = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        if (sqrt <= 500.0f) {
            return false;
        }
        this.g.z();
        this.g.a(34, (int) (sqrt * 0.6f), (((int) motionEvent2.getY()) << 16) | ((int) motionEvent2.getX()));
        this.g.L();
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.g != null && this.g.g != null && this.g.i) {
            String a = this.g.g.a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.g.j);
            if (a == null || a.equals("")) {
                for (k c : this.g.f) {
                    c.c(this.g.b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
                return;
            }
            for (k c2 : this.g.f) {
                if (c2.b(a)) {
                    this.g.n = true;
                } else {
                    c2.c(this.g.b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (this.g != null && this.g.g != null) {
            this.g.g.e();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.g != null && this.g.g != null) {
            for (k d : this.g.f) {
                d.d();
            }
            this.g.g.i();
            this.g.g.f();
            this.g.g.p();
            setRenderMode(1);
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONException e;
        if (!(this.g == null || this.g.g == null || !this.g.i)) {
            String a = this.g.g.a(-1, (int) motionEvent.getX(), (int) motionEvent.getY(), this.g.j);
            if (a == null || a.equals("")) {
                for (k a2 : this.g.f) {
                    a2.a(this.g.b((int) motionEvent.getX(), (int) motionEvent.getY()));
                }
            } else {
                try {
                    jSONObject = new JSONObject(a);
                    try {
                        jSONObject.put("px", (int) motionEvent.getX());
                        jSONObject.put("py", (int) motionEvent.getY());
                        jSONObject2 = jSONObject;
                    } catch (JSONException e2) {
                        e = e2;
                        e.printStackTrace();
                        jSONObject2 = jSONObject;
                        for (k a22 : this.g.f) {
                            if (jSONObject2 == null) {
                                a22.a(jSONObject2.toString());
                            }
                        }
                        return true;
                    }
                } catch (JSONException e3) {
                    JSONException jSONException = e3;
                    jSONObject = null;
                    e = jSONException;
                    e.printStackTrace();
                    jSONObject2 = jSONObject;
                    for (k a222 : this.g.f) {
                        if (jSONObject2 == null) {
                            a222.a(jSONObject2.toString());
                        }
                    }
                    return true;
                }
                for (k a2222 : this.g.f) {
                    if (jSONObject2 == null) {
                        a2222.a(jSONObject2.toString());
                    }
                }
            }
        }
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.g == null || this.g.g == null) {
            return true;
        }
        super.onTouchEvent(motionEvent);
        for (k a : this.g.f) {
            a.a(motionEvent);
        }
        return this.f.onTouchEvent(motionEvent) ? true : this.g.a(motionEvent);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        super.surfaceChanged(surfaceHolder, i, i2, i3);
        if (this.g != null && this.g.g != null) {
            this.c.a = i2;
            this.c.b = i3;
            this.d = i2;
            this.e = i3;
            this.c.c = 0;
            D D = this.g.D();
            if (D.f == 0 || D.f == -1 || D.f == (D.j.a - D.j.b) / 2) {
                D.f = -1;
            }
            if (D.g == 0 || D.g == -1 || D.g == (D.j.d - D.j.c) / 2) {
                D.g = -1;
            }
            D.j.a = 0;
            D.j.c = 0;
            D.j.d = i3;
            D.j.b = i2;
            this.g.a(D);
            this.g.a(this.d, this.e);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
        if (surfaceHolder != null && !surfaceHolder.getSurface().isValid()) {
            surfaceDestroyed(surfaceHolder);
        }
    }
}
