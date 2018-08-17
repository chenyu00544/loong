package com.ecjia.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.ecjia.component.a.n;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.ECJiaShareActivity;
import com.ecjia.hamster.model.ECJia_PHOTO;
import com.ecmoban.android.missmall.R;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.media.UMImage;
import org.apache.commons.lang3.c;

/* compiled from: ECJiaShareHelper */
public class z {
    private Context a;
    private n b;

    public static void a() {
        PlatformConfig.setSinaWeibo("", "");
        PlatformConfig.setWeixin("wx38b6c8356cdad915", "822c818edbc3b1d2ceebaf172ab54dc7");
        PlatformConfig.setQQZone("101447794", "f52b6c498930258cdd81a6f2f97110ae");
    }

    public z(Context context, n nVar) {
        this.a = context;
        this.b = nVar;
    }

    public boolean a(UMImage uMImage) {
        return uMImage == new UMImage(this.a, BitmapFactory.decodeResource(this.a.getResources(), R.drawable.umeng_socialize_share_pic));
    }

    public void b() {
        String string = this.a.getResources().getString(R.string.detail_refresh_info);
        String string2 = this.a.getResources().getString(R.string.detail_empty_img);
        String string3 = this.a.getResources().getString(R.string.detail_sale);
        String string4 = this.a.getResources().getString(R.string.detail_good);
        String string5 = this.a.getResources().getString(R.string.detail_no_network);
        String string6 = this.a.getResources().getString(R.string.app_name);
        k kVar;
        if (!d.a(this.a)) {
            kVar = new k(this.a, string5);
            kVar.a(17, 0, 0);
            kVar.a();
        } else if ((c.a(this.b.b.n().getSmall()) && c.a(this.b.b.n().getThumb()) && c.a(this.b.b.n().getThumb())) || c.a(this.b.b.p())) {
            k kVar2 = new k(this.a, string);
            kVar2.a(17, 0, 0);
            kVar2.a();
        } else {
            String p = this.b.b.p();
            ECJia_PHOTO n = this.b.b.n();
            for (Object obj : new String[]{n.getSmall(), n.getThumb(), n.getUrl()}) {
                if (!a(new UMImage(this.a, (String) obj))) {
                    break;
                }
            }
            Object obj2 = null;
            if (TextUtils.isEmpty(obj2)) {
                kVar = new k(this.a, string2);
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            }
            string5 = "";
            this.b.b.c() + this.b.b.t();
            string5 = string6 + string3 + p + string4;
            Intent intent = new Intent(this.a, ECJiaShareActivity.class);
            intent.putExtra("share_content", string5);
            intent.putExtra("share_image_url", obj2);
            intent.putExtra("share_goods_url", this.b.b.c());
            intent.putExtra("share_goods_name", p);
            this.a.startActivity(intent);
            ((Activity) this.a).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        }
    }
}
