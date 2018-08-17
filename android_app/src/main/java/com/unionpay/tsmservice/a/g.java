package com.unionpay.tsmservice.a;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class g implements Parcelable {
    public static final Creator<g> CREATOR = new h();
    private Bitmap a;
    private Rect b;
    private byte[] c;

    public g(Parcel parcel) {
        this.a = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.b = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.c = new byte[readInt];
            parcel.readByteArray(this.c);
            return;
        }
        this.c = null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
        if (this.c != null) {
            parcel.writeInt(this.c.length);
            parcel.writeByteArray(this.c);
        }
    }
}
