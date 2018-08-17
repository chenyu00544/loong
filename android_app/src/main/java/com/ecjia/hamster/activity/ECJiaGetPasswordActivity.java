package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.j;
import com.ecjia.component.a.m;
import com.ecjia.component.view.d;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ECJiaGetPasswordActivity extends a implements TextWatcher, a {
    public k a;
    private ImageView b;
    private EditText c;
    private Button d;
    private d e;
    private m k;
    private LinearLayout l;

    class ECJiaGetPasswordActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaGetPasswordActivity a;

        ECJiaGetPasswordActivity_1(ECJiaGetPasswordActivity eCJiaGetPasswordActivity) {
            this.a = eCJiaGetPasswordActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaGetPasswordActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaGetPasswordActivity a;

        ECJiaGetPasswordActivity_2(ECJiaGetPasswordActivity eCJiaGetPasswordActivity) {
            this.a = eCJiaGetPasswordActivity;
        }

        public void onClick(View view) {
            String obj = this.a.c.getText().toString();
            if (ECJiaGetPasswordActivity.a(obj)) {
                if (this.a.k.a != null) {
                    this.a.k.b("mobile", obj);
                    this.a.e.show();
                }
            } else if (obj == null || obj == "") {
                this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_null));
                this.a.a.a(17, 0, 0);
                this.a.a.a();
            } else {
                this.a.a = new k(this.a, this.a.g.getString(R.string.register_num_format));
                this.a.a.a(17, 0, 0);
                this.a.a.a();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_get_password);
        b();
    }

    private void b() {
        this.l = (LinearLayout) findViewById(R.id.root_view);
        if (j.a().b != null) {
            this.l.setBackgroundDrawable(j.a().b);
        }
        this.e = d.a((Context) this);
        this.k = new m(this);
        this.k.a((a) this);
        this.k.b();
        this.b = (ImageView) findViewById(R.id.back);
        this.b.setOnClickListener(new ECJiaGetPasswordActivity_1(this));
        this.c = (EditText) findViewById(R.id.get_password_edit);
        this.c.addTextChangedListener(this);
        this.d = (Button) findViewById(R.id.get_password_next);
        this.d.setOnClickListener(new ECJiaGetPasswordActivity_2(this));
    }

    public static boolean a(String str) {
        Matcher matcher = Pattern.compile("(1)\\d{10}$").matcher(str);
        System.out.println(matcher.matches() + "---");
        return matcher.matches();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() == 11) {
            this.d.setEnabled(true);
            this.d.setTextColor(Color.parseColor("#ffffffff"));
            this.d.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.d.setEnabled(false);
        this.d.setTextColor(Color.parseColor("#ff999999"));
        this.d.setBackgroundResource(R.drawable.shape_unable);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.c.getText().toString().length() == 11) {
            this.d.setEnabled(true);
            this.d.setTextColor(Color.parseColor("#ffffffff"));
            this.d.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.d.setEnabled(false);
        this.d.setTextColor(Color.parseColor("#ff999999"));
        this.d.setBackgroundResource(R.drawable.shape_unable);
    }

    public void afterTextChanged(Editable editable) {
        if (this.c.getText().toString().length() == 11) {
            this.d.setEnabled(true);
            this.d.setTextColor(Color.parseColor("#ffffffff"));
            this.d.setBackgroundResource(R.drawable.selector_login_button);
            return;
        }
        this.d.setEnabled(false);
        this.d.setTextColor(Color.parseColor("#ff999999"));
        this.d.setBackgroundResource(R.drawable.shape_unable);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/forget_password")) {
            this.e.dismiss();
            if (axVar.b() == 1) {
                Intent intent = new Intent(this, ECJiaPostSMSActivity.class);
                intent.putExtra("mobile", this.c.getText().toString());
                startActivity(intent);
            }
        } else if (str.equals("shop/token")) {
            this.e.dismiss();
        }
    }
}
