package com.ecjia.component.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ecjia.hamster.adapter.ax;
import com.ecjia.hamster.adapter.ax.a;
import com.ecjia.hamster.model.ECJia_GOODS_COUPON;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaRedpaperDialog */
public class i {
    public ax a;
    a b;
    private final ListView c;
    private Context d;
    private Dialog e;
    private Display f;

    /* compiled from: ECJiaRedpaperDialog */
    class i_1 implements OnClickListener {
        final /* synthetic */ i a;

        i_1(i iVar) {
            this.a = iVar;
        }

        public void onClick(View view) {
            this.a.e.dismiss();
        }
    }

    @SuppressLint({"NewApi"})
    public i(Context context, ArrayList<ECJia_GOODS_COUPON> arrayList) {
        this.d = context;
        this.f = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_receive_redpaper, null);
        inflate.setMinimumWidth(this.f.getWidth());
        inflate.setAlpha(1.0f);
        this.c = (ListView) inflate.findViewById(R.id.redpaper_list);
        inflate.findViewById(R.id.redpaper_close).setOnClickListener(new i_1(this));
        if (arrayList != null && arrayList.size() > 0) {
            this.a = new ax(context, arrayList);
            this.a.a(this.b);
            this.c.setAdapter(this.a);
            a(this.c);
        }
        this.e = new Dialog(context, R.style.ActionSheetDialogStyle);
        this.e.setContentView(inflate);
        Window window = this.e.getWindow();
        window.setGravity(80);
        LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
    }

    public void a() {
        this.e.show();
    }

    public void a(ListView listView) {
        int height = ((WindowManager) this.d.getSystemService("window")).getDefaultDisplay().getHeight();
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
            if (height - i < ((int) ((((double) height) * 14.0d) / 25.0d))) {
                layoutParams.height = height - ((int) ((((double) height) * 14.0d) / 25.0d));
            }
            listView.setLayoutParams(layoutParams);
        }
    }
}
