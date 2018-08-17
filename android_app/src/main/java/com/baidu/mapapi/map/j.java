package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.D;
import com.taobao.accs.ErrorCode;

class j implements OnClickListener {
    final /* synthetic */ MapView a;

    j(MapView mapView) {
        this.a = mapView;
    }

    public void onClick(View view) {
        D D = this.a.c.a().D();
        D.a -= 1.0f;
        this.a.c.a().a(D, (int) ErrorCode.APP_NOT_BIND);
    }
}
