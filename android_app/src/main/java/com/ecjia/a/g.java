package com.ecjia.a;

import android.text.TextUtils;
import java.io.File;

/* compiled from: ECJiaDataCleanManager */
public class g {
    public static void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    for (File absolutePath : listFiles) {
                        a(absolutePath.getAbsolutePath(), true);
                    }
                }
                if (!z) {
                    return;
                }
                if (!file.isDirectory()) {
                    file.delete();
                } else if (file.listFiles().length == 0) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(String str, String str2) {
        File file = new File(str);
        if (file != null && file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                q.b("=====delete=====" + file2.getName() + "-----" + str2);
                if (file2.getName().equals(str2)) {
                    file2.delete();
                }
            }
        }
    }
}
