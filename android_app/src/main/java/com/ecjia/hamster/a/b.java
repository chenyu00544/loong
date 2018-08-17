package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.component.view.ECJiaMyListView;
import com.ecjia.hamster.activity.ECJiaShopListFragmentActivity;
import com.ecjia.hamster.model.ao;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: ECJiaGoodShopView */
public class b extends d<ao> {
    private LinearLayout d;
    private LinearLayout e;
    private ECJiaMyListView f;
    private LinearLayout g;
    private ImageView h;

    /* compiled from: ECJiaGoodShopView */
    class b_1 implements OnClickListener {
        final /* synthetic */ b a;

        b_1(b bVar) {
            this.a = bVar;
        }

        public void onClick(View view) {
            this.a.a.startActivity(new Intent(this.a.a, ECJiaShopListFragmentActivity.class));
        }
    }

    public b(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.d = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.home_sellers_view, null);
        this.h = (ImageView) this.d.findViewById(R.id.home_seller_icon);
        this.e = (LinearLayout) this.d.findViewById(R.id.home_seller_big_item);
        this.f = (ECJiaMyListView) this.d.findViewById(R.id.home_seller_listview);
        this.g = (LinearLayout) this.d.findViewById(R.id.home_more_shop);
        this.g.setOnClickListener(new b_1(this));
        if (this.a.getResources().getConfiguration().locale.equals(Locale.CHINA)) {
            a((int) R.drawable.goodshop_icon_chinese);
        } else {
            a((int) R.drawable.goodshop_icon_english);
        }
    }

    private void a(int i) {
        this.h.setImageResource(i);
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.d);
    }

    public void a(ArrayList<ao> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.c = arrayList;
    }
}
