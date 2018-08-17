package com.ecjia.hamster.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ecjia.a.b;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.al;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaMyPurseActivity extends a implements a {
    private ImageView a;
    private LinearLayout b;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private TextView k;
    private TextView l;
    private TextView m;
    private LinearLayout n;
    private ECJiaTopView o;

    class ECJiaMyPurseActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaMyPurseActivity a;

        ECJiaMyPurseActivity_1(ECJiaMyPurseActivity eCJiaMyPurseActivity) {
            this.a = eCJiaMyPurseActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaMyPurseActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaMyPurseActivity a;

        ECJiaMyPurseActivity_2(ECJiaMyPurseActivity eCJiaMyPurseActivity) {
            this.a = eCJiaMyPurseActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaAccountActivity.class));
        }
    }

    class ECJiaMyPurseActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaMyPurseActivity a;

        ECJiaMyPurseActivity_3(ECJiaMyPurseActivity eCJiaMyPurseActivity) {
            this.a = eCJiaMyPurseActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaRedpapperListActivity.class));
        }
    }

    class ECJiaMyPurseActivity_4 implements OnClickListener {
        final /* synthetic */ ECJiaMyPurseActivity a;

        ECJiaMyPurseActivity_4(ECJiaMyPurseActivity eCJiaMyPurseActivity) {
            this.a = eCJiaMyPurseActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaAddRedpaperActivity.class));
        }
    }

    class ECJiaMyPurseActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaMyPurseActivity a;

        ECJiaMyPurseActivity_5(ECJiaMyPurseActivity eCJiaMyPurseActivity) {
            this.a = eCJiaMyPurseActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new b(this.a, ECJiaInvitationWinRewardActivity.class));
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_purse);
        PushAgent.getInstance(this).onAppStart();
        c.a().a((Object) this);
        b();
    }

    protected void onResume() {
        super.onResume();
    }

    private void b() {
        this.o = (ECJiaTopView) findViewById(R.id.mypurse_topview);
        this.o.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaMyPurseActivity_1(this));
        this.o.setTitleText((int) R.string.purse_mypurse);
        this.o.setRightType(13);
        this.b = (LinearLayout) findViewById(R.id.mypurse_purse_item);
        this.c = (LinearLayout) findViewById(R.id.mypurse_redpaper_item);
        this.d = (LinearLayout) findViewById(R.id.mypurse_integral_item);
        this.e = (LinearLayout) findViewById(R.id.mypurse_win_integral_item);
        this.n = (LinearLayout) findViewById(R.id.add_redpaper_item);
        this.k = (TextView) findViewById(R.id.my_purse);
        this.l = (TextView) findViewById(R.id.my_redpaper);
        this.a = (ImageView) findViewById(R.id.iv_redpager);
        this.m = (TextView) findViewById(R.id.my_integral);
        this.b.setOnClickListener(new ECJiaMyPurseActivity_2(this));
        this.c.setOnClickListener(new ECJiaMyPurseActivity_3(this));
        this.n.setOnClickListener(new ECJiaMyPurseActivity_4(this));
        this.e.setOnClickListener(new ECJiaMyPurseActivity_5(this));
        if (this.h.e() != null && !TextUtils.isEmpty(this.h.e().m())) {
            c();
            al alVar = new al(this);
            alVar.a((a) this);
            alVar.a();
        }
    }

    private void c() {
        this.k.setText(this.h.e().i());
        this.l.setText(this.h.e().k());
        this.m.setText(this.h.e().j());
        this.b.setEnabled(true);
        this.c.setEnabled(true);
        this.d.setEnabled(true);
        if ("0".equals(this.h.e().k())) {
            this.c.setEnabled(false);
            this.a.setVisibility(8);
            return;
        }
        this.c.setEnabled(true);
        this.m.setVisibility(0);
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(com.ecjia.a.a.b bVar) {
        if ("changed".equals(bVar.c())) {
            this.k.setText(this.h.e().i());
        }
        if (!"red_paper_refresh".equals(bVar.c())) {
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (!str.equals("user/info")) {
            return;
        }
        if (axVar.b() == 1) {
            c();
            return;
        }
        this.b.setEnabled(false);
        this.c.setEnabled(false);
        this.d.setEnabled(false);
        this.a.setVisibility(4);
    }
}
