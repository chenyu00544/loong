package org.xutils.image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build.VERSION;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

public final class ImageDecoder {
    private static final int a;
    private static final AtomicInteger b = new AtomicInteger(0);
    private static final Object c = new Object();
    private static final Object d = new Object();
    private static final byte[] e = new byte[]{(byte) 71, (byte) 73, (byte) 70};
    private static final Executor f = new PriorityExecutor(1, true);
    private static final LruDiskCache g = LruDiskCache.getDiskCache("xUtils_img_thumb");
    private static final boolean h;

    static {
        boolean z = false;
        int i = 1;
        if (VERSION.SDK_INT > 16) {
            z = true;
        }
        h = z;
        if (Runtime.getRuntime().availableProcessors() > 4) {
            i = 2;
        }
        a = i;
    }

    private ImageDecoder() {
    }

    static void a() {
        g.clearCacheFiles();
    }

    static Drawable a(final File file, final ImageOptions imageOptions, Cancelable cancelable) throws IOException {
        if (file == null || !file.exists() || file.length() < 1) {
            return null;
        }
        if (cancelable != null && cancelable.isCancelled()) {
            throw new CancelledException("cancelled during decode image");
        } else if (imageOptions.isIgnoreGif() || !isGif(file)) {
            try {
                Bitmap a;
                synchronized (c) {
                    while (b.get() >= a && (cancelable == null || !cancelable.isCancelled())) {
                        try {
                            c.wait();
                        } catch (InterruptedException e) {
                            throw new CancelledException("cancelled during decode image");
                        } catch (Throwable th) {
                        }
                    }
                }
                if (cancelable != null) {
                    if (cancelable.isCancelled()) {
                        throw new CancelledException("cancelled during decode image");
                    }
                }
                b.incrementAndGet();
                if (imageOptions.isCompress()) {
                    a = a(file, imageOptions);
                } else {
                    a = null;
                }
                if (a == null) {
                    a = decodeBitmap(file, imageOptions, cancelable);
                    if (a != null && imageOptions.isCompress()) {
                        f.execute(new Runnable() {
                            public void run() {
                                ImageDecoder.b(file, imageOptions, a);
                            }
                        });
                    }
                }
                b.decrementAndGet();
                synchronized (c) {
                    c.notifyAll();
                }
                if (a != null) {
                    return new c(x.app().getResources(), a);
                }
                return null;
            } catch (Throwable th2) {
                b.decrementAndGet();
                synchronized (c) {
                    c.notifyAll();
                }
            }
        } else {
            Movie decodeGif;
            synchronized (d) {
                decodeGif = decodeGif(file, imageOptions, cancelable);
            }
            if (decodeGif != null) {
                return new GifDrawable(decodeGif, (int) file.length());
            }
            return null;
        }
    }

    public static boolean isGif(File file) {
        Throwable th;
        Closeable fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                boolean equals = Arrays.equals(e, IOUtil.readBytes(fileInputStream, 0, 3));
                IOUtil.closeQuietly(fileInputStream);
                return equals;
            } catch (Throwable th2) {
                th = th2;
                try {
                    LogUtil.e(th.getMessage(), th);
                    IOUtil.closeQuietly(fileInputStream);
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    IOUtil.closeQuietly(fileInputStream);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            IOUtil.closeQuietly(fileInputStream);
            throw th;
        }
    }

    public static Bitmap decodeBitmap(File file, ImageOptions imageOptions, Cancelable cancelable) throws IOException {
        int i = 0;
        if (file == null || !file.exists() || file.length() < 1) {
            return null;
        }
        if (imageOptions == null) {
            imageOptions = ImageOptions.DEFAULT;
        }
        if (imageOptions.getMaxWidth() <= 0 || imageOptions.getMaxHeight() <= 0) {
            imageOptions.a(null);
        }
        if (cancelable != null) {
            try {
                if (cancelable.isCancelled()) {
                    throw new CancelledException("cancelled during decode image");
                }
            } catch (IOException e) {
                throw e;
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
                return null;
            }
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = imageOptions.getConfig();
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int width = imageOptions.getWidth();
        int height = imageOptions.getHeight();
        if (imageOptions.isAutoRotate()) {
            i = getRotateAngle(file.getAbsolutePath());
            if ((i / 90) % 2 == 1) {
                i2 = options.outHeight;
                i3 = options.outWidth;
            }
        }
        if (!imageOptions.isCrop() && width > 0 && height > 0) {
            if ((i / 90) % 2 == 1) {
                options.outWidth = height;
                options.outHeight = width;
            } else {
                options.outWidth = width;
                options.outHeight = height;
            }
        }
        options.inSampleSize = calculateSampleSize(i2, i3, imageOptions.getMaxWidth(), imageOptions.getMaxHeight());
        if (cancelable == null || !cancelable.isCancelled()) {
            Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            if (decodeFile == null) {
                throw new IOException("decode image error");
            } else if (cancelable == null || !cancelable.isCancelled()) {
                if (i != 0) {
                    decodeFile = rotate(decodeFile, i, true);
                }
                if (cancelable == null || !cancelable.isCancelled()) {
                    if (imageOptions.isCrop() && width > 0 && height > 0) {
                        decodeFile = cut2ScaleSize(decodeFile, width, height, true);
                    }
                    if (decodeFile == null) {
                        throw new IOException("decode image error");
                    } else if (cancelable == null || !cancelable.isCancelled()) {
                        if (imageOptions.isCircular()) {
                            decodeFile = cut2Circular(decodeFile, true);
                        } else if (imageOptions.getRadius() > 0) {
                            decodeFile = cut2RoundCorner(decodeFile, imageOptions.getRadius(), imageOptions.isSquare(), true);
                        } else if (imageOptions.isSquare()) {
                            decodeFile = cut2Square(decodeFile, true);
                        }
                        if (decodeFile != null) {
                            return decodeFile;
                        }
                        throw new IOException("decode image error");
                    } else {
                        throw new CancelledException("cancelled during decode image");
                    }
                }
                throw new CancelledException("cancelled during decode image");
            } else {
                throw new CancelledException("cancelled during decode image");
            }
        }
        throw new CancelledException("cancelled during decode image");
    }

    public static Movie decodeGif(File file, ImageOptions imageOptions, Cancelable cancelable) throws IOException {
        Throwable th;
        if (file == null || !file.exists() || file.length() < 1) {
            return null;
        }
        Closeable closeable;
        if (cancelable != null) {
            try {
                if (cancelable.isCancelled()) {
                    throw new CancelledException("cancelled during decode image");
                }
            } catch (IOException e) {
                IOException iOException = e;
                Movie movie = null;
                r0 = iOException;
                try {
                    IOException iOException2;
                    throw iOException2;
                } catch (Throwable th2) {
                    th = th2;
                    closeable = movie;
                }
            } catch (Throwable th3) {
                closeable = null;
                th = th3;
                IOUtil.closeQuietly(closeable);
                throw th;
            }
        }
        closeable = new BufferedInputStream(new FileInputStream(file), 16384);
        try {
            closeable.mark(16384);
            movie = Movie.decodeStream(closeable);
            if (movie == null) {
                throw new IOException("decode image error");
            }
            IOUtil.closeQuietly(closeable);
            return movie;
        } catch (IOException e2) {
            iOException2 = e2;
            Object obj = closeable;
            throw iOException2;
        } catch (Throwable th4) {
            th3 = th4;
            LogUtil.e(th3.getMessage(), th3);
            IOUtil.closeQuietly(closeable);
            return null;
        }
    }

    public static int calculateSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i > i3 || i2 > i4) {
            int round;
            if (i > i2) {
                round = Math.round(((float) i2) / ((float) i4));
            } else {
                round = Math.round(((float) i) / ((float) i3));
            }
            if (round >= 1) {
                i5 = round;
            }
            while (((float) (i * i2)) / ((float) (i5 * i5)) > ((float) ((i3 * i4) * 2))) {
                i5++;
            }
        }
        return i5;
    }

    public static Bitmap cut2Square(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == height) {
            return bitmap;
        }
        int min = Math.min(width, height);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, (width - min) / 2, (height - min) / 2, min, min);
        if (createBitmap == null) {
            return bitmap;
        }
        if (!z || createBitmap == bitmap) {
            return createBitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap cut2Circular(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        if (createBitmap == null) {
            return bitmap;
        }
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawCircle((float) (min / 2), (float) (min / 2), (float) (min / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float) ((min - width) / 2), (float) ((min - height) / 2), paint);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap cut2RoundCorner(Bitmap bitmap, int i, boolean z, boolean z2) {
        if (i <= 0) {
            return bitmap;
        }
        int min;
        int i2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (z) {
            min = Math.min(width, height);
            i2 = min;
        } else {
            min = height;
            i2 = width;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap = Bitmap.createBitmap(i2, min, Config.ARGB_8888);
        if (createBitmap == null) {
            return bitmap;
        }
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) i2, (float) min), (float) i, (float) i, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float) ((i2 - width) / 2), (float) ((min - height) / 2), paint);
        if (z2) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap cut2ScaleSize(Bitmap bitmap, int i, int i2, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i && height == i2) {
            return bitmap;
        }
        int i3;
        int i4;
        int i5;
        float f;
        Matrix matrix = new Matrix();
        float f2 = ((float) i) / ((float) width);
        float f3 = ((float) i2) / ((float) height);
        if (f2 > f3) {
            height = (int) ((((float) height) - (((float) i2) / f2)) / 2.0f);
            i3 = (int) ((((float) height) + (((float) i2) / f2)) / 2.0f);
            i4 = 0;
            i5 = width;
            f = f2;
        } else {
            i3 = (int) ((((float) width) - (((float) i) / f3)) / 2.0f);
            f = f3;
            float f4 = f3;
            i4 = i3;
            i3 = height;
            height = 0;
            i5 = (int) ((((float) width) + (((float) i) / f3)) / 2.0f);
            f2 = f4;
        }
        matrix.setScale(f, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i4, height, i5 - i4, i3 - height, matrix, true);
        if (createBitmap == null) {
            return bitmap;
        }
        if (!z || createBitmap == bitmap) {
            return createBitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap rotate(Bitmap bitmap, int i, boolean z) {
        Bitmap createBitmap;
        if (i != 0) {
            Matrix matrix = new Matrix();
            matrix.setRotate((float) i);
            try {
                createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
            if (createBitmap != null) {
                return bitmap;
            }
            if (z || createBitmap == bitmap) {
                return createBitmap;
            }
            bitmap.recycle();
            return createBitmap;
        }
        createBitmap = null;
        if (createBitmap != null) {
            return bitmap;
        }
        if (z) {
        }
        return createBitmap;
    }

    public static int getRotateAngle(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 0)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            return 0;
        }
    }

    private static void b(File file, ImageOptions imageOptions, Bitmap bitmap) {
        Closeable createDiskCacheFile;
        Closeable fileOutputStream;
        Throwable th;
        Throwable th2;
        Closeable closeable = null;
        DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
        diskCacheEntity.setKey(file.getAbsolutePath() + "@" + file.lastModified() + imageOptions.toString());
        try {
            createDiskCacheFile = g.createDiskCacheFile(diskCacheEntity);
            if (createDiskCacheFile != null) {
                try {
                    fileOutputStream = new FileOutputStream(createDiskCacheFile);
                    try {
                        bitmap.compress(h ? CompressFormat.WEBP : CompressFormat.PNG, 80, fileOutputStream);
                        fileOutputStream.flush();
                        closeable = createDiskCacheFile.commit();
                    } catch (Throwable th3) {
                        th = th3;
                        closeable = fileOutputStream;
                        th2 = th;
                        IOUtil.closeQuietly(createDiskCacheFile);
                        IOUtil.closeQuietly(closeable);
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    IOUtil.deleteFileOrDir(createDiskCacheFile);
                    LogUtil.w(th2.getMessage(), th2);
                    IOUtil.closeQuietly(createDiskCacheFile);
                    IOUtil.closeQuietly(closeable);
                }
            }
            fileOutputStream = null;
            closeable = createDiskCacheFile;
            IOUtil.closeQuietly(closeable);
            IOUtil.closeQuietly(fileOutputStream);
        } catch (Throwable th5) {
            th2 = th5;
            createDiskCacheFile = null;
            IOUtil.closeQuietly(createDiskCacheFile);
            IOUtil.closeQuietly(closeable);
            throw th2;
        }
    }

    private static Bitmap a(File file, ImageOptions imageOptions) {
        Throwable th;
        Throwable th2;
        Bitmap bitmap = null;
        Closeable diskCacheFile;
        try {
            diskCacheFile = g.getDiskCacheFile(file.getAbsolutePath() + "@" + file.lastModified() + imageOptions.toString());
            if (diskCacheFile != null) {
                try {
                    if (diskCacheFile.exists()) {
                        Options options = new Options();
                        options.inJustDecodeBounds = false;
                        options.inPurgeable = true;
                        options.inInputShareable = true;
                        options.inPreferredConfig = Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeFile(diskCacheFile.getAbsolutePath(), options);
                        IOUtil.closeQuietly(diskCacheFile);
                        return bitmap;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        LogUtil.w(th.getMessage(), th);
                        IOUtil.closeQuietly(diskCacheFile);
                        return bitmap;
                    } catch (Throwable th4) {
                        th2 = th4;
                        IOUtil.closeQuietly(diskCacheFile);
                        throw th2;
                    }
                }
            }
            IOUtil.closeQuietly(diskCacheFile);
        } catch (Throwable th5) {
            diskCacheFile = null;
            th2 = th5;
            IOUtil.closeQuietly(diskCacheFile);
            throw th2;
        }
        return bitmap;
    }
}
