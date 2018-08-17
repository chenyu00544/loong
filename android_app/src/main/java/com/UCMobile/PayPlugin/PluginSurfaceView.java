package com.UCMobile.PayPlugin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.unionpay.mobile.android.j.a;

public class PluginSurfaceView extends SurfaceView {
    SurfaceHolder a;
    Paint b;
    Bitmap c;
    int[] d;
    private final int e;
    private boolean f;
    private Object g;

    static {
        System.loadLibrary("ucpayplugin");
    }

    public PluginSurfaceView(Context context, int i, int i2, int i3) {
        super(context);
        this.f = true;
        this.g = new Object();
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.a = getHolder();
        this.a.setFormat(1);
        this.b = new Paint();
        this.e = i;
        this.c = new BitmapDrawable(a.class.getClassLoader().getResourceAsStream("res/drawable/mobilepayplugin.bin")).getBitmap().copy(Config.ARGB_8888, false);
        getHolder().setFormat(-3);
        getHolder().setFormat(1);
        getHolder().addCallback(new a(this));
        getHolder().setSizeFromLayout();
        setWillNotDraw(false);
    }

    private native void nativeSurfaceChanged(int i, int i2, int i3, int i4);

    private native void nativeSurfaceCreated(int i);

    private native void nativeSurfaceDestroyed(int i);

    public int getIconHeight() {
        return this.c != null ? this.c.getHeight() : 0;
    }

    public int[] getIconPixels() {
        int i = 0;
        if (this.d != null) {
            return this.d;
        }
        int width = this.c.getWidth();
        int height = this.c.getHeight();
        int rowBytes = this.c.getRowBytes() * height;
        if (this.c != null) {
            this.d = new int[rowBytes];
            this.c.getPixels(this.d, 0, width, 0, 0, width, height);
        }
        while (i < rowBytes) {
            this.d[i] = ((this.d[i] >> 16) & 255) | (((this.d[i] << 16) & 16711680) | (this.d[i] & -16711936));
            i++;
        }
        return this.d;
    }

    public int getIconRowBytes() {
        return this.c != null ? this.c.getRowBytes() : 0;
    }

    public int getIconWidth() {
        return this.c != null ? this.c.getWidth() : 0;
    }

    public void invalidateNPP() {
        synchronized (this.g) {
            this.f = false;
        }
    }
}
