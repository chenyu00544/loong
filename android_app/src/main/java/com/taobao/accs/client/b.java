package com.taobao.accs.client;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Taobao */
public class b {
    private Context a;
    private ConcurrentMap<String, Integer> b = new ConcurrentHashMap();
    private ConcurrentMap<String, Set<String>> c = new ConcurrentHashMap();
    private long d;
    private String e = "ACCS_BIND";

    public b(Context context, String str) {
        if (context == null) {
            throw new RuntimeException("Context is null!!");
        }
        this.a = context.getApplicationContext();
        this.e = "ACCS_BIND" + str;
        a();
    }

    public void a(String str) {
        Integer num = (Integer) this.b.get(str);
        if (num == null || num.intValue() != 2) {
            this.b.put(str, Integer.valueOf(2));
            a(this.a, this.e, this.d, this.b);
        }
    }

    public void b(String str) {
        Integer num = (Integer) this.b.get(str);
        if (num == null || num.intValue() != 4) {
            this.b.put(str, Integer.valueOf(4));
            a(this.a, this.e, this.d, this.b);
        }
    }

    public boolean c(String str) {
        if (this.b.isEmpty()) {
            a();
        }
        Integer num = (Integer) this.b.get(str);
        ALog.i("ClientManager", "isAppBinded begin..appStatus=" + num + ",mBindStatus=" + this.b, new Object[0]);
        if (num == null || num.intValue() != 2) {
            return false;
        }
        return true;
    }

    public boolean d(String str) {
        Integer num = (Integer) this.b.get(str);
        if (num == null || num.intValue() != 4) {
            return false;
        }
        return true;
    }

    public void a(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                Set set = (Set) this.c.get(str);
                if (set == null) {
                    set = new HashSet();
                }
                set.add(str2);
                this.c.put(str, set);
            }
        } catch (Exception e) {
            ALog.e("ClientManager", "ClientManager" + e.toString(), new Object[0]);
            e.printStackTrace();
        }
    }

    public void e(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.c.remove(str);
            }
        } catch (Exception e) {
            ALog.e("ClientManager", "ClientManager" + e.toString(), new Object[0]);
            e.printStackTrace();
        }
    }

    public boolean b(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Set set = (Set) this.c.get(str);
            if (set != null && set.contains(str2)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            ALog.e("ClientManager", "ClientManager" + e.toString(), new Object[0]);
            e.printStackTrace();
        }
    }

    private void a() {
        try {
            Object string = this.a.getSharedPreferences(this.e, 0).getString("bind_status", null);
            if (TextUtils.isEmpty(string)) {
                ALog.i("ClientManager", "restoreClients packs null return", new Object[0]);
                return;
            }
            JSONArray jSONArray = new JSONArray(string);
            this.d = jSONArray.getLong(0);
            if (System.currentTimeMillis() < this.d + 86400000) {
                for (int i = 1; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    this.b.put(jSONObject.getString("p"), Integer.valueOf(jSONObject.getInt("s")));
                }
                ALog.i("ClientManager", "restoreClients mBindStatus restore=" + this.b, new Object[0]);
                return;
            }
            ALog.i("ClientManager", "restoreClients expired", "lastFlushTime", Long.valueOf(this.d));
            this.d = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, long j, Map<String, Integer> map) {
        try {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            JSONArray jSONArray = new JSONArray();
            if (j <= 0 || j >= System.currentTimeMillis()) {
                jSONArray.put(((double) System.currentTimeMillis()) - (Math.random() * 8.64E7d));
            } else {
                jSONArray.put(j);
            }
            for (Object obj : strArr) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("p", obj);
                jSONObject.put("s", ((Integer) map.get(obj)).intValue());
                jSONArray.put(jSONObject);
            }
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString("bind_status", jSONArray.toString());
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
