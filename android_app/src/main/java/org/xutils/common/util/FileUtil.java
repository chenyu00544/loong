package org.xutils.common.util;

import android.os.Environment;
import android.os.StatFs;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.xutils.x;

public class FileUtil {
    private FileUtil() {
    }

    public static File getCacheDir(String str) {
        File file;
        if (existsSdcard().booleanValue()) {
            File externalCacheDir = x.app().getExternalCacheDir();
            if (externalCacheDir == null) {
                file = new File(Environment.getExternalStorageDirectory(), "Android/data/" + x.app().getPackageName() + "/cache/" + str);
            } else {
                file = new File(externalCacheDir, str);
            }
        } else {
            file = new File(x.app().getCacheDir(), str);
        }
        return (file.exists() || file.mkdirs()) ? file : null;
    }

    public static boolean isDiskAvailable() {
        return getDiskAvailableSize() > 10485760;
    }

    public static long getDiskAvailableSize() {
        if (!existsSdcard().booleanValue()) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static Boolean existsSdcard() {
        return Boolean.valueOf(Environment.getExternalStorageState().equals("mounted"));
    }

    public static long getFileOrDirSize(File file) {
        long j = 0;
        if (!file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        int i = 0;
        while (i < listFiles.length) {
            long fileOrDirSize = getFileOrDirSize(listFiles[i]) + j;
            i++;
            j = fileOrDirSize;
        }
        return j;
    }

    public static boolean copy(String str, String str2) {
        Closeable fileInputStream;
        Closeable fileOutputStream;
        Throwable th;
        Throwable th2;
        Closeable closeable = null;
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(str2);
        IOUtil.deleteFileOrDir(file2);
        File parentFile = file2.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            return false;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th3) {
                th2 = th3;
                IOUtil.closeQuietly(fileInputStream);
                IOUtil.closeQuietly(closeable);
                throw th2;
            }
            try {
                IOUtil.copy(fileInputStream, fileOutputStream);
                IOUtil.closeQuietly(fileInputStream);
                IOUtil.closeQuietly(fileOutputStream);
                return true;
            } catch (Throwable th4) {
                th2 = th4;
                closeable = fileOutputStream;
                IOUtil.closeQuietly(fileInputStream);
                IOUtil.closeQuietly(closeable);
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            fileInputStream = null;
            IOUtil.closeQuietly(fileInputStream);
            IOUtil.closeQuietly(closeable);
            throw th2;
        }
    }
}
