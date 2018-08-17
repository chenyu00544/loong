package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.baidu.mapapi.map.WeightedLatLng;
import com.ecjia.a.b.a;
import com.ecjia.component.view.banner.ECJiaBannerView;
import com.ecjia.component.view.banner.ECJiaBannerView.b;
import com.ecjia.component.view.banner.ECJiaBannerView.c;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaGoodsListActivity;
import com.ecjia.hamster.model.ECJia_PHOTO;
import com.ecjia.hamster.model.ad;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import java.util.ArrayList;

/* compiled from: ECJiaNewBannerView */
public class f extends d<ad> {
    private LinearLayout d;
    private RelativeLayout e;
    private ECJiaBannerView<ECJia_PHOTO> f;
    private ArrayList<ECJia_PHOTO> g;

    /* compiled from: ECJiaNewBannerView */
    class f_1 implements c<ECJia_PHOTO> {
        final /* synthetic */ f a;

        f_1(f fVar) {
            this.a = fVar;
        }

        public void a(ImageView imageView, ECJia_PHOTO eCJia_PHOTO) {
            ImageLoader.getInstance().displayImage(eCJia_PHOTO.getUrl(), imageView);
        }
    }

    /* compiled from: ECJiaNewBannerView */
    class f_2 implements b {
        final /* synthetic */ f a;

        f_2(f fVar) {
            this.a = fVar;
        }

        public void a(View view, int i) {
            ad adVar = (ad) this.a.c.get(i);
            if (adVar.c() == null) {
                if (adVar.b() != null) {
                    a.a().a(this.a.a, adVar.b());
                }
            } else if (adVar.c().equals("goods")) {
                r1 = new Intent(this.a.a, ECJiaGoodsDetailActivity.class);
                r1.putExtra("goods_id", adVar.d() + "");
                this.a.a.startActivity(r1);
                this.a.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            } else if (adVar.c().equals(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY)) {
                r1 = new Intent(this.a.a, ECJiaGoodsListActivity.class);
                r1.putExtra("category_id", adVar.d() + "");
                this.a.a.startActivity(r1);
                this.a.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            } else if (adVar.b() != null) {
                a.a().a(this.a.a, adVar.b());
            }
        }
    }

    public f(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.d = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.new_banner_scroll_view, null);
        this.e = (RelativeLayout) this.d.findViewById(R.id.banner_layout_in);
        this.f = (ECJiaBannerView) this.d.findViewById(R.id.home_banner);
        LayoutParams layoutParams = this.f.getLayoutParams();
        layoutParams.width = b();
        layoutParams.height = (int) (((((double) layoutParams.width) * WeightedLatLng.DEFAULT_INTENSITY) / 16.0d) * 9.0d);
        this.f.setLayoutParams(layoutParams);
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.d);
    }

    public void a(ArrayList<ad> arrayList) {
        if (arrayList != null || arrayList.size() != 0) {
            this.c = arrayList;
            c();
        }
    }

    public void c() {
        int size = this.c.size();
        this.g = new ArrayList();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.g.add(((ad) this.c.get(i)).a());
            }
            this.f.setBannerStyle(1);
            this.f.setImages(this.g);
            this.f.setOnBannerImageListener(new f_1(this));
            this.f.setOnBannerClickListener(new f_2(this));
        }
    }

    public void d() {
        this.f.setAutoPlayEnable(true);
        this.f.setDelayTime(5000);
        this.f.isAutoPlay(true);
    }
}
