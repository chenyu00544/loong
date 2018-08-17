package com.unionpay.utils;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class g {
    private static boolean a;
    private static int b = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;

    static {
        a = false;
        a = false;
    }

    private static int a(int i, String str, String str2) {
        int i2 = 0;
        if (!(str == null || str2 == null)) {
            switch (i) {
                case 2:
                    i2 = Log.v(str, str2);
                    break;
                case 3:
                    i2 = Log.d(str, str2);
                    break;
                case 4:
                    i2 = Log.i(str, str2);
                    break;
                case 5:
                    i2 = Log.w(str, str2);
                    break;
                case 6:
                    i2 = Log.e(str, str2);
                    break;
            }
            if (a) {
                String str3 = "[ ERROR ] " + str + ":" + str2;
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "upmp_log.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    str3 = str3 + "\n";
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(str3.getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return i2;
    }

    public static int a(String str, String str2) {
        if (b <= 3) {
            a(3, str, str2);
        }
        return 0;
    }

    public static int b(String str, String str2) {
        return b <= 6 ? a(6, str, str2) : 0;
    }
}
