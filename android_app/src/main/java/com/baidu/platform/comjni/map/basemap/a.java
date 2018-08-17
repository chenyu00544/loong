package com.baidu.platform.comjni.map.basemap;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.platform.comapi.AssetsLoadUtil;
import com.baidu.platform.comapi.NativeLoader;
import com.baidu.platform.comapi.map.M;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static final String a = a.class.getSimpleName();
    private static List<JNIBaseMap> d = new ArrayList();
    private long b;
    private JNIBaseMap c;

    static {
        if (VersionInfo.getApiVersion().equals(M.a())) {
            a(BMapManager.getContext());
            NativeLoader.getInstance().loadLibrary(M.b());
            return;
        }
        throw new BaiduMapSDKException("the version of map is not match with base");
    }

    public a() {
        this.b = 0;
        this.c = null;
        this.c = new JNIBaseMap();
    }

    public static int a(long j, int i, int i2, int i3) {
        return JNIBaseMap.MapProc(j, i, i2, i3);
    }

    private static void a(Context context) {
        int i = 0;
        if (context != null) {
            try {
                File file = new File(SysOSUtil.getModuleFileName());
                if (!file.exists()) {
                    file.mkdirs();
                }
                context.getAssets();
                String[] strArr = new String[]{"cfg/a/ResPack.cfg", "cfg/idrres/baseindoormap.sty", "cfg/idrres/DVIndoor.cfg", "cfg/idrres/ResPack.cfg", "cfg/h/DVHotcity.cfg", "cfg/l/DVHotcity.cfg", "cfg/h/DVHotMap.cfg", "cfg/l/DVHotMap.cfg", "cfg/l/DVDirectory.cfg", "cfg/l/DVVersion.cfg", "cfg/h/DVDirectory.cfg", "cfg/h/DVVersion.cfg", "cfg/a/mapstyle.sty", "cfg/a/satellitestyle.sty", "cfg/a/trafficstyle.sty", "cfg/a/CustomIndex"};
                String[] strArr2 = new String[]{"cfg/a/CustomIndex"};
                String[] strArr3 = new String[]{"cfg/a/ResPack.rs", "cfg/idrres/baseindoormap.sty", "cfg/idrres/DVIndoor.cfg", "cfg/idrres/ResPackIndoorMap.rs", "cfg/h/DVHotcity.cfg", "cfg/l/DVHotcity.cfg", "cfg/h/DVHotMap.cfg", "cfg/l/DVHotMap.cfg", "cfg/l/DVDirectory.cfg", "cfg/l/DVVersion.cfg", "cfg/h/DVDirectory.cfg", "cfg/h/DVVersion.cfg", "cfg/a/mapstyle.sty", "cfg/a/satellitestyle.sty", "cfg/a/trafficstyle.sty", "cfg/a/CustomIndex"};
                String[] strArr4 = new String[]{"cfg/a/CustomIndex"};
                try {
                    int i2;
                    FileOutputStream fileOutputStream;
                    File file2;
                    int i3;
                    File file3 = new File(SysOSUtil.getModuleFileName() + "/ver.dat");
                    byte[] bArr = new byte[]{(byte) 4, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
                    if (file3.exists()) {
                        FileInputStream fileInputStream = new FileInputStream(file3);
                        byte[] bArr2 = new byte[fileInputStream.available()];
                        fileInputStream.read(bArr2);
                        fileInputStream.close();
                        if (Arrays.equals(bArr2, bArr)) {
                            file = new File(SysOSUtil.getModuleFileName() + "/cfg/a/mapstyle.sty");
                            if (file.exists() && file.length() > 0) {
                                i2 = 0;
                                if (i2 != 0) {
                                    if (file3.exists()) {
                                        file3.delete();
                                    }
                                    file3.createNewFile();
                                    fileOutputStream = new FileOutputStream(file3);
                                    fileOutputStream.write(bArr);
                                    fileOutputStream.close();
                                    file2 = new File(SysOSUtil.getModuleFileName() + "/cfg/a");
                                    if (!file2.exists()) {
                                        file2.mkdirs();
                                    }
                                    file2 = new File(SysOSUtil.getModuleFileName() + "/cfg/h");
                                    if (!file2.exists()) {
                                        file2.mkdirs();
                                    }
                                    file2 = new File(SysOSUtil.getModuleFileName() + "/cfg/l");
                                    if (!file2.exists()) {
                                        file2.mkdirs();
                                    }
                                    file2 = new File(SysOSUtil.getModuleFileName() + "/cfg/idrres");
                                    if (!file2.exists()) {
                                        file2.mkdirs();
                                    }
                                }
                                for (i3 = 0; i3 < strArr4.length; i3++) {
                                    AssetsLoadUtil.copyFileFromAsset(strArr2[i3], strArr4[i3], context);
                                }
                                if (i2 != 0) {
                                    while (i < strArr3.length) {
                                        AssetsLoadUtil.copyFileFromAsset(strArr[i], strArr3[i], context);
                                        i++;
                                    }
                                }
                            }
                        }
                    }
                    i2 = 1;
                    if (i2 != 0) {
                        if (file3.exists()) {
                            file3.delete();
                        }
                        file3.createNewFile();
                        fileOutputStream = new FileOutputStream(file3);
                        fileOutputStream.write(bArr);
                        fileOutputStream.close();
                        file2 = new File(SysOSUtil.getModuleFileName() + "/cfg/a");
                        if (file2.exists()) {
                            file2.mkdirs();
                        }
                        file2 = new File(SysOSUtil.getModuleFileName() + "/cfg/h");
                        if (file2.exists()) {
                            file2.mkdirs();
                        }
                        file2 = new File(SysOSUtil.getModuleFileName() + "/cfg/l");
                        if (file2.exists()) {
                            file2.mkdirs();
                        }
                        file2 = new File(SysOSUtil.getModuleFileName() + "/cfg/idrres");
                        if (file2.exists()) {
                            file2.mkdirs();
                        }
                    }
                    for (i3 = 0; i3 < strArr4.length; i3++) {
                        AssetsLoadUtil.copyFileFromAsset(strArr2[i3], strArr4[i3], context);
                    }
                    if (i2 != 0) {
                        while (i < strArr3.length) {
                            AssetsLoadUtil.copyFileFromAsset(strArr[i], strArr3[i], context);
                            i++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void b(long j, boolean z) {
        JNIBaseMap.SetMapCustomEnable(j, z);
    }

    public static List<JNIBaseMap> d() {
        return d;
    }

    public int a(int i) {
        return this.c.SetMapControlMode(this.b, i);
    }

    public long a(int i, int i2, String str) {
        return this.c.AddLayer(this.b, i, i2, str);
    }

    public String a(int i, int i2) {
        return this.c.ScrPtToGeoPoint(this.b, i, i2);
    }

    public String a(int i, int i2, int i3, int i4) {
        return this.c.GetNearlyObjID(this.b, (long) i, i2, i3, i4);
    }

    public String a(String str) {
        return this.c.OnSchcityGet(this.b, str);
    }

    public void a(long j, boolean z) {
        this.c.ShowLayers(this.b, j, z);
    }

    public void a(Bundle bundle) {
        this.c.SetMapStatus(this.b, bundle);
    }

    public void a(String str, Bundle bundle) {
        this.c.SaveScreenToLocal(this.b, str, bundle);
    }

    public void a(boolean z) {
        this.c.ShowSatelliteMap(this.b, z);
    }

    public void a(Bundle[] bundleArr) {
        this.c.addOverlayItems(this.b, bundleArr, bundleArr.length);
    }

    public boolean a() {
        if (d.size() == 0) {
            this.b = this.c.Create();
        } else {
            this.b = this.c.CreateDuplicate(((JNIBaseMap) d.get(0)).a);
        }
        this.c.a = this.b;
        d.add(this.c);
        this.c.SetCallback(this.b, null);
        return true;
    }

    public boolean a(int i, boolean z) {
        return this.c.OnRecordReload(this.b, i, z);
    }

    public boolean a(int i, boolean z, int i2) {
        return this.c.OnRecordStart(this.b, i, z, i2);
    }

    public boolean a(long j) {
        return this.c.LayersIsShow(this.b, j);
    }

    public boolean a(String str, String str2) {
        return this.c.SwitchBaseIndoorMapFloor(this.b, str, str2);
    }

    public boolean a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return this.c.Init(this.b, str, str2, str3, str4, str5, str6, str7, str8, i, i2, i3, i4, i5, i6, i7);
    }

    public boolean a(boolean z, boolean z2) {
        return this.c.OnRecordImport(this.b, z, z2);
    }

    public int[] a(int[] iArr, int i, int i2) {
        return this.c.GetScreenBuf(this.b, iArr, i, i2);
    }

    public String b(int i, int i2) {
        return this.c.GeoPtToScrPoint(this.b, i, i2);
    }

    public void b(long j) {
        this.c.UpdateLayers(this.b, j);
    }

    public void b(Bundle bundle) {
        this.c.setMapStatusLimits(this.b, bundle);
    }

    public void b(boolean z) {
        this.c.ShowHotMap(this.b, z);
    }

    public boolean b() {
        this.c.Release(this.b);
        d.remove(this.c);
        return true;
    }

    public boolean b(int i) {
        return this.c.OnRecordAdd(this.b, i);
    }

    public boolean b(int i, boolean z) {
        return this.c.OnRecordRemove(this.b, i, z);
    }

    public boolean b(int i, boolean z, int i2) {
        return this.c.OnRecordSuspend(this.b, i, z, i2);
    }

    public float c(Bundle bundle) {
        return this.c.GetZoomToBound(this.b, bundle);
    }

    public long c() {
        return this.b;
    }

    public String c(int i) {
        return this.c.OnRecordGetAt(this.b, i);
    }

    public void c(boolean z) {
        this.c.ShowTrafficMap(this.b, z);
    }

    public boolean c(long j) {
        return this.c.cleanSDKTileDataCache(this.b, j);
    }

    public void d(long j) {
        this.c.ClearLayer(this.b, j);
    }

    public void d(boolean z) {
        this.c.enableDrawHouseHeight(this.b, z);
    }

    public boolean d(Bundle bundle) {
        return this.c.updateSDKTile(this.b, bundle);
    }

    public String e(long j) {
        return this.c.getCompassPosition(this.b, j);
    }

    public void e() {
        this.c.OnPause(this.b);
    }

    public void e(boolean z) {
        this.c.ShowBaseIndoorMap(this.b, z);
    }

    public boolean e(Bundle bundle) {
        return this.c.addtileOverlay(this.b, bundle);
    }

    public void f() {
        this.c.OnResume(this.b);
    }

    public void f(Bundle bundle) {
        this.c.addOneOverlayItem(this.b, bundle);
    }

    public void g() {
        this.c.OnBackground(this.b);
    }

    public void g(Bundle bundle) {
        this.c.updateOneOverlayItem(this.b, bundle);
    }

    public void h() {
        this.c.OnForeground(this.b);
    }

    public void h(Bundle bundle) {
        this.c.removeOneOverlayItem(this.b, bundle);
    }

    public void i() {
        this.c.ResetImageRes(this.b);
    }

    public Bundle j() {
        return this.c.GetMapStatus(this.b);
    }

    public Bundle k() {
        Bundle mapStatusLimits = this.c.getMapStatusLimits(this.b);
        Log.d("test", "GetMapStatusLimits, maddr: " + this.b + "bundle: " + mapStatusLimits);
        return mapStatusLimits;
    }

    public Bundle l() {
        return this.c.getDrawingMapStatus(this.b);
    }

    public boolean m() {
        return this.c.GetBaiduHotMapCityInfo(this.b);
    }

    public String n() {
        return this.c.OnRecordGetAll(this.b);
    }

    public String o() {
        return this.c.OnHotcityGet(this.b);
    }

    public void p() {
        this.c.PostStatInfo(this.b);
    }

    public boolean q() {
        return this.c.isDrawHouseHeightEnable(this.b);
    }

    public void r() {
        this.c.clearHeatMapLayerCache(this.b);
    }

    public MapBaseIndoorMapInfo s() {
        JSONException e;
        String str = this.c.getfocusedBaseIndoorMapInfo(this.b);
        if (str == null) {
            return null;
        }
        String str2 = "";
        String str3 = new String();
        ArrayList arrayList = new ArrayList(1);
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.optString("focusindoorid");
            str = jSONObject.optString("curfloor");
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("floorlist");
                if (optJSONArray == null) {
                    return null;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.get(i).toString());
                }
                return new MapBaseIndoorMapInfo(str2, str, arrayList);
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return new MapBaseIndoorMapInfo(str2, str, arrayList);
            }
        } catch (JSONException e3) {
            e = e3;
            str = str3;
            e.printStackTrace();
            return new MapBaseIndoorMapInfo(str2, str, arrayList);
        }
    }

    public boolean t() {
        return this.c.IsBaseIndoorMapMode(this.b);
    }
}
