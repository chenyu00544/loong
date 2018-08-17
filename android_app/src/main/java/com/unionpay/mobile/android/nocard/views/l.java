package com.unionpay.mobile.android.nocard.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.AuthActivity;
import com.umeng.analytics.pro.x;
import com.umeng.socialize.common.SocializeConstants;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.g.c;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.utils.q;
import com.unionpay.mobile.android.utils.r;
import com.unionpay.mobile.android.utils.r.a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l extends b implements a {
    private String A;
    private int B;
    private volatile int C;
    private boolean D;
    private boolean E;
    private JSONArray F;
    private e G;
    private long H;
    private Activity I;
    private boolean J;
    private boolean K;
    public List<c> r;
    public List<c> s;
    int t;
    String u;
    String v;
    String w;
    int x;
    int y;
    private ProgressBar z;

    public l(Context context) {
        super(context);
        this.z = null;
        this.A = null;
        this.B = 0;
        this.C = 0;
        this.r = null;
        this.s = null;
        this.D = false;
        this.E = false;
        this.K = false;
        this.t = 0;
        this.u = "";
        this.v = "";
        this.w = "";
        this.x = d_ResultType.SHORT_URL;
        this.y = 5;
        this.f = 1;
        this.q = "init";
        d();
        this.I = (Activity) context;
        try {
            this.J = com.unionpay.mobile.android.nocard.utils.a.a(this.I.getIntent(), this.a);
        } catch (Exception e) {
            this.J = false;
        }
        if (this.a.aM) {
            setVisibility(8);
            this.b.a(com.unionpay.mobile.android.f.c.bD.c);
        }
        u();
    }

    private final boolean D() {
        try {
            String string = this.F != null ? this.F.getString(3) : null;
            return (string == null || string.length() <= 0 || "null".equalsIgnoreCase(string)) ? false : true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void E() {
        /*
        r8 = this;
        r5 = 2;
        r0 = 0;
        r1 = 1;
        monitor-enter(r8);
        r2 = "uppay";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ae }
        r4 = "showContentView() +++";
        r3.<init>(r4);	 Catch:{ all -> 0x00ae }
        r4 = r8.C;	 Catch:{ all -> 0x00ae }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00ae }
        r3 = r3.toString();	 Catch:{ all -> 0x00ae }
        com.unionpay.mobile.android.utils.k.c(r2, r3);	 Catch:{ all -> 0x00ae }
        r2 = r8.C;	 Catch:{ all -> 0x00ae }
        if (r2 == r5) goto L_0x0055;
    L_0x001e:
        r2 = r8.E;	 Catch:{ all -> 0x00ae }
        if (r2 != 0) goto L_0x0053;
    L_0x0022:
        r3 = r1;
    L_0x0023:
        r2 = r8.E;	 Catch:{ all -> 0x00ae }
        if (r2 == 0) goto L_0x004d;
    L_0x0027:
        r2 = "1";
        r4 = r8.a;	 Catch:{ all -> 0x00ae }
        r4 = r4.an;	 Catch:{ all -> 0x00ae }
        r2 = r2.equalsIgnoreCase(r4);	 Catch:{ all -> 0x00ae }
        if (r2 != 0) goto L_0x004d;
    L_0x0033:
        r2 = r8.a;	 Catch:{ all -> 0x00ae }
        r2 = r2.ao;	 Catch:{ all -> 0x00ae }
        r4 = r8.f(r2);	 Catch:{ all -> 0x00ae }
        if (r4 == 0) goto L_0x0284;
    L_0x003d:
        r4 = 69889; // 0x11101 float:9.7935E-41 double:3.453E-319;
        r2 = r2 & r4;
        if (r2 != 0) goto L_0x0284;
    L_0x0043:
        r2 = r1;
    L_0x0044:
        if (r2 != 0) goto L_0x004d;
    L_0x0046:
        r2 = r8.a;	 Catch:{ all -> 0x00ae }
        r2 = r2.aD;	 Catch:{ all -> 0x00ae }
        if (r2 != 0) goto L_0x004d;
    L_0x004c:
        r0 = r1;
    L_0x004d:
        if (r3 != 0) goto L_0x0051;
    L_0x004f:
        if (r0 == 0) goto L_0x0055;
    L_0x0051:
        monitor-exit(r8);
        return;
    L_0x0053:
        r3 = r0;
        goto L_0x0023;
    L_0x0055:
        r0 = r8.D;	 Catch:{ all -> 0x00ae }
        if (r0 != 0) goto L_0x0051;
    L_0x0059:
        r0 = 1;
        r8.D = r0;	 Catch:{ all -> 0x00ae }
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.aM;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0067;
    L_0x0062:
        r0 = r8.b;	 Catch:{ all -> 0x00ae }
        r0.c();	 Catch:{ all -> 0x00ae }
    L_0x0067:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r2 = r0.ao;	 Catch:{ all -> 0x00ae }
        r0 = "1";
        r3 = r8.a;	 Catch:{ all -> 0x00ae }
        r3 = r3.an;	 Catch:{ all -> 0x00ae }
        r0 = r0.equalsIgnoreCase(r3);	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x00b8;
    L_0x0077:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.aC;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x00b1;
    L_0x007d:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r1 = 0;
        r0.q = r1;	 Catch:{ all -> 0x00ae }
    L_0x0082:
        r0 = "0";
        r1 = r8.a;	 Catch:{ all -> 0x00ae }
        r1 = r1.an;	 Catch:{ all -> 0x00ae }
        r0 = r0.equalsIgnoreCase(r1);	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x016b;
    L_0x008e:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.q;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x009e;
    L_0x0094:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.q;	 Catch:{ all -> 0x00ae }
        r0 = r0.size();	 Catch:{ all -> 0x00ae }
        if (r0 > 0) goto L_0x016b;
    L_0x009e:
        r0 = r8.f(r2);	 Catch:{ all -> 0x00ae }
        if (r0 != 0) goto L_0x016b;
    L_0x00a4:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.ap;	 Catch:{ all -> 0x00ae }
        r1 = "fail";
        r8.c(r0, r1);	 Catch:{ all -> 0x00ae }
        goto L_0x0051;
    L_0x00ae:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x00b1:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r1 = r8.r;	 Catch:{ all -> 0x00ae }
        r0.q = r1;	 Catch:{ all -> 0x00ae }
        goto L_0x0082;
    L_0x00b8:
        r0 = com.unionpay.mobile.android.g.b.aA;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x00ec;
    L_0x00bc:
        r0 = com.unionpay.mobile.android.g.b.aB;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x00ec;
    L_0x00c0:
        r0 = com.unionpay.mobile.android.g.b.bn;	 Catch:{ all -> 0x00ae }
        if (r0 != 0) goto L_0x00ec;
    L_0x00c4:
        r0 = r8.s;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x00ec;
    L_0x00c8:
        r0 = r8.s;	 Catch:{ all -> 0x00ae }
        r0 = r0.size();	 Catch:{ all -> 0x00ae }
        if (r0 <= 0) goto L_0x00ec;
    L_0x00d0:
        r0 = r8.s;	 Catch:{ all -> 0x00ae }
        r3 = r0.iterator();	 Catch:{ all -> 0x00ae }
    L_0x00d6:
        r0 = r3.hasNext();	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x00ec;
    L_0x00dc:
        r0 = r3.next();	 Catch:{ all -> 0x00ae }
        r0 = (com.unionpay.mobile.android.g.c) r0;	 Catch:{ all -> 0x00ae }
        r0 = r0.c();	 Catch:{ all -> 0x00ae }
        if (r0 != r1) goto L_0x00d6;
    L_0x00e8:
        r3.remove();	 Catch:{ all -> 0x00ae }
        goto L_0x00d6;
    L_0x00ec:
        r0 = "0";
        r1 = r8.a;	 Catch:{ all -> 0x00ae }
        r1 = r1.an;	 Catch:{ all -> 0x00ae }
        r0 = r0.equalsIgnoreCase(r1);	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0133;
    L_0x00f8:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r1 = r8.s;	 Catch:{ all -> 0x00ae }
        r0.q = r1;	 Catch:{ all -> 0x00ae }
    L_0x00fe:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.q;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0082;
    L_0x0104:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.q;	 Catch:{ all -> 0x00ae }
        r0 = r0.size();	 Catch:{ all -> 0x00ae }
        if (r0 <= 0) goto L_0x0082;
    L_0x010e:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.q;	 Catch:{ all -> 0x00ae }
        r1 = r0.iterator();	 Catch:{ all -> 0x00ae }
    L_0x0116:
        r0 = r1.hasNext();	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0082;
    L_0x011c:
        r0 = r1.next();	 Catch:{ all -> 0x00ae }
        r0 = (com.unionpay.mobile.android.g.c) r0;	 Catch:{ all -> 0x00ae }
        r3 = r0.c();	 Catch:{ all -> 0x00ae }
        if (r3 == 0) goto L_0x0116;
    L_0x0128:
        r0 = r0.c();	 Catch:{ all -> 0x00ae }
        r0 = r0 & r2;
        if (r0 != 0) goto L_0x0116;
    L_0x012f:
        r1.remove();	 Catch:{ all -> 0x00ae }
        goto L_0x0116;
    L_0x0133:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.aC;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0140;
    L_0x0139:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r1 = r8.s;	 Catch:{ all -> 0x00ae }
        r0.q = r1;	 Catch:{ all -> 0x00ae }
        goto L_0x00fe;
    L_0x0140:
        r0 = r8.s;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0155;
    L_0x0144:
        r0 = r8.s;	 Catch:{ all -> 0x00ae }
        r0 = r0.size();	 Catch:{ all -> 0x00ae }
        if (r0 <= 0) goto L_0x0155;
    L_0x014c:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.q;	 Catch:{ all -> 0x00ae }
        r1 = r8.s;	 Catch:{ all -> 0x00ae }
        r0.addAll(r1);	 Catch:{ all -> 0x00ae }
    L_0x0155:
        r0 = r8.r;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x00fe;
    L_0x0159:
        r0 = r8.r;	 Catch:{ all -> 0x00ae }
        r0 = r0.size();	 Catch:{ all -> 0x00ae }
        if (r0 <= 0) goto L_0x00fe;
    L_0x0161:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.q;	 Catch:{ all -> 0x00ae }
        r1 = r8.r;	 Catch:{ all -> 0x00ae }
        r0.addAll(r1);	 Catch:{ all -> 0x00ae }
        goto L_0x00fe;
    L_0x016b:
        r0 = "1";
        r1 = r8.a;	 Catch:{ all -> 0x00ae }
        r1 = r1.an;	 Catch:{ all -> 0x00ae }
        r0 = r0.equalsIgnoreCase(r1);	 Catch:{ all -> 0x00ae }
        if (r0 != 0) goto L_0x01d6;
    L_0x0177:
        r0 = r8.F;	 Catch:{ JSONException -> 0x01d2 }
        if (r0 == 0) goto L_0x01f1;
    L_0x017b:
        r0 = r8.F;	 Catch:{ JSONException -> 0x01d2 }
        r1 = 0;
        r3 = r0.getString(r1);	 Catch:{ JSONException -> 0x01d2 }
    L_0x0182:
        r0 = r8.F;	 Catch:{ JSONException -> 0x01d2 }
        if (r0 == 0) goto L_0x01f6;
    L_0x0186:
        r0 = r8.F;	 Catch:{ JSONException -> 0x01d2 }
        r1 = 1;
        r4 = r0.getString(r1);	 Catch:{ JSONException -> 0x01d2 }
    L_0x018d:
        r0 = r8.F;	 Catch:{ JSONException -> 0x01d2 }
        if (r0 == 0) goto L_0x01fb;
    L_0x0191:
        r0 = r8.F;	 Catch:{ JSONException -> 0x01d2 }
        r1 = 2;
        r2 = r0.getString(r1);	 Catch:{ JSONException -> 0x01d2 }
    L_0x0198:
        r0 = r8.D();	 Catch:{ JSONException -> 0x01d2 }
        if (r0 == 0) goto L_0x0200;
    L_0x019e:
        r0 = r8.F;	 Catch:{ JSONException -> 0x01d2 }
        r1 = 3;
        r5 = r0.getString(r1);	 Catch:{ JSONException -> 0x01d2 }
    L_0x01a5:
        r0 = r8.a;	 Catch:{ JSONException -> 0x01d2 }
        r0 = r0.q;	 Catch:{ JSONException -> 0x01d2 }
        if (r0 == 0) goto L_0x01d6;
    L_0x01ab:
        r0 = r8.a;	 Catch:{ JSONException -> 0x01d2 }
        r0 = r0.q;	 Catch:{ JSONException -> 0x01d2 }
        r6 = r0.iterator();	 Catch:{ JSONException -> 0x01d2 }
    L_0x01b3:
        r0 = r6.hasNext();	 Catch:{ JSONException -> 0x01d2 }
        if (r0 == 0) goto L_0x01d6;
    L_0x01b9:
        r0 = r6.next();	 Catch:{ JSONException -> 0x01d2 }
        r0 = (com.unionpay.mobile.android.g.c) r0;	 Catch:{ JSONException -> 0x01d2 }
        r1 = r0.c();	 Catch:{ JSONException -> 0x01d2 }
        if (r1 == 0) goto L_0x01b3;
    L_0x01c5:
        r1 = "";
        r7 = r0.c();	 Catch:{ JSONException -> 0x01d2 }
        switch(r7) {
            case 1: goto L_0x020b;
            case 4: goto L_0x0209;
            case 8: goto L_0x0207;
            case 16: goto L_0x0205;
            default: goto L_0x01ce;
        };	 Catch:{ JSONException -> 0x01d2 }
    L_0x01ce:
        r0.a(r1);	 Catch:{ JSONException -> 0x01d2 }
        goto L_0x01b3;
    L_0x01d2:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00ae }
    L_0x01d6:
        r0 = com.unionpay.mobile.android.g.b.bn;	 Catch:{ all -> 0x00ae }
        if (r0 != 0) goto L_0x01e2;
    L_0x01da:
        r0 = com.unionpay.mobile.android.g.b.aA;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x01e2;
    L_0x01de:
        r0 = com.unionpay.mobile.android.g.b.aB;	 Catch:{ all -> 0x00ae }
        if (r0 != 0) goto L_0x020d;
    L_0x01e2:
        r0 = com.unionpay.mobile.android.g.b.bm;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x020d;
    L_0x01e6:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.ap;	 Catch:{ all -> 0x00ae }
        r1 = "fail";
        r8.c(r0, r1);	 Catch:{ all -> 0x00ae }
        goto L_0x0051;
    L_0x01f1:
        r0 = com.unionpay.mobile.android.f.c.bD;	 Catch:{ JSONException -> 0x01d2 }
        r3 = r0.ak;	 Catch:{ JSONException -> 0x01d2 }
        goto L_0x0182;
    L_0x01f6:
        r0 = com.unionpay.mobile.android.f.c.bD;	 Catch:{ JSONException -> 0x01d2 }
        r4 = r0.aj;	 Catch:{ JSONException -> 0x01d2 }
        goto L_0x018d;
    L_0x01fb:
        r0 = com.unionpay.mobile.android.f.c.bD;	 Catch:{ JSONException -> 0x01d2 }
        r2 = r0.ai;	 Catch:{ JSONException -> 0x01d2 }
        goto L_0x0198;
    L_0x0200:
        r0 = com.unionpay.mobile.android.f.c.bD;	 Catch:{ JSONException -> 0x01d2 }
        r5 = r0.al;	 Catch:{ JSONException -> 0x01d2 }
        goto L_0x01a5;
    L_0x0205:
        r1 = r2;
        goto L_0x01ce;
    L_0x0207:
        r1 = r3;
        goto L_0x01ce;
    L_0x0209:
        r1 = r4;
        goto L_0x01ce;
    L_0x020b:
        r1 = r5;
        goto L_0x01ce;
    L_0x020d:
        r0 = r8.w();	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0235;
    L_0x0213:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.az;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0235;
    L_0x0219:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.aC;	 Catch:{ all -> 0x00ae }
        if (r0 != 0) goto L_0x0235;
    L_0x021f:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.aD;	 Catch:{ all -> 0x00ae }
        if (r0 != 0) goto L_0x0235;
    L_0x0225:
        r1 = r8.t;	 Catch:{ all -> 0x00ae }
        r2 = r8.x;	 Catch:{ all -> 0x00ae }
        r3 = r8.u;	 Catch:{ all -> 0x00ae }
        r4 = r8.v;	 Catch:{ all -> 0x00ae }
        r5 = r8.y;	 Catch:{ all -> 0x00ae }
        r6 = r8.w;	 Catch:{ all -> 0x00ae }
        r0 = r8;
        r0.a(r1, r2, r3, r4, r5, r6);	 Catch:{ all -> 0x00ae }
    L_0x0235:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.ax;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0255;
    L_0x023b:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.aD;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0255;
    L_0x0241:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.ay;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0255;
    L_0x0247:
        r0 = 17;
        r8.d(r0);	 Catch:{ all -> 0x00ae }
    L_0x024c:
        r0 = "uppay";
        r1 = "showContentView() ---";
        com.unionpay.mobile.android.utils.k.c(r0, r1);	 Catch:{ all -> 0x00ae }
        goto L_0x0051;
    L_0x0255:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.aE;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x027d;
    L_0x025b:
        r0 = r8.a;	 Catch:{ all -> 0x00ae }
        r0 = r0.az;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x027d;
    L_0x0261:
        r0 = com.unionpay.mobile.android.g.b.bl;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x027d;
    L_0x0265:
        r0 = com.unionpay.mobile.android.g.b.bb;	 Catch:{ all -> 0x00ae }
        if (r0 == 0) goto L_0x0271;
    L_0x0269:
        r0 = com.unionpay.mobile.android.g.b.bb;	 Catch:{ all -> 0x00ae }
        r0 = r0.size();	 Catch:{ all -> 0x00ae }
        if (r0 > 0) goto L_0x027d;
    L_0x0271:
        r0 = com.unionpay.mobile.android.f.c.bD;	 Catch:{ all -> 0x00ae }
        r0 = r0.bq;	 Catch:{ all -> 0x00ae }
        r1 = r8.a;	 Catch:{ all -> 0x00ae }
        r1 = r1.bh;	 Catch:{ all -> 0x00ae }
        r8.b(r0, r1);	 Catch:{ all -> 0x00ae }
        goto L_0x024c;
    L_0x027d:
        r0 = 2;
        r1 = r8.G;	 Catch:{ all -> 0x00ae }
        r8.a(r0, r1);	 Catch:{ all -> 0x00ae }
        goto L_0x024c;
    L_0x0284:
        r2 = r0;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.nocard.views.l.E():void");
    }

    private void a(String str, String str2, String str3) {
        OnClickListener axVar = new ax(this, str3);
        OnClickListener ayVar = new ay(this);
        if ((str.equalsIgnoreCase("01") ? null : 1) != null) {
            this.b.a(axVar, ayVar);
            this.b.a(com.unionpay.mobile.android.f.c.bD.ae, str2, com.unionpay.mobile.android.f.c.bD.af, com.unionpay.mobile.android.f.c.bD.ag);
            return;
        }
        this.b.a(axVar, ayVar);
        this.b.a(com.unionpay.mobile.android.f.c.bD.Y, str2, com.unionpay.mobile.android.f.c.bD.af, com.unionpay.mobile.android.f.c.bD.ag);
    }

    private void b(int i, String str) {
        if (str != null && str.length() > 0) {
            this.a.I.f = str;
        }
        this.z.setVisibility(4);
        a(c(i), true);
    }

    private void c(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            this.a.I.f = str2;
        }
        this.z.setVisibility(4);
        a(str, true);
    }

    private boolean f(int i) {
        if ((i & 2) == 0) {
            return false;
        }
        this.a.aw = true;
        return true;
    }

    protected final void A() {
        this.C++;
        E();
    }

    public final void B() {
        removeAllViews();
        this.z = null;
    }

    public void C() {
        d("000");
    }

    public void a(int i, int i2, String str, String str2, int i3, String str3) {
    }

    public final void a(int i, byte[] bArr) {
        j();
        if (i != 0) {
            b(i, null);
        }
        k.a("uppay", "status = " + i);
        if (bArr != null) {
            Object obj = null;
            if ("mounted".equals(Environment.getExternalStorageState())) {
                obj = 1;
            }
            if (obj == null) {
                b(9, null);
            } else if (q.a(bArr)) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                String str = Environment.getExternalStorageDirectory() + File.separator + "UPPay" + File.separator + "UPPayPluginEx.apk";
                k.a("uppay", "apk path:" + str);
                intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
                ((com.unionpay.mobile.android.i.a) this.d).startActivityForResult(intent, 100);
            } else {
                b(21, null);
            }
        }
    }

    public final void a(JSONObject jSONObject) {
        k.a("uppay", "init.parserParamJsonObj() +++");
        if (jSONObject == null) {
            b(2);
            return;
        }
        int b;
        switch (this.B) {
            case 1:
                String a = j.a(jSONObject, x.c);
                j.a(jSONObject, "sec_sign");
                this.e.e(a);
                CharSequence a2 = j.a(jSONObject, "talking_data_flag");
                if (!TextUtils.isEmpty(a2)) {
                    com.unionpay.mobile.android.d.a.L = "0".equals(a2);
                }
                String a3 = j.a(jSONObject, "mer_id");
                a = "002";
                if (!TextUtils.isEmpty(a3)) {
                    a = a3;
                }
                if (!this.a.I.d) {
                    a = "001";
                }
                if (com.unionpay.mobile.android.d.a.L) {
                    com.unionpay.c.a.a(this.d, this.e.b(this.a.I.c), a);
                    com.unionpay.c.a.a(true);
                    com.unionpay.c.a.a(com.unionpay.mobile.android.f.c.bD.a, 57);
                }
                b.a(this.d, "tn", p.a, (Object[]) new String[]{this.a.b});
                b.a(this.d, "merch_id", p.b, (Object[]) new String[]{a3});
                b.a(this.d, "MID_" + a3);
                b.a(this.d, "package_version", p.c, (Object[]) new String[]{com.unionpay.mobile.android.f.c.bD.a});
                JSONObject c = j.c(jSONObject, "upgrade_info");
                this.A = j.a(c, "type");
                String a4 = j.a(c, SocialConstants.PARAM_APP_DESC);
                String a5 = j.a(c, "url");
                if (!this.A.equalsIgnoreCase("02")) {
                    int i;
                    Object b2;
                    String a6;
                    this.a.o = j.a(jSONObject, "title");
                    this.a.j = j.c(jSONObject, "init_button");
                    this.a.h = j.d(jSONObject, "order");
                    Map hashMap = new HashMap();
                    for (i = 0; i < this.a.h.length(); i++) {
                        b2 = j.b(this.a.h, i);
                        if (b2 != null) {
                            c = (JSONObject) b2;
                            hashMap.put(j.a(c, "label"), j.a(c, "value"));
                        }
                    }
                    b.a(this.d, "basic_info", p.d, (Object[]) new String[]{hashMap.toString()});
                    this.a.i = j.c(jSONObject, "risk_info");
                    List e = j.e(jSONObject, "cards");
                    if (e != null && e.size() > 0) {
                        this.r = new ArrayList(e.size());
                    }
                    i = 0;
                    while (e != null && i < e.size()) {
                        this.r.add(new com.unionpay.mobile.android.g.a(j.a((JSONArray) e.get(i), 0), j.a((JSONArray) e.get(i), 1), j.a((JSONArray) e.get(i), 2), (byte) 0));
                        i++;
                    }
                    this.a.s = j.a(jSONObject, "bank_url");
                    this.a.t = j.d(jSONObject, "input_info");
                    this.a.v = j.c(jSONObject, "account_info");
                    this.a.w = j.c(jSONObject, "other_card_info");
                    this.a.u = j.a(jSONObject, SocializeConstants.TENCENT_UID);
                    this.e.c(j.a(jSONObject, "sid"));
                    this.e.d(j.a(jSONObject, x.c));
                    a = j.a(jSONObject, "sid");
                    if (!TextUtils.isEmpty(a)) {
                        this.a.k = this.e.i(com.unionpay.mobile.android.utils.c.b(a));
                    }
                    a = j.a(jSONObject, x.c);
                    if (!TextUtils.isEmpty(a)) {
                        this.a.l = this.e.i(com.unionpay.mobile.android.utils.c.b(a));
                        this.a.m = a;
                    }
                    a = j.a(jSONObject, "uid");
                    if (!(a == null || TextUtils.isEmpty(a))) {
                        PreferenceUtils.b(this.d, a);
                    }
                    if (x()) {
                        if (w()) {
                            this.a.x = j.c(jSONObject, "kalefu_info");
                            this.a.y = j.a(jSONObject, "kalefu_button_label");
                            if ((this.a.y == null || this.a.y.length() <= 0) && com.unionpay.mobile.android.f.c.bD != null) {
                                this.a.y = com.unionpay.mobile.android.f.c.bD.am;
                            }
                        }
                        this.F = j.d(jSONObject, "cards_desc");
                        this.a.an = j.a(jSONObject, "trade_privilege");
                        this.a.ap = j.a(jSONObject, "upcard_msg");
                        this.a.ao = 0;
                        a = j.a(jSONObject, "upcard_support_type");
                        if (!("1".equalsIgnoreCase(this.a.an) || a == null || a.length() <= 0)) {
                            this.a.ao = Integer.parseInt(a, 2);
                        }
                        f(this.a.ao);
                    }
                    this.a.ar = j.a(jSONObject, "ad");
                    this.a.at = j.a(jSONObject, "pay_tip");
                    a2 = j.a(jSONObject, "sup_pay_method");
                    if (!TextUtils.isEmpty(a2)) {
                        this.a.aC = "01".equals(a2);
                        this.a.aD = "001".equals(a2);
                    }
                    a2 = j.a(jSONObject, "default_pay_type");
                    if (!TextUtils.isEmpty(a2)) {
                        this.a.aE = "0501".equals(a2);
                    }
                    this.a.au = j.c(jSONObject, "find_pwd_url");
                    this.a.Y = j.c(jSONObject, "reg_url");
                    this.a.ay = "1".equals(j.a(jSONObject, "sup_nfc"));
                    this.a.az = "1".equals(j.a(jSONObject, "sup_hce"));
                    b.aA = "1".equals(j.a(jSONObject, "sup_samsung_pay"));
                    this.a.bh = j.a(jSONObject, "hce_introduction_url");
                    if (w() && this.a.ay && !this.a.aC) {
                        b2 = j.a(jSONObject, "nfc_title");
                        if (!TextUtils.isEmpty(b2)) {
                            com.unionpay.mobile.android.f.c.bD.bo = b2;
                        }
                        c = j.c(jSONObject, "nfc_button");
                        if (c != null) {
                            b2 = j.a(c, "label");
                            if (!(b2 == null || TextUtils.isEmpty(b2))) {
                                com.unionpay.mobile.android.f.c.bD.bp = b2;
                            }
                        }
                    }
                    if (w() && this.a.az && !this.a.aC && !this.a.aD) {
                        b2 = j.a(jSONObject, "hce_title");
                        this.t = j.b(jSONObject, "hce_page_size");
                        JSONObject c2 = j.c(jSONObject, "hce_button");
                        a6 = j.a(c2, "label");
                        Object a7 = j.a(c2, "htmlLabel");
                        if (TextUtils.isEmpty(b2)) {
                            com.unionpay.mobile.android.f.c.bD.bq = a6;
                        } else {
                            com.unionpay.mobile.android.f.c.bD.bq = b2;
                        }
                        if (TextUtils.isEmpty(a7)) {
                            com.unionpay.mobile.android.f.c.bD.br = a6;
                        } else {
                            com.unionpay.mobile.android.f.c.bD.br = a7;
                        }
                        this.u = j.a(c2, AuthActivity.ACTION_KEY);
                        this.v = j.a(c2, "reserved");
                        this.w = j.a(jSONObject, "iss_ins_code");
                        this.x = j.b(jSONObject, "hce_bank_timeout");
                        this.y = j.b(jSONObject, "hce_concurrent_count");
                        b = j.b(jSONObject, "hce_pay_timeout");
                        if (b != 0) {
                            this.a.be = b;
                        } else {
                            this.a.be = 5000;
                        }
                        this.a.bi = j.a(jSONObject, "no_hce_card_msg");
                    }
                    b bVar = this.a;
                    bVar.p = new HashMap();
                    JSONObject c3 = j.c(jSONObject, "f55");
                    a3 = j.a(c3, "order_amount");
                    HashMap hashMap2 = bVar.p;
                    String str = "trans_amt";
                    b2 = (a3 == null || a3.length() <= 0) ? "000000000000" : a3;
                    hashMap2.put(str, b2);
                    bVar.bp = com.unionpay.mobile.android.utils.c.c(a3);
                    a3 = j.a(c3, "order_currency");
                    hashMap2 = bVar.p;
                    str = "trans currcy code";
                    b2 = (a3 == null || a3.length() <= 0) ? "0156" : a3;
                    hashMap2.put(str, b2);
                    bVar.bq = a3;
                    b2 = j.a(c3, "trans_type");
                    HashMap hashMap3 = bVar.p;
                    String str2 = "trans_type";
                    if (b2 == null || b2.length() <= 0) {
                        b2 = "00";
                    }
                    hashMap3.put(str2, b2);
                    b2 = j.a(c3, "mer_name");
                    hashMap3 = bVar.p;
                    a6 = "mer_name";
                    if (b2 == null || b2.length() <= 0) {
                        b2 = "";
                    }
                    hashMap3.put(a6, b2);
                    if (this.a.aD) {
                        this.a.u = "";
                    }
                    if (this.A.equalsIgnoreCase("00")) {
                        if (!b.b(this.a.u)) {
                            y();
                            break;
                        }
                        this.B = 2;
                        this.e.n(String.format("\"user_id\":\"%s\"", new Object[]{this.a.u}));
                        break;
                    }
                    a(this.A, a4, a5);
                    break;
                }
                a(this.A, a4, a5);
                break;
                break;
            case 2:
                f.c(this.a, jSONObject);
                b = f.b(this.a, jSONObject);
                if (b == 0) {
                    if (!TextUtils.isEmpty(j.a(jSONObject, "userId"))) {
                        b.a(this.d, "_login", p.e, new Object[]{j.a(jSONObject, "userId")});
                    }
                    this.G = f.a(jSONObject);
                    y();
                    break;
                }
                b(b);
                break;
        }
        k.a("uppay", "init.parserParamJsonObj() ---");
    }

    public final void a(boolean z) {
        this.a.ax = z;
    }

    public final void b(int i) {
        k.a("uppay", toString() + "doErrHappended() +++");
        b(i, "fail");
        k.a("uppay", toString() + "doErrHappended() ---");
    }

    public final void c(String str) {
        this.b.a(com.unionpay.mobile.android.f.c.bD.U);
        new r(this.d, str, this).a();
    }

    protected final void d() {
        super.d();
        this.m.setBackgroundColor(-1);
        setBackgroundDrawable(this.c.a(3008));
        int i = com.unionpay.mobile.android.d.a.I / 2;
        View imageView = new ImageView(getContext());
        imageView.setImageDrawable(this.c.a(1027, i, -1));
        imageView.setId(imageView.hashCode());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, -2);
        layoutParams.addRule(14);
        layoutParams.leftMargin = com.unionpay.mobile.android.d.a.j;
        layoutParams.topMargin = (int) (0.3f * ((float) com.unionpay.mobile.android.d.a.t));
        addView(imageView, layoutParams);
        this.z = new ProgressBar(getContext(), null, 16843399);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14, -1);
        layoutParams2.addRule(3, imageView.getId());
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.d * 3;
        addView(this.z, layoutParams2);
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(14, -1);
        layoutParams3.addRule(12, -1);
        layoutParams3.bottomMargin = com.unionpay.mobile.android.d.a.b;
        addView(linearLayout, layoutParams3);
        View textView = new TextView(this.d);
        textView.setText(com.unionpay.mobile.android.f.c.bD.a);
        textView.setTextColor(-1);
        textView.setTextSize(14.0f);
        textView.setGravity(1);
        new LinearLayout.LayoutParams(-2, -2).gravity = 14;
        linearLayout.addView(textView, layoutParams3);
        textView = new TextView(getContext());
        textView.setText(com.unionpay.mobile.android.f.c.bD.b);
        textView.setTextColor(-1);
        textView.setTextSize(16.0f);
        textView.setGravity(1);
        new LinearLayout.LayoutParams(-2, -2).gravity = 14;
        linearLayout.addView(textView, layoutParams3);
    }

    public final void d(String str) {
        this.B = 1;
        this.e.a(this.H);
        if (this.a.f) {
            this.e.b(this.a.e, str);
        } else {
            this.e.b(this.a.b, str);
        }
        this.e.a((UPPayEngine.a) this);
    }

    public final void l() {
    }

    protected void u() {
        v();
    }

    public final void v() {
        int i = 1;
        if (!this.K) {
            int parseInt;
            this.K = true;
            z();
            this.E = false;
            Activity activity = this.I;
            boolean z = this.J;
            this.e.a();
            try {
                parseInt = Integer.parseInt(this.a.I.c);
            } catch (NumberFormatException e) {
                parseInt = 0;
            }
            boolean z2 = !"com.unionpay.uppay".equals(com.unionpay.mobile.android.utils.f.b(this.d));
            b bVar;
            if (this.a.f) {
                bVar = this.a;
                i = 2;
            } else if (this.a.c) {
                bVar = this.a;
            } else {
                b bVar2 = this.a;
                i = 0;
            }
            this.H = this.e.initJNIEnv(activity, i, parseInt, z2, this.a.a, this.a.aO, com.unionpay.mobile.android.utils.c.b(this.a.f ? this.a.d : this.a.b));
            if (z && this.H != 0 && this.H != -1) {
                Context context = this.d;
                C();
            } else if (this.H == -1) {
                b(7, null);
            } else if (!z) {
                b(5, null);
            }
        }
    }

    public boolean w() {
        return false;
    }

    public boolean x() {
        return false;
    }

    public final void y() {
        if (this.A.equalsIgnoreCase("02")) {
            k();
            return;
        }
        this.C++;
        this.E = true;
        E();
    }

    public void z() {
        A();
    }
}
