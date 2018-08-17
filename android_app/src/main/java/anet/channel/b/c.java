package anet.channel.b;

import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import com.baidu.mapapi.UIMsg.d_ResultType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
class c extends a {
    ConcurrentHashMap<String, List<Long>> c = new ConcurrentHashMap();
    HashMap<String, Long> d = new HashMap();

    c() {
    }

    protected Object a(int i, Object... objArr) {
        if (i == 0) {
            a((String) objArr[0], ((Integer) objArr[1]).intValue());
        } else if (i == 1) {
            a((String) objArr[0]);
            return b;
        }
        return a;
    }

    public void a(String str, int i) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.FailOverHandler", "FailOverHandler hook onResponseCode", null, "host", str, "code", Integer.valueOf(i));
        }
        if (i >= d_ResultType.SHORT_URL && i < 600) {
            List linkedList;
            List list = (List) this.c.get(str);
            if (list == null) {
                linkedList = new LinkedList();
                list = (List) this.c.putIfAbsent(str, linkedList);
                if (list != null) {
                    linkedList = list;
                }
            } else {
                linkedList = list;
            }
            synchronized (linkedList) {
                if (linkedList.size() < 5) {
                    linkedList.add(Long.valueOf(System.currentTimeMillis()));
                } else {
                    long longValue = ((Long) linkedList.remove(0)).longValue();
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - longValue <= 60000) {
                        a(str);
                        linkedList.clear();
                    } else {
                        linkedList.add(Long.valueOf(currentTimeMillis));
                    }
                }
            }
        }
    }

    private void a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.d) {
            Long l = (Long) this.d.get(str);
            if (l == null || l.longValue() - currentTimeMillis > 60000) {
                this.d.put(str, Long.valueOf(currentTimeMillis));
                StrategyCenter.getInstance().forceRefreshStrategy(str);
            }
        }
    }
}
