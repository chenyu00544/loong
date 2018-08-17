package com.baidu.platform.comapi.map;

import android.opengl.GLSurfaceView.Renderer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MapRenderer implements Renderer {
    private static final String d = MapRenderer.class.getSimpleName();
    public int a;
    public int b;
    public int c;
    private long e;
    private a f;
    private final i g;

    public interface a {
        void e();
    }

    public MapRenderer(i iVar, a aVar) {
        this.f = aVar;
        this.g = iVar;
    }

    private void a(GL10 gl10) {
        gl10.glClear(16640);
        gl10.glClearColor(0.85f, 0.8f, 0.8f, 0.0f);
    }

    private boolean a() {
        return this.e != 0;
    }

    public static native void nativeInit(long j);

    public static native int nativeRender(long j);

    public static native void nativeResize(long j, int i, int i2);

    public void a(long j) {
        this.e = j;
    }

    public void onDrawFrame(GL10 gl10) {
        if (a()) {
            if (this.c <= 1) {
                nativeResize(this.e, this.a, this.b);
                this.c++;
            }
            this.f.e();
            int nativeRender = nativeRender(this.e);
            for (k kVar : this.g.a().f) {
                D H = this.g.a().H();
                gl10.glPushMatrix();
                gl10.glRotatef((float) H.c, 1.0f, 0.0f, 0.0f);
                gl10.glRotatef((float) H.b, 0.0f, 0.0f, 1.0f);
                kVar.a(gl10, H);
                gl10.glPopMatrix();
                gl10.glColor4f(0.96f, 0.95f, 0.94f, 1.0f);
            }
            i iVar = this.g;
            if (nativeRender == 1) {
                iVar.requestRender();
                return;
            } else if (this.g.a().c()) {
                if (iVar.getRenderMode() != 1) {
                    iVar.setRenderMode(1);
                    return;
                }
                return;
            } else if (iVar.getRenderMode() != 0) {
                iVar.setRenderMode(0);
                return;
            } else {
                return;
            }
        }
        a(gl10);
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        if (this.e != 0) {
            nativeResize(this.e, i, i2);
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        nativeInit(this.e);
        if (a()) {
            this.f.e();
        }
    }
}
