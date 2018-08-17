package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.hamster.activity.ECJiaGoodsDetailActivity;
import com.ecjia.hamster.activity.ECJiaPromotionalGoodsActivity;
import com.ecjia.hamster.adaptercell.ECJiaMyHotCell;
import com.ecjia.hamster.model.au;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/* compiled from: ECJiaPromotionView */
public class h extends d<au> {
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout g;

    /* compiled from: ECJiaPromotionView */
    class h_1 implements OnClickListener {
        final /* synthetic */ h a;

        h_1(h hVar) {
            this.a = hVar;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a.a, ECJiaPromotionalGoodsActivity.class);
            intent.putExtra("type", "promotion");
            this.a.a.startActivity(intent);
            this.a.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public h(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.f = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.home_hotsell, null);
        this.g = (LinearLayout) this.f.findViewById(R.id.home_promote_getmore);
        this.g.setOnClickListener(new h_1(this));
        this.d = (LinearLayout) this.f.findViewById(R.id.hot_big_item);
        this.e = (LinearLayout) this.f.findViewById(R.id.myhot_item);
    }

    private void c() {
        this.e.removeAllViews();
        if (this.c.size() > 0) {
            this.e.setVisibility(0);
            for (int i = 0; i < this.c.size(); i += 2) {
                final au auVar;
                ECJiaMyHotCell eCJiaMyHotCell = (ECJiaMyHotCell) LayoutInflater.from(this.a).inflate(R.layout.home_myhotcell2, null);
                eCJiaMyHotCell.cellinit();
                if (this.c.size() > 0) {
                    auVar = (au) this.c.get(i);
                    if (!(auVar == null || auVar.i() == null || auVar.i().getThumb() == null || auVar.i().getSmall() == null)) {
                        ImageLoader.getInstance().displayImage(auVar.i().getThumb(), eCJiaMyHotCell.good_cell_photo_one);
                    }
                    if (auVar.d() == null || auVar.d().length() <= 0) {
                        eCJiaMyHotCell.good_cell_price_one.setText(auVar.e());
                    } else {
                        eCJiaMyHotCell.good_cell_price_one.setText(auVar.d());
                    }
                    eCJiaMyHotCell.good_info_one.setText(auVar.g());
                    eCJiaMyHotCell.good_cell_one.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ h b;

                        public void onClick(View view) {
                            Intent intent = new Intent(this.b.a, ECJiaGoodsDetailActivity.class);
                            intent.putExtra("goods_id", auVar.c() + "");
                            this.b.a.startActivity(intent);
                            this.b.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        }
                    });
                }
                if (i < this.c.size() - 1) {
                    auVar = (au) this.c.get(i + 1);
                    if (!(auVar == null || auVar.i() == null || auVar.i().getThumb() == null || auVar.i().getSmall() == null)) {
                        ImageLoader.getInstance().displayImage(auVar.i().getThumb(), eCJiaMyHotCell.good_cell_photo_two);
                    }
                    if (auVar.d() == null || auVar.d().length() <= 0) {
                        eCJiaMyHotCell.good_cell_price_two.setText(auVar.e());
                    } else {
                        eCJiaMyHotCell.good_cell_price_two.setText(auVar.d());
                    }
                    eCJiaMyHotCell.good_info_two.setText(auVar.g());
                    eCJiaMyHotCell.good_cell_two.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ h b;

                        public void onClick(View view) {
                            Intent intent = new Intent(this.b.a, ECJiaGoodsDetailActivity.class);
                            intent.putExtra("goods_id", auVar.c() + "");
                            this.b.a.startActivity(intent);
                            this.b.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                        }
                    });
                } else {
                    eCJiaMyHotCell.good_cell_two.setVisibility(4);
                }
                this.e.addView(eCJiaMyHotCell);
            }
            return;
        }
        if (this.c != null) {
            this.c.clear();
        }
        this.e.removeAllViews();
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.f);
    }

    public void a(ArrayList<au> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setVisibility(0);
        this.c = arrayList;
        c();
    }
}
