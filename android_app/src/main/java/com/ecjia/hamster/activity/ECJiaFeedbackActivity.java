package com.ecjia.hamster.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.k;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.c;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaFeedbackActivity extends a implements a {
    int[] a = new int[]{R.string.feedback_type_message, R.string.feedback_type_complain, R.string.feedback_type_enquire, R.string.feedback_type_customer_service, R.string.feedback_type_shopping_guide};
    boolean b = false;
    String c = "message";
    private k d;
    private LinearLayout e;
    private ImageView k;
    private EditText l;
    private EditText m;
    private TextView n;
    private TextView o;

    class ECJiaFeedbackActivity_10 implements OnClickListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_10(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public void onClick(View view) {
            this.a.c();
            this.a.a(this.a.l);
            this.a.a(this.a.m);
        }
    }

    class ECJiaFeedbackActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_1(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaFeedbackActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_3(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public void onClick(View view) {
            Object obj = this.a.l.getText().toString();
            String obj2 = this.a.m.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                com.ecjia.component.view.k kVar = new com.ecjia.component.view.k(this.a, "请输入您的意见或建议");
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            }
            this.a.d.a(this.a.c, obj, obj2);
        }
    }

    class ECJiaFeedbackActivity_4 implements OnTouchListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_4(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 1:
                    this.a.c();
                    break;
            }
            return true;
        }
    }

    class ECJiaFeedbackActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_5(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public void onClick(View view) {
            this.a.n.setText(this.a.a[0]);
            this.a.o.setText(this.a.a[0]);
            this.a.c = "message";
            this.a.c();
        }
    }

    class ECJiaFeedbackActivity_6 implements OnClickListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_6(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public void onClick(View view) {
            this.a.n.setText(this.a.a[1]);
            this.a.o.setText(this.a.a[1]);
            this.a.c = "complain";
            this.a.c();
        }
    }

    class ECJiaFeedbackActivity_7 implements OnClickListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_7(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public void onClick(View view) {
            this.a.n.setText(this.a.a[2]);
            this.a.o.setText(this.a.a[2]);
            this.a.c = "enquire";
            this.a.c();
        }
    }

    class ECJiaFeedbackActivity_8 implements OnClickListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_8(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public void onClick(View view) {
            this.a.n.setText(this.a.a[3]);
            this.a.o.setText(this.a.a[3]);
            this.a.c = "customer_service";
            this.a.c();
        }
    }

    class ECJiaFeedbackActivity_9 implements OnClickListener {
        final /* synthetic */ ECJiaFeedbackActivity a;

        ECJiaFeedbackActivity_9(ECJiaFeedbackActivity eCJiaFeedbackActivity) {
            this.a = eCJiaFeedbackActivity;
        }

        public void onClick(View view) {
            this.a.n.setText(this.a.a[4]);
            this.a.o.setText(this.a.a[4]);
            this.a.c = "shopping_guide";
            this.a.c();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_feedback);
        PushAgent.getInstance(this).onAppStart();
        b();
        this.d = new k(this);
        this.d.a((a) this);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.feedback_topview);
        this.i.setTitleText((int) R.string.main_suggest);
        this.i.setLeftType(5);
        this.i.setLeftOnClickListener(new ECJiaFeedbackActivity_1(this));
        this.i.setRightType(11);
        this.i.setRightText((int) R.string.feedback_submit, new ECJiaFeedbackActivity_3(this));
    }

    void b() {
        a();
        this.e = (LinearLayout) findViewById(R.id.feedback_type_list);
        this.e.setOnTouchListener(new ECJiaFeedbackActivity_4(this));
        this.n = (TextView) findViewById(R.id.feedback_type_left);
        this.n.setText(this.a[0]);
        this.o = (TextView) findViewById(R.id.feedback_type_right);
        this.o.setText(this.a[0]);
        ((TextView) findViewById(R.id.feedback_message)).setText(this.a[0]);
        ((TextView) findViewById(R.id.feedback_complain)).setText(this.a[1]);
        ((TextView) findViewById(R.id.feedback_enquire)).setText(this.a[2]);
        ((TextView) findViewById(R.id.feedback_customer_service)).setText(this.a[3]);
        ((TextView) findViewById(R.id.feedback_shopping_guide)).setText(this.a[4]);
        findViewById(R.id.feedback_message).setOnClickListener(new ECJiaFeedbackActivity_5(this));
        findViewById(R.id.feedback_complain).setOnClickListener(new ECJiaFeedbackActivity_6(this));
        findViewById(R.id.feedback_enquire).setOnClickListener(new ECJiaFeedbackActivity_7(this));
        findViewById(R.id.feedback_customer_service).setOnClickListener(new ECJiaFeedbackActivity_8(this));
        findViewById(R.id.feedback_shopping_guide).setOnClickListener(new ECJiaFeedbackActivity_9(this));
        this.k = (ImageView) findViewById(R.id.feedback_type_switcher);
        this.k.setOnClickListener(new ECJiaFeedbackActivity_10(this));
        this.l = (EditText) findViewById(R.id.feedback_content);
        this.m = (EditText) findViewById(R.id.feedback_contact);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    void c() {
        if (this.b) {
            this.b = false;
            this.k.setImageResource(R.drawable.search_showchild);
            this.e.setVisibility(8);
            this.n.setVisibility(4);
            this.o.setVisibility(0);
            return;
        }
        this.b = true;
        this.k.setImageResource(R.drawable.search_hidden);
        this.e.setVisibility(0);
        this.n.setVisibility(0);
        this.o.setVisibility(4);
    }

    public void finish() {
        a(this.l);
        a(this.m);
        super.finish();
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("feedback/create") && axVar.b() == 1) {
            final c cVar = new c(this, this.g.getString(R.string.feedback_succeed), this.g.getString(R.string.feedback_thank_for_suggest));
            cVar.a(1);
            cVar.a(new OnClickListener(this) {
                final /* synthetic */ ECJiaFeedbackActivity b;

                public void onClick(View view) {
                    cVar.b();
                    this.b.finish();
                }
            });
            cVar.a();
        }
    }
}
