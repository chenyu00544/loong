package com.ecjia.a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.ecjia.consts.b;
import java.io.File;

/* compiled from: ECJiaLocationUtil */
public class s {
    public static void a(Context context, String str, String str2, String str3, String str4) {
        String str5;
        if (TextUtils.isEmpty(str2)) {
            str2 = "0";
        }
        String str6 = "";
        if (Integer.valueOf(str2).intValue() > m_AppUI.MSG_APP_DATA_OK) {
            str5 = "driving";
        } else {
            str5 = "walking";
        }
        try {
            Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:" + b.g[1] + "," + b.g[0] + "|name:" + b.h[1] + "&destination=" + str + "|latlng:" + str3 + "," + str4 + "&mode=" + str5 + "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            if (a("com.baidu.BaiduMap")) {
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "您尚未安装百度地图app或app版本过低，请安装最新版的百度地图", 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "您尚未安装百度地图app或app版本过低，请安装最新版的百度地图", 1).show();
        }
    }

    private static boolean a(String str) {
        return new File("/data/data/" + str).exists();
    }
}
