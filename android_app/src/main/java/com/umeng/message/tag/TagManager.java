package com.umeng.message.tag;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.common.UmLog;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.common.inter.ITagManager.Result;
import com.umeng.message.util.HttpRequest;
import com.umeng.message.util.e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class TagManager {
    private static final String a = TagManager.class.getName();
    private static final String b = "ok";
    private static final String c = "fail";
    private static TagManager d;
    private static ITagManager f;
    private Context e;

    public interface TCallBack {
        void onMessage(boolean z, Result result);
    }

    public interface TagListCallBack {
        void onMessage(boolean z, List<String> list);
    }

    private TagManager(Context context) {
        this.e = context.getApplicationContext();
    }

    public static synchronized TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (d == null) {
                d = new TagManager(context.getApplicationContext());
                try {
                    f = (ITagManager) Class.forName("com.umeng.message.common.impl.json.JTagManager").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            tagManager = d;
        }
        return tagManager;
    }

    public void add(final TCallBack tCallBack, final String... strArr) {
        new Thread(new Runnable(this) {
            final /* synthetic */ TagManager c;

            public void run() {
                Exception e;
                Result result = null;
                if (this.c.d()) {
                    try {
                        throw new Exception("Tag API is disabled by the server.");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                } else if (!this.c.c()) {
                    try {
                        throw new Exception("No utdid or device_token");
                    } catch (Exception e22) {
                        e22.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                } else if (strArr == null || strArr.length == 0) {
                    try {
                        throw new Exception("No tags");
                    } catch (Exception e222) {
                        e222.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                } else {
                    List arrayList = new ArrayList();
                    for (String str : strArr) {
                        if (!(MessageSharedPrefs.getInstance(this.c.e).isTagSet(str) || arrayList.contains(str))) {
                            arrayList.add(str);
                        }
                    }
                    if (arrayList.size() == 0) {
                        tCallBack.onMessage(true, this.c.e());
                        return;
                    }
                    JSONObject e3;
                    try {
                        e3 = this.c.b();
                        try {
                            e3.put(MsgConstant.KEY_TAGS, e.a(arrayList));
                            result = TagManager.f.add(e3, true, strArr);
                            tCallBack.onMessage(true, result);
                        } catch (Exception e4) {
                            e222 = e4;
                            e222.printStackTrace();
                            if (e222 == null && e222.getMessage() != null && e222.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                                try {
                                    result = TagManager.f.add(e3, false, strArr);
                                    tCallBack.onMessage(true, result);
                                    return;
                                } catch (Exception e2222) {
                                    tCallBack.onMessage(false, result);
                                    e2222.printStackTrace();
                                    return;
                                }
                            }
                            tCallBack.onMessage(false, result);
                        }
                    } catch (Exception e5) {
                        e2222 = e5;
                        e3 = null;
                        e2222.printStackTrace();
                        if (e2222 == null) {
                        }
                        tCallBack.onMessage(false, result);
                    }
                }
            }
        }).start();
    }

    public void update(final TCallBack tCallBack, final String... strArr) {
        new Thread(new Runnable(this) {
            final /* synthetic */ TagManager c;

            public void run() {
                Exception e;
                JSONObject e2;
                Result result = null;
                if (this.c.d()) {
                    try {
                        throw new Exception("Tag API is disabled by the server.");
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                } else if (!this.c.c()) {
                    try {
                        throw new Exception("No utdid or device_token");
                    } catch (Exception e32) {
                        e32.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                } else if (strArr == null || strArr.length == 0) {
                    try {
                        throw new Exception("No tags");
                    } catch (Exception e322) {
                        e322.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                } else {
                    List arrayList = new ArrayList();
                    for (Object add : strArr) {
                        arrayList.add(add);
                    }
                    if (arrayList.size() == 0) {
                        tCallBack.onMessage(true, this.c.e());
                        return;
                    }
                    try {
                        e2 = this.c.b();
                        try {
                            e2.put(MsgConstant.KEY_TAGS, e.a(arrayList));
                            result = TagManager.f.update(e2, true, strArr);
                            tCallBack.onMessage(true, result);
                        } catch (Exception e4) {
                            e322 = e4;
                            e322.printStackTrace();
                            if (e322 == null && e322.getMessage() != null && e322.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                                try {
                                    result = TagManager.f.update(e2, false, strArr);
                                    tCallBack.onMessage(true, result);
                                    return;
                                } catch (Exception e3222) {
                                    tCallBack.onMessage(false, result);
                                    e3222.printStackTrace();
                                    return;
                                }
                            }
                            tCallBack.onMessage(false, result);
                        }
                    } catch (Exception e5) {
                        e3222 = e5;
                        e2 = null;
                        e3222.printStackTrace();
                        if (e3222 == null) {
                        }
                        tCallBack.onMessage(false, result);
                    }
                }
            }
        }).start();
    }

    public void delete(final TCallBack tCallBack, final String... strArr) {
        new Thread(new Runnable(this) {
            final /* synthetic */ TagManager c;

            public void run() {
                Exception e;
                Result result = null;
                if (this.c.d()) {
                    try {
                        throw new Exception("Tag API is disabled by the server.");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                } else if (this.c.c()) {
                    if (strArr == null || strArr.length == 0) {
                        try {
                            throw new Exception("No tags");
                        } catch (Exception e22) {
                            e22.printStackTrace();
                            tCallBack.onMessage(false, null);
                        }
                    }
                    JSONObject e3;
                    try {
                        e3 = this.c.b();
                        try {
                            e3.put(MsgConstant.KEY_TAGS, e.a(strArr));
                            result = TagManager.f.delete(e3, true, strArr);
                            tCallBack.onMessage(true, result);
                        } catch (Exception e4) {
                            e22 = e4;
                            e22.printStackTrace();
                            if (e22 == null && e22.getMessage() != null && e22.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                                try {
                                    result = TagManager.f.delete(e3, false, strArr);
                                    tCallBack.onMessage(true, result);
                                    return;
                                } catch (Exception e222) {
                                    tCallBack.onMessage(false, result);
                                    e222.printStackTrace();
                                    return;
                                }
                            }
                            tCallBack.onMessage(false, result);
                        }
                    } catch (Exception e5) {
                        e222 = e5;
                        e3 = null;
                        e222.printStackTrace();
                        if (e222 == null) {
                        }
                        tCallBack.onMessage(false, result);
                    }
                } else {
                    try {
                        throw new Exception("No utdid or device_token");
                    } catch (Exception e2222) {
                        e2222.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                }
            }
        }).start();
    }

    public void reset(final TCallBack tCallBack) {
        new Thread(new Runnable(this) {
            final /* synthetic */ TagManager b;

            public void run() {
                Exception e;
                Result result = null;
                if (this.b.d()) {
                    try {
                        throw new Exception("Tag API is disabled by the server.");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                } else if (this.b.c()) {
                    JSONObject e3;
                    try {
                        e3 = this.b.b();
                        try {
                            result = TagManager.f.reset(e3, true);
                            tCallBack.onMessage(true, result);
                        } catch (Exception e4) {
                            e2 = e4;
                            e2.printStackTrace();
                            if (e2 == null && e2.getMessage() != null && e2.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                                try {
                                    result = TagManager.f.reset(e3, false);
                                    tCallBack.onMessage(true, result);
                                    return;
                                } catch (Exception e22) {
                                    tCallBack.onMessage(false, result);
                                    e22.printStackTrace();
                                    return;
                                }
                            }
                            tCallBack.onMessage(false, result);
                        }
                    } catch (Exception e5) {
                        e22 = e5;
                        e3 = null;
                        e22.printStackTrace();
                        if (e22 == null) {
                        }
                        tCallBack.onMessage(false, result);
                    }
                } else {
                    try {
                        throw new Exception("No utdid or device_token");
                    } catch (Exception e222) {
                        e222.printStackTrace();
                        tCallBack.onMessage(false, null);
                    }
                }
            }
        }).start();
    }

    public void list(final TagListCallBack tagListCallBack) {
        new Thread(new Runnable(this) {
            final /* synthetic */ TagManager b;

            public void run() {
                Exception e;
                JSONObject e2;
                List list = null;
                if (this.b.d()) {
                    try {
                        throw new Exception("Tag API is disabled by the server.");
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        tagListCallBack.onMessage(false, null);
                    }
                } else if (this.b.c()) {
                    try {
                        e2 = this.b.b();
                        try {
                            list = TagManager.f.list(e2, true);
                            tagListCallBack.onMessage(true, list);
                        } catch (Exception e4) {
                            e3 = e4;
                            if (e3 != null || e3.getMessage() == null) {
                                tagListCallBack.onMessage(false, list);
                            } else if (e3.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                                try {
                                    list = TagManager.f.list(e2, false);
                                    tagListCallBack.onMessage(true, list);
                                } catch (Exception e5) {
                                    tagListCallBack.onMessage(false, list);
                                }
                            } else {
                                tagListCallBack.onMessage(false, list);
                            }
                        }
                    } catch (Exception e6) {
                        e3 = e6;
                        e2 = null;
                        if (e3 != null) {
                        }
                        tagListCallBack.onMessage(false, list);
                    }
                } else {
                    try {
                        throw new Exception("No utdid or device_token");
                    } catch (Exception e32) {
                        e32.printStackTrace();
                        tagListCallBack.onMessage(false, null);
                    }
                }
            }
        }).start();
    }

    private JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.e).getHeader());
        jSONObject.put("utdid", UmengMessageDeviceConfig.getUtdid(this.e));
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, MessageSharedPrefs.getInstance(this.e).getDeviceToken());
        jSONObject.put("ts", System.currentTimeMillis());
        return jSONObject;
    }

    private static JSONObject a(JSONObject jSONObject, String str) throws JSONException {
        String body = HttpRequest.post((CharSequence) str).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).send(jSONObject.toString()).body("UTF-8");
        UmLog.d(a, "postJson() url=" + str + "\n request = " + jSONObject + "\n response = " + body);
        return new JSONObject(body);
    }

    private boolean c() {
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.e))) {
            UmLog.e(a, "UTDID is empty");
            return false;
        } else if (!TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.e).getDeviceToken())) {
            return true;
        } else {
            UmLog.e(a, "RegistrationId is empty");
            return false;
        }
    }

    private boolean d() {
        boolean z = true;
        if (MessageSharedPrefs.getInstance(this.e).getTagSendPolicy() != 1) {
            z = false;
        }
        if (z) {
            UmLog.d(a, "tag is disabled by the server");
        }
        return z;
    }

    private Result e() {
        Result result = new Result(new JSONObject());
        result.remain = MessageSharedPrefs.getInstance(this.e).getTagRemain();
        result.status = "ok";
        result.jsonString = "status:" + result.status + "," + " remain:" + result.remain + "," + "description:" + result.status;
        return result;
    }
}
