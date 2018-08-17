package com.baidu.mapapi.search.share;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;

public class ShareUrlResult extends SearchResult implements Parcelable {
    public static final Creator<ShareUrlResult> CREATOR = new a();
    private String a;
    private int b;

    ShareUrlResult() {
    }

    protected ShareUrlResult(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
    }

    ShareUrlResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(int i) {
        this.b = i;
    }

    void a(String str) {
        this.a = str;
    }

    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return this.a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
    }
}
