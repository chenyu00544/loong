package com.ecjia.hamster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;

public class ECJiaShowPhoneActivity extends b {
    private TextView a;
    private TextView b;
    private TextView c;
    private String d;
    private ImageView e;

    class ECJiaShowPhoneActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaShowPhoneActivity a;

        ECJiaShowPhoneActivity_1(ECJiaShowPhoneActivity eCJiaShowPhoneActivity) {
            this.a = eCJiaShowPhoneActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaShowPhoneActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaShowPhoneActivity a;

        ECJiaShowPhoneActivity_2(ECJiaShowPhoneActivity eCJiaShowPhoneActivity) {
            this.a = eCJiaShowPhoneActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.a, ECJiaReplacePhoneActivity.class);
            intent.putExtra("type", this.a.d);
            intent.putExtra("textType", this.a.c.getText().toString());
            this.a.startActivityForResult(intent, 111);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.binding_phone_itme);
        this.d = getIntent().getStringExtra("type");
        b();
    }

    void b() {
        this.e = (ImageView) findViewById(R.id.top_view_back);
        this.e.setOnClickListener(new ECJiaShowPhoneActivity_1(this));
        this.b = (TextView) findViewById(R.id.top_view_text);
        this.c = (TextView) findViewById(R.id.phone_haoma);
        this.a = (TextView) findViewById(R.id.binding_exitLogin);
        if (this.d.equals("user_modify_mobile")) {
            this.b.setText("绑定手机号");
            this.c.setText(this.h.e().g());
            this.a.setText("更换手机号");
        } else if (this.d.equals("user_modify_mail")) {
            this.b.setText("绑定邮箱");
            this.c.setText(this.h.e().h());
            this.a.setText("更换邮箱");
        }
        this.a.setOnClickListener(new ECJiaShowPhoneActivity_2(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 111:
                if (i2 == -1 && intent != null) {
                    setResult(-1, new Intent());
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
