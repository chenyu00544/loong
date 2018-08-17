package com.baidu.platform.comjni.map.commonmemcache;

import android.os.Bundle;

public class a {
    private long a;
    private JNICommonMemCache b;

    public a() {
        this.a = 0;
        this.b = null;
        this.b = new JNICommonMemCache();
    }

    public long a() {
        this.a = this.b.Create();
        return this.a;
    }

    public void a(Bundle bundle) {
        if (this.a != 0) {
            this.b.Init(this.a, bundle);
        }
    }

    public String b() {
        return this.b.GetPhoneInfoUrl(this.a);
    }
}
