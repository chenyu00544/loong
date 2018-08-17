package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.h;
import java.util.List;

public abstract class Overlay {
    protected a listener;
    String p = (System.currentTimeMillis() + "_" + hashCode());
    h q;
    int r;
    boolean s;
    Bundle t;

    interface a {
        void a(Overlay overlay);

        void b(Overlay overlay);
    }

    protected Overlay() {
    }

    static void a(int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        int i2 = i >>> 24;
        int i3 = (i >> 8) & 255;
        int i4 = i & 255;
        bundle2.putFloat("red", ((float) ((i >> 16) & 255)) / 255.0f);
        bundle2.putFloat("green", ((float) i3) / 255.0f);
        bundle2.putFloat("blue", ((float) i4) / 255.0f);
        bundle2.putFloat("alpha", ((float) i2) / 255.0f);
        bundle.putBundle("color", bundle2);
    }

    static void a(List<LatLng> list, Bundle bundle) {
        int size = list.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            GeoPoint ll2mc = CoordUtil.ll2mc((LatLng) list.get(i));
            dArr[i] = ll2mc.getLongitudeE6();
            dArr2[i] = ll2mc.getLatitudeE6();
        }
        bundle.putDoubleArray("x_array", dArr);
        bundle.putDoubleArray("y_array", dArr2);
    }

    Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.p);
        bundle.putInt("type", this.q.ordinal());
        return bundle;
    }

    Bundle a(Bundle bundle) {
        bundle.putString("id", this.p);
        bundle.putInt("type", this.q.ordinal());
        bundle.putInt("visibility", this.s ? 1 : 0);
        bundle.putInt("z_index", this.r);
        return bundle;
    }

    public Bundle getExtraInfo() {
        return this.t;
    }

    public int getZIndex() {
        return this.r;
    }

    public boolean isVisible() {
        return this.s;
    }

    public void remove() {
        this.listener.a(this);
    }

    public void setExtraInfo(Bundle bundle) {
        this.t = bundle;
    }

    public void setVisible(boolean z) {
        this.s = z;
        this.listener.b(this);
    }

    public void setZIndex(int i) {
        this.r = i;
        this.listener.b(this);
    }
}
