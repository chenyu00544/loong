package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.b;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.t;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.c;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;

public class ECJiaInvitationRecordActivity extends a implements OnClickListener, a {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private TextView k;
    private FrameLayout l;
    private FrameLayout m;
    private t n;
    private c o;

    class ECJiaInvitationRecordActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaInvitationRecordActivity a;

        ECJiaInvitationRecordActivity_1(ECJiaInvitationRecordActivity eCJiaInvitationRecordActivity) {
            this.a = eCJiaInvitationRecordActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaInvitationRecordActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaInvitationRecordActivity a;

        ECJiaInvitationRecordActivity_2(ECJiaInvitationRecordActivity eCJiaInvitationRecordActivity) {
            this.a = eCJiaInvitationRecordActivity;
        }

        public void onClick(View view) {
            this.a.o.b();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_invitation_record);
        PushAgent.getInstance(this).onAppStart();
        b();
        this.n = new t(this);
        this.n.a((a) this);
        this.n.a();
    }

    private void b() {
        a();
        this.d = (TextView) findViewById(R.id.tv_points_reward);
        this.e = (TextView) findViewById(R.id.tv_redpacket_reward);
        this.k = (TextView) findViewById(R.id.tv_cash_reward);
        this.a = (ImageView) findViewById(R.id.iv_points_reward);
        this.b = (ImageView) findViewById(R.id.iv_redpacket_reward);
        this.c = (ImageView) findViewById(R.id.iv_cash_reward);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.l = (FrameLayout) findViewById(R.id.fl_invitation_detail);
        this.l.setOnClickListener(this);
        this.m = (FrameLayout) findViewById(R.id.fl_get_reward);
        this.m.setOnClickListener(this);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.invitation_topview);
        this.i.setTitleText((int) R.string.invitation_reward);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaInvitationRecordActivity_1(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_points_reward:
                a(1);
                return;
            case R.id.iv_redpacket_reward:
                a(2);
                return;
            case R.id.iv_cash_reward:
                a(3);
                return;
            case R.id.fl_invitation_detail:
                if (this.n.c.size() > 0) {
                    Intent intent = new Intent(this, ECJiaInvitationRewardActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("rewards", this.n.c);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return;
                }
                k kVar = new k((Context) this, this.g.getString(R.string.invitation_reward_detail_tips));
                kVar.a(17, 0, 0);
                kVar.a();
                return;
            case R.id.fl_get_reward:
                startActivityForResult(new b(this, ECJiaInvitationWinRewardActivity.class), 100);
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1) {
            finish();
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    private void a(int i) {
        String string = this.g.getString(R.string.reward_tips);
        String str = "";
        switch (i) {
            case 1:
                str = this.g.getString(R.string.reward_tips_content);
                break;
            case 2:
                str = this.g.getString(R.string.reward_tips_content2);
                break;
            case 3:
                str = this.g.getString(R.string.reward_tips_content3);
                break;
        }
        this.o = new c(this, string, str);
        this.o.a(1);
        this.o.a(new ECJiaInvitationRecordActivity_2(this));
        this.o.a();
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("invite/reward") && axVar.b() == 1) {
            this.d.setText(this.n.d.getInvite_integral_reward() + "");
            this.e.setText(this.n.d.getInvite_bouns_reward() + "");
            this.k.setText(this.n.d.getInvite_balance_reward() + "");
        }
    }
}
