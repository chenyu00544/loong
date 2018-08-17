package org.xutils.cache;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.ProcessLock;

public final class DiskCacheFile extends File implements Closeable {
    DiskCacheEntity a;
    ProcessLock b;

    DiskCacheFile(DiskCacheEntity diskCacheEntity, String str, ProcessLock processLock) {
        super(str);
        this.a = diskCacheEntity;
        this.b = processLock;
    }

    public void close() throws IOException {
        IOUtil.closeQuietly(this.b);
    }

    public DiskCacheFile commit() throws IOException {
        return getDiskCache().a(this);
    }

    public LruDiskCache getDiskCache() {
        return LruDiskCache.getDiskCache(getParentFile().getName());
    }

    public DiskCacheEntity getCacheEntity() {
        return this.a;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }
}
