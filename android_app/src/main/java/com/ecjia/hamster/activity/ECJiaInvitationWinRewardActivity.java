package com.ecjia.hamster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ecjia.a.a.b;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.R;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaInvitationWinRewardActivity extends a implements OnClickListener {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;

    class ECJiaInvitationWinRewardActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaInvitationWinRewardActivity a;

        ECJiaInvitationWinRewardActivity_1(ECJiaInvitationWinRewardActivity eCJiaInvitationWinRewardActivity) {
            this.a = eCJiaInvitationWinRewardActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_win_reward);
        PushAgent.getInstance(this).onAppStart();
        b();
    }

    private void b() {
        a();
        this.a = (ImageView) findViewById(R.id.iv_win_reward1);
        this.b = (ImageView) findViewById(R.id.iv_win_reward2);
        this.c = (ImageView) findViewById(R.id.iv_win_reward3);
        this.d = (ImageView) findViewById(R.id.iv_win_reward4);
        this.e = (ImageView) findViewById(R.id.iv_win_reward5);
        a(this.a);
        a(this.d);
        a(this.e);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    void a(ImageView imageView) {
        int dimension = (int) this.g.getDimension(R.dimen.dp_10);
        int d = d() - (dimension * 2);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(d, (d * 10) / 27);
        layoutParams.setMargins(dimension, dimension, dimension, 0);
        imageView.setLayoutParams(layoutParams);
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.invitation_topview);
        this.i.setTitleText((int) R.string.invitation_get_reward);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaInvitationWinRewardActivity_1(this));
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_win_reward1:
                if (getIntent().getStringExtra("activity_name") == null || getIntent().getStringExtra("activity_name").equals(ECJiaInvitationRecordActivity.class.getName())) {
                    setResult(-1);
                    finish();
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    return;
                }
                intent = new Intent(this, ECJiaShareQRCodeActivity.class);
                intent.putExtra("startType", 1);
                startActivity(intent);
                return;
            case R.id.iv_win_reward4:
                c.a().c(new b("WINREWARD_ECJIAMAIN"));
                intent = new Intent(this, ECJiaMainActivity.class);
                intent.setFlags(67108864);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return;
            case R.id.iv_win_reward5:
                intent = new Intent(this, ECJiaOrderListActivity.class);
                intent.putExtra("order_type", "allow_comment");
                startActivity(intent);
                return;
            default:
                return;
        }
    }
}
