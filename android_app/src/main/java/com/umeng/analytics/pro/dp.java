package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: TIOStreamTransport */
public class dp extends dr {
    protected InputStream a = null;
    protected OutputStream b = null;

    protected dp() {
    }

    public dp(InputStream inputStream) {
        this.a = inputStream;
    }

    public dp(OutputStream outputStream) {
        this.b = outputStream;
    }

    public dp(InputStream inputStream, OutputStream outputStream) {
        this.a = inputStream;
        this.b = outputStream;
    }

    public boolean a() {
        return true;
    }

    public void b() throws ds {
    }

    public void c() {
        if (this.a != null) {
            try {
                this.a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.a = null;
        }
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.b = null;
        }
    }

    public int a(byte[] bArr, int i, int i2) throws ds {
        if (this.a == null) {
            throw new ds(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new ds(4);
        } catch (Throwable e) {
            throw new ds(0, e);
        }
    }

    public void b(byte[] bArr, int i, int i2) throws ds {
        if (this.b == null) {
            throw new ds(1, "Cannot write to null outputStream");
        }
        try {
            this.b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new ds(0, e);
        }
    }

    public void d() throws ds {
        if (this.b == null) {
            throw new ds(1, "Cannot flush null outputStream");
        }
        try {
            this.b.flush();
        } catch (Throwable e) {
            throw new ds(0, e);
        }
    }
}
