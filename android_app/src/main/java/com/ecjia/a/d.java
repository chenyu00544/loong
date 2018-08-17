package com.ecjia.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/* compiled from: ECJiaCheckInternet */
public class d {
    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo state : allNetworkInfo) {
            if (state.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }
}
