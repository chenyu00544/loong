package com.umeng.socialize.editorpage.location;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.text.TextUtils;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.Log;

/* compiled from: DefaultLocationProvider */
public class a implements SocializeLocationProvider {
    private static final String a = "DefaultLocationProvider";
    private Location b;
    private Context c;
    private d d;
    private c e = null;
    private String f;

    public void a(Context context) {
        this.c = context;
        this.e = new c();
        b();
    }

    public void a() {
        if (this.d != null && this.e != null) {
            this.d.a(this.e);
        }
    }

    public Location b() {
        if (this.b == null) {
            if (DeviceConfig.checkPermission(this.c, "android.permission.ACCESS_FINE_LOCATION")) {
                a(this.c, 1);
            } else if (DeviceConfig.checkPermission(this.c, "android.permission.ACCESS_COARSE_LOCATION")) {
                a(this.c, 2);
            }
        }
        return this.b;
    }

    private void a(Context context, int i) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(i);
        String a = this.d.a(criteria, true);
        if (a != null) {
            this.f = a;
        }
        Log.d(a, "Get location from " + this.f);
        try {
            if (!TextUtils.isEmpty(this.f)) {
                Location a2 = this.d.a(this.f);
                if (a2 != null) {
                    this.b = a2;
                } else if (this.d.b(this.f) && this.e != null && (context instanceof Activity)) {
                    this.d.a((Activity) context, this.f, 1, 0.0f, this.e);
                }
            }
        } catch (IllegalArgumentException e) {
        }
    }

    public void a(d dVar) {
        this.d = dVar;
    }

    protected d c() {
        return this.d;
    }

    protected void a(Location location) {
        this.b = location;
    }

    public void a(String str) {
        this.f = str;
    }
}
