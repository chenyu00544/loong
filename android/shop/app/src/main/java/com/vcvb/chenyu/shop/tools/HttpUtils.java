package com.vcvb.chenyu.shop.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private static final byte[] LOCKER = new byte[0];
    private static HttpUtils mInstance;
    private OkHttpClient client;

    public HttpUtils() {
        okhttp3.OkHttpClient.Builder ClientBuilder = new okhttp3.OkHttpClient.Builder();
        ClientBuilder.readTimeout(20, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(6, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        //支持HTTPS请求，跳过证书验证
        ClientBuilder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
        ClientBuilder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        client = ClientBuilder.build();
    }

    /**
     * 单例模式获取NetUtils
     *
     * @return
     */
    public static HttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (LOCKER) {
                if (mInstance == null) {
                    mInstance = new HttpUtils();
                }
            }
        }
        return mInstance;
    }

    public static byte[] sendPost(String path) {
        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        byte[] bytes = null;
        HttpURLConnection connection = null;
        InputStream is = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            is = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        try {
            byte[] buffer = new byte[1024 * 8];
            int length = 0;
            while ((length = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, length);
            }
            bufferedOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    //异步get
    public void get(String url, final NetCall netCall) {
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        //2 将Request封装为Call
        Call call = client.newCall(request);
        //3 执行Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                netCall.failed(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObject = parseJsonData(response.body().string());
                netCall.success(call, jsonObject);
            }
        });
    }

    //异步post
    public void post(String url, Map<String, String> params, final NetCall netCall) {
        //构造token
        String token = "";
        if (params.get("token") != null) {
            token = params.get("token");
            params.remove("token");
        }
        //1构造RequestBody
        RequestBody body = setRequestBody(params);
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.addHeader("vcvbuy-Authorization", token).post(body).url
                (url).build();
        //3 将Request封装为Call
        Call call = client.newCall(request);
        //4 执行Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                netCall.failed(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObject = parseJsonData(response.body().string());
                netCall.success(call, jsonObject);
            }
        });
    }

    /**
     * 自定义网络回调接口
     */
    public interface NetCall {
        void success(Call call, JSONObject json) throws IOException;

        void failed(Call call, IOException e);
    }

    private static JSONObject parseJsonData(String string) {
        try {
            //解析的过程就是在逐层剥开代码的过程
            return new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post的请求参数，构造RequestBody
     *
     * @param params
     * @return
     */
    private RequestBody setRequestBody(Map<String, String> params) {
        RequestBody body = null;
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        if (params != null) {
            Iterator<String> iterator = params.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                if (params.get(key) != null) {
                    formEncodingBuilder.add(key, params.get(key));
                }
                Log.d("post http", "post_Params===" + key + "====" + params.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用来判断网络连接
            //则可以使用cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
