package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.ParameterizedTypeUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpResponse;
import org.xutils.http.app.InputStreamResponseParser;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

/* compiled from: ObjectLoader */
class f extends Loader<Object> {
    private String a = "UTF-8";
    private String b = null;
    private final Type c;
    private final Class<?> d;
    private final ResponseParser e;

    public f(Type type) {
        this.c = type;
        if (type instanceof ParameterizedType) {
            this.d = (Class) ((ParameterizedType) type).getRawType();
        } else if (type instanceof TypeVariable) {
            throw new IllegalArgumentException("not support callback type " + type.toString());
        } else {
            this.d = (Class) type;
        }
        HttpResponse httpResponse;
        if (List.class.equals(this.d)) {
            Class cls;
            Type parameterizedType = ParameterizedTypeUtil.getParameterizedType(this.c, List.class, 0);
            if (parameterizedType instanceof ParameterizedType) {
                cls = (Class) ((ParameterizedType) parameterizedType).getRawType();
            } else if (parameterizedType instanceof TypeVariable) {
                throw new IllegalArgumentException("not support callback type " + parameterizedType.toString());
            } else {
                cls = (Class) parameterizedType;
            }
            httpResponse = (HttpResponse) cls.getAnnotation(HttpResponse.class);
            if (httpResponse != null) {
                try {
                    this.e = (ResponseParser) httpResponse.parser().newInstance();
                    return;
                } catch (Throwable th) {
                    RuntimeException runtimeException = new RuntimeException("create parser error", th);
                }
            } else {
                throw new IllegalArgumentException("not found @HttpResponse from " + parameterizedType);
            }
        }
        httpResponse = (HttpResponse) this.d.getAnnotation(HttpResponse.class);
        if (httpResponse != null) {
            try {
                this.e = (ResponseParser) httpResponse.parser().newInstance();
            } catch (Throwable th2) {
                runtimeException = new RuntimeException("create parser error", th2);
            }
        } else {
            throw new IllegalArgumentException("not found @HttpResponse from " + this.c);
        }
    }

    public Loader<Object> newInstance() {
        throw new IllegalAccessError("use constructor create ObjectLoader.");
    }

    public void setParams(RequestParams requestParams) {
        if (requestParams != null) {
            Object charset = requestParams.getCharset();
            if (!TextUtils.isEmpty(charset)) {
                this.a = charset;
            }
        }
    }

    public Object load(InputStream inputStream) throws Throwable {
        if (this.e instanceof InputStreamResponseParser) {
            return ((InputStreamResponseParser) this.e).parse(this.c, this.d, inputStream);
        }
        this.b = IOUtil.readStr(inputStream, this.a);
        return this.e.parse(this.c, this.d, this.b);
    }

    public Object load(UriRequest uriRequest) throws Throwable {
        try {
            uriRequest.sendRequest();
            return load(uriRequest.getInputStream());
        } finally {
            this.e.checkResponse(uriRequest);
        }
    }

    public Object loadFromCache(DiskCacheEntity diskCacheEntity) throws Throwable {
        if (diskCacheEntity != null) {
            Object textContent = diskCacheEntity.getTextContent();
            if (!TextUtils.isEmpty(textContent)) {
                return this.e.parse(this.c, this.d, textContent);
            }
        }
        return null;
    }

    public void save2Cache(UriRequest uriRequest) {
        saveStringCache(uriRequest, this.b);
    }
}
