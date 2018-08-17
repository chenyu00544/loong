package org.xutils.http.loader;

import com.taobao.accs.ErrorCode;
import java.io.InputStream;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.http.request.UriRequest;

/* compiled from: BooleanLoader */
class a extends Loader<Boolean> {
    a() {
    }

    public Loader<Boolean> newInstance() {
        return new a();
    }

    public Boolean load(InputStream inputStream) throws Throwable {
        return Boolean.valueOf(false);
    }

    public Boolean load(UriRequest uriRequest) throws Throwable {
        uriRequest.sendRequest();
        return Boolean.valueOf(uriRequest.getResponseCode() < ErrorCode.APP_NOT_BIND);
    }

    public Boolean loadFromCache(DiskCacheEntity diskCacheEntity) throws Throwable {
        return null;
    }

    public void save2Cache(UriRequest uriRequest) {
    }
}
