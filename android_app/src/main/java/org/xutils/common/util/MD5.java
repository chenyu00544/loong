package org.xutils.common.util;

import anet.channel.security.ISecurity;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5 {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private MD5() {
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            stringBuilder.append(a[(b >> 4) & 15]);
            stringBuilder.append(a[b & 15]);
        }
        return stringBuilder.toString();
    }

    public static String md5(File file) throws IOException {
        Closeable fileInputStream;
        Closeable channel;
        Throwable e;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            fileInputStream = new FileInputStream(file);
            try {
                channel = fileInputStream.getChannel();
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                closeable = fileInputStream;
                try {
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    e = th;
                    fileInputStream = closeable;
                    IOUtil.closeQuietly(fileInputStream);
                    IOUtil.closeQuietly(closeable2);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                IOUtil.closeQuietly(fileInputStream);
                IOUtil.closeQuietly(closeable2);
                throw e;
            }
            try {
                instance.update(channel.map(MapMode.READ_ONLY, 0, file.length()));
                byte[] digest = instance.digest();
                IOUtil.closeQuietly(fileInputStream);
                IOUtil.closeQuietly(channel);
                return toHexString(digest);
            } catch (Throwable e3) {
                closeable = fileInputStream;
                Closeable closeable3 = channel;
                e = e3;
                closeable2 = closeable3;
                throw new RuntimeException(e);
            } catch (Throwable e32) {
                Throwable th3 = e32;
                closeable2 = channel;
                e = th3;
                IOUtil.closeQuietly(fileInputStream);
                IOUtil.closeQuietly(closeable2);
                throw e;
            }
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
            closeable = null;
            throw new RuntimeException(e);
        } catch (Throwable th4) {
            e = th4;
            fileInputStream = null;
            IOUtil.closeQuietly(fileInputStream);
            IOUtil.closeQuietly(closeable2);
            throw e;
        }
    }

    public static String md5(String str) {
        try {
            return toHexString(MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5).digest(str.getBytes("UTF-8")));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }
}
