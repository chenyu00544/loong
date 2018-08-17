package com.ecjia.hamster.a;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.component.view.ECJiaMyListView;
import com.ecjia.hamster.adapter.h;
import com.ecjia.hamster.model.ECJia_ADSENSE_GROUP;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaEventView */
public class a extends d<ECJia_ADSENSE_GROUP> {
    private LinearLayout d;
    private LinearLayout e;
    private ECJiaMyListView f;
    private h g;

    public a(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.e = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.home_event, null);
        this.d = (LinearLayout) this.e.findViewById(R.id.event_item);
        this.f = (ECJiaMyListView) this.e.findViewById(R.id.lv_event);
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.e);
    }

    public void a(ArrayList<ECJia_ADSENSE_GROUP> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setVisibility(0);
        this.c = arrayList;
        if (this.g == null) {
            this.g = new h(this.a, this.c);
            this.f.setAdapter(this.g);
        }
        this.g.notifyDataSetChanged();
    }
}
