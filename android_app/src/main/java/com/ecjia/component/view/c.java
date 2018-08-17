package com.ecjia.component.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaMyDialog */
public class c {
    public AlertDialog a;
    public LinearLayout b;
    public LinearLayout c;
    public LinearLayout d;
    public LinearLayout e;
    public TextView f;
    public TextView g;
    public TextView h;
    private TextView i;
    private TextView j;
    private Context k;
    private int l = 2;
    private OnClickListener m;
    private OnClickListener n;
    private OnClickListener o;

    public c(Context context, String str, String str2) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_layout_new, null);
        this.k = context;
        this.a = new Builder(context).setCancelable(false).setView(inflate).create();
        this.i = (TextView) inflate.findViewById(R.id.dialog_title);
        this.j = (TextView) inflate.findViewById(R.id.dialog_message);
        this.i.setText(str);
        this.j.setText(str2);
        this.c = (LinearLayout) inflate.findViewById(R.id.version_sure);
        this.h = (TextView) inflate.findViewById(R.id.version_yes);
        this.b = (LinearLayout) inflate.findViewById(R.id.neadpay);
        this.f = (TextView) this.b.findViewById(R.id.yes);
        this.d = (LinearLayout) inflate.findViewById(R.id.unneadpay);
        this.g = (TextView) inflate.findViewById(R.id.no);
        this.e = (LinearLayout) inflate.findViewById(R.id.update_cancel);
    }

    public void a() {
        this.a.show();
    }

    public void b() {
        this.a.dismiss();
    }

    public void c() {
        this.a.setCancelable(false);
    }

    public void a(int i) {
        this.l = i;
        switch (this.l) {
            case 1:
                this.c.setVisibility(0);
                this.e.setVisibility(8);
                return;
            case 2:
                this.c.setVisibility(8);
                this.e.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void a(OnClickListener onClickListener) {
        if (this.l == 1) {
            this.m = onClickListener;
            this.c.setOnClickListener(this.m);
        }
    }

    public void b(OnClickListener onClickListener) {
        if (this.l == 2) {
            this.n = onClickListener;
            this.b.setOnClickListener(this.n);
        }
    }

    public void c(OnClickListener onClickListener) {
        if (this.l == 2) {
            this.o = onClickListener;
            this.d.setOnClickListener(this.o);
        }
    }
}
