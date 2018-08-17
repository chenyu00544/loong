package com.ecjia.component.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

/* compiled from: ECJiaEditPictureDialog */
public class b {
    public TextView a;
    public TextView b;
    public TextView c;
    public TextView d;
    public LinearLayout e;
    private Context f;
    private Dialog g;
    private Display h;

    /* compiled from: ECJiaEditPictureDialog */
    class b_1 implements OnClickListener {
        final /* synthetic */ b a;

        b_1(b bVar) {
            this.a = bVar;
        }

        public void onClick(View view) {
            this.a.g.dismiss();
        }
    }

    @SuppressLint({"NewApi"})
    public b(Context context) {
        this.f = context;
        this.h = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_editheadpicture, null);
        inflate.setMinimumWidth(this.h.getWidth());
        this.a = (TextView) inflate.findViewById(R.id.editheadpicture_take_photo);
        this.b = (TextView) inflate.findViewById(R.id.editheadpicture_from_photos);
        this.d = (TextView) inflate.findViewById(R.id.editheadpicture_title);
        this.e = (LinearLayout) inflate.findViewById(R.id.ll_editheadpicture_title);
        this.c = (TextView) inflate.findViewById(R.id.editheadpicture_cancle);
        this.c.setOnClickListener(new b_1(this));
        this.g = new Dialog(context, R.style.ActionSheetDialogStyle);
        this.g.setContentView(inflate);
        Window window = this.g.getWindow();
        window.setGravity(83);
        LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
    }

    public void a() {
        this.g.show();
    }

    public void b() {
        this.g.dismiss();
    }
}
