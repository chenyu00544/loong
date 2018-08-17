package com.baidu.mtjstatsdk;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mtjstatsdk.a.b;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

public class SessionAnalysisObject {
    long a = 0;
    long b = 0;
    long c = 0;
    long d = 0;
    WeakReference<Context> e;
    WeakReference<Fragment> f;
    WeakReference<Object> g;
    b h = new b();
    int i = -1;
    boolean j = true;
    boolean k = false;
    boolean l = false;
    boolean m = false;
    boolean n = false;
    String o = null;

    public void flushSession(Context context, long j, String str) {
        if (a.a(str)) {
            d.a("statsdk", "flush current session to last_session.json");
        }
        String str2 = "{}";
        JSONObject jSONObject = new JSONObject();
        jSONObject = this.h.c();
        try {
            jSONObject.put("e", j);
        } catch (JSONException e) {
            if (a.a(str)) {
                d.a("statsdk", "StatSession.flushSession() failed");
            }
        }
        str2 = jSONObject.toString();
        if (a.a(str)) {
            d.a("statsdk", "cacheString=" + str2);
        }
        com.baidu.mtjstatsdk.b.b.a(false, context, str + "__local_last_session.json", str2, false);
    }

    public int getSessionPeriod() {
        if (this.i == -1) {
            this.i = m_AppUI.MSG_RADAR_SEARCH_RETURN_RESULT;
        }
        return this.i;
    }

    public long getSessionStartTime() {
        return this.h.a();
    }

    public boolean isFirstResume() {
        return this.j;
    }

    public void setFirstResume(boolean z) {
        this.j = z;
    }

    public void setSessionCounted() {
        this.h.a(this.h.d() + 1);
    }

    public void setSessionPeriod(int i) {
        this.i = i * 1000;
    }
}
