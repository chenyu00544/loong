package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.baidu.mapapi.MessageCenter;
import com.baidu.mapapi.UIMsg.k_event;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.WeightedLatLng;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.LatLngBounds.Builder;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.BaseMapCallback;
import com.baidu.platform.comjni.map.basemap.JNIBaseMap;
import com.baidu.platform.comjni.map.basemap.a;
import com.baidu.platform.comjni.map.basemap.b;
import com.taobao.accs.ErrorCode;
import com.tencent.open.yyb.TitleBar;
import com.umeng.socialize.common.SocializeConstants;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.TnetStatusCode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class e implements b {
    private static int N;
    private static int O;
    private static List<JNIBaseMap> ai;
    static long k = 0;
    private static final String o = i.class.getSimpleName();
    private Context A;
    private List<d> B;
    private z C;
    private g D;
    private n E;
    private G F;
    private J G;
    private r H;
    private m I;
    private o J;
    private a K;
    private p L;
    private H M;
    private int P;
    private int Q;
    private int R;
    private a S = new a();
    private VelocityTracker T;
    private long U;
    private long V;
    private long W;
    private long X;
    private int Y;
    private float Z;
    public float a = 22.0f;
    private float aa;
    private boolean ab;
    private long ac;
    private long ad;
    private f ae;
    private String af;
    private b ag;
    private c ah;
    public float b = 3.0f;
    public float c = 22.0f;
    boolean d = true;
    boolean e = true;
    List<k> f;
    a g;
    long h;
    boolean i;
    public int j;
    boolean l;
    boolean m;
    boolean n;
    private boolean p;
    private boolean q;
    private boolean r = true;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = true;
    private boolean w = true;
    private boolean x = false;
    private L y;
    private K z;

    public e(Context context, String str) {
        this.A = context;
        this.f = new ArrayList();
        this.af = str;
    }

    private void N() {
        if (this.t || this.q || this.p || this.u) {
            if (this.a > TitleBar.BACKBTN_LEFT_MARGIN) {
                this.a = TitleBar.BACKBTN_LEFT_MARGIN;
            }
            if (D().a > TitleBar.BACKBTN_LEFT_MARGIN) {
                D D = D();
                D.a = TitleBar.BACKBTN_LEFT_MARGIN;
                a(D);
                return;
            }
            return;
        }
        this.a = this.c;
    }

    private Activity a(Context context) {
        return context == null ? null : context instanceof Activity ? (Activity) context : context instanceof ContextWrapper ? a(((ContextWrapper) context).getBaseContext()) : null;
    }

    private boolean e(Bundle bundle) {
        return this.g == null ? false : this.g.e(bundle);
    }

    private boolean f(Bundle bundle) {
        boolean z = false;
        if (!(bundle == null || this.g == null)) {
            z = this.g.d(bundle);
            if (z) {
                c(z);
                this.g.b(this.y.a);
            }
        }
        return z;
    }

    private void g(Bundle bundle) {
        if (bundle.get(SocializeConstants.OP_KEY) != null) {
            Bundle bundle2 = (Bundle) bundle.get(SocializeConstants.OP_KEY);
            int i = bundle2.getInt("type");
            if (i == h.ground.ordinal()) {
                bundle2.putLong("layer_addr", this.E.a);
                return;
            } else if (i >= h.arc.ordinal()) {
                bundle2.putLong("layer_addr", this.I.a);
                return;
            } else if (i == h.popup.ordinal()) {
                bundle2.putLong("layer_addr", this.H.a);
                return;
            } else {
                bundle2.putLong("layer_addr", this.G.a);
                return;
            }
        }
        int i2 = bundle.getInt("type");
        if (i2 == h.ground.ordinal()) {
            bundle.putLong("layer_addr", this.E.a);
        } else if (i2 >= h.arc.ordinal()) {
            bundle.putLong("layer_addr", this.I.a);
        } else if (i2 == h.popup.ordinal()) {
            bundle.putLong("layer_addr", this.H.a);
        } else {
            bundle.putLong("layer_addr", this.G.a);
        }
    }

    public static void j(boolean z) {
        ai = a.d();
        if (ai == null || ai.size() == 0) {
            a.b(0, z);
            return;
        }
        a.b(((JNIBaseMap) ai.get(0)).a, z);
        for (JNIBaseMap jNIBaseMap : ai) {
            jNIBaseMap.ClearLayer(jNIBaseMap.a, -1);
        }
    }

    void A() {
        this.m = false;
        this.l = false;
        for (k c : this.f) {
            c.c(D());
        }
    }

    public boolean B() {
        return this.g != null ? this.g.a(this.F.a) : false;
    }

    public boolean C() {
        return this.g != null ? this.g.a(this.ah.a) : false;
    }

    public D D() {
        if (this.g == null) {
            return null;
        }
        Bundle j = this.g.j();
        D d = new D();
        d.a(j);
        return d;
    }

    public LatLngBounds E() {
        if (this.g == null) {
            return null;
        }
        Bundle k = this.g.k();
        Builder builder = new Builder();
        int i = k.getInt("maxCoorx");
        int i2 = k.getInt("minCoorx");
        builder.include(CoordUtil.mc2ll(new GeoPoint((double) k.getInt("minCoory"), (double) i))).include(CoordUtil.mc2ll(new GeoPoint((double) k.getInt("maxCoory"), (double) i2)));
        return builder.build();
    }

    public int F() {
        return this.P;
    }

    public int G() {
        return this.Q;
    }

    public D H() {
        if (this.g == null) {
            return null;
        }
        Bundle l = this.g.l();
        D d = new D();
        d.a(l);
        return d;
    }

    public double I() {
        return D().m;
    }

    void J() {
        if (!this.l) {
            this.l = true;
            this.m = false;
            for (k a : this.f) {
                a.a(D());
            }
        }
    }

    void K() {
        this.l = false;
        if (!this.m) {
            for (k c : this.f) {
                c.c(D());
            }
        }
    }

    void L() {
        this.R = 0;
        this.S.e = false;
        this.S.h = 0.0d;
    }

    void M() {
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }

    public float a(int i, int i2, int i3, int i4, int i5, int i6) {
        if (!this.i) {
            return 12.0f;
        }
        if (this.g == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("left", i);
        bundle.putInt("right", i3);
        bundle.putInt("bottom", i4);
        bundle.putInt("top", i2);
        bundle.putInt("hasHW", 1);
        bundle.putInt("width", i5);
        bundle.putInt("height", i6);
        return this.g.c(bundle);
    }

    int a(int i, int i2, int i3) {
        return a.a(this.h, i, i2, i3);
    }

    public int a(Bundle bundle, long j, int i, Bundle bundle2) {
        if (j == this.D.a) {
            bundle.putString("jsondata", this.D.a());
            bundle.putBundle(SocializeConstants.OP_KEY, this.D.b());
            return this.D.g;
        } else if (j == this.C.a) {
            bundle.putString("jsondata", this.C.a());
            bundle.putBundle(SocializeConstants.OP_KEY, this.C.b());
            return this.C.g;
        } else if (j == this.J.a) {
            bundle.putBundle(SocializeConstants.OP_KEY, this.L.a(bundle2.getInt("x"), bundle2.getInt("y"), bundle2.getInt("zoom")));
            return this.J.g;
        } else if (j != this.y.a) {
            return 0;
        } else {
            bundle.putBundle(SocializeConstants.OP_KEY, this.z.a(bundle2.getInt("x"), bundle2.getInt("y"), bundle2.getInt("zoom"), this.A));
            return this.y.g;
        }
    }

    public Point a(GeoPoint geoPoint) {
        return this.M.a(geoPoint);
    }

    void a() {
        this.g = new a();
        this.g.a();
        this.h = this.g.c();
        if (SysOSUtil.getDensityDpi() < 180) {
            this.j = 18;
        } else if (SysOSUtil.getDensityDpi() < SocializeConstants.MASK_USER_CENTER_HIDE_AREA) {
            this.j = 25;
        } else if (SysOSUtil.getDensityDpi() < 320) {
            this.j = 37;
        } else {
            this.j = 50;
        }
        String moduleFileName = SysOSUtil.getModuleFileName();
        String appSDCardPath = EnvironmentUtilities.getAppSDCardPath();
        String appCachePath = EnvironmentUtilities.getAppCachePath();
        String appSecondCachePath = EnvironmentUtilities.getAppSecondCachePath();
        int mapTmpStgMax = EnvironmentUtilities.getMapTmpStgMax();
        int domTmpStgMax = EnvironmentUtilities.getDomTmpStgMax();
        int itsTmpStgMax = EnvironmentUtilities.getItsTmpStgMax();
        String str = SysOSUtil.getDensityDpi() >= 180 ? "/h/" : "/l/";
        String str2 = moduleFileName + "/cfg";
        String str3 = appSDCardPath + "/vmp";
        moduleFileName = str2 + str;
        String str4 = str2 + "/a/";
        String str5 = str2 + "/idrres/";
        appSDCardPath = str3 + str;
        str2 = str3 + str;
        appCachePath = appCachePath + "/tmp/";
        appSecondCachePath = appSecondCachePath + "/tmp/";
        Activity a = a(this.A);
        if (a != null) {
            Display defaultDisplay = a.getWindowManager().getDefaultDisplay();
            this.g.a(moduleFileName, appSDCardPath, appCachePath, appSecondCachePath, str2, str4, this.af, str5, defaultDisplay.getWidth(), defaultDisplay.getHeight(), SysOSUtil.getDensityDpi(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, 0);
            this.g.f();
            return;
        }
        throw new RuntimeException("Please give the right context.");
    }

    public void a(float f, float f2) {
        this.a = f;
        this.c = f;
        this.b = f2;
    }

    void a(int i, int i2) {
        this.P = i;
        this.Q = i2;
    }

    public void a(Bitmap bitmap) {
        if (this.g != null) {
            Bundle bundle;
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put("type", 0);
                jSONObject2.put("x", N);
                jSONObject2.put("y", O);
                jSONObject2.put("hidetime", 1000);
                jSONArray.put(jSONObject2);
                jSONObject.put("data", jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (bitmap == null) {
                bundle = null;
            } else {
                Bundle bundle2 = new Bundle();
                ArrayList arrayList = new ArrayList();
                ParcelItem parcelItem = new ParcelItem();
                Bundle bundle3 = new Bundle();
                Buffer allocate = ByteBuffer.allocate((bitmap.getWidth() * bitmap.getHeight()) * 4);
                bitmap.copyPixelsToBuffer(allocate);
                bundle3.putByteArray("imgdata", allocate.array());
                bundle3.putInt("imgindex", bitmap.hashCode());
                bundle3.putInt("imgH", bitmap.getHeight());
                bundle3.putInt("imgW", bitmap.getWidth());
                bundle3.putInt("hasIcon", 1);
                parcelItem.setBundle(bundle3);
                arrayList.add(parcelItem);
                if (arrayList.size() > 0) {
                    Parcelable[] parcelableArr = new ParcelItem[arrayList.size()];
                    for (int i = 0; i < arrayList.size(); i++) {
                        parcelableArr[i] = (ParcelItem) arrayList.get(i);
                    }
                    bundle2.putParcelableArray("icondata", parcelableArr);
                }
                bundle = bundle2;
            }
            b(jSONObject.toString(), bundle);
            this.g.b(this.D.a);
        }
    }

    void a(Handler handler) {
        MessageCenter.registMessage(m_AppUI.MSG_APP_SAVESCREEN, handler);
        MessageCenter.registMessage(39, handler);
        MessageCenter.registMessage(41, handler);
        MessageCenter.registMessage(49, handler);
        MessageCenter.registMessage(m_AppUI.V_WM_VDATAENGINE, handler);
        MessageCenter.registMessage(50, handler);
        MessageCenter.registMessage(999, handler);
        BaseMapCallback.addLayerDataInterface(this.h, this);
    }

    public void a(LatLngBounds latLngBounds) {
        if (latLngBounds != null && this.g != null) {
            LatLng latLng = latLngBounds.northeast;
            LatLng latLng2 = latLngBounds.southwest;
            GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
            GeoPoint ll2mc2 = CoordUtil.ll2mc(latLng2);
            int longitudeE6 = (int) ll2mc.getLongitudeE6();
            int latitudeE6 = (int) ll2mc2.getLatitudeE6();
            int longitudeE62 = (int) ll2mc2.getLongitudeE6();
            int latitudeE62 = (int) ll2mc.getLatitudeE6();
            Bundle bundle = new Bundle();
            bundle.putInt("maxCoorx", longitudeE6);
            bundle.putInt("minCoory", latitudeE6);
            bundle.putInt("minCoorx", longitudeE62);
            bundle.putInt("maxCoory", latitudeE62);
            this.g.b(bundle);
        }
    }

    void a(B b) {
        D d = new D();
        if (b == null) {
            b = new B();
        }
        d = b.a;
        this.v = b.f;
        this.w = b.d;
        this.d = b.e;
        this.e = b.g;
        this.g.a(d.a(this));
        this.g.a(A.DEFAULT.ordinal());
        this.r = b.b;
        if (b.b) {
            N = (int) (SysOSUtil.getDensity() * 40.0f);
            O = (int) (SysOSUtil.getDensity() * 40.0f);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("x", N);
                jSONObject2.put("y", O);
                jSONObject2.put("hidetime", 1000);
                jSONArray.put(jSONObject2);
                jSONObject.put("data", jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.D.a(jSONObject.toString());
            this.g.a(this.D.a, true);
        } else {
            this.g.a(this.D.a, false);
        }
        int i = b.c;
        if (i == 2) {
            a(true);
        }
        if (i == 3) {
            this.g.a(this.ae.a, false);
        }
    }

    public void a(D d) {
        if (this.g != null) {
            Bundle a = d.a(this);
            a.putInt("animation", 0);
            a.putInt("animatime", 0);
            this.g.a(a);
        }
    }

    public void a(D d, int i) {
        if (this.g != null) {
            Bundle a = d.a(this);
            a.putInt("animation", 1);
            a.putInt("animatime", i);
            z();
            this.g.a(a);
        }
    }

    public void a(K k) {
        this.z = k;
    }

    void a(d dVar) {
        if (this.g != null) {
            dVar.a = this.g.a(dVar.c, dVar.d, dVar.b);
            this.B.add(dVar);
        }
    }

    public void a(k kVar) {
        this.f.add(kVar);
    }

    public void a(p pVar) {
        this.L = pVar;
    }

    public void a(String str, Bundle bundle) {
        if (this.g != null) {
            this.C.a(str);
            this.C.a(bundle);
            this.g.b(this.C.a);
        }
    }

    public void a(List<Bundle> list) {
        if (this.g != null && list != null) {
            int size = list.size();
            Bundle[] bundleArr = new Bundle[list.size()];
            for (int i = 0; i < size; i++) {
                g((Bundle) list.get(i));
                bundleArr[i] = (Bundle) list.get(i);
            }
            this.g.a(bundleArr);
        }
    }

    public void a(boolean z) {
        if (this.g != null) {
            if (!this.g.a(this.ae.a)) {
                this.g.a(this.ae.a, true);
            }
            this.q = z;
            N();
            this.g.a(this.q);
        }
    }

    public boolean a(long j) {
        for (d dVar : this.B) {
            if (dVar.a == j) {
                return true;
            }
        }
        return false;
    }

    public boolean a(Point point) {
        if (point == null || this.g == null || point.x < 0 || point.y < 0) {
            return false;
        }
        N = point.x;
        O = point.y;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("x", N);
            jSONObject2.put("y", O);
            jSONObject2.put("hidetime", 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put("data", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.D.a(jSONObject.toString());
        this.g.b(this.D.a);
        return true;
    }

    public boolean a(Bundle bundle) {
        if (this.g == null) {
            return false;
        }
        this.y = new L();
        long a = this.g.a(this.y.c, this.y.d, this.y.b);
        if (a == 0) {
            return false;
        }
        this.y.a = a;
        this.B.add(this.y);
        bundle.putLong("sdktileaddr", a);
        return e(bundle) && f(bundle);
    }

    boolean a(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount == 2 && !(c((int) motionEvent.getX(0), (int) motionEvent.getY(0)) && c((int) motionEvent.getX(1), (int) motionEvent.getY(1)))) {
            pointerCount = 1;
        }
        if (pointerCount == 2) {
            float y = ((float) this.Q) - motionEvent.getY(0);
            float y2 = ((float) this.Q) - motionEvent.getY(1);
            float x = motionEvent.getX(0);
            float x2 = motionEvent.getX(1);
            switch (motionEvent.getAction()) {
                case 5:
                    this.V = motionEvent.getEventTime();
                    this.Y--;
                    break;
                case 6:
                    this.X = motionEvent.getEventTime();
                    this.Y++;
                    break;
                case 261:
                    this.U = motionEvent.getEventTime();
                    this.Y--;
                    break;
                case 262:
                    this.W = motionEvent.getEventTime();
                    this.Y++;
                    break;
            }
            if (this.T == null) {
                this.T = VelocityTracker.obtain();
            }
            this.T.addMovement(motionEvent);
            int minimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            this.T.computeCurrentVelocity(1000, (float) ViewConfiguration.getMaximumFlingVelocity());
            float xVelocity = this.T.getXVelocity(1);
            float yVelocity = this.T.getYVelocity(1);
            float xVelocity2 = this.T.getXVelocity(2);
            float yVelocity2 = this.T.getYVelocity(2);
            if (Math.abs(xVelocity) > ((float) minimumFlingVelocity) || Math.abs(yVelocity) > ((float) minimumFlingVelocity) || Math.abs(xVelocity2) > ((float) minimumFlingVelocity) || Math.abs(yVelocity2) > ((float) minimumFlingVelocity)) {
                if (this.S.e) {
                    double sqrt;
                    int log;
                    if (this.R == 0) {
                        if ((this.S.c - y <= 0.0f || this.S.d - y2 <= 0.0f) && (this.S.c - y >= 0.0f || this.S.d - y2 >= 0.0f)) {
                            this.R = 2;
                        } else {
                            sqrt = Math.sqrt((double) (((x2 - x) * (x2 - x)) + ((y2 - y) * (y2 - y)))) / this.S.h;
                            log = (int) ((Math.log(sqrt) / Math.log(2.0d)) * 10000.0d);
                            minimumFlingVelocity = (int) (((Math.atan2((double) (y2 - y), (double) (x2 - x)) - Math.atan2((double) (this.S.d - this.S.c), (double) (this.S.b - this.S.a))) * 180.0d) / 3.1416d);
                            if ((sqrt <= 0.0d || (log <= b.REQUEST_MERGE_PERIOD && log >= TnetStatusCode.EASY_REASON_HANDSHAKE_ERROR)) && Math.abs(minimumFlingVelocity) < 10) {
                                this.R = 1;
                            } else {
                                this.R = 2;
                            }
                        }
                        if (this.R == 0) {
                            return true;
                        }
                    }
                    if (this.R == 1 && this.v) {
                        if (this.S.c - y > 0.0f && this.S.d - y2 > 0.0f) {
                            J();
                            a(1, 83, 0);
                        } else if (this.S.c - y < 0.0f && this.S.d - y2 < 0.0f) {
                            J();
                            a(1, 87, 0);
                        }
                    } else if (this.R == 2 || this.R == 4 || this.R == 3) {
                        double atan2 = Math.atan2((double) (y2 - y), (double) (x2 - x)) - Math.atan2((double) (this.S.d - this.S.c), (double) (this.S.b - this.S.a));
                        sqrt = Math.sqrt((double) (((x2 - x) * (x2 - x)) + ((y2 - y) * (y2 - y)))) / this.S.h;
                        log = (int) ((Math.log(sqrt) / Math.log(2.0d)) * 10000.0d);
                        double atan22 = Math.atan2((double) (this.S.g - this.S.c), (double) (this.S.f - this.S.a));
                        double sqrt2 = Math.sqrt((double) (((this.S.f - this.S.a) * (this.S.f - this.S.a)) + ((this.S.g - this.S.c) * (this.S.g - this.S.c))));
                        float cos = (float) (((Math.cos(atan22 + atan2) * sqrt2) * sqrt) + ((double) x));
                        float sin = (float) (((Math.sin(atan22 + atan2) * sqrt2) * sqrt) + ((double) y));
                        minimumFlingVelocity = (int) ((atan2 * 180.0d) / 3.1416d);
                        if (sqrt > 0.0d && (3 == this.R || (Math.abs(log) > m_AppUI.MSG_APP_DATA_OK && 2 == this.R))) {
                            this.R = 3;
                            float f = D().a;
                            if (this.e) {
                                if (sqrt > WeightedLatLng.DEFAULT_INTENSITY) {
                                    if (f >= this.a) {
                                        return false;
                                    }
                                    J();
                                    a(k_event.V_WM_ROTATE, 3, log);
                                } else if (f <= this.b) {
                                    return false;
                                } else {
                                    J();
                                    a(k_event.V_WM_ROTATE, 3, log);
                                }
                            }
                        } else if (minimumFlingVelocity != 0 && (4 == this.R || (Math.abs(minimumFlingVelocity) > 10 && 2 == this.R))) {
                            this.R = 4;
                            if (this.w) {
                                J();
                                a(k_event.V_WM_ROTATE, 1, minimumFlingVelocity);
                            }
                        }
                        this.S.f = cos;
                        this.S.g = sin;
                    }
                }
            } else if (this.R == 0 && this.Y == 0) {
                this.W = this.W > this.X ? this.W : this.X;
                this.U = this.U < this.V ? this.V : this.U;
                if (this.W - this.U < 200 && this.e) {
                    D D = D();
                    if (D != null) {
                        D.a -= 1.0f;
                        a(D, (int) ErrorCode.APP_NOT_BIND);
                    }
                }
            }
            if (2 != this.R) {
                this.S.c = y;
                this.S.d = y2;
                this.S.a = x;
                this.S.b = x2;
            }
            if (!this.S.e) {
                this.S.f = (float) (this.P / 2);
                this.S.g = (float) (this.Q / 2);
                this.S.e = true;
                if (0.0d == this.S.h) {
                    this.S.h = Math.sqrt((double) (((this.S.b - this.S.a) * (this.S.b - this.S.a)) + ((this.S.d - this.S.c) * (this.S.d - this.S.c))));
                }
            }
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                b(motionEvent);
                return true;
            case 1:
                return d(motionEvent);
            case 2:
                c(motionEvent);
                return true;
            default:
                return false;
        }
    }

    public boolean a(String str, String str2) {
        return this.g.a(str, str2);
    }

    public GeoPoint b(int i, int i2) {
        return this.M.a(i, i2);
    }

    void b() {
        this.B = new ArrayList();
        this.ae = new f();
        a(this.ae);
        this.ag = new b();
        a(this.ag);
        if (this.g != null) {
            this.g.e(false);
        }
        this.E = new n();
        a(this.E);
        this.J = new o();
        a(this.J);
        this.K = new a();
        a(this.K);
        a(new q());
        this.F = new G();
        a(this.F);
        this.ah = new c();
        a(this.ah);
        this.I = new m();
        a(this.I);
        this.G = new J();
        a(this.G);
        this.D = new g();
        a(this.D);
        this.C = new z();
        a(this.C);
        this.H = new r();
        a(this.H);
    }

    public void b(Bundle bundle) {
        if (this.g != null) {
            g(bundle);
            this.g.f(bundle);
        }
    }

    void b(Handler handler) {
        MessageCenter.unregistMessage(m_AppUI.MSG_APP_SAVESCREEN, handler);
        MessageCenter.unregistMessage(41, handler);
        MessageCenter.unregistMessage(49, handler);
        MessageCenter.unregistMessage(39, handler);
        MessageCenter.unregistMessage(m_AppUI.V_WM_VDATAENGINE, handler);
        MessageCenter.unregistMessage(50, handler);
        MessageCenter.unregistMessage(999, handler);
        BaseMapCallback.removeLayerDataInterface(this.h);
    }

    void b(MotionEvent motionEvent) {
        if (!this.S.e) {
            this.ad = motionEvent.getDownTime();
            if (this.ad - this.ac >= 400) {
                this.ac = this.ad;
            } else if (Math.abs(motionEvent.getX() - this.Z) >= 120.0f || Math.abs(motionEvent.getY() - this.aa) >= 120.0f) {
                this.ac = this.ad;
            } else {
                this.ac = 0;
            }
            this.Z = motionEvent.getX();
            this.aa = motionEvent.getY();
            a(4, 0, ((int) motionEvent.getX()) | (((int) motionEvent.getY()) << 16));
            this.ab = true;
        }
    }

    public void b(String str, Bundle bundle) {
        if (this.g != null) {
            this.D.a(str);
            this.D.a(bundle);
            this.g.b(this.D.a);
        }
    }

    public void b(boolean z) {
        this.x = z;
    }

    public void c(Bundle bundle) {
        if (this.g != null) {
            g(bundle);
            this.g.g(bundle);
        }
    }

    public void c(boolean z) {
        if (this.g != null) {
            this.g.a(this.y.a, z);
        }
    }

    public boolean c() {
        return this.x;
    }

    boolean c(int i, int i2) {
        return i >= 0 && i <= this.P + 0 && i2 >= 0 && i2 <= this.Q + 0;
    }

    boolean c(MotionEvent motionEvent) {
        if (this.S.e) {
            return true;
        }
        if (System.currentTimeMillis() - k < 300) {
            return true;
        }
        if (this.n) {
            for (k d : this.f) {
                d.d(b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
            return true;
        }
        float abs = Math.abs(motionEvent.getX() - this.Z);
        float abs2 = Math.abs(motionEvent.getY() - this.aa);
        float density = (float) (((double) SysOSUtil.getDensity()) > 1.5d ? ((double) SysOSUtil.getDensity()) * 1.5d : (double) SysOSUtil.getDensity());
        if (this.ab && abs / density <= 3.0f && abs2 / density <= 3.0f) {
            return true;
        }
        this.ab = false;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (!this.d) {
            return false;
        }
        J();
        a(3, 0, (y << 16) | x);
        return false;
    }

    public void d(Bundle bundle) {
        if (this.g != null) {
            g(bundle);
            this.g.h(bundle);
        }
    }

    public void d(boolean z) {
        if (this.g != null) {
            this.g.a(this.ae.a, z);
        }
    }

    public boolean d() {
        return (this.y == null || this.g == null) ? false : this.g.c(this.y.a);
    }

    boolean d(MotionEvent motionEvent) {
        if (this.n) {
            for (k e : this.f) {
                e.e(b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
            this.n = false;
            return true;
        }
        boolean z = !this.S.e && motionEvent.getEventTime() - this.ad < 400 && Math.abs(motionEvent.getX() - this.Z) < TitleBar.SHAREBTN_RIGHT_MARGIN && Math.abs(motionEvent.getY() - this.aa) < TitleBar.SHAREBTN_RIGHT_MARGIN;
        L();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (z) {
            return false;
        }
        if (x < 0) {
            x = 0;
        }
        a(5, 0, ((y < 0 ? 0 : y) << 16) | x);
        return true;
    }

    void e() {
        if (this.g != null) {
            this.M = new H(this.g);
        }
    }

    public void e(boolean z) {
        if (this.g != null) {
            this.u = z;
            this.g.b(this.u);
        }
    }

    public void f(boolean z) {
        if (this.g != null) {
            this.p = z;
            this.g.c(this.p);
        }
    }

    public boolean f() {
        return this.p;
    }

    public String g() {
        return this.g == null ? null : this.g.e(this.D.a);
    }

    public void g(boolean z) {
        if (this.g != null) {
            this.g.d(z);
        }
    }

    public void h(boolean z) {
        if (this.g != null) {
            this.r = z;
            this.g.a(this.D.a, z);
        }
    }

    public boolean h() {
        return this.u;
    }

    public void i(boolean z) {
        this.g.e(z);
        this.g.d(this.ag.a);
        this.g.d(this.ah.a);
    }

    public boolean i() {
        return this.g == null ? false : this.g.m();
    }

    public boolean j() {
        return this.q;
    }

    public void k(boolean z) {
        if (this.g != null) {
            this.s = z;
            this.g.a(this.C.a, z);
        }
    }

    public boolean k() {
        return this.g.a(this.ae.a);
    }

    public void l(boolean z) {
        if (this.g != null) {
            this.t = z;
            this.g.a(this.J.a, z);
        }
    }

    public boolean l() {
        return this.g == null ? false : this.g.q();
    }

    public void m() {
        if (this.g != null) {
            this.g.d(this.E.a);
            this.g.d(this.I.a);
            this.g.d(this.G.a);
            this.g.d(this.H.a);
        }
    }

    public void m(boolean z) {
        this.d = z;
    }

    public void n() {
        if (this.g != null) {
            this.g.r();
            this.g.b(this.J.a);
        }
    }

    public void n(boolean z) {
        this.e = z;
    }

    public MapBaseIndoorMapInfo o() {
        return this.g.s();
    }

    public void o(boolean z) {
        this.w = z;
    }

    public void p(boolean z) {
        this.v = z;
    }

    public boolean p() {
        return this.g.t();
    }

    public void q(boolean z) {
        if (this.g != null) {
            this.g.a(this.F.a, z);
        }
    }

    public boolean q() {
        return this.r;
    }

    public void r(boolean z) {
        if (this.g != null) {
            this.g.a(this.ah.a, z);
        }
    }

    public boolean r() {
        return this.s;
    }

    public void s() {
        if (this.g != null) {
            this.g.b(this.J.a);
        }
    }

    public void t() {
        if (this.g != null) {
            this.g.g();
        }
    }

    public void u() {
        if (this.g != null) {
            this.g.h();
        }
    }

    public boolean v() {
        return this.d;
    }

    public boolean w() {
        return this.e;
    }

    public boolean x() {
        return this.w;
    }

    public boolean y() {
        return this.v;
    }

    void z() {
        if (!this.l && !this.m) {
            this.m = true;
            for (k a : this.f) {
                a.a(D());
            }
        }
    }
}
