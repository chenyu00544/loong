package com.baidu.mapapi.common;

import com.baidu.platform.comapi.util.f;

public class SysOSUtil {
    public static float getDensity() {
        return f.z;
    }

    public static int getDensityDpi() {
        return f.k();
    }

    public static String getDeviceID() {
        return f.m();
    }

    public static String getModuleFileName() {
        return f.l();
    }

    public static int getScreenSizeX() {
        return f.g();
    }

    public static int getScreenSizeY() {
        return f.i();
    }
}
