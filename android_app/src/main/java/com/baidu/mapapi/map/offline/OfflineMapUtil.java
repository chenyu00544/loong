package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.platform.comapi.map.s;
import com.baidu.platform.comapi.map.v;
import java.util.ArrayList;
import java.util.Iterator;

public class OfflineMapUtil {
    public static MKOLSearchRecord getSearchRecordFromLocalCityInfo(s sVar) {
        if (sVar == null) {
            return null;
        }
        int i;
        MKOLSearchRecord mKOLSearchRecord = new MKOLSearchRecord();
        mKOLSearchRecord.cityID = sVar.a;
        mKOLSearchRecord.cityName = sVar.b;
        mKOLSearchRecord.cityType = sVar.d;
        if (sVar.a() != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = sVar.a().iterator();
            i = 0;
            while (it.hasNext()) {
                s sVar2 = (s) it.next();
                arrayList.add(getSearchRecordFromLocalCityInfo(sVar2));
                int i2 = sVar2.c + i;
                mKOLSearchRecord.childCities = arrayList;
                i = i2;
            }
        } else {
            i = 0;
        }
        if (mKOLSearchRecord.cityType == 1) {
            mKOLSearchRecord.size = i;
        } else {
            mKOLSearchRecord.size = sVar.c;
        }
        return mKOLSearchRecord;
    }

    public static MKOLUpdateElement getUpdatElementFromLocalMapElement(v vVar) {
        if (vVar == null) {
            return null;
        }
        MKOLUpdateElement mKOLUpdateElement = new MKOLUpdateElement();
        mKOLUpdateElement.cityID = vVar.a;
        mKOLUpdateElement.cityName = vVar.b;
        if (vVar.g != null) {
            mKOLUpdateElement.geoPt = CoordUtil.mc2ll(vVar.g);
        }
        mKOLUpdateElement.level = vVar.e;
        mKOLUpdateElement.ratio = vVar.i;
        mKOLUpdateElement.serversize = vVar.h;
        if (vVar.i == 100) {
            mKOLUpdateElement.size = vVar.h;
        } else {
            mKOLUpdateElement.size = (vVar.h / 100) * vVar.i;
        }
        mKOLUpdateElement.status = vVar.l;
        mKOLUpdateElement.update = vVar.j;
        return mKOLUpdateElement;
    }
}
