package org.xutils.http.request;

import com.baidu.mapapi.UIMsg.l_ErrorNo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class AssetsRequest extends UriRequest {
    private long a = 0;
    private InputStream b;

    public AssetsRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    public void sendRequest() throws Throwable {
    }

    public boolean isLoading() {
        return true;
    }

    public String getCacheKey() {
        return this.queryUrl;
    }

    public Object loadResult() throws Throwable {
        return this.loader.load((UriRequest) this);
    }

    public Object loadResultFromCache() throws Throwable {
        DiskCacheEntity diskCacheEntity = LruDiskCache.getDiskCache(this.params.getCacheDirName()).setMaxSize(this.params.getCacheSize()).get(getCacheKey());
        if (diskCacheEntity == null) {
            return null;
        }
        Date lastModify = diskCacheEntity.getLastModify();
        if (lastModify == null || lastModify.getTime() < getAssetsLastModified()) {
            return null;
        }
        return this.loader.loadFromCache(diskCacheEntity);
    }

    public void clearCacheHeader() {
    }

    public InputStream getInputStream() throws IOException {
        if (this.b == null && this.callingClassLoader != null) {
            this.b = this.callingClassLoader.getResourceAsStream("assets/" + this.queryUrl.substring("assets://".length()));
            this.a = (long) this.b.available();
        }
        return this.b;
    }

    public void close() throws IOException {
        IOUtil.closeQuietly(this.b);
        this.b = null;
    }

    public long getContentLength() {
        try {
            getInputStream();
            return this.a;
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            return 0;
        }
    }

    public int getResponseCode() throws IOException {
        return getInputStream() != null ? 200 : l_ErrorNo.NETWORK_ERROR_404;
    }

    public String getResponseMessage() throws IOException {
        return null;
    }

    public long getExpiration() {
        return Long.MAX_VALUE;
    }

    public long getLastModified() {
        return getAssetsLastModified();
    }

    public String getETag() {
        return null;
    }

    public String getResponseHeader(String str) {
        return null;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return null;
    }

    public long getHeaderFieldDate(String str, long j) {
        return j;
    }

    protected long getAssetsLastModified() {
        return new File(x.app().getApplicationInfo().sourceDir).lastModified();
    }
}
