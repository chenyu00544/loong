package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import org.json.JSONException;
import org.json.JSONObject;

public class PoiDetailResult extends SearchResult implements Parcelable {
    public static final Creator<PoiDetailResult> CREATOR = new a();
    int a;
    String b;
    String c;
    LatLng d;
    String e;
    String f;
    String g;
    String h;
    String i;
    String j;
    double k;
    double l;
    double m;
    double n;
    double o;
    double p;
    double q;
    double r;
    int s;
    int t;
    int u;
    int v;
    int w;
    String x;

    PoiDetailResult() {
    }

    protected PoiDetailResult(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readDouble();
        this.l = parcel.readDouble();
        this.m = parcel.readDouble();
        this.n = parcel.readDouble();
        this.o = parcel.readDouble();
        this.p = parcel.readDouble();
        this.q = parcel.readDouble();
        this.r = parcel.readDouble();
        this.s = parcel.readInt();
        this.t = parcel.readInt();
        this.u = parcel.readInt();
        this.v = parcel.readInt();
        this.w = parcel.readInt();
        this.x = parcel.readString();
    }

    public PoiDetailResult(ERRORNO errorno) {
        super(errorno);
    }

    boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.a = jSONObject.optInt("status");
            if (this.a != 0) {
                return false;
            }
            this.b = jSONObject.optString("message");
            jSONObject = jSONObject.optJSONObject("result");
            if (jSONObject == null) {
                return false;
            }
            this.c = jSONObject.optString("name");
            JSONObject optJSONObject = jSONObject.optJSONObject("location");
            this.d = new LatLng(optJSONObject.optDouble("lat"), optJSONObject.optDouble("lng"));
            this.e = jSONObject.optString("address");
            this.f = jSONObject.optString("telephone");
            this.g = jSONObject.optString("uid");
            jSONObject = jSONObject.optJSONObject("detail_info");
            if (jSONObject != null) {
                this.h = jSONObject.optString("tag");
                this.i = jSONObject.optString("detail_url");
                this.j = jSONObject.optString("type");
                this.k = jSONObject.optDouble("price", 0.0d);
                this.l = jSONObject.optDouble("overall_rating", 0.0d);
                this.m = jSONObject.optDouble("taste_rating", 0.0d);
                this.n = jSONObject.optDouble("service_rating", 0.0d);
                this.o = jSONObject.optDouble("environment_rating", 0.0d);
                this.p = jSONObject.optDouble("facility_rating", 0.0d);
                this.q = jSONObject.optDouble("hygiene_rating", 0.0d);
                this.r = jSONObject.optDouble("technology_rating", 0.0d);
                this.s = jSONObject.optInt("image_num");
                this.t = jSONObject.optInt("groupon_num");
                this.u = jSONObject.optInt("comment_num");
                this.v = jSONObject.optInt("favorite_num");
                this.w = jSONObject.optInt("checkin_num");
                this.x = jSONObject.optString("shop_hours");
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.e;
    }

    public int getCheckinNum() {
        return this.w;
    }

    public int getCommentNum() {
        return this.u;
    }

    public String getDetailUrl() {
        return this.i;
    }

    public double getEnvironmentRating() {
        return this.o;
    }

    public double getFacilityRating() {
        return this.p;
    }

    public int getFavoriteNum() {
        return this.v;
    }

    public int getGrouponNum() {
        return this.t;
    }

    public double getHygieneRating() {
        return this.q;
    }

    public int getImageNum() {
        return this.s;
    }

    public LatLng getLocation() {
        return this.d;
    }

    public String getName() {
        return this.c;
    }

    public double getOverallRating() {
        return this.l;
    }

    public double getPrice() {
        return this.k;
    }

    public double getServiceRating() {
        return this.n;
    }

    public String getShopHours() {
        return this.x;
    }

    public String getTag() {
        return this.h;
    }

    public double getTasteRating() {
        return this.m;
    }

    public double getTechnologyRating() {
        return this.r;
    }

    public String getTelephone() {
        return this.f;
    }

    public String getType() {
        return this.j;
    }

    public String getUid() {
        return this.g;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeValue(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeDouble(this.k);
        parcel.writeDouble(this.l);
        parcel.writeDouble(this.m);
        parcel.writeDouble(this.n);
        parcel.writeDouble(this.o);
        parcel.writeDouble(this.p);
        parcel.writeDouble(this.q);
        parcel.writeDouble(this.r);
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v);
        parcel.writeInt(this.w);
        parcel.writeString(this.x);
    }
}
