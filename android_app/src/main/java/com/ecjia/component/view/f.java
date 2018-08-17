package com.ecjia.component.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaPayToastView */
public class f {
    public static Toast a;

    public f(Context context, String str, boolean z) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pay_toast, null);
        TextView textView = (TextView) inflate.findViewById(R.id.paysuccess_text);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.pay_image_success);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.pay_image_fail);
        if (z) {
            imageView.setVisibility(8);
            imageView2.setVisibility(0);
        }
        textView.setText(str);
        if (a != null) {
            a.cancel();
        }
        textView.setText(str);
        if (a != null) {
            a.cancel();
        }
        a = new Toast(context);
        a.setDuration(0);
        a.setView(inflate);
    }

    public void a(int i, int i2, int i3) {
        a.setGravity(17, 0, 0);
    }

    public void a() {
        a.show();
    }
}
