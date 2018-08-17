package com.baidu.lbsapi.auth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.taobao.accs.utl.UtilityImpl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;

public class g {
    private Context a;
    private String b = null;
    private HashMap<String, String> c = null;
    private String d = null;

    public g(Context context) {
        this.a = context;
    }

    private String a(Context context) {
        String str = UtilityImpl.NET_TYPE_WIFI;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            return (extraInfo == null || !(extraInfo.trim().toLowerCase().equals("cmwap") || extraInfo.trim().toLowerCase().equals("uniwap") || extraInfo.trim().toLowerCase().equals("3gwap") || extraInfo.trim().toLowerCase().equals("ctwap"))) ? str : extraInfo.trim().toLowerCase().equals("ctwap") ? "ctwap" : "cmwap";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void a(HttpsURLConnection httpsURLConnection) {
        OutputStream outputStream;
        InputStream inputStream;
        BufferedReader bufferedReader;
        IOException e;
        BufferedReader bufferedReader2;
        int i;
        Throwable th;
        MalformedURLException e2;
        int i2 = -1;
        OutputStream outputStream2 = null;
        a.a("httpsPost start,url:" + this.b);
        if (this.c == null) {
            this.d = ErrorMessage.a("httpsPost request paramters is null.");
            return;
        }
        Object obj = 1;
        try {
            outputStream = httpsURLConnection.getOutputStream();
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(b(this.c));
                a.a(b(this.c));
                bufferedWriter.flush();
                bufferedWriter.close();
                httpsURLConnection.connect();
                try {
                    inputStream = httpsURLConnection.getInputStream();
                    try {
                        int responseCode = httpsURLConnection.getResponseCode();
                        if (200 == responseCode) {
                            try {
                                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
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
                                } catch (IOException e3) {
                                    e = e3;
                                    bufferedReader2 = bufferedReader;
                                    i = responseCode;
                                    try {
                                        a.a("httpsPost parse failed;" + e.getMessage());
                                        this.d = ErrorMessage.a("httpsPost failed,Exception:" + e.getMessage());
                                        if (obj == null) {
                                        }
                                        if (this.d == null) {
                                            a.a("httpsPost failed,mResult is null");
                                            this.d = ErrorMessage.a("httpsPost failed,internal error");
                                        }
                                        a.a("httpsPost success end,parse result = " + this.d);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (!(inputStream == null || bufferedReader2 == null)) {
                                            bufferedReader2.close();
                                            inputStream.close();
                                        }
                                        if (httpsURLConnection != null) {
                                            httpsURLConnection.disconnect();
                                        }
                                        throw th;
                                    }
                                    try {
                                        bufferedReader2.close();
                                        inputStream.close();
                                        if (httpsURLConnection == null) {
                                            obj = null;
                                            i2 = i;
                                        } else {
                                            httpsURLConnection.disconnect();
                                            obj = null;
                                            i2 = i;
                                        }
                                        if (outputStream != null) {
                                            try {
                                                outputStream.close();
                                            } catch (IOException e4) {
                                                e4.printStackTrace();
                                            }
                                        }
                                    } catch (MalformedURLException e5) {
                                        e2 = e5;
                                        i2 = i;
                                        outputStream2 = outputStream;
                                        try {
                                            this.d = ErrorMessage.a("httpsPost failed,Exception:" + e2.getMessage());
                                            if (outputStream2 != null) {
                                                try {
                                                    outputStream2.close();
                                                    obj = null;
                                                } catch (IOException e6) {
                                                    e6.printStackTrace();
                                                    obj = null;
                                                }
                                                if (obj == null) {
                                                }
                                                if (this.d == null) {
                                                    a.a("httpsPost failed,mResult is null");
                                                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                                                }
                                                a.a("httpsPost success end,parse result = " + this.d);
                                            }
                                            obj = null;
                                            if (obj == null) {
                                            }
                                            if (this.d == null) {
                                                a.a("httpsPost success end,parse result = " + this.d);
                                            }
                                            a.a("httpsPost failed,mResult is null");
                                            this.d = ErrorMessage.a("httpsPost failed,internal error");
                                        } catch (Throwable th3) {
                                            th = th3;
                                            outputStream = outputStream2;
                                            if (outputStream != null) {
                                                try {
                                                    outputStream.close();
                                                } catch (IOException e42) {
                                                    e42.printStackTrace();
                                                }
                                            }
                                            throw th;
                                        }
                                    } catch (IOException e7) {
                                        e6 = e7;
                                        i2 = i;
                                        try {
                                            this.d = ErrorMessage.a("httpsPost failed,Exception:" + e6.getMessage());
                                            if (outputStream != null) {
                                                try {
                                                    outputStream.close();
                                                    obj = null;
                                                } catch (IOException e62) {
                                                    e62.printStackTrace();
                                                    obj = null;
                                                }
                                                if (obj == null) {
                                                }
                                                if (this.d == null) {
                                                    a.a("httpsPost failed,mResult is null");
                                                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                                                }
                                                a.a("httpsPost success end,parse result = " + this.d);
                                            }
                                            obj = null;
                                            if (obj == null) {
                                            }
                                            if (this.d == null) {
                                                a.a("httpsPost success end,parse result = " + this.d);
                                            }
                                            a.a("httpsPost failed,mResult is null");
                                            this.d = ErrorMessage.a("httpsPost failed,internal error");
                                        } catch (Throwable th4) {
                                            th = th4;
                                            if (outputStream != null) {
                                                outputStream.close();
                                            }
                                            throw th;
                                        }
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    bufferedReader2 = bufferedReader;
                                    i = responseCode;
                                    bufferedReader2.close();
                                    inputStream.close();
                                    if (httpsURLConnection != null) {
                                        httpsURLConnection.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (IOException e8) {
                                e62 = e8;
                                i = responseCode;
                                a.a("httpsPost parse failed;" + e62.getMessage());
                                this.d = ErrorMessage.a("httpsPost failed,Exception:" + e62.getMessage());
                                bufferedReader2.close();
                                inputStream.close();
                                if (httpsURLConnection == null) {
                                    httpsURLConnection.disconnect();
                                    obj = null;
                                    i2 = i;
                                } else {
                                    obj = null;
                                    i2 = i;
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (obj == null) {
                                }
                                if (this.d == null) {
                                    a.a("httpsPost success end,parse result = " + this.d);
                                }
                                a.a("httpsPost failed,mResult is null");
                                this.d = ErrorMessage.a("httpsPost failed,internal error");
                            } catch (Throwable th6) {
                                th = th6;
                                i = responseCode;
                                bufferedReader2.close();
                                inputStream.close();
                                if (httpsURLConnection != null) {
                                    httpsURLConnection.disconnect();
                                }
                                throw th;
                            }
                        }
                        bufferedReader = null;
                        if (!(inputStream == null || bufferedReader == null)) {
                            try {
                                bufferedReader.close();
                                inputStream.close();
                            } catch (MalformedURLException e9) {
                                e2 = e9;
                                i2 = responseCode;
                                outputStream2 = outputStream;
                                this.d = ErrorMessage.a("httpsPost failed,Exception:" + e2.getMessage());
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                    obj = null;
                                    if (obj == null) {
                                    }
                                    if (this.d == null) {
                                        a.a("httpsPost success end,parse result = " + this.d);
                                    }
                                    a.a("httpsPost failed,mResult is null");
                                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                                }
                                obj = null;
                                if (obj == null) {
                                }
                                if (this.d == null) {
                                    a.a("httpsPost failed,mResult is null");
                                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                                }
                                a.a("httpsPost success end,parse result = " + this.d);
                            } catch (IOException e10) {
                                e62 = e10;
                                i2 = responseCode;
                                this.d = ErrorMessage.a("httpsPost failed,Exception:" + e62.getMessage());
                                if (outputStream != null) {
                                    outputStream.close();
                                    obj = null;
                                    if (obj == null) {
                                    }
                                    if (this.d == null) {
                                        a.a("httpsPost success end,parse result = " + this.d);
                                    }
                                    a.a("httpsPost failed,mResult is null");
                                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                                }
                                obj = null;
                                if (obj == null) {
                                }
                                if (this.d == null) {
                                    a.a("httpsPost failed,mResult is null");
                                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                                }
                                a.a("httpsPost success end,parse result = " + this.d);
                            }
                        }
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                            i2 = responseCode;
                        } else {
                            i2 = responseCode;
                        }
                    } catch (IOException e11) {
                        e62 = e11;
                        i = -1;
                        a.a("httpsPost parse failed;" + e62.getMessage());
                        this.d = ErrorMessage.a("httpsPost failed,Exception:" + e62.getMessage());
                        bufferedReader2.close();
                        inputStream.close();
                        if (httpsURLConnection == null) {
                            obj = null;
                            i2 = i;
                        } else {
                            httpsURLConnection.disconnect();
                            obj = null;
                            i2 = i;
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (obj == null) {
                        }
                        if (this.d == null) {
                            a.a("httpsPost failed,mResult is null");
                            this.d = ErrorMessage.a("httpsPost failed,internal error");
                        }
                        a.a("httpsPost success end,parse result = " + this.d);
                    } catch (Throwable th7) {
                        th = th7;
                        i = -1;
                        bufferedReader2.close();
                        inputStream.close();
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (IOException e12) {
                    e62 = e12;
                    inputStream = null;
                    i = -1;
                    a.a("httpsPost parse failed;" + e62.getMessage());
                    this.d = ErrorMessage.a("httpsPost failed,Exception:" + e62.getMessage());
                    if (!(inputStream == null || bufferedReader2 == null)) {
                        bufferedReader2.close();
                        inputStream.close();
                    }
                    if (httpsURLConnection == null) {
                        httpsURLConnection.disconnect();
                        obj = null;
                        i2 = i;
                    } else {
                        obj = null;
                        i2 = i;
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (obj == null) {
                    }
                    if (this.d == null) {
                        a.a("httpsPost success end,parse result = " + this.d);
                    }
                    a.a("httpsPost failed,mResult is null");
                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                } catch (Throwable th8) {
                    th = th8;
                    inputStream = null;
                    i = -1;
                    bufferedReader2.close();
                    inputStream.close();
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw th;
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (MalformedURLException e13) {
                e2 = e13;
                outputStream2 = outputStream;
                this.d = ErrorMessage.a("httpsPost failed,Exception:" + e2.getMessage());
                if (outputStream2 != null) {
                    outputStream2.close();
                    obj = null;
                    if (obj == null) {
                    }
                    if (this.d == null) {
                        a.a("httpsPost success end,parse result = " + this.d);
                    }
                    a.a("httpsPost failed,mResult is null");
                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                }
                obj = null;
                if (obj == null) {
                }
                if (this.d == null) {
                    a.a("httpsPost failed,mResult is null");
                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                }
                a.a("httpsPost success end,parse result = " + this.d);
            } catch (IOException e14) {
                e62 = e14;
                this.d = ErrorMessage.a("httpsPost failed,Exception:" + e62.getMessage());
                if (outputStream != null) {
                    outputStream.close();
                    obj = null;
                    if (obj == null) {
                    }
                    if (this.d == null) {
                        a.a("httpsPost success end,parse result = " + this.d);
                    }
                    a.a("httpsPost failed,mResult is null");
                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                }
                obj = null;
                if (obj == null) {
                }
                if (this.d == null) {
                    a.a("httpsPost failed,mResult is null");
                    this.d = ErrorMessage.a("httpsPost failed,internal error");
                }
                a.a("httpsPost success end,parse result = " + this.d);
            }
        } catch (MalformedURLException e15) {
            e2 = e15;
            this.d = ErrorMessage.a("httpsPost failed,Exception:" + e2.getMessage());
            if (outputStream2 != null) {
                outputStream2.close();
                obj = null;
                if (obj == null) {
                }
                if (this.d == null) {
                    a.a("httpsPost success end,parse result = " + this.d);
                }
                a.a("httpsPost failed,mResult is null");
                this.d = ErrorMessage.a("httpsPost failed,internal error");
            }
            obj = null;
            if (obj == null) {
            }
            if (this.d == null) {
                a.a("httpsPost failed,mResult is null");
                this.d = ErrorMessage.a("httpsPost failed,internal error");
            }
            a.a("httpsPost success end,parse result = " + this.d);
        } catch (IOException e16) {
            e62 = e16;
            outputStream = null;
            this.d = ErrorMessage.a("httpsPost failed,Exception:" + e62.getMessage());
            if (outputStream != null) {
                outputStream.close();
                obj = null;
                if (obj == null) {
                }
                if (this.d == null) {
                    a.a("httpsPost success end,parse result = " + this.d);
                }
                a.a("httpsPost failed,mResult is null");
                this.d = ErrorMessage.a("httpsPost failed,internal error");
            }
            obj = null;
            if (obj == null) {
            }
            if (this.d == null) {
                a.a("httpsPost failed,mResult is null");
                this.d = ErrorMessage.a("httpsPost failed,internal error");
            }
            a.a("httpsPost success end,parse result = " + this.d);
        } catch (Throwable th9) {
            th = th9;
            outputStream = null;
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
        if (obj == null && 200 != i2) {
            a.a("httpsPost failed,statusCode:" + i2);
            this.d = ErrorMessage.a("httpsPost failed,statusCode:" + i2);
        } else if (this.d == null) {
            a.a("httpsPost failed,mResult is null");
            this.d = ErrorMessage.a("httpsPost failed,internal error");
        } else {
            a.a("httpsPost success end,parse result = " + this.d);
        }
    }

    private static String b(HashMap<String, String> hashMap) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : hashMap.entrySet()) {
            Object obj2;
            if (obj != null) {
                obj2 = null;
            } else {
                stringBuilder.append("&");
                obj2 = obj;
            }
            stringBuilder.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    private HttpsURLConnection b() {
        try {
            URL url = new URL(this.b);
            String a = a(this.a);
            if (a == null || a.equals("")) {
                a.b("Current network is not available.");
                this.d = ErrorMessage.a("Current network is not available.");
                return null;
            }
            a.a("checkNetwork = " + a);
            HttpsURLConnection httpsURLConnection = a.equals("cmwap") ? (HttpsURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.172", 80))) : a.equals("ctwap") ? (HttpsURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setConnectTimeout(50000);
            httpsURLConnection.setReadTimeout(50000);
            return httpsURLConnection;
        } catch (MalformedURLException e) {
            a.a(e.getMessage());
            this.d = ErrorMessage.a("Auth server could not be parsed as a URL.");
            return null;
        } catch (Exception e2) {
            a.a(e2.getMessage());
            this.d = ErrorMessage.a("Init httpsurlconnection failed.");
            return null;
        }
    }

    private HashMap<String, String> c(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap();
        for (String str : hashMap.keySet()) {
            String str2 = str2.toString();
            hashMap2.put(str2, hashMap.get(str2));
        }
        return hashMap2;
    }

    protected String a(HashMap<String, String> hashMap) {
        this.c = c(hashMap);
        this.b = (String) this.c.get("url");
        HttpsURLConnection b = b();
        if (b == null) {
            a.b("syncConnect failed,httpsURLConnection is null");
            return this.d;
        }
        a(b);
        return this.d;
    }

    protected boolean a() {
        a.a("checkNetwork start");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            a.a("checkNetwork end");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
