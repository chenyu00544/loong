package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.D;
import com.taobao.accs.ErrorCode;

class s implements OnClickListener {
    final /* synthetic */ TextureMapView a;

    s(TextureMapView textureMapView) {
        this.a = textureMapView;
    }

    public void onClick(View view) {
        D D = this.a.b.b().D();
        D.a += 1.0f;
        this.a.b.b().a(D, (int) ErrorCode.APP_NOT_BIND);
    }
}
