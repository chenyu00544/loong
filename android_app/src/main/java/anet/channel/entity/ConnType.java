package anet.channel.entity;

import android.text.TextUtils;
import anet.channel.strategy.l.a;
import anet.channel.util.HttpConstant;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyProtocol;

/* compiled from: Taobao */
public class ConnType implements Serializable {
    public static ConnType ACCS_0RTT = new ConnType("spdy_0rtt_acs", 4226, "acs");
    public static ConnType ACCS_1RTT = new ConnType("spdy_1rtt_acs", 8322, "acs");
    public static final String ACS = "acs";
    public static final String CDN = "cdn";
    public static ConnType H2_ACCS_0RTT = new ConnType("http2_0rtt_acs", SpdyProtocol.SSSL_0RTT_HTTP2, "acs");
    public static ConnType H2_ACCS_1RTT = new ConnType("http2_1rtt_acs", SpdyProtocol.SSSL_1RTT_HTTP2, "acs");
    public static ConnType HTTP = new ConnType(HttpConstant.HTTP);
    public static final String HTTP2 = "http2";
    public static ConnType HTTPS = new ConnType(HttpConstant.HTTPS);
    public static final String OPEN = "open";
    public static final String RTT_0 = "0rtt";
    public static final String RTT_1 = "1rtt";
    public static ConnType SPDY = new ConnType(SPDY_STR, 2, null);
    public static final String SPDY_STR = "spdy";
    private static Map<String, ConnType> connTypeMap = new HashMap();
    private static final long serialVersionUID = 4362386279661117076L;
    private String name = "";
    private String publicKey;
    private int spdyProtocol;

    /* compiled from: Taobao */
    public enum TypeLevel {
        SPDY,
        HTTP
    }

    static {
        connTypeMap.put("spdy_0rtt_acs", ACCS_0RTT);
        connTypeMap.put("spdy_1rtt_acs", ACCS_1RTT);
        connTypeMap.put("http2_0rtt_acs", H2_ACCS_0RTT);
        connTypeMap.put("http2_1rtt_acs", H2_ACCS_1RTT);
        connTypeMap.put(SPDY_STR, SPDY);
    }

    private ConnType(String str) {
        this.name = str;
    }

    private ConnType(String str, int i, String str2) {
        this.spdyProtocol = i;
        this.publicKey = str2;
        this.name = str;
    }

    public int getTnetConType() {
        return this.spdyProtocol;
    }

    public boolean isHttpType() {
        return equals(HTTP) || equals(HTTPS);
    }

    public boolean isSSL() {
        return equals(HTTPS) || (this.spdyProtocol & 128) != 0;
    }

    public String toProtocol() {
        return this.name;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public static ConnType valueOf(a aVar) {
        if (TextUtils.isEmpty(aVar.b) || HttpConstant.HTTP.equals(aVar.b)) {
            return HTTP;
        }
        if (HttpConstant.HTTPS.equals(aVar.b)) {
            return HTTPS;
        }
        String buildKey = buildKey(aVar);
        synchronized (connTypeMap) {
            if (connTypeMap.containsKey(buildKey)) {
                ConnType connType = (ConnType) connTypeMap.get(buildKey);
                return connType;
            }
            connType = new ConnType(buildKey);
            connType.publicKey = aVar.j;
            if (HTTP2.equals(aVar.b)) {
                connType.spdyProtocol |= 8;
            } else if (SPDY_STR.equals(aVar.b)) {
                connType.spdyProtocol |= 2;
            }
            if (connType.spdyProtocol == 0) {
                return null;
            }
            if (!TextUtils.isEmpty(aVar.j)) {
                connType.spdyProtocol |= 128;
                if (RTT_1.equals(aVar.g)) {
                    connType.spdyProtocol |= 8192;
                } else {
                    connType.spdyProtocol |= 4096;
                }
                if (aVar.i) {
                    connType.spdyProtocol |= 16384;
                }
            }
            connTypeMap.put(buildKey, connType);
            return connType;
        }
    }

    private static String buildKey(a aVar) {
        if (TextUtils.isEmpty(aVar.j)) {
            return aVar.b;
        }
        StringBuilder stringBuilder = new StringBuilder(18);
        stringBuilder.append(aVar.b);
        if (TextUtils.isEmpty(aVar.g)) {
            stringBuilder.append("_0rtt");
        } else {
            stringBuilder.append("_").append(aVar.g);
        }
        stringBuilder.append("_");
        stringBuilder.append(aVar.j);
        if (aVar.i) {
            stringBuilder.append("_l7");
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ConnType)) {
            return false;
        }
        if (this == obj || this.name.equals(((ConnType) obj).name)) {
            return true;
        }
        return false;
    }

    public TypeLevel getTypeLevel() {
        if (isHttpType()) {
            return TypeLevel.HTTP;
        }
        return TypeLevel.SPDY;
    }

    private int getPriority() {
        if (isHttpType()) {
            return 1;
        }
        if ((this.spdyProtocol & 8) == 0) {
            return 0;
        }
        return -1;
    }

    public static int compare(ConnType connType, ConnType connType2) {
        return connType.getPriority() - connType2.getPriority();
    }
}
