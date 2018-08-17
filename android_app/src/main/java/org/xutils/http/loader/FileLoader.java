package org.xutils.http.loader;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Arrays;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.DiskCacheFile;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.request.UriRequest;

public class FileLoader extends Loader<File> {
    private String a;
    private String b;
    private boolean c;
    private boolean d;
    private long e;
    private String f;
    private DiskCacheFile g;

    public Loader<File> newInstance() {
        return new FileLoader();
    }

    public void setParams(RequestParams requestParams) {
        if (requestParams != null) {
            this.params = requestParams;
            this.c = requestParams.isAutoResume();
            this.d = requestParams.isAutoRename();
        }
    }

    public File load(InputStream inputStream) throws Throwable {
        long length;
        Closeable fileInputStream;
        Throwable th;
        OutputStream fileOutputStream;
        Closeable closeable = null;
        File file = new File(this.a);
        if (file.isDirectory()) {
            IOUtil.deleteFileOrDir(file);
        }
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!(parentFile.exists() || parentFile.mkdirs())) {
                throw new IOException("can not create dir: " + parentFile.getAbsolutePath());
            }
        }
        try {
            length = file.length();
            if (this.c && length > 0) {
                long j = length - 512;
                if (j > 0) {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = null;
                        IOUtil.closeQuietly(fileInputStream);
                        throw th;
                    }
                    try {
                        if (Arrays.equals(IOUtil.readBytes(inputStream, 0, 512), IOUtil.readBytes(fileInputStream, j, 512))) {
                            this.e -= 512;
                            IOUtil.closeQuietly(fileInputStream);
                        } else {
                            IOUtil.closeQuietly(fileInputStream);
                            IOUtil.deleteFileOrDir(file);
                            throw new RuntimeException("need retry");
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        IOUtil.closeQuietly(fileInputStream);
                        throw th;
                    }
                }
                IOUtil.deleteFileOrDir(file);
                throw new RuntimeException("need retry");
            }
            if (this.c) {
                fileOutputStream = new FileOutputStream(file, true);
            } else {
                fileOutputStream = new FileOutputStream(file);
                length = 0;
            }
            long j2 = this.e + length;
            Closeable bufferedInputStream = new BufferedInputStream(inputStream);
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            IOUtil.closeQuietly(fileInputStream);
            IOUtil.closeQuietly(closeable);
            throw th;
        }
        try {
            Closeable bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = bufferedInputStream;
            IOUtil.closeQuietly(fileInputStream);
            IOUtil.closeQuietly(closeable);
            throw th;
        }
        try {
            if (this.progressHandler == null || this.progressHandler.updateProgress(j2, length, true)) {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    } else if (file.getParentFile().exists()) {
                        bufferedOutputStream.write(bArr, 0, read);
                        length += (long) read;
                        if (this.progressHandler != null && !this.progressHandler.updateProgress(j2, length, false)) {
                            bufferedOutputStream.flush();
                            throw new CancelledException("download stopped!");
                        }
                    } else {
                        file.getParentFile().mkdirs();
                        throw new IOException("parent be deleted!");
                    }
                }
                bufferedOutputStream.flush();
                if (this.g != null) {
                    file = this.g.commit();
                }
                if (this.progressHandler != null) {
                    this.progressHandler.updateProgress(j2, length, true);
                }
                IOUtil.closeQuietly(bufferedInputStream);
                IOUtil.closeQuietly(bufferedOutputStream);
                return a(file);
            }
            throw new CancelledException("download stopped!");
        } catch (Throwable th6) {
            th = th6;
            closeable = bufferedOutputStream;
            fileInputStream = bufferedInputStream;
            IOUtil.closeQuietly(fileInputStream);
            IOUtil.closeQuietly(closeable);
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File load(org.xutils.http.request.UriRequest r13) throws java.lang.Throwable {
        /*
        r12 = this;
        r10 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r7 = 0;
        r8 = 0;
        r0 = r12.params;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0 = r0.getSaveFilePath();	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r12.b = r0;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0 = 0;
        r12.g = r0;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0 = r12.b;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        if (r0 == 0) goto L_0x008a;
    L_0x0018:
        r0 = r12.progressHandler;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        if (r0 == 0) goto L_0x0064;
    L_0x001c:
        r1 = r12.progressHandler;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r2 = 0;
        r4 = 0;
        r6 = 0;
        r0 = r1.updateProgress(r2, r4, r6);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        if (r0 != 0) goto L_0x0064;
    L_0x0029:
        r0 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r1 = "download stopped!";
        r0.<init>(r1);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        throw r0;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
    L_0x0031:
        r0 = move-exception;
        r1 = r7;
    L_0x0033:
        r2 = r0.getCode();	 Catch:{ all -> 0x01b8 }
        r3 = 416; // 0x1a0 float:5.83E-43 double:2.055E-321;
        if (r2 != r3) goto L_0x01dc;
    L_0x003b:
        r0 = r12.g;	 Catch:{ all -> 0x01b8 }
        if (r0 == 0) goto L_0x01af;
    L_0x003f:
        r0 = r12.g;	 Catch:{ all -> 0x01b8 }
        r0 = r0.commit();	 Catch:{ all -> 0x01b8 }
    L_0x0045:
        if (r0 == 0) goto L_0x01bc;
    L_0x0047:
        r2 = r0.exists();	 Catch:{ all -> 0x01b8 }
        if (r2 == 0) goto L_0x01bc;
    L_0x004d:
        r2 = r12.d;	 Catch:{ all -> 0x01b8 }
        if (r2 == 0) goto L_0x0057;
    L_0x0051:
        r2 = b(r13);	 Catch:{ all -> 0x01b8 }
        r12.f = r2;	 Catch:{ all -> 0x01b8 }
    L_0x0057:
        r0 = r12.a(r0);	 Catch:{ all -> 0x01b8 }
        org.xutils.common.util.IOUtil.closeQuietly(r1);
        r1 = r12.g;
        org.xutils.common.util.IOUtil.closeQuietly(r1);
    L_0x0063:
        return r0;
    L_0x0064:
        r12.a(r13);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
    L_0x0067:
        r0 = r12.progressHandler;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        if (r0 == 0) goto L_0x00a2;
    L_0x006b:
        r1 = r12.progressHandler;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r2 = 0;
        r4 = 0;
        r6 = 0;
        r0 = r1.updateProgress(r2, r4, r6);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        if (r0 != 0) goto L_0x00a2;
    L_0x0078:
        r0 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r1 = "download stopped!";
        r0.<init>(r1);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        throw r0;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
    L_0x0080:
        r0 = move-exception;
    L_0x0081:
        org.xutils.common.util.IOUtil.closeQuietly(r7);
        r1 = r12.g;
        org.xutils.common.util.IOUtil.closeQuietly(r1);
        throw r0;
    L_0x008a:
        r0 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0.<init>();	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r1 = r12.b;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0 = r0.append(r1);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r1 = ".tmp";
        r0 = r0.append(r1);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0 = r0.toString();	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r12.a = r0;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        goto L_0x0067;
    L_0x00a2:
        r0 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0.<init>();	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r1 = r12.b;	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0 = r0.append(r1);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r1 = "_lock";
        r0 = r0.append(r1);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r0 = r0.toString();	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        r1 = 1;
        r7 = org.xutils.common.util.ProcessLock.tryLock(r0, r1);	 Catch:{ HttpException -> 0x0031, all -> 0x0080 }
        if (r7 == 0) goto L_0x00c4;
    L_0x00be:
        r0 = r7.isValid();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 != 0) goto L_0x00e3;
    L_0x00c4:
        r0 = new org.xutils.ex.FileLockedException;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1.<init>();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2 = "download exists: ";
        r1 = r1.append(r2);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2 = r12.b;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = r1.append(r2);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = r1.toString();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0.<init>(r1);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        throw r0;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
    L_0x00df:
        r0 = move-exception;
        r1 = r7;
        goto L_0x0033;
    L_0x00e3:
        r0 = r13.getParams();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r12.params = r0;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r12.c;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 == 0) goto L_0x01dd;
    L_0x00ed:
        r0 = new java.io.File;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = r12.a;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0.<init>(r1);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2 = r0.length();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r1 > 0) goto L_0x0139;
    L_0x00fc:
        org.xutils.common.util.IOUtil.deleteFileOrDir(r0);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r8;
    L_0x0100:
        r2 = r12.params;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r3 = "RANGE";
        r4 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r4.<init>();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r5 = "bytes=";
        r4 = r4.append(r5);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r4.append(r0);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = "-";
        r0 = r0.append(r1);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r0.toString();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2.setHeader(r3, r0);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r12.progressHandler;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 == 0) goto L_0x013c;
    L_0x0124:
        r1 = r12.progressHandler;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2 = 0;
        r4 = 0;
        r6 = 0;
        r0 = r1.updateProgress(r2, r4, r6);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 != 0) goto L_0x013c;
    L_0x0131:
        r0 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = "download stopped!";
        r0.<init>(r1);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        throw r0;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
    L_0x0139:
        r0 = r2 - r10;
        goto L_0x0100;
    L_0x013c:
        r13.sendRequest();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r13.getContentLength();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r12.e = r0;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r12.d;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 == 0) goto L_0x014f;
    L_0x0149:
        r0 = b(r13);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r12.f = r0;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
    L_0x014f:
        r0 = r12.c;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 == 0) goto L_0x0159;
    L_0x0153:
        r0 = c(r13);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r12.c = r0;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
    L_0x0159:
        r0 = r12.progressHandler;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 == 0) goto L_0x0172;
    L_0x015d:
        r1 = r12.progressHandler;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2 = 0;
        r4 = 0;
        r6 = 0;
        r0 = r1.updateProgress(r2, r4, r6);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 != 0) goto L_0x0172;
    L_0x016a:
        r0 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = "download stopped!";
        r0.<init>(r1);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        throw r0;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
    L_0x0172:
        r0 = r12.g;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        if (r0 == 0) goto L_0x019d;
    L_0x0176:
        r0 = r12.g;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r0.getCacheEntity();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0.setLastAccess(r2);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = r13.getETag();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0.setEtag(r1);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2 = r13.getExpiration();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0.setExpires(r2);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1 = new java.util.Date;	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r2 = r13.getLastModified();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r1.<init>(r2);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0.setLastModify(r1);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
    L_0x019d:
        r0 = r13.getInputStream();	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        r0 = r12.load(r0);	 Catch:{ HttpException -> 0x00df, all -> 0x0080 }
        org.xutils.common.util.IOUtil.closeQuietly(r7);
        r1 = r12.g;
        org.xutils.common.util.IOUtil.closeQuietly(r1);
        goto L_0x0063;
    L_0x01af:
        r0 = new java.io.File;	 Catch:{ all -> 0x01b8 }
        r2 = r12.a;	 Catch:{ all -> 0x01b8 }
        r0.<init>(r2);	 Catch:{ all -> 0x01b8 }
        goto L_0x0045;
    L_0x01b8:
        r0 = move-exception;
        r7 = r1;
        goto L_0x0081;
    L_0x01bc:
        org.xutils.common.util.IOUtil.deleteFileOrDir(r0);	 Catch:{ all -> 0x01b8 }
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x01b8 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01b8 }
        r2.<init>();	 Catch:{ all -> 0x01b8 }
        r3 = "cache file not found";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01b8 }
        r3 = r13.getCacheKey();	 Catch:{ all -> 0x01b8 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x01b8 }
        r2 = r2.toString();	 Catch:{ all -> 0x01b8 }
        r0.<init>(r2);	 Catch:{ all -> 0x01b8 }
        throw r0;	 Catch:{ all -> 0x01b8 }
    L_0x01dc:
        throw r0;	 Catch:{ all -> 0x01b8 }
    L_0x01dd:
        r0 = r8;
        goto L_0x0100;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.http.loader.FileLoader.load(org.xutils.http.request.UriRequest):java.io.File");
    }

    private void a(UriRequest uriRequest) throws Throwable {
        DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
        diskCacheEntity.setKey(uriRequest.getCacheKey());
        this.g = LruDiskCache.getDiskCache(this.params.getCacheDirName()).createDiskCacheFile(diskCacheEntity);
        if (this.g != null) {
            this.b = this.g.getAbsolutePath();
            this.a = this.b;
            this.d = false;
            return;
        }
        throw new IOException("create cache file error:" + uriRequest.getCacheKey());
    }

    private File a(File file) {
        File file2;
        if (this.d && file.exists() && !TextUtils.isEmpty(this.f)) {
            file2 = new File(file.getParent(), this.f);
            while (file2.exists()) {
                file2 = new File(file.getParent(), System.currentTimeMillis() + this.f);
            }
            if (!file.renameTo(file2)) {
                file2 = file;
            }
            return file2;
        } else if (this.b.equals(this.a)) {
            return file;
        } else {
            file2 = new File(this.b);
            if (file.renameTo(file2)) {
                return file2;
            }
            return file;
        }
    }

    private static String b(UriRequest uriRequest) {
        if (uriRequest == null) {
            return null;
        }
        String responseHeader = uriRequest.getResponseHeader("Content-Disposition");
        if (!TextUtils.isEmpty(responseHeader)) {
            int indexOf = responseHeader.indexOf("filename=");
            if (indexOf > 0) {
                int i = indexOf + 9;
                indexOf = responseHeader.indexOf(";", i);
                if (indexOf < 0) {
                    indexOf = responseHeader.length();
                }
                if (indexOf > i) {
                    try {
                        String decode = URLDecoder.decode(responseHeader.substring(i, indexOf), uriRequest.getParams().getCharset());
                        if (decode.startsWith("\"") && decode.endsWith("\"")) {
                            return decode.substring(1, decode.length() - 1);
                        }
                        return decode;
                    } catch (Throwable e) {
                        LogUtil.e(e.getMessage(), e);
                    }
                }
            }
        }
        return null;
    }

    private static boolean c(UriRequest uriRequest) {
        if (uriRequest == null) {
            return false;
        }
        String responseHeader = uriRequest.getResponseHeader("Accept-Ranges");
        if (responseHeader != null) {
            return responseHeader.contains("bytes");
        }
        responseHeader = uriRequest.getResponseHeader("Content-Range");
        if (responseHeader == null || !responseHeader.contains("bytes")) {
            return false;
        }
        return true;
    }

    public File loadFromCache(DiskCacheEntity diskCacheEntity) throws Throwable {
        return LruDiskCache.getDiskCache(this.params.getCacheDirName()).getDiskCacheFile(diskCacheEntity.getKey());
    }

    public void save2Cache(UriRequest uriRequest) {
    }
}
