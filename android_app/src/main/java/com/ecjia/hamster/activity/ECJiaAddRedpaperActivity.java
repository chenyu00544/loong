package com.ecjia.hamster.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.ecjia.a.a.b;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.ab;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.h;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import de.greenrobot.event.c;

public class ECJiaAddRedpaperActivity extends a implements a, h.a {
    Handler a = new ECJiaAddRedpaperActivity_1(this);
    private EditText b;
    private Button c;
    private ab d;
    private h e;
    private LinearLayout k;
    private View l;

    class ECJiaAddRedpaperActivity_1 extends Handler {
        final /* synthetic */ ECJiaAddRedpaperActivity a;

        ECJiaAddRedpaperActivity_1(ECJiaAddRedpaperActivity eCJiaAddRedpaperActivity) {
            this.a = eCJiaAddRedpaperActivity;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.a.b.requestFocus();
            ((InputMethodManager) this.a.getSystemService("input_method")).toggleSoftInput(0, 2);
        }
    }

    class ECJiaAddRedpaperActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaAddRedpaperActivity a;

        ECJiaAddRedpaperActivity_2(ECJiaAddRedpaperActivity eCJiaAddRedpaperActivity) {
            this.a = eCJiaAddRedpaperActivity;
        }

        public void onClick(View view) {
            if (TextUtils.isEmpty(this.a.b.getText().toString())) {
                k kVar = new k(this.a, this.a.g.getString(R.string.num_null));
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            }
            this.a.b();
            this.a.d.a(this.a.b.getText().toString());
        }
    }

    class ECJiaAddRedpaperActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaAddRedpaperActivity a;

        ECJiaAddRedpaperActivity_3(ECJiaAddRedpaperActivity eCJiaAddRedpaperActivity) {
            this.a = eCJiaAddRedpaperActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_redpaper);
        c.a().a((Object) this);
        c();
    }

    private void c() {
        a();
        this.k = (LinearLayout) findViewById(R.id.root_view);
        this.l = findViewById(R.id.buttom_view);
        this.d = new ab(this);
        this.d.a((a) this);
        this.b = (EditText) findViewById(R.id.add_red_paper_input);
        this.c = (Button) findViewById(R.id.add_redpaper_ok);
        this.c.setOnClickListener(new ECJiaAddRedpaperActivity_2(this));
        this.e = new h(this);
        this.e.a((h.a) this);
        a(this.k, this.l);
    }

    public void a() {
        this.i = (ECJiaTopView) findViewById(R.id.addredpaper_topview);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaAddRedpaperActivity_3(this));
        this.i.setRightType(13);
        this.i.setTitleText((int) R.string.mypurse_add_redpper);
    }

    public void a(View view, int i) {
        if (i == 1) {
            this.a.sendMessageDelayed(new Message(), 200);
        } else if (i == 2) {
            this.d.b(this.b.getText().toString());
        } else if (i == 3) {
            this.b.setText("");
            c.a().c(new b("red_paper_refresh"));
        }
    }

    public void onEvent(b bVar) {
    }

    public void b() {
        this.b.clearFocus();
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.b.getWindowToken(), 0);
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    private void a(final View view, final View view2) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ ECJiaAddRedpaperActivity c;

            public void onGlobalLayout() {
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                if (view.getRootView().getHeight() - rect.bottom > 100) {
                    int[] iArr = new int[2];
                    view2.getLocationInWindow(iArr);
                    view.scrollTo(0, (iArr[1] + view2.getHeight()) - rect.bottom);
                    return;
                }
                view.scrollTo(0, 0);
            }
        });
    }

    public void a(String str, String str2, ax axVar) {
        if (str == "bonus/validate") {
            if (axVar.b() == 1) {
                this.e.b = this.d.a;
                this.e.a(2);
                this.e.a();
                return;
            }
            this.e.a(1);
            this.e.a();
        } else if (str == "bonus/bind" && axVar.b() == 1) {
            this.e.a(3);
        }
    }
}
