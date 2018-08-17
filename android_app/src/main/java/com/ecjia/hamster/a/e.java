package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.component.view.ECJiaMyGridView;
import com.ecjia.hamster.activity.ECJiaMobilebuyGoodsActivity;
import com.ecjia.hamster.adapter.ah;
import com.ecjia.hamster.model.y;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaMobileBuyView */
public class e extends d<y> {
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private ECJiaMyGridView g;
    private ah h;

    /* compiled from: ECJiaMobileBuyView */
    class e_1 implements OnClickListener {
        final /* synthetic */ e a;

        e_1(e eVar) {
            this.a = eVar;
        }

        public void onClick(View view) {
            this.a.a.startActivity(new Intent(this.a.a, ECJiaMobilebuyGoodsActivity.class));
            this.a.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public e(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.d = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.home_mobile_buy, null);
        this.e = (LinearLayout) this.d.findViewById(R.id.homefragment_mb);
        this.f = (LinearLayout) this.d.findViewById(R.id.mobile_getmore);
        this.f.setOnClickListener(new e_1(this));
        this.g = (ECJiaMyGridView) this.d.findViewById(R.id.mymb_item);
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.d);
    }

    public void a(ArrayList<y> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        if (this.h == null) {
            this.h = new ah(this.a);
            this.h.a((ArrayList) arrayList);
            this.g.setAdapter(this.h);
            return;
        }
        this.h.notifyDataSetChanged();
    }
}
