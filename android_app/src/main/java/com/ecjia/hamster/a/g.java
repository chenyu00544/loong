package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.hamster.activity.ECJiaPromotionalGoodsActivity;
import com.ecjia.hamster.adapter.x;
import com.ecjia.hamster.model.au;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaNewGoodsView */
public class g extends d<au> {
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private RecyclerView g;
    private x h;

    /* compiled from: ECJiaNewGoodsView */
    class g_1 implements OnClickListener {
        final /* synthetic */ g a;

        g_1(g gVar) {
            this.a = gVar;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a.a, ECJiaPromotionalGoodsActivity.class);
            intent.putExtra("type", "new");
            this.a.a.startActivity(intent);
            this.a.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public g(Activity activity) {
        super(activity);
    }

    protected void a() {
        this.d = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.new_goods_putaway, null);
        this.e = (LinearLayout) this.d.findViewById(R.id.new_goods_putaway_in_layout);
        this.f = (LinearLayout) this.d.findViewById(R.id.newgoods_getmore);
        this.g = (RecyclerView) this.e.findViewById(R.id.horizontallistview1);
        this.e.setVisibility(8);
        this.f.setOnClickListener(new g_1(this));
        LayoutManager linearLayoutManager = new LinearLayoutManager(this.a);
        linearLayoutManager.setOrientation(0);
        this.g.setLayoutManager(linearLayoutManager);
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.d);
    }

    public void a(ArrayList<au> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        if (this.h == null) {
            this.h = new x(this.a, arrayList);
            this.g.setAdapter(this.h);
            return;
        }
        this.h.notifyDataSetChanged();
    }
}
