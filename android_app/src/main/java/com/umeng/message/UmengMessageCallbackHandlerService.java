package com.umeng.message;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.h;
import com.umeng.message.provider.a;
import java.io.File;
import java.io.FileOutputStream;
import org.json.JSONObject;

public class UmengMessageCallbackHandlerService extends IntentService {
    public static final String TAG = UmengMessageCallbackHandlerService.class.getName();
    private Context a = this;

    class UmengMessageCallbackHandlerService_2 implements Runnable {
        final /* synthetic */ UmengMessageCallbackHandlerService a;

        UmengMessageCallbackHandlerService_2(UmengMessageCallbackHandlerService umengMessageCallbackHandlerService) {
            this.a = umengMessageCallbackHandlerService;
        }

        public void run() {
            UTrack.getInstance(this.a.a).trackRegister();
        }
    }

    class UmengMessageCallbackHandlerService_3 implements Runnable {
        final /* synthetic */ UmengMessageCallbackHandlerService a;

        UmengMessageCallbackHandlerService_3(UmengMessageCallbackHandlerService umengMessageCallbackHandlerService) {
            this.a = umengMessageCallbackHandlerService;
        }

        public void run() {
            PushAgent.getInstance(this.a.a).onAppStart();
        }
    }

    public UmengMessageCallbackHandlerService() {
        super("UmengMessageCallbackHandlerService");
    }

    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "processName=" + h.a(this.a, Process.myPid()));
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION)) {
                try {
                    final String stringExtra = intent.getStringExtra(MsgConstant.KEY_REGISTRATION_ID);
                    boolean booleanExtra = intent.getBooleanExtra("status", false);
                    Log.d(TAG, "enable(): register-->:" + stringExtra + ",status:" + booleanExtra);
                    IUmengRegisterCallback registerCallback = PushAgent.getInstance(this.a).getRegisterCallback();
                    if (booleanExtra) {
                        new Thread(new Runnable(this) {
                            final /* synthetic */ UmengMessageCallbackHandlerService b;

                            public void run() {
                                try {
                                    String deviceToken = MessageSharedPrefs.getInstance(this.b.a).getDeviceToken();
                                    if (stringExtra != null && deviceToken != null && !stringExtra.equals(deviceToken)) {
                                        MessageSharedPrefs.getInstance(this.b.a).setHasResgister(false);
                                        MessageSharedPrefs.getInstance(this.b.a).setDeviceToken(stringExtra);
                                        this.b.a(this.b.a, stringExtra);
                                        ContentResolver contentResolver = this.b.a.getContentResolver();
                                        a.a(this.b.a);
                                        contentResolver.delete(a.e, null, null);
                                        MessageSharedPrefs.getInstance(this.b.a).resetTags();
                                    }
                                } catch (Exception e) {
                                    if (e != null) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();
                        Handler handler = new Handler(getMainLooper());
                        handler.postDelayed(new UmengMessageCallbackHandlerService_2(this), 600);
                        if (registerCallback != null) {
                            registerCallback.onSuccess(stringExtra);
                            handler.postDelayed(new UmengMessageCallbackHandlerService_3(this), 10000);
                        }
                    } else if (registerCallback != null) {
                        registerCallback.onFailure(intent.getStringExtra("s"), intent.getStringExtra("s1"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION)) {
                try {
                    r0 = intent.getBooleanExtra("status", false);
                    r1 = PushAgent.getInstance(this.a).getCallback();
                    Log.d(TAG, "enable()-->status:" + r0);
                    if (r0) {
                        if (r1 != null) {
                            r1.onSuccess();
                        }
                    } else if (r1 != null) {
                        r1.onFailure(intent.getStringExtra("s"), intent.getStringExtra("s1"));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION)) {
                try {
                    r0 = intent.getBooleanExtra("status", false);
                    r1 = PushAgent.getInstance(this.a).getCallback();
                    Log.d(TAG, "disable()-->status:" + r0);
                    if (r0) {
                        if (r1 != null) {
                            r1.onSuccess();
                        }
                    } else if (r1 != null) {
                        r1.onFailure(intent.getStringExtra("s"), intent.getStringExtra("s1"));
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION)) {
                try {
                    UHandler adHandler;
                    UMessage uMessage = new UMessage(new JSONObject(intent.getStringExtra("body")));
                    if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                        adHandler = PushAgent.getInstance(this.a).getAdHandler();
                    } else {
                        adHandler = PushAgent.getInstance(this.a).getMessageHandler();
                    }
                    if (adHandler != null) {
                        adHandler.handleMessage(this.a, uMessage);
                    }
                } catch (Exception e222) {
                    Log.d(TAG, e222.toString());
                }
            }
        }
    }

    private void a(Context context, String str) throws Exception {
        File file = new File(context.getExternalFilesDir(null).getPath() + "/deviceToken");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();
    }
}
