package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.D;
import com.baidu.platform.comapi.map.k;
import javax.microedition.khronos.opengles.GL10;

class u implements k {
    final /* synthetic */ WearMapView a;

    u(WearMapView wearMapView) {
        this.a = wearMapView;
    }

    public void a() {
        if (this.a.d != null && this.a.d.a() != null) {
            float f = this.a.d.a().D().a;
            if (this.a.x != f) {
                CharSequence format;
                int intValue = ((Integer) WearMapView.u.get((int) f)).intValue();
                int i = (int) (((double) intValue) / this.a.d.a().D().m);
                this.a.p.setPadding(i / 2, 0, i / 2, 0);
                if (intValue >= 1000) {
                    format = String.format(" %d公里 ", new Object[]{Integer.valueOf(intValue / 1000)});
                } else {
                    format = String.format(" %d米 ", new Object[]{Integer.valueOf(intValue)});
                }
                this.a.n.setText(format);
                this.a.o.setText(format);
                this.a.x = f;
            }
            this.a.requestLayout();
        }
    }

    public void a(Bitmap bitmap) {
    }

    public void a(MotionEvent motionEvent) {
    }

    public void a(GeoPoint geoPoint) {
    }

    public void a(D d) {
    }

    public void a(String str) {
    }

    public void a(GL10 gl10, D d) {
    }

    public void a(boolean z) {
    }

    public void b() {
    }

    public void b(GeoPoint geoPoint) {
    }

    public void b(D d) {
    }

    public boolean b(String str) {
        return false;
    }

    public void c() {
    }

    public void c(GeoPoint geoPoint) {
    }

    public void c(D d) {
    }

    public void d() {
    }

    public void d(GeoPoint geoPoint) {
    }

    public void e() {
    }

    public void e(GeoPoint geoPoint) {
    }

    public void f() {
    }
}
