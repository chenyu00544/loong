package com.taobao.accs.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.tencent.tauth.AuthActivity;

/* compiled from: Taobao */
public class MsgDistributeService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            ALog.i("MsgDistributeService", "onStartCommand", AuthActivity.ACTION_KEY, intent.getAction());
            if (TextUtils.isEmpty(intent.getAction()) || !TextUtils.equals(intent.getAction(), Constants.ACTION_SEND)) {
                e.a(getApplicationContext(), intent);
                return 2;
            }
            ACCSManager.getAccsInstance(getApplicationContext(), intent.getStringExtra("appKey")).sendRequest(getApplicationContext(), (AccsRequest) intent.getSerializableExtra(Constants.KEY_SEND_REQDATA), intent.getStringExtra(Constants.KEY_PACKAGE_NAME), false);
            return 2;
        } catch (Throwable th) {
            ALog.e("MsgDistributeService", "onStartCommand", th, new Object[0]);
        }
    }
}
