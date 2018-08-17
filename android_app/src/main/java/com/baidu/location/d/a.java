package com.baidu.location.d;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.v4.widget.AutoScrollHelper;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.Jni;
import com.baidu.location.LocationClient;
import com.baidu.location.b.f;
import com.baidu.location.h.e;
import com.baidu.platform.comapi.location.CoordinateType;
import java.util.ArrayList;
import java.util.Iterator;

public class a implements f {
    public static final String fB = "android.com.baidu.location.TIMER.NOTIFY";
    private a fA = new a(this);
    private PendingIntent fC = null;
    private ArrayList fD = null;
    private BDLocation fE = null;
    private long fF = 0;
    private b fG = null;
    private float fH = AutoScrollHelper.NO_MAX;
    private boolean fI = false;
    private long fJ = 0;
    private boolean fK = false;
    private LocationClient fL = null;
    private int fx = 0;
    private Context fy = null;
    private AlarmManager fz = null;

    public class a implements BDLocationListener {
        final /* synthetic */ a a;

        public a(a aVar) {
            this.a = aVar;
        }

        public void onReceiveLocation(BDLocation bDLocation) {
            if (this.a.fD != null && this.a.fD.size() > 0) {
                this.a.for(bDLocation);
            }
        }
    }

    public class b extends BroadcastReceiver {
        final /* synthetic */ a a;

        public b(a aVar) {
            this.a = aVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (this.a.fD != null && !this.a.fD.isEmpty()) {
                this.a.fL.requestNotifyLocation();
            }
        }
    }

    public a(Context context, LocationClient locationClient) {
        this.fy = context;
        this.fL = locationClient;
        this.fL.registerNotifyLocationListener(this.fA);
        this.fz = (AlarmManager) this.fy.getSystemService("alarm");
        this.fG = new b(this);
        this.fK = false;
    }

    private void a2() {
        int i = 10000;
        if (a4()) {
            boolean z;
            int i2 = this.fH > 5000.0f ? 600000 : this.fH > 1000.0f ? 120000 : this.fH > 500.0f ? 60000 : 10000;
            if (this.fI) {
                this.fI = false;
            } else {
                i = i2;
            }
            if (this.fx != 0) {
                if (((long) i) > (this.fF + ((long) this.fx)) - System.currentTimeMillis()) {
                    z = false;
                    if (z) {
                        this.fx = i;
                        this.fF = System.currentTimeMillis();
                        new((long) this.fx);
                    }
                }
            }
            z = true;
            if (z) {
                this.fx = i;
                this.fF = System.currentTimeMillis();
                new((long) this.fx);
            }
        }
    }

    private boolean a4() {
        if (this.fD == null || this.fD.isEmpty()) {
            return false;
        }
        Iterator it = this.fD.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z = ((BDNotifyListener) it.next()).Notified < 3 ? true : z;
        }
        return z;
    }

    private void for(BDLocation bDLocation) {
        if (bDLocation.getLocType() != 61 && bDLocation.getLocType() != BDLocation.TypeNetWorkLocation && bDLocation.getLocType() != 65) {
            new(120000);
        } else if (System.currentTimeMillis() - this.fJ >= e.kg && this.fD != null) {
            this.fE = bDLocation;
            this.fJ = System.currentTimeMillis();
            float[] fArr = new float[1];
            Iterator it = this.fD.iterator();
            float f = AutoScrollHelper.NO_MAX;
            while (it.hasNext()) {
                BDNotifyListener bDNotifyListener = (BDNotifyListener) it.next();
                Location.distanceBetween(bDLocation.getLatitude(), bDLocation.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
                float radius = (fArr[0] - bDNotifyListener.mRadius) - bDLocation.getRadius();
                if (radius > 0.0f) {
                    if (radius < f) {
                    }
                    radius = f;
                } else {
                    if (bDNotifyListener.Notified < 3) {
                        bDNotifyListener.Notified++;
                        bDNotifyListener.onNotify(bDLocation, fArr[0]);
                        if (bDNotifyListener.Notified < 3) {
                            this.fI = true;
                        }
                    }
                    radius = f;
                }
                f = radius;
            }
            if (f < this.fH) {
                this.fH = f;
            }
            this.fx = 0;
            a2();
        }
    }

    private void new(long j) {
        try {
            if (this.fC != null) {
                this.fz.cancel(this.fC);
            }
            this.fC = PendingIntent.getBroadcast(this.fy, 0, new Intent(fB), 134217728);
            if (this.fC != null) {
                this.fz.set(0, System.currentTimeMillis() + j, this.fC);
            }
        } catch (Exception e) {
        }
    }

    public void a3() {
        if (this.fC != null) {
            this.fz.cancel(this.fC);
        }
        this.fE = null;
        this.fJ = 0;
        if (this.fK) {
            this.fy.unregisterReceiver(this.fG);
        }
        this.fK = false;
    }

    public int do(BDNotifyListener bDNotifyListener) {
        if (this.fD == null) {
            this.fD = new ArrayList();
        }
        this.fD.add(bDNotifyListener);
        bDNotifyListener.isAdded = true;
        bDNotifyListener.mNotifyCache = this;
        if (!this.fK) {
            this.fy.registerReceiver(this.fG, new IntentFilter(fB));
            this.fK = true;
        }
        if (bDNotifyListener.mCoorType != null) {
            if (!bDNotifyListener.mCoorType.equals(CoordinateType.GCJ02)) {
                double[] dArr = Jni.if(bDNotifyListener.mLongitude, bDNotifyListener.mLatitude, bDNotifyListener.mCoorType + "2gcj");
                bDNotifyListener.mLongitudeC = dArr[0];
                bDNotifyListener.mLatitudeC = dArr[1];
            }
            if (this.fE == null || System.currentTimeMillis() - this.fJ > StatisticConfig.MIN_UPLOAD_INTERVAL) {
                this.fL.requestNotifyLocation();
            } else {
                float[] fArr = new float[1];
                Location.distanceBetween(this.fE.getLatitude(), this.fE.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
                float radius = (fArr[0] - bDNotifyListener.mRadius) - this.fE.getRadius();
                if (radius > 0.0f) {
                    if (radius < this.fH) {
                        this.fH = radius;
                    }
                } else if (bDNotifyListener.Notified < 3) {
                    bDNotifyListener.Notified++;
                    bDNotifyListener.onNotify(this.fE, fArr[0]);
                    if (bDNotifyListener.Notified < 3) {
                        this.fI = true;
                    }
                }
            }
            a2();
        }
        return 1;
    }

    public int for(BDNotifyListener bDNotifyListener) {
        if (this.fD == null) {
            return 0;
        }
        if (this.fD.contains(bDNotifyListener)) {
            this.fD.remove(bDNotifyListener);
        }
        if (this.fD.size() == 0 && this.fC != null) {
            this.fz.cancel(this.fC);
        }
        return 1;
    }

    public void if(BDNotifyListener bDNotifyListener) {
        if (bDNotifyListener.mCoorType != null) {
            if (!bDNotifyListener.mCoorType.equals(CoordinateType.GCJ02)) {
                double[] dArr = Jni.if(bDNotifyListener.mLongitude, bDNotifyListener.mLatitude, bDNotifyListener.mCoorType + "2gcj");
                bDNotifyListener.mLongitudeC = dArr[0];
                bDNotifyListener.mLatitudeC = dArr[1];
            }
            if (this.fE == null || System.currentTimeMillis() - this.fJ > 300000) {
                this.fL.requestNotifyLocation();
            } else {
                float[] fArr = new float[1];
                Location.distanceBetween(this.fE.getLatitude(), this.fE.getLongitude(), bDNotifyListener.mLatitudeC, bDNotifyListener.mLongitudeC, fArr);
                float radius = (fArr[0] - bDNotifyListener.mRadius) - this.fE.getRadius();
                if (radius > 0.0f) {
                    if (radius < this.fH) {
                        this.fH = radius;
                    }
                } else if (bDNotifyListener.Notified < 3) {
                    bDNotifyListener.Notified++;
                    bDNotifyListener.onNotify(this.fE, fArr[0]);
                    if (bDNotifyListener.Notified < 3) {
                        this.fI = true;
                    }
                }
            }
            a2();
        }
    }
}
