package org.xutils.http.request;

import com.baidu.mapapi.UIMsg.l_ErrorNo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.xutils.common.util.IOUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.loader.FileLoader;

public class LocalFileRequest extends UriRequest {
    private InputStream a;

    LocalFileRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    public void sendRequest() throws Throwable {
    }

    public boolean isLoading() {
        return true;
    }

    public String getCacheKey() {
        return null;
    }

    public Object loadResult() throws Throwable {
        if (this.loader instanceof FileLoader) {
            return a();
        }
        return this.loader.load((UriRequest) this);
    }

    public Object loadResultFromCache() throws Throwable {
        return null;
    }

    public void clearCacheHeader() {
    }

    public void save2Cache() {
    }

    private File a() {
        String substring;
        if (this.queryUrl.startsWith("file:")) {
            substring = this.queryUrl.substring("file:".length());
        } else {
            substring = this.queryUrl;
        }
        return new File(substring);
    }

    public InputStream getInputStream() throws IOException {
        if (this.a == null) {
            this.a = new FileInputStream(a());
        }
        return this.a;
    }

    public void close() throws IOException {
        IOUtil.closeQuietly(this.a);
        this.a = null;
    }

    public long getContentLength() {
        return a().length();
    }

    public int getResponseCode() throws IOException {
        return a().exists() ? 200 : l_ErrorNo.NETWORK_ERROR_404;
    }

    public String getResponseMessage() throws IOException {
        return null;
    }

    public long getExpiration() {
        return -1;
    }

    public long getLastModified() {
        return a().lastModified();
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
}
