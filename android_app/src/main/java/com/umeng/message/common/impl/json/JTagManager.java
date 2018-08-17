package com.umeng.message.common.impl.json;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.common.UmLog;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.common.inter.ITagManager.Result;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public class JTagManager implements ITagManager {
    private static final String a = JTagManager.class.getSimpleName();
    private Context b;

    public JTagManager(Context context) {
        this.b = context;
    }

    public Result add(JSONObject jSONObject, boolean z, String... strArr) throws Exception {
        JSONObject sendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/add";
        if (z) {
            try {
                sendRequest = JUtrack.sendRequest(jSONObject, str);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                UmLog.i(a, "add tag UnknownHostException");
                sendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            }
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, str.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        Result result = new Result(sendRequest);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).addTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
        }
        return result;
    }

    public Result update(JSONObject jSONObject, boolean z, String... strArr) throws Exception {
        JSONObject sendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/update";
        if (z) {
            try {
                sendRequest = JUtrack.sendRequest(jSONObject, str);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            }
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, str.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        Result result = new Result(sendRequest);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).resetTags();
            MessageSharedPrefs.getInstance(this.b).addTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
        }
        return result;
    }

    public Result delete(JSONObject jSONObject, boolean z, String... strArr) throws Exception {
        JSONObject sendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/delete";
        if (z) {
            try {
                sendRequest = JUtrack.sendRequest(jSONObject, str);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            }
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, str.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        Result result = new Result(sendRequest);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).removeTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
        }
        return result;
    }

    public Result reset(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/reset";
        if (z) {
            try {
                sendRequest = JUtrack.sendRequest(jSONObject, str);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            }
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, str.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        Result result = new Result(sendRequest);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).resetTags();
        }
        return result;
    }

    public List<String> list(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        String str = MsgConstant.TAG_ENDPOINT + "/get";
        if (z) {
            try {
                sendRequest = JUtrack.sendRequest(jSONObject, str);
            } catch (Throwable e) {
                if (e == null || e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UtilityImpl.isNetworkConnected(this.b)) {
                    throw new Exception(e);
                }
                sendRequest = JUtrack.sendRequest(this.b, jSONObject, str);
            }
        } else {
            sendRequest = JUtrack.sendRequest(jSONObject, str.replace(HttpConstant.HTTPS, HttpConstant.HTTP));
        }
        if (!TextUtils.equals(new Result(sendRequest).status, ITagManager.SUCCESS) || sendRequest.getString(MsgConstant.KEY_TAGS) == null) {
            return null;
        }
        UmLog.d(a, sendRequest.getString(MsgConstant.KEY_TAGS));
        return Arrays.asList(sendRequest.getString(MsgConstant.KEY_TAGS).split(","));
    }
}
