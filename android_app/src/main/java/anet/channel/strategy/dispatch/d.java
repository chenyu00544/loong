package anet.channel.strategy.dispatch;

import android.text.TextUtils;
import android.util.Base64InputStream;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.flow.b;
import anet.channel.request.Request;
import anet.channel.request.Request.Builder;
import anet.channel.session.c;
import anet.channel.session.c.a;
import anet.channel.statist.AmdcStatistic;
import anet.channel.statist.StatObject;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.e;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
import org.json.JSONTokener;

/* compiled from: Taobao */
class d {
    static AtomicInteger a = new AtomicInteger(0);

    d() {
    }

    static List<IConnStrategy> a() {
        List<IConnStrategy> list = Collections.EMPTY_LIST;
        if (NetworkStatusHelper.g()) {
            return list;
        }
        List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(c.a());
        if (HttpConstant.HTTPS.equals(StrategyCenter.getInstance().getSchemeByHost(c.a()))) {
            Object obj = ConnType.HTTPS;
        } else {
            ConnType connType = ConnType.HTTP;
        }
        ListIterator listIterator = connStrategyListByHost.listIterator();
        while (listIterator.hasNext()) {
            if (!((IConnStrategy) listIterator.next()).getConnType().equals(obj)) {
                listIterator.remove();
            }
        }
        return connStrategyListByHost;
    }

    public static void a(Map map) {
        if (map == null) {
            ALog.e("awcn.DispatchCore", "amdc request's parameter invalid!", null, new Object[0]);
            return;
        }
        List a = a();
        StringBuilder stringBuilder = new StringBuilder(16);
        int i = 0;
        while (i < 3) {
            IConnStrategy iConnStrategy = i != 2 ? a.isEmpty() ? null : (IConnStrategy) a.remove(0) : null;
            int a2 = a(new HashMap(map), iConnStrategy, i, stringBuilder);
            if (iConnStrategy != null) {
                StrategyCenter.getInstance().notifyConnEvent(c.a(), iConnStrategy, a2 == 0 ? EventType.CONNECTED : EventType.CONNECT_FAIL, null);
            }
            if (a2 != 0 && a2 != 2) {
                i++;
            } else {
                return;
            }
        }
    }

    private static e a(Map<String, String> map, int i) {
        StringBuilder stringBuilder = new StringBuilder(32);
        String schemeByHost = StrategyCenter.getInstance().getSchemeByHost(c.a());
        if (TextUtils.isEmpty(schemeByHost)) {
            schemeByHost = HttpConstant.HTTP;
        } else if (i == 2 && HttpConstant.HTTPS.equals(schemeByHost) && System.currentTimeMillis() % 2 == 1) {
            schemeByHost = HttpConstant.HTTP;
        }
        stringBuilder.append(schemeByHost);
        stringBuilder.append(HttpConstant.SCHEME_SPLIT);
        stringBuilder.append(c.a());
        stringBuilder.append(c.serverPath);
        Map hashMap = new HashMap();
        hashMap.put("appkey", map.remove("appkey"));
        hashMap.put(c.VERSION, map.remove(c.VERSION));
        hashMap.put("deviceId", map.remove("deviceId"));
        hashMap.put("platform", map.remove("platform"));
        stringBuilder.append('?');
        stringBuilder.append(StringUtils.encodeQueryParams(hashMap, "utf-8"));
        e a = e.a(stringBuilder.toString());
        a.h();
        return a;
    }

    public static int a(Map<String, String> map, IConnStrategy iConnStrategy, int i, StringBuilder stringBuilder) {
        Request request = null;
        int i2 = 2;
        String concatString;
        try {
            concatString = StringUtils.concatString("AMDC", String.valueOf(a.incrementAndGet()));
            request = new Builder().setMethod("POST").setUrl(a(map, i)).setParams(map).addHeader(HttpConstant.CONNECTION, "close").addHeader("Accept-Encoding", "gzip").setRedirectEnable(false).setConnectTimeout(BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT).setReadTimeout(BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT).setSeq(concatString).build();
            if (iConnStrategy != null) {
                request.setDnsOptimize(iConnStrategy.getIp(), iConnStrategy.getPort());
            }
            a a = c.a(request, null);
            if (!(stringBuilder == null || request.getUrl() == null)) {
                if (iConnStrategy != null) {
                    stringBuilder.append(iConnStrategy.getIp()).append(':').append(iConnStrategy.getPort());
                } else {
                    stringBuilder.append(request.getHost());
                }
                stringBuilder.append('-').append(a.a).append('|');
            }
            b bVar = new b();
            bVar.a = "amdc";
            bVar.b = HttpConstant.HTTP;
            bVar.c = request.getUrlString();
            bVar.d = request.rs.sendDataSize;
            bVar.e = request.rs.recDataSize;
            anet.channel.flow.c.a().commitFlow(bVar);
            if (a.a < 0) {
                if (a.a != ErrorConstant.ERROR_NO_NETWORK) {
                    a(String.valueOf(a.a), "request fail.", request, i, 1, stringBuilder);
                }
                return 1;
            }
            int i3 = a.a;
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.DispatchCore", "amdc response. code: " + i3, concatString, "\nheaders", a.c);
            }
            if (i3 != 200) {
                if (!(i3 == 302 || i3 == HttpConstant.SC_TEMPORARY_REDIRECT)) {
                    i2 = 1;
                }
                a(String.valueOf(i3), "response code not 200", request, i, i2, stringBuilder);
                return i2;
            }
            String b = anet.channel.util.c.b(a.c, "x-am-code");
            if (Constants.DEFAULT_UIN.equals(b)) {
                Object trim = anet.channel.util.c.b(a.c, "x-am-sign").trim();
                if (TextUtils.isEmpty(trim)) {
                    a("-1001", "response sign is empty", request, i, 1, stringBuilder);
                    return 1;
                }
                String a2 = a(a.b);
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.DispatchCore", "amdc response body", concatString, "\nbody", a2);
                }
                if (a2 == null) {
                    a("-1002", "read answer error", request, i, 1, stringBuilder);
                    return 1;
                }
                if (!f.a(a2).equalsIgnoreCase(trim)) {
                    ALog.e("awcn.DispatchCore", "check ret sign failed", concatString, "retSign", trim, "checkSign", f.a(a2));
                    a("-1003", "check sign failed", request, i, 1, stringBuilder);
                    return 1;
                } else if (a(a2)) {
                    a(b, "request success", request, i, 0, stringBuilder);
                    return 0;
                } else {
                    ALog.e("awcn.DispatchCore", "resolve amdc anser failed", concatString, new Object[0]);
                    a("-1004", "resolve answer failed", request, i, 1, stringBuilder);
                    return 1;
                }
            }
            if (!("1007".equals(b) || "1008".equals(b))) {
                i2 = 1;
            }
            a(b, "return code: " + b, request, i, i2, stringBuilder);
            return i2;
        } catch (Throwable th) {
            concatString = th.getMessage();
            if (TextUtils.isEmpty(concatString)) {
                concatString = th.toString();
            }
            a("-1000", concatString, request, i, 1, stringBuilder);
            return 1;
        }
    }

    static String a(byte[] bArr) {
        InputStream inputStream;
        Throwable th;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        InputStream base64InputStream;
        try {
            base64InputStream = new Base64InputStream(new ByteArrayInputStream(bArr), 0);
            try {
                anet.channel.a.a a = a.a.a(1024);
                while (a.a(base64InputStream) != -1) {
                    a.a(byteArrayOutputStream);
                }
                a.d();
                String str = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                if (base64InputStream == null) {
                    return str;
                }
                try {
                    base64InputStream.close();
                    return str;
                } catch (IOException e) {
                    return str;
                }
            } catch (IOException e2) {
                inputStream = base64InputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (base64InputStream != null) {
                    try {
                        base64InputStream.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            base64InputStream = null;
            if (base64InputStream != null) {
                base64InputStream.close();
            }
            throw th;
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(str).nextValue();
            if (jSONObject == null) {
                HttpDispatcher.getInstance().fireEvent(new DispatchEvent(0, null));
                return false;
            }
            HttpDispatcher.getInstance().fireEvent(new DispatchEvent(1, jSONObject));
            return true;
        } catch (Throwable e) {
            ALog.e("awcn.DispatchCore", "answerJsonResolve exception", null, e, new Object[0]);
            HttpDispatcher.getInstance().fireEvent(new DispatchEvent(0, null));
            return false;
        }
    }

    static void a(String str, String str2, Request request, int i, int i2, StringBuilder stringBuilder) {
        if ((i2 != 1 || i == 2) && GlobalAppRuntimeInfo.isTargetProcess()) {
            try {
                StatObject amdcStatistic = new AmdcStatistic();
                amdcStatistic.errorCode = str;
                amdcStatistic.errorMsg = str2;
                if (request != null) {
                    amdcStatistic.host = request.getUrl().getHost();
                    amdcStatistic.url = request.getUrlString();
                }
                amdcStatistic.retryTimes = i;
                if (stringBuilder != null) {
                    amdcStatistic.trace = stringBuilder.toString();
                }
                AppMonitor.getInstance().commitStat(amdcStatistic);
            } catch (Exception e) {
            }
        }
    }
}
