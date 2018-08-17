package com.baidu.location;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.location.b.f;
import com.baidu.location.b.g;
import com.baidu.location.b.j;
import com.baidu.location.e.p;
import java.util.ArrayList;
import java.util.Iterator;

public final class LocationClient implements f, com.baidu.location.e.p.a {
    private static final int lB = 7;
    private static final int lJ = 5;
    private static final int lK = 12;
    private static final int lM = 6;
    private static final int lN = 2;
    private static final int lU = 11;
    private static final int lY = 4;
    private static final int lj = 10;
    private static final String ll = "baidu_location_Client";
    private static final int lo = 1;
    private static final int ls = 1000;
    private static final int lv = 3;
    private static final int lw = 8;
    private static final int lz = 9;
    private p l0 = null;
    private ArrayList l1 = null;
    private boolean lA = false;
    private boolean lC;
    private final Messenger lD = new Messenger(this.lk);
    private Context lE = null;
    private boolean lF = false;
    private Messenger lG = null;
    private long lH = 0;
    private LocationClientOption lI = new LocationClientOption();
    private Boolean lL = Boolean.valueOf(true);
    private boolean lO = false;
    private long lP = 0;
    private long lQ = 0;
    private ServiceConnection lR = new LocationClient_1(this);
    private String lS;
    private BDLocation lT = null;
    private String lV = null;
    private boolean lW = false;
    private String lX = null;
    private boolean lZ = true;
    private boolean li = false;
    private a lk = new a();
    private boolean lm = false;
    private final Object ln = new Object();
    private b lp = null;
    private Boolean lq = Boolean.valueOf(false);
    private com.baidu.location.d.a lr = null;
    private Boolean lt = Boolean.valueOf(false);
    private boolean lu = false;
    private BDLocationListener lx = null;
    private boolean ly = false;

    class LocationClient_1 implements ServiceConnection {
        final /* synthetic */ LocationClient a;

        LocationClient_1(LocationClient locationClient) {
            this.a = locationClient;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.a.lG = new Messenger(iBinder);
            if (this.a.lG != null) {
                this.a.lO = true;
                Log.d("baidu_location_client", "baidu location connected ...");
                if (this.a.lZ) {
                    this.a.lk.obtainMessage(2).sendToTarget();
                    return;
                }
                try {
                    Message obtain = Message.obtain(null, 11);
                    obtain.replyTo = this.a.lD;
                    obtain.setData(this.a.dI());
                    this.a.lG.send(obtain);
                    this.a.lO = true;
                    if (this.a.lI != null) {
                        if (this.a.lL.booleanValue()) {
                            this.a.lk.obtainMessage(4).sendToTarget();
                        } else {
                            this.a.lk.obtainMessage(4).sendToTarget();
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.a.lG = null;
            this.a.lO = false;
        }
    }

    private class a extends Handler {
        final /* synthetic */ LocationClient a;

        private a(LocationClient locationClient) {
            this.a = locationClient;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.dJ();
                    return;
                case 2:
                    this.a.dK();
                    return;
                case 3:
                    this.a.e(message);
                    return;
                case 4:
                    this.a.dN();
                    return;
                case 5:
                    this.a.j(message);
                    return;
                case 6:
                    this.a.d(message);
                    return;
                case 7:
                    return;
                case 8:
                    this.a.k(message);
                    return;
                case 9:
                    this.a.void(message);
                    return;
                case 10:
                    this.a.i(message);
                    return;
                case 11:
                    this.a.dL();
                    return;
                case 12:
                    this.a.dM();
                    return;
                case 21:
                    Bundle data = message.getData();
                    data.setClassLoader(BDLocation.class.getClassLoader());
                    BDLocation bDLocation = (BDLocation) data.getParcelable("locStr");
                    if (this.a.lm || !this.a.lF || bDLocation.getLocType() != 66) {
                        if (this.a.lm || !this.a.lF) {
                            if (!this.a.lm) {
                                this.a.lm = true;
                            }
                            this.a.if(message, 21);
                            return;
                        }
                        this.a.lm = true;
                        return;
                    }
                    return;
                case 26:
                    this.a.if(message, 26);
                    return;
                case 27:
                    this.a.h(message);
                    return;
                case 54:
                    if (this.a.lI.location_change_notify) {
                        this.a.li = true;
                        return;
                    }
                    return;
                case 55:
                    if (this.a.lI.location_change_notify) {
                        this.a.li = false;
                        return;
                    }
                    return;
                case g.I /*701*/:
                    this.a.byte((BDLocation) message.obj);
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    private class b implements Runnable {
        final /* synthetic */ LocationClient a;

        private b(LocationClient locationClient) {
            this.a = locationClient;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r6 = this;
            r0 = r6.a;
            r1 = r0.ln;
            monitor-enter(r1);
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r2 = 0;
            r0.lA = r2;	 Catch:{ all -> 0x0036 }
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.lG;	 Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x001d;
        L_0x0015:
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.lD;	 Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x001f;
        L_0x001d:
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
        L_0x001e:
            return;
        L_0x001f:
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.l1;	 Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0034;
        L_0x0027:
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.l1;	 Catch:{ all -> 0x0036 }
            r0 = r0.size();	 Catch:{ all -> 0x0036 }
            r2 = 1;
            if (r0 >= r2) goto L_0x0039;
        L_0x0034:
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
            goto L_0x001e;
        L_0x0036:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
            throw r0;
        L_0x0039:
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.lW;	 Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x006f;
        L_0x0041:
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.lp;	 Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x0055;
        L_0x0049:
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r2 = new com.baidu.location.LocationClient$b;	 Catch:{ all -> 0x0036 }
            r3 = r6.a;	 Catch:{ all -> 0x0036 }
            r2.<init>(r3);	 Catch:{ all -> 0x0036 }
            r0.lp = r2;	 Catch:{ all -> 0x0036 }
        L_0x0055:
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.lk;	 Catch:{ all -> 0x0036 }
            r2 = r6.a;	 Catch:{ all -> 0x0036 }
            r2 = r2.lp;	 Catch:{ all -> 0x0036 }
            r3 = r6.a;	 Catch:{ all -> 0x0036 }
            r3 = r3.lI;	 Catch:{ all -> 0x0036 }
            r3 = r3.scanSpan;	 Catch:{ all -> 0x0036 }
            r4 = (long) r3;	 Catch:{ all -> 0x0036 }
            r0.postDelayed(r2, r4);	 Catch:{ all -> 0x0036 }
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
            goto L_0x001e;
        L_0x006f:
            r0 = r6.a;	 Catch:{ all -> 0x0036 }
            r0 = r0.lk;	 Catch:{ all -> 0x0036 }
            r2 = 4;
            r0 = r0.obtainMessage(r2);	 Catch:{ all -> 0x0036 }
            r0.sendToTarget();	 Catch:{ all -> 0x0036 }
            monitor-exit(r1);	 Catch:{ all -> 0x0036 }
            goto L_0x001e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.LocationClient.b.run():void");
        }
    }

    public LocationClient(Context context) {
        this.lE = context;
        this.lI = new LocationClientOption();
    }

    public LocationClient(Context context, LocationClientOption locationClientOption) {
        this.lE = context;
        this.lI = locationClientOption;
        if (this.l0 == null) {
            this.l0 = new p(this.lE, this.lI, this);
            this.l0.cs();
        }
    }

    private void byte(BDLocation bDLocation) {
        if (!this.lZ) {
            this.lT = bDLocation;
            if (!this.lm && bDLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                this.lF = true;
            }
            if (this.l1 != null) {
                Iterator it = this.l1.iterator();
                while (it.hasNext()) {
                    ((BDLocationListener) it.next()).onReceiveLocation(bDLocation);
                }
            }
        }
    }

    private void d(Message message) {
        if (message != null && message.obj != null) {
            BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
            if (this.l1 != null && this.l1.contains(bDLocationListener)) {
                this.l1.remove(bDLocationListener);
            }
        }
    }

    private Bundle dI() {
        if (this.lI == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packName", this.lX);
        bundle.putString("prodName", this.lI.prodName);
        bundle.putString("coorType", this.lI.coorType);
        bundle.putString("addrType", this.lI.addrType);
        bundle.putBoolean("openGPS", this.lI.openGps);
        bundle.putBoolean("location_change_notify", this.lI.location_change_notify);
        bundle.putInt("scanSpan", this.lI.scanSpan);
        bundle.putInt("timeOut", this.lI.timeOut);
        bundle.putInt("priority", this.lI.priority);
        bundle.putBoolean("map", this.lt.booleanValue());
        bundle.putBoolean("import", this.lq.booleanValue());
        bundle.putBoolean("needDirect", this.lI.mIsNeedDeviceDirect);
        bundle.putBoolean("isneedaptag", this.lI.isNeedAptag);
        bundle.putBoolean("isneedpoiregion", this.lI.isNeedPoiRegion);
        bundle.putBoolean("isneedregular", this.lI.isNeedRegular);
        bundle.putBoolean("isneedaptagd", this.lI.isNeedAptagd);
        bundle.putBoolean("isneedaltitude", this.lI.isNeedAltitude);
        return bundle;
    }

    private void dJ() {
        if (!this.lO) {
            if (this.lL.booleanValue()) {
                if (this.l0 == null) {
                    this.l0 = new p(this.lE, this.lI, this);
                    this.l0.cs();
                }
                this.l0.cq();
                this.lL = Boolean.valueOf(false);
            }
            this.lX = this.lE.getPackageName();
            this.lV = this.lX + "_bdls_v2.9";
            Intent intent = new Intent(this.lE, f.class);
            try {
                intent.putExtra("debug_dev", this.lC);
            } catch (Exception e) {
            }
            if (this.lI == null) {
                this.lI = new LocationClientOption();
            }
            intent.putExtra("cache_exception", this.lI.isIgnoreCacheException);
            intent.putExtra("kill_process", this.lI.isIgnoreKillProcess);
            try {
                this.lE.bindService(intent, this.lR, 1);
            } catch (Exception e2) {
                e2.printStackTrace();
                this.lO = false;
            }
        }
    }

    private void dK() {
        if (this.lO && this.lG != null) {
            Message obtain = Message.obtain(null, 12);
            obtain.replyTo = this.lD;
            try {
                this.lG.send(obtain);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                this.lE.unbindService(this.lR);
            } catch (Exception e2) {
            }
            synchronized (this.ln) {
                try {
                    if (this.lA) {
                        this.lk.removeCallbacks(this.lp);
                        this.lA = false;
                    }
                } catch (Exception e3) {
                }
            }
            if (this.lr != null) {
                this.lr.a3();
            }
            this.lG = null;
            this.lW = false;
            this.lu = false;
            this.lO = false;
            this.lF = false;
            this.lm = false;
        }
    }

    private void dL() {
        if (this.lG != null) {
            Message obtain = Message.obtain(null, 22);
            try {
                obtain.replyTo = this.lD;
                this.lG.send(obtain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void dM() {
        Message obtain = Message.obtain(null, 28);
        try {
            obtain.replyTo = this.lD;
            this.lG.send(obtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dN() {
        if (this.lG != null) {
            if ((System.currentTimeMillis() - this.lH > 3000 || !this.lI.location_change_notify || this.lW) && (!this.lu || System.currentTimeMillis() - this.lQ > 20000 || this.lW)) {
                Message obtain = Message.obtain(null, 22);
                if (this.lW) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isWaitingLocTag", this.lW);
                    obtain.setData(bundle);
                }
                try {
                    obtain.replyTo = this.lD;
                    this.lG.send(obtain);
                    this.lP = System.currentTimeMillis();
                    this.ly = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            synchronized (this.ln) {
                if (!(this.lI == null || this.lI.scanSpan < 1000 || this.lA)) {
                    if (this.lp == null) {
                        this.lp = new b();
                    }
                    this.lk.postDelayed(this.lp, (long) this.lI.scanSpan);
                    this.lA = true;
                }
            }
        }
    }

    private void e(Message message) {
        this.lW = false;
        if (message != null && message.obj != null) {
            LocationClientOption locationClientOption = (LocationClientOption) message.obj;
            if (!this.lI.equals(locationClientOption)) {
                if (this.lI.scanSpan != locationClientOption.scanSpan) {
                    try {
                        synchronized (this.ln) {
                            if (this.lA) {
                                this.lk.removeCallbacks(this.lp);
                                this.lA = false;
                            }
                            if (locationClientOption.scanSpan >= 1000 && !this.lA) {
                                if (this.lp == null) {
                                    this.lp = new b();
                                }
                                this.lk.postDelayed(this.lp, (long) locationClientOption.scanSpan);
                                this.lA = true;
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                this.lI = new LocationClientOption(locationClientOption);
                if (this.lG != null) {
                    try {
                        Message obtain = Message.obtain(null, 15);
                        obtain.replyTo = this.lD;
                        obtain.setData(dI());
                        this.lG.send(obtain);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    public static BDLocation getBDLocationInCoorType(BDLocation bDLocation, String str) {
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        double[] dArr = Jni.if(bDLocation.getLongitude(), bDLocation.getLatitude(), str);
        bDLocation2.setLatitude(dArr[1]);
        bDLocation2.setLongitude(dArr[0]);
        return bDLocation2;
    }

    private void h(Message message) {
        Bundle data = message.getData();
        data.setClassLoader(BDLocation.class.getClassLoader());
        BDLocation bDLocation = (BDLocation) data.getParcelable("locStr");
        if (this.lx == null) {
            return;
        }
        if (this.lI == null || !this.lI.isDisableCache() || bDLocation.getLocType() != 65) {
            this.lx.onReceiveLocation(bDLocation);
        }
    }

    private void i(Message message) {
        if (message != null && message.obj != null) {
            BDNotifyListener bDNotifyListener = (BDNotifyListener) message.obj;
            if (this.lr != null) {
                this.lr.for(bDNotifyListener);
            }
        }
    }

    private void if(Message message, int i) {
        if (this.lO) {
            Bundle data = message.getData();
            data.setClassLoader(BDLocation.class.getClassLoader());
            this.lT = (BDLocation) data.getParcelable("locStr");
            if (this.lT.getLocType() == 61) {
                this.lH = System.currentTimeMillis();
            }
            k(i);
        }
    }

    private void j(Message message) {
        if (message != null && message.obj != null) {
            BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
            if (this.l1 == null) {
                this.l1 = new ArrayList();
            }
            if (!this.l1.contains(bDLocationListener)) {
                this.l1.add(bDLocationListener);
            }
        }
    }

    private void k(int i) {
        if (this.lT.getCoorType() == null) {
            this.lT.setCoorType(this.lI.coorType);
        }
        if (this.ly || ((this.lI.location_change_notify && this.lT.getLocType() == 61) || this.lT.getLocType() == 66 || this.lT.getLocType() == 67 || this.lu || this.lT.getLocType() == BDLocation.TypeNetWorkLocation)) {
            if (this.l1 != null) {
                Iterator it = this.l1.iterator();
                while (it.hasNext()) {
                    ((BDLocationListener) it.next()).onReceiveLocation(this.lT);
                }
            }
            if (this.lT.getLocType() != 66 && this.lT.getLocType() != 67) {
                this.ly = false;
                this.lQ = System.currentTimeMillis();
            }
        }
    }

    private void k(Message message) {
        if (message != null && message.obj != null) {
            this.lx = (BDLocationListener) message.obj;
        }
    }

    private void void(Message message) {
        if (message != null && message.obj != null) {
            BDNotifyListener bDNotifyListener = (BDNotifyListener) message.obj;
            if (this.lr == null) {
                this.lr = new com.baidu.location.d.a(this.lE, this);
            }
            this.lr.do(bDNotifyListener);
        }
    }

    public String getAccessKey() {
        try {
            this.lS = j.a(this.lE);
            if (TextUtils.isEmpty(this.lS)) {
                throw new IllegalStateException("please setting key from Manifest.xml");
            }
            return String.format("KEY=%s;SHA1=%s", new Object[]{this.lS, j.if(this.lE)});
        } catch (Exception e) {
            return null;
        }
    }

    public BDLocation getLastKnownLocation() {
        return this.lT;
    }

    public LocationClientOption getLocOption() {
        return this.lI;
    }

    public String getVersion() {
        return f.bg;
    }

    public boolean isStarted() {
        return this.lO;
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        if ((!this.lm || this.lF) && bDLocation != null) {
            Message obtainMessage = this.lk.obtainMessage(g.I);
            obtainMessage.obj = bDLocation;
            obtainMessage.sendToTarget();
        }
    }

    public void registerLocationListener(BDLocationListener bDLocationListener) {
        if (bDLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message obtainMessage = this.lk.obtainMessage(5);
        obtainMessage.obj = bDLocationListener;
        obtainMessage.sendToTarget();
    }

    public void registerNotify(BDNotifyListener bDNotifyListener) {
        Message obtainMessage = this.lk.obtainMessage(9);
        obtainMessage.obj = bDNotifyListener;
        obtainMessage.sendToTarget();
    }

    public void registerNotifyLocationListener(BDLocationListener bDLocationListener) {
        Message obtainMessage = this.lk.obtainMessage(8);
        obtainMessage.obj = bDLocationListener;
        obtainMessage.sendToTarget();
    }

    public void removeNotifyEvent(BDNotifyListener bDNotifyListener) {
        Message obtainMessage = this.lk.obtainMessage(10);
        obtainMessage.obj = bDNotifyListener;
        obtainMessage.sendToTarget();
    }

    public int requestLocation() {
        if (this.lG == null || this.lD == null) {
            return 1;
        }
        if (this.l1 == null || this.l1.size() < 1) {
            return 2;
        }
        if (System.currentTimeMillis() - this.lP < 1000) {
            return 6;
        }
        Message obtainMessage = this.lk.obtainMessage(4);
        obtainMessage.arg1 = 0;
        obtainMessage.sendToTarget();
        return 0;
    }

    public void requestNotifyLocation() {
        this.lk.obtainMessage(11).sendToTarget();
    }

    public int requestOfflineLocation() {
        if (this.lG == null || this.lD == null) {
            return 1;
        }
        if (this.l1 == null || this.l1.size() < 1) {
            return 2;
        }
        this.lk.obtainMessage(12).sendToTarget();
        return 0;
    }

    public void setLocOption(LocationClientOption locationClientOption) {
        if (locationClientOption == null) {
            locationClientOption = new LocationClientOption();
        }
        if (this.l0 == null) {
            this.l0 = new p(this.lE, locationClientOption, this);
            this.l0.cs();
        }
        Message obtainMessage = this.lk.obtainMessage(3);
        obtainMessage.obj = locationClientOption;
        obtainMessage.sendToTarget();
    }

    public void start() {
        this.lZ = false;
        this.lk.obtainMessage(1).sendToTarget();
    }

    public void stop() {
        this.lZ = true;
        this.lk.obtainMessage(2).sendToTarget();
        this.l0 = null;
    }

    public void unRegisterLocationListener(BDLocationListener bDLocationListener) {
        if (bDLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message obtainMessage = this.lk.obtainMessage(6);
        obtainMessage.obj = bDLocationListener;
        obtainMessage.sendToTarget();
    }

    public boolean updateLocation(Location location) {
        if (this.lG == null || this.lD == null || location == null) {
            return false;
        }
        try {
            Message obtain = Message.obtain(null, 57);
            obtain.obj = location;
            this.lG.send(obtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
