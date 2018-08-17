package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.D;
import com.taobao.accs.ErrorCode;

class w implements OnClickListener {
    final /* synthetic */ WearMapView a;

    w(WearMapView wearMapView) {
        this.a = wearMapView;
    }

    public void onClick(View view) {
        D D = this.a.d.a().D();
        D.a += 1.0f;
        this.a.d.a().a(D, (int) ErrorCode.APP_NOT_BIND);
    }
}
