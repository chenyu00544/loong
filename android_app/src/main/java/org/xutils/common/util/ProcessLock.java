package org.xutils.common.util;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.xutils.x;

public final class ProcessLock implements Closeable {
    private static final DoubleKeyValueMap<String, Integer, ProcessLock> f = new DoubleKeyValueMap();
    private static final DecimalFormat g = new DecimalFormat("0.##################");
    private final String a;
    private final FileLock b;
    private final File c;
    private final Closeable d;
    private final boolean e;

    static {
        IOUtil.deleteFileOrDir(x.app().getDir("process_lock", 0));
    }

    private ProcessLock(String str, File file, FileLock fileLock, Closeable closeable, boolean z) {
        this.a = str;
        this.b = fileLock;
        this.c = file;
        this.d = closeable;
        this.e = z;
    }

    public static ProcessLock tryLock(String str, boolean z) {
        return a(str, a(str), z);
    }

    public static ProcessLock tryLock(String str, boolean z, long j) throws InterruptedException {
        ProcessLock processLock = null;
        long currentTimeMillis = System.currentTimeMillis() + j;
        String a = a(str);
        while (System.currentTimeMillis() < currentTimeMillis) {
            processLock = a(str, a, z);
            if (processLock != null) {
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw e;
            } catch (Throwable th) {
            }
        }
        return processLock;
    }

    public boolean isValid() {
        return a(this.b);
    }

    public void release() {
        a(this.a, this.b, this.c, this.d);
    }

    public void close() throws IOException {
        release();
    }

    private static boolean a(FileLock fileLock) {
        return fileLock != null && fileLock.isValid();
    }

    private static void a(String str, FileLock fileLock, File file, Closeable closeable) {
        synchronized (f) {
            if (fileLock != null) {
                try {
                    f.remove(str, Integer.valueOf(fileLock.hashCode()));
                    ConcurrentHashMap concurrentHashMap = f.get(str);
                    if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
                        IOUtil.deleteFileOrDir(file);
                    }
                    if (fileLock.channel().isOpen()) {
                        fileLock.release();
                    }
                    IOUtil.closeQuietly(fileLock.channel());
                } catch (Throwable th) {
                    IOUtil.closeQuietly(fileLock.channel());
                }
            }
            IOUtil.closeQuietly(closeable);
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        double d = 0.0d;
        byte[] bytes = str.getBytes();
        for (int i = 0; i < str.length(); i++) {
            d = ((d * 255.0d) + ((double) bytes[i])) * 0.005d;
        }
        return g.format(d);
    }

    private static ProcessLock a(String str, String str2, boolean z) {
        Throwable th;
        Closeable closeable;
        Closeable closeable2;
        boolean z2 = false;
        synchronized (f) {
            ConcurrentHashMap concurrentHashMap = f.get(str);
            if (!(concurrentHashMap == null || concurrentHashMap.isEmpty())) {
                Iterator it = concurrentHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    ProcessLock processLock = (ProcessLock) ((Entry) it.next()).getValue();
                    if (processLock == null) {
                        it.remove();
                    } else if (!processLock.isValid()) {
                        it.remove();
                    } else if (z) {
                        return null;
                    } else if (processLock.e) {
                        return null;
                    }
                }
            }
            try {
                File file = new File(x.app().getDir("process_lock", 0), str2);
                if (file.exists() || file.createNewFile()) {
                    Closeable fileOutputStream;
                    FileChannel channel;
                    if (z) {
                        fileOutputStream = new FileOutputStream(file, false);
                        channel = fileOutputStream.getChannel();
                    } else {
                        fileOutputStream = new FileInputStream(file);
                        channel = fileOutputStream.getChannel();
                    }
                    if (channel != null) {
                        if (!z) {
                            z2 = true;
                        }
                        try {
                            FileLock tryLock = channel.tryLock(0, Long.MAX_VALUE, z2);
                            if (a(tryLock)) {
                                ProcessLock processLock2 = new ProcessLock(str, file, tryLock, fileOutputStream, z);
                                f.put(str, Integer.valueOf(tryLock.hashCode()), processLock2);
                                return processLock2;
                            }
                            a(str, tryLock, file, fileOutputStream);
                        } catch (Throwable th2) {
                            th = th2;
                            closeable = channel;
                            closeable2 = fileOutputStream;
                            LogUtil.d("tryLock: " + str + ", " + th.getMessage());
                            IOUtil.closeQuietly(closeable2);
                            IOUtil.closeQuietly(closeable);
                            return null;
                        }
                    }
                    throw new IOException("can not get file channel:" + file.getAbsolutePath());
                }
            } catch (Throwable th3) {
                th = th3;
                closeable2 = null;
                closeable = null;
                LogUtil.d("tryLock: " + str + ", " + th.getMessage());
                IOUtil.closeQuietly(closeable2);
                IOUtil.closeQuietly(closeable);
                return null;
            }
            return null;
        }
    }

    public String toString() {
        return this.a + ": " + this.c.getName();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        release();
    }
}
