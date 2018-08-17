package com.ecjia.component.view;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaMyProgressDialog */
public class d extends Dialog {
    private static d b = null;
    private Context a = null;

    public d(Context context, int i) {
        super(context, i);
    }

    public static d a(Context context) {
        b = new d(context, R.style.CustomProgressDialog);
        b.setContentView(R.layout.customprogressdialog);
        LayoutParams attributes = b.getWindow().getAttributes();
        b.getWindow().setGravity(1);
        attributes.y = -100;
        b.getWindow().setAttributes(attributes);
        return b;
    }

    public void onWindowFocusChanged(boolean z) {
        if (b != null) {
        }
    }

    public d a(String str) {
        TextView textView = (TextView) b.findViewById(R.id.id_tv_loadingmsg);
        if (textView != null) {
            textView.setText(str);
        }
        return b;
    }
}
