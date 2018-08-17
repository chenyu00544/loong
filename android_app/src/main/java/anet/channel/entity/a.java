package anet.channel.entity;

import anet.channel.strategy.IConnStrategy;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/* compiled from: Taobao */
public class a {
    public final IConnStrategy a;
    public int b = 0;
    public int c = 0;
    private String d;
    private String e;

    public a(String str, String str2, IConnStrategy iConnStrategy) {
        this.a = iConnStrategy;
        this.d = str;
        this.e = str2;
    }

    public String a() {
        if (this.a != null) {
            return this.a.getIp();
        }
        return null;
    }

    public int b() {
        if (this.a != null) {
            return this.a.getPort();
        }
        return 0;
    }

    public ConnType c() {
        if (this.a != null) {
            return this.a.getConnType();
        }
        return ConnType.HTTP;
    }

    public int d() {
        if (this.a == null || this.a.getConnectionTimeout() == 0) {
            return BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT;
        }
        return this.a.getConnectionTimeout();
    }

    public int e() {
        if (this.a == null || this.a.getReadTimeout() == 0) {
            return BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT;
        }
        return this.a.getReadTimeout();
    }

    public boolean f() {
        if (this.a != null) {
            return this.a.isNeedAuth();
        }
        return false;
    }

    public String g() {
        return this.d;
    }

    public int h() {
        if (this.a != null) {
            return this.a.getHeartbeat();
        }
        return 45000;
    }

    public String i() {
        return this.e;
    }

    public String toString() {
        return "ConnInfo [ip=" + a() + ",port=" + b() + ",type=" + c() + ",hb" + h() + "]";
    }
}
