package com.taobao.accs.net;

import android.content.Context;
import android.content.Intent;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.a;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.b;

/* compiled from: Taobao */
class e implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    e(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public void run() {
        Intent intent = new Intent(Constants.ACTION_START_SERVICE);
        intent.putExtra("appKey", this.b.i());
        intent.putExtra(Constants.KEY_TTID, this.b.a);
        intent.putExtra(Constants.KEY_PACKAGE_NAME, this.a.getPackageName());
        intent.putExtra("app_sercet", this.b.h.getAppSecret());
        intent.putExtra(Constants.KEY_MODE, AccsClientConfig.mEnv);
        intent.putExtra(b.PROPERTY_APP_KEY, b.a(this.a));
        intent.setClassName(this.a.getPackageName(), a.channelService);
        this.a.startService(intent);
        intent = new Intent();
        intent.setAction(AgooConstants.INTENT_FROM_AGOO_REPORT);
        intent.setPackage(this.a.getPackageName());
        intent.setClassName(this.a.getPackageName(), com.taobao.accs.client.a.a(this.a.getPackageName()));
        this.a.startService(intent);
    }
}
