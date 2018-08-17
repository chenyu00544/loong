package com.unionpay.mobile.android.pboctransaction.d;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.baidu.mapapi.UIMsg.f_FUN;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.open.GameAppOperation;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.b.aq;
import com.unionpay.tsmservice.b.bc;
import com.unionpay.tsmservice.b.bk;
import com.unionpay.tsmservice.b.bo;
import com.unionpay.tsmservice.b.bs;
import com.unionpay.tsmservice.b.by;
import com.unionpay.tsmservice.b.w;
import com.unionpay.tsmservice.i;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public final class f implements d {
    private String A = "";
    private String B = "";
    private bk C;
    private long D = 8000;
    private boolean E = false;
    private final Callback F = new g(this);
    private final Handler G = new Handler(this.F);
    private bo H;
    private by I;
    private final com.unionpay.tsmservice.i.a J = new h(this);
    String a = "19999741583305435775450371903957622252895007857586703985696530069777024392884287211670048223494223356836139331264745305488035196420545843046674853984852305228918004888414759300445308845681087472809487791392726663269247999482633231304479943902981311338709709401000108625221857486530967716878328228310703650408575058288784573884262229674701501842066793928002725038356122707147929560460157457327696696471785787505023643000687928051333648369477362945785046976181683285277919023274376124429148429078602516462345014971452220809120399264066736558357572250447243744965533695780751271768398207631002867947152625578881506566297";
    String b = "65537";
    String c = "5929703506495688276130676968213384164609348882017291684789802337394713840702726472221198819456433069055388915357817202968369194525956730949539025096963015440180443916974948318765778051794088998339276397676916425744003507605582286608745438301704836361482343765671805403004194772735755889141443700570750608527755694790475628670051863813384800013641474007746161600969180035295709344887092512089121125289090881005234379649440422346798246278284328310221953743757037875834557694749810951089453346522229122216198442376081589767583019062954875861469699069474707285206935898628020341168773624455554331118138151051364372906861";
    String d = "UnionPay";
    String e = "";
    boolean f = false;
    boolean g = false;
    boolean h = false;
    private final String i = "A0000003334355502D4D4F42494C45";
    private final int j = 10000;
    private Context k;
    private c l;
    private a m;
    private i n;
    private Handler o = null;
    private int p = 0;
    private final int q = 8;
    private boolean r = false;
    private String s = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    private String t = "";
    private boolean u = false;
    private String v = null;
    private boolean w = false;
    private String x = "";
    private String y = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    private String z = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;

    public interface a {
        void a(boolean z);
    }

    public f(a aVar) {
        this.m = aVar;
    }

    static /* synthetic */ void a(f fVar, int i, String str) {
        switch (i) {
            case 1000:
                fVar.a(false);
                return;
            case 1011:
                k.c("uppay", "open channel fail");
                fVar.s = null;
                fVar.t = "";
                fVar.r = true;
                return;
            case 1012:
                k.c("uppay", "apdu fail");
                fVar.G.removeMessages(3);
                fVar.u = true;
                return;
            case 1013:
                k.c("uppay", "close channel fail");
                fVar.w = true;
                return;
            case 1014:
                fVar.o.sendMessage(fVar.G.obtainMessage(8, null));
                return;
            case 1015:
                k.c("uppay-spay", "get spay list call back");
                fVar.o.sendMessage(fVar.o.obtainMessage(f_FUN.FUN_ID_VOICE_SCH, str));
                return;
            case 1016:
            case 1018:
                k.c("uppay-spay", "check spay support fail");
                fVar.m.a(false);
                return;
            default:
                return;
        }
    }

    private void a(String str, String str2) {
        this.I = new by();
        this.I.a(str2);
        this.I.b(str);
        if (this.f) {
            this.I.d(g());
        }
    }

    private void a(boolean z) {
        if (this.l == null) {
            return;
        }
        if (z) {
            this.l.a();
        } else {
            this.l.b();
        }
    }

    private void f() {
        if (this.H == null) {
            this.H = new bo();
            if (this.f) {
                this.H.a(g());
                this.H.d(g());
            }
        }
        try {
            int a = this.n.a(this.H, new e(1000, this.G));
            if (a != 0) {
                this.G.sendMessage(Message.obtain(this.G, 1, 1000, 0, ""));
            } else {
                this.G.sendMessageDelayed(Message.obtain(this.G, 4, 1000, 0, ""), this.D);
            }
            k.c("uppay", "ret = " + a);
        } catch (RemoteException e) {
            a(false);
            e.printStackTrace();
        }
    }

    private String g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(GameAppOperation.GAME_SIGNATURE, this.e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public final String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        this.t = "";
        this.r = false;
        try {
            bs bsVar = new bs();
            bsVar.a(str);
            if (this.f) {
                bsVar.d(g());
            }
            if (this.n.a(bsVar, new e(1011, this.G)) != 0) {
                this.G.sendMessage(Message.obtain(this.G, 1, 1011, 0, ""));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        while (TextUtils.isEmpty(this.t)) {
            if (this.r) {
                break;
            }
        }
        if ("A0000003334355502D4D4F42494C45".equals(str)) {
            this.y = this.s;
        } else {
            this.z = this.s;
        }
        this.r = false;
        return this.t;
    }

    public final ArrayList<com.unionpay.mobile.android.g.c> a(e eVar) {
        if (this.n != null) {
            try {
                if (b.aB && b.aA && b.bo) {
                    aq aqVar = new aq();
                    com.unionpay.tsmservice.a.a aVar = new com.unionpay.tsmservice.a.a();
                    aVar.b(this.A);
                    Object e = com.unionpay.mobile.android.pboctransaction.f.e(this.B);
                    if (!TextUtils.isEmpty(e)) {
                        aVar.a(e);
                    }
                    aqVar.a(aVar);
                    if (this.f) {
                        aqVar.d(g());
                    }
                    int a = this.n.a(aqVar, new e(1015, this.G));
                    if (a != 0) {
                        this.G.sendMessage(Message.obtain(this.G, 1, 1015, 0, ""));
                    } else {
                        this.G.sendMessageDelayed(Message.obtain(this.G, 4, 1015, 0, ""), 8000);
                    }
                    k.c("uppay", "readList: " + a);
                    k.c("uppay", "readList");
                } else {
                    bc bcVar = new bc();
                    if (this.f) {
                        bcVar.d(g());
                    }
                    if (this.n.a(bcVar, new e(1014, this.G)) != 0) {
                        this.G.sendMessage(Message.obtain(this.G, 1, 1014, 0, ""));
                    } else {
                        this.G.sendMessageDelayed(Message.obtain(this.G, 4, 1014, 0, ""), this.D);
                    }
                    k.c("uppay", "readList");
                }
            } catch (RemoteException e2) {
                a(false);
                e2.printStackTrace();
            } catch (Exception e3) {
                a(false);
                e3.printStackTrace();
            }
        }
        return null;
    }

    public final void a() {
        if (this.n != null) {
            this.n.b(this.J);
            this.n.e();
        }
    }

    public final void a(Handler handler) {
        this.o = handler;
    }

    public final void a(c cVar, Context context) {
        this.l = cVar;
        this.k = context;
        try {
            this.e = a.a(KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateKeySpec(new BigInteger(this.a), new BigInteger(this.c))), this.d);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e2) {
            e2.printStackTrace();
        }
        if (b.bm) {
            this.D = 60000;
        }
        this.f = !"com.unionpay.uppay".equals(com.unionpay.mobile.android.utils.f.b(this.k));
        this.n = i.a(context);
        if (!this.g) {
            this.n.a(this.J);
            this.g = true;
        }
        k.c("uppay-spay", "type se  bind service");
        if (this.n != null && !this.n.b()) {
            k.c("uppay", "bind service");
            if (!this.n.d()) {
                a(false);
            }
        } else if (this.n != null && this.n.b()) {
            k.c("uppay", "tem service already connected");
            if (this.h) {
                a(true);
            } else {
                f();
            }
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        Object obj = null;
        this.v = null;
        this.u = false;
        k.a("uppay", "--->" + com.unionpay.mobile.android.pboctransaction.f.a(bArr));
        if (i == 0) {
            a(com.unionpay.mobile.android.pboctransaction.f.a(bArr), this.z);
        } else if (i == 1) {
            a(com.unionpay.mobile.android.pboctransaction.f.a(bArr), this.y);
        }
        try {
            if (this.n.a(this.I, new e(1012, this.G)) != 0) {
                this.G.sendMessage(Message.obtain(this.G, 1, 1012, 0, ""));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.G.sendMessageDelayed(Message.obtain(this.G, 3), 10000);
        while (TextUtils.isEmpty(this.v)) {
            if (this.u) {
                break;
            }
        }
        k.a("uppay", "mApduResult: " + this.v + ",mApduReturn:" + this.u);
        if (this.v != null) {
            obj = com.unionpay.mobile.android.pboctransaction.f.a(this.v);
            k.a("uppay", "ret1 <---" + obj);
        }
        k.a("uppay", "<---" + this.v);
        this.u = false;
        k.a("uppay", "ret2 <---" + obj);
        return obj;
    }

    public final void b() {
    }

    public final void b(String str) {
        this.A = str;
    }

    public final void c() {
        w wVar;
        if (!(this.y == null || WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(this.y))) {
            this.x = "";
            this.w = false;
            try {
                wVar = new w();
                wVar.a(this.y);
                if (this.f) {
                    wVar.d(g());
                }
                if (this.n.a(wVar, new e(1013, this.G)) != 0) {
                    this.G.sendMessage(Message.obtain(this.G, 1, 1013, 0, ""));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            while (TextUtils.isEmpty(this.x)) {
                if (this.w) {
                    break;
                }
            }
            this.y = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            this.w = false;
        }
        if (this.z != null && !WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(this.z)) {
            this.x = "";
            this.w = false;
            try {
                wVar = new w();
                wVar.a(this.z);
                if (this.f) {
                    wVar.d(g());
                }
                if (this.n.a(wVar, new e(1013, this.G)) != 0) {
                    this.G.sendMessage(Message.obtain(this.G, 1, 1013, 0, ""));
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            while (TextUtils.isEmpty(this.x)) {
                if (this.w) {
                    break;
                }
            }
            this.z = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            this.w = false;
        }
    }

    public final void c(String str) {
        this.B = str;
    }

    public final void d() {
    }

    public final boolean e() {
        try {
            k.c("uppay", "getVendorPayStatus()");
            if (this.C == null) {
                this.C = new bk();
                if (this.f) {
                    this.C.d(g());
                }
            }
            if (this.n.a(this.C, new e(1018, this.G)) == 0) {
                return true;
            }
            this.G.sendMessage(Message.obtain(this.G, 1, 1018, 0, ""));
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
