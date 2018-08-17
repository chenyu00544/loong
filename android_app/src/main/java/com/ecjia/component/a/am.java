package com.ecjia.component.a;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ecjia.component.view.d;
import com.ecjia.hamster.activity.ECJiaUpdateActivity;
import com.ecjia.hamster.model.ECJia_VERSION;
import com.ecjia.hamster.model.ap;
import com.ecmoban.android.missmall.R;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaVersionUpdateUtil */
public class am extends e {
    static am a;
    d b;
    private boolean c;
    private Context d;
    private b e;

    /* compiled from: ECJiaVersionUpdateUtil */
    private static class a {
        Dialog a;
        TextView b;
        Button c;
        Button d;
        Button e;

        /* compiled from: ECJiaVersionUpdateUtil */
        class am_a_3 implements OnClickListener {
            final /* synthetic */ a a;

            am_a_3(a aVar) {
                this.a = aVar;
            }

            public void onClick(View view) {
                this.a.b();
            }
        }

        public a(final Context context, final ECJia_VERSION eCJia_VERSION) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.umeng_update_dialog, null);
            this.b = (TextView) inflate.findViewById(R.id.umeng_update_content);
            this.b.setText(eCJia_VERSION.getChangelog());
            this.c = (Button) inflate.findViewById(R.id.umeng_update_id_ok);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a c;

                public void onClick(View view) {
                    Intent intent = new Intent(context, ECJiaUpdateActivity.class);
                    intent.putExtra("version", eCJia_VERSION);
                    context.startActivity(intent);
                    this.c.b();
                }
            });
            this.e = (Button) inflate.findViewById(R.id.umeng_update_id_ignore);
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a c;

                public void onClick(View view) {
                    context.getSharedPreferences("version_update", 0).edit().putString("ignore_version", eCJia_VERSION.getVersion()).commit();
                    this.c.b();
                }
            });
            this.d = (Button) inflate.findViewById(R.id.umeng_update_id_cancel);
            this.d.setOnClickListener(new am_a_3(this));
            this.a = new Dialog(context, R.style.dialog);
            this.a.setContentView(inflate);
            this.a.setCanceledOnTouchOutside(false);
        }

        public void a() {
            if (this.a != null) {
                this.a.show();
            }
        }

        public void b() {
            if (this.a != null && this.a.isShowing()) {
                this.a.dismiss();
            }
        }
    }

    /* compiled from: ECJiaVersionUpdateUtil */
    public interface b {
        void a();

        void a(int i, ECJia_VERSION eCJia_VERSION);
    }

    public am(Context context) {
        super(context);
        this.d = context;
        this.s.a((com.ecjia.component.a.a.b) this);
    }

    public static am a(Context context) {
        if (a == null) {
            synchronized (am.class) {
                if (a == null) {
                    a = new am(context);
                }
            }
        }
        return a;
    }

    public void b(Context context) {
        this.c = false;
        this.q = "app/upgrade/check";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("channel_code", "ecjia");
            jSONObject.put("app_key", "742a672133ae8e432bb316dc4f8cfd49");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.a(this.q, jSONObject.toString(), "https://cloud.ecjia.com/sites/api/index.php?url=");
    }

    public void c(Context context) {
        this.c = true;
        this.b = d.a(context);
        this.b.a("正在检测中...");
        this.b.setCancelable(false);
        this.b.show();
        String str = "app/upgrade/check";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("channel_code", "ecjia");
            jSONObject.put("app_key", "742a672133ae8e432bb316dc4f8cfd49");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.a(this.q, jSONObject.toString(), "https://cloud.ecjia.com/sites/api/index.php?url=");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r1 = -1;
        r0 = 0;
        r5 = 1;
        super.a(r7, r8);
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0092 }
        r2.<init>(r8);	 Catch:{ JSONException -> 0x0092 }
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0092 }
        r3.<init>();	 Catch:{ JSONException -> 0x0092 }
        r4 = "===";
        r3 = r3.append(r4);	 Catch:{ JSONException -> 0x0092 }
        r3 = r3.append(r7);	 Catch:{ JSONException -> 0x0092 }
        r4 = "返回===";
        r3 = r3.append(r4);	 Catch:{ JSONException -> 0x0092 }
        r4 = r2.toString();	 Catch:{ JSONException -> 0x0092 }
        r3 = r3.append(r4);	 Catch:{ JSONException -> 0x0092 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x0092 }
        com.ecjia.a.q.a(r3);	 Catch:{ JSONException -> 0x0092 }
        r3 = "status";
        r3 = r2.optJSONObject(r3);	 Catch:{ JSONException -> 0x0092 }
        r3 = com.ecjia.hamster.model.ax.a(r3);	 Catch:{ JSONException -> 0x0092 }
        r6.r = r3;	 Catch:{ JSONException -> 0x0092 }
        r3 = r7.hashCode();	 Catch:{ JSONException -> 0x0092 }
        switch(r3) {
            case 247984103: goto L_0x0058;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x0092 }
    L_0x0042:
        r0 = r1;
    L_0x0043:
        switch(r0) {
            case 0: goto L_0x0061;
            default: goto L_0x0046;
        };	 Catch:{ JSONException -> 0x0092 }
    L_0x0046:
        r0 = r6.b;	 Catch:{ JSONException -> 0x0092 }
        if (r0 == 0) goto L_0x004f;
    L_0x004a:
        r0 = r6.b;	 Catch:{ JSONException -> 0x0092 }
        r0.dismiss();	 Catch:{ JSONException -> 0x0092 }
    L_0x004f:
        r6.g();	 Catch:{ JSONException -> 0x0092 }
        r0 = r6.r;	 Catch:{ JSONException -> 0x0092 }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x0092 }
    L_0x0057:
        return;
    L_0x0058:
        r3 = "app/upgrade/check";
        r3 = r7.equals(r3);	 Catch:{ JSONException -> 0x0092 }
        if (r3 == 0) goto L_0x0042;
    L_0x0060:
        goto L_0x0043;
    L_0x0061:
        r0 = r6.c;	 Catch:{ JSONException -> 0x0092 }
        if (r0 == 0) goto L_0x00e1;
    L_0x0065:
        r0 = r6.r;	 Catch:{ JSONException -> 0x0092 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0092 }
        if (r0 != r5) goto L_0x00d6;
    L_0x006d:
        r0 = "data";
        r0 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x0092 }
        r0 = com.ecjia.hamster.model.ECJia_VERSION.fromJson(r0);	 Catch:{ JSONException -> 0x0092 }
        r1 = r0.getVersion();	 Catch:{ JSONException -> 0x0092 }
        r2 = r6.d;	 Catch:{ JSONException -> 0x0092 }
        r2 = d(r2);	 Catch:{ JSONException -> 0x0092 }
        r1 = b(r1, r2);	 Catch:{ JSONException -> 0x0092 }
        if (r1 <= 0) goto L_0x00bc;
    L_0x0087:
        r1 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        if (r1 == 0) goto L_0x0046;
    L_0x008b:
        r1 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        r2 = 1;
        r1.a(r2, r0);	 Catch:{ JSONException -> 0x0092 }
        goto L_0x0046;
    L_0x0092:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r6.e;
        r0.a();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x0057;
    L_0x00bc:
        if (r1 != 0) goto L_0x00ca;
    L_0x00be:
        r1 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        if (r1 == 0) goto L_0x0046;
    L_0x00c2:
        r1 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        r2 = 0;
        r1.a(r2, r0);	 Catch:{ JSONException -> 0x0092 }
        goto L_0x0046;
    L_0x00ca:
        r1 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        if (r1 == 0) goto L_0x0046;
    L_0x00ce:
        r1 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        r2 = -1;
        r1.a(r2, r0);	 Catch:{ JSONException -> 0x0092 }
        goto L_0x0046;
    L_0x00d6:
        r0 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        if (r0 == 0) goto L_0x0046;
    L_0x00da:
        r0 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        r0.a();	 Catch:{ JSONException -> 0x0092 }
        goto L_0x0046;
    L_0x00e1:
        r0 = r6.r;	 Catch:{ JSONException -> 0x0092 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0092 }
        if (r0 != r5) goto L_0x0046;
    L_0x00e9:
        r0 = "data";
        r0 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x0092 }
        r0 = com.ecjia.hamster.model.ECJia_VERSION.fromJson(r0);	 Catch:{ JSONException -> 0x0092 }
        r1 = r6.d;	 Catch:{ JSONException -> 0x0092 }
        r2 = "version_update";
        r3 = 0;
        r1 = r1.getSharedPreferences(r2, r3);	 Catch:{ JSONException -> 0x0092 }
        r2 = "ignore_version";
        r3 = "";
        r1 = r1.getString(r2, r3);	 Catch:{ JSONException -> 0x0092 }
        r2 = r0.getVersion();	 Catch:{ JSONException -> 0x0092 }
        r1 = r2.equals(r1);	 Catch:{ JSONException -> 0x0092 }
        if (r1 != 0) goto L_0x0057;
    L_0x010e:
        r1 = r0.getVersion();	 Catch:{ JSONException -> 0x0092 }
        r2 = r6.d;	 Catch:{ JSONException -> 0x0092 }
        r2 = d(r2);	 Catch:{ JSONException -> 0x0092 }
        r1 = b(r1, r2);	 Catch:{ JSONException -> 0x0092 }
        if (r1 <= 0) goto L_0x0046;
    L_0x011e:
        r1 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        if (r1 == 0) goto L_0x0046;
    L_0x0122:
        r1 = r6.e;	 Catch:{ JSONException -> 0x0092 }
        r2 = 1;
        r1.a(r2, r0);	 Catch:{ JSONException -> 0x0092 }
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.am.a(java.lang.String, java.lang.String):void");
    }

    public static String d(Context context) {
        String str = "";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            Log.e("msg", e.getMessage());
            return str;
        }
    }

    public static int b(String str, String str2) {
        if (str.equals(str2)) {
            return 0;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int min = Math.min(split.length, split2.length);
        int i = 0;
        int i2 = 0;
        while (i2 < min) {
            i = Integer.parseInt(split[i2]) - Integer.parseInt(split2[i2]);
            if (i != 0) {
                break;
            }
            i2++;
        }
        if (i == 0) {
            for (i = i2; i < split.length; i++) {
                if (Integer.parseInt(split[i]) > 0) {
                    return 1;
                }
            }
            while (i2 < split2.length) {
                if (Integer.parseInt(split2[i2]) > 0) {
                    return -1;
                }
                i2++;
            }
            return 0;
        }
        return i > 0 ? 1 : -1;
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    public static void a(Context context, ECJia_VERSION eCJia_VERSION) {
        new a(context, eCJia_VERSION).a();
    }
}
