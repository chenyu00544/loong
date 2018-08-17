package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.utils.g;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class u extends LinearLayout implements OnClickListener {
    private Context a = null;
    private EditText b = null;
    private ImageView c = null;
    private boolean d = true;
    private b e = null;
    private a f = null;
    private int g;
    private Drawable h;
    private TextView i = null;
    private LinearLayout j;
    private OnClickListener k = new ba(this);
    private TextWatcher l = new bb(this);
    private OnFocusChangeListener m = new bc(this);

    public interface a {
        void a(boolean z);
    }

    public interface b extends a {
        void a_();

        void e();
    }

    public u(Context context) {
        super(context);
        this.a = context;
        setOrientation(0);
        this.g = com.unionpay.mobile.android.d.a.n;
        setLayoutParams(new LayoutParams(-1, -1));
        setFocusable(true);
        View textView = new TextView(this.a);
        textView.setPadding(g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN), 0, 0, 0);
        textView.setEms(4);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -1);
        layoutParams.gravity = 19;
        addView(textView, layoutParams);
        textView.setGravity(19);
        textView.setTextSize(com.unionpay.mobile.android.d.b.k);
        textView.setTextColor(-13421773);
        this.i = textView;
        this.i.setVisibility(8);
        textView = new RelativeLayout(this.a);
        textView.setGravity(21);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams.gravity = 19;
        addView(textView, layoutParams2);
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setGravity(21);
        linearLayout.setId(linearLayout.hashCode());
        layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(11, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.rightMargin = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
        linearLayout.setVisibility(8);
        textView.addView(linearLayout, layoutParams2);
        this.j = linearLayout;
        View relativeLayout = new RelativeLayout(this.a);
        relativeLayout.setGravity(16);
        ViewGroup.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        layoutParams3.addRule(0, linearLayout.getId());
        layoutParams3.rightMargin = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
        textView.addView(relativeLayout, layoutParams3);
        this.c = new ImageView(this.a);
        this.c.setId(this.c.hashCode());
        this.c.setBackgroundDrawable(this.h);
        this.c.setOnClickListener(this.k);
        this.c.setVisibility(8);
        this.c.setId(this.c.hashCode());
        this.c.setAdjustViewBounds(true);
        int a = g.a(this.a, 30.0f);
        layoutParams = new RelativeLayout.LayoutParams(a, a);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        relativeLayout.addView(this.c, layoutParams);
        this.b = new EditText(this.a);
        this.b.setSingleLine();
        this.b.setTextSize(com.unionpay.mobile.android.d.b.k);
        this.b.setTextColor(-10066330);
        this.b.setHintTextColor(-6710887);
        this.b.setBackgroundDrawable(null);
        this.b.setGravity(16);
        this.b.setPadding(g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN), 0, 0, 0);
        this.b.addTextChangedListener(this.l);
        if (this.d) {
            this.b.setOnFocusChangeListener(this.m);
        }
        ViewGroup.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(0, this.c.getId());
        layoutParams4.addRule(15, -1);
        layoutParams4.addRule(9, -1);
        relativeLayout.addView(this.b, layoutParams4);
    }

    static /* synthetic */ boolean b(u uVar) {
        return (uVar.b == null || uVar.b().length() == 0 || !uVar.d) ? false : true;
    }

    static /* synthetic */ void g() {
    }

    public final TextView a(String str) {
        if (!(this.i == null || TextUtils.isEmpty(str))) {
            this.i.setVisibility(0);
            this.i.setText(str);
        }
        return this.i;
    }

    public final u a(Drawable drawable) {
        if (drawable != null) {
            this.c.setBackgroundDrawable(drawable);
        }
        return this;
    }

    public final void a() {
        this.d = false;
        if (this.b != null) {
            this.b.setKeyListener(null);
            this.b.setFocusable(false);
            if (this.c != null && this.c.getVisibility() == 0) {
                this.c.setVisibility(8);
            }
        }
    }

    public final void a(int i) {
        if (this.b != null) {
            this.b.setInputType(i);
        }
    }

    public final void a(InputFilter inputFilter) {
        Object obj = new InputFilter[]{inputFilter};
        if (this.b != null) {
            Object filters = this.b.getFilters();
            if (filters == null) {
                this.b.setFilters(obj);
                return;
            }
            Object obj2 = new InputFilter[(filters.length + obj.length)];
            System.arraycopy(filters, 0, obj2, 0, filters.length);
            System.arraycopy(obj, 0, obj2, filters.length, obj.length);
            this.b.setFilters(obj2);
        }
    }

    public final void a(TextWatcher textWatcher) {
        if (this.b != null && textWatcher != null) {
            this.b.addTextChangedListener(textWatcher);
        }
    }

    public final void a(View view, LayoutParams layoutParams) {
        if (view != null && this.j != null) {
            this.j.addView(view, layoutParams);
            this.j.setVisibility(0);
        }
    }

    public final void a(OnEditorActionListener onEditorActionListener) {
        if (this.b != null && this.d) {
            this.b.setOnEditorActionListener(onEditorActionListener);
        }
    }

    public final void a(a aVar) {
        this.f = aVar;
    }

    public final void a(b bVar) {
        this.e = bVar;
        if (this.b != null) {
            this.b.setOnClickListener(this);
        }
    }

    public final String b() {
        return this.b != null ? this.b.getText().toString() : null;
    }

    public final void b(int i) {
        if (this.b != null) {
            this.b.setSelection(i);
        }
    }

    public final void b(String str) {
        if (this.b != null && str != null) {
            this.b.setHint(str);
        }
    }

    public final Editable c() {
        return this.b != null ? this.b.getText() : null;
    }

    public final void c(String str) {
        if (this.b != null && str != null) {
            this.b.setText(str);
        }
    }

    public final void d() {
        if (this.b != null) {
            this.b.setLongClickable(false);
        }
    }

    public final void e() {
        if (this.b != null) {
            this.b.setText("");
            if (this.e != null) {
                this.e.e();
            }
        }
    }

    public final void f() {
        ((Activity) this.a).getWindow().setSoftInputMode(3);
        int i = VERSION.SDK_INT;
        String str = null;
        if (i >= 16) {
            str = "setShowSoftInputOnFocus";
        } else if (i >= 14) {
            str = "setSoftInputShownOnFocus";
        }
        if (str == null) {
            this.b.setInputType(0);
            return;
        }
        try {
            Method method = EditText.class.getMethod(str, new Class[]{Boolean.TYPE});
            method.setAccessible(true);
            method.invoke(this.b, new Object[]{Boolean.valueOf(false)});
        } catch (NoSuchMethodException e) {
            this.b.setInputType(0);
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    public final void onClick(View view) {
        if (this.e != null) {
            this.e.a_();
        }
    }

    public final void setOnTouchListener(OnTouchListener onTouchListener) {
        if (this.b != null) {
            this.b.setOnTouchListener(onTouchListener);
        }
    }
}
