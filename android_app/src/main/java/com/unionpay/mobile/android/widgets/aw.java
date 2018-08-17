package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.unionpay.mobile.android.utils.p;

final class aw implements OnItemClickListener {
    final /* synthetic */ p a;

    aw(p pVar) {
        this.a = pVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(this.a.d, this.a.s() + this.a.d(), p.f, new Object[]{Integer.valueOf(i)});
        this.a.a(i);
        if (this.a.q != null) {
            this.a.q.dismiss();
        }
    }
}
