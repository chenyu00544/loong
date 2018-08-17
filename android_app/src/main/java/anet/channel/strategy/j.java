package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.b.b;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.dispatch.DispatchEvent;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.strategy.dispatch.HttpDispatcher.IDispatchEventListener;
import anet.channel.strategy.l.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anet.channel.util.Utils;
import anet.channel.util.e;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* compiled from: Taobao */
class j implements IStrategyInstance, IDispatchEventListener {
    protected StrategyInfoHolder a = null;
    private boolean b = false;
    private long c = 0;
    private CopyOnWriteArraySet<IStrategyListener> d = new CopyOnWriteArraySet();

    j() {
    }

    public synchronized void initialize() {
        if (!this.b) {
            try {
                ALog.i("awcn.StrategyCenter", "StrategyCenter initialize started.", null, new Object[0]);
                m.a();
                HttpDispatcher.getInstance().addListener(this);
                NetworkStatusHelper.a(GlobalAppRuntimeInfo.getContext());
                this.a = StrategyInfoHolder.a();
                this.b = true;
                ALog.i("awcn.StrategyCenter", "StrategyCenter initialize finished.", null, new Object[0]);
            } catch (Throwable e) {
                ALog.e("awcn.StrategyCenter", "StrategyCenter initialize failed.", null, e, new Object[0]);
            }
        }
    }

    public synchronized void switchEnv() {
        if (this.b) {
            m.b();
            HttpDispatcher.getInstance().switchENV();
            this.a = StrategyInfoHolder.a();
        } else {
            initialize();
            m.b();
            HttpDispatcher.getInstance().switchENV();
        }
    }

    @Deprecated
    public String getSchemeByHost(String str) {
        return getSchemeByHost(str, null);
    }

    public String getSchemeByHost(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (a()) {
            return str2;
        }
        String str3 = (String) this.a.f.get(str);
        if (!TextUtils.isEmpty(str3)) {
            str = str3;
        }
        str3 = this.a.c.a(str);
        if (str3 == null && !TextUtils.isEmpty(str2)) {
            str3 = str2;
        }
        if (str3 == null) {
            str3 = (String) b.a().a(2, str);
            if (str3 == null) {
                str3 = HttpConstant.HTTP;
            }
        }
        ALog.d("awcn.StrategyCenter", "getSchemeByHost", null, "host", str, "scheme", str3);
        return str3;
    }

    public String getCNameByHost(String str) {
        if (a() || TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = (String) this.a.f.get(str);
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    public String getFormalizeUrl(String str) {
        Throwable e;
        e a = e.a(str);
        if (a == null) {
            ALog.e("awcn.StrategyCenter", "url is invalid.", null, "URL", str, "stack", Utils.getStackMsg(new Exception("getFormalizeUrl")));
            return null;
        }
        String str2;
        try {
            String schemeByHost = getSchemeByHost(a.b(), a.a());
            if (schemeByHost.equalsIgnoreCase(a.a())) {
                str2 = str;
            } else {
                str2 = StringUtils.concatString(schemeByHost, ":", str.substring(str.indexOf("//")));
            }
            try {
                if (!ALog.isPrintLog(1)) {
                    return str2;
                }
                ALog.d("awcn.StrategyCenter", "", null, "raw", StringUtils.simplifyString(str, 128), "ret", StringUtils.simplifyString(str2, 128));
                return str2;
            } catch (Exception e2) {
                e = e2;
                ALog.e("awcn.StrategyCenter", "getFormalizeUrl failed", null, e, "raw", str);
                return str2;
            }
        } catch (Throwable e3) {
            e = e3;
            str2 = str;
            ALog.e("awcn.StrategyCenter", "getFormalizeUrl failed", null, e, "raw", str);
            return str2;
        }
    }

    @Deprecated
    public String getFormalizeUrl(String str, String str2) {
        return getFormalizeUrl(str);
    }

    public List<IConnStrategy> getConnStrategyListByHost(String str) {
        if (TextUtils.isEmpty(str) || a()) {
            return Collections.EMPTY_LIST;
        }
        String str2 = (String) this.a.f.get(str);
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        List<IConnStrategy> queryByHost = this.a.c().queryByHost(str2);
        if (queryByHost.isEmpty()) {
            queryByHost = this.a.e.a(str2);
        }
        if (!ALog.isPrintLog(1)) {
            return queryByHost;
        }
        ALog.d("getConnStrategyListByHost", null, "host", str2, "result", queryByHost);
        return queryByHost;
    }

    public void forceRefreshStrategy(String str) {
        if (!a() && !TextUtils.isEmpty(str)) {
            this.a.c().a(str, true);
        }
    }

    public void registerListener(IStrategyListener iStrategyListener) {
        if (iStrategyListener != null) {
            this.d.add(iStrategyListener);
        }
    }

    public void unregisterListener(IStrategyListener iStrategyListener) {
        this.d.remove(iStrategyListener);
    }

    public String getUnitPrefix(String str, String str2) {
        if (a()) {
            return null;
        }
        return this.a.b.a(str, str2);
    }

    public void setUnitPrefix(String str, String str2, String str3) {
        if (!a()) {
            this.a.b.a(str, str2, str3);
        }
    }

    public Map<String, IHRStrategy> getHRStrategyMap() {
        if (a()) {
            return Collections.EMPTY_MAP;
        }
        return this.a.d.a(this.a.c());
    }

    public String getClientIp() {
        if (a()) {
            return "";
        }
        return this.a.c().b;
    }

    public void notifyConnEvent(String str, IConnStrategy iConnStrategy, EventType eventType, d dVar) {
        if (!a()) {
            this.a.c().notifyConnEvent(str, iConnStrategy, eventType, dVar);
        }
    }

    private boolean a() {
        if (this.a != null) {
            return false;
        }
        ALog.w("StrategyCenter not initialized", null, "isInitialized", Boolean.valueOf(this.b));
        return true;
    }

    public void onEvent(DispatchEvent dispatchEvent) {
        if (dispatchEvent.eventType == 1 && this.a != null) {
            ALog.d("awcn.StrategyCenter", "receive DNS event", null, new Object[0]);
            c a = l.a((JSONObject) dispatchEvent.extraObject);
            if (a != null) {
                this.a.a(a);
                saveData();
                Iterator it = this.d.iterator();
                while (it.hasNext()) {
                    ((IStrategyListener) it.next()).onStrategyUpdated(a);
                }
            }
        }
    }

    public synchronized void saveData() {
        ALog.i("awcn.StrategyCenter", "saveData", null, new Object[0]);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.c > 180000) {
            this.c = currentTimeMillis;
            anet.channel.c.c.a(new k(this), 8);
        }
    }
}
