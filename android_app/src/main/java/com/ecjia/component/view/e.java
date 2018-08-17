package com.ecjia.component.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaOrderCancelDialog */
public class e {
    public TextView a;
    public TextView b;
    private Dialog c;

    public e(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dailog_order_cancel, null);
        inflate.setMinimumWidth(((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth());
        this.c = new Dialog(context, R.style.ActionSheetDialogStyle);
        this.c.setContentView(inflate);
        this.c.setCanceledOnTouchOutside(false);
        this.a = (TextView) inflate.findViewById(R.id.cancel);
        this.b = (TextView) inflate.findViewById(R.id.sure);
        Window window = this.c.getWindow();
        window.setGravity(80);
        LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
    }

    public void a() {
        this.c.show();
    }

    public void b() {
        this.c.dismiss();
    }
}
