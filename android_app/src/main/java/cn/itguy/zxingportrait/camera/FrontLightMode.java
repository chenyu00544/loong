package cn.itguy.zxingportrait.camera;

import android.content.SharedPreferences;

public enum FrontLightMode {
    ON,
    AUTO,
    OFF;

    private static FrontLightMode a(String str) {
        return str == null ? OFF : valueOf(str);
    }

    public static FrontLightMode readPref(SharedPreferences sharedPreferences) {
        return a(null);
    }
}
