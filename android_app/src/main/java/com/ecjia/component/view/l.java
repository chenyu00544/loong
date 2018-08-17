package com.ecjia.component.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaToastView_ForReceiveRedpaper */
public class l {
    public static Toast a;

    public l(Context context, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_toast_receive_redpaper, null);
        ((TextView) inflate.findViewById(R.id.toast_text)).setText(i);
        if (a != null) {
            a.cancel();
        }
        a = new Toast(context);
        a.setDuration(0);
        a.setView(inflate);
    }

    public void a(int i, int i2, int i3) {
        a.setGravity(i, i2, i3);
    }

    public void a() {
        a.show();
    }
}
