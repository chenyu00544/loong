package com.umeng.socialize.editorpage;

import android.location.Location;
import android.widget.Toast;
import com.umeng.socialize.bean.UMLocation;
import com.umeng.socialize.editorpage.location.a;
import com.umeng.socialize.editorpage.location.b;
import com.umeng.socialize.utils.Log;

/* compiled from: ShareActivity */
class f extends b {
    final /* synthetic */ ShareActivity a;

    f(ShareActivity shareActivity, a aVar) {
        this.a = shareActivity;
        super(aVar);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Location) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.a.a(true);
    }

    protected void a(Location location) {
        super.onPostExecute(location);
        Log.e("xxxxx", "result = " + location);
        this.a.C = UMLocation.build(location);
        this.a.a(false);
        if (location == null && !this.a.isFinishing()) {
            Toast.makeText(this.a.y, "获取地理位置失败，请稍候重试.", 0).show();
        }
    }

    protected void onCancelled() {
        super.onCancelled();
        this.a.a(false);
    }
}
