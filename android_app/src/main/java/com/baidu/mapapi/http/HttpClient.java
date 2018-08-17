package com.baidu.mapapi.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.baidu.platform.comapi.util.f;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    HttpURLConnection a;
    private Context b;
    private String c = null;
    private String d = null;
    private int e;
    private int f;
    private String g;
    private ProtoResultCallback h;

    public enum HttpStateError {
        NETWORK_ERROR,
        INNER_ERROR
    }

    public static abstract class ProtoResultCallback {
        public abstract void onFailed(HttpStateError httpStateError);

        public abstract void onSuccess(String str);
    }

    public HttpClient(Context context, String str, ProtoResultCallback protoResultCallback) {
        this.b = context;
        this.g = str;
        this.h = protoResultCallback;
    }

    private HttpURLConnection a() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.c).openConnection();
            httpURLConnection.setRequestMethod(this.g);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(this.e);
            httpURLConnection.setReadTimeout(this.f);
            return httpURLConnection;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getAuthToken() {
        return f.A;
    }

    public static String getPhoneInfo() {
        return f.c();
    }

    protected boolean checkNetwork() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.b.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void request(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Throwable th;
        Throwable th2;
        InputStream inputStream = null;
        this.c = str;
        if (checkNetwork()) {
            this.a = a();
            if (this.a == null) {
                this.h.onFailed(HttpStateError.INNER_ERROR);
            } else if (TextUtils.isEmpty(this.c)) {
                this.h.onFailed(HttpStateError.INNER_ERROR);
            } else {
                try {
                    this.a.connect();
                    InputStream inputStream2;
                    try {
                        inputStream2 = this.a.getInputStream();
                        try {
                            if (200 == this.a.getResponseCode()) {
                                bufferedReader = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));
                                try {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    while (true) {
                                        int read = bufferedReader.read();
                                        if (read == -1) {
                                            break;
                                        }
                                        stringBuffer.append((char) read);
                                    }
                                    this.d = stringBuffer.toString();
                                } catch (Exception e) {
                                    bufferedReader2 = bufferedReader;
                                    try {
                                        this.h.onFailed(HttpStateError.INNER_ERROR);
                                        bufferedReader2.close();
                                        inputStream2.close();
                                        if (this.a == null) {
                                            this.a.disconnect();
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        bufferedReader = bufferedReader2;
                                        inputStream = inputStream2;
                                        th2 = th;
                                        bufferedReader.close();
                                        inputStream.close();
                                        if (this.a != null) {
                                            this.a.disconnect();
                                        }
                                        throw th2;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    inputStream = inputStream2;
                                    th2 = th;
                                    bufferedReader.close();
                                    inputStream.close();
                                    if (this.a != null) {
                                        this.a.disconnect();
                                    }
                                    throw th2;
                                }
                            }
                            bufferedReader = null;
                            if (!(inputStream2 == null || bufferedReader == null)) {
                                bufferedReader.close();
                                inputStream2.close();
                            }
                            if (this.a != null) {
                                this.a.disconnect();
                            }
                            this.h.onSuccess(this.d);
                        } catch (Exception e2) {
                            this.h.onFailed(HttpStateError.INNER_ERROR);
                            bufferedReader2.close();
                            inputStream2.close();
                            if (this.a == null) {
                                this.a.disconnect();
                            }
                        } catch (Throwable th32) {
                            th = th32;
                            bufferedReader = null;
                            inputStream = inputStream2;
                            th2 = th;
                            bufferedReader.close();
                            inputStream.close();
                            if (this.a != null) {
                                this.a.disconnect();
                            }
                            throw th2;
                        }
                    } catch (Exception e3) {
                        inputStream2 = null;
                        this.h.onFailed(HttpStateError.INNER_ERROR);
                        if (!(inputStream2 == null || bufferedReader2 == null)) {
                            bufferedReader2.close();
                            inputStream2.close();
                        }
                        if (this.a == null) {
                            this.a.disconnect();
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        bufferedReader = null;
                        if (!(inputStream == null || bufferedReader == null)) {
                            bufferedReader.close();
                            inputStream.close();
                        }
                        if (this.a != null) {
                            this.a.disconnect();
                        }
                        throw th2;
                    }
                } catch (Exception e4) {
                }
            }
        } else {
            this.h.onFailed(HttpStateError.NETWORK_ERROR);
        }
    }

    public void setMaxTimeOut(int i) {
        this.e = i;
    }

    public void setReadTimeOut(int i) {
        this.f = i;
    }
}
