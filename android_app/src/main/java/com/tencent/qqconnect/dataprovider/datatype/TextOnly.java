package com.tencent.qqconnect.dataprovider.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: ProGuard */
public class TextOnly implements Parcelable {
    public static final Creator<TextOnly> CREATOR = new TextOnly_1();
    private String a;

    /* compiled from: ProGuard */
    static class TextOnly_1 implements Creator<TextOnly> {
        TextOnly_1() {
        }

        public TextOnly createFromParcel(Parcel parcel) {
            return new TextOnly(parcel);
        }

        public TextOnly[] newArray(int i) {
            return new TextOnly[i];
        }
    }

    public TextOnly(String str) {
        this.a = str;
    }

    public String getText() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }

    private TextOnly(Parcel parcel) {
        this.a = parcel.readString();
    }
}
