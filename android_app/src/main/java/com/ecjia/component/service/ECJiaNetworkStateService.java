package com.ecjia.component.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import com.baidu.location.h.c;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;

public class ECJiaNetworkStateService extends Service {
    private ConnectivityManager a;
    private NetworkInfo b;
    private SharedPreferences c;
    private Editor d;
    private BroadcastReceiver e = new ECJiaNetworkStateService_1(this);

    class ECJiaNetworkStateService_1 extends BroadcastReceiver {
        final /* synthetic */ ECJiaNetworkStateService a;

        ECJiaNetworkStateService_1(ECJiaNetworkStateService eCJiaNetworkStateService) {
            this.a = eCJiaNetworkStateService;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                this.a.a = (ConnectivityManager) this.a.getSystemService("connectivity");
                this.a.b = this.a.a.getActiveNetworkInfo();
                if (this.a.b != null && this.a.b.isAvailable()) {
                    if (this.a.b.getTypeName().equals(c.do)) {
                        this.a.d.putString(anet.channel.strategy.dispatch.c.NET_TYPE, UtilityImpl.NET_TYPE_WIFI);
                        this.a.d.commit();
                        return;
                    }
                    this.a.d.putString(anet.channel.strategy.dispatch.c.NET_TYPE, UtilityImpl.NET_TYPE_3G);
                    this.a.d.commit();
                }
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.c = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.d = this.c.edit();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.e, intentFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.e);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
