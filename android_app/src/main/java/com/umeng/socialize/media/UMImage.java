package com.umeng.socialize.media;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.common.ImageFormat;
import com.umeng.socialize.media.UMediaObject.MediaType;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.net.utils.SocializeNetUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.Log;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class UMImage extends BaseMediaObject {
    private ConfiguredConvertor i = null;
    private ConvertConfig j;
    private WeakReference<Bitmap> k = new WeakReference(null);

    interface IImageConvertor {
        byte[] asBinary();

        Bitmap asBitmap();

        File asFile();

        String asUrl();
    }

    static abstract class ConfiguredConvertor implements IImageConvertor {
        public ConvertConfig config = null;

        ConfiguredConvertor() {
        }

        public void setConfig(ConvertConfig convertConfig) {
            this.config = convertConfig;
        }
    }

    static class BinaryConvertor extends ConfiguredConvertor {
        private ConvertConfig a = new ConvertConfig();
        private byte[] b;

        public BinaryConvertor(byte[] bArr) {
            this.b = bArr;
        }

        public File asFile() {
            try {
                return a(this.b, this.a.generateCacheFile(getFileName()));
            } catch (IOException e) {
                Log.e("Sorry cannot setImage..[" + e.toString() + "]");
                return null;
            }
        }

        public String asUrl() {
            return null;
        }

        public byte[] asBinary() {
            return this.b;
        }

        public Bitmap asBitmap() {
            if (this.b != null) {
                return BitmapFactory.decodeByteArray(this.b, 0, this.b.length);
            }
            return null;
        }

        public String getFileName() {
            return AesHelper.md5(String.valueOf(System.currentTimeMillis()));
        }

        private File a(byte[] bArr, File file) {
            BufferedOutputStream bufferedOutputStream;
            Exception e;
            Throwable th;
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                try {
                    bufferedOutputStream.write(bArr);
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        return file;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                bufferedOutputStream = null;
                e.printStackTrace();
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return file;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                throw th;
            }
            return file;
        }
    }

    static class BitmapConvertor extends ConfiguredConvertor {
        private Bitmap a;

        public BitmapConvertor(Bitmap bitmap) {
            this.a = bitmap;
        }

        public File asFile() {
            Exception e;
            Throwable th;
            OutputStream fileOutputStream;
            try {
                long currentTimeMillis = System.currentTimeMillis();
                File generateCacheFile = this.config.generateCacheFile(AesHelper.md5(this.a.toString()));
                fileOutputStream = new FileOutputStream(generateCacheFile);
                try {
                    int rowBytes = (this.a.getRowBytes() * this.a.getHeight()) / 1024;
                    Log.d("### bitmap size = " + rowBytes + " KB");
                    int i = 100;
                    if (((float) rowBytes) > this.config.mImageSizeLimit) {
                        i = (int) (((float) 100) * (this.config.mImageSizeLimit / ((float) rowBytes)));
                    }
                    Log.d("### 压缩质量 : " + i);
                    if (!this.a.isRecycled()) {
                        this.a.compress(CompressFormat.JPEG, i, fileOutputStream);
                    }
                    Log.d("##save bitmap " + generateCacheFile.getAbsolutePath());
                    Log.d("#### 图片序列化耗时 : " + (System.currentTimeMillis() - currentTimeMillis) + " ms.");
                    if (fileOutputStream == null) {
                        return generateCacheFile;
                    }
                    try {
                        fileOutputStream.close();
                        return generateCacheFile;
                    } catch (IOException e2) {
                        return generateCacheFile;
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.e("Sorry cannot setImage..[" + e.toString() + "]");
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileOutputStream = null;
                Log.e("Sorry cannot setImage..[" + e.toString() + "]");
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }

        public String asUrl() {
            return null;
        }

        public byte[] asBinary() {
            return BitmapUtils.bitmap2Bytes(this.a);
        }

        public Bitmap asBitmap() {
            return this.a;
        }
    }

    static class ConvertConfig {
        private static final String a = "/umeng_cache/";
        private String b = "";
        public float mImageSizeLimit = 2048.0f;

        public ConvertConfig(Context context) {
            try {
                this.b = context.getCacheDir().getCanonicalPath();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public File getCache() throws IOException {
            String canonicalPath;
            if (DeviceConfig.isSdCardWrittenable()) {
                canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
            } else if (TextUtils.isEmpty(this.b)) {
                throw new IOException("dirpath is unknow");
            } else {
                canonicalPath = this.b;
            }
            File file = new File(canonicalPath + a);
            if (!(file == null || file.exists())) {
                file.mkdirs();
            }
            return file;
        }

        public File generateCacheFile(String str) throws IOException {
            BitmapUtils.cleanCache();
            File file = new File(getCache(), str);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            return file;
        }
    }

    static class FileConvertor extends ConfiguredConvertor {
        private File a;

        public FileConvertor(File file) {
            this.a = file;
        }

        public File asFile() {
            return this.a;
        }

        public String asUrl() {
            return null;
        }

        public byte[] asBinary() {
            return a(this.a);
        }

        public Bitmap asBitmap() {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.a.toString(), options);
            options.inSampleSize = BitmapUtils.calculateInSampleSize(options, BitmapUtils.MAX_WIDTH, BitmapUtils.MAX_HEIGHT);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(this.a.getAbsolutePath(), options);
        }

        private byte[] a(File file) {
            if (file == null || !file.getAbsoluteFile().exists()) {
                return null;
            }
            byte[] b = b(file);
            if (b == null || b.length <= 0) {
                return null;
            }
            if (ImageFormat.FORMAT_NAMES[1].equals(ImageFormat.checkFormat(b))) {
                return b;
            }
            return UMImage.b(b);
        }

        private static byte[] b(File file) {
            InputStream fileInputStream;
            Exception e;
            Throwable th;
            byte[] bArr = null;
            ByteArrayOutputStream byteArrayOutputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr2 = new byte[4096];
                        while (true) {
                            int read = fileInputStream.read(bArr2);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        }
                        bArr = byteArrayOutputStream.toByteArray();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            return bArr;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e5) {
                                    throw th;
                                }
                            }
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            throw th;
                        }
                    }
                } catch (Exception e6) {
                    e = e6;
                    byteArrayOutputStream = bArr;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    return bArr;
                } catch (Throwable th3) {
                    byteArrayOutputStream = bArr;
                    th = th3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e = e7;
                byteArrayOutputStream = bArr;
                fileInputStream = bArr;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return bArr;
            } catch (Throwable th32) {
                byteArrayOutputStream = bArr;
                fileInputStream = bArr;
                th = th32;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
            return bArr;
        }
    }

    static class ResConvertor extends ConfiguredConvertor {
        private Context a;
        private int b = 0;

        public ResConvertor(Context context, int i) {
            this.a = context;
            this.b = i;
        }

        public File asFile() {
            FileInputStream createInputStream;
            FileOutputStream fileOutputStream;
            IOException e;
            Throwable th;
            FileOutputStream fileOutputStream2 = null;
            try {
                createInputStream = this.a.getResources().openRawResourceFd(this.b).createInputStream();
                try {
                    File generateCacheFile = this.config.generateCacheFile(AesHelper.md5(createInputStream.toString()));
                    fileOutputStream = new FileOutputStream(generateCacheFile);
                    try {
                        byte[] bArr = new byte[4096];
                        while (createInputStream.read(bArr) != -1) {
                            fileOutputStream.write(bArr);
                        }
                        fileOutputStream.flush();
                        if (createInputStream != null) {
                            try {
                                createInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return generateCacheFile;
                            }
                        }
                        if (fileOutputStream == null) {
                            return generateCacheFile;
                        }
                        fileOutputStream.close();
                        return generateCacheFile;
                    } catch (IOException e3) {
                        e = e3;
                        try {
                            e.printStackTrace();
                            if (createInputStream != null) {
                                try {
                                    createInputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    return null;
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream2 = fileOutputStream;
                            if (createInputStream != null) {
                                try {
                                    createInputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                    throw th;
                                }
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            throw th;
                        }
                    }
                } catch (IOException e5) {
                    e4 = e5;
                    fileOutputStream = null;
                    e4.printStackTrace();
                    if (createInputStream != null) {
                        createInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    if (createInputStream != null) {
                        createInputStream.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e4 = e6;
                createInputStream = null;
                fileOutputStream = null;
                e4.printStackTrace();
                if (createInputStream != null) {
                    createInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                createInputStream = null;
                if (createInputStream != null) {
                    createInputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        }

        public String asUrl() {
            return null;
        }

        public byte[] asBinary() {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (Config.isLoadImgByCompress) {
                Options options = new Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                InputStream openRawResource = this.a.getResources().openRawResource(this.b);
                Log.e("xxxxxxxxx1");
                BitmapFactory.decodeStream(openRawResource, null, options).compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                Log.e("xxxxxxxxx2");
                return byteArrayOutputStream.toByteArray();
            }
            Drawable drawable;
            Resources resources = this.a.getResources();
            if (VERSION.SDK_INT >= 21) {
                drawable = resources.getDrawable(this.b, null);
            } else {
                drawable = resources.getDrawable(this.b);
            }
            UMImage.a(drawable).compress(CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        public Bitmap asBitmap() {
            if (!Config.isLoadImgByCompress) {
                return BitmapFactory.decodeResource(this.a.getResources(), this.b);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Options options = new Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeStream(this.a.getResources().openRawResource(this.b), null, options);
        }
    }

    static class UrlConvertor extends ConfiguredConvertor {
        private String a = null;

        public UrlConvertor(String str) {
            this.a = str;
        }

        public File asFile() {
            File generateCacheFile;
            Exception e;
            try {
                generateCacheFile = this.config.generateCacheFile(AesHelper.md5(this.a));
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(generateCacheFile);
                    fileOutputStream.write(asBinary());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return generateCacheFile;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                generateCacheFile = null;
                e = exception;
                e.printStackTrace();
                return generateCacheFile;
            }
            return generateCacheFile;
        }

        public String asUrl() {
            return this.a;
        }

        public byte[] asBinary() {
            return SocializeNetUtils.getNetData(this.a);
        }

        public Bitmap asBitmap() {
            byte[] asBinary = asBinary();
            if (asBinary != null) {
                return BitmapFactory.decodeByteArray(asBinary, 0, asBinary.length);
            }
            return null;
        }
    }

    public UMImage(Context context, File file) {
        a(context, file);
    }

    public UMImage(Context context, String str) {
        super(str);
        a((Context) new WeakReference(context).get(), str);
    }

    public UMImage(Context context, int i) {
        a(context, Integer.valueOf(i));
    }

    public UMImage(Context context, byte[] bArr) {
        a(context, bArr);
    }

    public UMImage(Context context, Bitmap bitmap) {
        a(context, bitmap);
    }

    private void a(Context context, Object obj) {
        if (obj instanceof File) {
            this.i = new FileConvertor((File) obj);
        } else if (obj instanceof String) {
            this.i = new UrlConvertor((String) obj);
        } else if (obj instanceof Integer) {
            this.i = new ResConvertor(context, ((Integer) obj).intValue());
        } else if (obj instanceof byte[]) {
            this.i = new BinaryConvertor((byte[]) obj);
        } else if (obj instanceof Bitmap) {
            this.i = new BitmapConvertor((Bitmap) obj);
        } else {
            throw new RuntimeException("Don't support type");
        }
        this.i.setConfig(new ConvertConfig(context));
    }

    public byte[] toByte() {
        return asBinImage();
    }

    public final Map<String, Object> toUrlExtraParams() {
        Map<String, Object> hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
        }
        return hashMap;
    }

    private static byte[] b(byte[] bArr) {
        Throwable th;
        byte[] bArr2 = null;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, BitmapUtils.getBitmapOptions(bArr));
            byteArrayOutputStream = new ByteArrayOutputStream();
            if (decodeByteArray != null) {
                try {
                    decodeByteArray.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                    decodeByteArray.recycle();
                    System.gc();
                } catch (Exception e) {
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                    return bArr2;
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    throw th;
                }
            }
            bArr2 = byteArrayOutputStream.toByteArray();
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e4) {
                }
            }
        } catch (Exception e5) {
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return bArr2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            byteArrayOutputStream = null;
            th = th4;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
        return bArr2;
    }

    public MediaType getMediaType() {
        return MediaType.IMAGE;
    }

    public void resize(int i, int i2) {
    }

    public boolean isMultiMedia() {
        return true;
    }

    public File asFileImage() {
        Log.e("xxxxx convor=" + this.i.asFile());
        return this.i == null ? null : this.i.asFile();
    }

    public String asUrlImage() {
        return this.i == null ? null : this.i.asUrl();
    }

    public byte[] asBinImage() {
        return this.i == null ? null : this.i.asBinary();
    }

    public Bitmap asBitmap() {
        return this.i == null ? null : this.i.asBitmap();
    }

    static Bitmap a(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }
}
