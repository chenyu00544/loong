package com.ecjia.consts;

import android.content.Context;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: ECJiaAppConst */
public class b {
    public static final Boolean a = Boolean.valueOf(true);
    public static boolean b = false;
    public static ArrayList<HashMap<String, String>> c;
    public static ArrayList<HashMap<String, Boolean>> d;
    public static ArrayList<HashMap<String, Boolean>> e;
    public static ArrayList<HashMap<String, Boolean>> f;
    public static double[] g = new double[]{0.0d, 0.0d};
    public static String[] h = new String[]{"", "", ""};
    private static a i;

    /* compiled from: ECJiaAppConst */
    public interface a {
    }

    public static void a(a aVar) {
        i = aVar;
    }

    public static Resources a(Context context) {
        return context.getResources();
    }
}
