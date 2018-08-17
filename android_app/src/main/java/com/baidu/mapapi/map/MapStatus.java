package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.D;

public final class MapStatus implements Parcelable {
    public static final Creator<MapStatus> CREATOR = new h();
    D a;
    private double b;
    public final LatLngBounds bound;
    private double c;
    public final float overlook;
    public final float rotate;
    public final LatLng target;
    public final Point targetScreen;
    public final float zoom;

    public static final class Builder {
        private float a = -2.14748365E9f;
        private LatLng b = null;
        private float c = -2.14748365E9f;
        private float d = -2.14748365E9f;
        private Point e = null;
        private LatLngBounds f = null;
        private double g = 0.0d;
        private double h = 0.0d;

        public Builder(MapStatus mapStatus) {
            this.a = mapStatus.rotate;
            this.b = mapStatus.target;
            this.c = mapStatus.overlook;
            this.d = mapStatus.zoom;
            this.e = mapStatus.targetScreen;
            this.g = mapStatus.a();
            this.h = mapStatus.b();
        }

        public MapStatus build() {
            return new MapStatus(this.a, this.b, this.c, this.d, this.e, this.f);
        }

        public Builder overlook(float f) {
            this.c = f;
            return this;
        }

        public Builder rotate(float f) {
            this.a = f;
            return this;
        }

        public Builder target(LatLng latLng) {
            this.b = latLng;
            return this;
        }

        public Builder targetScreen(Point point) {
            this.e = point;
            return this;
        }

        public Builder zoom(float f) {
            this.d = f;
            return this;
        }
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, double d, double d2, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        this.b = d;
        this.c = d2;
        this.bound = latLngBounds;
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        if (this.target != null) {
            this.b = CoordUtil.ll2mc(this.target).getLongitudeE6();
            this.c = CoordUtil.ll2mc(this.target).getLatitudeE6();
        }
        this.bound = latLngBounds;
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, D d, double d2, double d3, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        this.a = d;
        this.b = d2;
        this.c = d3;
        this.bound = latLngBounds;
    }

    protected MapStatus(Parcel parcel) {
        this.rotate = parcel.readFloat();
        this.target = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.overlook = parcel.readFloat();
        this.zoom = parcel.readFloat();
        this.targetScreen = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.bound = (LatLngBounds) parcel.readParcelable(LatLngBounds.class.getClassLoader());
        this.b = parcel.readDouble();
        this.c = parcel.readDouble();
    }

    static MapStatus a(D d) {
        if (d == null) {
            return null;
        }
        float f = (float) d.b;
        double d2 = d.e;
        double d3 = d.d;
        LatLng mc2ll = CoordUtil.mc2ll(new GeoPoint(d2, d3));
        float f2 = (float) d.c;
        float f3 = d.a;
        Point point = new Point(d.f, d.g);
        LatLng mc2ll2 = CoordUtil.mc2ll(new GeoPoint((double) d.k.e.y, (double) d.k.e.x));
        LatLng mc2ll3 = CoordUtil.mc2ll(new GeoPoint((double) d.k.f.y, (double) d.k.f.x));
        LatLng mc2ll4 = CoordUtil.mc2ll(new GeoPoint((double) d.k.h.y, (double) d.k.h.x));
        LatLng mc2ll5 = CoordUtil.mc2ll(new GeoPoint((double) d.k.g.y, (double) d.k.g.x));
        com.baidu.mapapi.model.LatLngBounds.Builder builder = new com.baidu.mapapi.model.LatLngBounds.Builder();
        builder.include(mc2ll2);
        builder.include(mc2ll3);
        builder.include(mc2ll4);
        builder.include(mc2ll5);
        return new MapStatus(f, mc2ll, f2, f3, point, d, d3, d2, builder.build());
    }

    double a() {
        return this.b;
    }

    double b() {
        return this.c;
    }

    D b(D d) {
        if (this.rotate != -2.14748365E9f) {
            d.b = (int) this.rotate;
        }
        if (this.zoom != -2.14748365E9f) {
            d.a = this.zoom;
        }
        if (this.overlook != -2.14748365E9f) {
            d.c = (int) this.overlook;
        }
        if (this.target != null) {
            CoordUtil.ll2mc(this.target);
            d.d = this.b;
            d.e = this.c;
        }
        if (this.targetScreen != null) {
            d.f = this.targetScreen.x;
            d.g = this.targetScreen.y;
        }
        return d;
    }

    D c() {
        return b(new D());
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.target != null) {
            stringBuilder.append("target lat: " + this.target.latitude + "\n");
            stringBuilder.append("target lng: " + this.target.longitude + "\n");
        }
        if (this.targetScreen != null) {
            stringBuilder.append("target screen x: " + this.targetScreen.x + "\n");
            stringBuilder.append("target screen y: " + this.targetScreen.y + "\n");
        }
        stringBuilder.append("zoom: " + this.zoom + "\n");
        stringBuilder.append("rotate: " + this.rotate + "\n");
        stringBuilder.append("overlook: " + this.overlook + "\n");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.rotate);
        parcel.writeParcelable(this.target, i);
        parcel.writeFloat(this.overlook);
        parcel.writeFloat(this.zoom);
        parcel.writeParcelable(this.targetScreen, i);
        parcel.writeParcelable(this.bound, i);
        parcel.writeDouble(this.b);
        parcel.writeDouble(this.c);
    }
}
