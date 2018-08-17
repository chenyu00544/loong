package com.unionpay.c;

import android.content.Context;
import android.location.Location;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class al {
    public static List a(Context context) {
        List arrayList = new ArrayList();
        if (am.a && am.a(23) && context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
        }
        return arrayList;
    }

    public static String b(Context context) {
        List<Location> a = a(context);
        StringBuffer stringBuffer = new StringBuffer();
        for (Location location : a) {
            stringBuffer.append(location.getLatitude()).append(',').append(location.getLongitude()).append(',').append(location.hasAltitude() ? Double.valueOf(location.getAltitude()) : "").append(',').append(location.getTime()).append(',').append(location.hasAccuracy() ? Float.valueOf(location.getAccuracy()) : "").append(',').append(location.hasBearing() ? Float.valueOf(location.getBearing()) : "").append(',').append(location.hasSpeed() ? Float.valueOf(location.getSpeed()) : "").append(',').append(location.getProvider()).append(':');
        }
        return stringBuffer.toString();
    }

    public static JSONArray c(Context context) {
        return null;
    }
}
