package com.unionpay.mobile.android.e;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.c;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.agoo.a.a.b;
import com.tencent.open.GameAppOperation;
import com.unionpay.mobile.android.c.a;
import com.unionpay.mobile.android.g.d;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private static final Object w = new Object();
    private Context a;
    private a b;
    private Handler c;
    private String d;
    private String e;
    private int f = 0;
    private int g = 0;
    private int h = 10;
    private int i = 1000;
    private String j = "hce";
    private String k = "";
    private String l;
    private String m = "";
    private HashMap<String, k> n = new HashMap(0);
    private List<k> o = new ArrayList(0);
    private HashMap<Integer, k> p = new HashMap(0);
    private int q = 0;
    private int r = 1;
    private int s = 0;
    private int t = 5;
    private ConcurrentHashMap<String, d> u = new ConcurrentHashMap(0);
    private ConcurrentHashMap<String, l> v = new ConcurrentHashMap(0);
    private final Callback x = new g(this);
    private Handler y;

    public f(Context context) {
        this.a = context;
        this.y = new Handler(this.x);
        this.l = "20150801000000000000";
        this.b = (a) ((com.unionpay.mobile.android.i.a) context).a(UPPayEngine.class.toString());
    }

    private Bundle a(String str, int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(c.VERSION, "1.9");
            jSONObject.put(b.JSON_CMD, this.j);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("params", jSONObject2);
            jSONObject2.put("base_version", str);
            jSONObject2.put(ParamKey.PAGE, i);
            jSONObject2.put("size", i2);
            bundle.putString("action_resp_message", this.b.a(jSONObject.toString()));
            a(bundle);
            this.f++;
            if (this.f <= this.g) {
                a(this.l, this.f, this.h);
            }
        } catch (JSONException e) {
            bundle.putString("action_resp_code", "10019");
        }
        return bundle;
    }

    static /* synthetic */ void a(f fVar) {
        for (Entry value : fVar.n.entrySet()) {
            k kVar = (k) value.getValue();
            if (!kVar.a()) {
                String b = kVar.b();
                String e = kVar.e();
                if (com.unionpay.mobile.android.utils.c.a(fVar.a, b) && e.equalsIgnoreCase(com.unionpay.mobile.android.utils.c.b(fVar.a, b))) {
                    fVar.q++;
                    fVar.p.put(Integer.valueOf(fVar.q), kVar);
                    fVar.v.put(b, new l(b));
                    fVar.u.put(b, new d(b));
                }
            }
        }
    }

    static /* synthetic */ void a(f fVar, String str) {
        d dVar = (d) fVar.u.get(str);
        l lVar = (l) fVar.v.get(str);
        if (dVar.a() && lVar.a()) {
            fVar.y.removeMessages(m_AppUI.MSG_APP_VERSION_COMMEND, str);
            String c = lVar.c();
            com.unionpay.mobile.android.e.a.a b = lVar.b();
            Object c2 = dVar.c();
            ServiceConnection d = dVar.d();
            if (!(c == null || TextUtils.isEmpty(c) || c2 == null || TextUtils.isEmpty(c2))) {
                String a = a.a(c2, c);
                k.c("uppay-hce", str + " card after: " + a);
                try {
                    JSONArray jSONArray = new JSONArray(a);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        c2 = jSONArray.get(i);
                        if (c2 != null) {
                            JSONObject jSONObject = (JSONObject) c2;
                            if (com.unionpay.mobile.android.g.b.bb == null) {
                                com.unionpay.mobile.android.g.b.bb = new ArrayList(1);
                            }
                            c cVar = new c(jSONObject, c, b, d);
                            Object obj = null;
                            int i2 = 0;
                            while (com.unionpay.mobile.android.g.b.bb != null && i2 < com.unionpay.mobile.android.g.b.bb.size()) {
                                c2 = (TextUtils.isEmpty(cVar.a()) || !cVar.a().equalsIgnoreCase(((d) com.unionpay.mobile.android.g.b.bb.get(i2)).a())) ? obj : 1;
                                i2++;
                                obj = c2;
                            }
                            if (obj == null) {
                                com.unionpay.mobile.android.g.b.bb.add(cVar);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            dVar.f();
            fVar.u.put(str, dVar);
            lVar.e();
            fVar.v.put(str, lVar);
            synchronized (w) {
                fVar.s--;
            }
            fVar.b();
        }
        for (Entry value : fVar.u.entrySet()) {
            if (!((d) value.getValue()).b()) {
                return;
            }
        }
        k.c("uppay", "hce finished !!!!!!!!!!!!");
        com.unionpay.mobile.android.g.b.bl = true;
        if (fVar.c != null) {
            fVar.c.sendMessage(fVar.c.obtainMessage(0));
        }
    }

    private boolean a(Bundle bundle) {
        String string = bundle.getString("action_resp_code");
        String string2 = bundle.getString("action_resp_message");
        if ("0000".equalsIgnoreCase(string) && string2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(string2);
                string2 = j.a(jSONObject, "resp");
                j.a(jSONObject, "msg");
                JSONObject c = j.c(jSONObject, "params");
                if (string2.equalsIgnoreCase("00")) {
                    int i;
                    this.d = j.c(c, GameAppOperation.GAME_SIGNATURE).toString();
                    JSONArray d = j.d(c, "configs");
                    if (d != null) {
                        for (i = 0; i < d.length(); i++) {
                            this.o.add(new k(d.getJSONObject(i)));
                            this.m = j.a(c, "version");
                        }
                    }
                    i = Integer.decode(j.a(c, "total_count")).intValue();
                    if (i > this.h) {
                        this.g = i / this.h;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean a(String str, String str2) {
        ServiceConnection iVar = new i(this, str, str2);
        d dVar = new d(str);
        dVar.a(iVar);
        this.u.put(str, dVar);
        try {
            Intent intent = new Intent();
            intent.setAction("com.unionpay.uppay.action.HCE");
            intent.setPackage(str);
            this.a.startService(intent);
            return this.a.bindService(intent, iVar, 1);
        } catch (Exception e) {
            return false;
        }
    }

    static /* synthetic */ void k(f fVar) {
        Iterator it;
        if (!TextUtils.isEmpty(fVar.m)) {
            for (k kVar : fVar.o) {
                fVar.n.put(kVar.b(), kVar);
            }
            JSONArray jSONArray = new JSONArray();
            for (Entry value : fVar.n.entrySet()) {
                jSONArray.put(((k) value.getValue()).f());
            }
            PreferenceUtils.a(fVar.a, fVar.m, "hce_version");
            PreferenceUtils.a(fVar.a, jSONArray.toString(), "hce_info");
        }
        if (!TextUtils.isEmpty(fVar.k) && fVar.n != null && fVar.n.size() > 0) {
            it = fVar.n.entrySet().iterator();
            while (it.hasNext()) {
                if (!fVar.k.equals(((k) ((Entry) it.next()).getValue()).c())) {
                    it.remove();
                }
            }
        }
    }

    public final void a() {
        this.f = 1;
    }

    public final void a(int i) {
        this.h = i;
    }

    public final void a(Handler handler) {
        this.c = handler;
    }

    public final void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j = str;
        }
    }

    public final void b() {
        if (this.q == 0) {
            com.unionpay.mobile.android.g.b.bl = true;
            if (this.c != null) {
                this.c.sendMessage(this.c.obtainMessage(0));
                return;
            }
            return;
        }
        synchronized (w) {
            for (int i = this.r; i <= this.q && this.s < this.t; i++) {
                k kVar = (k) this.p.get(Integer.valueOf(i));
                String b = kVar.b();
                String d = kVar.d();
                this.s++;
                new h(this, b, d).start();
                this.r++;
            }
        }
    }

    public final void b(int i) {
        if (i > 0) {
            this.i = i;
        }
    }

    public final void b(String str) {
        this.e = str;
    }

    public final void c() {
        if (com.unionpay.mobile.android.g.b.bb != null) {
            com.unionpay.mobile.android.g.b.bb.clear();
            com.unionpay.mobile.android.g.b.bb = null;
        }
        Object a = PreferenceUtils.a(this.a, "hce_version");
        if (!TextUtils.isEmpty(a)) {
            this.l = a;
        }
        String a2 = PreferenceUtils.a(this.a, "hce_info");
        if (TextUtils.isEmpty(a2)) {
            a2 = "[{\"package\":\"com.yitong.mbank0408\",\"issuer\":\"64083300\",\"syn_key\":\"0123456789ABCDEF1010101010101010\",\"pub_key\":\"268576AF6F50DA40196E18D6E059D2A721373638\",\"status\":\"I\",\"priority\":\"1\"}]";
        }
        try {
            JSONArray jSONArray = new JSONArray(a2);
            for (int i = 0; i < jSONArray.length(); i++) {
                k kVar = new k(jSONArray.getJSONObject(i));
                this.n.put(kVar.b(), kVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new j(this)).start();
    }

    public final void c(int i) {
        if (i > 0) {
            this.t = i;
        }
    }

    public final void c(String str) {
        this.k = str;
    }
}
