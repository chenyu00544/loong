package com.baidu.platform.comapi.map;

import android.os.Handler;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MessageCenter;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.a;
import com.tencent.stat.DeviceInfo;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class t {
    private static final String a = t.class.getSimpleName();
    private static t c;
    private a b;
    private y d;
    private Handler e;

    private t() {
    }

    public static t a() {
        if (c == null) {
            c = new t();
            c.g();
        }
        return c;
    }

    private void g() {
        h();
        this.d = new y();
        this.e = new u(this);
        MessageCenter.registMessage(m_AppUI.V_WM_VDATAENGINE, this.e);
    }

    private void h() {
        EnvironmentUtilities.initAppDirectory(BMapManager.getContext());
        this.b = new a();
        this.b.a();
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
        this.b.a(moduleFileName, str3 + str, appCachePath + "/tmp/", appSecondCachePath + "/tmp/", str3 + str, str4, null, str5, SysOSUtil.getScreenSizeX(), SysOSUtil.getScreenSizeY(), SysOSUtil.getDensityDpi(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, 0);
        this.b.f();
    }

    public ArrayList<s> a(String str) {
        if (str.equals("") || this.b == null) {
            return null;
        }
        String str2 = "";
        String a = this.b.a(str);
        if (a == null || a.equals("")) {
            return null;
        }
        ArrayList<s> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(a);
            if (jSONObject == null || jSONObject.length() == 0) {
                return null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("dataset");
            if (optJSONArray == null) {
                return null;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                s sVar = new s();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                sVar.a = jSONObject2.optInt("id");
                sVar.b = jSONObject2.optString("name");
                sVar.c = jSONObject2.optInt("mapsize");
                sVar.d = jSONObject2.optInt("cty");
                if (jSONObject2.has("child")) {
                    JSONArray optJSONArray2 = jSONObject2.optJSONArray("child");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        s sVar2 = new s();
                        JSONObject optJSONObject = optJSONArray2.optJSONObject(i2);
                        sVar2.a = optJSONObject.optInt("id");
                        sVar2.b = optJSONObject.optString("name");
                        sVar2.c = optJSONObject.optInt("mapsize");
                        sVar2.d = optJSONObject.optInt("cty");
                        arrayList2.add(sVar2);
                    }
                    sVar.a(arrayList2);
                }
                arrayList.add(sVar);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(x xVar) {
        if (this.d != null) {
            this.d.a(xVar);
        }
    }

    public boolean a(int i) {
        return (this.b == null || i < 0) ? false : this.b.b(i);
    }

    public boolean a(boolean z, boolean z2) {
        return this.b == null ? false : this.b.a(z, z2);
    }

    public void b() {
        MessageCenter.unregistMessage(m_AppUI.V_WM_VDATAENGINE, this.e);
        this.b.b();
        c = null;
    }

    public void b(x xVar) {
        if (this.d != null) {
            this.d.b(xVar);
        }
    }

    public boolean b(int i) {
        return (this.b == null || i < 0) ? false : this.b.a(i, false, 0);
    }

    public ArrayList<s> c() {
        if (this.b == null) {
            return null;
        }
        String str = "";
        String o = this.b.o();
        ArrayList<s> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(o).optJSONArray("dataset");
            for (int i = 0; i < optJSONArray.length(); i++) {
                s sVar = new s();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                sVar.a = optJSONObject.optInt("id");
                sVar.b = optJSONObject.optString("name");
                sVar.c = optJSONObject.optInt("mapsize");
                sVar.d = optJSONObject.optInt("cty");
                if (optJSONObject.has("child")) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("child");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        s sVar2 = new s();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        sVar2.a = optJSONObject2.optInt("id");
                        sVar2.b = optJSONObject2.optString("name");
                        sVar2.c = optJSONObject2.optInt("mapsize");
                        sVar2.d = optJSONObject2.optInt("cty");
                        arrayList2.add(sVar2);
                    }
                    sVar.a(arrayList2);
                }
                arrayList.add(sVar);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean c(int i) {
        return (this.b == null || i < 0) ? false : this.b.b(i, false, 0);
    }

    public ArrayList<s> d() {
        String str = "";
        if (this.b == null) {
            return null;
        }
        String a = this.b.a("");
        ArrayList<s> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(a).optJSONArray("dataset");
            for (int i = 0; i < optJSONArray.length(); i++) {
                s sVar = new s();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                sVar.a = optJSONObject.optInt("id");
                sVar.b = optJSONObject.optString("name");
                sVar.c = optJSONObject.optInt("mapsize");
                sVar.d = optJSONObject.optInt("cty");
                if (optJSONObject.has("child")) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("child");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        s sVar2 = new s();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        sVar2.a = optJSONObject2.optInt("id");
                        sVar2.b = optJSONObject2.optString("name");
                        sVar2.c = optJSONObject2.optInt("mapsize");
                        sVar2.d = optJSONObject2.optInt("cty");
                        arrayList2.add(sVar2);
                    }
                    sVar.a(arrayList2);
                }
                arrayList.add(sVar);
            }
            return arrayList;
        } catch (JSONException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public boolean d(int i) {
        return this.b == null ? false : this.b.b(0, true, i);
    }

    public ArrayList<w> e() {
        if (this.b == null) {
            return null;
        }
        String str = "";
        String n = this.b.n();
        if (n == null || n.equals("")) {
            return null;
        }
        ArrayList<w> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(n);
            if (jSONObject.length() == 0) {
                return null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("dataset");
            for (int i = 0; i < optJSONArray.length(); i++) {
                w wVar = new w();
                v vVar = new v();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                vVar.a = optJSONObject.optInt("id");
                vVar.b = optJSONObject.optString("name");
                vVar.c = optJSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_PINYIN);
                vVar.h = optJSONObject.optInt("mapoldsize");
                vVar.i = optJSONObject.optInt("ratio");
                vVar.l = optJSONObject.optInt("status");
                vVar.g = new GeoPoint((double) optJSONObject.optInt("y"), (double) optJSONObject.optInt("x"));
                if (optJSONObject.optInt("up") == 1) {
                    vVar.j = true;
                } else {
                    vVar.j = false;
                }
                vVar.e = optJSONObject.optInt("lev");
                if (vVar.j) {
                    vVar.k = optJSONObject.optInt("mapsize");
                } else {
                    vVar.k = 0;
                }
                wVar.a(vVar);
                arrayList.add(wVar);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean e(int i) {
        return (this.b == null || i < 0) ? false : this.b.b(i, false);
    }

    public boolean f(int i) {
        return (this.b == null || i < 0) ? false : this.b.a(i, false);
    }

    public w g(int i) {
        if (this.b == null || i < 0) {
            return null;
        }
        String str = "";
        String c = this.b.c(i);
        if (c == null || c.equals("")) {
            return null;
        }
        w wVar = new w();
        v vVar = new v();
        try {
            JSONObject jSONObject = new JSONObject(c);
            if (jSONObject.length() == 0) {
                return null;
            }
            vVar.a = jSONObject.optInt("id");
            vVar.b = jSONObject.optString("name");
            vVar.c = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_PINYIN);
            vVar.d = jSONObject.optString("headchar");
            vVar.h = jSONObject.optInt("mapoldsize");
            vVar.i = jSONObject.optInt("ratio");
            vVar.l = jSONObject.optInt("status");
            vVar.g = new GeoPoint((double) jSONObject.optInt("y"), (double) jSONObject.optInt("x"));
            if (jSONObject.optInt("up") == 1) {
                vVar.j = true;
            } else {
                vVar.j = false;
            }
            vVar.e = jSONObject.optInt("lev");
            if (vVar.j) {
                vVar.k = jSONObject.optInt("mapsize");
            } else {
                vVar.k = 0;
            }
            vVar.f = jSONObject.optInt(DeviceInfo.TAG_VERSION);
            wVar.a(vVar);
            return wVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
