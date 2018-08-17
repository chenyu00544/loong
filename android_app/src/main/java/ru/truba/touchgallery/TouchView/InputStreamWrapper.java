package ru.truba.touchgallery.TouchView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamWrapper extends BufferedInputStream {
    protected long mBytesLoaded = 0;
    protected long mContentLen;
    protected InputStreamProgressListener mProgressListener;

    public interface InputStreamProgressListener {
        void onProgress(float f, long j, long j2);
    }

    public InputStreamWrapper(InputStream inputStream, int i, long j) {
        super(inputStream, i);
        this.mContentLen = j;
    }

    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        this.mBytesLoaded += (long) i2;
        if (this.mProgressListener != null) {
            this.mProgressListener.onProgress((((float) this.mBytesLoaded) * 1.0f) / ((float) this.mContentLen), this.mBytesLoaded, this.mContentLen);
        }
        return super.read(bArr, i, i2);
    }

    public void setProgressListener(InputStreamProgressListener inputStreamProgressListener) {
        this.mProgressListener = inputStreamProgressListener;
    }
}
