package com.ecmoban.android.missmall;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;
import android.view.View;
import android.view.View.OnClickListener;

import com.baidu.mapapi.SDKInitializer;
import com.ecjia.a.a.b;
import com.ecjia.a.b.a;
import com.ecjia.a.j;
import com.ecjia.a.q;
import com.ecjia.a.v;
import com.ecjia.hamster.activity.ECJiaWebViewActivity;
import com.ecjia.hamster.adapter.aj;
import com.ecjia.hamster.model.ECJia_PAYMENT;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.at;
import com.ecjia.hamster.model.bf;
import com.ecjia.hamster.model.k;
import com.ecjia.hamster.model.z;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.Constants;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.utils.BitmapUtils;

import de.greenrobot.event.c;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x.Ext;

public class ECJiaApplication extends MultiDexApplication {
    public ActivityManager a;
    public String b;
    public boolean c = false;
    public ArrayList<ECJia_PAYMENT> d = new ArrayList();
    public ArrayList<ECJia_PAYMENT> e = new ArrayList();
    public ArrayList<ECJia_PAYMENT> f = new ArrayList();
    public ArrayList<ECJia_PAYMENT> g = new ArrayList();
    private ArrayList<at> h = new ArrayList();
    private bf i = new bf();
    private int j = 0;
    private String k;
    private k l;

    class ECJiaApplication_1 implements IUmengRegisterCallback {
        final /* synthetic */ ECJiaApplication a;

        ECJiaApplication_1(ECJiaApplication eCJiaApplication) {
            this.a = eCJiaApplication;
        }

        public void onSuccess(String str) {
            q.a("===umeng-deviceToken===" + str);
            v.a(this.a, str);
        }

        public void onFailure(String str, String str2) {
            q.b("===umeng-deviceToken===" + str + str2);
        }
    }

    class ECJiaApplication_2 extends UmengMessageHandler {
        final /* synthetic */ ECJiaApplication a;

        ECJiaApplication_2(ECJiaApplication eCJiaApplication) {
            this.a = eCJiaApplication;
        }

        public void handleMessage(Context context, UMessage uMessage) {
            q.b("===handleMessage===");
            z zVar = new z();
            zVar.a(1);
            zVar.m(uMessage.title);
            zVar.n(uMessage.text);
            zVar.l(uMessage.custom);
            zVar.h(uMessage.msg_id);
            zVar.i(uMessage.after_open);
            zVar.j(uMessage.url);
            zVar.k(uMessage.activity);
            if (uMessage.extra != null) {
                zVar.f((String) uMessage.extra.get("open_type"));
                if ("webview".equals(zVar.g())) {
                    zVar.c((String) uMessage.extra.get("url"));
                } else if ("goods_list".equals(zVar.g())) {
                    zVar.b((String) uMessage.extra.get("category_id"));
                } else if ("goods_comment".equals(zVar.g())) {
                    zVar.d((String) uMessage.extra.get("goods_id"));
                } else if ("goods_detail".equals(zVar.g())) {
                    zVar.a((String) uMessage.extra.get("goods_id"));
                } else if ("orders_detail".equals(zVar.g())) {
                    zVar.e((String) uMessage.extra.get("order_id"));
                } else if ("search".equals(zVar.g())) {
                    zVar.p((String) uMessage.extra.get("keyword"));
                }
            }
            aj.a(context).a(zVar);
            if (this.a.a()) {
                b bVar;
                if ((this.a.b + ".ECJiaPushActivity").equals(this.a.b())) {
                    bVar = new b("UPDATE_MESSAGE");
                    bVar.a(zVar);
                    c.a().c(bVar);
                } else if ("com.ecjia.hamster.activity.ECJiaMainActivity".equals(this.a.b())) {
                    bVar = new b("UPDATE_MESSAGE");
                    bVar.a(zVar);
                    c.a().c(bVar);
                }
                if (uMessage.extra != null) {
                    this.a.a(uMessage);
                } else {
                    this.a.b(uMessage);
                }
            }
            super.handleMessage(context, uMessage);
        }

        public void dealWithCustomMessage(Context context, final UMessage uMessage) {
            new Handler(this.a.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ ECJiaApplication_2 b;

                public void run() {
                    q.b("===dealWithCustomMessage===");
                    UTrack.getInstance(this.b.a.getApplicationContext()).trackMsgClick(uMessage);
                }
            });
        }
    }

    class ECJiaApplication_3 extends UmengNotificationClickHandler {
        final /* synthetic */ ECJiaApplication a;

        ECJiaApplication_3(ECJiaApplication eCJiaApplication) {
            this.a = eCJiaApplication;
        }

        public void openUrl(Context context, UMessage uMessage) {
            super.openUrl(context, uMessage);
            aj.a(this.a.getApplicationContext()).a(uMessage.msg_id, 0);
            Intent intent = new Intent(context, ECJiaWebViewActivity.class);
            intent.putExtra("url", uMessage.url);
            intent.putExtra("title", uMessage.title);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }

        public void dealWithCustomAction(Context context, UMessage uMessage) {
            aj.a(this.a.getApplicationContext()).a(uMessage.msg_id, 0);
            Intent intent = new Intent(context, ECJiaPushActivity.class);
            intent.putExtra("refresh", true);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }

        public void launchApp(Context context, UMessage uMessage) {
            aj.a(this.a.getApplicationContext()).a(uMessage.msg_id, 0);
            v.a(context, uMessage);
            super.launchApp(context, uMessage);
        }
    }

    public void onCreate() {
        super.onCreate();
        Ext.init(this);
        a.a((Application) this);
        SDKInitializer.initialize(this);
        i();
        h();
        k();
        j();
    }

    private void h() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.KEY_USER_ID, 0);
        ap c = ap.c();
        c.a(sharedPreferences.getString("uid", ""));
        c.b(sharedPreferences.getString("sid", ""));
    }

    private void i() {
        com.ecjia.a.z.a();
    }

    private void j() {
        ImageLoader.getInstance().init(new Builder(this).defaultDisplayImageOptions(new
                DisplayImageOptions.Builder().showStubImage(R.drawable.default_image)
                .showImageForEmptyUri((int) R.drawable.default_image).showImageOnFail((int) R
                        .drawable.default_image).showImageOnLoading((int) R.drawable
                        .default_image).cacheInMemory(true).cacheOnDisk(true).imageScaleType
                        (ImageScaleType.IN_SAMPLE_INT).bitmapConfig(Config.RGB_565).build())
                .threadPriority(3).memoryCache(new WeakMemoryCache()).memoryCacheSize(BitmapUtils
                        .COMPRESS_FLAG).denyCacheImageMultipleSizesInMemory()
                .memoryCacheExtraOptions(ErrorCode.APP_NOT_BIND, ErrorCode.APP_NOT_BIND)
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder
                        (QueueProcessingType.LIFO).build());
    }

    private void k() {
        PushAgent instance = PushAgent.getInstance(this);
        this.b = getPackageName();
        this.a = (ActivityManager) getSystemService("activity");
        q.a("===umeng-deviceToken0===" + PushAgent.getInstance(this).getRegistrationId());
        instance.register(new ECJiaApplication_1(this));
        instance.setMessageHandler(new ECJiaApplication_2(this));
        instance.setNotificationClickHandler(new ECJiaApplication_3(this));
    }

    void a(final UMessage uMessage) {
        final com.ecjia.component.view.c cVar = new com.ecjia.component.view.c
                (getApplicationContext(), uMessage.title, uMessage.text);
        cVar.a(2);
        cVar.f.setText("打开");
        cVar.g.setText("忽略");
        cVar.b(new OnClickListener(this) {
            final /* synthetic */ ECJiaApplication c;

            public void onClick(View view) {
                if ((this.c.b + ".ECJiaPushActivity").equals(this.c.b())) {
                    c.a().c(new b("refresh_push_adpter"));
                }
                cVar.b();
                aj.a(this.c.getApplicationContext()).a(uMessage.msg_id, 0);
                v.a(this.c, uMessage);
            }
        });
        cVar.c(new OnClickListener(this) {
            final /* synthetic */ ECJiaApplication b;

            public void onClick(View view) {
                if ((this.b.b + ".ECJiaPushActivity").equals(this.b.b())) {
                    c.a().c(new b("refresh_push_adpter"));
                }
                cVar.b();
            }
        });
        cVar.a.getWindow().setType(2003);
        cVar.a();
    }

    void b(UMessage uMessage) {
        final com.ecjia.component.view.c cVar = new com.ecjia.component.view.c
                (getApplicationContext(), uMessage.title, uMessage.text);
        cVar.a(1);
        cVar.h.setText("确定");
        cVar.a(new OnClickListener(this) {
            final /* synthetic */ ECJiaApplication b;

            public void onClick(View view) {
                if ((this.b.b + ".ECJiaPushActivity").equals(this.b.b())) {
                    c.a().c(new b("refresh_push_adpter"));
                }
                cVar.b();
            }
        });
        cVar.a.getWindow().setType(2003);
        cVar.a();
    }

    public boolean a() {
        List runningAppProcesses = this.a.getRunningAppProcesses();
        if (runningAppProcesses.size() > 0) {
            q.a("top Activity = " + ((RunningAppProcessInfo) runningAppProcesses.get(0)).getClass
                    ().getName());
            if (this.b.equals(((RunningAppProcessInfo) runningAppProcesses.get(0)).getClass()
                    .getName())) {
                q.a("在前台1");
                return true;
            }
        }
        q.a("在后台1");
        return false;
    }

    public String b() {
        List runningTasks = ((ActivityManager) getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null) {
            return null;
        }
        q.a("(runningTaskInfos.get(0).topActivity).toString()================" + (
                (RunningTaskInfo) runningTasks.get(0)).topActivity.getClassName());
        return ((RunningTaskInfo) runningTasks.get(0)).topActivity.getClassName();
    }

    public void c() {
        this.j = 0;
        this.i = new bf();
    }

    public k d() {
        if (this.l == null) {
            try {
                this.l = k.a(new JSONObject(j.a("sdcard/android/data/com.ecmoban.android" +
                        ".missmall/shop_config", "shopconfig")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this.l;
    }

    public bf e() {
        return this.i;
    }

    public void a(bf bfVar) {
        this.i = bfVar;
    }

    public void a(String str) {
        this.k = str;
    }

    public ArrayList<at> f() {
        return this.h;
    }

    public void a(ArrayList<at> arrayList) {
        this.h = arrayList;
    }

    public int g() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }
}
