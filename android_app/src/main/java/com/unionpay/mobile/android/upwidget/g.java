package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.unionpay.mobile.android.d.b;
import java.util.ArrayList;

public final class g extends LinearLayout {
    private Context a;
    private c b;
    private ArrayList<OnItemClickListener> c = new ArrayList();
    private ArrayList<OnClickListener> d = new ArrayList();
    private OnItemClickListener e = new h(this);
    private OnClickListener f = new i(this);

    public g(Context context, c cVar) {
        super(context);
        this.a = context;
        this.b = cVar;
        View relativeLayout = new RelativeLayout(this.a);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setId(linearLayout.hashCode());
        relativeLayout.addView(linearLayout, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        View linearLayout2 = new LinearLayout(this.a);
        layoutParams.addRule(10, -1);
        layoutParams.addRule(2, linearLayout.getId());
        relativeLayout.addView(linearLayout2, layoutParams);
        linearLayout2.setOnClickListener(this.f);
        int a = com.unionpay.mobile.android.utils.g.a(this.a, 1.0f);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        int i = b.a;
        layoutParams2.bottomMargin = i;
        layoutParams2.topMargin = i;
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, a);
        View linearLayout3 = new LinearLayout(this.a);
        linearLayout3.setBackgroundColor(-3355444);
        linearLayout.addView(linearLayout3, layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        linearLayout3 = new ListView(this.a);
        linearLayout3.setDivider(null);
        a(linearLayout3, this.b);
        linearLayout3.setAdapter(this.b);
        linearLayout3.setCacheColorHint(-1);
        linearLayout3.setOnItemClickListener(this.e);
        layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        linearLayout.addView(linearLayout3);
        addView(relativeLayout);
    }

    private void a(ListView listView, ListAdapter listAdapter) {
        if (listView != null && listAdapter != null) {
            LayoutParams layoutParams = listView.getLayoutParams();
            if (layoutParams != null) {
                Rect rect = new Rect();
                ((Activity) this.a).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int height = rect.height();
                int i = 0;
                for (int i2 = 0; i2 < listAdapter.getCount(); i2++) {
                    View view = listAdapter.getView(i2, null, listView);
                    view.measure(0, 0);
                    i += view.getMeasuredHeight();
                    if (i > height) {
                        break;
                    }
                }
                layoutParams.height = Math.min(i, height);
                listView.setLayoutParams(layoutParams);
            }
        }
    }

    public final void a(OnClickListener onClickListener) {
        this.d.add(onClickListener);
    }

    public final void a(OnItemClickListener onItemClickListener) {
        this.c.add(onItemClickListener);
    }
}
