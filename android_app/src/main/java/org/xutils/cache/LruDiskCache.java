package org.xutils.cache;

import android.text.TextUtils;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import org.xutils.DbManager;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.FileUtil;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.common.util.MD5;
import org.xutils.common.util.ProcessLock;
import org.xutils.config.DbConfigs;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.FileLockedException;
import org.xutils.x;

public final class LruDiskCache {
    private static final HashMap<String, LruDiskCache> a = new HashMap(5);
    private boolean b = false;
    private final DbManager c = x.getDb(DbConfigs.HTTP.getConfig());
    private File d;
    private long e = 104857600;
    private final Executor f = new PriorityExecutor(1, true);
    private long g = 0;

    class LruDiskCache_2 implements Runnable {
        final /* synthetic */ LruDiskCache a;

        LruDiskCache_2(LruDiskCache lruDiskCache) {
            this.a = lruDiskCache;
        }

        public void run() {
            if (this.a.b) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.a.g >= 1000) {
                    List<Object> findAll;
                    String path;
                    this.a.g = currentTimeMillis;
                    this.a.b();
                    try {
                        int count = (int) this.a.c.selector(DiskCacheEntity.class).count();
                        if (count > 5010) {
                            findAll = this.a.c.selector(DiskCacheEntity.class).orderBy("lastAccess").orderBy("hits").limit(count - 5000).offset(0).findAll();
                            if (findAll != null && findAll.size() > 0) {
                                for (Object obj : findAll) {
                                    try {
                                        this.a.c.delete(obj);
                                        path = obj.getPath();
                                        if (!TextUtils.isEmpty(path)) {
                                            this.a.a(path);
                                            this.a.a(path + ".tmp");
                                        }
                                    } catch (Throwable e) {
                                        LogUtil.e(e.getMessage(), e);
                                    }
                                }
                            }
                        }
                    } catch (Throwable e2) {
                        LogUtil.e(e2.getMessage(), e2);
                    }
                    while (FileUtil.getFileOrDirSize(this.a.d) > this.a.e) {
                        findAll = this.a.c.selector(DiskCacheEntity.class).orderBy("lastAccess").orderBy("hits").limit(10).offset(0).findAll();
                        if (findAll != null && findAll.size() > 0) {
                            for (Object obj2 : findAll) {
                                try {
                                    this.a.c.delete(obj2);
                                    path = obj2.getPath();
                                    if (!TextUtils.isEmpty(path)) {
                                        this.a.a(path);
                                        this.a.a(path + ".tmp");
                                    }
                                } catch (Throwable e22) {
                                    try {
                                        LogUtil.e(e22.getMessage(), e22);
                                    } catch (Throwable e222) {
                                        LogUtil.e(e222.getMessage(), e222);
                                        return;
                                    }
                                }
                            }
                            continue;
                        }
                    }
                }
            }
        }
    }

    class LruDiskCache_3 implements Runnable {
        final /* synthetic */ LruDiskCache a;

        LruDiskCache_3(LruDiskCache lruDiskCache) {
            this.a = lruDiskCache;
        }

        public void run() {
            if (this.a.b) {
                try {
                    File[] listFiles = this.a.d.listFiles();
                    if (listFiles != null) {
                        for (File file : listFiles) {
                            if (this.a.c.selector(DiskCacheEntity.class).where("path", "=", file.getAbsolutePath()).count() < 1) {
                                IOUtil.deleteFileOrDir(file);
                            }
                        }
                    }
                } catch (Throwable th) {
                    LogUtil.e(th.getMessage(), th);
                }
            }
        }
    }

    public static synchronized LruDiskCache getDiskCache(String str) {
        LruDiskCache lruDiskCache;
        synchronized (LruDiskCache.class) {
            if (TextUtils.isEmpty(str)) {
                str = "xUtils_cache";
            }
            lruDiskCache = (LruDiskCache) a.get(str);
            if (lruDiskCache == null) {
                lruDiskCache = new LruDiskCache(str);
                a.put(str, lruDiskCache);
            }
        }
        return lruDiskCache;
    }

    private LruDiskCache(String str) {
        this.d = FileUtil.getCacheDir(str);
        if (this.d != null && (this.d.exists() || this.d.mkdirs())) {
            this.b = true;
        }
        c();
    }

    public LruDiskCache setMaxSize(long j) {
        if (j > 0) {
            long diskAvailableSize = FileUtil.getDiskAvailableSize();
            if (diskAvailableSize > j) {
                this.e = j;
            } else {
                this.e = diskAvailableSize;
            }
        }
        return this;
    }

    public DiskCacheEntity get(String str) {
        if (!this.b || TextUtils.isEmpty(str)) {
            return null;
        }
        DiskCacheEntity diskCacheEntity;
        try {
            diskCacheEntity = (DiskCacheEntity) this.c.selector(DiskCacheEntity.class).where("key", "=", str).findFirst();
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            diskCacheEntity = null;
        }
        if (diskCacheEntity == null) {
            return diskCacheEntity;
        }
        if (diskCacheEntity.getExpires() < System.currentTimeMillis()) {
            return null;
        }
        this.f.execute(new Runnable(this) {
            final /* synthetic */ LruDiskCache b;

            public void run() {
                diskCacheEntity.setHits(diskCacheEntity.getHits() + 1);
                diskCacheEntity.setLastAccess(System.currentTimeMillis());
                try {
                    this.b.c.update(diskCacheEntity, "hits", "lastAccess");
                } catch (Throwable th) {
                    LogUtil.e(th.getMessage(), th);
                }
            }
        });
        return diskCacheEntity;
    }

    public void put(DiskCacheEntity diskCacheEntity) {
        if (this.b && diskCacheEntity != null && !TextUtils.isEmpty(diskCacheEntity.getTextContent()) && diskCacheEntity.getExpires() >= System.currentTimeMillis()) {
            try {
                this.c.replace(diskCacheEntity);
            } catch (Throwable e) {
                LogUtil.e(e.getMessage(), e);
            }
            a();
        }
    }

    public DiskCacheFile getDiskCacheFile(String str) throws InterruptedException {
        if (!this.b || TextUtils.isEmpty(str)) {
            return null;
        }
        Object obj = get(str);
        if (obj == null || !new File(obj.getPath()).exists()) {
            return null;
        }
        ProcessLock tryLock = ProcessLock.tryLock(obj.getPath(), false, 3000);
        if (tryLock == null || !tryLock.isValid()) {
            return null;
        }
        DiskCacheFile diskCacheFile = new DiskCacheFile(obj, obj.getPath(), tryLock);
        if (diskCacheFile.exists()) {
            return diskCacheFile;
        }
        try {
            this.c.delete(obj);
            return null;
        } catch (Throwable e) {
            LogUtil.e(e.getMessage(), e);
            return null;
        }
    }

    public DiskCacheFile createDiskCacheFile(DiskCacheEntity diskCacheEntity) throws IOException {
        if (!this.b || diskCacheEntity == null) {
            return null;
        }
        diskCacheEntity.setPath(new File(this.d, MD5.md5(diskCacheEntity.getKey())).getAbsolutePath());
        String str = diskCacheEntity.getPath() + ".tmp";
        ProcessLock tryLock = ProcessLock.tryLock(str, true);
        if (tryLock == null || !tryLock.isValid()) {
            throw new FileLockedException(diskCacheEntity.getPath());
        }
        DiskCacheFile diskCacheFile = new DiskCacheFile(diskCacheEntity, str, tryLock);
        if (diskCacheFile.getParentFile().exists()) {
            return diskCacheFile;
        }
        diskCacheFile.mkdirs();
        return diskCacheFile;
    }

    public void clearCacheFiles() {
        IOUtil.deleteFileOrDir(this.d);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    org.xutils.cache.DiskCacheFile a(org.xutils.cache.DiskCacheFile r10) throws java.io.IOException {
        /*
        r9 = this;
        r1 = 0;
        if (r10 == 0) goto L_0x0012;
    L_0x0003:
        r2 = r10.length();
        r4 = 1;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x0012;
    L_0x000d:
        org.xutils.common.util.IOUtil.closeQuietly(r10);
        r10 = r1;
    L_0x0011:
        return r10;
    L_0x0012:
        r0 = r9.b;
        if (r0 == 0) goto L_0x0018;
    L_0x0016:
        if (r10 != 0) goto L_0x001a;
    L_0x0018:
        r10 = r1;
        goto L_0x0011;
    L_0x001a:
        r3 = r10.a;
        r0 = r10.getName();
        r2 = ".tmp";
        r0 = r0.endsWith(r2);
        if (r0 == 0) goto L_0x0011;
    L_0x0028:
        r4 = r3.getPath();	 Catch:{ InterruptedException -> 0x00d8, all -> 0x00c8 }
        r0 = 1;
        r6 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r2 = org.xutils.common.util.ProcessLock.tryLock(r4, r0, r6);	 Catch:{ InterruptedException -> 0x00d8, all -> 0x00c8 }
        if (r2 == 0) goto L_0x00a8;
    L_0x0035:
        r0 = r2.isValid();	 Catch:{ InterruptedException -> 0x00ae, all -> 0x00cc }
        if (r0 == 0) goto L_0x00a8;
    L_0x003b:
        r0 = new org.xutils.cache.DiskCacheFile;	 Catch:{ InterruptedException -> 0x00ae, all -> 0x00cc }
        r0.<init>(r3, r4, r2);	 Catch:{ InterruptedException -> 0x00ae, all -> 0x00cc }
        r4 = r10.renameTo(r0);	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        if (r4 == 0) goto L_0x007a;
    L_0x0046:
        r1 = r9.c;	 Catch:{ DbException -> 0x005a }
        r1.replace(r3);	 Catch:{ DbException -> 0x005a }
    L_0x004b:
        r9.a();	 Catch:{ InterruptedException -> 0x0063, all -> 0x00cf }
        if (r0 != 0) goto L_0x00b0;
    L_0x0050:
        org.xutils.common.util.IOUtil.closeQuietly(r0);
        org.xutils.common.util.IOUtil.closeQuietly(r2);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r0);
        goto L_0x0011;
    L_0x005a:
        r1 = move-exception;
        r3 = r1.getMessage();	 Catch:{ InterruptedException -> 0x0063, all -> 0x00cf }
        org.xutils.common.util.LogUtil.e(r3, r1);	 Catch:{ InterruptedException -> 0x0063, all -> 0x00cf }
        goto L_0x004b;
    L_0x0063:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x0067:
        r3 = r0.getMessage();	 Catch:{ all -> 0x00d5 }
        org.xutils.common.util.LogUtil.e(r3, r0);	 Catch:{ all -> 0x00d5 }
        if (r10 != 0) goto L_0x00b9;
    L_0x0070:
        org.xutils.common.util.IOUtil.closeQuietly(r1);
        org.xutils.common.util.IOUtil.closeQuietly(r2);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r1);
        goto L_0x0011;
    L_0x007a:
        r3 = new java.io.IOException;	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        r4 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        r4.<init>();	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        r5 = "rename:";
        r4 = r4.append(r5);	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        r5 = r10.getAbsolutePath();	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        r4 = r4.append(r5);	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        r4 = r4.toString();	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        r3.<init>(r4);	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
        throw r3;	 Catch:{ InterruptedException -> 0x0063, all -> 0x0097 }
    L_0x0097:
        r3 = move-exception;
        r8 = r3;
        r3 = r1;
        r1 = r0;
        r0 = r8;
    L_0x009c:
        if (r3 != 0) goto L_0x00c1;
    L_0x009e:
        org.xutils.common.util.IOUtil.closeQuietly(r1);
        org.xutils.common.util.IOUtil.closeQuietly(r2);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r1);
    L_0x00a7:
        throw r0;
    L_0x00a8:
        r0 = new org.xutils.ex.FileLockedException;	 Catch:{ InterruptedException -> 0x00ae, all -> 0x00cc }
        r0.<init>(r4);	 Catch:{ InterruptedException -> 0x00ae, all -> 0x00cc }
        throw r0;	 Catch:{ InterruptedException -> 0x00ae, all -> 0x00cc }
    L_0x00ae:
        r0 = move-exception;
        goto L_0x0067;
    L_0x00b0:
        org.xutils.common.util.IOUtil.closeQuietly(r10);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r10);
        r10 = r0;
        goto L_0x0011;
    L_0x00b9:
        org.xutils.common.util.IOUtil.closeQuietly(r10);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r10);
        goto L_0x0011;
    L_0x00c1:
        org.xutils.common.util.IOUtil.closeQuietly(r10);
        org.xutils.common.util.IOUtil.deleteFileOrDir(r10);
        goto L_0x00a7;
    L_0x00c8:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        goto L_0x009c;
    L_0x00cc:
        r0 = move-exception;
        r3 = r1;
        goto L_0x009c;
    L_0x00cf:
        r1 = move-exception;
        r3 = r0;
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x009c;
    L_0x00d5:
        r0 = move-exception;
        r3 = r10;
        goto L_0x009c;
    L_0x00d8:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0067;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.cache.LruDiskCache.a(org.xutils.cache.DiskCacheFile):org.xutils.cache.DiskCacheFile");
    }

    private void a() {
        this.f.execute(new LruDiskCache_2(this));
    }

    private void b() {
        try {
            WhereBuilder b = WhereBuilder.b(MobileRegisterActivity.RESPONSE_EXPIRES, "<", Long.valueOf(System.currentTimeMillis()));
            List<DiskCacheEntity> findAll = this.c.selector(DiskCacheEntity.class).where(b).findAll();
            this.c.delete(DiskCacheEntity.class, b);
            if (findAll != null && findAll.size() > 0) {
                for (DiskCacheEntity path : findAll) {
                    String path2 = path.getPath();
                    if (!TextUtils.isEmpty(path2)) {
                        a(path2);
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    private void c() {
        this.f.execute(new LruDiskCache_3(this));
    }

    private boolean a(String str) {
        Closeable closeable = null;
        try {
            closeable = ProcessLock.tryLock(str, true);
            if (closeable == null || !closeable.isValid()) {
                IOUtil.closeQuietly(closeable);
                return false;
            }
            boolean deleteFileOrDir = IOUtil.deleteFileOrDir(new File(str));
            return deleteFileOrDir;
        } finally {
            IOUtil.closeQuietly(closeable);
        }
    }
}
