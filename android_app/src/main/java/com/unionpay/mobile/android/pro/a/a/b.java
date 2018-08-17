package com.unionpay.mobile.android.pro.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.unionpay.mobile.android.g.c;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.d.f;
import com.unionpay.mobile.android.pboctransaction.d.f.a;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;
import java.util.HashMap;

public class b implements Callback, a {
    private a A = null;
    private boolean B = false;
    private final int a = 0;
    private final int b = 1;
    private final int c = 2;
    private final int d = 4;
    private final int e = 8;
    private int f = 0;
    private Context g = null;
    private Handler h = null;
    private ArrayList<c> i = null;
    private ArrayList<c> j = null;
    private ArrayList<c> k = null;
    private ArrayList<c> l = null;
    private ArrayList<c> m = null;
    private e n = null;
    private d o = null;
    private final com.unionpay.mobile.android.pboctransaction.c p = new c(this);
    private e q = null;
    private com.unionpay.mobile.android.pboctransaction.c.a r = null;
    private final com.unionpay.mobile.android.pboctransaction.c s = new d(this);
    private e t = null;
    private com.unionpay.mobile.android.pboctransaction.simapdu.b u = null;
    private final com.unionpay.mobile.android.pboctransaction.c v = new e(this);
    private e w = null;
    private com.unionpay.mobile.android.pboctransaction.d.b x = null;
    private f y = null;
    private final com.unionpay.mobile.android.pboctransaction.c z = new f(this);

    public b(Context context, String str) {
        this.g = context;
        this.h = new Handler(this);
        this.i = new ArrayList(1);
        com.unionpay.mobile.android.c.a aVar = (com.unionpay.mobile.android.c.a) ((com.unionpay.mobile.android.i.a) context).a(UPPayEngine.class.toString());
        if (a("cn.gov.pbc.tsm.client.mobile.andorid", 1)) {
            this.o = new com.unionpay.mobile.android.pboctransaction.a.a();
        } else {
            this.o = new com.unionpay.mobile.android.pboctransaction.sdapdu.a();
        }
        this.n = new e(this.o, aVar, str);
        try {
            Class.forName("org.simalliance.openmobileapi.SEService");
            this.r = new com.unionpay.mobile.android.pboctransaction.c.a();
            this.q = new e(this.r, aVar, str);
            this.u = com.unionpay.mobile.android.pboctransaction.simapdu.b.e();
            this.t = new e(this.u, aVar, str);
            if (a("com.unionpay.tsmservice", 18)) {
                this.y = new f(this);
                this.y.a(this.h);
                this.w = new e(this.y, aVar, str);
                return;
            }
            com.unionpay.mobile.android.g.b.aB = false;
            this.x = new com.unionpay.mobile.android.pboctransaction.d.b();
            this.w = new e(this.x, aVar, str);
            this.z.b();
        } catch (ClassNotFoundException e) {
            this.s.b();
            this.v.b();
            this.z.b();
        } catch (Exception e2) {
            this.s.b();
            this.v.b();
            this.z.b();
        }
    }

    private final void a(int i) {
        switch (i) {
            case 1:
                k.c("UPCardEngine", "cmcc");
                if (!a("com.unionpay.mobile.tsm", 12)) {
                    this.s.b();
                    return;
                } else if (this.r != null) {
                    this.r.a(this.B);
                    this.r.a(this.s, this.g);
                    return;
                } else {
                    return;
                }
            case 2:
                k.c("UPCardEngine", SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON);
                if (this.u == null) {
                    return;
                }
                if (b().contains("ZTE")) {
                    this.u.a(this.v, this.g);
                    return;
                } else {
                    this.v.b();
                    return;
                }
            case 4:
                k.c("UPCardEngine", "se");
                if (a("com.unionpay.tsmservice", 18) && this.y != null) {
                    Log.e("uppay-spay", "type se  start init");
                    this.y.a(this.z, this.g);
                    return;
                } else if (this.x != null) {
                    this.z.b();
                    return;
                } else {
                    return;
                }
            case 8:
                return;
            default:
                k.c("UPCardEngine", "sd");
                if (this.o != null) {
                    this.o.a(this.p, this.g);
                    return;
                }
                return;
        }
    }

    private boolean a(String str, int i) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.g.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
        } catch (Exception e2) {
        }
        if (packageInfo == null) {
            return false;
        }
        k.a("tsm-client", "tsm version code=" + packageInfo.versionCode);
        return packageInfo.versionCode >= i;
    }

    private static String b() {
        return Build.BRAND + "_" + Build.MODEL;
    }

    private void b(int i) {
        if (i == 1) {
            new Thread(new g(this)).start();
        } else if (i == 2) {
            new Thread(new h(this)).start();
        } else if (i == 4) {
            if (b().contains("ZTE")) {
                new Thread(new i(this)).start();
            }
        } else if (i == 8) {
            new Thread(new j(this)).start();
        }
    }

    public final Bundle a(c cVar, String str, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String str2) {
        if (cVar == null) {
            return null;
        }
        int c = cVar.c();
        int d = cVar.d();
        if (d == 1) {
            com.unionpay.mobile.android.pboctransaction.a aVar = new com.unionpay.mobile.android.pboctransaction.a(cVar.a(), null);
            String str3 = cVar.d() == 1 ? "2" : "1";
            if (c == 8) {
                return this.n.a(aVar, str, str3, hashMap, hashMap2, str2);
            }
            if (c == 4) {
                return this.q.a(aVar, str, str3, hashMap, hashMap2, str2);
            }
            if (c == 16) {
                return this.t.a(aVar, str, str3, hashMap, hashMap2, str2);
            }
            if (c == 1) {
                return this.w.a(aVar, str, str3, hashMap, hashMap2, str2);
            }
            if (c != 32) {
                return null;
            }
            return this.w.a(aVar, str, "10", hashMap, hashMap2, str2);
        } else if (d != 2) {
            return null;
        } else {
            return this.n.a(Integer.parseInt(cVar.a()), str, hashMap2, str2);
        }
    }

    public final void a() {
        if (this.r != null) {
            this.r.a();
            this.r = null;
        }
        if (this.o != null) {
            this.o.a();
            this.o = null;
        }
        if (this.u != null) {
            this.u.a();
            this.u = null;
        }
        if (this.x != null) {
            this.x.a();
            this.x = null;
        }
        if (this.y != null) {
            this.y.a();
            this.y = null;
        }
        this.g = null;
        this.A = null;
        this.h.removeCallbacksAndMessages(null);
        this.h = null;
        this.w = null;
        this.n = null;
        this.q = null;
        this.t = null;
        this.f = 0;
    }

    public final void a(Handler handler, String str, String str2) {
        if (com.unionpay.mobile.android.g.b.aA && com.unionpay.mobile.android.g.b.aB) {
            com.unionpay.mobile.android.g.b.bo = true;
            if (this.y != null && this.w != null) {
                this.y.a(handler);
                this.y.b(str);
                this.y.c(str2);
                Log.e("uppay-spay", "tsmservice  get spay card list");
                this.w.b();
            }
        }
    }

    public final void a(a aVar, boolean z) {
        this.B = z;
        this.A = aVar;
        this.f = 0;
        a(0);
    }

    public final void a(boolean z) {
        k.c("uppay", "startReadList  spay");
        com.unionpay.mobile.android.g.b.aB = z;
        b(8);
    }

    public boolean handleMessage(Message message) {
        k.c("UPCardEngine", " msg.what = " + message.what);
        if (message.what == 1 || message.what == 2 || message.what == 4 || message.what == 8) {
            this.f ^= message.what;
            k.c("UPCardEngine", " mTag = " + this.f);
            if (message.obj != null) {
                if (message.what == 1) {
                    this.j = (ArrayList) message.obj;
                } else if (message.what == 2) {
                    this.k = (ArrayList) message.obj;
                } else if (message.what == 4) {
                    this.l = (ArrayList) message.obj;
                } else if (message.what == 8) {
                    this.m = (ArrayList) message.obj;
                }
            }
            a(message.what);
        }
        if (this.f == 15 && this.A != null) {
            if (this.j != null && this.j.size() > 0) {
                this.i.addAll(this.j);
            }
            if (this.k != null && this.k.size() > 0) {
                this.i.addAll(this.k);
            }
            if (this.l != null && this.l.size() > 0) {
                this.i.addAll(this.l);
            }
            if (this.m != null && this.m.size() > 0) {
                this.i.addAll(this.m);
            }
            this.A.a(this.i);
        }
        return true;
    }
}
