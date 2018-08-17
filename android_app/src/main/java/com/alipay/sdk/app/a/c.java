package com.alipay.sdk.app.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.g.b;
import com.alipay.sdk.util.a;
import com.umeng.socialize.common.SocializeConstants;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class c {
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    String g;
    String h;
    String i = "";
    String j;

    public c(Context context) {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        this.a = String.format("123456789,%s", new Object[]{format});
        this.c = a(context);
        format = a("15.2.2");
        String a = a("h.a.3.2.2");
        this.d = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{format, a});
        format = a(b.a().a);
        a = a(com.alipay.sdk.f.b.a().c());
        this.e = String.format("%s,%s,-,-,-", new Object[]{format, a});
        format = a(a.d(context));
        a = anet.channel.strategy.dispatch.c.ANDROID;
        String a2 = a(VERSION.RELEASE);
        String a3 = a(Build.MODEL);
        String str = SocializeConstants.OP_DIVIDER_MINUS;
        String a4 = a(a.a(context).a());
        String a5 = a(a.b(context).p);
        String a6 = a(a.a(context).b());
        this.f = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{format, a, a2, a3, str, a4, a5, "gw", a6});
        this.g = SocializeConstants.OP_DIVIDER_MINUS;
        this.h = SocializeConstants.OP_DIVIDER_MINUS;
        this.j = SocializeConstants.OP_DIVIDER_MINUS;
    }

    public final void a(String str, String str2, Throwable th) {
        a(str, str2, a(th));
    }

    public final void a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.i)) {
            str5 = str5 + "^";
        }
        this.i += (str5 + String.format("%s,%s,%s,%s", new Object[]{str, str2, a(str3), str4}));
    }

    public final void a(String str, String str2, String str3) {
        a(str, str2, str3, SocializeConstants.OP_DIVIDER_MINUS);
    }

    static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace(SocializeConstants.OP_DIVIDER_MINUS, "=").replace("^", "~");
    }

    static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + " 》 ");
                }
            }
        } catch (Throwable th2) {
        }
        return stringBuffer.toString();
    }

    private static String a(Context context) {
        String str = SocializeConstants.OP_DIVIDER_MINUS;
        String str2 = SocializeConstants.OP_DIVIDER_MINUS;
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                str2 = applicationContext.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (Throwable th) {
            }
        }
        return String.format("%s,%s,-,-,-", new Object[]{str, str2});
    }
}
