package com.unionpay.mobile.android.widgets;

import android.view.ViewGroup.MarginLayoutParams;
import android.widget.PopupWindow.OnDismissListener;
import com.unionpay.mobile.android.d.a;

final class ab implements OnDismissListener {
    final /* synthetic */ x a;

    ab(x xVar) {
        this.a = xVar;
    }

    public final void onDismiss() {
        if (this.a.g != null) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.a.g.getLayoutParams();
            marginLayoutParams.bottomMargin = a.b;
            marginLayoutParams.height = this.a.h;
            this.a.g.setLayoutParams(marginLayoutParams);
        }
    }
}
