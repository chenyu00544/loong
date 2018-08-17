package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ecjia.a.ab;
import com.ecjia.a.c;
import com.ecjia.a.y;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.f;
import com.ecjia.component.view.ECJiaSignView;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.adapter.br;
import com.ecjia.hamster.model.ax;
import com.ecjia.hamster.model.h;
import com.ecmoban.android.missmall.R;
import com.nineoldandroids.a.b;
import com.nineoldandroids.a.i;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import com.umeng.socialize.common.SocializeConstants;
import java.util.Date;

public class ECJiaCheckInActivity extends a implements OnClickListener, a {
    boolean a = true;
    TextView b;
    LinearLayout c;
    boolean d = false;
    boolean e = false;
    ECJiaSignView k;
    ECJiaSignView l;
    ECJiaSignView m;
    ECJiaSignView n;
    ECJiaSignView o;
    TextView p;
    TextView q;
    boolean r = false;
    private ECJiaTopView s;
    private View t;
    private f u;
    private ListView v;
    private br w;
    private ImageView x;

    class ECJiaCheckInActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaCheckInActivity a;

        ECJiaCheckInActivity_1(ECJiaCheckInActivity eCJiaCheckInActivity) {
            this.a = eCJiaCheckInActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaCheckInActivity_2 implements OnTouchListener {
        final /* synthetic */ ECJiaCheckInActivity a;

        ECJiaCheckInActivity_2(ECJiaCheckInActivity eCJiaCheckInActivity) {
            this.a = eCJiaCheckInActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    class ECJiaCheckInActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaCheckInActivity a;

        ECJiaCheckInActivity_3(ECJiaCheckInActivity eCJiaCheckInActivity) {
            this.a = eCJiaCheckInActivity;
        }

        public void onClick(View view) {
            if (this.a.d) {
                this.a.d = false;
                this.a.e();
                new k(this.a, (int) R.string.check_in_close).a();
                this.a.getSharedPreferences(Constants.KEY_USER_ID, 0).edit().putBoolean("sign_alarm", false).commit();
                this.a.x.setImageResource(R.drawable.address_nusetdefault);
                return;
            }
            this.a.d = true;
            this.a.c();
            new k(this.a, (int) R.string.check_in_open).a();
            this.a.getSharedPreferences(Constants.KEY_USER_ID, 0).edit().putBoolean("sign_alarm", true).commit();
            this.a.x.setImageResource(R.drawable.address_setdefault);
        }
    }

    class ECJiaCheckInActivity_4 implements OnScrollListener {
        final /* synthetic */ ECJiaCheckInActivity a;

        ECJiaCheckInActivity_4(ECJiaCheckInActivity eCJiaCheckInActivity) {
            this.a = eCJiaCheckInActivity;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 0 && this.a.e && this.a.u.h.a() == 1) {
                this.a.u.a();
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            ECJiaCheckInActivity eCJiaCheckInActivity = this.a;
            boolean z = i3 > 0 && i + i2 >= i3 - 1;
            eCJiaCheckInActivity.e = z;
        }
    }

    class ECJiaCheckInActivity_5 extends b {
        final /* synthetic */ ECJiaCheckInActivity a;

        ECJiaCheckInActivity_5(ECJiaCheckInActivity eCJiaCheckInActivity) {
            this.a = eCJiaCheckInActivity;
        }

        public void a(com.nineoldandroids.a.a aVar) {
            super.a(aVar);
            this.a.a = false;
            this.a.b.setEnabled(true);
            this.a.b.setText(R.string.calander_click_close);
        }
    }

    class ECJiaCheckInActivity_6 extends b {
        final /* synthetic */ ECJiaCheckInActivity a;

        ECJiaCheckInActivity_6(ECJiaCheckInActivity eCJiaCheckInActivity) {
            this.a = eCJiaCheckInActivity;
        }

        public void a(com.nineoldandroids.a.a aVar) {
            super.a(aVar);
            this.a.a = true;
            this.a.b.setEnabled(true);
            this.a.b.setText(R.string.calander_click_look);
        }
    }

    class ECJiaCheckInActivity_7 extends b {
        final /* synthetic */ ECJiaCheckInActivity a;

        ECJiaCheckInActivity_7(ECJiaCheckInActivity eCJiaCheckInActivity) {
            this.a = eCJiaCheckInActivity;
        }

        public void a(com.nineoldandroids.a.a aVar) {
            super.a(aVar);
            this.a.a = false;
            this.a.b.setEnabled(true);
            this.a.b.setText(R.string.calander_click_close);
            this.a.p.setText(Integer.valueOf(this.a.h.e().j()) + "");
            this.a.q.setText(this.a.u.e);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_signin);
        PushAgent.getInstance(this).onAppStart();
        f();
        b();
        this.u = new f(this);
        this.u.a((a) this);
        this.u.a("current");
    }

    private void f() {
        this.s = (ECJiaTopView) findViewById(R.id.lottery_topview);
        this.s.setLeftType(1);
        this.s.setRightType(13);
        this.s.setLeftOnClickListener(new ECJiaCheckInActivity_1(this));
        this.s.setTitleText((int) R.string.check_in);
        this.t = findViewById(R.id.calendar_view);
        this.t.setOnTouchListener(new ECJiaCheckInActivity_2(this));
        ((TextView) findViewById(R.id.calender_date)).setText(ab.a(new Date()));
        this.c = (LinearLayout) findViewById(R.id.calender_view_parent);
        c(this.c);
        this.x = (ImageView) findViewById(R.id.calender_checkbox);
        if (getSharedPreferences(Constants.KEY_USER_ID, 0).getBoolean("sign_alarm", false)) {
            this.d = true;
            this.x.setImageResource(R.drawable.address_setdefault);
        } else {
            this.d = false;
            this.x.setImageResource(R.drawable.address_nusetdefault);
        }
        this.x.setOnClickListener(new ECJiaCheckInActivity_3(this));
        this.b = (TextView) findViewById(R.id.calender_button);
        this.b.setOnClickListener(this);
        this.v = (ListView) findViewById(R.id.lottery_listview);
        this.v.setOnScrollListener(new ECJiaCheckInActivity_4(this));
    }

    private void c(LinearLayout linearLayout) {
        for (int i = 0; i < 6; i++) {
            View linearLayout2 = new LinearLayout(this);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, y.a(this, 30));
            layoutParams.gravity = 17;
            linearLayout2.setLayoutParams(layoutParams);
            linearLayout2.setOrientation(0);
            for (int i2 = 0; i2 < 7; i2++) {
                LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
                layoutParams2.gravity = 17;
                layoutParams2.weight = 1.0f;
                layoutParams2.setMargins(1, 1, 1, 1);
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.layout_signin_calender_item, null);
                frameLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                frameLayout.setLayoutParams(layoutParams2);
                linearLayout2.addView(frameLayout);
            }
            linearLayout.addView(linearLayout2);
        }
        a(linearLayout);
    }

    void a(LinearLayout linearLayout) {
        int a = c.a() - 1;
        int b = c.b();
        for (int i = a; i < b + a; i++) {
            ((TextView) ((FrameLayout) ((LinearLayout) linearLayout.getChildAt(i / 7)).getChildAt(i % 7)).getChildAt(0)).setText(((i - c.a()) + 2) + "");
        }
    }

    void b() {
        this.p = (TextView) findViewById(R.id.signin_record_myscore);
        this.k = (ECJiaSignView) findViewById(R.id.signrecord_days_one);
        this.l = (ECJiaSignView) findViewById(R.id.signrecord_days_two);
        this.m = (ECJiaSignView) findViewById(R.id.signrecord_days_three);
        this.n = (ECJiaSignView) findViewById(R.id.signrecord_days_four);
        this.o = (ECJiaSignView) findViewById(R.id.signrecord_days_five);
        this.q = (TextView) findViewById(R.id.signin_lable);
    }

    void b(LinearLayout linearLayout) {
        int c = c.c();
        int[] iArr = new int[(c * 7)];
        for (int i = 0; i < c * 7; i++) {
            iArr[i] = 0;
        }
        int size = this.u.f.size();
        for (c = 0; c < size; c++) {
            iArr[c.b(((h) this.u.f.get(c)).a() * 1000) - 1] = 1;
        }
        int a = c.a() - 1;
        int b = c.b();
        for (c = a; c < b + a; c++) {
            ImageView imageView = (ImageView) ((FrameLayout) ((LinearLayout) linearLayout.getChildAt(c / 7)).getChildAt(c % 7)).getChildAt(1);
            if (iArr[c] == 1) {
                imageView.setVisibility(0);
            }
        }
        int b2 = c.b(((h) this.u.f.get(size - 1)).a() * 1000);
        for (size = a; size < b + a; size++) {
            TextView textView = (TextView) ((FrameLayout) ((LinearLayout) linearLayout.getChildAt(size / 7)).getChildAt(size % 7)).getChildAt(2);
            c = b2 - 1;
            f fVar = this.u;
            int i2 = this.u.c;
            f fVar2 = this.u;
            if (size >= c + (5 - (i2 % 5))) {
                c = 1;
            } else {
                c = 0;
            }
            i2 = b2 - 1;
            fVar2 = this.u;
            int i3 = this.u.c;
            f fVar3 = this.u;
            i2 = size - (i2 + (5 - (i3 % 5)));
            fVar2 = this.u;
            if (i2 % 5 == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (!(c == 0 || r4 == 0)) {
                textView.setVisibility(0);
                textView.setText(SocializeConstants.OP_DIVIDER_PLUS + this.u.d);
            }
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void a() {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calender_button:
                int height;
                i a;
                if (this.a) {
                    this.b.setEnabled(false);
                    height = this.t.getHeight();
                    a = i.a(this.t, "translationY", 0.0f, Float.valueOf((float) (height - this.b.getHeight())).floatValue());
                    a.b(800);
                    a.a(new ECJiaCheckInActivity_5(this));
                    a.a();
                    return;
                }
                this.b.setEnabled(false);
                height = this.t.getHeight();
                a = i.a(this.t, "translationY", Float.valueOf((float) (height - this.b.getHeight())).floatValue(), 0.0f);
                a.b(800);
                a.a(new ECJiaCheckInActivity_6(this));
                a.a();
                return;
            default:
                return;
        }
    }

    void a(int i) {
        ((TextView) findViewById(R.id.calender_date_hundred)).setText((this.u.c / 100) + "");
        ((TextView) findViewById(R.id.calender_date_decade)).setText(((this.u.c % 100) / 10) + "");
        ((TextView) findViewById(R.id.calender_date_unit)).setText(((this.u.c % 100) % 10) + "");
        switch (i) {
            case 0:
                this.k.setSignType(true);
                this.l.setSignType(true);
                this.m.setSignType(true);
                this.n.setSignType(true);
                this.o.setSignType(true);
                return;
            case 1:
                this.k.setSignType(true);
                this.l.setSignType(false);
                this.m.setSignType(false);
                this.n.setSignType(false);
                this.o.setSignType(false);
                return;
            case 2:
                this.k.setSignType(true);
                this.l.setSignType(true);
                this.m.setSignType(false);
                this.n.setSignType(false);
                this.o.setSignType(false);
                return;
            case 3:
                this.k.setSignType(true);
                this.l.setSignType(true);
                this.m.setSignType(true);
                this.n.setSignType(false);
                this.o.setSignType(false);
                return;
            case 4:
                this.k.setSignType(true);
                this.l.setSignType(true);
                this.m.setSignType(true);
                this.n.setSignType(true);
                this.o.setSignType(false);
                return;
            default:
                this.k.setSignType(false);
                this.l.setSignType(false);
                this.m.setSignType(false);
                this.n.setSignType(false);
                this.o.setSignType(false);
                return;
        }
    }

    void c() {
        startService(new Intent(this, com.ecjia.component.service.a.class));
    }

    void e() {
        stopService(new Intent(this, com.ecjia.component.service.a.class));
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("mobile/checkin")) {
            if (axVar.b() == 1) {
                this.r = true;
            }
            getSharedPreferences(Constants.KEY_USER_ID, 0).edit().putLong("signin_time", System.currentTimeMillis()).commit();
            this.u.b("current");
        } else if (!str.equals("mobile/checkin/record") || axVar.b() != 1) {
        } else {
            if (axVar.a() == 1) {
                if (this.w == null) {
                    this.w = new br(this, this.u.g);
                    this.v.setAdapter(this.w);
                    return;
                }
                this.w.notifyDataSetChanged();
            } else if (axVar.a() == 0) {
                this.u.b("all");
                a(this.u.c % 5);
                b(this.c);
                if (!this.r) {
                    k kVar = new k((Context) this, (int) R.string.check_in_checked);
                    kVar.a(17, 0, 0);
                    kVar.a();
                    ((TextView) findViewById(R.id.calender_myscore)).setText(this.h.e().j());
                    ((TextView) findViewById(R.id.calender_award)).setVisibility(8);
                    this.p.setText(Integer.valueOf(this.h.e().j()) + "");
                    this.q.setText(this.u.e);
                } else if (!(this.h.e() == null || TextUtils.isEmpty(this.h.e().m()))) {
                    ((TextView) findViewById(R.id.calender_myscore)).setText(this.h.e().j());
                    ((TextView) findViewById(R.id.calender_award)).setText(SocializeConstants.OP_DIVIDER_PLUS + this.u.b);
                    ((TextView) findViewById(R.id.calender_award)).setVisibility(0);
                    this.h.e().a((Integer.valueOf(this.h.e().j()).intValue() + this.u.b) + "");
                    this.b.setEnabled(false);
                    int height = this.t.getHeight();
                    i a = i.a(this.t, "translationY", 0.0f, Float.valueOf((float) (height - this.b.getHeight())).floatValue());
                    a.b(800);
                    a.a(new ECJiaCheckInActivity_7(this));
                    a.a();
                }
                ((TextView) findViewById(R.id.calender_checkin_extra_day)).setText(this.u.e);
            }
        }
    }
}
