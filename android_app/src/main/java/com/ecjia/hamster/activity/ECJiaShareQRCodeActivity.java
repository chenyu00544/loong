package com.ecjia.hamster.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.p;
import com.ecjia.a.q;
import com.ecjia.a.y;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.t;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import de.greenrobot.event.c;

public class ECJiaShareQRCodeActivity extends a implements OnClickListener, a {
    public int a;
    public LayoutParams b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private t k;
    private ECJiaTopView l;
    private Button m;
    private EditText n;
    private LinearLayout o;
    private String p;
    private int q;
    private ScrollView r;
    private LinearLayout s;
    private BroadcastReceiver t = new ECJiaShareQRCodeActivity_4(this);

    class ECJiaShareQRCodeActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaShareQRCodeActivity a;

        ECJiaShareQRCodeActivity_1(ECJiaShareQRCodeActivity eCJiaShareQRCodeActivity) {
            this.a = eCJiaShareQRCodeActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaShareQRCodeActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaShareQRCodeActivity a;

        ECJiaShareQRCodeActivity_2(ECJiaShareQRCodeActivity eCJiaShareQRCodeActivity) {
            this.a = eCJiaShareQRCodeActivity;
        }

        public void onClick(View view) {
            this.a.startActivity(new Intent(this.a, ECJiaInvitationRecordActivity.class));
            this.a.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            this.a.a(this.a.n);
        }
    }

    class ECJiaShareQRCodeActivity_4 extends BroadcastReceiver {
        String a = "reason";
        String b = "homekey";
        String c = "recentapps";
        final /* synthetic */ ECJiaShareQRCodeActivity d;

        ECJiaShareQRCodeActivity_4(ECJiaShareQRCodeActivity eCJiaShareQRCodeActivity) {
            this.d = eCJiaShareQRCodeActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                CharSequence stringExtra = intent.getStringExtra(this.a);
                if (TextUtils.equals(stringExtra, this.b)) {
                    if (this.d.q == 0) {
                        this.d.finish();
                    }
                } else if (!TextUtils.equals(stringExtra, this.c)) {
                }
            }
        }
    }

    public void onCreate(Bundle bundle) {
        int width;
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_suggest);
        c.a().a((Object) this);
        registerReceiver(this.t, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        if (getWindowManager().getDefaultDisplay().getWidth() < getWindowManager().getDefaultDisplay().getHeight()) {
            width = getWindowManager().getDefaultDisplay().getWidth();
        } else {
            width = getWindowManager().getDefaultDisplay().getHeight();
        }
        this.a = width;
        b();
        this.k = new t(this);
        this.k.a((a) this);
        this.k.b();
    }

    private void b() {
        this.l = (ECJiaTopView) findViewById(R.id.suggest_topview);
        this.l.setTitleText((int) R.string.my_suggest);
        this.l.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaShareQRCodeActivity_1(this));
        this.l.setRightType(11);
        this.l.setRightText((int) R.string.invitation_reward, new ECJiaShareQRCodeActivity_2(this));
        this.e = (ImageView) findViewById(R.id.iv_share_qr);
        this.c = (TextView) findViewById(R.id.tv_invitation_code);
        this.d = (TextView) findViewById(R.id.tv_invitation_tips);
        this.n = (EditText) findViewById(R.id.et_invitation);
        this.r = (ScrollView) findViewById(R.id.sc_invite);
        this.s = (LinearLayout) findViewById(R.id.ll_my_suggest);
        this.o = (LinearLayout) findViewById(R.id.ll_share_qr);
        this.b = new LayoutParams(-1, -1);
        this.b.height = (this.a * 1) / 2;
        this.b.width = (this.a * 1) / 2;
        this.o.setLayoutParams(this.b);
        this.m = (Button) findViewById(R.id.btn_invite);
        this.m.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        a(this.s, this.m);
        this.q = getIntent().getIntExtra("startType", 0);
        q.a("startType===" + this.q);
    }

    public void finish() {
        super.finish();
        a(this.n);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_invite:
                c();
                return;
            default:
                return;
        }
    }

    private void c() {
        Intent intent = new Intent(this, ECJiaShareActivity.class);
        intent.putExtra("share_content", this.n.getText().toString());
        intent.putExtra("share_goods_url", this.p);
        intent.putExtra("share_goods_name", this.h.e().p() + "推荐这个实用的App给你～");
        startActivity(intent);
        overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
        a(this.n);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    private void a(final View view, final View view2) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ ECJiaShareQRCodeActivity c;

            public void onGlobalLayout() {
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                if (view.getRootView().getHeight() - rect.bottom > y.d(this.c)) {
                    view2.getLocationInWindow(new int[2]);
                    this.c.o.setVisibility(8);
                    return;
                }
                this.c.o.setVisibility(0);
            }
        });
    }

    public void onEvent(b bVar) {
        if ("not_from_widget".equals(bVar.c()) && this.q == 1) {
            finish();
        }
    }

    protected void onDestroy() {
        c.a().b(this);
        unregisterReceiver(this.t);
        super.onDestroy();
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("invite/user") && axVar.b() == 1) {
            p.a((Context) this).a(this.e, this.k.e.getInvite_qrcode_image());
            this.n.setText(this.k.e.getInvite_template());
            if (this.k.e.getInvite_template().length() > 0) {
                this.n.setSelection(this.k.e.getInvite_template().length());
            }
            this.d.setText(this.k.e.getInvite_explain());
            this.c.setText(this.k.e.getInvite_code());
            this.p = this.k.e.getInvite_url();
        }
    }
}
