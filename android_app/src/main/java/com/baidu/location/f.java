package com.baidu.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.baidu.location.a.a;
import com.baidu.location.b.e;
import com.baidu.location.b.k;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.RandomAccessFile;

public class f extends Service implements com.baidu.location.b.f {
    public static boolean isServing = false;
    private static final String l3 = "app.jar";
    public static Context mC = null;
    public static String replaceFileName = "repll.jar";
    LLSInterface l2 = null;
    LLSInterface l4 = null;
    LLSInterface l5 = null;

    private boolean do(File file) {
        boolean z = false;
        try {
            File file2 = new File(e.int + "/grtcf.dat");
            if (file2.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                randomAccessFile.seek(200);
                if (randomAccessFile.readBoolean() && randomAccessFile.readBoolean()) {
                    int readInt = randomAccessFile.readInt();
                    if (readInt != 0) {
                        byte[] bArr = new byte[readInt];
                        randomAccessFile.read(bArr, 0, readInt);
                        String str = new String(bArr);
                        String str2 = k.if(file);
                        if (!(str == null || str2 == null || !str2.equals(str))) {
                            z = true;
                        }
                    }
                }
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
        return z;
    }

    public static float getFrameVersion() {
        return com.baidu.location.b.f.bi;
    }

    public static String getJarFileName() {
        return l3;
    }

    public static Context getServiceContext() {
        return mC;
    }

    public IBinder onBind(Intent intent) {
        return this.l5.onBind(intent);
    }

    public void onCreate() {
        mC = getApplicationContext();
        System.currentTimeMillis();
        this.l4 = new a();
        try {
            File file = new File(k.ai() + File.separator + replaceFileName);
            File file2 = new File(k.ai() + File.separator + l3);
            if (file.exists()) {
                if (file2.exists()) {
                    file2.delete();
                }
                file.renameTo(file2);
            }
            if (file2.exists()) {
                this.l2 = (LLSInterface) new DexClassLoader(k.ai() + File.separator + l3, k.ai(), null, getClassLoader()).loadClass("com.baidu.serverLoc.LocationService").newInstance();
            }
        } catch (Exception e) {
            this.l2 = null;
        }
        if (this.l2 == null || this.l2.getVersion() < this.l4.getVersion() || !do(new File(k.ai() + File.separator + l3))) {
            this.l5 = this.l4;
            this.l2 = null;
        } else {
            this.l5 = this.l2;
            this.l4 = null;
        }
        isServing = true;
        this.l5.onCreate(this);
    }

    public void onDestroy() {
        isServing = false;
        this.l5.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return this.l5.onStartCommand(intent, i, i2);
    }

    public boolean onUnbind(Intent intent) {
        return this.l5.onUnBind(intent);
    }
}
