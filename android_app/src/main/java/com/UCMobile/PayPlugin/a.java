package com.UCMobile.PayPlugin;

import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

final class a implements Callback {
    final /* synthetic */ PluginSurfaceView a;

    a(PluginSurfaceView pluginSurfaceView) {
        this.a = pluginSurfaceView;
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        synchronized (this.a.g) {
            if (this.a.f) {
                this.a.nativeSurfaceChanged(this.a.e, i, i2, i3);
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        synchronized (this.a.g) {
            if (this.a.f) {
                this.a.nativeSurfaceCreated(this.a.e);
            }
        }
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        synchronized (this.a.g) {
            if (this.a.f) {
                this.a.nativeSurfaceDestroyed(this.a.e);
            }
        }
    }
}
