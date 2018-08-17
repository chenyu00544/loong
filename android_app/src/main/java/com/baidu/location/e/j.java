package com.baidu.location.e;

import android.os.Bundle;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.d;
import com.baidu.location.b.f;
import com.baidu.location.b.g;
import com.baidu.location.b.k;
import com.baidu.location.b.m;
import com.baidu.location.c.d.b;
import com.baidu.location.h.c;
import com.baidu.location.h.h;
import com.baidu.location.h.l;
import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

class j implements f {
    private static j hH;
    private h hE = null;
    private com.baidu.location.h.f hF = null;
    private long hG = 0;
    private String hI = null;
    private final long hJ = 1000;

    class a extends m {
        final /* synthetic */ j eL;
        private String eM;

        a(j jVar) {
            this.eL = jVar;
            this.eM = null;
            this.c7 = new ArrayList();
        }

        public void au() {
            this.dg = 1;
            this.c5 = k.Z();
            String S = d.U().S();
            if (c8 == g.for || c8 == g.o) {
                this.c5 = "http://" + S + "/sdk.php";
            }
            S = Jni.H(this.eM);
            this.eM = null;
            this.c7.add(new BasicNameValuePair("bloc", S));
        }

        public void d(String str) {
            this.eM = str;
            ao();
        }

        public void int(boolean z) {
            if (!z || this.c6 == null) {
                boolean z2 = k.bX;
                k.bX = true;
                if (com.baidu.location.c.d.try().long() && com.baidu.location.c.d.try().e()) {
                    BDLocation bDLocation = com.baidu.location.c.d.try().if(c.a().cN(), l.a().c7(), null, com.baidu.location.c.d.c.IS_NOT_MIX_MODE, b.NEED_TO_LOG);
                    if (bDLocation == null || bDLocation.getLocType() == 67) {
                        this.eL.t(null);
                    } else {
                        this.eL.t(bDLocation.getLocationDescribe());
                    }
                } else {
                    this.eL.t(null);
                }
                if (!z2) {
                    k.bX = false;
                }
            } else {
                try {
                    this.eL.t(new BDLocation(EntityUtils.toString(this.c6, "utf-8")).getLocationDescribe());
                } catch (Exception e) {
                    this.eL.t(null);
                }
            }
            if (this.c7 != null) {
                this.c7.clear();
            }
        }
    }

    private j() {
    }

    public static synchronized j b2() {
        j jVar;
        synchronized (j.class) {
            if (hH == null) {
                hH = new j();
            }
            jVar = hH;
        }
        return jVar;
    }

    private boolean for(com.baidu.location.h.f fVar) {
        com.baidu.location.h.f dd = l.a().dd();
        return fVar == dd ? false : dd == null || fVar == null || !fVar.try(dd);
    }

    private boolean for(h hVar) {
        h cN = c.a().cN();
        return cN == hVar ? false : cN == null || hVar == null || !hVar.case(cN);
    }

    private void t(String str) {
        this.hI = str;
        Bundle bundle = new Bundle();
        if (str == null || str.equals("")) {
            bundle.putByteArray("locationtag", null);
        } else {
            bundle.putByteArray("locationtag", str.getBytes());
        }
        c.bq().if(bundle, (int) g.x);
    }

    public void b1() {
        if (System.currentTimeMillis() - this.hG >= 1000 || this.hI == null) {
            this.hG = System.currentTimeMillis();
            boolean z = for(this.hF);
            boolean z2 = for(this.hE);
            if (z || z2 || this.hI == null) {
                this.hE = c.a().cN();
                this.hF = l.a().dd();
                StringBuffer stringBuffer = new StringBuffer(1024);
                if (this.hE != null && this.hE.dt()) {
                    stringBuffer.append(this.hE.dy());
                }
                if (this.hF != null && this.hF.dm() > 1) {
                    stringBuffer.append(this.hF.e(15));
                }
                String cH = com.baidu.location.h.d.a().cH();
                if (cH != null) {
                    stringBuffer.append(cH);
                }
                stringBuffer.append("&sema=aptag");
                stringBuffer.append(com.baidu.location.b.c.N().do(false));
                stringBuffer.append(c.bq().bt());
                new a(this).d(stringBuffer.toString());
                return;
            }
            t(this.hI);
            return;
        }
        t(this.hI);
    }
}
