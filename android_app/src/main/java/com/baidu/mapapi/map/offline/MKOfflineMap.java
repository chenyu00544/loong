package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.BMapManager;
import com.baidu.platform.comapi.map.s;
import com.baidu.platform.comapi.map.t;
import com.baidu.platform.comapi.map.w;
import java.util.ArrayList;
import java.util.Iterator;

public class MKOfflineMap {
    public static final int TYPE_DOWNLOAD_UPDATE = 0;
    public static final int TYPE_NETWORK_ERROR = 2;
    public static final int TYPE_NEW_OFFLINE = 6;
    public static final int TYPE_VER_UPDATE = 4;
    private static final String a = MKOfflineMap.class.getSimpleName();
    private t b;
    private MKOfflineMapListener c;

    public void destroy() {
        this.b.d(0);
        this.b.b(null);
        this.b.b();
        BMapManager.destroy();
    }

    public ArrayList<MKOLUpdateElement> getAllUpdateInfo() {
        ArrayList e = this.b.e();
        if (e == null) {
            return null;
        }
        ArrayList<MKOLUpdateElement> arrayList = new ArrayList();
        Iterator it = e.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getUpdatElementFromLocalMapElement(((w) it.next()).a()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getHotCityList() {
        ArrayList c = this.b.c();
        if (c == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((s) it.next()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getOfflineCityList() {
        ArrayList d = this.b.d();
        if (d == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList();
        Iterator it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((s) it.next()));
        }
        return arrayList;
    }

    public MKOLUpdateElement getUpdateInfo(int i) {
        w g = this.b.g(i);
        return g == null ? null : OfflineMapUtil.getUpdatElementFromLocalMapElement(g.a());
    }

    @Deprecated
    public int importOfflineData() {
        return importOfflineData(false);
    }

    @Deprecated
    public int importOfflineData(boolean z) {
        int i;
        int i2 = 0;
        ArrayList e = this.b.e();
        if (e != null) {
            i2 = e.size();
            i = i2;
        } else {
            i = 0;
        }
        this.b.a(z, true);
        ArrayList e2 = this.b.e();
        if (e2 != null) {
            i2 = e2.size();
        }
        return i2 - i;
    }

    public boolean init(MKOfflineMapListener mKOfflineMapListener) {
        BMapManager.init();
        this.b = t.a();
        if (this.b == null) {
            return false;
        }
        this.b.a(new a(this));
        this.c = mKOfflineMapListener;
        return true;
    }

    public boolean pause(int i) {
        return this.b.c(i);
    }

    public boolean remove(int i) {
        return this.b.e(i);
    }

    public ArrayList<MKOLSearchRecord> searchCity(String str) {
        ArrayList a = this.b.a(str);
        if (a == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((s) it.next()));
        }
        return arrayList;
    }

    public boolean start(int i) {
        if (this.b == null) {
            return false;
        }
        if (this.b.e() != null) {
            Iterator it = this.b.e().iterator();
            while (it.hasNext()) {
                w wVar = (w) it.next();
                if (wVar.a.a == i) {
                    return (wVar.a.j || wVar.a.l == 2 || wVar.a.l == 3 || wVar.a.l == 6) ? this.b.b(i) : false;
                }
            }
        }
        return this.b.a(i);
    }

    public boolean update(int i) {
        if (this.b == null) {
            return false;
        }
        if (this.b.e() != null) {
            Iterator it = this.b.e().iterator();
            while (it.hasNext()) {
                w wVar = (w) it.next();
                if (wVar.a.a == i) {
                    return wVar.a.j ? this.b.f(i) : false;
                }
            }
        }
        return false;
    }
}
