package org.xutils.image;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.xutils.cache.LruCache;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback.CacheCallback;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.Callback.PrepareCallback;
import org.xutils.common.Callback.ProgressCallback;
import org.xutils.common.Callback.TypedCallback;
import org.xutils.common.task.Priority;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.FileLockedException;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions.ParamsBuilder;
import org.xutils.x;

/* compiled from: ImageLoader */
final class a implements CacheCallback<Drawable>, Cancelable, PrepareCallback<File, Drawable>, ProgressCallback<Drawable>, TypedCallback<Drawable> {
    private static final AtomicLong d = new AtomicLong(0);
    private static final Executor m = new PriorityExecutor(10, false);
    private static final LruCache<b, Drawable> n = new LruCache<b, Drawable>(4194304) {
        private boolean a = false;

        protected int sizeOf(b bVar, Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                return bitmap == null ? 0 : bitmap.getByteCount();
            } else if (drawable instanceof GifDrawable) {
                return ((GifDrawable) drawable).getByteCount();
            } else {
                return super.sizeOf(bVar, drawable);
            }
        }

        public void trimToSize(int i) {
            if (i < 0) {
                this.a = true;
            }
            super.trimToSize(i);
            this.a = false;
        }

        protected void entryRemoved(boolean z, b bVar, Drawable drawable, Drawable drawable2) {
            super.entryRemoved(z, bVar, drawable, drawable2);
            if (z && this.a && (drawable instanceof d)) {
                ((d) drawable).setMemCacheKey(null);
            }
        }
    };
    private static final HashMap<String, a> o = new HashMap();
    private static final Type p = File.class;
    private b a;
    private ImageOptions b;
    private WeakReference<ImageView> c;
    private final long e = d.incrementAndGet();
    private volatile boolean f = false;
    private volatile boolean g = false;
    private Cancelable h;
    private CommonCallback<Drawable> i;
    private PrepareCallback<File, Drawable> j;
    private CacheCallback<Drawable> k;
    private ProgressCallback<Drawable> l;
    private boolean q = false;

    /* compiled from: ImageLoader */
    class a_2 implements Runnable {
        final /* synthetic */ a a;

        a_2(a aVar) {
            this.a = aVar;
        }

        public void run() {
            a.a((ImageView) this.a.c.get(), this.a.a.a, this.a.b, this.a.i);
        }
    }

    @SuppressLint({"ViewConstructor"})
    /* compiled from: ImageLoader */
    private static final class a extends ImageView {
        private Drawable a;

        public a() {
            super(x.app());
        }

        public void setImageDrawable(Drawable drawable) {
            this.a = drawable;
        }

        public Drawable getDrawable() {
            return this.a;
        }

        public void setLayerType(int i, Paint paint) {
        }

        public void setScaleType(ScaleType scaleType) {
        }

        public void startAnimation(Animation animation) {
        }
    }

    static {
        int memoryClass = (((ActivityManager) x.app().getSystemService("activity")).getMemoryClass() * 1048576) / 8;
        if (memoryClass < 4194304) {
            memoryClass = 4194304;
        }
        n.resize(memoryClass);
    }

    private a() {
    }

    static void a() {
        n.evictAll();
    }

    static void b() {
        LruDiskCache.getDiskCache("xUtils_img").clearCacheFiles();
    }

    static Cancelable a(String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback) {
        if (TextUtils.isEmpty(str)) {
            a(null, imageOptions, "url is null", (CommonCallback) commonCallback);
            return null;
        }
        ImageView imageView;
        synchronized (o) {
            imageView = (a) o.get(str);
            if (imageView == null) {
                imageView = new a();
            }
        }
        return a(imageView, str, imageOptions, (CommonCallback) commonCallback);
    }

    static Cancelable a(String str, ImageOptions imageOptions, CacheCallback<File> cacheCallback) {
        if (TextUtils.isEmpty(str)) {
            a(null, imageOptions, "url is null", (CommonCallback) cacheCallback);
            return null;
        }
        return x.http().get(a(str, imageOptions), cacheCallback);
    }

    static Cancelable a(ImageView imageView, String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback) {
        boolean z;
        Throwable th;
        boolean z2 = false;
        if (imageView == null) {
            a(null, imageOptions, "view is null", (CommonCallback) commonCallback);
            return null;
        } else if (TextUtils.isEmpty(str)) {
            a(imageView, imageOptions, "url is null", (CommonCallback) commonCallback);
            return null;
        } else {
            Drawable drawable;
            if (imageOptions == null) {
                imageOptions = ImageOptions.DEFAULT;
            }
            imageOptions.a(imageView);
            b bVar = new b(str, imageOptions);
            Drawable drawable2 = imageView.getDrawable();
            if (drawable2 instanceof AsyncDrawable) {
                a imageLoader = ((AsyncDrawable) drawable2).getImageLoader();
                if (!(imageLoader == null || imageLoader.f)) {
                    if (bVar.equals(imageLoader.a)) {
                        return null;
                    }
                    imageLoader.cancel();
                }
            } else if (drawable2 instanceof d) {
                b memCacheKey = ((d) drawable2).getMemCacheKey();
                if (memCacheKey != null && memCacheKey.equals(bVar)) {
                    n.put(bVar, drawable2);
                }
            }
            if (imageOptions.isUseMemCache()) {
                drawable2 = (Drawable) n.get(bVar);
                if (drawable2 instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) drawable2).getBitmap();
                    if (bitmap == null || bitmap.isRecycled()) {
                        drawable = null;
                    }
                }
                drawable = drawable2;
            } else {
                drawable = null;
            }
            if (drawable == null) {
                return new a().b(imageView, str, imageOptions, commonCallback);
            }
            try {
                if (commonCallback instanceof ProgressCallback) {
                    ((ProgressCallback) commonCallback).onWaiting();
                }
                imageView.setScaleType(imageOptions.getImageScaleType());
                imageView.setImageDrawable(drawable);
                z = true;
                try {
                    if (commonCallback instanceof CacheCallback) {
                        z = ((CacheCallback) commonCallback).onCache(drawable);
                        if (!z) {
                            try {
                                Cancelable b = new a().b(imageView, str, imageOptions, commonCallback);
                                if (!z || commonCallback == null) {
                                    return b;
                                }
                                try {
                                    commonCallback.onFinished();
                                    return b;
                                } catch (Throwable th2) {
                                    LogUtil.e(th2.getMessage(), th2);
                                    return b;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                z2 = z;
                                try {
                                    commonCallback.onFinished();
                                } catch (Throwable th22) {
                                    LogUtil.e(th22.getMessage(), th22);
                                }
                                throw th;
                            }
                        }
                    } else if (commonCallback != null) {
                        commonCallback.onSuccess(drawable);
                    }
                    if (z && commonCallback != null) {
                        try {
                            commonCallback.onFinished();
                        } catch (Throwable th4) {
                            LogUtil.e(th4.getMessage(), th4);
                        }
                    }
                    return null;
                } catch (Throwable th5) {
                    th4 = th5;
                    try {
                        LogUtil.e(th4.getMessage(), th4);
                    } catch (Throwable th6) {
                        th4 = th6;
                        z2 = z;
                        if (z2 && commonCallback != null) {
                            commonCallback.onFinished();
                        }
                        throw th4;
                    }
                    try {
                        return new a().b(imageView, str, imageOptions, commonCallback);
                    } catch (Throwable th7) {
                        th4 = th7;
                        commonCallback.onFinished();
                        throw th4;
                    }
                }
            } catch (Throwable th8) {
                th4 = th8;
                z = false;
                LogUtil.e(th4.getMessage(), th4);
                return new a().b(imageView, str, imageOptions, commonCallback);
            }
        }
    }

    private Cancelable b(ImageView imageView, String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback) {
        this.c = new WeakReference(imageView);
        this.b = imageOptions;
        this.a = new b(str, imageOptions);
        this.i = commonCallback;
        if (commonCallback instanceof ProgressCallback) {
            this.l = (ProgressCallback) commonCallback;
        }
        if (commonCallback instanceof PrepareCallback) {
            this.j = (PrepareCallback) commonCallback;
        }
        if (commonCallback instanceof CacheCallback) {
            this.k = (CacheCallback) commonCallback;
        }
        if (imageOptions.isForceLoadingDrawable()) {
            Drawable loadingDrawable = imageOptions.getLoadingDrawable(imageView);
            imageView.setScaleType(imageOptions.getPlaceholderScaleType());
            imageView.setImageDrawable(new AsyncDrawable(this, loadingDrawable));
        } else {
            imageView.setImageDrawable(new AsyncDrawable(this, imageView.getDrawable()));
        }
        RequestParams a = a(str, imageOptions);
        if (imageView instanceof a) {
            synchronized (o) {
                o.put(str, (a) imageView);
            }
        }
        Cancelable cancelable = x.http().get(a, this);
        this.h = cancelable;
        return cancelable;
    }

    public void cancel() {
        this.f = true;
        this.g = true;
        if (this.h != null) {
            this.h.cancel();
        }
    }

    public boolean isCancelled() {
        return this.g || !a(false);
    }

    public void onWaiting() {
        if (this.l != null) {
            this.l.onWaiting();
        }
    }

    public void onStarted() {
        if (a(true) && this.l != null) {
            this.l.onStarted();
        }
    }

    public void onLoading(long j, long j2, boolean z) {
        if (a(true) && this.l != null) {
            this.l.onLoading(j, j2, z);
        }
    }

    public Type getLoadType() {
        return p;
    }

    public Drawable prepare(File file) {
        if (!a(true)) {
            return null;
        }
        try {
            Drawable drawable;
            if (this.j != null) {
                drawable = (Drawable) this.j.prepare(file);
            } else {
                drawable = null;
            }
            if (drawable == null) {
                drawable = ImageDecoder.a(file, this.b, (Cancelable) this);
            }
            if (drawable == null || !(drawable instanceof d)) {
                return drawable;
            }
            ((d) drawable).setMemCacheKey(this.a);
            n.put(this.a, drawable);
            return drawable;
        } catch (Throwable e) {
            IOUtil.deleteFileOrDir(file);
            LogUtil.w(e.getMessage(), e);
            return null;
        }
    }

    public boolean onCache(Drawable drawable) {
        if (!a(true) || drawable == null) {
            return false;
        }
        this.q = true;
        a(drawable);
        if (this.k != null) {
            return this.k.onCache(drawable);
        }
        if (this.i == null) {
            return true;
        }
        this.i.onSuccess(drawable);
        return true;
    }

    public void onSuccess(Drawable drawable) {
        if (a(!this.q) && drawable != null) {
            a(drawable);
            if (this.i != null) {
                this.i.onSuccess(drawable);
            }
        }
    }

    public void onError(Throwable th, boolean z) {
        this.f = true;
        if (!a(false)) {
            return;
        }
        if (th instanceof FileLockedException) {
            LogUtil.d("ImageFileLocked: " + this.a.a);
            x.task().postDelayed(new a_2(this), 10);
            return;
        }
        LogUtil.e(this.a.a, th);
        c();
        if (this.i != null) {
            this.i.onError(th, z);
        }
    }

    public void onCancelled(CancelledException cancelledException) {
        this.f = true;
        if (a(false) && this.i != null) {
            this.i.onCancelled(cancelledException);
        }
    }

    public void onFinished() {
        this.f = true;
        if (((ImageView) this.c.get()) instanceof a) {
            synchronized (o) {
                o.remove(this.a.a);
            }
        }
        if (a(false) && this.i != null) {
            this.i.onFinished();
        }
    }

    private static RequestParams a(String str, ImageOptions imageOptions) {
        RequestParams requestParams = new RequestParams(str);
        requestParams.setCacheDirName("xUtils_img");
        requestParams.setConnectTimeout(8000);
        requestParams.setPriority(Priority.BG_LOW);
        requestParams.setExecutor(m);
        requestParams.setCancelFast(true);
        requestParams.setUseCookie(false);
        if (imageOptions == null) {
            return requestParams;
        }
        ParamsBuilder paramsBuilder = imageOptions.getParamsBuilder();
        if (paramsBuilder != null) {
            return paramsBuilder.buildParams(requestParams, imageOptions);
        }
        return requestParams;
    }

    private boolean a(boolean z) {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView == null) {
            return false;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof AsyncDrawable) {
            a imageLoader = ((AsyncDrawable) drawable).getImageLoader();
            if (imageLoader != null) {
                if (imageLoader == this) {
                    if (imageView.getVisibility() == 0) {
                        return true;
                    }
                    imageLoader.cancel();
                    return false;
                } else if (this.e > imageLoader.e) {
                    imageLoader.cancel();
                    return true;
                } else {
                    cancel();
                    return false;
                }
            }
        } else if (z) {
            cancel();
            return false;
        }
        return true;
    }

    private void a(Drawable drawable) {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView != null) {
            imageView.setScaleType(this.b.getImageScaleType());
            if (drawable instanceof GifDrawable) {
                if (imageView.getScaleType() == ScaleType.CENTER) {
                    imageView.setScaleType(ScaleType.CENTER_INSIDE);
                }
                imageView.setLayerType(1, null);
            }
            if (this.b.getAnimation() != null) {
                ImageAnimationHelper.animationDisplay(imageView, drawable, this.b.getAnimation());
            } else if (this.b.isFadeIn()) {
                ImageAnimationHelper.fadeInDisplay(imageView, drawable);
            } else {
                imageView.setImageDrawable(drawable);
            }
        }
    }

    private void c() {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView != null) {
            Drawable failureDrawable = this.b.getFailureDrawable(imageView);
            imageView.setScaleType(this.b.getPlaceholderScaleType());
            imageView.setImageDrawable(failureDrawable);
        }
    }

    private static void a(final ImageView imageView, final ImageOptions imageOptions, final String str, final CommonCallback<?> commonCallback) {
        x.task().autoPost(new Runnable() {
            public void run() {
                try {
                    if (commonCallback instanceof ProgressCallback) {
                        ((ProgressCallback) commonCallback).onWaiting();
                    }
                    if (!(imageView == null || imageOptions == null)) {
                        imageView.setScaleType(imageOptions.getPlaceholderScaleType());
                        imageView.setImageDrawable(imageOptions.getFailureDrawable(imageView));
                    }
                    if (commonCallback != null) {
                        commonCallback.onError(new IllegalArgumentException(str), false);
                    }
                    if (commonCallback != null) {
                        try {
                            commonCallback.onFinished();
                            return;
                        } catch (Throwable th) {
                            LogUtil.e(th.getMessage(), th);
                            return;
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    LogUtil.e(th2.getMessage(), th2);
                    return;
                }
                if (commonCallback != null) {
                    commonCallback.onFinished();
                }
            }
        });
    }
}
