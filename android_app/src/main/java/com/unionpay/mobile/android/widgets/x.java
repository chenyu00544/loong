package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.InputDeviceCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class x {
    private static int a = com.unionpay.mobile.android.d.a.r;
    private static int b = 40;
    private static List<Integer> i = new ArrayList(10);
    private Context c = null;
    private OnClickListener d = null;
    private PopupWindow e = null;
    private View f = null;
    private ScrollView g = null;
    private int h = -1;
    private OnDismissListener j = new ab(this);

    private class a extends BaseAdapter {
        final /* synthetic */ x a;

        private a(x xVar) {
            this.a = xVar;
        }

        public final int getCount() {
            return x.i.size() + 2;
        }

        public final Object getItem(int i) {
            return null;
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            int i2 = 10;
            View linearLayout = new LinearLayout(this.a.c);
            Drawable a = c.a(this.a.c).a(1022);
            Drawable a2 = c.a(this.a.c).a(1022);
            linearLayout.setBackgroundDrawable(h.a(a, a2, a2, a));
            linearLayout.setMinimumHeight(g.a(this.a.c, 55.0f));
            linearLayout.setClickable(true);
            linearLayout.setOnClickListener(this.a.d);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            View imageView;
            if (i == 9 || i == 11) {
                imageView = new ImageView(this.a.c);
                if (i != 9) {
                    i2 = 20;
                }
                imageView.setImageDrawable(c.a(this.a.c).a(i == 9 ? 1024 : InputDeviceCompat.SOURCE_GAMEPAD, -1, g.a(this.a.c, TitleBar.BACKBTN_LEFT_MARGIN)));
                linearLayout.setId(i2);
                linearLayout.addView(imageView, layoutParams);
            } else {
                imageView = new TextView(this.a.c);
                imageView.setTextColor(-1);
                imageView.getPaint().setFakeBoldText(true);
                imageView.setTextSize(30.0f);
                imageView.setGravity(17);
                if (i == 10) {
                    i = 9;
                }
                int intValue = ((Integer) x.i.get(i)).intValue();
                linearLayout.setId(intValue);
                imageView.setText(intValue);
                linearLayout.addView(imageView, layoutParams);
            }
            return linearLayout;
        }
    }

    private class b extends LinearLayout {
        final /* synthetic */ x a;

        public b(x xVar, Context context) {
            this.a = xVar;
            super(context);
            setOrientation(1);
            setBackgroundColor(-11316397);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            View linearLayout = new LinearLayout(context);
            int a = g.a(context, 5.0f);
            linearLayout.setPadding(0, a, 0, a);
            linearLayout.setGravity(17);
            linearLayout.setBackgroundColor(-13816531);
            linearLayout.setOrientation(0);
            Drawable a2 = c.a(context).a(m_AppUI.MSG_GET_GL_OK, -1, g.a(context, 24.0f));
            View imageView = new ImageView(context);
            imageView.setImageDrawable(a2);
            linearLayout.addView(imageView);
            addView(linearLayout, layoutParams);
            Collections.shuffle(x.i);
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            linearLayout = new GridView(context);
            linearLayout.setNumColumns(3);
            linearLayout.setAdapter(new a());
            linearLayout.setGravity(17);
            linearLayout.setStretchMode(2);
            linearLayout.setHorizontalScrollBarEnabled(false);
            linearLayout.setVerticalScrollBarEnabled(false);
            linearLayout.setEnabled(true);
            a = g.a(this.a.c, 1.0f);
            linearLayout.setHorizontalSpacing(a);
            linearLayout.setVerticalSpacing(a);
            int i = -a;
            linearLayout.setPadding(i, a, i, i);
            addView(linearLayout, layoutParams);
        }
    }

    static {
        for (int i = 0; i < 10; i++) {
            i.add(Integer.valueOf(i));
        }
    }

    public x(Context context, OnClickListener onClickListener, View view) {
        this.c = context;
        this.d = onClickListener;
        a = g.a(this.c, 55.0f);
        b = g.a(this.c, 40.0f);
        for (ViewParent viewParent = (ViewParent) view; viewParent != null; viewParent = viewParent.getParent()) {
            if (viewParent instanceof ScrollView) {
                this.g = (ScrollView) viewParent;
                k.a("UPWidgetKeyBoard", "mSV : " + this.g.toString());
                k.a("UPWidgetKeyBoard", "mSV H:" + this.g.getHeight());
                this.f = ((ScrollView) viewParent).getChildAt(0);
                break;
            }
        }
        View relativeLayout = new RelativeLayout(context);
        new RelativeLayout.LayoutParams(-1, -2).setMargins(0, 0, 0, 0);
        relativeLayout.setBackgroundColor(-1342177280);
        View relativeLayout2 = new RelativeLayout(context);
        relativeLayout2.setBackgroundColor(-13290188);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        relativeLayout.addView(relativeLayout2, layoutParams);
        relativeLayout2.addView(new b(this, this.c), layoutParams);
        this.e = new PopupWindow(relativeLayout, -1, -2, true);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        this.e.setBackgroundDrawable(new BitmapDrawable());
        this.e.setOutsideTouchable(false);
        this.e.setFocusable(false);
        this.e.setOnDismissListener(this.j);
    }

    private static int d() {
        int i = (a * 4) + b;
        k.c("UPWidgetKeyBoard", "kbH=" + i);
        return i;
    }

    public final void a() {
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    public final void a(View view) {
        if (this.e != null) {
            this.e.showAtLocation(view, 80, 0, 0);
            if (this.f != null) {
                view.setVisibility(0);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.g.getLayoutParams();
                this.h = marginLayoutParams.height;
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                marginLayoutParams.height = ((com.unionpay.mobile.android.d.a.t - rect.top) - com.unionpay.mobile.android.d.a.k) - d();
                k.a("UPWidgetKeyBoard", "height = " + marginLayoutParams.height);
                marginLayoutParams.bottomMargin = d();
                this.g.setLayoutParams(marginLayoutParams);
            }
        }
    }

    public final boolean b() {
        return this.e.isShowing();
    }
}
