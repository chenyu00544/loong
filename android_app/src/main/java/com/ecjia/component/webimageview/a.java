package com.ecjia.component.webimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ECJiaWebImageCache */
public class a {
    private static final String a = a.class.getSimpleName();
    private static boolean b = true;
    private static boolean c = true;
    private static int d = 86400;
    private Map<String, SoftReference<Bitmap>> e = new HashMap();

    public static void a(boolean z) {
        b = z;
        Log.v(a, "Memory cache " + (z ? "enabled" : "disabled") + ".");
    }

    public static void b(boolean z) {
        c = z;
        Log.v(a, "Disk cache " + (z ? "enabled" : "disabled") + ".");
    }

    public static void a(int i) {
        d = i;
        Log.v(a, "Disk cache timeout set to " + i + " seconds.");
    }

    public Bitmap a(String str) {
        if (b) {
            synchronized (this.e) {
                SoftReference softReference = (SoftReference) this.e.get(str);
                if (softReference != null) {
                    Bitmap bitmap = (Bitmap) softReference.get();
                    if (bitmap == null) {
                        this.e.remove(str);
                        Log.v(a, "Expiring memory cache for URL " + str + ".");
                    } else {
                        Log.v(a, "Retrieved " + str + " from memory cache.");
                        return bitmap;
                    }
                }
            }
        }
        return null;
    }

    public Bitmap a(Context context, String str, int i) {
        InputStream fileInputStream;
        Exception e;
        Throwable th;
        Bitmap bitmap = null;
        if (c) {
            File cacheDir = context.getCacheDir();
            String b = b(str);
            if (i < 0) {
                i = d;
            }
            File file = new File(cacheDir, b);
            if (file.exists() && file.canRead()) {
                if (file.lastModified() + (((long) i) * 1000) < new Date().getTime()) {
                    Log.v(a, "Expiring disk cache (TO: " + i + "s) for URL " + str);
                    file.delete();
                } else {
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            bitmap = BitmapFactory.decodeStream(fileInputStream);
                            Log.v(a, "Retrieved " + str + " from disk cache (TO: " + i + "s).");
                            try {
                                fileInputStream.close();
                            } catch (Exception e2) {
                            }
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                Log.e(a, "Could not retrieve " + str + " from disk cache: " + e.toString());
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                }
                                return bitmap;
                            } catch (Throwable th2) {
                                th = th2;
                                try {
                                    fileInputStream.close();
                                } catch (Exception e5) {
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e6) {
                        e = e6;
                        fileInputStream = null;
                        Log.e(a, "Could not retrieve " + str + " from disk cache: " + e.toString());
                        fileInputStream.close();
                        return bitmap;
                    } catch (Throwable th3) {
                        fileInputStream = null;
                        th = th3;
                        fileInputStream.close();
                        throw th;
                    }
                }
            }
        }
        return bitmap;
    }

    public void a(String str, Bitmap bitmap) {
        if (b) {
            synchronized (this.e) {
                this.e.put(str, new SoftReference(bitmap));
            }
        }
    }

    public void a(Context context, String str, Bitmap bitmap) {
        OutputStream fileOutputStream;
        Exception e;
        Throwable th;
        a(str, bitmap);
        if (c) {
            try {
                fileOutputStream = new FileOutputStream(new File(context.getCacheDir(), b(str)).getAbsolutePath());
                try {
                    bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.e(a, "Could not store " + str + " to disk cache: " + e.toString());
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            fileOutputStream.close();
                        } catch (Exception e5) {
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileOutputStream = null;
                Log.e(a, "Could not store " + str + " to disk cache: " + e.toString());
                fileOutputStream.close();
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                fileOutputStream.close();
                throw th;
            }
        }
    }

    private String b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("ECJiaMD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str.replaceAll("[^A-Za-z0-9]", "#");
        }
    }
}
