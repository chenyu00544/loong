package com.unionpay.mobile.android.upwidget;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import java.util.Iterator;

final class p implements OnClickListener {
    final /* synthetic */ j a;

    p(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue != this.a.o) {
            this.a.a(intValue);
            if (this.a.e && !TextUtils.isEmpty(this.a.f[intValue].d)) {
                view.setTag(this.a.f[intValue].d);
                Iterator it = this.a.w.iterator();
                while (it.hasNext()) {
                    ((OnClickListener) it.next()).onClick(view);
                }
                this.a.a((LinearLayout) this.a.f[intValue].c, true, "正在查询。。。", null, null);
                this.a.e = false;
            }
            view.setTag(Integer.valueOf(intValue));
        }
    }
}
