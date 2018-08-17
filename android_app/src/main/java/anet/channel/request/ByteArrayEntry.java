package anet.channel.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: Taobao */
public class ByteArrayEntry implements BodyEntry {
    public static final Creator<ByteArrayEntry> CREATOR = new ByteArrayEntry_1();
    private byte[] bytes;
    private int count;
    private int offset;

    /* compiled from: Taobao */
    final class ByteArrayEntry_1 implements Creator<ByteArrayEntry> {
        ByteArrayEntry_1() {
        }

        public ByteArrayEntry createFromParcel(Parcel parcel) {
            ByteArrayEntry byteArrayEntry = new ByteArrayEntry();
            byteArrayEntry.bytes = new byte[parcel.readInt()];
            parcel.readByteArray(byteArrayEntry.bytes);
            byteArrayEntry.offset = parcel.readInt();
            byteArrayEntry.count = parcel.readInt();
            return byteArrayEntry;
        }

        public ByteArrayEntry[] newArray(int i) {
            return new ByteArrayEntry[i];
        }
    }

    public ByteArrayEntry(byte[] bArr) {
        this.offset = 0;
        this.count = 0;
        this.bytes = bArr;
        this.offset = 0;
        this.count = bArr.length;
    }

    public ByteArrayEntry(byte[] bArr, int i, int i2) {
        this.offset = 0;
        this.count = 0;
        this.bytes = bArr;
        this.offset = i;
        this.count = i2;
    }

    private ByteArrayEntry() {
        this.offset = 0;
        this.count = 0;
    }

    public String getContentType() {
        return null;
    }

    public int writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.bytes, this.offset, this.count);
        return this.count;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.bytes.length);
        parcel.writeByteArray(this.bytes);
        parcel.writeInt(this.offset);
        parcel.writeInt(this.count);
    }
}
