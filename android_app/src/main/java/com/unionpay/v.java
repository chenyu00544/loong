package com.unionpay;

import com.unionpay.utils.UPUtils;

final class v implements d {
    final /* synthetic */ UPPayWapActivity a;

    v(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void a(String str, e eVar) {
        UPUtils.b(this.a, str);
        if (eVar != null) {
            UPPayWapActivity uPPayWapActivity = this.a;
            eVar.a(UPPayWapActivity.b("0", "success", null));
        }
    }
}
