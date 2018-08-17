package com.unionpay.mobile.android.pro.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import anet.channel.security.ISecurity;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.pboctransaction.f;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upwidget.UPRadiationView;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.uppay.PayActivity;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class k extends b implements Callback, a.b {
    private static Date ab = new Date(System.currentTimeMillis());
    private static String ac = new SimpleDateFormat("yyyyMMddhhmmss").format(ab);
    private LinearLayout A;
    private LinearLayout B;
    private RelativeLayout C;
    private LinearLayout D;
    private LinearLayout E;
    private ay F;
    private UPRadiationView G;
    private ImageView H;
    private String I;
    private String J;
    private com.unionpay.mobile.android.upwidget.a K;
    private com.unionpay.mobile.android.upwidget.a L;
    private boolean M;
    private boolean N;
    private a O;
    private String P;
    private int Q;
    private NfcAdapter R;
    private FrameLayout S;
    private OnClickListener T;
    private OnClickListener U;
    private OnClickListener V;
    private OnClickListener W;
    private OnClickListener Z;
    private OnClickListener aa;
    private String ad;
    private HashMap<String, String> ae;
    public String r;
    UPPayEngine s;
    com.unionpay.mobile.android.pboctransaction.b.a t;
    private int u;
    private int v;
    private TextView w;
    private boolean x;
    private a y;
    private Handler z;

    public k(Context context, e eVar, UPPayEngine uPPayEngine) {
        super(context, eVar);
        this.u = 20;
        this.v = 100;
        this.r = "1.8";
        this.w = null;
        this.x = false;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.K = null;
        this.L = null;
        this.M = true;
        this.N = false;
        this.O = null;
        this.Q = 5;
        this.T = new s(this);
        this.U = new u(this);
        this.V = new w(this);
        this.W = new z(this);
        this.Z = new aa(this);
        this.aa = new ab(this);
        this.ad = null;
        this.ae = new HashMap();
        this.f = 6;
        this.q = "nfcpay";
        this.s = uPPayEngine;
        this.z = new Handler(this);
        this.x = this.a.K;
        setBackgroundColor(-11495169);
        e();
    }

    static /* synthetic */ void a(k kVar, String str, String str2) {
        kVar.v = 7;
        kVar.b.a(c.bD.U);
        kVar.e.c(str, str2);
    }

    private void a(String str, StringBuffer stringBuffer) {
        byte[] bArr = new byte[]{(byte) (((String) this.ae.get(str)).length() / 2)};
        String a = f.a(bArr, bArr.length);
        stringBuffer.append(str);
        stringBuffer.append(a);
        stringBuffer.append(r0);
    }

    private void b(String str, HashMap<String, String> hashMap) {
        Object a = ((PayActivity) this.d).a(com.unionpay.mobile.android.pro.a.a.b.class.toString());
        if ((a != null ? (com.unionpay.mobile.android.pro.a.a.b) a : null) == null) {
            b(5);
        } else {
            new Thread(new ac(this, str, hashMap)).start();
        }
    }

    private synchronized Bundle c(String str, HashMap<String, String> hashMap) {
        Bundle bundle;
        Bundle x = x();
        this.ae.put("PIN", str);
        StringBuffer stringBuffer = new StringBuffer();
        a("9F26", stringBuffer);
        a("9F27", stringBuffer);
        a("9F10", stringBuffer);
        a("9F37", stringBuffer);
        a("9F36", stringBuffer);
        a("95", stringBuffer);
        a("9A", stringBuffer);
        a("9C", stringBuffer);
        a("9F02", stringBuffer);
        a("5F2A", stringBuffer);
        a("82", stringBuffer);
        a("9F1A", stringBuffer);
        a("9F03", stringBuffer);
        a("9F33", stringBuffer);
        a("9F34", stringBuffer);
        a("9F35", stringBuffer);
        a("9F1E", stringBuffer);
        if (!(this.ae.get("9F63") == null || TextUtils.isEmpty((CharSequence) this.ae.get("9F63")))) {
            a("9F63", stringBuffer);
        }
        this.ae.put("DCD", stringBuffer.toString());
        this.I = e(this.I, this.s.b());
        String str2 = (((("" + "pan=" + ((String) this.ae.get("AN1"))) + "&pin=" + ((String) this.ae.get("PIN"))) + "&icc_data=" + ((String) this.ae.get("DCD"))) + "&card_seq_id=" + ((String) this.ae.get("5F34"))) + "&auth_id=" + this.J;
        com.unionpay.mobile.android.utils.k.c(SocializeProtocolConstants.PROTOCOL_KEY_MAC, str2);
        String d = d(str2);
        com.unionpay.mobile.android.utils.k.c("md5", d);
        d = this.e.h(d);
        com.unionpay.mobile.android.utils.k.c("sig", d);
        str2 = this.e.a(str2 + "&" + d, this.I);
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
            jSONObject2.put("carrier_tp", "7");
            jSONObject2.put("carrier_app_tp", "0");
            jSONObject2.put("sign", d);
            jSONObject2.put("pan", this.ae.get("AN1"));
            if (this.ae.get("ED") != null) {
                jSONObject2.put("expire", this.ae.get("ED"));
            }
            if (this.ae.get("TD2") != null) {
                jSONObject2.put("track2_data", this.ae.get("TD2"));
            }
            if (!(hashMap == null || hashMap.keySet() == null || hashMap.keySet().size() <= 0)) {
                hashMap.remove("pan");
                hashMap.remove("pin");
                for (String str22 : hashMap.keySet()) {
                    jSONObject2.put(str22, hashMap.get(str22));
                }
            }
            x.putString("action_resp_message", this.s.a(jSONObject.toString()));
            bundle = x;
        } catch (JSONException e) {
            x.putString("action_resp_code", "10019");
            bundle = x;
        }
        return bundle;
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

    private void d(String str, String str2) {
        this.v = 8;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.Q--;
    }

    private static String e(String str, String str2) {
        byte[] a = f.a(str);
        byte[] a2 = f.a(str2);
        for (int i = 0; i < a.length; i++) {
            a[i] = (byte) (a[i] ^ a2[i]);
        }
        return f.a(a);
    }

    private void v() {
        this.v = 103;
        int i = this.u;
        this.e.a("query", this.a.aj, 3);
        this.u--;
    }

    private HashMap<String, String> w() {
        HashMap<String, String> hashMap = new HashMap();
        if (this.y != null) {
            hashMap = this.y.c();
        }
        if (this.O == null) {
            return hashMap;
        }
        Map c = this.O.c();
        if (c == null || hashMap == null) {
            return (c == null || hashMap != null) ? hashMap : c;
        } else {
            hashMap.putAll(c);
            return hashMap;
        }
    }

    private static Bundle x() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    private int y() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public final Bundle a(String str, HashMap<String, String> hashMap) {
        return c(str, (HashMap) hashMap);
    }

    public final void a(NfcAdapter nfcAdapter) {
        this.R = nfcAdapter;
        if (this.R.isEnabled()) {
            this.B.setVisibility(8);
            if (this.M) {
                this.D.setVisibility(0);
            }
            this.E.setVisibility(8);
            if (this.G != null) {
                this.G.setVisibility(0);
                return;
            }
            return;
        }
        this.B.setVisibility(0);
        this.D.setVisibility(8);
        this.E.setVisibility(0);
        if (this.G != null) {
            this.G.setVisibility(4);
        }
    }

    public final void a(com.unionpay.mobile.android.pboctransaction.b.a aVar) {
        Object format;
        boolean z;
        this.t = aVar;
        HashMap hashMap = this.a.p;
        String substring = ac.substring(2, 8);
        String valueOf = String.valueOf(new Date(System.currentTimeMillis()).getTime());
        if (valueOf.length() < 8) {
            format = String.format("%08d", new Object[]{Long.valueOf(r6)});
        } else {
            format = valueOf.substring(valueOf.length() - 8, valueOf.length());
        }
        this.ae.put("9F26", "");
        this.ae.put("9F27", "80");
        this.ae.put("9F10", "");
        this.ae.put("9F37", format);
        this.ae.put("9F36", "");
        this.ae.put("95", "0000000800");
        this.ae.put("9A", substring);
        this.ae.put("9C", "45");
        this.ae.put("9F02", "000000000000");
        this.ae.put("5F2A", "0156");
        this.ae.put("82", "");
        this.ae.put("9F1A", "0156");
        this.ae.put("9F03", "000000000000");
        this.ae.put("9F33", "A04000");
        this.ae.put("9F34", "420300");
        this.ae.put("9F35", "34");
        this.ae.put("9F1E", "3030303030303030");
        this.ae.put("84", "");
        this.ae.put("9F09", "0001");
        this.ae.put("9F74", "");
        this.ae.put("9F63", "");
        this.ae.put("9F7A", "00");
        this.ae.put("9F21", ac.substring(8));
        this.ae.put("9F4E", "0000000000000000000000000000000000000000");
        this.ae.put("DF31", "0100000000");
        this.ae.put("9F66", "36800000");
        this.ae.put("DF60", "00");
        this.ae.put("9F02", hashMap.get("trans_amt"));
        this.ae.put("9F1A", "0156");
        this.ae.put("5F2A", hashMap.get("trans currcy code"));
        this.ae.put("9C", hashMap.get("trans_type"));
        this.ae.put("CUR", this.ae.get("5F2A"));
        format = x();
        ab = new Date(System.currentTimeMillis());
        ac = new SimpleDateFormat("yyyyMMddHHmmss").format(ab);
        this.ad = new String(ac);
        String a = this.t.a();
        if (a == null || a.length() == 0) {
            format.putString("action_resp_code", "10019");
        } else if ("noSupUnionpay".equals(a)) {
            format.putString("action_resp_code", "10024");
        } else {
            a = this.t.a(a, this.ae);
            if (a == null || a.length() == 0) {
                format.putString("action_resp_code", "10020");
            } else {
                com.unionpay.mobile.android.pboctransaction.b.a aVar2 = this.t;
                com.unionpay.mobile.android.pboctransaction.b.a.b(a, this.ae);
            }
        }
        if (format.getString("action_resp_code") != "0000") {
            Handler handler = this.z;
            Handler handler2 = this.z;
            if (format == null) {
                format = null;
            }
            handler.sendMessage(handler2.obtainMessage(0, format));
            z = false;
        } else {
            z = true;
        }
        if (z && this.M) {
            this.v = 102;
            this.j = false;
            this.b.a(c.bD.U);
            this.e.c("cardrules", "\"pan\":\"" + ((String) this.ae.get("AN1")) + "\",\"carrier_tp\":\"7\"");
        }
    }

    public final void a(a.a aVar) {
    }

    protected final void a(String str, boolean z) {
        this.b.a(new t(this, z), null);
        this.b.a(c.bD.Y, str, c.bD.W);
    }

    public final void a(JSONObject jSONObject) {
        boolean z = true;
        JSONArray d;
        String a;
        String a2;
        switch (this.v) {
            case 7:
                j();
                d = j.d(jSONObject, "options");
                if (this.O != null) {
                    this.O.a(d);
                    return;
                }
                return;
            case 8:
                a = j.a(jSONObject, "status");
                if (a == null || !"01".equals(a)) {
                    d = j.d(jSONObject, "options");
                    a2 = j.a(jSONObject, "empty_info");
                    if (this.O != null) {
                        this.O.a(d, a2);
                        return;
                    }
                    return;
                }
                a = j.a(jSONObject, "uuid");
                if (this.Q >= 0) {
                    d(this.P, a);
                    return;
                }
                a = c.bD.D;
                if (this.O != null) {
                    this.O.a(null, a);
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
                this.u = 20;
                v();
                return;
            case 102:
                this.b.c();
                try {
                    this.I = (String) jSONObject.get("encrypt_key");
                    this.J = (String) jSONObject.get("auth_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int a3 = com.unionpay.mobile.android.nocard.utils.f.a(this.a, jSONObject, false);
                this.p = com.unionpay.mobile.android.nocard.utils.f.a(jSONObject);
                if (a3 != 0) {
                    b(a3);
                    return;
                }
                LayoutParams layoutParams;
                this.M = false;
                this.S.setBackgroundColor(-1052684);
                setBackgroundColor(-1052684);
                this.F.setBackgroundColor(com.unionpay.mobile.android.d.a.M);
                this.F.a(8);
                this.A.removeAllViews();
                this.B.setVisibility(8);
                this.m.setBackgroundColor(-1052684);
                this.l.setVisibility(0);
                this.H.setVisibility(8);
                this.D.setVisibility(8);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                JSONArray jSONArray = new JSONArray();
                if (this.p != null) {
                    com.unionpay.mobile.android.g.f fVar = (com.unionpay.mobile.android.g.f) this.p;
                    jSONArray.put(fVar.a("promotion"));
                    jSONArray.put(fVar.a("instalment"));
                    this.a.aU = fVar.a("promotion_instalment_msgbox");
                }
                if (jSONArray.length() > 0) {
                    this.O = new a(this.d, jSONArray, this, this.q);
                    this.O.a(this.b, this.a.aU);
                    this.O.a(this.U);
                    this.O.b(this.V);
                    this.O.c(this.W);
                    layoutParams = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
                    this.A.addView(this.O, layoutParams);
                }
                if (this.O != null) {
                    this.O.c("instalment");
                }
                this.y = new a(this.d, this.a.z, this.e.c(), this, (String) this.ae.get("AN1"), true, this.q);
                layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
                this.A.addView(this.y, layoutParams);
                View linearLayout = new LinearLayout(this.d);
                linearLayout.setOrientation(1);
                linearLayout.setId(linearLayout.hashCode());
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams4.topMargin = com.unionpay.mobile.android.d.a.f;
                layoutParams4.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
                this.A.addView(linearLayout, layoutParams4);
                if (!(!i() && this.a.al == null && this.a.am == null)) {
                    if (this.a.al != null) {
                        this.L = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.al, this.aa, this.q + "_agree_user_protocol");
                        linearLayout.addView(this.L);
                    }
                    if (this.a.am != null) {
                        this.K = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.am, null, this.q + "_remember_bankNO");
                        layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams4.topMargin = com.unionpay.mobile.android.d.a.f;
                        linearLayout.addView(this.K, layoutParams4);
                    }
                }
                layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                this.w = new TextView(this.d);
                this.w.setText(j.a(this.a.C, "label"));
                this.w.setTextSize(com.unionpay.mobile.android.d.b.i);
                this.w.setTextColor(b.p());
                this.w.setGravity(17);
                TextView textView = this.w;
                if (!(this.y == null || this.y.e())) {
                    z = false;
                }
                textView.setEnabled(z);
                a3 = com.unionpay.mobile.android.d.a.n;
                this.w.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
                this.w.setOnClickListener(this.T);
                layoutParams4 = new LinearLayout.LayoutParams(-1, a3);
                layoutParams4.topMargin = com.unionpay.mobile.android.d.a.f;
                a3 = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
                layoutParams4.rightMargin = a3;
                layoutParams4.leftMargin = a3;
                this.A.addView(this.w, layoutParams4);
                return;
            case 103:
                a = j.a(jSONObject, "status");
                a2 = j.a(jSONObject, "fail_msg");
                if (this.u <= 0 || !a.equalsIgnoreCase("01")) {
                    this.v = 100;
                    if (a.equalsIgnoreCase("00")) {
                        j();
                        this.v = 100;
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
                        if (this.y != null) {
                            this.y.f();
                        }
                        b.a(this.d, this.q + "_succeed");
                        if (this.a.f) {
                            this.a.I.f = "success";
                            k();
                            return;
                        }
                        d(8);
                        return;
                    }
                    j();
                    if (a.equalsIgnoreCase("03")) {
                        b.a(this.d, this.q + "_fail", p.j, (Object[]) new String[]{a, a2});
                        a(a2);
                        return;
                    } else if (this.u <= 0) {
                        a(this.a.ak);
                        return;
                    } else {
                        return;
                    }
                }
                v();
                return;
            case 104:
                try {
                    this.I = (String) jSONObject.get("encrypt_key");
                    this.J = (String) jSONObject.get("auth_id");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (this.a.p != null) {
                    a.a a4 = this.y.a();
                    if (a4.a()) {
                        this.v = 101;
                        b(this.y.a().b, w());
                        return;
                    }
                    a(a4.b);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.w != null) {
            this.w.setEnabled(!z);
        }
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        if (this.a.aD) {
            this.F = new ay(this.d, c.bD.bo, this.c.a(1030), g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN), this);
        } else {
            this.F = new ay(getContext(), c.bD.bo, this);
        }
        this.F.setBackgroundColor(-16686660);
        this.F.a(0);
        layoutParams.addRule(13, -1);
        this.k.addView(this.F, layoutParams);
    }

    public final void b(int i) {
        int i2 = this.v;
        super.b(i);
    }

    protected final void c() {
        setBackgroundColor(-11495169);
        this.l.setVisibility(8);
        this.S = new FrameLayout(this.d);
        this.m.addView(this.S, new RelativeLayout.LayoutParams(-1, -1));
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, (y() - com.unionpay.mobile.android.d.b.n) - (com.unionpay.mobile.android.d.b.n / 2));
        this.A = new LinearLayout(this.d);
        this.A.setId(this.A.hashCode());
        this.A.setOrientation(1);
        this.S.addView(this.A, layoutParams);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(com.unionpay.mobile.android.d.b.n * 2, com.unionpay.mobile.android.d.b.n * 2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        layoutParams2.leftMargin = (displayMetrics.widthPixels / 2) - com.unionpay.mobile.android.d.b.n;
        layoutParams2.topMargin = (y() / 2) - (com.unionpay.mobile.android.d.b.n * 2);
        this.H = new ImageView(this.d);
        this.H.setBackgroundDrawable(this.c.a(1032));
        this.S.addView(this.H, layoutParams2);
        this.E = new LinearLayout(this.d);
        this.E.setBackgroundColor(-1342177280);
        this.S.addView(this.E, new FrameLayout.LayoutParams(-1, -1));
        this.C = new RelativeLayout(this.d);
        this.S.addView(this.C, new FrameLayout.LayoutParams(-1, -1));
        this.m.setBackgroundColor(-11495169);
        LinearLayout linearLayout = this.A;
        this.A.removeAllViews();
        this.G = new UPRadiationView(this.d);
        linearLayout.addView(this.G, new LinearLayout.LayoutParams(-1, -1));
        RelativeLayout relativeLayout = this.C;
        relativeLayout.setGravity(1);
        int a = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.B = new LinearLayout(this.d);
        this.B.setId(this.B.hashCode());
        this.B.setOrientation(0);
        this.B.setPadding(a * 2, a, a * 2, a);
        Drawable a2 = this.c.a(1033);
        this.B.setBackgroundDrawable(a2);
        CharSequence charSequence = c.bD.bl;
        View textView = new TextView(this.d);
        textView.setTextColor(-1);
        textView.setText(charSequence);
        textView.setTextSize(com.unionpay.mobile.android.d.b.k);
        this.B.addView(textView);
        charSequence = c.bD.bm;
        textView = new TextView(this.d);
        textView.setTextColor(-16729682);
        textView.setText(charSequence);
        textView.setTextSize(com.unionpay.mobile.android.d.b.k);
        textView.setOnClickListener(this.Z);
        this.B.addView(textView);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(10, -1);
        layoutParams3.topMargin = com.unionpay.mobile.android.d.b.n;
        relativeLayout.addView(this.B, layoutParams3);
        this.D = new LinearLayout(this.d);
        this.D.setId(this.D.hashCode());
        this.D.setOrientation(0);
        this.D.setGravity(17);
        this.D.setPadding(a * 2, a, a * 2, a);
        this.D.setBackgroundDrawable(a2);
        CharSequence charSequence2 = c.bD.bn;
        View textView2 = new TextView(this.d);
        textView2.setTextColor(-1);
        textView2.setText(charSequence2);
        textView2.setTextSize(com.unionpay.mobile.android.d.b.k);
        this.D.addView(textView2);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(12, -1);
        layoutParams2.bottomMargin = com.unionpay.mobile.android.d.b.n;
        relativeLayout.addView(this.D, layoutParams2);
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
                "10024".equalsIgnoreCase(string);
                a(this.a.ap, false);
            } else if (string2 != null) {
                a(0, string2);
            }
        }
        return true;
    }

    public final void l() {
        if (this.a.aD) {
            this.b.a(new ad(this), new ae(this));
            this.b.a(c.bD.Y, c.bD.av, c.bD.W, c.bD.X);
        } else if (this.y != null && this.y.d()) {
        } else {
            if (this.a.K && this.x) {
                this.a.K = false;
                n();
                return;
            }
            this.a.K = false;
            this.M = false;
            a(2);
        }
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.y != null) {
            this.y.d();
        }
        if (this.G != null) {
            this.G.a();
        }
        this.G = null;
        this.b = null;
    }

    public final void u() {
    }
}
