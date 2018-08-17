package org.xutils.http.cookie;

import android.text.TextUtils;
import anet.channel.strategy.dispatch.c;
import anet.channel.util.HttpConstant;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;

public enum DbCookieStore implements CookieStore {
    INSTANCE;
    
    private final DbManager a;
    private final Executor b;
    private long c;

    class DbCookieStore_1 implements Runnable {
        final /* synthetic */ DbCookieStore a;

        DbCookieStore_1(DbCookieStore dbCookieStore) {
            this.a = dbCookieStore;
        }

        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.a.c >= 1000) {
                this.a.c = currentTimeMillis;
                try {
                    this.a.a.delete(a.class, WhereBuilder.b("expiry", "<", Long.valueOf(System.currentTimeMillis())).and("expiry", "!=", Long.valueOf(-1)));
                } catch (Throwable th) {
                    LogUtil.e(th.getMessage(), th);
                }
                try {
                    int count = (int) this.a.a.selector(a.class).count();
                    if (count > 5010) {
                        Object findAll = this.a.a.selector(a.class).where("expiry", "!=", Long.valueOf(-1)).orderBy("expiry", false).limit(count - 5000).findAll();
                        if (findAll != null) {
                            this.a.a.delete(findAll);
                        }
                    }
                } catch (Throwable th2) {
                    LogUtil.e(th2.getMessage(), th2);
                }
            }
        }
    }

    public void add(URI uri, HttpCookie httpCookie) {
        if (httpCookie != null) {
            try {
                this.a.replace(new a(a(uri), httpCookie));
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
            a();
        }
    }

    public List<HttpCookie> get(URI uri) {
        if (uri == null) {
            throw new NullPointerException("uri is null");
        }
        URI a = a(uri);
        List<HttpCookie> arrayList = new ArrayList();
        try {
            Selector selector = this.a.selector(a.class);
            WhereBuilder b = WhereBuilder.b();
            String host = a.getHost();
            if (!TextUtils.isEmpty(host)) {
                WhereBuilder or = WhereBuilder.b(c.DOMAIN, "=", host).or(c.DOMAIN, "=", "." + host);
                int indexOf = host.indexOf(".");
                int lastIndexOf = host.lastIndexOf(".");
                if (indexOf > 0 && lastIndexOf > indexOf) {
                    CharSequence substring = host.substring(indexOf, host.length());
                    if (!TextUtils.isEmpty(substring)) {
                        or.or(c.DOMAIN, "=", substring);
                    }
                }
                b.and(or);
            }
            String path = a.getPath();
            if (!TextUtils.isEmpty(path)) {
                WhereBuilder or2 = WhereBuilder.b("path", "=", path).or("path", "=", "/").or("path", "=", null);
                int lastIndexOf2 = path.lastIndexOf("/");
                while (lastIndexOf2 > 0) {
                    path = path.substring(0, lastIndexOf2);
                    or2.or("path", "=", path);
                    lastIndexOf2 = path.lastIndexOf("/");
                }
                b.and(or2);
            }
            b.or("uri", "=", a.toString());
            List<a> findAll = selector.where(b).findAll();
            if (findAll != null) {
                for (a aVar : findAll) {
                    if (!aVar.isExpired()) {
                        arrayList.add(aVar.toHttpCookie());
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        return arrayList;
    }

    public List<HttpCookie> getCookies() {
        List<HttpCookie> arrayList = new ArrayList();
        try {
            List<a> findAll = this.a.findAll(a.class);
            if (findAll != null) {
                for (a aVar : findAll) {
                    if (!aVar.isExpired()) {
                        arrayList.add(aVar.toHttpCookie());
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        return arrayList;
    }

    public List<URI> getURIs() {
        List<URI> arrayList = new ArrayList();
        List<DbModel> findAll = this.a.selector(a.class).select("uri").findAll();
        if (findAll != null) {
            for (DbModel string : findAll) {
                Object string2 = string.getString("uri");
                if (!TextUtils.isEmpty(string2)) {
                    try {
                        arrayList.add(new URI(string2));
                    } catch (Throwable th) {
                        LogUtil.e(th.getMessage(), th);
                    }
                }
            }
        }
        return arrayList;
    }

    public boolean remove(URI uri, HttpCookie httpCookie) {
        if (httpCookie == null) {
            return true;
        }
        try {
            WhereBuilder b = WhereBuilder.b("name", "=", httpCookie.getName());
            CharSequence domain = httpCookie.getDomain();
            if (!TextUtils.isEmpty(domain)) {
                b.and(c.DOMAIN, "=", domain);
            }
            Object path = httpCookie.getPath();
            if (!TextUtils.isEmpty(path)) {
                if (path.length() > 1 && path.endsWith("/")) {
                    path = path.substring(0, path.length() - 1);
                }
                b.and("path", "=", path);
            }
            this.a.delete(a.class, b);
            return true;
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            return false;
        }
    }

    public boolean removeAll() {
        try {
            this.a.delete(a.class);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        return true;
    }

    private void a() {
        this.b.execute(new DbCookieStore_1(this));
    }

    private URI a(URI uri) {
        try {
            return new URI(HttpConstant.HTTP, uri.getHost(), uri.getPath(), null, null);
        } catch (Throwable th) {
            return uri;
        }
    }
}
