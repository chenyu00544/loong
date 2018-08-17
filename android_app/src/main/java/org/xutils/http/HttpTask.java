package org.xutils.http;

import android.text.TextUtils;
import com.baidu.location.b.g;
import java.io.Closeable;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.common.Callback.CacheCallback;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.Callback.PrepareCallback;
import org.xutils.common.Callback.ProgressCallback;
import org.xutils.common.Callback.ProxyCacheCallback;
import org.xutils.common.Callback.TypedCallback;
import org.xutils.common.task.AbsTask;
import org.xutils.common.task.Priority;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.common.util.ParameterizedTypeUtil;
import org.xutils.ex.HttpRedirectException;
import org.xutils.http.app.HttpRetryHandler;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.request.UriRequest;
import org.xutils.http.request.UriRequestFactory;
import org.xutils.x;

public class HttpTask<ResultType> extends AbsTask<ResultType> implements ProgressHandler {
    static final /* synthetic */ boolean a;
    private static final AtomicInteger q = new AtomicInteger(0);
    private static final HashMap<String, WeakReference<HttpTask<?>>> r = new HashMap(1);
    private static final PriorityExecutor s = new PriorityExecutor(5, true);
    private static final PriorityExecutor t = new PriorityExecutor(5, true);
    private RequestParams b;
    private UriRequest c;
    private a d;
    private final Executor e;
    private volatile boolean f = false;
    private final CommonCallback<ResultType> g;
    private Object h = null;
    private volatile Boolean i = null;
    private final Object j = new Object();
    private CacheCallback<ResultType> k;
    private PrepareCallback l;
    private ProgressCallback m;
    private RequestInterceptListener n;
    private RequestTracker o;
    private Type p;
    private long u;
    private long v = 300;

    class HttpTask_1 implements Runnable {
        final /* synthetic */ HttpTask a;

        HttpTask_1(HttpTask httpTask) {
            this.a = httpTask;
        }

        public void run() {
            this.a.f();
        }
    }

    class HttpTask_2 implements Runnable {
        final /* synthetic */ HttpTask a;

        HttpTask_2(HttpTask httpTask) {
            this.a = httpTask;
        }

        public void run() {
            this.a.f();
        }
    }

    private final class a {
        Object a;
        Throwable b;
        final /* synthetic */ HttpTask c;

        private a(HttpTask httpTask) {
            this.c = httpTask;
        }

        public void a() {
            Object obj = null;
            try {
                if (File.class == this.c.p) {
                    synchronized (HttpTask.q) {
                        while (HttpTask.q.get() >= 3 && !this.c.isCancelled()) {
                            try {
                                HttpTask.q.wait(10);
                            } catch (InterruptedException e) {
                                obj = 1;
                            } catch (Throwable th) {
                            }
                        }
                    }
                    HttpTask.q.incrementAndGet();
                }
                if (obj != null || this.c.isCancelled()) {
                    String str;
                    StringBuilder append = new StringBuilder().append("cancelled before request");
                    if (obj != null) {
                        str = "(interrupted)";
                    } else {
                        str = "";
                    }
                    throw new CancelledException(append.append(str).toString());
                }
                try {
                    this.c.c.setRequestInterceptListener(this.c.n);
                    this.a = this.c.c.loadResult();
                } catch (Throwable th2) {
                    this.b = th2;
                }
                if (this.b != null) {
                    throw this.b;
                } else if (File.class == this.c.p) {
                    synchronized (HttpTask.q) {
                        HttpTask.q.decrementAndGet();
                        HttpTask.q.notifyAll();
                    }
                    return;
                } else {
                    return;
                }
            } catch (Throwable th3) {
                this.b = th;
            }
            if (File.class == this.c.p) {
                synchronized (HttpTask.q) {
                    HttpTask.q.decrementAndGet();
                    HttpTask.q.notifyAll();
                }
            }
        }
    }

    static {
        boolean z;
        if (HttpTask.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
    }

    public HttpTask(RequestParams requestParams, Cancelable cancelable, CommonCallback<ResultType> commonCallback) {
        super(cancelable);
        if (!a && requestParams == null) {
            throw new AssertionError();
        } else if (a || commonCallback != null) {
            this.b = requestParams;
            this.g = commonCallback;
            if (commonCallback instanceof CacheCallback) {
                this.k = (CacheCallback) commonCallback;
            }
            if (commonCallback instanceof PrepareCallback) {
                this.l = (PrepareCallback) commonCallback;
            }
            if (commonCallback instanceof ProgressCallback) {
                this.m = (ProgressCallback) commonCallback;
            }
            if (commonCallback instanceof RequestInterceptListener) {
                this.n = (RequestInterceptListener) commonCallback;
            }
            RequestTracker requestTracker = requestParams.getRequestTracker();
            if (requestTracker != null) {
                Object obj = requestTracker;
            } else if (commonCallback instanceof RequestTracker) {
                commonCallback = (RequestTracker) commonCallback;
            } else {
                commonCallback = UriRequestFactory.getDefaultTracker();
            }
            if (commonCallback != null) {
                this.o = new b(commonCallback);
            }
            if (requestParams.getExecutor() != null) {
                this.e = requestParams.getExecutor();
            } else if (this.k != null) {
                this.e = t;
            } else {
                this.e = s;
            }
        } else {
            throw new AssertionError();
        }
    }

    private void b() {
        Type type = this.g.getClass();
        if (this.g instanceof TypedCallback) {
            this.p = ((TypedCallback) this.g).getLoadType();
        } else if (this.g instanceof PrepareCallback) {
            this.p = ParameterizedTypeUtil.getParameterizedType(type, PrepareCallback.class, 0);
        } else {
            this.p = ParameterizedTypeUtil.getParameterizedType(type, CommonCallback.class, 0);
        }
    }

    private UriRequest c() throws Throwable {
        this.b.a();
        UriRequest uriRequest = UriRequestFactory.getUriRequest(this.b, this.p);
        uriRequest.setCallingClassLoader(this.g.getClass().getClassLoader());
        uriRequest.setProgressHandler(this);
        this.v = (long) this.b.getLoadingUpdateMaxTimeSpan();
        update(1, uriRequest);
        return uriRequest;
    }

    private void d() {
        if (File.class == this.p) {
            synchronized (r) {
                WeakReference weakReference;
                CharSequence saveFilePath = this.b.getSaveFilePath();
                if (!TextUtils.isEmpty(saveFilePath)) {
                    weakReference = (WeakReference) r.get(saveFilePath);
                    if (weakReference != null) {
                        HttpTask httpTask = (HttpTask) weakReference.get();
                        if (httpTask != null) {
                            httpTask.cancel();
                            httpTask.f();
                        }
                        r.remove(saveFilePath);
                    }
                    r.put(saveFilePath, new WeakReference(this));
                }
                if (r.size() > 3) {
                    Iterator it = r.entrySet().iterator();
                    while (it.hasNext()) {
                        weakReference = (WeakReference) ((Entry) it.next()).getValue();
                        if (weakReference == null || weakReference.get() == null) {
                            it.remove();
                        }
                    }
                }
            }
        }
    }

    protected ResultType doBackground() throws Throwable {
        ResultType resultType;
        int i;
        boolean z;
        ResultType resultType2;
        ResultType resultType3;
        if (isCancelled()) {
            throw new CancelledException("cancelled before request");
        }
        HttpRetryHandler httpRetryHandler;
        b();
        this.c = c();
        d();
        HttpRetryHandler httpRetryHandler2 = this.b.getHttpRetryHandler();
        if (httpRetryHandler2 == null) {
            httpRetryHandler = new HttpRetryHandler();
        } else {
            httpRetryHandler = httpRetryHandler2;
        }
        httpRetryHandler.setMaxRetryCount(this.b.getMaxRetryCount());
        if (isCancelled()) {
            throw new CancelledException("cancelled before request");
        }
        Object prepare;
        if (this.k != null && HttpMethod.permitsCache(this.b.getMethod())) {
            try {
                e();
                LogUtil.d("load cache: " + this.c.getRequestUri());
                this.h = this.c.loadResultFromCache();
            } catch (Throwable th) {
                LogUtil.w("load disk cache error", th);
            }
            if (isCancelled()) {
                e();
                throw new CancelledException("cancelled before request");
            } else if (this.h != null) {
                if (this.l != null) {
                    try {
                        prepare = this.l.prepare(this.h);
                    } catch (Throwable th2) {
                        prepare = th2;
                        LogUtil.w("prepare disk cache error", prepare);
                        prepare = null;
                        if (isCancelled()) {
                            throw new CancelledException("cancelled before request");
                        }
                        if (prepare != null) {
                            update(2, prepare);
                            synchronized (this.j) {
                                while (this.i == null) {
                                    try {
                                        this.j.wait();
                                    } catch (InterruptedException e) {
                                        throw new CancelledException("cancelled before request");
                                    } catch (Throwable th3) {
                                    }
                                }
                            }
                            if (this.i.booleanValue()) {
                                return null;
                            }
                        }
                        if (this.i == null) {
                            this.i = Boolean.valueOf(false);
                        }
                        if (prepare == null) {
                            this.c.clearCacheHeader();
                        }
                        if (!(this.g instanceof ProxyCacheCallback)) {
                        }
                        resultType = null;
                        i = 0;
                        z = true;
                        prepare = null;
                        while (z) {
                            try {
                                if (isCancelled()) {
                                    throw new CancelledException("cancelled before request");
                                }
                                this.c.close();
                                e();
                                LogUtil.d("load: " + this.c.getRequestUri());
                                this.d = new a();
                                this.d.a();
                                if (this.d.b != null) {
                                    throw this.d.b;
                                }
                                this.h = this.d.a;
                                if (this.l != null) {
                                    prepare = this.h;
                                } else if (isCancelled()) {
                                    throw new CancelledException("cancelled before request");
                                } else {
                                    prepare = this.l.prepare(this.h);
                                    e();
                                }
                                this.c.save2Cache();
                                if (isCancelled()) {
                                    throw new CancelledException("cancelled after request");
                                }
                                z = false;
                            } catch (HttpRedirectException e2) {
                                LogUtil.w("Http Redirect:" + this.b.getUri());
                                z = true;
                            } catch (ResultType th4) {
                                resultType2 = th4;
                                r3 = prepare;
                                resultType3 = resultType2;
                                switch (this.c.getResponseCode()) {
                                    case g.c /*204*/:
                                    case g.aa /*205*/:
                                    case 304:
                                        return null;
                                    default:
                                        Object obj;
                                        if (isCancelled() && !(resultType3 instanceof CancelledException)) {
                                            resultType3 = new CancelledException("canceled by user");
                                        }
                                        int i2 = i + 1;
                                        boolean canRetry = httpRetryHandler.canRetry(this.c, resultType3, i2);
                                        resultType2 = resultType3;
                                        prepare = obj;
                                        z = canRetry;
                                        i = i2;
                                        resultType = resultType2;
                                        break;
                                }
                            }
                        }
                        if (resultType != null) {
                        }
                        return prepare;
                    } finally {
                        e();
                    }
                } else {
                    prepare = this.h;
                }
                if (isCancelled()) {
                    throw new CancelledException("cancelled before request");
                }
                if (prepare != null) {
                    update(2, prepare);
                    synchronized (this.j) {
                        while (this.i == null) {
                            this.j.wait();
                        }
                    }
                    if (this.i.booleanValue()) {
                        return null;
                    }
                }
                if (this.i == null) {
                    this.i = Boolean.valueOf(false);
                }
                if (prepare == null) {
                    this.c.clearCacheHeader();
                }
                if (!(this.g instanceof ProxyCacheCallback) && ((ProxyCacheCallback) this.g).onlyCache()) {
                    return null;
                }
                resultType = null;
                i = 0;
                z = true;
                prepare = null;
                while (z) {
                    if (isCancelled()) {
                        throw new CancelledException("cancelled before request");
                    }
                    this.c.close();
                    e();
                    LogUtil.d("load: " + this.c.getRequestUri());
                    this.d = new a();
                    this.d.a();
                    if (this.d.b != null) {
                        throw this.d.b;
                    }
                    this.h = this.d.a;
                    if (this.l != null) {
                        prepare = this.h;
                    } else if (isCancelled()) {
                        throw new CancelledException("cancelled before request");
                    } else {
                        prepare = this.l.prepare(this.h);
                        e();
                    }
                    if (this.k != null && HttpMethod.permitsCache(this.b.getMethod())) {
                        this.c.save2Cache();
                    }
                    if (isCancelled()) {
                        throw new CancelledException("cancelled after request");
                    }
                    z = false;
                }
                if (resultType != null || prepare != null || this.i.booleanValue()) {
                    return prepare;
                }
                this.f = true;
                throw resultType;
            }
        }
        prepare = null;
        if (this.i == null) {
            this.i = Boolean.valueOf(false);
        }
        if (prepare == null) {
            this.c.clearCacheHeader();
        }
        if (!(this.g instanceof ProxyCacheCallback)) {
        }
        resultType = null;
        i = 0;
        z = true;
        prepare = null;
        while (z) {
            if (isCancelled()) {
                this.c.close();
                e();
                LogUtil.d("load: " + this.c.getRequestUri());
                this.d = new a();
                this.d.a();
                if (this.d.b != null) {
                    this.h = this.d.a;
                    if (this.l != null) {
                        prepare = this.h;
                    } else if (isCancelled()) {
                        prepare = this.l.prepare(this.h);
                        e();
                    } else {
                        throw new CancelledException("cancelled before request");
                    }
                    this.c.save2Cache();
                    if (isCancelled()) {
                        z = false;
                    } else {
                        throw new CancelledException("cancelled after request");
                    }
                }
                throw this.d.b;
            }
            throw new CancelledException("cancelled before request");
        }
        if (resultType != null) {
        }
        return prepare;
    }

    protected void onUpdate(int i, Object... objArr) {
        switch (i) {
            case 1:
                if (this.o != null) {
                    this.o.onRequestCreated((UriRequest) objArr[0]);
                    return;
                }
                return;
            case 2:
                synchronized (this.j) {
                    try {
                        Object obj = objArr[0];
                        if (this.o != null) {
                            this.o.onCache(this.c, obj);
                        }
                        this.i = Boolean.valueOf(this.k.onCache(obj));
                    } catch (Throwable th) {
                        this.i = Boolean.valueOf(false);
                        this.g.onError(th, true);
                    } finally {
                        this.j.notifyAll();
                    }
                }
                return;
            case 3:
                if (this.m != null && objArr.length == 3) {
                    try {
                        this.m.onLoading(((Number) objArr[0]).longValue(), ((Number) objArr[1]).longValue(), ((Boolean) objArr[2]).booleanValue());
                        return;
                    } catch (Throwable th2) {
                        this.g.onError(th2, true);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    protected void onWaiting() {
        if (this.o != null) {
            this.o.onWaiting(this.b);
        }
        if (this.m != null) {
            this.m.onWaiting();
        }
    }

    protected void onStarted() {
        if (this.o != null) {
            this.o.onStart(this.b);
        }
        if (this.m != null) {
            this.m.onStarted();
        }
    }

    protected void onSuccess(ResultType resultType) {
        if (!this.f) {
            if (this.o != null) {
                this.o.onSuccess(this.c, resultType);
            }
            this.g.onSuccess(resultType);
        }
    }

    protected void onError(Throwable th, boolean z) {
        if (this.o != null) {
            this.o.onError(this.c, th, z);
        }
        this.g.onError(th, z);
    }

    protected void onCancelled(CancelledException cancelledException) {
        if (this.o != null) {
            this.o.onCancelled(this.c);
        }
        this.g.onCancelled(cancelledException);
    }

    protected void onFinished() {
        if (this.o != null) {
            this.o.onFinished(this.c);
        }
        x.task().run(new HttpTask_1(this));
        this.g.onFinished();
    }

    private void e() {
        if (this.h instanceof Closeable) {
            IOUtil.closeQuietly((Closeable) this.h);
        }
        this.h = null;
    }

    protected void cancelWorks() {
        x.task().run(new HttpTask_2(this));
    }

    protected boolean isCancelFast() {
        return this.b.isCancelFast();
    }

    private void f() {
        e();
        IOUtil.closeQuietly(this.c);
    }

    public Executor getExecutor() {
        return this.e;
    }

    public Priority getPriority() {
        return this.b.getPriority();
    }

    public boolean updateProgress(long j, long j2, boolean z) {
        if (isCancelled() || isFinished()) {
            return false;
        }
        if (!(this.m == null || this.c == null || j <= 0)) {
            if (j < j2) {
                j = j2;
            }
            if (z) {
                this.u = System.currentTimeMillis();
                update(3, Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(this.c.isLoading()));
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.u >= this.v) {
                    this.u = currentTimeMillis;
                    update(3, Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(this.c.isLoading()));
                }
            }
        }
        if (isCancelled() || isFinished()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.b.toString();
    }
}
