package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import java.util.List;

public class ReverseGeoCodeResult extends SearchResult {
    public static final Creator<ReverseGeoCodeResult> CREATOR = new c();
    private String a;
    private String b;
    private AddressComponent c;
    private LatLng d;
    private List<PoiInfo> e;

    public static class AddressComponent implements Parcelable {
        public static final Creator<AddressComponent> CREATOR = new d();
        public String city;
        public String district;
        public String province;
        public String street;
        public String streetNumber;

        protected AddressComponent(Parcel parcel) {
            this.streetNumber = parcel.readString();
            this.street = parcel.readString();
            this.district = parcel.readString();
            this.city = parcel.readString();
            this.province = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.streetNumber);
            parcel.writeString(this.street);
            parcel.writeString(this.district);
            parcel.writeString(this.city);
            parcel.writeString(this.province);
        }
    }

    ReverseGeoCodeResult() {
    }

    protected ReverseGeoCodeResult(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = (AddressComponent) parcel.readParcelable(AddressComponent.class.getClassLoader());
        this.d = (LatLng) parcel.readValue(LatLng.class.getClassLoader());
        this.e = parcel.createTypedArrayList(PoiInfo.CREATOR);
    }

    ReverseGeoCodeResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(LatLng latLng) {
        this.d = latLng;
    }

    void a(AddressComponent addressComponent) {
        this.c = addressComponent;
    }

    void a(String str) {
        this.a = str;
    }

    void a(List<PoiInfo> list) {
        this.e = list;
    }

    void b(String str) {
        this.b = str;
    }

    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.b;
    }

    public AddressComponent getAddressDetail() {
        return this.c;
    }

    public String getBusinessCircle() {
        return this.a;
    }

    public LatLng getLocation() {
        return this.d;
    }

    public List<PoiInfo> getPoiList() {
        return this.e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.c, 0);
        parcel.writeValue(this.d);
        parcel.writeTypedList(this.e);
    }
}
