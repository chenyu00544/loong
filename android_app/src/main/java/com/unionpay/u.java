package com.unionpay;

import com.unionpay.utils.UPUtils;

final class u implements d {
    final /* synthetic */ UPPayWapActivity a;

    u(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void a(String str, e eVar) {
        String a = UPUtils.a(this.a, str);
        if (eVar != null) {
            UPPayWapActivity uPPayWapActivity = this.a;
            eVar.a(UPPayWapActivity.b("0", "success", a));
        }
    }
}
