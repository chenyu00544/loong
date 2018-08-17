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
import com.ecjia.hamster.adapter.ECJiaShippingDetailAdapter;
import com.ecjia.hamster.model.ECJia_STOREGOODSLIST;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;

/* compiled from: ECJiaShippingDetailDialog */
public class j {
    public ECJiaShippingDetailAdapter a;
    private final ListView b;
    private Context c;
    private Dialog d;
    private Display e;

    /* compiled from: ECJiaShippingDetailDialog */
    class j_1 implements OnClickListener {
        final /* synthetic */ j a;

        j_1(j jVar) {
            this.a = jVar;
        }

        public void onClick(View view) {
            this.a.d.dismiss();
        }
    }

    @SuppressLint({"NewApi"})
    public j(Context context, ArrayList<ECJia_STOREGOODSLIST> arrayList) {
        this.c = context;
        this.e = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_shipping_fee_detail_layout, null);
        inflate.setMinimumWidth(this.e.getWidth());
        inflate.setAlpha(1.0f);
        this.b = (ListView) inflate.findViewById(R.id.lv_shipping_detail);
        inflate.findViewById(R.id.ll_sure).setOnClickListener(new j_1(this));
        if (arrayList != null && arrayList.size() > 0) {
            this.a = new ECJiaShippingDetailAdapter(context, arrayList);
            this.b.setAdapter(this.a);
            a(this.b);
        }
        this.d = new Dialog(context, R.style.AlertDialogStyle);
        this.d.setContentView(inflate);
        Window window = this.d.getWindow();
        window.setGravity(17);
        LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
    }

    public void a() {
        this.d.show();
    }

    public void a(ListView listView) {
        int height = ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay().getHeight();
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int count;
            if (adapter.getCount() <= 2) {
                count = adapter.getCount();
            } else {
                count = 2;
            }
            int i = 0;
            for (int i2 = 0; i2 < count; i2++) {
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
