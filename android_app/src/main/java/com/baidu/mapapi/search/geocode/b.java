package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult.AddressComponent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static AddressComponent a(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        AddressComponent addressComponent = new AddressComponent();
        addressComponent.city = optJSONObject.optString("city");
        addressComponent.district = optJSONObject.optString("district");
        addressComponent.province = optJSONObject.optString("province");
        addressComponent.street = optJSONObject.optString("street");
        addressComponent.streetNumber = optJSONObject.optString("street_number");
        return addressComponent;
    }

    public static ReverseGeoCodeResult a(String str) {
        ReverseGeoCodeResult reverseGeoCodeResult = new ReverseGeoCodeResult();
        if (str == null || "".equals(str)) {
            reverseGeoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                reverseGeoCodeResult.b(jSONObject.optString("address"));
                reverseGeoCodeResult.a(jSONObject.optString("business"));
                reverseGeoCodeResult.a(a(jSONObject, "addr_detail"));
                reverseGeoCodeResult.a(b(jSONObject, "point"));
                reverseGeoCodeResult.a(c(jSONObject, "surround_poi"));
            } catch (JSONException e) {
                e.printStackTrace();
                reverseGeoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
            }
        }
        return reverseGeoCodeResult;
    }

    private static LatLng b(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        return CoordUtil.mc2ll(new GeoPoint((double) optJSONObject.optInt("y"), (double) optJSONObject.optInt("x")));
    }

    public static GeoCodeResult b(String str) {
        GeoCodeResult geoCodeResult = new GeoCodeResult();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("error") != 0) {
                geoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
            } else {
                geoCodeResult.a(CoordUtil.mc2ll(new GeoPoint((double) jSONObject.optInt("y"), (double) jSONObject.optInt("x"))));
                geoCodeResult.setAddress(jSONObject.optString("addr"));
            }
        } catch (JSONException e) {
            geoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
            e.printStackTrace();
        }
        return geoCodeResult;
    }

    private static List<PoiInfo> c(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        List<PoiInfo> arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                PoiInfo poiInfo = new PoiInfo();
                poiInfo.address = optJSONObject.optString("addr");
                poiInfo.phoneNum = optJSONObject.optString("tel");
                poiInfo.uid = optJSONObject.optString("uid");
                poiInfo.postCode = optJSONObject.optString("zip");
                poiInfo.name = optJSONObject.optString("name");
                poiInfo.location = b(optJSONObject, "point");
                arrayList.add(poiInfo);
            }
        }
        return arrayList;
    }
}
