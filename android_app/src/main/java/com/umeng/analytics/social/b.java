package com.umeng.analytics.social;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.umeng.analytics.pro.bw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/* compiled from: UMNetwork */
public abstract class b {
    protected static String a(String str) {
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        int nextInt = new Random().nextInt(1000);
        HttpURLConnection httpURLConnection2;
        try {
            String property = System.getProperty("line.separator");
            if (str.length() <= 1) {
                bw.e(nextInt + ":  Invalid baseUrl.");
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return httpURLConnection;
            }
            httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setConnectTimeout(10000);
                httpURLConnection2.setReadTimeout(BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT);
                httpURLConnection2.setRequestMethod("GET");
                if (Integer.parseInt(VERSION.SDK) < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                bw.c(nextInt + ": GET_URL: " + str);
                if (httpURLConnection2.getResponseCode() == 200) {
                    InputStream gZIPInputStream;
                    InputStream inputStream = httpURLConnection2.getInputStream();
                    Object headerField = httpURLConnection2.getHeaderField("Content-Encoding");
                    if (!TextUtils.isEmpty(headerField) && headerField.equalsIgnoreCase("gzip")) {
                        bw.c(nextInt + "  Use GZIPInputStream get data....");
                        gZIPInputStream = new GZIPInputStream(inputStream);
                    } else if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("deflate")) {
                        gZIPInputStream = inputStream;
                    } else {
                        bw.c(nextInt + "  Use InflaterInputStream get data....");
                        gZIPInputStream = new InflaterInputStream(inputStream);
                    }
                    String a = a(gZIPInputStream);
                    bw.c(nextInt + ":  response: " + property + a);
                    if (a == null) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return httpURLConnection;
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return a;
                }
                bw.c(nextInt + ":  Failed to get message." + str);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return httpURLConnection;
            } catch (Exception e) {
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return httpURLConnection;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e2) {
            httpURLConnection2 = httpURLConnection;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            return httpURLConnection;
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8192);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine + "\n");
            } catch (IOException e) {
                return null;
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    return null;
                }
            }
        }
        return stringBuilder.toString();
    }

    protected static String a(String str, String str2) {
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        int nextInt = new Random().nextInt(1000);
        HttpURLConnection httpURLConnection2;
        try {
            String property = System.getProperty("line.separator");
            httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setConnectTimeout(10000);
                httpURLConnection2.setReadTimeout(BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setRequestMethod("POST");
                bw.c(nextInt + ": POST_URL: " + str);
                if (Integer.parseInt(VERSION.SDK) < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                if (!TextUtils.isEmpty(str2)) {
                    bw.c(nextInt + ": POST_BODY: " + str2);
                    List arrayList = new ArrayList();
                    arrayList.add("data=" + str2);
                    OutputStream outputStream = httpURLConnection2.getOutputStream();
                    outputStream.write(URLEncoder.encode(arrayList.toString()).getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
                if (httpURLConnection2.getResponseCode() == 200) {
                    InputStream inputStream;
                    InputStream inputStream2 = httpURLConnection2.getInputStream();
                    Object headerField = httpURLConnection2.getHeaderField("Content-Encoding");
                    if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("gzip")) {
                        inputStream = inputStream2;
                    } else {
                        inputStream = new InflaterInputStream(inputStream2);
                    }
                    String a = a(inputStream);
                    bw.c(nextInt + ":  response: " + property + a);
                    if (a == null) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return null;
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return a;
                }
                bw.e(nextInt + ":  Failed to send message." + str);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return null;
            } catch (Exception e) {
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return null;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e2) {
            httpURLConnection2 = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
