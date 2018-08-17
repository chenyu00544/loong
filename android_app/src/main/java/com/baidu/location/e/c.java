package com.baidu.location.e;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.Messenger;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.h.d;
import com.baidu.platform.comapi.location.CoordinateType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c implements f {
    private static c gn = null;
    private boolean gl;
    private ArrayList gm;
    public boolean go;
    private boolean gp;

    private class a {
        final /* synthetic */ c a;
        public LocationClientOption do = new LocationClientOption();
        public Messenger for = null;
        public int if = 0;
        public String int = null;

        public a(c cVar, Message message) {
            boolean z = false;
            this.a = cVar;
            this.for = message.replyTo;
            this.int = message.getData().getString("packName");
            this.do.prodName = message.getData().getString("prodName");
            com.baidu.location.b.c.N().if(this.do.prodName, this.int);
            this.do.coorType = message.getData().getString("coorType");
            this.do.addrType = message.getData().getString("addrType");
            this.do.enableSimulateGps = message.getData().getBoolean("enableSimulateGps", false);
            boolean z2 = k.cj || this.do.enableSimulateGps;
            k.cj = z2;
            if (!k.cf.equals("all")) {
                k.cf = this.do.addrType;
            }
            this.do.openGps = message.getData().getBoolean("openGPS");
            this.do.scanSpan = message.getData().getInt("scanSpan");
            this.do.timeOut = message.getData().getInt("timeOut");
            this.do.priority = message.getData().getInt("priority");
            this.do.location_change_notify = message.getData().getBoolean("location_change_notify");
            this.do.mIsNeedDeviceDirect = message.getData().getBoolean("needDirect", false);
            this.do.isNeedAltitude = message.getData().getBoolean("isneedaltitude", false);
            z2 = k.bX || message.getData().getBoolean("isneedaptag", false);
            k.bX = z2;
            if (k.bP || message.getData().getBoolean("isneedaptagd", false)) {
                z = true;
            }
            k.bP = z;
            if (this.do.scanSpan >= 1000) {
                i.bX().bW();
            }
            if (this.do.mIsNeedDeviceDirect || this.do.isNeedAltitude) {
                l.cf().case(this.do.mIsNeedDeviceDirect);
                l.cf().char(this.do.isNeedAltitude);
                l.cf().cd();
            }
        }

        private void a(int i) {
            Message obtain = Message.obtain(null, i);
            try {
                if (this.for != null) {
                    this.for.send(obtain);
                }
                this.if = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.if++;
                }
            }
        }

        private void a(int i, Bundle bundle) {
            Message obtain = Message.obtain(null, i);
            obtain.setData(bundle);
            try {
                if (this.for != null) {
                    this.for.send(obtain);
                }
                this.if = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.if++;
                }
                e.printStackTrace();
            }
        }

        private void a(int i, String str, BDLocation bDLocation) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(str, bDLocation);
            Message obtain = Message.obtain(null, i);
            obtain.setData(bundle);
            try {
                if (this.for != null) {
                    this.for.send(obtain);
                }
                this.if = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.if++;
                }
            }
        }

        public void a() {
            if (!this.do.location_change_notify) {
                return;
            }
            if (k.cG) {
                a(54);
            } else {
                a(55);
            }
        }

        public void a(BDLocation bDLocation) {
            a(bDLocation, 21);
        }

        public void a(BDLocation bDLocation, int i) {
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (l.cf().cb() && (bDLocation2.getLocType() == BDLocation.TypeNetWorkLocation || bDLocation2.getLocType() == 66)) {
                bDLocation2.setAltitude(l.cf().ce());
            }
            if (i == 21) {
                a(27, "locStr", bDLocation2);
            }
            if (!(this.do.coorType == null || this.do.coorType.equals(CoordinateType.GCJ02))) {
                double longitude = bDLocation2.getLongitude();
                double latitude = bDLocation2.getLatitude();
                if (!(longitude == Double.MIN_VALUE || latitude == Double.MIN_VALUE)) {
                    double[] dArr;
                    if ((bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals(CoordinateType.GCJ02)) || bDLocation2.getCoorType() == null) {
                        dArr = Jni.if(longitude, latitude, this.do.coorType);
                        bDLocation2.setLongitude(dArr[0]);
                        bDLocation2.setLatitude(dArr[1]);
                        bDLocation2.setCoorType(this.do.coorType);
                    } else if (!(bDLocation2.getCoorType() == null || !bDLocation2.getCoorType().equals(CoordinateType.WGS84) || this.do.coorType.equals("bd09ll"))) {
                        dArr = Jni.if(longitude, latitude, "wgs842mc");
                        bDLocation2.setLongitude(dArr[0]);
                        bDLocation2.setLatitude(dArr[1]);
                        bDLocation2.setCoorType("wgs84mc");
                    }
                }
            }
            a(i, "locStr", bDLocation2);
        }

        public void do() {
            a(111);
        }

        public void if() {
            a(23);
        }

        public void if(BDLocation bDLocation) {
            if (this.do.location_change_notify) {
                a(bDLocation);
            }
        }
    }

    private c() {
        this.gm = null;
        this.gp = false;
        this.gl = false;
        this.go = true;
        this.gm = new ArrayList();
    }

    private void bm() {
        bp();
        br();
    }

    private void bp() {
        Iterator it = this.gm.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.do.openGps) {
                z2 = true;
            }
            z = aVar.do.location_change_notify ? true : z;
        }
        k.cm = z;
        if (this.gp != z2) {
            this.gp = z2;
            d.a().else(this.gp);
        }
    }

    public static c bq() {
        if (gn == null) {
            gn = new c();
        }
        return gn;
    }

    private a if(Messenger messenger) {
        if (this.gm == null) {
            return null;
        }
        Iterator it = this.gm.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.for.equals(messenger)) {
                return aVar;
            }
        }
        return null;
    }

    private void if(a aVar) {
        if (aVar != null) {
            if (if(aVar.for) != null) {
                aVar.a(14);
                return;
            }
            this.gm.add(aVar);
            aVar.a(13);
        }
    }

    public void bk() {
        Iterator it = this.gm.iterator();
        while (it.hasNext()) {
            ((a) it.next()).do();
        }
    }

    public void bl() {
        Iterator it = this.gm.iterator();
        while (it.hasNext()) {
            ((a) it.next()).if();
        }
    }

    public boolean bn() {
        return this.go;
    }

    public boolean bo() {
        return this.gp;
    }

    public void br() {
        Iterator it = this.gm.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
        }
    }

    public void bs() {
        this.gm.clear();
        bm();
    }

    public String bt() {
        StringBuffer stringBuffer = new StringBuffer(256);
        if (this.gm.isEmpty()) {
            return "&prod=" + com.baidu.location.b.c.bj + ":" + com.baidu.location.b.c.bn;
        }
        a aVar = (a) this.gm.get(0);
        if (aVar.do.prodName != null) {
            stringBuffer.append(aVar.do.prodName);
        }
        if (aVar.int != null) {
            stringBuffer.append(":");
            stringBuffer.append(aVar.int);
            stringBuffer.append("|");
        }
        String stringBuffer2 = stringBuffer.toString();
        return (stringBuffer2 == null || stringBuffer2.equals("")) ? null : "&prod=" + stringBuffer2;
    }

    public int case(Message message) {
        if (message == null || message.replyTo == null) {
            return 1000;
        }
        a aVar = if(message.replyTo);
        return (aVar == null || aVar.do == null) ? 1000 : aVar.do.scanSpan;
    }

    public void char(Message message) {
        a aVar = if(message.replyTo);
        if (aVar != null) {
            this.gm.remove(aVar);
        }
        i.bX().bZ();
        l.cf().cg();
        bm();
    }

    public void do(BDLocation bDLocation, int i) {
        Iterator it = this.gm.iterator();
        while (it.hasNext()) {
            try {
                a aVar = (a) it.next();
                aVar.a(bDLocation, i);
                if (aVar.if > 4) {
                    it.remove();
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    public int else(Message message) {
        if (message == null || message.replyTo == null) {
            return 1;
        }
        a aVar = if(message.replyTo);
        return (aVar == null || aVar.do == null) ? 1 : aVar.do.priority;
    }

    public boolean goto(Message message) {
        boolean z = true;
        a aVar = if(message.replyTo);
        if (aVar == null) {
            return false;
        }
        int i = aVar.do.scanSpan;
        aVar.do.scanSpan = message.getData().getInt("scanSpan", aVar.do.scanSpan);
        if (aVar.do.scanSpan < 1000) {
            i.bX().bU();
            l.cf().cg();
            this.go = false;
        } else {
            i.bX().bV();
            this.go = true;
        }
        if (aVar.do.scanSpan <= 999 || i >= 1000) {
            z = false;
        } else if (aVar.do.mIsNeedDeviceDirect || aVar.do.isNeedAltitude) {
            l.cf().case(aVar.do.mIsNeedDeviceDirect);
            l.cf().char(aVar.do.isNeedAltitude);
            l.cf().cd();
        }
        aVar.do.openGps = message.getData().getBoolean("openGPS", aVar.do.openGps);
        String string = message.getData().getString("coorType");
        LocationClientOption locationClientOption = aVar.do;
        if (string == null || string.equals("")) {
            string = aVar.do.coorType;
        }
        locationClientOption.coorType = string;
        string = message.getData().getString("addrType");
        locationClientOption = aVar.do;
        if (string == null || string.equals("")) {
            string = aVar.do.addrType;
        }
        locationClientOption.addrType = string;
        if (!k.cf.equals(aVar.do.addrType)) {
            m.ba().be();
        }
        aVar.do.timeOut = message.getData().getInt("timeOut", aVar.do.timeOut);
        aVar.do.location_change_notify = message.getData().getBoolean("location_change_notify", aVar.do.location_change_notify);
        aVar.do.priority = message.getData().getInt("priority", aVar.do.priority);
        bm();
        return z;
    }

    public void if(Bundle bundle, int i) {
        Iterator it = this.gm.iterator();
        while (it.hasNext()) {
            try {
                a aVar = (a) it.next();
                aVar.a(i, bundle);
                if (aVar.if > 4) {
                    it.remove();
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    public void if(BDLocation bDLocation, Message message) {
        if (bDLocation != null && message != null) {
            a aVar = if(message.replyTo);
            if (aVar != null) {
                aVar.a(bDLocation);
                if (aVar.if > 4) {
                    this.gm.remove(aVar);
                }
            }
        }
    }

    public void l(String str) {
        BDLocation bDLocation = new BDLocation(str);
        Address address = m.ba().new(bDLocation);
        String bd = m.ba().bd();
        List bf = m.ba().bf();
        if (address != null) {
            bDLocation.setAddr(address);
        }
        if (bd != null) {
            bDLocation.setLocationDescribe(bd);
        }
        if (bf != null) {
            bDLocation.setPoiList(bf);
        }
        Iterator it = this.gm.iterator();
        while (it.hasNext()) {
            ((a) it.next()).if(bDLocation);
        }
    }

    public void long(Message message) {
        if (message != null && message.replyTo != null) {
            if(new a(this, message));
            bm();
        }
    }

    public void try(BDLocation bDLocation) {
        Iterator it = this.gm.iterator();
        while (it.hasNext()) {
            try {
                a aVar = (a) it.next();
                aVar.a(bDLocation);
                if (aVar.if > 4) {
                    it.remove();
                }
            } catch (Exception e) {
                return;
            }
        }
    }
}
