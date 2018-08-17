package com.ecjia.hamster.a;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ecjia.component.view.ECJiaUpMarqueeView;
import com.ecjia.component.view.ECJiaUpMarqueeView.a;
import com.ecjia.component.view.ECJiaUpMarqueeView.b;
import com.ecjia.hamster.activity.ECJiaToplineActivity;
import com.ecjia.hamster.model.bd;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaToplines */
public class l extends d<bd> {
    ArrayList<b> d = new ArrayList();
    int e = 0;
    private LinearLayout f;
    private LinearLayout g;
    private ECJiaUpMarqueeView h;

    /* compiled from: ECJiaToplines */
    class l_1 implements a {
        final /* synthetic */ l a;

        l_1(l lVar) {
            this.a = lVar;
        }

        public void a() {
            this.a.a.startActivity(new Intent(this.a.a, ECJiaToplineActivity.class));
        }

        public void a(int i) {
            this.a.a.startActivity(new Intent(this.a.a, ECJiaToplineActivity.class));
        }

        public void b() {
        }
    }

    public l(Activity activity) {
        super(activity);
    }

    protected void a() {
        super.a();
        this.f = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.layout_home_topline, null);
        this.g = (LinearLayout) this.f.findViewById(R.id.home_topline_in);
        this.h = (ECJiaUpMarqueeView) this.f.findViewById(R.id.upmarqueeview);
        this.h.setMarqueeViewItemClickListener(new l_1(this));
        this.h.setTime(3000);
    }

    public void a(ListView listView) {
        listView.addHeaderView(this.f);
    }

    public void a(ArrayList<bd> arrayList) {
        this.c = arrayList;
        if (arrayList == null || arrayList.size() == 0) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
        this.d.clear();
        int i;
        if (this.c.size() % 2 == 0) {
            for (i = 0; i < this.c.size(); i = (i + 1) + 1) {
                this.d.add(new b(((bd) this.c.get(i)).c(), ((bd) this.c.get(i)).d(), ((bd) this.c.get(i + 1)).c(), ((bd) this.c.get(i + 1)).d()));
            }
        } else {
            for (i = 0; i < this.c.size() / 2; i = (i + 1) + 1) {
                this.d.add(new b(((bd) this.c.get(i)).c(), ((bd) this.c.get(i)).d(), ((bd) this.c.get(i + 1)).c(), ((bd) this.c.get(i + 1)).d()));
            }
            this.d.add(new b(((bd) this.c.get(this.c.size() - 1)).c(), ((bd) this.c.get(this.c.size() - 1)).d(), "", ""));
        }
        this.h.setData(this.d);
    }
}
