package com.baidu.mapapi.map;

import android.graphics.Point;
import android.graphics.PointF;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.D;
import com.baidu.platform.comapi.map.e;

public final class Projection {
    private e a;

    Projection(e eVar) {
        this.a = eVar;
    }

    public LatLng fromScreenLocation(Point point) {
        return (point == null || this.a == null) ? null : CoordUtil.mc2ll(this.a.b(point.x, point.y));
    }

    public float metersToEquatorPixels(float f) {
        return f <= 0.0f ? 0.0f : (float) (((double) f) / this.a.I());
    }

    public PointF toOpenGLLocation(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        D d = mapStatus.a;
        return new PointF((float) ((ll2mc.getLongitudeE6() - d.d) / d.n), (float) ((ll2mc.getLatitudeE6() - d.e) / d.n));
    }

    public Point toScreenLocation(LatLng latLng) {
        if (latLng == null || this.a == null) {
            return null;
        }
        return this.a.a(CoordUtil.ll2mc(latLng));
    }
}
