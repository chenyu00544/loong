package com.ecjia.component.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.ecjia.a.b.a;
import com.ecjia.a.q;
import com.ecjia.hamster.model.ay;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import org.json.JSONException;

@SuppressLint({"ResourceAsColor", "ShowToast"})
/* compiled from: ECJiaPopMenus */
public class g {
    private ArrayList<ay> a = new ArrayList();
    private Context b;
    private PopupWindow c;
    private LinearLayout d;
    private int e;
    private int f;
    private View g;

    /* compiled from: ECJiaPopMenus */
    class g_1 implements OnDismissListener {
        final /* synthetic */ g a;

        g_1(g gVar) {
            this.a = gVar;
        }

        public void onDismiss() {
        }
    }

    public g(Context context, ArrayList<ay> arrayList, int i, int i2) {
        int i3 = -2;
        this.b = context;
        this.a = arrayList;
        this.e = i;
        this.f = i2;
        this.g = LayoutInflater.from(context).inflate(R.layout.popmenus, null);
        this.g.setLayoutParams(new LayoutParams(-1, -1, 1.0f));
        this.d = (LinearLayout) this.g.findViewById(R.id.layout_subcustommenu);
        try {
            a();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.d.setBackgroundColor(context.getResources().getColor(R.color.white));
        this.d.setFocusableInTouchMode(true);
        this.d.setFocusable(true);
        View view = this.g;
        int i4 = this.e == 0 ? -2 : this.e;
        if (this.f != 0) {
            i3 = this.f;
        }
        this.c = new PopupWindow(view, i4, i3);
    }

    public void a(View view) {
        this.c.setBackgroundDrawable(new ColorDrawable());
        this.g.measure(0, 0);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.c.showAtLocation(view, 83, iArr[0] + 10, view.getHeight() + (view.getHeight() / 8));
        this.c.setOutsideTouchable(true);
        this.c.setFocusable(true);
        this.c.update();
        this.c.setOnDismissListener(new g_1(this));
    }

    void a() throws JSONException {
        int i;
        this.d.removeAllViews();
        int size = this.a.size();
        if (size > 5) {
            i = 5;
        } else {
            i = size;
        }
        for (int i2 = 0; i2 < i; i2++) {
            final ay ayVar = (ay) this.a.get(i2);
            LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.pomenu_menuitem, null);
            this.g.setLayoutParams(new LayoutParams(-1, -1, 1.0f));
            linearLayout.setFocusable(true);
            TextView textView = (TextView) linearLayout.findViewById(R.id.pop_item_textView);
            View findViewById = linearLayout.findViewById(R.id.pop_item_line);
            if (i2 + 1 == this.a.size()) {
                findViewById.setVisibility(8);
            }
            textView.setText(ayVar.a());
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ g b;

                public void onClick(View view) {
                    a.a().a(this.b.b, ayVar.b());
                    q.b("===ECJia_SUB_BUTTON===" + ayVar.b());
                }
            });
            this.d.addView(linearLayout);
        }
        this.d.setVisibility(0);
    }
}
