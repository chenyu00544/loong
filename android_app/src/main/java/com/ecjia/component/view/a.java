package com.ecjia.component.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaEditGoodDialog */
public class a {
    public EditText a;
    public Button b;
    public Button c;
    private Dialog d;

    public a(Context context, String str) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shopcart_goodnum_edit, null);
        this.d = new Dialog(context, R.style.dialog);
        this.d.setContentView(inflate);
        this.d.setCanceledOnTouchOutside(false);
        this.a = (EditText) inflate.findViewById(R.id.shop_goods_edit);
        this.a.setText(str);
        this.a.setSelection(str.length());
        this.b = (Button) inflate.findViewById(R.id.shop_goods_cancel);
        this.c = (Button) inflate.findViewById(R.id.shop_goods_ok);
    }

    public void a() {
        this.d.show();
    }

    public void b() {
        this.d.dismiss();
    }
}
