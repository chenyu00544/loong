package com.umeng.socialize.bean;

import android.location.Location;
import java.io.Serializable;

public class UMLocation implements Serializable {
    private static final long a = 1;
    private double b;
    private double c;

    public UMLocation(double d, double d2) {
        this.b = d;
        this.c = d2;
    }

    public double getLatitude() {
        return this.b;
    }

    public void setLatitude(double d) {
        this.b = d;
    }

    public double getLongitude() {
        return this.c;
    }

    public void setLongitude(double d) {
        this.c = d;
    }

    public String toString() {
        return "(" + this.c + "," + this.b + ")";
    }

    public static UMLocation build(String str) {
        try {
            String[] split = str.substring(1, str.length() - 1).split(",");
            return new UMLocation(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
        } catch (Exception e) {
            return null;
        }
    }

    public static UMLocation build(Location location) {
        try {
            if (!(location.getLatitude() == 0.0d || location.getLongitude() == 0.0d)) {
                return new UMLocation(location.getLatitude(), location.getLongitude());
            }
        } catch (Exception e) {
        }
        return null;
    }
}
