package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import anet.channel.security.ISecurity;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.unionpay.mobile.android.e.c;
import com.unionpay.mobile.android.g.d;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.pboctransaction.f;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.mobile.android.widgets.z;
import com.unionpay.uppay.PayActivity;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a extends b implements Callback, com.unionpay.mobile.android.upviews.a.b {
    private static Date M = new Date(System.currentTimeMillis());
    private static String N = new SimpleDateFormat("yyyyMMddhhmmss").format(M);
    private static HashMap<String, String> P = new HashMap();
    private static HashMap<String, String> Q = new HashMap();
    private ay A;
    private String B;
    private c C;
    private int D;
    private String E;
    private boolean F;
    private Callback G;
    private Handler H;
    private OnClickListener I;
    private OnClickListener J;
    private OnClickListener K;
    private OnClickListener L;
    private String O;
    public String r;
    UPPayEngine s;
    private int t;
    private int u;
    private TextView v;
    private boolean w;
    private com.unionpay.mobile.android.upviews.a x;
    private com.unionpay.mobile.android.upviews.a y;
    private Handler z;

    public a(Context context, e eVar, UPPayEngine uPPayEngine) {
        super(context, eVar);
        this.t = 20;
        this.u = 100;
        this.r = "1.9";
        this.v = null;
        this.w = false;
        this.x = null;
        this.y = null;
        this.z = null;
        this.D = 5;
        this.F = false;
        this.G = new l(this);
        this.H = new Handler(this.G);
        this.I = new m(this);
        this.J = new n(this);
        this.K = new o(this);
        this.L = new p(this);
        this.O = null;
        this.f = 6;
        this.q = "hcepay";
        this.s = uPPayEngine;
        this.z = new Handler(this);
        this.C = (c) com.unionpay.mobile.android.g.b.bb.get(this.a.bc);
        this.w = this.a.K;
        setBackgroundColor(-1052684);
        e();
    }

    private void a(LinearLayout linearLayout) {
        z zVar = null;
        if (this.y != null) {
            zVar = this.y.c("instalment");
        }
        int length = this.a.z.length();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < length; i++) {
            Object b = j.b(this.a.z, i);
            if (b != null) {
                try {
                    JSONObject jSONObject = (JSONObject) b;
                    if ("pan".equals(j.a(jSONObject, "type"))) {
                        jSONObject.put("label", this.C.b() + this.C.c());
                    }
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        this.x = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this.e.c(), this, this.C.a(), true, false, zVar, this.a.ad, this.q);
        linearLayout.addView(this.x, new LayoutParams(-1, -2));
    }

    static /* synthetic */ void a(a aVar, String str, String str2) {
        aVar.u = 8;
        aVar.b.a(com.unionpay.mobile.android.f.c.bD.U);
        aVar.e.c(str, str2);
    }

    static /* synthetic */ void a(a aVar, String str, HashMap hashMap) {
        Object a = ((PayActivity) aVar.d).a(com.unionpay.mobile.android.pro.a.a.b.class.toString());
        if ((a != null ? (com.unionpay.mobile.android.pro.a.a.b) a : null) == null) {
            aVar.b(5);
        } else {
            new Thread(new q(aVar, str, hashMap)).start();
        }
    }

    private static void a(StringBuffer stringBuffer) {
        for (String str : P.keySet()) {
            String str2 = (String) P.get(str);
            if (!TextUtils.isEmpty(str2)) {
                byte[] bArr = new byte[]{(byte) (str2.length() / 2)};
                String a = f.a(bArr, bArr.length);
                stringBuffer.append(str);
                stringBuffer.append(a);
                stringBuffer.append(str2);
            }
        }
    }

    private synchronized Bundle b(String str, HashMap<String, String> hashMap) {
        Bundle bundle;
        String toUpperCase;
        Bundle w = w();
        Q.put("PIN", str);
        Q.put("AN1", this.C.a());
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer);
        Q.put("DCD", stringBuffer.toString());
        this.B = d(this.a.bf, this.s.b());
        if (Q.get("5F34") != null) {
            StringBuffer stringBuffer2 = new StringBuffer((String) Q.get("5F34"));
            stringBuffer2.insert(0, "0");
            Q.put("5F34", stringBuffer2.toString());
        } else {
            Q.put("5F34", "");
        }
        if (Q.get("57") != null) {
            toUpperCase = ((String) Q.get("57")).toUpperCase();
            while (true) {
                if (!toUpperCase.substring(toUpperCase.length() - 1, toUpperCase.length()).equalsIgnoreCase("f") && !toUpperCase.substring(toUpperCase.length() - 1, toUpperCase.length()).equalsIgnoreCase("F")) {
                    break;
                }
                toUpperCase = toUpperCase.substring(0, toUpperCase.length() - 1);
            }
            Q.put("TD2", toUpperCase.toString());
            int indexOf = toUpperCase.indexOf("D");
            Object substring = toUpperCase.substring(0, indexOf);
            if (substring.endsWith("F") || substring.endsWith("f")) {
                substring = substring.substring(0, substring.length() - 1);
            }
            Q.put("AN1", substring);
            Q.put("ED", toUpperCase.substring(indexOf + 1, indexOf + 5));
        }
        Q.put("AMT", P.get("9F02"));
        String str2 = (((("" + "pan=" + ((String) Q.get("AN1"))) + "&pin=" + ((String) Q.get("PIN"))) + "&icc_data=" + ((String) Q.get("DCD"))) + "&card_seq_id=" + ((String) Q.get("5F34"))) + "&auth_id=" + this.a.bg;
        k.c(SocializeProtocolConstants.PROTOCOL_KEY_MAC, str2);
        toUpperCase = d(str2);
        k.c("md5", toUpperCase);
        toUpperCase = this.e.h(toUpperCase);
        k.c("sig", toUpperCase);
        str2 = this.e.a(str2 + "&" + toUpperCase, this.B);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(anet.channel.strategy.dispatch.c.VERSION, this.r);
            jSONObject.put(com.taobao.agoo.a.a.b.JSON_CMD, "pay");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("params", jSONObject2);
            jSONObject2.put("encrypt_key_field", str2);
            jSONObject2.put("pay_type", "2");
            jSONObject2.put("pay_mode", "1");
            jSONObject2.put("bind", "no");
            jSONObject2.put("carrier_tp", "9");
            jSONObject2.put("carrier_app_tp", "0");
            jSONObject2.put("sign", toUpperCase);
            jSONObject2.put("pan", Q.get("AN1"));
            if (Q.get("ED") != null) {
                jSONObject2.put("expire", Q.get("ED"));
            }
            if (Q.get("TD2") != null) {
                jSONObject2.put("track2_data", Q.get("TD2"));
            }
            if (!(hashMap == null || hashMap.keySet() == null || hashMap.keySet().size() <= 0)) {
                hashMap.remove("pan");
                hashMap.remove("pin");
                for (String str22 : hashMap.keySet()) {
                    jSONObject2.put(str22, hashMap.get(str22));
                }
            }
            w.putString("action_resp_message", this.s.a(jSONObject.toString()));
            bundle = w;
        } catch (JSONException e) {
            w.putString("action_resp_code", "10019");
            bundle = w;
        }
        return bundle;
    }

    private boolean b(HashMap<String, String> hashMap) {
        Object format;
        String substring = N.substring(2, 8);
        String valueOf = String.valueOf(new Date(System.currentTimeMillis()).getTime());
        if (valueOf.length() < 8) {
            format = String.format("%08d", new Object[]{Long.valueOf(r4)});
        } else {
            format = valueOf.substring(valueOf.length() - 8, valueOf.length());
        }
        P.put("9F26", "");
        P.put("9F27", "80");
        P.put("9F10", "");
        P.put("9F37", format);
        P.put("9F36", "");
        P.put("95", "0000000800");
        P.put("9A", substring);
        P.put("9C", "45");
        P.put("9F02", "000000000000");
        P.put("5F2A", "0156");
        P.put("82", "");
        P.put("9F1A", "0156");
        P.put("9F03", "000000000000");
        P.put("9F33", "A04000");
        P.put("9F34", "420300");
        P.put("9F35", "34");
        P.put("9F1E", "3030303030303030");
        P.put("84", "");
        P.put("9F09", "0001");
        P.put("9F41", "");
        P.put("91", "");
        P.put("71", "");
        P.put("72", "");
        P.put("DF31", "");
        P.put("9F74", "");
        P.put("9F63", "");
        P.put("8A", "");
        Q.put("9F66", "26C00000");
        w();
        P.put("9F02", hashMap.get("trans_amt"));
        P.put("9F1A", "0156");
        P.put("5F2A", hashMap.get("trans currcy code"));
        P.put("9C", hashMap.get("trans_type"));
        Q.put("CUR", P.get("5F2A"));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("9F66", Q.get("9F66"));
            jSONObject.put("9F02", P.get("9F02"));
            jSONObject.put("9F03", P.get("9F03"));
            jSONObject.put("9F1A", P.get("9F1A"));
            jSONObject.put("95", P.get("95"));
            jSONObject.put("5F2A", P.get("5F2A"));
            jSONObject.put("9A", P.get("9A"));
            jSONObject.put("9C", P.get("9C"));
            jSONObject.put("9F37", P.get("9F37"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valueOf = this.C.f();
        try {
            this.C.g().a(com.unionpay.mobile.android.e.a.b(this.C.a(), valueOf), com.unionpay.mobile.android.e.a.b(jSONObject.toString(), valueOf), "", new com.unionpay.mobile.android.e.b(m_AppUI.MSG_APP_VERSION, "", this.H));
            this.H.sendMessageDelayed(this.H.obtainMessage(m_AppUI.MSG_APP_VERSION_COMMEND), (long) this.a.be);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            a(this.a.ap, false);
            return false;
        }
    }

    private static final String d(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(bytes);
            return f.a(instance.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String d(String str, String str2) {
        byte[] a = f.a(str);
        byte[] a2 = f.a(str2);
        for (int i = 0; i < a.length; i++) {
            a[i] = (byte) (a[i] ^ a2[i]);
        }
        return f.a(a);
    }

    static /* synthetic */ boolean d(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        String str;
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            str = (String) keys.next();
            CharSequence a = j.a(jSONObject, str);
            if (!TextUtils.isEmpty(a)) {
                if ("5F34".equals(str) || "57".equals(str) || "9F6C".equals(str) || "9F5D".equals(str) || "5F20".equals(str)) {
                    Q.put(str, a);
                } else {
                    P.put(str, a);
                }
            }
        }
        str = (String) P.get("9F10");
        return TextUtils.isEmpty(str) || ((byte) (f.a(str)[4] & 48)) == (byte) 32;
    }

    private void e(String str, String str2) {
        this.u = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.D--;
    }

    static /* synthetic */ HashMap f(a aVar) {
        HashMap hashMap = new HashMap();
        if (aVar.x != null) {
            hashMap = aVar.x.c();
        }
        if (aVar.y == null) {
            return hashMap;
        }
        Map c = aVar.y.c();
        if (c == null || hashMap == null) {
            return (c == null || hashMap != null) ? hashMap : c;
        } else {
            hashMap.putAll(c);
            return hashMap;
        }
    }

    static /* synthetic */ void p(a aVar) {
        aVar.u = 104;
        aVar.j = false;
        aVar.b.a(com.unionpay.mobile.android.f.c.bD.U);
        String str = "";
        k.c("uppay", str);
        aVar.e.c("cardsecret", str);
    }

    private void v() {
        this.u = 103;
        int i = this.t;
        this.e.a("query", this.a.aj, 3);
        this.t--;
    }

    private static Bundle w() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    public final Bundle a(String str, HashMap<String, String> hashMap) {
        return b(str, (HashMap) hashMap);
    }

    public final void a(com.unionpay.mobile.android.upviews.a.a aVar) {
    }

    public final void a(JSONObject jSONObject) {
        JSONArray d;
        String a;
        String a2;
        switch (this.u) {
            case 8:
                j();
                d = j.d(jSONObject, "options");
                if (this.y != null) {
                    this.y.a(d);
                    return;
                }
                return;
            case 9:
                a = j.a(jSONObject, "status");
                if (a == null || !"01".equals(a)) {
                    d = j.d(jSONObject, "options");
                    a2 = j.a(jSONObject, "empty_info");
                    if (this.y != null) {
                        this.y.a(d, a2);
                        return;
                    }
                    return;
                }
                a = j.a(jSONObject, "uuid");
                if (this.D >= 0) {
                    e(this.E, a);
                    return;
                }
                a = com.unionpay.mobile.android.f.c.bD.D;
                if (this.y != null) {
                    this.y.a(null, a);
                    return;
                }
                return;
            case 101:
                this.a.aj = i.a(jSONObject.toString());
                a = j.a(jSONObject, "qn");
                if (!TextUtils.isEmpty(a)) {
                    this.a.n = this.e.i(com.unionpay.mobile.android.utils.c.b(a));
                }
                if (this.a.aj == null || this.a.aj.length() <= 0) {
                    b(2);
                    return;
                }
                this.t = 20;
                v();
                return;
            case 103:
                a = j.a(jSONObject, "status");
                a2 = j.a(jSONObject, "fail_msg");
                if (this.t <= 0 || !a.equalsIgnoreCase("01")) {
                    this.u = 100;
                    if (a.equalsIgnoreCase("00")) {
                        j();
                        this.u = 100;
                        this.a.H = j.d(jSONObject, "result");
                        this.a.P = j.a(jSONObject, "openupgrade_flag");
                        this.a.Q = j.a(jSONObject, "temporary_pay_flag");
                        this.a.R = j.a(jSONObject, "temporary_pay_info");
                        this.a.V = j.a(jSONObject, "front_url");
                        this.a.W = j.a(jSONObject, "front_request");
                        this.a.A = j.a(jSONObject, "title");
                        this.a.B = j.a(jSONObject, "succ_info");
                        com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.a);
                        com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.a);
                        if (this.x != null) {
                            this.x.f();
                        }
                        b.a(this.d, this.q + "_succeed");
                        for (d dVar : com.unionpay.mobile.android.g.b.bb) {
                            try {
                                this.d.unbindService(((c) dVar).h());
                            } catch (IllegalArgumentException e) {
                            }
                        }
                        if (this.a.f) {
                            this.a.I.f = "success";
                            k();
                            return;
                        }
                        d(8);
                        return;
                    }
                    j();
                    this.F = true;
                    if (a.equalsIgnoreCase("03")) {
                        b.a(this.d, this.q + "_fail", p.j, (Object[]) new String[]{a, a2});
                        a(a2);
                        return;
                    } else if (this.t <= 0) {
                        b(19);
                        return;
                    } else {
                        return;
                    }
                }
                v();
                return;
            case 104:
                try {
                    this.a.bf = (String) jSONObject.get("encrypt_key");
                    this.a.bg = (String) jSONObject.get("auth_id");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (this.a.p != null) {
                    com.unionpay.mobile.android.upviews.a.a a3 = this.x.a();
                    if (a3.a()) {
                        this.u = 101;
                        a(this.a.p);
                        return;
                    }
                    this.F = true;
                    a(a3.b);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.v != null) {
            this.v.setEnabled(!z);
        }
    }

    public final boolean a(HashMap<String, String> hashMap) {
        M = new Date(System.currentTimeMillis());
        N = new SimpleDateFormat("yyyyMMddHHmmss").format(M);
        this.O = new String(N);
        return b((HashMap) hashMap);
    }

    protected final void b() {
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        this.A = new ay(getContext(), com.unionpay.mobile.android.f.c.bD.bq, this);
        layoutParams.addRule(13, -1);
        this.k.addView(this.A, layoutParams);
    }

    public final void b(int i) {
        switch (this.u) {
            case 101:
            case 103:
            case 104:
                this.F = true;
                break;
        }
        super.b(i);
    }

    protected final void c() {
        this.m.removeAllViews();
        this.o.a((com.unionpay.mobile.android.upwidget.UPScrollView.a) this);
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
        layoutParams.addRule(10, -1);
        this.m.addView(linearLayout, layoutParams);
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.g.f fVar = (com.unionpay.mobile.android.g.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.a.aU = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            this.y = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this, this.q);
            this.y.a(this.I);
            this.y.b(this.J);
            this.y.c(this.K);
            this.y.a(this.b, this.a.aU);
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.bottomMargin = com.unionpay.mobile.android.d.a.f;
            linearLayout.addView(this.y, layoutParams);
        }
        a(linearLayout);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        this.v = new TextView(this.d);
        this.v.setText(j.a(this.a.C, "label"));
        this.v.setTextSize(com.unionpay.mobile.android.d.b.i);
        this.v.setTextColor(b.p());
        this.v.setGravity(17);
        TextView textView = this.v;
        boolean z = this.x == null || this.x.e();
        textView.setEnabled(z);
        int i = com.unionpay.mobile.android.d.a.n;
        this.v.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        this.v.setOnClickListener(this.L);
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-1, i);
        layoutParams3.topMargin = com.unionpay.mobile.android.d.a.f;
        i = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams3.rightMargin = i;
        layoutParams3.leftMargin = i;
        linearLayout.addView(this.v, layoutParams3);
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    public final boolean handleMessage(Message message) {
        if (message.obj != null) {
            Bundle bundle = (Bundle) message.obj;
            String string = bundle.getString("action_resp_code");
            String string2 = bundle.getString("action_resp_message");
            if (!"0000".equalsIgnoreCase(string)) {
                a(this.a.ap, false);
            } else if (string2 != null) {
                a(0, string2);
            }
        }
        return true;
    }

    public final void l() {
        if (this.x != null && this.x.d()) {
            return;
        }
        if (this.a.K && this.w) {
            this.a.K = false;
            n();
            return;
        }
        this.a.K = false;
        a(2);
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.x != null) {
            this.x.d();
        }
        this.b = null;
    }

    public final void u() {
    }
}
