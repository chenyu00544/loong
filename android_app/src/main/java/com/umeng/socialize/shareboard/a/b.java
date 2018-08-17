package com.umeng.socialize.shareboard.a;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.DeviceConfig;

/* compiled from: SNSPlatformAdapter */
class b implements OnClickListener {
    final /* synthetic */ SnsPlatform a;
    final /* synthetic */ a b;

    b(a aVar, SnsPlatform snsPlatform) {
        this.b = aVar;
        this.a = snsPlatform;
    }

    public void onClick(View view) {
        this.b.c.dismiss();
        SHARE_MEDIA share_media = this.a.mPlatform;
        if (DeviceConfig.isNetworkAvailable(this.b.b) || share_media == SHARE_MEDIA.SMS) {
            this.b.a(this.a, share_media);
        } else {
            Toast.makeText(this.b.b, "您的网络不可用,请检查网络连接...", 0).show();
        }
    }
}
