package com.baidu.platform.comapi.a;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.http.HttpClient.HttpStateError;
import com.baidu.platform.comjni.util.AppMD5;
import com.tencent.tauth.AuthActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    Context a;
    AsyncHttpClient b;

    public interface a<T> {
        void a(HttpStateError httpStateError);

        void a(T t);
    }

    public a(Context context) {
        this.a = context;
        this.b = new AsyncHttpClient(context);
    }

    private c a(String str) {
        if (str == null || str.equals("")) {
            return new c(d.PANO_NOT_FOUND);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("result");
            if (optJSONObject == null) {
                return new c(d.PANO_NOT_FOUND);
            }
            if (optJSONObject.optInt("error") != 0) {
                return new c(d.PANO_UID_ERROR);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("content");
            if (optJSONArray == null) {
                return new c(d.PANO_NOT_FOUND);
            }
            c cVar = null;
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i).optJSONObject("poiinfo");
                if (optJSONObject2 != null) {
                    cVar = new c(d.PANO_NO_ERROR);
                    cVar.a(optJSONObject2.optString("PID"));
                    cVar.a(optJSONObject2.optInt("hasstreet"));
                }
            }
            return cVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return new c(d.PANO_NOT_FOUND);
        }
    }

    private String a(Builder builder) {
        Builder buildUpon = Uri.parse(builder.build().toString() + HttpClient.getPhoneInfo()).buildUpon();
        buildUpon.appendQueryParameter("sign", AppMD5.getSignMD5String(buildUpon.build().getEncodedQuery()));
        return buildUpon.build().toString();
    }

    private void a(Builder builder, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    public void a(String str, a<c> aVar) {
        Builder builder = new Builder();
        builder.scheme(HttpConstant.HTTP);
        builder.encodedAuthority("api.map.baidu.com");
        builder.path("/sdkproxy/lbs_androidsdk/pano/v1/");
        a(builder, "qt", "poi");
        a(builder, "uid", str);
        a(builder, AuthActivity.ACTION_KEY, "0");
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            aVar.a(new c(d.PANO_NO_TOKEN));
            return;
        }
        a(builder, "token", authToken);
        this.b.get(a(builder), new b(this, aVar));
    }
}
