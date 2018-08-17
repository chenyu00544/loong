package com.ecjia.component.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaToastView */
public class k {
    public static Toast a;

    public k(Context context, String str) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
        ((TextView) inflate.findViewById(R.id.toast_text)).setText(str);
        if (a != null) {
            a.cancel();
        }
        a = new Toast(context);
        a.setGravity(17, 0, 0);
        a.setDuration(0);
        a.setView(inflate);
    }

    public k(Context context, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
        ((TextView) inflate.findViewById(R.id.toast_text)).setText(i);
        if (a != null) {
            a.cancel();
        }
        a = new Toast(context);
        a.setGravity(17, 0, 0);
        a.setDuration(0);
        a.setView(inflate);
    }

    public void a(int i, int i2, int i3) {
        a.setGravity(i, i2, i3);
    }

    public void a(int i) {
        a.setGravity(i, 0, 0);
    }

    public void b(int i) {
        a.setDuration(i);
    }

    public void a() {
        a.show();
    }
}
