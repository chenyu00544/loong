package com.ecjia.component.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaUploadPicDialog */
public class m {
    public ImageView a;
    public ImageView b;
    public TextView c;
    public TextView d;
    public TextView e;
    public TextView f;
    private Dialog g;

    public m(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_upload_pic, null);
        this.g = new Dialog(context, R.style.dialog);
        this.g.setContentView(inflate);
        this.g.setCanceledOnTouchOutside(true);
        this.f = (TextView) inflate.findViewById(R.id.tv_upload_title);
        this.c = (TextView) inflate.findViewById(R.id.tv_upload_cancel);
        this.d = (TextView) inflate.findViewById(R.id.tv_take_photo);
        this.e = (TextView) inflate.findViewById(R.id.tv_take_album);
        this.a = (ImageView) inflate.findViewById(R.id.iv_upload_photo);
        this.b = (ImageView) inflate.findViewById(R.id.iv_sample);
    }

    public void a() {
        this.g.show();
    }

    public void b() {
        this.g.dismiss();
    }
}
