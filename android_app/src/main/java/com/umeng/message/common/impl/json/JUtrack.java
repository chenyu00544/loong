package com.umeng.message.common.impl.json;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack.ICallBack;
import com.umeng.message.common.UmLog;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.common.inter.IUtrack;
import com.umeng.message.entity.Alias;
import com.umeng.message.entity.Ucode;
import com.umeng.message.proguard.c;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.l;
import com.umeng.message.provider.a;
import com.umeng.message.util.HttpRequest;
import com.umeng.message.util.b;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class JUtrack implements IUtrack {
    private static final String a = JUtrack.class.getSimpleName();
    private Context b;

    public JUtrack(Context context) {
        this.b = context;
    }

    public void sendMsgLog(JSONObject jSONObject, String str, int i, long j, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            try {
                sendRequest = sendRequest(jSONObject, MsgConstant.LOG_ENDPOINT);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = sendRequest(this.b, jSONObject, MsgConstant.LOG_ENDPOINT);
            }
        } else {
            sendRequest = sendRequest(jSONObject, MsgConstant.LOG_ENDPOINT.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            l.a(this.b).a(str, i);
            if (i != 0) {
                l.a(this.b).b(str);
            }
        }
    }

    public void trackAppLaunch(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            try {
                sendRequest = sendRequest(jSONObject, MsgConstant.LAUNCH_ENDPOINT);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = sendRequest(this.b, jSONObject, MsgConstant.LAUNCH_ENDPOINT);
            }
        } else {
            sendRequest = sendRequest(jSONObject, MsgConstant.LAUNCH_ENDPOINT.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            l.a(this.b).a(System.currentTimeMillis());
            int i = sendRequest.getInt("launch_policy");
            UmLog.d(a, "launch_policy:" + i);
            int i2 = sendRequest.getInt("tag_policy");
            UmLog.d(a, "tag_policy:" + i2);
            if (i > 0) {
                MessageSharedPrefs.getInstance(this.b).setAppLaunchLogSendPolicy(i);
            }
            if (i2 > 0) {
                MessageSharedPrefs.getInstance(this.b).setTagSendPolicy(i2);
            }
            String optString = sendRequest.optString(MsgConstant.KEY_UCODE);
            if (optString != null && !optString.equals("")) {
                JSONArray jSONArray = new JSONArray(c.b(optString, "utf-8"));
                final List arrayList = new ArrayList();
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    String optString2 = jSONArray.getJSONObject(i3).optString("p");
                    int optInt = jSONArray.getJSONObject(i3).optInt("f");
                    Ucode ucode = new Ucode();
                    ucode.p = optString2;
                    ucode.f = (long) optInt;
                    ucode.b = false;
                    arrayList.add(ucode);
                }
                new Thread(new Runnable(this) {
                    final /* synthetic */ JUtrack b;

                    public void run() {
                        try {
                            Thread.sleep(10000);
                            for (int i = 0; i < arrayList.size(); i++) {
                                Ucode ucode = (Ucode) arrayList.get(i);
                                ucode.b = UmengMessageDeviceConfig.getDataData(ucode.p);
                            }
                            MessageSharedPrefs.getInstance(this.b.b).setUcode(h.a(arrayList));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }

    public void trackRegister(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            try {
                sendRequest = sendRequest(jSONObject, MsgConstant.REGISTER_ENDPOINT);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = sendRequest(this.b, jSONObject, MsgConstant.REGISTER_ENDPOINT);
            }
        } else {
            sendRequest = sendRequest(jSONObject, MsgConstant.REGISTER_ENDPOINT.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).setHasResgister(true);
            if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.b).getDeviceToken())) {
                UmLog.e(a, "setRegisteredToUmeng: empty registration id");
            }
        }
    }

    public void trackLocation(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            try {
                sendRequest = sendRequest(jSONObject, MsgConstant.LBS_ENDPOINT);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = sendRequest(this.b, jSONObject, MsgConstant.LBS_ENDPOINT);
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        sendRequest = sendRequest(jSONObject, MsgConstant.LBS_ENDPOINT.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        if (sendRequest == null) {
            return;
        }
        if (TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            UmLog.d(a, "location track success");
        }
    }

    public void addAlias(String str, String str2, JSONObject jSONObject, ICallBack iCallBack, boolean z) throws Exception {
        String optString = jSONObject.optString("fail", "");
        String optString2 = jSONObject.optString("success", "");
        UmLog.i(a, "keyfail:" + optString + ",keysuccess:" + optString2);
        if (optString.equals("") && optString2.equals("")) {
            JSONObject sendRequest;
            if (z) {
                try {
                    sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_ENDPOINT);
                } catch (Throwable e) {
                    if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                        throw new Exception(e);
                    }
                    sendRequest = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_ENDPOINT);
                }
            } else {
                sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_ENDPOINT.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
            }
            if (sendRequest == null || !TextUtils.equals(sendRequest.optString("success", ""), ITagManager.SUCCESS)) {
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 1, "网络请求失败alias:" + str + ",type:" + str2 + ",devicetoken:" + MessageSharedPrefs.getInstance(this.b).getDeviceToken());
                iCallBack.onMessage(false, "alias:" + str + "添加失败");
                return;
            }
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 0, "");
            iCallBack.onMessage(true, "alias:" + str + "添加成功");
            return;
        }
        if (!optString.equals("")) {
            iCallBack.onMessage(false, "alias:" + str + "添加失败");
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 1, optString);
        }
        if (!optString2.equals("")) {
            iCallBack.onMessage(true, "alias:" + str + "已经添加");
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 2, optString2);
        }
    }

    public void addExclusiveAlias(String str, String str2, JSONObject jSONObject, ICallBack iCallBack, boolean z) throws Exception {
        String optString = jSONObject.optString("fail", "");
        String optString2 = jSONObject.optString("success", "");
        UmLog.i(a, "keyfail:" + optString + ",keysuccess:" + optString2);
        if (optString.equals("") && optString2.equals("")) {
            JSONObject sendRequest;
            if (z) {
                try {
                    sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT);
                } catch (Throwable e) {
                    if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                        throw new Exception(e);
                    }
                    sendRequest = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT);
                }
            } else {
                sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
            }
            if (sendRequest == null || !TextUtils.equals(sendRequest.optString("success", ""), ITagManager.SUCCESS)) {
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 1, "网络请求失败alias:" + str + ",type:" + str2 + ",devicetoken:" + MessageSharedPrefs.getInstance(this.b).getDeviceToken());
                iCallBack.onMessage(false, "alias:" + str + "添加失败");
                return;
            }
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 0, "");
            iCallBack.onMessage(true, "alias:" + str + "添加成功");
            return;
        }
        if (!optString.equals("")) {
            iCallBack.onMessage(false, "alias:" + str + "添加失败");
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 1, optString);
        }
        if (!optString2.equals("")) {
            iCallBack.onMessage(true, "alias:" + str + "已经添加");
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 2, optString2);
        }
    }

    public void removeAlias(String str, String str2, JSONObject jSONObject, ICallBack iCallBack, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            try {
                sendRequest = sendRequest(jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = sendRequest(this.b, jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT);
            }
        } else {
            sendRequest = sendRequest(jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).removeAlias(0, str, str2);
            MessageSharedPrefs.getInstance(this.b).removeAlias(1, str, str2);
            iCallBack.onMessage(true, "alias:" + str + ",type:" + str2 + "删除成功");
        }
    }

    public static JSONObject sendRequest(JSONObject jSONObject, String str) throws Exception {
        String body = HttpRequest.post((CharSequence) str).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).send(jSONObject.toString()).body("UTF-8");
        UmLog.d(a, "sendRequest() url=" + str + "\n request = " + jSONObject + "\n response = " + body);
        return new JSONObject(body);
    }

    public static JSONObject sendRequest(Context context, JSONObject jSONObject, String str) throws Exception {
        String host = new URL(str).getHost();
        String a = b.a(context, host);
        UmLog.d(a, "ip:" + a);
        if (a == null) {
            return null;
        }
        URL url = new URL(str.replaceFirst(host, a));
        a = HttpRequest.post(url).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).header(HttpConstant.HOST, host).trustHosts().send(jSONObject.toString()).body("UTF-8");
        UmLog.d(a, "dns-->sendRequest() url=" + url.toString() + "\n request = " + jSONObject + "\n response = " + a);
        return new JSONObject(a);
    }

    public void sendAliasFailLog(JSONObject jSONObject) {
        if (MessageSharedPrefs.getInstance(this.b).getDaRegisterSendPolicy() == 1) {
            UmLog.d(a, "da_register_policy=1, skip sending da_register info.");
            return;
        }
        try {
            String[] strArr = new String[]{"1"};
            String str = "";
            ContentResolver contentResolver = this.b.getContentResolver();
            a.a(this.b);
            Cursor query = contentResolver.query(a.d, new String[]{"message", "time"}, "error=?", strArr, null);
            if (query != null) {
                List<Alias> arrayList = new ArrayList();
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    String string = query.getString(query.getColumnIndex("message"));
                    long j = query.getLong(query.getColumnIndex("time"));
                    Alias alias = new Alias();
                    alias.aliasMessage = string;
                    alias.aliasTime = j;
                    arrayList.add(alias);
                    query.moveToNext();
                }
                if (query != null) {
                    query.close();
                }
                for (Alias alias2 : arrayList) {
                    a(jSONObject, alias2.aliasMessage, alias2.aliasTime);
                }
            }
        } catch (Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
        }
    }

    public void sendRegisterLog(JSONObject jSONObject) throws Exception {
        JSONObject sendRequest;
        try {
            sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_LOG);
        } catch (Throwable e) {
            if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                throw new Exception(e);
            }
            sendRequest = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_LOG);
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.optString("success", ""), ITagManager.SUCCESS)) {
            int parseInt = Integer.parseInt(sendRequest.getString("da_register_policy"));
            if (parseInt > 0) {
                MessageSharedPrefs.getInstance(this.b).setDaRegisterSendPolicy(parseInt);
            }
        }
    }

    private void a(JSONObject jSONObject, String str, long j) throws Exception {
        if (!str.equals("")) {
            JSONObject sendRequest;
            jSONObject.put(MsgConstant.KEY_ALIAS_FAIL_LOG, str);
            try {
                sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_LOG);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_LOG);
            }
            if (sendRequest != null && TextUtils.equals(sendRequest.optString("success", ""), ITagManager.SUCCESS)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("error", "3");
                String[] strArr = new String[]{j + ""};
                ContentResolver contentResolver = this.b.getContentResolver();
                a.a(this.b);
                contentResolver.update(a.d, contentValues, "time=?", strArr);
                int parseInt = Integer.parseInt(sendRequest.optString("da_register_policy"));
                if (parseInt > 0) {
                    MessageSharedPrefs.getInstance(this.b).setDaRegisterSendPolicy(parseInt);
                }
            }
        }
    }
}
