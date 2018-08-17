package com.unionpay.tsmservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.unionpay.tsmservice.c.a;

public final class l extends a {
    private Context a;

    public l(Context context) {
        this.a = context;
    }

    public final void a(String str, String str2, int i, Bundle bundle) throws RemoteException {
        ComponentName componentName = new ComponentName(str, str2);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        if (i != -1) {
            intent.setFlags(i);
        }
        intent.setComponent(componentName);
        this.a.startActivity(intent);
    }
}
