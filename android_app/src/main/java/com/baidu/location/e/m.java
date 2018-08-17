package com.baidu.location.e;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.b.o;
import com.baidu.location.c.d;
import com.baidu.location.h.c;
import com.baidu.location.h.e;
import com.baidu.location.h.h;
import com.baidu.location.h.l;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import java.util.List;

public class m extends b implements f {
    private static m fW = null;
    private String f0;
    private h f1;
    private List f2;
    private long f3;
    private long f4;
    private h f5;
    private boolean f6;
    private double f7;
    private com.baidu.location.h.f f8;
    private boolean f9;
    public final Handler fP;
    final int fS;
    public a fT;
    private volatile boolean fU;
    private boolean fV;
    private long fX;
    private boolean fY;
    private b fZ;
    private double ga;
    private long gb;
    private boolean gc;
    private boolean gd;
    private boolean ge;
    private Address gf;
    private BDLocation gg;
    private BDLocation gh;
    final int gi;
    private String gj;
    private com.baidu.location.h.f gk;

    class m_1 extends Thread {
        final /* synthetic */ m a;

        m_1(m mVar) {
            this.a = mVar;
        }

        public void run() {
            super.run();
            d.try().k();
        }
    }

    private class a implements Runnable {
        final /* synthetic */ m a;

        private a(m mVar) {
            this.a = mVar;
        }

        public void run() {
            if (this.a.f9) {
                this.a.f9 = false;
                this.a.new(null);
            }
        }
    }

    private class b implements Runnable {
        final /* synthetic */ m a;

        private b(m mVar) {
            this.a = mVar;
        }

        public void run() {
            if (this.a.f6) {
                this.a.f6 = false;
                if (!this.a.fY) {
                    this.a.bc();
                }
            }
        }
    }

    private m() {
        this.gi = m_AppUI.MSG_APP_DATA_OK;
        this.fS = 1000;
        this.gc = true;
        this.fT = null;
        this.gj = null;
        this.gg = null;
        this.gh = null;
        this.f8 = null;
        this.f5 = null;
        this.gk = null;
        this.f1 = null;
        this.gd = true;
        this.fU = false;
        this.f9 = false;
        this.f3 = 0;
        this.f4 = 0;
        this.gf = null;
        this.f0 = null;
        this.f2 = null;
        this.fV = false;
        this.gb = 0;
        this.fX = 0;
        this.fZ = null;
        this.f6 = false;
        this.fY = false;
        this.ge = true;
        this.fP = new com.baidu.location.e.b.b(this);
        this.fT = new a(this);
    }

    private boolean a7() {
        return true;
    }

    private void a8() {
        this.fU = false;
        this.fY = false;
        this.ge = false;
        this.fV = false;
        bj();
    }

    public static m ba() {
        if (fW == null) {
            fW = new m();
        }
        return fW;
    }

    private boolean bh() {
        BDLocation bDLocation = null;
        double random = Math.random();
        long uptimeMillis = SystemClock.uptimeMillis();
        h cN = c.a().cN();
        com.baidu.location.h.f c7 = l.a().c7();
        boolean z = cN != null && cN.dz() && (c7 == null || c7.dm() == 0);
        if (d.try().long() && d.try().case() && (z || (0.0d < random && random < d.try().byte()))) {
            bDLocation = d.try().if(c.a().cN(), l.a().c7(), null, d.c.IS_MIX_MODE, com.baidu.location.c.d.b.NEED_TO_LOG);
        }
        if (bDLocation == null || bDLocation.getLocType() != 66 || !this.fU) {
            return false;
        }
        com.baidu.location.b.l lVar = new com.baidu.location.b.l();
        lVar.do(this.fX);
        lVar.if(uptimeMillis);
        lVar.for(uptimeMillis);
        lVar.int(SystemClock.uptimeMillis());
        lVar.char(com.baidu.location.b.l.cZ);
        if (this.f5 != null) {
            lVar.else(this.f5.dy());
            lVar.else("&offtag=1");
        }
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        bDLocation2.setLocType(BDLocation.TypeNetWorkLocation);
        if (!this.fU) {
            return false;
        }
        o.aX().if(lVar);
        this.fY = true;
        c.bq().try(bDLocation2);
        this.gg = bDLocation2;
        return true;
    }

    private void bj() {
        if (this.gg != null) {
            o.cn().cp();
        }
    }

    private void byte(Message message) {
        if (com.baidu.location.h.d.a().cI()) {
            for(message);
        } else {
            int(message);
        }
    }

    private boolean do(com.baidu.location.h.f fVar) {
        this.fR = l.a().dd();
        return fVar == this.fR ? false : this.fR == null || fVar == null || !fVar.try(this.fR);
    }

    private boolean do(h hVar) {
        this.fQ = c.a().cN();
        return this.fQ == hVar ? false : this.fQ == null || hVar == null || !hVar.case(this.fQ);
    }

    private void for(Message message) {
        BDLocation bDLocation = new BDLocation(com.baidu.location.h.d.a().cE());
        if (k.cf.equals("all") || k.bX || k.bP) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.ga, this.f7, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] < 100.0f) {
                if (this.gf != null) {
                    bDLocation.setAddr(this.gf);
                }
                if (this.f0 != null) {
                    bDLocation.setLocationDescribe(this.f0);
                }
                if (this.f2 != null) {
                    bDLocation.setPoiList(this.f2);
                }
            } else {
                this.fV = true;
                int(null);
            }
        }
        this.gg = bDLocation;
        this.gh = null;
        c.bq().if(bDLocation, message);
    }

    private boolean if(com.baidu.location.h.f fVar) {
        return fVar == null ? false : this.gk == null || !e.if(fVar, this.gk, 0.1f);
    }

    private boolean if(h hVar) {
        return hVar == null ? false : this.f1 == null || !hVar.case(this.f1);
    }

    private void int(Message message) {
        if (this.gd) {
            this.fX = SystemClock.uptimeMillis();
            new(message);
        } else if (!this.fU) {
            this.fX = SystemClock.uptimeMillis();
            if (l.a().c4()) {
                this.f9 = true;
                this.fP.postDelayed(new a(), 2000);
                return;
            }
            new(message);
        }
    }

    private void new(Message message) {
        if (!this.fU) {
            if (System.currentTimeMillis() - this.f3 >= 1000 || this.gg == null) {
                if (this.fX > 0) {
                    o.aX().aU().do(this.fX);
                } else {
                    o.aX().aU().do(SystemClock.uptimeMillis());
                }
                this.fU = true;
                this.gc = do(this.f5);
                if (do(this.f8) || this.gc || this.gg == null || this.fV) {
                    String k = k(null);
                    if (k != null) {
                        if (this.gj != null) {
                            k = k + this.gj;
                            this.gj = null;
                        }
                        o.aX().aU().if(SystemClock.uptimeMillis());
                        this.fT.long(k);
                        this.f5 = this.fQ;
                        this.f8 = this.fR;
                        if (this.f5 == null || this.f5.du() != 0) {
                            if (bh()) {
                                this.f5 = this.fQ;
                                this.f8 = this.fR;
                            }
                            if (d.try().i()) {
                                if (this.fZ == null) {
                                    this.fZ = new b();
                                }
                                this.fP.postDelayed(this.fZ, d.try().if(c.a(c.a().cQ())));
                                this.f6 = true;
                            }
                        } else {
                            new com.baidu.location.g.b(this.f5, this.f8, true).cA();
                            a8();
                        }
                        if (this.gd) {
                            this.gd = false;
                            if (l.a().da() && message != null && c.bq().case(message) < 1000 && d.try().long()) {
                                new m_1(this).start();
                            }
                        }
                        this.f3 = System.currentTimeMillis();
                        return;
                    } else if (this.gg != null) {
                        c.bq().try(this.gg);
                        a8();
                        return;
                    } else {
                        BDLocation bDLocation = new BDLocation();
                        bDLocation.setLocType(62);
                        c.bq().try(bDLocation);
                        a8();
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.gb > 60000) {
                            this.gb = currentTimeMillis;
                            o.aX().j("TypeCriteriaException");
                            return;
                        }
                        return;
                    }
                }
                if (this.gh != null && System.currentTimeMillis() - this.f4 > StatisticConfig.MIN_UPLOAD_INTERVAL) {
                    this.gg = this.gh;
                    this.gh = null;
                }
                if (l.cf().cc()) {
                    this.gg.setDirection(l.cf().ch());
                }
                c.bq().try(this.gg);
                a8();
                return;
            }
            c.bq().try(this.gg);
            a8();
        }
    }

    private void try(Message message) {
        if (message.getData().getBoolean("isWaitingLocTag", false)) {
            j.b2().b1();
        }
        switch (c.bq().else(message)) {
            case 1:
                byte(message);
                return;
            case 2:
                int(message);
                return;
            case 3:
                if (com.baidu.location.h.d.a().cI()) {
                    for(message);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException(String.format("this type %d is illegal", new Object[]{Integer.valueOf(c.bq().else(message))}));
        }
    }

    public void a5() {
        boolean z;
        boolean z2 = true;
        if (this.fZ == null || !this.f6) {
            z = false;
        } else {
            this.f6 = false;
            this.fP.removeCallbacks(this.fZ);
            z = true;
        }
        if (com.baidu.location.h.d.a().cI()) {
            BDLocation bDLocation = new BDLocation(com.baidu.location.h.d.a().cE());
            if (k.cf.equals("all") || k.bX || k.bP) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.ga, this.f7, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    if (this.gf != null) {
                        bDLocation.setAddr(this.gf);
                    }
                    if (this.f0 != null) {
                        bDLocation.setLocationDescribe(this.f0);
                    }
                    if (this.f2 != null) {
                        bDLocation.setPoiList(this.f2);
                    }
                }
            }
            c.bq().do(bDLocation, 21);
            a8();
        } else if (this.fY) {
            a8();
        } else {
            o.aX().aU().for(SystemClock.uptimeMillis());
            BDLocation bDLocation2;
            if (z) {
                if (d.try().long() && d.try().e()) {
                    bDLocation2 = d.try().if(c.a().cN(), l.a().c7(), null, d.c.IS_NOT_MIX_MODE, com.baidu.location.c.d.b.NEED_TO_LOG);
                    if (bDLocation2 != null && bDLocation2.getLocType() == 66) {
                        c.bq().do(bDLocation2, 21);
                    }
                } else {
                    bDLocation2 = null;
                }
                if (bDLocation2 == null || bDLocation2.getLocType() == 67) {
                    if (this.gc || this.gg == null) {
                        bDLocation2 = com.baidu.location.c.b.aZ().byte(false);
                        c.bq().do(bDLocation2, 21);
                        if (k.cf.equals("all") && bDLocation2.getAddrStr() == null) {
                            z2 = false;
                        }
                        if (k.bX && bDLocation2.getLocationDescribe() == null) {
                            z2 = false;
                        }
                        if (k.bP && bDLocation2.getPoiList() == null) {
                            z2 = false;
                        }
                        if (!z2) {
                            bDLocation2.setLocType(67);
                        }
                    } else {
                        c.bq().do(this.gg, 21);
                    }
                }
                o.aX().aU().int(SystemClock.uptimeMillis());
                if (bDLocation2 == null || bDLocation2.getLocType() == 67) {
                    this.gg = null;
                    o.aX().aU().char(com.baidu.location.b.l.cW);
                    if (this.f5 != null) {
                        o.aX().aU().else(this.f5.dy());
                    }
                    o.aX().aW();
                } else {
                    this.gg = bDLocation2;
                    o.aX().aU().char(com.baidu.location.b.l.cZ);
                    if (this.f5 != null) {
                        o.aX().aU().else(this.f5.dy());
                    }
                    o.aX().aW();
                }
            } else {
                bDLocation2 = new BDLocation();
                bDLocation2.setLocType(63);
                this.gg = null;
                c.bq().do(bDLocation2, 21);
            }
            this.gh = null;
            a8();
        }
    }

    public BDLocation a6() {
        return this.gg;
    }

    public void a9() {
        if (this.f9) {
            new(null);
            this.f9 = false;
        }
    }

    public void bb() {
        this.fU = false;
        this.f9 = false;
        this.fY = false;
        this.ge = true;
        be();
    }

    public void bc() {
        BDLocation bDLocation;
        if (d.try().long() && d.try().goto()) {
            BDLocation bDLocation2 = d.try().if(c.a().cN(), l.a().c7(), null, d.c.IS_NOT_MIX_MODE, com.baidu.location.c.d.b.NEED_TO_LOG);
            bDLocation = (bDLocation2 == null || bDLocation2.getLocType() == 67) ? com.baidu.location.c.b.aZ().byte(false) : bDLocation2;
        } else {
            bDLocation = com.baidu.location.c.b.aZ().byte(false);
        }
        if (bDLocation != null && bDLocation.getLocType() == 66) {
            boolean z = true;
            if (k.cf.equals("all") && bDLocation.getAddrStr() == null) {
                z = false;
            }
            if (k.bX && bDLocation.getLocationDescribe() == null) {
                z = false;
            }
            if (k.bP && bDLocation.getPoiList() == null) {
                z = false;
            }
            if (z) {
                c.bq().do(bDLocation, 21);
            }
        }
    }

    public String bd() {
        return this.f0;
    }

    public void be() {
        this.gg = null;
    }

    public List bf() {
        return this.f2;
    }

    public boolean bg() {
        return this.gc;
    }

    public void bi() {
        this.gd = true;
        this.fU = false;
    }

    public void do(Message message) {
        try(message);
    }

    public void if(Message message) {
        if (this.fZ != null && this.f6) {
            this.f6 = false;
            this.fP.removeCallbacks(this.fZ);
        }
        BDLocation bDLocation = (BDLocation) message.obj;
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        if (bDLocation.hasAddr()) {
            this.gf = bDLocation.getAddress();
            this.f7 = bDLocation.getLongitude();
            this.ga = bDLocation.getLatitude();
        }
        if (bDLocation.getLocationDescribe() != null) {
            this.f0 = bDLocation.getLocationDescribe();
            this.f7 = bDLocation.getLongitude();
            this.ga = bDLocation.getLatitude();
        }
        if (bDLocation.getPoiList() != null) {
            this.f2 = bDLocation.getPoiList();
            this.f7 = bDLocation.getLongitude();
            this.ga = bDLocation.getLatitude();
        }
        float[] fArr;
        if (com.baidu.location.h.d.a().cI()) {
            bDLocation = new BDLocation(com.baidu.location.h.d.a().cE());
            if (k.cf.equals("all") || k.bX || k.bP) {
                fArr = new float[2];
                Location.distanceBetween(this.ga, this.f7, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    if (this.gf != null) {
                        bDLocation.setAddr(this.gf);
                    }
                    if (this.f0 != null) {
                        bDLocation.setLocationDescribe(this.f0);
                    }
                    if (this.f2 != null) {
                        bDLocation.setPoiList(this.f2);
                    }
                }
            }
            c.bq().do(bDLocation, 21);
            a8();
        } else if (bDLocation.getNetworkLocationType() != null && bDLocation.getNetworkLocationType().equals("sky")) {
            bDLocation.setNetworkLocationType("wf");
            c.bq().do(bDLocation, 21);
            this.f4 = System.currentTimeMillis();
            this.gg = bDLocation;
        } else if (this.fY) {
            fArr = new float[2];
            if (this.gg != null) {
                Location.distanceBetween(this.gg.getLatitude(), this.gg.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            }
            if (fArr[0] > TitleBar.SHAREBTN_RIGHT_MARGIN) {
                this.gg = bDLocation;
                if (!this.ge) {
                    this.ge = false;
                    c.bq().do(bDLocation, 21);
                }
            }
            a8();
        } else {
            o.aX().aU().for(SystemClock.uptimeMillis());
            this.gh = null;
            if (!(this.f5 == null || this.f5.du() == 0 || ((bDLocation.getLocationWhere() != 2 && bDLocation.getLocationWhere() != 0) || bDLocation.getLocType() != BDLocation.TypeServerError))) {
                new com.baidu.location.g.b(this.f5, this.f8, true).cA();
            }
            if (bDLocation.getLocType() == BDLocation.TypeNetWorkLocation && k.cf.equals("all") && bDLocation.getAddrStr() == null && !com.baidu.location.b.h.W().for(bDLocation.getLongitude(), bDLocation.getLatitude())) {
                new com.baidu.location.g.b(this.f5, this.f8, true).cA();
                a8();
                return;
            }
            boolean z;
            if (bDLocation.getLocType() == BDLocation.TypeNetWorkLocation && "cl".equals(bDLocation.getNetworkLocationType()) && this.gg != null && this.gg.getLocType() == BDLocation.TypeNetWorkLocation && "wf".equals(this.gg.getNetworkLocationType()) && System.currentTimeMillis() - this.f4 < StatisticConfig.MIN_UPLOAD_INTERVAL) {
                this.gh = bDLocation;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                c.bq().do(this.gg, 21);
            } else {
                c.bq().do(bDLocation, 21);
                this.f4 = System.currentTimeMillis();
                o.aX().aU().int(SystemClock.uptimeMillis());
                if (bDLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                    o.aX().aU().char(com.baidu.location.b.l.c2);
                    if (this.f5 != null) {
                        o.aX().aU().else(this.f5.dy());
                    }
                } else {
                    o.aX().aU().char(com.baidu.location.b.l.cV);
                    if (this.f5 != null) {
                        o.aX().aU().else(this.f5.dy());
                    }
                    o.aX().aW();
                }
            }
            if (!k.do(bDLocation)) {
                this.gg = null;
            } else if (!z) {
                this.gg = bDLocation;
            }
            int i = k.for(fN, "ssid\":\"", "\"");
            if (i == Integer.MIN_VALUE || this.f8 == null) {
                this.gj = null;
            } else {
                this.gj = this.f8.j(i);
            }
            if (d.try().long() && bDLocation.getLocType() == BDLocation.TypeNetWorkLocation && "cl".equals(bDLocation.getNetworkLocationType()) && if(this.f5)) {
                d.try().if(this.f5, null, bDLocation2, d.c.IS_NOT_MIX_MODE, com.baidu.location.c.d.b.NO_NEED_TO_LOG);
                this.f1 = this.f5;
            }
            if (d.try().long() && bDLocation.getLocType() == BDLocation.TypeNetWorkLocation && "wf".equals(bDLocation.getNetworkLocationType())) {
                d.try().if(null, this.f8, bDLocation2, d.c.IS_NOT_MIX_MODE, com.baidu.location.c.d.b.NO_NEED_TO_LOG);
                this.gk = this.f8;
            }
            com.baidu.location.c.b.aZ().if(fN, this.f5, this.f8, bDLocation2);
            if (l.a().da()) {
                d.try().k();
            }
            a8();
        }
    }

    public void int(BDLocation bDLocation) {
        be();
        this.gg = bDLocation;
        this.gg.setIndoorLocMode(false);
    }

    public Address new(BDLocation bDLocation) {
        if (k.cf.equals("all") || k.bX || k.bP) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.ga, this.f7, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] >= 100.0f) {
                this.f0 = null;
                this.f2 = null;
                this.fV = true;
                int(null);
            } else if (this.gf != null) {
                return this.gf;
            }
        }
        return null;
    }
}
