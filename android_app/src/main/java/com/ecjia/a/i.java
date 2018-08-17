package com.ecjia.a;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/* compiled from: ECJiaFileSizeUtil */
public class i {
    public static String a(String str) {
        File file = new File(str);
        long j = 0;
        try {
            if (file.isDirectory()) {
                j = b(file);
            } else {
                j = (long) a(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return a(j);
    }

    public static int a(File file) throws Exception {
        if (file.exists()) {
            return new FileInputStream(file).available();
        }
        file.createNewFile();
        Log.e("获取文件大小", "文件不存在!");
        return 0;
    }

    private static long b(File file) throws Exception {
        long j = 0;
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                j += b(listFiles[i]);
            } else {
                j += (long) a(listFiles[i]);
            }
        }
        return j;
    }

    public static String a(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String str = "";
        str = "0B";
        if (j == 0) {
            return str;
        }
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return decimalFormat.format((double) j) + "B";
        }
        if (j < 1048576) {
            return decimalFormat.format(((double) j) / 1024.0d) + "KB";
        }
        if (j < 1073741824) {
            return decimalFormat.format(((double) j) / 1048576.0d) + "MB";
        }
        return decimalFormat.format(((double) j) / 1.073741824E9d) + "GB";
    }

    public static double a(long j, int i) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        switch (i) {
            case 1:
                return Double.valueOf(decimalFormat.format((double) j)).doubleValue();
            case 2:
                return Double.valueOf(decimalFormat.format(((double) j) / 1024.0d)).doubleValue();
            case 3:
                return Double.valueOf(decimalFormat.format(((double) j) / 1048576.0d)).doubleValue();
            case 4:
                return Double.valueOf(decimalFormat.format(((double) j) / 1.073741824E9d)).doubleValue();
            default:
                return 0.0d;
        }
    }
}
