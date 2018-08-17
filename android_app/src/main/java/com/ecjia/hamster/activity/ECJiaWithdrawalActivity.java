package com.ecjia.hamster.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ecjia.a.k;
import com.ecjia.component.a.a.a;
import com.ecjia.component.a.aa;
import com.ecjia.component.a.al;
import com.ecjia.component.view.ECJiaTopView;
import com.ecjia.hamster.model.ax;
import com.ecmoban.android.missmall.R;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.lang3.c;

public class ECJiaWithdrawalActivity extends a implements a {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    EditText e;
    EditText k;
    Button l;
    aa m;
    al n;

    class ECJiaWithdrawalActivity_1 implements OnClickListener {
        final /* synthetic */ ECJiaWithdrawalActivity a;

        ECJiaWithdrawalActivity_1(ECJiaWithdrawalActivity eCJiaWithdrawalActivity) {
            this.a = eCJiaWithdrawalActivity;
        }

        public void onClick(View view) {
            this.a.c();
        }
    }

    class ECJiaWithdrawalActivity_2 implements OnClickListener {
        final /* synthetic */ ECJiaWithdrawalActivity a;

        ECJiaWithdrawalActivity_2(ECJiaWithdrawalActivity eCJiaWithdrawalActivity) {
            this.a = eCJiaWithdrawalActivity;
        }

        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.a.h.e().i())) {
                this.a.e.setText(k.c(this.a.h.e().i()));
                this.a.e.setSelection(this.a.e.length() - 1);
            }
        }
    }

    class ECJiaWithdrawalActivity_3 implements TextWatcher {
        final /* synthetic */ ECJiaWithdrawalActivity a;

        ECJiaWithdrawalActivity_3(ECJiaWithdrawalActivity eCJiaWithdrawalActivity) {
            this.a = eCJiaWithdrawalActivity;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.length() > 0) {
                this.a.d.setVisibility(8);
                this.a.e.setVisibility(0);
                this.a.e.setFocusable(true);
                this.a.e.setFocusableInTouchMode(true);
                this.a.e.requestFocus();
                if (k.a(editable.toString()) > 0.0f) {
                    this.a.l.setEnabled(true);
                    this.a.l.setTextColor(this.a.g.getColor(R.color.my_white));
                    return;
                }
                this.a.l.setEnabled(false);
                this.a.l.setTextColor(this.a.g.getColor(R.color.actionsheet_gray));
                return;
            }
            this.a.l.setEnabled(false);
            this.a.l.setTextColor(this.a.g.getColor(R.color.actionsheet_gray));
            this.a.d.setVisibility(0);
            this.a.e.setVisibility(8);
            this.a.e.setFocusable(false);
            this.a.e.setFocusableInTouchMode(false);
        }
    }

    class ECJiaWithdrawalActivity_4 implements OnTouchListener {
        final /* synthetic */ ECJiaWithdrawalActivity a;

        class ECJiaWithdrawalActivity_4_1 extends TimerTask {
            final /* synthetic */ ECJiaWithdrawalActivity_4 a;

            ECJiaWithdrawalActivity_4_1(ECJiaWithdrawalActivity_4 eCJiaWithdrawalActivity_4) {
                this.a = eCJiaWithdrawalActivity_4;
            }

            public void run() {
                ((InputMethodManager) this.a.a.e.getContext().getSystemService("input_method")).showSoftInput(this.a.a.e, 0);
            }
        }

        ECJiaWithdrawalActivity_4(ECJiaWithdrawalActivity eCJiaWithdrawalActivity) {
            this.a = eCJiaWithdrawalActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.d.setVisibility(8);
            this.a.e.setVisibility(0);
            this.a.e.setFocusable(true);
            this.a.e.setFocusableInTouchMode(true);
            this.a.e.requestFocus();
            new Timer().schedule(new ECJiaWithdrawalActivity_4_1(this), 300);
            return true;
        }
    }

    class ECJiaWithdrawalActivity_5 implements OnClickListener {
        final /* synthetic */ ECJiaWithdrawalActivity a;

        ECJiaWithdrawalActivity_5(ECJiaWithdrawalActivity eCJiaWithdrawalActivity) {
            this.a = eCJiaWithdrawalActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_withdrawal);
        b();
    }

    protected void onResume() {
        super.onResume();
        this.n.a();
    }

    private void b() {
        a();
        this.m = new aa(this);
        this.m.a((a) this);
        this.n = new al(this);
        this.n.a((a) this);
        this.a = (TextView) findViewById(R.id.user_money);
        this.b = (TextView) findViewById(R.id.tv_withdraw_all);
        this.c = (TextView) findViewById(R.id.tv_bank_info);
        this.d = (TextView) findViewById(R.id.input_money_hint);
        this.e = (EditText) findViewById(R.id.input_money);
        this.k = (EditText) findViewById(R.id.information_context);
        this.l = (Button) findViewById(R.id.withdrawal_ok);
        this.l.setOnClickListener(new ECJiaWithdrawalActivity_1(this));
        this.b.setOnClickListener(new ECJiaWithdrawalActivity_2(this));
        this.e.addTextChangedListener(new ECJiaWithdrawalActivity_3(this));
        this.d.setOnTouchListener(new ECJiaWithdrawalActivity_4(this));
    }

    public void a() {
        super.a();
        this.i = (ECJiaTopView) findViewById(R.id.withdrawal_topview);
        this.i.setTitleText((int) R.string.withdraw);
        this.i.setLeftBackImage((int) R.drawable.header_back_arrow, new ECJiaWithdrawalActivity_5(this));
    }

    private void c() {
        com.ecjia.component.view.k kVar;
        if (c.a(this.e.getText().toString())) {
            kVar = new com.ecjia.component.view.k((Context) this, (int) R.string.not_null);
            kVar.a(17, 0, 0);
            kVar.a();
        } else if (Float.valueOf(this.h.e().i().replace("￥", "").replace("元", "").replace("¥", "")).floatValue() < Float.valueOf(this.e.getText().toString()).floatValue()) {
            kVar = new com.ecjia.component.view.k((Context) this, (int) R.string.too_large);
            kVar.a(17, 0, 0);
            kVar.a();
        } else {
            this.m.b(this.e.getText().toString(), this.k.getText().toString());
        }
    }

    public void finish() {
        super.finish();
        a(this.e);
    }

    public void a(String str, String str2, ax axVar) {
        if (str.equals("user/account/raply")) {
            if (axVar.b() == 1) {
                finish();
                startActivity(new Intent(this, ECJiaWithdrawDetaiActivity.class));
            }
        } else if (str.equals("user/info") && axVar.b() == 1) {
            CharSequence charSequence;
            this.a.setText(this.h.e().i());
            String b = this.h.e().u().b();
            if (b.length() >= 4) {
                charSequence = this.h.e().u().c() + "(" + b.substring(b.length() - 4, b.length()) + ")";
            } else {
                charSequence = this.h.e().u().c();
            }
            this.c.setText(charSequence);
            this.d.setHint("可转出到卡" + this.h.e().i());
        }
    }
}
