package com.umeng.socialize.view;

import android.os.Handler;
import android.os.Message;

/* compiled from: OauthDialog */
class a extends Handler {
    final /* synthetic */ OauthDialog a;

    a(OauthDialog oauthDialog) {
        this.a = oauthDialog;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 1 && this.a.e != null) {
            this.a.e.setVisibility(8);
        }
        if (message.what != 2) {
        }
    }
}
