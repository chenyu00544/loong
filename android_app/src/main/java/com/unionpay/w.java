package com.unionpay;

final class w implements d {
    final /* synthetic */ UPPayWapActivity a;

    w(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    public final void a(String str, e eVar) {
        UPPayWapActivity.a(this.a, Boolean.parseBoolean(str));
        if (eVar != null) {
            UPPayWapActivity uPPayWapActivity = this.a;
            eVar.a(UPPayWapActivity.b("0", "success", null));
        }
    }
}
