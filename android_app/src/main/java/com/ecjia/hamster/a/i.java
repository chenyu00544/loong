package com.ecjia.hamster.a;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.component.view.ECJiaMyGridView;
import com.ecjia.hamster.adapter.aw;
import com.ecjia.hamster.model.ah;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaQuickEnter */
public class i extends d<ah> {
    private LinearLayout d;
    private LinearLayout e;
    private ECJiaMyGridView f;
    private aw g;

    public i(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.d = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.quick_new_item, null);
        this.e = (LinearLayout) this.d.findViewById(R.id.quick_showview);
        this.f = (ECJiaMyGridView) this.d.findViewById(R.id.quick_gradview);
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.d);
    }

    public void a(ArrayList<ah> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        if (this.g == null) {
            this.g = new aw(this.a, arrayList);
            this.f.setAdapter(this.g);
            return;
        }
        this.g.notifyDataSetChanged();
    }
}
