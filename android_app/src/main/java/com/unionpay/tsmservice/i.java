package com.unionpay.tsmservice;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mapapi.UIMsg.f_FUN;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.taobao.accs.common.Constants;
import com.unionpay.tsmservice.b.aa;
import com.unionpay.tsmservice.b.ac;
import com.unionpay.tsmservice.b.ae;
import com.unionpay.tsmservice.b.ag;
import com.unionpay.tsmservice.b.ai;
import com.unionpay.tsmservice.b.ak;
import com.unionpay.tsmservice.b.am;
import com.unionpay.tsmservice.b.ao;
import com.unionpay.tsmservice.b.aq;
import com.unionpay.tsmservice.b.as;
import com.unionpay.tsmservice.b.aw;
import com.unionpay.tsmservice.b.ay;
import com.unionpay.tsmservice.b.ba;
import com.unionpay.tsmservice.b.bc;
import com.unionpay.tsmservice.b.be;
import com.unionpay.tsmservice.b.bg;
import com.unionpay.tsmservice.b.bi;
import com.unionpay.tsmservice.b.bk;
import com.unionpay.tsmservice.b.bm;
import com.unionpay.tsmservice.b.bo;
import com.unionpay.tsmservice.b.bq;
import com.unionpay.tsmservice.b.bs;
import com.unionpay.tsmservice.b.bu;
import com.unionpay.tsmservice.b.bw;
import com.unionpay.tsmservice.b.by;
import com.unionpay.tsmservice.b.ca;
import com.unionpay.tsmservice.b.cc;
import com.unionpay.tsmservice.b.y;
import com.unionpay.tsmservice.c.e;
import com.unionpay.tsmservice.c.g;
import com.unionpay.tsmservice.c.k;
import com.unionpay.tsmservice.c.m;
import com.unionpay.tsmservice.c.o;
import com.unionpay.tsmservice.c.q;
import com.unionpay.tsmservice.c.s;
import com.unionpay.tsmservice.c.u;
import com.unionpay.tsmservice.c.w;
import com.unionpay.tsmservice.utils.IUPJniInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class i {
    private static i a = null;
    private static ArrayList<a> b = null;
    private HashMap<String, d> A = new HashMap();
    private HashMap<String, d> B = new HashMap();
    private HashMap<String, d> C = new HashMap();
    private HashMap<String, d> D = new HashMap();
    private HashMap<String, d> E = new HashMap();
    private HashMap<String, d> F = new HashMap();
    private HashMap<String, d> G = new HashMap();
    private HashMap<String, d> H = new HashMap();
    private HashMap<String, d> I = new HashMap();
    private HashMap<String, d> J = new HashMap();
    private HashMap<String, d> K = new HashMap();
    private HashMap<String, d> L = new HashMap();
    private HashMap<String, d> M = new HashMap();
    private HashMap<String, d> N = new HashMap();
    private HashMap<String, d> O = new HashMap();
    private HashMap<String, d> P = new HashMap();
    private HashMap<String, d> Q = new HashMap();
    private HashMap<String, c> R = new HashMap();
    private int[] S;
    private final Callback T = new j(this);
    private final Handler U = new Handler(Looper.getMainLooper(), this.T);
    private Context c = null;
    private ServiceConnection d = null;
    private f e = null;
    private boolean f = false;
    private int g = 1;
    private boolean h = false;
    private HashMap<String, d> i = new HashMap();
    private HashMap<String, d> j = new HashMap();
    private HashMap<String, d> k = new HashMap();
    private HashMap<String, d> l = new HashMap();
    private HashMap<String, d> m = new HashMap();
    private HashMap<String, d> n = new HashMap();
    private HashMap<String, d> o = new HashMap();
    private HashMap<String, d> p = new HashMap();
    private HashMap<String, d> q = new HashMap();
    private HashMap<String, d> r = new HashMap();
    private HashMap<String, d> s = new HashMap();
    private HashMap<String, d> t = new HashMap();
    private HashMap<String, d> u = new HashMap();
    private HashMap<String, d> v = new HashMap();
    private HashMap<String, d> w = new HashMap();
    private HashMap<String, d> x = new HashMap();
    private HashMap<String, d> y = new HashMap();
    private HashMap<String, d> z = new HashMap();

    public interface a {
        void a();

        void b();
    }

    public class b extends com.unionpay.tsmservice.c.a {
        final /* synthetic */ i a;
        private int b = 1000;

        public b(i iVar) {
            this.a = iVar;
        }

        public final void a(String str, String str2, int i, Bundle bundle) throws RemoteException {
            i iVar = this.a;
            i.a((c) i.b(this.a, this.b).get(this.a.c.getPackageName()), str, str2, i, bundle);
            i.b(this.a, this.b).remove(this.a.c.getPackageName());
        }
    }

    private class c extends com.unionpay.tsmservice.d.a {
        final /* synthetic */ i a;
        private int b;
        private int c;

        private c(i iVar, int i, int i2) {
            this.a = iVar;
            this.b = i;
            this.c = i2;
        }

        public final void a(Bundle bundle) throws RemoteException {
            i iVar;
            Bundle bundle2 = new Bundle();
            int i = this.b;
            Bundle bundle3 = new Bundle();
            Parcel obtain = Parcel.obtain();
            String string = bundle.getString(Constants.KEY_ERROR_CODE);
            String string2 = bundle.getString("result");
            bundle3.putString(Constants.KEY_ERROR_CODE, string);
            if (!TextUtils.isEmpty(string2)) {
                byte[] decode = Base64.decode(this.a.c(string2), 0);
                if (!(decode == null || decode.length == 0)) {
                    obtain.unmarshall(decode, 0, decode.length);
                    obtain.setDataPosition(0);
                }
                if (obtain.dataSize() == 0) {
                    bundle3.putString(Constants.KEY_ERROR_CODE, "010035");
                    bundle = bundle3;
                    obtain.recycle();
                    iVar = this.a;
                    i.a((d) i.a(this.a, this.b).get(String.valueOf(this.c)), bundle);
                    i.a(this.a, this.b).remove(String.valueOf(this.c));
                    if (i.a(this.a, this.b).isEmpty()) {
                        this.a.S[this.b] = 0;
                    }
                }
            }
            switch (i) {
                case 0:
                    bundle3.putParcelable("result", (o) obtain.readParcelable(o.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 3:
                    bundle3.putParcelable("result", (k) obtain.readParcelable(k.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 5:
                    break;
                case 12:
                    bundle3.putParcelable("result", (m) obtain.readParcelable(m.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 20:
                    bundle3.putParcelable("result", (s) obtain.readParcelable(s.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 22:
                    bundle3.putParcelable("result", (u) obtain.readParcelable(u.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 23:
                    bundle3.putParcelable("result", (e) obtain.readParcelable(e.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 28:
                    bundle3.putParcelable("result", (g) obtain.readParcelable(g.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 29:
                    bundle3.putParcelable("result", (com.unionpay.tsmservice.c.c) obtain.readParcelable(com.unionpay.tsmservice.c.c.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 31:
                    bundle3.putParcelable("result", (com.unionpay.tsmservice.c.i) obtain.readParcelable(com.unionpay.tsmservice.c.i.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 36:
                    bundle3.putParcelable("result", (w) obtain.readParcelable(w.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 38:
                    bundle3.putParcelable("result", (com.unionpay.tsmservice.c.a) obtain.readParcelable(com.unionpay.tsmservice.c.a.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                case 39:
                    bundle3.putParcelable("result", (q) obtain.readParcelable(q.class.getClassLoader()));
                    bundle = bundle3;
                    break;
                default:
                    break;
            }
            obtain.recycle();
            iVar = this.a;
            i.a((d) i.a(this.a, this.b).get(String.valueOf(this.c)), bundle);
            i.a(this.a, this.b).remove(String.valueOf(this.c));
            if (i.a(this.a, this.b).isEmpty()) {
                this.a.S[this.b] = 0;
            }
        }

        public final void a(String str, String str2) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_ERROR_CODE, str);
            bundle.putString("errorDesc", str2);
            i iVar = this.a;
            i.a((d) i.a(this.a, this.b).get(String.valueOf(this.c)), bundle);
            i.a(this.a, this.b).remove(String.valueOf(this.c));
            if (i.a(this.a, this.b).isEmpty()) {
                this.a.S[this.b] = 0;
            }
        }
    }

    static {
        try {
            System.loadLibrary("uptsmaddon");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    private i(Context context) {
        this.c = context;
        this.S = new int[40];
        if (!b(context)) {
            throw new RuntimeException();
        }
    }

    private static int a(int i, bu buVar, d dVar) throws RemoteException {
        return new h(a, i, buVar, dVar).a();
    }

    private static int a(int i, bu buVar, d dVar, e eVar) throws RemoteException {
        return new h(a, i, buVar, dVar, eVar).a();
    }

    private static int a(int i, bw bwVar, int i2, g gVar, Context context) throws RemoteException {
        return new h(a, i, bwVar, i2, gVar, context).a();
    }

    public static i a(Context context) {
        if (context == null) {
            return null;
        }
        if (a == null) {
            a = new i(context.getApplicationContext());
        }
        if (b == null) {
            b = new ArrayList();
        }
        return a;
    }

    private String a(Bundle bundle) {
        String str = "";
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        if (!(marshall == null || marshall.length == 0)) {
            str = b(Base64.encodeToString(marshall, 0));
        }
        obtain.recycle();
        return str;
    }

    static /* synthetic */ HashMap a(i iVar, int i) {
        switch (i) {
            case 0:
                return iVar.i;
            case 1:
                return iVar.j;
            case 2:
                return iVar.l;
            case 3:
                return iVar.k;
            case 4:
                return iVar.n;
            case 5:
                return iVar.m;
            case 6:
                return iVar.A;
            case 7:
                return iVar.u;
            case 8:
                return iVar.v;
            case 9:
                return iVar.o;
            case 10:
                return iVar.t;
            case 11:
                return iVar.r;
            case 12:
                return iVar.z;
            case 13:
                return iVar.y;
            case 14:
                return iVar.E;
            case 15:
                return iVar.p;
            case 16:
                return iVar.C;
            case 17:
                return iVar.D;
            case 18:
                return iVar.q;
            case 19:
                return iVar.s;
            case 20:
                return iVar.w;
            case 21:
                return iVar.F;
            case 22:
                return iVar.x;
            case 23:
                return iVar.B;
            case 24:
                return iVar.G;
            case 25:
                return iVar.H;
            case 28:
                return iVar.I;
            case 29:
                return iVar.J;
            case 30:
                return iVar.K;
            case 31:
                return iVar.L;
            case 35:
                return iVar.M;
            case 36:
                return iVar.N;
            case 37:
                return iVar.O;
            case 38:
                return iVar.P;
            case 39:
                return iVar.Q;
            default:
                return null;
        }
    }

    private static HashMap<String, String> a(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return new HashMap();
        }
        HashMap<String, String> hashMap2 = new HashMap();
        for (String str : hashMap.keySet()) {
            if (str != null) {
                String str2 = (String) hashMap.get(str);
                if (str2 != null) {
                    hashMap2.put(new String(str), new String(str2));
                }
            }
        }
        return hashMap2;
    }

    static /* synthetic */ void a(c cVar, String str, String str2, int i, Bundle bundle) {
        if (cVar != null) {
            try {
                cVar.a(str, str2, i, bundle);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void a(d dVar, Bundle bundle) {
        if (dVar != null) {
            try {
                String string = bundle.getString(Constants.KEY_ERROR_CODE);
                if ("10000".equals(string)) {
                    dVar.a(bundle);
                } else {
                    dVar.a(string, bundle.getString("errorDesc"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean a(String str) {
        try {
            return IUPJniInterface.cSKV(str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    private String b(String str) {
        try {
            return IUPJniInterface.eMG(str, this.g);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    static /* synthetic */ HashMap b(i iVar, int i) {
        switch (i) {
            case 1000:
                return iVar.R;
            default:
                return null;
        }
    }

    private static boolean b(Context context) {
        try {
            return IUPJniInterface.iJE(context);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return false;
        }
    }

    private String c(String str) {
        try {
            return IUPJniInterface.dMG(str, this.g);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean d(String str) {
        String e = e("com.unionpay.tsmservice");
        return e != null && e.compareTo(str) >= 0;
    }

    private String e(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.c.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionName : null;
    }

    private static String f(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("jarVersionCode", 24);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    private synchronized void g() {
        if (b != null && b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar != null) {
                    aVar.a();
                }
            }
        }
    }

    private synchronized void h() {
        if (b != null && b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar != null) {
                    aVar.b();
                }
            }
        }
    }

    private boolean i() {
        String e = e("com.unionpay.tsmservice");
        if (e == null || e.compareTo("01.00.11") < 0) {
            return false;
        }
        if (e.compareTo("01.00.18") >= 0) {
            this.g = 1;
            this.h = true;
            return true;
        } else if (e.compareTo("01.00.12") >= 0 && e.compareTo("01.00.16") <= 0) {
            this.g = 2;
            this.h = false;
            return true;
        } else if (e.compareTo("01.00.17") != 0 && e.compareTo("01.00.11") != 0) {
            return false;
        } else {
            this.g = 1;
            this.h = false;
            return true;
        }
    }

    public synchronized int a(int i) throws RemoteException {
        int i2;
        if (i < m_AppUI.MSG_APP_DATA_OK || i > f_FUN.FUN_ID_VOICE_SCH) {
            i2 = -3;
        } else if (!i()) {
            i2 = -8;
        } else if (this.e == null) {
            i2 = -1;
        } else if (a(this.c.getPackageName())) {
            try {
                i2 = this.e.a(i);
                if (-2 == i2) {
                    i2 = a(33, null, i, null, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = a(33, null, i, null, null);
        }
        return i2;
    }

    public int a(int i, String[] strArr) throws RemoteException {
        if (strArr == null || strArr.length == 0) {
            return -3;
        }
        if (!i()) {
            return -8;
        }
        if (this.e == null) {
            return -1;
        }
        try {
            return this.e.a(i, strArr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException();
        }
    }

    public synchronized int a(com.unionpay.tsmservice.b.a aVar, d dVar) throws RemoteException {
        int i = -8;
        synchronized (this) {
            if (dVar == null) {
                i = -3;
            } else if (d("01.00.20") && i()) {
                if (this.e == null) {
                    i = -1;
                } else if (a(this.c.getPackageName())) {
                    com.unionpay.tsmservice.b.a aVar2 = new com.unionpay.tsmservice.b.a();
                    String str = "";
                    if (aVar != null) {
                        str = aVar.e();
                    }
                    if (this.h) {
                        str = f(str);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        aVar2.d(b(str));
                    }
                    this.O.put(String.valueOf(this.S[37]), dVar);
                    try {
                        f fVar = this.e;
                        int[] iArr = this.S;
                        int i2 = iArr[37];
                        iArr[37] = i2 + 1;
                        i = fVar.a(aVar2, new c(37, i2));
                        if (i != 0) {
                            HashMap hashMap = this.O;
                            int[] iArr2 = this.S;
                            int i3 = iArr2[37] - 1;
                            iArr2[37] = i3;
                            hashMap.remove(String.valueOf(i3));
                        }
                        if (-2 == i) {
                            i = a(37, (bu) aVar, dVar);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RemoteException();
                    }
                } else {
                    i = a(37, (bu) aVar, dVar);
                }
            }
        }
        return i;
    }

    public synchronized int a(aa aaVar, d dVar) throws RemoteException {
        int i;
        synchronized (this) {
            if (aaVar == null || dVar == null) {
                i = -3;
            } else if (!i()) {
                i = -8;
            } else if (this.e == null) {
                i = -1;
            } else if (a(this.c.getPackageName())) {
                int size;
                aa aaVar2 = new aa();
                String e = aaVar.e();
                if (this.h) {
                    e = f(e);
                }
                if (!TextUtils.isEmpty(e)) {
                    aaVar2.d(b(e));
                }
                ArrayList arrayList = (ArrayList) aaVar.a();
                if (arrayList != null) {
                    size = arrayList.size();
                    if (size == 0) {
                        i = -3;
                    } else {
                        List arrayList2 = new ArrayList();
                        for (int i2 = 0; i2 < size; i2++) {
                            String str = (String) arrayList.get(i2);
                            if (!TextUtils.isEmpty(str)) {
                                arrayList2.add(b(str));
                            }
                        }
                        aaVar2.a(arrayList2);
                    }
                }
                this.B.put(String.valueOf(this.S[23]), dVar);
                try {
                    f fVar = this.e;
                    int[] iArr = this.S;
                    int i3 = iArr[23];
                    iArr[23] = i3 + 1;
                    i = fVar.a(aaVar2, new c(23, i3));
                    if (i != 0) {
                        HashMap hashMap = this.B;
                        int[] iArr2 = this.S;
                        size = iArr2[23] - 1;
                        iArr2[23] = size;
                        hashMap.remove(String.valueOf(size));
                    }
                    if (-2 == i) {
                        i = a(23, (bu) aaVar, dVar);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            } else {
                i = a(23, (bu) aaVar, dVar);
            }
        }
        return i;
    }

    public synchronized int a(ac acVar, d dVar, e eVar) throws RemoteException {
        int i;
        if (acVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            ac acVar2 = new ac();
            String e = acVar.e();
            String a = acVar.a();
            String b = acVar.b();
            if (this.h) {
                e = f(e);
            }
            if (!TextUtils.isEmpty(e)) {
                acVar2.d(b(e));
            }
            if (!TextUtils.isEmpty(a)) {
                acVar2.a(b(a));
            }
            if (!TextUtils.isEmpty(b)) {
                acVar2.b(b(b));
            }
            this.H.put(String.valueOf(this.S[25]), dVar);
            try {
                f fVar = this.e;
                int[] iArr = this.S;
                int i2 = iArr[25];
                iArr[25] = i2 + 1;
                i = fVar.a(acVar2, new c(25, i2), eVar);
                if (i != 0) {
                    HashMap hashMap = this.H;
                    int[] iArr2 = this.S;
                    int i3 = iArr2[25] - 1;
                    iArr2[25] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == i) {
                    i = a(25, (bu) acVar, dVar, eVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(25, (bu) acVar, dVar, eVar);
        }
        return i;
    }

    public synchronized int a(ae aeVar, d dVar) throws RemoteException {
        int i;
        if (aeVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            ae aeVar2 = new ae();
            String e = aeVar.e();
            a a = aeVar.a();
            if (!TextUtils.isEmpty(e)) {
                aeVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b))) {
                    aeVar2.a(new a(b(e), b(b)));
                }
            }
            e = aeVar.b();
            if (!TextUtils.isEmpty(e)) {
                aeVar2.a(e);
            }
            try {
                i = this.e.a(aeVar2, new c(8, this.S[8]));
                if (-2 == i) {
                    i = a(8, (bu) aeVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.v;
                    int[] iArr = this.S;
                    int i2 = iArr[8];
                    iArr[8] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(8, (bu) aeVar, dVar);
        }
        return i;
    }

    public synchronized int a(ag agVar, d dVar) throws RemoteException {
        int i;
        if (agVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            ag agVar2 = new ag();
            String e = agVar.e();
            a a = agVar.a();
            if (!TextUtils.isEmpty(e)) {
                agVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b))) {
                    agVar2.a(new a(b(e), b(b)));
                }
            }
            try {
                i = this.e.a(agVar2, new c(7, this.S[7]));
                if (-2 == i) {
                    i = a(7, (bu) agVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.u;
                    int[] iArr = this.S;
                    int i2 = iArr[7];
                    iArr[7] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(7, (bu) agVar, dVar);
        }
        return i;
    }

    public synchronized int a(ai aiVar, d dVar) throws RemoteException {
        int i;
        if (aiVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            ai aiVar2 = new ai();
            String e = aiVar.e();
            a a = aiVar.a();
            String b = aiVar.b();
            if (!TextUtils.isEmpty(e)) {
                aiVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b2 = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b2))) {
                    aiVar2.a(new a(b(e), b(b2)));
                }
            }
            if (!TextUtils.isEmpty(b)) {
                aiVar2.a(b(b));
            }
            try {
                i = this.e.a(aiVar2, new c(4, this.S[4]));
                if (-2 == i) {
                    i = a(4, (bu) aiVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.n;
                    int[] iArr = this.S;
                    int i2 = iArr[4];
                    iArr[4] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(4, (bu) aiVar, dVar);
        }
        return i;
    }

    public synchronized int a(ak akVar, d dVar) throws RemoteException {
        int i;
        synchronized (this) {
            if (akVar == null || dVar == null) {
                i = -3;
            } else if (!i()) {
                i = -8;
            } else if (this.e == null) {
                i = -1;
            } else if (a(this.c.getPackageName())) {
                ak akVar2 = new ak();
                String e = akVar.e();
                String a = akVar.a();
                String[] b = akVar.b();
                if (!TextUtils.isEmpty(e)) {
                    akVar2.d(b(e));
                }
                if (!TextUtils.isEmpty(a)) {
                    akVar2.a(b(a));
                }
                if (b != null) {
                    int length = b.length;
                    String[] strArr = new String[length];
                    for (i = 0; i < length; i++) {
                        if (!TextUtils.isEmpty(b[i])) {
                            strArr[i] = b(b[i]);
                        }
                    }
                    akVar2.a(strArr);
                }
                try {
                    i = this.e.a(akVar2, new c(2, this.S[2]));
                    if (-2 == i) {
                        i = a(2, (bu) akVar, dVar);
                    } else if (i == 0) {
                        HashMap hashMap = this.l;
                        int[] iArr = this.S;
                        int i2 = iArr[2];
                        iArr[2] = i2 + 1;
                        hashMap.put(String.valueOf(i2), dVar);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            } else {
                i = a(2, (bu) akVar, dVar);
            }
        }
        return i;
    }

    public synchronized int a(am amVar, d dVar) throws RemoteException {
        int i;
        if (amVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            am amVar2 = new am();
            String e = amVar.e();
            a a = amVar.a();
            if (!TextUtils.isEmpty(e)) {
                amVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b))) {
                    amVar2.a(new a(b(e), b(b)));
                }
            }
            try {
                i = this.e.a(amVar2, new c(5, this.S[5]));
                if (-2 == i) {
                    i = a(5, (bu) amVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.m;
                    int[] iArr = this.S;
                    int i2 = iArr[5];
                    iArr[5] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(5, (bu) amVar, dVar);
        }
        return i;
    }

    public synchronized int a(ao aoVar, d dVar) throws RemoteException {
        int i = -3;
        synchronized (this) {
            if (!(aoVar == null || dVar == null)) {
                String a = aoVar.a();
                if (!TextUtils.isEmpty(a)) {
                    if (!i()) {
                        i = -8;
                    } else if (this.e == null) {
                        i = -1;
                    } else if (a(this.c.getPackageName())) {
                        ao aoVar2 = new ao();
                        String e = aoVar.e();
                        if (this.h) {
                            e = f(e);
                        }
                        if (!TextUtils.isEmpty(e)) {
                            aoVar2.d(b(e));
                        }
                        aoVar2.a(b(a));
                        try {
                            i = this.e.a(aoVar2, new c(1, this.S[1]));
                            if (-2 == i) {
                                i = a(1, (bu) aoVar, dVar);
                            } else if (i == 0) {
                                HashMap hashMap = this.j;
                                int[] iArr = this.S;
                                int i2 = iArr[1];
                                iArr[1] = i2 + 1;
                                hashMap.put(String.valueOf(i2), dVar);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            throw new RemoteException();
                        }
                    } else {
                        i = a(1, (bu) aoVar, dVar);
                    }
                }
            }
        }
        return i;
    }

    public synchronized int a(aq aqVar, d dVar) throws RemoteException {
        int i;
        if (aqVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            aq aqVar2 = new aq();
            String e = aqVar.e();
            if (this.h) {
                e = f(e);
            }
            if (!TextUtils.isEmpty(e)) {
                aqVar2.d(b(e));
            }
            com.unionpay.tsmservice.a.a a = aqVar.a();
            if (a != null) {
                String a2 = a.a();
                e = a.b();
                com.unionpay.tsmservice.a.a aVar = new com.unionpay.tsmservice.a.a();
                if (!TextUtils.isEmpty(a2)) {
                    aVar.a(b(a2));
                }
                if (!TextUtils.isEmpty(e)) {
                    aVar.b(b(e));
                }
                aqVar2.a(aVar);
            }
            this.I.put(String.valueOf(this.S[28]), dVar);
            try {
                f fVar = this.e;
                int[] iArr = this.S;
                int i2 = iArr[28];
                iArr[28] = i2 + 1;
                i = fVar.a(aqVar2, new c(28, i2));
                if (i != 0) {
                    HashMap hashMap = this.I;
                    int[] iArr2 = this.S;
                    int i3 = iArr2[28] - 1;
                    iArr2[28] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == i) {
                    i = a(28, (bu) aqVar, dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(28, (bu) aqVar, dVar);
        }
        return i;
    }

    public synchronized int a(as asVar, d dVar) throws RemoteException {
        int i = -3;
        synchronized (this) {
            if (!(asVar == null || dVar == null)) {
                String[] a = asVar.a();
                int length = a.length;
                if (!(a == null || length == 0)) {
                    int i2 = 0;
                    while (i2 < length && a[i2] == null) {
                        i2++;
                    }
                    if (i2 != length) {
                        if (!i()) {
                            i = -8;
                        } else if (this.e == null) {
                            i = -1;
                        } else if (a(this.c.getPackageName())) {
                            String[] strArr = new String[length];
                            for (i = 0; i < length; i++) {
                                if (a[i] == null) {
                                    strArr[i] = a[i];
                                } else {
                                    strArr[i] = b(a[i]);
                                }
                            }
                            as asVar2 = new as();
                            asVar2.a(strArr);
                            String e = asVar.e();
                            if (this.h) {
                                e = f(e);
                            }
                            if (!TextUtils.isEmpty(e)) {
                                asVar2.d(b(e));
                            }
                            try {
                                i = this.e.a(asVar2, new c(6, this.S[6]));
                                if (-2 == i) {
                                    i = a(6, (bu) asVar, dVar);
                                } else if (i == 0) {
                                    HashMap hashMap = this.A;
                                    int[] iArr = this.S;
                                    length = iArr[6];
                                    iArr[6] = length + 1;
                                    hashMap.put(String.valueOf(length), dVar);
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                throw new RemoteException();
                            }
                        } else {
                            i = a(6, (bu) asVar, dVar);
                        }
                    }
                }
            }
        }
        return i;
    }

    public synchronized int a(aw awVar, d dVar) throws RemoteException {
        int i;
        if (dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            aw awVar2 = new aw();
            if (awVar != null) {
                String e = awVar.e();
                if (!TextUtils.isEmpty(e)) {
                    awVar2.d(b(e));
                }
            }
            try {
                i = this.e.a(awVar2, new c(13, this.S[13]));
                if (-2 == i) {
                    i = a(13, (bu) awVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.y;
                    int[] iArr = this.S;
                    int i2 = iArr[13];
                    iArr[13] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(13, (bu) awVar, dVar);
        }
        return i;
    }

    public synchronized int a(ay ayVar, d dVar) throws RemoteException {
        int i = -3;
        synchronized (this) {
            if (!(dVar == null || ayVar == null)) {
                int a = ayVar.a();
                String b = ayVar.b();
                if (a >= m_AppUI.MSG_APP_DATA_OK && a <= f_FUN.FUN_ID_VOICE_SCH && !(a == m_AppUI.MSG_APP_DATA_OK && TextUtils.isEmpty(b))) {
                    if (!i()) {
                        i = -8;
                    } else if (this.e == null) {
                        i = -1;
                    } else if (a(this.c.getPackageName())) {
                        ay ayVar2 = new ay();
                        if (a == m_AppUI.MSG_APP_DATA_OK) {
                            ayVar2.a(b(b));
                        }
                        ayVar2.a(a);
                        String e = ayVar.e();
                        if (this.h) {
                            e = f(e);
                        }
                        if (!TextUtils.isEmpty(e)) {
                            ayVar2.d(b(e));
                        }
                        this.L.put(String.valueOf(this.S[31]), dVar);
                        try {
                            f fVar = this.e;
                            int[] iArr = this.S;
                            int i2 = iArr[31];
                            iArr[31] = i2 + 1;
                            i = fVar.a(ayVar2, new c(31, i2));
                            if (i != 0) {
                                HashMap hashMap = this.L;
                                int[] iArr2 = this.S;
                                int i3 = iArr2[31] - 1;
                                iArr2[31] = i3;
                                hashMap.remove(String.valueOf(i3));
                            }
                            if (-2 == i) {
                                i = a(31, (bu) ayVar, dVar);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            throw new RemoteException();
                        }
                    } else {
                        i = a(31, (bu) ayVar, dVar);
                    }
                }
            }
        }
        return i;
    }

    public synchronized int a(ba baVar, d dVar) throws RemoteException {
        int i;
        if (baVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            String b;
            ba baVar2 = new ba();
            String e = baVar.e();
            a a = baVar.a();
            if (!TextUtils.isEmpty(e)) {
                baVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                b = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b))) {
                    baVar2.a(new a(b(e), b(b)));
                }
            }
            e = baVar.b();
            b = baVar.c();
            if (!TextUtils.isEmpty(e)) {
                baVar2.a(b(e));
            }
            if (!TextUtils.isEmpty(b)) {
                baVar2.b(b(b));
            }
            try {
                i = this.e.a(baVar2, new c(11, this.S[11]));
                if (-2 == i) {
                    i = a(11, (bu) baVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.r;
                    int[] iArr = this.S;
                    int i2 = iArr[11];
                    iArr[11] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(11, (bu) baVar, dVar);
        }
        return i;
    }

    public synchronized int a(bc bcVar, d dVar) throws RemoteException {
        int i;
        if (dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            bc bcVar2 = new bc();
            String str = "";
            if (bcVar != null) {
                str = bcVar.e();
            }
            if (this.h) {
                str = f(str);
            }
            if (!TextUtils.isEmpty(str)) {
                bcVar2.d(b(str));
            }
            this.k.put(String.valueOf(this.S[3]), dVar);
            try {
                f fVar = this.e;
                int[] iArr = this.S;
                int i2 = iArr[3];
                iArr[3] = i2 + 1;
                i = fVar.a(bcVar2, new c(3, i2));
                if (i != 0) {
                    HashMap hashMap = this.k;
                    int[] iArr2 = this.S;
                    int i3 = iArr2[3] - 1;
                    iArr2[3] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == i) {
                    i = a(3, (bu) bcVar, dVar);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(3, (bu) bcVar, dVar);
        }
        return i;
    }

    public synchronized int a(be beVar, d dVar) throws RemoteException {
        int i;
        if (dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            be beVar2 = new be();
            String str = "";
            if (beVar != null) {
                str = beVar.e();
            }
            if (this.h) {
                str = f(str);
            }
            if (!TextUtils.isEmpty(str)) {
                beVar2.d(b(str));
            }
            this.z.put(String.valueOf(this.S[12]), dVar);
            try {
                f fVar = this.e;
                int[] iArr = this.S;
                int i2 = iArr[12];
                iArr[12] = i2 + 1;
                i = fVar.a(beVar2, new c(12, i2));
                if (i != 0) {
                    HashMap hashMap = this.z;
                    int[] iArr2 = this.S;
                    int i3 = iArr2[12] - 1;
                    iArr2[12] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == i) {
                    i = a(12, (bu) beVar, dVar);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(12, (bu) beVar, dVar);
        }
        return i;
    }

    public synchronized int a(bg bgVar, d dVar) throws RemoteException {
        int i;
        if (bgVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            bg bgVar2 = new bg();
            String e = bgVar.e();
            a a = bgVar.a();
            String b = bgVar.b();
            if (!TextUtils.isEmpty(e)) {
                bgVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b2 = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b2))) {
                    bgVar2.a(new a(b(e), b(b2)));
                }
            }
            if (!TextUtils.isEmpty(b)) {
                bgVar2.a(b(b));
            }
            try {
                i = this.e.a(bgVar2, new c(9, this.S[9]));
                if (-2 == i) {
                    i = a(9, (bu) bgVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.o;
                    int[] iArr = this.S;
                    int i2 = iArr[9];
                    iArr[9] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(9, (bu) bgVar, dVar);
        }
        return i;
    }

    public synchronized int a(bi biVar, d dVar) throws RemoteException {
        int i;
        if (biVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            bi biVar2 = new bi();
            String e = biVar.e();
            a a = biVar.a();
            if (!TextUtils.isEmpty(e)) {
                biVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b))) {
                    biVar2.a(new a(b(e), b(b)));
                }
            }
            try {
                i = this.e.a(biVar2, new c(10, this.S[10]));
                if (-2 == i) {
                    i = a(10, (bu) biVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.t;
                    int[] iArr = this.S;
                    int i2 = iArr[10];
                    iArr[10] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(10, (bu) biVar, dVar);
        }
        return i;
    }

    public synchronized int a(bk bkVar, d dVar) throws RemoteException {
        int i = -8;
        synchronized (this) {
            if (dVar == null) {
                i = -3;
            } else if (d("01.00.20") && i()) {
                if (this.e == null) {
                    i = -1;
                } else if (a(this.c.getPackageName())) {
                    bk bkVar2 = new bk();
                    String str = "";
                    if (bkVar != null) {
                        str = bkVar.e();
                    }
                    if (this.h) {
                        str = f(str);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        bkVar2.d(b(str));
                    }
                    this.N.put(String.valueOf(this.S[36]), dVar);
                    try {
                        f fVar = this.e;
                        int[] iArr = this.S;
                        int i2 = iArr[36];
                        iArr[36] = i2 + 1;
                        i = fVar.a(bkVar2, new c(36, i2));
                        if (i != 0) {
                            HashMap hashMap = this.N;
                            int[] iArr2 = this.S;
                            int i3 = iArr2[36] - 1;
                            iArr2[36] = i3;
                            hashMap.remove(String.valueOf(i3));
                        }
                        if (-2 == i) {
                            i = a(36, (bu) bkVar, dVar);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RemoteException();
                    }
                } else {
                    i = a(36, (bu) bkVar, dVar);
                }
            }
        }
        return i;
    }

    public synchronized int a(bm bmVar, d dVar) throws RemoteException {
        int i = -3;
        synchronized (this) {
            if (!(bmVar == null || dVar == null)) {
                String a = bmVar.a();
                if (!TextUtils.isEmpty(a)) {
                    if (!i()) {
                        i = -8;
                    } else if (this.e == null) {
                        i = -1;
                    } else if (a(this.c.getPackageName())) {
                        String b = b(a);
                        bm bmVar2 = new bm();
                        bmVar2.a(b);
                        b = bmVar.e();
                        if (!TextUtils.isEmpty(b)) {
                            bmVar2.d(b(b));
                        }
                        try {
                            i = this.e.a(bmVar2, new c(24, this.S[24]));
                            if (-2 == i) {
                                i = a(24, (bu) bmVar, dVar);
                            } else if (i == 0) {
                                HashMap hashMap = this.G;
                                int[] iArr = this.S;
                                int i2 = iArr[24];
                                iArr[24] = i2 + 1;
                                hashMap.put(String.valueOf(i2), dVar);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RemoteException();
                        }
                    } else {
                        i = a(24, (bu) bmVar, dVar);
                    }
                }
            }
        }
        return i;
    }

    public synchronized int a(bo boVar, d dVar) throws RemoteException {
        int i;
        if (dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            bo boVar2 = new bo();
            String str = "";
            if (boVar != null) {
                str = boVar.e();
                String a = boVar.a();
                if (!TextUtils.isEmpty(a)) {
                    boVar2.a(b(a));
                }
            }
            if (this.h) {
                str = f(str);
            }
            if (!TextUtils.isEmpty(str)) {
                boVar2.d(b(str));
            }
            this.i.put(String.valueOf(this.S[0]), dVar);
            try {
                f fVar = this.e;
                int[] iArr = this.S;
                int i2 = iArr[0];
                iArr[0] = i2 + 1;
                i = fVar.a(boVar2, new c(0, i2));
                if (i != 0) {
                    HashMap hashMap = this.i;
                    int[] iArr2 = this.S;
                    int i3 = iArr2[0] - 1;
                    iArr2[0] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == i) {
                    i = a(0, (bu) boVar, dVar);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(0, (bu) boVar, dVar);
        }
        return i;
    }

    public synchronized int a(bq bqVar, d dVar) throws RemoteException {
        int i = -8;
        synchronized (this) {
            if (bqVar != null && dVar == null) {
                i = -3;
            } else if (d("01.00.21") && i()) {
                if (this.e == null) {
                    i = -1;
                } else if (a(this.c.getPackageName())) {
                    bq bqVar2 = new bq();
                    Bundle a = bqVar.a();
                    if (a != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("encryptData", a(a));
                        bqVar2.a(bundle);
                    }
                    String b = bqVar.b();
                    String c = bqVar.c();
                    if (!TextUtils.isEmpty(b)) {
                        bqVar2.a(b(b));
                    }
                    if (!TextUtils.isEmpty(c)) {
                        bqVar2.b(b(c));
                    }
                    b = bqVar.e();
                    if (this.h) {
                        b = f(b);
                    }
                    if (!TextUtils.isEmpty(b)) {
                        bqVar2.d(b(b));
                    }
                    this.Q.put(String.valueOf(this.S[39]), dVar);
                    try {
                        f fVar = this.e;
                        int[] iArr = this.S;
                        int i2 = iArr[39];
                        iArr[39] = i2 + 1;
                        i = fVar.a(bqVar2, new c(39, i2));
                        if (i != 0) {
                            HashMap hashMap = this.Q;
                            int[] iArr2 = this.S;
                            int i3 = iArr2[39] - 1;
                            iArr2[39] = i3;
                            hashMap.remove(String.valueOf(i3));
                        }
                        if (-2 == i) {
                            i = a(39, (bu) bqVar, dVar);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RemoteException();
                    }
                } else {
                    i = a(39, (bu) bqVar, dVar);
                }
            }
        }
        return i;
    }

    public synchronized int a(bs bsVar, d dVar) throws RemoteException {
        int i = -3;
        synchronized (this) {
            if (!(bsVar == null || dVar == null)) {
                String a = bsVar.a();
                if (!TextUtils.isEmpty(a)) {
                    if (!i()) {
                        i = -8;
                    } else if (this.e == null) {
                        i = -1;
                    } else if (a(this.c.getPackageName())) {
                        String b = b(a);
                        bs bsVar2 = new bs();
                        bsVar2.a(b);
                        b = bsVar.e();
                        if (this.h) {
                            b = f(b);
                        }
                        if (!TextUtils.isEmpty(b)) {
                            bsVar2.d(b(b));
                        }
                        this.w.put(String.valueOf(this.S[20]), dVar);
                        try {
                            f fVar = this.e;
                            int[] iArr = this.S;
                            int i2 = iArr[20];
                            iArr[20] = i2 + 1;
                            i = fVar.a(bsVar2, new c(20, i2));
                            if (i != 0) {
                                HashMap hashMap = this.w;
                                int[] iArr2 = this.S;
                                int i3 = iArr2[20] - 1;
                                iArr2[20] = i3;
                                hashMap.remove(String.valueOf(i3));
                            }
                            if (-2 == i) {
                                i = a(20, (bu) bsVar, dVar);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RemoteException();
                        }
                    } else {
                        i = a(20, (bu) bsVar, dVar);
                    }
                }
            }
        }
        return i;
    }

    public synchronized int a(bw bwVar) throws RemoteException {
        int i;
        if (bwVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            String e = bwVar.e();
            if (this.h) {
                e = f(e);
            }
            if (!TextUtils.isEmpty(e)) {
                bwVar.d(b(e));
            }
            try {
                i = this.e.a(bwVar);
                if (-2 == i) {
                    i = a(32, (bu) bwVar, null);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(32, (bu) bwVar, null);
        }
        return i;
    }

    public synchronized int a(bw bwVar, int i, g gVar, Context context) throws RemoteException {
        int i2;
        if (bwVar == null || i < m_AppUI.MSG_APP_DATA_OK || i > f_FUN.FUN_ID_VOICE_SCH) {
            i2 = -3;
        } else if (!i()) {
            i2 = -8;
        } else if (this.e == null) {
            i2 = -1;
        } else if (a(this.c.getPackageName())) {
            this.R.put(this.c.getPackageName(), new l(context));
            String e = bwVar.e();
            if (this.h) {
                e = f(e);
            }
            if (!TextUtils.isEmpty(e)) {
                bwVar.d(b(e));
            }
            try {
                i2 = this.e.a(bwVar, i, gVar, new b(this));
                if (i2 != 0) {
                    this.R.remove(this.c.getPackageName());
                }
                if (-2 == i2) {
                    i2 = a(1000, bwVar, i, gVar, context);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = a(1000, bwVar, i, gVar, context);
        }
        return i2;
    }

    public synchronized int a(by byVar, d dVar) throws RemoteException {
        int i;
        if (byVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            by byVar2 = new by();
            String e = byVar.e();
            String a = byVar.a();
            String b = byVar.b();
            if (this.h) {
                e = f(e);
            }
            if (!TextUtils.isEmpty(e)) {
                byVar2.d(b(e));
            }
            if (!TextUtils.isEmpty(a)) {
                byVar2.a(b(a));
            }
            if (!TextUtils.isEmpty(b)) {
                byVar2.b(b(b));
            }
            this.x.put(String.valueOf(this.S[22]), dVar);
            try {
                f fVar = this.e;
                int[] iArr = this.S;
                int i2 = iArr[22];
                iArr[22] = i2 + 1;
                i = fVar.a(byVar2, new c(22, i2));
                if (i != 0) {
                    HashMap hashMap = this.x;
                    int[] iArr2 = this.S;
                    int i3 = iArr2[22] - 1;
                    iArr2[22] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == i) {
                    i = a(22, (bu) byVar, dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(22, (bu) byVar, dVar);
        }
        return i;
    }

    public synchronized int a(com.unionpay.tsmservice.b.c cVar, d dVar, e eVar) throws RemoteException {
        int i = -8;
        synchronized (this) {
            if (cVar != null && dVar == null) {
                i = -3;
            } else if (d("01.00.20") && i()) {
                if (this.e == null) {
                    i = -1;
                } else if (a(this.c.getPackageName())) {
                    com.unionpay.tsmservice.b.c cVar2 = new com.unionpay.tsmservice.b.c();
                    Bundle a = cVar.a();
                    if (a != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("encryptData", a(a));
                        cVar2.a(bundle);
                    }
                    String e = cVar.e();
                    if (this.h) {
                        e = f(e);
                    }
                    if (!TextUtils.isEmpty(e)) {
                        cVar2.d(b(e));
                    }
                    this.P.put(String.valueOf(this.S[38]), dVar);
                    try {
                        f fVar = this.e;
                        int[] iArr = this.S;
                        int i2 = iArr[38];
                        iArr[38] = i2 + 1;
                        i = fVar.a(cVar2, new c(38, i2), eVar);
                        if (i != 0) {
                            HashMap hashMap = this.P;
                            int[] iArr2 = this.S;
                            int i3 = iArr2[38] - 1;
                            iArr2[38] = i3;
                            hashMap.remove(String.valueOf(i3));
                        }
                        if (-2 == i) {
                            i = a(38, (bu) cVar, dVar, eVar);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        throw new RemoteException();
                    }
                } else {
                    i = a(38, (bu) cVar, dVar);
                }
            }
        }
        return i;
    }

    public synchronized int a(ca caVar, d dVar) throws RemoteException {
        int i = -3;
        synchronized (this) {
            if (!(caVar == null || dVar == null)) {
                String a = caVar.a();
                if (!TextUtils.isEmpty(a)) {
                    if (!i()) {
                        i = -8;
                    } else if (this.e == null) {
                        i = -1;
                    } else if (a(this.c.getPackageName())) {
                        String b = b(a);
                        ca caVar2 = new ca();
                        caVar2.a(b);
                        b = caVar.e();
                        if (!TextUtils.isEmpty(b)) {
                            caVar2.d(b(b));
                        }
                        try {
                            i = this.e.a(caVar2, new c(14, this.S[14]));
                            if (-2 == i) {
                                i = a(14, (bu) caVar, dVar);
                            } else if (i == 0) {
                                HashMap hashMap = this.E;
                                int[] iArr = this.S;
                                int i2 = iArr[14];
                                iArr[14] = i2 + 1;
                                hashMap.put(String.valueOf(i2), dVar);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RemoteException();
                        }
                    } else {
                        i = a(14, (bu) caVar, dVar);
                    }
                }
            }
        }
        return i;
    }

    public synchronized int a(cc ccVar, d dVar) throws RemoteException {
        int i;
        if (dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            cc ccVar2 = new cc();
            if (ccVar != null) {
                String e = ccVar.e();
                if (!TextUtils.isEmpty(e)) {
                    ccVar2.d(b(e));
                }
            }
            this.K.put(String.valueOf(this.S[30]), dVar);
            try {
                f fVar = this.e;
                int[] iArr = this.S;
                int i2 = iArr[30];
                iArr[30] = i2 + 1;
                i = fVar.a(ccVar2, new c(30, i2));
                if (i != 0) {
                    HashMap hashMap = this.K;
                    int[] iArr2 = this.S;
                    int i3 = iArr2[30] - 1;
                    iArr2[30] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == i) {
                    i = a(30, (bu) ccVar, dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(30, (bu) ccVar, dVar);
        }
        return i;
    }

    public synchronized int a(com.unionpay.tsmservice.b.e eVar, d dVar, e eVar2) throws RemoteException {
        int i;
        if (eVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            com.unionpay.tsmservice.b.e eVar3 = new com.unionpay.tsmservice.b.e();
            String e = eVar.e();
            a a = eVar.a();
            if (!TextUtils.isEmpty(e)) {
                eVar3.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b))) {
                    eVar3.a(new a(b(e), b(b)));
                }
            }
            try {
                i = this.e.a(eVar3, new c(18, this.S[18]), eVar2);
                if (-2 == i) {
                    i = a(18, (bu) eVar, dVar, eVar2);
                } else if (i == 0) {
                    HashMap hashMap = this.q;
                    int[] iArr = this.S;
                    int i2 = iArr[18];
                    iArr[18] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(18, (bu) eVar, dVar, eVar2);
        }
        return i;
    }

    public synchronized int a(com.unionpay.tsmservice.b.g r18, com.unionpay.tsmservice.d r19, com.unionpay.tsmservice.e r20) throws android.os.RemoteException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.unionpay.tsmservice.i.a(com.unionpay.tsmservice.b.g, com.unionpay.tsmservice.d, com.unionpay.tsmservice.e):int. bs: [B:25:0x0072, B:75:0x01df]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r17 = this;
        monitor-enter(r17);
        if (r18 == 0) goto L_0x0005;
    L_0x0003:
        if (r19 != 0) goto L_0x0008;
    L_0x0005:
        r3 = -3;
    L_0x0006:
        monitor-exit(r17);
        return r3;
    L_0x0008:
        r3 = r17.i();	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x0010;	 Catch:{ all -> 0x00a9 }
    L_0x000e:
        r3 = -8;	 Catch:{ all -> 0x00a9 }
        goto L_0x0006;	 Catch:{ all -> 0x00a9 }
    L_0x0010:
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r3 = r0.e;	 Catch:{ all -> 0x00a9 }
        if (r3 == 0) goto L_0x0241;	 Catch:{ all -> 0x00a9 }
    L_0x0016:
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r3 = r0.c;	 Catch:{ all -> 0x00a9 }
        r3 = r3.getPackageName();	 Catch:{ all -> 0x00a9 }
        r3 = a(r3);	 Catch:{ all -> 0x00a9 }
        if (r3 == 0) goto L_0x0233;	 Catch:{ all -> 0x00a9 }
    L_0x0024:
        r14 = new com.unionpay.tsmservice.b.g;	 Catch:{ all -> 0x00a9 }
        r14.<init>();	 Catch:{ all -> 0x00a9 }
        r3 = r18.e();	 Catch:{ all -> 0x00a9 }
        r4 = r18.a();	 Catch:{ all -> 0x00a9 }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x00a9 }
        if (r5 != 0) goto L_0x0040;	 Catch:{ all -> 0x00a9 }
    L_0x0037:
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r3 = r0.b(r3);	 Catch:{ all -> 0x00a9 }
        r14.d(r3);	 Catch:{ all -> 0x00a9 }
    L_0x0040:
        if (r4 == 0) goto L_0x006a;	 Catch:{ all -> 0x00a9 }
    L_0x0042:
        r3 = r4.a();	 Catch:{ all -> 0x00a9 }
        r4 = r4.b();	 Catch:{ all -> 0x00a9 }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x00a9 }
        if (r5 != 0) goto L_0x006a;	 Catch:{ all -> 0x00a9 }
    L_0x0050:
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x00a9 }
        if (r5 != 0) goto L_0x006a;	 Catch:{ all -> 0x00a9 }
    L_0x0056:
        r5 = new com.unionpay.tsmservice.a;	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r3 = r0.b(r3);	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r4);	 Catch:{ all -> 0x00a9 }
        r5.<init>(r3, r4);	 Catch:{ all -> 0x00a9 }
        r14.a(r5);	 Catch:{ all -> 0x00a9 }
    L_0x006a:
        r3 = r18.b();	 Catch:{ all -> 0x00a9 }
        r3 = (java.util.HashMap) r3;	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x00c9;
    L_0x0072:
        r0 = r17;	 Catch:{ Exception -> 0x009f }
        r3 = r0.e;	 Catch:{ Exception -> 0x009f }
        r4 = new com.unionpay.tsmservice.i$c;	 Catch:{ Exception -> 0x009f }
        r5 = 17;	 Catch:{ Exception -> 0x009f }
        r0 = r17;	 Catch:{ Exception -> 0x009f }
        r6 = r0.S;	 Catch:{ Exception -> 0x009f }
        r7 = 17;	 Catch:{ Exception -> 0x009f }
        r6 = r6[r7];	 Catch:{ Exception -> 0x009f }
        r7 = 0;	 Catch:{ Exception -> 0x009f }
        r0 = r17;	 Catch:{ Exception -> 0x009f }
        r4.<init>(r5, r6);	 Catch:{ Exception -> 0x009f }
        r0 = r20;	 Catch:{ Exception -> 0x009f }
        r3 = r3.a(r14, r4, r0);	 Catch:{ Exception -> 0x009f }
        r4 = -2;
        if (r4 != r3) goto L_0x00ac;
    L_0x0091:
        r3 = 17;
        r0 = r18;	 Catch:{ all -> 0x00a9 }
        r1 = r19;	 Catch:{ all -> 0x00a9 }
        r2 = r20;	 Catch:{ all -> 0x00a9 }
        r3 = a(r3, r0, r1, r2);	 Catch:{ all -> 0x00a9 }
        goto L_0x0006;	 Catch:{ all -> 0x00a9 }
    L_0x009f:
        r3 = move-exception;	 Catch:{ all -> 0x00a9 }
        r3.printStackTrace();	 Catch:{ all -> 0x00a9 }
        r3 = new android.os.RemoteException;	 Catch:{ all -> 0x00a9 }
        r3.<init>();	 Catch:{ all -> 0x00a9 }
        throw r3;	 Catch:{ all -> 0x00a9 }
    L_0x00a9:
        r3 = move-exception;
        monitor-exit(r17);
        throw r3;
    L_0x00ac:
        if (r3 != 0) goto L_0x0006;
    L_0x00ae:
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.D;	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r5 = r0.S;	 Catch:{ all -> 0x00a9 }
        r6 = 17;	 Catch:{ all -> 0x00a9 }
        r7 = r5[r6];	 Catch:{ all -> 0x00a9 }
        r8 = r7 + 1;	 Catch:{ all -> 0x00a9 }
        r5[r6] = r8;	 Catch:{ all -> 0x00a9 }
        r5 = java.lang.String.valueOf(r7);	 Catch:{ all -> 0x00a9 }
        r0 = r19;	 Catch:{ all -> 0x00a9 }
        r4.put(r5, r0);	 Catch:{ all -> 0x00a9 }
        goto L_0x0006;	 Catch:{ all -> 0x00a9 }
    L_0x00c9:
        r15 = a(r3);	 Catch:{ all -> 0x00a9 }
        r3 = "cardHolderName";	 Catch:{ all -> 0x00a9 }
        r3 = r15.get(r3);	 Catch:{ all -> 0x00a9 }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x00a9 }
        r4 = "idType";	 Catch:{ all -> 0x00a9 }
        r4 = r15.get(r4);	 Catch:{ all -> 0x00a9 }
        r4 = (java.lang.String) r4;	 Catch:{ all -> 0x00a9 }
        r5 = "idNo";	 Catch:{ all -> 0x00a9 }
        r5 = r15.get(r5);	 Catch:{ all -> 0x00a9 }
        r5 = (java.lang.String) r5;	 Catch:{ all -> 0x00a9 }
        r6 = "pan";	 Catch:{ all -> 0x00a9 }
        r6 = r15.get(r6);	 Catch:{ all -> 0x00a9 }
        r6 = (java.lang.String) r6;	 Catch:{ all -> 0x00a9 }
        r7 = "pin";	 Catch:{ all -> 0x00a9 }
        r7 = r15.get(r7);	 Catch:{ all -> 0x00a9 }
        r7 = (java.lang.String) r7;	 Catch:{ all -> 0x00a9 }
        r8 = "expiryDate";	 Catch:{ all -> 0x00a9 }
        r8 = r15.get(r8);	 Catch:{ all -> 0x00a9 }
        r8 = (java.lang.String) r8;	 Catch:{ all -> 0x00a9 }
        r9 = "cvn2";	 Catch:{ all -> 0x00a9 }
        r9 = r15.get(r9);	 Catch:{ all -> 0x00a9 }
        r9 = (java.lang.String) r9;	 Catch:{ all -> 0x00a9 }
        r10 = "msisdn";	 Catch:{ all -> 0x00a9 }
        r10 = r15.get(r10);	 Catch:{ all -> 0x00a9 }
        r10 = (java.lang.String) r10;	 Catch:{ all -> 0x00a9 }
        r11 = "smsAuthCode";	 Catch:{ all -> 0x00a9 }
        r11 = r15.get(r11);	 Catch:{ all -> 0x00a9 }
        r11 = (java.lang.String) r11;	 Catch:{ all -> 0x00a9 }
        r12 = "ecashBalance";	 Catch:{ all -> 0x00a9 }
        r12 = r15.get(r12);	 Catch:{ all -> 0x00a9 }
        r12 = (java.lang.String) r12;	 Catch:{ all -> 0x00a9 }
        r13 = "cardType";	 Catch:{ all -> 0x00a9 }
        r13 = r15.get(r13);	 Catch:{ all -> 0x00a9 }
        r13 = (java.lang.String) r13;	 Catch:{ all -> 0x00a9 }
        r16 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x00a9 }
        if (r16 != 0) goto L_0x0138;	 Catch:{ all -> 0x00a9 }
    L_0x012b:
        r16 = "cardHolderName";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r3 = r0.b(r3);	 Catch:{ all -> 0x00a9 }
        r0 = r16;	 Catch:{ all -> 0x00a9 }
        r15.put(r0, r3);	 Catch:{ all -> 0x00a9 }
    L_0x0138:
        r3 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x0149;	 Catch:{ all -> 0x00a9 }
    L_0x013e:
        r3 = "idType";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r4);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x0149:
        r3 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x015a;	 Catch:{ all -> 0x00a9 }
    L_0x014f:
        r3 = "idNo";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r5);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x015a:
        r3 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x016b;	 Catch:{ all -> 0x00a9 }
    L_0x0160:
        r3 = "pan";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r6);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x016b:
        r3 = android.text.TextUtils.isEmpty(r7);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x0176;	 Catch:{ all -> 0x00a9 }
    L_0x0171:
        r3 = "pin";	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r7);	 Catch:{ all -> 0x00a9 }
    L_0x0176:
        r3 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x0187;	 Catch:{ all -> 0x00a9 }
    L_0x017c:
        r3 = "expiryDate";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r8);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x0187:
        r3 = android.text.TextUtils.isEmpty(r9);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x0198;	 Catch:{ all -> 0x00a9 }
    L_0x018d:
        r3 = "cvn2";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r9);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x0198:
        r3 = android.text.TextUtils.isEmpty(r10);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x01a9;	 Catch:{ all -> 0x00a9 }
    L_0x019e:
        r3 = "msisdn";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r10);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x01a9:
        r3 = android.text.TextUtils.isEmpty(r11);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x01ba;	 Catch:{ all -> 0x00a9 }
    L_0x01af:
        r3 = "smsAuthCode";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r11);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x01ba:
        r3 = android.text.TextUtils.isEmpty(r12);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x01cb;	 Catch:{ all -> 0x00a9 }
    L_0x01c0:
        r3 = "ecashBalance";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r12);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x01cb:
        r3 = android.text.TextUtils.isEmpty(r13);	 Catch:{ all -> 0x00a9 }
        if (r3 != 0) goto L_0x01dc;	 Catch:{ all -> 0x00a9 }
    L_0x01d1:
        r3 = "cardType";	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.b(r13);	 Catch:{ all -> 0x00a9 }
        r15.put(r3, r4);	 Catch:{ all -> 0x00a9 }
    L_0x01dc:
        r14.a(r15);	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ Exception -> 0x020c }
        r3 = r0.e;	 Catch:{ Exception -> 0x020c }
        r4 = new com.unionpay.tsmservice.i$c;	 Catch:{ Exception -> 0x020c }
        r5 = 17;	 Catch:{ Exception -> 0x020c }
        r0 = r17;	 Catch:{ Exception -> 0x020c }
        r6 = r0.S;	 Catch:{ Exception -> 0x020c }
        r7 = 17;	 Catch:{ Exception -> 0x020c }
        r6 = r6[r7];	 Catch:{ Exception -> 0x020c }
        r7 = 0;	 Catch:{ Exception -> 0x020c }
        r0 = r17;	 Catch:{ Exception -> 0x020c }
        r4.<init>(r5, r6);	 Catch:{ Exception -> 0x020c }
        r0 = r20;	 Catch:{ Exception -> 0x020c }
        r3 = r3.a(r14, r4, r0);	 Catch:{ Exception -> 0x020c }
        r4 = -2;
        if (r4 != r3) goto L_0x0216;
    L_0x01fe:
        r3 = 17;
        r0 = r18;	 Catch:{ all -> 0x00a9 }
        r1 = r19;	 Catch:{ all -> 0x00a9 }
        r2 = r20;	 Catch:{ all -> 0x00a9 }
        r3 = a(r3, r0, r1, r2);	 Catch:{ all -> 0x00a9 }
        goto L_0x0006;	 Catch:{ all -> 0x00a9 }
    L_0x020c:
        r3 = move-exception;	 Catch:{ all -> 0x00a9 }
        r3.printStackTrace();	 Catch:{ all -> 0x00a9 }
        r3 = new android.os.RemoteException;	 Catch:{ all -> 0x00a9 }
        r3.<init>();	 Catch:{ all -> 0x00a9 }
        throw r3;	 Catch:{ all -> 0x00a9 }
    L_0x0216:
        if (r3 != 0) goto L_0x0006;	 Catch:{ all -> 0x00a9 }
    L_0x0218:
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r4 = r0.D;	 Catch:{ all -> 0x00a9 }
        r0 = r17;	 Catch:{ all -> 0x00a9 }
        r5 = r0.S;	 Catch:{ all -> 0x00a9 }
        r6 = 17;	 Catch:{ all -> 0x00a9 }
        r7 = r5[r6];	 Catch:{ all -> 0x00a9 }
        r8 = r7 + 1;	 Catch:{ all -> 0x00a9 }
        r5[r6] = r8;	 Catch:{ all -> 0x00a9 }
        r5 = java.lang.String.valueOf(r7);	 Catch:{ all -> 0x00a9 }
        r0 = r19;	 Catch:{ all -> 0x00a9 }
        r4.put(r5, r0);	 Catch:{ all -> 0x00a9 }
        goto L_0x0006;	 Catch:{ all -> 0x00a9 }
    L_0x0233:
        r3 = 17;	 Catch:{ all -> 0x00a9 }
        r0 = r18;	 Catch:{ all -> 0x00a9 }
        r1 = r19;	 Catch:{ all -> 0x00a9 }
        r2 = r20;	 Catch:{ all -> 0x00a9 }
        r3 = a(r3, r0, r1, r2);	 Catch:{ all -> 0x00a9 }
        goto L_0x0006;
    L_0x0241:
        r3 = -1;
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.i.a(com.unionpay.tsmservice.b.g, com.unionpay.tsmservice.d, com.unionpay.tsmservice.e):int");
    }

    public synchronized int a(com.unionpay.tsmservice.b.i r18, com.unionpay.tsmservice.d r19) throws android.os.RemoteException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.unionpay.tsmservice.i.a(com.unionpay.tsmservice.b.i, com.unionpay.tsmservice.d):int. bs: [B:25:0x0072, B:78:0x01f4]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r17 = this;
        monitor-enter(r17);
        if (r18 == 0) goto L_0x0005;
    L_0x0003:
        if (r19 != 0) goto L_0x0008;
    L_0x0005:
        r2 = -3;
    L_0x0006:
        monitor-exit(r17);
        return r2;
    L_0x0008:
        r2 = r17.i();	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x0010;	 Catch:{ all -> 0x00a5 }
    L_0x000e:
        r2 = -8;	 Catch:{ all -> 0x00a5 }
        goto L_0x0006;	 Catch:{ all -> 0x00a5 }
    L_0x0010:
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r2 = r0.e;	 Catch:{ all -> 0x00a5 }
        if (r2 == 0) goto L_0x0250;	 Catch:{ all -> 0x00a5 }
    L_0x0016:
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r2 = r0.c;	 Catch:{ all -> 0x00a5 }
        r2 = r2.getPackageName();	 Catch:{ all -> 0x00a5 }
        r2 = a(r2);	 Catch:{ all -> 0x00a5 }
        if (r2 == 0) goto L_0x0244;	 Catch:{ all -> 0x00a5 }
    L_0x0024:
        r14 = new com.unionpay.tsmservice.b.i;	 Catch:{ all -> 0x00a5 }
        r14.<init>();	 Catch:{ all -> 0x00a5 }
        r2 = r18.e();	 Catch:{ all -> 0x00a5 }
        r3 = r18.a();	 Catch:{ all -> 0x00a5 }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x00a5 }
        if (r4 != 0) goto L_0x0040;	 Catch:{ all -> 0x00a5 }
    L_0x0037:
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r2 = r0.b(r2);	 Catch:{ all -> 0x00a5 }
        r14.d(r2);	 Catch:{ all -> 0x00a5 }
    L_0x0040:
        if (r3 == 0) goto L_0x006a;	 Catch:{ all -> 0x00a5 }
    L_0x0042:
        r2 = r3.a();	 Catch:{ all -> 0x00a5 }
        r3 = r3.b();	 Catch:{ all -> 0x00a5 }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x00a5 }
        if (r4 != 0) goto L_0x006a;	 Catch:{ all -> 0x00a5 }
    L_0x0050:
        r4 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x00a5 }
        if (r4 != 0) goto L_0x006a;	 Catch:{ all -> 0x00a5 }
    L_0x0056:
        r4 = new com.unionpay.tsmservice.a;	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r2 = r0.b(r2);	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r3);	 Catch:{ all -> 0x00a5 }
        r4.<init>(r2, r3);	 Catch:{ all -> 0x00a5 }
        r14.a(r4);	 Catch:{ all -> 0x00a5 }
    L_0x006a:
        r2 = r18.b();	 Catch:{ all -> 0x00a5 }
        r2 = (java.util.HashMap) r2;	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x00c5;
    L_0x0072:
        r0 = r17;	 Catch:{ Exception -> 0x009b }
        r2 = r0.e;	 Catch:{ Exception -> 0x009b }
        r3 = new com.unionpay.tsmservice.i$c;	 Catch:{ Exception -> 0x009b }
        r4 = 15;	 Catch:{ Exception -> 0x009b }
        r0 = r17;	 Catch:{ Exception -> 0x009b }
        r5 = r0.S;	 Catch:{ Exception -> 0x009b }
        r6 = 15;	 Catch:{ Exception -> 0x009b }
        r5 = r5[r6];	 Catch:{ Exception -> 0x009b }
        r6 = 0;	 Catch:{ Exception -> 0x009b }
        r0 = r17;	 Catch:{ Exception -> 0x009b }
        r3.<init>(r4, r5);	 Catch:{ Exception -> 0x009b }
        r2 = r2.a(r14, r3);	 Catch:{ Exception -> 0x009b }
        r3 = -2;
        if (r3 != r2) goto L_0x00a8;
    L_0x008f:
        r2 = 15;
        r0 = r18;	 Catch:{ all -> 0x00a5 }
        r1 = r19;	 Catch:{ all -> 0x00a5 }
        r2 = a(r2, r0, r1);	 Catch:{ all -> 0x00a5 }
        goto L_0x0006;	 Catch:{ all -> 0x00a5 }
    L_0x009b:
        r2 = move-exception;	 Catch:{ all -> 0x00a5 }
        r2.printStackTrace();	 Catch:{ all -> 0x00a5 }
        r2 = new android.os.RemoteException;	 Catch:{ all -> 0x00a5 }
        r2.<init>();	 Catch:{ all -> 0x00a5 }
        throw r2;	 Catch:{ all -> 0x00a5 }
    L_0x00a5:
        r2 = move-exception;
        monitor-exit(r17);
        throw r2;
    L_0x00a8:
        if (r2 != 0) goto L_0x0006;
    L_0x00aa:
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.p;	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r4 = r0.S;	 Catch:{ all -> 0x00a5 }
        r5 = 15;	 Catch:{ all -> 0x00a5 }
        r6 = r4[r5];	 Catch:{ all -> 0x00a5 }
        r7 = r6 + 1;	 Catch:{ all -> 0x00a5 }
        r4[r5] = r7;	 Catch:{ all -> 0x00a5 }
        r4 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x00a5 }
        r0 = r19;	 Catch:{ all -> 0x00a5 }
        r3.put(r4, r0);	 Catch:{ all -> 0x00a5 }
        goto L_0x0006;	 Catch:{ all -> 0x00a5 }
    L_0x00c5:
        r15 = a(r2);	 Catch:{ all -> 0x00a5 }
        r2 = "accountLimit";	 Catch:{ all -> 0x00a5 }
        r2 = r15.get(r2);	 Catch:{ all -> 0x00a5 }
        r2 = (java.lang.String) r2;	 Catch:{ all -> 0x00a5 }
        r3 = "accountType";	 Catch:{ all -> 0x00a5 }
        r3 = r15.get(r3);	 Catch:{ all -> 0x00a5 }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x00a5 }
        r4 = "cardHolderName";	 Catch:{ all -> 0x00a5 }
        r4 = r15.get(r4);	 Catch:{ all -> 0x00a5 }
        r4 = (java.lang.String) r4;	 Catch:{ all -> 0x00a5 }
        r5 = "idType";	 Catch:{ all -> 0x00a5 }
        r5 = r15.get(r5);	 Catch:{ all -> 0x00a5 }
        r5 = (java.lang.String) r5;	 Catch:{ all -> 0x00a5 }
        r6 = "idNo";	 Catch:{ all -> 0x00a5 }
        r6 = r15.get(r6);	 Catch:{ all -> 0x00a5 }
        r6 = (java.lang.String) r6;	 Catch:{ all -> 0x00a5 }
        r7 = "pan";	 Catch:{ all -> 0x00a5 }
        r7 = r15.get(r7);	 Catch:{ all -> 0x00a5 }
        r7 = (java.lang.String) r7;	 Catch:{ all -> 0x00a5 }
        r8 = "pin";	 Catch:{ all -> 0x00a5 }
        r8 = r15.get(r8);	 Catch:{ all -> 0x00a5 }
        r8 = (java.lang.String) r8;	 Catch:{ all -> 0x00a5 }
        r9 = "expiryDate";	 Catch:{ all -> 0x00a5 }
        r9 = r15.get(r9);	 Catch:{ all -> 0x00a5 }
        r9 = (java.lang.String) r9;	 Catch:{ all -> 0x00a5 }
        r10 = "cvn2";	 Catch:{ all -> 0x00a5 }
        r10 = r15.get(r10);	 Catch:{ all -> 0x00a5 }
        r10 = (java.lang.String) r10;	 Catch:{ all -> 0x00a5 }
        r11 = "msisdn";	 Catch:{ all -> 0x00a5 }
        r11 = r15.get(r11);	 Catch:{ all -> 0x00a5 }
        r11 = (java.lang.String) r11;	 Catch:{ all -> 0x00a5 }
        r12 = "smsAuthCode";	 Catch:{ all -> 0x00a5 }
        r12 = r15.get(r12);	 Catch:{ all -> 0x00a5 }
        r12 = (java.lang.String) r12;	 Catch:{ all -> 0x00a5 }
        r13 = "cardType";	 Catch:{ all -> 0x00a5 }
        r13 = r15.get(r13);	 Catch:{ all -> 0x00a5 }
        r13 = (java.lang.String) r13;	 Catch:{ all -> 0x00a5 }
        r16 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x00a5 }
        if (r16 != 0) goto L_0x013c;	 Catch:{ all -> 0x00a5 }
    L_0x012f:
        r16 = "accountLimit";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r2 = r0.b(r2);	 Catch:{ all -> 0x00a5 }
        r0 = r16;	 Catch:{ all -> 0x00a5 }
        r15.put(r0, r2);	 Catch:{ all -> 0x00a5 }
    L_0x013c:
        r2 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x014d;	 Catch:{ all -> 0x00a5 }
    L_0x0142:
        r2 = "accountType";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r3);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x014d:
        r2 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x015e;	 Catch:{ all -> 0x00a5 }
    L_0x0153:
        r2 = "cardHolderName";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r4);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x015e:
        r2 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x016f;	 Catch:{ all -> 0x00a5 }
    L_0x0164:
        r2 = "idType";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r5);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x016f:
        r2 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x0180;	 Catch:{ all -> 0x00a5 }
    L_0x0175:
        r2 = "idNo";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r6);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x0180:
        r2 = android.text.TextUtils.isEmpty(r7);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x0191;	 Catch:{ all -> 0x00a5 }
    L_0x0186:
        r2 = "pan";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r7);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x0191:
        r2 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x019c;	 Catch:{ all -> 0x00a5 }
    L_0x0197:
        r2 = "pin";	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r8);	 Catch:{ all -> 0x00a5 }
    L_0x019c:
        r2 = android.text.TextUtils.isEmpty(r9);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x01ad;	 Catch:{ all -> 0x00a5 }
    L_0x01a2:
        r2 = "expiryDate";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r9);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x01ad:
        r2 = android.text.TextUtils.isEmpty(r10);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x01be;	 Catch:{ all -> 0x00a5 }
    L_0x01b3:
        r2 = "cvn2";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r10);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x01be:
        r2 = android.text.TextUtils.isEmpty(r11);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x01cf;	 Catch:{ all -> 0x00a5 }
    L_0x01c4:
        r2 = "msisdn";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r11);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x01cf:
        r2 = android.text.TextUtils.isEmpty(r12);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x01e0;	 Catch:{ all -> 0x00a5 }
    L_0x01d5:
        r2 = "smsAuthCode";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r12);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x01e0:
        r2 = android.text.TextUtils.isEmpty(r13);	 Catch:{ all -> 0x00a5 }
        if (r2 != 0) goto L_0x01f1;	 Catch:{ all -> 0x00a5 }
    L_0x01e6:
        r2 = "cardType";	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.b(r13);	 Catch:{ all -> 0x00a5 }
        r15.put(r2, r3);	 Catch:{ all -> 0x00a5 }
    L_0x01f1:
        r14.a(r15);	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ Exception -> 0x021d }
        r2 = r0.e;	 Catch:{ Exception -> 0x021d }
        r3 = new com.unionpay.tsmservice.i$c;	 Catch:{ Exception -> 0x021d }
        r4 = 15;	 Catch:{ Exception -> 0x021d }
        r0 = r17;	 Catch:{ Exception -> 0x021d }
        r5 = r0.S;	 Catch:{ Exception -> 0x021d }
        r6 = 15;	 Catch:{ Exception -> 0x021d }
        r5 = r5[r6];	 Catch:{ Exception -> 0x021d }
        r6 = 0;	 Catch:{ Exception -> 0x021d }
        r0 = r17;	 Catch:{ Exception -> 0x021d }
        r3.<init>(r4, r5);	 Catch:{ Exception -> 0x021d }
        r2 = r2.a(r14, r3);	 Catch:{ Exception -> 0x021d }
        r3 = -2;
        if (r3 != r2) goto L_0x0227;
    L_0x0211:
        r2 = 15;
        r0 = r18;	 Catch:{ all -> 0x00a5 }
        r1 = r19;	 Catch:{ all -> 0x00a5 }
        r2 = a(r2, r0, r1);	 Catch:{ all -> 0x00a5 }
        goto L_0x0006;	 Catch:{ all -> 0x00a5 }
    L_0x021d:
        r2 = move-exception;	 Catch:{ all -> 0x00a5 }
        r2.printStackTrace();	 Catch:{ all -> 0x00a5 }
        r2 = new android.os.RemoteException;	 Catch:{ all -> 0x00a5 }
        r2.<init>();	 Catch:{ all -> 0x00a5 }
        throw r2;	 Catch:{ all -> 0x00a5 }
    L_0x0227:
        if (r2 != 0) goto L_0x0006;	 Catch:{ all -> 0x00a5 }
    L_0x0229:
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r3 = r0.p;	 Catch:{ all -> 0x00a5 }
        r0 = r17;	 Catch:{ all -> 0x00a5 }
        r4 = r0.S;	 Catch:{ all -> 0x00a5 }
        r5 = 15;	 Catch:{ all -> 0x00a5 }
        r6 = r4[r5];	 Catch:{ all -> 0x00a5 }
        r7 = r6 + 1;	 Catch:{ all -> 0x00a5 }
        r4[r5] = r7;	 Catch:{ all -> 0x00a5 }
        r4 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x00a5 }
        r0 = r19;	 Catch:{ all -> 0x00a5 }
        r3.put(r4, r0);	 Catch:{ all -> 0x00a5 }
        goto L_0x0006;	 Catch:{ all -> 0x00a5 }
    L_0x0244:
        r2 = 15;	 Catch:{ all -> 0x00a5 }
        r0 = r18;	 Catch:{ all -> 0x00a5 }
        r1 = r19;	 Catch:{ all -> 0x00a5 }
        r2 = a(r2, r0, r1);	 Catch:{ all -> 0x00a5 }
        goto L_0x0006;
    L_0x0250:
        r2 = -1;
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.i.a(com.unionpay.tsmservice.b.i, com.unionpay.tsmservice.d):int");
    }

    public synchronized int a(com.unionpay.tsmservice.b.k kVar, d dVar, e eVar) throws RemoteException {
        int i;
        if (kVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            com.unionpay.tsmservice.b.k kVar2 = new com.unionpay.tsmservice.b.k();
            String e = kVar.e();
            a a = kVar.a();
            String b = kVar.b();
            if (!TextUtils.isEmpty(e)) {
                kVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b2 = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b2))) {
                    kVar2.a(new a(b(e), b(b2)));
                }
            }
            if (!TextUtils.isEmpty(b)) {
                kVar2.a(b(b));
            }
            try {
                i = this.e.a(kVar2, new c(16, this.S[16]), eVar);
                if (-2 == i) {
                    i = a(16, (bu) kVar, dVar, eVar);
                } else if (i == 0) {
                    HashMap hashMap = this.C;
                    int[] iArr = this.S;
                    int i2 = iArr[16];
                    iArr[16] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(16, (bu) kVar, dVar, eVar);
        }
        return i;
    }

    public synchronized int a(com.unionpay.tsmservice.b.m mVar, d dVar) throws RemoteException {
        int i;
        if (mVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            com.unionpay.tsmservice.b.m mVar2 = new com.unionpay.tsmservice.b.m();
            String e = mVar.e();
            a a = mVar.a();
            if (!TextUtils.isEmpty(e)) {
                mVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b))) {
                    mVar2.a(new a(b(e), b(b)));
                }
            }
            try {
                i = this.e.a(mVar2, dVar);
                if (-2 == i) {
                    i = a(26, (bu) mVar, dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(26, (bu) mVar, dVar);
        }
        return i;
    }

    public synchronized int a(com.unionpay.tsmservice.b.o oVar, d dVar) throws RemoteException {
        int i;
        if (oVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            com.unionpay.tsmservice.b.o oVar2 = new com.unionpay.tsmservice.b.o();
            String e = oVar.e();
            a a = oVar.a();
            if (!TextUtils.isEmpty(e)) {
                oVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b))) {
                    oVar2.a(new a(b(e), b(b)));
                }
            }
            try {
                i = this.e.a(oVar2, dVar);
                if (-2 == i) {
                    i = a(27, (bu) oVar, dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(27, (bu) oVar, dVar);
        }
        return i;
    }

    public synchronized int a(com.unionpay.tsmservice.b.q qVar, d dVar) throws RemoteException {
        int i = -8;
        synchronized (this) {
            if (dVar == null) {
                i = -3;
            } else if (d("01.00.14") && i()) {
                if (this.e == null) {
                    i = -1;
                } else if (a(this.c.getPackageName())) {
                    com.unionpay.tsmservice.b.q qVar2 = new com.unionpay.tsmservice.b.q();
                    String str = "";
                    if (qVar != null) {
                        str = qVar.e();
                    }
                    if (this.h) {
                        str = f(str);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        qVar2.d(b(str));
                    }
                    this.M.put(String.valueOf(this.S[35]), dVar);
                    try {
                        f fVar = this.e;
                        int[] iArr = this.S;
                        int i2 = iArr[35];
                        iArr[35] = i2 + 1;
                        i = fVar.a(qVar2, new c(35, i2));
                        if (i != 0) {
                            HashMap hashMap = this.M;
                            int[] iArr2 = this.S;
                            int i3 = iArr2[35] - 1;
                            iArr2[35] = i3;
                            hashMap.remove(String.valueOf(i3));
                        }
                        if (-2 == i) {
                            i = a(35, (bu) qVar, dVar);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RemoteException();
                    }
                } else {
                    i = a(35, (bu) qVar, dVar);
                }
            }
        }
        return i;
    }

    public synchronized int a(com.unionpay.tsmservice.b.s sVar, d dVar) throws RemoteException {
        int i;
        if (sVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            com.unionpay.tsmservice.b.s sVar2 = new com.unionpay.tsmservice.b.s();
            String e = sVar.e();
            if (this.h) {
                e = f(e);
            }
            if (!TextUtils.isEmpty(e)) {
                sVar2.d(b(e));
            }
            this.J.put(String.valueOf(this.S[29]), dVar);
            try {
                f fVar = this.e;
                int[] iArr = this.S;
                int i2 = iArr[29];
                iArr[29] = i2 + 1;
                i = fVar.a(sVar2, new c(29, i2));
                if (i != 0) {
                    HashMap hashMap = this.J;
                    int[] iArr2 = this.S;
                    int i3 = iArr2[29] - 1;
                    iArr2[29] = i3;
                    hashMap.remove(String.valueOf(i3));
                }
                if (-2 == i) {
                    i = a(29, (bu) sVar, dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(29, (bu) sVar, dVar);
        }
        return i;
    }

    public synchronized int a(com.unionpay.tsmservice.b.w wVar, d dVar) throws RemoteException {
        int i = -3;
        synchronized (this) {
            if (!(wVar == null || dVar == null)) {
                String a = wVar.a();
                if (!TextUtils.isEmpty(a)) {
                    if (!i()) {
                        i = -8;
                    } else if (this.e == null) {
                        i = -1;
                    } else if (a(this.c.getPackageName())) {
                        String b = b(a);
                        com.unionpay.tsmservice.b.w wVar2 = new com.unionpay.tsmservice.b.w();
                        wVar2.a(b);
                        b = wVar.e();
                        if (this.h) {
                            b = f(b);
                        }
                        if (!TextUtils.isEmpty(b)) {
                            wVar2.d(b(b));
                        }
                        this.F.put(String.valueOf(this.S[21]), dVar);
                        try {
                            f fVar = this.e;
                            int[] iArr = this.S;
                            int i2 = iArr[21];
                            iArr[21] = i2 + 1;
                            i = fVar.a(wVar2, new c(21, i2));
                            if (i != 0) {
                                HashMap hashMap = this.F;
                                int[] iArr2 = this.S;
                                int i3 = iArr2[21] - 1;
                                iArr2[21] = i3;
                                hashMap.remove(String.valueOf(i3));
                            }
                            if (-2 == i) {
                                i = a(21, (bu) wVar, dVar);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RemoteException();
                        }
                    } else {
                        i = a(21, (bu) wVar, dVar);
                    }
                }
            }
        }
        return i;
    }

    public synchronized int a(y yVar, d dVar) throws RemoteException {
        int i;
        if (yVar == null || dVar == null) {
            i = -3;
        } else if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            y yVar2 = new y();
            String e = yVar.e();
            a a = yVar.a();
            String b = yVar.b();
            String c = yVar.c();
            if (!TextUtils.isEmpty(e)) {
                yVar2.d(b(e));
            }
            if (a != null) {
                e = a.a();
                String b2 = a.b();
                if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(b2))) {
                    yVar2.a(new a(b(e), b(b2)));
                }
            }
            Object d = yVar.d();
            if (!TextUtils.isEmpty(d)) {
                yVar2.c(d);
            }
            if (!TextUtils.isEmpty(b)) {
                yVar2.a(b(b));
            }
            if (!TextUtils.isEmpty(c)) {
                yVar2.b(b(c));
            }
            try {
                i = this.e.a(yVar2, new c(19, this.S[19]));
                if (-2 == i) {
                    i = a(19, (bu) yVar, dVar);
                } else if (i == 0) {
                    HashMap hashMap = this.s;
                    int[] iArr = this.S;
                    int i2 = iArr[19];
                    iArr[19] = i2 + 1;
                    hashMap.put(String.valueOf(i2), dVar);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(19, (bu) yVar, dVar);
        }
        return i;
    }

    public int a(String str, String[] strArr) throws RemoteException {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return -3;
        }
        if (!i()) {
            return -8;
        }
        if (this.e == null) {
            return -1;
        }
        try {
            return this.e.a(str, strArr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException();
        }
    }

    public Context a() {
        return this.c;
    }

    public synchronized void a(a aVar) {
        if (aVar != null) {
            b.add(aVar);
        }
    }

    public synchronized void b(a aVar) {
        if (aVar != null) {
            b.remove(aVar);
        }
    }

    public boolean b() {
        return this.f;
    }

    public int c() {
        return this.g;
    }

    public boolean d() {
        if (this.d == null) {
            this.d = new k(this);
        }
        if (this.f) {
            return true;
        }
        Intent intent = new Intent("com.unionpay.tsmservice.UPTsmService");
        intent.setPackage("com.unionpay.tsmservice");
        this.c.startService(intent);
        return this.c.bindService(intent, this.d, 1);
    }

    public void e() {
        if (this.d != null && this.f) {
            this.c.unbindService(this.d);
            this.f = false;
        }
    }

    public synchronized int f() throws RemoteException {
        int i;
        if (!i()) {
            i = -8;
        } else if (this.e == null) {
            i = -1;
        } else if (a(this.c.getPackageName())) {
            try {
                i = this.e.a();
                if (-2 == i) {
                    i = a(34, null, 0, null, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i = a(34, null, 0, null, null);
        }
        return i;
    }
}
