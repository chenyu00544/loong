package com.ecjia.hamster.module.goodsReturn.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ecjia.component.view.ECJiaErrorView;
import com.ecjia.component.view.ECJiaXListView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.activity.a;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.module.goodsReturn.a.c;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import java.util.Timer;
import java.util.TimerTask;

public class ECJiaSearchInputActivity extends a implements OnClickListener, com.ecjia.component.a.a.a, com.ecjia.hamster.b.a {
    String a;
    private LinearLayout b;
    private EditText c;
    private ECJiaXListView d;
    private com.ecjia.hamster.module.goodsReturn.a e;
    private c k;
    private ECJiaErrorView l;

    class ECJiaSearchInputActivity_1 implements OnTouchListener {
        final /* synthetic */ ECJiaSearchInputActivity a;

        ECJiaSearchInputActivity_1(ECJiaSearchInputActivity eCJiaSearchInputActivity) {
            this.a = eCJiaSearchInputActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.finish();
            return false;
        }
    }

    class ECJiaSearchInputActivity_2 implements OnTouchListener {
        final /* synthetic */ ECJiaSearchInputActivity a;

        ECJiaSearchInputActivity_2(ECJiaSearchInputActivity eCJiaSearchInputActivity) {
            this.a = eCJiaSearchInputActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.finish();
            return false;
        }
    }

    class ECJiaSearchInputActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaSearchInputActivity a;

        ECJiaSearchInputActivity_3(ECJiaSearchInputActivity eCJiaSearchInputActivity) {
            this.a = eCJiaSearchInputActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaSearchInputActivity_4 implements OnEditorActionListener {
        final /* synthetic */ ECJiaSearchInputActivity a;

        ECJiaSearchInputActivity_4(ECJiaSearchInputActivity eCJiaSearchInputActivity) {
            this.a = eCJiaSearchInputActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (TextUtils.isEmpty(textView.getText())) {
                new k(this.a, this.a.getBaseContext().getResources().getString(R.string.search_please_input)).a();
            } else {
                this.a.a(this.a.c);
                if (i == 3) {
                    this.a.a = textView.getText().toString();
                    this.a.e.c(this.a.a);
                }
            }
            return false;
        }
    }

    class ECJiaSearchInputActivity_5 extends TimerTask {
        final /* synthetic */ ECJiaSearchInputActivity a;

        ECJiaSearchInputActivity_5(ECJiaSearchInputActivity eCJiaSearchInputActivity) {
            this.a = eCJiaSearchInputActivity;
        }

        public void run() {
            ((InputMethodManager) this.a.c.getContext().getSystemService("input_method")).showSoftInput(this.a.c, 0);
            this.a.f.sendEmptyMessage(0);
        }
    }

    class ECJiaSearchInputActivity_6 implements ECJiaXListView.a {
        final /* synthetic */ ECJiaSearchInputActivity a;

        ECJiaSearchInputActivity_6(ECJiaSearchInputActivity eCJiaSearchInputActivity) {
            this.a = eCJiaSearchInputActivity;
        }

        public void a(int i) {
            this.a.e.c(this.a.c.getText().toString());
        }

        public void b(int i) {
            this.a.e.d(this.a.c.getText().toString());
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_search_input);
        PushAgent.getInstance(this).onAppStart();
        b();
        c();
    }

    void b() {
        this.e = new com.ecjia.hamster.module.goodsReturn.a(this);
        this.e.a((com.ecjia.component.a.a.a) this);
    }

    private void c() {
        this.l = (ECJiaErrorView) findViewById(R.id.null_pager);
        this.l.setOnTouchListener(new ECJiaSearchInputActivity_1(this));
        findViewById(R.id.order_search_empty).setOnTouchListener(new ECJiaSearchInputActivity_2(this));
        findViewById(R.id.tv_search_cancel).setOnClickListener(new ECJiaSearchInputActivity_3(this));
        this.b = (LinearLayout) findViewById(R.id.fl_search_top);
        this.b.setBackgroundColor(-2236963);
        this.c = (EditText) findViewById(R.id.et_search_input);
        this.c.setFocusable(true);
        this.c.setFocusableInTouchMode(true);
        this.c.requestFocus();
        this.c.setOnEditorActionListener(new ECJiaSearchInputActivity_4(this));
        ((InputMethodManager) this.c.getContext().getSystemService("input_method")).showSoftInput(this.c, 0);
        new Timer().schedule(new ECJiaSearchInputActivity_5(this), 300);
        this.d = (ECJiaXListView) findViewById(R.id.order_search_list);
        this.d.setPullLoadEnable(true);
        this.d.setRefreshTime();
        this.d.setXListViewListener(new ECJiaSearchInputActivity_6(this), 1);
    }

    @TargetApi(11)
    public void onClick(View view) {
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animation_4, 0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 2);
        return super.onTouchEvent(motionEvent);
    }

    public void a(int i, int i2, final Object obj) {
        if (obj instanceof String) {
            final com.ecjia.component.view.c cVar = new com.ecjia.component.view.c(this, "", "确定取消本次售后申请吗？");
            cVar.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaSearchInputActivity c;

                public void onClick(View view) {
                    this.c.e.a((String) obj);
                    cVar.b();
                }
            });
            cVar.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ECJiaSearchInputActivity b;

                public void onClick(View view) {
                    cVar.b();
                }
            });
            cVar.a();
        }
    }

    public void a(String str, String str2, ax axVar) {
        if ("order/return/list".equals(str)) {
            if (axVar.b() == 1) {
                if (this.k == null) {
                    this.k = new c(this, this.e.e);
                    this.k.a((com.ecjia.hamster.b.a) this);
                    this.d.setAdapter(this.k);
                } else {
                    this.k.notifyDataSetChanged();
                }
                if (this.e.a(this.e.d)) {
                    this.d.setPullLoadEnable(true);
                } else {
                    this.d.setPullLoadEnable(false);
                }
                if (this.e.e.size() > 0) {
                    this.l.setVisibility(8);
                    this.d.setVisibility(0);
                    return;
                }
                this.l.setVisibility(0);
                this.d.setVisibility(8);
            }
        } else if ("order/return/cancel".equals(str)) {
            this.e.c(this.a);
        }
    }
}
