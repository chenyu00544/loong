package com.ecjia.hamster.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecjia.a.a.b;
import com.ecjia.a.ab;
import com.ecjia.a.q;
import com.ecjia.a.w;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.al;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.component.view.k;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import de.greenrobot.event.c;

public class ECJiaChangeUsernameActivity extends b implements a {
    private ECJiaTopView a;
    private EditText b;
    private TextView c;
    private TextView d;
    private al e;
    private String k;
    private String l;
    private String m;
    private ImageView n;
    private TextView o;
    private TextView p;

    class ECJiaChangeUsernameActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaChangeUsernameActivity a;

        ECJiaChangeUsernameActivity_1(ECJiaChangeUsernameActivity eCJiaChangeUsernameActivity) {
            this.a = eCJiaChangeUsernameActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class ECJiaChangeUsernameActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaChangeUsernameActivity a;

        ECJiaChangeUsernameActivity_2(ECJiaChangeUsernameActivity eCJiaChangeUsernameActivity) {
            this.a = eCJiaChangeUsernameActivity;
        }

        public void onClick(View view) {
            this.a.k = this.a.b.getText().toString();
            k kVar;
            if (!w.a(this.a.k)) {
                kVar = new k(this.a, "用户名格式错误");
                kVar.a(17, 0, 0);
                kVar.a();
            } else if (this.a.k.equals(this.a.l)) {
                kVar = new k(this.a, "新旧用户名一样");
                kVar.a(17, 0, 0);
                kVar.a();
            } else {
                this.a.e.b(this.a.k);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.act_change_username);
        this.b = (EditText) findViewById(R.id.et_username);
        this.c = (TextView) findViewById(R.id.tv_changname_disable);
        this.d = (TextView) findViewById(R.id.tv_changname_tips);
        this.e = new al(this);
        this.e.a((a) this);
        b();
    }

    void b() {
        if (!(this.h.e() == null || TextUtils.isEmpty(this.h.e().m()) || TextUtils.isEmpty(this.h.e().a()))) {
            this.l = this.h.e().a();
            if (!TextUtils.isEmpty(this.h.e().c())) {
                this.m = this.h.e().c();
            }
            this.b.setText(this.l);
            if (this.l.length() > 0) {
                this.b.setSelection(this.l.length());
            }
        }
        this.n = (ImageView) findViewById(R.id.top_view_back);
        this.n.setOnClickListener(new ECJiaChangeUsernameActivity_1(this));
        this.o = (TextView) findViewById(R.id.top_view_text);
        this.o.setText("修改昵称");
        this.p = (TextView) findViewById(R.id.top_right_save);
        this.p.setText("保存");
        this.p.setVisibility(0);
        this.p.setOnClickListener(new ECJiaChangeUsernameActivity_2(this));
        if (TextUtils.isEmpty(this.m)) {
            b(this.b);
        } else if (ab.c(this.m)) {
            b(this.b);
        } else {
            this.b.setTextColor(this.g.getColor(R.color.TextColorHint));
            this.b.setEnabled(false);
            this.c.setVisibility(0);
            this.a.setRightType(13);
            this.d.setText(this.g.getString(R.string.input_username_tips4) + this.g.getString(R.string.input_username_update_time) + this.m);
        }
    }

    public void a(String str, String str2, ax axVar) {
        if (str != "user/update") {
            return;
        }
        if (axVar.b() == 1) {
            q.a("===111");
            c.a().c(new b("CHANGE_USERNAME"));
            setResult(-1);
            finish();
            return;
        }
        k kVar = new k((Context) this, axVar.d());
        kVar.a(17, 0, 0);
        kVar.a();
    }
}
