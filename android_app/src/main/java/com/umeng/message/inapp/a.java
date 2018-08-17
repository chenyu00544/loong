package com.umeng.message.inapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.umeng.message.common.UmLog;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UmengCardMessageBuilder */
final class a implements IUmengInAppMessageCallback, ImageLoaderCallback {
    private static final String a = a.class.getName();
    private static final int f = 10;
    private Context b;
    private String c;
    private boolean d;
    private UInAppMessage e;
    private IUmengInAppMsgCloseCallback g;

    public a(Activity activity, String str, IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback) {
        this.b = activity;
        this.c = str;
        this.g = iUmengInAppMsgCloseCallback;
    }

    public a(Context context, String str) {
        this.b = context.getApplicationContext();
        this.c = str;
        this.d = true;
    }

    private boolean a(String str) {
        JSONArray jSONArray;
        if (!UmengMessageDeviceConfig.getAppVersionCode(this.b).equals(InAppMessageManager.getInstance(this.b).e())) {
            InAppMessageManager.getInstance(this.b).e("");
        }
        InAppMessageManager.getInstance(this.b).f(UmengMessageDeviceConfig.getAppVersionCode(this.b));
        Object d = InAppMessageManager.getInstance(this.b).d();
        if (!TextUtils.isEmpty(d)) {
            try {
                jSONArray = new JSONArray(d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONArray == null) {
                jSONArray = new JSONArray();
                jSONArray.put(str);
                InAppMessageManager.getInstance(this.b).e(jSONArray.toString());
                return true;
            } else if (!a(jSONArray, str)) {
                return true;
            } else {
                if (jSONArray.length() < 10) {
                    return false;
                }
                jSONArray.put(str);
                InAppMessageManager.getInstance(this.b).e(jSONArray.toString());
                return true;
            }
        }
        jSONArray = null;
        if (jSONArray == null) {
            jSONArray = new JSONArray();
            jSONArray.put(str);
            InAppMessageManager.getInstance(this.b).e(jSONArray.toString());
            return true;
        } else if (!a(jSONArray, str)) {
            return true;
        } else {
            if (jSONArray.length() < 10) {
                return false;
            }
            jSONArray.put(str);
            InAppMessageManager.getInstance(this.b).e(jSONArray.toString());
            return true;
        }
    }

    private boolean a(JSONArray jSONArray, String str) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                if (jSONArray.getString(i).equals(str)) {
                    return true;
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void a() {
        if (TextUtils.isEmpty(this.c.trim())) {
            if (UmLog.LOG) {
                Toast.makeText(this.b, "The label of card message should not be empty", 1).show();
            }
            UmLog.e(a, "The label of card message should not be empty");
        } else if (!a(this.c)) {
            UmLog.e(a, "The maximum number labels of card message is 10");
        } else if (InAppMessageManager.a) {
            InAppMessageManager.getInstance(this.b).a(this.c, (IUmengInAppMessageCallback) this);
        } else if (System.currentTimeMillis() - InAppMessageManager.getInstance(this.b).b(this.c) > ((long) InAppMessageManager.b)) {
            InAppMessageManager.getInstance(this.b).a(this.c, (IUmengInAppMessageCallback) this);
        } else {
            onCardMessage(null);
        }
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                UmengCardMessage umengCardMessage = new UmengCardMessage();
                umengCardMessage.a(this.g);
                Bundle bundle = new Bundle();
                bundle.putString("label", this.c);
                bundle.putString("msg", this.e.getRaw().toString());
                bundle.putByteArray("bitmapByte", toByteArray);
                umengCardMessage.setArguments(bundle);
                umengCardMessage.show(((Activity) this.b).getFragmentManager(), this.c);
                InAppMessageManager.getInstance(this.b).a(this.e.msg_id, 1);
                InAppMessageManager.getInstance(this.b).g(this.c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSplashMessage(UInAppMessage uInAppMessage) {
    }

    public void onCardMessage(UInAppMessage uInAppMessage) {
        UInAppMessage uInAppMessage2;
        Object c = InAppMessageManager.getInstance(this.b).c(this.c);
        if (!TextUtils.isEmpty(c)) {
            try {
                uInAppMessage2 = new UInAppMessage(new JSONObject(c));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (uInAppMessage != null) {
                if (!(uInAppMessage2 == null || uInAppMessage.msg_id.equals(uInAppMessage2.msg_id))) {
                    InAppMessageManager.getInstance(this.b).a(new File(h.d(this.b, uInAppMessage2.msg_id)));
                }
                this.e = uInAppMessage;
            } else if (uInAppMessage2 != null) {
                this.e = uInAppMessage2;
            } else {
                return;
            }
            if (this.e.show_type == 1 && !b(this.c)) {
                InAppMessageManager.getInstance(this.b).a(this.e.msg_id, 0);
            }
            if (InAppMessageManager.getInstance(this.b).b(this.e) && InAppMessageManager.getInstance(this.b).c(this.e)) {
                UImageLoadTask uImageLoadTask = new UImageLoadTask(this.b, this.e);
                uImageLoadTask.a((ImageLoaderCallback) this);
                uImageLoadTask.execute(new String[]{this.e.image_url});
                return;
            }
        }
        uInAppMessage2 = null;
        if (uInAppMessage != null) {
            InAppMessageManager.getInstance(this.b).a(new File(h.d(this.b, uInAppMessage2.msg_id)));
            this.e = uInAppMessage;
        } else if (uInAppMessage2 != null) {
            this.e = uInAppMessage2;
        } else {
            return;
        }
        InAppMessageManager.getInstance(this.b).a(this.e.msg_id, 0);
        if (InAppMessageManager.getInstance(this.b).b(this.e)) {
        }
    }

    public void onLoadImage(Bitmap[] bitmapArr) {
        if (!this.d) {
            a(bitmapArr[0]);
        }
        InAppMessageManager.getInstance(this.b).a(this.e, this.c);
    }

    private boolean b(String str) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(InAppMessageManager.getInstance(this.b).h(str));
        Calendar instance2 = Calendar.getInstance();
        if (instance.get(6) == instance2.get(6) && instance.get(1) == instance2.get(1)) {
            return true;
        }
        return false;
    }
}
