package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.p;
import com.ecjia.component.view.ECJiaTopView;
import com.ecmoban.android.missmall.R;
import com.taobao.accs.common.Constants;
import com.umeng.message.PushAgent;
import de.greenrobot.event.c;

public class ECJiaOuterLoginBindActivity extends a {
    SharedPreferences a;
    Editor b;
    private Button c;
    private Button d;
    private TextView e;
    private TextView k;
    private ImageView l;

    class ECJiaOuterLoginBindActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaOuterLoginBindActivity a;

        ECJiaOuterLoginBindActivity_1(ECJiaOuterLoginBindActivity eCJiaOuterLoginBindActivity) {
            this.a = eCJiaOuterLoginBindActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaLoginBindActivity.class), 1001);
        }
    }

    class ECJiaOuterLoginBindActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaOuterLoginBindActivity a;

        ECJiaOuterLoginBindActivity_2(ECJiaOuterLoginBindActivity eCJiaOuterLoginBindActivity) {
            this.a = eCJiaOuterLoginBindActivity;
        }

        public void onClick(View view) {
            this.a.startActivityForResult(new Intent(this.a, ECJiaRegisterBindActivity.class), 1001);
        }
    }

    class ECJiaOuterLoginBindActivity_3 implements OnClickListener {
        final /* synthetic */ ECJiaOuterLoginBindActivity a;

        ECJiaOuterLoginBindActivity_3(ECJiaOuterLoginBindActivity eCJiaOuterLoginBindActivity) {
            this.a = eCJiaOuterLoginBindActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PushAgent.getInstance(this).onAppStart();
        setContentView(R.layout.activity_outer_login_bind);
        a();
        c.a().a((Object) this);
        this.a = getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.b = this.a.edit();
        this.l = (ImageView) findViewById(R.id.user_head_img);
        this.c = (Button) findViewById(R.id.btn_quick_register);
        this.d = (Button) findViewById(R.id.btn_quick_bind);
        this.k = (TextView) findViewById(R.id.tv_dear);
        this.e = (TextView) findViewById(R.id.tv_nick_name);
        if ("sns_qq".equals(this.a.getString("thirdWay", ""))) {
            this.k.setText(this.g.getString(R.string.dear_third_login_user).replace("#replace#", com.tencent.connect.common.Constants.SOURCE_QQ));
            this.e.setText(this.a.getString("myscreen_name", ""));
            p.a((Context) this).a(this.l, this.a.getString("qq_log_img", ""));
        } else if ("sns_wechat".equals(this.a.getString("thirdWay", ""))) {
            this.k.setText(this.g.getString(R.string.dear_third_login_user).replace("#replace#", this.g.getString(R.string.wechat)));
            this.e.setText(this.a.getString("nick_name", ""));
            p.a((Context) this).a(this.l, this.a.getString("wx_log_img", ""));
        }
        this.d.setOnClickListener(new ECJiaOuterLoginBindActivity_1(this));
        this.c.setOnClickListener(new ECJiaOuterLoginBindActivity_2(this));
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.bind_topview);
        this.i.setTitleText((int) R.string.third_login);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaOuterLoginBindActivity_3(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == -1) {
            setResult(-1);
            finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        return true;
    }

    protected void onDestroy() {
        c.a().b(this);
        super.onDestroy();
    }

    public void onEvent(b bVar) {
    }
}
