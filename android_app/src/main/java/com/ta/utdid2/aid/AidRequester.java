package com.ta.utdid2.aid;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ta.utdid2.android.utils.DebugUtils;
import com.ta.utdid2.android.utils.NetworkUtils;
import com.ut.device.AidCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class AidRequester {
    private static final String AIDFUNCNAME = "/get_aid/";
    private static final String AIDSERVER = "http://hydra.alibaba.com/";
    private static final String NAME_AID = "&aid=";
    private static final String NAME_ID = "&id=";
    private static final String NAME_RESULT_ACTION = "action";
    private static final String NAME_RESULT_AID = "aid";
    private static final String NAME_RESULT_ISERROR = "isError";
    private static final String NAME_RESULT_STATUS = "status";
    private static final String NAME_RESUTL_DATA = "data";
    private static final String NAME_TOKEN = "auth[token]=";
    private static final String NAME_TYPE = "&type=";
    private static final String RSP_ACTION_CHANGED = "changed";
    private static final String RSP_ACTION_NEW = "new";
    private static final String RSP_ACTION_UNCHANGED = "unchanged";
    private static final String RSP_ISERROR_FALSE = "false";
    private static final String RSP_ISERROR_TRUE = "true";
    private static final String RSP_STATUS_INVALID_APP = "404";
    private static final String RSP_STATUS_INVALID_TOKEN = "401";
    private static final String RSP_STATUS_OK = "200";
    private static final int SESSION_TIME_OUT = 1000;
    private static final String TAG = AidRequester.class.getName();
    private static final String TYPE_UTDID = "utdid";
    private static final int WEAK_SESSION_TIME_OUT = 3000;
    private static AidRequester sAidRequester = null;
    private Context mContext;
    private Object mLock = new Object();

    class PostRestThread extends Thread {
        String mAppName;
        AidCallback mCallback;
        String mOldAid;
        HttpPost mPost;
        String mRspLine = "";
        String mToken = "";

        public PostRestThread(HttpPost httpPost) {
            this.mPost = httpPost;
        }

        public PostRestThread(HttpPost httpPost, AidCallback aidCallback, String str, String str2, String str3) {
            this.mPost = httpPost;
            this.mCallback = aidCallback;
            this.mOldAid = str;
            this.mAppName = str2;
            this.mToken = str3;
        }

        public void run() {
            HttpResponse execute;
            BufferedReader bufferedReader = null;
            if (this.mCallback != null) {
                this.mCallback.onAidEventChanged(1000, this.mOldAid);
            }
            try {
                execute = new DefaultHttpClient().execute(this.mPost);
            } catch (Exception e) {
                if (this.mCallback != null) {
                    this.mCallback.onAidEventChanged(1002, this.mOldAid);
                }
                Log.e(AidRequester.TAG, e.toString());
                execute = null;
            }
            if (execute != null) {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(execute.getEntity().getContent(), Charset.forName("UTF-8")));
                } catch (Exception e2) {
                    if (this.mCallback != null) {
                        this.mCallback.onAidEventChanged(1002, this.mOldAid);
                    }
                    Log.e(AidRequester.TAG, e2.toString());
                }
            } else {
                Log.e(AidRequester.TAG, "response is null!");
            }
            String str = "";
            if (bufferedReader != null) {
                while (true) {
                    str = bufferedReader.readLine();
                    if (str == null) {
                        break;
                    }
                    try {
                        if (DebugUtils.DBG) {
                            Log.d(AidRequester.TAG, str);
                        }
                        this.mRspLine = str;
                    } catch (Exception e22) {
                        if (this.mCallback != null) {
                            this.mCallback.onAidEventChanged(1002, this.mOldAid);
                        }
                        Log.e(AidRequester.TAG, e22.toString());
                    }
                }
            } else {
                Log.e(AidRequester.TAG, "BufferredReader is null!");
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    if (DebugUtils.DBG) {
                        Log.d(AidRequester.TAG, "close the bufferreader");
                    }
                } catch (IOException e3) {
                    Log.e(AidRequester.TAG, e3.toString());
                }
            }
            if (this.mCallback == null) {
                synchronized (AidRequester.this.mLock) {
                    AidRequester.this.mLock.notifyAll();
                }
                return;
            }
            str = AidRequester.getAidFromJsonRsp(this.mRspLine, this.mOldAid);
            this.mCallback.onAidEventChanged(1001, str);
            AidStorageController.setAidValueToSP(AidRequester.this.mContext, this.mAppName, str, this.mToken);
        }

        public String getResponseLine() {
            return this.mRspLine;
        }
    }

    public static synchronized AidRequester getInstance(Context context) {
        AidRequester aidRequester;
        synchronized (AidRequester.class) {
            if (sAidRequester == null) {
                sAidRequester = new AidRequester(context);
            }
            aidRequester = sAidRequester;
        }
        return aidRequester;
    }

    public AidRequester(Context context) {
        this.mContext = context;
    }

    public void postRestAsync(String str, String str2, String str3, String str4, AidCallback aidCallback) {
        String postUrl = getPostUrl(str, str2, str3, str4);
        if (DebugUtils.DBG) {
            Log.d(TAG, "url:" + postUrl + "; len:" + postUrl.length());
        }
        new PostRestThread(new HttpPost(postUrl), aidCallback, str4, str, str2).start();
    }

    public String postRest(String str, String str2, String str3, String str4) {
        String str5 = "";
        String postUrl = getPostUrl(str, str2, str3, str4);
        int i = NetworkUtils.isConnectedToWeakNetwork(this.mContext) ? 3000 : 1000;
        if (DebugUtils.DBG) {
            Log.d(TAG, "url:" + postUrl + "; timeout:" + i);
        }
        PostRestThread postRestThread = new PostRestThread(new HttpPost(postUrl));
        postRestThread.start();
        try {
            synchronized (this.mLock) {
                this.mLock.wait((long) i);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        str5 = postRestThread.getResponseLine();
        if (DebugUtils.DBG) {
            Log.d(TAG, "mLine:" + str5);
        }
        return getAidFromJsonRsp(str5, str4);
    }

    private static String getAidFromJsonRsp(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string;
            if (jSONObject.has("data")) {
                jSONObject = jSONObject.getJSONObject("data");
                if (!jSONObject.has("action") || !jSONObject.has("aid")) {
                    return str2;
                }
                string = jSONObject.getString("action");
                return (string.equalsIgnoreCase(RSP_ACTION_NEW) || string.equalsIgnoreCase(RSP_ACTION_CHANGED)) ? jSONObject.getString("aid") : str2;
            } else if (!jSONObject.has(NAME_RESULT_ISERROR) || !jSONObject.has("status")) {
                return str2;
            } else {
                string = jSONObject.getString(NAME_RESULT_ISERROR);
                str3 = jSONObject.getString("status");
                if (!string.equalsIgnoreCase(RSP_ISERROR_TRUE)) {
                    return str2;
                }
                if (!str3.equalsIgnoreCase(RSP_STATUS_INVALID_APP) && !str3.equalsIgnoreCase(RSP_STATUS_INVALID_TOKEN)) {
                    return str2;
                }
                if (DebugUtils.DBG) {
                    Log.d(TAG, "remove the AID, status:" + str3);
                }
                return "";
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            return str2;
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
            return str2;
        }
    }

    private static String getPostUrl(String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            str3 = URLEncoder.encode(str3, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.append(AIDSERVER).append(str).append(AIDFUNCNAME).append("?").append(NAME_TOKEN).append(str2).append(NAME_TYPE).append("utdid").append(NAME_ID).append(str3).append(NAME_AID).append(str4).toString();
    }
}
