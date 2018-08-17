package com.baidu.mapapi.model;

import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comapi.util.b;
import com.baidu.platform.comjni.tools.a;
import java.util.List;

public class CoordUtil {
    public static Point Coordinate_encryptEx(float f, float f2, String str) {
        return b.a(f, f2, str);
    }

    public static LatLng decodeLocation(String str) {
        return b.a(str);
    }

    public static List<LatLng> decodeLocationList(String str) {
        return b.c(str);
    }

    public static List<List<LatLng>> decodeLocationList2D(String str) {
        return b.d(str);
    }

    public static LatLng decodeNodeLocation(String str) {
        return b.b(str);
    }

    public static double getDistance(Point point, Point point2) {
        return a.a(point, point2);
    }

    public static int getMCDistanceByOneLatLngAndRadius(LatLng latLng, int i) {
        return b.a(latLng, i);
    }

    public static GeoPoint ll2mc(LatLng latLng) {
        return b.a(latLng);
    }

    public static Point ll2point(LatLng latLng) {
        return b.b(latLng);
    }

    public static LatLng mc2ll(GeoPoint geoPoint) {
        return b.a(geoPoint);
    }
}
